package cj.netos.absorb.robot.model;

import java.util.ArrayList;
import java.util.List;

public class SliceTemplateExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    public SliceTemplateExample() {
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

        public Criteria andNameIsNull() {
            addCriterion("`name` is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("`name` is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("`name` =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("`name` <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("`name` >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("`name` >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("`name` <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("`name` <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("`name` like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("`name` not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("`name` in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("`name` not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("`name` between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("`name` not between", value1, value2, "name");
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

        public Criteria andCopyrightIsNull() {
            addCriterion("copyright is null");
            return (Criteria) this;
        }

        public Criteria andCopyrightIsNotNull() {
            addCriterion("copyright is not null");
            return (Criteria) this;
        }

        public Criteria andCopyrightEqualTo(String value) {
            addCriterion("copyright =", value, "copyright");
            return (Criteria) this;
        }

        public Criteria andCopyrightNotEqualTo(String value) {
            addCriterion("copyright <>", value, "copyright");
            return (Criteria) this;
        }

        public Criteria andCopyrightGreaterThan(String value) {
            addCriterion("copyright >", value, "copyright");
            return (Criteria) this;
        }

        public Criteria andCopyrightGreaterThanOrEqualTo(String value) {
            addCriterion("copyright >=", value, "copyright");
            return (Criteria) this;
        }

        public Criteria andCopyrightLessThan(String value) {
            addCriterion("copyright <", value, "copyright");
            return (Criteria) this;
        }

        public Criteria andCopyrightLessThanOrEqualTo(String value) {
            addCriterion("copyright <=", value, "copyright");
            return (Criteria) this;
        }

        public Criteria andCopyrightLike(String value) {
            addCriterion("copyright like", value, "copyright");
            return (Criteria) this;
        }

        public Criteria andCopyrightNotLike(String value) {
            addCriterion("copyright not like", value, "copyright");
            return (Criteria) this;
        }

        public Criteria andCopyrightIn(List<String> values) {
            addCriterion("copyright in", values, "copyright");
            return (Criteria) this;
        }

        public Criteria andCopyrightNotIn(List<String> values) {
            addCriterion("copyright not in", values, "copyright");
            return (Criteria) this;
        }

        public Criteria andCopyrightBetween(String value1, String value2) {
            addCriterion("copyright between", value1, value2, "copyright");
            return (Criteria) this;
        }

        public Criteria andCopyrightNotBetween(String value1, String value2) {
            addCriterion("copyright not between", value1, value2, "copyright");
            return (Criteria) this;
        }

        public Criteria andMaxAbsorbersIsNull() {
            addCriterion("max_absorbers is null");
            return (Criteria) this;
        }

        public Criteria andMaxAbsorbersIsNotNull() {
            addCriterion("max_absorbers is not null");
            return (Criteria) this;
        }

        public Criteria andMaxAbsorbersEqualTo(Long value) {
            addCriterion("max_absorbers =", value, "maxAbsorbers");
            return (Criteria) this;
        }

        public Criteria andMaxAbsorbersNotEqualTo(Long value) {
            addCriterion("max_absorbers <>", value, "maxAbsorbers");
            return (Criteria) this;
        }

        public Criteria andMaxAbsorbersGreaterThan(Long value) {
            addCriterion("max_absorbers >", value, "maxAbsorbers");
            return (Criteria) this;
        }

        public Criteria andMaxAbsorbersGreaterThanOrEqualTo(Long value) {
            addCriterion("max_absorbers >=", value, "maxAbsorbers");
            return (Criteria) this;
        }

        public Criteria andMaxAbsorbersLessThan(Long value) {
            addCriterion("max_absorbers <", value, "maxAbsorbers");
            return (Criteria) this;
        }

        public Criteria andMaxAbsorbersLessThanOrEqualTo(Long value) {
            addCriterion("max_absorbers <=", value, "maxAbsorbers");
            return (Criteria) this;
        }

        public Criteria andMaxAbsorbersIn(List<Long> values) {
            addCriterion("max_absorbers in", values, "maxAbsorbers");
            return (Criteria) this;
        }

        public Criteria andMaxAbsorbersNotIn(List<Long> values) {
            addCriterion("max_absorbers not in", values, "maxAbsorbers");
            return (Criteria) this;
        }

        public Criteria andMaxAbsorbersBetween(Long value1, Long value2) {
            addCriterion("max_absorbers between", value1, value2, "maxAbsorbers");
            return (Criteria) this;
        }

        public Criteria andMaxAbsorbersNotBetween(Long value1, Long value2) {
            addCriterion("max_absorbers not between", value1, value2, "maxAbsorbers");
            return (Criteria) this;
        }

        public Criteria andOwnerWeightIsNull() {
            addCriterion("owner_weight is null");
            return (Criteria) this;
        }

        public Criteria andOwnerWeightIsNotNull() {
            addCriterion("owner_weight is not null");
            return (Criteria) this;
        }

        public Criteria andOwnerWeightEqualTo(Long value) {
            addCriterion("owner_weight =", value, "ownerWeight");
            return (Criteria) this;
        }

        public Criteria andOwnerWeightNotEqualTo(Long value) {
            addCriterion("owner_weight <>", value, "ownerWeight");
            return (Criteria) this;
        }

        public Criteria andOwnerWeightGreaterThan(Long value) {
            addCriterion("owner_weight >", value, "ownerWeight");
            return (Criteria) this;
        }

        public Criteria andOwnerWeightGreaterThanOrEqualTo(Long value) {
            addCriterion("owner_weight >=", value, "ownerWeight");
            return (Criteria) this;
        }

        public Criteria andOwnerWeightLessThan(Long value) {
            addCriterion("owner_weight <", value, "ownerWeight");
            return (Criteria) this;
        }

        public Criteria andOwnerWeightLessThanOrEqualTo(Long value) {
            addCriterion("owner_weight <=", value, "ownerWeight");
            return (Criteria) this;
        }

        public Criteria andOwnerWeightIn(List<Long> values) {
            addCriterion("owner_weight in", values, "ownerWeight");
            return (Criteria) this;
        }

        public Criteria andOwnerWeightNotIn(List<Long> values) {
            addCriterion("owner_weight not in", values, "ownerWeight");
            return (Criteria) this;
        }

        public Criteria andOwnerWeightBetween(Long value1, Long value2) {
            addCriterion("owner_weight between", value1, value2, "ownerWeight");
            return (Criteria) this;
        }

        public Criteria andOwnerWeightNotBetween(Long value1, Long value2) {
            addCriterion("owner_weight not between", value1, value2, "ownerWeight");
            return (Criteria) this;
        }

        public Criteria andParticipWeightIsNull() {
            addCriterion("particip_weight is null");
            return (Criteria) this;
        }

        public Criteria andParticipWeightIsNotNull() {
            addCriterion("particip_weight is not null");
            return (Criteria) this;
        }

        public Criteria andParticipWeightEqualTo(Long value) {
            addCriterion("particip_weight =", value, "participWeight");
            return (Criteria) this;
        }

        public Criteria andParticipWeightNotEqualTo(Long value) {
            addCriterion("particip_weight <>", value, "participWeight");
            return (Criteria) this;
        }

        public Criteria andParticipWeightGreaterThan(Long value) {
            addCriterion("particip_weight >", value, "participWeight");
            return (Criteria) this;
        }

        public Criteria andParticipWeightGreaterThanOrEqualTo(Long value) {
            addCriterion("particip_weight >=", value, "participWeight");
            return (Criteria) this;
        }

        public Criteria andParticipWeightLessThan(Long value) {
            addCriterion("particip_weight <", value, "participWeight");
            return (Criteria) this;
        }

        public Criteria andParticipWeightLessThanOrEqualTo(Long value) {
            addCriterion("particip_weight <=", value, "participWeight");
            return (Criteria) this;
        }

        public Criteria andParticipWeightIn(List<Long> values) {
            addCriterion("particip_weight in", values, "participWeight");
            return (Criteria) this;
        }

        public Criteria andParticipWeightNotIn(List<Long> values) {
            addCriterion("particip_weight not in", values, "participWeight");
            return (Criteria) this;
        }

        public Criteria andParticipWeightBetween(Long value1, Long value2) {
            addCriterion("particip_weight between", value1, value2, "participWeight");
            return (Criteria) this;
        }

        public Criteria andParticipWeightNotBetween(Long value1, Long value2) {
            addCriterion("particip_weight not between", value1, value2, "participWeight");
            return (Criteria) this;
        }

        public Criteria andIngeoWeightIsNull() {
            addCriterion("ingeo_weight is null");
            return (Criteria) this;
        }

        public Criteria andIngeoWeightIsNotNull() {
            addCriterion("ingeo_weight is not null");
            return (Criteria) this;
        }

        public Criteria andIngeoWeightEqualTo(Long value) {
            addCriterion("ingeo_weight =", value, "ingeoWeight");
            return (Criteria) this;
        }

        public Criteria andIngeoWeightNotEqualTo(Long value) {
            addCriterion("ingeo_weight <>", value, "ingeoWeight");
            return (Criteria) this;
        }

        public Criteria andIngeoWeightGreaterThan(Long value) {
            addCriterion("ingeo_weight >", value, "ingeoWeight");
            return (Criteria) this;
        }

        public Criteria andIngeoWeightGreaterThanOrEqualTo(Long value) {
            addCriterion("ingeo_weight >=", value, "ingeoWeight");
            return (Criteria) this;
        }

        public Criteria andIngeoWeightLessThan(Long value) {
            addCriterion("ingeo_weight <", value, "ingeoWeight");
            return (Criteria) this;
        }

        public Criteria andIngeoWeightLessThanOrEqualTo(Long value) {
            addCriterion("ingeo_weight <=", value, "ingeoWeight");
            return (Criteria) this;
        }

        public Criteria andIngeoWeightIn(List<Long> values) {
            addCriterion("ingeo_weight in", values, "ingeoWeight");
            return (Criteria) this;
        }

        public Criteria andIngeoWeightNotIn(List<Long> values) {
            addCriterion("ingeo_weight not in", values, "ingeoWeight");
            return (Criteria) this;
        }

        public Criteria andIngeoWeightBetween(Long value1, Long value2) {
            addCriterion("ingeo_weight between", value1, value2, "ingeoWeight");
            return (Criteria) this;
        }

        public Criteria andIngeoWeightNotBetween(Long value1, Long value2) {
            addCriterion("ingeo_weight not between", value1, value2, "ingeoWeight");
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