package com.lx.benefits.bean.entity.enterpruserinfo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EnterprCheckingRecorderCondition {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private Integer limit;

    private Integer offset;

    public EnterprCheckingRecorderCondition() {
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

        public Criteria andEnterprIdIsNull() {
            addCriterion("enterpr_id is null");
            return (Criteria) this;
        }

        public Criteria andEnterprIdIsNotNull() {
            addCriterion("enterpr_id is not null");
            return (Criteria) this;
        }

        public Criteria andEnterprIdEqualTo(Long value) {
            addCriterion("enterpr_id =", value, "enterprId");
            return (Criteria) this;
        }

        public Criteria andEnterprIdNotEqualTo(Long value) {
            addCriterion("enterpr_id <>", value, "enterprId");
            return (Criteria) this;
        }

        public Criteria andEnterprIdGreaterThan(Long value) {
            addCriterion("enterpr_id >", value, "enterprId");
            return (Criteria) this;
        }

        public Criteria andEnterprIdGreaterThanOrEqualTo(Long value) {
            addCriterion("enterpr_id >=", value, "enterprId");
            return (Criteria) this;
        }

        public Criteria andEnterprIdLessThan(Long value) {
            addCriterion("enterpr_id <", value, "enterprId");
            return (Criteria) this;
        }

        public Criteria andEnterprIdLessThanOrEqualTo(Long value) {
            addCriterion("enterpr_id <=", value, "enterprId");
            return (Criteria) this;
        }

        public Criteria andEnterprIdIn(List<Long> values) {
            addCriterion("enterpr_id in", values, "enterprId");
            return (Criteria) this;
        }

        public Criteria andEnterprIdNotIn(List<Long> values) {
            addCriterion("enterpr_id not in", values, "enterprId");
            return (Criteria) this;
        }

        public Criteria andEnterprIdBetween(Long value1, Long value2) {
            addCriterion("enterpr_id between", value1, value2, "enterprId");
            return (Criteria) this;
        }

        public Criteria andEnterprIdNotBetween(Long value1, Long value2) {
            addCriterion("enterpr_id not between", value1, value2, "enterprId");
            return (Criteria) this;
        }

        public Criteria andEnterprNameIsNull() {
            addCriterion("enterpr_name is null");
            return (Criteria) this;
        }

        public Criteria andEnterprNameIsNotNull() {
            addCriterion("enterpr_name is not null");
            return (Criteria) this;
        }

        public Criteria andEnterprNameEqualTo(String value) {
            addCriterion("enterpr_name =", value, "enterprName");
            return (Criteria) this;
        }

        public Criteria andEnterprNameNotEqualTo(String value) {
            addCriterion("enterpr_name <>", value, "enterprName");
            return (Criteria) this;
        }

        public Criteria andEnterprNameGreaterThan(String value) {
            addCriterion("enterpr_name >", value, "enterprName");
            return (Criteria) this;
        }

        public Criteria andEnterprNameGreaterThanOrEqualTo(String value) {
            addCriterion("enterpr_name >=", value, "enterprName");
            return (Criteria) this;
        }

        public Criteria andEnterprNameLessThan(String value) {
            addCriterion("enterpr_name <", value, "enterprName");
            return (Criteria) this;
        }

        public Criteria andEnterprNameLessThanOrEqualTo(String value) {
            addCriterion("enterpr_name <=", value, "enterprName");
            return (Criteria) this;
        }

        public Criteria andEnterprNameLike(String value) {
            addCriterion("enterpr_name like", value, "enterprName");
            return (Criteria) this;
        }

        public Criteria andEnterprNameNotLike(String value) {
            addCriterion("enterpr_name not like", value, "enterprName");
            return (Criteria) this;
        }

        public Criteria andEnterprNameIn(List<String> values) {
            addCriterion("enterpr_name in", values, "enterprName");
            return (Criteria) this;
        }

        public Criteria andEnterprNameNotIn(List<String> values) {
            addCriterion("enterpr_name not in", values, "enterprName");
            return (Criteria) this;
        }

        public Criteria andEnterprNameBetween(String value1, String value2) {
            addCriterion("enterpr_name between", value1, value2, "enterprName");
            return (Criteria) this;
        }

        public Criteria andEnterprNameNotBetween(String value1, String value2) {
            addCriterion("enterpr_name not between", value1, value2, "enterprName");
            return (Criteria) this;
        }

        public Criteria andSubEnterprNameIsNull() {
            addCriterion("sub_enterpr_name is null");
            return (Criteria) this;
        }

        public Criteria andSubEnterprNameIsNotNull() {
            addCriterion("sub_enterpr_name is not null");
            return (Criteria) this;
        }

        public Criteria andSubEnterprNameEqualTo(String value) {
            addCriterion("sub_enterpr_name =", value, "subEnterprName");
            return (Criteria) this;
        }

        public Criteria andSubEnterprNameNotEqualTo(String value) {
            addCriterion("sub_enterpr_name <>", value, "subEnterprName");
            return (Criteria) this;
        }

        public Criteria andSubEnterprNameGreaterThan(String value) {
            addCriterion("sub_enterpr_name >", value, "subEnterprName");
            return (Criteria) this;
        }

        public Criteria andSubEnterprNameGreaterThanOrEqualTo(String value) {
            addCriterion("sub_enterpr_name >=", value, "subEnterprName");
            return (Criteria) this;
        }

        public Criteria andSubEnterprNameLessThan(String value) {
            addCriterion("sub_enterpr_name <", value, "subEnterprName");
            return (Criteria) this;
        }

        public Criteria andSubEnterprNameLessThanOrEqualTo(String value) {
            addCriterion("sub_enterpr_name <=", value, "subEnterprName");
            return (Criteria) this;
        }

        public Criteria andSubEnterprNameLike(String value) {
            addCriterion("sub_enterpr_name like", value, "subEnterprName");
            return (Criteria) this;
        }

        public Criteria andSubEnterprNameNotLike(String value) {
            addCriterion("sub_enterpr_name not like", value, "subEnterprName");
            return (Criteria) this;
        }

        public Criteria andSubEnterprNameIn(List<String> values) {
            addCriterion("sub_enterpr_name in", values, "subEnterprName");
            return (Criteria) this;
        }

        public Criteria andSubEnterprNameNotIn(List<String> values) {
            addCriterion("sub_enterpr_name not in", values, "subEnterprName");
            return (Criteria) this;
        }

        public Criteria andSubEnterprNameBetween(String value1, String value2) {
            addCriterion("sub_enterpr_name between", value1, value2, "subEnterprName");
            return (Criteria) this;
        }

        public Criteria andSubEnterprNameNotBetween(String value1, String value2) {
            addCriterion("sub_enterpr_name not between", value1, value2, "subEnterprName");
            return (Criteria) this;
        }

        public Criteria andEnterprIdentityIdIsNull() {
            addCriterion("enterpr_identity_id is null");
            return (Criteria) this;
        }

        public Criteria andEnterprIdentityIdIsNotNull() {
            addCriterion("enterpr_identity_id is not null");
            return (Criteria) this;
        }

        public Criteria andEnterprIdentityIdEqualTo(String value) {
            addCriterion("enterpr_identity_id =", value, "enterprIdentityId");
            return (Criteria) this;
        }

        public Criteria andEnterprIdentityIdNotEqualTo(String value) {
            addCriterion("enterpr_identity_id <>", value, "enterprIdentityId");
            return (Criteria) this;
        }

        public Criteria andEnterprIdentityIdGreaterThan(String value) {
            addCriterion("enterpr_identity_id >", value, "enterprIdentityId");
            return (Criteria) this;
        }

        public Criteria andEnterprIdentityIdGreaterThanOrEqualTo(String value) {
            addCriterion("enterpr_identity_id >=", value, "enterprIdentityId");
            return (Criteria) this;
        }

        public Criteria andEnterprIdentityIdLessThan(String value) {
            addCriterion("enterpr_identity_id <", value, "enterprIdentityId");
            return (Criteria) this;
        }

        public Criteria andEnterprIdentityIdLessThanOrEqualTo(String value) {
            addCriterion("enterpr_identity_id <=", value, "enterprIdentityId");
            return (Criteria) this;
        }

        public Criteria andEnterprIdentityIdLike(String value) {
            addCriterion("enterpr_identity_id like", value, "enterprIdentityId");
            return (Criteria) this;
        }

        public Criteria andEnterprIdentityIdNotLike(String value) {
            addCriterion("enterpr_identity_id not like", value, "enterprIdentityId");
            return (Criteria) this;
        }

        public Criteria andEnterprIdentityIdIn(List<String> values) {
            addCriterion("enterpr_identity_id in", values, "enterprIdentityId");
            return (Criteria) this;
        }

        public Criteria andEnterprIdentityIdNotIn(List<String> values) {
            addCriterion("enterpr_identity_id not in", values, "enterprIdentityId");
            return (Criteria) this;
        }

        public Criteria andEnterprIdentityIdBetween(String value1, String value2) {
            addCriterion("enterpr_identity_id between", value1, value2, "enterprIdentityId");
            return (Criteria) this;
        }

        public Criteria andEnterprIdentityIdNotBetween(String value1, String value2) {
            addCriterion("enterpr_identity_id not between", value1, value2, "enterprIdentityId");
            return (Criteria) this;
        }

        public Criteria andEnterprRegisterAddressIsNull() {
            addCriterion("enterpr_register_address is null");
            return (Criteria) this;
        }

        public Criteria andEnterprRegisterAddressIsNotNull() {
            addCriterion("enterpr_register_address is not null");
            return (Criteria) this;
        }

        public Criteria andEnterprRegisterAddressEqualTo(String value) {
            addCriterion("enterpr_register_address =", value, "enterprRegisterAddress");
            return (Criteria) this;
        }

        public Criteria andEnterprRegisterAddressNotEqualTo(String value) {
            addCriterion("enterpr_register_address <>", value, "enterprRegisterAddress");
            return (Criteria) this;
        }

        public Criteria andEnterprRegisterAddressGreaterThan(String value) {
            addCriterion("enterpr_register_address >", value, "enterprRegisterAddress");
            return (Criteria) this;
        }

        public Criteria andEnterprRegisterAddressGreaterThanOrEqualTo(String value) {
            addCriterion("enterpr_register_address >=", value, "enterprRegisterAddress");
            return (Criteria) this;
        }

        public Criteria andEnterprRegisterAddressLessThan(String value) {
            addCriterion("enterpr_register_address <", value, "enterprRegisterAddress");
            return (Criteria) this;
        }

        public Criteria andEnterprRegisterAddressLessThanOrEqualTo(String value) {
            addCriterion("enterpr_register_address <=", value, "enterprRegisterAddress");
            return (Criteria) this;
        }

        public Criteria andEnterprRegisterAddressLike(String value) {
            addCriterion("enterpr_register_address like", value, "enterprRegisterAddress");
            return (Criteria) this;
        }

        public Criteria andEnterprRegisterAddressNotLike(String value) {
            addCriterion("enterpr_register_address not like", value, "enterprRegisterAddress");
            return (Criteria) this;
        }

        public Criteria andEnterprRegisterAddressIn(List<String> values) {
            addCriterion("enterpr_register_address in", values, "enterprRegisterAddress");
            return (Criteria) this;
        }

        public Criteria andEnterprRegisterAddressNotIn(List<String> values) {
            addCriterion("enterpr_register_address not in", values, "enterprRegisterAddress");
            return (Criteria) this;
        }

        public Criteria andEnterprRegisterAddressBetween(String value1, String value2) {
            addCriterion("enterpr_register_address between", value1, value2, "enterprRegisterAddress");
            return (Criteria) this;
        }

        public Criteria andEnterprRegisterAddressNotBetween(String value1, String value2) {
            addCriterion("enterpr_register_address not between", value1, value2, "enterprRegisterAddress");
            return (Criteria) this;
        }

        public Criteria andCertifyImageUrlIsNull() {
            addCriterion("certify_image_url is null");
            return (Criteria) this;
        }

        public Criteria andCertifyImageUrlIsNotNull() {
            addCriterion("certify_image_url is not null");
            return (Criteria) this;
        }

        public Criteria andCertifyImageUrlEqualTo(String value) {
            addCriterion("certify_image_url =", value, "certifyImageUrl");
            return (Criteria) this;
        }

        public Criteria andCertifyImageUrlNotEqualTo(String value) {
            addCriterion("certify_image_url <>", value, "certifyImageUrl");
            return (Criteria) this;
        }

        public Criteria andCertifyImageUrlGreaterThan(String value) {
            addCriterion("certify_image_url >", value, "certifyImageUrl");
            return (Criteria) this;
        }

        public Criteria andCertifyImageUrlGreaterThanOrEqualTo(String value) {
            addCriterion("certify_image_url >=", value, "certifyImageUrl");
            return (Criteria) this;
        }

        public Criteria andCertifyImageUrlLessThan(String value) {
            addCriterion("certify_image_url <", value, "certifyImageUrl");
            return (Criteria) this;
        }

        public Criteria andCertifyImageUrlLessThanOrEqualTo(String value) {
            addCriterion("certify_image_url <=", value, "certifyImageUrl");
            return (Criteria) this;
        }

        public Criteria andCertifyImageUrlLike(String value) {
            addCriterion("certify_image_url like", value, "certifyImageUrl");
            return (Criteria) this;
        }

        public Criteria andCertifyImageUrlNotLike(String value) {
            addCriterion("certify_image_url not like", value, "certifyImageUrl");
            return (Criteria) this;
        }

        public Criteria andCertifyImageUrlIn(List<String> values) {
            addCriterion("certify_image_url in", values, "certifyImageUrl");
            return (Criteria) this;
        }

        public Criteria andCertifyImageUrlNotIn(List<String> values) {
            addCriterion("certify_image_url not in", values, "certifyImageUrl");
            return (Criteria) this;
        }

        public Criteria andCertifyImageUrlBetween(String value1, String value2) {
            addCriterion("certify_image_url between", value1, value2, "certifyImageUrl");
            return (Criteria) this;
        }

        public Criteria andCertifyImageUrlNotBetween(String value1, String value2) {
            addCriterion("certify_image_url not between", value1, value2, "certifyImageUrl");
            return (Criteria) this;
        }

        public Criteria andCountryIsNull() {
            addCriterion("country is null");
            return (Criteria) this;
        }

        public Criteria andCountryIsNotNull() {
            addCriterion("country is not null");
            return (Criteria) this;
        }

        public Criteria andCountryEqualTo(String value) {
            addCriterion("country =", value, "country");
            return (Criteria) this;
        }

        public Criteria andCountryNotEqualTo(String value) {
            addCriterion("country <>", value, "country");
            return (Criteria) this;
        }

        public Criteria andCountryGreaterThan(String value) {
            addCriterion("country >", value, "country");
            return (Criteria) this;
        }

        public Criteria andCountryGreaterThanOrEqualTo(String value) {
            addCriterion("country >=", value, "country");
            return (Criteria) this;
        }

        public Criteria andCountryLessThan(String value) {
            addCriterion("country <", value, "country");
            return (Criteria) this;
        }

        public Criteria andCountryLessThanOrEqualTo(String value) {
            addCriterion("country <=", value, "country");
            return (Criteria) this;
        }

        public Criteria andCountryLike(String value) {
            addCriterion("country like", value, "country");
            return (Criteria) this;
        }

        public Criteria andCountryNotLike(String value) {
            addCriterion("country not like", value, "country");
            return (Criteria) this;
        }

        public Criteria andCountryIn(List<String> values) {
            addCriterion("country in", values, "country");
            return (Criteria) this;
        }

        public Criteria andCountryNotIn(List<String> values) {
            addCriterion("country not in", values, "country");
            return (Criteria) this;
        }

        public Criteria andCountryBetween(String value1, String value2) {
            addCriterion("country between", value1, value2, "country");
            return (Criteria) this;
        }

        public Criteria andCountryNotBetween(String value1, String value2) {
            addCriterion("country not between", value1, value2, "country");
            return (Criteria) this;
        }

        public Criteria andProvinceIsNull() {
            addCriterion("province is null");
            return (Criteria) this;
        }

        public Criteria andProvinceIsNotNull() {
            addCriterion("province is not null");
            return (Criteria) this;
        }

        public Criteria andProvinceEqualTo(String value) {
            addCriterion("province =", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceNotEqualTo(String value) {
            addCriterion("province <>", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceGreaterThan(String value) {
            addCriterion("province >", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceGreaterThanOrEqualTo(String value) {
            addCriterion("province >=", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceLessThan(String value) {
            addCriterion("province <", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceLessThanOrEqualTo(String value) {
            addCriterion("province <=", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceLike(String value) {
            addCriterion("province like", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceNotLike(String value) {
            addCriterion("province not like", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceIn(List<String> values) {
            addCriterion("province in", values, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceNotIn(List<String> values) {
            addCriterion("province not in", values, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceBetween(String value1, String value2) {
            addCriterion("province between", value1, value2, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceNotBetween(String value1, String value2) {
            addCriterion("province not between", value1, value2, "province");
            return (Criteria) this;
        }

        public Criteria andCityIsNull() {
            addCriterion("city is null");
            return (Criteria) this;
        }

        public Criteria andCityIsNotNull() {
            addCriterion("city is not null");
            return (Criteria) this;
        }

        public Criteria andCityEqualTo(String value) {
            addCriterion("city =", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityNotEqualTo(String value) {
            addCriterion("city <>", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityGreaterThan(String value) {
            addCriterion("city >", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityGreaterThanOrEqualTo(String value) {
            addCriterion("city >=", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityLessThan(String value) {
            addCriterion("city <", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityLessThanOrEqualTo(String value) {
            addCriterion("city <=", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityLike(String value) {
            addCriterion("city like", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityNotLike(String value) {
            addCriterion("city not like", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityIn(List<String> values) {
            addCriterion("city in", values, "city");
            return (Criteria) this;
        }

        public Criteria andCityNotIn(List<String> values) {
            addCriterion("city not in", values, "city");
            return (Criteria) this;
        }

        public Criteria andCityBetween(String value1, String value2) {
            addCriterion("city between", value1, value2, "city");
            return (Criteria) this;
        }

        public Criteria andCityNotBetween(String value1, String value2) {
            addCriterion("city not between", value1, value2, "city");
            return (Criteria) this;
        }

        public Criteria andCountyIsNull() {
            addCriterion("county is null");
            return (Criteria) this;
        }

        public Criteria andCountyIsNotNull() {
            addCriterion("county is not null");
            return (Criteria) this;
        }

        public Criteria andCountyEqualTo(String value) {
            addCriterion("county =", value, "county");
            return (Criteria) this;
        }

        public Criteria andCountyNotEqualTo(String value) {
            addCriterion("county <>", value, "county");
            return (Criteria) this;
        }

        public Criteria andCountyGreaterThan(String value) {
            addCriterion("county >", value, "county");
            return (Criteria) this;
        }

        public Criteria andCountyGreaterThanOrEqualTo(String value) {
            addCriterion("county >=", value, "county");
            return (Criteria) this;
        }

        public Criteria andCountyLessThan(String value) {
            addCriterion("county <", value, "county");
            return (Criteria) this;
        }

        public Criteria andCountyLessThanOrEqualTo(String value) {
            addCriterion("county <=", value, "county");
            return (Criteria) this;
        }

        public Criteria andCountyLike(String value) {
            addCriterion("county like", value, "county");
            return (Criteria) this;
        }

        public Criteria andCountyNotLike(String value) {
            addCriterion("county not like", value, "county");
            return (Criteria) this;
        }

        public Criteria andCountyIn(List<String> values) {
            addCriterion("county in", values, "county");
            return (Criteria) this;
        }

        public Criteria andCountyNotIn(List<String> values) {
            addCriterion("county not in", values, "county");
            return (Criteria) this;
        }

        public Criteria andCountyBetween(String value1, String value2) {
            addCriterion("county between", value1, value2, "county");
            return (Criteria) this;
        }

        public Criteria andCountyNotBetween(String value1, String value2) {
            addCriterion("county not between", value1, value2, "county");
            return (Criteria) this;
        }

        public Criteria andStreetIsNull() {
            addCriterion("street is null");
            return (Criteria) this;
        }

        public Criteria andStreetIsNotNull() {
            addCriterion("street is not null");
            return (Criteria) this;
        }

        public Criteria andStreetEqualTo(String value) {
            addCriterion("street =", value, "street");
            return (Criteria) this;
        }

        public Criteria andStreetNotEqualTo(String value) {
            addCriterion("street <>", value, "street");
            return (Criteria) this;
        }

        public Criteria andStreetGreaterThan(String value) {
            addCriterion("street >", value, "street");
            return (Criteria) this;
        }

        public Criteria andStreetGreaterThanOrEqualTo(String value) {
            addCriterion("street >=", value, "street");
            return (Criteria) this;
        }

        public Criteria andStreetLessThan(String value) {
            addCriterion("street <", value, "street");
            return (Criteria) this;
        }

        public Criteria andStreetLessThanOrEqualTo(String value) {
            addCriterion("street <=", value, "street");
            return (Criteria) this;
        }

        public Criteria andStreetLike(String value) {
            addCriterion("street like", value, "street");
            return (Criteria) this;
        }

        public Criteria andStreetNotLike(String value) {
            addCriterion("street not like", value, "street");
            return (Criteria) this;
        }

        public Criteria andStreetIn(List<String> values) {
            addCriterion("street in", values, "street");
            return (Criteria) this;
        }

        public Criteria andStreetNotIn(List<String> values) {
            addCriterion("street not in", values, "street");
            return (Criteria) this;
        }

        public Criteria andStreetBetween(String value1, String value2) {
            addCriterion("street between", value1, value2, "street");
            return (Criteria) this;
        }

        public Criteria andStreetNotBetween(String value1, String value2) {
            addCriterion("street not between", value1, value2, "street");
            return (Criteria) this;
        }

        public Criteria andAddressIsNull() {
            addCriterion("address is null");
            return (Criteria) this;
        }

        public Criteria andAddressIsNotNull() {
            addCriterion("address is not null");
            return (Criteria) this;
        }

        public Criteria andAddressEqualTo(String value) {
            addCriterion("address =", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotEqualTo(String value) {
            addCriterion("address <>", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressGreaterThan(String value) {
            addCriterion("address >", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressGreaterThanOrEqualTo(String value) {
            addCriterion("address >=", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLessThan(String value) {
            addCriterion("address <", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLessThanOrEqualTo(String value) {
            addCriterion("address <=", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLike(String value) {
            addCriterion("address like", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotLike(String value) {
            addCriterion("address not like", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressIn(List<String> values) {
            addCriterion("address in", values, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotIn(List<String> values) {
            addCriterion("address not in", values, "address");
            return (Criteria) this;
        }

        public Criteria andAddressBetween(String value1, String value2) {
            addCriterion("address between", value1, value2, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotBetween(String value1, String value2) {
            addCriterion("address not between", value1, value2, "address");
            return (Criteria) this;
        }

        public Criteria andLeaveCreditIsNull() {
            addCriterion("leave_credit is null");
            return (Criteria) this;
        }

        public Criteria andLeaveCreditIsNotNull() {
            addCriterion("leave_credit is not null");
            return (Criteria) this;
        }

        public Criteria andLeaveCreditEqualTo(Integer value) {
            addCriterion("leave_credit =", value, "leaveCredit");
            return (Criteria) this;
        }

        public Criteria andLeaveCreditNotEqualTo(Integer value) {
            addCriterion("leave_credit <>", value, "leaveCredit");
            return (Criteria) this;
        }

        public Criteria andLeaveCreditGreaterThan(Integer value) {
            addCriterion("leave_credit >", value, "leaveCredit");
            return (Criteria) this;
        }

        public Criteria andLeaveCreditGreaterThanOrEqualTo(Integer value) {
            addCriterion("leave_credit >=", value, "leaveCredit");
            return (Criteria) this;
        }

        public Criteria andLeaveCreditLessThan(Integer value) {
            addCriterion("leave_credit <", value, "leaveCredit");
            return (Criteria) this;
        }

        public Criteria andLeaveCreditLessThanOrEqualTo(Integer value) {
            addCriterion("leave_credit <=", value, "leaveCredit");
            return (Criteria) this;
        }

        public Criteria andLeaveCreditIn(List<Integer> values) {
            addCriterion("leave_credit in", values, "leaveCredit");
            return (Criteria) this;
        }

        public Criteria andLeaveCreditNotIn(List<Integer> values) {
            addCriterion("leave_credit not in", values, "leaveCredit");
            return (Criteria) this;
        }

        public Criteria andLeaveCreditBetween(Integer value1, Integer value2) {
            addCriterion("leave_credit between", value1, value2, "leaveCredit");
            return (Criteria) this;
        }

        public Criteria andLeaveCreditNotBetween(Integer value1, Integer value2) {
            addCriterion("leave_credit not between", value1, value2, "leaveCredit");
            return (Criteria) this;
        }

        public Criteria andContactUserIsNull() {
            addCriterion("contact_user is null");
            return (Criteria) this;
        }

        public Criteria andContactUserIsNotNull() {
            addCriterion("contact_user is not null");
            return (Criteria) this;
        }

        public Criteria andContactUserEqualTo(String value) {
            addCriterion("contact_user =", value, "contactUser");
            return (Criteria) this;
        }

        public Criteria andContactUserNotEqualTo(String value) {
            addCriterion("contact_user <>", value, "contactUser");
            return (Criteria) this;
        }

        public Criteria andContactUserGreaterThan(String value) {
            addCriterion("contact_user >", value, "contactUser");
            return (Criteria) this;
        }

        public Criteria andContactUserGreaterThanOrEqualTo(String value) {
            addCriterion("contact_user >=", value, "contactUser");
            return (Criteria) this;
        }

        public Criteria andContactUserLessThan(String value) {
            addCriterion("contact_user <", value, "contactUser");
            return (Criteria) this;
        }

        public Criteria andContactUserLessThanOrEqualTo(String value) {
            addCriterion("contact_user <=", value, "contactUser");
            return (Criteria) this;
        }

        public Criteria andContactUserLike(String value) {
            addCriterion("contact_user like", value, "contactUser");
            return (Criteria) this;
        }

        public Criteria andContactUserNotLike(String value) {
            addCriterion("contact_user not like", value, "contactUser");
            return (Criteria) this;
        }

        public Criteria andContactUserIn(List<String> values) {
            addCriterion("contact_user in", values, "contactUser");
            return (Criteria) this;
        }

        public Criteria andContactUserNotIn(List<String> values) {
            addCriterion("contact_user not in", values, "contactUser");
            return (Criteria) this;
        }

        public Criteria andContactUserBetween(String value1, String value2) {
            addCriterion("contact_user between", value1, value2, "contactUser");
            return (Criteria) this;
        }

        public Criteria andContactUserNotBetween(String value1, String value2) {
            addCriterion("contact_user not between", value1, value2, "contactUser");
            return (Criteria) this;
        }

        public Criteria andMobileIsNull() {
            addCriterion("mobile is null");
            return (Criteria) this;
        }

        public Criteria andMobileIsNotNull() {
            addCriterion("mobile is not null");
            return (Criteria) this;
        }

        public Criteria andMobileEqualTo(String value) {
            addCriterion("mobile =", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileNotEqualTo(String value) {
            addCriterion("mobile <>", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileGreaterThan(String value) {
            addCriterion("mobile >", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileGreaterThanOrEqualTo(String value) {
            addCriterion("mobile >=", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileLessThan(String value) {
            addCriterion("mobile <", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileLessThanOrEqualTo(String value) {
            addCriterion("mobile <=", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileLike(String value) {
            addCriterion("mobile like", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileNotLike(String value) {
            addCriterion("mobile not like", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileIn(List<String> values) {
            addCriterion("mobile in", values, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileNotIn(List<String> values) {
            addCriterion("mobile not in", values, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileBetween(String value1, String value2) {
            addCriterion("mobile between", value1, value2, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileNotBetween(String value1, String value2) {
            addCriterion("mobile not between", value1, value2, "mobile");
            return (Criteria) this;
        }

        public Criteria andEmailIsNull() {
            addCriterion("email is null");
            return (Criteria) this;
        }

        public Criteria andEmailIsNotNull() {
            addCriterion("email is not null");
            return (Criteria) this;
        }

        public Criteria andEmailEqualTo(String value) {
            addCriterion("email =", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotEqualTo(String value) {
            addCriterion("email <>", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailGreaterThan(String value) {
            addCriterion("email >", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailGreaterThanOrEqualTo(String value) {
            addCriterion("email >=", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLessThan(String value) {
            addCriterion("email <", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLessThanOrEqualTo(String value) {
            addCriterion("email <=", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLike(String value) {
            addCriterion("email like", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotLike(String value) {
            addCriterion("email not like", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailIn(List<String> values) {
            addCriterion("email in", values, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotIn(List<String> values) {
            addCriterion("email not in", values, "email");
            return (Criteria) this;
        }

        public Criteria andEmailBetween(String value1, String value2) {
            addCriterion("email between", value1, value2, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotBetween(String value1, String value2) {
            addCriterion("email not between", value1, value2, "email");
            return (Criteria) this;
        }

        public Criteria andAddUserIsNull() {
            addCriterion("add_user is null");
            return (Criteria) this;
        }

        public Criteria andAddUserIsNotNull() {
            addCriterion("add_user is not null");
            return (Criteria) this;
        }

        public Criteria andAddUserEqualTo(String value) {
            addCriterion("add_user =", value, "addUser");
            return (Criteria) this;
        }

        public Criteria andAddUserNotEqualTo(String value) {
            addCriterion("add_user <>", value, "addUser");
            return (Criteria) this;
        }

        public Criteria andAddUserGreaterThan(String value) {
            addCriterion("add_user >", value, "addUser");
            return (Criteria) this;
        }

        public Criteria andAddUserGreaterThanOrEqualTo(String value) {
            addCriterion("add_user >=", value, "addUser");
            return (Criteria) this;
        }

        public Criteria andAddUserLessThan(String value) {
            addCriterion("add_user <", value, "addUser");
            return (Criteria) this;
        }

        public Criteria andAddUserLessThanOrEqualTo(String value) {
            addCriterion("add_user <=", value, "addUser");
            return (Criteria) this;
        }

        public Criteria andAddUserLike(String value) {
            addCriterion("add_user like", value, "addUser");
            return (Criteria) this;
        }

        public Criteria andAddUserNotLike(String value) {
            addCriterion("add_user not like", value, "addUser");
            return (Criteria) this;
        }

        public Criteria andAddUserIn(List<String> values) {
            addCriterion("add_user in", values, "addUser");
            return (Criteria) this;
        }

        public Criteria andAddUserNotIn(List<String> values) {
            addCriterion("add_user not in", values, "addUser");
            return (Criteria) this;
        }

        public Criteria andAddUserBetween(String value1, String value2) {
            addCriterion("add_user between", value1, value2, "addUser");
            return (Criteria) this;
        }

        public Criteria andAddUserNotBetween(String value1, String value2) {
            addCriterion("add_user not between", value1, value2, "addUser");
            return (Criteria) this;
        }

        public Criteria andAddTimeIsNull() {
            addCriterion("add_time is null");
            return (Criteria) this;
        }

        public Criteria andAddTimeIsNotNull() {
            addCriterion("add_time is not null");
            return (Criteria) this;
        }

        public Criteria andAddTimeEqualTo(Date value) {
            addCriterion("add_time =", value, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeNotEqualTo(Date value) {
            addCriterion("add_time <>", value, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeGreaterThan(Date value) {
            addCriterion("add_time >", value, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("add_time >=", value, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeLessThan(Date value) {
            addCriterion("add_time <", value, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeLessThanOrEqualTo(Date value) {
            addCriterion("add_time <=", value, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeIn(List<Date> values) {
            addCriterion("add_time in", values, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeNotIn(List<Date> values) {
            addCriterion("add_time not in", values, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeBetween(Date value1, Date value2) {
            addCriterion("add_time between", value1, value2, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeNotBetween(Date value1, Date value2) {
            addCriterion("add_time not between", value1, value2, "addTime");
            return (Criteria) this;
        }

        public Criteria andAgentIdIsNull() {
            addCriterion("agent_id is null");
            return (Criteria) this;
        }

        public Criteria andAgentIdIsNotNull() {
            addCriterion("agent_id is not null");
            return (Criteria) this;
        }

        public Criteria andAgentIdEqualTo(Integer value) {
            addCriterion("agent_id =", value, "agentId");
            return (Criteria) this;
        }

        public Criteria andAgentIdNotEqualTo(Integer value) {
            addCriterion("agent_id <>", value, "agentId");
            return (Criteria) this;
        }

        public Criteria andAgentIdGreaterThan(Integer value) {
            addCriterion("agent_id >", value, "agentId");
            return (Criteria) this;
        }

        public Criteria andAgentIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("agent_id >=", value, "agentId");
            return (Criteria) this;
        }

        public Criteria andAgentIdLessThan(Integer value) {
            addCriterion("agent_id <", value, "agentId");
            return (Criteria) this;
        }

        public Criteria andAgentIdLessThanOrEqualTo(Integer value) {
            addCriterion("agent_id <=", value, "agentId");
            return (Criteria) this;
        }

        public Criteria andAgentIdIn(List<Integer> values) {
            addCriterion("agent_id in", values, "agentId");
            return (Criteria) this;
        }

        public Criteria andAgentIdNotIn(List<Integer> values) {
            addCriterion("agent_id not in", values, "agentId");
            return (Criteria) this;
        }

        public Criteria andAgentIdBetween(Integer value1, Integer value2) {
            addCriterion("agent_id between", value1, value2, "agentId");
            return (Criteria) this;
        }

        public Criteria andAgentIdNotBetween(Integer value1, Integer value2) {
            addCriterion("agent_id not between", value1, value2, "agentId");
            return (Criteria) this;
        }

        public Criteria andCheckUserIsNull() {
            addCriterion("check_user is null");
            return (Criteria) this;
        }

        public Criteria andCheckUserIsNotNull() {
            addCriterion("check_user is not null");
            return (Criteria) this;
        }

        public Criteria andCheckUserEqualTo(String value) {
            addCriterion("check_user =", value, "checkUser");
            return (Criteria) this;
        }

        public Criteria andCheckUserNotEqualTo(String value) {
            addCriterion("check_user <>", value, "checkUser");
            return (Criteria) this;
        }

        public Criteria andCheckUserGreaterThan(String value) {
            addCriterion("check_user >", value, "checkUser");
            return (Criteria) this;
        }

        public Criteria andCheckUserGreaterThanOrEqualTo(String value) {
            addCriterion("check_user >=", value, "checkUser");
            return (Criteria) this;
        }

        public Criteria andCheckUserLessThan(String value) {
            addCriterion("check_user <", value, "checkUser");
            return (Criteria) this;
        }

        public Criteria andCheckUserLessThanOrEqualTo(String value) {
            addCriterion("check_user <=", value, "checkUser");
            return (Criteria) this;
        }

        public Criteria andCheckUserLike(String value) {
            addCriterion("check_user like", value, "checkUser");
            return (Criteria) this;
        }

        public Criteria andCheckUserNotLike(String value) {
            addCriterion("check_user not like", value, "checkUser");
            return (Criteria) this;
        }

        public Criteria andCheckUserIn(List<String> values) {
            addCriterion("check_user in", values, "checkUser");
            return (Criteria) this;
        }

        public Criteria andCheckUserNotIn(List<String> values) {
            addCriterion("check_user not in", values, "checkUser");
            return (Criteria) this;
        }

        public Criteria andCheckUserBetween(String value1, String value2) {
            addCriterion("check_user between", value1, value2, "checkUser");
            return (Criteria) this;
        }

        public Criteria andCheckUserNotBetween(String value1, String value2) {
            addCriterion("check_user not between", value1, value2, "checkUser");
            return (Criteria) this;
        }

        public Criteria andCheckTimeIsNull() {
            addCriterion("check_time is null");
            return (Criteria) this;
        }

        public Criteria andCheckTimeIsNotNull() {
            addCriterion("check_time is not null");
            return (Criteria) this;
        }

        public Criteria andCheckTimeEqualTo(Date value) {
            addCriterion("check_time =", value, "checkTime");
            return (Criteria) this;
        }

        public Criteria andCheckTimeNotEqualTo(Date value) {
            addCriterion("check_time <>", value, "checkTime");
            return (Criteria) this;
        }

        public Criteria andCheckTimeGreaterThan(Date value) {
            addCriterion("check_time >", value, "checkTime");
            return (Criteria) this;
        }

        public Criteria andCheckTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("check_time >=", value, "checkTime");
            return (Criteria) this;
        }

        public Criteria andCheckTimeLessThan(Date value) {
            addCriterion("check_time <", value, "checkTime");
            return (Criteria) this;
        }

        public Criteria andCheckTimeLessThanOrEqualTo(Date value) {
            addCriterion("check_time <=", value, "checkTime");
            return (Criteria) this;
        }

        public Criteria andCheckTimeIn(List<Date> values) {
            addCriterion("check_time in", values, "checkTime");
            return (Criteria) this;
        }

        public Criteria andCheckTimeNotIn(List<Date> values) {
            addCriterion("check_time not in", values, "checkTime");
            return (Criteria) this;
        }

        public Criteria andCheckTimeBetween(Date value1, Date value2) {
            addCriterion("check_time between", value1, value2, "checkTime");
            return (Criteria) this;
        }

        public Criteria andCheckTimeNotBetween(Date value1, Date value2) {
            addCriterion("check_time not between", value1, value2, "checkTime");
            return (Criteria) this;
        }

        public Criteria andCheckStatusIsNull() {
            addCriterion("check_status is null");
            return (Criteria) this;
        }

        public Criteria andCheckStatusIsNotNull() {
            addCriterion("check_status is not null");
            return (Criteria) this;
        }

        public Criteria andCheckStatusEqualTo(Integer value) {
            addCriterion("check_status =", value, "checkStatus");
            return (Criteria) this;
        }

        public Criteria andCheckStatusNotEqualTo(Integer value) {
            addCriterion("check_status <>", value, "checkStatus");
            return (Criteria) this;
        }

        public Criteria andCheckStatusGreaterThan(Integer value) {
            addCriterion("check_status >", value, "checkStatus");
            return (Criteria) this;
        }

        public Criteria andCheckStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("check_status >=", value, "checkStatus");
            return (Criteria) this;
        }

        public Criteria andCheckStatusLessThan(Integer value) {
            addCriterion("check_status <", value, "checkStatus");
            return (Criteria) this;
        }

        public Criteria andCheckStatusLessThanOrEqualTo(Integer value) {
            addCriterion("check_status <=", value, "checkStatus");
            return (Criteria) this;
        }

        public Criteria andCheckStatusIn(List<Integer> values) {
            addCriterion("check_status in", values, "checkStatus");
            return (Criteria) this;
        }

        public Criteria andCheckStatusNotIn(List<Integer> values) {
            addCriterion("check_status not in", values, "checkStatus");
            return (Criteria) this;
        }

        public Criteria andCheckStatusBetween(Integer value1, Integer value2) {
            addCriterion("check_status between", value1, value2, "checkStatus");
            return (Criteria) this;
        }

        public Criteria andCheckStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("check_status not between", value1, value2, "checkStatus");
            return (Criteria) this;
        }

        public Criteria andContractNumberIsNull() {
            addCriterion("contract_number is null");
            return (Criteria) this;
        }

        public Criteria andContractNumberIsNotNull() {
            addCriterion("contract_number is not null");
            return (Criteria) this;
        }

        public Criteria andContractNumberEqualTo(String value) {
            addCriterion("contract_number =", value, "contractNumber");
            return (Criteria) this;
        }

        public Criteria andContractNumberNotEqualTo(String value) {
            addCriterion("contract_number <>", value, "contractNumber");
            return (Criteria) this;
        }

        public Criteria andContractNumberGreaterThan(String value) {
            addCriterion("contract_number >", value, "contractNumber");
            return (Criteria) this;
        }

        public Criteria andContractNumberGreaterThanOrEqualTo(String value) {
            addCriterion("contract_number >=", value, "contractNumber");
            return (Criteria) this;
        }

        public Criteria andContractNumberLessThan(String value) {
            addCriterion("contract_number <", value, "contractNumber");
            return (Criteria) this;
        }

        public Criteria andContractNumberLessThanOrEqualTo(String value) {
            addCriterion("contract_number <=", value, "contractNumber");
            return (Criteria) this;
        }

        public Criteria andContractNumberLike(String value) {
            addCriterion("contract_number like", value, "contractNumber");
            return (Criteria) this;
        }

        public Criteria andContractNumberNotLike(String value) {
            addCriterion("contract_number not like", value, "contractNumber");
            return (Criteria) this;
        }

        public Criteria andContractNumberIn(List<String> values) {
            addCriterion("contract_number in", values, "contractNumber");
            return (Criteria) this;
        }

        public Criteria andContractNumberNotIn(List<String> values) {
            addCriterion("contract_number not in", values, "contractNumber");
            return (Criteria) this;
        }

        public Criteria andContractNumberBetween(String value1, String value2) {
            addCriterion("contract_number between", value1, value2, "contractNumber");
            return (Criteria) this;
        }

        public Criteria andContractNumberNotBetween(String value1, String value2) {
            addCriterion("contract_number not between", value1, value2, "contractNumber");
            return (Criteria) this;
        }

        public Criteria andLoginNameIsNull() {
            addCriterion("login_name is null");
            return (Criteria) this;
        }

        public Criteria andLoginNameIsNotNull() {
            addCriterion("login_name is not null");
            return (Criteria) this;
        }

        public Criteria andLoginNameEqualTo(String value) {
            addCriterion("login_name =", value, "loginName");
            return (Criteria) this;
        }

        public Criteria andLoginNameNotEqualTo(String value) {
            addCriterion("login_name <>", value, "loginName");
            return (Criteria) this;
        }

        public Criteria andLoginNameGreaterThan(String value) {
            addCriterion("login_name >", value, "loginName");
            return (Criteria) this;
        }

        public Criteria andLoginNameGreaterThanOrEqualTo(String value) {
            addCriterion("login_name >=", value, "loginName");
            return (Criteria) this;
        }

        public Criteria andLoginNameLessThan(String value) {
            addCriterion("login_name <", value, "loginName");
            return (Criteria) this;
        }

        public Criteria andLoginNameLessThanOrEqualTo(String value) {
            addCriterion("login_name <=", value, "loginName");
            return (Criteria) this;
        }

        public Criteria andLoginNameLike(String value) {
            addCriterion("login_name like", value, "loginName");
            return (Criteria) this;
        }

        public Criteria andLoginNameNotLike(String value) {
            addCriterion("login_name not like", value, "loginName");
            return (Criteria) this;
        }

        public Criteria andLoginNameIn(List<String> values) {
            addCriterion("login_name in", values, "loginName");
            return (Criteria) this;
        }

        public Criteria andLoginNameNotIn(List<String> values) {
            addCriterion("login_name not in", values, "loginName");
            return (Criteria) this;
        }

        public Criteria andLoginNameBetween(String value1, String value2) {
            addCriterion("login_name between", value1, value2, "loginName");
            return (Criteria) this;
        }

        public Criteria andLoginNameNotBetween(String value1, String value2) {
            addCriterion("login_name not between", value1, value2, "loginName");
            return (Criteria) this;
        }

        public Criteria andCheckRemarkIsNull() {
            addCriterion("check_remark is null");
            return (Criteria) this;
        }

        public Criteria andCheckRemarkIsNotNull() {
            addCriterion("check_remark is not null");
            return (Criteria) this;
        }

        public Criteria andCheckRemarkEqualTo(String value) {
            addCriterion("check_remark =", value, "checkRemark");
            return (Criteria) this;
        }

        public Criteria andCheckRemarkNotEqualTo(String value) {
            addCriterion("check_remark <>", value, "checkRemark");
            return (Criteria) this;
        }

        public Criteria andCheckRemarkGreaterThan(String value) {
            addCriterion("check_remark >", value, "checkRemark");
            return (Criteria) this;
        }

        public Criteria andCheckRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("check_remark >=", value, "checkRemark");
            return (Criteria) this;
        }

        public Criteria andCheckRemarkLessThan(String value) {
            addCriterion("check_remark <", value, "checkRemark");
            return (Criteria) this;
        }

        public Criteria andCheckRemarkLessThanOrEqualTo(String value) {
            addCriterion("check_remark <=", value, "checkRemark");
            return (Criteria) this;
        }

        public Criteria andCheckRemarkLike(String value) {
            addCriterion("check_remark like", value, "checkRemark");
            return (Criteria) this;
        }

        public Criteria andCheckRemarkNotLike(String value) {
            addCriterion("check_remark not like", value, "checkRemark");
            return (Criteria) this;
        }

        public Criteria andCheckRemarkIn(List<String> values) {
            addCriterion("check_remark in", values, "checkRemark");
            return (Criteria) this;
        }

        public Criteria andCheckRemarkNotIn(List<String> values) {
            addCriterion("check_remark not in", values, "checkRemark");
            return (Criteria) this;
        }

        public Criteria andCheckRemarkBetween(String value1, String value2) {
            addCriterion("check_remark between", value1, value2, "checkRemark");
            return (Criteria) this;
        }

        public Criteria andCheckRemarkNotBetween(String value1, String value2) {
            addCriterion("check_remark not between", value1, value2, "checkRemark");
            return (Criteria) this;
        }

        public Criteria andRegisteredTypeIsNull() {
            addCriterion("registered_type is null");
            return (Criteria) this;
        }

        public Criteria andRegisteredTypeIsNotNull() {
            addCriterion("registered_type is not null");
            return (Criteria) this;
        }

        public Criteria andRegisteredTypeEqualTo(String value) {
            addCriterion("registered_type =", value, "registeredType");
            return (Criteria) this;
        }

        public Criteria andRegisteredTypeNotEqualTo(String value) {
            addCriterion("registered_type <>", value, "registeredType");
            return (Criteria) this;
        }

        public Criteria andRegisteredTypeGreaterThan(String value) {
            addCriterion("registered_type >", value, "registeredType");
            return (Criteria) this;
        }

        public Criteria andRegisteredTypeGreaterThanOrEqualTo(String value) {
            addCriterion("registered_type >=", value, "registeredType");
            return (Criteria) this;
        }

        public Criteria andRegisteredTypeLessThan(String value) {
            addCriterion("registered_type <", value, "registeredType");
            return (Criteria) this;
        }

        public Criteria andRegisteredTypeLessThanOrEqualTo(String value) {
            addCriterion("registered_type <=", value, "registeredType");
            return (Criteria) this;
        }

        public Criteria andRegisteredTypeLike(String value) {
            addCriterion("registered_type like", value, "registeredType");
            return (Criteria) this;
        }

        public Criteria andRegisteredTypeNotLike(String value) {
            addCriterion("registered_type not like", value, "registeredType");
            return (Criteria) this;
        }

        public Criteria andRegisteredTypeIn(List<String> values) {
            addCriterion("registered_type in", values, "registeredType");
            return (Criteria) this;
        }

        public Criteria andRegisteredTypeNotIn(List<String> values) {
            addCriterion("registered_type not in", values, "registeredType");
            return (Criteria) this;
        }

        public Criteria andRegisteredTypeBetween(String value1, String value2) {
            addCriterion("registered_type between", value1, value2, "registeredType");
            return (Criteria) this;
        }

        public Criteria andRegisteredTypeNotBetween(String value1, String value2) {
            addCriterion("registered_type not between", value1, value2, "registeredType");
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

        public Criteria andDeletedEqualTo(Integer value) {
            addCriterion("deleted =", value, "deleted");
            return (Criteria) this;
        }

        public Criteria andDeletedNotEqualTo(Integer value) {
            addCriterion("deleted <>", value, "deleted");
            return (Criteria) this;
        }

        public Criteria andDeletedGreaterThan(Integer value) {
            addCriterion("deleted >", value, "deleted");
            return (Criteria) this;
        }

        public Criteria andDeletedGreaterThanOrEqualTo(Integer value) {
            addCriterion("deleted >=", value, "deleted");
            return (Criteria) this;
        }

        public Criteria andDeletedLessThan(Integer value) {
            addCriterion("deleted <", value, "deleted");
            return (Criteria) this;
        }

        public Criteria andDeletedLessThanOrEqualTo(Integer value) {
            addCriterion("deleted <=", value, "deleted");
            return (Criteria) this;
        }

        public Criteria andDeletedIn(List<Integer> values) {
            addCriterion("deleted in", values, "deleted");
            return (Criteria) this;
        }

        public Criteria andDeletedNotIn(List<Integer> values) {
            addCriterion("deleted not in", values, "deleted");
            return (Criteria) this;
        }

        public Criteria andDeletedBetween(Integer value1, Integer value2) {
            addCriterion("deleted between", value1, value2, "deleted");
            return (Criteria) this;
        }

        public Criteria andDeletedNotBetween(Integer value1, Integer value2) {
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