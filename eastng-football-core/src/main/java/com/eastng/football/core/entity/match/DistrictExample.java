package com.eastng.football.core.entity.match;

import java.util.ArrayList;
import java.util.List;

public class DistrictExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_district
     *
     * @mbggenerated Mon Jan 25 22:43:53 CST 2016
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_district
     *
     * @mbggenerated Mon Jan 25 22:43:53 CST 2016
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_district
     *
     * @mbggenerated Mon Jan 25 22:43:53 CST 2016
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_district
     *
     * @mbggenerated Mon Jan 25 22:43:53 CST 2016
     */
    public DistrictExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_district
     *
     * @mbggenerated Mon Jan 25 22:43:53 CST 2016
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_district
     *
     * @mbggenerated Mon Jan 25 22:43:53 CST 2016
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_district
     *
     * @mbggenerated Mon Jan 25 22:43:53 CST 2016
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_district
     *
     * @mbggenerated Mon Jan 25 22:43:53 CST 2016
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_district
     *
     * @mbggenerated Mon Jan 25 22:43:53 CST 2016
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_district
     *
     * @mbggenerated Mon Jan 25 22:43:53 CST 2016
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_district
     *
     * @mbggenerated Mon Jan 25 22:43:53 CST 2016
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_district
     *
     * @mbggenerated Mon Jan 25 22:43:53 CST 2016
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
     * This method corresponds to the database table t_district
     *
     * @mbggenerated Mon Jan 25 22:43:53 CST 2016
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_district
     *
     * @mbggenerated Mon Jan 25 22:43:53 CST 2016
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table t_district
     *
     * @mbggenerated Mon Jan 25 22:43:53 CST 2016
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

        public Criteria andIdIsNull() {
            addCriterion("ID is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("ID is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("ID =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("ID <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("ID >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("ID >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("ID <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("ID <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("ID in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("ID not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("ID between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("ID not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andDistrictNoIsNull() {
            addCriterion("DISTRICT_NO is null");
            return (Criteria) this;
        }

        public Criteria andDistrictNoIsNotNull() {
            addCriterion("DISTRICT_NO is not null");
            return (Criteria) this;
        }

        public Criteria andDistrictNoEqualTo(String value) {
            addCriterion("DISTRICT_NO =", value, "districtNo");
            return (Criteria) this;
        }

        public Criteria andDistrictNoNotEqualTo(String value) {
            addCriterion("DISTRICT_NO <>", value, "districtNo");
            return (Criteria) this;
        }

        public Criteria andDistrictNoGreaterThan(String value) {
            addCriterion("DISTRICT_NO >", value, "districtNo");
            return (Criteria) this;
        }

        public Criteria andDistrictNoGreaterThanOrEqualTo(String value) {
            addCriterion("DISTRICT_NO >=", value, "districtNo");
            return (Criteria) this;
        }

        public Criteria andDistrictNoLessThan(String value) {
            addCriterion("DISTRICT_NO <", value, "districtNo");
            return (Criteria) this;
        }

        public Criteria andDistrictNoLessThanOrEqualTo(String value) {
            addCriterion("DISTRICT_NO <=", value, "districtNo");
            return (Criteria) this;
        }

        public Criteria andDistrictNoLike(String value) {
            addCriterion("DISTRICT_NO like", value, "districtNo");
            return (Criteria) this;
        }

        public Criteria andDistrictNoNotLike(String value) {
            addCriterion("DISTRICT_NO not like", value, "districtNo");
            return (Criteria) this;
        }

        public Criteria andDistrictNoIn(List<String> values) {
            addCriterion("DISTRICT_NO in", values, "districtNo");
            return (Criteria) this;
        }

        public Criteria andDistrictNoNotIn(List<String> values) {
            addCriterion("DISTRICT_NO not in", values, "districtNo");
            return (Criteria) this;
        }

        public Criteria andDistrictNoBetween(String value1, String value2) {
            addCriterion("DISTRICT_NO between", value1, value2, "districtNo");
            return (Criteria) this;
        }

        public Criteria andDistrictNoNotBetween(String value1, String value2) {
            addCriterion("DISTRICT_NO not between", value1, value2, "districtNo");
            return (Criteria) this;
        }

        public Criteria andDistrictNameIsNull() {
            addCriterion("DISTRICT_NAME is null");
            return (Criteria) this;
        }

        public Criteria andDistrictNameIsNotNull() {
            addCriterion("DISTRICT_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andDistrictNameEqualTo(String value) {
            addCriterion("DISTRICT_NAME =", value, "districtName");
            return (Criteria) this;
        }

        public Criteria andDistrictNameNotEqualTo(String value) {
            addCriterion("DISTRICT_NAME <>", value, "districtName");
            return (Criteria) this;
        }

        public Criteria andDistrictNameGreaterThan(String value) {
            addCriterion("DISTRICT_NAME >", value, "districtName");
            return (Criteria) this;
        }

        public Criteria andDistrictNameGreaterThanOrEqualTo(String value) {
            addCriterion("DISTRICT_NAME >=", value, "districtName");
            return (Criteria) this;
        }

        public Criteria andDistrictNameLessThan(String value) {
            addCriterion("DISTRICT_NAME <", value, "districtName");
            return (Criteria) this;
        }

        public Criteria andDistrictNameLessThanOrEqualTo(String value) {
            addCriterion("DISTRICT_NAME <=", value, "districtName");
            return (Criteria) this;
        }

        public Criteria andDistrictNameLike(String value) {
            addCriterion("DISTRICT_NAME like", value, "districtName");
            return (Criteria) this;
        }

        public Criteria andDistrictNameNotLike(String value) {
            addCriterion("DISTRICT_NAME not like", value, "districtName");
            return (Criteria) this;
        }

        public Criteria andDistrictNameIn(List<String> values) {
            addCriterion("DISTRICT_NAME in", values, "districtName");
            return (Criteria) this;
        }

        public Criteria andDistrictNameNotIn(List<String> values) {
            addCriterion("DISTRICT_NAME not in", values, "districtName");
            return (Criteria) this;
        }

        public Criteria andDistrictNameBetween(String value1, String value2) {
            addCriterion("DISTRICT_NAME between", value1, value2, "districtName");
            return (Criteria) this;
        }

        public Criteria andDistrictNameNotBetween(String value1, String value2) {
            addCriterion("DISTRICT_NAME not between", value1, value2, "districtName");
            return (Criteria) this;
        }

        public Criteria andDistrictLevelIsNull() {
            addCriterion("DISTRICT_LEVEL is null");
            return (Criteria) this;
        }

        public Criteria andDistrictLevelIsNotNull() {
            addCriterion("DISTRICT_LEVEL is not null");
            return (Criteria) this;
        }

        public Criteria andDistrictLevelEqualTo(String value) {
            addCriterion("DISTRICT_LEVEL =", value, "districtLevel");
            return (Criteria) this;
        }

        public Criteria andDistrictLevelNotEqualTo(String value) {
            addCriterion("DISTRICT_LEVEL <>", value, "districtLevel");
            return (Criteria) this;
        }

        public Criteria andDistrictLevelGreaterThan(String value) {
            addCriterion("DISTRICT_LEVEL >", value, "districtLevel");
            return (Criteria) this;
        }

        public Criteria andDistrictLevelGreaterThanOrEqualTo(String value) {
            addCriterion("DISTRICT_LEVEL >=", value, "districtLevel");
            return (Criteria) this;
        }

        public Criteria andDistrictLevelLessThan(String value) {
            addCriterion("DISTRICT_LEVEL <", value, "districtLevel");
            return (Criteria) this;
        }

        public Criteria andDistrictLevelLessThanOrEqualTo(String value) {
            addCriterion("DISTRICT_LEVEL <=", value, "districtLevel");
            return (Criteria) this;
        }

        public Criteria andDistrictLevelLike(String value) {
            addCriterion("DISTRICT_LEVEL like", value, "districtLevel");
            return (Criteria) this;
        }

        public Criteria andDistrictLevelNotLike(String value) {
            addCriterion("DISTRICT_LEVEL not like", value, "districtLevel");
            return (Criteria) this;
        }

        public Criteria andDistrictLevelIn(List<String> values) {
            addCriterion("DISTRICT_LEVEL in", values, "districtLevel");
            return (Criteria) this;
        }

        public Criteria andDistrictLevelNotIn(List<String> values) {
            addCriterion("DISTRICT_LEVEL not in", values, "districtLevel");
            return (Criteria) this;
        }

        public Criteria andDistrictLevelBetween(String value1, String value2) {
            addCriterion("DISTRICT_LEVEL between", value1, value2, "districtLevel");
            return (Criteria) this;
        }

        public Criteria andDistrictLevelNotBetween(String value1, String value2) {
            addCriterion("DISTRICT_LEVEL not between", value1, value2, "districtLevel");
            return (Criteria) this;
        }

        public Criteria andParentDistrictNoIsNull() {
            addCriterion("PARENT_DISTRICT_NO is null");
            return (Criteria) this;
        }

        public Criteria andParentDistrictNoIsNotNull() {
            addCriterion("PARENT_DISTRICT_NO is not null");
            return (Criteria) this;
        }

        public Criteria andParentDistrictNoEqualTo(String value) {
            addCriterion("PARENT_DISTRICT_NO =", value, "parentDistrictNo");
            return (Criteria) this;
        }

        public Criteria andParentDistrictNoNotEqualTo(String value) {
            addCriterion("PARENT_DISTRICT_NO <>", value, "parentDistrictNo");
            return (Criteria) this;
        }

        public Criteria andParentDistrictNoGreaterThan(String value) {
            addCriterion("PARENT_DISTRICT_NO >", value, "parentDistrictNo");
            return (Criteria) this;
        }

        public Criteria andParentDistrictNoGreaterThanOrEqualTo(String value) {
            addCriterion("PARENT_DISTRICT_NO >=", value, "parentDistrictNo");
            return (Criteria) this;
        }

        public Criteria andParentDistrictNoLessThan(String value) {
            addCriterion("PARENT_DISTRICT_NO <", value, "parentDistrictNo");
            return (Criteria) this;
        }

        public Criteria andParentDistrictNoLessThanOrEqualTo(String value) {
            addCriterion("PARENT_DISTRICT_NO <=", value, "parentDistrictNo");
            return (Criteria) this;
        }

        public Criteria andParentDistrictNoLike(String value) {
            addCriterion("PARENT_DISTRICT_NO like", value, "parentDistrictNo");
            return (Criteria) this;
        }

        public Criteria andParentDistrictNoNotLike(String value) {
            addCriterion("PARENT_DISTRICT_NO not like", value, "parentDistrictNo");
            return (Criteria) this;
        }

        public Criteria andParentDistrictNoIn(List<String> values) {
            addCriterion("PARENT_DISTRICT_NO in", values, "parentDistrictNo");
            return (Criteria) this;
        }

        public Criteria andParentDistrictNoNotIn(List<String> values) {
            addCriterion("PARENT_DISTRICT_NO not in", values, "parentDistrictNo");
            return (Criteria) this;
        }

        public Criteria andParentDistrictNoBetween(String value1, String value2) {
            addCriterion("PARENT_DISTRICT_NO between", value1, value2, "parentDistrictNo");
            return (Criteria) this;
        }

        public Criteria andParentDistrictNoNotBetween(String value1, String value2) {
            addCriterion("PARENT_DISTRICT_NO not between", value1, value2, "parentDistrictNo");
            return (Criteria) this;
        }

        public Criteria andFirstLetterIsNull() {
            addCriterion("FIRST_LETTER is null");
            return (Criteria) this;
        }

        public Criteria andFirstLetterIsNotNull() {
            addCriterion("FIRST_LETTER is not null");
            return (Criteria) this;
        }

        public Criteria andFirstLetterEqualTo(String value) {
            addCriterion("FIRST_LETTER =", value, "firstLetter");
            return (Criteria) this;
        }

        public Criteria andFirstLetterNotEqualTo(String value) {
            addCriterion("FIRST_LETTER <>", value, "firstLetter");
            return (Criteria) this;
        }

        public Criteria andFirstLetterGreaterThan(String value) {
            addCriterion("FIRST_LETTER >", value, "firstLetter");
            return (Criteria) this;
        }

        public Criteria andFirstLetterGreaterThanOrEqualTo(String value) {
            addCriterion("FIRST_LETTER >=", value, "firstLetter");
            return (Criteria) this;
        }

        public Criteria andFirstLetterLessThan(String value) {
            addCriterion("FIRST_LETTER <", value, "firstLetter");
            return (Criteria) this;
        }

        public Criteria andFirstLetterLessThanOrEqualTo(String value) {
            addCriterion("FIRST_LETTER <=", value, "firstLetter");
            return (Criteria) this;
        }

        public Criteria andFirstLetterLike(String value) {
            addCriterion("FIRST_LETTER like", value, "firstLetter");
            return (Criteria) this;
        }

        public Criteria andFirstLetterNotLike(String value) {
            addCriterion("FIRST_LETTER not like", value, "firstLetter");
            return (Criteria) this;
        }

        public Criteria andFirstLetterIn(List<String> values) {
            addCriterion("FIRST_LETTER in", values, "firstLetter");
            return (Criteria) this;
        }

        public Criteria andFirstLetterNotIn(List<String> values) {
            addCriterion("FIRST_LETTER not in", values, "firstLetter");
            return (Criteria) this;
        }

        public Criteria andFirstLetterBetween(String value1, String value2) {
            addCriterion("FIRST_LETTER between", value1, value2, "firstLetter");
            return (Criteria) this;
        }

        public Criteria andFirstLetterNotBetween(String value1, String value2) {
            addCriterion("FIRST_LETTER not between", value1, value2, "firstLetter");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table t_district
     *
     * @mbggenerated do_not_delete_during_merge Mon Jan 25 22:43:53 CST 2016
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table t_district
     *
     * @mbggenerated Mon Jan 25 22:43:53 CST 2016
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