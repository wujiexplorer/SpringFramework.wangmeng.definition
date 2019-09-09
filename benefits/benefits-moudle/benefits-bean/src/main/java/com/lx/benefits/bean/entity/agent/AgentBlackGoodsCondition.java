package com.lx.benefits.bean.entity.agent;

import java.util.ArrayList;
import java.util.List;

public class AgentBlackGoodsCondition {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private Integer limit;

    private Integer offset;

    public AgentBlackGoodsCondition() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Integer getOffset() {
        return offset;
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

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andRebateTypeIsNull() {
            addCriterion("rebate_type is null");
            return (Criteria) this;
        }

        public Criteria andRebateTypeIsNotNull() {
            addCriterion("rebate_type is not null");
            return (Criteria) this;
        }

        public Criteria andRebateTypeEqualTo(Integer value) {
            addCriterion("rebate_type =", value, "rebateType");
            return (Criteria) this;
        }

        public Criteria andRebateTypeNotEqualTo(Integer value) {
            addCriterion("rebate_type <>", value, "rebateType");
            return (Criteria) this;
        }

        public Criteria andRebateTypeGreaterThan(Integer value) {
            addCriterion("rebate_type >", value, "rebateType");
            return (Criteria) this;
        }

        public Criteria andRebateTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("rebate_type >=", value, "rebateType");
            return (Criteria) this;
        }

        public Criteria andRebateTypeLessThan(Integer value) {
            addCriterion("rebate_type <", value, "rebateType");
            return (Criteria) this;
        }

        public Criteria andRebateTypeLessThanOrEqualTo(Integer value) {
            addCriterion("rebate_type <=", value, "rebateType");
            return (Criteria) this;
        }

        public Criteria andRebateTypeIn(List<Integer> values) {
            addCriterion("rebate_type in", values, "rebateType");
            return (Criteria) this;
        }

        public Criteria andRebateTypeNotIn(List<Integer> values) {
            addCriterion("rebate_type not in", values, "rebateType");
            return (Criteria) this;
        }

        public Criteria andRebateTypeBetween(Integer value1, Integer value2) {
            addCriterion("rebate_type between", value1, value2, "rebateType");
            return (Criteria) this;
        }

