package com.lx.benefits.bean.entity.suborder;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SubOrderExample {
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

    public SubOrderExample() {
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

        public Criteria andOrder_statusIsNull() {
            addCriterion("order_status is null");
            return (Criteria) this;
        }

        public Criteria andOrder_statusIsNotNull() {
            addCriterion("order_status is not null");
            return (Criteria) this;
        }

        public Criteria andOrder_statusEqualTo(Integer value) {
            addCriterion("order_status =", value, "order_status");
            return (Criteria) this;
        }

        public Criteria andOrder_statusNotEqualTo(Byte value) {
            addCriterion("order_status <>", value, "order_status");
            return (Criteria) this;
        }

        public Criteria andOrder_statusGreaterThan(Byte value) {
            addCriterion("order_status >", value, "order_status");
            return (Criteria) this;
        }

        public Criteria andOrder_statusGreaterThanOrEqualTo(Byte value) {
            addCriterion("order_status >=", value, "order_status");
            return (Criteria) this;
        }

        public Criteria andOrder_statusLessThan(Byte value) {
            addCriterion("order_status <", value, "order_status");
            return (Criteria) this;
        }

        public Criteria andOrder_statusLessThanOrEqualTo(Byte value) {
            addCriterion("order_status <=", value, "order_status");
            return (Criteria) this;
        }

        public Criteria andOrder_statusIn(List<Byte> values) {
            addCriterion("order_status in", values, "order_status");
            return (Criteria) this;
        }

        public Criteria andOrder_statusNotIn(List<Byte> values) {
            addCriterion("order_status not in", values, "order_status");
            return (Criteria) this;
        }

        public Criteria andOrder_statusBetween(Byte value1, Byte value2) {
            addCriterion("order_status between", value1, value2, "order_status");
            return (Criteria) this;
        }

        public Criteria andOrder_statusNotBetween(Byte value1, Byte value2) {
            addCriterion("order_status not between", value1, value2, "order_status");
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

        public Criteria andTotalIsNull() {
            addCriterion("total is null");
            return (Criteria) this;
        }

        public Criteria andTotalIsNotNull() {
            addCriterion("total is not null");
            return (Criteria) this;
        }

        public Criteria andTotalEqualTo(Double value) {
            addCriterion("total =", value, "total");
            return (Criteria) this;
        }

        public Criteria andTotalNotEqualTo(Double value) {
            addCriterion("total <>", value, "total");
            return (Criteria) this;
        }

        public Criteria andTotalGreaterThan(Double value) {
            addCriterion("total >", value, "total");
            return (Criteria) this;
        }

        public Criteria andTotalGreaterThanOrEqualTo(Double value) {
            addCriterion("total >=", value, "total");
            return (Criteria) this;
        }

        public Criteria andTotalLessThan(Double value) {
            addCriterion("total <", value, "total");
            return (Criteria) this;
        }

        public Criteria andTotalLessThanOrEqualTo(Double value) {
            addCriterion("total <=", value, "total");
            return (Criteria) this;
        }

        public Criteria andTotalIn(List<Double> values) {
            addCriterion("total in", values, "total");
            return (Criteria) this;
        }

        public Criteria andTotalNotIn(List<Double> values) {
            addCriterion("total not in", values, "total");
            return (Criteria) this;
        }

        public Criteria andTotalBetween(Double value1, Double value2) {
            addCriterion("total between", value1, value2, "total");
            return (Criteria) this;
        }

        public Criteria andTotalNotBetween(Double value1, Double value2) {
            addCriterion("total not between", value1, value2, "total");
            return (Criteria) this;
        }

        public Criteria andItem_totalIsNull() {
            addCriterion("item_total is null");
            return (Criteria) this;
        }

        public Criteria andItem_totalIsNotNull() {
            addCriterion("item_total is not null");
            return (Criteria) this;
        }

        public Criteria andItem_totalEqualTo(Double value) {
            addCriterion("item_total =", value, "item_total");
            return (Criteria) this;
        }

        public Criteria andItem_totalNotEqualTo(Double value) {
            addCriterion("item_total <>", value, "item_total");
            return (Criteria) this;
        }

        public Criteria andItem_totalGreaterThan(Double value) {
            addCriterion("item_total >", value, "item_total");
            return (Criteria) this;
        }

        public Criteria andItem_totalGreaterThanOrEqualTo(Double value) {
            addCriterion("item_total >=", value, "item_total");
            return (Criteria) this;
        }

        public Criteria andItem_totalLessThan(Double value) {
            addCriterion("item_total <", value, "item_total");
            return (Criteria) this;
        }

        public Criteria andItem_totalLessThanOrEqualTo(Double value) {
            addCriterion("item_total <=", value, "item_total");
            return (Criteria) this;
        }

        public Criteria andItem_totalIn(List<Double> values) {
            addCriterion("item_total in", values, "item_total");
            return (Criteria) this;
        }

        public Criteria andItem_totalNotIn(List<Double> values) {
            addCriterion("item_total not in", values, "item_total");
            return (Criteria) this;
        }

        public Criteria andItem_totalBetween(Double value1, Double value2) {
            addCriterion("item_total between", value1, value2, "item_total");
            return (Criteria) this;
        }

        public Criteria andItem_totalNotBetween(Double value1, Double value2) {
            addCriterion("item_total not between", value1, value2, "item_total");
            return (Criteria) this;
        }

        public Criteria andOriginal_totalIsNull() {
            addCriterion("original_total is null");
            return (Criteria) this;
        }

        public Criteria andOriginal_totalIsNotNull() {
            addCriterion("original_total is not null");
            return (Criteria) this;
        }

        public Criteria andOriginal_totalEqualTo(Double value) {
            addCriterion("original_total =", value, "original_total");
            return (Criteria) this;
        }

        public Criteria andOriginal_totalNotEqualTo(Double value) {
            addCriterion("original_total <>", value, "original_total");
            return (Criteria) this;
        }

        public Criteria andOriginal_totalGreaterThan(Double value) {
            addCriterion("original_total >", value, "original_total");
            return (Criteria) this;
        }

        public Criteria andOriginal_totalGreaterThanOrEqualTo(Double value) {
            addCriterion("original_total >=", value, "original_total");
            return (Criteria) this;
        }

        public Criteria andOriginal_totalLessThan(Double value) {
            addCriterion("original_total <", value, "original_total");
            return (Criteria) this;
        }

        public Criteria andOriginal_totalLessThanOrEqualTo(Double value) {
            addCriterion("original_total <=", value, "original_total");
            return (Criteria) this;
        }

        public Criteria andOriginal_totalIn(List<Double> values) {
            addCriterion("original_total in", values, "original_total");
            return (Criteria) this;
        }

        public Criteria andOriginal_totalNotIn(List<Double> values) {
            addCriterion("original_total not in", values, "original_total");
            return (Criteria) this;
        }

        public Criteria andOriginal_totalBetween(Double value1, Double value2) {
            addCriterion("original_total between", value1, value2, "original_total");
            return (Criteria) this;
        }

        public Criteria andOriginal_totalNotBetween(Double value1, Double value2) {
            addCriterion("original_total not between", value1, value2, "original_total");
            return (Criteria) this;
        }

        public Criteria andDiscountIsNull() {
            addCriterion("discount is null");
            return (Criteria) this;
        }

        public Criteria andDiscountIsNotNull() {
            addCriterion("discount is not null");
            return (Criteria) this;
        }

        public Criteria andDiscountEqualTo(Double value) {
            addCriterion("discount =", value, "discount");
            return (Criteria) this;
        }

        public Criteria andDiscountNotEqualTo(Double value) {
            addCriterion("discount <>", value, "discount");
            return (Criteria) this;
        }

        public Criteria andDiscountGreaterThan(Double value) {
            addCriterion("discount >", value, "discount");
            return (Criteria) this;
        }

        public Criteria andDiscountGreaterThanOrEqualTo(Double value) {
            addCriterion("discount >=", value, "discount");
            return (Criteria) this;
        }

        public Criteria andDiscountLessThan(Double value) {
            addCriterion("discount <", value, "discount");
            return (Criteria) this;
        }

        public Criteria andDiscountLessThanOrEqualTo(Double value) {
            addCriterion("discount <=", value, "discount");
            return (Criteria) this;
        }

        public Criteria andDiscountIn(List<Double> values) {
            addCriterion("discount in", values, "discount");
            return (Criteria) this;
        }

        public Criteria andDiscountNotIn(List<Double> values) {
            addCriterion("discount not in", values, "discount");
            return (Criteria) this;
        }

        public Criteria andDiscountBetween(Double value1, Double value2) {
            addCriterion("discount between", value1, value2, "discount");
            return (Criteria) this;
        }

        public Criteria andDiscountNotBetween(Double value1, Double value2) {
            addCriterion("discount not between", value1, value2, "discount");
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

        public Criteria andBalanceIsNull() {
            addCriterion("balance is null");
            return (Criteria) this;
        }

        public Criteria andBalanceIsNotNull() {
            addCriterion("balance is not null");
            return (Criteria) this;
        }

        public Criteria andBalanceEqualTo(Double value) {
            addCriterion("balance =", value, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceNotEqualTo(Double value) {
            addCriterion("balance <>", value, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceGreaterThan(Double value) {
            addCriterion("balance >", value, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceGreaterThanOrEqualTo(Double value) {
            addCriterion("balance >=", value, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceLessThan(Double value) {
            addCriterion("balance <", value, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceLessThanOrEqualTo(Double value) {
            addCriterion("balance <=", value, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceIn(List<Double> values) {
            addCriterion("balance in", values, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceNotIn(List<Double> values) {
            addCriterion("balance not in", values, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceBetween(Double value1, Double value2) {
            addCriterion("balance between", value1, value2, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceNotBetween(Double value1, Double value2) {
            addCriterion("balance not between", value1, value2, "balance");
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

        public Criteria andSupplier_aliasIsNull() {
            addCriterion("supplier_alias is null");
            return (Criteria) this;
        }

        public Criteria andSupplier_aliasIsNotNull() {
            addCriterion("supplier_alias is not null");
            return (Criteria) this;
        }

        public Criteria andSupplier_aliasEqualTo(String value) {
            addCriterion("supplier_alias =", value, "supplier_alias");
            return (Criteria) this;
        }

        public Criteria andSupplier_aliasNotEqualTo(String value) {
            addCriterion("supplier_alias <>", value, "supplier_alias");
            return (Criteria) this;
        }

        public Criteria andSupplier_aliasGreaterThan(String value) {
            addCriterion("supplier_alias >", value, "supplier_alias");
            return (Criteria) this;
        }

        public Criteria andSupplier_aliasGreaterThanOrEqualTo(String value) {
            addCriterion("supplier_alias >=", value, "supplier_alias");
            return (Criteria) this;
        }

        public Criteria andSupplier_aliasLessThan(String value) {
            addCriterion("supplier_alias <", value, "supplier_alias");
            return (Criteria) this;
        }

        public Criteria andSupplier_aliasLessThanOrEqualTo(String value) {
            addCriterion("supplier_alias <=", value, "supplier_alias");
            return (Criteria) this;
        }

        public Criteria andSupplier_aliasLike(String value) {
            addCriterion("supplier_alias like", value, "supplier_alias");
            return (Criteria) this;
        }

        public Criteria andSupplier_aliasNotLike(String value) {
            addCriterion("supplier_alias not like", value, "supplier_alias");
            return (Criteria) this;
        }

        public Criteria andSupplier_aliasIn(List<String> values) {
            addCriterion("supplier_alias in", values, "supplier_alias");
            return (Criteria) this;
        }

        public Criteria andSupplier_aliasNotIn(List<String> values) {
            addCriterion("supplier_alias not in", values, "supplier_alias");
            return (Criteria) this;
        }

        public Criteria andSupplier_aliasBetween(String value1, String value2) {
            addCriterion("supplier_alias between", value1, value2, "supplier_alias");
            return (Criteria) this;
        }

        public Criteria andSupplier_aliasNotBetween(String value1, String value2) {
            addCriterion("supplier_alias not between", value1, value2, "supplier_alias");
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

        public Criteria andWarehouse_nameIsNull() {
            addCriterion("warehouse_name is null");
            return (Criteria) this;
        }

        public Criteria andWarehouse_nameIsNotNull() {
            addCriterion("warehouse_name is not null");
            return (Criteria) this;
        }

        public Criteria andWarehouse_nameEqualTo(String value) {
            addCriterion("warehouse_name =", value, "warehouse_name");
            return (Criteria) this;
        }

        public Criteria andWarehouse_nameNotEqualTo(String value) {
            addCriterion("warehouse_name <>", value, "warehouse_name");
            return (Criteria) this;
        }

        public Criteria andWarehouse_nameGreaterThan(String value) {
            addCriterion("warehouse_name >", value, "warehouse_name");
            return (Criteria) this;
        }

        public Criteria andWarehouse_nameGreaterThanOrEqualTo(String value) {
            addCriterion("warehouse_name >=", value, "warehouse_name");
            return (Criteria) this;
        }

        public Criteria andWarehouse_nameLessThan(String value) {
            addCriterion("warehouse_name <", value, "warehouse_name");
            return (Criteria) this;
        }

        public Criteria andWarehouse_nameLessThanOrEqualTo(String value) {
            addCriterion("warehouse_name <=", value, "warehouse_name");
            return (Criteria) this;
        }

        public Criteria andWarehouse_nameLike(String value) {
            addCriterion("warehouse_name like", value, "warehouse_name");
            return (Criteria) this;
        }

        public Criteria andWarehouse_nameNotLike(String value) {
            addCriterion("warehouse_name not like", value, "warehouse_name");
            return (Criteria) this;
        }

        public Criteria andWarehouse_nameIn(List<String> values) {
            addCriterion("warehouse_name in", values, "warehouse_name");
            return (Criteria) this;
        }

        public Criteria andWarehouse_nameNotIn(List<String> values) {
            addCriterion("warehouse_name not in", values, "warehouse_name");
            return (Criteria) this;
        }

        public Criteria andWarehouse_nameBetween(String value1, String value2) {
            addCriterion("warehouse_name between", value1, value2, "warehouse_name");
            return (Criteria) this;
        }

        public Criteria andWarehouse_nameNotBetween(String value1, String value2) {
            addCriterion("warehouse_name not between", value1, value2, "warehouse_name");
            return (Criteria) this;
        }

        public Criteria andPut_signIsNull() {
            addCriterion("put_sign is null");
            return (Criteria) this;
        }

        public Criteria andPut_signIsNotNull() {
            addCriterion("put_sign is not null");
            return (Criteria) this;
        }

        public Criteria andPut_signEqualTo(Short value) {
            addCriterion("put_sign =", value, "put_sign");
            return (Criteria) this;
        }

        public Criteria andPut_signNotEqualTo(Short value) {
            addCriterion("put_sign <>", value, "put_sign");
            return (Criteria) this;
        }

        public Criteria andPut_signGreaterThan(Short value) {
            addCriterion("put_sign >", value, "put_sign");
            return (Criteria) this;
        }

        public Criteria andPut_signGreaterThanOrEqualTo(Short value) {
            addCriterion("put_sign >=", value, "put_sign");
            return (Criteria) this;
        }

        public Criteria andPut_signLessThan(Short value) {
            addCriterion("put_sign <", value, "put_sign");
            return (Criteria) this;
        }

        public Criteria andPut_signLessThanOrEqualTo(Short value) {
            addCriterion("put_sign <=", value, "put_sign");
            return (Criteria) this;
        }

        public Criteria andPut_signIn(List<Short> values) {
            addCriterion("put_sign in", values, "put_sign");
            return (Criteria) this;
        }

        public Criteria andPut_signNotIn(List<Short> values) {
            addCriterion("put_sign not in", values, "put_sign");
            return (Criteria) this;
        }

        public Criteria andPut_signBetween(Short value1, Short value2) {
            addCriterion("put_sign between", value1, value2, "put_sign");
            return (Criteria) this;
        }

        public Criteria andPut_signNotBetween(Short value1, Short value2) {
            addCriterion("put_sign not between", value1, value2, "put_sign");
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

        public Criteria andDeletedIsNull() {
            addCriterion("deleted is null");
            return (Criteria) this;
        }

        public Criteria andDeletedIsNotNull() {
            addCriterion("deleted is not null");
            return (Criteria) this;
        }

        public Criteria andDeletedEqualTo(Boolean value) {
            addCriterion("deleted =", value, "deleted");
            return (Criteria) this;
        }

        public Criteria andDeletedNotEqualTo(Boolean value) {
            addCriterion("deleted <>", value, "deleted");
            return (Criteria) this;
        }

        public Criteria andDeletedGreaterThan(Boolean value) {
            addCriterion("deleted >", value, "deleted");
            return (Criteria) this;
        }

        public Criteria andDeletedGreaterThanOrEqualTo(Boolean value) {
            addCriterion("deleted >=", value, "deleted");
            return (Criteria) this;
        }

        public Criteria andDeletedLessThan(Boolean value) {
            addCriterion("deleted <", value, "deleted");
            return (Criteria) this;
        }

        public Criteria andDeletedLessThanOrEqualTo(Boolean value) {
            addCriterion("deleted <=", value, "deleted");
            return (Criteria) this;
        }

        public Criteria andDeletedIn(List<Boolean> values) {
            addCriterion("deleted in", values, "deleted");
            return (Criteria) this;
        }

        public Criteria andDeletedNotIn(List<Boolean> values) {
            addCriterion("deleted not in", values, "deleted");
            return (Criteria) this;
        }

        public Criteria andDeletedBetween(Boolean value1, Boolean value2) {
            addCriterion("deleted between", value1, value2, "deleted");
            return (Criteria) this;
        }

        public Criteria andDeletedNotBetween(Boolean value1, Boolean value2) {
            addCriterion("deleted not between", value1, value2, "deleted");
            return (Criteria) this;
        }

        public Criteria andDone_timeIsNull() {
            addCriterion("done_time is null");
            return (Criteria) this;
        }

        public Criteria andDone_timeIsNotNull() {
            addCriterion("done_time is not null");
            return (Criteria) this;
        }

        public Criteria andDone_timeEqualTo(Date value) {
            addCriterion("done_time =", value, "done_time");
            return (Criteria) this;
        }

        public Criteria andDone_timeNotEqualTo(Date value) {
            addCriterion("done_time <>", value, "done_time");
            return (Criteria) this;
        }

        public Criteria andDone_timeGreaterThan(Date value) {
            addCriterion("done_time >", value, "done_time");
            return (Criteria) this;
        }

        public Criteria andDone_timeGreaterThanOrEqualTo(Date value) {
            addCriterion("done_time >=", value, "done_time");
            return (Criteria) this;
        }

        public Criteria andDone_timeLessThan(Date value) {
            addCriterion("done_time <", value, "done_time");
            return (Criteria) this;
        }

        public Criteria andDone_timeLessThanOrEqualTo(Date value) {
            addCriterion("done_time <=", value, "done_time");
            return (Criteria) this;
        }

        public Criteria andDone_timeIn(List<Date> values) {
            addCriterion("done_time in", values, "done_time");
            return (Criteria) this;
        }

        public Criteria andDone_timeNotIn(List<Date> values) {
            addCriterion("done_time not in", values, "done_time");
            return (Criteria) this;
        }

        public Criteria andDone_timeBetween(Date value1, Date value2) {
            addCriterion("done_time between", value1, value2, "done_time");
            return (Criteria) this;
        }

        public Criteria andDone_timeNotBetween(Date value1, Date value2) {
            addCriterion("done_time not between", value1, value2, "done_time");
            return (Criteria) this;
        }

        public Criteria andFast_user_idIsNull() {
            addCriterion("fast_user_id is null");
            return (Criteria) this;
        }

        public Criteria andFast_user_idIsNotNull() {
            addCriterion("fast_user_id is not null");
            return (Criteria) this;
        }

        public Criteria andFast_user_idEqualTo(Long value) {
            addCriterion("fast_user_id =", value, "fast_user_id");
            return (Criteria) this;
        }

        public Criteria andFast_user_idNotEqualTo(Long value) {
            addCriterion("fast_user_id <>", value, "fast_user_id");
            return (Criteria) this;
        }

        public Criteria andFast_user_idGreaterThan(Long value) {
            addCriterion("fast_user_id >", value, "fast_user_id");
            return (Criteria) this;
        }

        public Criteria andFast_user_idGreaterThanOrEqualTo(Long value) {
            addCriterion("fast_user_id >=", value, "fast_user_id");
            return (Criteria) this;
        }

        public Criteria andFast_user_idLessThan(Long value) {
            addCriterion("fast_user_id <", value, "fast_user_id");
            return (Criteria) this;
        }

        public Criteria andFast_user_idLessThanOrEqualTo(Long value) {
            addCriterion("fast_user_id <=", value, "fast_user_id");
            return (Criteria) this;
        }

        public Criteria andFast_user_idIn(List<Long> values) {
            addCriterion("fast_user_id in", values, "fast_user_id");
            return (Criteria) this;
        }

        public Criteria andFast_user_idNotIn(List<Long> values) {
            addCriterion("fast_user_id not in", values, "fast_user_id");
            return (Criteria) this;
        }

        public Criteria andFast_user_idBetween(Long value1, Long value2) {
            addCriterion("fast_user_id between", value1, value2, "fast_user_id");
            return (Criteria) this;
        }

        public Criteria andFast_user_idNotBetween(Long value1, Long value2) {
            addCriterion("fast_user_id not between", value1, value2, "fast_user_id");
            return (Criteria) this;
        }

        public Criteria andConsignee_nameIsNull() {
            addCriterion("consignee_name is null");
            return (Criteria) this;
        }

        public Criteria andConsignee_nameIsNotNull() {
            addCriterion("consignee_name is not null");
            return (Criteria) this;
        }

        public Criteria andConsignee_nameEqualTo(String value) {
            addCriterion("consignee_name =", value, "consignee_name");
            return (Criteria) this;
        }

        public Criteria andConsignee_nameNotEqualTo(String value) {
            addCriterion("consignee_name <>", value, "consignee_name");
            return (Criteria) this;
        }

        public Criteria andConsignee_nameGreaterThan(String value) {
            addCriterion("consignee_name >", value, "consignee_name");
            return (Criteria) this;
        }

        public Criteria andConsignee_nameGreaterThanOrEqualTo(String value) {
            addCriterion("consignee_name >=", value, "consignee_name");
            return (Criteria) this;
        }

        public Criteria andConsignee_nameLessThan(String value) {
            addCriterion("consignee_name <", value, "consignee_name");
            return (Criteria) this;
        }

        public Criteria andConsignee_nameLessThanOrEqualTo(String value) {
            addCriterion("consignee_name <=", value, "consignee_name");
            return (Criteria) this;
        }

        public Criteria andConsignee_nameLike(String value) {
            addCriterion("consignee_name like", value, "consignee_name");
            return (Criteria) this;
        }

        public Criteria andConsignee_nameNotLike(String value) {
            addCriterion("consignee_name not like", value, "consignee_name");
            return (Criteria) this;
        }

        public Criteria andConsignee_nameIn(List<String> values) {
            addCriterion("consignee_name in", values, "consignee_name");
            return (Criteria) this;
        }

        public Criteria andConsignee_nameNotIn(List<String> values) {
            addCriterion("consignee_name not in", values, "consignee_name");
            return (Criteria) this;
        }

        public Criteria andConsignee_nameBetween(String value1, String value2) {
            addCriterion("consignee_name between", value1, value2, "consignee_name");
            return (Criteria) this;
        }

        public Criteria andConsignee_nameNotBetween(String value1, String value2) {
            addCriterion("consignee_name not between", value1, value2, "consignee_name");
            return (Criteria) this;
        }

        public Criteria andConsignee_mobileIsNull() {
            addCriterion("consignee_mobile is null");
            return (Criteria) this;
        }

        public Criteria andConsignee_mobileIsNotNull() {
            addCriterion("consignee_mobile is not null");
            return (Criteria) this;
        }

        public Criteria andConsignee_mobileEqualTo(String value) {
            addCriterion("consignee_mobile =", value, "consignee_mobile");
            return (Criteria) this;
        }

        public Criteria andConsignee_mobileNotEqualTo(String value) {
            addCriterion("consignee_mobile <>", value, "consignee_mobile");
            return (Criteria) this;
        }

        public Criteria andConsignee_mobileGreaterThan(String value) {
            addCriterion("consignee_mobile >", value, "consignee_mobile");
            return (Criteria) this;
        }

        public Criteria andConsignee_mobileGreaterThanOrEqualTo(String value) {
            addCriterion("consignee_mobile >=", value, "consignee_mobile");
            return (Criteria) this;
        }

        public Criteria andConsignee_mobileLessThan(String value) {
            addCriterion("consignee_mobile <", value, "consignee_mobile");
            return (Criteria) this;
        }

        public Criteria andConsignee_mobileLessThanOrEqualTo(String value) {
            addCriterion("consignee_mobile <=", value, "consignee_mobile");
            return (Criteria) this;
        }

        public Criteria andConsignee_mobileLike(String value) {
            addCriterion("consignee_mobile like", value, "consignee_mobile");
            return (Criteria) this;
        }

        public Criteria andConsignee_mobileNotLike(String value) {
            addCriterion("consignee_mobile not like", value, "consignee_mobile");
            return (Criteria) this;
        }

        public Criteria andConsignee_mobileIn(List<String> values) {
            addCriterion("consignee_mobile in", values, "consignee_mobile");
            return (Criteria) this;
        }

        public Criteria andConsignee_mobileNotIn(List<String> values) {
            addCriterion("consignee_mobile not in", values, "consignee_mobile");
            return (Criteria) this;
        }

        public Criteria andConsignee_mobileBetween(String value1, String value2) {
            addCriterion("consignee_mobile between", value1, value2, "consignee_mobile");
            return (Criteria) this;
        }

        public Criteria andConsignee_mobileNotBetween(String value1, String value2) {
            addCriterion("consignee_mobile not between", value1, value2, "consignee_mobile");
            return (Criteria) this;
        }

        public Criteria andAccount_nameIsNull() {
            addCriterion("account_name is null");
            return (Criteria) this;
        }

        public Criteria andAccount_nameIsNotNull() {
            addCriterion("account_name is not null");
            return (Criteria) this;
        }

        public Criteria andAccount_nameEqualTo(String value) {
            addCriterion("account_name =", value, "account_name");
            return (Criteria) this;
        }

        public Criteria andAccount_nameNotEqualTo(String value) {
            addCriterion("account_name <>", value, "account_name");
            return (Criteria) this;
        }

        public Criteria andAccount_nameGreaterThan(String value) {
            addCriterion("account_name >", value, "account_name");
            return (Criteria) this;
        }

        public Criteria andAccount_nameGreaterThanOrEqualTo(String value) {
            addCriterion("account_name >=", value, "account_name");
            return (Criteria) this;
        }

        public Criteria andAccount_nameLessThan(String value) {
            addCriterion("account_name <", value, "account_name");
            return (Criteria) this;
        }

        public Criteria andAccount_nameLessThanOrEqualTo(String value) {
            addCriterion("account_name <=", value, "account_name");
            return (Criteria) this;
        }

        public Criteria andAccount_nameLike(String value) {
            addCriterion("account_name like", value, "account_name");
            return (Criteria) this;
        }

        public Criteria andAccount_nameNotLike(String value) {
            addCriterion("account_name not like", value, "account_name");
            return (Criteria) this;
        }

        public Criteria andAccount_nameIn(List<String> values) {
            addCriterion("account_name in", values, "account_name");
            return (Criteria) this;
        }

        public Criteria andAccount_nameNotIn(List<String> values) {
            addCriterion("account_name not in", values, "account_name");
            return (Criteria) this;
        }

        public Criteria andAccount_nameBetween(String value1, String value2) {
            addCriterion("account_name between", value1, value2, "account_name");
            return (Criteria) this;
        }

        public Criteria andAccount_nameNotBetween(String value1, String value2) {
            addCriterion("account_name not between", value1, value2, "account_name");
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

        public Criteria andCancel_reasonIsNull() {
            addCriterion("cancel_reason is null");
            return (Criteria) this;
        }

        public Criteria andCancel_reasonIsNotNull() {
            addCriterion("cancel_reason is not null");
            return (Criteria) this;
        }

        public Criteria andCancel_reasonEqualTo(String value) {
            addCriterion("cancel_reason =", value, "cancel_reason");
            return (Criteria) this;
        }

        public Criteria andCancel_reasonNotEqualTo(String value) {
            addCriterion("cancel_reason <>", value, "cancel_reason");
            return (Criteria) this;
        }

        public Criteria andCancel_reasonGreaterThan(String value) {
            addCriterion("cancel_reason >", value, "cancel_reason");
            return (Criteria) this;
        }

        public Criteria andCancel_reasonGreaterThanOrEqualTo(String value) {
            addCriterion("cancel_reason >=", value, "cancel_reason");
            return (Criteria) this;
        }

        public Criteria andCancel_reasonLessThan(String value) {
            addCriterion("cancel_reason <", value, "cancel_reason");
            return (Criteria) this;
        }

        public Criteria andCancel_reasonLessThanOrEqualTo(String value) {
            addCriterion("cancel_reason <=", value, "cancel_reason");
            return (Criteria) this;
        }

        public Criteria andCancel_reasonLike(String value) {
            addCriterion("cancel_reason like", value, "cancel_reason");
            return (Criteria) this;
        }

        public Criteria andCancel_reasonNotLike(String value) {
            addCriterion("cancel_reason not like", value, "cancel_reason");
            return (Criteria) this;
        }

        public Criteria andCancel_reasonIn(List<String> values) {
            addCriterion("cancel_reason in", values, "cancel_reason");
            return (Criteria) this;
        }

        public Criteria andCancel_reasonNotIn(List<String> values) {
            addCriterion("cancel_reason not in", values, "cancel_reason");
            return (Criteria) this;
        }

        public Criteria andCancel_reasonBetween(String value1, String value2) {
            addCriterion("cancel_reason between", value1, value2, "cancel_reason");
            return (Criteria) this;
        }

        public Criteria andCancel_reasonNotBetween(String value1, String value2) {
            addCriterion("cancel_reason not between", value1, value2, "cancel_reason");
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

        public Criteria andDelivered_timeIsNull() {
            addCriterion("delivered_time is null");
            return (Criteria) this;
        }

        public Criteria andDelivered_timeIsNotNull() {
            addCriterion("delivered_time is not null");
            return (Criteria) this;
        }

        public Criteria andDelivered_timeEqualTo(Date value) {
            addCriterion("delivered_time =", value, "delivered_time");
            return (Criteria) this;
        }

        public Criteria andDelivered_timeNotEqualTo(Date value) {
            addCriterion("delivered_time <>", value, "delivered_time");
            return (Criteria) this;
        }

        public Criteria andDelivered_timeGreaterThan(Date value) {
            addCriterion("delivered_time >", value, "delivered_time");
            return (Criteria) this;
        }

        public Criteria andDelivered_timeGreaterThanOrEqualTo(Date value) {
            addCriterion("delivered_time >=", value, "delivered_time");
            return (Criteria) this;
        }

        public Criteria andDelivered_timeLessThan(Date value) {
            addCriterion("delivered_time <", value, "delivered_time");
            return (Criteria) this;
        }

        public Criteria andDelivered_timeLessThanOrEqualTo(Date value) {
            addCriterion("delivered_time <=", value, "delivered_time");
            return (Criteria) this;
        }

        public Criteria andDelivered_timeIn(List<Date> values) {
            addCriterion("delivered_time in", values, "delivered_time");
            return (Criteria) this;
        }

        public Criteria andDelivered_timeNotIn(List<Date> values) {
            addCriterion("delivered_time not in", values, "delivered_time");
            return (Criteria) this;
        }

        public Criteria andDelivered_timeBetween(Date value1, Date value2) {
            addCriterion("delivered_time between", value1, value2, "delivered_time");
            return (Criteria) this;
        }

        public Criteria andDelivered_timeNotBetween(Date value1, Date value2) {
            addCriterion("delivered_time not between", value1, value2, "delivered_time");
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

        public Criteria andPay_typeIsNull() {
            addCriterion("pay_type is null");
            return (Criteria) this;
        }

        public Criteria andPay_typeIsNotNull() {
            addCriterion("pay_type is not null");
            return (Criteria) this;
        }

        public Criteria andPay_typeEqualTo(Byte value) {
            addCriterion("pay_type =", value, "pay_type");
            return (Criteria) this;
        }

        public Criteria andPay_typeNotEqualTo(Byte value) {
            addCriterion("pay_type <>", value, "pay_type");
            return (Criteria) this;
        }

        public Criteria andPay_typeGreaterThan(Byte value) {
            addCriterion("pay_type >", value, "pay_type");
            return (Criteria) this;
        }

        public Criteria andPay_typeGreaterThanOrEqualTo(Byte value) {
            addCriterion("pay_type >=", value, "pay_type");
            return (Criteria) this;
        }

        public Criteria andPay_typeLessThan(Byte value) {
            addCriterion("pay_type <", value, "pay_type");
            return (Criteria) this;
        }

        public Criteria andPay_typeLessThanOrEqualTo(Byte value) {
            addCriterion("pay_type <=", value, "pay_type");
            return (Criteria) this;
        }

        public Criteria andPay_typeIn(List<Byte> values) {
            addCriterion("pay_type in", values, "pay_type");
            return (Criteria) this;
        }

        public Criteria andPay_typeNotIn(List<Byte> values) {
            addCriterion("pay_type not in", values, "pay_type");
            return (Criteria) this;
        }

        public Criteria andPay_typeBetween(Byte value1, Byte value2) {
            addCriterion("pay_type between", value1, value2, "pay_type");
            return (Criteria) this;
        }

        public Criteria andPay_typeNotBetween(Byte value1, Byte value2) {
            addCriterion("pay_type not between", value1, value2, "pay_type");
            return (Criteria) this;
        }

        public Criteria andPay_wayIsNull() {
            addCriterion("pay_way is null");
            return (Criteria) this;
        }

        public Criteria andPay_wayIsNotNull() {
            addCriterion("pay_way is not null");
            return (Criteria) this;
        }

        public Criteria andPay_wayEqualTo(Byte value) {
            addCriterion("pay_way =", value, "pay_way");
            return (Criteria) this;
        }

        public Criteria andPay_wayNotEqualTo(Byte value) {
            addCriterion("pay_way <>", value, "pay_way");
            return (Criteria) this;
        }

        public Criteria andPay_wayGreaterThan(Byte value) {
            addCriterion("pay_way >", value, "pay_way");
            return (Criteria) this;
        }

        public Criteria andPay_wayGreaterThanOrEqualTo(Byte value) {
            addCriterion("pay_way >=", value, "pay_way");
            return (Criteria) this;
        }

        public Criteria andPay_wayLessThan(Byte value) {
            addCriterion("pay_way <", value, "pay_way");
            return (Criteria) this;
        }

        public Criteria andPay_wayLessThanOrEqualTo(Byte value) {
            addCriterion("pay_way <=", value, "pay_way");
            return (Criteria) this;
        }

        public Criteria andPay_wayIn(List<Byte> values) {
            addCriterion("pay_way in", values, "pay_way");
            return (Criteria) this;
        }

        public Criteria andPay_wayNotIn(List<Byte> values) {
            addCriterion("pay_way not in", values, "pay_way");
            return (Criteria) this;
        }

        public Criteria andPay_wayBetween(Byte value1, Byte value2) {
            addCriterion("pay_way between", value1, value2, "pay_way");
            return (Criteria) this;
        }

        public Criteria andPay_wayNotBetween(Byte value1, Byte value2) {
            addCriterion("pay_way not between", value1, value2, "pay_way");
            return (Criteria) this;
        }

        public Criteria andPay_timeIsNull() {
            addCriterion("pay_time is null");
            return (Criteria) this;
        }

        public Criteria andPay_timeIsNotNull() {
            addCriterion("pay_time is not null");
            return (Criteria) this;
        }

        public Criteria andPay_timeEqualTo(Date value) {
            addCriterion("pay_time =", value, "pay_time");
            return (Criteria) this;
        }

        public Criteria andPay_timeNotEqualTo(Date value) {
            addCriterion("pay_time <>", value, "pay_time");
            return (Criteria) this;
        }

        public Criteria andPay_timeGreaterThan(Date value) {
            addCriterion("pay_time >", value, "pay_time");
            return (Criteria) this;
        }

        public Criteria andPay_timeGreaterThanOrEqualTo(Date value) {
            addCriterion("pay_time >=", value, "pay_time");
            return (Criteria) this;
        }

        public Criteria andPay_timeLessThan(Date value) {
            addCriterion("pay_time <", value, "pay_time");
            return (Criteria) this;
        }

        public Criteria andPay_timeLessThanOrEqualTo(Date value) {
            addCriterion("pay_time <=", value, "pay_time");
            return (Criteria) this;
        }

        public Criteria andPay_timeIn(List<Date> values) {
            addCriterion("pay_time in", values, "pay_time");
            return (Criteria) this;
        }

        public Criteria andPay_timeNotIn(List<Date> values) {
            addCriterion("pay_time not in", values, "pay_time");
            return (Criteria) this;
        }

        public Criteria andPay_timeBetween(Date value1, Date value2) {
            addCriterion("pay_time between", value1, value2, "pay_time");
            return (Criteria) this;
        }

        public Criteria andPay_timeNotBetween(Date value1, Date value2) {
            addCriterion("pay_time not between", value1, value2, "pay_time");
            return (Criteria) this;
        }

        public Criteria andPay_codeIsNull() {
            addCriterion("pay_code is null");
            return (Criteria) this;
        }

        public Criteria andPay_codeIsNotNull() {
            addCriterion("pay_code is not null");
            return (Criteria) this;
        }

        public Criteria andPay_codeEqualTo(String value) {
            addCriterion("pay_code =", value, "pay_code");
            return (Criteria) this;
        }

        public Criteria andPay_codeNotEqualTo(String value) {
            addCriterion("pay_code <>", value, "pay_code");
            return (Criteria) this;
        }

        public Criteria andPay_codeGreaterThan(String value) {
            addCriterion("pay_code >", value, "pay_code");
            return (Criteria) this;
        }

        public Criteria andPay_codeGreaterThanOrEqualTo(String value) {
            addCriterion("pay_code >=", value, "pay_code");
            return (Criteria) this;
        }

        public Criteria andPay_codeLessThan(String value) {
            addCriterion("pay_code <", value, "pay_code");
            return (Criteria) this;
        }

        public Criteria andPay_codeLessThanOrEqualTo(String value) {
            addCriterion("pay_code <=", value, "pay_code");
            return (Criteria) this;
        }

        public Criteria andPay_codeLike(String value) {
            addCriterion("pay_code like", value, "pay_code");
            return (Criteria) this;
        }

        public Criteria andPay_codeNotLike(String value) {
            addCriterion("pay_code not like", value, "pay_code");
            return (Criteria) this;
        }

        public Criteria andPay_codeIn(List<String> values) {
            addCriterion("pay_code in", values, "pay_code");
            return (Criteria) this;
        }

        public Criteria andPay_codeNotIn(List<String> values) {
            addCriterion("pay_code not in", values, "pay_code");
            return (Criteria) this;
        }

        public Criteria andPay_codeBetween(String value1, String value2) {
            addCriterion("pay_code between", value1, value2, "pay_code");
            return (Criteria) this;
        }

        public Criteria andPay_codeNotBetween(String value1, String value2) {
            addCriterion("pay_code not between", value1, value2, "pay_code");
            return (Criteria) this;
        }

        public Criteria andPay_company_codeIsNull() {
            addCriterion("pay_company_code is null");
            return (Criteria) this;
        }

        public Criteria andPay_company_codeIsNotNull() {
            addCriterion("pay_company_code is not null");
            return (Criteria) this;
        }

        public Criteria andPay_company_codeEqualTo(String value) {
            addCriterion("pay_company_code =", value, "pay_company_code");
            return (Criteria) this;
        }

        public Criteria andPay_company_codeNotEqualTo(String value) {
            addCriterion("pay_company_code <>", value, "pay_company_code");
            return (Criteria) this;
        }

        public Criteria andPay_company_codeGreaterThan(String value) {
            addCriterion("pay_company_code >", value, "pay_company_code");
            return (Criteria) this;
        }

        public Criteria andPay_company_codeGreaterThanOrEqualTo(String value) {
            addCriterion("pay_company_code >=", value, "pay_company_code");
            return (Criteria) this;
        }

        public Criteria andPay_company_codeLessThan(String value) {
            addCriterion("pay_company_code <", value, "pay_company_code");
            return (Criteria) this;
        }

        public Criteria andPay_company_codeLessThanOrEqualTo(String value) {
            addCriterion("pay_company_code <=", value, "pay_company_code");
            return (Criteria) this;
        }

        public Criteria andPay_company_codeLike(String value) {
            addCriterion("pay_company_code like", value, "pay_company_code");
            return (Criteria) this;
        }

        public Criteria andPay_company_codeNotLike(String value) {
            addCriterion("pay_company_code not like", value, "pay_company_code");
            return (Criteria) this;
        }

        public Criteria andPay_company_codeIn(List<String> values) {
            addCriterion("pay_company_code in", values, "pay_company_code");
            return (Criteria) this;
        }

        public Criteria andPay_company_codeNotIn(List<String> values) {
            addCriterion("pay_company_code not in", values, "pay_company_code");
            return (Criteria) this;
        }

        public Criteria andPay_company_codeBetween(String value1, String value2) {
            addCriterion("pay_company_code between", value1, value2, "pay_company_code");
            return (Criteria) this;
        }

        public Criteria andPay_company_codeNotBetween(String value1, String value2) {
            addCriterion("pay_company_code not between", value1, value2, "pay_company_code");
            return (Criteria) this;
        }

        public Criteria andSea_channelIsNull() {
            addCriterion("sea_channel is null");
            return (Criteria) this;
        }

        public Criteria andSea_channelIsNotNull() {
            addCriterion("sea_channel is not null");
            return (Criteria) this;
        }

        public Criteria andSea_channelEqualTo(Long value) {
            addCriterion("sea_channel =", value, "sea_channel");
            return (Criteria) this;
        }

        public Criteria andSea_channelNotEqualTo(Long value) {
            addCriterion("sea_channel <>", value, "sea_channel");
            return (Criteria) this;
        }

        public Criteria andSea_channelGreaterThan(Long value) {
            addCriterion("sea_channel >", value, "sea_channel");
            return (Criteria) this;
        }

        public Criteria andSea_channelGreaterThanOrEqualTo(Long value) {
            addCriterion("sea_channel >=", value, "sea_channel");
            return (Criteria) this;
        }

        public Criteria andSea_channelLessThan(Long value) {
            addCriterion("sea_channel <", value, "sea_channel");
            return (Criteria) this;
        }

        public Criteria andSea_channelLessThanOrEqualTo(Long value) {
            addCriterion("sea_channel <=", value, "sea_channel");
            return (Criteria) this;
        }

        public Criteria andSea_channelIn(List<Long> values) {
            addCriterion("sea_channel in", values, "sea_channel");
            return (Criteria) this;
        }

        public Criteria andSea_channelNotIn(List<Long> values) {
            addCriterion("sea_channel not in", values, "sea_channel");
            return (Criteria) this;
        }

        public Criteria andSea_channelBetween(Long value1, Long value2) {
            addCriterion("sea_channel between", value1, value2, "sea_channel");
            return (Criteria) this;
        }

        public Criteria andSea_channelNotBetween(Long value1, Long value2) {
            addCriterion("sea_channel not between", value1, value2, "sea_channel");
            return (Criteria) this;
        }

        public Criteria andCustom_codeIsNull() {
            addCriterion("custom_code is null");
            return (Criteria) this;
        }

        public Criteria andCustom_codeIsNotNull() {
            addCriterion("custom_code is not null");
            return (Criteria) this;
        }

        public Criteria andCustom_codeEqualTo(String value) {
            addCriterion("custom_code =", value, "custom_code");
            return (Criteria) this;
        }

        public Criteria andCustom_codeNotEqualTo(String value) {
            addCriterion("custom_code <>", value, "custom_code");
            return (Criteria) this;
        }

        public Criteria andCustom_codeGreaterThan(String value) {
            addCriterion("custom_code >", value, "custom_code");
            return (Criteria) this;
        }

        public Criteria andCustom_codeGreaterThanOrEqualTo(String value) {
            addCriterion("custom_code >=", value, "custom_code");
            return (Criteria) this;
        }

        public Criteria andCustom_codeLessThan(String value) {
            addCriterion("custom_code <", value, "custom_code");
            return (Criteria) this;
        }

        public Criteria andCustom_codeLessThanOrEqualTo(String value) {
            addCriterion("custom_code <=", value, "custom_code");
            return (Criteria) this;
        }

        public Criteria andCustom_codeLike(String value) {
            addCriterion("custom_code like", value, "custom_code");
            return (Criteria) this;
        }

        public Criteria andCustom_codeNotLike(String value) {
            addCriterion("custom_code not like", value, "custom_code");
            return (Criteria) this;
        }

        public Criteria andCustom_codeIn(List<String> values) {
            addCriterion("custom_code in", values, "custom_code");
            return (Criteria) this;
        }

        public Criteria andCustom_codeNotIn(List<String> values) {
            addCriterion("custom_code not in", values, "custom_code");
            return (Criteria) this;
        }

        public Criteria andCustom_codeBetween(String value1, String value2) {
            addCriterion("custom_code between", value1, value2, "custom_code");
            return (Criteria) this;
        }

        public Criteria andCustom_codeNotBetween(String value1, String value2) {
            addCriterion("custom_code not between", value1, value2, "custom_code");
            return (Criteria) this;
        }

        public Criteria andOrg_nameIsNull() {
            addCriterion("org_name is null");
            return (Criteria) this;
        }

        public Criteria andOrg_nameIsNotNull() {
            addCriterion("org_name is not null");
            return (Criteria) this;
        }

        public Criteria andOrg_nameEqualTo(String value) {
            addCriterion("org_name =", value, "org_name");
            return (Criteria) this;
        }

        public Criteria andOrg_nameNotEqualTo(String value) {
            addCriterion("org_name <>", value, "org_name");
            return (Criteria) this;
        }

        public Criteria andOrg_nameGreaterThan(String value) {
            addCriterion("org_name >", value, "org_name");
            return (Criteria) this;
        }

        public Criteria andOrg_nameGreaterThanOrEqualTo(String value) {
            addCriterion("org_name >=", value, "org_name");
            return (Criteria) this;
        }

        public Criteria andOrg_nameLessThan(String value) {
            addCriterion("org_name <", value, "org_name");
            return (Criteria) this;
        }

        public Criteria andOrg_nameLessThanOrEqualTo(String value) {
            addCriterion("org_name <=", value, "org_name");
            return (Criteria) this;
        }

        public Criteria andOrg_nameLike(String value) {
            addCriterion("org_name like", value, "org_name");
            return (Criteria) this;
        }

        public Criteria andOrg_nameNotLike(String value) {
            addCriterion("org_name not like", value, "org_name");
            return (Criteria) this;
        }

        public Criteria andOrg_nameIn(List<String> values) {
            addCriterion("org_name in", values, "org_name");
            return (Criteria) this;
        }

        public Criteria andOrg_nameNotIn(List<String> values) {
            addCriterion("org_name not in", values, "org_name");
            return (Criteria) this;
        }

        public Criteria andOrg_nameBetween(String value1, String value2) {
            addCriterion("org_name between", value1, value2, "org_name");
            return (Criteria) this;
        }

        public Criteria andOrg_nameNotBetween(String value1, String value2) {
            addCriterion("org_name not between", value1, value2, "org_name");
            return (Criteria) this;
        }

        public Criteria andPut_statusIsNull() {
            addCriterion("put_status is null");
            return (Criteria) this;
        }

        public Criteria andPut_statusIsNotNull() {
            addCriterion("put_status is not null");
            return (Criteria) this;
        }

        public Criteria andPut_statusEqualTo(Byte value) {
            addCriterion("put_status =", value, "put_status");
            return (Criteria) this;
        }

        public Criteria andPut_statusNotEqualTo(Byte value) {
            addCriterion("put_status <>", value, "put_status");
            return (Criteria) this;
        }

        public Criteria andPut_statusGreaterThan(Byte value) {
            addCriterion("put_status >", value, "put_status");
            return (Criteria) this;
        }

        public Criteria andPut_statusGreaterThanOrEqualTo(Byte value) {
            addCriterion("put_status >=", value, "put_status");
            return (Criteria) this;
        }

        public Criteria andPut_statusLessThan(Byte value) {
            addCriterion("put_status <", value, "put_status");
            return (Criteria) this;
        }

        public Criteria andPut_statusLessThanOrEqualTo(Byte value) {
            addCriterion("put_status <=", value, "put_status");
            return (Criteria) this;
        }

        public Criteria andPut_statusIn(List<Byte> values) {
            addCriterion("put_status in", values, "put_status");
            return (Criteria) this;
        }

        public Criteria andPut_statusNotIn(List<Byte> values) {
            addCriterion("put_status not in", values, "put_status");
            return (Criteria) this;
        }

        public Criteria andPut_statusBetween(Byte value1, Byte value2) {
            addCriterion("put_status between", value1, value2, "put_status");
            return (Criteria) this;
        }

        public Criteria andPut_statusNotBetween(Byte value1, Byte value2) {
            addCriterion("put_status not between", value1, value2, "put_status");
            return (Criteria) this;
        }

        public Criteria andSea_channel_nameIsNull() {
            addCriterion("sea_channel_name is null");
            return (Criteria) this;
        }

        public Criteria andSea_channel_nameIsNotNull() {
            addCriterion("sea_channel_name is not null");
            return (Criteria) this;
        }

        public Criteria andSea_channel_nameEqualTo(String value) {
            addCriterion("sea_channel_name =", value, "sea_channel_name");
            return (Criteria) this;
        }

        public Criteria andSea_channel_nameNotEqualTo(String value) {
            addCriterion("sea_channel_name <>", value, "sea_channel_name");
            return (Criteria) this;
        }

        public Criteria andSea_channel_nameGreaterThan(String value) {
            addCriterion("sea_channel_name >", value, "sea_channel_name");
            return (Criteria) this;
        }

        public Criteria andSea_channel_nameGreaterThanOrEqualTo(String value) {
            addCriterion("sea_channel_name >=", value, "sea_channel_name");
            return (Criteria) this;
        }

        public Criteria andSea_channel_nameLessThan(String value) {
            addCriterion("sea_channel_name <", value, "sea_channel_name");
            return (Criteria) this;
        }

        public Criteria andSea_channel_nameLessThanOrEqualTo(String value) {
            addCriterion("sea_channel_name <=", value, "sea_channel_name");
            return (Criteria) this;
        }

        public Criteria andSea_channel_nameLike(String value) {
            addCriterion("sea_channel_name like", value, "sea_channel_name");
            return (Criteria) this;
        }

        public Criteria andSea_channel_nameNotLike(String value) {
            addCriterion("sea_channel_name not like", value, "sea_channel_name");
            return (Criteria) this;
        }

        public Criteria andSea_channel_nameIn(List<String> values) {
            addCriterion("sea_channel_name in", values, "sea_channel_name");
            return (Criteria) this;
        }

        public Criteria andSea_channel_nameNotIn(List<String> values) {
            addCriterion("sea_channel_name not in", values, "sea_channel_name");
            return (Criteria) this;
        }

        public Criteria andSea_channel_nameBetween(String value1, String value2) {
            addCriterion("sea_channel_name between", value1, value2, "sea_channel_name");
            return (Criteria) this;
        }

        public Criteria andSea_channel_nameNotBetween(String value1, String value2) {
            addCriterion("sea_channel_name not between", value1, value2, "sea_channel_name");
            return (Criteria) this;
        }

        public Criteria andSourceIsNull() {
            addCriterion("source is null");
            return (Criteria) this;
        }

        public Criteria andSourceIsNotNull() {
            addCriterion("source is not null");
            return (Criteria) this;
        }

        public Criteria andSourceEqualTo(Byte value) {
            addCriterion("source =", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceNotEqualTo(Byte value) {
            addCriterion("source <>", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceGreaterThan(Byte value) {
            addCriterion("source >", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceGreaterThanOrEqualTo(Byte value) {
            addCriterion("source >=", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceLessThan(Byte value) {
            addCriterion("source <", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceLessThanOrEqualTo(Byte value) {
            addCriterion("source <=", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceIn(List<Byte> values) {
            addCriterion("source in", values, "source");
            return (Criteria) this;
        }

        public Criteria andSourceNotIn(List<Byte> values) {
            addCriterion("source not in", values, "source");
            return (Criteria) this;
        }

        public Criteria andSourceBetween(Byte value1, Byte value2) {
            addCriterion("source between", value1, value2, "source");
            return (Criteria) this;
        }

        public Criteria andSourceNotBetween(Byte value1, Byte value2) {
            addCriterion("source not between", value1, value2, "source");
            return (Criteria) this;
        }

        public Criteria andPromoter_idIsNull() {
            addCriterion("promoter_id is null");
            return (Criteria) this;
        }

        public Criteria andPromoter_idIsNotNull() {
            addCriterion("promoter_id is not null");
            return (Criteria) this;
        }

        public Criteria andPromoter_idEqualTo(Long value) {
            addCriterion("promoter_id =", value, "promoter_id");
            return (Criteria) this;
        }

        public Criteria andPromoter_idNotEqualTo(Long value) {
            addCriterion("promoter_id <>", value, "promoter_id");
            return (Criteria) this;
        }

        public Criteria andPromoter_idGreaterThan(Long value) {
            addCriterion("promoter_id >", value, "promoter_id");
            return (Criteria) this;
        }

        public Criteria andPromoter_idGreaterThanOrEqualTo(Long value) {
            addCriterion("promoter_id >=", value, "promoter_id");
            return (Criteria) this;
        }

        public Criteria andPromoter_idLessThan(Long value) {
            addCriterion("promoter_id <", value, "promoter_id");
            return (Criteria) this;
        }

        public Criteria andPromoter_idLessThanOrEqualTo(Long value) {
            addCriterion("promoter_id <=", value, "promoter_id");
            return (Criteria) this;
        }

        public Criteria andPromoter_idIn(List<Long> values) {
            addCriterion("promoter_id in", values, "promoter_id");
            return (Criteria) this;
        }

        public Criteria andPromoter_idNotIn(List<Long> values) {
            addCriterion("promoter_id not in", values, "promoter_id");
            return (Criteria) this;
        }

        public Criteria andPromoter_idBetween(Long value1, Long value2) {
            addCriterion("promoter_id between", value1, value2, "promoter_id");
            return (Criteria) this;
        }

        public Criteria andPromoter_idNotBetween(Long value1, Long value2) {
            addCriterion("promoter_id not between", value1, value2, "promoter_id");
            return (Criteria) this;
        }

        public Criteria andShop_promoter_idIsNull() {
            addCriterion("shop_promoter_id is null");
            return (Criteria) this;
        }

        public Criteria andShop_promoter_idIsNotNull() {
            addCriterion("shop_promoter_id is not null");
            return (Criteria) this;
        }

        public Criteria andShop_promoter_idEqualTo(Long value) {
            addCriterion("shop_promoter_id =", value, "shop_promoter_id");
            return (Criteria) this;
        }

        public Criteria andShop_promoter_idNotEqualTo(Long value) {
            addCriterion("shop_promoter_id <>", value, "shop_promoter_id");
            return (Criteria) this;
        }

        public Criteria andShop_promoter_idGreaterThan(Long value) {
            addCriterion("shop_promoter_id >", value, "shop_promoter_id");
            return (Criteria) this;
        }

        public Criteria andShop_promoter_idGreaterThanOrEqualTo(Long value) {
            addCriterion("shop_promoter_id >=", value, "shop_promoter_id");
            return (Criteria) this;
        }

        public Criteria andShop_promoter_idLessThan(Long value) {
            addCriterion("shop_promoter_id <", value, "shop_promoter_id");
            return (Criteria) this;
        }

        public Criteria andShop_promoter_idLessThanOrEqualTo(Long value) {
            addCriterion("shop_promoter_id <=", value, "shop_promoter_id");
            return (Criteria) this;
        }

        public Criteria andShop_promoter_idIn(List<Long> values) {
            addCriterion("shop_promoter_id in", values, "shop_promoter_id");
            return (Criteria) this;
        }

        public Criteria andShop_promoter_idNotIn(List<Long> values) {
            addCriterion("shop_promoter_id not in", values, "shop_promoter_id");
            return (Criteria) this;
        }

        public Criteria andShop_promoter_idBetween(Long value1, Long value2) {
            addCriterion("shop_promoter_id between", value1, value2, "shop_promoter_id");
            return (Criteria) this;
        }

        public Criteria andShop_promoter_idNotBetween(Long value1, Long value2) {
            addCriterion("shop_promoter_id not between", value1, value2, "shop_promoter_id");
            return (Criteria) this;
        }

        public Criteria andScan_promoter_idIsNull() {
            addCriterion("scan_promoter_id is null");
            return (Criteria) this;
        }

        public Criteria andScan_promoter_idIsNotNull() {
            addCriterion("scan_promoter_id is not null");
            return (Criteria) this;
        }

        public Criteria andScan_promoter_idEqualTo(Long value) {
            addCriterion("scan_promoter_id =", value, "scan_promoter_id");
            return (Criteria) this;
        }

        public Criteria andScan_promoter_idNotEqualTo(Long value) {
            addCriterion("scan_promoter_id <>", value, "scan_promoter_id");
            return (Criteria) this;
        }

        public Criteria andScan_promoter_idGreaterThan(Long value) {
            addCriterion("scan_promoter_id >", value, "scan_promoter_id");
            return (Criteria) this;
        }

        public Criteria andScan_promoter_idGreaterThanOrEqualTo(Long value) {
            addCriterion("scan_promoter_id >=", value, "scan_promoter_id");
            return (Criteria) this;
        }

        public Criteria andScan_promoter_idLessThan(Long value) {
            addCriterion("scan_promoter_id <", value, "scan_promoter_id");
            return (Criteria) this;
        }

        public Criteria andScan_promoter_idLessThanOrEqualTo(Long value) {
            addCriterion("scan_promoter_id <=", value, "scan_promoter_id");
            return (Criteria) this;
        }

        public Criteria andScan_promoter_idIn(List<Long> values) {
            addCriterion("scan_promoter_id in", values, "scan_promoter_id");
            return (Criteria) this;
        }

        public Criteria andScan_promoter_idNotIn(List<Long> values) {
            addCriterion("scan_promoter_id not in", values, "scan_promoter_id");
            return (Criteria) this;
        }

        public Criteria andScan_promoter_idBetween(Long value1, Long value2) {
            addCriterion("scan_promoter_id between", value1, value2, "scan_promoter_id");
            return (Criteria) this;
        }

        public Criteria andScan_promoter_idNotBetween(Long value1, Long value2) {
            addCriterion("scan_promoter_id not between", value1, value2, "scan_promoter_id");
            return (Criteria) this;
        }

        public Criteria andTrack_sourceIsNull() {
            addCriterion("track_source is null");
            return (Criteria) this;
        }

        public Criteria andTrack_sourceIsNotNull() {
            addCriterion("track_source is not null");
            return (Criteria) this;
        }

        public Criteria andTrack_sourceEqualTo(Byte value) {
            addCriterion("track_source =", value, "track_source");
            return (Criteria) this;
        }

        public Criteria andTrack_sourceNotEqualTo(Byte value) {
            addCriterion("track_source <>", value, "track_source");
            return (Criteria) this;
        }

        public Criteria andTrack_sourceGreaterThan(Byte value) {
            addCriterion("track_source >", value, "track_source");
            return (Criteria) this;
        }

        public Criteria andTrack_sourceGreaterThanOrEqualTo(Byte value) {
            addCriterion("track_source >=", value, "track_source");
            return (Criteria) this;
        }

        public Criteria andTrack_sourceLessThan(Byte value) {
            addCriterion("track_source <", value, "track_source");
            return (Criteria) this;
        }

        public Criteria andTrack_sourceLessThanOrEqualTo(Byte value) {
            addCriterion("track_source <=", value, "track_source");
            return (Criteria) this;
        }

        public Criteria andTrack_sourceIn(List<Byte> values) {
            addCriterion("track_source in", values, "track_source");
            return (Criteria) this;
        }

        public Criteria andTrack_sourceNotIn(List<Byte> values) {
            addCriterion("track_source not in", values, "track_source");
            return (Criteria) this;
        }

        public Criteria andTrack_sourceBetween(Byte value1, Byte value2) {
            addCriterion("track_source between", value1, value2, "track_source");
            return (Criteria) this;
        }

        public Criteria andTrack_sourceNotBetween(Byte value1, Byte value2) {
            addCriterion("track_source not between", value1, value2, "track_source");
            return (Criteria) this;
        }

        public Criteria andChannel_codeIsNull() {
            addCriterion("channel_code is null");
            return (Criteria) this;
        }

        public Criteria andChannel_codeIsNotNull() {
            addCriterion("channel_code is not null");
            return (Criteria) this;
        }

        public Criteria andChannel_codeEqualTo(String value) {
            addCriterion("channel_code =", value, "channel_code");
            return (Criteria) this;
        }

        public Criteria andChannel_codeNotEqualTo(String value) {
            addCriterion("channel_code <>", value, "channel_code");
            return (Criteria) this;
        }

        public Criteria andChannel_codeGreaterThan(String value) {
            addCriterion("channel_code >", value, "channel_code");
            return (Criteria) this;
        }

        public Criteria andChannel_codeGreaterThanOrEqualTo(String value) {
            addCriterion("channel_code >=", value, "channel_code");
            return (Criteria) this;
        }

        public Criteria andChannel_codeLessThan(String value) {
            addCriterion("channel_code <", value, "channel_code");
            return (Criteria) this;
        }

        public Criteria andChannel_codeLessThanOrEqualTo(String value) {
            addCriterion("channel_code <=", value, "channel_code");
            return (Criteria) this;
        }

        public Criteria andChannel_codeLike(String value) {
            addCriterion("channel_code like", value, "channel_code");
            return (Criteria) this;
        }

        public Criteria andChannel_codeNotLike(String value) {
            addCriterion("channel_code not like", value, "channel_code");
            return (Criteria) this;
        }

        public Criteria andChannel_codeIn(List<String> values) {
            addCriterion("channel_code in", values, "channel_code");
            return (Criteria) this;
        }

        public Criteria andChannel_codeNotIn(List<String> values) {
            addCriterion("channel_code not in", values, "channel_code");
            return (Criteria) this;
        }

        public Criteria andChannel_codeBetween(String value1, String value2) {
            addCriterion("channel_code between", value1, value2, "channel_code");
            return (Criteria) this;
        }

        public Criteria andChannel_codeNotBetween(String value1, String value2) {
            addCriterion("channel_code not between", value1, value2, "channel_code");
            return (Criteria) this;
        }

        public Criteria andGroup_idIsNull() {
            addCriterion("group_id is null");
            return (Criteria) this;
        }

        public Criteria andGroup_idIsNotNull() {
            addCriterion("group_id is not null");
            return (Criteria) this;
        }

        public Criteria andGroup_idEqualTo(Long value) {
            addCriterion("group_id =", value, "group_id");
            return (Criteria) this;
        }

        public Criteria andGroup_idNotEqualTo(Long value) {
            addCriterion("group_id <>", value, "group_id");
            return (Criteria) this;
        }

        public Criteria andGroup_idGreaterThan(Long value) {
            addCriterion("group_id >", value, "group_id");
            return (Criteria) this;
        }

        public Criteria andGroup_idGreaterThanOrEqualTo(Long value) {
            addCriterion("group_id >=", value, "group_id");
            return (Criteria) this;
        }

        public Criteria andGroup_idLessThan(Long value) {
            addCriterion("group_id <", value, "group_id");
            return (Criteria) this;
        }

        public Criteria andGroup_idLessThanOrEqualTo(Long value) {
            addCriterion("group_id <=", value, "group_id");
            return (Criteria) this;
        }

        public Criteria andGroup_idIn(List<Long> values) {
            addCriterion("group_id in", values, "group_id");
            return (Criteria) this;
        }

        public Criteria andGroup_idNotIn(List<Long> values) {
            addCriterion("group_id not in", values, "group_id");
            return (Criteria) this;
        }

        public Criteria andGroup_idBetween(Long value1, Long value2) {
            addCriterion("group_id between", value1, value2, "group_id");
            return (Criteria) this;
        }

        public Criteria andGroup_idNotBetween(Long value1, Long value2) {
            addCriterion("group_id not between", value1, value2, "group_id");
            return (Criteria) this;
        }

        public Criteria andClearance_statusIsNull() {
            addCriterion("clearance_status is null");
            return (Criteria) this;
        }

        public Criteria andClearance_statusIsNotNull() {
            addCriterion("clearance_status is not null");
            return (Criteria) this;
        }

        public Criteria andClearance_statusEqualTo(Integer value) {
            addCriterion("clearance_status =", value, "clearance_status");
            return (Criteria) this;
        }

        public Criteria andClearance_statusNotEqualTo(Integer value) {
            addCriterion("clearance_status <>", value, "clearance_status");
            return (Criteria) this;
        }

        public Criteria andClearance_statusGreaterThan(Integer value) {
            addCriterion("clearance_status >", value, "clearance_status");
            return (Criteria) this;
        }

        public Criteria andClearance_statusGreaterThanOrEqualTo(Integer value) {
            addCriterion("clearance_status >=", value, "clearance_status");
            return (Criteria) this;
        }

        public Criteria andClearance_statusLessThan(Integer value) {
            addCriterion("clearance_status <", value, "clearance_status");
            return (Criteria) this;
        }

        public Criteria andClearance_statusLessThanOrEqualTo(Integer value) {
            addCriterion("clearance_status <=", value, "clearance_status");
            return (Criteria) this;
        }

        public Criteria andClearance_statusIn(List<Integer> values) {
            addCriterion("clearance_status in", values, "clearance_status");
            return (Criteria) this;
        }

        public Criteria andClearance_statusNotIn(List<Integer> values) {
            addCriterion("clearance_status not in", values, "clearance_status");
            return (Criteria) this;
        }

        public Criteria andClearance_statusBetween(Integer value1, Integer value2) {
            addCriterion("clearance_status between", value1, value2, "clearance_status");
            return (Criteria) this;
        }

        public Criteria andClearance_statusNotBetween(Integer value1, Integer value2) {
            addCriterion("clearance_status not between", value1, value2, "clearance_status");
            return (Criteria) this;
        }

        public Criteria andPut_order_statusIsNull() {
            addCriterion("put_order_status is null");
            return (Criteria) this;
        }

        public Criteria andPut_order_statusIsNotNull() {
            addCriterion("put_order_status is not null");
            return (Criteria) this;
        }

        public Criteria andPut_order_statusEqualTo(Integer value) {
            addCriterion("put_order_status =", value, "put_order_status");
            return (Criteria) this;
        }

        public Criteria andPut_order_statusNotEqualTo(Integer value) {
            addCriterion("put_order_status <>", value, "put_order_status");
            return (Criteria) this;
        }

        public Criteria andPut_order_statusGreaterThan(Integer value) {
            addCriterion("put_order_status >", value, "put_order_status");
            return (Criteria) this;
        }

        public Criteria andPut_order_statusGreaterThanOrEqualTo(Integer value) {
            addCriterion("put_order_status >=", value, "put_order_status");
            return (Criteria) this;
        }

        public Criteria andPut_order_statusLessThan(Integer value) {
            addCriterion("put_order_status <", value, "put_order_status");
            return (Criteria) this;
        }

        public Criteria andPut_order_statusLessThanOrEqualTo(Integer value) {
            addCriterion("put_order_status <=", value, "put_order_status");
            return (Criteria) this;
        }

        public Criteria andPut_order_statusIn(List<Integer> values) {
            addCriterion("put_order_status in", values, "put_order_status");
            return (Criteria) this;
        }

        public Criteria andPut_order_statusNotIn(List<Integer> values) {
            addCriterion("put_order_status not in", values, "put_order_status");
            return (Criteria) this;
        }

        public Criteria andPut_order_statusBetween(Integer value1, Integer value2) {
            addCriterion("put_order_status between", value1, value2, "put_order_status");
            return (Criteria) this;
        }

        public Criteria andPut_order_statusNotBetween(Integer value1, Integer value2) {
            addCriterion("put_order_status not between", value1, value2, "put_order_status");
            return (Criteria) this;
        }

        public Criteria andPut_personalgoods_statusIsNull() {
            addCriterion("put_personalgoods_status is null");
            return (Criteria) this;
        }

        public Criteria andPut_personalgoods_statusIsNotNull() {
            addCriterion("put_personalgoods_status is not null");
            return (Criteria) this;
        }

        public Criteria andPut_personalgoods_statusEqualTo(Integer value) {
            addCriterion("put_personalgoods_status =", value, "put_personalgoods_status");
            return (Criteria) this;
        }

        public Criteria andPut_personalgoods_statusNotEqualTo(Integer value) {
            addCriterion("put_personalgoods_status <>", value, "put_personalgoods_status");
            return (Criteria) this;
        }

        public Criteria andPut_personalgoods_statusGreaterThan(Integer value) {
            addCriterion("put_personalgoods_status >", value, "put_personalgoods_status");
            return (Criteria) this;
        }

        public Criteria andPut_personalgoods_statusGreaterThanOrEqualTo(Integer value) {
            addCriterion("put_personalgoods_status >=", value, "put_personalgoods_status");
            return (Criteria) this;
        }

        public Criteria andPut_personalgoods_statusLessThan(Integer value) {
            addCriterion("put_personalgoods_status <", value, "put_personalgoods_status");
            return (Criteria) this;
        }

        public Criteria andPut_personalgoods_statusLessThanOrEqualTo(Integer value) {
            addCriterion("put_personalgoods_status <=", value, "put_personalgoods_status");
            return (Criteria) this;
        }

        public Criteria andPut_personalgoods_statusIn(List<Integer> values) {
            addCriterion("put_personalgoods_status in", values, "put_personalgoods_status");
            return (Criteria) this;
        }

        public Criteria andPut_personalgoods_statusNotIn(List<Integer> values) {
            addCriterion("put_personalgoods_status not in", values, "put_personalgoods_status");
            return (Criteria) this;
        }

        public Criteria andPut_personalgoods_statusBetween(Integer value1, Integer value2) {
            addCriterion("put_personalgoods_status between", value1, value2, "put_personalgoods_status");
            return (Criteria) this;
        }

        public Criteria andPut_personalgoods_statusNotBetween(Integer value1, Integer value2) {
            addCriterion("put_personalgoods_status not between", value1, value2, "put_personalgoods_status");
            return (Criteria) this;
        }

        public Criteria andPut_waybill_statusIsNull() {
            addCriterion("put_waybill_status is null");
            return (Criteria) this;
        }

        public Criteria andPut_waybill_statusIsNotNull() {
            addCriterion("put_waybill_status is not null");
            return (Criteria) this;
        }

        public Criteria andPut_waybill_statusEqualTo(Integer value) {
            addCriterion("put_waybill_status =", value, "put_waybill_status");
            return (Criteria) this;
        }

        public Criteria andPut_waybill_statusNotEqualTo(Integer value) {
            addCriterion("put_waybill_status <>", value, "put_waybill_status");
            return (Criteria) this;
        }

        public Criteria andPut_waybill_statusGreaterThan(Integer value) {
            addCriterion("put_waybill_status >", value, "put_waybill_status");
            return (Criteria) this;
        }

        public Criteria andPut_waybill_statusGreaterThanOrEqualTo(Integer value) {
            addCriterion("put_waybill_status >=", value, "put_waybill_status");
            return (Criteria) this;
        }

        public Criteria andPut_waybill_statusLessThan(Integer value) {
            addCriterion("put_waybill_status <", value, "put_waybill_status");
            return (Criteria) this;
        }

        public Criteria andPut_waybill_statusLessThanOrEqualTo(Integer value) {
            addCriterion("put_waybill_status <=", value, "put_waybill_status");
            return (Criteria) this;
        }

        public Criteria andPut_waybill_statusIn(List<Integer> values) {
            addCriterion("put_waybill_status in", values, "put_waybill_status");
            return (Criteria) this;
        }

        public Criteria andPut_waybill_statusNotIn(List<Integer> values) {
            addCriterion("put_waybill_status not in", values, "put_waybill_status");
            return (Criteria) this;
        }

        public Criteria andPut_waybill_statusBetween(Integer value1, Integer value2) {
            addCriterion("put_waybill_status between", value1, value2, "put_waybill_status");
            return (Criteria) this;
        }

        public Criteria andPut_waybill_statusNotBetween(Integer value1, Integer value2) {
            addCriterion("put_waybill_status not between", value1, value2, "put_waybill_status");
            return (Criteria) this;
        }

        public Criteria andPut_pay_statusIsNull() {
            addCriterion("put_pay_status is null");
            return (Criteria) this;
        }

        public Criteria andPut_pay_statusIsNotNull() {
            addCriterion("put_pay_status is not null");
            return (Criteria) this;
        }

        public Criteria andPut_pay_statusEqualTo(Integer value) {
            addCriterion("put_pay_status =", value, "put_pay_status");
            return (Criteria) this;
        }

        public Criteria andPut_pay_statusNotEqualTo(Integer value) {
            addCriterion("put_pay_status <>", value, "put_pay_status");
            return (Criteria) this;
        }

        public Criteria andPut_pay_statusGreaterThan(Integer value) {
            addCriterion("put_pay_status >", value, "put_pay_status");
            return (Criteria) this;
        }

        public Criteria andPut_pay_statusGreaterThanOrEqualTo(Integer value) {
            addCriterion("put_pay_status >=", value, "put_pay_status");
            return (Criteria) this;
        }

        public Criteria andPut_pay_statusLessThan(Integer value) {
            addCriterion("put_pay_status <", value, "put_pay_status");
            return (Criteria) this;
        }

        public Criteria andPut_pay_statusLessThanOrEqualTo(Integer value) {
            addCriterion("put_pay_status <=", value, "put_pay_status");
            return (Criteria) this;
        }

        public Criteria andPut_pay_statusIn(List<Integer> values) {
            addCriterion("put_pay_status in", values, "put_pay_status");
            return (Criteria) this;
        }

        public Criteria andPut_pay_statusNotIn(List<Integer> values) {
            addCriterion("put_pay_status not in", values, "put_pay_status");
            return (Criteria) this;
        }

        public Criteria andPut_pay_statusBetween(Integer value1, Integer value2) {
            addCriterion("put_pay_status between", value1, value2, "put_pay_status");
            return (Criteria) this;
        }

        public Criteria andPut_pay_statusNotBetween(Integer value1, Integer value2) {
            addCriterion("put_pay_status not between", value1, value2, "put_pay_status");
            return (Criteria) this;
        }

        public Criteria andPut_customs_timesIsNull() {
            addCriterion("put_customs_times is null");
            return (Criteria) this;
        }

        public Criteria andPut_customs_timesIsNotNull() {
            addCriterion("put_customs_times is not null");
            return (Criteria) this;
        }

        public Criteria andPut_customs_timesEqualTo(Integer value) {
            addCriterion("put_customs_times =", value, "put_customs_times");
            return (Criteria) this;
        }

        public Criteria andPut_customs_timesNotEqualTo(Integer value) {
            addCriterion("put_customs_times <>", value, "put_customs_times");
            return (Criteria) this;
        }

        public Criteria andPut_customs_timesGreaterThan(Integer value) {
            addCriterion("put_customs_times >", value, "put_customs_times");
            return (Criteria) this;
        }

        public Criteria andPut_customs_timesGreaterThanOrEqualTo(Integer value) {
            addCriterion("put_customs_times >=", value, "put_customs_times");
            return (Criteria) this;
        }

        public Criteria andPut_customs_timesLessThan(Integer value) {
            addCriterion("put_customs_times <", value, "put_customs_times");
            return (Criteria) this;
        }

        public Criteria andPut_customs_timesLessThanOrEqualTo(Integer value) {
            addCriterion("put_customs_times <=", value, "put_customs_times");
            return (Criteria) this;
        }

        public Criteria andPut_customs_timesIn(List<Integer> values) {
            addCriterion("put_customs_times in", values, "put_customs_times");
            return (Criteria) this;
        }

        public Criteria andPut_customs_timesNotIn(List<Integer> values) {
            addCriterion("put_customs_times not in", values, "put_customs_times");
            return (Criteria) this;
        }

        public Criteria andPut_customs_timesBetween(Integer value1, Integer value2) {
            addCriterion("put_customs_times between", value1, value2, "put_customs_times");
            return (Criteria) this;
        }

        public Criteria andPut_customs_timesNotBetween(Integer value1, Integer value2) {
            addCriterion("put_customs_times not between", value1, value2, "put_customs_times");
            return (Criteria) this;
        }

        public Criteria andReceive_telIsNull() {
            addCriterion("receive_tel is null");
            return (Criteria) this;
        }

        public Criteria andReceive_telIsNotNull() {
            addCriterion("receive_tel is not null");
            return (Criteria) this;
        }

        public Criteria andReceive_telEqualTo(String value) {
            addCriterion("receive_tel =", value, "receive_tel");
            return (Criteria) this;
        }

        public Criteria andReceive_telNotEqualTo(String value) {
            addCriterion("receive_tel <>", value, "receive_tel");
            return (Criteria) this;
        }

        public Criteria andReceive_telGreaterThan(String value) {
            addCriterion("receive_tel >", value, "receive_tel");
            return (Criteria) this;
        }

        public Criteria andReceive_telGreaterThanOrEqualTo(String value) {
            addCriterion("receive_tel >=", value, "receive_tel");
            return (Criteria) this;
        }

        public Criteria andReceive_telLessThan(String value) {
            addCriterion("receive_tel <", value, "receive_tel");
            return (Criteria) this;
        }

        public Criteria andReceive_telLessThanOrEqualTo(String value) {
            addCriterion("receive_tel <=", value, "receive_tel");
            return (Criteria) this;
        }

        public Criteria andReceive_telLike(String value) {
            addCriterion("receive_tel like", value, "receive_tel");
            return (Criteria) this;
        }

        public Criteria andReceive_telNotLike(String value) {
            addCriterion("receive_tel not like", value, "receive_tel");
            return (Criteria) this;
        }

        public Criteria andReceive_telIn(List<String> values) {
            addCriterion("receive_tel in", values, "receive_tel");
            return (Criteria) this;
        }

        public Criteria andReceive_telNotIn(List<String> values) {
            addCriterion("receive_tel not in", values, "receive_tel");
            return (Criteria) this;
        }

        public Criteria andReceive_telBetween(String value1, String value2) {
            addCriterion("receive_tel between", value1, value2, "receive_tel");
            return (Criteria) this;
        }

        public Criteria andReceive_telNotBetween(String value1, String value2) {
            addCriterion("receive_tel not between", value1, value2, "receive_tel");
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

        public Criteria andOrder_typeIsNull() {
            addCriterion("order_type is null");
            return (Criteria) this;
        }

        public Criteria andOrder_typeIsNotNull() {
            addCriterion("order_type is not null");
            return (Criteria) this;
        }

        public Criteria andOrder_typeEqualTo(Integer value) {
            addCriterion("order_type =", value, "order_type");
            return (Criteria) this;
        }

        public Criteria andOrder_typeNotEqualTo(Integer value) {
            addCriterion("order_type <>", value, "order_type");
            return (Criteria) this;
        }

        public Criteria andOrder_typeGreaterThan(Integer value) {
            addCriterion("order_type >", value, "order_type");
            return (Criteria) this;
        }

        public Criteria andOrder_typeGreaterThanOrEqualTo(Integer value) {
            addCriterion("order_type >=", value, "order_type");
            return (Criteria) this;
        }

        public Criteria andOrder_typeLessThan(Integer value) {
            addCriterion("order_type <", value, "order_type");
            return (Criteria) this;
        }

        public Criteria andOrder_typeLessThanOrEqualTo(Integer value) {
            addCriterion("order_type <=", value, "order_type");
            return (Criteria) this;
        }

        public Criteria andOrder_typeIn(List<Integer> values) {
            addCriterion("order_type in", values, "order_type");
            return (Criteria) this;
        }

        public Criteria andOrder_typeNotIn(List<Integer> values) {
            addCriterion("order_type not in", values, "order_type");
            return (Criteria) this;
        }

        public Criteria andOrder_typeBetween(Integer value1, Integer value2) {
            addCriterion("order_type between", value1, value2, "order_type");
            return (Criteria) this;
        }

        public Criteria andOrder_typeNotBetween(Integer value1, Integer value2) {
            addCriterion("order_type not between", value1, value2, "order_type");
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

        public Criteria andInvoice_typeIsNull() {
            addCriterion("invoice_type is null");
            return (Criteria) this;
        }

        public Criteria andInvoice_typeIsNotNull() {
            addCriterion("invoice_type is not null");
            return (Criteria) this;
        }

        public Criteria andInvoice_typeEqualTo(Byte value) {
            addCriterion("invoice_type =", value, "invoice_type");
            return (Criteria) this;
        }

        public Criteria andInvoice_typeNotEqualTo(Byte value) {
            addCriterion("invoice_type <>", value, "invoice_type");
            return (Criteria) this;
        }

        public Criteria andInvoice_typeGreaterThan(Byte value) {
            addCriterion("invoice_type >", value, "invoice_type");
            return (Criteria) this;
        }

        public Criteria andInvoice_typeGreaterThanOrEqualTo(Byte value) {
            addCriterion("invoice_type >=", value, "invoice_type");
            return (Criteria) this;
        }

        public Criteria andInvoice_typeLessThan(Byte value) {
            addCriterion("invoice_type <", value, "invoice_type");
            return (Criteria) this;
        }

        public Criteria andInvoice_typeLessThanOrEqualTo(Byte value) {
            addCriterion("invoice_type <=", value, "invoice_type");
            return (Criteria) this;
        }

        public Criteria andInvoice_typeIn(List<Byte> values) {
            addCriterion("invoice_type in", values, "invoice_type");
            return (Criteria) this;
        }

        public Criteria andInvoice_typeNotIn(List<Byte> values) {
            addCriterion("invoice_type not in", values, "invoice_type");
            return (Criteria) this;
        }

        public Criteria andInvoice_typeBetween(Byte value1, Byte value2) {
            addCriterion("invoice_type between", value1, value2, "invoice_type");
            return (Criteria) this;
        }

        public Criteria andInvoice_typeNotBetween(Byte value1, Byte value2) {
            addCriterion("invoice_type not between", value1, value2, "invoice_type");
            return (Criteria) this;
        }

        public Criteria andEnt_promoter_idIsNull() {
            addCriterion("ent_promoter_id is null");
            return (Criteria) this;
        }

        public Criteria andEnt_promoter_idIsNotNull() {
            addCriterion("ent_promoter_id is not null");
            return (Criteria) this;
        }

        public Criteria andEnt_promoter_idEqualTo(Long value) {
            addCriterion("ent_promoter_id =", value, "ent_promoter_id");
            return (Criteria) this;
        }

        public Criteria andEnt_promoter_idNotEqualTo(Long value) {
            addCriterion("ent_promoter_id <>", value, "ent_promoter_id");
            return (Criteria) this;
        }

        public Criteria andEnt_promoter_idGreaterThan(Long value) {
            addCriterion("ent_promoter_id >", value, "ent_promoter_id");
            return (Criteria) this;
        }

        public Criteria andEnt_promoter_idGreaterThanOrEqualTo(Long value) {
            addCriterion("ent_promoter_id >=", value, "ent_promoter_id");
            return (Criteria) this;
        }

        public Criteria andEnt_promoter_idLessThan(Long value) {
            addCriterion("ent_promoter_id <", value, "ent_promoter_id");
            return (Criteria) this;
        }

        public Criteria andEnt_promoter_idLessThanOrEqualTo(Long value) {
            addCriterion("ent_promoter_id <=", value, "ent_promoter_id");
            return (Criteria) this;
        }

        public Criteria andEnt_promoter_idIn(List<Long> values) {
            addCriterion("ent_promoter_id in", values, "ent_promoter_id");
            return (Criteria) this;
        }

        public Criteria andEnt_promoter_idNotIn(List<Long> values) {
            addCriterion("ent_promoter_id not in", values, "ent_promoter_id");
            return (Criteria) this;
        }

        public Criteria andEnt_promoter_idBetween(Long value1, Long value2) {
            addCriterion("ent_promoter_id between", value1, value2, "ent_promoter_id");
            return (Criteria) this;
        }

        public Criteria andEnt_promoter_idNotBetween(Long value1, Long value2) {
            addCriterion("ent_promoter_id not between", value1, value2, "ent_promoter_id");
            return (Criteria) this;
        }

        public Criteria andEnterprise_idIsNull() {
            addCriterion("enterprise_id is null");
            return (Criteria) this;
        }

        public Criteria andEnterprise_idIsNotNull() {
            addCriterion("enterprise_id is not null");
            return (Criteria) this;
        }

        public Criteria andEnterprise_idEqualTo(Long value) {
            addCriterion("enterprise_id =", value, "enterprise_id");
            return (Criteria) this;
        }

        public Criteria andEnterprise_idNotEqualTo(Long value) {
            addCriterion("enterprise_id <>", value, "enterprise_id");
            return (Criteria) this;
        }

        public Criteria andEnterprise_idGreaterThan(Long value) {
            addCriterion("enterprise_id >", value, "enterprise_id");
            return (Criteria) this;
        }

        public Criteria andEnterprise_idGreaterThanOrEqualTo(Long value) {
            addCriterion("enterprise_id >=", value, "enterprise_id");
            return (Criteria) this;
        }

        public Criteria andEnterprise_idLessThan(Long value) {
            addCriterion("enterprise_id <", value, "enterprise_id");
            return (Criteria) this;
        }

        public Criteria andEnterprise_idLessThanOrEqualTo(Long value) {
            addCriterion("enterprise_id <=", value, "enterprise_id");
            return (Criteria) this;
        }

        public Criteria andEnterprise_idIn(List<Long> values) {
            addCriterion("enterprise_id in", values, "enterprise_id");
            return (Criteria) this;
        }

        public Criteria andEnterprise_idNotIn(List<Long> values) {
            addCriterion("enterprise_id not in", values, "enterprise_id");
            return (Criteria) this;
        }

        public Criteria andEnterprise_idBetween(Long value1, Long value2) {
            addCriterion("enterprise_id between", value1, value2, "enterprise_id");
            return (Criteria) this;
        }

        public Criteria andEnterprise_idNotBetween(Long value1, Long value2) {
            addCriterion("enterprise_id not between", value1, value2, "enterprise_id");
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