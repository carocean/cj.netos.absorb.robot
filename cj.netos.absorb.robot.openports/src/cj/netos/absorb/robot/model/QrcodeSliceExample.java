package cj.netos.absorb.robot.model;

import java.util.ArrayList;
import java.util.List;

public class QrcodeSliceExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    public QrcodeSliceExample() {
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

        public Criteria andHrefIsNull() {
            addCriterion("href is null");
            return (Criteria) this;
        }

        public Criteria andHrefIsNotNull() {
            addCriterion("href is not null");
            return (Criteria) this;
        }

        public Criteria andHrefEqualTo(String value) {
            addCriterion("href =", value, "href");
            return (Criteria) this;
        }

        public Criteria andHrefNotEqualTo(String value) {
            addCriterion("href <>", value, "href");
            return (Criteria) this;
        }

        public Criteria andHrefGreaterThan(String value) {
            addCriterion("href >", value, "href");
            return (Criteria) this;
        }

        public Criteria andHrefGreaterThanOrEqualTo(String value) {
            addCriterion("href >=", value, "href");
            return (Criteria) this;
        }

        public Criteria andHrefLessThan(String value) {
            addCriterion("href <", value, "href");
            return (Criteria) this;
        }

        public Criteria andHrefLessThanOrEqualTo(String value) {
            addCriterion("href <=", value, "href");
            return (Criteria) this;
        }

        public Criteria andHrefLike(String value) {
            addCriterion("href like", value, "href");
            return (Criteria) this;
        }

        public Criteria andHrefNotLike(String value) {
            addCriterion("href not like", value, "href");
            return (Criteria) this;
        }

        public Criteria andHrefIn(List<String> values) {
            addCriterion("href in", values, "href");
            return (Criteria) this;
        }

        public Criteria andHrefNotIn(List<String> values) {
            addCriterion("href not in", values, "href");
            return (Criteria) this;
        }

        public Criteria andHrefBetween(String value1, String value2) {
            addCriterion("href between", value1, value2, "href");
            return (Criteria) this;
        }

        public Criteria andHrefNotBetween(String value1, String value2) {
            addCriterion("href not between", value1, value2, "href");
            return (Criteria) this;
        }

        public Criteria andCreatorIsNull() {
            addCriterion("creator is null");
            return (Criteria) this;
        }

        public Criteria andCreatorIsNotNull() {
            addCriterion("creator is not null");
            return (Criteria) this;
        }

