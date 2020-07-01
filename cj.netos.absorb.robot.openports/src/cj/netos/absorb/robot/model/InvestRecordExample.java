package cj.netos.absorb.robot.model;

import java.util.ArrayList;
import java.util.List;

public class InvestRecordExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    public InvestRecordExample() {
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

        public Criteria andAmountIsNull() {
            addCriterion("amount is null");
            return (Criteria) this;
        }

        public Criteria andAmountIsNotNull() {
            addCriterion("amount is not null");
            return (Criteria) this;
        }

        public Criteria andAmountEqualTo(Long value) {
            addCriterion("amount =", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountNotEqualTo(Long value) {
            addCriterion("amount <>", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountGreaterThan(Long value) {
            addCriterion("amount >", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountGreaterThanOrEqualTo(Long value) {
            addCriterion("amount >=", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountLessThan(Long value) {
            addCriterion("amount <", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountLessThanOrEqualTo(Long value) {
            addCriterion("amount <=", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountIn(List<Long> values) {
            addCriterion("amount in", values, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountNotIn(List<Long> values) {
            addCriterion("amount not in", values, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountBetween(Long value1, Long value2) {
            addCriterion("amount between", value1, value2, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountNotBetween(Long value1, Long value2) {
            addCriterion("amount not between", value1, value2, "amount");
            return (Criteria) this;
        }

        public Criteria andInvesterIsNull() {
            addCriterion("invester is null");
            return (Criteria) this;
        }

        public Criteria andInvesterIsNotNull() {
            addCriterion("invester is not null");
            return (Criteria) this;
        }

        public Criteria andInvesterEqualTo(String value) {
            addCriterion("invester =", value, "invester");
            return (Criteria) this;
        }

        public Criteria andInvesterNotEqualTo(String value) {
            addCriterion("invester <>", value, "invester");
            return (Criteria) this;
        }

        public Criteria andInvesterGreaterThan(String value) {
            addCriterion("invester >", value, "invester");
            return (Criteria) this;
        }

        public Criteria andInvesterGreaterThanOrEqualTo(String value) {
            addCriterion("invester >=", value, "invester");
            return (Criteria) this;
        }

        public Criteria andInvesterLessThan(String value) {
            addCriterion("invester <", value, "invester");
            return (Criteria) this;
        }

        public Criteria andInvesterLessThanOrEqualTo(String value) {
            addCriterion("invester <=", value, "invester");
            return (Criteria) this;
        }

        public Criteria andInvesterLike(String value) {
            addCriterion("invester like", value, "invester");
            return (Criteria) this;
        }

        public Criteria andInvesterNotLike(String value) {
            addCriterion("invester not like", value, "invester");
            return (Criteria) this;
        }

        public Criteria andInvesterIn(List<String> values) {
            addCriterion("invester in", values, "invester");
            return (Criteria) this;
        }

        public Criteria andInvesterNotIn(List<String> values) {
            addCriterion("invester not in", values, "invester");
            return (Criteria) this;
        }

        public Criteria andInvesterBetween(String value1, String value2) {
            addCriterion("invester between", value1, value2, "invester");
            return (Criteria) this;
        }

        public Criteria andInvesterNotBetween(String value1, String value2) {
            addCriterion("invester not between", value1, value2, "invester");
            return (Criteria) this;
        }

        public Criteria andPersonNameIsNull() {
            addCriterion("person_name is null");
            return (Criteria) this;
        }

        public Criteria andPersonNameIsNotNull() {
            addCriterion("person_name is not null");
            return (Criteria) this;
        }

        public Criteria andPersonNameEqualTo(String value) {
            addCriterion("person_name =", value, "personName");
            return (Criteria) this;
        }

        public Criteria andPersonNameNotEqualTo(String value) {
            addCriterion("person_name <>", value, "personName");
            return (Criteria) this;
        }

        public Criteria andPersonNameGreaterThan(String value) {
            addCriterion("person_name >", value, "personName");
            return (Criteria) this;
        }

        public Criteria andPersonNameGreaterThanOrEqualTo(String value) {
            addCriterion("person_name >=", value, "personName");
            return (Criteria) this;
        }

        public Criteria andPersonNameLessThan(String value) {
            addCriterion("person_name <", value, "personName");
            return (Criteria) this;
        }

        public Criteria andPersonNameLessThanOrEqualTo(String value) {
            addCriterion("person_name <=", value, "personName");
            return (Criteria) this;
        }

        public Criteria andPersonNameLike(String value) {
            addCriterion("person_name like", value, "personName");
            return (Criteria) this;
        }

        public Criteria andPersonNameNotLike(String value) {
            addCriterion("person_name not like", value, "personName");
            return (Criteria) this;
        }

        public Criteria andPersonNameIn(List<String> values) {
            addCriterion("person_name in", values, "personName");
            return (Criteria) this;
        }

        public Criteria andPersonNameNotIn(List<String> values) {
            addCriterion("person_name not in", values, "personName");
            return (Criteria) this;
        }

        public Criteria andPersonNameBetween(String value1, String value2) {
            addCriterion("person_name between", value1, value2, "personName");
            return (Criteria) this;
        }

        public Criteria andPersonNameNotBetween(String value1, String value2) {
            addCriterion("person_name not between", value1, value2, "personName");
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

        public Criteria andOutTradeSnIsNull() {
            addCriterion("out_trade_sn is null");
            return (Criteria) this;
        }

        public Criteria andOutTradeSnIsNotNull() {
            addCriterion("out_trade_sn is not null");
            return (Criteria) this;
        }

        public Criteria andOutTradeSnEqualTo(String value) {
            addCriterion("out_trade_sn =", value, "outTradeSn");
            return (Criteria) this;
        }

        public Criteria andOutTradeSnNotEqualTo(String value) {
            addCriterion("out_trade_sn <>", value, "outTradeSn");
            return (Criteria) this;
        }

        public Criteria andOutTradeSnGreaterThan(String value) {
            addCriterion("out_trade_sn >", value, "outTradeSn");
            return (Criteria) this;
        }

        public Criteria andOutTradeSnGreaterThanOrEqualTo(String value) {
            addCriterion("out_trade_sn >=", value, "outTradeSn");
            return (Criteria) this;
        }

        public Criteria andOutTradeSnLessThan(String value) {
            addCriterion("out_trade_sn <", value, "outTradeSn");
            return (Criteria) this;
        }

        public Criteria andOutTradeSnLessThanOrEqualTo(String value) {
            addCriterion("out_trade_sn <=", value, "outTradeSn");
            return (Criteria) this;
        }

        public Criteria andOutTradeSnLike(String value) {
            addCriterion("out_trade_sn like", value, "outTradeSn");
            return (Criteria) this;
        }

        public Criteria andOutTradeSnNotLike(String value) {
            addCriterion("out_trade_sn not like", value, "outTradeSn");
            return (Criteria) this;
        }

        public Criteria andOutTradeSnIn(List<String> values) {
            addCriterion("out_trade_sn in", values, "outTradeSn");
            return (Criteria) this;
        }

        public Criteria andOutTradeSnNotIn(List<String> values) {
            addCriterion("out_trade_sn not in", values, "outTradeSn");
            return (Criteria) this;
        }

        public Criteria andOutTradeSnBetween(String value1, String value2) {
            addCriterion("out_trade_sn between", value1, value2, "outTradeSn");
            return (Criteria) this;
        }

        public Criteria andOutTradeSnNotBetween(String value1, String value2) {
            addCriterion("out_trade_sn not between", value1, value2, "outTradeSn");
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