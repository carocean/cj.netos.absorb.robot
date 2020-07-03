package cj.netos.absorb.robot;

import cj.netos.absorb.robot.bo.AbsorberTemplate;
import cj.netos.absorb.robot.bo.RecipientsAbsorbBill;
import cj.netos.absorb.robot.model.*;
import cj.studio.ecm.net.CircuitException;
import cj.studio.orm.mybatis.annotation.CjTransaction;

import java.math.BigDecimal;
import java.util.List;

public interface IAbsorberHubService {
    AbsorberTemplate getAbsorberTemplate();

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

    void addTailAmount(BigDecimal amount,String person, String bankid, String refsn, int order, String cause);

    RecipientsAbsorbBill addRecipientsRecord(Absorber absorber, Recipients recipients, Object result, BigDecimal money);


    List<POR> searchAroundPerson(String latLng, Long radius, int limit, long offset) throws CircuitException;

    long getMaxRecipientsCount(Absorber absorber);

    void updateAbsorberCurrent(String absorber, long currentTimes, BigDecimal currentAmount);

    void stopAbsorber(String absorber, String exitCause);

    void updateAbsorberWeight(String id, BigDecimal newWeight);

    @CjTransaction
    HubTails getAndInitHubTails(String bankid);

    TailBill withdrawHubTails(String principal, String bankid);

    boolean existsRecipientsEncourageCode(String principal, String absorberid, String encourageCode);

    void addWeightToRecipients(String principal, String absorberid);

    List<Recipients> pageGeoRecipients(Absorber absorberid, int limit, long offset) throws CircuitException;

}
