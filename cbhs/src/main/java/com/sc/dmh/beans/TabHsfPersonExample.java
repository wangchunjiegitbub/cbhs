package com.sc.dmh.beans;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TabHsfPersonExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table tab_hsf_person
     *
     * @mbggenerated
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table tab_hsf_person
     *
     * @mbggenerated
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table tab_hsf_person
     *
     * @mbggenerated
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tab_hsf_person
     *
     * @mbggenerated
     */
    public TabHsfPersonExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tab_hsf_person
     *
     * @mbggenerated
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tab_hsf_person
     *
     * @mbggenerated
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tab_hsf_person
     *
     * @mbggenerated
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tab_hsf_person
     *
     * @mbggenerated
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tab_hsf_person
     *
     * @mbggenerated
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tab_hsf_person
     *
     * @mbggenerated
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tab_hsf_person
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
     * This method corresponds to the database table tab_hsf_person
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
     * This method corresponds to the database table tab_hsf_person
     *
     * @mbggenerated
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tab_hsf_person
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
     * This class corresponds to the database table tab_hsf_person
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

        public Criteria andPersonIdIsNull() {
            addCriterion("person_id is null");
            return (Criteria) this;
        }

        public Criteria andPersonIdIsNotNull() {
            addCriterion("person_id is not null");
            return (Criteria) this;
        }

        public Criteria andPersonIdEqualTo(Integer value) {
            addCriterion("person_id =", value, "personId");
            return (Criteria) this;
        }

        public Criteria andPersonIdNotEqualTo(Integer value) {
            addCriterion("person_id <>", value, "personId");
            return (Criteria) this;
        }

        public Criteria andPersonIdGreaterThan(Integer value) {
            addCriterion("person_id >", value, "personId");
            return (Criteria) this;
        }

        public Criteria andPersonIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("person_id >=", value, "personId");
            return (Criteria) this;
        }

        public Criteria andPersonIdLessThan(Integer value) {
            addCriterion("person_id <", value, "personId");
            return (Criteria) this;
        }

        public Criteria andPersonIdLessThanOrEqualTo(Integer value) {
            addCriterion("person_id <=", value, "personId");
            return (Criteria) this;
        }

        public Criteria andPersonIdIn(List<Integer> values) {
            addCriterion("person_id in", values, "personId");
            return (Criteria) this;
        }

        public Criteria andPersonIdNotIn(List<Integer> values) {
            addCriterion("person_id not in", values, "personId");
            return (Criteria) this;
        }

        public Criteria andPersonIdBetween(Integer value1, Integer value2) {
            addCriterion("person_id between", value1, value2, "personId");
            return (Criteria) this;
        }

        public Criteria andPersonIdNotBetween(Integer value1, Integer value2) {
            addCriterion("person_id not between", value1, value2, "personId");
            return (Criteria) this;
        }

        public Criteria andPersonInIsNull() {
            addCriterion("person_in is null");
            return (Criteria) this;
        }

        public Criteria andPersonInIsNotNull() {
            addCriterion("person_in is not null");
            return (Criteria) this;
        }

        public Criteria andPersonInEqualTo(Integer value) {
            addCriterion("person_in =", value, "personIn");
            return (Criteria) this;
        }

        public Criteria andPersonInNotEqualTo(Integer value) {
            addCriterion("person_in <>", value, "personIn");
            return (Criteria) this;
        }

        public Criteria andPersonInGreaterThan(Integer value) {
            addCriterion("person_in >", value, "personIn");
            return (Criteria) this;
        }

        public Criteria andPersonInGreaterThanOrEqualTo(Integer value) {
            addCriterion("person_in >=", value, "personIn");
            return (Criteria) this;
        }

        public Criteria andPersonInLessThan(Integer value) {
            addCriterion("person_in <", value, "personIn");
            return (Criteria) this;
        }

        public Criteria andPersonInLessThanOrEqualTo(Integer value) {
            addCriterion("person_in <=", value, "personIn");
            return (Criteria) this;
        }

        public Criteria andPersonInIn(List<Integer> values) {
            addCriterion("person_in in", values, "personIn");
            return (Criteria) this;
        }

        public Criteria andPersonInNotIn(List<Integer> values) {
            addCriterion("person_in not in", values, "personIn");
            return (Criteria) this;
        }

        public Criteria andPersonInBetween(Integer value1, Integer value2) {
            addCriterion("person_in between", value1, value2, "personIn");
            return (Criteria) this;
        }

        public Criteria andPersonInNotBetween(Integer value1, Integer value2) {
            addCriterion("person_in not between", value1, value2, "personIn");
            return (Criteria) this;
        }

        public Criteria andPersonOutIsNull() {
            addCriterion("person_out is null");
            return (Criteria) this;
        }

        public Criteria andPersonOutIsNotNull() {
            addCriterion("person_out is not null");
            return (Criteria) this;
        }

        public Criteria andPersonOutEqualTo(Integer value) {
            addCriterion("person_out =", value, "personOut");
            return (Criteria) this;
        }

        public Criteria andPersonOutNotEqualTo(Integer value) {
            addCriterion("person_out <>", value, "personOut");
            return (Criteria) this;
        }

        public Criteria andPersonOutGreaterThan(Integer value) {
            addCriterion("person_out >", value, "personOut");
            return (Criteria) this;
        }

        public Criteria andPersonOutGreaterThanOrEqualTo(Integer value) {
            addCriterion("person_out >=", value, "personOut");
            return (Criteria) this;
        }

        public Criteria andPersonOutLessThan(Integer value) {
            addCriterion("person_out <", value, "personOut");
            return (Criteria) this;
        }

        public Criteria andPersonOutLessThanOrEqualTo(Integer value) {
            addCriterion("person_out <=", value, "personOut");
            return (Criteria) this;
        }

        public Criteria andPersonOutIn(List<Integer> values) {
            addCriterion("person_out in", values, "personOut");
            return (Criteria) this;
        }

        public Criteria andPersonOutNotIn(List<Integer> values) {
            addCriterion("person_out not in", values, "personOut");
            return (Criteria) this;
        }

        public Criteria andPersonOutBetween(Integer value1, Integer value2) {
            addCriterion("person_out between", value1, value2, "personOut");
            return (Criteria) this;
        }

        public Criteria andPersonOutNotBetween(Integer value1, Integer value2) {
            addCriterion("person_out not between", value1, value2, "personOut");
            return (Criteria) this;
        }

        public Criteria andPersonTimeIsNull() {
            addCriterion("person_time is null");
            return (Criteria) this;
        }

        public Criteria andPersonTimeIsNotNull() {
            addCriterion("person_time is not null");
            return (Criteria) this;
        }

        public Criteria andPersonTimeEqualTo(Date value) {
            addCriterion("person_time =", value, "personTime");
            return (Criteria) this;
        }

        public Criteria andPersonTimeNotEqualTo(Date value) {
            addCriterion("person_time <>", value, "personTime");
            return (Criteria) this;
        }

        public Criteria andPersonTimeGreaterThan(Date value) {
            addCriterion("person_time >", value, "personTime");
            return (Criteria) this;
        }

        public Criteria andPersonTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("person_time >=", value, "personTime");
            return (Criteria) this;
        }

        public Criteria andPersonTimeLessThan(Date value) {
            addCriterion("person_time <", value, "personTime");
            return (Criteria) this;
        }

        public Criteria andPersonTimeLessThanOrEqualTo(Date value) {
            addCriterion("person_time <=", value, "personTime");
            return (Criteria) this;
        }

        public Criteria andPersonTimeIn(List<Date> values) {
            addCriterion("person_time in", values, "personTime");
            return (Criteria) this;
        }

        public Criteria andPersonTimeNotIn(List<Date> values) {
            addCriterion("person_time not in", values, "personTime");
            return (Criteria) this;
        }

        public Criteria andPersonTimeBetween(Date value1, Date value2) {
            addCriterion("person_time between", value1, value2, "personTime");
            return (Criteria) this;
        }

        public Criteria andPersonTimeNotBetween(Date value1, Date value2) {
            addCriterion("person_time not between", value1, value2, "personTime");
            return (Criteria) this;
        }

        public Criteria andPersonAutdateIsNull() {
            addCriterion("person_autdate is null");
            return (Criteria) this;
        }

        public Criteria andPersonAutdateIsNotNull() {
            addCriterion("person_autdate is not null");
            return (Criteria) this;
        }

        public Criteria andPersonAutdateEqualTo(Date value) {
            addCriterion("person_autdate =", value, "personAutdate");
            return (Criteria) this;
        }

        public Criteria andPersonAutdateNotEqualTo(Date value) {
            addCriterion("person_autdate <>", value, "personAutdate");
            return (Criteria) this;
        }

        public Criteria andPersonAutdateGreaterThan(Date value) {
            addCriterion("person_autdate >", value, "personAutdate");
            return (Criteria) this;
        }

        public Criteria andPersonAutdateGreaterThanOrEqualTo(Date value) {
            addCriterion("person_autdate >=", value, "personAutdate");
            return (Criteria) this;
        }

        public Criteria andPersonAutdateLessThan(Date value) {
            addCriterion("person_autdate <", value, "personAutdate");
            return (Criteria) this;
        }

        public Criteria andPersonAutdateLessThanOrEqualTo(Date value) {
            addCriterion("person_autdate <=", value, "personAutdate");
            return (Criteria) this;
        }

        public Criteria andPersonAutdateIn(List<Date> values) {
            addCriterion("person_autdate in", values, "personAutdate");
            return (Criteria) this;
        }

        public Criteria andPersonAutdateNotIn(List<Date> values) {
            addCriterion("person_autdate not in", values, "personAutdate");
            return (Criteria) this;
        }

        public Criteria andPersonAutdateBetween(Date value1, Date value2) {
            addCriterion("person_autdate between", value1, value2, "personAutdate");
            return (Criteria) this;
        }

        public Criteria andPersonAutdateNotBetween(Date value1, Date value2) {
            addCriterion("person_autdate not between", value1, value2, "personAutdate");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNull() {
            addCriterion("user_id is null");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNotNull() {
            addCriterion("user_id is not null");
            return (Criteria) this;
        }

        public Criteria andUserIdEqualTo(Integer value) {
            addCriterion("user_id =", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotEqualTo(Integer value) {
            addCriterion("user_id <>", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThan(Integer value) {
            addCriterion("user_id >", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("user_id >=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThan(Integer value) {
            addCriterion("user_id <", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThanOrEqualTo(Integer value) {
            addCriterion("user_id <=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdIn(List<Integer> values) {
            addCriterion("user_id in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotIn(List<Integer> values) {
            addCriterion("user_id not in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdBetween(Integer value1, Integer value2) {
            addCriterion("user_id between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotBetween(Integer value1, Integer value2) {
            addCriterion("user_id not between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andDepIdIsNull() {
            addCriterion("dep_id is null");
            return (Criteria) this;
        }

        public Criteria andDepIdIsNotNull() {
            addCriterion("dep_id is not null");
            return (Criteria) this;
        }

        public Criteria andDepIdEqualTo(Integer value) {
            addCriterion("dep_id =", value, "depId");
            return (Criteria) this;
        }

        public Criteria andDepIdNotEqualTo(Integer value) {
            addCriterion("dep_id <>", value, "depId");
            return (Criteria) this;
        }

        public Criteria andDepIdGreaterThan(Integer value) {
            addCriterion("dep_id >", value, "depId");
            return (Criteria) this;
        }

        public Criteria andDepIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("dep_id >=", value, "depId");
            return (Criteria) this;
        }

        public Criteria andDepIdLessThan(Integer value) {
            addCriterion("dep_id <", value, "depId");
            return (Criteria) this;
        }

        public Criteria andDepIdLessThanOrEqualTo(Integer value) {
            addCriterion("dep_id <=", value, "depId");
            return (Criteria) this;
        }

        public Criteria andDepIdIn(List<Integer> values) {
            addCriterion("dep_id in", values, "depId");
            return (Criteria) this;
        }

        public Criteria andDepIdNotIn(List<Integer> values) {
            addCriterion("dep_id not in", values, "depId");
            return (Criteria) this;
        }

        public Criteria andDepIdBetween(Integer value1, Integer value2) {
            addCriterion("dep_id between", value1, value2, "depId");
            return (Criteria) this;
        }

        public Criteria andDepIdNotBetween(Integer value1, Integer value2) {
            addCriterion("dep_id not between", value1, value2, "depId");
            return (Criteria) this;
        }

        public Criteria andPersonStateIsNull() {
            addCriterion("person_state is null");
            return (Criteria) this;
        }

        public Criteria andPersonStateIsNotNull() {
            addCriterion("person_state is not null");
            return (Criteria) this;
        }

        public Criteria andPersonStateEqualTo(Integer value) {
            addCriterion("person_state =", value, "personState");
            return (Criteria) this;
        }

        public Criteria andPersonStateNotEqualTo(Integer value) {
            addCriterion("person_state <>", value, "personState");
            return (Criteria) this;
        }

        public Criteria andPersonStateGreaterThan(Integer value) {
            addCriterion("person_state >", value, "personState");
            return (Criteria) this;
        }

        public Criteria andPersonStateGreaterThanOrEqualTo(Integer value) {
            addCriterion("person_state >=", value, "personState");
            return (Criteria) this;
        }

        public Criteria andPersonStateLessThan(Integer value) {
            addCriterion("person_state <", value, "personState");
            return (Criteria) this;
        }

        public Criteria andPersonStateLessThanOrEqualTo(Integer value) {
            addCriterion("person_state <=", value, "personState");
            return (Criteria) this;
        }

        public Criteria andPersonStateIn(List<Integer> values) {
            addCriterion("person_state in", values, "personState");
            return (Criteria) this;
        }

        public Criteria andPersonStateNotIn(List<Integer> values) {
            addCriterion("person_state not in", values, "personState");
            return (Criteria) this;
        }

        public Criteria andPersonStateBetween(Integer value1, Integer value2) {
            addCriterion("person_state between", value1, value2, "personState");
            return (Criteria) this;
        }

        public Criteria andPersonStateNotBetween(Integer value1, Integer value2) {
            addCriterion("person_state not between", value1, value2, "personState");
            return (Criteria) this;
        }

        public Criteria andPersonBak1IsNull() {
            addCriterion("person_bak1 is null");
            return (Criteria) this;
        }

        public Criteria andPersonBak1IsNotNull() {
            addCriterion("person_bak1 is not null");
            return (Criteria) this;
        }

        public Criteria andPersonBak1EqualTo(String value) {
            addCriterion("person_bak1 =", value, "personBak1");
            return (Criteria) this;
        }

        public Criteria andPersonBak1NotEqualTo(String value) {
            addCriterion("person_bak1 <>", value, "personBak1");
            return (Criteria) this;
        }

        public Criteria andPersonBak1GreaterThan(String value) {
            addCriterion("person_bak1 >", value, "personBak1");
            return (Criteria) this;
        }

        public Criteria andPersonBak1GreaterThanOrEqualTo(String value) {
            addCriterion("person_bak1 >=", value, "personBak1");
            return (Criteria) this;
        }

        public Criteria andPersonBak1LessThan(String value) {
            addCriterion("person_bak1 <", value, "personBak1");
            return (Criteria) this;
        }

        public Criteria andPersonBak1LessThanOrEqualTo(String value) {
            addCriterion("person_bak1 <=", value, "personBak1");
            return (Criteria) this;
        }

        public Criteria andPersonBak1Like(String value) {
            addCriterion("person_bak1 like", value, "personBak1");
            return (Criteria) this;
        }

        public Criteria andPersonBak1NotLike(String value) {
            addCriterion("person_bak1 not like", value, "personBak1");
            return (Criteria) this;
        }

        public Criteria andPersonBak1In(List<String> values) {
            addCriterion("person_bak1 in", values, "personBak1");
            return (Criteria) this;
        }

        public Criteria andPersonBak1NotIn(List<String> values) {
            addCriterion("person_bak1 not in", values, "personBak1");
            return (Criteria) this;
        }

        public Criteria andPersonBak1Between(String value1, String value2) {
            addCriterion("person_bak1 between", value1, value2, "personBak1");
            return (Criteria) this;
        }

        public Criteria andPersonBak1NotBetween(String value1, String value2) {
            addCriterion("person_bak1 not between", value1, value2, "personBak1");
            return (Criteria) this;
        }

        public Criteria andPersonBak2IsNull() {
            addCriterion("person_bak2 is null");
            return (Criteria) this;
        }

        public Criteria andPersonBak2IsNotNull() {
            addCriterion("person_bak2 is not null");
            return (Criteria) this;
        }

        public Criteria andPersonBak2EqualTo(String value) {
            addCriterion("person_bak2 =", value, "personBak2");
            return (Criteria) this;
        }

        public Criteria andPersonBak2NotEqualTo(String value) {
            addCriterion("person_bak2 <>", value, "personBak2");
            return (Criteria) this;
        }

        public Criteria andPersonBak2GreaterThan(String value) {
            addCriterion("person_bak2 >", value, "personBak2");
            return (Criteria) this;
        }

        public Criteria andPersonBak2GreaterThanOrEqualTo(String value) {
            addCriterion("person_bak2 >=", value, "personBak2");
            return (Criteria) this;
        }

        public Criteria andPersonBak2LessThan(String value) {
            addCriterion("person_bak2 <", value, "personBak2");
            return (Criteria) this;
        }

        public Criteria andPersonBak2LessThanOrEqualTo(String value) {
            addCriterion("person_bak2 <=", value, "personBak2");
            return (Criteria) this;
        }

        public Criteria andPersonBak2Like(String value) {
            addCriterion("person_bak2 like", value, "personBak2");
            return (Criteria) this;
        }

        public Criteria andPersonBak2NotLike(String value) {
            addCriterion("person_bak2 not like", value, "personBak2");
            return (Criteria) this;
        }

        public Criteria andPersonBak2In(List<String> values) {
            addCriterion("person_bak2 in", values, "personBak2");
            return (Criteria) this;
        }

        public Criteria andPersonBak2NotIn(List<String> values) {
            addCriterion("person_bak2 not in", values, "personBak2");
            return (Criteria) this;
        }

        public Criteria andPersonBak2Between(String value1, String value2) {
            addCriterion("person_bak2 between", value1, value2, "personBak2");
            return (Criteria) this;
        }

        public Criteria andPersonBak2NotBetween(String value1, String value2) {
            addCriterion("person_bak2 not between", value1, value2, "personBak2");
            return (Criteria) this;
        }

        public Criteria andPersonBak3IsNull() {
            addCriterion("person_bak3 is null");
            return (Criteria) this;
        }

        public Criteria andPersonBak3IsNotNull() {
            addCriterion("person_bak3 is not null");
            return (Criteria) this;
        }

        public Criteria andPersonBak3EqualTo(String value) {
            addCriterion("person_bak3 =", value, "personBak3");
            return (Criteria) this;
        }

        public Criteria andPersonBak3NotEqualTo(String value) {
            addCriterion("person_bak3 <>", value, "personBak3");
            return (Criteria) this;
        }

        public Criteria andPersonBak3GreaterThan(String value) {
            addCriterion("person_bak3 >", value, "personBak3");
            return (Criteria) this;
        }

        public Criteria andPersonBak3GreaterThanOrEqualTo(String value) {
            addCriterion("person_bak3 >=", value, "personBak3");
            return (Criteria) this;
        }

        public Criteria andPersonBak3LessThan(String value) {
            addCriterion("person_bak3 <", value, "personBak3");
            return (Criteria) this;
        }

        public Criteria andPersonBak3LessThanOrEqualTo(String value) {
            addCriterion("person_bak3 <=", value, "personBak3");
            return (Criteria) this;
        }

        public Criteria andPersonBak3Like(String value) {
            addCriterion("person_bak3 like", value, "personBak3");
            return (Criteria) this;
        }

        public Criteria andPersonBak3NotLike(String value) {
            addCriterion("person_bak3 not like", value, "personBak3");
            return (Criteria) this;
        }

        public Criteria andPersonBak3In(List<String> values) {
            addCriterion("person_bak3 in", values, "personBak3");
            return (Criteria) this;
        }

        public Criteria andPersonBak3NotIn(List<String> values) {
            addCriterion("person_bak3 not in", values, "personBak3");
            return (Criteria) this;
        }

        public Criteria andPersonBak3Between(String value1, String value2) {
            addCriterion("person_bak3 between", value1, value2, "personBak3");
            return (Criteria) this;
        }

        public Criteria andPersonBak3NotBetween(String value1, String value2) {
            addCriterion("person_bak3 not between", value1, value2, "personBak3");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table tab_hsf_person
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
     * This class corresponds to the database table tab_hsf_person
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