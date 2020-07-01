package cj.netos.absorb.robot.cmd;

import cj.netos.absorb.robot.IWithdrawService;
import cj.netos.rabbitmq.CjConsumer;
import cj.netos.rabbitmq.RabbitMQException;
import cj.netos.rabbitmq.RetryCommandException;
import cj.netos.rabbitmq.consumer.IConsumerCommand;
import cj.studio.ecm.CJSystem;
import cj.studio.ecm.annotation.CjService;
import cj.studio.ecm.annotation.CjServiceRef;
import cj.studio.ecm.net.CircuitException;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Envelope;

import java.io.IOException;

@CjConsumer(name = "onUpdateAbsorbs")
@CjService(name = "/notify.ports#updateAbsorbs")
public class OnUpdateCommand implements IConsumerCommand {
    @CjServiceRef
    IWithdrawService withdrawService;

    @Override
    public void command(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws RabbitMQException, RetryCommandException, IOException {
        String bankid = properties.getHeaders().get("bankid").toString();
        String shunter = properties.getHeaders().get("shunter").toString();
        String alias = properties.getHeaders().get("alias").toString();
        long amount = (long) properties.getHeaders().get("amount");
//        System.out.println(String.format("%s %s %s %s", bankid, shunter, alias, amount));
        try {
            withdrawService.doRequest(bankid, shunter, alias, amount);
        } catch (Exception e) {
            CJSystem.logging().error(getClass(),e);
            CircuitException ce = CircuitException.search(e);
            if (ce == null) {
                throw new RabbitMQException(ce.getStatus(), ce);
            }
            throw new RabbitMQException("500", e);
        }
    }
}
