package com.lx.benefits.bean.entity.card;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CardSaleOrderCondition {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private Integer limit;

    private Integer offset;

    public CardSaleOrderCondition() {
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

        public Criteria andBindEnterprIdIsNull() {
            addCriterion("bind_enterpr_id is null");
            return (Criteria) this;
        }

        public Criteria andBindEnterprIdIsNotNull() {
            addCriterion("bind_enterpr_id is not null");
            return (Criteria) this;
        }

        public Criteria andBindEnterprIdEqualTo(Long value) {
            addCriterion("bind_enterpr_id =", value, "bindEnterprId");
            return (Criteria) this;
        }

        public Criteria andBindEnterprIdNotEqualTo(Long value) {
            addCriterion("bind_enterpr_id <>", value, "bindEnterprId");
            return (Criteria) this;
        }

        public Criteria andBindEnterprIdGreaterThan(Long value) {
            addCriterion("bind_enterpr_id >", value, "bindEnterprId");
            return (Criteria) this;
        }

        public Criteria andBindEnterprIdGreaterThanOrEqualTo(Long value) {
            addCriterion("bind_enterpr_id >=", value, "bindEnterprId");
            return (Criteria) this;
        }

        public Criteria andBindEnterprIdLessThan(Long value) {
            addCriterion("bind_enterpr_id <", value, "bindEnterprId");
            return (Criteria) this;
        }

        public Criteria andBindEnterprIdLessThanOrEqualTo(Long value) {
            addCriterion("bind_enterpr_id <=", value, "bindEnterprId");
            return (Criteria) this;
        }

        public Criteria andBindEnterprIdIn(List<Long> values) {
            addCriterion("bind_enterpr_id in", values, "bindEnterprId");
            return (Criteria) this;
        }

        public Criteria andBindEnterprIdNotIn(List<Long> values) {
            addCriterion("bind_enterpr_id not in", values, "bindEnterprId");
            return (Criteria) this;
        }

        public Criteria andBindEnterprIdBetween(Long value1, Long value2) {
            addCriterion("bind_enterpr_id between", value1, value2, "bindEnterprId");
            return (Criteria) this;
        }

        public Criteria andBindEnterprIdNotBetween(Long value1, Long value2) {
            addCriterion("bind_enterpr_id not between", value1, value2, "bindEnterprId");
            return (Criteria) this;
        }

        public Criteria andIsCustomCardIsNull() {
            addCriterion("is_custom_card is null");
            return (Criteria) this;
        }

        public Criteria andIsCustomCardIsNotNull() {
            addCriterion("is_custom_card is not null");
            return (Criteria) this;
        }

        public Criteria andIsCustomCardEqualTo(Byte value) {
            addCriterion("is_custom_card =", value, "isCustomCard");
            return (Criteria) this;
        }

        public Criteria andIsCustomCardNotEqualTo(Byte value) {
            addCriterion("is_custom_card <>", value, "isCustomCard");
            return (Criteria) this;
        }

        public Criteria andIsCustomCardGreaterThan(Byte value) {
            addCriterion("is_custom_card >", value, "isCustomCard");
            return (Criteria) this;
        }

        public Criteria andIsCustomCardGreaterThanOrEqualTo(Byte value) {
            addCriterion("is_custom_card >=", value, "isCustomCard");
            return (Criteria) this;
        }

        public Criteria andIsCustomCardLessThan(Byte value) {
            addCriterion("is_custom_card <", value, "isCustomCard");
            return (Criteria) this;
        }

        public Criteria andIsCustomCardLessThanOrEqualTo(Byte value) {
            addCriterion("is_custom_card <=", value, "isCustomCard");
            return (Criteria) this;
        }

        public Criteria andIsCustomCardIn(List<Byte> values) {
            addCriterion("is_custom_card in", values, "isCustomCard");
            return (Criteria) this;
        }

        public Criteria andIsCustomCardNotIn(List<Byte> values) {
            addCriterion("is_custom_card not in", values, "isCustomCard");
            return (Criteria) this;
        }

        public Criteria andIsCustomCardBetween(Byte value1, Byte value2) {
            addCriterion("is_custom_card between", value1, value2, "isCustomCard");
            return (Criteria) this;
        }

        public Criteria andIsCustomCardNotBetween(Byte value1, Byte value2) {
            addCriterion("is_custom_card not between", value1, value2, "isCustomCard");
            return (Criteria) this;
        }

        public Criteria andPayTypeIsNull() {
            addCriterion("pay_type is null");
            return (Criteria) this;
        }

        public Criteria andPayTypeIsNotNull() {
            addCriterion("pay_type is not null");
            return (Criteria) this;
        }

        public Criteria andPayTypeEqualTo(Integer value) {
            addCriterion("pay_type =", value, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeNotEqualTo(Integer value) {
            addCriterion("pay_type <>", value, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeGreaterThan(Integer value) {
            addCriterion("pay_type >", value, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("pay_type >=", value, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeLessThan(Integer value) {
            addCriterion("pay_type <", value, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeLessThanOrEqualTo(Integer value) {
            addCriterion("pay_type <=", value, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeIn(List<Integer> values) {
            addCriterion("pay_type in", values, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeNotIn(List<Integer> values) {
            addCriterion("pay_type not in", values, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeBetween(Integer value1, Integer value2) {
            addCriterion("pay_type between", value1, value2, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("pay_type not between", value1, value2, "payType");
            return (Criteria) this;
        }

        public Criteria andDiscountOnSaleIsNull() {
            addCriterion("discount_on_sale is null");
            return (Criteria) this;
        }

        public Criteria andDiscountOnSaleIsNotNull() {
            addCriterion("discount_on_sale is not null");
            return (Criteria) this;
        }

        public Criteria andDiscountOnSaleEqualTo(BigDecimal value) {
            addCriterion("discount_on_sale =", value, "discountOnSale");
            return (Criteria) this;
        }

        public Criteria andDiscountOnSaleNotEqualTo(BigDecimal value) {
            addCriterion("discount_on_sale <>", value, "discountOnSale");
            return (Criteria) this;
        }

        public Criteria andDiscountOnSaleGreaterThan(BigDecimal value) {
            addCriterion("discount_on_sale >", value, "discountOnSale");
            return (Criteria) this;
        }

        public Criteria andDiscountOnSaleGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("discount_on_sale >=", value, "discountOnSale");
            return (Criteria) this;
        }

        public Criteria andDiscountOnSaleLessThan(BigDecimal value) {
            addCriterion("discount_on_sale <", value, "discountOnSale");
            return (Criteria) this;
        }

        public Criteria andDiscountOnSaleLessThanOrEqualTo(BigDecimal value) {
            addCriterion("discount_on_sale <=", value, "discountOnSale");
            return (Criteria) this;
        }

        public Criteria andDiscountOnSaleIn(List<BigDecimal> values) {
            addCriterion("discount_on_sale in", values, "discountOnSale");
            return (Criteria) this;
        }

        public Criteria andDiscountOnSaleNotIn(List<BigDecimal> values) {
            addCriterion("discount_on_sale not in", values, "discountOnSale");
            return (Criteria) this;
        }

        public Criteria andDiscountOnSaleBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("discount_on_sale between", value1, value2, "discountOnSale");
            return (Criteria) this;
        }

        public Criteria andDiscountOnSaleNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("discount_on_sale not between", value1, value2, "discountOnSale");
            return (Criteria) this;
        }

        public Criteria andSumPayableIsNull() {
            addCriterion("sum_payable is null");
            return (Criteria) this;
        }

        public Criteria andSumPayableIsNotNull() {
            addCriterion("sum_payable is not null");
            return (Criteria) this;
        }

        public Criteria andSumPayableEqualTo(BigDecimal value) {
            addCriterion("sum_payable =", value, "sumPayable");
            return (Criteria) this;
        }

        public Criteria andSumPayableNotEqualTo(BigDecimal value) {
            addCriterion("sum_payable <>", value, "sumPayable");
            return (Criteria) this;
        }

        public Criteria andSumPayableGreaterThan(BigDecimal value) {
            addCriterion("sum_payable >", value, "sumPayable");
            return (Criteria) this;
        }

        public Criteria andSumPayableGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("sum_payable >=", value, "sumPayable");
            return (Criteria) this;
        }

        public Criteria andSumPayableLessThan(BigDecimal value) {
            addCriterion("sum_payable <", value, "sumPayable");
            return (Criteria) this;
        }

        public Criteria andSumPayableLessThanOrEqualTo(BigDecimal value) {
            addCriterion("sum_payable <=", value, "sumPayable");
            return (Criteria) this;
        }

        public Criteria andSumPayableIn(List<BigDecimal> values) {
            addCriterion("sum_payable in", values, "sumPayable");
            return (Criteria) this;
        }

        public Criteria andSumPayableNotIn(List<BigDecimal> values) {
            addCriterion("sum_payable not in", values, "sumPayable");
            return (Criteria) this;
        }

        public Criteria andSumPayableBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("sum_payable between", value1, value2, "sumPayable");
            return (Criteria) this;
        }

        public Criteria andSumPayableNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("sum_payable not between", value1, value2, "sumPayable");
            return (Criteria) this;
        }

        public Criteria andPaidAmountIsNull() {
            addCriterion("paid_amount is null");
            return (Criteria) this;
        }

        public Criteria andPaidAmountIsNotNull() {
            addCriterion("paid_amount is not null");
            return (Criteria) this;
        }

        public Criteria andPaidAmountEqualTo(BigDecimal value) {
            addCriterion("paid_amount =", value, "paidAmount");
            return (Criteria) this;
        }

        public Criteria andPaidAmountNotEqualTo(BigDecimal value) {
            addCriterion("paid_amount <>", value, "paidAmount");
            return (Criteria) this;
        }

        public Criteria andPaidAmountGreaterThan(BigDecimal value) {
            addCriterion("paid_amount >", value, "paidAmount");
            return (Criteria) this;
        }

        public Criteria andPaidAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("paid_amount >=", value, "paidAmount");
            return (Criteria) this;
        }

        public Criteria andPaidAmountLessThan(BigDecimal value) {
            addCriterion("paid_amount <", value, "paidAmount");
            return (Criteria) this;
        }

        public Criteria andPaidAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("paid_amount <=", value, "paidAmount");
            return (Criteria) this;
        }

        public Criteria andPaidAmountIn(List<BigDecimal> values) {
            addCriterion("paid_amount in", values, "paidAmount");
            return (Criteria) this;
        }

        public Criteria andPaidAmountNotIn(List<BigDecimal> values) {
            addCriterion("paid_amount not in", values, "paidAmount");
            return (Criteria) this;
        }

        public Criteria andPaidAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("paid_amount between", value1, value2, "paidAmount");
            return (Criteria) this;
        }

        public Criteria andPaidAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("paid_amount not between", value1, value2, "paidAmount");
            return (Criteria) this;
        }

        public Criteria andCreateUserIsNull() {
            addCriterion("create_user is null");
            return (Criteria) this;
        }

        public Criteria andCreateUserIsNotNull() {
            addCriterion("create_user is not null");
            return (Criteria) this;
        }

        public Criteria andCreateUserEqualTo(String value) {
            addCriterion("create_user =", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserNotEqualTo(String value) {
            addCriterion("create_user <>", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserGreaterThan(String value) {
            addCriterion("create_user >", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserGreaterThanOrEqualTo(String value) {
            addCriterion("create_user >=", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserLessThan(String value) {
            addCriterion("create_user <", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserLessThanOrEqualTo(String value) {
            addCriterion("create_user <=", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserLike(String value) {
            addCriterion("create_user like", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserNotLike(String value) {
            addCriterion("create_user not like", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserIn(List<String> values) {
            addCriterion("create_user in", values, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserNotIn(List<String> values) {
            addCriterion("create_user not in", values, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserBetween(String value1, String value2) {
            addCriterion("create_user between", value1, value2, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserNotBetween(String value1, String value2) {
            addCriterion("create_user not between", value1, value2, "createUser");
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

        public Criteria andVerifyUserIsNull() {
            addCriterion("verify_user is null");
            return (Criteria) this;
        }

        public Criteria andVerifyUserIsNotNull() {
            addCriterion("verify_user is not null");
            return (Criteria) this;
        }

        public Criteria andVerifyUserEqualTo(String value) {
            addCriterion("verify_user =", value, "verifyUser");
            return (Criteria) this;
        }

        public Criteria andVerifyUserNotEqualTo(String value) {
            addCriterion("verify_user <>", value, "verifyUser");
            return (Criteria) this;
        }

        public Criteria andVerifyUserGreaterThan(String value) {
            addCriterion("verify_user >", value, "verifyUser");
            return (Criteria) this;
        }

        public Criteria andVerifyUserGreaterThanOrEqualTo(String value) {
            addCriterion("verify_user >=", value, "verifyUser");
            return (Criteria) this;
        }

        public Criteria andVerifyUserLessThan(String value) {
            addCriterion("verify_user <", value, "verifyUser");
            return (Criteria) this;
        }

        public Criteria andVerifyUserLessThanOrEqualTo(String value) {
            addCriterion("verify_user <=", value, "verifyUser");
            return (Criteria) this;
        }

        public Criteria andVerifyUserLike(String value) {
            addCriterion("verify_user like", value, "verifyUser");
            return (Criteria) this;
        }

        public Criteria andVerifyUserNotLike(String value) {
            addCriterion("verify_user not like", value, "verifyUser");
            return (Criteria) this;
        }

        public Criteria andVerifyUserIn(List<String> values) {
            addCriterion("verify_user in", values, "verifyUser");
            return (Criteria) this;
        }

        public Criteria andVerifyUserNotIn(List<String> values) {
            addCriterion("verify_user not in", values, "verifyUser");
            return (Criteria) this;
        }

        public Criteria andVerifyUserBetween(String value1, String value2) {
            addCriterion("verify_user between", value1, value2, "verifyUser");
            return (Criteria) this;
        }

        public Criteria andVerifyUserNotBetween(String value1, String value2) {
            addCriterion("verify_user not between", value1, value2, "verifyUser");
            return (Criteria) this;
        }

        public Criteria andVerifyTimeIsNull() {
            addCriterion("verify_time is null");
            return (Criteria) this;
        }

        public Criteria andVerifyTimeIsNotNull() {
            addCriterion("verify_time is not null");
            return (Criteria) this;
        }

        public Criteria andVerifyTimeEqualTo(Date value) {
            addCriterion("verify_time =", value, "verifyTime");
            return (Criteria) this;
        }

        public Criteria andVerifyTimeNotEqualTo(Date value) {
            addCriterion("verify_time <>", value, "verifyTime");
            return (Criteria) this;
        }

        public Criteria andVerifyTimeGreaterThan(Date value) {
            addCriterion("verify_time >", value, "verifyTime");
            return (Criteria) this;
        }

        public Criteria andVerifyTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("verify_time >=", value, "verifyTime");
            return (Criteria) this;
        }

        public Criteria andVerifyTimeLessThan(Date value) {
            addCriterion("verify_time <", value, "verifyTime");
            return (Criteria) this;
        }

        public Criteria andVerifyTimeLessThanOrEqualTo(Date value) {
            addCriterion("verify_time <=", value, "verifyTime");
            return (Criteria) this;
        }

        public Criteria andVerifyTimeIn(List<Date> values) {
            addCriterion("verify_time in", values, "verifyTime");
            return (Criteria) this;
        }

        public Criteria andVerifyTimeNotIn(List<Date> values) {
            addCriterion("verify_time not in", values, "verifyTime");
            return (Criteria) this;
        }

        public Criteria andVerifyTimeBetween(Date value1, Date value2) {
            addCriterion("verify_time between", value1, value2, "verifyTime");
            return (Criteria) this;
        }

        public Criteria andVerifyTimeNotBetween(Date value1, Date value2) {
            addCriterion("verify_time not between", value1, value2, "verifyTime");
            return (Criteria) this;
        }

        public Criteria andVerifyInfoIsNull() {
            addCriterion("verify_info is null");
            return (Criteria) this;
        }

        public Criteria andVerifyInfoIsNotNull() {
            addCriterion("verify_info is not null");
            return (Criteria) this;
        }

        public Criteria andVerifyInfoEqualTo(String value) {
            addCriterion("verify_info =", value, "verifyInfo");
            return (Criteria) this;
        }

        public Criteria andVerifyInfoNotEqualTo(String value) {
            addCriterion("verify_info <>", value, "verifyInfo");
            return (Criteria) this;
        }

        public Criteria andVerifyInfoGreaterThan(String value) {
            addCriterion("verify_info >", value, "verifyInfo");
            return (Criteria) this;
        }

        public Criteria andVerifyInfoGreaterThanOrEqualTo(String value) {
            addCriterion("verify_info >=", value, "verifyInfo");
            return (Criteria) this;
        }

        public Criteria andVerifyInfoLessThan(String value) {
            addCriterion("verify_info <", value, "verifyInfo");
            return (Criteria) this;
        }

        public Criteria andVerifyInfoLessThanOrEqualTo(String value) {
            addCriterion("verify_info <=", value, "verifyInfo");
            return (Criteria) this;
        }

        public Criteria andVerifyInfoLike(String value) {
            addCriterion("verify_info like", value, "verifyInfo");
            return (Criteria) this;
        }

        public Criteria andVerifyInfoNotLike(String value) {
            addCriterion("verify_info not like", value, "verifyInfo");
            return (Criteria) this;
        }

        public Criteria andVerifyInfoIn(List<String> values) {
            addCriterion("verify_info in", values, "verifyInfo");
            return (Criteria) this;
        }

        public Criteria andVerifyInfoNotIn(List<String> values) {
            addCriterion("verify_info not in", values, "verifyInfo");
            return (Criteria) this;
        }

        public Criteria andVerifyInfoBetween(String value1, String value2) {
            addCriterion("verify_info between", value1, value2, "verifyInfo");
            return (Criteria) this;
        }

        public Criteria andVerifyInfoNotBetween(String value1, String value2) {
            addCriterion("verify_info not between", value1, value2, "verifyInfo");
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