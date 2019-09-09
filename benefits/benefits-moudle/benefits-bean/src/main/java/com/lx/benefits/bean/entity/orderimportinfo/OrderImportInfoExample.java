package com.lx.benefits.bean.entity.orderimportinfo;

import java.util.ArrayList;
import java.util.List;

public class OrderImportInfoExample {
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

    public OrderImportInfoExample() {
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

        public Criteria andEnterprIdIsNull() {
            addCriterion("enterprId is null");
            return (Criteria) this;
        }

        public Criteria andEnterprIdIsNotNull() {
            addCriterion("enterprId is not null");
            return (Criteria) this;
        }

        public Criteria andEnterprIdEqualTo(Long value) {
            addCriterion("enterprId =", value, "enterprId");
            return (Criteria) this;
        }

        public Criteria andEnterprIdNotEqualTo(Long value) {
            addCriterion("enterprId <>", value, "enterprId");
            return (Criteria) this;
        }

        public Criteria andEnterprIdGreaterThan(Long value) {
            addCriterion("enterprId >", value, "enterprId");
            return (Criteria) this;
        }

        public Criteria andEnterprIdGreaterThanOrEqualTo(Long value) {
            addCriterion("enterprId >=", value, "enterprId");
            return (Criteria) this;
        }

        public Criteria andEnterprIdLessThan(Long value) {
            addCriterion("enterprId <", value, "enterprId");
            return (Criteria) this;
        }

        public Criteria andEnterprIdLessThanOrEqualTo(Long value) {
            addCriterion("enterprId <=", value, "enterprId");
            return (Criteria) this;
        }

        public Criteria andEnterprIdIn(List<Long> values) {
            addCriterion("enterprId in", values, "enterprId");
            return (Criteria) this;
        }

        public Criteria andEnterprIdNotIn(List<Long> values) {
            addCriterion("enterprId not in", values, "enterprId");
            return (Criteria) this;
        }

        public Criteria andEnterprIdBetween(Long value1, Long value2) {
            addCriterion("enterprId between", value1, value2, "enterprId");
            return (Criteria) this;
        }

        public Criteria andEnterprIdNotBetween(Long value1, Long value2) {
            addCriterion("enterprId not between", value1, value2, "enterprId");
            return (Criteria) this;
        }

        public Criteria andFileUrlIsNull() {
            addCriterion("fileUrl is null");
            return (Criteria) this;
        }

        public Criteria andFileUrlIsNotNull() {
            addCriterion("fileUrl is not null");
            return (Criteria) this;
        }

        public Criteria andFileUrlEqualTo(String value) {
            addCriterion("fileUrl =", value, "fileUrl");
            return (Criteria) this;
        }

        public Criteria andFileUrlNotEqualTo(String value) {
            addCriterion("fileUrl <>", value, "fileUrl");
            return (Criteria) this;
        }

        public Criteria andFileUrlGreaterThan(String value) {
            addCriterion("fileUrl >", value, "fileUrl");
            return (Criteria) this;
        }

        public Criteria andFileUrlGreaterThanOrEqualTo(String value) {
            addCriterion("fileUrl >=", value, "fileUrl");
            return (Criteria) this;
        }

        public Criteria andFileUrlLessThan(String value) {
            addCriterion("fileUrl <", value, "fileUrl");
            return (Criteria) this;
        }

        public Criteria andFileUrlLessThanOrEqualTo(String value) {
            addCriterion("fileUrl <=", value, "fileUrl");
            return (Criteria) this;
        }

        public Criteria andFileUrlLike(String value) {
            addCriterion("fileUrl like", value, "fileUrl");
            return (Criteria) this;
        }

        public Criteria andFileUrlNotLike(String value) {
            addCriterion("fileUrl not like", value, "fileUrl");
            return (Criteria) this;
        }

        public Criteria andFileUrlIn(List<String> values) {
            addCriterion("fileUrl in", values, "fileUrl");
            return (Criteria) this;
        }

        public Criteria andFileUrlNotIn(List<String> values) {
            addCriterion("fileUrl not in", values, "fileUrl");
            return (Criteria) this;
        }

        public Criteria andFileUrlBetween(String value1, String value2) {
            addCriterion("fileUrl between", value1, value2, "fileUrl");
            return (Criteria) this;
        }

        public Criteria andFileUrlNotBetween(String value1, String value2) {
            addCriterion("fileUrl not between", value1, value2, "fileUrl");
            return (Criteria) this;
        }

        public Criteria andOptUserIdIsNull() {
            addCriterion("optUserId is null");
            return (Criteria) this;
        }

        public Criteria andOptUserIdIsNotNull() {
            addCriterion("optUserId is not null");
            return (Criteria) this;
        }

        public Criteria andOptUserIdEqualTo(Long value) {
            addCriterion("optUserId =", value, "optUserId");
            return (Criteria) this;
        }

        public Criteria andOptUserIdNotEqualTo(Long value) {
            addCriterion("optUserId <>", value, "optUserId");
            return (Criteria) this;
        }

        public Criteria andOptUserIdGreaterThan(Long value) {
            addCriterion("optUserId >", value, "optUserId");
            return (Criteria) this;
        }

        public Criteria andOptUserIdGreaterThanOrEqualTo(Long value) {
            addCriterion("optUserId >=", value, "optUserId");
            return (Criteria) this;
        }

        public Criteria andOptUserIdLessThan(Long value) {
            addCriterion("optUserId <", value, "optUserId");
            return (Criteria) this;
        }

        public Criteria andOptUserIdLessThanOrEqualTo(Long value) {
            addCriterion("optUserId <=", value, "optUserId");
            return (Criteria) this;
        }

        public Criteria andOptUserIdIn(List<Long> values) {
            addCriterion("optUserId in", values, "optUserId");
            return (Criteria) this;
        }

        public Criteria andOptUserIdNotIn(List<Long> values) {
            addCriterion("optUserId not in", values, "optUserId");
            return (Criteria) this;
        }

        public Criteria andOptUserIdBetween(Long value1, Long value2) {
            addCriterion("optUserId between", value1, value2, "optUserId");
            return (Criteria) this;
        }

        public Criteria andOptUserIdNotBetween(Long value1, Long value2) {
            addCriterion("optUserId not between", value1, value2, "optUserId");
            return (Criteria) this;
        }

        public Criteria andImportCountIsNull() {
            addCriterion("importCount is null");
            return (Criteria) this;
        }

        public Criteria andImportCountIsNotNull() {
            addCriterion("importCount is not null");
            return (Criteria) this;
        }

        public Criteria andImportCountEqualTo(Long value) {
            addCriterion("importCount =", value, "importCount");
            return (Criteria) this;
        }

        public Criteria andImportCountNotEqualTo(Long value) {
            addCriterion("importCount <>", value, "importCount");
            return (Criteria) this;
        }

        public Criteria andImportCountGreaterThan(Long value) {
            addCriterion("importCount >", value, "importCount");
            return (Criteria) this;
        }

        public Criteria andImportCountGreaterThanOrEqualTo(Long value) {
            addCriterion("importCount >=", value, "importCount");
            return (Criteria) this;
        }

        public Criteria andImportCountLessThan(Long value) {
            addCriterion("importCount <", value, "importCount");
            return (Criteria) this;
        }

        public Criteria andImportCountLessThanOrEqualTo(Long value) {
            addCriterion("importCount <=", value, "importCount");
            return (Criteria) this;
        }

        public Criteria andImportCountIn(List<Long> values) {
            addCriterion("importCount in", values, "importCount");
            return (Criteria) this;
        }

        public Criteria andImportCountNotIn(List<Long> values) {
            addCriterion("importCount not in", values, "importCount");
            return (Criteria) this;
        }

        public Criteria andImportCountBetween(Long value1, Long value2) {
            addCriterion("importCount between", value1, value2, "importCount");
            return (Criteria) this;
        }

        public Criteria andImportCountNotBetween(Long value1, Long value2) {
            addCriterion("importCount not between", value1, value2, "importCount");
            return (Criteria) this;
        }

        public Criteria andIsDeletedIsNull() {
            addCriterion("isDeleted is null");
            return (Criteria) this;
        }

        public Criteria andIsDeletedIsNotNull() {
            addCriterion("isDeleted is not null");
            return (Criteria) this;
        }

        public Criteria andIsDeletedEqualTo(Integer value) {
            addCriterion("isDeleted =", value, "isDeleted");
            return (Criteria) this;
        }

        public Criteria andIsDeletedNotEqualTo(Integer value) {
            addCriterion("isDeleted <>", value, "isDeleted");
            return (Criteria) this;
        }

        public Criteria andIsDeletedGreaterThan(Integer value) {
            addCriterion("isDeleted >", value, "isDeleted");
            return (Criteria) this;
        }

        public Criteria andIsDeletedGreaterThanOrEqualTo(Integer value) {
            addCriterion("isDeleted >=", value, "isDeleted");
            return (Criteria) this;
        }

        public Criteria andIsDeletedLessThan(Integer value) {
            addCriterion("isDeleted <", value, "isDeleted");
            return (Criteria) this;
        }

        public Criteria andIsDeletedLessThanOrEqualTo(Integer value) {
            addCriterion("isDeleted <=", value, "isDeleted");
            return (Criteria) this;
        }

        public Criteria andIsDeletedIn(List<Integer> values) {
            addCriterion("isDeleted in", values, "isDeleted");
            return (Criteria) this;
        }

        public Criteria andIsDeletedNotIn(List<Integer> values) {
            addCriterion("isDeleted not in", values, "isDeleted");
            return (Criteria) this;
        }

        public Criteria andIsDeletedBetween(Integer value1, Integer value2) {
            addCriterion("isDeleted between", value1, value2, "isDeleted");
            return (Criteria) this;
        }

        public Criteria andIsDeletedNotBetween(Integer value1, Integer value2) {
            addCriterion("isDeleted not between", value1, value2, "isDeleted");
            return (Criteria) this;
        }

        public Criteria andCreatedIsNull() {
            addCriterion("created is null");
            return (Criteria) this;
        }

        public Criteria andCreatedIsNotNull() {
            addCriterion("created is not null");
            return (Criteria) this;
        }

        public Criteria andCreatedEqualTo(Integer value) {
            addCriterion("created =", value, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedNotEqualTo(Integer value) {
            addCriterion("created <>", value, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedGreaterThan(Integer value) {
            addCriterion("created >", value, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedGreaterThanOrEqualTo(Integer value) {
            addCriterion("created >=", value, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedLessThan(Integer value) {
            addCriterion("created <", value, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedLessThanOrEqualTo(Integer value) {
            addCriterion("created <=", value, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedIn(List<Integer> values) {
            addCriterion("created in", values, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedNotIn(List<Integer> values) {
            addCriterion("created not in", values, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedBetween(Integer value1, Integer value2) {
            addCriterion("created between", value1, value2, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedNotBetween(Integer value1, Integer value2) {
            addCriterion("created not between", value1, value2, "created");
            return (Criteria) this;
        }

        public Criteria andUpdatedIsNull() {
            addCriterion("updated is null");
            return (Criteria) this;
        }

        public Criteria andUpdatedIsNotNull() {
            addCriterion("updated is not null");
            return (Criteria) this;
        }

        public Criteria andUpdatedEqualTo(Integer value) {
            addCriterion("updated =", value, "updated");
            return (Criteria) this;
        }

        public Criteria andUpdatedNotEqualTo(Integer value) {
            addCriterion("updated <>", value, "updated");
            return (Criteria) this;
        }

        public Criteria andUpdatedGreaterThan(Integer value) {
            addCriterion("updated >", value, "updated");
            return (Criteria) this;
        }

        public Criteria andUpdatedGreaterThanOrEqualTo(Integer value) {
            addCriterion("updated >=", value, "updated");
            return (Criteria) this;
        }

        public Criteria andUpdatedLessThan(Integer value) {
            addCriterion("updated <", value, "updated");
            return (Criteria) this;
        }

        public Criteria andUpdatedLessThanOrEqualTo(Integer value) {
            addCriterion("updated <=", value, "updated");
            return (Criteria) this;
        }

        public Criteria andUpdatedIn(List<Integer> values) {
            addCriterion("updated in", values, "updated");
            return (Criteria) this;
        }

        public Criteria andUpdatedNotIn(List<Integer> values) {
            addCriterion("updated not in", values, "updated");
            return (Criteria) this;
        }

        public Criteria andUpdatedBetween(Integer value1, Integer value2) {
            addCriterion("updated between", value1, value2, "updated");
            return (Criteria) this;
        }

        public Criteria andUpdatedNotBetween(Integer value1, Integer value2) {
            addCriterion("updated not between", value1, value2, "updated");
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