        public Criteria andCreatorEqualTo(String value) {
            addCriterion("creator =", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorNotEqualTo(String value) {
            addCriterion("creator <>", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorGreaterThan(String value) {
            addCriterion("creator >", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorGreaterThanOrEqualTo(String value) {
            addCriterion("creator >=", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorLessThan(String value) {
            addCriterion("creator <", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorLessThanOrEqualTo(String value) {
            addCriterion("creator <=", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorLike(String value) {
            addCriterion("creator like", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorNotLike(String value) {
            addCriterion("creator not like", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorIn(List<String> values) {
            addCriterion("creator in", values, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorNotIn(List<String> values) {
            addCriterion("creator not in", values, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorBetween(String value1, String value2) {
            addCriterion("creator between", value1, value2, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorNotBetween(String value1, String value2) {
            addCriterion("creator not between", value1, value2, "creator");
            return (Criteria) this;
        }

        public Criteria andCnameIsNull() {
            addCriterion("cname is null");
            return (Criteria) this;
        }

        public Criteria andCnameIsNotNull() {
            addCriterion("cname is not null");
            return (Criteria) this;
        }

        public Criteria andCnameEqualTo(String value) {
            addCriterion("cname =", value, "cname");
            return (Criteria) this;
        }

        public Criteria andCnameNotEqualTo(String value) {
            addCriterion("cname <>", value, "cname");
            return (Criteria) this;
        }

        public Criteria andCnameGreaterThan(String value) {
            addCriterion("cname >", value, "cname");
            return (Criteria) this;
        }

        public Criteria andCnameGreaterThanOrEqualTo(String value) {
            addCriterion("cname >=", value, "cname");
            return (Criteria) this;
        }

        public Criteria andCnameLessThan(String value) {
            addCriterion("cname <", value, "cname");
            return (Criteria) this;
        }

        public Criteria andCnameLessThanOrEqualTo(String value) {
            addCriterion("cname <=", value, "cname");
            return (Criteria) this;
        }

        public Criteria andCnameLike(String value) {
            addCriterion("cname like", value, "cname");
            return (Criteria) this;
        }

        public Criteria andCnameNotLike(String value) {
            addCriterion("cname not like", value, "cname");
            return (Criteria) this;
        }

        public Criteria andCnameIn(List<String> values) {
            addCriterion("cname in", values, "cname");
            return (Criteria) this;
        }

        public Criteria andCnameNotIn(List<String> values) {
            addCriterion("cname not in", values, "cname");
            return (Criteria) this;
        }

        public Criteria andCnameBetween(String value1, String value2) {
            addCriterion("cname between", value1, value2, "cname");
            return (Criteria) this;
        }

        public Criteria andCnameNotBetween(String value1, String value2) {
            addCriterion("cname not between", value1, value2, "cname");
            return (Criteria) this;
        }

        public Criteria andConsumerIsNull() {
            addCriterion("consumer is null");
            return (Criteria) this;
        }

        public Criteria andConsumerIsNotNull() {
            addCriterion("consumer is not null");
            return (Criteria) this;
        }

        public Criteria andConsumerEqualTo(String value) {
            addCriterion("consumer =", value, "consumer");
            return (Criteria) this;
        }

        public Criteria andConsumerNotEqualTo(String value) {
            addCriterion("consumer <>", value, "consumer");
            return (Criteria) this;
        }

        public Criteria andConsumerGreaterThan(String value) {
            addCriterion("consumer >", value, "consumer");
            return (Criteria) this;
        }

        public Criteria andConsumerGreaterThanOrEqualTo(String value) {
            addCriterion("consumer >=", value, "consumer");
            return (Criteria) this;
        }

        public Criteria andConsumerLessThan(String value) {
            addCriterion("consumer <", value, "consumer");
            return (Criteria) this;
        }

        public Criteria andConsumerLessThanOrEqualTo(String value) {
            addCriterion("consumer <=", value, "consumer");
            return (Criteria) this;
        }

        public Criteria andConsumerLike(String value) {
            addCriterion("consumer like", value, "consumer");
            return (Criteria) this;
        }

        public Criteria andConsumerNotLike(String value) {
            addCriterion("consumer not like", value, "consumer");
            return (Criteria) this;
        }

        public Criteria andConsumerIn(List<String> values) {
            addCriterion("consumer in", values, "consumer");
            return (Criteria) this;
        }

        public Criteria andConsumerNotIn(List<String> values) {
            addCriterion("consumer not in", values, "consumer");
            return (Criteria) this;
        }

        public Criteria andConsumerBetween(String value1, String value2) {
            addCriterion("consumer between", value1, value2, "consumer");
            return (Criteria) this;
        }

        public Criteria andConsumerNotBetween(String value1, String value2) {
            addCriterion("consumer not between", value1, value2, "consumer");
            return (Criteria) this;
        }

        public Criteria andTemplateIsNull() {
            addCriterion("`template` is null");
            return (Criteria) this;
        }

        public Criteria andTemplateIsNotNull() {
            addCriterion("`template` is not null");
            return (Criteria) this;
        }

        public Criteria andTemplateEqualTo(String value) {
            addCriterion("`template` =", value, "template");
            return (Criteria) this;
        }

        public Criteria andTemplateNotEqualTo(String value) {
            addCriterion("`template` <>", value, "template");
            return (Criteria) this;
        }

        public Criteria andTemplateGreaterThan(String value) {
            addCriterion("`template` >", value, "template");
            return (Criteria) this;
        }

        public Criteria andTemplateGreaterThanOrEqualTo(String value) {
            addCriterion("`template` >=", value, "template");
            return (Criteria) this;
        }

        public Criteria andTemplateLessThan(String value) {
            addCriterion("`template` <", value, "template");
            return (Criteria) this;
        }

        public Criteria andTemplateLessThanOrEqualTo(String value) {
            addCriterion("`template` <=", value, "template");
            return (Criteria) this;
        }

        public Criteria andTemplateLike(String value) {
            addCriterion("`template` like", value, "template");
            return (Criteria) this;
        }

        public Criteria andTemplateNotLike(String value) {
            addCriterion("`template` not like", value, "template");
            return (Criteria) this;
        }

        public Criteria andTemplateIn(List<String> values) {
            addCriterion("`template` in", values, "template");
            return (Criteria) this;
        }

        public Criteria andTemplateNotIn(List<String> values) {
            addCriterion("`template` not in", values, "template");
            return (Criteria) this;
        }

        public Criteria andTemplateBetween(String value1, String value2) {
            addCriterion("`template` between", value1, value2, "template");
            return (Criteria) this;
        }

        public Criteria andTemplateNotBetween(String value1, String value2) {
            addCriterion("`template` not between", value1, value2, "template");
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

        public Criteria andExpireIsNull() {
            addCriterion("expire is null");
            return (Criteria) this;
        }

        public Criteria andExpireIsNotNull() {
            addCriterion("expire is not null");
            return (Criteria) this;
        }

        public Criteria andExpireEqualTo(Long value) {
            addCriterion("expire =", value, "expire");
            return (Criteria) this;
        }

        public Criteria andExpireNotEqualTo(Long value) {
            addCriterion("expire <>", value, "expire");
            return (Criteria) this;
        }

        public Criteria andExpireGreaterThan(Long value) {
            addCriterion("expire >", value, "expire");
            return (Criteria) this;
        }

        public Criteria andExpireGreaterThanOrEqualTo(Long value) {
            addCriterion("expire >=", value, "expire");
            return (Criteria) this;
        }

        public Criteria andExpireLessThan(Long value) {
            addCriterion("expire <", value, "expire");
            return (Criteria) this;
        }

        public Criteria andExpireLessThanOrEqualTo(Long value) {
            addCriterion("expire <=", value, "expire");
            return (Criteria) this;
        }

        public Criteria andExpireIn(List<Long> values) {
            addCriterion("expire in", values, "expire");
            return (Criteria) this;
        }

        public Criteria andExpireNotIn(List<Long> values) {
            addCriterion("expire not in", values, "expire");
            return (Criteria) this;
        }

        public Criteria andExpireBetween(Long value1, Long value2) {
            addCriterion("expire between", value1, value2, "expire");
            return (Criteria) this;
        }

        public Criteria andExpireNotBetween(Long value1, Long value2) {
            addCriterion("expire not between", value1, value2, "expire");
            return (Criteria) this;
        }

        public Criteria andLocationIsNull() {
            addCriterion("`location` is null");
            return (Criteria) this;
        }

        public Criteria andLocationIsNotNull() {
            addCriterion("`location` is not null");
            return (Criteria) this;
        }

        public Criteria andLocationEqualTo(String value) {
            addCriterion("`location` =", value, "location");
            return (Criteria) this;
        }

        public Criteria andLocationNotEqualTo(String value) {
            addCriterion("`location` <>", value, "location");
            return (Criteria) this;
        }

        public Criteria andLocationGreaterThan(String value) {
            addCriterion("`location` >", value, "location");
            return (Criteria) this;
        }

        public Criteria andLocationGreaterThanOrEqualTo(String value) {
            addCriterion("`location` >=", value, "location");
            return (Criteria) this;
        }

        public Criteria andLocationLessThan(String value) {
            addCriterion("`location` <", value, "location");
            return (Criteria) this;
        }

        public Criteria andLocationLessThanOrEqualTo(String value) {
            addCriterion("`location` <=", value, "location");
            return (Criteria) this;
        }

        public Criteria andLocationLike(String value) {
            addCriterion("`location` like", value, "location");
            return (Criteria) this;
        }

        public Criteria andLocationNotLike(String value) {
            addCriterion("`location` not like", value, "location");
            return (Criteria) this;
        }

        public Criteria andLocationIn(List<String> values) {
            addCriterion("`location` in", values, "location");
            return (Criteria) this;
        }

        public Criteria andLocationNotIn(List<String> values) {
            addCriterion("`location` not in", values, "location");
            return (Criteria) this;
        }

        public Criteria andLocationBetween(String value1, String value2) {
            addCriterion("`location` between", value1, value2, "location");
            return (Criteria) this;
        }

        public Criteria andLocationNotBetween(String value1, String value2) {
            addCriterion("`location` not between", value1, value2, "location");
            return (Criteria) this;
        }

        public Criteria andRadiusIsNull() {
            addCriterion("radius is null");
            return (Criteria) this;
        }

        public Criteria andRadiusIsNotNull() {
            addCriterion("radius is not null");
            return (Criteria) this;
        }

        public Criteria andRadiusEqualTo(Long value) {
            addCriterion("radius =", value, "radius");
            return (Criteria) this;
        }

        public Criteria andRadiusNotEqualTo(Long value) {
            addCriterion("radius <>", value, "radius");
            return (Criteria) this;
        }

        public Criteria andRadiusGreaterThan(Long value) {
            addCriterion("radius >", value, "radius");
            return (Criteria) this;
        }

        public Criteria andRadiusGreaterThanOrEqualTo(Long value) {
            addCriterion("radius >=", value, "radius");
            return (Criteria) this;
        }

        public Criteria andRadiusLessThan(Long value) {
            addCriterion("radius <", value, "radius");
            return (Criteria) this;
        }

        public Criteria andRadiusLessThanOrEqualTo(Long value) {
            addCriterion("radius <=", value, "radius");
            return (Criteria) this;
        }

        public Criteria andRadiusIn(List<Long> values) {
            addCriterion("radius in", values, "radius");
            return (Criteria) this;
        }

        public Criteria andRadiusNotIn(List<Long> values) {
            addCriterion("radius not in", values, "radius");
            return (Criteria) this;
        }

        public Criteria andRadiusBetween(Long value1, Long value2) {
            addCriterion("radius between", value1, value2, "radius");
            return (Criteria) this;
        }

        public Criteria andRadiusNotBetween(Long value1, Long value2) {
            addCriterion("radius not between", value1, value2, "radius");
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

        public Criteria andOriginAbsorberIsNull() {
            addCriterion("origin_absorber is null");
            return (Criteria) this;
        }

        public Criteria andOriginAbsorberIsNotNull() {
            addCriterion("origin_absorber is not null");
            return (Criteria) this;
        }

        public Criteria andOriginAbsorberEqualTo(String value) {
            addCriterion("origin_absorber =", value, "originAbsorber");
            return (Criteria) this;
        }

        public Criteria andOriginAbsorberNotEqualTo(String value) {
            addCriterion("origin_absorber <>", value, "originAbsorber");
            return (Criteria) this;
        }

        public Criteria andOriginAbsorberGreaterThan(String value) {
            addCriterion("origin_absorber >", value, "originAbsorber");
            return (Criteria) this;
        }

        public Criteria andOriginAbsorberGreaterThanOrEqualTo(String value) {
            addCriterion("origin_absorber >=", value, "originAbsorber");
            return (Criteria) this;
        }

        public Criteria andOriginAbsorberLessThan(String value) {
            addCriterion("origin_absorber <", value, "originAbsorber");
            return (Criteria) this;
        }

        public Criteria andOriginAbsorberLessThanOrEqualTo(String value) {
            addCriterion("origin_absorber <=", value, "originAbsorber");
            return (Criteria) this;
        }

        public Criteria andOriginAbsorberLike(String value) {
            addCriterion("origin_absorber like", value, "originAbsorber");
            return (Criteria) this;
        }

        public Criteria andOriginAbsorberNotLike(String value) {
            addCriterion("origin_absorber not like", value, "originAbsorber");
            return (Criteria) this;
        }

        public Criteria andOriginAbsorberIn(List<String> values) {
            addCriterion("origin_absorber in", values, "originAbsorber");
            return (Criteria) this;
        }

        public Criteria andOriginAbsorberNotIn(List<String> values) {
            addCriterion("origin_absorber not in", values, "originAbsorber");
            return (Criteria) this;
        }

        public Criteria andOriginAbsorberBetween(String value1, String value2) {
            addCriterion("origin_absorber between", value1, value2, "originAbsorber");
            return (Criteria) this;
        }

        public Criteria andOriginAbsorberNotBetween(String value1, String value2) {
            addCriterion("origin_absorber not between", value1, value2, "originAbsorber");
            return (Criteria) this;
        }

        public Criteria andOriginPersonIsNull() {
            addCriterion("origin_person is null");
            return (Criteria) this;
        }

        public Criteria andOriginPersonIsNotNull() {
            addCriterion("origin_person is not null");
            return (Criteria) this;
        }

        public Criteria andOriginPersonEqualTo(String value) {
            addCriterion("origin_person =", value, "originPerson");
            return (Criteria) this;
        }

        public Criteria andOriginPersonNotEqualTo(String value) {
            addCriterion("origin_person <>", value, "originPerson");
            return (Criteria) this;
        }

        public Criteria andOriginPersonGreaterThan(String value) {
            addCriterion("origin_person >", value, "originPerson");
            return (Criteria) this;
        }

        public Criteria andOriginPersonGreaterThanOrEqualTo(String value) {
            addCriterion("origin_person >=", value, "originPerson");
            return (Criteria) this;
        }

        public Criteria andOriginPersonLessThan(String value) {
            addCriterion("origin_person <", value, "originPerson");
            return (Criteria) this;
        }

        public Criteria andOriginPersonLessThanOrEqualTo(String value) {
            addCriterion("origin_person <=", value, "originPerson");
            return (Criteria) this;
        }

        public Criteria andOriginPersonLike(String value) {
            addCriterion("origin_person like", value, "originPerson");
            return (Criteria) this;
        }

        public Criteria andOriginPersonNotLike(String value) {
            addCriterion("origin_person not like", value, "originPerson");
            return (Criteria) this;
        }

        public Criteria andOriginPersonIn(List<String> values) {
            addCriterion("origin_person in", values, "originPerson");
            return (Criteria) this;
        }

        public Criteria andOriginPersonNotIn(List<String> values) {
            addCriterion("origin_person not in", values, "originPerson");
            return (Criteria) this;
        }

        public Criteria andOriginPersonBetween(String value1, String value2) {
            addCriterion("origin_person between", value1, value2, "originPerson");
            return (Criteria) this;
        }

        public Criteria andOriginPersonNotBetween(String value1, String value2) {
            addCriterion("origin_person not between", value1, value2, "originPerson");
            return (Criteria) this;
        }

        public Criteria andBatchNoIsNull() {
            addCriterion("batch_no is null");
            return (Criteria) this;
        }

        public Criteria andBatchNoIsNotNull() {
            addCriterion("batch_no is not null");
            return (Criteria) this;
        }

        public Criteria andBatchNoEqualTo(String value) {
            addCriterion("batch_no =", value, "batchNo");
            return (Criteria) this;
        }

        public Criteria andBatchNoNotEqualTo(String value) {
            addCriterion("batch_no <>", value, "batchNo");
            return (Criteria) this;
        }

        public Criteria andBatchNoGreaterThan(String value) {
            addCriterion("batch_no >", value, "batchNo");
            return (Criteria) this;
        }

        public Criteria andBatchNoGreaterThanOrEqualTo(String value) {
            addCriterion("batch_no >=", value, "batchNo");
            return (Criteria) this;
        }

        public Criteria andBatchNoLessThan(String value) {
            addCriterion("batch_no <", value, "batchNo");
            return (Criteria) this;
        }

        public Criteria andBatchNoLessThanOrEqualTo(String value) {
            addCriterion("batch_no <=", value, "batchNo");
            return (Criteria) this;
        }

        public Criteria andBatchNoLike(String value) {
            addCriterion("batch_no like", value, "batchNo");
            return (Criteria) this;
        }

        public Criteria andBatchNoNotLike(String value) {
            addCriterion("batch_no not like", value, "batchNo");
            return (Criteria) this;
        }

        public Criteria andBatchNoIn(List<String> values) {
            addCriterion("batch_no in", values, "batchNo");
            return (Criteria) this;
        }

        public Criteria andBatchNoNotIn(List<String> values) {
            addCriterion("batch_no not in", values, "batchNo");
            return (Criteria) this;
        }

        public Criteria andBatchNoBetween(String value1, String value2) {
            addCriterion("batch_no between", value1, value2, "batchNo");
            return (Criteria) this;
        }

        public Criteria andBatchNoNotBetween(String value1, String value2) {
            addCriterion("batch_no not between", value1, value2, "batchNo");
            return (Criteria) this;
        }

        public Criteria andStateIsNull() {
            addCriterion("`state` is null");
            return (Criteria) this;
        }

        public Criteria andStateIsNotNull() {
            addCriterion("`state` is not null");
            return (Criteria) this;
        }

        public Criteria andStateEqualTo(Integer value) {
            addCriterion("`state` =", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotEqualTo(Integer value) {
            addCriterion("`state` <>", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateGreaterThan(Integer value) {
            addCriterion("`state` >", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateGreaterThanOrEqualTo(Integer value) {
            addCriterion("`state` >=", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLessThan(Integer value) {
            addCriterion("`state` <", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLessThanOrEqualTo(Integer value) {
            addCriterion("`state` <=", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateIn(List<Integer> values) {
            addCriterion("`state` in", values, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotIn(List<Integer> values) {
            addCriterion("`state` not in", values, "state");
            return (Criteria) this;
        }

        public Criteria andStateBetween(Integer value1, Integer value2) {
            addCriterion("`state` between", value1, value2, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotBetween(Integer value1, Integer value2) {
            addCriterion("`state` not between", value1, value2, "state");
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