        public Criteria andRebateTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("rebate_type not between", value1, value2, "rebateType");
            return (Criteria) this;
        }

        public Criteria andCategoryIdsIsNull() {
            addCriterion("category_ids is null");
            return (Criteria) this;
        }

        public Criteria andCategoryIdsIsNotNull() {
            addCriterion("category_ids is not null");
            return (Criteria) this;
        }

        public Criteria andCategoryIdsEqualTo(String value) {
            addCriterion("category_ids =", value, "categoryIds");
            return (Criteria) this;
        }

        public Criteria andCategoryIdsNotEqualTo(String value) {
            addCriterion("category_ids <>", value, "categoryIds");
            return (Criteria) this;
        }

        public Criteria andCategoryIdsGreaterThan(String value) {
            addCriterion("category_ids >", value, "categoryIds");
            return (Criteria) this;
        }

        public Criteria andCategoryIdsGreaterThanOrEqualTo(String value) {
            addCriterion("category_ids >=", value, "categoryIds");
            return (Criteria) this;
        }

        public Criteria andCategoryIdsLessThan(String value) {
            addCriterion("category_ids <", value, "categoryIds");
            return (Criteria) this;
        }

        public Criteria andCategoryIdsLessThanOrEqualTo(String value) {
            addCriterion("category_ids <=", value, "categoryIds");
            return (Criteria) this;
        }

        public Criteria andCategoryIdsLike(String value) {
            addCriterion("category_ids like", value, "categoryIds");
            return (Criteria) this;
        }

        public Criteria andCategoryIdsNotLike(String value) {
            addCriterion("category_ids not like", value, "categoryIds");
            return (Criteria) this;
        }

        public Criteria andCategoryIdsIn(List<String> values) {
            addCriterion("category_ids in", values, "categoryIds");
            return (Criteria) this;
        }

        public Criteria andCategoryIdsNotIn(List<String> values) {
            addCriterion("category_ids not in", values, "categoryIds");
            return (Criteria) this;
        }

        public Criteria andCategoryIdsBetween(String value1, String value2) {
            addCriterion("category_ids between", value1, value2, "categoryIds");
            return (Criteria) this;
        }

        public Criteria andCategoryIdsNotBetween(String value1, String value2) {
            addCriterion("category_ids not between", value1, value2, "categoryIds");
            return (Criteria) this;
        }

        public Criteria andBrandIdsIsNull() {
            addCriterion("brand_ids is null");
            return (Criteria) this;
        }

        public Criteria andBrandIdsIsNotNull() {
            addCriterion("brand_ids is not null");
            return (Criteria) this;
        }

        public Criteria andBrandIdsEqualTo(String value) {
            addCriterion("brand_ids =", value, "brandIds");
            return (Criteria) this;
        }

        public Criteria andBrandIdsNotEqualTo(String value) {
            addCriterion("brand_ids <>", value, "brandIds");
            return (Criteria) this;
        }

        public Criteria andBrandIdsGreaterThan(String value) {
            addCriterion("brand_ids >", value, "brandIds");
            return (Criteria) this;
        }

        public Criteria andBrandIdsGreaterThanOrEqualTo(String value) {
            addCriterion("brand_ids >=", value, "brandIds");
            return (Criteria) this;
        }

        public Criteria andBrandIdsLessThan(String value) {
            addCriterion("brand_ids <", value, "brandIds");
            return (Criteria) this;
        }

        public Criteria andBrandIdsLessThanOrEqualTo(String value) {
            addCriterion("brand_ids <=", value, "brandIds");
            return (Criteria) this;
        }

        public Criteria andBrandIdsLike(String value) {
            addCriterion("brand_ids like", value, "brandIds");
            return (Criteria) this;
        }

        public Criteria andBrandIdsNotLike(String value) {
            addCriterion("brand_ids not like", value, "brandIds");
            return (Criteria) this;
        }

        public Criteria andBrandIdsIn(List<String> values) {
            addCriterion("brand_ids in", values, "brandIds");
            return (Criteria) this;
        }

        public Criteria andBrandIdsNotIn(List<String> values) {
            addCriterion("brand_ids not in", values, "brandIds");
            return (Criteria) this;
        }

        public Criteria andBrandIdsBetween(String value1, String value2) {
            addCriterion("brand_ids between", value1, value2, "brandIds");
            return (Criteria) this;
        }

        public Criteria andBrandIdsNotBetween(String value1, String value2) {
            addCriterion("brand_ids not between", value1, value2, "brandIds");
            return (Criteria) this;
        }

        public Criteria andGoodIdsIsNull() {
            addCriterion("good_Ids is null");
            return (Criteria) this;
        }

        public Criteria andGoodIdsIsNotNull() {
            addCriterion("good_Ids is not null");
            return (Criteria) this;
        }

        public Criteria andGoodIdsEqualTo(String value) {
            addCriterion("good_Ids =", value, "goodIds");
            return (Criteria) this;
        }

        public Criteria andGoodIdsNotEqualTo(String value) {
            addCriterion("good_Ids <>", value, "goodIds");
            return (Criteria) this;
        }

        public Criteria andGoodIdsGreaterThan(String value) {
            addCriterion("good_Ids >", value, "goodIds");
            return (Criteria) this;
        }

        public Criteria andGoodIdsGreaterThanOrEqualTo(String value) {
            addCriterion("good_Ids >=", value, "goodIds");
            return (Criteria) this;
        }

        public Criteria andGoodIdsLessThan(String value) {
            addCriterion("good_Ids <", value, "goodIds");
            return (Criteria) this;
        }

        public Criteria andGoodIdsLessThanOrEqualTo(String value) {
            addCriterion("good_Ids <=", value, "goodIds");
            return (Criteria) this;
        }

        public Criteria andGoodIdsLike(String value) {
            addCriterion("good_Ids like", value, "goodIds");
            return (Criteria) this;
        }

        public Criteria andGoodIdsNotLike(String value) {
            addCriterion("good_Ids not like", value, "goodIds");
            return (Criteria) this;
        }

        public Criteria andGoodIdsIn(List<String> values) {
            addCriterion("good_Ids in", values, "goodIds");
            return (Criteria) this;
        }

        public Criteria andGoodIdsNotIn(List<String> values) {
            addCriterion("good_Ids not in", values, "goodIds");
            return (Criteria) this;
        }

        public Criteria andGoodIdsBetween(String value1, String value2) {
            addCriterion("good_Ids between", value1, value2, "goodIds");
            return (Criteria) this;
        }

        public Criteria andGoodIdsNotBetween(String value1, String value2) {
            addCriterion("good_Ids not between", value1, value2, "goodIds");
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