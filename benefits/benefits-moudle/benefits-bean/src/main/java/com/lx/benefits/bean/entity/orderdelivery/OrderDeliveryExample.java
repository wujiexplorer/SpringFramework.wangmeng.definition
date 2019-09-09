package com.lx.benefits.bean.entity.orderdelivery;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderDeliveryExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public OrderDeliveryExample() {
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

        public Criteria andCompany_idIsNull() {
            addCriterion("company_id is null");
            return (Criteria) this;
        }

        public Criteria andCompany_idIsNotNull() {
            addCriterion("company_id is not null");
            return (Criteria) this;
        }

        public Criteria andCompany_idEqualTo(String value) {
            addCriterion("company_id =", value, "company_id");
            return (Criteria) this;
        }

        public Criteria andCompany_idNotEqualTo(String value) {
            addCriterion("company_id <>", value, "company_id");
            return (Criteria) this;
        }

        public Criteria andCompany_idGreaterThan(String value) {
            addCriterion("company_id >", value, "company_id");
            return (Criteria) this;
        }

        public Criteria andCompany_idGreaterThanOrEqualTo(String value) {
            addCriterion("company_id >=", value, "company_id");
            return (Criteria) this;
        }

        public Criteria andCompany_idLessThan(String value) {
            addCriterion("company_id <", value, "company_id");
            return (Criteria) this;
        }

        public Criteria andCompany_idLessThanOrEqualTo(String value) {
            addCriterion("company_id <=", value, "company_id");
            return (Criteria) this;
        }

        public Criteria andCompany_idLike(String value) {
            addCriterion("company_id like", value, "company_id");
            return (Criteria) this;
        }

        public Criteria andCompany_idNotLike(String value) {
            addCriterion("company_id not like", value, "company_id");
            return (Criteria) this;
        }

        public Criteria andCompany_idIn(List<String> values) {
            addCriterion("company_id in", values, "company_id");
            return (Criteria) this;
        }

        public Criteria andCompany_idNotIn(List<String> values) {
            addCriterion("company_id not in", values, "company_id");
            return (Criteria) this;
        }

        public Criteria andCompany_idBetween(String value1, String value2) {
            addCriterion("company_id between", value1, value2, "company_id");
            return (Criteria) this;
        }

        public Criteria andCompany_idNotBetween(String value1, String value2) {
            addCriterion("company_id not between", value1, value2, "company_id");
            return (Criteria) this;
        }

        public Criteria andCompany_nameIsNull() {
            addCriterion("company_name is null");
            return (Criteria) this;
        }

        public Criteria andCompany_nameIsNotNull() {
            addCriterion("company_name is not null");
            return (Criteria) this;
        }

        public Criteria andCompany_nameEqualTo(String value) {
            addCriterion("company_name =", value, "company_name");
            return (Criteria) this;
        }

        public Criteria andCompany_nameNotEqualTo(String value) {
            addCriterion("company_name <>", value, "company_name");
            return (Criteria) this;
        }

        public Criteria andCompany_nameGreaterThan(String value) {
            addCriterion("company_name >", value, "company_name");
            return (Criteria) this;
        }

        public Criteria andCompany_nameGreaterThanOrEqualTo(String value) {
            addCriterion("company_name >=", value, "company_name");
            return (Criteria) this;
        }

        public Criteria andCompany_nameLessThan(String value) {
            addCriterion("company_name <", value, "company_name");
            return (Criteria) this;
        }

        public Criteria andCompany_nameLessThanOrEqualTo(String value) {
            addCriterion("company_name <=", value, "company_name");
            return (Criteria) this;
        }

        public Criteria andCompany_nameLike(String value) {
            addCriterion("company_name like", value, "company_name");
            return (Criteria) this;
        }

        public Criteria andCompany_nameNotLike(String value) {
            addCriterion("company_name not like", value, "company_name");
            return (Criteria) this;
        }

        public Criteria andCompany_nameIn(List<String> values) {
            addCriterion("company_name in", values, "company_name");
            return (Criteria) this;
        }

        public Criteria andCompany_nameNotIn(List<String> values) {
            addCriterion("company_name not in", values, "company_name");
            return (Criteria) this;
        }

        public Criteria andCompany_nameBetween(String value1, String value2) {
            addCriterion("company_name between", value1, value2, "company_name");
            return (Criteria) this;
        }

        public Criteria andCompany_nameNotBetween(String value1, String value2) {
            addCriterion("company_name not between", value1, value2, "company_name");
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

        public Criteria andPackage_noIsNull() {
            addCriterion("package_no is null");
            return (Criteria) this;
        }

        public Criteria andPackage_noIsNotNull() {
            addCriterion("package_no is not null");
            return (Criteria) this;
        }

        public Criteria andPackage_noEqualTo(String value) {
            addCriterion("package_no =", value, "package_no");
            return (Criteria) this;
        }

        public Criteria andPackage_noNotEqualTo(String value) {
            addCriterion("package_no <>", value, "package_no");
            return (Criteria) this;
        }

        public Criteria andPackage_noGreaterThan(String value) {
            addCriterion("package_no >", value, "package_no");
            return (Criteria) this;
        }

        public Criteria andPackage_noGreaterThanOrEqualTo(String value) {
            addCriterion("package_no >=", value, "package_no");
            return (Criteria) this;
        }

        public Criteria andPackage_noLessThan(String value) {
            addCriterion("package_no <", value, "package_no");
            return (Criteria) this;
        }

        public Criteria andPackage_noLessThanOrEqualTo(String value) {
            addCriterion("package_no <=", value, "package_no");
            return (Criteria) this;
        }

        public Criteria andPackage_noLike(String value) {
            addCriterion("package_no like", value, "package_no");
            return (Criteria) this;
        }

        public Criteria andPackage_noNotLike(String value) {
            addCriterion("package_no not like", value, "package_no");
            return (Criteria) this;
        }

        public Criteria andPackage_noIn(List<String> values) {
            addCriterion("package_no in", values, "package_no");
            return (Criteria) this;
        }

        public Criteria andPackage_noNotIn(List<String> values) {
            addCriterion("package_no not in", values, "package_no");
            return (Criteria) this;
        }

        public Criteria andPackage_noBetween(String value1, String value2) {
            addCriterion("package_no between", value1, value2, "package_no");
            return (Criteria) this;
        }

        public Criteria andPackage_noNotBetween(String value1, String value2) {
            addCriterion("package_no not between", value1, value2, "package_no");
            return (Criteria) this;
        }

        public Criteria andDelivery_timeIsNull() {
            addCriterion("delivery_time is null");
            return (Criteria) this;
        }

        public Criteria andDelivery_timeIsNotNull() {
            addCriterion("delivery_time is not null");
            return (Criteria) this;
        }

        public Criteria andDelivery_timeEqualTo(Date value) {
            addCriterion("delivery_time =", value, "delivery_time");
            return (Criteria) this;
        }

        public Criteria andDelivery_timeNotEqualTo(Date value) {
            addCriterion("delivery_time <>", value, "delivery_time");
            return (Criteria) this;
        }

        public Criteria andDelivery_timeGreaterThan(Date value) {
            addCriterion("delivery_time >", value, "delivery_time");
            return (Criteria) this;
        }

        public Criteria andDelivery_timeGreaterThanOrEqualTo(Date value) {
            addCriterion("delivery_time >=", value, "delivery_time");
            return (Criteria) this;
        }

        public Criteria andDelivery_timeLessThan(Date value) {
            addCriterion("delivery_time <", value, "delivery_time");
            return (Criteria) this;
        }

        public Criteria andDelivery_timeLessThanOrEqualTo(Date value) {
            addCriterion("delivery_time <=", value, "delivery_time");
            return (Criteria) this;
        }

        public Criteria andDelivery_timeIn(List<Date> values) {
            addCriterion("delivery_time in", values, "delivery_time");
            return (Criteria) this;
        }

        public Criteria andDelivery_timeNotIn(List<Date> values) {
            addCriterion("delivery_time not in", values, "delivery_time");
            return (Criteria) this;
        }

        public Criteria andDelivery_timeBetween(Date value1, Date value2) {
            addCriterion("delivery_time between", value1, value2, "delivery_time");
            return (Criteria) this;
        }

        public Criteria andDelivery_timeNotBetween(Date value1, Date value2) {
            addCriterion("delivery_time not between", value1, value2, "delivery_time");
            return (Criteria) this;
        }

        public Criteria andLink_infoIsNull() {
            addCriterion("link_info is null");
            return (Criteria) this;
        }

        public Criteria andLink_infoIsNotNull() {
            addCriterion("link_info is not null");
            return (Criteria) this;
        }

        public Criteria andLink_infoEqualTo(String value) {
            addCriterion("link_info =", value, "link_info");
            return (Criteria) this;
        }

        public Criteria andLink_infoNotEqualTo(String value) {
            addCriterion("link_info <>", value, "link_info");
            return (Criteria) this;
        }

        public Criteria andLink_infoGreaterThan(String value) {
            addCriterion("link_info >", value, "link_info");
            return (Criteria) this;
        }

        public Criteria andLink_infoGreaterThanOrEqualTo(String value) {
            addCriterion("link_info >=", value, "link_info");
            return (Criteria) this;
        }

        public Criteria andLink_infoLessThan(String value) {
            addCriterion("link_info <", value, "link_info");
            return (Criteria) this;
        }

        public Criteria andLink_infoLessThanOrEqualTo(String value) {
            addCriterion("link_info <=", value, "link_info");
            return (Criteria) this;
        }

        public Criteria andLink_infoLike(String value) {
            addCriterion("link_info like", value, "link_info");
            return (Criteria) this;
        }

        public Criteria andLink_infoNotLike(String value) {
            addCriterion("link_info not like", value, "link_info");
            return (Criteria) this;
        }

        public Criteria andLink_infoIn(List<String> values) {
            addCriterion("link_info in", values, "link_info");
            return (Criteria) this;
        }

        public Criteria andLink_infoNotIn(List<String> values) {
            addCriterion("link_info not in", values, "link_info");
            return (Criteria) this;
        }

        public Criteria andLink_infoBetween(String value1, String value2) {
            addCriterion("link_info between", value1, value2, "link_info");
            return (Criteria) this;
        }

        public Criteria andLink_infoNotBetween(String value1, String value2) {
            addCriterion("link_info not between", value1, value2, "link_info");
            return (Criteria) this;
        }

        public Criteria andStart_cityIsNull() {
            addCriterion("start_city is null");
            return (Criteria) this;
        }

        public Criteria andStart_cityIsNotNull() {
            addCriterion("start_city is not null");
            return (Criteria) this;
        }

        public Criteria andStart_cityEqualTo(String value) {
            addCriterion("start_city =", value, "start_city");
            return (Criteria) this;
        }

        public Criteria andStart_cityNotEqualTo(String value) {
            addCriterion("start_city <>", value, "start_city");
            return (Criteria) this;
        }

        public Criteria andStart_cityGreaterThan(String value) {
            addCriterion("start_city >", value, "start_city");
            return (Criteria) this;
        }

        public Criteria andStart_cityGreaterThanOrEqualTo(String value) {
            addCriterion("start_city >=", value, "start_city");
            return (Criteria) this;
        }

        public Criteria andStart_cityLessThan(String value) {
            addCriterion("start_city <", value, "start_city");
            return (Criteria) this;
        }

        public Criteria andStart_cityLessThanOrEqualTo(String value) {
            addCriterion("start_city <=", value, "start_city");
            return (Criteria) this;
        }

        public Criteria andStart_cityLike(String value) {
            addCriterion("start_city like", value, "start_city");
            return (Criteria) this;
        }

        public Criteria andStart_cityNotLike(String value) {
            addCriterion("start_city not like", value, "start_city");
            return (Criteria) this;
        }

        public Criteria andStart_cityIn(List<String> values) {
            addCriterion("start_city in", values, "start_city");
            return (Criteria) this;
        }

        public Criteria andStart_cityNotIn(List<String> values) {
            addCriterion("start_city not in", values, "start_city");
            return (Criteria) this;
        }

        public Criteria andStart_cityBetween(String value1, String value2) {
            addCriterion("start_city between", value1, value2, "start_city");
            return (Criteria) this;
        }

        public Criteria andStart_cityNotBetween(String value1, String value2) {
            addCriterion("start_city not between", value1, value2, "start_city");
            return (Criteria) this;
        }

        public Criteria andEnd_cityIsNull() {
            addCriterion("end_city is null");
            return (Criteria) this;
        }

        public Criteria andEnd_cityIsNotNull() {
            addCriterion("end_city is not null");
            return (Criteria) this;
        }

        public Criteria andEnd_cityEqualTo(String value) {
            addCriterion("end_city =", value, "end_city");
            return (Criteria) this;
        }

        public Criteria andEnd_cityNotEqualTo(String value) {
            addCriterion("end_city <>", value, "end_city");
            return (Criteria) this;
        }

        public Criteria andEnd_cityGreaterThan(String value) {
            addCriterion("end_city >", value, "end_city");
            return (Criteria) this;
        }

        public Criteria andEnd_cityGreaterThanOrEqualTo(String value) {
            addCriterion("end_city >=", value, "end_city");
            return (Criteria) this;
        }

        public Criteria andEnd_cityLessThan(String value) {
            addCriterion("end_city <", value, "end_city");
            return (Criteria) this;
        }

        public Criteria andEnd_cityLessThanOrEqualTo(String value) {
            addCriterion("end_city <=", value, "end_city");
            return (Criteria) this;
        }

        public Criteria andEnd_cityLike(String value) {
            addCriterion("end_city like", value, "end_city");
            return (Criteria) this;
        }

        public Criteria andEnd_cityNotLike(String value) {
            addCriterion("end_city not like", value, "end_city");
            return (Criteria) this;
        }

        public Criteria andEnd_cityIn(List<String> values) {
            addCriterion("end_city in", values, "end_city");
            return (Criteria) this;
        }

        public Criteria andEnd_cityNotIn(List<String> values) {
            addCriterion("end_city not in", values, "end_city");
            return (Criteria) this;
        }

        public Criteria andEnd_cityBetween(String value1, String value2) {
            addCriterion("end_city between", value1, value2, "end_city");
            return (Criteria) this;
        }

        public Criteria andEnd_cityNotBetween(String value1, String value2) {
            addCriterion("end_city not between", value1, value2, "end_city");
            return (Criteria) this;
        }

        public Criteria andRefund_infoIsNull() {
            addCriterion("refund_info is null");
            return (Criteria) this;
        }

        public Criteria andRefund_infoIsNotNull() {
            addCriterion("refund_info is not null");
            return (Criteria) this;
        }

        public Criteria andRefund_infoEqualTo(String value) {
            addCriterion("refund_info =", value, "refund_info");
            return (Criteria) this;
        }

        public Criteria andRefund_infoNotEqualTo(String value) {
            addCriterion("refund_info <>", value, "refund_info");
            return (Criteria) this;
        }

        public Criteria andRefund_infoGreaterThan(String value) {
            addCriterion("refund_info >", value, "refund_info");
            return (Criteria) this;
        }

        public Criteria andRefund_infoGreaterThanOrEqualTo(String value) {
            addCriterion("refund_info >=", value, "refund_info");
            return (Criteria) this;
        }

        public Criteria andRefund_infoLessThan(String value) {
            addCriterion("refund_info <", value, "refund_info");
            return (Criteria) this;
        }

        public Criteria andRefund_infoLessThanOrEqualTo(String value) {
            addCriterion("refund_info <=", value, "refund_info");
            return (Criteria) this;
        }

        public Criteria andRefund_infoLike(String value) {
            addCriterion("refund_info like", value, "refund_info");
            return (Criteria) this;
        }

        public Criteria andRefund_infoNotLike(String value) {
            addCriterion("refund_info not like", value, "refund_info");
            return (Criteria) this;
        }

        public Criteria andRefund_infoIn(List<String> values) {
            addCriterion("refund_info in", values, "refund_info");
            return (Criteria) this;
        }

        public Criteria andRefund_infoNotIn(List<String> values) {
            addCriterion("refund_info not in", values, "refund_info");
            return (Criteria) this;
        }

        public Criteria andRefund_infoBetween(String value1, String value2) {
            addCriterion("refund_info between", value1, value2, "refund_info");
            return (Criteria) this;
        }

        public Criteria andRefund_infoNotBetween(String value1, String value2) {
            addCriterion("refund_info not between", value1, value2, "refund_info");
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

        public Criteria andPost_kuaidi100IsNull() {
            addCriterion("post_kuaidi100 is null");
            return (Criteria) this;
        }

        public Criteria andPost_kuaidi100IsNotNull() {
            addCriterion("post_kuaidi100 is not null");
            return (Criteria) this;
        }

        public Criteria andPost_kuaidi100EqualTo(Boolean value) {
            addCriterion("post_kuaidi100 =", value, "post_kuaidi100");
            return (Criteria) this;
        }

        public Criteria andPost_kuaidi100NotEqualTo(Boolean value) {
            addCriterion("post_kuaidi100 <>", value, "post_kuaidi100");
            return (Criteria) this;
        }

        public Criteria andPost_kuaidi100GreaterThan(Boolean value) {
            addCriterion("post_kuaidi100 >", value, "post_kuaidi100");
            return (Criteria) this;
        }

        public Criteria andPost_kuaidi100GreaterThanOrEqualTo(Boolean value) {
            addCriterion("post_kuaidi100 >=", value, "post_kuaidi100");
            return (Criteria) this;
        }

        public Criteria andPost_kuaidi100LessThan(Boolean value) {
            addCriterion("post_kuaidi100 <", value, "post_kuaidi100");
            return (Criteria) this;
        }

        public Criteria andPost_kuaidi100LessThanOrEqualTo(Boolean value) {
            addCriterion("post_kuaidi100 <=", value, "post_kuaidi100");
            return (Criteria) this;
        }

        public Criteria andPost_kuaidi100In(List<Boolean> values) {
            addCriterion("post_kuaidi100 in", values, "post_kuaidi100");
            return (Criteria) this;
        }

        public Criteria andPost_kuaidi100NotIn(List<Boolean> values) {
            addCriterion("post_kuaidi100 not in", values, "post_kuaidi100");
            return (Criteria) this;
        }

        public Criteria andPost_kuaidi100Between(Boolean value1, Boolean value2) {
            addCriterion("post_kuaidi100 between", value1, value2, "post_kuaidi100");
            return (Criteria) this;
        }

        public Criteria andPost_kuaidi100NotBetween(Boolean value1, Boolean value2) {
            addCriterion("post_kuaidi100 not between", value1, value2, "post_kuaidi100");
            return (Criteria) this;
        }

        public Criteria andPost_kuaidi100_timesIsNull() {
            addCriterion("post_kuaidi100_times is null");
            return (Criteria) this;
        }

        public Criteria andPost_kuaidi100_timesIsNotNull() {
            addCriterion("post_kuaidi100_times is not null");
            return (Criteria) this;
        }

        public Criteria andPost_kuaidi100_timesEqualTo(Boolean value) {
            addCriterion("post_kuaidi100_times =", value, "post_kuaidi100_times");
            return (Criteria) this;
        }

        public Criteria andPost_kuaidi100_timesNotEqualTo(Boolean value) {
            addCriterion("post_kuaidi100_times <>", value, "post_kuaidi100_times");
            return (Criteria) this;
        }

        public Criteria andPost_kuaidi100_timesGreaterThan(Boolean value) {
            addCriterion("post_kuaidi100_times >", value, "post_kuaidi100_times");
            return (Criteria) this;
        }

        public Criteria andPost_kuaidi100_timesGreaterThanOrEqualTo(Boolean value) {
            addCriterion("post_kuaidi100_times >=", value, "post_kuaidi100_times");
            return (Criteria) this;
        }

        public Criteria andPost_kuaidi100_timesLessThan(Boolean value) {
            addCriterion("post_kuaidi100_times <", value, "post_kuaidi100_times");
            return (Criteria) this;
        }

        public Criteria andPost_kuaidi100_timesLessThanOrEqualTo(Boolean value) {
            addCriterion("post_kuaidi100_times <=", value, "post_kuaidi100_times");
            return (Criteria) this;
        }

        public Criteria andPost_kuaidi100_timesIn(List<Boolean> values) {
            addCriterion("post_kuaidi100_times in", values, "post_kuaidi100_times");
            return (Criteria) this;
        }

        public Criteria andPost_kuaidi100_timesNotIn(List<Boolean> values) {
            addCriterion("post_kuaidi100_times not in", values, "post_kuaidi100_times");
            return (Criteria) this;
        }

        public Criteria andPost_kuaidi100_timesBetween(Boolean value1, Boolean value2) {
            addCriterion("post_kuaidi100_times between", value1, value2, "post_kuaidi100_times");
            return (Criteria) this;
        }

        public Criteria andPost_kuaidi100_timesNotBetween(Boolean value1, Boolean value2) {
            addCriterion("post_kuaidi100_times not between", value1, value2, "post_kuaidi100_times");
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