package cj.netos.absorb.robot.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class DomainBillExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    public DomainBillExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andSnIsNull() {
            addCriterion("sn is null");
            return (Criteria) this;
        }

        public Criteria andSnIsNotNull() {
            addCriterion("sn is not null");
            return (Criteria) this;
        }

        public Criteria andSnEqualTo(String value) {
            addCriterion("sn =", value, "sn");
            return (Criteria) this;
        }

        public Criteria andSnNotEqualTo(String value) {
            addCriterion("sn <>", value, "sn");
            return (Criteria) this;
        }

        public Criteria andSnGreaterThan(String value) {
            addCriterion("sn >", value, "sn");
            return (Criteria) this;
        }

        public Criteria andSnGreaterThanOrEqualTo(String value) {
            addCriterion("sn >=", value, "sn");
            return (Criteria) this;
        }

        public Criteria andSnLessThan(String value) {
            addCriterion("sn <", value, "sn");
            return (Criteria) this;
        }

        public Criteria andSnLessThanOrEqualTo(String value) {
            addCriterion("sn <=", value, "sn");
            return (Criteria) this;
        }

        public Criteria andSnLike(String value) {
            addCriterion("sn like", value, "sn");
            return (Criteria) this;
        }

        public Criteria andSnNotLike(String value) {
            addCriterion("sn not like", value, "sn");
            return (Criteria) this;
        }

        public Criteria andSnIn(List<String> values) {
            addCriterion("sn in", values, "sn");
            return (Criteria) this;
        }

        public Criteria andSnNotIn(List<String> values) {
            addCriterion("sn not in", values, "sn");
            return (Criteria) this;
        }

        public Criteria andSnBetween(String value1, String value2) {
            addCriterion("sn between", value1, value2, "sn");
            return (Criteria) this;
        }

        public Criteria andSnNotBetween(String value1, String value2) {
            addCriterion("sn not between", value1, value2, "sn");
            return (Criteria) this;
        }

        public Criteria andAbsorberIsNull() {
            addCriterion("absorber is null");
            return (Criteria) this;
        }

        public Criteria andAbsorberIsNotNull() {
            addCriterion("absorber is not null");
            return (Criteria) this;
        }

        public Criteria andAbsorberEqualTo(String value) {
            addCriterion("absorber =", value, "absorber");
            return (Criteria) this;
        }

        public Criteria andAbsorberNotEqualTo(String value) {
            addCriterion("absorber <>", value, "absorber");
            return (Criteria) this;
        }

        public Criteria andAbsorberGreaterThan(String value) {
            addCriterion("absorber >", value, "absorber");
            return (Criteria) this;
        }

        public Criteria andAbsorberGreaterThanOrEqualTo(String value) {
            addCriterion("absorber >=", value, "absorber");
            return (Criteria) this;
        }

        public Criteria andAbsorberLessThan(String value) {
            addCriterion("absorber <", value, "absorber");
            return (Criteria) this;
        }

        public Criteria andAbsorberLessThanOrEqualTo(String value) {
            addCriterion("absorber <=", value, "absorber");
            return (Criteria) this;
        }

        public Criteria andAbsorberLike(String value) {
            addCriterion("absorber like", value, "absorber");
            return (Criteria) this;
        }

        public Criteria andAbsorberNotLike(String value) {
            addCriterion("absorber not like", value, "absorber");
            return (Criteria) this;
        }

        public Criteria andAbsorberIn(List<String> values) {
            addCriterion("absorber in", values, "absorber");
            return (Criteria) this;
        }

        public Criteria andAbsorberNotIn(List<String> values) {
            addCriterion("absorber not in", values, "absorber");
            return (Criteria) this;
        }

        public Criteria andAbsorberBetween(String value1, String value2) {
            addCriterion("absorber between", value1, value2, "absorber");
            return (Criteria) this;
        }

        public Criteria andAbsorberNotBetween(String value1, String value2) {
            addCriterion("absorber not between", value1, value2, "absorber");
            return (Criteria) this;
        }

        public Criteria andRefsnIsNull() {
            addCriterion("refsn is null");
            return (Criteria) this;
        }

        public Criteria andRefsnIsNotNull() {
            addCriterion("refsn is not null");
            return (Criteria) this;
        }

        public Criteria andRefsnEqualTo(String value) {
            addCriterion("refsn =", value, "refsn");
            return (Criteria) this;
        }

        public Criteria andRefsnNotEqualTo(String value) {
            addCriterion("refsn <>", value, "refsn");
            return (Criteria) this;
        }

        public Criteria andRefsnGreaterThan(String value) {
            addCriterion("refsn >", value, "refsn");
            return (Criteria) this;
        }

        public Criteria andRefsnGreaterThanOrEqualTo(String value) {
            addCriterion("refsn >=", value, "refsn");
            return (Criteria) this;
        }

        public Criteria andRefsnLessThan(String value) {
            addCriterion("refsn <", value, "refsn");
            return (Criteria) this;
        }

        public Criteria andRefsnLessThanOrEqualTo(String value) {
            addCriterion("refsn <=", value, "refsn");
            return (Criteria) this;
        }

        public Criteria andRefsnLike(String value) {
            addCriterion("refsn like", value, "refsn");
            return (Criteria) this;
        }

        public Criteria andRefsnNotLike(String value) {
            addCriterion("refsn not like", value, "refsn");
            return (Criteria) this;
        }

        public Criteria andRefsnIn(List<String> values) {
            addCriterion("refsn in", values, "refsn");
            return (Criteria) this;
        }

        public Criteria andRefsnNotIn(List<String> values) {
            addCriterion("refsn not in", values, "refsn");
            return (Criteria) this;
        }

        public Criteria andRefsnBetween(String value1, String value2) {
            addCriterion("refsn between", value1, value2, "refsn");
            return (Criteria) this;
        }

        public Criteria andRefsnNotBetween(String value1, String value2) {
            addCriterion("refsn not between", value1, value2, "refsn");
            return (Criteria) this;
        }

        public Criteria andWaaPriceIsNull() {
            addCriterion("waa_price is null");
            return (Criteria) this;
        }

        public Criteria andWaaPriceIsNotNull() {
            addCriterion("waa_price is not null");
            return (Criteria) this;
        }

        public Criteria andWaaPriceEqualTo(BigDecimal value) {
            addCriterion("waa_price =", value, "waaPrice");
            return (Criteria) this;
        }

        public Criteria andWaaPriceNotEqualTo(BigDecimal value) {
            addCriterion("waa_price <>", value, "waaPrice");
            return (Criteria) this;
        }

        public Criteria andWaaPriceGreaterThan(BigDecimal value) {
            addCriterion("waa_price >", value, "waaPrice");
            return (Criteria) this;
        }

        public Criteria andWaaPriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("waa_price >=", value, "waaPrice");
            return (Criteria) this;
        }

        public Criteria andWaaPriceLessThan(BigDecimal value) {
            addCriterion("waa_price <", value, "waaPrice");
            return (Criteria) this;
        }

        public Criteria andWaaPriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("waa_price <=", value, "waaPrice");
            return (Criteria) this;
        }

        public Criteria andWaaPriceIn(List<BigDecimal> values) {
            addCriterion("waa_price in", values, "waaPrice");
            return (Criteria) this;
        }

        public Criteria andWaaPriceNotIn(List<BigDecimal> values) {
            addCriterion("waa_price not in", values, "waaPrice");
            return (Criteria) this;
        }

        public Criteria andWaaPriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("waa_price between", value1, value2, "waaPrice");
            return (Criteria) this;
        }

        public Criteria andWaaPriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("waa_price not between", value1, value2, "waaPrice");
            return (Criteria) this;
        }

        public Criteria andAfterWaaPriceIsNull() {
            addCriterion("after_waa_price is null");
            return (Criteria) this;
        }

        public Criteria andAfterWaaPriceIsNotNull() {
            addCriterion("after_waa_price is not null");
            return (Criteria) this;
        }

        public Criteria andAfterWaaPriceEqualTo(BigDecimal value) {
            addCriterion("after_waa_price =", value, "afterWaaPrice");
            return (Criteria) this;
        }

        public Criteria andAfterWaaPriceNotEqualTo(BigDecimal value) {
            addCriterion("after_waa_price <>", value, "afterWaaPrice");
            return (Criteria) this;
        }

        public Criteria andAfterWaaPriceGreaterThan(BigDecimal value) {
            addCriterion("after_waa_price >", value, "afterWaaPrice");
            return (Criteria) this;
        }

        public Criteria andAfterWaaPriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("after_waa_price >=", value, "afterWaaPrice");
            return (Criteria) this;
        }

        public Criteria andAfterWaaPriceLessThan(BigDecimal value) {
            addCriterion("after_waa_price <", value, "afterWaaPrice");
            return (Criteria) this;
        }

        public Criteria andAfterWaaPriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("after_waa_price <=", value, "afterWaaPrice");
            return (Criteria) this;
        }

        public Criteria andAfterWaaPriceIn(List<BigDecimal> values) {
            addCriterion("after_waa_price in", values, "afterWaaPrice");
            return (Criteria) this;
        }

        public Criteria andAfterWaaPriceNotIn(List<BigDecimal> values) {
            addCriterion("after_waa_price not in", values, "afterWaaPrice");
            return (Criteria) this;
        }

        public Criteria andAfterWaaPriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("after_waa_price between", value1, value2, "afterWaaPrice");
            return (Criteria) this;
        }

        public Criteria andAfterWaaPriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("after_waa_price not between", value1, value2, "afterWaaPrice");
            return (Criteria) this;
        }

        public Criteria andAbsorbCountIsNull() {
            addCriterion("absorb_count is null");
            return (Criteria) this;
        }

        public Criteria andAbsorbCountIsNotNull() {
            addCriterion("absorb_count is not null");
            return (Criteria) this;
        }

        public Criteria andAbsorbCountEqualTo(Long value) {
            addCriterion("absorb_count =", value, "absorbCount");
            return (Criteria) this;
        }

        public Criteria andAbsorbCountNotEqualTo(Long value) {
            addCriterion("absorb_count <>", value, "absorbCount");
            return (Criteria) this;
        }

        public Criteria andAbsorbCountGreaterThan(Long value) {
            addCriterion("absorb_count >", value, "absorbCount");
            return (Criteria) this;
        }

        public Criteria andAbsorbCountGreaterThanOrEqualTo(Long value) {
            addCriterion("absorb_count >=", value, "absorbCount");
            return (Criteria) this;
        }

        public Criteria andAbsorbCountLessThan(Long value) {
            addCriterion("absorb_count <", value, "absorbCount");
            return (Criteria) this;
        }

        public Criteria andAbsorbCountLessThanOrEqualTo(Long value) {
            addCriterion("absorb_count <=", value, "absorbCount");
            return (Criteria) this;
        }

        public Criteria andAbsorbCountIn(List<Long> values) {
            addCriterion("absorb_count in", values, "absorbCount");
            return (Criteria) this;
        }

        public Criteria andAbsorbCountNotIn(List<Long> values) {
            addCriterion("absorb_count not in", values, "absorbCount");
            return (Criteria) this;
        }

        public Criteria andAbsorbCountBetween(Long value1, Long value2) {
            addCriterion("absorb_count between", value1, value2, "absorbCount");
            return (Criteria) this;
        }

        public Criteria andAbsorbCountNotBetween(Long value1, Long value2) {
            addCriterion("absorb_count not between", value1, value2, "absorbCount");
            return (Criteria) this;
        }

        public Criteria andWInvestAmountIsNull() {
            addCriterion("w_invest_amount is null");
            return (Criteria) this;
        }

        public Criteria andWInvestAmountIsNotNull() {
            addCriterion("w_invest_amount is not null");
            return (Criteria) this;
        }

        public Criteria andWInvestAmountEqualTo(BigDecimal value) {
            addCriterion("w_invest_amount =", value, "wInvestAmount");
            return (Criteria) this;
        }

        public Criteria andWInvestAmountNotEqualTo(BigDecimal value) {
            addCriterion("w_invest_amount <>", value, "wInvestAmount");
            return (Criteria) this;
        }

        public Criteria andWInvestAmountGreaterThan(BigDecimal value) {
            addCriterion("w_invest_amount >", value, "wInvestAmount");
            return (Criteria) this;
        }

        public Criteria andWInvestAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("w_invest_amount >=", value, "wInvestAmount");
            return (Criteria) this;
        }

        public Criteria andWInvestAmountLessThan(BigDecimal value) {
            addCriterion("w_invest_amount <", value, "wInvestAmount");
            return (Criteria) this;
        }

        public Criteria andWInvestAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("w_invest_amount <=", value, "wInvestAmount");
            return (Criteria) this;
        }

        public Criteria andWInvestAmountIn(List<BigDecimal> values) {
            addCriterion("w_invest_amount in", values, "wInvestAmount");
            return (Criteria) this;
        }

        public Criteria andWInvestAmountNotIn(List<BigDecimal> values) {
            addCriterion("w_invest_amount not in", values, "wInvestAmount");
            return (Criteria) this;
        }

        public Criteria andWInvestAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("w_invest_amount between", value1, value2, "wInvestAmount");
            return (Criteria) this;
        }

        public Criteria andWInvestAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("w_invest_amount not between", value1, value2, "wInvestAmount");
            return (Criteria) this;
        }

        public Criteria andPInvestAmountIsNull() {
            addCriterion("p_invest_amount is null");
            return (Criteria) this;
        }

        public Criteria andPInvestAmountIsNotNull() {
            addCriterion("p_invest_amount is not null");
            return (Criteria) this;
        }

        public Criteria andPInvestAmountEqualTo(BigDecimal value) {
            addCriterion("p_invest_amount =", value, "pInvestAmount");
            return (Criteria) this;
        }

        public Criteria andPInvestAmountNotEqualTo(BigDecimal value) {
            addCriterion("p_invest_amount <>", value, "pInvestAmount");
            return (Criteria) this;
        }

        public Criteria andPInvestAmountGreaterThan(BigDecimal value) {
            addCriterion("p_invest_amount >", value, "pInvestAmount");
            return (Criteria) this;
        }

        public Criteria andPInvestAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("p_invest_amount >=", value, "pInvestAmount");
            return (Criteria) this;
        }

        public Criteria andPInvestAmountLessThan(BigDecimal value) {
            addCriterion("p_invest_amount <", value, "pInvestAmount");
            return (Criteria) this;
        }

        public Criteria andPInvestAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("p_invest_amount <=", value, "pInvestAmount");
            return (Criteria) this;
        }

        public Criteria andPInvestAmountIn(List<BigDecimal> values) {
            addCriterion("p_invest_amount in", values, "pInvestAmount");
            return (Criteria) this;
        }

        public Criteria andPInvestAmountNotIn(List<BigDecimal> values) {
            addCriterion("p_invest_amount not in", values, "pInvestAmount");
            return (Criteria) this;
        }

        public Criteria andPInvestAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("p_invest_amount between", value1, value2, "pInvestAmount");
            return (Criteria) this;
        }

        public Criteria andPInvestAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("p_invest_amount not between", value1, value2, "pInvestAmount");
            return (Criteria) this;
        }

        public Criteria andOrderIsNull() {
            addCriterion("`order` is null");
            return (Criteria) this;
        }

        public Criteria andOrderIsNotNull() {
            addCriterion("`order` is not null");
            return (Criteria) this;
        }

        public Criteria andOrderEqualTo(Integer value) {
            addCriterion("`order` =", value, "order");
            return (Criteria) this;
        }

        public Criteria andOrderNotEqualTo(Integer value) {
            addCriterion("`order` <>", value, "order");
            return (Criteria) this;
        }

        public Criteria andOrderGreaterThan(Integer value) {
            addCriterion("`order` >", value, "order");
            return (Criteria) this;
        }

        public Criteria andOrderGreaterThanOrEqualTo(Integer value) {
            addCriterion("`order` >=", value, "order");
            return (Criteria) this;
        }

        public Criteria andOrderLessThan(Integer value) {
            addCriterion("`order` <", value, "order");
            return (Criteria) this;
        }

        public Criteria andOrderLessThanOrEqualTo(Integer value) {
            addCriterion("`order` <=", value, "order");
            return (Criteria) this;
        }

        public Criteria andOrderIn(List<Integer> values) {
            addCriterion("`order` in", values, "order");
            return (Criteria) this;
        }

        public Criteria andOrderNotIn(List<Integer> values) {
            addCriterion("`order` not in", values, "order");
            return (Criteria) this;
        }

        public Criteria andOrderBetween(Integer value1, Integer value2) {
            addCriterion("`order` between", value1, value2, "order");
            return (Criteria) this;
        }

        public Criteria andOrderNotBetween(Integer value1, Integer value2) {
            addCriterion("`order` not between", value1, value2, "order");
            return (Criteria) this;
        }

        public Criteria andCtimeIsNull() {
            addCriterion("ctime is null");
            return (Criteria) this;
        }

        public Criteria andCtimeIsNotNull() {
            addCriterion("ctime is not null");
            return (Criteria) this;
        }

        public Criteria andCtimeEqualTo(String value) {
            addCriterion("ctime =", value, "ctime");
            return (Criteria) this;
        }

        public Criteria andCtimeNotEqualTo(String value) {
            addCriterion("ctime <>", value, "ctime");
            return (Criteria) this;
        }

        public Criteria andCtimeGreaterThan(String value) {
            addCriterion("ctime >", value, "ctime");
            return (Criteria) this;
        }

        public Criteria andCtimeGreaterThanOrEqualTo(String value) {
            addCriterion("ctime >=", value, "ctime");
            return (Criteria) this;
        }

        public Criteria andCtimeLessThan(String value) {
            addCriterion("ctime <", value, "ctime");
            return (Criteria) this;
        }

        public Criteria andCtimeLessThanOrEqualTo(String value) {
            addCriterion("ctime <=", value, "ctime");
            return (Criteria) this;
        }

        public Criteria andCtimeLike(String value) {
            addCriterion("ctime like", value, "ctime");
            return (Criteria) this;
        }

        public Criteria andCtimeNotLike(String value) {
            addCriterion("ctime not like", value, "ctime");
            return (Criteria) this;
        }

        public Criteria andCtimeIn(List<String> values) {
            addCriterion("ctime in", values, "ctime");
            return (Criteria) this;
        }

        public Criteria andCtimeNotIn(List<String> values) {
            addCriterion("ctime not in", values, "ctime");
            return (Criteria) this;
        }

        public Criteria andCtimeBetween(String value1, String value2) {
            addCriterion("ctime between", value1, value2, "ctime");
            return (Criteria) this;
        }

        public Criteria andCtimeNotBetween(String value1, String value2) {
            addCriterion("ctime not between", value1, value2, "ctime");
            return (Criteria) this;
        }

        public Criteria andNoteIsNull() {
            addCriterion("note is null");
            return (Criteria) this;
        }

        public Criteria andNoteIsNotNull() {
            addCriterion("note is not null");
            return (Criteria) this;
        }

        public Criteria andNoteEqualTo(String value) {
            addCriterion("note =", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteNotEqualTo(String value) {
            addCriterion("note <>", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteGreaterThan(String value) {
            addCriterion("note >", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteGreaterThanOrEqualTo(String value) {
            addCriterion("note >=", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteLessThan(String value) {
            addCriterion("note <", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteLessThanOrEqualTo(String value) {
            addCriterion("note <=", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteLike(String value) {
            addCriterion("note like", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteNotLike(String value) {
            addCriterion("note not like", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteIn(List<String> values) {
            addCriterion("note in", values, "note");
            return (Criteria) this;
        }

        public Criteria andNoteNotIn(List<String> values) {
            addCriterion("note not in", values, "note");
            return (Criteria) this;
        }

        public Criteria andNoteBetween(String value1, String value2) {
            addCriterion("note between", value1, value2, "note");
            return (Criteria) this;
        }

        public Criteria andNoteNotBetween(String value1, String value2) {
            addCriterion("note not between", value1, value2, "note");
            return (Criteria) this;
        }

        public Criteria andWorkdayIsNull() {
            addCriterion("workday is null");
            return (Criteria) this;
        }

        public Criteria andWorkdayIsNotNull() {
            addCriterion("workday is not null");
            return (Criteria) this;
        }

        public Criteria andWorkdayEqualTo(String value) {
            addCriterion("workday =", value, "workday");
            return (Criteria) this;
        }

        public Criteria andWorkdayNotEqualTo(String value) {
            addCriterion("workday <>", value, "workday");
            return (Criteria) this;
        }

        public Criteria andWorkdayGreaterThan(String value) {
            addCriterion("workday >", value, "workday");
            return (Criteria) this;
        }

        public Criteria andWorkdayGreaterThanOrEqualTo(String value) {
            addCriterion("workday >=", value, "workday");
            return (Criteria) this;
        }

        public Criteria andWorkdayLessThan(String value) {
            addCriterion("workday <", value, "workday");
            return (Criteria) this;
        }

        public Criteria andWorkdayLessThanOrEqualTo(String value) {
            addCriterion("workday <=", value, "workday");
            return (Criteria) this;
        }

        public Criteria andWorkdayLike(String value) {
            addCriterion("workday like", value, "workday");
            return (Criteria) this;
        }

        public Criteria andWorkdayNotLike(String value) {
            addCriterion("workday not like", value, "workday");
            return (Criteria) this;
        }

        public Criteria andWorkdayIn(List<String> values) {
            addCriterion("workday in", values, "workday");
            return (Criteria) this;
        }

        public Criteria andWorkdayNotIn(List<String> values) {
            addCriterion("workday not in", values, "workday");
            return (Criteria) this;
        }

        public Criteria andWorkdayBetween(String value1, String value2) {
            addCriterion("workday between", value1, value2, "workday");
            return (Criteria) this;
        }

        public Criteria andWorkdayNotBetween(String value1, String value2) {
            addCriterion("workday not between", value1, value2, "workday");
            return (Criteria) this;
        }

        public Criteria andDayIsNull() {
            addCriterion("`day` is null");
            return (Criteria) this;
        }

        public Criteria andDayIsNotNull() {
            addCriterion("`day` is not null");
            return (Criteria) this;
        }

        public Criteria andDayEqualTo(Integer value) {
            addCriterion("`day` =", value, "day");
            return (Criteria) this;
        }

        public Criteria andDayNotEqualTo(Integer value) {
            addCriterion("`day` <>", value, "day");
            return (Criteria) this;
        }

        public Criteria andDayGreaterThan(Integer value) {
            addCriterion("`day` >", value, "day");
            return (Criteria) this;
        }

        public Criteria andDayGreaterThanOrEqualTo(Integer value) {
            addCriterion("`day` >=", value, "day");
            return (Criteria) this;
        }

        public Criteria andDayLessThan(Integer value) {
            addCriterion("`day` <", value, "day");
            return (Criteria) this;
        }

        public Criteria andDayLessThanOrEqualTo(Integer value) {
            addCriterion("`day` <=", value, "day");
            return (Criteria) this;
        }

        public Criteria andDayIn(List<Integer> values) {
            addCriterion("`day` in", values, "day");
            return (Criteria) this;
        }

        public Criteria andDayNotIn(List<Integer> values) {
            addCriterion("`day` not in", values, "day");
            return (Criteria) this;
        }

        public Criteria andDayBetween(Integer value1, Integer value2) {
            addCriterion("`day` between", value1, value2, "day");
            return (Criteria) this;
        }

        public Criteria andDayNotBetween(Integer value1, Integer value2) {
            addCriterion("`day` not between", value1, value2, "day");
            return (Criteria) this;
        }

        public Criteria andMonthIsNull() {
            addCriterion("`month` is null");
            return (Criteria) this;
        }

        public Criteria andMonthIsNotNull() {
            addCriterion("`month` is not null");
            return (Criteria) this;
        }

        public Criteria andMonthEqualTo(Integer value) {
            addCriterion("`month` =", value, "month");
            return (Criteria) this;
        }

        public Criteria andMonthNotEqualTo(Integer value) {
            addCriterion("`month` <>", value, "month");
            return (Criteria) this;
        }

        public Criteria andMonthGreaterThan(Integer value) {
            addCriterion("`month` >", value, "month");
            return (Criteria) this;
        }

        public Criteria andMonthGreaterThanOrEqualTo(Integer value) {
            addCriterion("`month` >=", value, "month");
            return (Criteria) this;
        }

        public Criteria andMonthLessThan(Integer value) {
            addCriterion("`month` <", value, "month");
            return (Criteria) this;
        }

        public Criteria andMonthLessThanOrEqualTo(Integer value) {
            addCriterion("`month` <=", value, "month");
            return (Criteria) this;
        }

        public Criteria andMonthIn(List<Integer> values) {
            addCriterion("`month` in", values, "month");
            return (Criteria) this;
        }

        public Criteria andMonthNotIn(List<Integer> values) {
            addCriterion("`month` not in", values, "month");
            return (Criteria) this;
        }

        public Criteria andMonthBetween(Integer value1, Integer value2) {
            addCriterion("`month` between", value1, value2, "month");
            return (Criteria) this;
        }

        public Criteria andMonthNotBetween(Integer value1, Integer value2) {
            addCriterion("`month` not between", value1, value2, "month");
            return (Criteria) this;
        }

        public Criteria andSeasonIsNull() {
            addCriterion("season is null");
            return (Criteria) this;
        }

        public Criteria andSeasonIsNotNull() {
            addCriterion("season is not null");
            return (Criteria) this;
        }

        public Criteria andSeasonEqualTo(Integer value) {
            addCriterion("season =", value, "season");
            return (Criteria) this;
        }

        public Criteria andSeasonNotEqualTo(Integer value) {
            addCriterion("season <>", value, "season");
            return (Criteria) this;
        }

        public Criteria andSeasonGreaterThan(Integer value) {
            addCriterion("season >", value, "season");
            return (Criteria) this;
        }

        public Criteria andSeasonGreaterThanOrEqualTo(Integer value) {
            addCriterion("season >=", value, "season");
            return (Criteria) this;
        }

        public Criteria andSeasonLessThan(Integer value) {
            addCriterion("season <", value, "season");
            return (Criteria) this;
        }

        public Criteria andSeasonLessThanOrEqualTo(Integer value) {
            addCriterion("season <=", value, "season");
            return (Criteria) this;
        }

        public Criteria andSeasonIn(List<Integer> values) {
            addCriterion("season in", values, "season");
            return (Criteria) this;
        }

        public Criteria andSeasonNotIn(List<Integer> values) {
            addCriterion("season not in", values, "season");
            return (Criteria) this;
        }

        public Criteria andSeasonBetween(Integer value1, Integer value2) {
            addCriterion("season between", value1, value2, "season");
            return (Criteria) this;
        }

        public Criteria andSeasonNotBetween(Integer value1, Integer value2) {
            addCriterion("season not between", value1, value2, "season");
            return (Criteria) this;
        }

        public Criteria andYearIsNull() {
            addCriterion("`year` is null");
            return (Criteria) this;
        }

        public Criteria andYearIsNotNull() {
            addCriterion("`year` is not null");
            return (Criteria) this;
        }

        public Criteria andYearEqualTo(Integer value) {
            addCriterion("`year` =", value, "year");
            return (Criteria) this;
        }

        public Criteria andYearNotEqualTo(Integer value) {
            addCriterion("`year` <>", value, "year");
            return (Criteria) this;
        }

        public Criteria andYearGreaterThan(Integer value) {
            addCriterion("`year` >", value, "year");
            return (Criteria) this;
        }

        public Criteria andYearGreaterThanOrEqualTo(Integer value) {
            addCriterion("`year` >=", value, "year");
            return (Criteria) this;
        }

        public Criteria andYearLessThan(Integer value) {
            addCriterion("`year` <", value, "year");
            return (Criteria) this;
        }

        public Criteria andYearLessThanOrEqualTo(Integer value) {
            addCriterion("`year` <=", value, "year");
            return (Criteria) this;
        }

        public Criteria andYearIn(List<Integer> values) {
            addCriterion("`year` in", values, "year");
            return (Criteria) this;
        }

        public Criteria andYearNotIn(List<Integer> values) {
            addCriterion("`year` not in", values, "year");
            return (Criteria) this;
        }

        public Criteria andYearBetween(Integer value1, Integer value2) {
            addCriterion("`year` between", value1, value2, "year");
            return (Criteria) this;
        }

        public Criteria andYearNotBetween(Integer value1, Integer value2) {
            addCriterion("`year` not between", value1, value2, "year");
            return (Criteria) this;
        }

        public Criteria andBankidIsNull() {
            addCriterion("bankid is null");
            return (Criteria) this;
        }

        public Criteria andBankidIsNotNull() {
            addCriterion("bankid is not null");
            return (Criteria) this;
        }

        public Criteria andBankidEqualTo(String value) {
            addCriterion("bankid =", value, "bankid");
            return (Criteria) this;
        }

        public Criteria andBankidNotEqualTo(String value) {
            addCriterion("bankid <>", value, "bankid");
            return (Criteria) this;
        }

        public Criteria andBankidGreaterThan(String value) {
            addCriterion("bankid >", value, "bankid");
            return (Criteria) this;
        }

        public Criteria andBankidGreaterThanOrEqualTo(String value) {
            addCriterion("bankid >=", value, "bankid");
            return (Criteria) this;
        }

        public Criteria andBankidLessThan(String value) {
            addCriterion("bankid <", value, "bankid");
            return (Criteria) this;
        }

        public Criteria andBankidLessThanOrEqualTo(String value) {
            addCriterion("bankid <=", value, "bankid");
            return (Criteria) this;
        }

        public Criteria andBankidLike(String value) {
            addCriterion("bankid like", value, "bankid");
            return (Criteria) this;
        }

        public Criteria andBankidNotLike(String value) {
            addCriterion("bankid not like", value, "bankid");
            return (Criteria) this;
        }

        public Criteria andBankidIn(List<String> values) {
            addCriterion("bankid in", values, "bankid");
            return (Criteria) this;
        }

        public Criteria andBankidNotIn(List<String> values) {
            addCriterion("bankid not in", values, "bankid");
            return (Criteria) this;
        }

        public Criteria andBankidBetween(String value1, String value2) {
            addCriterion("bankid between", value1, value2, "bankid");
            return (Criteria) this;
        }

        public Criteria andBankidNotBetween(String value1, String value2) {
            addCriterion("bankid not between", value1, value2, "bankid");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}