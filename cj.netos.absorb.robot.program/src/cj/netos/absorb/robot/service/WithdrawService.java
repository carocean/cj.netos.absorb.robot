package cj.netos.absorb.robot.service;

import cj.netos.absorb.robot.*;
import cj.netos.absorb.robot.distributes.OnWithdrawHubDistribute;
import cj.netos.absorb.robot.mapper.WithdrawRecordMapper;
import cj.netos.absorb.robot.model.WithdrawRecord;
import cj.netos.absorb.robot.util.IdWorker;
import cj.netos.absorb.robot.util.RobotUtils;
import cj.netos.rabbitmq.IRabbitMQProducer;
import cj.studio.ecm.IServiceSite;
import cj.studio.ecm.annotation.CjBridge;
import cj.studio.ecm.annotation.CjService;
import cj.studio.ecm.annotation.CjServiceRef;
import cj.studio.ecm.annotation.CjServiceSite;
import cj.studio.ecm.net.CircuitException;
import cj.studio.openport.util.Encript;
import cj.studio.orm.mybatis.annotation.CjTransaction;
import cj.ultimate.gson2.com.google.gson.Gson;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@CjBridge(aspects = "@transaction")
@CjService(name = "withdrawService")
public class WithdrawService implements IWithdrawService {
    @CjServiceRef(refByName = "mybatis.cj.netos.absorb.robot.mapper.WithdrawRecordMapper")
    WithdrawRecordMapper withdrawRecordMapper;
    @CjServiceSite
    IServiceSite site;
    @CjServiceRef
    IHubService hubService;

    @CjServiceRef(refByName = "@.rabbitmq.producer.distributeAbsorbsToWallet")
    IRabbitMQProducer rabbitMQProducer;

    @CjTransaction
    @Override
    public void doRequest(String bankid, String shunter, String alias, long amount) throws CircuitException {
        WithdrawRecord record = new WithdrawRecord();
        record.setAlias(alias);
        record.setBankid(bankid);
        record.setCtime(RobotUtils.dateTimeToMicroSecond(System.currentTimeMillis()));
        record.setReqAmount(amount);
        record.setShunter(shunter);
        record.setWithdrawer("absorbRobot@system.netos");
        record.setSn(new IdWorker().nextId());
        record.setStatus("200");
        record.setMessage("ok");
        record.setPersonName("洇取机器");
        record.setState(0);
        withdrawRecordMapper.insert(record);
        Map<String, Object> response = null;
        try {
            response = _call_bank(record);
        } catch (CircuitException e) {
            withdrawRecordMapper.updateStatus(record.getSn(), e.getStatus(), e.getMessage());
            throw e;
        }
        withdrawRecordMapper.updateRefsn(record.getSn(), (String) response.get("outTradeSn"));
    }

    private Map<String, Object> _call_bank(WithdrawRecord record) throws CircuitException {
        OkHttpClient client = (OkHttpClient) site.getService("@.http");

        String appid = site.getProperty("appid");
        String appKey = site.getProperty("appKey");
        String appSecret = site.getProperty("appSecret");
        String portsurl = site.getProperty("rhub.ports.wybank.trade");

        String nonce = Encript.md5(String.format("%s%s", UUID.randomUUID().toString(), System.currentTimeMillis()));
        String sign = Encript.md5(String.format("%s%s%s", appKey, nonce, appSecret));

        String url = String.format("%s" +
                        "?wenyBankID=%s" +
                        "&shunter=%s" +
                        "&withdrawer=%s" +
                        "&withdrawerName=%s" +
                        "&req_amount=%s" +
                        "&out_trade_sn=%s" +
                        "&note=%s", portsurl,
                record.getBankid(),
                record.getShunter(),
                record.getWithdrawer(),
                record.getPersonName(),
                record.getReqAmount(),
                record.getSn(),
                "网络洇金提取器提取");
        final Request request = new Request.Builder()
                .url(url)
                .addHeader("Rest-Command", "withdraw")
                .addHeader("app-id", appid)
                .addHeader("app-key", appKey)
                .addHeader("app-nonce", nonce)
                .addHeader("app-sign", sign)
                .get()
                .build();
        final Call call = client.newCall(request);
        Response response = null;
        try {
            response = call.execute();
        } catch (IOException e) {
            throw new CircuitException("1002", e);
        }
        if (response.code() >= 400) {
            throw new CircuitException("1002", String.format("远程访问失败:%s", response.message()));
        }
        String json = null;
        try {
            json = response.body().string();
        } catch (IOException e) {
            throw new CircuitException("1002", e);
        }
        Map<String, Object> map = new Gson().fromJson(json, HashMap.class);
        if (Double.parseDouble(map.get("status") + "") >= 400) {
            throw new CircuitException(map.get("status") + "", map.get("message") + "");
        }
        json = (String) map.get("dataText");
        return new Gson().fromJson(json, HashMap.class);
    }

    @CjTransaction
    @Override
    public void doResponse(BankWithdrawResult result) throws CircuitException {
        withdrawRecordMapper.done(result.getOutTradeSn(), result.getRealAmount(), RobotUtils.dateTimeToMicroSecond(System.currentTimeMillis()));
        IHubDistribute<BankWithdrawResult> onWithdrawDistributeService = new OnWithdrawHubDistribute(hubService, rabbitMQProducer);
        onWithdrawDistributeService.distribute(result);
    }
}
