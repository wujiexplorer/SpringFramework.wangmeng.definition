package com.lx.benefits.bean.entity.prditeminfo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ItemInfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ItemInfoExample() {
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

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andSupplier_idIsNull() {
            addCriterion("supplier_id is null");
            return (Criteria) this;
        }

        public Criteria andSupplier_idIsNotNull() {
            addCriterion("supplier_id is not null");
            return (Criteria) this;
        }

        public Criteria andSupplier_idEqualTo(Long value) {
            addCriterion("supplier_id =", value, "supplier_id");
            return (Criteria) this;
        }

        public Criteria andSupplier_idNotEqualTo(Long value) {
            addCriterion("supplier_id <>", value, "supplier_id");
            return (Criteria) this;
        }

        public Criteria andSupplier_idGreaterThan(Long value) {
            addCriterion("supplier_id >", value, "supplier_id");
            return (Criteria) this;
        }

        public Criteria andSupplier_idGreaterThanOrEqualTo(Long value) {
            addCriterion("supplier_id >=", value, "supplier_id");
            return (Criteria) this;
        }

        public Criteria andSupplier_idLessThan(Long value) {
            addCriterion("supplier_id <", value, "supplier_id");
            return (Criteria) this;
        }

        public Criteria andSupplier_idLessThanOrEqualTo(Long value) {
            addCriterion("supplier_id <=", value, "supplier_id");
            return (Criteria) this;
        }

        public Criteria andSupplier_idIn(List<Long> values) {
            addCriterion("supplier_id in", values, "supplier_id");
            return (Criteria) this;
        }

        public Criteria andSupplier_idNotIn(List<Long> values) {
            addCriterion("supplier_id not in", values, "supplier_id");
            return (Criteria) this;
        }

        public Criteria andSupplier_idBetween(Long value1, Long value2) {
            addCriterion("supplier_id between", value1, value2, "supplier_id");
            return (Criteria) this;
        }

        public Criteria andSupplier_idNotBetween(Long value1, Long value2) {
            addCriterion("supplier_id not between", value1, value2, "supplier_id");
            return (Criteria) this;
        }

        public Criteria andSpuIsNull() {
            addCriterion("spu is null");
            return (Criteria) this;
        }

        public Criteria andSpuIsNotNull() {
            addCriterion("spu is not null");
            return (Criteria) this;
        }

        public Criteria andSpuEqualTo(String value) {
            addCriterion("spu =", value, "spu");
            return (Criteria) this;
        }

        public Criteria andSpuNotEqualTo(String value) {
            addCriterion("spu <>", value, "spu");
            return (Criteria) this;
        }

        public Criteria andSpuGreaterThan(String value) {
            addCriterion("spu >", value, "spu");
            return (Criteria) this;
        }

        public Criteria andSpuGreaterThanOrEqualTo(String value) {
            addCriterion("spu >=", value, "spu");
            return (Criteria) this;
        }

        public Criteria andSpuLessThan(String value) {
            addCriterion("spu <", value, "spu");
            return (Criteria) this;
        }

        public Criteria andSpuLessThanOrEqualTo(String value) {
            addCriterion("spu <=", value, "spu");
            return (Criteria) this;
        }

        public Criteria andSpuLike(String value) {
            addCriterion("spu like", value, "spu");
            return (Criteria) this;
        }

        public Criteria andSpuNotLike(String value) {
            addCriterion("spu not like", value, "spu");
            return (Criteria) this;
        }

        public Criteria andSpuIn(List<String> values) {
            addCriterion("spu in", values, "spu");
            return (Criteria) this;
        }

        public Criteria andSpuNotIn(List<String> values) {
            addCriterion("spu not in", values, "spu");
            return (Criteria) this;
        }

        public Criteria andSpuBetween(String value1, String value2) {
            addCriterion("spu between", value1, value2, "spu");
            return (Criteria) this;
        }

        public Criteria andSpuNotBetween(String value1, String value2) {
            addCriterion("spu not between", value1, value2, "spu");
            return (Criteria) this;
        }

        public Criteria andMain_titleIsNull() {
            addCriterion("main_title is null");
            return (Criteria) this;
        }

        public Criteria andMain_titleIsNotNull() {
            addCriterion("main_title is not null");
            return (Criteria) this;
        }

        public Criteria andMain_titleEqualTo(String value) {
            addCriterion("main_title =", value, "main_title");
            return (Criteria) this;
        }

        public Criteria andMain_titleNotEqualTo(String value) {
            addCriterion("main_title <>", value, "main_title");
            return (Criteria) this;
        }

        public Criteria andMain_titleGreaterThan(String value) {
            addCriterion("main_title >", value, "main_title");
            return (Criteria) this;
        }

        public Criteria andMain_titleGreaterThanOrEqualTo(String value) {
            addCriterion("main_title >=", value, "main_title");
            return (Criteria) this;
        }

        public Criteria andMain_titleLessThan(String value) {
            addCriterion("main_title <", value, "main_title");
            return (Criteria) this;
        }

        public Criteria andMain_titleLessThanOrEqualTo(String value) {
            addCriterion("main_title <=", value, "main_title");
            return (Criteria) this;
        }

        public Criteria andMain_titleLike(String value) {
            addCriterion("main_title like", value, "main_title");
            return (Criteria) this;
        }

        public Criteria andMain_titleNotLike(String value) {
            addCriterion("main_title not like", value, "main_title");
            return (Criteria) this;
        }

        public Criteria andMain_titleIn(List<String> values) {
            addCriterion("main_title in", values, "main_title");
            return (Criteria) this;
        }

        public Criteria andMain_titleNotIn(List<String> values) {
            addCriterion("main_title not in", values, "main_title");
            return (Criteria) this;
        }

        public Criteria andMain_titleBetween(String value1, String value2) {
            addCriterion("main_title between", value1, value2, "main_title");
            return (Criteria) this;
        }

        public Criteria andMain_titleNotBetween(String value1, String value2) {
            addCriterion("main_title not between", value1, value2, "main_title");
            return (Criteria) this;
        }

        public Criteria andBrand_idIsNull() {
            addCriterion("brand_id is null");
            return (Criteria) this;
        }

        public Criteria andBrand_idIsNotNull() {
            addCriterion("brand_id is not null");
            return (Criteria) this;
        }

        public Criteria andBrand_idEqualTo(Integer value) {
            addCriterion("brand_id =", value, "brand_id");
            return (Criteria) this;
        }

        public Criteria andBrand_idNotEqualTo(Integer value) {
            addCriterion("brand_id <>", value, "brand_id");
            return (Criteria) this;
        }

        public Criteria andBrand_idGreaterThan(Integer value) {
            addCriterion("brand_id >", value, "brand_id");
            return (Criteria) this;
        }

        public Criteria andBrand_idGreaterThanOrEqualTo(Integer value) {
            addCriterion("brand_id >=", value, "brand_id");
            return (Criteria) this;
        }

        public Criteria andBrand_idLessThan(Integer value) {
            addCriterion("brand_id <", value, "brand_id");
            return (Criteria) this;
        }

        public Criteria andBrand_idLessThanOrEqualTo(Integer value) {
            addCriterion("brand_id <=", value, "brand_id");
            return (Criteria) this;
        }

        public Criteria andBrand_idIn(List<Integer> values) {
            addCriterion("brand_id in", values, "brand_id");
            return (Criteria) this;
        }

        public Criteria andBrand_idNotIn(List<Integer> values) {
            addCriterion("brand_id not in", values, "brand_id");
            return (Criteria) this;
        }

        public Criteria andBrand_idBetween(Integer value1, Integer value2) {
            addCriterion("brand_id between", value1, value2, "brand_id");
            return (Criteria) this;
        }

        public Criteria andBrand_idNotBetween(Integer value1, Integer value2) {
            addCriterion("brand_id not between", value1, value2, "brand_id");
            return (Criteria) this;
        }

        public Criteria andLarge_idIsNull() {
            addCriterion("large_id is null");
            return (Criteria) this;
        }

        public Criteria andLarge_idIsNotNull() {
            addCriterion("large_id is not null");
            return (Criteria) this;
        }

        public Criteria andLarge_idEqualTo(Integer value) {
            addCriterion("large_id =", value, "large_id");
            return (Criteria) this;
        }

        public Criteria andLarge_idNotEqualTo(Integer value) {
            addCriterion("large_id <>", value, "large_id");
            return (Criteria) this;
        }

        public Criteria andLarge_idGreaterThan(Integer value) {
            addCriterion("large_id >", value, "large_id");
            return (Criteria) this;
        }

        public Criteria andLarge_idGreaterThanOrEqualTo(Integer value) {
            addCriterion("large_id >=", value, "large_id");
            return (Criteria) this;
        }

        public Criteria andLarge_idLessThan(Integer value) {
            addCriterion("large_id <", value, "large_id");
            return (Criteria) this;
        }

        public Criteria andLarge_idLessThanOrEqualTo(Integer value) {
            addCriterion("large_id <=", value, "large_id");
            return (Criteria) this;
        }

        public Criteria andLarge_idIn(List<Integer> values) {
            addCriterion("large_id in", values, "large_id");
            return (Criteria) this;
        }

        public Criteria andLarge_idNotIn(List<Integer> values) {
            addCriterion("large_id not in", values, "large_id");
            return (Criteria) this;
        }

        public Criteria andLarge_idBetween(Integer value1, Integer value2) {
            addCriterion("large_id between", value1, value2, "large_id");
            return (Criteria) this;
        }

        public Criteria andLarge_idNotBetween(Integer value1, Integer value2) {
            addCriterion("large_id not between", value1, value2, "large_id");
            return (Criteria) this;
        }

        public Criteria andMedium_idIsNull() {
            addCriterion("medium_id is null");
            return (Criteria) this;
        }

        public Criteria andMedium_idIsNotNull() {
            addCriterion("medium_id is not null");
            return (Criteria) this;
        }

        public Criteria andMedium_idEqualTo(Integer value) {
            addCriterion("medium_id =", value, "medium_id");
            return (Criteria) this;
        }

        public Criteria andMedium_idNotEqualTo(Integer value) {
            addCriterion("medium_id <>", value, "medium_id");
            return (Criteria) this;
        }

        public Criteria andMedium_idGreaterThan(Integer value) {
            addCriterion("medium_id >", value, "medium_id");
            return (Criteria) this;
        }

        public Criteria andMedium_idGreaterThanOrEqualTo(Integer value) {
            addCriterion("medium_id >=", value, "medium_id");
            return (Criteria) this;
        }

        public Criteria andMedium_idLessThan(Integer value) {
            addCriterion("medium_id <", value, "medium_id");
            return (Criteria) this;
        }

        public Criteria andMedium_idLessThanOrEqualTo(Integer value) {
            addCriterion("medium_id <=", value, "medium_id");
            return (Criteria) this;
        }

        public Criteria andMedium_idIn(List<Integer> values) {
            addCriterion("medium_id in", values, "medium_id");
            return (Criteria) this;
        }

        public Criteria andMedium_idNotIn(List<Integer> values) {
            addCriterion("medium_id not in", values, "medium_id");
            return (Criteria) this;
        }

        public Criteria andMedium_idBetween(Integer value1, Integer value2) {
            addCriterion("medium_id between", value1, value2, "medium_id");
            return (Criteria) this;
        }

        public Criteria andMedium_idNotBetween(Integer value1, Integer value2) {
            addCriterion("medium_id not between", value1, value2, "medium_id");
            return (Criteria) this;
        }

        public Criteria andSmall_idIsNull() {
            addCriterion("small_id is null");
            return (Criteria) this;
        }

        public Criteria andSmall_idIsNotNull() {
            addCriterion("small_id is not null");
            return (Criteria) this;
        }

        public Criteria andSmall_idEqualTo(Integer value) {
            addCriterion("small_id =", value, "small_id");
            return (Criteria) this;
        }

        public Criteria andSmall_idNotEqualTo(Integer value) {
            addCriterion("small_id <>", value, "small_id");
            return (Criteria) this;
        }

        public Criteria andSmall_idGreaterThan(Integer value) {
            addCriterion("small_id >", value, "small_id");
            return (Criteria) this;
        }

        public Criteria andSmall_idGreaterThanOrEqualTo(Integer value) {
            addCriterion("small_id >=", value, "small_id");
            return (Criteria) this;
        }

        public Criteria andSmall_idLessThan(Integer value) {
            addCriterion("small_id <", value, "small_id");
            return (Criteria) this;
        }

        public Criteria andSmall_idLessThanOrEqualTo(Integer value) {
            addCriterion("small_id <=", value, "small_id");
            return (Criteria) this;
        }

        public Criteria andSmall_idIn(List<Integer> values) {
            addCriterion("small_id in", values, "small_id");
            return (Criteria) this;
        }

        public Criteria andSmall_idNotIn(List<Integer> values) {
            addCriterion("small_id not in", values, "small_id");
            return (Criteria) this;
        }

        public Criteria andSmall_idBetween(Integer value1, Integer value2) {
            addCriterion("small_id between", value1, value2, "small_id");
            return (Criteria) this;
        }

        public Criteria andSmall_idNotBetween(Integer value1, Integer value2) {
            addCriterion("small_id not between", value1, value2, "small_id");
            return (Criteria) this;
        }

        public Criteria andUnit_idIsNull() {
            addCriterion("unit_id is null");
            return (Criteria) this;
        }

        public Criteria andUnit_idIsNotNull() {
            addCriterion("unit_id is not null");
            return (Criteria) this;
        }

        public Criteria andUnit_idEqualTo(Integer value) {
            addCriterion("unit_id =", value, "unit_id");
            return (Criteria) this;
        }

        public Criteria andUnit_idNotEqualTo(Integer value) {
            addCriterion("unit_id <>", value, "unit_id");
            return (Criteria) this;
        }

        public Criteria andUnit_idGreaterThan(Integer value) {
            addCriterion("unit_id >", value, "unit_id");
            return (Criteria) this;
        }

        public Criteria andUnit_idGreaterThanOrEqualTo(Integer value) {
            addCriterion("unit_id >=", value, "unit_id");
            return (Criteria) this;
        }

        public Criteria andUnit_idLessThan(Integer value) {
            addCriterion("unit_id <", value, "unit_id");
            return (Criteria) this;
        }

        public Criteria andUnit_idLessThanOrEqualTo(Integer value) {
            addCriterion("unit_id <=", value, "unit_id");
            return (Criteria) this;
        }

        public Criteria andUnit_idIn(List<Integer> values) {
            addCriterion("unit_id in", values, "unit_id");
            return (Criteria) this;
        }

        public Criteria andUnit_idNotIn(List<Integer> values) {
            addCriterion("unit_id not in", values, "unit_id");
            return (Criteria) this;
        }

        public Criteria andUnit_idBetween(Integer value1, Integer value2) {
            addCriterion("unit_id between", value1, value2, "unit_id");
            return (Criteria) this;
        }

        public Criteria andUnit_idNotBetween(Integer value1, Integer value2) {
            addCriterion("unit_id not between", value1, value2, "unit_id");
            return (Criteria) this;
        }

        public Criteria andBind_levelIsNull() {
            addCriterion("bind_level is null");
            return (Criteria) this;
        }

        public Criteria andBind_levelIsNotNull() {
            addCriterion("bind_level is not null");
            return (Criteria) this;
        }

        public Criteria andBind_levelEqualTo(String value) {
            addCriterion("bind_level =", value, "bind_level");
            return (Criteria) this;
        }

        public Criteria andBind_levelNotEqualTo(String value) {
            addCriterion("bind_level <>", value, "bind_level");
            return (Criteria) this;
        }

        public Criteria andBind_levelGreaterThan(String value) {
            addCriterion("bind_level >", value, "bind_level");
            return (Criteria) this;
        }

        public Criteria andBind_levelGreaterThanOrEqualTo(String value) {
            addCriterion("bind_level >=", value, "bind_level");
            return (Criteria) this;
        }

        public Criteria andBind_levelLessThan(String value) {
            addCriterion("bind_level <", value, "bind_level");
            return (Criteria) this;
        }

        public Criteria andBind_levelLessThanOrEqualTo(String value) {
            addCriterion("bind_level <=", value, "bind_level");
            return (Criteria) this;
        }

        public Criteria andBind_levelLike(String value) {
            addCriterion("bind_level like", value, "bind_level");
            return (Criteria) this;
        }

        public Criteria andBind_levelNotLike(String value) {
            addCriterion("bind_level not like", value, "bind_level");
            return (Criteria) this;
        }

        public Criteria andBind_levelIn(List<String> values) {
            addCriterion("bind_level in", values, "bind_level");
            return (Criteria) this;
        }

        public Criteria andBind_levelNotIn(List<String> values) {
            addCriterion("bind_level not in", values, "bind_level");
            return (Criteria) this;
        }

        public Criteria andBind_levelBetween(String value1, String value2) {
            addCriterion("bind_level between", value1, value2, "bind_level");
            return (Criteria) this;
        }

        public Criteria andBind_levelNotBetween(String value1, String value2) {
            addCriterion("bind_level not between", value1, value2, "bind_level");
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

        public Criteria andCreate_timeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreate_timeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreate_timeEqualTo(Date value) {
            addCriterion("create_time =", value, "create_time");
            return (Criteria) this;
        }

        public Criteria andCreate_timeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "create_time");
            return (Criteria) this;
        }

        public Criteria andCreate_timeGreaterThan(Date value) {
            addCriterion("create_time >", value, "create_time");
            return (Criteria) this;
        }

        public Criteria andCreate_timeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "create_time");
            return (Criteria) this;
        }

        public Criteria andCreate_timeLessThan(Date value) {
            addCriterion("create_time <", value, "create_time");
            return (Criteria) this;
        }

        public Criteria andCreate_timeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "create_time");
            return (Criteria) this;
        }

        public Criteria andCreate_timeIn(List<Date> values) {
            addCriterion("create_time in", values, "create_time");
            return (Criteria) this;
        }

        public Criteria andCreate_timeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "create_time");
            return (Criteria) this;
        }

        public Criteria andCreate_timeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "create_time");
            return (Criteria) this;
        }

        public Criteria andCreate_timeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "create_time");
            return (Criteria) this;
        }

        public Criteria andCreate_userIsNull() {
            addCriterion("create_user is null");
            return (Criteria) this;
        }

        public Criteria andCreate_userIsNotNull() {
            addCriterion("create_user is not null");
            return (Criteria) this;
        }

        public Criteria andCreate_userEqualTo(String value) {
            addCriterion("create_user =", value, "create_user");
            return (Criteria) this;
        }

        public Criteria andCreate_userNotEqualTo(String value) {
            addCriterion("create_user <>", value, "create_user");
            return (Criteria) this;
        }

        public Criteria andCreate_userGreaterThan(String value) {
            addCriterion("create_user >", value, "create_user");
            return (Criteria) this;
        }

        public Criteria andCreate_userGreaterThanOrEqualTo(String value) {
            addCriterion("create_user >=", value, "create_user");
            return (Criteria) this;
        }

        public Criteria andCreate_userLessThan(String value) {
            addCriterion("create_user <", value, "create_user");
            return (Criteria) this;
        }

        public Criteria andCreate_userLessThanOrEqualTo(String value) {
            addCriterion("create_user <=", value, "create_user");
            return (Criteria) this;
        }

        public Criteria andCreate_userLike(String value) {
            addCriterion("create_user like", value, "create_user");
            return (Criteria) this;
        }

        public Criteria andCreate_userNotLike(String value) {
            addCriterion("create_user not like", value, "create_user");
            return (Criteria) this;
        }

        public Criteria andCreate_userIn(List<String> values) {
            addCriterion("create_user in", values, "create_user");
            return (Criteria) this;
        }

        public Criteria andCreate_userNotIn(List<String> values) {
            addCriterion("create_user not in", values, "create_user");
            return (Criteria) this;
        }

        public Criteria andCreate_userBetween(String value1, String value2) {
            addCriterion("create_user between", value1, value2, "create_user");
            return (Criteria) this;
        }

        public Criteria andCreate_userNotBetween(String value1, String value2) {
            addCriterion("create_user not between", value1, value2, "create_user");
            return (Criteria) this;
        }

        public Criteria andUpdate_timeIsNull() {
            addCriterion("update_time is null");
            return (Criteria) this;
        }

        public Criteria andUpdate_timeIsNotNull() {
            addCriterion("update_time is not null");
            return (Criteria) this;
        }

        public Criteria andUpdate_timeEqualTo(Date value) {
            addCriterion("update_time =", value, "update_time");
            return (Criteria) this;
        }

        public Criteria andUpdate_timeNotEqualTo(Date value) {
            addCriterion("update_time <>", value, "update_time");
            return (Criteria) this;
        }

        public Criteria andUpdate_timeGreaterThan(Date value) {
            addCriterion("update_time >", value, "update_time");
            return (Criteria) this;
        }

        public Criteria andUpdate_timeGreaterThanOrEqualTo(Date value) {
            addCriterion("update_time >=", value, "update_time");
            return (Criteria) this;
        }

        public Criteria andUpdate_timeLessThan(Date value) {
            addCriterion("update_time <", value, "update_time");
            return (Criteria) this;
        }

        public Criteria andUpdate_timeLessThanOrEqualTo(Date value) {
            addCriterion("update_time <=", value, "update_time");
            return (Criteria) this;
        }

        public Criteria andUpdate_timeIn(List<Date> values) {
            addCriterion("update_time in", values, "update_time");
            return (Criteria) this;
        }

        public Criteria andUpdate_timeNotIn(List<Date> values) {
            addCriterion("update_time not in", values, "update_time");
            return (Criteria) this;
        }

        public Criteria andUpdate_timeBetween(Date value1, Date value2) {
            addCriterion("update_time between", value1, value2, "update_time");
            return (Criteria) this;
        }

        public Criteria andUpdate_timeNotBetween(Date value1, Date value2) {
            addCriterion("update_time not between", value1, value2, "update_time");
            return (Criteria) this;
        }

        public Criteria andUpdate_userIsNull() {
            addCriterion("update_user is null");
            return (Criteria) this;
        }

        public Criteria andUpdate_userIsNotNull() {
            addCriterion("update_user is not null");
            return (Criteria) this;
        }

        public Criteria andUpdate_userEqualTo(String value) {
            addCriterion("update_user =", value, "update_user");
            return (Criteria) this;
        }

        public Criteria andUpdate_userNotEqualTo(String value) {
            addCriterion("update_user <>", value, "update_user");
            return (Criteria) this;
        }

        public Criteria andUpdate_userGreaterThan(String value) {
            addCriterion("update_user >", value, "update_user");
            return (Criteria) this;
        }

        public Criteria andUpdate_userGreaterThanOrEqualTo(String value) {
            addCriterion("update_user >=", value, "update_user");
            return (Criteria) this;
        }

        public Criteria andUpdate_userLessThan(String value) {
            addCriterion("update_user <", value, "update_user");
            return (Criteria) this;
        }

        public Criteria andUpdate_userLessThanOrEqualTo(String value) {
            addCriterion("update_user <=", value, "update_user");
            return (Criteria) this;
        }

        public Criteria andUpdate_userLike(String value) {
            addCriterion("update_user like", value, "update_user");
            return (Criteria) this;
        }

        public Criteria andUpdate_userNotLike(String value) {
            addCriterion("update_user not like", value, "update_user");
            return (Criteria) this;
        }

        public Criteria andUpdate_userIn(List<String> values) {
            addCriterion("update_user in", values, "update_user");
            return (Criteria) this;
        }

        public Criteria andUpdate_userNotIn(List<String> values) {
            addCriterion("update_user not in", values, "update_user");
            return (Criteria) this;
        }

        public Criteria andUpdate_userBetween(String value1, String value2) {
            addCriterion("update_user between", value1, value2, "update_user");
            return (Criteria) this;
        }

        public Criteria andUpdate_userNotBetween(String value1, String value2) {
            addCriterion("update_user not between", value1, value2, "update_user");
            return (Criteria) this;
        }

        public Criteria andItem_sourceIsNull() {
            addCriterion("item_source is null");
            return (Criteria) this;
        }

        public Criteria andItem_sourceIsNotNull() {
            addCriterion("item_source is not null");
            return (Criteria) this;
        }

        public Criteria andItem_sourceEqualTo(Byte value) {
            addCriterion("item_source =", value, "item_source");
            return (Criteria) this;
        }

        public Criteria andItem_sourceNotEqualTo(Byte value) {
            addCriterion("item_source <>", value, "item_source");
            return (Criteria) this;
        }

        public Criteria andItem_sourceGreaterThan(Byte value) {
            addCriterion("item_source >", value, "item_source");
            return (Criteria) this;
        }

        public Criteria andItem_sourceGreaterThanOrEqualTo(Byte value) {
            addCriterion("item_source >=", value, "item_source");
            return (Criteria) this;
        }

        public Criteria andItem_sourceLessThan(Byte value) {
            addCriterion("item_source <", value, "item_source");
            return (Criteria) this;
        }

        public Criteria andItem_sourceLessThanOrEqualTo(Byte value) {
            addCriterion("item_source <=", value, "item_source");
            return (Criteria) this;
        }

        public Criteria andItem_sourceIn(List<Byte> values) {
            addCriterion("item_source in", values, "item_source");
            return (Criteria) this;
        }

        public Criteria andItem_sourceNotIn(List<Byte> values) {
            addCriterion("item_source not in", values, "item_source");
            return (Criteria) this;
        }

        public Criteria andItem_sourceBetween(Byte value1, Byte value2) {
            addCriterion("item_source between", value1, value2, "item_source");
            return (Criteria) this;
        }

        public Criteria andItem_sourceNotBetween(Byte value1, Byte value2) {
            addCriterion("item_source not between", value1, value2, "item_source");
            return (Criteria) this;
        }

        public Criteria andRef_idIsNull() {
            addCriterion("ref_id is null");
            return (Criteria) this;
        }

        public Criteria andRef_idIsNotNull() {
            addCriterion("ref_id is not null");
            return (Criteria) this;
        }

        public Criteria andRef_idEqualTo(String value) {
            addCriterion("ref_id =", value, "ref_id");
            return (Criteria) this;
        }

        public Criteria andRef_idNotEqualTo(String value) {
            addCriterion("ref_id <>", value, "ref_id");
            return (Criteria) this;
        }

        public Criteria andRef_idGreaterThan(String value) {
            addCriterion("ref_id >", value, "ref_id");
            return (Criteria) this;
        }

        public Criteria andRef_idGreaterThanOrEqualTo(String value) {
            addCriterion("ref_id >=", value, "ref_id");
            return (Criteria) this;
        }

        public Criteria andRef_idLessThan(String value) {
            addCriterion("ref_id <", value, "ref_id");
            return (Criteria) this;
        }

        public Criteria andRef_idLessThanOrEqualTo(String value) {
            addCriterion("ref_id <=", value, "ref_id");
            return (Criteria) this;
        }

        public Criteria andRef_idLike(String value) {
            addCriterion("ref_id like", value, "ref_id");
            return (Criteria) this;
        }

        public Criteria andRef_idNotLike(String value) {
            addCriterion("ref_id not like", value, "ref_id");
            return (Criteria) this;
        }

        public Criteria andRef_idIn(List<String> values) {
            addCriterion("ref_id in", values, "ref_id");
            return (Criteria) this;
        }

        public Criteria andRef_idNotIn(List<String> values) {
            addCriterion("ref_id not in", values, "ref_id");
            return (Criteria) this;
        }

        public Criteria andRef_idBetween(String value1, String value2) {
            addCriterion("ref_id between", value1, value2, "ref_id");
            return (Criteria) this;
        }

        public Criteria andRef_idNotBetween(String value1, String value2) {
            addCriterion("ref_id not between", value1, value2, "ref_id");
            return (Criteria) this;
        }

        public Criteria andItem_styleIsNull() {
            addCriterion("item_style is null");
            return (Criteria) this;
        }

        public Criteria andItem_styleIsNotNull() {
            addCriterion("item_style is not null");
            return (Criteria) this;
        }

        public Criteria andItem_styleEqualTo(Integer value) {
            addCriterion("item_style =", value, "item_style");
            return (Criteria) this;
        }

        public Criteria andItem_styleNotEqualTo(Integer value) {
            addCriterion("item_style <>", value, "item_style");
            return (Criteria) this;
        }

        public Criteria andItem_styleGreaterThan(Integer value) {
            addCriterion("item_style >", value, "item_style");
            return (Criteria) this;
        }

        public Criteria andItem_styleGreaterThanOrEqualTo(Integer value) {
            addCriterion("item_style >=", value, "item_style");
            return (Criteria) this;
        }

        public Criteria andItem_styleLessThan(Integer value) {
            addCriterion("item_style <", value, "item_style");
            return (Criteria) this;
        }

        public Criteria andItem_styleLessThanOrEqualTo(Integer value) {
            addCriterion("item_style <=", value, "item_style");
            return (Criteria) this;
        }

        public Criteria andItem_styleIn(List<Integer> values) {
            addCriterion("item_style in", values, "item_style");
            return (Criteria) this;
        }

        public Criteria andItem_styleNotIn(List<Integer> values) {
            addCriterion("item_style not in", values, "item_style");
            return (Criteria) this;
        }

        public Criteria andItem_styleBetween(Integer value1, Integer value2) {
            addCriterion("item_style between", value1, value2, "item_style");
            return (Criteria) this;
        }

        public Criteria andItem_styleNotBetween(Integer value1, Integer value2) {
            addCriterion("item_style not between", value1, value2, "item_style");
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