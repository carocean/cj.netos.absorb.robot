package cj.netos.absorb.robot.cmd;

import cj.netos.absorb.robot.*;
import cj.netos.absorb.robot.model.Absorber;
import cj.netos.rabbitmq.CjConsumer;
import cj.netos.rabbitmq.RabbitMQException;
import cj.netos.rabbitmq.RetryCommandException;
import cj.netos.rabbitmq.consumer.IConsumerCommand;
import cj.studio.ecm.CJSystem;
import cj.studio.ecm.annotation.CjService;
import cj.studio.ecm.annotation.CjServiceRef;
import cj.studio.ecm.net.CircuitException;
import cj.ultimate.gson2.com.google.gson.Gson;
import cj.ultimate.util.StringUtil;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Envelope;
import com.rabbitmq.client.LongString;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.framework.recipes.locks.InterProcessReadWriteLock;

import java.io.IOException;
import java.math.BigDecimal;

@CjConsumer(name = "fromWallet_onPayTrade")
@CjService(name = "/wallet/trade.mhub#payTrade")
public class OnInvestCommand implements IConsumerCommand {
    @CjServiceRef
    IHubService hubService;

    @CjServiceRef
    IInvestService investService;

    @CjServiceRef(refByName = "curator.framework")
    CuratorFramework framework;

    @CjServiceRef
    ICuratorPathChecker curatorPathChecker;
    @Override
    public void command(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws RabbitMQException, RetryCommandException, IOException {
        LongString payeeType = (LongString) properties.getHeaders().get("payeeType");
        if (!"absorber".equals(payeeType.toString())) {//1为洇取器
            throw new RetryCommandException("301", "不是向洇取器付款的事件，返回给mhub");
        }
        String json = new String(body);
        PaymentResult result = new Gson().fromJson(json, PaymentResult.class);
        Absorber absorber = hubService.getAbsorber(result.getDetails().getPayeeCode());
        if (absorber == null) {
            throw new RabbitMQException("404", "支付的洇取器不存在");
        }
        String path = String.format("/robot/bank/%s/locks", absorber.getBankid());
        try {
            curatorPathChecker.check(framework, path);
        } catch (Exception e) {
            throw new RabbitMQException("500", e);
        }
        InterProcessReadWriteLock lock = new InterProcessReadWriteLock(framework, path);
        InterProcessMutex mutex = lock.writeLock();
        try {
            mutex.acquire();
            investService.doResponse(absorber, result);
        } catch (Exception e) {
            String msg = e.getMessage();
            if (!StringUtil.isEmpty(msg)) {
                msg = msg.substring(0, msg.length() > 200 ? 200 : msg.length());
            }
            hubService.addTailAmount(new BigDecimal(result.getAmount() + ""),result.getPerson(), absorber.getBankid(), result.getSn(), 1, String.format("派发过程出错:%s", msg));
            CJSystem.logging().error(getClass(), e);
            CircuitException ce = CircuitException.search(e);
            if (ce == null) {
                throw new RabbitMQException(ce.getStatus(), e);
            }
            throw new RabbitMQException("500", e);
        } finally {
            try {
                mutex.release();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
