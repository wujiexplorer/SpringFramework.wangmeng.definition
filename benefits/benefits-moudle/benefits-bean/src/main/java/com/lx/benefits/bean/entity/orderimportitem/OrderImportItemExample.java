package com.lx.benefits.bean.entity.orderimportitem;

import java.util.ArrayList;
import java.util.List;

public class OrderImportItemExample {
    protected String orderByClause;

    protected boolean distinct;

    private Integer start;

    protected Integer page;

    protected Integer pageSize;

    protected List<Criteria> oredCriteria;

    public Integer getStart() {
        this.page = null == this.page || this.page < 1 ? 1 : this.page;
        this.pageSize = null == this.pageSize || this.pageSize < 1 ? 1 : this.pageSize;
        start = (this.page - 1) * this.pageSize;
        return start;
    }

    public OrderImportItemExample() {
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

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
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

        public Criteria andImportIdIsNull() {
            addCriterion("importId is null");
            return (Criteria) this;
        }

        public Criteria andImportIdIsNotNull() {
            addCriterion("importId is not null");
            return (Criteria) this;
        }

        public Criteria andImportIdEqualTo(Long value) {
            addCriterion("importId =", value, "importId");
            return (Criteria) this;
        }

        public Criteria andImportIdNotEqualTo(Long value) {
            addCriterion("importId <>", value, "importId");
            return (Criteria) this;
        }

        public Criteria andImportIdGreaterThan(Long value) {
            addCriterion("importId >", value, "importId");
            return (Criteria) this;
        }

        public Criteria andImportIdGreaterThanOrEqualTo(Long value) {
            addCriterion("importId >=", value, "importId");
            return (Criteria) this;
        }

        public Criteria andImportIdLessThan(Long value) {
            addCriterion("importId <", value, "importId");
            return (Criteria) this;
        }

        public Criteria andImportIdLessThanOrEqualTo(Long value) {
            addCriterion("importId <=", value, "importId");
            return (Criteria) this;
        }

        public Criteria andImportIdIn(List<Long> values) {
            addCriterion("importId in", values, "importId");
            return (Criteria) this;
        }

        public Criteria andImportIdNotIn(List<Long> values) {
            addCriterion("importId not in", values, "importId");
            return (Criteria) this;
        }

        public Criteria andImportIdBetween(Long value1, Long value2) {
            addCriterion("importId between", value1, value2, "importId");
            return (Criteria) this;
        }

        public Criteria andImportIdNotBetween(Long value1, Long value2) {
            addCriterion("importId not between", value1, value2, "importId");
            return (Criteria) this;
        }

        public Criteria andDetailIdIsNull() {
            addCriterion("detailId is null");
            return (Criteria) this;
        }

        public Criteria andDetailIdIsNotNull() {
            addCriterion("detailId is not null");
            return (Criteria) this;
        }

        public Criteria andDetailIdEqualTo(Long value) {
            addCriterion("detailId =", value, "detailId");
            return (Criteria) this;
        }

        public Criteria andDetailIdNotEqualTo(Long value) {
            addCriterion("detailId <>", value, "detailId");
            return (Criteria) this;
        }

        public Criteria andDetailIdGreaterThan(Long value) {
            addCriterion("detailId >", value, "detailId");
            return (Criteria) this;
        }

        public Criteria andDetailIdGreaterThanOrEqualTo(Long value) {
            addCriterion("detailId >=", value, "detailId");
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