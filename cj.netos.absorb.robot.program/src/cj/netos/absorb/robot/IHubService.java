package cj.netos.absorb.robot;

import cj.netos.absorb.robot.bo.*;
import cj.netos.absorb.robot.model.*;
import cj.netos.absorb.robot.result.QrcodeSliceResult;
import cj.netos.absorb.robot.result.QrcodeSliceTemplateResult;
import cj.studio.ecm.net.CircuitException;
import cj.studio.orm.mybatis.annotation.CjTransaction;

import java.math.BigDecimal;
import java.util.List;

public interface IHubService {

    void createAbsorber(Absorber absorber);

    Absorber getAbsorber(String absorberid);

    AbsorberBucket getAndInitAbsorbBucket(String absorberid);

    List<Absorber> pageAbsorber(String bankid, int type, int limit, long offset);

    void removeAbsorber(String absorberid);

    void addRecipients(Recipients recipients) throws CircuitException;

    long totalRecipientsCount(String absorber);

    boolean existsRecipients(String principal, String absorberid);


    BigDecimal totalWeightsOfRecipients(String absorberid);

    List<Recipients> pageSimpleRecipients(String id, int limit, long offset);

    void addTailAmount(BigDecimal amount, String person, String bankid, String refsn, int order, String cause);

    RecipientsAbsorbBill addRecipientsRecord(Absorber absorber, Recipients recipients, Object result, BigDecimal money);


    List<POR> searchAroundPerson(String latLng, Long radius, int limit, long offset) throws CircuitException;

    long getMaxRecipientsCount(Absorber absorber);


    void stopAbsorber(String absorber, String exitCause);


    HubTails getAndInitHubTails(String bankid);

    TailBill withdrawHubTails(String principal, String bankid);

    boolean existsRecipientsEncourageCode(String principal, String absorberid, String encourageCode);

    @CjTransaction
    boolean canntPubingSliceRecipients(String principal, String absorberid);

    void addWeightToRecipients(String principal, String absorberid);

    List<Recipients> pageGeoRecipients(Absorber absorberid, int limit, long offset) throws CircuitException;

    void startAbsorber(String absorberid);

    List<RecipientsSummary> pageRecipientsSummary(String absorberid, int limit, long offset);

    long countRecipients(String absorberid);

    DomainBulletin getDomainBulletin(String bankid);

    List<AbsorberBucket> pageAbsorberBucketInBullectin(DomainBulletin bulletin, int limit, int offset);


    List<AbsorberBucket> pageAbsorberBucket(String bankid, int limit, int offset);
    /**
     * 更新洇金桶的纹银银行投资余额并计算价格
     * @param bucket
     * @param realDistribute
     * @param withdrawResult
     */
    void updateAbsorbBucket0(AbsorberBucket bucket, BigDecimal realDistribute, BankWithdrawResult withdrawResult);

    /**
     * 更新洇金桶的公众投资余额并计算价格
     * @param bucket
     * @param realDistribute
     * @param investRecord
     */
    void updateByPersonInvest(AbsorberBucket bucket, BigDecimal realDistribute, InvestRecord investRecord);


    void removeRecipients(String absorberid, String person);

    void updateRecipientsWeights(String absorberid, String person, String encourageCode, BigDecimal weights);

    DomainBulletin getDomainBulletinBeginWaaPrice(String bankid);

    void updateAbsorberLocation(String absorberid, LatLng location);

    void updateAbsorberRadius(String absorberid, long radius);

    boolean isBindingsAbsorbabler(String absorberid, String absorbabler);

    void bindAbsorbabler(String absorberid, String absorbabler) throws CircuitException;

    void unbindAbsorbabler(String absorberid);

    Absorber getAbsorberByAbsorbabler(String absorbabler);

    boolean existsRecipients2(String absorberid, String person, String encourageCode);

    void removeRecipients2(String absorberid, String person, String encourageCode);

    void updateMaxRecipients(String absorberid, long maxRecipients);

    Recipients getRecipients(String recipientsId);

    void updateRecipientsWeights(String recipientsId, BigDecimal weights);

    List<Recipients> pageSimpleRecipientsByPerson(String absorberid, String principal, int limit, long offset);

    void addCommentWeightsOfRecipients(String absorberid, String principal, String encourageCode);

    boolean subCommentWeightOfRecipients(String absorberid, String principal, String encourageCode);

    List<Recipients> getAroundLocationByPerson(Absorber absorber, String principal) throws CircuitException;

    List<Absorber> pageMyAbsorber(String principal, int type, int limit, long offset);

    List<Absorber> pageMyAbsorberByUsage(String principal, int usage, int limit, long offset);

    List<Absorber> pageJioninAbsorberByUsage(String principal, int usage, int limit, long offset);

    void configQrcodeSliceTemplate(List<QrcodeSliceTemplateBO> templates);

    void emptySliceTemplate();

    List<QrcodeSliceResult> createQrcodeSlice(String principal, String nickName,  QrcodeSliceTemplateResult sliceTemplate, long expire, LatLng location, long radius, String originAbsorber, String originPerson, int count, String note) throws CircuitException;

    void updateQrcodeSliceProperty(String principal,String slice, String propId, String propValue);

    QrcodeSliceTemplateResult getQrcodeSliceTemplate(String id);

    List<QrcodeSliceTemplateResult> pageQrcodeSliceTemplate(int limit, long offset);

    List<SliceBatch> pageQrcodeSliceBatch(String principal,int limit, long offset);

    List<QrcodeSliceResult> pageQrcodeSlice(String principal,int limit, long offset);

    void addQrcodeSliceRecipients(String principal,String absorberid, String qrcodeSlice) throws CircuitException;

    boolean cannotCreateQrocdeSlice(String principal);

    List<QrcodeSliceResult> pageQrcodeSliceOfBatch(String batchno, String principal,int limit, long offset);

    QrcodeSliceResult getQrcodeSlice(String slice);

    void consumeQrcodeSlice(String consumer,String nickName, QrcodeSlice qrcodeSlice) throws CircuitException;

    @CjTransaction
    void addRecipientsBalanceBill(Recipients recipients, RecipientsBalance balance, BigDecimal money);

    @CjTransaction
    RecipientsBalance getRecipientsBalnace(String recipientsid);

    void updateRecipientsBalance(String recipientsid, BigDecimal amount);

}
