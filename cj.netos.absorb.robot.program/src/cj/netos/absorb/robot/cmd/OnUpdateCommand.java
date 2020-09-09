package cj.netos.absorb.robot.cmd;

import cj.netos.absorb.robot.ICuratorPathChecker;
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
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.framework.recipes.locks.InterProcessReadWriteLock;

import java.io.IOException;

@CjConsumer(name = "fromWyBank_onUpdateAbsorbs")
@CjService(name = "/notify.ports#updateAbsorbs")
public class OnUpdateCommand implements IConsumerCommand {
    @CjServiceRef
    IWithdrawService withdrawService;
    @CjServiceRef(refByName = "curator.framework")
    CuratorFramework framework;

    @CjServiceRef
    ICuratorPathChecker curatorPathChecker;
    @Override
    public void command(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws RabbitMQException, RetryCommandException, IOException {
        String bankid = properties.getHeaders().get("bankid").toString();
        String shunter = properties.getHeaders().get("shunter").toString();
        String alias = properties.getHeaders().get("alias").toString();
        long amount = (long) properties.getHeaders().get("amount");
//        System.out.println(String.format("%s %s %s %s", bankid, shunter, alias, amount));
        String path = String.format("/robot/bank/%s/locks", bankid);
        try {
            curatorPathChecker.check(framework, path);
        } catch (Exception e) {
            throw new RabbitMQException("500", e);
        }
        InterProcessReadWriteLock lock = new InterProcessReadWriteLock(framework, path);
        InterProcessMutex mutex = lock.writeLock();
        try {
            mutex.acquire();
            withdrawService.doRequest(bankid, shunter, alias, amount);
        } catch (Exception e) {
            CJSystem.logging().error(getClass(),e);
            CircuitException ce = CircuitException.search(e);
            if (ce == null) {
                throw new RabbitMQException(ce.getStatus(), ce);
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
