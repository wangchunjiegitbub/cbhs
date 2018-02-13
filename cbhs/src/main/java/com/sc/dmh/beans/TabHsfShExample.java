package com.sc.dmh.beans;

import java.util.ArrayList;
import java.util.List;

public class TabHsfShExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table tab_hsf_sh
     *
     * @mbggenerated
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table tab_hsf_sh
     *
     * @mbggenerated
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table tab_hsf_sh
     *
     * @mbggenerated
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tab_hsf_sh
     *
     * @mbggenerated
     */
    public TabHsfShExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tab_hsf_sh
     *
     * @mbggenerated
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tab_hsf_sh
     *
     * @mbggenerated
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tab_hsf_sh
     *
     * @mbggenerated
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tab_hsf_sh
     *
     * @mbggenerated
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tab_hsf_sh
     *
     * @mbggenerated
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tab_hsf_sh
     *
     * @mbggenerated
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tab_hsf_sh
     *
     * @mbggenerated
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tab_hsf_sh
     *
     * @mbggenerated
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tab_hsf_sh
     *
     * @mbggenerated
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tab_hsf_sh
     *
     * @mbggenerated
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table tab_hsf_sh
     *
     * @mbggenerated
     */
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

        public Criteria andShIdIsNull() {
            addCriterion("sh_id is null");
            return (Criteria) this;
        }

        public Criteria andShIdIsNotNull() {
            addCriterion("sh_id is not null");
            return (Criteria) this;
        }

        public Criteria andShIdEqualTo(Integer value) {
            addCriterion("sh_id =", value, "shId");
            return (Criteria) this;
        }

        public Criteria andShIdNotEqualTo(Integer value) {
            addCriterion("sh_id <>", value, "shId");
            return (Criteria) this;
        }

        public Criteria andShIdGreaterThan(Integer value) {
            addCriterion("sh_id >", value, "shId");
            return (Criteria) this;
        }

        public Criteria andShIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("sh_id >=", value, "shId");
            return (Criteria) this;
        }

        public Criteria andShIdLessThan(Integer value) {
            addCriterion("sh_id <", value, "shId");
            return (Criteria) this;
        }

        public Criteria andShIdLessThanOrEqualTo(Integer value) {
            addCriterion("sh_id <=", value, "shId");
            return (Criteria) this;
        }

        public Criteria andShIdIn(List<Integer> values) {
            addCriterion("sh_id in", values, "shId");
            return (Criteria) this;
        }

        public Criteria andShIdNotIn(List<Integer> values) {
            addCriterion("sh_id not in", values, "shId");
            return (Criteria) this;
        }

        public Criteria andShIdBetween(Integer value1, Integer value2) {
            addCriterion("sh_id between", value1, value2, "shId");
            return (Criteria) this;
        }

        public Criteria andShIdNotBetween(Integer value1, Integer value2) {
            addCriterion("sh_id not between", value1, value2, "shId");
            return (Criteria) this;
        }

        public Criteria andShNameIsNull() {
            addCriterion("sh_name is null");
            return (Criteria) this;
        }

        public Criteria andShNameIsNotNull() {
            addCriterion("sh_name is not null");
            return (Criteria) this;
        }

        public Criteria andShNameEqualTo(String value) {
            addCriterion("sh_name =", value, "shName");
            return (Criteria) this;
        }

        public Criteria andShNameNotEqualTo(String value) {
            addCriterion("sh_name <>", value, "shName");
            return (Criteria) this;
        }

        public Criteria andShNameGreaterThan(String value) {
            addCriterion("sh_name >", value, "shName");
            return (Criteria) this;
        }

        public Criteria andShNameGreaterThanOrEqualTo(String value) {
            addCriterion("sh_name >=", value, "shName");
            return (Criteria) this;
        }

        public Criteria andShNameLessThan(String value) {
            addCriterion("sh_name <", value, "shName");
            return (Criteria) this;
        }

        public Criteria andShNameLessThanOrEqualTo(String value) {
            addCriterion("sh_name <=", value, "shName");
            return (Criteria) this;
        }

        public Criteria andShNameLike(String value) {
            addCriterion("sh_name like", value, "shName");
            return (Criteria) this;
        }

        public Criteria andShNameNotLike(String value) {
            addCriterion("sh_name not like", value, "shName");
            return (Criteria) this;
        }

        public Criteria andShNameIn(List<String> values) {
            addCriterion("sh_name in", values, "shName");
            return (Criteria) this;
        }

        public Criteria andShNameNotIn(List<String> values) {
            addCriterion("sh_name not in", values, "shName");
            return (Criteria) this;
        }

        public Criteria andShNameBetween(String value1, String value2) {
            addCriterion("sh_name between", value1, value2, "shName");
            return (Criteria) this;
        }

        public Criteria andShNameNotBetween(String value1, String value2) {
            addCriterion("sh_name not between", value1, value2, "shName");
            return (Criteria) this;
        }

        public Criteria andShDepidIsNull() {
            addCriterion("sh_depid is null");
            return (Criteria) this;
        }

        public Criteria andShDepidIsNotNull() {
            addCriterion("sh_depid is not null");
            return (Criteria) this;
        }

        public Criteria andShDepidEqualTo(Integer value) {
            addCriterion("sh_depid =", value, "shDepid");
            return (Criteria) this;
        }

        public Criteria andShDepidNotEqualTo(Integer value) {
            addCriterion("sh_depid <>", value, "shDepid");
            return (Criteria) this;
        }

        public Criteria andShDepidGreaterThan(Integer value) {
            addCriterion("sh_depid >", value, "shDepid");
            return (Criteria) this;
        }

        public Criteria andShDepidGreaterThanOrEqualTo(Integer value) {
            addCriterion("sh_depid >=", value, "shDepid");
            return (Criteria) this;
        }

        public Criteria andShDepidLessThan(Integer value) {
            addCriterion("sh_depid <", value, "shDepid");
            return (Criteria) this;
        }

        public Criteria andShDepidLessThanOrEqualTo(Integer value) {
            addCriterion("sh_depid <=", value, "shDepid");
            return (Criteria) this;
        }

        public Criteria andShDepidIn(List<Integer> values) {
            addCriterion("sh_depid in", values, "shDepid");
            return (Criteria) this;
        }

        public Criteria andShDepidNotIn(List<Integer> values) {
            addCriterion("sh_depid not in", values, "shDepid");
            return (Criteria) this;
        }

        public Criteria andShDepidBetween(Integer value1, Integer value2) {
            addCriterion("sh_depid between", value1, value2, "shDepid");
            return (Criteria) this;
        }

        public Criteria andShDepidNotBetween(Integer value1, Integer value2) {
            addCriterion("sh_depid not between", value1, value2, "shDepid");
            return (Criteria) this;
        }

        public Criteria andShActivityIsNull() {
            addCriterion("sh_activity is null");
            return (Criteria) this;
        }

        public Criteria andShActivityIsNotNull() {
            addCriterion("sh_activity is not null");
            return (Criteria) this;
        }

        public Criteria andShActivityEqualTo(Integer value) {
            addCriterion("sh_activity =", value, "shActivity");
            return (Criteria) this;
        }

        public Criteria andShActivityNotEqualTo(Integer value) {
            addCriterion("sh_activity <>", value, "shActivity");
            return (Criteria) this;
        }

        public Criteria andShActivityGreaterThan(Integer value) {
            addCriterion("sh_activity >", value, "shActivity");
            return (Criteria) this;
        }

        public Criteria andShActivityGreaterThanOrEqualTo(Integer value) {
            addCriterion("sh_activity >=", value, "shActivity");
            return (Criteria) this;
        }

        public Criteria andShActivityLessThan(Integer value) {
            addCriterion("sh_activity <", value, "shActivity");
            return (Criteria) this;
        }

        public Criteria andShActivityLessThanOrEqualTo(Integer value) {
            addCriterion("sh_activity <=", value, "shActivity");
            return (Criteria) this;
        }

        public Criteria andShActivityIn(List<Integer> values) {
            addCriterion("sh_activity in", values, "shActivity");
            return (Criteria) this;
        }

        public Criteria andShActivityNotIn(List<Integer> values) {
            addCriterion("sh_activity not in", values, "shActivity");
            return (Criteria) this;
        }

        public Criteria andShActivityBetween(Integer value1, Integer value2) {
            addCriterion("sh_activity between", value1, value2, "shActivity");
            return (Criteria) this;
        }

        public Criteria andShActivityNotBetween(Integer value1, Integer value2) {
            addCriterion("sh_activity not between", value1, value2, "shActivity");
            return (Criteria) this;
        }

        public Criteria andShIdnumberIsNull() {
            addCriterion("sh_idnumber is null");
            return (Criteria) this;
        }

        public Criteria andShIdnumberIsNotNull() {
            addCriterion("sh_idnumber is not null");
            return (Criteria) this;
        }

        public Criteria andShIdnumberEqualTo(String value) {
            addCriterion("sh_idnumber =", value, "shIdnumber");
            return (Criteria) this;
        }

        public Criteria andShIdnumberNotEqualTo(String value) {
            addCriterion("sh_idnumber <>", value, "shIdnumber");
            return (Criteria) this;
        }

        public Criteria andShIdnumberGreaterThan(String value) {
            addCriterion("sh_idnumber >", value, "shIdnumber");
            return (Criteria) this;
        }

        public Criteria andShIdnumberGreaterThanOrEqualTo(String value) {
            addCriterion("sh_idnumber >=", value, "shIdnumber");
            return (Criteria) this;
        }

        public Criteria andShIdnumberLessThan(String value) {
            addCriterion("sh_idnumber <", value, "shIdnumber");
            return (Criteria) this;
        }

        public Criteria andShIdnumberLessThanOrEqualTo(String value) {
            addCriterion("sh_idnumber <=", value, "shIdnumber");
            return (Criteria) this;
        }

        public Criteria andShIdnumberLike(String value) {
            addCriterion("sh_idnumber like", value, "shIdnumber");
            return (Criteria) this;
        }

        public Criteria andShIdnumberNotLike(String value) {
            addCriterion("sh_idnumber not like", value, "shIdnumber");
            return (Criteria) this;
        }

        public Criteria andShIdnumberIn(List<String> values) {
            addCriterion("sh_idnumber in", values, "shIdnumber");
            return (Criteria) this;
        }

        public Criteria andShIdnumberNotIn(List<String> values) {
            addCriterion("sh_idnumber not in", values, "shIdnumber");
            return (Criteria) this;
        }

        public Criteria andShIdnumberBetween(String value1, String value2) {
            addCriterion("sh_idnumber between", value1, value2, "shIdnumber");
            return (Criteria) this;
        }

        public Criteria andShIdnumberNotBetween(String value1, String value2) {
            addCriterion("sh_idnumber not between", value1, value2, "shIdnumber");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table tab_hsf_sh
     *
     * @mbggenerated do_not_delete_during_merge
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table tab_hsf_sh
     *
     * @mbggenerated
     */
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