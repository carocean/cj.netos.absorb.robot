package cj.netos.absorb.robot.model;

import java.math.BigDecimal;
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

        public Criteria andBankIsNull() {
            addCriterion("bank is null");
            return (Criteria) this;
        }

        public Criteria andBankIsNotNull() {
            addCriterion("bank is not null");
            return (Criteria) this;
        }

        public Criteria andBankEqualTo(String value) {
            addCriterion("bank =", value, "bank");
            return (Criteria) this;
        }

        public Criteria andBankNotEqualTo(String value) {
            addCriterion("bank <>", value, "bank");
            return (Criteria) this;
        }

        public Criteria andBankGreaterThan(String value) {
            addCriterion("bank >", value, "bank");
            return (Criteria) this;
        }

        public Criteria andBankGreaterThanOrEqualTo(String value) {
            addCriterion("bank >=", value, "bank");
            return (Criteria) this;
        }

        public Criteria andBankLessThan(String value) {
            addCriterion("bank <", value, "bank");
            return (Criteria) this;
        }

        public Criteria andBankLessThanOrEqualTo(String value) {
            addCriterion("bank <=", value, "bank");
            return (Criteria) this;
        }

        public Criteria andBankLike(String value) {
            addCriterion("bank like", value, "bank");
            return (Criteria) this;
        }

        public Criteria andBankNotLike(String value) {
            addCriterion("bank not like", value, "bank");
            return (Criteria) this;
        }

        public Criteria andBankIn(List<String> values) {
            addCriterion("bank in", values, "bank");
            return (Criteria) this;
        }

        public Criteria andBankNotIn(List<String> values) {
            addCriterion("bank not in", values, "bank");
            return (Criteria) this;
        }

        public Criteria andBankBetween(String value1, String value2) {
            addCriterion("bank between", value1, value2, "bank");
            return (Criteria) this;
        }

        public Criteria andBankNotBetween(String value1, String value2) {
            addCriterion("bank not between", value1, value2, "bank");
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

        public Criteria andPriceIsNull() {
            addCriterion("price is null");
            return (Criteria) this;
        }

        public Criteria andPriceIsNotNull() {
            addCriterion("price is not null");
            return (Criteria) this;
        }

        public Criteria andPriceEqualTo(BigDecimal value) {
            addCriterion("price =", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceNotEqualTo(BigDecimal value) {
            addCriterion("price <>", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceGreaterThan(BigDecimal value) {
            addCriterion("price >", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("price >=", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceLessThan(BigDecimal value) {
            addCriterion("price <", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("price <=", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceIn(List<BigDecimal> values) {
            addCriterion("price in", values, "price");
            return (Criteria) this;
        }

        public Criteria andPriceNotIn(List<BigDecimal> values) {
            addCriterion("price not in", values, "price");
            return (Criteria) this;
        }

        public Criteria andPriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("price between", value1, value2, "price");
            return (Criteria) this;
        }

        public Criteria andPriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("price not between", value1, value2, "price");
            return (Criteria) this;
        }

        public Criteria andTimesIsNull() {
            addCriterion("times is null");
            return (Criteria) this;
        }

        public Criteria andTimesIsNotNull() {
            addCriterion("times is not null");
            return (Criteria) this;
        }

        public Criteria andTimesEqualTo(Long value) {
            addCriterion("times =", value, "times");
            return (Criteria) this;
        }

        public Criteria andTimesNotEqualTo(Long value) {
            addCriterion("times <>", value, "times");
            return (Criteria) this;
        }

        public Criteria andTimesGreaterThan(Long value) {
            addCriterion("times >", value, "times");
            return (Criteria) this;
        }

        public Criteria andTimesGreaterThanOrEqualTo(Long value) {
            addCriterion("times >=", value, "times");
            return (Criteria) this;
        }

        public Criteria andTimesLessThan(Long value) {
            addCriterion("times <", value, "times");
            return (Criteria) this;
        }

        public Criteria andTimesLessThanOrEqualTo(Long value) {
            addCriterion("times <=", value, "times");
            return (Criteria) this;
        }

        public Criteria andTimesIn(List<Long> values) {
            addCriterion("times in", values, "times");
            return (Criteria) this;
        }

        public Criteria andTimesNotIn(List<Long> values) {
            addCriterion("times not in", values, "times");
            return (Criteria) this;
        }

        public Criteria andTimesBetween(Long value1, Long value2) {
            addCriterion("times between", value1, value2, "times");
            return (Criteria) this;
        }

        public Criteria andTimesNotBetween(Long value1, Long value2) {
            addCriterion("times not between", value1, value2, "times");
            return (Criteria) this;
        }

        public Criteria andUtimeIsNull() {
            addCriterion("utime is null");
            return (Criteria) this;
        }

        public Criteria andUtimeIsNotNull() {
            addCriterion("utime is not null");
            return (Criteria) this;
        }

        public Criteria andUtimeEqualTo(String value) {
            addCriterion("utime =", value, "utime");
            return (Criteria) this;
        }

        public Criteria andUtimeNotEqualTo(String value) {
            addCriterion("utime <>", value, "utime");
            return (Criteria) this;
        }

        public Criteria andUtimeGreaterThan(String value) {
            addCriterion("utime >", value, "utime");
            return (Criteria) this;
        }

        public Criteria andUtimeGreaterThanOrEqualTo(String value) {
            addCriterion("utime >=", value, "utime");
            return (Criteria) this;
        }

        public Criteria andUtimeLessThan(String value) {
            addCriterion("utime <", value, "utime");
            return (Criteria) this;
        }

        public Criteria andUtimeLessThanOrEqualTo(String value) {
            addCriterion("utime <=", value, "utime");
            return (Criteria) this;
        }

        public Criteria andUtimeLike(String value) {
            addCriterion("utime like", value, "utime");
            return (Criteria) this;
        }

        public Criteria andUtimeNotLike(String value) {
            addCriterion("utime not like", value, "utime");
            return (Criteria) this;
        }

        public Criteria andUtimeIn(List<String> values) {
            addCriterion("utime in", values, "utime");
            return (Criteria) this;
        }

        public Criteria andUtimeNotIn(List<String> values) {
            addCriterion("utime not in", values, "utime");
            return (Criteria) this;
        }

        public Criteria andUtimeBetween(String value1, String value2) {
            addCriterion("utime between", value1, value2, "utime");
            return (Criteria) this;
        }

        public Criteria andUtimeNotBetween(String value1, String value2) {
            addCriterion("utime not between", value1, value2, "utime");
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