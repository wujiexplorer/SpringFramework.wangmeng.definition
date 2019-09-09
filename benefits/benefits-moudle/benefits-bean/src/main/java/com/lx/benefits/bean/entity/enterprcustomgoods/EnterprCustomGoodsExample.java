package com.lx.benefits.bean.entity.enterprcustomgoods;

import java.util.ArrayList;
import java.util.List;

public class EnterprCustomGoodsExample {
    protected String orderByClause;

    protected boolean distinct;

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

    protected List<Criteria> oredCriteria;

    public EnterprCustomGoodsExample() {
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

        public Criteria andHotGoodsIdListIsNull() {
            addCriterion("hotGoodsIdList is null");
            return (Criteria) this;
        }

        public Criteria andHotGoodsIdListIsNotNull() {
            addCriterion("hotGoodsIdList is not null");
            return (Criteria) this;
        }

        public Criteria andHotGoodsIdListEqualTo(String value) {
            addCriterion("hotGoodsIdList =", value, "hotGoodsIdList");
            return (Criteria) this;
        }

        public Criteria andHotGoodsIdListNotEqualTo(String value) {
            addCriterion("hotGoodsIdList <>", value, "hotGoodsIdList");
            return (Criteria) this;
        }

        public Criteria andHotGoodsIdListGreaterThan(String value) {
            addCriterion("hotGoodsIdList >", value, "hotGoodsIdList");
            return (Criteria) this;
        }

        public Criteria andHotGoodsIdListGreaterThanOrEqualTo(String value) {
            addCriterion("hotGoodsIdList >=", value, "hotGoodsIdList");
            return (Criteria) this;
        }

        public Criteria andHotGoodsIdListLessThan(String value) {
            addCriterion("hotGoodsIdList <", value, "hotGoodsIdList");
            return (Criteria) this;
        }

        public Criteria andHotGoodsIdListLessThanOrEqualTo(String value) {
            addCriterion("hotGoodsIdList <=", value, "hotGoodsIdList");
            return (Criteria) this;
        }

        public Criteria andHotGoodsIdListLike(String value) {
            addCriterion("hotGoodsIdList like", value, "hotGoodsIdList");
            return (Criteria) this;
        }

        public Criteria andHotGoodsIdListNotLike(String value) {
            addCriterion("hotGoodsIdList not like", value, "hotGoodsIdList");
            return (Criteria) this;
        }

        public Criteria andHotGoodsIdListIn(List<String> values) {
            addCriterion("hotGoodsIdList in", values, "hotGoodsIdList");
            return (Criteria) this;
        }

        public Criteria andHotGoodsIdListNotIn(List<String> values) {
            addCriterion("hotGoodsIdList not in", values, "hotGoodsIdList");
            return (Criteria) this;
        }

        public Criteria andHotGoodsIdListBetween(String value1, String value2) {
            addCriterion("hotGoodsIdList between", value1, value2, "hotGoodsIdList");
            return (Criteria) this;
        }

        public Criteria andHotGoodsIdListNotBetween(String value1, String value2) {
            addCriterion("hotGoodsIdList not between", value1, value2, "hotGoodsIdList");
            return (Criteria) this;
        }

        public Criteria andExclusionGoodsIdListIsNull() {
            addCriterion("exclusionGoodsIdList is null");
            return (Criteria) this;
        }

        public Criteria andExclusionGoodsIdListIsNotNull() {
            addCriterion("exclusionGoodsIdList is not null");
            return (Criteria) this;
        }

        public Criteria andExclusionGoodsIdListEqualTo(String value) {
            addCriterion("exclusionGoodsIdList =", value, "exclusionGoodsIdList");
            return (Criteria) this;
        }

        public Criteria andExclusionGoodsIdListNotEqualTo(String value) {
            addCriterion("exclusionGoodsIdList <>", value, "exclusionGoodsIdList");
            return (Criteria) this;
        }

        public Criteria andExclusionGoodsIdListGreaterThan(String value) {
            addCriterion("exclusionGoodsIdList >", value, "exclusionGoodsIdList");
            return (Criteria) this;
        }

        public Criteria andExclusionGoodsIdListGreaterThanOrEqualTo(String value) {
            addCriterion("exclusionGoodsIdList >=", value, "exclusionGoodsIdList");
            return (Criteria) this;
        }

        public Criteria andExclusionGoodsIdListLessThan(String value) {
            addCriterion("exclusionGoodsIdList <", value, "exclusionGoodsIdList");
            return (Criteria) this;
        }

        public Criteria andExclusionGoodsIdListLessThanOrEqualTo(String value) {
            addCriterion("exclusionGoodsIdList <=", value, "exclusionGoodsIdList");
            return (Criteria) this;
        }

        public Criteria andExclusionGoodsIdListLike(String value) {
            addCriterion("exclusionGoodsIdList like", value, "exclusionGoodsIdList");
            return (Criteria) this;
        }

        public Criteria andExclusionGoodsIdListNotLike(String value) {
            addCriterion("exclusionGoodsIdList not like", value, "exclusionGoodsIdList");
            return (Criteria) this;
        }

        public Criteria andExclusionGoodsIdListIn(List<String> values) {
            addCriterion("exclusionGoodsIdList in", values, "exclusionGoodsIdList");
            return (Criteria) this;
        }

        public Criteria andExclusionGoodsIdListNotIn(List<String> values) {
            addCriterion("exclusionGoodsIdList not in", values, "exclusionGoodsIdList");
            return (Criteria) this;
        }

        public Criteria andExclusionGoodsIdListBetween(String value1, String value2) {
            addCriterion("exclusionGoodsIdList between", value1, value2, "exclusionGoodsIdList");
            return (Criteria) this;
        }

        public Criteria andExclusionGoodsIdListNotBetween(String value1, String value2) {
            addCriterion("exclusionGoodsIdList not between", value1, value2, "exclusionGoodsIdList");
            return (Criteria) this;
        }

        public Criteria andExclusionCateIdListIsNull() {
            addCriterion("exclusionCateIdList is null");
            return (Criteria) this;
        }

        public Criteria andExclusionCateIdListIsNotNull() {
            addCriterion("exclusionCateIdList is not null");
            return (Criteria) this;
        }

        public Criteria andExclusionCateIdListEqualTo(String value) {
            addCriterion("exclusionCateIdList =", value, "exclusionCateIdList");
            return (Criteria) this;
        }

        public Criteria andExclusionCateIdListNotEqualTo(String value) {
            addCriterion("exclusionCateIdList <>", value, "exclusionCateIdList");
            return (Criteria) this;
        }

        public Criteria andExclusionCateIdListGreaterThan(String value) {
            addCriterion("exclusionCateIdList >", value, "exclusionCateIdList");
            return (Criteria) this;
        }

        public Criteria andExclusionCateIdListGreaterThanOrEqualTo(String value) {
            addCriterion("exclusionCateIdList >=", value, "exclusionCateIdList");
            return (Criteria) this;
        }

        public Criteria andExclusionCateIdListLessThan(String value) {
            addCriterion("exclusionCateIdList <", value, "exclusionCateIdList");
            return (Criteria) this;
        }

        public Criteria andExclusionCateIdListLessThanOrEqualTo(String value) {
            addCriterion("exclusionCateIdList <=", value, "exclusionCateIdList");
            return (Criteria) this;
        }

        public Criteria andExclusionCateIdListLike(String value) {
            addCriterion("exclusionCateIdList like", value, "exclusionCateIdList");
            return (Criteria) this;
        }

        public Criteria andExclusionCateIdListNotLike(String value) {
            addCriterion("exclusionCateIdList not like", value, "exclusionCateIdList");
            return (Criteria) this;
        }

        public Criteria andExclusionCateIdListIn(List<String> values) {
            addCriterion("exclusionCateIdList in", values, "exclusionCateIdList");
            return (Criteria) this;
        }

        public Criteria andExclusionCateIdListNotIn(List<String> values) {
            addCriterion("exclusionCateIdList not in", values, "exclusionCateIdList");
            return (Criteria) this;
        }

        public Criteria andExclusionCateIdListBetween(String value1, String value2) {
            addCriterion("exclusionCateIdList between", value1, value2, "exclusionCateIdList");
            return (Criteria) this;
        }

        public Criteria andExclusionCateIdListNotBetween(String value1, String value2) {
            addCriterion("exclusionCateIdList not between", value1, value2, "exclusionCateIdList");
            return (Criteria) this;
        }

        public Criteria andExclusionSupplierIdListIsNull() {
            addCriterion("exclusionSupplierIdList is null");
            return (Criteria) this;
        }

        public Criteria andExclusionSupplierIdListIsNotNull() {
            addCriterion("exclusionSupplierIdList is not null");
            return (Criteria) this;
        }

        public Criteria andExclusionSupplierIdListEqualTo(String value) {
            addCriterion("exclusionSupplierIdList =", value, "exclusionSupplierIdList");
            return (Criteria) this;
        }

        public Criteria andExclusionSupplierIdListNotEqualTo(String value) {
            addCriterion("exclusionSupplierIdList <>", value, "exclusionSupplierIdList");
            return (Criteria) this;
        }

        public Criteria andExclusionSupplierIdListGreaterThan(String value) {
            addCriterion("exclusionSupplierIdList >", value, "exclusionSupplierIdList");
            return (Criteria) this;
        }

        public Criteria andExclusionSupplierIdListGreaterThanOrEqualTo(String value) {
            addCriterion("exclusionSupplierIdList >=", value, "exclusionSupplierIdList");
            return (Criteria) this;
        }

        public Criteria andExclusionSupplierIdListLessThan(String value) {
            addCriterion("exclusionSupplierIdList <", value, "exclusionSupplierIdList");
            return (Criteria) this;
        }

        public Criteria andExclusionSupplierIdListLessThanOrEqualTo(String value) {
            addCriterion("exclusionSupplierIdList <=", value, "exclusionSupplierIdList");
            return (Criteria) this;
        }

        public Criteria andExclusionSupplierIdListLike(String value) {
            addCriterion("exclusionSupplierIdList like", value, "exclusionSupplierIdList");
            return (Criteria) this;
        }

        public Criteria andExclusionSupplierIdListNotLike(String value) {
            addCriterion("exclusionSupplierIdList not like", value, "exclusionSupplierIdList");
            return (Criteria) this;
        }

        public Criteria andExclusionSupplierIdListIn(List<String> values) {
            addCriterion("exclusionSupplierIdList in", values, "exclusionSupplierIdList");
            return (Criteria) this;
        }

        public Criteria andExclusionSupplierIdListNotIn(List<String> values) {
            addCriterion("exclusionSupplierIdList not in", values, "exclusionSupplierIdList");
            return (Criteria) this;
        }

        public Criteria andExclusionSupplierIdListBetween(String value1, String value2) {
            addCriterion("exclusionSupplierIdList between", value1, value2, "exclusionSupplierIdList");
            return (Criteria) this;
        }

        public Criteria andExclusionSupplierIdListNotBetween(String value1, String value2) {
            addCriterion("exclusionSupplierIdList not between", value1, value2, "exclusionSupplierIdList");
            return (Criteria) this;
        }

        public Criteria andExclusionGoodsTypeListIsNull() {
            addCriterion("exclusionGoodsTypeList is null");
            return (Criteria) this;
        }

        public Criteria andExclusionGoodsTypeListIsNotNull() {
            addCriterion("exclusionGoodsTypeList is not null");
            return (Criteria) this;
        }

        public Criteria andExclusionGoodsTypeListEqualTo(String value) {
            addCriterion("exclusionGoodsTypeList =", value, "exclusionGoodsTypeList");
            return (Criteria) this;
        }

        public Criteria andExclusionGoodsTypeListNotEqualTo(String value) {
            addCriterion("exclusionGoodsTypeList <>", value, "exclusionGoodsTypeList");
            return (Criteria) this;
        }

        public Criteria andExclusionGoodsTypeListGreaterThan(String value) {
            addCriterion("exclusionGoodsTypeList >", value, "exclusionGoodsTypeList");
            return (Criteria) this;
        }

        public Criteria andExclusionGoodsTypeListGreaterThanOrEqualTo(String value) {
            addCriterion("exclusionGoodsTypeList >=", value, "exclusionGoodsTypeList");
            return (Criteria) this;
        }

        public Criteria andExclusionGoodsTypeListLessThan(String value) {
            addCriterion("exclusionGoodsTypeList <", value, "exclusionGoodsTypeList");
            return (Criteria) this;
        }

        public Criteria andExclusionGoodsTypeListLessThanOrEqualTo(String value) {
            addCriterion("exclusionGoodsTypeList <=", value, "exclusionGoodsTypeList");
            return (Criteria) this;
        }

        public Criteria andExclusionGoodsTypeListLike(String value) {
            addCriterion("exclusionGoodsTypeList like", value, "exclusionGoodsTypeList");
            return (Criteria) this;
        }

        public Criteria andExclusionGoodsTypeListNotLike(String value) {
            addCriterion("exclusionGoodsTypeList not like", value, "exclusionGoodsTypeList");
            return (Criteria) this;
        }

        public Criteria andExclusionGoodsTypeListIn(List<String> values) {
            addCriterion("exclusionGoodsTypeList in", values, "exclusionGoodsTypeList");
            return (Criteria) this;
        }

        public Criteria andExclusionGoodsTypeListNotIn(List<String> values) {
            addCriterion("exclusionGoodsTypeList not in", values, "exclusionGoodsTypeList");
            return (Criteria) this;
        }

        public Criteria andExclusionGoodsTypeListBetween(String value1, String value2) {
            addCriterion("exclusionGoodsTypeList between", value1, value2, "exclusionGoodsTypeList");
            return (Criteria) this;
        }

        public Criteria andExclusionGoodsTypeListNotBetween(String value1, String value2) {
            addCriterion("exclusionGoodsTypeList not between", value1, value2, "exclusionGoodsTypeList");
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