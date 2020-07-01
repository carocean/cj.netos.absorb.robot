package cj.netos.absorb.robot;

import cj.netos.absorb.robot.bo.LatLng;
import cj.netos.absorb.robot.bo.RecipientsAbsorbBill;
import cj.netos.absorb.robot.model.Absorber;
import cj.netos.absorb.robot.model.InvestRecord;
import cj.netos.absorb.robot.model.Recipients;
import cj.studio.ecm.net.CircuitException;
import cj.studio.orm.mybatis.annotation.CjTransaction;

import java.math.BigDecimal;
import java.util.List;

public interface IAbsorberHubService {
    void createSimpleAbsorber(Absorber absorber);

    Absorber getAbsorber(String absorberid);

    List<Absorber> pageAbsorber(String bankid, int type, int limit, long offset);

    void removeAbsorber(String absorberid);

    void addRecipients(Recipients recipients) throws CircuitException;

    @CjTransaction
    long totalRecipientsCount(String absorber);

    boolean existsRecipients(String principal, String absorberid);

    BigDecimal totalWeightsOfAbsorber(String bankid);

    BigDecimal totalWeightsOfRecipients(String absorberid);

    List<Recipients> pageRecipients(String id, int limit, long offset);

    void addTailAmount(BigDecimal amount, String bankid, String refsn, int order, String cause);

    RecipientsAbsorbBill addRecipientsRecord(Absorber absorber, Recipients recipients, Object result, BigDecimal money);


    List<POR> searchAroundPerson(String latLng, Long radius, int limit, long offset) throws CircuitException;

    long getMaxRecipientsCount(Absorber absorber);
}
