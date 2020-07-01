package cj.netos.absorb.robot.cmd;

import cj.netos.absorb.robot.BankWithdrawResult;
import cj.netos.absorb.robot.IAbsorberHubService;
import cj.netos.absorb.robot.IWithdrawService;
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

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@CjConsumer(name = "onWithdraw")
@CjService(name = "/wybank/trade/settle.mq#withdraw")
public class OnWithdrawCommand implements IConsumerCommand {
    @CjServiceRef
    IWithdrawService withdrawService;
    @CjServiceRef
    IAbsorberHubService absorberHubService;

    @Override
    public void command(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws RabbitMQException, RetryCommandException, IOException {
        String json = new String(body);
        BankWithdrawResult result = new Gson().fromJson(json, BankWithdrawResult.class);
        try {
            withdrawService.doResponse(result);
        } catch (Exception e) {
            String msg = e.getMessage();
            if (!StringUtil.isEmpty(msg)) {
                msg = msg.substring(0, msg.length() > 200 ? 200 : msg.length());
            }
            absorberHubService.addTailAmount(new BigDecimal(result.getRealAmount() + ""), result.getBankid(), result.getOutTradeSn(), 0, String.format("派发过程出错:%s", msg));
            CJSystem.logging().error(getClass(),e);
            CircuitException ce = CircuitException.search(e);
            if (ce == null) {
                throw new RabbitMQException(ce.getStatus(), e);
            }
            throw new RabbitMQException("500", e);
        }
    }
}
