package com.lx.benefits.bean.entity.employeeleaveinfo;

import java.util.ArrayList;
import java.util.List;

public class EmplyeeLeaveInfoExample {
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

    public EmplyeeLeaveInfoExample() {
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

        public Criteria andLeaveIdIsNull() {
            addCriterion("leaveId is null");
            return (Criteria) this;
        }

        public Criteria andLeaveIdIsNotNull() {
            addCriterion("leaveId is not null");
            return (Criteria) this;
        }

        public Criteria andLeaveIdEqualTo(Long value) {
            addCriterion("leaveId =", value, "leaveId");
            return (Criteria) this;
        }

        public Criteria andLeaveIdNotEqualTo(Long value) {
            addCriterion("leaveId <>", value, "leaveId");
            return (Criteria) this;
        }

        public Criteria andLeaveIdGreaterThan(Long value) {
            addCriterion("leaveId >", value, "leaveId");
            return (Criteria) this;
        }

        public Criteria andLeaveIdGreaterThanOrEqualTo(Long value) {
            addCriterion("leaveId >=", value, "leaveId");
            return (Criteria) this;
        }

        public Criteria andLeaveIdLessThan(Long value) {
            addCriterion("leaveId <", value, "leaveId");
            return (Criteria) this;
        }

        public Criteria andLeaveIdLessThanOrEqualTo(Long value) {
            addCriterion("leaveId <=", value, "leaveId");
            return (Criteria) this;
        }

        public Criteria andLeaveIdIn(List<Long> values) {
            addCriterion("leaveId in", values, "leaveId");
            return (Criteria) this;
        }

        public Criteria andLeaveIdNotIn(List<Long> values) {
            addCriterion("leaveId not in", values, "leaveId");
            return (Criteria) this;
        }

        public Criteria andLeaveIdBetween(Long value1, Long value2) {
            addCriterion("leaveId between", value1, value2, "leaveId");
            return (Criteria) this;
        }

        public Criteria andLeaveIdNotBetween(Long value1, Long value2) {
            addCriterion("leaveId not between", value1, value2, "leaveId");
            return (Criteria) this;
        }

        public Criteria andEmployeeIdIsNull() {
            addCriterion("employeeId is null");
            return (Criteria) this;
        }

        public Criteria andEmployeeIdIsNotNull() {
            addCriterion("employeeId is not null");
            return (Criteria) this;
        }

        public Criteria andEmployeeIdEqualTo(Long value) {
            addCriterion("employeeId =", value, "employeeId");
            return (Criteria) this;
        }

        public Criteria andEmployeeIdNotEqualTo(Long value) {
            addCriterion("employeeId <>", value, "employeeId");
            return (Criteria) this;
        }

        public Criteria andEmployeeIdGreaterThan(Long value) {
            addCriterion("employeeId >", value, "employeeId");
            return (Criteria) this;
        }

        public Criteria andEmployeeIdGreaterThanOrEqualTo(Long value) {
            addCriterion("employeeId >=", value, "employeeId");
            return (Criteria) this;
        }

        public Criteria andEmployeeIdLessThan(Long value) {
            addCriterion("employeeId <", value, "employeeId");
            return (Criteria) this;
        }

        public Criteria andEmployeeIdLessThanOrEqualTo(Long value) {
            addCriterion("employeeId <=", value, "employeeId");
            return (Criteria) this;
        }

        public Criteria andEmployeeIdIn(List<Long> values) {
            addCriterion("employeeId in", values, "employeeId");
            return (Criteria) this;
        }

        public Criteria andEmployeeIdNotIn(List<Long> values) {
            addCriterion("employeeId not in", values, "employeeId");
            return (Criteria) this;
        }

        public Criteria andEmployeeIdBetween(Long value1, Long value2) {
            addCriterion("employeeId between", value1, value2, "employeeId");
            return (Criteria) this;
        }

        public Criteria andEmployeeIdNotBetween(Long value1, Long value2) {
            addCriterion("employeeId not between", value1, value2, "employeeId");
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

        public Criteria andLeaveStatusIsNull() {
            addCriterion("leaveStatus is null");
            return (Criteria) this;
        }

        public Criteria andLeaveStatusIsNotNull() {
            addCriterion("leaveStatus is not null");
            return (Criteria) this;
        }

        public Criteria andLeaveStatusEqualTo(Integer value) {
            addCriterion("leaveStatus =", value, "leaveStatus");
            return (Criteria) this;
        }

        public Criteria andLeaveStatusNotEqualTo(Integer value) {
            addCriterion("leaveStatus <>", value, "leaveStatus");
            return (Criteria) this;
        }

        public Criteria andLeaveStatusGreaterThan(Integer value) {
            addCriterion("leaveStatus >", value, "leaveStatus");
            return (Criteria) this;
        }

        public Criteria andLeaveStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("leaveStatus >=", value, "leaveStatus");
            return (Criteria) this;
        }

        public Criteria andLeaveStatusLessThan(Integer value) {
            addCriterion("leaveStatus <", value, "leaveStatus");
            return (Criteria) this;
        }

        public Criteria andLeaveStatusLessThanOrEqualTo(Integer value) {
            addCriterion("leaveStatus <=", value, "leaveStatus");
            return (Criteria) this;
        }

        public Criteria andLeaveStatusIn(List<Integer> values) {
            addCriterion("leaveStatus in", values, "leaveStatus");
            return (Criteria) this;
        }

        public Criteria andLeaveStatusNotIn(List<Integer> values) {
            addCriterion("leaveStatus not in", values, "leaveStatus");
            return (Criteria) this;
        }

        public Criteria andLeaveStatusBetween(Integer value1, Integer value2) {
            addCriterion("leaveStatus between", value1, value2, "leaveStatus");
            return (Criteria) this;
        }

        public Criteria andLeaveStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("leaveStatus not between", value1, value2, "leaveStatus");
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