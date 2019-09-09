package com.lx.benefits.bean.entity.enterprfestivalpacket;

import java.util.ArrayList;
import java.util.List;

public class EnterprFestivalPacketExample {
    protected String orderByClause;

    protected boolean distinct;

    private Integer start;

    protected Integer page;

    protected Integer pageSize;
    
    protected List<Criteria> oredCriteria;

    public EnterprFestivalPacketExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public Integer getStart() {
        this.page = null == this.page || this.page < 1 ? 1 : this.page;
        this.pageSize = null == this.pageSize || this.pageSize < 1 ? 1 : this.pageSize;
        start = (this.page - 1) * this.pageSize;
        return start;
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

        public Criteria andCampaignIdIsNull() {
            addCriterion("campaignId is null");
            return (Criteria) this;
        }

        public Criteria andCampaignIdIsNotNull() {
            addCriterion("campaignId is not null");
            return (Criteria) this;
        }

        public Criteria andCampaignIdEqualTo(Long value) {
            addCriterion("campaignId =", value, "campaignId");
            return (Criteria) this;
        }

        public Criteria andCampaignIdNotEqualTo(Long value) {
            addCriterion("campaignId <>", value, "campaignId");
            return (Criteria) this;
        }

        public Criteria andCampaignIdGreaterThan(Long value) {
            addCriterion("campaignId >", value, "campaignId");
            return (Criteria) this;
        }

        public Criteria andCampaignIdGreaterThanOrEqualTo(Long value) {
            addCriterion("campaignId >=", value, "campaignId");
            return (Criteria) this;
        }

        public Criteria andCampaignIdLessThan(Long value) {
            addCriterion("campaignId <", value, "campaignId");
            return (Criteria) this;
        }

        public Criteria andCampaignIdLessThanOrEqualTo(Long value) {
            addCriterion("campaignId <=", value, "campaignId");
            return (Criteria) this;
        }

        public Criteria andCampaignIdIn(List<Long> values) {
            addCriterion("campaignId in", values, "campaignId");
            return (Criteria) this;
        }

        public Criteria andCampaignIdNotIn(List<Long> values) {
            addCriterion("campaignId not in", values, "campaignId");
            return (Criteria) this;
        }

        public Criteria andCampaignIdBetween(Long value1, Long value2) {
            addCriterion("campaignId between", value1, value2, "campaignId");
            return (Criteria) this;
        }

        public Criteria andCampaignIdNotBetween(Long value1, Long value2) {
            addCriterion("campaignId not between", value1, value2, "campaignId");
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

        public Criteria andLeaveStatusEqualTo(Integer value) {
            addCriterion("leaveStatus =", value, "leaveStatus");
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

        public Criteria andCampaignNameIsNull() {
            addCriterion("campaignName is null");
            return (Criteria) this;
        }

        public Criteria andCampaignNameIsNotNull() {
            addCriterion("campaignName is not null");
            return (Criteria) this;
        }

        public Criteria andCampaignNameEqualTo(String value) {
            addCriterion("campaignName =", value, "campaignName");
            return (Criteria) this;
        }

        public Criteria andCampaignNameNotEqualTo(String value) {
            addCriterion("campaignName <>", value, "campaignName");
            return (Criteria) this;
        }

        public Criteria andCampaignNameGreaterThan(String value) {
            addCriterion("campaignName >", value, "campaignName");
            return (Criteria) this;
        }

        public Criteria andCampaignNameGreaterThanOrEqualTo(String value) {
            addCriterion("campaignName >=", value, "campaignName");
            return (Criteria) this;
        }

        public Criteria andCampaignNameLessThan(String value) {
            addCriterion("campaignName <", value, "campaignName");
            return (Criteria) this;
        }

        public Criteria andCampaignNameLessThanOrEqualTo(String value) {
            addCriterion("campaignName <=", value, "campaignName");
            return (Criteria) this;
        }

        public Criteria andCampaignNameLike(String value) {
            addCriterion("campaignName like", value, "campaignName");
            return (Criteria) this;
        }

        public Criteria andCampaignNameNotLike(String value) {
            addCriterion("campaignName not like", value, "campaignName");
            return (Criteria) this;
        }

        public Criteria andCampaignNameIn(List<String> values) {
            addCriterion("campaignName in", values, "campaignName");
            return (Criteria) this;
        }

        public Criteria andCampaignNameNotIn(List<String> values) {
            addCriterion("campaignName not in", values, "campaignName");
            return (Criteria) this;
        }

        public Criteria andCampaignNameBetween(String value1, String value2) {
            addCriterion("campaignName between", value1, value2, "campaignName");
            return (Criteria) this;
        }

        public Criteria andCampaignNameNotBetween(String value1, String value2) {
            addCriterion("campaignName not between", value1, value2, "campaignName");
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

        public Criteria andTypeEqualTo(Integer value) {
            addCriterion("type =", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotIn(List<Integer> values) {
            addCriterion("type not in", values, "type");
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

        public Criteria andCampaignThemeIdIsNull() {
            addCriterion("campaignThemeId is null");
            return (Criteria) this;
        }

        public Criteria andCampaignThemeIdIsNotNull() {
            addCriterion("campaignThemeId is not null");
            return (Criteria) this;
        }

        public Criteria andCampaignThemeIdEqualTo(Integer value) {
            addCriterion("campaignThemeId =", value, "campaignThemeId");
            return (Criteria) this;
        }

        public Criteria andCampaignThemeIdNotEqualTo(Integer value) {
            addCriterion("campaignThemeId <>", value, "campaignThemeId");
            return (Criteria) this;
        }

        public Criteria andCampaignThemeIdGreaterThan(Integer value) {
            addCriterion("campaignThemeId >", value, "campaignThemeId");
            return (Criteria) this;
        }

        public Criteria andCampaignThemeIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("campaignThemeId >=", value, "campaignThemeId");
            return (Criteria) this;
        }

        public Criteria andCampaignThemeIdLessThan(Integer value) {
            addCriterion("campaignThemeId <", value, "campaignThemeId");
            return (Criteria) this;
        }

        public Criteria andCampaignThemeIdLessThanOrEqualTo(Integer value) {
            addCriterion("campaignThemeId <=", value, "campaignThemeId");
            return (Criteria) this;
        }

        public Criteria andCampaignThemeIdIn(List<Integer> values) {
            addCriterion("campaignThemeId in", values, "campaignThemeId");
            return (Criteria) this;
        }

        public Criteria andCampaignThemeIdNotIn(List<Integer> values) {
            addCriterion("campaignThemeId not in", values, "campaignThemeId");
            return (Criteria) this;
        }

        public Criteria andCampaignThemeIdBetween(Integer value1, Integer value2) {
            addCriterion("campaignThemeId between", value1, value2, "campaignThemeId");
            return (Criteria) this;
        }

        public Criteria andCampaignThemeIdNotBetween(Integer value1, Integer value2) {
            addCriterion("campaignThemeId not between", value1, value2, "campaignThemeId");
            return (Criteria) this;
        }

        public Criteria andCampaignStatusIsNull() {
            addCriterion("campaignStatus is null");
            return (Criteria) this;
        }

        public Criteria andCampaignStatusIsNotNull() {
            addCriterion("campaignStatus is not null");
            return (Criteria) this;
        }

        public Criteria andCampaignStatusEqualTo(Integer value) {
            addCriterion("campaignStatus =", value, "campaignStatus");
            return (Criteria) this;
        }

        public Criteria andCampaignStatusNotEqualTo(Integer value) {
            addCriterion("campaignStatus <>", value, "campaignStatus");
            return (Criteria) this;
        }

        public Criteria andCampaignStatusGreaterThan(Integer value) {
            addCriterion("campaignStatus >", value, "campaignStatus");
            return (Criteria) this;
        }

        public Criteria andCampaignStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("campaignStatus >=", value, "campaignStatus");
            return (Criteria) this;
        }

        public Criteria andCampaignStatusLessThan(Integer value) {
            addCriterion("campaignStatus <", value, "campaignStatus");
            return (Criteria) this;
        }

        public Criteria andCampaignStatusLessThanOrEqualTo(Integer value) {
            addCriterion("campaignStatus <=", value, "campaignStatus");
            return (Criteria) this;
        }

        public Criteria andCampaignStatusIn(List<Integer> values) {
            addCriterion("campaignStatus in", values, "campaignStatus");
            return (Criteria) this;
        }

        public Criteria andCampaignStatusNotIn(List<Integer> values) {
            addCriterion("campaignStatus not in", values, "campaignStatus");
            return (Criteria) this;
        }

        public Criteria andCampaignStatusBetween(Integer value1, Integer value2) {
            addCriterion("campaignStatus between", value1, value2, "campaignStatus");
            return (Criteria) this;
        }

        public Criteria andCampaignStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("campaignStatus not between", value1, value2, "campaignStatus");
            return (Criteria) this;
        }

        public Criteria andStartTimeIsNull() {
            addCriterion("startTime is null");
            return (Criteria) this;
        }

        public Criteria andStartTimeIsNotNull() {
            addCriterion("startTime is not null");
            return (Criteria) this;
        }

        public Criteria andStartTimeEqualTo(Integer value) {
            addCriterion("startTime =", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeNotEqualTo(Integer value) {
            addCriterion("startTime <>", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeGreaterThan(Integer value) {
            addCriterion("startTime >", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeGreaterThanOrEqualTo(Integer value) {
            addCriterion("startTime >=", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeLessThan(Integer value) {
            addCriterion("startTime <", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeLessThanOrEqualTo(Integer value) {
            addCriterion("startTime <=", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeIn(List<Integer> values) {
            addCriterion("startTime in", values, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeNotIn(List<Integer> values) {
            addCriterion("startTime not in", values, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeBetween(Integer value1, Integer value2) {
            addCriterion("startTime between", value1, value2, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeNotBetween(Integer value1, Integer value2) {
            addCriterion("startTime not between", value1, value2, "startTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeIsNull() {
            addCriterion("endTime is null");
            return (Criteria) this;
        }

        public Criteria andEndTimeIsNotNull() {
            addCriterion("endTime is not null");
            return (Criteria) this;
        }

        public Criteria andEndTimeEqualTo(Integer value) {
            addCriterion("endTime =", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotEqualTo(Integer value) {
            addCriterion("endTime <>", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeGreaterThan(Integer value) {
            addCriterion("endTime >", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeGreaterThanOrEqualTo(Integer value) {
            addCriterion("endTime >=", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeLessThan(Integer value) {
            addCriterion("endTime <", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeLessThanOrEqualTo(Integer value) {
            addCriterion("endTime <=", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeIn(List<Integer> values) {
            addCriterion("endTime in", values, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotIn(List<Integer> values) {
            addCriterion("endTime not in", values, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeBetween(Integer value1, Integer value2) {
            addCriterion("endTime between", value1, value2, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotBetween(Integer value1, Integer value2) {
            addCriterion("endTime not between", value1, value2, "endTime");
            return (Criteria) this;
        }

        public Criteria andCreditListFileIsNull() {
            addCriterion("creditListFile is null");
            return (Criteria) this;
        }

        public Criteria andCreditListFileIsNotNull() {
            addCriterion("creditListFile is not null");
            return (Criteria) this;
        }

        public Criteria andCreditListFileEqualTo(String value) {
            addCriterion("creditListFile =", value, "creditListFile");
            return (Criteria) this;
        }

        public Criteria andCreditListFileNotEqualTo(String value) {
            addCriterion("creditListFile <>", value, "creditListFile");
            return (Criteria) this;
        }

        public Criteria andCreditListFileGreaterThan(String value) {
            addCriterion("creditListFile >", value, "creditListFile");
            return (Criteria) this;
        }

        public Criteria andCreditListFileGreaterThanOrEqualTo(String value) {
            addCriterion("creditListFile >=", value, "creditListFile");
            return (Criteria) this;
        }

        public Criteria andCreditListFileLessThan(String value) {
            addCriterion("creditListFile <", value, "creditListFile");
            return (Criteria) this;
        }

        public Criteria andCreditListFileLessThanOrEqualTo(String value) {
            addCriterion("creditListFile <=", value, "creditListFile");
            return (Criteria) this;
        }

        public Criteria andCreditListFileLike(String value) {
            addCriterion("creditListFile like", value, "creditListFile");
            return (Criteria) this;
        }

        public Criteria andCreditListFileNotLike(String value) {
            addCriterion("creditListFile not like", value, "creditListFile");
            return (Criteria) this;
        }

        public Criteria andCreditListFileIn(List<String> values) {
            addCriterion("creditListFile in", values, "creditListFile");
            return (Criteria) this;
        }

        public Criteria andCreditListFileNotIn(List<String> values) {
            addCriterion("creditListFile not in", values, "creditListFile");
            return (Criteria) this;
        }

        public Criteria andCreditListFileBetween(String value1, String value2) {
            addCriterion("creditListFile between", value1, value2, "creditListFile");
            return (Criteria) this;
        }

        public Criteria andCreditListFileNotBetween(String value1, String value2) {
            addCriterion("creditListFile not between", value1, value2, "creditListFile");
            return (Criteria) this;
        }

        public Criteria andGoodsIdListIsNull() {
            addCriterion("goodsIdList is null");
            return (Criteria) this;
        }

        public Criteria andGoodsIdListIsNotNull() {
            addCriterion("goodsIdList is not null");
            return (Criteria) this;
        }

        public Criteria andGoodsIdListEqualTo(String value) {
            addCriterion("goodsIdList =", value, "goodsIdList");
            return (Criteria) this;
        }

        public Criteria andGoodsIdListNotEqualTo(String value) {
            addCriterion("goodsIdList <>", value, "goodsIdList");
            return (Criteria) this;
        }

        public Criteria andGoodsIdListGreaterThan(String value) {
            addCriterion("goodsIdList >", value, "goodsIdList");
            return (Criteria) this;
        }

        public Criteria andGoodsIdListGreaterThanOrEqualTo(String value) {
            addCriterion("goodsIdList >=", value, "goodsIdList");
            return (Criteria) this;
        }

        public Criteria andGoodsIdListLessThan(String value) {
            addCriterion("goodsIdList <", value, "goodsIdList");
            return (Criteria) this;
        }

        public Criteria andGoodsIdListLessThanOrEqualTo(String value) {
            addCriterion("goodsIdList <=", value, "goodsIdList");
            return (Criteria) this;
        }

        public Criteria andGoodsIdListLike(String value) {
            addCriterion("goodsIdList like", value, "goodsIdList");
            return (Criteria) this;
        }

        public Criteria andGoodsIdListNotLike(String value) {
            addCriterion("goodsIdList not like", value, "goodsIdList");
            return (Criteria) this;
        }

        public Criteria andGoodsIdListIn(List<String> values) {
            addCriterion("goodsIdList in", values, "goodsIdList");
            return (Criteria) this;
        }

        public Criteria andGoodsIdListNotIn(List<String> values) {
            addCriterion("goodsIdList not in", values, "goodsIdList");
            return (Criteria) this;
        }

        public Criteria andGoodsIdListBetween(String value1, String value2) {
            addCriterion("goodsIdList between", value1, value2, "goodsIdList");
            return (Criteria) this;
        }

        public Criteria andGoodsIdListNotBetween(String value1, String value2) {
            addCriterion("goodsIdList not between", value1, value2, "goodsIdList");
            return (Criteria) this;
        }

        public Criteria andBannerPathIsNull() {
            addCriterion("bannerPath is null");
            return (Criteria) this;
        }

        public Criteria andBannerPathIsNotNull() {
            addCriterion("bannerPath is not null");
            return (Criteria) this;
        }

        public Criteria andBannerPathEqualTo(String value) {
            addCriterion("bannerPath =", value, "bannerPath");
            return (Criteria) this;
        }

        public Criteria andBannerPathNotEqualTo(String value) {
            addCriterion("bannerPath <>", value, "bannerPath");
            return (Criteria) this;
        }

        public Criteria andBannerPathGreaterThan(String value) {
            addCriterion("bannerPath >", value, "bannerPath");
            return (Criteria) this;
        }

        public Criteria andBannerPathGreaterThanOrEqualTo(String value) {
            addCriterion("bannerPath >=", value, "bannerPath");
            return (Criteria) this;
        }

        public Criteria andBannerPathLessThan(String value) {
            addCriterion("bannerPath <", value, "bannerPath");
            return (Criteria) this;
        }

        public Criteria andBannerPathLessThanOrEqualTo(String value) {
            addCriterion("bannerPath <=", value, "bannerPath");
            return (Criteria) this;
        }

        public Criteria andBannerPathLike(String value) {
            addCriterion("bannerPath like", value, "bannerPath");
            return (Criteria) this;
        }

        public Criteria andBannerPathNotLike(String value) {
            addCriterion("bannerPath not like", value, "bannerPath");
            return (Criteria) this;
        }

        public Criteria andBannerPathIn(List<String> values) {
            addCriterion("bannerPath in", values, "bannerPath");
            return (Criteria) this;
        }

        public Criteria andBannerPathNotIn(List<String> values) {
            addCriterion("bannerPath not in", values, "bannerPath");
            return (Criteria) this;
        }

        public Criteria andBannerPathBetween(String value1, String value2) {
            addCriterion("bannerPath between", value1, value2, "bannerPath");
            return (Criteria) this;
        }

        public Criteria andBannerPathNotBetween(String value1, String value2) {
            addCriterion("bannerPath not between", value1, value2, "bannerPath");
            return (Criteria) this;
        }

        public Criteria andDefaultGoodsIdIsNull() {
            addCriterion("defaultGoodsId is null");
            return (Criteria) this;
        }

        public Criteria andDefaultGoodsIdIsNotNull() {
            addCriterion("defaultGoodsId is not null");
            return (Criteria) this;
        }

        public Criteria andDefaultGoodsIdEqualTo(Long value) {
            addCriterion("defaultGoodsId =", value, "defaultGoodsId");
            return (Criteria) this;
        }

        public Criteria andDefaultGoodsIdNotEqualTo(Long value) {
            addCriterion("defaultGoodsId <>", value, "defaultGoodsId");
            return (Criteria) this;
        }

        public Criteria andDefaultGoodsIdGreaterThan(Long value) {
            addCriterion("defaultGoodsId >", value, "defaultGoodsId");
            return (Criteria) this;
        }

        public Criteria andDefaultGoodsIdGreaterThanOrEqualTo(Long value) {
            addCriterion("defaultGoodsId >=", value, "defaultGoodsId");
            return (Criteria) this;
        }

        public Criteria andDefaultGoodsIdLessThan(Long value) {
            addCriterion("defaultGoodsId <", value, "defaultGoodsId");
            return (Criteria) this;
        }

        public Criteria andDefaultGoodsIdLessThanOrEqualTo(Long value) {
            addCriterion("defaultGoodsId <=", value, "defaultGoodsId");
            return (Criteria) this;
        }

        public Criteria andDefaultGoodsIdIn(List<Long> values) {
            addCriterion("defaultGoodsId in", values, "defaultGoodsId");
            return (Criteria) this;
        }

        public Criteria andDefaultGoodsIdNotIn(List<Long> values) {
            addCriterion("defaultGoodsId not in", values, "defaultGoodsId");
            return (Criteria) this;
        }

        public Criteria andDefaultGoodsIdBetween(Long value1, Long value2) {
            addCriterion("defaultGoodsId between", value1, value2, "defaultGoodsId");
            return (Criteria) this;
        }

        public Criteria andDefaultGoodsIdNotBetween(Long value1, Long value2) {
            addCriterion("defaultGoodsId not between", value1, value2, "defaultGoodsId");
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

        public Criteria andIsEmailEqualTo(Integer value) {
            addCriterion("isEmail =", value, "isEmail");
            return (Criteria) this;
        }

        public Criteria andIsSmsEqualTo(Integer value) {
            addCriterion("isSms =", value, "isSms");
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
        
        public Criteria andIswhitelistIsNull() {
            addCriterion("isWhitelist is null");
            return (Criteria) this;
        }

        public Criteria andIswhitelistIsNotNull() {
            addCriterion("isWhitelist is not null");
            return (Criteria) this;
        }

        public Criteria andIswhitelistEqualTo(Boolean value) {
            addCriterion("isWhitelist =", value, "iswhitelist");
            return (Criteria) this;
        }

        public Criteria andIswhitelistNotEqualTo(Boolean value) {
            addCriterion("isWhitelist <>", value, "iswhitelist");
            return (Criteria) this;
        }

        public Criteria andIswhitelistGreaterThan(Boolean value) {
            addCriterion("isWhitelist >", value, "iswhitelist");
            return (Criteria) this;
        }

        public Criteria andIswhitelistGreaterThanOrEqualTo(Boolean value) {
            addCriterion("isWhitelist >=", value, "iswhitelist");
            return (Criteria) this;
        }

        public Criteria andIswhitelistLessThan(Boolean value) {
            addCriterion("isWhitelist <", value, "iswhitelist");
            return (Criteria) this;
        }

        public Criteria andIswhitelistLessThanOrEqualTo(Boolean value) {
            addCriterion("isWhitelist <=", value, "iswhitelist");
            return (Criteria) this;
        }

        public Criteria andIswhitelistIn(List<Boolean> values) {
            addCriterion("isWhitelist in", values, "iswhitelist");
            return (Criteria) this;
        }

        public Criteria andIswhitelistNotIn(List<Boolean> values) {
            addCriterion("isWhitelist not in", values, "iswhitelist");
            return (Criteria) this;
        }

        public Criteria andIswhitelistBetween(Boolean value1, Boolean value2) {
            addCriterion("isWhitelist between", value1, value2, "iswhitelist");
            return (Criteria) this;
        }

        public Criteria andIswhitelistNotBetween(Boolean value1, Boolean value2) {
            addCriterion("isWhitelist not between", value1, value2, "iswhitelist");
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