package com.lx.benefits.bean.entity.orderitem;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderItemExample {
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

    public OrderItemExample() {
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

        public Criteria andTypeIsNull() {
            addCriterion("type is null");
            return (Criteria) this;
        }

        public Criteria andTypeIsNotNull() {
            addCriterion("type is not null");
            return (Criteria) this;
        }

        public Criteria andTypeEqualTo(Byte value) {
            addCriterion("type =", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotEqualTo(Byte value) {
            addCriterion("type <>", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThan(Byte value) {
            addCriterion("type >", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanOrEqualTo(Byte value) {
            addCriterion("type >=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThan(Byte value) {
            addCriterion("type <", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThanOrEqualTo(Byte value) {
            addCriterion("type <=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeIn(List<Byte> values) {
            addCriterion("type in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotIn(List<Byte> values) {
            addCriterion("type not in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeBetween(Byte value1, Byte value2) {
            addCriterion("type between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotBetween(Byte value1, Byte value2) {
            addCriterion("type not between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andParent_order_idIsNull() {
            addCriterion("parent_order_id is null");
            return (Criteria) this;
        }

        public Criteria andParent_order_idIsNotNull() {
            addCriterion("parent_order_id is not null");
            return (Criteria) this;
        }

        public Criteria andParent_order_idEqualTo(Long value) {
            addCriterion("parent_order_id =", value, "parent_order_id");
            return (Criteria) this;
        }

        public Criteria andParent_order_idNotEqualTo(Long value) {
            addCriterion("parent_order_id <>", value, "parent_order_id");
            return (Criteria) this;
        }

        public Criteria andParent_order_idGreaterThan(Long value) {
            addCriterion("parent_order_id >", value, "parent_order_id");
            return (Criteria) this;
        }

        public Criteria andParent_order_idGreaterThanOrEqualTo(Long value) {
            addCriterion("parent_order_id >=", value, "parent_order_id");
            return (Criteria) this;
        }

        public Criteria andParent_order_idLessThan(Long value) {
            addCriterion("parent_order_id <", value, "parent_order_id");
            return (Criteria) this;
        }

        public Criteria andParent_order_idLessThanOrEqualTo(Long value) {
            addCriterion("parent_order_id <=", value, "parent_order_id");
            return (Criteria) this;
        }

        public Criteria andParent_order_idIn(List<Long> values) {
            addCriterion("parent_order_id in", values, "parent_order_id");
            return (Criteria) this;
        }

        public Criteria andParent_order_idNotIn(List<Long> values) {
            addCriterion("parent_order_id not in", values, "parent_order_id");
            return (Criteria) this;
        }

        public Criteria andParent_order_idBetween(Long value1, Long value2) {
            addCriterion("parent_order_id between", value1, value2, "parent_order_id");
            return (Criteria) this;
        }

        public Criteria andParent_order_idNotBetween(Long value1, Long value2) {
            addCriterion("parent_order_id not between", value1, value2, "parent_order_id");
            return (Criteria) this;
        }

        public Criteria andParent_order_codeIsNull() {
            addCriterion("parent_order_code is null");
            return (Criteria) this;
        }

        public Criteria andParent_order_codeIsNotNull() {
            addCriterion("parent_order_code is not null");
            return (Criteria) this;
        }

        public Criteria andParent_order_codeEqualTo(Long value) {
            addCriterion("parent_order_code =", value, "parent_order_code");
            return (Criteria) this;
        }

        public Criteria andParent_order_codeNotEqualTo(Long value) {
            addCriterion("parent_order_code <>", value, "parent_order_code");
            return (Criteria) this;
        }

        public Criteria andParent_order_codeGreaterThan(Long value) {
            addCriterion("parent_order_code >", value, "parent_order_code");
            return (Criteria) this;
        }

        public Criteria andParent_order_codeGreaterThanOrEqualTo(Long value) {
            addCriterion("parent_order_code >=", value, "parent_order_code");
            return (Criteria) this;
        }

        public Criteria andParent_order_codeLessThan(Long value) {
            addCriterion("parent_order_code <", value, "parent_order_code");
            return (Criteria) this;
        }

        public Criteria andParent_order_codeLessThanOrEqualTo(Long value) {
            addCriterion("parent_order_code <=", value, "parent_order_code");
            return (Criteria) this;
        }

        public Criteria andParent_order_codeIn(List<Long> values) {
            addCriterion("parent_order_code in", values, "parent_order_code");
            return (Criteria) this;
        }

        public Criteria andParent_order_codeNotIn(List<Long> values) {
            addCriterion("parent_order_code not in", values, "parent_order_code");
            return (Criteria) this;
        }

        public Criteria andParent_order_codeBetween(Long value1, Long value2) {
            addCriterion("parent_order_code between", value1, value2, "parent_order_code");
            return (Criteria) this;
        }

        public Criteria andParent_order_codeNotBetween(Long value1, Long value2) {
            addCriterion("parent_order_code not between", value1, value2, "parent_order_code");
            return (Criteria) this;
        }

        public Criteria andOrder_idIsNull() {
            addCriterion("order_id is null");
            return (Criteria) this;
        }

        public Criteria andOrder_idIsNotNull() {
            addCriterion("order_id is not null");
            return (Criteria) this;
        }

        public Criteria andOrder_idEqualTo(Long value) {
            addCriterion("order_id =", value, "order_id");
            return (Criteria) this;
        }

        public Criteria andOrder_idNotEqualTo(Long value) {
            addCriterion("order_id <>", value, "order_id");
            return (Criteria) this;
        }

        public Criteria andOrder_idGreaterThan(Long value) {
            addCriterion("order_id >", value, "order_id");
            return (Criteria) this;
        }

        public Criteria andOrder_idGreaterThanOrEqualTo(Long value) {
            addCriterion("order_id >=", value, "order_id");
            return (Criteria) this;
        }

        public Criteria andOrder_idLessThan(Long value) {
            addCriterion("order_id <", value, "order_id");
            return (Criteria) this;
        }

        public Criteria andOrder_idLessThanOrEqualTo(Long value) {
            addCriterion("order_id <=", value, "order_id");
            return (Criteria) this;
        }

        public Criteria andOrder_idIn(List<Long> values) {
            addCriterion("order_id in", values, "order_id");
            return (Criteria) this;
        }

        public Criteria andOrder_idNotIn(List<Long> values) {
            addCriterion("order_id not in", values, "order_id");
            return (Criteria) this;
        }

        public Criteria andOrder_idBetween(Long value1, Long value2) {
            addCriterion("order_id between", value1, value2, "order_id");
            return (Criteria) this;
        }

        public Criteria andOrder_idNotBetween(Long value1, Long value2) {
            addCriterion("order_id not between", value1, value2, "order_id");
            return (Criteria) this;
        }

        public Criteria andOrder_codeIsNull() {
            addCriterion("order_code is null");
            return (Criteria) this;
        }

        public Criteria andOrder_codeIsNotNull() {
            addCriterion("order_code is not null");
            return (Criteria) this;
        }

        public Criteria andOrder_codeEqualTo(Long value) {
            addCriterion("order_code =", value, "order_code");
            return (Criteria) this;
        }

        public Criteria andOrder_codeNotEqualTo(Long value) {
            addCriterion("order_code <>", value, "order_code");
            return (Criteria) this;
        }

        public Criteria andOrder_codeGreaterThan(Long value) {
            addCriterion("order_code >", value, "order_code");
            return (Criteria) this;
        }

        public Criteria andOrder_codeGreaterThanOrEqualTo(Long value) {
            addCriterion("order_code >=", value, "order_code");
            return (Criteria) this;
        }

        public Criteria andOrder_codeLessThan(Long value) {
            addCriterion("order_code <", value, "order_code");
            return (Criteria) this;
        }

        public Criteria andOrder_codeLessThanOrEqualTo(Long value) {
            addCriterion("order_code <=", value, "order_code");
            return (Criteria) this;
        }

        public Criteria andOrder_codeIn(List<Long> values) {
            addCriterion("order_code in", values, "order_code");
            return (Criteria) this;
        }

        public Criteria andOrder_codeNotIn(List<Long> values) {
            addCriterion("order_code not in", values, "order_code");
            return (Criteria) this;
        }

        public Criteria andOrder_codeBetween(Long value1, Long value2) {
            addCriterion("order_code between", value1, value2, "order_code");
            return (Criteria) this;
        }

        public Criteria andOrder_codeNotBetween(Long value1, Long value2) {
            addCriterion("order_code not between", value1, value2, "order_code");
            return (Criteria) this;
        }

        public Criteria andMember_idIsNull() {
            addCriterion("member_id is null");
            return (Criteria) this;
        }

        public Criteria andMember_idIsNotNull() {
            addCriterion("member_id is not null");
            return (Criteria) this;
        }

        public Criteria andMember_idEqualTo(Long value) {
            addCriterion("member_id =", value, "member_id");
            return (Criteria) this;
        }

        public Criteria andMember_idNotEqualTo(Long value) {
            addCriterion("member_id <>", value, "member_id");
            return (Criteria) this;
        }

        public Criteria andMember_idGreaterThan(Long value) {
            addCriterion("member_id >", value, "member_id");
            return (Criteria) this;
        }

        public Criteria andMember_idGreaterThanOrEqualTo(Long value) {
            addCriterion("member_id >=", value, "member_id");
            return (Criteria) this;
        }

        public Criteria andMember_idLessThan(Long value) {
            addCriterion("member_id <", value, "member_id");
            return (Criteria) this;
        }

        public Criteria andMember_idLessThanOrEqualTo(Long value) {
            addCriterion("member_id <=", value, "member_id");
            return (Criteria) this;
        }

        public Criteria andMember_idIn(List<Long> values) {
            addCriterion("member_id in", values, "member_id");
            return (Criteria) this;
        }

        public Criteria andMember_idNotIn(List<Long> values) {
            addCriterion("member_id not in", values, "member_id");
            return (Criteria) this;
        }

        public Criteria andMember_idBetween(Long value1, Long value2) {
            addCriterion("member_id between", value1, value2, "member_id");
            return (Criteria) this;
        }

        public Criteria andMember_idNotBetween(Long value1, Long value2) {
            addCriterion("member_id not between", value1, value2, "member_id");
            return (Criteria) this;
        }

        public Criteria andSpu_idIsNull() {
            addCriterion("spu_id is null");
            return (Criteria) this;
        }

        public Criteria andSpu_idIsNotNull() {
            addCriterion("spu_id is not null");
            return (Criteria) this;
        }

        public Criteria andSpu_idEqualTo(Long value) {
            addCriterion("spu_id =", value, "spu_id");
            return (Criteria) this;
        }

        public Criteria andSpu_idNotEqualTo(Long value) {
            addCriterion("spu_id <>", value, "spu_id");
            return (Criteria) this;
        }

        public Criteria andSpu_idGreaterThan(Long value) {
            addCriterion("spu_id >", value, "spu_id");
            return (Criteria) this;
        }

        public Criteria andSpu_idGreaterThanOrEqualTo(Long value) {
            addCriterion("spu_id >=", value, "spu_id");
            return (Criteria) this;
        }

        public Criteria andSpu_idLessThan(Long value) {
            addCriterion("spu_id <", value, "spu_id");
            return (Criteria) this;
        }

        public Criteria andSpu_idLessThanOrEqualTo(Long value) {
            addCriterion("spu_id <=", value, "spu_id");
            return (Criteria) this;
        }

        public Criteria andSpu_idIn(List<Long> values) {
            addCriterion("spu_id in", values, "spu_id");
            return (Criteria) this;
        }

        public Criteria andSpu_idNotIn(List<Long> values) {
            addCriterion("spu_id not in", values, "spu_id");
            return (Criteria) this;
        }

        public Criteria andSpu_idBetween(Long value1, Long value2) {
            addCriterion("spu_id between", value1, value2, "spu_id");
            return (Criteria) this;
        }

        public Criteria andSpu_idNotBetween(Long value1, Long value2) {
            addCriterion("spu_id not between", value1, value2, "spu_id");
            return (Criteria) this;
        }

        public Criteria andSpu_codeIsNull() {
            addCriterion("spu_code is null");
            return (Criteria) this;
        }

        public Criteria andSpu_codeIsNotNull() {
            addCriterion("spu_code is not null");
            return (Criteria) this;
        }

        public Criteria andSpu_codeEqualTo(String value) {
            addCriterion("spu_code =", value, "spu_code");
            return (Criteria) this;
        }

        public Criteria andSpu_codeNotEqualTo(String value) {
            addCriterion("spu_code <>", value, "spu_code");
            return (Criteria) this;
        }

        public Criteria andSpu_codeGreaterThan(String value) {
            addCriterion("spu_code >", value, "spu_code");
            return (Criteria) this;
        }

        public Criteria andSpu_codeGreaterThanOrEqualTo(String value) {
            addCriterion("spu_code >=", value, "spu_code");
            return (Criteria) this;
        }

        public Criteria andSpu_codeLessThan(String value) {
            addCriterion("spu_code <", value, "spu_code");
            return (Criteria) this;
        }

        public Criteria andSpu_codeLessThanOrEqualTo(String value) {
            addCriterion("spu_code <=", value, "spu_code");
            return (Criteria) this;
        }

        public Criteria andSpu_codeLike(String value) {
            addCriterion("spu_code like", value, "spu_code");
            return (Criteria) this;
        }

        public Criteria andSpu_codeNotLike(String value) {
            addCriterion("spu_code not like", value, "spu_code");
            return (Criteria) this;
        }

        public Criteria andSpu_codeIn(List<String> values) {
            addCriterion("spu_code in", values, "spu_code");
            return (Criteria) this;
        }

        public Criteria andSpu_codeNotIn(List<String> values) {
            addCriterion("spu_code not in", values, "spu_code");
            return (Criteria) this;
        }

        public Criteria andSpu_codeBetween(String value1, String value2) {
            addCriterion("spu_code between", value1, value2, "spu_code");
            return (Criteria) this;
        }

        public Criteria andSpu_codeNotBetween(String value1, String value2) {
            addCriterion("spu_code not between", value1, value2, "spu_code");
            return (Criteria) this;
        }

        public Criteria andSpu_nameIsNull() {
            addCriterion("spu_name is null");
            return (Criteria) this;
        }

        public Criteria andSpu_nameIsNotNull() {
            addCriterion("spu_name is not null");
            return (Criteria) this;
        }

        public Criteria andSpu_nameEqualTo(String value) {
            addCriterion("spu_name =", value, "spu_name");
            return (Criteria) this;
        }

        public Criteria andSpu_nameNotEqualTo(String value) {
            addCriterion("spu_name <>", value, "spu_name");
            return (Criteria) this;
        }

        public Criteria andSpu_nameGreaterThan(String value) {
            addCriterion("spu_name >", value, "spu_name");
            return (Criteria) this;
        }

        public Criteria andSpu_nameGreaterThanOrEqualTo(String value) {
            addCriterion("spu_name >=", value, "spu_name");
            return (Criteria) this;
        }

        public Criteria andSpu_nameLessThan(String value) {
            addCriterion("spu_name <", value, "spu_name");
            return (Criteria) this;
        }

        public Criteria andSpu_nameLessThanOrEqualTo(String value) {
            addCriterion("spu_name <=", value, "spu_name");
            return (Criteria) this;
        }

        public Criteria andSpu_nameLike(String value) {
            addCriterion("spu_name like", value, "spu_name");
            return (Criteria) this;
        }

        public Criteria andSpu_nameNotLike(String value) {
            addCriterion("spu_name not like", value, "spu_name");
            return (Criteria) this;
        }

        public Criteria andSpu_nameIn(List<String> values) {
            addCriterion("spu_name in", values, "spu_name");
            return (Criteria) this;
        }

        public Criteria andSpu_nameNotIn(List<String> values) {
            addCriterion("spu_name not in", values, "spu_name");
            return (Criteria) this;
        }

        public Criteria andSpu_nameBetween(String value1, String value2) {
            addCriterion("spu_name between", value1, value2, "spu_name");
            return (Criteria) this;
        }

        public Criteria andSpu_nameNotBetween(String value1, String value2) {
            addCriterion("spu_name not between", value1, value2, "spu_name");
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

        public Criteria andBrand_idEqualTo(Long value) {
            addCriterion("brand_id =", value, "brand_id");
            return (Criteria) this;
        }

        public Criteria andBrand_idNotEqualTo(Long value) {
            addCriterion("brand_id <>", value, "brand_id");
            return (Criteria) this;
        }

        public Criteria andBrand_idGreaterThan(Long value) {
            addCriterion("brand_id >", value, "brand_id");
            return (Criteria) this;
        }

        public Criteria andBrand_idGreaterThanOrEqualTo(Long value) {
            addCriterion("brand_id >=", value, "brand_id");
            return (Criteria) this;
        }

        public Criteria andBrand_idLessThan(Long value) {
            addCriterion("brand_id <", value, "brand_id");
            return (Criteria) this;
        }

        public Criteria andBrand_idLessThanOrEqualTo(Long value) {
            addCriterion("brand_id <=", value, "brand_id");
            return (Criteria) this;
        }

        public Criteria andBrand_idIn(List<Long> values) {
            addCriterion("brand_id in", values, "brand_id");
            return (Criteria) this;
        }

        public Criteria andBrand_idNotIn(List<Long> values) {
            addCriterion("brand_id not in", values, "brand_id");
            return (Criteria) this;
        }

        public Criteria andBrand_idBetween(Long value1, Long value2) {
            addCriterion("brand_id between", value1, value2, "brand_id");
            return (Criteria) this;
        }

        public Criteria andBrand_idNotBetween(Long value1, Long value2) {
            addCriterion("brand_id not between", value1, value2, "brand_id");
            return (Criteria) this;
        }

        public Criteria andBrand_NameIsNull() {
            addCriterion("brand_Name is null");
            return (Criteria) this;
        }

        public Criteria andBrand_NameIsNotNull() {
            addCriterion("brand_Name is not null");
            return (Criteria) this;
        }

        public Criteria andBrand_NameEqualTo(String value) {
            addCriterion("brand_Name =", value, "brand_Name");
            return (Criteria) this;
        }

        public Criteria andBrand_NameNotEqualTo(String value) {
            addCriterion("brand_Name <>", value, "brand_Name");
            return (Criteria) this;
        }

        public Criteria andBrand_NameGreaterThan(String value) {
            addCriterion("brand_Name >", value, "brand_Name");
            return (Criteria) this;
        }

        public Criteria andBrand_NameGreaterThanOrEqualTo(String value) {
            addCriterion("brand_Name >=", value, "brand_Name");
            return (Criteria) this;
        }

        public Criteria andBrand_NameLessThan(String value) {
            addCriterion("brand_Name <", value, "brand_Name");
            return (Criteria) this;
        }

        public Criteria andBrand_NameLessThanOrEqualTo(String value) {
            addCriterion("brand_Name <=", value, "brand_Name");
            return (Criteria) this;
        }

        public Criteria andBrand_NameLike(String value) {
            addCriterion("brand_Name like", value, "brand_Name");
            return (Criteria) this;
        }

        public Criteria andBrand_NameNotLike(String value) {
            addCriterion("brand_Name not like", value, "brand_Name");
            return (Criteria) this;
        }

        public Criteria andBrand_NameIn(List<String> values) {
            addCriterion("brand_Name in", values, "brand_Name");
            return (Criteria) this;
        }

        public Criteria andBrand_NameNotIn(List<String> values) {
            addCriterion("brand_Name not in", values, "brand_Name");
            return (Criteria) this;
        }

        public Criteria andBrand_NameBetween(String value1, String value2) {
            addCriterion("brand_Name between", value1, value2, "brand_Name");
            return (Criteria) this;
        }

        public Criteria andBrand_NameNotBetween(String value1, String value2) {
            addCriterion("brand_Name not between", value1, value2, "brand_Name");
            return (Criteria) this;
        }

        public Criteria andSku_idIsNull() {
            addCriterion("sku_id is null");
            return (Criteria) this;
        }

        public Criteria andSku_idIsNotNull() {
            addCriterion("sku_id is not null");
            return (Criteria) this;
        }

        public Criteria andSku_idEqualTo(Long value) {
            addCriterion("sku_id =", value, "sku_id");
            return (Criteria) this;
        }

        public Criteria andSku_idNotEqualTo(Long value) {
            addCriterion("sku_id <>", value, "sku_id");
            return (Criteria) this;
        }

        public Criteria andSku_idGreaterThan(Long value) {
            addCriterion("sku_id >", value, "sku_id");
            return (Criteria) this;
        }

        public Criteria andSku_idGreaterThanOrEqualTo(Long value) {
            addCriterion("sku_id >=", value, "sku_id");
            return (Criteria) this;
        }

        public Criteria andSku_idLessThan(Long value) {
            addCriterion("sku_id <", value, "sku_id");
            return (Criteria) this;
        }

        public Criteria andSku_idLessThanOrEqualTo(Long value) {
            addCriterion("sku_id <=", value, "sku_id");
            return (Criteria) this;
        }

        public Criteria andSku_idIn(List<Long> values) {
            addCriterion("sku_id in", values, "sku_id");
            return (Criteria) this;
        }

        public Criteria andSku_idNotIn(List<Long> values) {
            addCriterion("sku_id not in", values, "sku_id");
            return (Criteria) this;
        }

        public Criteria andSku_idBetween(Long value1, Long value2) {
            addCriterion("sku_id between", value1, value2, "sku_id");
            return (Criteria) this;
        }

        public Criteria andSku_idNotBetween(Long value1, Long value2) {
            addCriterion("sku_id not between", value1, value2, "sku_id");
            return (Criteria) this;
        }

        public Criteria andSku_codeIsNull() {
            addCriterion("sku_code is null");
            return (Criteria) this;
        }

        public Criteria andSku_codeIsNotNull() {
            addCriterion("sku_code is not null");
            return (Criteria) this;
        }

        public Criteria andSku_codeEqualTo(String value) {
            addCriterion("sku_code =", value, "sku_code");
            return (Criteria) this;
        }

        public Criteria andSku_codeNotEqualTo(String value) {
            addCriterion("sku_code <>", value, "sku_code");
            return (Criteria) this;
        }

        public Criteria andSku_codeGreaterThan(String value) {
            addCriterion("sku_code >", value, "sku_code");
            return (Criteria) this;
        }

        public Criteria andSku_codeGreaterThanOrEqualTo(String value) {
            addCriterion("sku_code >=", value, "sku_code");
            return (Criteria) this;
        }

        public Criteria andSku_codeLessThan(String value) {
            addCriterion("sku_code <", value, "sku_code");
            return (Criteria) this;
        }

        public Criteria andSku_codeLessThanOrEqualTo(String value) {
            addCriterion("sku_code <=", value, "sku_code");
            return (Criteria) this;
        }

        public Criteria andSku_codeLike(String value) {
            addCriterion("sku_code like", value, "sku_code");
            return (Criteria) this;
        }

        public Criteria andSku_codeNotLike(String value) {
            addCriterion("sku_code not like", value, "sku_code");
            return (Criteria) this;
        }

        public Criteria andSku_codeIn(List<String> values) {
            addCriterion("sku_code in", values, "sku_code");
            return (Criteria) this;
        }

        public Criteria andSku_codeNotIn(List<String> values) {
            addCriterion("sku_code not in", values, "sku_code");
            return (Criteria) this;
        }

        public Criteria andSku_codeBetween(String value1, String value2) {
            addCriterion("sku_code between", value1, value2, "sku_code");
            return (Criteria) this;
        }

        public Criteria andSku_codeNotBetween(String value1, String value2) {
            addCriterion("sku_code not between", value1, value2, "sku_code");
            return (Criteria) this;
        }

        public Criteria andBar_codeIsNull() {
            addCriterion("bar_code is null");
            return (Criteria) this;
        }

        public Criteria andBar_codeIsNotNull() {
            addCriterion("bar_code is not null");
            return (Criteria) this;
        }

        public Criteria andBar_codeEqualTo(String value) {
            addCriterion("bar_code =", value, "bar_code");
            return (Criteria) this;
        }

        public Criteria andBar_codeNotEqualTo(String value) {
            addCriterion("bar_code <>", value, "bar_code");
            return (Criteria) this;
        }

        public Criteria andBar_codeGreaterThan(String value) {
            addCriterion("bar_code >", value, "bar_code");
            return (Criteria) this;
        }

        public Criteria andBar_codeGreaterThanOrEqualTo(String value) {
            addCriterion("bar_code >=", value, "bar_code");
            return (Criteria) this;
        }

        public Criteria andBar_codeLessThan(String value) {
            addCriterion("bar_code <", value, "bar_code");
            return (Criteria) this;
        }

        public Criteria andBar_codeLessThanOrEqualTo(String value) {
            addCriterion("bar_code <=", value, "bar_code");
            return (Criteria) this;
        }

        public Criteria andBar_codeLike(String value) {
            addCriterion("bar_code like", value, "bar_code");
            return (Criteria) this;
        }

        public Criteria andBar_codeNotLike(String value) {
            addCriterion("bar_code not like", value, "bar_code");
            return (Criteria) this;
        }

        public Criteria andBar_codeIn(List<String> values) {
            addCriterion("bar_code in", values, "bar_code");
            return (Criteria) this;
        }

        public Criteria andBar_codeNotIn(List<String> values) {
            addCriterion("bar_code not in", values, "bar_code");
            return (Criteria) this;
        }

        public Criteria andBar_codeBetween(String value1, String value2) {
            addCriterion("bar_code between", value1, value2, "bar_code");
            return (Criteria) this;
        }

        public Criteria andBar_codeNotBetween(String value1, String value2) {
            addCriterion("bar_code not between", value1, value2, "bar_code");
            return (Criteria) this;
        }

        public Criteria andImgIsNull() {
            addCriterion("img is null");
            return (Criteria) this;
        }

        public Criteria andImgIsNotNull() {
            addCriterion("img is not null");
            return (Criteria) this;
        }

        public Criteria andImgEqualTo(String value) {
            addCriterion("img =", value, "img");
            return (Criteria) this;
        }

        public Criteria andImgNotEqualTo(String value) {
            addCriterion("img <>", value, "img");
            return (Criteria) this;
        }

        public Criteria andImgGreaterThan(String value) {
            addCriterion("img >", value, "img");
            return (Criteria) this;
        }

        public Criteria andImgGreaterThanOrEqualTo(String value) {
            addCriterion("img >=", value, "img");
            return (Criteria) this;
        }

        public Criteria andImgLessThan(String value) {
            addCriterion("img <", value, "img");
            return (Criteria) this;
        }

        public Criteria andImgLessThanOrEqualTo(String value) {
            addCriterion("img <=", value, "img");
            return (Criteria) this;
        }

        public Criteria andImgLike(String value) {
            addCriterion("img like", value, "img");
            return (Criteria) this;
        }

        public Criteria andImgNotLike(String value) {
            addCriterion("img not like", value, "img");
            return (Criteria) this;
        }

        public Criteria andImgIn(List<String> values) {
            addCriterion("img in", values, "img");
            return (Criteria) this;
        }

        public Criteria andImgNotIn(List<String> values) {
            addCriterion("img not in", values, "img");
            return (Criteria) this;
        }

        public Criteria andImgBetween(String value1, String value2) {
            addCriterion("img between", value1, value2, "img");
            return (Criteria) this;
        }

        public Criteria andImgNotBetween(String value1, String value2) {
            addCriterion("img not between", value1, value2, "img");
            return (Criteria) this;
        }

        public Criteria andSku_versionIsNull() {
            addCriterion("sku_version is null");
            return (Criteria) this;
        }

        public Criteria andSku_versionIsNotNull() {
            addCriterion("sku_version is not null");
            return (Criteria) this;
        }

        public Criteria andSku_versionEqualTo(String value) {
            addCriterion("sku_version =", value, "sku_version");
            return (Criteria) this;
        }

        public Criteria andSku_versionNotEqualTo(String value) {
            addCriterion("sku_version <>", value, "sku_version");
            return (Criteria) this;
        }

        public Criteria andSku_versionGreaterThan(String value) {
            addCriterion("sku_version >", value, "sku_version");
            return (Criteria) this;
        }

        public Criteria andSku_versionGreaterThanOrEqualTo(String value) {
            addCriterion("sku_version >=", value, "sku_version");
            return (Criteria) this;
        }

        public Criteria andSku_versionLessThan(String value) {
            addCriterion("sku_version <", value, "sku_version");
            return (Criteria) this;
        }

        public Criteria andSku_versionLessThanOrEqualTo(String value) {
            addCriterion("sku_version <=", value, "sku_version");
            return (Criteria) this;
        }

        public Criteria andSku_versionLike(String value) {
            addCriterion("sku_version like", value, "sku_version");
            return (Criteria) this;
        }

        public Criteria andSku_versionNotLike(String value) {
            addCriterion("sku_version not like", value, "sku_version");
            return (Criteria) this;
        }

        public Criteria andSku_versionIn(List<String> values) {
            addCriterion("sku_version in", values, "sku_version");
            return (Criteria) this;
        }

        public Criteria andSku_versionNotIn(List<String> values) {
            addCriterion("sku_version not in", values, "sku_version");
            return (Criteria) this;
        }

        public Criteria andSku_versionBetween(String value1, String value2) {
            addCriterion("sku_version between", value1, value2, "sku_version");
            return (Criteria) this;
        }

        public Criteria andSku_versionNotBetween(String value1, String value2) {
            addCriterion("sku_version not between", value1, value2, "sku_version");
            return (Criteria) this;
        }

        public Criteria andTopic_idIsNull() {
            addCriterion("topic_id is null");
            return (Criteria) this;
        }

        public Criteria andTopic_idIsNotNull() {
            addCriterion("topic_id is not null");
            return (Criteria) this;
        }

        public Criteria andTopic_idEqualTo(Long value) {
            addCriterion("topic_id =", value, "topic_id");
            return (Criteria) this;
        }

        public Criteria andTopic_idNotEqualTo(Long value) {
            addCriterion("topic_id <>", value, "topic_id");
            return (Criteria) this;
        }

        public Criteria andTopic_idGreaterThan(Long value) {
            addCriterion("topic_id >", value, "topic_id");
            return (Criteria) this;
        }

        public Criteria andTopic_idGreaterThanOrEqualTo(Long value) {
            addCriterion("topic_id >=", value, "topic_id");
            return (Criteria) this;
        }

        public Criteria andTopic_idLessThan(Long value) {
            addCriterion("topic_id <", value, "topic_id");
            return (Criteria) this;
        }

        public Criteria andTopic_idLessThanOrEqualTo(Long value) {
            addCriterion("topic_id <=", value, "topic_id");
            return (Criteria) this;
        }

        public Criteria andTopic_idIn(List<Long> values) {
            addCriterion("topic_id in", values, "topic_id");
            return (Criteria) this;
        }

        public Criteria andTopic_idNotIn(List<Long> values) {
            addCriterion("topic_id not in", values, "topic_id");
            return (Criteria) this;
        }

        public Criteria andTopic_idBetween(Long value1, Long value2) {
            addCriterion("topic_id between", value1, value2, "topic_id");
            return (Criteria) this;
        }

        public Criteria andTopic_idNotBetween(Long value1, Long value2) {
            addCriterion("topic_id not between", value1, value2, "topic_id");
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

        public Criteria andSupplier_nameIsNull() {
            addCriterion("supplier_name is null");
            return (Criteria) this;
        }

        public Criteria andSupplier_nameIsNotNull() {
            addCriterion("supplier_name is not null");
            return (Criteria) this;
        }

        public Criteria andSupplier_nameEqualTo(String value) {
            addCriterion("supplier_name =", value, "supplier_name");
            return (Criteria) this;
        }

        public Criteria andSupplier_nameNotEqualTo(String value) {
            addCriterion("supplier_name <>", value, "supplier_name");
            return (Criteria) this;
        }

        public Criteria andSupplier_nameGreaterThan(String value) {
            addCriterion("supplier_name >", value, "supplier_name");
            return (Criteria) this;
        }

        public Criteria andSupplier_nameGreaterThanOrEqualTo(String value) {
            addCriterion("supplier_name >=", value, "supplier_name");
            return (Criteria) this;
        }

        public Criteria andSupplier_nameLessThan(String value) {
            addCriterion("supplier_name <", value, "supplier_name");
            return (Criteria) this;
        }

        public Criteria andSupplier_nameLessThanOrEqualTo(String value) {
            addCriterion("supplier_name <=", value, "supplier_name");
            return (Criteria) this;
        }

        public Criteria andSupplier_nameLike(String value) {
            addCriterion("supplier_name like", value, "supplier_name");
            return (Criteria) this;
        }

        public Criteria andSupplier_nameNotLike(String value) {
            addCriterion("supplier_name not like", value, "supplier_name");
            return (Criteria) this;
        }

        public Criteria andSupplier_nameIn(List<String> values) {
            addCriterion("supplier_name in", values, "supplier_name");
            return (Criteria) this;
        }

        public Criteria andSupplier_nameNotIn(List<String> values) {
            addCriterion("supplier_name not in", values, "supplier_name");
            return (Criteria) this;
        }

        public Criteria andSupplier_nameBetween(String value1, String value2) {
            addCriterion("supplier_name between", value1, value2, "supplier_name");
            return (Criteria) this;
        }

        public Criteria andSupplier_nameNotBetween(String value1, String value2) {
            addCriterion("supplier_name not between", value1, value2, "supplier_name");
            return (Criteria) this;
        }

        public Criteria andWarehouse_idIsNull() {
            addCriterion("warehouse_id is null");
            return (Criteria) this;
        }

        public Criteria andWarehouse_idIsNotNull() {
            addCriterion("warehouse_id is not null");
            return (Criteria) this;
        }

        public Criteria andWarehouse_idEqualTo(Long value) {
            addCriterion("warehouse_id =", value, "warehouse_id");
            return (Criteria) this;
        }

        public Criteria andWarehouse_idNotEqualTo(Long value) {
            addCriterion("warehouse_id <>", value, "warehouse_id");
            return (Criteria) this;
        }

        public Criteria andWarehouse_idGreaterThan(Long value) {
            addCriterion("warehouse_id >", value, "warehouse_id");
            return (Criteria) this;
        }

        public Criteria andWarehouse_idGreaterThanOrEqualTo(Long value) {
            addCriterion("warehouse_id >=", value, "warehouse_id");
            return (Criteria) this;
        }

        public Criteria andWarehouse_idLessThan(Long value) {
            addCriterion("warehouse_id <", value, "warehouse_id");
            return (Criteria) this;
        }

        public Criteria andWarehouse_idLessThanOrEqualTo(Long value) {
            addCriterion("warehouse_id <=", value, "warehouse_id");
            return (Criteria) this;
        }

        public Criteria andWarehouse_idIn(List<Long> values) {
            addCriterion("warehouse_id in", values, "warehouse_id");
            return (Criteria) this;
        }

        public Criteria andWarehouse_idNotIn(List<Long> values) {
            addCriterion("warehouse_id not in", values, "warehouse_id");
            return (Criteria) this;
        }

        public Criteria andWarehouse_idBetween(Long value1, Long value2) {
            addCriterion("warehouse_id between", value1, value2, "warehouse_id");
            return (Criteria) this;
        }

        public Criteria andWarehouse_idNotBetween(Long value1, Long value2) {
            addCriterion("warehouse_id not between", value1, value2, "warehouse_id");
            return (Criteria) this;
        }

        public Criteria andBatch_codeIsNull() {
            addCriterion("batch_code is null");
            return (Criteria) this;
        }

        public Criteria andBatch_codeIsNotNull() {
            addCriterion("batch_code is not null");
            return (Criteria) this;
        }

        public Criteria andBatch_codeEqualTo(String value) {
            addCriterion("batch_code =", value, "batch_code");
            return (Criteria) this;
        }

        public Criteria andBatch_codeNotEqualTo(String value) {
            addCriterion("batch_code <>", value, "batch_code");
            return (Criteria) this;
        }

        public Criteria andBatch_codeGreaterThan(String value) {
            addCriterion("batch_code >", value, "batch_code");
            return (Criteria) this;
        }

        public Criteria andBatch_codeGreaterThanOrEqualTo(String value) {
            addCriterion("batch_code >=", value, "batch_code");
            return (Criteria) this;
        }

        public Criteria andBatch_codeLessThan(String value) {
            addCriterion("batch_code <", value, "batch_code");
            return (Criteria) this;
        }

        public Criteria andBatch_codeLessThanOrEqualTo(String value) {
            addCriterion("batch_code <=", value, "batch_code");
            return (Criteria) this;
        }

        public Criteria andBatch_codeLike(String value) {
            addCriterion("batch_code like", value, "batch_code");
            return (Criteria) this;
        }

        public Criteria andBatch_codeNotLike(String value) {
            addCriterion("batch_code not like", value, "batch_code");
            return (Criteria) this;
        }

        public Criteria andBatch_codeIn(List<String> values) {
            addCriterion("batch_code in", values, "batch_code");
            return (Criteria) this;
        }

        public Criteria andBatch_codeNotIn(List<String> values) {
            addCriterion("batch_code not in", values, "batch_code");
            return (Criteria) this;
        }

        public Criteria andBatch_codeBetween(String value1, String value2) {
            addCriterion("batch_code between", value1, value2, "batch_code");
            return (Criteria) this;
        }

        public Criteria andBatch_codeNotBetween(String value1, String value2) {
            addCriterion("batch_code not between", value1, value2, "batch_code");
            return (Criteria) this;
        }

        public Criteria andSales_typeIsNull() {
            addCriterion("sales_type is null");
            return (Criteria) this;
        }

        public Criteria andSales_typeIsNotNull() {
            addCriterion("sales_type is not null");
            return (Criteria) this;
        }

        public Criteria andSales_typeEqualTo(Byte value) {
            addCriterion("sales_type =", value, "sales_type");
            return (Criteria) this;
        }

        public Criteria andSales_typeNotEqualTo(Byte value) {
            addCriterion("sales_type <>", value, "sales_type");
            return (Criteria) this;
        }

        public Criteria andSales_typeGreaterThan(Byte value) {
            addCriterion("sales_type >", value, "sales_type");
            return (Criteria) this;
        }

        public Criteria andSales_typeGreaterThanOrEqualTo(Byte value) {
            addCriterion("sales_type >=", value, "sales_type");
            return (Criteria) this;
        }

        public Criteria andSales_typeLessThan(Byte value) {
            addCriterion("sales_type <", value, "sales_type");
            return (Criteria) this;
        }

        public Criteria andSales_typeLessThanOrEqualTo(Byte value) {
            addCriterion("sales_type <=", value, "sales_type");
            return (Criteria) this;
        }

        public Criteria andSales_typeIn(List<Byte> values) {
            addCriterion("sales_type in", values, "sales_type");
            return (Criteria) this;
        }

        public Criteria andSales_typeNotIn(List<Byte> values) {
            addCriterion("sales_type not in", values, "sales_type");
            return (Criteria) this;
        }

        public Criteria andSales_typeBetween(Byte value1, Byte value2) {
            addCriterion("sales_type between", value1, value2, "sales_type");
            return (Criteria) this;
        }

        public Criteria andSales_typeNotBetween(Byte value1, Byte value2) {
            addCriterion("sales_type not between", value1, value2, "sales_type");
            return (Criteria) this;
        }

        public Criteria andSales_propertyIsNull() {
            addCriterion("sales_property is null");
            return (Criteria) this;
        }

        public Criteria andSales_propertyIsNotNull() {
            addCriterion("sales_property is not null");
            return (Criteria) this;
        }

        public Criteria andSales_propertyEqualTo(String value) {
            addCriterion("sales_property =", value, "sales_property");
            return (Criteria) this;
        }

        public Criteria andSales_propertyNotEqualTo(String value) {
            addCriterion("sales_property <>", value, "sales_property");
            return (Criteria) this;
        }

        public Criteria andSales_propertyGreaterThan(String value) {
            addCriterion("sales_property >", value, "sales_property");
            return (Criteria) this;
        }

        public Criteria andSales_propertyGreaterThanOrEqualTo(String value) {
            addCriterion("sales_property >=", value, "sales_property");
            return (Criteria) this;
        }

        public Criteria andSales_propertyLessThan(String value) {
            addCriterion("sales_property <", value, "sales_property");
            return (Criteria) this;
        }

        public Criteria andSales_propertyLessThanOrEqualTo(String value) {
            addCriterion("sales_property <=", value, "sales_property");
            return (Criteria) this;
        }

        public Criteria andSales_propertyLike(String value) {
            addCriterion("sales_property like", value, "sales_property");
            return (Criteria) this;
        }

        public Criteria andSales_propertyNotLike(String value) {
            addCriterion("sales_property not like", value, "sales_property");
            return (Criteria) this;
        }

        public Criteria andSales_propertyIn(List<String> values) {
            addCriterion("sales_property in", values, "sales_property");
            return (Criteria) this;
        }

        public Criteria andSales_propertyNotIn(List<String> values) {
            addCriterion("sales_property not in", values, "sales_property");
            return (Criteria) this;
        }

        public Criteria andSales_propertyBetween(String value1, String value2) {
            addCriterion("sales_property between", value1, value2, "sales_property");
            return (Criteria) this;
        }

        public Criteria andSales_propertyNotBetween(String value1, String value2) {
            addCriterion("sales_property not between", value1, value2, "sales_property");
            return (Criteria) this;
        }

        public Criteria andColorIsNull() {
            addCriterion("color is null");
            return (Criteria) this;
        }

        public Criteria andColorIsNotNull() {
            addCriterion("color is not null");
            return (Criteria) this;
        }

        public Criteria andColorEqualTo(String value) {
            addCriterion("color =", value, "color");
            return (Criteria) this;
        }

        public Criteria andColorNotEqualTo(String value) {
            addCriterion("color <>", value, "color");
            return (Criteria) this;
        }

        public Criteria andColorGreaterThan(String value) {
            addCriterion("color >", value, "color");
            return (Criteria) this;
        }

        public Criteria andColorGreaterThanOrEqualTo(String value) {
            addCriterion("color >=", value, "color");
            return (Criteria) this;
        }

        public Criteria andColorLessThan(String value) {
            addCriterion("color <", value, "color");
            return (Criteria) this;
        }

        public Criteria andColorLessThanOrEqualTo(String value) {
            addCriterion("color <=", value, "color");
            return (Criteria) this;
        }

        public Criteria andColorLike(String value) {
            addCriterion("color like", value, "color");
            return (Criteria) this;
        }

        public Criteria andColorNotLike(String value) {
            addCriterion("color not like", value, "color");
            return (Criteria) this;
        }

        public Criteria andColorIn(List<String> values) {
            addCriterion("color in", values, "color");
            return (Criteria) this;
        }

        public Criteria andColorNotIn(List<String> values) {
            addCriterion("color not in", values, "color");
            return (Criteria) this;
        }

        public Criteria andColorBetween(String value1, String value2) {
            addCriterion("color between", value1, value2, "color");
            return (Criteria) this;
        }

        public Criteria andColorNotBetween(String value1, String value2) {
            addCriterion("color not between", value1, value2, "color");
            return (Criteria) this;
        }

        public Criteria andSizeIsNull() {
            addCriterion("size is null");
            return (Criteria) this;
        }

        public Criteria andSizeIsNotNull() {
            addCriterion("size is not null");
            return (Criteria) this;
        }

        public Criteria andSizeEqualTo(String value) {
            addCriterion("size =", value, "size");
            return (Criteria) this;
        }

        public Criteria andSizeNotEqualTo(String value) {
            addCriterion("size <>", value, "size");
            return (Criteria) this;
        }

        public Criteria andSizeGreaterThan(String value) {
            addCriterion("size >", value, "size");
            return (Criteria) this;
        }

        public Criteria andSizeGreaterThanOrEqualTo(String value) {
            addCriterion("size >=", value, "size");
            return (Criteria) this;
        }

        public Criteria andSizeLessThan(String value) {
            addCriterion("size <", value, "size");
            return (Criteria) this;
        }

        public Criteria andSizeLessThanOrEqualTo(String value) {
            addCriterion("size <=", value, "size");
            return (Criteria) this;
        }

        public Criteria andSizeLike(String value) {
            addCriterion("size like", value, "size");
            return (Criteria) this;
        }

        public Criteria andSizeNotLike(String value) {
            addCriterion("size not like", value, "size");
            return (Criteria) this;
        }

        public Criteria andSizeIn(List<String> values) {
            addCriterion("size in", values, "size");
            return (Criteria) this;
        }

        public Criteria andSizeNotIn(List<String> values) {
            addCriterion("size not in", values, "size");
            return (Criteria) this;
        }

        public Criteria andSizeBetween(String value1, String value2) {
            addCriterion("size between", value1, value2, "size");
            return (Criteria) this;
        }

        public Criteria andSizeNotBetween(String value1, String value2) {
            addCriterion("size not between", value1, value2, "size");
            return (Criteria) this;
        }

        public Criteria andStart_timeIsNull() {
            addCriterion("start_time is null");
            return (Criteria) this;
        }

        public Criteria andStart_timeIsNotNull() {
            addCriterion("start_time is not null");
            return (Criteria) this;
        }

        public Criteria andStart_timeEqualTo(Date value) {
            addCriterion("start_time =", value, "start_time");
            return (Criteria) this;
        }

        public Criteria andStart_timeNotEqualTo(Date value) {
            addCriterion("start_time <>", value, "start_time");
            return (Criteria) this;
        }

        public Criteria andStart_timeGreaterThan(Date value) {
            addCriterion("start_time >", value, "start_time");
            return (Criteria) this;
        }

        public Criteria andStart_timeGreaterThanOrEqualTo(Date value) {
            addCriterion("start_time >=", value, "start_time");
            return (Criteria) this;
        }

        public Criteria andStart_timeLessThan(Date value) {
            addCriterion("start_time <", value, "start_time");
            return (Criteria) this;
        }

        public Criteria andStart_timeLessThanOrEqualTo(Date value) {
            addCriterion("start_time <=", value, "start_time");
            return (Criteria) this;
        }

        public Criteria andStart_timeIn(List<Date> values) {
            addCriterion("start_time in", values, "start_time");
            return (Criteria) this;
        }

        public Criteria andStart_timeNotIn(List<Date> values) {
            addCriterion("start_time not in", values, "start_time");
            return (Criteria) this;
        }

        public Criteria andStart_timeBetween(Date value1, Date value2) {
            addCriterion("start_time between", value1, value2, "start_time");
            return (Criteria) this;
        }

        public Criteria andStart_timeNotBetween(Date value1, Date value2) {
            addCriterion("start_time not between", value1, value2, "start_time");
            return (Criteria) this;
        }

        public Criteria andEnd_timeIsNull() {
            addCriterion("end_time is null");
            return (Criteria) this;
        }

        public Criteria andEnd_timeIsNotNull() {
            addCriterion("end_time is not null");
            return (Criteria) this;
        }

        public Criteria andEnd_timeEqualTo(Date value) {
            addCriterion("end_time =", value, "end_time");
            return (Criteria) this;
        }

        public Criteria andEnd_timeNotEqualTo(Date value) {
            addCriterion("end_time <>", value, "end_time");
            return (Criteria) this;
        }

        public Criteria andEnd_timeGreaterThan(Date value) {
            addCriterion("end_time >", value, "end_time");
            return (Criteria) this;
        }

        public Criteria andEnd_timeGreaterThanOrEqualTo(Date value) {
            addCriterion("end_time >=", value, "end_time");
            return (Criteria) this;
        }

        public Criteria andEnd_timeLessThan(Date value) {
            addCriterion("end_time <", value, "end_time");
            return (Criteria) this;
        }

        public Criteria andEnd_timeLessThanOrEqualTo(Date value) {
            addCriterion("end_time <=", value, "end_time");
            return (Criteria) this;
        }

        public Criteria andEnd_timeIn(List<Date> values) {
            addCriterion("end_time in", values, "end_time");
            return (Criteria) this;
        }

        public Criteria andEnd_timeNotIn(List<Date> values) {
            addCriterion("end_time not in", values, "end_time");
            return (Criteria) this;
        }

        public Criteria andEnd_timeBetween(Date value1, Date value2) {
            addCriterion("end_time between", value1, value2, "end_time");
            return (Criteria) this;
        }

        public Criteria andEnd_timeNotBetween(Date value1, Date value2) {
            addCriterion("end_time not between", value1, value2, "end_time");
            return (Criteria) this;
        }

        public Criteria andQuantityIsNull() {
            addCriterion("quantity is null");
            return (Criteria) this;
        }

        public Criteria andQuantityIsNotNull() {
            addCriterion("quantity is not null");
            return (Criteria) this;
        }

        public Criteria andQuantityEqualTo(Integer value) {
            addCriterion("quantity =", value, "quantity");
            return (Criteria) this;
        }

        public Criteria andQuantityNotEqualTo(Integer value) {
            addCriterion("quantity <>", value, "quantity");
            return (Criteria) this;
        }

        public Criteria andQuantityGreaterThan(Integer value) {
            addCriterion("quantity >", value, "quantity");
            return (Criteria) this;
        }

        public Criteria andQuantityGreaterThanOrEqualTo(Integer value) {
            addCriterion("quantity >=", value, "quantity");
            return (Criteria) this;
        }

        public Criteria andQuantityLessThan(Integer value) {
            addCriterion("quantity <", value, "quantity");
            return (Criteria) this;
        }

        public Criteria andQuantityLessThanOrEqualTo(Integer value) {
            addCriterion("quantity <=", value, "quantity");
            return (Criteria) this;
        }

        public Criteria andQuantityIn(List<Integer> values) {
            addCriterion("quantity in", values, "quantity");
            return (Criteria) this;
        }

        public Criteria andQuantityNotIn(List<Integer> values) {
            addCriterion("quantity not in", values, "quantity");
            return (Criteria) this;
        }

        public Criteria andQuantityBetween(Integer value1, Integer value2) {
            addCriterion("quantity between", value1, value2, "quantity");
            return (Criteria) this;
        }

        public Criteria andQuantityNotBetween(Integer value1, Integer value2) {
            addCriterion("quantity not between", value1, value2, "quantity");
            return (Criteria) this;
        }

        public Criteria andUnit_quantityIsNull() {
            addCriterion("unit_quantity is null");
            return (Criteria) this;
        }

        public Criteria andUnit_quantityIsNotNull() {
            addCriterion("unit_quantity is not null");
            return (Criteria) this;
        }

        public Criteria andUnit_quantityEqualTo(Integer value) {
            addCriterion("unit_quantity =", value, "unit_quantity");
            return (Criteria) this;
        }

        public Criteria andUnit_quantityNotEqualTo(Integer value) {
            addCriterion("unit_quantity <>", value, "unit_quantity");
            return (Criteria) this;
        }

        public Criteria andUnit_quantityGreaterThan(Integer value) {
            addCriterion("unit_quantity >", value, "unit_quantity");
            return (Criteria) this;
        }

        public Criteria andUnit_quantityGreaterThanOrEqualTo(Integer value) {
            addCriterion("unit_quantity >=", value, "unit_quantity");
            return (Criteria) this;
        }

        public Criteria andUnit_quantityLessThan(Integer value) {
            addCriterion("unit_quantity <", value, "unit_quantity");
            return (Criteria) this;
        }

        public Criteria andUnit_quantityLessThanOrEqualTo(Integer value) {
            addCriterion("unit_quantity <=", value, "unit_quantity");
            return (Criteria) this;
        }

        public Criteria andUnit_quantityIn(List<Integer> values) {
            addCriterion("unit_quantity in", values, "unit_quantity");
            return (Criteria) this;
        }

        public Criteria andUnit_quantityNotIn(List<Integer> values) {
            addCriterion("unit_quantity not in", values, "unit_quantity");
            return (Criteria) this;
        }

        public Criteria andUnit_quantityBetween(Integer value1, Integer value2) {
            addCriterion("unit_quantity between", value1, value2, "unit_quantity");
            return (Criteria) this;
        }

        public Criteria andUnit_quantityNotBetween(Integer value1, Integer value2) {
            addCriterion("unit_quantity not between", value1, value2, "unit_quantity");
            return (Criteria) this;
        }

        public Criteria andWrap_quantityIsNull() {
            addCriterion("wrap_quantity is null");
            return (Criteria) this;
        }

        public Criteria andWrap_quantityIsNotNull() {
            addCriterion("wrap_quantity is not null");
            return (Criteria) this;
        }

        public Criteria andWrap_quantityEqualTo(Integer value) {
            addCriterion("wrap_quantity =", value, "wrap_quantity");
            return (Criteria) this;
        }

        public Criteria andWrap_quantityNotEqualTo(Integer value) {
            addCriterion("wrap_quantity <>", value, "wrap_quantity");
            return (Criteria) this;
        }

        public Criteria andWrap_quantityGreaterThan(Integer value) {
            addCriterion("wrap_quantity >", value, "wrap_quantity");
            return (Criteria) this;
        }

        public Criteria andWrap_quantityGreaterThanOrEqualTo(Integer value) {
            addCriterion("wrap_quantity >=", value, "wrap_quantity");
            return (Criteria) this;
        }

        public Criteria andWrap_quantityLessThan(Integer value) {
            addCriterion("wrap_quantity <", value, "wrap_quantity");
            return (Criteria) this;
        }

        public Criteria andWrap_quantityLessThanOrEqualTo(Integer value) {
            addCriterion("wrap_quantity <=", value, "wrap_quantity");
            return (Criteria) this;
        }

        public Criteria andWrap_quantityIn(List<Integer> values) {
            addCriterion("wrap_quantity in", values, "wrap_quantity");
            return (Criteria) this;
        }

        public Criteria andWrap_quantityNotIn(List<Integer> values) {
            addCriterion("wrap_quantity not in", values, "wrap_quantity");
            return (Criteria) this;
        }

        public Criteria andWrap_quantityBetween(Integer value1, Integer value2) {
            addCriterion("wrap_quantity between", value1, value2, "wrap_quantity");
            return (Criteria) this;
        }

        public Criteria andWrap_quantityNotBetween(Integer value1, Integer value2) {
            addCriterion("wrap_quantity not between", value1, value2, "wrap_quantity");
            return (Criteria) this;
        }

        public Criteria andList_priceIsNull() {
            addCriterion("list_price is null");
            return (Criteria) this;
        }

        public Criteria andList_priceIsNotNull() {
            addCriterion("list_price is not null");
            return (Criteria) this;
        }

        public Criteria andList_priceEqualTo(Double value) {
            addCriterion("list_price =", value, "list_price");
            return (Criteria) this;
        }

        public Criteria andList_priceNotEqualTo(Double value) {
            addCriterion("list_price <>", value, "list_price");
            return (Criteria) this;
        }

        public Criteria andList_priceGreaterThan(Double value) {
            addCriterion("list_price >", value, "list_price");
            return (Criteria) this;
        }

        public Criteria andList_priceGreaterThanOrEqualTo(Double value) {
            addCriterion("list_price >=", value, "list_price");
            return (Criteria) this;
        }

        public Criteria andList_priceLessThan(Double value) {
            addCriterion("list_price <", value, "list_price");
            return (Criteria) this;
        }

        public Criteria andList_priceLessThanOrEqualTo(Double value) {
            addCriterion("list_price <=", value, "list_price");
            return (Criteria) this;
        }

        public Criteria andList_priceIn(List<Double> values) {
            addCriterion("list_price in", values, "list_price");
            return (Criteria) this;
        }

        public Criteria andList_priceNotIn(List<Double> values) {
            addCriterion("list_price not in", values, "list_price");
            return (Criteria) this;
        }

        public Criteria andList_priceBetween(Double value1, Double value2) {
            addCriterion("list_price between", value1, value2, "list_price");
            return (Criteria) this;
        }

        public Criteria andList_priceNotBetween(Double value1, Double value2) {
            addCriterion("list_price not between", value1, value2, "list_price");
            return (Criteria) this;
        }

        public Criteria andSales_priceIsNull() {
            addCriterion("sales_price is null");
            return (Criteria) this;
        }

        public Criteria andSales_priceIsNotNull() {
            addCriterion("sales_price is not null");
            return (Criteria) this;
        }

        public Criteria andSales_priceEqualTo(Double value) {
            addCriterion("sales_price =", value, "sales_price");
            return (Criteria) this;
        }

        public Criteria andSales_priceNotEqualTo(Double value) {
            addCriterion("sales_price <>", value, "sales_price");
            return (Criteria) this;
        }

        public Criteria andSales_priceGreaterThan(Double value) {
            addCriterion("sales_price >", value, "sales_price");
            return (Criteria) this;
        }

        public Criteria andSales_priceGreaterThanOrEqualTo(Double value) {
            addCriterion("sales_price >=", value, "sales_price");
            return (Criteria) this;
        }

        public Criteria andSales_priceLessThan(Double value) {
            addCriterion("sales_price <", value, "sales_price");
            return (Criteria) this;
        }

        public Criteria andSales_priceLessThanOrEqualTo(Double value) {
            addCriterion("sales_price <=", value, "sales_price");
            return (Criteria) this;
        }

        public Criteria andSales_priceIn(List<Double> values) {
            addCriterion("sales_price in", values, "sales_price");
            return (Criteria) this;
        }

        public Criteria andSales_priceNotIn(List<Double> values) {
            addCriterion("sales_price not in", values, "sales_price");
            return (Criteria) this;
        }

        public Criteria andSales_priceBetween(Double value1, Double value2) {
            addCriterion("sales_price between", value1, value2, "sales_price");
            return (Criteria) this;
        }

        public Criteria andSales_priceNotBetween(Double value1, Double value2) {
            addCriterion("sales_price not between", value1, value2, "sales_price");
            return (Criteria) this;
        }

        public Criteria andPriceIsNull() {
            addCriterion("price is null");
            return (Criteria) this;
        }

        public Criteria andPriceIsNotNull() {
            addCriterion("price is not null");
            return (Criteria) this;
        }

        public Criteria andPriceEqualTo(Double value) {
            addCriterion("price =", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceNotEqualTo(Double value) {
            addCriterion("price <>", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceGreaterThan(Double value) {
            addCriterion("price >", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceGreaterThanOrEqualTo(Double value) {
            addCriterion("price >=", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceLessThan(Double value) {
            addCriterion("price <", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceLessThanOrEqualTo(Double value) {
            addCriterion("price <=", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceIn(List<Double> values) {
            addCriterion("price in", values, "price");
            return (Criteria) this;
        }

        public Criteria andPriceNotIn(List<Double> values) {
            addCriterion("price not in", values, "price");
            return (Criteria) this;
        }

        public Criteria andPriceBetween(Double value1, Double value2) {
            addCriterion("price between", value1, value2, "price");
            return (Criteria) this;
        }

        public Criteria andPriceNotBetween(Double value1, Double value2) {
            addCriterion("price not between", value1, value2, "price");
            return (Criteria) this;
        }

        public Criteria andSub_totalIsNull() {
            addCriterion("sub_total is null");
            return (Criteria) this;
        }

        public Criteria andSub_totalIsNotNull() {
            addCriterion("sub_total is not null");
            return (Criteria) this;
        }

        public Criteria andSub_totalEqualTo(Double value) {
            addCriterion("sub_total =", value, "sub_total");
            return (Criteria) this;
        }

        public Criteria andSub_totalNotEqualTo(Double value) {
            addCriterion("sub_total <>", value, "sub_total");
            return (Criteria) this;
        }

        public Criteria andSub_totalGreaterThan(Double value) {
            addCriterion("sub_total >", value, "sub_total");
            return (Criteria) this;
        }

        public Criteria andSub_totalGreaterThanOrEqualTo(Double value) {
            addCriterion("sub_total >=", value, "sub_total");
            return (Criteria) this;
        }

        public Criteria andSub_totalLessThan(Double value) {
            addCriterion("sub_total <", value, "sub_total");
            return (Criteria) this;
        }

        public Criteria andSub_totalLessThanOrEqualTo(Double value) {
            addCriterion("sub_total <=", value, "sub_total");
            return (Criteria) this;
        }

        public Criteria andSub_totalIn(List<Double> values) {
            addCriterion("sub_total in", values, "sub_total");
            return (Criteria) this;
        }

        public Criteria andSub_totalNotIn(List<Double> values) {
            addCriterion("sub_total not in", values, "sub_total");
            return (Criteria) this;
        }

        public Criteria andSub_totalBetween(Double value1, Double value2) {
            addCriterion("sub_total between", value1, value2, "sub_total");
            return (Criteria) this;
        }

        public Criteria andSub_totalNotBetween(Double value1, Double value2) {
            addCriterion("sub_total not between", value1, value2, "sub_total");
            return (Criteria) this;
        }

        public Criteria andOriginal_sub_totalIsNull() {
            addCriterion("original_sub_total is null");
            return (Criteria) this;
        }

        public Criteria andOriginal_sub_totalIsNotNull() {
            addCriterion("original_sub_total is not null");
            return (Criteria) this;
        }

        public Criteria andOriginal_sub_totalEqualTo(Double value) {
            addCriterion("original_sub_total =", value, "original_sub_total");
            return (Criteria) this;
        }

        public Criteria andOriginal_sub_totalNotEqualTo(Double value) {
            addCriterion("original_sub_total <>", value, "original_sub_total");
            return (Criteria) this;
        }

        public Criteria andOriginal_sub_totalGreaterThan(Double value) {
            addCriterion("original_sub_total >", value, "original_sub_total");
            return (Criteria) this;
        }

        public Criteria andOriginal_sub_totalGreaterThanOrEqualTo(Double value) {
            addCriterion("original_sub_total >=", value, "original_sub_total");
            return (Criteria) this;
        }

        public Criteria andOriginal_sub_totalLessThan(Double value) {
            addCriterion("original_sub_total <", value, "original_sub_total");
            return (Criteria) this;
        }

        public Criteria andOriginal_sub_totalLessThanOrEqualTo(Double value) {
            addCriterion("original_sub_total <=", value, "original_sub_total");
            return (Criteria) this;
        }

        public Criteria andOriginal_sub_totalIn(List<Double> values) {
            addCriterion("original_sub_total in", values, "original_sub_total");
            return (Criteria) this;
        }

        public Criteria andOriginal_sub_totalNotIn(List<Double> values) {
            addCriterion("original_sub_total not in", values, "original_sub_total");
            return (Criteria) this;
        }

        public Criteria andOriginal_sub_totalBetween(Double value1, Double value2) {
            addCriterion("original_sub_total between", value1, value2, "original_sub_total");
            return (Criteria) this;
        }

        public Criteria andOriginal_sub_totalNotBetween(Double value1, Double value2) {
            addCriterion("original_sub_total not between", value1, value2, "original_sub_total");
            return (Criteria) this;
        }

        public Criteria andOrig_freightIsNull() {
            addCriterion("orig_freight is null");
            return (Criteria) this;
        }

        public Criteria andOrig_freightIsNotNull() {
            addCriterion("orig_freight is not null");
            return (Criteria) this;
        }

        public Criteria andOrig_freightEqualTo(Double value) {
            addCriterion("orig_freight =", value, "orig_freight");
            return (Criteria) this;
        }

        public Criteria andOrig_freightNotEqualTo(Double value) {
            addCriterion("orig_freight <>", value, "orig_freight");
            return (Criteria) this;
        }

        public Criteria andOrig_freightGreaterThan(Double value) {
            addCriterion("orig_freight >", value, "orig_freight");
            return (Criteria) this;
        }

        public Criteria andOrig_freightGreaterThanOrEqualTo(Double value) {
            addCriterion("orig_freight >=", value, "orig_freight");
            return (Criteria) this;
        }

        public Criteria andOrig_freightLessThan(Double value) {
            addCriterion("orig_freight <", value, "orig_freight");
            return (Criteria) this;
        }

        public Criteria andOrig_freightLessThanOrEqualTo(Double value) {
            addCriterion("orig_freight <=", value, "orig_freight");
            return (Criteria) this;
        }

        public Criteria andOrig_freightIn(List<Double> values) {
            addCriterion("orig_freight in", values, "orig_freight");
            return (Criteria) this;
        }

        public Criteria andOrig_freightNotIn(List<Double> values) {
            addCriterion("orig_freight not in", values, "orig_freight");
            return (Criteria) this;
        }

        public Criteria andOrig_freightBetween(Double value1, Double value2) {
            addCriterion("orig_freight between", value1, value2, "orig_freight");
            return (Criteria) this;
        }

        public Criteria andOrig_freightNotBetween(Double value1, Double value2) {
            addCriterion("orig_freight not between", value1, value2, "orig_freight");
            return (Criteria) this;
        }

        public Criteria andOrig_tax_feeIsNull() {
            addCriterion("orig_tax_fee is null");
            return (Criteria) this;
        }

        public Criteria andOrig_tax_feeIsNotNull() {
            addCriterion("orig_tax_fee is not null");
            return (Criteria) this;
        }

        public Criteria andOrig_tax_feeEqualTo(Double value) {
            addCriterion("orig_tax_fee =", value, "orig_tax_fee");
            return (Criteria) this;
        }

        public Criteria andOrig_tax_feeNotEqualTo(Double value) {
            addCriterion("orig_tax_fee <>", value, "orig_tax_fee");
            return (Criteria) this;
        }

        public Criteria andOrig_tax_feeGreaterThan(Double value) {
            addCriterion("orig_tax_fee >", value, "orig_tax_fee");
            return (Criteria) this;
        }

        public Criteria andOrig_tax_feeGreaterThanOrEqualTo(Double value) {
            addCriterion("orig_tax_fee >=", value, "orig_tax_fee");
            return (Criteria) this;
        }

        public Criteria andOrig_tax_feeLessThan(Double value) {
            addCriterion("orig_tax_fee <", value, "orig_tax_fee");
            return (Criteria) this;
        }

        public Criteria andOrig_tax_feeLessThanOrEqualTo(Double value) {
            addCriterion("orig_tax_fee <=", value, "orig_tax_fee");
            return (Criteria) this;
        }

        public Criteria andOrig_tax_feeIn(List<Double> values) {
            addCriterion("orig_tax_fee in", values, "orig_tax_fee");
            return (Criteria) this;
        }

        public Criteria andOrig_tax_feeNotIn(List<Double> values) {
            addCriterion("orig_tax_fee not in", values, "orig_tax_fee");
            return (Criteria) this;
        }

        public Criteria andOrig_tax_feeBetween(Double value1, Double value2) {
            addCriterion("orig_tax_fee between", value1, value2, "orig_tax_fee");
            return (Criteria) this;
        }

        public Criteria andOrig_tax_feeNotBetween(Double value1, Double value2) {
            addCriterion("orig_tax_fee not between", value1, value2, "orig_tax_fee");
            return (Criteria) this;
        }

        public Criteria andItem_amountIsNull() {
            addCriterion("item_amount is null");
            return (Criteria) this;
        }

        public Criteria andItem_amountIsNotNull() {
            addCriterion("item_amount is not null");
            return (Criteria) this;
        }

        public Criteria andItem_amountEqualTo(Double value) {
            addCriterion("item_amount =", value, "item_amount");
            return (Criteria) this;
        }

        public Criteria andItem_amountNotEqualTo(Double value) {
            addCriterion("item_amount <>", value, "item_amount");
            return (Criteria) this;
        }

        public Criteria andItem_amountGreaterThan(Double value) {
            addCriterion("item_amount >", value, "item_amount");
            return (Criteria) this;
        }

        public Criteria andItem_amountGreaterThanOrEqualTo(Double value) {
            addCriterion("item_amount >=", value, "item_amount");
            return (Criteria) this;
        }

        public Criteria andItem_amountLessThan(Double value) {
            addCriterion("item_amount <", value, "item_amount");
            return (Criteria) this;
        }

        public Criteria andItem_amountLessThanOrEqualTo(Double value) {
            addCriterion("item_amount <=", value, "item_amount");
            return (Criteria) this;
        }

        public Criteria andItem_amountIn(List<Double> values) {
            addCriterion("item_amount in", values, "item_amount");
            return (Criteria) this;
        }

        public Criteria andItem_amountNotIn(List<Double> values) {
            addCriterion("item_amount not in", values, "item_amount");
            return (Criteria) this;
        }

        public Criteria andItem_amountBetween(Double value1, Double value2) {
            addCriterion("item_amount between", value1, value2, "item_amount");
            return (Criteria) this;
        }

        public Criteria andItem_amountNotBetween(Double value1, Double value2) {
            addCriterion("item_amount not between", value1, value2, "item_amount");
            return (Criteria) this;
        }

        public Criteria andFreightIsNull() {
            addCriterion("freight is null");
            return (Criteria) this;
        }

        public Criteria andFreightIsNotNull() {
            addCriterion("freight is not null");
            return (Criteria) this;
        }

        public Criteria andFreightEqualTo(Double value) {
            addCriterion("freight =", value, "freight");
            return (Criteria) this;
        }

        public Criteria andFreightNotEqualTo(Double value) {
            addCriterion("freight <>", value, "freight");
            return (Criteria) this;
        }

        public Criteria andFreightGreaterThan(Double value) {
            addCriterion("freight >", value, "freight");
            return (Criteria) this;
        }

        public Criteria andFreightGreaterThanOrEqualTo(Double value) {
            addCriterion("freight >=", value, "freight");
            return (Criteria) this;
        }

        public Criteria andFreightLessThan(Double value) {
            addCriterion("freight <", value, "freight");
            return (Criteria) this;
        }

        public Criteria andFreightLessThanOrEqualTo(Double value) {
            addCriterion("freight <=", value, "freight");
            return (Criteria) this;
        }

        public Criteria andFreightIn(List<Double> values) {
            addCriterion("freight in", values, "freight");
            return (Criteria) this;
        }

        public Criteria andFreightNotIn(List<Double> values) {
            addCriterion("freight not in", values, "freight");
            return (Criteria) this;
        }

        public Criteria andFreightBetween(Double value1, Double value2) {
            addCriterion("freight between", value1, value2, "freight");
            return (Criteria) this;
        }

        public Criteria andFreightNotBetween(Double value1, Double value2) {
            addCriterion("freight not between", value1, value2, "freight");
            return (Criteria) this;
        }

        public Criteria andPointsIsNull() {
            addCriterion("points is null");
            return (Criteria) this;
        }

        public Criteria andPointsIsNotNull() {
            addCriterion("points is not null");
            return (Criteria) this;
        }

        public Criteria andPointsEqualTo(Double value) {
            addCriterion("points =", value, "points");
            return (Criteria) this;
        }

        public Criteria andPointsNotEqualTo(Double value) {
            addCriterion("points <>", value, "points");
            return (Criteria) this;
        }

        public Criteria andPointsGreaterThan(Double value) {
            addCriterion("points >", value, "points");
            return (Criteria) this;
        }

        public Criteria andPointsGreaterThanOrEqualTo(Double value) {
            addCriterion("points >=", value, "points");
            return (Criteria) this;
        }

        public Criteria andPointsLessThan(Double value) {
            addCriterion("points <", value, "points");
            return (Criteria) this;
        }

        public Criteria andPointsLessThanOrEqualTo(Double value) {
            addCriterion("points <=", value, "points");
            return (Criteria) this;
        }

        public Criteria andPointsIn(List<Double> values) {
            addCriterion("points in", values, "points");
            return (Criteria) this;
        }

        public Criteria andPointsNotIn(List<Double> values) {
            addCriterion("points not in", values, "points");
            return (Criteria) this;
        }

        public Criteria andPointsBetween(Double value1, Double value2) {
            addCriterion("points between", value1, value2, "points");
            return (Criteria) this;
        }

        public Criteria andPointsNotBetween(Double value1, Double value2) {
            addCriterion("points not between", value1, value2, "points");
            return (Criteria) this;
        }

        public Criteria andWeightIsNull() {
            addCriterion("weight is null");
            return (Criteria) this;
        }

        public Criteria andWeightIsNotNull() {
            addCriterion("weight is not null");
            return (Criteria) this;
        }

        public Criteria andWeightEqualTo(Double value) {
            addCriterion("weight =", value, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightNotEqualTo(Double value) {
            addCriterion("weight <>", value, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightGreaterThan(Double value) {
            addCriterion("weight >", value, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightGreaterThanOrEqualTo(Double value) {
            addCriterion("weight >=", value, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightLessThan(Double value) {
            addCriterion("weight <", value, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightLessThanOrEqualTo(Double value) {
            addCriterion("weight <=", value, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightIn(List<Double> values) {
            addCriterion("weight in", values, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightNotIn(List<Double> values) {
            addCriterion("weight not in", values, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightBetween(Double value1, Double value2) {
            addCriterion("weight between", value1, value2, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightNotBetween(Double value1, Double value2) {
            addCriterion("weight not between", value1, value2, "weight");
            return (Criteria) this;
        }

        public Criteria andWeight_netIsNull() {
            addCriterion("weight_net is null");
            return (Criteria) this;
        }

        public Criteria andWeight_netIsNotNull() {
            addCriterion("weight_net is not null");
            return (Criteria) this;
        }

        public Criteria andWeight_netEqualTo(Double value) {
            addCriterion("weight_net =", value, "weight_net");
            return (Criteria) this;
        }

        public Criteria andWeight_netNotEqualTo(Double value) {
            addCriterion("weight_net <>", value, "weight_net");
            return (Criteria) this;
        }

        public Criteria andWeight_netGreaterThan(Double value) {
            addCriterion("weight_net >", value, "weight_net");
            return (Criteria) this;
        }

        public Criteria andWeight_netGreaterThanOrEqualTo(Double value) {
            addCriterion("weight_net >=", value, "weight_net");
            return (Criteria) this;
        }

        public Criteria andWeight_netLessThan(Double value) {
            addCriterion("weight_net <", value, "weight_net");
            return (Criteria) this;
        }

        public Criteria andWeight_netLessThanOrEqualTo(Double value) {
            addCriterion("weight_net <=", value, "weight_net");
            return (Criteria) this;
        }

        public Criteria andWeight_netIn(List<Double> values) {
            addCriterion("weight_net in", values, "weight_net");
            return (Criteria) this;
        }

        public Criteria andWeight_netNotIn(List<Double> values) {
            addCriterion("weight_net not in", values, "weight_net");
            return (Criteria) this;
        }

        public Criteria andWeight_netBetween(Double value1, Double value2) {
            addCriterion("weight_net between", value1, value2, "weight_net");
            return (Criteria) this;
        }

        public Criteria andWeight_netNotBetween(Double value1, Double value2) {
            addCriterion("weight_net not between", value1, value2, "weight_net");
            return (Criteria) this;
        }

        public Criteria andRefund_statusIsNull() {
            addCriterion("refund_status is null");
            return (Criteria) this;
        }

        public Criteria andRefund_statusIsNotNull() {
            addCriterion("refund_status is not null");
            return (Criteria) this;
        }

        public Criteria andRefund_statusEqualTo(Byte value) {
            addCriterion("refund_status =", value, "refund_status");
            return (Criteria) this;
        }

        public Criteria andRefund_statusNotEqualTo(Byte value) {
            addCriterion("refund_status <>", value, "refund_status");
            return (Criteria) this;
        }

        public Criteria andRefund_statusGreaterThan(Byte value) {
            addCriterion("refund_status >", value, "refund_status");
            return (Criteria) this;
        }

        public Criteria andRefund_statusGreaterThanOrEqualTo(Byte value) {
            addCriterion("refund_status >=", value, "refund_status");
            return (Criteria) this;
        }

        public Criteria andRefund_statusLessThan(Byte value) {
            addCriterion("refund_status <", value, "refund_status");
            return (Criteria) this;
        }

        public Criteria andRefund_statusLessThanOrEqualTo(Byte value) {
            addCriterion("refund_status <=", value, "refund_status");
            return (Criteria) this;
        }

        public Criteria andRefund_statusIn(List<Byte> values) {
            addCriterion("refund_status in", values, "refund_status");
            return (Criteria) this;
        }

        public Criteria andRefund_statusNotIn(List<Byte> values) {
            addCriterion("refund_status not in", values, "refund_status");
            return (Criteria) this;
        }

        public Criteria andRefund_statusBetween(Byte value1, Byte value2) {
            addCriterion("refund_status between", value1, value2, "refund_status");
            return (Criteria) this;
        }

        public Criteria andRefund_statusNotBetween(Byte value1, Byte value2) {
            addCriterion("refund_status not between", value1, value2, "refund_status");
            return (Criteria) this;
        }

        public Criteria andTax_rateIsNull() {
            addCriterion("tax_rate is null");
            return (Criteria) this;
        }

        public Criteria andTax_rateIsNotNull() {
            addCriterion("tax_rate is not null");
            return (Criteria) this;
        }

        public Criteria andTax_rateEqualTo(Double value) {
            addCriterion("tax_rate =", value, "tax_rate");
            return (Criteria) this;
        }

        public Criteria andTax_rateNotEqualTo(Double value) {
            addCriterion("tax_rate <>", value, "tax_rate");
            return (Criteria) this;
        }

        public Criteria andTax_rateGreaterThan(Double value) {
            addCriterion("tax_rate >", value, "tax_rate");
            return (Criteria) this;
        }

        public Criteria andTax_rateGreaterThanOrEqualTo(Double value) {
            addCriterion("tax_rate >=", value, "tax_rate");
            return (Criteria) this;
        }

        public Criteria andTax_rateLessThan(Double value) {
            addCriterion("tax_rate <", value, "tax_rate");
            return (Criteria) this;
        }

        public Criteria andTax_rateLessThanOrEqualTo(Double value) {
            addCriterion("tax_rate <=", value, "tax_rate");
            return (Criteria) this;
        }

        public Criteria andTax_rateIn(List<Double> values) {
            addCriterion("tax_rate in", values, "tax_rate");
            return (Criteria) this;
        }

        public Criteria andTax_rateNotIn(List<Double> values) {
            addCriterion("tax_rate not in", values, "tax_rate");
            return (Criteria) this;
        }

        public Criteria andTax_rateBetween(Double value1, Double value2) {
            addCriterion("tax_rate between", value1, value2, "tax_rate");
            return (Criteria) this;
        }

        public Criteria andTax_rateNotBetween(Double value1, Double value2) {
            addCriterion("tax_rate not between", value1, value2, "tax_rate");
            return (Criteria) this;
        }

        public Criteria andAdded_value_rateIsNull() {
            addCriterion("added_value_rate is null");
            return (Criteria) this;
        }

        public Criteria andAdded_value_rateIsNotNull() {
            addCriterion("added_value_rate is not null");
            return (Criteria) this;
        }

        public Criteria andAdded_value_rateEqualTo(Float value) {
            addCriterion("added_value_rate =", value, "added_value_rate");
            return (Criteria) this;
        }

        public Criteria andAdded_value_rateNotEqualTo(Float value) {
            addCriterion("added_value_rate <>", value, "added_value_rate");
            return (Criteria) this;
        }

        public Criteria andAdded_value_rateGreaterThan(Float value) {
            addCriterion("added_value_rate >", value, "added_value_rate");
            return (Criteria) this;
        }

        public Criteria andAdded_value_rateGreaterThanOrEqualTo(Float value) {
            addCriterion("added_value_rate >=", value, "added_value_rate");
            return (Criteria) this;
        }

        public Criteria andAdded_value_rateLessThan(Float value) {
            addCriterion("added_value_rate <", value, "added_value_rate");
            return (Criteria) this;
        }

        public Criteria andAdded_value_rateLessThanOrEqualTo(Float value) {
            addCriterion("added_value_rate <=", value, "added_value_rate");
            return (Criteria) this;
        }

        public Criteria andAdded_value_rateIn(List<Float> values) {
            addCriterion("added_value_rate in", values, "added_value_rate");
            return (Criteria) this;
        }

        public Criteria andAdded_value_rateNotIn(List<Float> values) {
            addCriterion("added_value_rate not in", values, "added_value_rate");
            return (Criteria) this;
        }

        public Criteria andAdded_value_rateBetween(Float value1, Float value2) {
            addCriterion("added_value_rate between", value1, value2, "added_value_rate");
            return (Criteria) this;
        }

        public Criteria andAdded_value_rateNotBetween(Float value1, Float value2) {
            addCriterion("added_value_rate not between", value1, value2, "added_value_rate");
            return (Criteria) this;
        }

        public Criteria andExcise_rateIsNull() {
            addCriterion("excise_rate is null");
            return (Criteria) this;
        }

        public Criteria andExcise_rateIsNotNull() {
            addCriterion("excise_rate is not null");
            return (Criteria) this;
        }

        public Criteria andExcise_rateEqualTo(Float value) {
            addCriterion("excise_rate =", value, "excise_rate");
            return (Criteria) this;
        }

        public Criteria andExcise_rateNotEqualTo(Float value) {
            addCriterion("excise_rate <>", value, "excise_rate");
            return (Criteria) this;
        }

        public Criteria andExcise_rateGreaterThan(Float value) {
            addCriterion("excise_rate >", value, "excise_rate");
            return (Criteria) this;
        }

        public Criteria andExcise_rateGreaterThanOrEqualTo(Float value) {
            addCriterion("excise_rate >=", value, "excise_rate");
            return (Criteria) this;
        }

        public Criteria andExcise_rateLessThan(Float value) {
            addCriterion("excise_rate <", value, "excise_rate");
            return (Criteria) this;
        }

        public Criteria andExcise_rateLessThanOrEqualTo(Float value) {
            addCriterion("excise_rate <=", value, "excise_rate");
            return (Criteria) this;
        }

        public Criteria andExcise_rateIn(List<Float> values) {
            addCriterion("excise_rate in", values, "excise_rate");
            return (Criteria) this;
        }

        public Criteria andExcise_rateNotIn(List<Float> values) {
            addCriterion("excise_rate not in", values, "excise_rate");
            return (Criteria) this;
        }

        public Criteria andExcise_rateBetween(Float value1, Float value2) {
            addCriterion("excise_rate between", value1, value2, "excise_rate");
            return (Criteria) this;
        }

        public Criteria andExcise_rateNotBetween(Float value1, Float value2) {
            addCriterion("excise_rate not between", value1, value2, "excise_rate");
            return (Criteria) this;
        }

        public Criteria andCustoms_rateIsNull() {
            addCriterion("customs_rate is null");
            return (Criteria) this;
        }

        public Criteria andCustoms_rateIsNotNull() {
            addCriterion("customs_rate is not null");
            return (Criteria) this;
        }

        public Criteria andCustoms_rateEqualTo(Float value) {
            addCriterion("customs_rate =", value, "customs_rate");
            return (Criteria) this;
        }

        public Criteria andCustoms_rateNotEqualTo(Float value) {
            addCriterion("customs_rate <>", value, "customs_rate");
            return (Criteria) this;
        }

        public Criteria andCustoms_rateGreaterThan(Float value) {
            addCriterion("customs_rate >", value, "customs_rate");
            return (Criteria) this;
        }

        public Criteria andCustoms_rateGreaterThanOrEqualTo(Float value) {
            addCriterion("customs_rate >=", value, "customs_rate");
            return (Criteria) this;
        }

        public Criteria andCustoms_rateLessThan(Float value) {
            addCriterion("customs_rate <", value, "customs_rate");
            return (Criteria) this;
        }

        public Criteria andCustoms_rateLessThanOrEqualTo(Float value) {
            addCriterion("customs_rate <=", value, "customs_rate");
            return (Criteria) this;
        }

        public Criteria andCustoms_rateIn(List<Float> values) {
            addCriterion("customs_rate in", values, "customs_rate");
            return (Criteria) this;
        }

        public Criteria andCustoms_rateNotIn(List<Float> values) {
            addCriterion("customs_rate not in", values, "customs_rate");
            return (Criteria) this;
        }

        public Criteria andCustoms_rateBetween(Float value1, Float value2) {
            addCriterion("customs_rate between", value1, value2, "customs_rate");
            return (Criteria) this;
        }

        public Criteria andCustoms_rateNotBetween(Float value1, Float value2) {
            addCriterion("customs_rate not between", value1, value2, "customs_rate");
            return (Criteria) this;
        }

        public Criteria andParcel_tax_idIsNull() {
            addCriterion("parcel_tax_id is null");
            return (Criteria) this;
        }

        public Criteria andParcel_tax_idIsNotNull() {
            addCriterion("parcel_tax_id is not null");
            return (Criteria) this;
        }

        public Criteria andParcel_tax_idEqualTo(String value) {
            addCriterion("parcel_tax_id =", value, "parcel_tax_id");
            return (Criteria) this;
        }

        public Criteria andParcel_tax_idNotEqualTo(String value) {
            addCriterion("parcel_tax_id <>", value, "parcel_tax_id");
            return (Criteria) this;
        }

        public Criteria andParcel_tax_idGreaterThan(String value) {
            addCriterion("parcel_tax_id >", value, "parcel_tax_id");
            return (Criteria) this;
        }

        public Criteria andParcel_tax_idGreaterThanOrEqualTo(String value) {
            addCriterion("parcel_tax_id >=", value, "parcel_tax_id");
            return (Criteria) this;
        }

        public Criteria andParcel_tax_idLessThan(String value) {
            addCriterion("parcel_tax_id <", value, "parcel_tax_id");
            return (Criteria) this;
        }

        public Criteria andParcel_tax_idLessThanOrEqualTo(String value) {
            addCriterion("parcel_tax_id <=", value, "parcel_tax_id");
            return (Criteria) this;
        }

        public Criteria andParcel_tax_idLike(String value) {
            addCriterion("parcel_tax_id like", value, "parcel_tax_id");
            return (Criteria) this;
        }

        public Criteria andParcel_tax_idNotLike(String value) {
            addCriterion("parcel_tax_id not like", value, "parcel_tax_id");
            return (Criteria) this;
        }

        public Criteria andParcel_tax_idIn(List<String> values) {
            addCriterion("parcel_tax_id in", values, "parcel_tax_id");
            return (Criteria) this;
        }

        public Criteria andParcel_tax_idNotIn(List<String> values) {
            addCriterion("parcel_tax_id not in", values, "parcel_tax_id");
            return (Criteria) this;
        }

        public Criteria andParcel_tax_idBetween(String value1, String value2) {
            addCriterion("parcel_tax_id between", value1, value2, "parcel_tax_id");
            return (Criteria) this;
        }

        public Criteria andParcel_tax_idNotBetween(String value1, String value2) {
            addCriterion("parcel_tax_id not between", value1, value2, "parcel_tax_id");
            return (Criteria) this;
        }

        public Criteria andIpIsNull() {
            addCriterion("ip is null");
            return (Criteria) this;
        }

        public Criteria andIpIsNotNull() {
            addCriterion("ip is not null");
            return (Criteria) this;
        }

        public Criteria andIpEqualTo(String value) {
            addCriterion("ip =", value, "ip");
            return (Criteria) this;
        }

        public Criteria andIpNotEqualTo(String value) {
            addCriterion("ip <>", value, "ip");
            return (Criteria) this;
        }

        public Criteria andIpGreaterThan(String value) {
            addCriterion("ip >", value, "ip");
            return (Criteria) this;
        }

        public Criteria andIpGreaterThanOrEqualTo(String value) {
            addCriterion("ip >=", value, "ip");
            return (Criteria) this;
        }

        public Criteria andIpLessThan(String value) {
            addCriterion("ip <", value, "ip");
            return (Criteria) this;
        }

        public Criteria andIpLessThanOrEqualTo(String value) {
            addCriterion("ip <=", value, "ip");
            return (Criteria) this;
        }

        public Criteria andIpLike(String value) {
            addCriterion("ip like", value, "ip");
            return (Criteria) this;
        }

        public Criteria andIpNotLike(String value) {
            addCriterion("ip not like", value, "ip");
            return (Criteria) this;
        }

        public Criteria andIpIn(List<String> values) {
            addCriterion("ip in", values, "ip");
            return (Criteria) this;
        }

        public Criteria andIpNotIn(List<String> values) {
            addCriterion("ip not in", values, "ip");
            return (Criteria) this;
        }

        public Criteria andIpBetween(String value1, String value2) {
            addCriterion("ip between", value1, value2, "ip");
            return (Criteria) this;
        }

        public Criteria andIpNotBetween(String value1, String value2) {
            addCriterion("ip not between", value1, value2, "ip");
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

        public Criteria andProduct_codeIsNull() {
            addCriterion("product_code is null");
            return (Criteria) this;
        }

        public Criteria andProduct_codeIsNotNull() {
            addCriterion("product_code is not null");
            return (Criteria) this;
        }

        public Criteria andProduct_codeEqualTo(String value) {
            addCriterion("product_code =", value, "product_code");
            return (Criteria) this;
        }

        public Criteria andProduct_codeNotEqualTo(String value) {
            addCriterion("product_code <>", value, "product_code");
            return (Criteria) this;
        }

        public Criteria andProduct_codeGreaterThan(String value) {
            addCriterion("product_code >", value, "product_code");
            return (Criteria) this;
        }

        public Criteria andProduct_codeGreaterThanOrEqualTo(String value) {
            addCriterion("product_code >=", value, "product_code");
            return (Criteria) this;
        }

        public Criteria andProduct_codeLessThan(String value) {
            addCriterion("product_code <", value, "product_code");
            return (Criteria) this;
        }

        public Criteria andProduct_codeLessThanOrEqualTo(String value) {
            addCriterion("product_code <=", value, "product_code");
            return (Criteria) this;
        }

        public Criteria andProduct_codeLike(String value) {
            addCriterion("product_code like", value, "product_code");
            return (Criteria) this;
        }

        public Criteria andProduct_codeNotLike(String value) {
            addCriterion("product_code not like", value, "product_code");
            return (Criteria) this;
        }

        public Criteria andProduct_codeIn(List<String> values) {
            addCriterion("product_code in", values, "product_code");
            return (Criteria) this;
        }

        public Criteria andProduct_codeNotIn(List<String> values) {
            addCriterion("product_code not in", values, "product_code");
            return (Criteria) this;
        }

        public Criteria andProduct_codeBetween(String value1, String value2) {
            addCriterion("product_code between", value1, value2, "product_code");
            return (Criteria) this;
        }

        public Criteria andProduct_codeNotBetween(String value1, String value2) {
            addCriterion("product_code not between", value1, value2, "product_code");
            return (Criteria) this;
        }

        public Criteria andTax_feeIsNull() {
            addCriterion("tax_fee is null");
            return (Criteria) this;
        }

        public Criteria andTax_feeIsNotNull() {
            addCriterion("tax_fee is not null");
            return (Criteria) this;
        }

        public Criteria andTax_feeEqualTo(Double value) {
            addCriterion("tax_fee =", value, "tax_fee");
            return (Criteria) this;
        }

        public Criteria andTax_feeNotEqualTo(Double value) {
            addCriterion("tax_fee <>", value, "tax_fee");
            return (Criteria) this;
        }

        public Criteria andTax_feeGreaterThan(Double value) {
            addCriterion("tax_fee >", value, "tax_fee");
            return (Criteria) this;
        }

        public Criteria andTax_feeGreaterThanOrEqualTo(Double value) {
            addCriterion("tax_fee >=", value, "tax_fee");
            return (Criteria) this;
        }

        public Criteria andTax_feeLessThan(Double value) {
            addCriterion("tax_fee <", value, "tax_fee");
            return (Criteria) this;
        }

        public Criteria andTax_feeLessThanOrEqualTo(Double value) {
            addCriterion("tax_fee <=", value, "tax_fee");
            return (Criteria) this;
        }

        public Criteria andTax_feeIn(List<Double> values) {
            addCriterion("tax_fee in", values, "tax_fee");
            return (Criteria) this;
        }

        public Criteria andTax_feeNotIn(List<Double> values) {
            addCriterion("tax_fee not in", values, "tax_fee");
            return (Criteria) this;
        }

        public Criteria andTax_feeBetween(Double value1, Double value2) {
            addCriterion("tax_fee between", value1, value2, "tax_fee");
            return (Criteria) this;
        }

        public Criteria andTax_feeNotBetween(Double value1, Double value2) {
            addCriterion("tax_fee not between", value1, value2, "tax_fee");
            return (Criteria) this;
        }

        public Criteria andRefund_daysIsNull() {
            addCriterion("refund_days is null");
            return (Criteria) this;
        }

        public Criteria andRefund_daysIsNotNull() {
            addCriterion("refund_days is not null");
            return (Criteria) this;
        }

        public Criteria andRefund_daysEqualTo(Integer value) {
            addCriterion("refund_days =", value, "refund_days");
            return (Criteria) this;
        }

        public Criteria andRefund_daysNotEqualTo(Integer value) {
            addCriterion("refund_days <>", value, "refund_days");
            return (Criteria) this;
        }

        public Criteria andRefund_daysGreaterThan(Integer value) {
            addCriterion("refund_days >", value, "refund_days");
            return (Criteria) this;
        }

        public Criteria andRefund_daysGreaterThanOrEqualTo(Integer value) {
            addCriterion("refund_days >=", value, "refund_days");
            return (Criteria) this;
        }

        public Criteria andRefund_daysLessThan(Integer value) {
            addCriterion("refund_days <", value, "refund_days");
            return (Criteria) this;
        }

        public Criteria andRefund_daysLessThanOrEqualTo(Integer value) {
            addCriterion("refund_days <=", value, "refund_days");
            return (Criteria) this;
        }

        public Criteria andRefund_daysIn(List<Integer> values) {
            addCriterion("refund_days in", values, "refund_days");
            return (Criteria) this;
        }

        public Criteria andRefund_daysNotIn(List<Integer> values) {
            addCriterion("refund_days not in", values, "refund_days");
            return (Criteria) this;
        }

        public Criteria andRefund_daysBetween(Integer value1, Integer value2) {
            addCriterion("refund_days between", value1, value2, "refund_days");
            return (Criteria) this;
        }

        public Criteria andRefund_daysNotBetween(Integer value1, Integer value2) {
            addCriterion("refund_days not between", value1, value2, "refund_days");
            return (Criteria) this;
        }

        public Criteria andCommision_rateIsNull() {
            addCriterion("commision_rate is null");
            return (Criteria) this;
        }

        public Criteria andCommision_rateIsNotNull() {
            addCriterion("commision_rate is not null");
            return (Criteria) this;
        }

        public Criteria andCommision_rateEqualTo(Double value) {
            addCriterion("commision_rate =", value, "commision_rate");
            return (Criteria) this;
        }

        public Criteria andCommision_rateNotEqualTo(Double value) {
            addCriterion("commision_rate <>", value, "commision_rate");
            return (Criteria) this;
        }

        public Criteria andCommision_rateGreaterThan(Double value) {
            addCriterion("commision_rate >", value, "commision_rate");
            return (Criteria) this;
        }

        public Criteria andCommision_rateGreaterThanOrEqualTo(Double value) {
            addCriterion("commision_rate >=", value, "commision_rate");
            return (Criteria) this;
        }

        public Criteria andCommision_rateLessThan(Double value) {
            addCriterion("commision_rate <", value, "commision_rate");
            return (Criteria) this;
        }

        public Criteria andCommision_rateLessThanOrEqualTo(Double value) {
            addCriterion("commision_rate <=", value, "commision_rate");
            return (Criteria) this;
        }

        public Criteria andCommision_rateIn(List<Double> values) {
            addCriterion("commision_rate in", values, "commision_rate");
            return (Criteria) this;
        }

        public Criteria andCommision_rateNotIn(List<Double> values) {
            addCriterion("commision_rate not in", values, "commision_rate");
            return (Criteria) this;
        }

        public Criteria andCommision_rateBetween(Double value1, Double value2) {
            addCriterion("commision_rate between", value1, value2, "commision_rate");
            return (Criteria) this;
        }

        public Criteria andCommision_rateNotBetween(Double value1, Double value2) {
            addCriterion("commision_rate not between", value1, value2, "commision_rate");
            return (Criteria) this;
        }

        public Criteria andUnitIsNull() {
            addCriterion("unit is null");
            return (Criteria) this;
        }

        public Criteria andUnitIsNotNull() {
            addCriterion("unit is not null");
            return (Criteria) this;
        }

        public Criteria andUnitEqualTo(String value) {
            addCriterion("unit =", value, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitNotEqualTo(String value) {
            addCriterion("unit <>", value, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitGreaterThan(String value) {
            addCriterion("unit >", value, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitGreaterThanOrEqualTo(String value) {
            addCriterion("unit >=", value, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitLessThan(String value) {
            addCriterion("unit <", value, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitLessThanOrEqualTo(String value) {
            addCriterion("unit <=", value, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitLike(String value) {
            addCriterion("unit like", value, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitNotLike(String value) {
            addCriterion("unit not like", value, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitIn(List<String> values) {
            addCriterion("unit in", values, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitNotIn(List<String> values) {
            addCriterion("unit not in", values, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitBetween(String value1, String value2) {
            addCriterion("unit between", value1, value2, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitNotBetween(String value1, String value2) {
            addCriterion("unit not between", value1, value2, "unit");
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

        public Criteria andUnit_idEqualTo(Long value) {
            addCriterion("unit_id =", value, "unit_id");
            return (Criteria) this;
        }

        public Criteria andUnit_idNotEqualTo(Long value) {
            addCriterion("unit_id <>", value, "unit_id");
            return (Criteria) this;
        }

        public Criteria andUnit_idGreaterThan(Long value) {
            addCriterion("unit_id >", value, "unit_id");
            return (Criteria) this;
        }

        public Criteria andUnit_idGreaterThanOrEqualTo(Long value) {
            addCriterion("unit_id >=", value, "unit_id");
            return (Criteria) this;
        }

        public Criteria andUnit_idLessThan(Long value) {
            addCriterion("unit_id <", value, "unit_id");
            return (Criteria) this;
        }

        public Criteria andUnit_idLessThanOrEqualTo(Long value) {
            addCriterion("unit_id <=", value, "unit_id");
            return (Criteria) this;
        }

        public Criteria andUnit_idIn(List<Long> values) {
            addCriterion("unit_id in", values, "unit_id");
            return (Criteria) this;
        }

        public Criteria andUnit_idNotIn(List<Long> values) {
            addCriterion("unit_id not in", values, "unit_id");
            return (Criteria) this;
        }

        public Criteria andUnit_idBetween(Long value1, Long value2) {
            addCriterion("unit_id between", value1, value2, "unit_id");
            return (Criteria) this;
        }

        public Criteria andUnit_idNotBetween(Long value1, Long value2) {
            addCriterion("unit_id not between", value1, value2, "unit_id");
            return (Criteria) this;
        }

        public Criteria andPlatform_rateIsNull() {
            addCriterion("platform_rate is null");
            return (Criteria) this;
        }

        public Criteria andPlatform_rateIsNotNull() {
            addCriterion("platform_rate is not null");
            return (Criteria) this;
        }

        public Criteria andPlatform_rateEqualTo(Double value) {
            addCriterion("platform_rate =", value, "platform_rate");
            return (Criteria) this;
        }

        public Criteria andPlatform_rateNotEqualTo(Double value) {
            addCriterion("platform_rate <>", value, "platform_rate");
            return (Criteria) this;
        }

        public Criteria andPlatform_rateGreaterThan(Double value) {
            addCriterion("platform_rate >", value, "platform_rate");
            return (Criteria) this;
        }

        public Criteria andPlatform_rateGreaterThanOrEqualTo(Double value) {
            addCriterion("platform_rate >=", value, "platform_rate");
            return (Criteria) this;
        }

        public Criteria andPlatform_rateLessThan(Double value) {
            addCriterion("platform_rate <", value, "platform_rate");
            return (Criteria) this;
        }

        public Criteria andPlatform_rateLessThanOrEqualTo(Double value) {
            addCriterion("platform_rate <=", value, "platform_rate");
            return (Criteria) this;
        }

        public Criteria andPlatform_rateIn(List<Double> values) {
            addCriterion("platform_rate in", values, "platform_rate");
            return (Criteria) this;
        }

        public Criteria andPlatform_rateNotIn(List<Double> values) {
            addCriterion("platform_rate not in", values, "platform_rate");
            return (Criteria) this;
        }

        public Criteria andPlatform_rateBetween(Double value1, Double value2) {
            addCriterion("platform_rate between", value1, value2, "platform_rate");
            return (Criteria) this;
        }

        public Criteria andPlatform_rateNotBetween(Double value1, Double value2) {
            addCriterion("platform_rate not between", value1, value2, "platform_rate");
            return (Criteria) this;
        }

        public Criteria andSettle_statusIsNull() {
            addCriterion("settle_status is null");
            return (Criteria) this;
        }

        public Criteria andSettle_statusIsNotNull() {
            addCriterion("settle_status is not null");
            return (Criteria) this;
        }

        public Criteria andSettle_statusEqualTo(Byte value) {
            addCriterion("settle_status =", value, "settle_status");
            return (Criteria) this;
        }

        public Criteria andSettle_statusNotEqualTo(Byte value) {
            addCriterion("settle_status <>", value, "settle_status");
            return (Criteria) this;
        }

        public Criteria andSettle_statusGreaterThan(Byte value) {
            addCriterion("settle_status >", value, "settle_status");
            return (Criteria) this;
        }

        public Criteria andSettle_statusGreaterThanOrEqualTo(Byte value) {
            addCriterion("settle_status >=", value, "settle_status");
            return (Criteria) this;
        }

        public Criteria andSettle_statusLessThan(Byte value) {
            addCriterion("settle_status <", value, "settle_status");
            return (Criteria) this;
        }

        public Criteria andSettle_statusLessThanOrEqualTo(Byte value) {
            addCriterion("settle_status <=", value, "settle_status");
            return (Criteria) this;
        }

        public Criteria andSettle_statusIn(List<Byte> values) {
            addCriterion("settle_status in", values, "settle_status");
            return (Criteria) this;
        }

        public Criteria andSettle_statusNotIn(List<Byte> values) {
            addCriterion("settle_status not in", values, "settle_status");
            return (Criteria) this;
        }

        public Criteria andSettle_statusBetween(Byte value1, Byte value2) {
            addCriterion("settle_status between", value1, value2, "settle_status");
            return (Criteria) this;
        }

        public Criteria andSettle_statusNotBetween(Byte value1, Byte value2) {
            addCriterion("settle_status not between", value1, value2, "settle_status");
            return (Criteria) this;
        }

        public Criteria andOrder_coupon_amountIsNull() {
            addCriterion("order_coupon_amount is null");
            return (Criteria) this;
        }

        public Criteria andOrder_coupon_amountIsNotNull() {
            addCriterion("order_coupon_amount is not null");
            return (Criteria) this;
        }

        public Criteria andOrder_coupon_amountEqualTo(Double value) {
            addCriterion("order_coupon_amount =", value, "order_coupon_amount");
            return (Criteria) this;
        }

        public Criteria andOrder_coupon_amountNotEqualTo(Double value) {
            addCriterion("order_coupon_amount <>", value, "order_coupon_amount");
            return (Criteria) this;
        }

        public Criteria andOrder_coupon_amountGreaterThan(Double value) {
            addCriterion("order_coupon_amount >", value, "order_coupon_amount");
            return (Criteria) this;
        }

        public Criteria andOrder_coupon_amountGreaterThanOrEqualTo(Double value) {
            addCriterion("order_coupon_amount >=", value, "order_coupon_amount");
            return (Criteria) this;
        }

        public Criteria andOrder_coupon_amountLessThan(Double value) {
            addCriterion("order_coupon_amount <", value, "order_coupon_amount");
            return (Criteria) this;
        }

        public Criteria andOrder_coupon_amountLessThanOrEqualTo(Double value) {
            addCriterion("order_coupon_amount <=", value, "order_coupon_amount");
            return (Criteria) this;
        }

        public Criteria andOrder_coupon_amountIn(List<Double> values) {
            addCriterion("order_coupon_amount in", values, "order_coupon_amount");
            return (Criteria) this;
        }

        public Criteria andOrder_coupon_amountNotIn(List<Double> values) {
            addCriterion("order_coupon_amount not in", values, "order_coupon_amount");
            return (Criteria) this;
        }

        public Criteria andOrder_coupon_amountBetween(Double value1, Double value2) {
            addCriterion("order_coupon_amount between", value1, value2, "order_coupon_amount");
            return (Criteria) this;
        }

        public Criteria andOrder_coupon_amountNotBetween(Double value1, Double value2) {
            addCriterion("order_coupon_amount not between", value1, value2, "order_coupon_amount");
            return (Criteria) this;
        }

        public Criteria andCoupon_amountIsNull() {
            addCriterion("coupon_amount is null");
            return (Criteria) this;
        }

        public Criteria andCoupon_amountIsNotNull() {
            addCriterion("coupon_amount is not null");
            return (Criteria) this;
        }

        public Criteria andCoupon_amountEqualTo(Double value) {
            addCriterion("coupon_amount =", value, "coupon_amount");
            return (Criteria) this;
        }

        public Criteria andCoupon_amountNotEqualTo(Double value) {
            addCriterion("coupon_amount <>", value, "coupon_amount");
            return (Criteria) this;
        }

        public Criteria andCoupon_amountGreaterThan(Double value) {
            addCriterion("coupon_amount >", value, "coupon_amount");
            return (Criteria) this;
        }

        public Criteria andCoupon_amountGreaterThanOrEqualTo(Double value) {
            addCriterion("coupon_amount >=", value, "coupon_amount");
            return (Criteria) this;
        }

        public Criteria andCoupon_amountLessThan(Double value) {
            addCriterion("coupon_amount <", value, "coupon_amount");
            return (Criteria) this;
        }

        public Criteria andCoupon_amountLessThanOrEqualTo(Double value) {
            addCriterion("coupon_amount <=", value, "coupon_amount");
            return (Criteria) this;
        }

        public Criteria andCoupon_amountIn(List<Double> values) {
            addCriterion("coupon_amount in", values, "coupon_amount");
            return (Criteria) this;
        }

        public Criteria andCoupon_amountNotIn(List<Double> values) {
            addCriterion("coupon_amount not in", values, "coupon_amount");
            return (Criteria) this;
        }

        public Criteria andCoupon_amountBetween(Double value1, Double value2) {
            addCriterion("coupon_amount between", value1, value2, "coupon_amount");
            return (Criteria) this;
        }

        public Criteria andCoupon_amountNotBetween(Double value1, Double value2) {
            addCriterion("coupon_amount not between", value1, value2, "coupon_amount");
            return (Criteria) this;
        }

        public Criteria andContract_idIsNull() {
            addCriterion("contract_id is null");
            return (Criteria) this;
        }

        public Criteria andContract_idIsNotNull() {
            addCriterion("contract_id is not null");
            return (Criteria) this;
        }

        public Criteria andContract_idEqualTo(Long value) {
            addCriterion("contract_id =", value, "contract_id");
            return (Criteria) this;
        }

        public Criteria andContract_idNotEqualTo(Long value) {
            addCriterion("contract_id <>", value, "contract_id");
            return (Criteria) this;
        }

        public Criteria andContract_idGreaterThan(Long value) {
            addCriterion("contract_id >", value, "contract_id");
            return (Criteria) this;
        }

        public Criteria andContract_idGreaterThanOrEqualTo(Long value) {
            addCriterion("contract_id >=", value, "contract_id");
            return (Criteria) this;
        }

        public Criteria andContract_idLessThan(Long value) {
            addCriterion("contract_id <", value, "contract_id");
            return (Criteria) this;
        }

        public Criteria andContract_idLessThanOrEqualTo(Long value) {
            addCriterion("contract_id <=", value, "contract_id");
            return (Criteria) this;
        }

        public Criteria andContract_idIn(List<Long> values) {
            addCriterion("contract_id in", values, "contract_id");
            return (Criteria) this;
        }

        public Criteria andContract_idNotIn(List<Long> values) {
            addCriterion("contract_id not in", values, "contract_id");
            return (Criteria) this;
        }

        public Criteria andContract_idBetween(Long value1, Long value2) {
            addCriterion("contract_id between", value1, value2, "contract_id");
            return (Criteria) this;
        }

        public Criteria andContract_idNotBetween(Long value1, Long value2) {
            addCriterion("contract_id not between", value1, value2, "contract_id");
            return (Criteria) this;
        }

        public Criteria andCountry_idIsNull() {
            addCriterion("country_id is null");
            return (Criteria) this;
        }

        public Criteria andCountry_idIsNotNull() {
            addCriterion("country_id is not null");
            return (Criteria) this;
        }

        public Criteria andCountry_idEqualTo(Long value) {
            addCriterion("country_id =", value, "country_id");
            return (Criteria) this;
        }

        public Criteria andCountry_idNotEqualTo(Long value) {
            addCriterion("country_id <>", value, "country_id");
            return (Criteria) this;
        }

        public Criteria andCountry_idGreaterThan(Long value) {
            addCriterion("country_id >", value, "country_id");
            return (Criteria) this;
        }

        public Criteria andCountry_idGreaterThanOrEqualTo(Long value) {
            addCriterion("country_id >=", value, "country_id");
            return (Criteria) this;
        }

        public Criteria andCountry_idLessThan(Long value) {
            addCriterion("country_id <", value, "country_id");
            return (Criteria) this;
        }

        public Criteria andCountry_idLessThanOrEqualTo(Long value) {
            addCriterion("country_id <=", value, "country_id");
            return (Criteria) this;
        }

        public Criteria andCountry_idIn(List<Long> values) {
            addCriterion("country_id in", values, "country_id");
            return (Criteria) this;
        }

        public Criteria andCountry_idNotIn(List<Long> values) {
            addCriterion("country_id not in", values, "country_id");
            return (Criteria) this;
        }

        public Criteria andCountry_idBetween(Long value1, Long value2) {
            addCriterion("country_id between", value1, value2, "country_id");
            return (Criteria) this;
        }

        public Criteria andCountry_idNotBetween(Long value1, Long value2) {
            addCriterion("country_id not between", value1, value2, "country_id");
            return (Criteria) this;
        }

        public Criteria andItem_condition_couponIsNull() {
            addCriterion("item_condition_coupon is null");
            return (Criteria) this;
        }

        public Criteria andItem_condition_couponIsNotNull() {
            addCriterion("item_condition_coupon is not null");
            return (Criteria) this;
        }

        public Criteria andItem_condition_couponEqualTo(Double value) {
            addCriterion("item_condition_coupon =", value, "item_condition_coupon");
            return (Criteria) this;
        }

        public Criteria andItem_condition_couponNotEqualTo(Double value) {
            addCriterion("item_condition_coupon <>", value, "item_condition_coupon");
            return (Criteria) this;
        }

        public Criteria andItem_condition_couponGreaterThan(Double value) {
            addCriterion("item_condition_coupon >", value, "item_condition_coupon");
            return (Criteria) this;
        }

        public Criteria andItem_condition_couponGreaterThanOrEqualTo(Double value) {
            addCriterion("item_condition_coupon >=", value, "item_condition_coupon");
            return (Criteria) this;
        }

        public Criteria andItem_condition_couponLessThan(Double value) {
            addCriterion("item_condition_coupon <", value, "item_condition_coupon");
            return (Criteria) this;
        }

        public Criteria andItem_condition_couponLessThanOrEqualTo(Double value) {
            addCriterion("item_condition_coupon <=", value, "item_condition_coupon");
            return (Criteria) this;
        }

        public Criteria andItem_condition_couponIn(List<Double> values) {
            addCriterion("item_condition_coupon in", values, "item_condition_coupon");
            return (Criteria) this;
        }

        public Criteria andItem_condition_couponNotIn(List<Double> values) {
            addCriterion("item_condition_coupon not in", values, "item_condition_coupon");
            return (Criteria) this;
        }

        public Criteria andItem_condition_couponBetween(Double value1, Double value2) {
            addCriterion("item_condition_coupon between", value1, value2, "item_condition_coupon");
            return (Criteria) this;
        }

        public Criteria andItem_condition_couponNotBetween(Double value1, Double value2) {
            addCriterion("item_condition_coupon not between", value1, value2, "item_condition_coupon");
            return (Criteria) this;
        }

        public Criteria andItem_couponIsNull() {
            addCriterion("item_coupon is null");
            return (Criteria) this;
        }

        public Criteria andItem_couponIsNotNull() {
            addCriterion("item_coupon is not null");
            return (Criteria) this;
        }

        public Criteria andItem_couponEqualTo(Double value) {
            addCriterion("item_coupon =", value, "item_coupon");
            return (Criteria) this;
        }

        public Criteria andItem_couponNotEqualTo(Double value) {
            addCriterion("item_coupon <>", value, "item_coupon");
            return (Criteria) this;
        }

        public Criteria andItem_couponGreaterThan(Double value) {
            addCriterion("item_coupon >", value, "item_coupon");
            return (Criteria) this;
        }

        public Criteria andItem_couponGreaterThanOrEqualTo(Double value) {
            addCriterion("item_coupon >=", value, "item_coupon");
            return (Criteria) this;
        }

        public Criteria andItem_couponLessThan(Double value) {
            addCriterion("item_coupon <", value, "item_coupon");
            return (Criteria) this;
        }

        public Criteria andItem_couponLessThanOrEqualTo(Double value) {
            addCriterion("item_coupon <=", value, "item_coupon");
            return (Criteria) this;
        }

        public Criteria andItem_couponIn(List<Double> values) {
            addCriterion("item_coupon in", values, "item_coupon");
            return (Criteria) this;
        }

        public Criteria andItem_couponNotIn(List<Double> values) {
            addCriterion("item_coupon not in", values, "item_coupon");
            return (Criteria) this;
        }

        public Criteria andItem_couponBetween(Double value1, Double value2) {
            addCriterion("item_coupon between", value1, value2, "item_coupon");
            return (Criteria) this;
        }

        public Criteria andItem_couponNotBetween(Double value1, Double value2) {
            addCriterion("item_coupon not between", value1, value2, "item_coupon");
            return (Criteria) this;
        }

        public Criteria andItem_pointIsNull() {
            addCriterion("item_point is null");
            return (Criteria) this;
        }

        public Criteria andItem_pointIsNotNull() {
            addCriterion("item_point is not null");
            return (Criteria) this;
        }

        public Criteria andItem_pointEqualTo(Double value) {
            addCriterion("item_point =", value, "item_point");
            return (Criteria) this;
        }

        public Criteria andItem_pointNotEqualTo(Double value) {
            addCriterion("item_point <>", value, "item_point");
            return (Criteria) this;
        }

        public Criteria andItem_pointGreaterThan(Double value) {
            addCriterion("item_point >", value, "item_point");
            return (Criteria) this;
        }

        public Criteria andItem_pointGreaterThanOrEqualTo(Double value) {
            addCriterion("item_point >=", value, "item_point");
            return (Criteria) this;
        }

        public Criteria andItem_pointLessThan(Double value) {
            addCriterion("item_point <", value, "item_point");
            return (Criteria) this;
        }

        public Criteria andItem_pointLessThanOrEqualTo(Double value) {
            addCriterion("item_point <=", value, "item_point");
            return (Criteria) this;
        }

        public Criteria andItem_pointIn(List<Double> values) {
            addCriterion("item_point in", values, "item_point");
            return (Criteria) this;
        }

        public Criteria andItem_pointNotIn(List<Double> values) {
            addCriterion("item_point not in", values, "item_point");
            return (Criteria) this;
        }

        public Criteria andItem_pointBetween(Double value1, Double value2) {
            addCriterion("item_point between", value1, value2, "item_point");
            return (Criteria) this;
        }

        public Criteria andItem_pointNotBetween(Double value1, Double value2) {
            addCriterion("item_point not between", value1, value2, "item_point");
            return (Criteria) this;
        }

        public Criteria andFreight_couponIsNull() {
            addCriterion("freight_coupon is null");
            return (Criteria) this;
        }

        public Criteria andFreight_couponIsNotNull() {
            addCriterion("freight_coupon is not null");
            return (Criteria) this;
        }

        public Criteria andFreight_couponEqualTo(Double value) {
            addCriterion("freight_coupon =", value, "freight_coupon");
            return (Criteria) this;
        }

        public Criteria andFreight_couponNotEqualTo(Double value) {
            addCriterion("freight_coupon <>", value, "freight_coupon");
            return (Criteria) this;
        }

        public Criteria andFreight_couponGreaterThan(Double value) {
            addCriterion("freight_coupon >", value, "freight_coupon");
            return (Criteria) this;
        }

        public Criteria andFreight_couponGreaterThanOrEqualTo(Double value) {
            addCriterion("freight_coupon >=", value, "freight_coupon");
            return (Criteria) this;
        }

        public Criteria andFreight_couponLessThan(Double value) {
            addCriterion("freight_coupon <", value, "freight_coupon");
            return (Criteria) this;
        }

        public Criteria andFreight_couponLessThanOrEqualTo(Double value) {
            addCriterion("freight_coupon <=", value, "freight_coupon");
            return (Criteria) this;
        }

        public Criteria andFreight_couponIn(List<Double> values) {
            addCriterion("freight_coupon in", values, "freight_coupon");
            return (Criteria) this;
        }

        public Criteria andFreight_couponNotIn(List<Double> values) {
            addCriterion("freight_coupon not in", values, "freight_coupon");
            return (Criteria) this;
        }

        public Criteria andFreight_couponBetween(Double value1, Double value2) {
            addCriterion("freight_coupon between", value1, value2, "freight_coupon");
            return (Criteria) this;
        }

        public Criteria andFreight_couponNotBetween(Double value1, Double value2) {
            addCriterion("freight_coupon not between", value1, value2, "freight_coupon");
            return (Criteria) this;
        }

        public Criteria andFreight_pointIsNull() {
            addCriterion("freight_point is null");
            return (Criteria) this;
        }

        public Criteria andFreight_pointIsNotNull() {
            addCriterion("freight_point is not null");
            return (Criteria) this;
        }

        public Criteria andFreight_pointEqualTo(Double value) {
            addCriterion("freight_point =", value, "freight_point");
            return (Criteria) this;
        }

        public Criteria andFreight_pointNotEqualTo(Double value) {
            addCriterion("freight_point <>", value, "freight_point");
            return (Criteria) this;
        }

        public Criteria andFreight_pointGreaterThan(Double value) {
            addCriterion("freight_point >", value, "freight_point");
            return (Criteria) this;
        }

        public Criteria andFreight_pointGreaterThanOrEqualTo(Double value) {
            addCriterion("freight_point >=", value, "freight_point");
            return (Criteria) this;
        }

        public Criteria andFreight_pointLessThan(Double value) {
            addCriterion("freight_point <", value, "freight_point");
            return (Criteria) this;
        }

        public Criteria andFreight_pointLessThanOrEqualTo(Double value) {
            addCriterion("freight_point <=", value, "freight_point");
            return (Criteria) this;
        }

        public Criteria andFreight_pointIn(List<Double> values) {
            addCriterion("freight_point in", values, "freight_point");
            return (Criteria) this;
        }

        public Criteria andFreight_pointNotIn(List<Double> values) {
            addCriterion("freight_point not in", values, "freight_point");
            return (Criteria) this;
        }

        public Criteria andFreight_pointBetween(Double value1, Double value2) {
            addCriterion("freight_point between", value1, value2, "freight_point");
            return (Criteria) this;
        }

        public Criteria andFreight_pointNotBetween(Double value1, Double value2) {
            addCriterion("freight_point not between", value1, value2, "freight_point");
            return (Criteria) this;
        }

        public Criteria andTax_couponIsNull() {
            addCriterion("tax_coupon is null");
            return (Criteria) this;
        }

        public Criteria andTax_couponIsNotNull() {
            addCriterion("tax_coupon is not null");
            return (Criteria) this;
        }

        public Criteria andTax_couponEqualTo(Double value) {
            addCriterion("tax_coupon =", value, "tax_coupon");
            return (Criteria) this;
        }

        public Criteria andTax_couponNotEqualTo(Double value) {
            addCriterion("tax_coupon <>", value, "tax_coupon");
            return (Criteria) this;
        }

        public Criteria andTax_couponGreaterThan(Double value) {
            addCriterion("tax_coupon >", value, "tax_coupon");
            return (Criteria) this;
        }

        public Criteria andTax_couponGreaterThanOrEqualTo(Double value) {
            addCriterion("tax_coupon >=", value, "tax_coupon");
            return (Criteria) this;
        }

        public Criteria andTax_couponLessThan(Double value) {
            addCriterion("tax_coupon <", value, "tax_coupon");
            return (Criteria) this;
        }

        public Criteria andTax_couponLessThanOrEqualTo(Double value) {
            addCriterion("tax_coupon <=", value, "tax_coupon");
            return (Criteria) this;
        }

        public Criteria andTax_couponIn(List<Double> values) {
            addCriterion("tax_coupon in", values, "tax_coupon");
            return (Criteria) this;
        }

        public Criteria andTax_couponNotIn(List<Double> values) {
            addCriterion("tax_coupon not in", values, "tax_coupon");
            return (Criteria) this;
        }

        public Criteria andTax_couponBetween(Double value1, Double value2) {
            addCriterion("tax_coupon between", value1, value2, "tax_coupon");
            return (Criteria) this;
        }

        public Criteria andTax_couponNotBetween(Double value1, Double value2) {
            addCriterion("tax_coupon not between", value1, value2, "tax_coupon");
            return (Criteria) this;
        }

        public Criteria andTax_pointIsNull() {
            addCriterion("tax_point is null");
            return (Criteria) this;
        }

        public Criteria andTax_pointIsNotNull() {
            addCriterion("tax_point is not null");
            return (Criteria) this;
        }

        public Criteria andTax_pointEqualTo(Double value) {
            addCriterion("tax_point =", value, "tax_point");
            return (Criteria) this;
        }

        public Criteria andTax_pointNotEqualTo(Double value) {
            addCriterion("tax_point <>", value, "tax_point");
            return (Criteria) this;
        }

        public Criteria andTax_pointGreaterThan(Double value) {
            addCriterion("tax_point >", value, "tax_point");
            return (Criteria) this;
        }

        public Criteria andTax_pointGreaterThanOrEqualTo(Double value) {
            addCriterion("tax_point >=", value, "tax_point");
            return (Criteria) this;
        }

        public Criteria andTax_pointLessThan(Double value) {
            addCriterion("tax_point <", value, "tax_point");
            return (Criteria) this;
        }

        public Criteria andTax_pointLessThanOrEqualTo(Double value) {
            addCriterion("tax_point <=", value, "tax_point");
            return (Criteria) this;
        }

        public Criteria andTax_pointIn(List<Double> values) {
            addCriterion("tax_point in", values, "tax_point");
            return (Criteria) this;
        }

        public Criteria andTax_pointNotIn(List<Double> values) {
            addCriterion("tax_point not in", values, "tax_point");
            return (Criteria) this;
        }

        public Criteria andTax_pointBetween(Double value1, Double value2) {
            addCriterion("tax_point between", value1, value2, "tax_point");
            return (Criteria) this;
        }

        public Criteria andTax_pointNotBetween(Double value1, Double value2) {
            addCriterion("tax_point not between", value1, value2, "tax_point");
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

        public Criteria andCdkeyIsNull() {
            addCriterion("cdkey is null");
            return (Criteria) this;
        }

        public Criteria andCdkeyIsNotNull() {
            addCriterion("cdkey is not null");
            return (Criteria) this;
        }

        public Criteria andCdkeyEqualTo(String value) {
            addCriterion("cdkey =", value, "cdkey");
            return (Criteria) this;
        }

        public Criteria andCdkeyNotEqualTo(String value) {
            addCriterion("cdkey <>", value, "cdkey");
            return (Criteria) this;
        }

        public Criteria andCdkeyGreaterThan(String value) {
            addCriterion("cdkey >", value, "cdkey");
            return (Criteria) this;
        }

        public Criteria andCdkeyGreaterThanOrEqualTo(String value) {
            addCriterion("cdkey >=", value, "cdkey");
            return (Criteria) this;
        }

        public Criteria andCdkeyLessThan(String value) {
            addCriterion("cdkey <", value, "cdkey");
            return (Criteria) this;
        }

        public Criteria andCdkeyLessThanOrEqualTo(String value) {
            addCriterion("cdkey <=", value, "cdkey");
            return (Criteria) this;
        }

        public Criteria andCdkeyLike(String value) {
            addCriterion("cdkey like", value, "cdkey");
            return (Criteria) this;
        }

        public Criteria andCdkeyNotLike(String value) {
            addCriterion("cdkey not like", value, "cdkey");
            return (Criteria) this;
        }

        public Criteria andCdkeyIn(List<String> values) {
            addCriterion("cdkey in", values, "cdkey");
            return (Criteria) this;
        }

        public Criteria andCdkeyNotIn(List<String> values) {
            addCriterion("cdkey not in", values, "cdkey");
            return (Criteria) this;
        }

        public Criteria andCdkeyBetween(String value1, String value2) {
            addCriterion("cdkey between", value1, value2, "cdkey");
            return (Criteria) this;
        }

        public Criteria andCdkeyNotBetween(String value1, String value2) {
            addCriterion("cdkey not between", value1, value2, "cdkey");
            return (Criteria) this;
        }

        public Criteria andDiscount_rateIsNull() {
            addCriterion("discount_rate is null");
            return (Criteria) this;
        }

        public Criteria andDiscount_rateIsNotNull() {
            addCriterion("discount_rate is not null");
            return (Criteria) this;
        }

        public Criteria andDiscount_rateEqualTo(Double value) {
            addCriterion("discount_rate =", value, "discount_rate");
            return (Criteria) this;
        }

        public Criteria andDiscount_rateNotEqualTo(Double value) {
            addCriterion("discount_rate <>", value, "discount_rate");
            return (Criteria) this;
        }

        public Criteria andDiscount_rateGreaterThan(Double value) {
            addCriterion("discount_rate >", value, "discount_rate");
            return (Criteria) this;
        }

        public Criteria andDiscount_rateGreaterThanOrEqualTo(Double value) {
            addCriterion("discount_rate >=", value, "discount_rate");
            return (Criteria) this;
        }

        public Criteria andDiscount_rateLessThan(Double value) {
            addCriterion("discount_rate <", value, "discount_rate");
            return (Criteria) this;
        }

        public Criteria andDiscount_rateLessThanOrEqualTo(Double value) {
            addCriterion("discount_rate <=", value, "discount_rate");
            return (Criteria) this;
        }

        public Criteria andDiscount_rateIn(List<Double> values) {
            addCriterion("discount_rate in", values, "discount_rate");
            return (Criteria) this;
        }

        public Criteria andDiscount_rateNotIn(List<Double> values) {
            addCriterion("discount_rate not in", values, "discount_rate");
            return (Criteria) this;
        }

        public Criteria andDiscount_rateBetween(Double value1, Double value2) {
            addCriterion("discount_rate between", value1, value2, "discount_rate");
            return (Criteria) this;
        }

        public Criteria andDiscount_rateNotBetween(Double value1, Double value2) {
            addCriterion("discount_rate not between", value1, value2, "discount_rate");
            return (Criteria) this;
        }

        public Criteria andDiscount_amountIsNull() {
            addCriterion("discount_amount is null");
            return (Criteria) this;
        }

        public Criteria andDiscount_amountIsNotNull() {
            addCriterion("discount_amount is not null");
            return (Criteria) this;
        }

        public Criteria andDiscount_amountEqualTo(Double value) {
            addCriterion("discount_amount =", value, "discount_amount");
            return (Criteria) this;
        }

        public Criteria andDiscount_amountNotEqualTo(Double value) {
            addCriterion("discount_amount <>", value, "discount_amount");
            return (Criteria) this;
        }

        public Criteria andDiscount_amountGreaterThan(Double value) {
            addCriterion("discount_amount >", value, "discount_amount");
            return (Criteria) this;
        }

        public Criteria andDiscount_amountGreaterThanOrEqualTo(Double value) {
            addCriterion("discount_amount >=", value, "discount_amount");
            return (Criteria) this;
        }

        public Criteria andDiscount_amountLessThan(Double value) {
            addCriterion("discount_amount <", value, "discount_amount");
            return (Criteria) this;
        }

        public Criteria andDiscount_amountLessThanOrEqualTo(Double value) {
            addCriterion("discount_amount <=", value, "discount_amount");
            return (Criteria) this;
        }

        public Criteria andDiscount_amountIn(List<Double> values) {
            addCriterion("discount_amount in", values, "discount_amount");
            return (Criteria) this;
        }

        public Criteria andDiscount_amountNotIn(List<Double> values) {
            addCriterion("discount_amount not in", values, "discount_amount");
            return (Criteria) this;
        }

        public Criteria andDiscount_amountBetween(Double value1, Double value2) {
            addCriterion("discount_amount between", value1, value2, "discount_amount");
            return (Criteria) this;
        }

        public Criteria andDiscount_amountNotBetween(Double value1, Double value2) {
            addCriterion("discount_amount not between", value1, value2, "discount_amount");
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