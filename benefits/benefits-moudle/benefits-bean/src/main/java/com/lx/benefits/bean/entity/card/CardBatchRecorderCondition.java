package com.lx.benefits.bean.entity.card;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CardBatchRecorderCondition {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private Integer limit;

    private Integer offset;

    public CardBatchRecorderCondition() {
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

        public Criteria andBatchIdIsNull() {
            addCriterion("batch_id is null");
            return (Criteria) this;
        }

        public Criteria andBatchIdIsNotNull() {
            addCriterion("batch_id is not null");
            return (Criteria) this;
        }

        public Criteria andBatchIdEqualTo(Integer value) {
            addCriterion("batch_id =", value, "batchId");
            return (Criteria) this;
        }

        public Criteria andBatchIdNotEqualTo(Integer value) {
            addCriterion("batch_id <>", value, "batchId");
            return (Criteria) this;
        }

        public Criteria andBatchIdGreaterThan(Integer value) {
            addCriterion("batch_id >", value, "batchId");
            return (Criteria) this;
        }

        public Criteria andBatchIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("batch_id >=", value, "batchId");
            return (Criteria) this;
        }

        public Criteria andBatchIdLessThan(Integer value) {
            addCriterion("batch_id <", value, "batchId");
            return (Criteria) this;
        }

        public Criteria andBatchIdLessThanOrEqualTo(Integer value) {
            addCriterion("batch_id <=", value, "batchId");
            return (Criteria) this;
        }

        public Criteria andBatchIdIn(List<Integer> values) {
            addCriterion("batch_id in", values, "batchId");
            return (Criteria) this;
        }

        public Criteria andBatchIdNotIn(List<Integer> values) {
            addCriterion("batch_id not in", values, "batchId");
            return (Criteria) this;
        }

        public Criteria andBatchIdBetween(Integer value1, Integer value2) {
            addCriterion("batch_id between", value1, value2, "batchId");
            return (Criteria) this;
        }

        public Criteria andBatchIdNotBetween(Integer value1, Integer value2) {
            addCriterion("batch_id not between", value1, value2, "batchId");
            return (Criteria) this;
        }

        public Criteria andCustomerBatchIdIsNull() {
            addCriterion("customer_batch_id is null");
            return (Criteria) this;
        }

        public Criteria andCustomerBatchIdIsNotNull() {
            addCriterion("customer_batch_id is not null");
            return (Criteria) this;
        }

        public Criteria andCustomerBatchIdEqualTo(Integer value) {
            addCriterion("customer_batch_id =", value, "customerBatchId");
            return (Criteria) this;
        }

        public Criteria andCustomerBatchIdNotEqualTo(Integer value) {
            addCriterion("customer_batch_id <>", value, "customerBatchId");
            return (Criteria) this;
        }

        public Criteria andCustomerBatchIdGreaterThan(Integer value) {
            addCriterion("customer_batch_id >", value, "customerBatchId");
            return (Criteria) this;
        }

        public Criteria andCustomerBatchIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("customer_batch_id >=", value, "customerBatchId");
            return (Criteria) this;
        }

        public Criteria andCustomerBatchIdLessThan(Integer value) {
            addCriterion("customer_batch_id <", value, "customerBatchId");
            return (Criteria) this;
        }

        public Criteria andCustomerBatchIdLessThanOrEqualTo(Integer value) {
            addCriterion("customer_batch_id <=", value, "customerBatchId");
            return (Criteria) this;
        }

        public Criteria andCustomerBatchIdIn(List<Integer> values) {
            addCriterion("customer_batch_id in", values, "customerBatchId");
            return (Criteria) this;
        }

        public Criteria andCustomerBatchIdNotIn(List<Integer> values) {
            addCriterion("customer_batch_id not in", values, "customerBatchId");
            return (Criteria) this;
        }

        public Criteria andCustomerBatchIdBetween(Integer value1, Integer value2) {
            addCriterion("customer_batch_id between", value1, value2, "customerBatchId");
            return (Criteria) this;
        }

        public Criteria andCustomerBatchIdNotBetween(Integer value1, Integer value2) {
            addCriterion("customer_batch_id not between", value1, value2, "customerBatchId");
            return (Criteria) this;
        }

        public Criteria andCustomerIdIsNull() {
            addCriterion("customer_id is null");
            return (Criteria) this;
        }

        public Criteria andCustomerIdIsNotNull() {
            addCriterion("customer_id is not null");
            return (Criteria) this;
        }

        public Criteria andCustomerIdEqualTo(Long value) {
            addCriterion("customer_id =", value, "customerId");
            return (Criteria) this;
        }

        public Criteria andCustomerIdNotEqualTo(Long value) {
            addCriterion("customer_id <>", value, "customerId");
            return (Criteria) this;
        }

        public Criteria andCustomerIdGreaterThan(Long value) {
            addCriterion("customer_id >", value, "customerId");
            return (Criteria) this;
        }

        public Criteria andCustomerIdGreaterThanOrEqualTo(Long value) {
            addCriterion("customer_id >=", value, "customerId");
            return (Criteria) this;
        }

        public Criteria andCustomerIdLessThan(Long value) {
            addCriterion("customer_id <", value, "customerId");
            return (Criteria) this;
        }

        public Criteria andCustomerIdLessThanOrEqualTo(Long value) {
            addCriterion("customer_id <=", value, "customerId");
            return (Criteria) this;
        }

        public Criteria andCustomerIdIn(List<Long> values) {
            addCriterion("customer_id in", values, "customerId");
            return (Criteria) this;
        }

        public Criteria andCustomerIdNotIn(List<Long> values) {
            addCriterion("customer_id not in", values, "customerId");
            return (Criteria) this;
        }

        public Criteria andCustomerIdBetween(Long value1, Long value2) {
            addCriterion("customer_id between", value1, value2, "customerId");
            return (Criteria) this;
        }

        public Criteria andCustomerIdNotBetween(Long value1, Long value2) {
            addCriterion("customer_id not between", value1, value2, "customerId");
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

        public Criteria andTypeEqualTo(Integer value) {
            addCriterion("type =", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotEqualTo(Integer value) {
            addCriterion("type <>", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThan(Integer value) {
            addCriterion("type >", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("type >=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThan(Integer value) {
            addCriterion("type <", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThanOrEqualTo(Integer value) {
            addCriterion("type <=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeIn(List<Integer> values) {
            addCriterion("type in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotIn(List<Integer> values) {
            addCriterion("type not in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeBetween(Integer value1, Integer value2) {
            addCriterion("type between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("type not between", value1, value2, "type");
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

        public Criteria andSaleOrderIdIsNull() {
            addCriterion("sale_order_id is null");
            return (Criteria) this;
        }

        public Criteria andSaleOrderIdIsNotNull() {
            addCriterion("sale_order_id is not null");
            return (Criteria) this;
        }

        public Criteria andSaleOrderIdEqualTo(Integer value) {
            addCriterion("sale_order_id =", value, "saleOrderId");
            return (Criteria) this;
        }

        public Criteria andSaleOrderIdNotEqualTo(Integer value) {
            addCriterion("sale_order_id <>", value, "saleOrderId");
            return (Criteria) this;
        }

        public Criteria andSaleOrderIdGreaterThan(Integer value) {
            addCriterion("sale_order_id >", value, "saleOrderId");
            return (Criteria) this;
        }

        public Criteria andSaleOrderIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("sale_order_id >=", value, "saleOrderId");
            return (Criteria) this;
        }

        public Criteria andSaleOrderIdLessThan(Integer value) {
            addCriterion("sale_order_id <", value, "saleOrderId");
            return (Criteria) this;
        }

        public Criteria andSaleOrderIdLessThanOrEqualTo(Integer value) {
            addCriterion("sale_order_id <=", value, "saleOrderId");
            return (Criteria) this;
        }

        public Criteria andSaleOrderIdIn(List<Integer> values) {
            addCriterion("sale_order_id in", values, "saleOrderId");
            return (Criteria) this;
        }

        public Criteria andSaleOrderIdNotIn(List<Integer> values) {
            addCriterion("sale_order_id not in", values, "saleOrderId");
            return (Criteria) this;
        }

        public Criteria andSaleOrderIdBetween(Integer value1, Integer value2) {
            addCriterion("sale_order_id between", value1, value2, "saleOrderId");
            return (Criteria) this;
        }

        public Criteria andSaleOrderIdNotBetween(Integer value1, Integer value2) {
            addCriterion("sale_order_id not between", value1, value2, "saleOrderId");
            return (Criteria) this;
        }

        public Criteria andMakeUserIsNull() {
            addCriterion("make_user is null");
            return (Criteria) this;
        }

        public Criteria andMakeUserIsNotNull() {
            addCriterion("make_user is not null");
            return (Criteria) this;
        }

        public Criteria andMakeUserEqualTo(String value) {
            addCriterion("make_user =", value, "makeUser");
            return (Criteria) this;
        }

        public Criteria andMakeUserNotEqualTo(String value) {
            addCriterion("make_user <>", value, "makeUser");
            return (Criteria) this;
        }

        public Criteria andMakeUserGreaterThan(String value) {
            addCriterion("make_user >", value, "makeUser");
            return (Criteria) this;
        }

        public Criteria andMakeUserGreaterThanOrEqualTo(String value) {
            addCriterion("make_user >=", value, "makeUser");
            return (Criteria) this;
        }

        public Criteria andMakeUserLessThan(String value) {
            addCriterion("make_user <", value, "makeUser");
            return (Criteria) this;
        }

        public Criteria andMakeUserLessThanOrEqualTo(String value) {
            addCriterion("make_user <=", value, "makeUser");
            return (Criteria) this;
        }

        public Criteria andMakeUserLike(String value) {
            addCriterion("make_user like", value, "makeUser");
            return (Criteria) this;
        }

        public Criteria andMakeUserNotLike(String value) {
            addCriterion("make_user not like", value, "makeUser");
            return (Criteria) this;
        }

        public Criteria andMakeUserIn(List<String> values) {
            addCriterion("make_user in", values, "makeUser");
            return (Criteria) this;
        }

        public Criteria andMakeUserNotIn(List<String> values) {
            addCriterion("make_user not in", values, "makeUser");
            return (Criteria) this;
        }

        public Criteria andMakeUserBetween(String value1, String value2) {
            addCriterion("make_user between", value1, value2, "makeUser");
            return (Criteria) this;
        }

        public Criteria andMakeUserNotBetween(String value1, String value2) {
            addCriterion("make_user not between", value1, value2, "makeUser");
            return (Criteria) this;
        }

        public Criteria andMakeTimeIsNull() {
            addCriterion("make_time is null");
            return (Criteria) this;
        }

        public Criteria andMakeTimeIsNotNull() {
            addCriterion("make_time is not null");
            return (Criteria) this;
        }

        public Criteria andMakeTimeEqualTo(Date value) {
            addCriterion("make_time =", value, "makeTime");
            return (Criteria) this;
        }

        public Criteria andMakeTimeNotEqualTo(Date value) {
            addCriterion("make_time <>", value, "makeTime");
            return (Criteria) this;
        }

        public Criteria andMakeTimeGreaterThan(Date value) {
            addCriterion("make_time >", value, "makeTime");
            return (Criteria) this;
        }

        public Criteria andMakeTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("make_time >=", value, "makeTime");
            return (Criteria) this;
        }

        public Criteria andMakeTimeLessThan(Date value) {
            addCriterion("make_time <", value, "makeTime");
            return (Criteria) this;
        }

        public Criteria andMakeTimeLessThanOrEqualTo(Date value) {
            addCriterion("make_time <=", value, "makeTime");
            return (Criteria) this;
        }

        public Criteria andMakeTimeIn(List<Date> values) {
            addCriterion("make_time in", values, "makeTime");
            return (Criteria) this;
        }

        public Criteria andMakeTimeNotIn(List<Date> values) {
            addCriterion("make_time not in", values, "makeTime");
            return (Criteria) this;
        }

        public Criteria andMakeTimeBetween(Date value1, Date value2) {
            addCriterion("make_time between", value1, value2, "makeTime");
            return (Criteria) this;
        }

        public Criteria andMakeTimeNotBetween(Date value1, Date value2) {
            addCriterion("make_time not between", value1, value2, "makeTime");
            return (Criteria) this;
        }

        public Criteria andStoreUserIsNull() {
            addCriterion("store_user is null");
            return (Criteria) this;
        }

        public Criteria andStoreUserIsNotNull() {
            addCriterion("store_user is not null");
            return (Criteria) this;
        }

        public Criteria andStoreUserEqualTo(String value) {
            addCriterion("store_user =", value, "storeUser");
            return (Criteria) this;
        }

        public Criteria andStoreUserNotEqualTo(String value) {
            addCriterion("store_user <>", value, "storeUser");
            return (Criteria) this;
        }

        public Criteria andStoreUserGreaterThan(String value) {
            addCriterion("store_user >", value, "storeUser");
            return (Criteria) this;
        }

        public Criteria andStoreUserGreaterThanOrEqualTo(String value) {
            addCriterion("store_user >=", value, "storeUser");
            return (Criteria) this;
        }

        public Criteria andStoreUserLessThan(String value) {
            addCriterion("store_user <", value, "storeUser");
            return (Criteria) this;
        }

        public Criteria andStoreUserLessThanOrEqualTo(String value) {
            addCriterion("store_user <=", value, "storeUser");
            return (Criteria) this;
        }

        public Criteria andStoreUserLike(String value) {
            addCriterion("store_user like", value, "storeUser");
            return (Criteria) this;
        }

        public Criteria andStoreUserNotLike(String value) {
            addCriterion("store_user not like", value, "storeUser");
            return (Criteria) this;
        }

        public Criteria andStoreUserIn(List<String> values) {
            addCriterion("store_user in", values, "storeUser");
            return (Criteria) this;
        }

        public Criteria andStoreUserNotIn(List<String> values) {
            addCriterion("store_user not in", values, "storeUser");
            return (Criteria) this;
        }

        public Criteria andStoreUserBetween(String value1, String value2) {
            addCriterion("store_user between", value1, value2, "storeUser");
            return (Criteria) this;
        }

        public Criteria andStoreUserNotBetween(String value1, String value2) {
            addCriterion("store_user not between", value1, value2, "storeUser");
            return (Criteria) this;
        }

        public Criteria andStoreTimeIsNull() {
            addCriterion("store_time is null");
            return (Criteria) this;
        }

        public Criteria andStoreTimeIsNotNull() {
            addCriterion("store_time is not null");
            return (Criteria) this;
        }

        public Criteria andStoreTimeEqualTo(Date value) {
            addCriterion("store_time =", value, "storeTime");
            return (Criteria) this;
        }

        public Criteria andStoreTimeNotEqualTo(Date value) {
            addCriterion("store_time <>", value, "storeTime");
            return (Criteria) this;
        }

        public Criteria andStoreTimeGreaterThan(Date value) {
            addCriterion("store_time >", value, "storeTime");
            return (Criteria) this;
        }

        public Criteria andStoreTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("store_time >=", value, "storeTime");
            return (Criteria) this;
        }

        public Criteria andStoreTimeLessThan(Date value) {
            addCriterion("store_time <", value, "storeTime");
            return (Criteria) this;
        }

        public Criteria andStoreTimeLessThanOrEqualTo(Date value) {
            addCriterion("store_time <=", value, "storeTime");
            return (Criteria) this;
        }

        public Criteria andStoreTimeIn(List<Date> values) {
            addCriterion("store_time in", values, "storeTime");
            return (Criteria) this;
        }

        public Criteria andStoreTimeNotIn(List<Date> values) {
            addCriterion("store_time not in", values, "storeTime");
            return (Criteria) this;
        }

        public Criteria andStoreTimeBetween(Date value1, Date value2) {
            addCriterion("store_time between", value1, value2, "storeTime");
            return (Criteria) this;
        }

        public Criteria andStoreTimeNotBetween(Date value1, Date value2) {
            addCriterion("store_time not between", value1, value2, "storeTime");
            return (Criteria) this;
        }

        public Criteria andDeliverUserIsNull() {
            addCriterion("deliver_user is null");
            return (Criteria) this;
        }

        public Criteria andDeliverUserIsNotNull() {
            addCriterion("deliver_user is not null");
            return (Criteria) this;
        }

        public Criteria andDeliverUserEqualTo(String value) {
            addCriterion("deliver_user =", value, "deliverUser");
            return (Criteria) this;
        }

        public Criteria andDeliverUserNotEqualTo(String value) {
            addCriterion("deliver_user <>", value, "deliverUser");
            return (Criteria) this;
        }

        public Criteria andDeliverUserGreaterThan(String value) {
            addCriterion("deliver_user >", value, "deliverUser");
            return (Criteria) this;
        }

        public Criteria andDeliverUserGreaterThanOrEqualTo(String value) {
            addCriterion("deliver_user >=", value, "deliverUser");
            return (Criteria) this;
        }

        public Criteria andDeliverUserLessThan(String value) {
            addCriterion("deliver_user <", value, "deliverUser");
            return (Criteria) this;
        }

        public Criteria andDeliverUserLessThanOrEqualTo(String value) {
            addCriterion("deliver_user <=", value, "deliverUser");
            return (Criteria) this;
        }

        public Criteria andDeliverUserLike(String value) {
            addCriterion("deliver_user like", value, "deliverUser");
            return (Criteria) this;
        }

        public Criteria andDeliverUserNotLike(String value) {
            addCriterion("deliver_user not like", value, "deliverUser");
            return (Criteria) this;
        }

        public Criteria andDeliverUserIn(List<String> values) {
            addCriterion("deliver_user in", values, "deliverUser");
            return (Criteria) this;
        }

        public Criteria andDeliverUserNotIn(List<String> values) {
            addCriterion("deliver_user not in", values, "deliverUser");
            return (Criteria) this;
        }

        public Criteria andDeliverUserBetween(String value1, String value2) {
            addCriterion("deliver_user between", value1, value2, "deliverUser");
            return (Criteria) this;
        }

        public Criteria andDeliverUserNotBetween(String value1, String value2) {
            addCriterion("deliver_user not between", value1, value2, "deliverUser");
            return (Criteria) this;
        }

        public Criteria andDeliverTimeIsNull() {
            addCriterion("deliver_time is null");
            return (Criteria) this;
        }

        public Criteria andDeliverTimeIsNotNull() {
            addCriterion("deliver_time is not null");
            return (Criteria) this;
        }

        public Criteria andDeliverTimeEqualTo(Date value) {
            addCriterion("deliver_time =", value, "deliverTime");
            return (Criteria) this;
        }

        public Criteria andDeliverTimeNotEqualTo(Date value) {
            addCriterion("deliver_time <>", value, "deliverTime");
            return (Criteria) this;
        }

        public Criteria andDeliverTimeGreaterThan(Date value) {
            addCriterion("deliver_time >", value, "deliverTime");
            return (Criteria) this;
        }

        public Criteria andDeliverTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("deliver_time >=", value, "deliverTime");
            return (Criteria) this;
        }

        public Criteria andDeliverTimeLessThan(Date value) {
            addCriterion("deliver_time <", value, "deliverTime");
            return (Criteria) this;
        }

        public Criteria andDeliverTimeLessThanOrEqualTo(Date value) {
            addCriterion("deliver_time <=", value, "deliverTime");
            return (Criteria) this;
        }

        public Criteria andDeliverTimeIn(List<Date> values) {
            addCriterion("deliver_time in", values, "deliverTime");
            return (Criteria) this;
        }

        public Criteria andDeliverTimeNotIn(List<Date> values) {
            addCriterion("deliver_time not in", values, "deliverTime");
            return (Criteria) this;
        }

        public Criteria andDeliverTimeBetween(Date value1, Date value2) {
            addCriterion("deliver_time between", value1, value2, "deliverTime");
            return (Criteria) this;
        }

        public Criteria andDeliverTimeNotBetween(Date value1, Date value2) {
            addCriterion("deliver_time not between", value1, value2, "deliverTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIsNull() {
            addCriterion("update_user is null");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIsNotNull() {
            addCriterion("update_user is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateUserEqualTo(String value) {
            addCriterion("update_user =", value, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserNotEqualTo(String value) {
            addCriterion("update_user <>", value, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserGreaterThan(String value) {
            addCriterion("update_user >", value, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserGreaterThanOrEqualTo(String value) {
            addCriterion("update_user >=", value, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserLessThan(String value) {
            addCriterion("update_user <", value, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserLessThanOrEqualTo(String value) {
            addCriterion("update_user <=", value, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserLike(String value) {
            addCriterion("update_user like", value, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserNotLike(String value) {
            addCriterion("update_user not like", value, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIn(List<String> values) {
            addCriterion("update_user in", values, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserNotIn(List<String> values) {
            addCriterion("update_user not in", values, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserBetween(String value1, String value2) {
            addCriterion("update_user between", value1, value2, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserNotBetween(String value1, String value2) {
            addCriterion("update_user not between", value1, value2, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNull() {
            addCriterion("update_time is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("update_time is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(Date value) {
            addCriterion("update_time =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Date value) {
            addCriterion("update_time <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Date value) {
            addCriterion("update_time >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("update_time >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Date value) {
            addCriterion("update_time <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("update_time <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Date> values) {
            addCriterion("update_time in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Date> values) {
            addCriterion("update_time not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("update_time between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("update_time not between", value1, value2, "updateTime");
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