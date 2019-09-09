package com.lx.benefits.bean.entity.creditoperateinfo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 积分操作记录
 *
 * @author luojie
 */
public class CreditOperateInfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected Integer page;

    protected Integer pageSize;

    protected List<Criteria> oredCriteria;

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

    public CreditOperateInfoExample() {
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
        oredCriteria.add(criteria);
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

        public Criteria andOptIdIsNull() {
            addCriterion("optId is null");
            return (Criteria) this;
        }

        public Criteria andOptIdIsNotNull() {
            addCriterion("optId is not null");
            return (Criteria) this;
        }

        public Criteria andOptIdEqualTo(Long value) {
            addCriterion("optId =", value, "optId");
            return (Criteria) this;
        }

        public Criteria andOptIdNotEqualTo(Long value) {
            addCriterion("optId <>", value, "optId");
            return (Criteria) this;
        }

        public Criteria andOptIdGreaterThan(Long value) {
            addCriterion("optId >", value, "optId");
            return (Criteria) this;
        }

        public Criteria andOptIdGreaterThanOrEqualTo(Long value) {
            addCriterion("optId >=", value, "optId");
            return (Criteria) this;
        }

        public Criteria andOptIdLessThan(Long value) {
            addCriterion("optId <", value, "optId");
            return (Criteria) this;
        }

        public Criteria andOptIdLessThanOrEqualTo(Long value) {
            addCriterion("optId <=", value, "optId");
            return (Criteria) this;
        }

        public Criteria andOptIdIn(List<Long> values) {
            addCriterion("optId in", values, "optId");
            return (Criteria) this;
        }

        public Criteria andOptIdNotIn(List<Long> values) {
            addCriterion("optId not in", values, "optId");
            return (Criteria) this;
        }

        public Criteria andOptIdBetween(Long value1, Long value2) {
            addCriterion("optId between", value1, value2, "optId");
            return (Criteria) this;
        }

        public Criteria andOptIdNotBetween(Long value1, Long value2) {
            addCriterion("optId not between", value1, value2, "optId");
            return (Criteria) this;
        }

        public Criteria andCampaignIdEqualTo(Long value) {
            addCriterion("campaignId =", value, "campaignId");
            return (Criteria) this;
        }
        
        public Criteria andCampaignIdIn(List<Long> values) {
            addCriterion("campaignId in", values, "campaignId");
            return (Criteria) this;
        }

        public Criteria andOwnerUserIdIsNull() {
            addCriterion("ownerUserId is null");
            return (Criteria) this;
        }

        public Criteria andOwnerUserIdIsNotNull() {
            addCriterion("ownerUserId is not null");
            return (Criteria) this;
        }

        public Criteria andOwnerUserIdEqualTo(Long value) {
            addCriterion("ownerUserId =", value, "ownerUserId");
            return (Criteria) this;
        }

        public Criteria andParentOptIdEqualTo(Long value) {
            addCriterion("parentOptId =", value, "parentOptId");
            return (Criteria) this;
        }

        public Criteria andParentOptIdGreaterThan(Long value) {
            addCriterion("parentOptId >", value, "parentOptId");
            return (Criteria) this;
        }

        public Criteria andOwnerUserIdNotEqualTo(Long value) {
            addCriterion("ownerUserId <>", value, "ownerUserId");
            return (Criteria) this;
        }

        public Criteria andOwnerUserIdGreaterThan(Long value) {
            addCriterion("ownerUserId >", value, "ownerUserId");
            return (Criteria) this;
        }

        public Criteria andOwnerUserIdGreaterThanOrEqualTo(Long value) {
            addCriterion("ownerUserId >=", value, "ownerUserId");
            return (Criteria) this;
        }

        public Criteria andOwnerUserIdLessThan(Long value) {
            addCriterion("ownerUserId <", value, "ownerUserId");
            return (Criteria) this;
        }

        public Criteria andOwnerUserIdLessThanOrEqualTo(Long value) {
            addCriterion("ownerUserId <=", value, "ownerUserId");
            return (Criteria) this;
        }

        public Criteria andOwnerUserIdIn(List<Long> values) {
            addCriterion("ownerUserId in", values, "ownerUserId");
            return (Criteria) this;
        }

        public Criteria andOwnerUserIdNotIn(List<Long> values) {
            addCriterion("ownerUserId not in", values, "ownerUserId");
            return (Criteria) this;
        }

        public Criteria andOwnerUserIdBetween(Long value1, Long value2) {
            addCriterion("ownerUserId between", value1, value2, "ownerUserId");
            return (Criteria) this;
        }

        public Criteria andOwnerUserIdNotBetween(Long value1, Long value2) {
            addCriterion("ownerUserId not between", value1, value2, "ownerUserId");
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

        public Criteria andCreditTypeEqualTo(Integer value) {
            addCriterion("creditType =", value, "creditType");
            return (Criteria) this;
        }

        public Criteria andCreditTypeNotEqualTo(Integer value) {
            addCriterion("creditType <>", value, "creditType");
            return (Criteria) this;
        }

        public Criteria andCreditTypeGreaterThan(Integer value) {
            addCriterion("creditType >", value, "creditType");
            return (Criteria) this;
        }

        public Criteria andCreditTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("creditType >=", value, "creditType");
            return (Criteria) this;
        }

        public Criteria andCreditTypeLessThan(Integer value) {
            addCriterion("creditType <", value, "creditType");
            return (Criteria) this;
        }

        public Criteria andCreditTypeLessThanOrEqualTo(Integer value) {
            addCriterion("creditType <=", value, "creditType");
            return (Criteria) this;
        }

        public Criteria andCreditTypeIn(List<Integer> values) {
            addCriterion("creditType in", values, "creditType");
            return (Criteria) this;
        }

        public Criteria andCreditTypeNotIn(List<Integer> values) {
            addCriterion("creditType not in", values, "creditType");
            return (Criteria) this;
        }

        public Criteria andCreditTypeBetween(Integer value1, Integer value2) {
            addCriterion("creditType between", value1, value2, "creditType");
            return (Criteria) this;
        }

        public Criteria andCreditTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("creditType not between", value1, value2, "creditType");
            return (Criteria) this;
        }

        public Criteria andCreditIsNull() {
            addCriterion("credit is null");
            return (Criteria) this;
        }

        public Criteria andCreditIsNotNull() {
            addCriterion("credit is not null");
            return (Criteria) this;
        }

        public Criteria andCreditEqualTo(BigDecimal value) {
            addCriterion("credit =", value, "credit");
            return (Criteria) this;
        }

        public Criteria andCreditNotEqualTo(BigDecimal value) {
            addCriterion("credit <>", value, "credit");
            return (Criteria) this;
        }

        public Criteria andCreditGreaterThan(BigDecimal value) {
            addCriterion("credit >", value, "credit");
            return (Criteria) this;
        }

        public Criteria andCreditGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("credit >=", value, "credit");
            return (Criteria) this;
        }

        public Criteria andCreditLessThan(BigDecimal value) {
            addCriterion("credit <", value, "credit");
            return (Criteria) this;
        }

        public Criteria andCreditLessThanOrEqualTo(BigDecimal value) {
            addCriterion("credit <=", value, "credit");
            return (Criteria) this;
        }

        public Criteria andCreditIn(List<BigDecimal> values) {
            addCriterion("credit in", values, "credit");
            return (Criteria) this;
        }

        public Criteria andCreditNotIn(List<BigDecimal> values) {
            addCriterion("credit not in", values, "credit");
            return (Criteria) this;
        }

        public Criteria andCreditBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("credit between", value1, value2, "credit");
            return (Criteria) this;
        }

        public Criteria andCreditNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("credit not between", value1, value2, "credit");
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

        public Criteria andOptTimeIsNull() {
            addCriterion("optTime is null");
            return (Criteria) this;
        }

        public Criteria andOptTimeIsNotNull() {
            addCriterion("optTime is not null");
            return (Criteria) this;
        }

        public Criteria andOptTimeEqualTo(Integer value) {
            addCriterion("optTime =", value, "optTime");
            return (Criteria) this;
        }

        public Criteria andOptTimeNotEqualTo(Integer value) {
            addCriterion("optTime <>", value, "optTime");
            return (Criteria) this;
        }

        public Criteria andOptTimeGreaterThan(Integer value) {
            addCriterion("optTime >", value, "optTime");
            return (Criteria) this;
        }

        public Criteria andOptTimeGreaterThanOrEqualTo(Integer value) {
            addCriterion("optTime >=", value, "optTime");
            return (Criteria) this;
        }

        public Criteria andOptTimeLessThan(Integer value) {
            addCriterion("optTime <", value, "optTime");
            return (Criteria) this;
        }

        public Criteria andOptTimeLessThanOrEqualTo(Integer value) {
            addCriterion("optTime <=", value, "optTime");
            return (Criteria) this;
        }

        public Criteria andOptTimeIn(List<Integer> values) {
            addCriterion("optTime in", values, "optTime");
            return (Criteria) this;
        }

        public Criteria andOptTimeNotIn(List<Integer> values) {
            addCriterion("optTime not in", values, "optTime");
            return (Criteria) this;
        }

        public Criteria andOptTimeBetween(Integer value1, Integer value2) {
            addCriterion("optTime between", value1, value2, "optTime");
            return (Criteria) this;
        }

        public Criteria andOptTimeNotBetween(Integer value1, Integer value2) {
            addCriterion("optTime not between", value1, value2, "optTime");
            return (Criteria) this;
        }

        public Criteria andAuditUserIdIsNull() {
            addCriterion("auditUserId is null");
            return (Criteria) this;
        }

        public Criteria andAuditUserIdIsNotNull() {
            addCriterion("auditUserId is not null");
            return (Criteria) this;
        }

        public Criteria andAuditUserIdEqualTo(Long value) {
            addCriterion("auditUserId =", value, "auditUserId");
            return (Criteria) this;
        }

        public Criteria andAuditUserIdNotEqualTo(Long value) {
            addCriterion("auditUserId <>", value, "auditUserId");
            return (Criteria) this;
        }

        public Criteria andAuditUserIdGreaterThan(Long value) {
            addCriterion("auditUserId >", value, "auditUserId");
            return (Criteria) this;
        }

        public Criteria andAuditUserIdGreaterThanOrEqualTo(Long value) {
            addCriterion("auditUserId >=", value, "auditUserId");
            return (Criteria) this;
        }

        public Criteria andAuditUserIdLessThan(Long value) {
            addCriterion("auditUserId <", value, "auditUserId");
            return (Criteria) this;
        }

        public Criteria andAuditUserIdLessThanOrEqualTo(Long value) {
            addCriterion("auditUserId <=", value, "auditUserId");
            return (Criteria) this;
        }

        public Criteria andAuditUserIdIn(List<Long> values) {
            addCriterion("auditUserId in", values, "auditUserId");
            return (Criteria) this;
        }

        public Criteria andAuditUserIdNotIn(List<Long> values) {
            addCriterion("auditUserId not in", values, "auditUserId");
            return (Criteria) this;
        }

        public Criteria andAuditUserIdBetween(Long value1, Long value2) {
            addCriterion("auditUserId between", value1, value2, "auditUserId");
            return (Criteria) this;
        }

        public Criteria andAuditUserIdNotBetween(Long value1, Long value2) {
            addCriterion("auditUserId not between", value1, value2, "auditUserId");
            return (Criteria) this;
        }

        public Criteria andAuditTimeIsNull() {
            addCriterion("auditTime is null");
            return (Criteria) this;
        }

        public Criteria andAuditTimeIsNotNull() {
            addCriterion("auditTime is not null");
            return (Criteria) this;
        }

        public Criteria andAuditTimeEqualTo(Integer value) {
            addCriterion("auditTime =", value, "auditTime");
            return (Criteria) this;
        }

        public Criteria andAuditTimeNotEqualTo(Integer value) {
            addCriterion("auditTime <>", value, "auditTime");
            return (Criteria) this;
        }

        public Criteria andAuditTimeGreaterThan(Integer value) {
            addCriterion("auditTime >", value, "auditTime");
            return (Criteria) this;
        }

        public Criteria andAuditTimeGreaterThanOrEqualTo(Integer value) {
            addCriterion("auditTime >=", value, "auditTime");
            return (Criteria) this;
        }

        public Criteria andAuditTimeLessThan(Integer value) {
            addCriterion("auditTime <", value, "auditTime");
            return (Criteria) this;
        }

        public Criteria andAuditTimeLessThanOrEqualTo(Integer value) {
            addCriterion("auditTime <=", value, "auditTime");
            return (Criteria) this;
        }

        public Criteria andAuditTimeIn(List<Integer> values) {
            addCriterion("auditTime in", values, "auditTime");
            return (Criteria) this;
        }

        public Criteria andAuditTimeNotIn(List<Integer> values) {
            addCriterion("auditTime not in", values, "auditTime");
            return (Criteria) this;
        }

        public Criteria andAuditTimeBetween(Integer value1, Integer value2) {
            addCriterion("auditTime between", value1, value2, "auditTime");
            return (Criteria) this;
        }

        public Criteria andAuditTimeNotBetween(Integer value1, Integer value2) {
            addCriterion("auditTime not between", value1, value2, "auditTime");
            return (Criteria) this;
        }

        public Criteria andOptTypeIsNull() {
            addCriterion("optType is null");
            return (Criteria) this;
        }

        public Criteria andOptTypeIsNotNull() {
            addCriterion("optType is not null");
            return (Criteria) this;
        }

        public Criteria andOptTypeEqualTo(Integer value) {
            addCriterion("optType =", value, "optType");
            return (Criteria) this;
        }

        public Criteria andOptTypeNotEqualTo(Integer value) {
            addCriterion("optType <>", value, "optType");
            return (Criteria) this;
        }

        public Criteria andOptTypeGreaterThan(Integer value) {
            addCriterion("optType >", value, "optType");
            return (Criteria) this;
        }

        public Criteria andOptTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("optType >=", value, "optType");
            return (Criteria) this;
        }

        public Criteria andOptTypeLessThan(Integer value) {
            addCriterion("optType <", value, "optType");
            return (Criteria) this;
        }

        public Criteria andOptTypeLessThanOrEqualTo(Integer value) {
            addCriterion("optType <=", value, "optType");
            return (Criteria) this;
        }

        public Criteria andOptTypeIn(List<Integer> values) {
            addCriterion("optType in", values, "optType");
            return (Criteria) this;
        }

        public Criteria andOptTypeNotIn(List<Integer> values) {
            addCriterion("optType not in", values, "optType");
            return (Criteria) this;
        }

        public Criteria andOptTypeBetween(Integer value1, Integer value2) {
            addCriterion("optType between", value1, value2, "optType");
            return (Criteria) this;
        }

        public Criteria andOptTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("optType not between", value1, value2, "optType");
            return (Criteria) this;
        }

        public Criteria andAuditStatusIsNull() {
            addCriterion("auditStatus is null");
            return (Criteria) this;
        }

        public Criteria andAuditStatusIsNotNull() {
            addCriterion("auditStatus is not null");
            return (Criteria) this;
        }

        public Criteria andAuditStatusEqualTo(Integer value) {
            addCriterion("auditStatus =", value, "auditStatus");
            return (Criteria) this;
        }

        public Criteria andAuditStatusNotEqualTo(Integer value) {
            addCriterion("auditStatus <>", value, "auditStatus");
            return (Criteria) this;
        }

        public Criteria andAuditStatusGreaterThan(Integer value) {
            addCriterion("auditStatus >", value, "auditStatus");
            return (Criteria) this;
        }

        public Criteria andAuditStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("auditStatus >=", value, "auditStatus");
            return (Criteria) this;
        }

        public Criteria andAuditStatusLessThan(Integer value) {
            addCriterion("auditStatus <", value, "auditStatus");
            return (Criteria) this;
        }

        public Criteria andAuditStatusLessThanOrEqualTo(Integer value) {
            addCriterion("auditStatus <=", value, "auditStatus");
            return (Criteria) this;
        }

        public Criteria andAuditStatusIn(List<Integer> values) {
            addCriterion("auditStatus in", values, "auditStatus");
            return (Criteria) this;
        }

        public Criteria andAuditStatusNotIn(List<Integer> values) {
            addCriterion("auditStatus not in", values, "auditStatus");
            return (Criteria) this;
        }

        public Criteria andAuditStatusBetween(Integer value1, Integer value2) {
            addCriterion("auditStatus between", value1, value2, "auditStatus");
            return (Criteria) this;
        }

        public Criteria andAuditStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("auditStatus not between", value1, value2, "auditStatus");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNull() {
            addCriterion("remark is null");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNotNull() {
            addCriterion("remark is not null");
            return (Criteria) this;
        }

        public Criteria andRemarkEqualTo(String value) {
            addCriterion("remark =", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotEqualTo(String value) {
            addCriterion("remark <>", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThan(String value) {
            addCriterion("remark >", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("remark >=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThan(String value) {
            addCriterion("remark <", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThanOrEqualTo(String value) {
            addCriterion("remark <=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLike(String value) {
            addCriterion("remark like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotLike(String value) {
            addCriterion("remark not like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkIn(List<String> values) {
            addCriterion("remark in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotIn(List<String> values) {
            addCriterion("remark not in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkBetween(String value1, String value2) {
            addCriterion("remark between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotBetween(String value1, String value2) {
            addCriterion("remark not between", value1, value2, "remark");
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