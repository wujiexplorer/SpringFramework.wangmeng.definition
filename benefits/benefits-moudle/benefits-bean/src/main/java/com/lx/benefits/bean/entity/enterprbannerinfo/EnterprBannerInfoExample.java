package com.lx.benefits.bean.entity.enterprbannerinfo;

import java.util.ArrayList;
import java.util.List;

public class EnterprBannerInfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public EnterprBannerInfoExample() {
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

        public Criteria andBannerIdIsNull() {
            addCriterion("bannerId is null");
            return (Criteria) this;
        }

        public Criteria andBannerIdIsNotNull() {
            addCriterion("bannerId is not null");
            return (Criteria) this;
        }

        public Criteria andBannerIdEqualTo(Long value) {
            addCriterion("bannerId =", value, "bannerId");
            return (Criteria) this;
        }

        public Criteria andBannerIdNotEqualTo(Long value) {
            addCriterion("bannerId <>", value, "bannerId");
            return (Criteria) this;
        }

        public Criteria andBannerIdGreaterThan(Long value) {
            addCriterion("bannerId >", value, "bannerId");
            return (Criteria) this;
        }

        public Criteria andBannerIdGreaterThanOrEqualTo(Long value) {
            addCriterion("bannerId >=", value, "bannerId");
            return (Criteria) this;
        }

        public Criteria andBannerIdLessThan(Long value) {
            addCriterion("bannerId <", value, "bannerId");
            return (Criteria) this;
        }

        public Criteria andBannerIdLessThanOrEqualTo(Long value) {
            addCriterion("bannerId <=", value, "bannerId");
            return (Criteria) this;
        }

        public Criteria andBannerIdIn(List<Long> values) {
            addCriterion("bannerId in", values, "bannerId");
            return (Criteria) this;
        }

        public Criteria andBannerIdNotIn(List<Long> values) {
            addCriterion("bannerId not in", values, "bannerId");
            return (Criteria) this;
        }

        public Criteria andBannerIdBetween(Long value1, Long value2) {
            addCriterion("bannerId between", value1, value2, "bannerId");
            return (Criteria) this;
        }

        public Criteria andBannerIdNotBetween(Long value1, Long value2) {
            addCriterion("bannerId not between", value1, value2, "bannerId");
            return (Criteria) this;
        }

        public Criteria andCustomIdIsNull() {
            addCriterion("customId is null");
            return (Criteria) this;
        }

        public Criteria andCustomIdIsNotNull() {
            addCriterion("customId is not null");
            return (Criteria) this;
        }

        public Criteria andCustomIdEqualTo(Long value) {
            addCriterion("customId =", value, "customId");
            return (Criteria) this;
        }

        public Criteria andCustomIdNotEqualTo(Long value) {
            addCriterion("customId <>", value, "customId");
            return (Criteria) this;
        }

        public Criteria andCustomIdGreaterThan(Long value) {
            addCriterion("customId >", value, "customId");
            return (Criteria) this;
        }

        public Criteria andCustomIdGreaterThanOrEqualTo(Long value) {
            addCriterion("customId >=", value, "customId");
            return (Criteria) this;
        }

        public Criteria andCustomIdLessThan(Long value) {
            addCriterion("customId <", value, "customId");
            return (Criteria) this;
        }

        public Criteria andCustomIdLessThanOrEqualTo(Long value) {
            addCriterion("customId <=", value, "customId");
            return (Criteria) this;
        }

        public Criteria andCustomIdIn(List<Long> values) {
            addCriterion("customId in", values, "customId");
            return (Criteria) this;
        }

        public Criteria andCustomIdNotIn(List<Long> values) {
            addCriterion("customId not in", values, "customId");
            return (Criteria) this;
        }

        public Criteria andCustomIdBetween(Long value1, Long value2) {
            addCriterion("customId between", value1, value2, "customId");
            return (Criteria) this;
        }

