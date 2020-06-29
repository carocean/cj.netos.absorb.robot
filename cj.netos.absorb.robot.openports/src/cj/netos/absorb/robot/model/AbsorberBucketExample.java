package cj.netos.absorb.robot.model;

import java.util.ArrayList;
import java.util.List;

public class AbsorberBucketExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    public AbsorberBucketExample() {
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

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(String value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(String value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(String value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(String value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(String value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(String value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLike(String value) {
            addCriterion("id like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotLike(String value) {
            addCriterion("id not like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<String> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<String> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(String value1, String value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(String value1, String value2) {
            addCriterion("id not between", value1, value2, "id");
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

        public Criteria andPeriodIsNull() {
            addCriterion("period is null");
            return (Criteria) this;
        }

        public Criteria andPeriodIsNotNull() {
            addCriterion("period is not null");
            return (Criteria) this;
        }

        public Criteria andPeriodEqualTo(Long value) {
            addCriterion("period =", value, "period");
            return (Criteria) this;
        }

        public Criteria andPeriodNotEqualTo(Long value) {
            addCriterion("period <>", value, "period");
            return (Criteria) this;
        }

        public Criteria andPeriodGreaterThan(Long value) {
            addCriterion("period >", value, "period");
            return (Criteria) this;
        }

        public Criteria andPeriodGreaterThanOrEqualTo(Long value) {
            addCriterion("period >=", value, "period");
            return (Criteria) this;
        }

        public Criteria andPeriodLessThan(Long value) {
            addCriterion("period <", value, "period");
            return (Criteria) this;
        }

        public Criteria andPeriodLessThanOrEqualTo(Long value) {
            addCriterion("period <=", value, "period");
            return (Criteria) this;
        }

        public Criteria andPeriodIn(List<Long> values) {
            addCriterion("period in", values, "period");
            return (Criteria) this;
        }

        public Criteria andPeriodNotIn(List<Long> values) {
            addCriterion("period not in", values, "period");
            return (Criteria) this;
        }

        public Criteria andPeriodBetween(Long value1, Long value2) {
            addCriterion("period between", value1, value2, "period");
            return (Criteria) this;
        }

        public Criteria andPeriodNotBetween(Long value1, Long value2) {
            addCriterion("period not between", value1, value2, "period");
            return (Criteria) this;
        }

        public Criteria andPerAmountIsNull() {
            addCriterion("per_amount is null");
            return (Criteria) this;
        }

        public Criteria andPerAmountIsNotNull() {
            addCriterion("per_amount is not null");
            return (Criteria) this;
        }

        public Criteria andPerAmountEqualTo(Long value) {
            addCriterion("per_amount =", value, "perAmount");
            return (Criteria) this;
        }

        public Criteria andPerAmountNotEqualTo(Long value) {
            addCriterion("per_amount <>", value, "perAmount");
            return (Criteria) this;
        }

        public Criteria andPerAmountGreaterThan(Long value) {
            addCriterion("per_amount >", value, "perAmount");
            return (Criteria) this;
        }

        public Criteria andPerAmountGreaterThanOrEqualTo(Long value) {
            addCriterion("per_amount >=", value, "perAmount");
            return (Criteria) this;
        }

        public Criteria andPerAmountLessThan(Long value) {
            addCriterion("per_amount <", value, "perAmount");
            return (Criteria) this;
        }

        public Criteria andPerAmountLessThanOrEqualTo(Long value) {
            addCriterion("per_amount <=", value, "perAmount");
            return (Criteria) this;
        }

        public Criteria andPerAmountIn(List<Long> values) {
            addCriterion("per_amount in", values, "perAmount");
            return (Criteria) this;
        }

        public Criteria andPerAmountNotIn(List<Long> values) {
            addCriterion("per_amount not in", values, "perAmount");
            return (Criteria) this;
        }

        public Criteria andPerAmountBetween(Long value1, Long value2) {
            addCriterion("per_amount between", value1, value2, "perAmount");
            return (Criteria) this;
        }

        public Criteria andPerAmountNotBetween(Long value1, Long value2) {
            addCriterion("per_amount not between", value1, value2, "perAmount");
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