package cj.netos.absorb.robot.cmd;

import cj.netos.absorb.robot.BankWithdrawResult;
import cj.netos.absorb.robot.IWithdrawService;
import cj.netos.rabbitmq.CjConsumer;
import cj.netos.rabbitmq.RabbitMQException;
import cj.netos.rabbitmq.RetryCommandException;
import cj.netos.rabbitmq.consumer.IConsumerCommand;
import cj.studio.ecm.annotation.CjService;
import cj.studio.ecm.annotation.CjServiceRef;
import cj.studio.ecm.net.CircuitException;
import cj.ultimate.gson2.com.google.gson.Gson;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Envelope;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@CjConsumer(name = "onWithdraw")
@CjService(name = "/wybank/trade/settle.mq#withdraw")
public class OnWithdrawCommand implements IConsumerCommand {
    @CjServiceRef
    IWithdrawService withdrawService;

    @Override
    public void command(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws RabbitMQException, RetryCommandException, IOException {
        String json = new String(body);
        BankWithdrawResult result = new Gson().fromJson(json, BankWithdrawResult.class);
        try {
            withdrawService.doResponse(result);
        } catch (Exception e) {
            CircuitException ce = CircuitException.search(e);
            if (ce == null) {
                withdrawService.error(result.getOutTradeSn(), ce.getStatus(), ce.getMessage());
                throw new RabbitMQException(ce.getStatus(), ce);
            }
            withdrawService.error(result.getOutTradeSn(), "500", e.getMessage());
            throw new RabbitMQException("500", e);
        }
    }
}
