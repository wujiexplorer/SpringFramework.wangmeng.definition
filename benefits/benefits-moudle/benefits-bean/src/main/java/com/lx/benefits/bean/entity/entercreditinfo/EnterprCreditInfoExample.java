package com.lx.benefits.bean.entity.entercreditinfo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class EnterprCreditInfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer page;

    protected Integer pageSize;

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

    public EnterprCreditInfoExample() {
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

        public Criteria andCreditIdIsNull() {
            addCriterion("creditId is null");
            return (Criteria) this;
        }

        public Criteria andCreditIdIsNotNull() {
            addCriterion("creditId is not null");
            return (Criteria) this;
        }

        public Criteria andCreditIdEqualTo(Long value) {
            addCriterion("creditId =", value, "creditId");
            return (Criteria) this;
        }

        public Criteria andCreditIdNotEqualTo(Long value) {
            addCriterion("creditId <>", value, "creditId");
            return (Criteria) this;
        }

        public Criteria andCreditIdGreaterThan(Long value) {
            addCriterion("creditId >", value, "creditId");
            return (Criteria) this;
        }

        public Criteria andCreditIdGreaterThanOrEqualTo(Long value) {
            addCriterion("creditId >=", value, "creditId");
            return (Criteria) this;
        }

        public Criteria andCreditIdLessThan(Long value) {
            addCriterion("creditId <", value, "creditId");
            return (Criteria) this;
        }

        public Criteria andCreditIdLessThanOrEqualTo(Long value) {
            addCriterion("creditId <=", value, "creditId");
            return (Criteria) this;
        }

        public Criteria andCreditIdIn(List<Long> values) {
            addCriterion("creditId in", values, "creditId");
            return (Criteria) this;
        }

        public Criteria andCreditIdNotIn(List<Long> values) {
            addCriterion("creditId not in", values, "creditId");
            return (Criteria) this;
        }

        public Criteria andCreditIdBetween(Long value1, Long value2) {
            addCriterion("creditId between", value1, value2, "creditId");
            return (Criteria) this;
        }

        public Criteria andCreditIdNotBetween(Long value1, Long value2) {
            addCriterion("creditId not between", value1, value2, "creditId");
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

        public Criteria andCreditTypeIsNull() {
            addCriterion("creditType is null");
            return (Criteria) this;
        }

        public Criteria andCreditTypeIsNotNull() {
            addCriterion("creditType is not null");
            return (Criteria) this;
        }

        public Criteria andCreditTypeEqualTo(byte value) {
            addCriterion("creditType =", value, "creditType");
            return (Criteria) this;
        }

        public Criteria andCreditTypeNotEqualTo(Boolean value) {
            addCriterion("creditType <>", value, "creditType");
            return (Criteria) this;
        }

        public Criteria andCreditTypeGreaterThan(Boolean value) {
            addCriterion("creditType >", value, "creditType");
            return (Criteria) this;
        }

        public Criteria andCreditTypeGreaterThanOrEqualTo(Boolean value) {
            addCriterion("creditType >=", value, "creditType");
            return (Criteria) this;
        }

        public Criteria andCreditTypeLessThan(Boolean value) {
            addCriterion("creditType <", value, "creditType");
            return (Criteria) this;
        }

        public Criteria andCreditTypeLessThanOrEqualTo(Boolean value) {
            addCriterion("creditType <=", value, "creditType");
            return (Criteria) this;
        }

        public Criteria andCreditTypeIn(List<Boolean> values) {
            addCriterion("creditType in", values, "creditType");
            return (Criteria) this;
        }

        public Criteria andCreditTypeNotIn(List<Boolean> values) {
            addCriterion("creditType not in", values, "creditType");
            return (Criteria) this;
        }

        public Criteria andCreditTypeBetween(Boolean value1, Boolean value2) {
            addCriterion("creditType between", value1, value2, "creditType");
            return (Criteria) this;
        }

        public Criteria andCreditTypeNotBetween(Boolean value1, Boolean value2) {
            addCriterion("creditType not between", value1, value2, "creditType");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("status is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("status is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(Boolean value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(Boolean value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(Boolean value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Boolean value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(Boolean value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(Boolean value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<Boolean> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<Boolean> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(Boolean value1, Boolean value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(Boolean value1, Boolean value2) {
            addCriterion("status not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andValidCreditIsNull() {
            addCriterion("validCredit is null");
            return (Criteria) this;
        }

        public Criteria andValidCreditIsNotNull() {
            addCriterion("validCredit is not null");
            return (Criteria) this;
        }

        public Criteria andValidCreditEqualTo(BigDecimal value) {
            addCriterion("validCredit =", value, "validCredit");
            return (Criteria) this;
        }

        public Criteria andValidCreditNotEqualTo(BigDecimal value) {
            addCriterion("validCredit <>", value, "validCredit");
            return (Criteria) this;
        }

        public Criteria andValidCreditGreaterThan(BigDecimal value) {
            addCriterion("validCredit >", value, "validCredit");
            return (Criteria) this;
        }

        public Criteria andValidCreditGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("validCredit >=", value, "validCredit");
            return (Criteria) this;
        }

        public Criteria andValidCreditLessThan(BigDecimal value) {
            addCriterion("validCredit <", value, "validCredit");
            return (Criteria) this;
        }

        public Criteria andValidCreditLessThanOrEqualTo(BigDecimal value) {
            addCriterion("validCredit <=", value, "validCredit");
            return (Criteria) this;
        }

        public Criteria andValidCreditIn(List<BigDecimal> values) {
            addCriterion("validCredit in", values, "validCredit");
            return (Criteria) this;
        }

        public Criteria andValidCreditNotIn(List<BigDecimal> values) {
            addCriterion("validCredit not in", values, "validCredit");
            return (Criteria) this;
        }

        public Criteria andValidCreditBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("validCredit between", value1, value2, "validCredit");
            return (Criteria) this;
        }

        public Criteria andValidCreditNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("validCredit not between", value1, value2, "validCredit");
            return (Criteria) this;
        }

        public Criteria andInvalidCreditIsNull() {
            addCriterion("invalidCredit is null");
            return (Criteria) this;
        }

        public Criteria andInvalidCreditIsNotNull() {
            addCriterion("invalidCredit is not null");
            return (Criteria) this;
        }

        public Criteria andInvalidCreditEqualTo(BigDecimal value) {
            addCriterion("invalidCredit =", value, "invalidCredit");
            return (Criteria) this;
        }

        public Criteria andInvalidCreditNotEqualTo(BigDecimal value) {
            addCriterion("invalidCredit <>", value, "invalidCredit");
            return (Criteria) this;
        }

        public Criteria andInvalidCreditGreaterThan(BigDecimal value) {
            addCriterion("invalidCredit >", value, "invalidCredit");
            return (Criteria) this;
        }

        public Criteria andInvalidCreditGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("invalidCredit >=", value, "invalidCredit");
            return (Criteria) this;
        }

        public Criteria andInvalidCreditLessThan(BigDecimal value) {
            addCriterion("invalidCredit <", value, "invalidCredit");
            return (Criteria) this;
        }

        public Criteria andInvalidCreditLessThanOrEqualTo(BigDecimal value) {
            addCriterion("invalidCredit <=", value, "invalidCredit");
            return (Criteria) this;
        }

        public Criteria andInvalidCreditIn(List<BigDecimal> values) {
            addCriterion("invalidCredit in", values, "invalidCredit");
            return (Criteria) this;
        }

        public Criteria andInvalidCreditNotIn(List<BigDecimal> values) {
            addCriterion("invalidCredit not in", values, "invalidCredit");
            return (Criteria) this;
        }

        public Criteria andInvalidCreditBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("invalidCredit between", value1, value2, "invalidCredit");
            return (Criteria) this;
        }

        public Criteria andInvalidCreditNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("invalidCredit not between", value1, value2, "invalidCredit");
            return (Criteria) this;
        }

        public Criteria andLockCreditIsNull() {
            addCriterion("lockCredit is null");
            return (Criteria) this;
        }

        public Criteria andLockCreditIsNotNull() {
            addCriterion("lockCredit is not null");
            return (Criteria) this;
        }

        public Criteria andLockCreditEqualTo(BigDecimal value) {
            addCriterion("lockCredit =", value, "lockCredit");
            return (Criteria) this;
        }

        public Criteria andLockCreditNotEqualTo(BigDecimal value) {
            addCriterion("lockCredit <>", value, "lockCredit");
            return (Criteria) this;
        }

        public Criteria andLockCreditGreaterThan(BigDecimal value) {
            addCriterion("lockCredit >", value, "lockCredit");
            return (Criteria) this;
        }

        public Criteria andLockCreditGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("lockCredit >=", value, "lockCredit");
            return (Criteria) this;
        }

        public Criteria andLockCreditLessThan(BigDecimal value) {
            addCriterion("lockCredit <", value, "lockCredit");
            return (Criteria) this;
        }

        public Criteria andLockCreditLessThanOrEqualTo(BigDecimal value) {
            addCriterion("lockCredit <=", value, "lockCredit");
            return (Criteria) this;
        }

        public Criteria andLockCreditIn(List<BigDecimal> values) {
            addCriterion("lockCredit in", values, "lockCredit");
            return (Criteria) this;
        }

        public Criteria andLockCreditNotIn(List<BigDecimal> values) {
            addCriterion("lockCredit not in", values, "lockCredit");
            return (Criteria) this;
        }

        public Criteria andLockCreditBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("lockCredit between", value1, value2, "lockCredit");
            return (Criteria) this;
        }

        public Criteria andLockCreditNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("lockCredit not between", value1, value2, "lockCredit");
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

        public Criteria andIsDeletedEqualTo(Boolean value) {
            addCriterion("isDeleted =", value, "isDeleted");
            return (Criteria) this;
        }

        public Criteria andIsDeletedNotEqualTo(Boolean value) {
            addCriterion("isDeleted <>", value, "isDeleted");
            return (Criteria) this;
        }

        public Criteria andIsDeletedGreaterThan(Boolean value) {
            addCriterion("isDeleted >", value, "isDeleted");
            return (Criteria) this;
        }

        public Criteria andIsDeletedGreaterThanOrEqualTo(Boolean value) {
            addCriterion("isDeleted >=", value, "isDeleted");
            return (Criteria) this;
        }

        public Criteria andIsDeletedLessThan(Boolean value) {
            addCriterion("isDeleted <", value, "isDeleted");
            return (Criteria) this;
        }

        public Criteria andIsDeletedLessThanOrEqualTo(Boolean value) {
            addCriterion("isDeleted <=", value, "isDeleted");
            return (Criteria) this;
        }

        public Criteria andIsDeletedIn(List<Boolean> values) {
            addCriterion("isDeleted in", values, "isDeleted");
            return (Criteria) this;
        }

        public Criteria andIsDeletedNotIn(List<Boolean> values) {
            addCriterion("isDeleted not in", values, "isDeleted");
            return (Criteria) this;
        }

        public Criteria andIsDeletedBetween(Boolean value1, Boolean value2) {
            addCriterion("isDeleted between", value1, value2, "isDeleted");
            return (Criteria) this;
        }

        public Criteria andIsDeletedNotBetween(Boolean value1, Boolean value2) {
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