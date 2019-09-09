package com.lx.benefits.bean.entity.enterpruserinfo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EnterprBindRecorderCondition {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private Integer limit;

    private Integer offset;

    public EnterprBindRecorderCondition() {
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

        public Criteria andEnterprIdIsNull() {
            addCriterion("enterpr_id is null");
            return (Criteria) this;
        }

        public Criteria andEnterprIdIsNotNull() {
            addCriterion("enterpr_id is not null");
            return (Criteria) this;
        }

        public Criteria andEnterprIdEqualTo(Long value) {
            addCriterion("enterpr_id =", value, "enterprId");
            return (Criteria) this;
        }

        public Criteria andEnterprIdNotEqualTo(Long value) {
            addCriterion("enterpr_id <>", value, "enterprId");
            return (Criteria) this;
        }

        public Criteria andEnterprIdGreaterThan(Long value) {
            addCriterion("enterpr_id >", value, "enterprId");
            return (Criteria) this;
        }

        public Criteria andEnterprIdGreaterThanOrEqualTo(Long value) {
            addCriterion("enterpr_id >=", value, "enterprId");
            return (Criteria) this;
        }

        public Criteria andEnterprIdLessThan(Long value) {
            addCriterion("enterpr_id <", value, "enterprId");
            return (Criteria) this;
        }

        public Criteria andEnterprIdLessThanOrEqualTo(Long value) {
            addCriterion("enterpr_id <=", value, "enterprId");
            return (Criteria) this;
        }

        public Criteria andEnterprIdIn(List<Long> values) {
            addCriterion("enterpr_id in", values, "enterprId");
            return (Criteria) this;
        }

        public Criteria andEnterprIdNotIn(List<Long> values) {
            addCriterion("enterpr_id not in", values, "enterprId");
            return (Criteria) this;
        }

        public Criteria andEnterprIdBetween(Long value1, Long value2) {
            addCriterion("enterpr_id between", value1, value2, "enterprId");
            return (Criteria) this;
        }

        public Criteria andEnterprIdNotBetween(Long value1, Long value2) {
            addCriterion("enterpr_id not between", value1, value2, "enterprId");
            return (Criteria) this;
        }

        public Criteria andAgentIdIsNull() {
            addCriterion("agent_id is null");
            return (Criteria) this;
        }

        public Criteria andAgentIdIsNotNull() {
            addCriterion("agent_id is not null");
            return (Criteria) this;
        }

        public Criteria andAgentIdEqualTo(Integer value) {
            addCriterion("agent_id =", value, "agentId");
            return (Criteria) this;
        }

        public Criteria andAgentIdNotEqualTo(Integer value) {
            addCriterion("agent_id <>", value, "agentId");
            return (Criteria) this;
        }

        public Criteria andAgentIdGreaterThan(Integer value) {
            addCriterion("agent_id >", value, "agentId");
            return (Criteria) this;
        }

        public Criteria andAgentIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("agent_id >=", value, "agentId");
            return (Criteria) this;
        }

        public Criteria andAgentIdLessThan(Integer value) {
            addCriterion("agent_id <", value, "agentId");
            return (Criteria) this;
        }

        public Criteria andAgentIdLessThanOrEqualTo(Integer value) {
            addCriterion("agent_id <=", value, "agentId");
            return (Criteria) this;
        }

        public Criteria andAgentIdIn(List<Integer> values) {
            addCriterion("agent_id in", values, "agentId");
            return (Criteria) this;
        }

        public Criteria andAgentIdNotIn(List<Integer> values) {
            addCriterion("agent_id not in", values, "agentId");
            return (Criteria) this;
        }

        public Criteria andAgentIdBetween(Integer value1, Integer value2) {
            addCriterion("agent_id between", value1, value2, "agentId");
            return (Criteria) this;
        }

        public Criteria andAgentIdNotBetween(Integer value1, Integer value2) {
            addCriterion("agent_id not between", value1, value2, "agentId");
            return (Criteria) this;
        }

        public Criteria andAgentNameIsNull() {
            addCriterion("agent_name is null");
            return (Criteria) this;
        }

        public Criteria andAgentNameIsNotNull() {
            addCriterion("agent_name is not null");
            return (Criteria) this;
        }

        public Criteria andAgentNameEqualTo(String value) {
            addCriterion("agent_name =", value, "agentName");
            return (Criteria) this;
        }

        public Criteria andAgentNameNotEqualTo(String value) {
            addCriterion("agent_name <>", value, "agentName");
            return (Criteria) this;
        }

        public Criteria andAgentNameGreaterThan(String value) {
            addCriterion("agent_name >", value, "agentName");
            return (Criteria) this;
        }

        public Criteria andAgentNameGreaterThanOrEqualTo(String value) {
            addCriterion("agent_name >=", value, "agentName");
            return (Criteria) this;
        }

        public Criteria andAgentNameLessThan(String value) {
            addCriterion("agent_name <", value, "agentName");
            return (Criteria) this;
        }

        public Criteria andAgentNameLessThanOrEqualTo(String value) {
            addCriterion("agent_name <=", value, "agentName");
            return (Criteria) this;
        }

        public Criteria andAgentNameLike(String value) {
            addCriterion("agent_name like", value, "agentName");
            return (Criteria) this;
        }

        public Criteria andAgentNameNotLike(String value) {
            addCriterion("agent_name not like", value, "agentName");
            return (Criteria) this;
        }

        public Criteria andAgentNameIn(List<String> values) {
            addCriterion("agent_name in", values, "agentName");
            return (Criteria) this;
        }

        public Criteria andAgentNameNotIn(List<String> values) {
            addCriterion("agent_name not in", values, "agentName");
            return (Criteria) this;
        }

        public Criteria andAgentNameBetween(String value1, String value2) {
            addCriterion("agent_name between", value1, value2, "agentName");
            return (Criteria) this;
        }

        public Criteria andAgentNameNotBetween(String value1, String value2) {
            addCriterion("agent_name not between", value1, value2, "agentName");
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

        public Criteria andStatusEqualTo(Integer value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(Integer value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(Integer value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(Integer value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(Integer value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<Integer> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<Integer> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(Integer value1, Integer value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("status not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andBindRemarkIsNull() {
            addCriterion("bind_remark is null");
            return (Criteria) this;
        }

        public Criteria andBindRemarkIsNotNull() {
            addCriterion("bind_remark is not null");
            return (Criteria) this;
        }

        public Criteria andBindRemarkEqualTo(String value) {
            addCriterion("bind_remark =", value, "bindRemark");
            return (Criteria) this;
        }

        public Criteria andBindRemarkNotEqualTo(String value) {
            addCriterion("bind_remark <>", value, "bindRemark");
            return (Criteria) this;
        }

        public Criteria andBindRemarkGreaterThan(String value) {
            addCriterion("bind_remark >", value, "bindRemark");
            return (Criteria) this;
        }

        public Criteria andBindRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("bind_remark >=", value, "bindRemark");
            return (Criteria) this;
        }

        public Criteria andBindRemarkLessThan(String value) {
            addCriterion("bind_remark <", value, "bindRemark");
            return (Criteria) this;
        }

        public Criteria andBindRemarkLessThanOrEqualTo(String value) {
            addCriterion("bind_remark <=", value, "bindRemark");
            return (Criteria) this;
        }

        public Criteria andBindRemarkLike(String value) {
            addCriterion("bind_remark like", value, "bindRemark");
            return (Criteria) this;
        }

        public Criteria andBindRemarkNotLike(String value) {
            addCriterion("bind_remark not like", value, "bindRemark");
            return (Criteria) this;
        }

        public Criteria andBindRemarkIn(List<String> values) {
            addCriterion("bind_remark in", values, "bindRemark");
            return (Criteria) this;
        }

        public Criteria andBindRemarkNotIn(List<String> values) {
            addCriterion("bind_remark not in", values, "bindRemark");
            return (Criteria) this;
        }

        public Criteria andBindRemarkBetween(String value1, String value2) {
            addCriterion("bind_remark between", value1, value2, "bindRemark");
            return (Criteria) this;
        }

        public Criteria andBindRemarkNotBetween(String value1, String value2) {
            addCriterion("bind_remark not between", value1, value2, "bindRemark");
            return (Criteria) this;
        }

        public Criteria andBindUserIsNull() {
            addCriterion("bind_user is null");
            return (Criteria) this;
        }

        public Criteria andBindUserIsNotNull() {
            addCriterion("bind_user is not null");
            return (Criteria) this;
        }

        public Criteria andBindUserEqualTo(String value) {
            addCriterion("bind_user =", value, "bindUser");
            return (Criteria) this;
        }

        public Criteria andBindUserNotEqualTo(String value) {
            addCriterion("bind_user <>", value, "bindUser");
            return (Criteria) this;
        }

        public Criteria andBindUserGreaterThan(String value) {
            addCriterion("bind_user >", value, "bindUser");
            return (Criteria) this;
        }

        public Criteria andBindUserGreaterThanOrEqualTo(String value) {
            addCriterion("bind_user >=", value, "bindUser");
            return (Criteria) this;
        }

        public Criteria andBindUserLessThan(String value) {
            addCriterion("bind_user <", value, "bindUser");
            return (Criteria) this;
        }

        public Criteria andBindUserLessThanOrEqualTo(String value) {
            addCriterion("bind_user <=", value, "bindUser");
            return (Criteria) this;
        }

        public Criteria andBindUserLike(String value) {
            addCriterion("bind_user like", value, "bindUser");
            return (Criteria) this;
        }

        public Criteria andBindUserNotLike(String value) {
            addCriterion("bind_user not like", value, "bindUser");
            return (Criteria) this;
        }

        public Criteria andBindUserIn(List<String> values) {
            addCriterion("bind_user in", values, "bindUser");
            return (Criteria) this;
        }

        public Criteria andBindUserNotIn(List<String> values) {
            addCriterion("bind_user not in", values, "bindUser");
            return (Criteria) this;
        }

        public Criteria andBindUserBetween(String value1, String value2) {
            addCriterion("bind_user between", value1, value2, "bindUser");
            return (Criteria) this;
        }

        public Criteria andBindUserNotBetween(String value1, String value2) {
            addCriterion("bind_user not between", value1, value2, "bindUser");
            return (Criteria) this;
        }

        public Criteria andBindTimeIsNull() {
            addCriterion("bind_time is null");
            return (Criteria) this;
        }

        public Criteria andBindTimeIsNotNull() {
            addCriterion("bind_time is not null");
            return (Criteria) this;
        }

        public Criteria andBindTimeEqualTo(Date value) {
            addCriterion("bind_time =", value, "bindTime");
            return (Criteria) this;
        }

        public Criteria andBindTimeNotEqualTo(Date value) {
            addCriterion("bind_time <>", value, "bindTime");
            return (Criteria) this;
        }

        public Criteria andBindTimeGreaterThan(Date value) {
            addCriterion("bind_time >", value, "bindTime");
            return (Criteria) this;
        }

        public Criteria andBindTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("bind_time >=", value, "bindTime");
            return (Criteria) this;
        }

        public Criteria andBindTimeLessThan(Date value) {
            addCriterion("bind_time <", value, "bindTime");
            return (Criteria) this;
        }

        public Criteria andBindTimeLessThanOrEqualTo(Date value) {
            addCriterion("bind_time <=", value, "bindTime");
            return (Criteria) this;
        }

        public Criteria andBindTimeIn(List<Date> values) {
            addCriterion("bind_time in", values, "bindTime");
            return (Criteria) this;
        }

        public Criteria andBindTimeNotIn(List<Date> values) {
            addCriterion("bind_time not in", values, "bindTime");
            return (Criteria) this;
        }

        public Criteria andBindTimeBetween(Date value1, Date value2) {
            addCriterion("bind_time between", value1, value2, "bindTime");
            return (Criteria) this;
        }

        public Criteria andBindTimeNotBetween(Date value1, Date value2) {
            addCriterion("bind_time not between", value1, value2, "bindTime");
            return (Criteria) this;
        }

        public Criteria andUnbindRemarkIsNull() {
            addCriterion("unbind_remark is null");
            return (Criteria) this;
        }

        public Criteria andUnbindRemarkIsNotNull() {
            addCriterion("unbind_remark is not null");
            return (Criteria) this;
        }

        public Criteria andUnbindRemarkEqualTo(String value) {
            addCriterion("unbind_remark =", value, "unbindRemark");
            return (Criteria) this;
        }

        public Criteria andUnbindRemarkNotEqualTo(String value) {
            addCriterion("unbind_remark <>", value, "unbindRemark");
            return (Criteria) this;
        }

        public Criteria andUnbindRemarkGreaterThan(String value) {
            addCriterion("unbind_remark >", value, "unbindRemark");
            return (Criteria) this;
        }

        public Criteria andUnbindRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("unbind_remark >=", value, "unbindRemark");
            return (Criteria) this;
        }

        public Criteria andUnbindRemarkLessThan(String value) {
            addCriterion("unbind_remark <", value, "unbindRemark");
            return (Criteria) this;
        }

        public Criteria andUnbindRemarkLessThanOrEqualTo(String value) {
            addCriterion("unbind_remark <=", value, "unbindRemark");
            return (Criteria) this;
        }

        public Criteria andUnbindRemarkLike(String value) {
            addCriterion("unbind_remark like", value, "unbindRemark");
            return (Criteria) this;
        }

        public Criteria andUnbindRemarkNotLike(String value) {
            addCriterion("unbind_remark not like", value, "unbindRemark");
            return (Criteria) this;
        }

        public Criteria andUnbindRemarkIn(List<String> values) {
            addCriterion("unbind_remark in", values, "unbindRemark");
            return (Criteria) this;
        }

        public Criteria andUnbindRemarkNotIn(List<String> values) {
            addCriterion("unbind_remark not in", values, "unbindRemark");
            return (Criteria) this;
        }

        public Criteria andUnbindRemarkBetween(String value1, String value2) {
            addCriterion("unbind_remark between", value1, value2, "unbindRemark");
            return (Criteria) this;
        }

        public Criteria andUnbindRemarkNotBetween(String value1, String value2) {
            addCriterion("unbind_remark not between", value1, value2, "unbindRemark");
            return (Criteria) this;
        }

        public Criteria andUnbindUserIsNull() {
            addCriterion("unbind_user is null");
            return (Criteria) this;
        }

        public Criteria andUnbindUserIsNotNull() {
            addCriterion("unbind_user is not null");
            return (Criteria) this;
        }

        public Criteria andUnbindUserEqualTo(String value) {
            addCriterion("unbind_user =", value, "unbindUser");
            return (Criteria) this;
        }

        public Criteria andUnbindUserNotEqualTo(String value) {
            addCriterion("unbind_user <>", value, "unbindUser");
            return (Criteria) this;
        }

        public Criteria andUnbindUserGreaterThan(String value) {
            addCriterion("unbind_user >", value, "unbindUser");
            return (Criteria) this;
        }

        public Criteria andUnbindUserGreaterThanOrEqualTo(String value) {
            addCriterion("unbind_user >=", value, "unbindUser");
            return (Criteria) this;
        }

        public Criteria andUnbindUserLessThan(String value) {
            addCriterion("unbind_user <", value, "unbindUser");
            return (Criteria) this;
        }

        public Criteria andUnbindUserLessThanOrEqualTo(String value) {
            addCriterion("unbind_user <=", value, "unbindUser");
            return (Criteria) this;
        }

        public Criteria andUnbindUserLike(String value) {
            addCriterion("unbind_user like", value, "unbindUser");
            return (Criteria) this;
        }

        public Criteria andUnbindUserNotLike(String value) {
            addCriterion("unbind_user not like", value, "unbindUser");
            return (Criteria) this;
        }

        public Criteria andUnbindUserIn(List<String> values) {
            addCriterion("unbind_user in", values, "unbindUser");
            return (Criteria) this;
        }

        public Criteria andUnbindUserNotIn(List<String> values) {
            addCriterion("unbind_user not in", values, "unbindUser");
            return (Criteria) this;
        }

        public Criteria andUnbindUserBetween(String value1, String value2) {
            addCriterion("unbind_user between", value1, value2, "unbindUser");
            return (Criteria) this;
        }

        public Criteria andUnbindUserNotBetween(String value1, String value2) {
            addCriterion("unbind_user not between", value1, value2, "unbindUser");
            return (Criteria) this;
        }

        public Criteria andUnbindTimeIsNull() {
            addCriterion("unbind_time is null");
            return (Criteria) this;
        }

        public Criteria andUnbindTimeIsNotNull() {
            addCriterion("unbind_time is not null");
            return (Criteria) this;
        }

        public Criteria andUnbindTimeEqualTo(Date value) {
            addCriterion("unbind_time =", value, "unbindTime");
            return (Criteria) this;
        }

        public Criteria andUnbindTimeNotEqualTo(Date value) {
            addCriterion("unbind_time <>", value, "unbindTime");
            return (Criteria) this;
        }

        public Criteria andUnbindTimeGreaterThan(Date value) {
            addCriterion("unbind_time >", value, "unbindTime");
            return (Criteria) this;
        }

        public Criteria andUnbindTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("unbind_time >=", value, "unbindTime");
            return (Criteria) this;
        }

        public Criteria andUnbindTimeLessThan(Date value) {
            addCriterion("unbind_time <", value, "unbindTime");
            return (Criteria) this;
        }

        public Criteria andUnbindTimeLessThanOrEqualTo(Date value) {
            addCriterion("unbind_time <=", value, "unbindTime");
            return (Criteria) this;
        }

        public Criteria andUnbindTimeIn(List<Date> values) {
            addCriterion("unbind_time in", values, "unbindTime");
            return (Criteria) this;
        }

        public Criteria andUnbindTimeNotIn(List<Date> values) {
            addCriterion("unbind_time not in", values, "unbindTime");
            return (Criteria) this;
        }

        public Criteria andUnbindTimeBetween(Date value1, Date value2) {
            addCriterion("unbind_time between", value1, value2, "unbindTime");
            return (Criteria) this;
        }

        public Criteria andUnbindTimeNotBetween(Date value1, Date value2) {
            addCriterion("unbind_time not between", value1, value2, "unbindTime");
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