        public Criteria andCustomIdNotBetween(Long value1, Long value2) {
            addCriterion("customId not between", value1, value2, "customId");
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

        public Criteria andLinkUrlIsNull() {
            addCriterion("linkUrl is null");
            return (Criteria) this;
        }

        public Criteria andLinkUrlIsNotNull() {
            addCriterion("linkUrl is not null");
            return (Criteria) this;
        }

        public Criteria andLinkUrlEqualTo(String value) {
            addCriterion("linkUrl =", value, "linkUrl");
            return (Criteria) this;
        }

        public Criteria andLinkUrlNotEqualTo(String value) {
            addCriterion("linkUrl <>", value, "linkUrl");
            return (Criteria) this;
        }

        public Criteria andLinkUrlGreaterThan(String value) {
            addCriterion("linkUrl >", value, "linkUrl");
            return (Criteria) this;
        }

        public Criteria andLinkUrlGreaterThanOrEqualTo(String value) {
            addCriterion("linkUrl >=", value, "linkUrl");
            return (Criteria) this;
        }

        public Criteria andLinkUrlLessThan(String value) {
            addCriterion("linkUrl <", value, "linkUrl");
            return (Criteria) this;
        }

        public Criteria andLinkUrlLessThanOrEqualTo(String value) {
            addCriterion("linkUrl <=", value, "linkUrl");
            return (Criteria) this;
        }

        public Criteria andLinkUrlLike(String value) {
            addCriterion("linkUrl like", value, "linkUrl");
            return (Criteria) this;
        }

        public Criteria andLinkUrlNotLike(String value) {
            addCriterion("linkUrl not like", value, "linkUrl");
            return (Criteria) this;
        }

        public Criteria andLinkUrlIn(List<String> values) {
            addCriterion("linkUrl in", values, "linkUrl");
            return (Criteria) this;
        }

        public Criteria andLinkUrlNotIn(List<String> values) {
            addCriterion("linkUrl not in", values, "linkUrl");
            return (Criteria) this;
        }

        public Criteria andLinkUrlBetween(String value1, String value2) {
            addCriterion("linkUrl between", value1, value2, "linkUrl");
            return (Criteria) this;
        }

        public Criteria andLinkUrlNotBetween(String value1, String value2) {
            addCriterion("linkUrl not between", value1, value2, "linkUrl");
            return (Criteria) this;
        }

        public Criteria andBannerTitleIsNull() {
            addCriterion("bannerTitle is null");
            return (Criteria) this;
        }

        public Criteria andBannerTitleIsNotNull() {
            addCriterion("bannerTitle is not null");
            return (Criteria) this;
        }

        public Criteria andBannerTitleEqualTo(String value) {
            addCriterion("bannerTitle =", value, "bannerTitle");
            return (Criteria) this;
        }

        public Criteria andBannerTitleNotEqualTo(String value) {
            addCriterion("bannerTitle <>", value, "bannerTitle");
            return (Criteria) this;
        }

        public Criteria andBannerTitleGreaterThan(String value) {
            addCriterion("bannerTitle >", value, "bannerTitle");
            return (Criteria) this;
        }

        public Criteria andBannerTitleGreaterThanOrEqualTo(String value) {
            addCriterion("bannerTitle >=", value, "bannerTitle");
            return (Criteria) this;
        }

        public Criteria andBannerTitleLessThan(String value) {
            addCriterion("bannerTitle <", value, "bannerTitle");
            return (Criteria) this;
        }

        public Criteria andBannerTitleLessThanOrEqualTo(String value) {
            addCriterion("bannerTitle <=", value, "bannerTitle");
            return (Criteria) this;
        }

        public Criteria andBannerTitleLike(String value) {
            addCriterion("bannerTitle like", value, "bannerTitle");
            return (Criteria) this;
        }

        public Criteria andBannerTitleNotLike(String value) {
            addCriterion("bannerTitle not like", value, "bannerTitle");
            return (Criteria) this;
        }

        public Criteria andBannerTitleIn(List<String> values) {
            addCriterion("bannerTitle in", values, "bannerTitle");
            return (Criteria) this;
        }

        public Criteria andBannerTitleNotIn(List<String> values) {
            addCriterion("bannerTitle not in", values, "bannerTitle");
            return (Criteria) this;
        }

        public Criteria andBannerTitleBetween(String value1, String value2) {
            addCriterion("bannerTitle between", value1, value2, "bannerTitle");
            return (Criteria) this;
        }

        public Criteria andBannerTitleNotBetween(String value1, String value2) {
            addCriterion("bannerTitle not between", value1, value2, "bannerTitle");
            return (Criteria) this;
        }

        public Criteria andBannerSortIsNull() {
            addCriterion("bannerSort is null");
            return (Criteria) this;
        }

        public Criteria andBannerSortIsNotNull() {
            addCriterion("bannerSort is not null");
            return (Criteria) this;
        }

        public Criteria andBannerSortEqualTo(Integer value) {
            addCriterion("bannerSort =", value, "bannerSort");
            return (Criteria) this;
        }

        public Criteria andBannerSortNotEqualTo(Integer value) {
            addCriterion("bannerSort <>", value, "bannerSort");
            return (Criteria) this;
        }

        public Criteria andBannerSortGreaterThan(Integer value) {
            addCriterion("bannerSort >", value, "bannerSort");
            return (Criteria) this;
        }

        public Criteria andBannerSortGreaterThanOrEqualTo(Integer value) {
            addCriterion("bannerSort >=", value, "bannerSort");
            return (Criteria) this;
        }

        public Criteria andBannerSortLessThan(Integer value) {
            addCriterion("bannerSort <", value, "bannerSort");
            return (Criteria) this;
        }

        public Criteria andBannerSortLessThanOrEqualTo(Integer value) {
            addCriterion("bannerSort <=", value, "bannerSort");
            return (Criteria) this;
        }

        public Criteria andBannerSortIn(List<Integer> values) {
            addCriterion("bannerSort in", values, "bannerSort");
            return (Criteria) this;
        }

        public Criteria andBannerSortNotIn(List<Integer> values) {
            addCriterion("bannerSort not in", values, "bannerSort");
            return (Criteria) this;
        }

        public Criteria andBannerSortBetween(Integer value1, Integer value2) {
            addCriterion("bannerSort between", value1, value2, "bannerSort");
            return (Criteria) this;
        }

        public Criteria andBannerSortNotBetween(Integer value1, Integer value2) {
            addCriterion("bannerSort not between", value1, value2, "bannerSort");
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


        //isshow start
        public Criteria andIsShowIsNull() {
            addCriterion("isShow is null");
            return (Criteria) this;
        }

        public Criteria andIsShowIsNotNull() {
            addCriterion("isShow is not null");
            return (Criteria) this;
        }

        public Criteria andIsShowEqualTo(Integer value) {
            addCriterion("isShow =", value, "isShow");
            return (Criteria) this;
        }

        public Criteria andIsShowNotEqualTo(Integer value) {
            addCriterion("isShow <>", value, "isShow");
            return (Criteria) this;
        }

        public Criteria andIsShowGreaterThan(Integer value) {
            addCriterion("isShow >", value, "isShow");
            return (Criteria) this;
        }

        public Criteria andIsShowGreaterThanOrEqualTo(Integer value) {
            addCriterion("isShow >=", value, "isShow");
            return (Criteria) this;
        }

        public Criteria andIsShowLessThan(Integer value) {
            addCriterion("isShow <", value, "isShow");
            return (Criteria) this;
        }

        public Criteria andIsShowLessThanOrEqualTo(Integer value) {
            addCriterion("isShow <=", value, "isShow");
            return (Criteria) this;
        }

        public Criteria andIsShowIn(List<Integer> values) {
            addCriterion("isShow in", values, "isShow");
            return (Criteria) this;
        }

        public Criteria andIsShowNotIn(List<Integer> values) {
            addCriterion("isShow not in", values, "isShow");
            return (Criteria) this;
        }

        public Criteria andIsShowBetween(Integer value1, Integer value2) {
            addCriterion("isShow between", value1, value2, "isShow");
            return (Criteria) this;
        }

        public Criteria andIsShowNotBetween(Integer value1, Integer value2) {
            addCriterion("isShow not between", value1, value2, "isDeleted");
            return (Criteria) this;
        }
        //isshow end






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

        public Criteria andTypeEqualTo(Integer value) {
            addCriterion("type =", value, "type");
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