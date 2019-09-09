package com.lx.benefits.bean.entity.memberdetail;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MemberDetailExample {
    protected String orderByClause;

    protected boolean distinct;

    protected Integer page;

    protected Integer pageSize;

    protected List<Criteria> oredCriteria;

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

    public MemberDetailExample() {
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

        public Criteria andUidIsNull() {
            addCriterion("uid is null");
            return (Criteria) this;
        }

        public Criteria andUidIsNotNull() {
            addCriterion("uid is not null");
            return (Criteria) this;
        }

        public Criteria andUidEqualTo(Long value) {
            addCriterion("uid =", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidNotEqualTo(Long value) {
            addCriterion("uid <>", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidGreaterThan(Long value) {
            addCriterion("uid >", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidGreaterThanOrEqualTo(Long value) {
            addCriterion("uid >=", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidLessThan(Long value) {
            addCriterion("uid <", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidLessThanOrEqualTo(Long value) {
            addCriterion("uid <=", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidIn(List<Long> values) {
            addCriterion("uid in", values, "uid");
            return (Criteria) this;
        }

        public Criteria andUidNotIn(List<Long> values) {
            addCriterion("uid not in", values, "uid");
            return (Criteria) this;
        }

        public Criteria andUidBetween(Long value1, Long value2) {
            addCriterion("uid between", value1, value2, "uid");
            return (Criteria) this;
        }

        public Criteria andUidNotBetween(Long value1, Long value2) {
            addCriterion("uid not between", value1, value2, "uid");
            return (Criteria) this;
        }

        public Criteria andBirthdayIsNull() {
            addCriterion("birthday is null");
            return (Criteria) this;
        }

        public Criteria andBirthdayIsNotNull() {
            addCriterion("birthday is not null");
            return (Criteria) this;
        }

        public Criteria andBirthdayEqualTo(Date value) {
            addCriterion("birthday =", value, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayNotEqualTo(Date value) {
            addCriterion("birthday <>", value, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayGreaterThan(Date value) {
            addCriterion("birthday >", value, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayGreaterThanOrEqualTo(Date value) {
            addCriterion("birthday >=", value, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayLessThan(Date value) {
            addCriterion("birthday <", value, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayLessThanOrEqualTo(Date value) {
            addCriterion("birthday <=", value, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayIn(List<Date> values) {
            addCriterion("birthday in", values, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayNotIn(List<Date> values) {
            addCriterion("birthday not in", values, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayBetween(Date value1, Date value2) {
            addCriterion("birthday between", value1, value2, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayNotBetween(Date value1, Date value2) {
            addCriterion("birthday not between", value1, value2, "birthday");
            return (Criteria) this;
        }

        public Criteria andTrue_nameIsNull() {
            addCriterion("true_name is null");
            return (Criteria) this;
        }

        public Criteria andTrue_nameIsNotNull() {
            addCriterion("true_name is not null");
            return (Criteria) this;
        }

        public Criteria andTrue_nameEqualTo(String value) {
            addCriterion("true_name =", value, "true_name");
            return (Criteria) this;
        }

        public Criteria andTrue_nameNotEqualTo(String value) {
            addCriterion("true_name <>", value, "true_name");
            return (Criteria) this;
        }

        public Criteria andTrue_nameGreaterThan(String value) {
            addCriterion("true_name >", value, "true_name");
            return (Criteria) this;
        }

        public Criteria andTrue_nameGreaterThanOrEqualTo(String value) {
            addCriterion("true_name >=", value, "true_name");
            return (Criteria) this;
        }

        public Criteria andTrue_nameLessThan(String value) {
            addCriterion("true_name <", value, "true_name");
            return (Criteria) this;
        }

        public Criteria andTrue_nameLessThanOrEqualTo(String value) {
            addCriterion("true_name <=", value, "true_name");
            return (Criteria) this;
        }

        public Criteria andTrue_nameLike(String value) {
            addCriterion("true_name like", value, "true_name");
            return (Criteria) this;
        }

        public Criteria andTrue_nameNotLike(String value) {
            addCriterion("true_name not like", value, "true_name");
            return (Criteria) this;
        }

        public Criteria andTrue_nameIn(List<String> values) {
            addCriterion("true_name in", values, "true_name");
            return (Criteria) this;
        }

        public Criteria andTrue_nameNotIn(List<String> values) {
            addCriterion("true_name not in", values, "true_name");
            return (Criteria) this;
        }

        public Criteria andTrue_nameBetween(String value1, String value2) {
            addCriterion("true_name between", value1, value2, "true_name");
            return (Criteria) this;
        }

        public Criteria andTrue_nameNotBetween(String value1, String value2) {
            addCriterion("true_name not between", value1, value2, "true_name");
            return (Criteria) this;
        }

        public Criteria andAvatar_urlIsNull() {
            addCriterion("avatar_url is null");
            return (Criteria) this;
        }

        public Criteria andAvatar_urlIsNotNull() {
            addCriterion("avatar_url is not null");
            return (Criteria) this;
        }

        public Criteria andAvatar_urlEqualTo(String value) {
            addCriterion("avatar_url =", value, "avatar_url");
            return (Criteria) this;
        }

        public Criteria andAvatar_urlNotEqualTo(String value) {
            addCriterion("avatar_url <>", value, "avatar_url");
            return (Criteria) this;
        }

        public Criteria andAvatar_urlGreaterThan(String value) {
            addCriterion("avatar_url >", value, "avatar_url");
            return (Criteria) this;
        }

        public Criteria andAvatar_urlGreaterThanOrEqualTo(String value) {
            addCriterion("avatar_url >=", value, "avatar_url");
            return (Criteria) this;
        }

        public Criteria andAvatar_urlLessThan(String value) {
            addCriterion("avatar_url <", value, "avatar_url");
            return (Criteria) this;
        }

        public Criteria andAvatar_urlLessThanOrEqualTo(String value) {
            addCriterion("avatar_url <=", value, "avatar_url");
            return (Criteria) this;
        }

        public Criteria andAvatar_urlLike(String value) {
            addCriterion("avatar_url like", value, "avatar_url");
            return (Criteria) this;
        }

        public Criteria andAvatar_urlNotLike(String value) {
            addCriterion("avatar_url not like", value, "avatar_url");
            return (Criteria) this;
        }

        public Criteria andAvatar_urlIn(List<String> values) {
            addCriterion("avatar_url in", values, "avatar_url");
            return (Criteria) this;
        }

        public Criteria andAvatar_urlNotIn(List<String> values) {
            addCriterion("avatar_url not in", values, "avatar_url");
            return (Criteria) this;
        }

        public Criteria andAvatar_urlBetween(String value1, String value2) {
            addCriterion("avatar_url between", value1, value2, "avatar_url");
            return (Criteria) this;
        }

        public Criteria andAvatar_urlNotBetween(String value1, String value2) {
            addCriterion("avatar_url not between", value1, value2, "avatar_url");
            return (Criteria) this;
        }

        public Criteria andPic_aIsNull() {
            addCriterion("pic_a is null");
            return (Criteria) this;
        }

        public Criteria andPic_aIsNotNull() {
            addCriterion("pic_a is not null");
            return (Criteria) this;
        }

        public Criteria andPic_aEqualTo(String value) {
            addCriterion("pic_a =", value, "pic_a");
            return (Criteria) this;
        }

        public Criteria andPic_aNotEqualTo(String value) {
            addCriterion("pic_a <>", value, "pic_a");
            return (Criteria) this;
        }

        public Criteria andPic_aGreaterThan(String value) {
            addCriterion("pic_a >", value, "pic_a");
            return (Criteria) this;
        }

        public Criteria andPic_aGreaterThanOrEqualTo(String value) {
            addCriterion("pic_a >=", value, "pic_a");
            return (Criteria) this;
        }

        public Criteria andPic_aLessThan(String value) {
            addCriterion("pic_a <", value, "pic_a");
            return (Criteria) this;
        }

        public Criteria andPic_aLessThanOrEqualTo(String value) {
            addCriterion("pic_a <=", value, "pic_a");
            return (Criteria) this;
        }

        public Criteria andPic_aLike(String value) {
            addCriterion("pic_a like", value, "pic_a");
            return (Criteria) this;
        }

        public Criteria andPic_aNotLike(String value) {
            addCriterion("pic_a not like", value, "pic_a");
            return (Criteria) this;
        }

        public Criteria andPic_aIn(List<String> values) {
            addCriterion("pic_a in", values, "pic_a");
            return (Criteria) this;
        }

        public Criteria andPic_aNotIn(List<String> values) {
            addCriterion("pic_a not in", values, "pic_a");
            return (Criteria) this;
        }

        public Criteria andPic_aBetween(String value1, String value2) {
            addCriterion("pic_a between", value1, value2, "pic_a");
            return (Criteria) this;
        }

        public Criteria andPic_aNotBetween(String value1, String value2) {
            addCriterion("pic_a not between", value1, value2, "pic_a");
            return (Criteria) this;
        }

        public Criteria andPic_bIsNull() {
            addCriterion("pic_b is null");
            return (Criteria) this;
        }

        public Criteria andPic_bIsNotNull() {
            addCriterion("pic_b is not null");
            return (Criteria) this;
        }

        public Criteria andPic_bEqualTo(String value) {
            addCriterion("pic_b =", value, "pic_b");
            return (Criteria) this;
        }

        public Criteria andPic_bNotEqualTo(String value) {
            addCriterion("pic_b <>", value, "pic_b");
            return (Criteria) this;
        }

        public Criteria andPic_bGreaterThan(String value) {
            addCriterion("pic_b >", value, "pic_b");
            return (Criteria) this;
        }

        public Criteria andPic_bGreaterThanOrEqualTo(String value) {
            addCriterion("pic_b >=", value, "pic_b");
            return (Criteria) this;
        }

        public Criteria andPic_bLessThan(String value) {
            addCriterion("pic_b <", value, "pic_b");
            return (Criteria) this;
        }

        public Criteria andPic_bLessThanOrEqualTo(String value) {
            addCriterion("pic_b <=", value, "pic_b");
            return (Criteria) this;
        }

        public Criteria andPic_bLike(String value) {
            addCriterion("pic_b like", value, "pic_b");
            return (Criteria) this;
        }

        public Criteria andPic_bNotLike(String value) {
            addCriterion("pic_b not like", value, "pic_b");
            return (Criteria) this;
        }

        public Criteria andPic_bIn(List<String> values) {
            addCriterion("pic_b in", values, "pic_b");
            return (Criteria) this;
        }

        public Criteria andPic_bNotIn(List<String> values) {
            addCriterion("pic_b not in", values, "pic_b");
            return (Criteria) this;
        }

        public Criteria andPic_bBetween(String value1, String value2) {
            addCriterion("pic_b between", value1, value2, "pic_b");
            return (Criteria) this;
        }

        public Criteria andPic_bNotBetween(String value1, String value2) {
            addCriterion("pic_b not between", value1, value2, "pic_b");
            return (Criteria) this;
        }

        public Criteria andIs_certificate_checkIsNull() {
            addCriterion("is_certificate_check is null");
            return (Criteria) this;
        }

        public Criteria andIs_certificate_checkIsNotNull() {
            addCriterion("is_certificate_check is not null");
            return (Criteria) this;
        }

        public Criteria andIs_certificate_checkEqualTo(Byte value) {
            addCriterion("is_certificate_check =", value, "is_certificate_check");
            return (Criteria) this;
        }

        public Criteria andIs_certificate_checkNotEqualTo(Byte value) {
            addCriterion("is_certificate_check <>", value, "is_certificate_check");
            return (Criteria) this;
        }

        public Criteria andIs_certificate_checkGreaterThan(Byte value) {
            addCriterion("is_certificate_check >", value, "is_certificate_check");
            return (Criteria) this;
        }

        public Criteria andIs_certificate_checkGreaterThanOrEqualTo(Byte value) {
            addCriterion("is_certificate_check >=", value, "is_certificate_check");
            return (Criteria) this;
        }

        public Criteria andIs_certificate_checkLessThan(Byte value) {
            addCriterion("is_certificate_check <", value, "is_certificate_check");
            return (Criteria) this;
        }

        public Criteria andIs_certificate_checkLessThanOrEqualTo(Byte value) {
            addCriterion("is_certificate_check <=", value, "is_certificate_check");
            return (Criteria) this;
        }

        public Criteria andIs_certificate_checkIn(List<Byte> values) {
            addCriterion("is_certificate_check in", values, "is_certificate_check");
            return (Criteria) this;
        }

        public Criteria andIs_certificate_checkNotIn(List<Byte> values) {
            addCriterion("is_certificate_check not in", values, "is_certificate_check");
            return (Criteria) this;
        }

        public Criteria andIs_certificate_checkBetween(Byte value1, Byte value2) {
            addCriterion("is_certificate_check between", value1, value2, "is_certificate_check");
            return (Criteria) this;
        }

        public Criteria andIs_certificate_checkNotBetween(Byte value1, Byte value2) {
            addCriterion("is_certificate_check not between", value1, value2, "is_certificate_check");
            return (Criteria) this;
        }

        public Criteria andVerify_statusIsNull() {
            addCriterion("verify_status is null");
            return (Criteria) this;
        }

        public Criteria andVerify_statusIsNotNull() {
            addCriterion("verify_status is not null");
            return (Criteria) this;
        }

        public Criteria andVerify_statusEqualTo(Integer value) {
            addCriterion("verify_status =", value, "verify_status");
            return (Criteria) this;
        }

        public Criteria andVerify_statusNotEqualTo(Integer value) {
            addCriterion("verify_status <>", value, "verify_status");
            return (Criteria) this;
        }

        public Criteria andVerify_statusGreaterThan(Integer value) {
            addCriterion("verify_status >", value, "verify_status");
            return (Criteria) this;
        }

        public Criteria andVerify_statusGreaterThanOrEqualTo(Integer value) {
            addCriterion("verify_status >=", value, "verify_status");
            return (Criteria) this;
        }

        public Criteria andVerify_statusLessThan(Integer value) {
            addCriterion("verify_status <", value, "verify_status");
            return (Criteria) this;
        }

        public Criteria andVerify_statusLessThanOrEqualTo(Integer value) {
            addCriterion("verify_status <=", value, "verify_status");
            return (Criteria) this;
        }

        public Criteria andVerify_statusIn(List<Integer> values) {
            addCriterion("verify_status in", values, "verify_status");
            return (Criteria) this;
        }

        public Criteria andVerify_statusNotIn(List<Integer> values) {
            addCriterion("verify_status not in", values, "verify_status");
            return (Criteria) this;
        }

        public Criteria andVerify_statusBetween(Integer value1, Integer value2) {
            addCriterion("verify_status between", value1, value2, "verify_status");
            return (Criteria) this;
        }

        public Criteria andVerify_statusNotBetween(Integer value1, Integer value2) {
            addCriterion("verify_status not between", value1, value2, "verify_status");
            return (Criteria) this;
        }

        public Criteria andRegistry_platformIsNull() {
            addCriterion("registry_platform is null");
            return (Criteria) this;
        }

        public Criteria andRegistry_platformIsNotNull() {
            addCriterion("registry_platform is not null");
            return (Criteria) this;
        }

        public Criteria andRegistry_platformEqualTo(Integer value) {
            addCriterion("registry_platform =", value, "registry_platform");
            return (Criteria) this;
        }

        public Criteria andRegistry_platformNotEqualTo(Integer value) {
            addCriterion("registry_platform <>", value, "registry_platform");
            return (Criteria) this;
        }

        public Criteria andRegistry_platformGreaterThan(Integer value) {
            addCriterion("registry_platform >", value, "registry_platform");
            return (Criteria) this;
        }

        public Criteria andRegistry_platformGreaterThanOrEqualTo(Integer value) {
            addCriterion("registry_platform >=", value, "registry_platform");
            return (Criteria) this;
        }

        public Criteria andRegistry_platformLessThan(Integer value) {
            addCriterion("registry_platform <", value, "registry_platform");
            return (Criteria) this;
        }

        public Criteria andRegistry_platformLessThanOrEqualTo(Integer value) {
            addCriterion("registry_platform <=", value, "registry_platform");
            return (Criteria) this;
        }

        public Criteria andRegistry_platformIn(List<Integer> values) {
            addCriterion("registry_platform in", values, "registry_platform");
            return (Criteria) this;
        }

        public Criteria andRegistry_platformNotIn(List<Integer> values) {
            addCriterion("registry_platform not in", values, "registry_platform");
            return (Criteria) this;
        }

        public Criteria andRegistry_platformBetween(Integer value1, Integer value2) {
            addCriterion("registry_platform between", value1, value2, "registry_platform");
            return (Criteria) this;
        }

        public Criteria andRegistry_platformNotBetween(Integer value1, Integer value2) {
            addCriterion("registry_platform not between", value1, value2, "registry_platform");
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

        public Criteria andBaby_birth_hospitalIsNull() {
            addCriterion("baby_birth_hospital is null");
            return (Criteria) this;
        }

        public Criteria andBaby_birth_hospitalIsNotNull() {
            addCriterion("baby_birth_hospital is not null");
            return (Criteria) this;
        }

        public Criteria andBaby_birth_hospitalEqualTo(String value) {
            addCriterion("baby_birth_hospital =", value, "baby_birth_hospital");
            return (Criteria) this;
        }

        public Criteria andBaby_birth_hospitalNotEqualTo(String value) {
            addCriterion("baby_birth_hospital <>", value, "baby_birth_hospital");
            return (Criteria) this;
        }

        public Criteria andBaby_birth_hospitalGreaterThan(String value) {
            addCriterion("baby_birth_hospital >", value, "baby_birth_hospital");
            return (Criteria) this;
        }

        public Criteria andBaby_birth_hospitalGreaterThanOrEqualTo(String value) {
            addCriterion("baby_birth_hospital >=", value, "baby_birth_hospital");
            return (Criteria) this;
        }

        public Criteria andBaby_birth_hospitalLessThan(String value) {
            addCriterion("baby_birth_hospital <", value, "baby_birth_hospital");
            return (Criteria) this;
        }

        public Criteria andBaby_birth_hospitalLessThanOrEqualTo(String value) {
            addCriterion("baby_birth_hospital <=", value, "baby_birth_hospital");
            return (Criteria) this;
        }

        public Criteria andBaby_birth_hospitalLike(String value) {
            addCriterion("baby_birth_hospital like", value, "baby_birth_hospital");
            return (Criteria) this;
        }

        public Criteria andBaby_birth_hospitalNotLike(String value) {
            addCriterion("baby_birth_hospital not like", value, "baby_birth_hospital");
            return (Criteria) this;
        }

        public Criteria andBaby_birth_hospitalIn(List<String> values) {
            addCriterion("baby_birth_hospital in", values, "baby_birth_hospital");
            return (Criteria) this;
        }

        public Criteria andBaby_birth_hospitalNotIn(List<String> values) {
            addCriterion("baby_birth_hospital not in", values, "baby_birth_hospital");
            return (Criteria) this;
        }

        public Criteria andBaby_birth_hospitalBetween(String value1, String value2) {
            addCriterion("baby_birth_hospital between", value1, value2, "baby_birth_hospital");
            return (Criteria) this;
        }

        public Criteria andBaby_birth_hospitalNotBetween(String value1, String value2) {
            addCriterion("baby_birth_hospital not between", value1, value2, "baby_birth_hospital");
            return (Criteria) this;
        }

        public Criteria andCertificate_typeIsNull() {
            addCriterion("certificate_type is null");
            return (Criteria) this;
        }

        public Criteria andCertificate_typeIsNotNull() {
            addCriterion("certificate_type is not null");
            return (Criteria) this;
        }

        public Criteria andCertificate_typeEqualTo(Integer value) {
            addCriterion("certificate_type =", value, "certificate_type");
            return (Criteria) this;
        }

        public Criteria andCertificate_typeNotEqualTo(Integer value) {
            addCriterion("certificate_type <>", value, "certificate_type");
            return (Criteria) this;
        }

        public Criteria andCertificate_typeGreaterThan(Integer value) {
            addCriterion("certificate_type >", value, "certificate_type");
            return (Criteria) this;
        }

        public Criteria andCertificate_typeGreaterThanOrEqualTo(Integer value) {
            addCriterion("certificate_type >=", value, "certificate_type");
            return (Criteria) this;
        }

        public Criteria andCertificate_typeLessThan(Integer value) {
            addCriterion("certificate_type <", value, "certificate_type");
            return (Criteria) this;
        }

        public Criteria andCertificate_typeLessThanOrEqualTo(Integer value) {
            addCriterion("certificate_type <=", value, "certificate_type");
            return (Criteria) this;
        }

        public Criteria andCertificate_typeIn(List<Integer> values) {
            addCriterion("certificate_type in", values, "certificate_type");
            return (Criteria) this;
        }

        public Criteria andCertificate_typeNotIn(List<Integer> values) {
            addCriterion("certificate_type not in", values, "certificate_type");
            return (Criteria) this;
        }

        public Criteria andCertificate_typeBetween(Integer value1, Integer value2) {
            addCriterion("certificate_type between", value1, value2, "certificate_type");
            return (Criteria) this;
        }

        public Criteria andCertificate_typeNotBetween(Integer value1, Integer value2) {
            addCriterion("certificate_type not between", value1, value2, "certificate_type");
            return (Criteria) this;
        }

        public Criteria andCertificate_valueIsNull() {
            addCriterion("certificate_value is null");
            return (Criteria) this;
        }

        public Criteria andCertificate_valueIsNotNull() {
            addCriterion("certificate_value is not null");
            return (Criteria) this;
        }

        public Criteria andCertificate_valueEqualTo(String value) {
            addCriterion("certificate_value =", value, "certificate_value");
            return (Criteria) this;
        }

        public Criteria andCertificate_valueNotEqualTo(String value) {
            addCriterion("certificate_value <>", value, "certificate_value");
            return (Criteria) this;
        }

        public Criteria andCertificate_valueGreaterThan(String value) {
            addCriterion("certificate_value >", value, "certificate_value");
            return (Criteria) this;
        }

        public Criteria andCertificate_valueGreaterThanOrEqualTo(String value) {
            addCriterion("certificate_value >=", value, "certificate_value");
            return (Criteria) this;
        }

        public Criteria andCertificate_valueLessThan(String value) {
            addCriterion("certificate_value <", value, "certificate_value");
            return (Criteria) this;
        }

        public Criteria andCertificate_valueLessThanOrEqualTo(String value) {
            addCriterion("certificate_value <=", value, "certificate_value");
            return (Criteria) this;
        }

        public Criteria andCertificate_valueLike(String value) {
            addCriterion("certificate_value like", value, "certificate_value");
            return (Criteria) this;
        }

        public Criteria andCertificate_valueNotLike(String value) {
            addCriterion("certificate_value not like", value, "certificate_value");
            return (Criteria) this;
        }

        public Criteria andCertificate_valueIn(List<String> values) {
            addCriterion("certificate_value in", values, "certificate_value");
            return (Criteria) this;
        }

        public Criteria andCertificate_valueNotIn(List<String> values) {
            addCriterion("certificate_value not in", values, "certificate_value");
            return (Criteria) this;
        }

        public Criteria andCertificate_valueBetween(String value1, String value2) {
            addCriterion("certificate_value between", value1, value2, "certificate_value");
            return (Criteria) this;
        }

        public Criteria andCertificate_valueNotBetween(String value1, String value2) {
            addCriterion("certificate_value not between", value1, value2, "certificate_value");
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

        public Criteria andModify_timeIsNull() {
            addCriterion("modify_time is null");
            return (Criteria) this;
        }

        public Criteria andModify_timeIsNotNull() {
            addCriterion("modify_time is not null");
            return (Criteria) this;
        }

        public Criteria andModify_timeEqualTo(Date value) {
            addCriterion("modify_time =", value, "modify_time");
            return (Criteria) this;
        }

        public Criteria andModify_timeNotEqualTo(Date value) {
            addCriterion("modify_time <>", value, "modify_time");
            return (Criteria) this;
        }

        public Criteria andModify_timeGreaterThan(Date value) {
            addCriterion("modify_time >", value, "modify_time");
            return (Criteria) this;
        }

        public Criteria andModify_timeGreaterThanOrEqualTo(Date value) {
            addCriterion("modify_time >=", value, "modify_time");
            return (Criteria) this;
        }

        public Criteria andModify_timeLessThan(Date value) {
            addCriterion("modify_time <", value, "modify_time");
            return (Criteria) this;
        }

        public Criteria andModify_timeLessThanOrEqualTo(Date value) {
            addCriterion("modify_time <=", value, "modify_time");
            return (Criteria) this;
        }

        public Criteria andModify_timeIn(List<Date> values) {
            addCriterion("modify_time in", values, "modify_time");
            return (Criteria) this;
        }

        public Criteria andModify_timeNotIn(List<Date> values) {
            addCriterion("modify_time not in", values, "modify_time");
            return (Criteria) this;
        }

        public Criteria andModify_timeBetween(Date value1, Date value2) {
            addCriterion("modify_time between", value1, value2, "modify_time");
            return (Criteria) this;
        }

        public Criteria andModify_timeNotBetween(Date value1, Date value2) {
            addCriterion("modify_time not between", value1, value2, "modify_time");
            return (Criteria) this;
        }

        public Criteria andDelete_timeIsNull() {
            addCriterion("delete_time is null");
            return (Criteria) this;
        }

        public Criteria andDelete_timeIsNotNull() {
            addCriterion("delete_time is not null");
            return (Criteria) this;
        }

        public Criteria andDelete_timeEqualTo(Date value) {
            addCriterion("delete_time =", value, "delete_time");
            return (Criteria) this;
        }

        public Criteria andDelete_timeNotEqualTo(Date value) {
            addCriterion("delete_time <>", value, "delete_time");
            return (Criteria) this;
        }

        public Criteria andDelete_timeGreaterThan(Date value) {
            addCriterion("delete_time >", value, "delete_time");
            return (Criteria) this;
        }

        public Criteria andDelete_timeGreaterThanOrEqualTo(Date value) {
            addCriterion("delete_time >=", value, "delete_time");
            return (Criteria) this;
        }

        public Criteria andDelete_timeLessThan(Date value) {
            addCriterion("delete_time <", value, "delete_time");
            return (Criteria) this;
        }

        public Criteria andDelete_timeLessThanOrEqualTo(Date value) {
            addCriterion("delete_time <=", value, "delete_time");
            return (Criteria) this;
        }

        public Criteria andDelete_timeIn(List<Date> values) {
            addCriterion("delete_time in", values, "delete_time");
            return (Criteria) this;
        }

        public Criteria andDelete_timeNotIn(List<Date> values) {
            addCriterion("delete_time not in", values, "delete_time");
            return (Criteria) this;
        }

        public Criteria andDelete_timeBetween(Date value1, Date value2) {
            addCriterion("delete_time between", value1, value2, "delete_time");
            return (Criteria) this;
        }

        public Criteria andDelete_timeNotBetween(Date value1, Date value2) {
            addCriterion("delete_time not between", value1, value2, "delete_time");
            return (Criteria) this;
        }

        public Criteria andIs_deleteIsNull() {
            addCriterion("is_delete is null");
            return (Criteria) this;
        }

        public Criteria andIs_deleteIsNotNull() {
            addCriterion("is_delete is not null");
            return (Criteria) this;
        }

        public Criteria andIs_deleteEqualTo(Boolean value) {
            addCriterion("is_delete =", value, "is_delete");
            return (Criteria) this;
        }

        public Criteria andIs_deleteNotEqualTo(Boolean value) {
            addCriterion("is_delete <>", value, "is_delete");
            return (Criteria) this;
        }

        public Criteria andIs_deleteGreaterThan(Boolean value) {
            addCriterion("is_delete >", value, "is_delete");
            return (Criteria) this;
        }

        public Criteria andIs_deleteGreaterThanOrEqualTo(Boolean value) {
            addCriterion("is_delete >=", value, "is_delete");
            return (Criteria) this;
        }

        public Criteria andIs_deleteLessThan(Boolean value) {
            addCriterion("is_delete <", value, "is_delete");
            return (Criteria) this;
        }

        public Criteria andIs_deleteLessThanOrEqualTo(Boolean value) {
            addCriterion("is_delete <=", value, "is_delete");
            return (Criteria) this;
        }

        public Criteria andIs_deleteIn(List<Boolean> values) {
            addCriterion("is_delete in", values, "is_delete");
            return (Criteria) this;
        }

        public Criteria andIs_deleteNotIn(List<Boolean> values) {
            addCriterion("is_delete not in", values, "is_delete");
            return (Criteria) this;
        }

        public Criteria andIs_deleteBetween(Boolean value1, Boolean value2) {
            addCriterion("is_delete between", value1, value2, "is_delete");
            return (Criteria) this;
        }

        public Criteria andIs_deleteNotBetween(Boolean value1, Boolean value2) {
            addCriterion("is_delete not between", value1, value2, "is_delete");
            return (Criteria) this;
        }

        public Criteria andLast_login_timeIsNull() {
            addCriterion("last_login_time is null");
            return (Criteria) this;
        }

        public Criteria andLast_login_timeIsNotNull() {
            addCriterion("last_login_time is not null");
            return (Criteria) this;
        }

        public Criteria andLast_login_timeEqualTo(Date value) {
            addCriterion("last_login_time =", value, "last_login_time");
            return (Criteria) this;
        }

        public Criteria andLast_login_timeNotEqualTo(Date value) {
            addCriterion("last_login_time <>", value, "last_login_time");
            return (Criteria) this;
        }

        public Criteria andLast_login_timeGreaterThan(Date value) {
            addCriterion("last_login_time >", value, "last_login_time");
            return (Criteria) this;
        }

        public Criteria andLast_login_timeGreaterThanOrEqualTo(Date value) {
            addCriterion("last_login_time >=", value, "last_login_time");
            return (Criteria) this;
        }

        public Criteria andLast_login_timeLessThan(Date value) {
            addCriterion("last_login_time <", value, "last_login_time");
            return (Criteria) this;
        }

        public Criteria andLast_login_timeLessThanOrEqualTo(Date value) {
            addCriterion("last_login_time <=", value, "last_login_time");
            return (Criteria) this;
        }

        public Criteria andLast_login_timeIn(List<Date> values) {
            addCriterion("last_login_time in", values, "last_login_time");
            return (Criteria) this;
        }

        public Criteria andLast_login_timeNotIn(List<Date> values) {
            addCriterion("last_login_time not in", values, "last_login_time");
            return (Criteria) this;
        }

        public Criteria andLast_login_timeBetween(Date value1, Date value2) {
            addCriterion("last_login_time between", value1, value2, "last_login_time");
            return (Criteria) this;
        }

        public Criteria andLast_login_timeNotBetween(Date value1, Date value2) {
            addCriterion("last_login_time not between", value1, value2, "last_login_time");
            return (Criteria) this;
        }

        public Criteria andLast_login_ipIsNull() {
            addCriterion("last_login_ip is null");
            return (Criteria) this;
        }

        public Criteria andLast_login_ipIsNotNull() {
            addCriterion("last_login_ip is not null");
            return (Criteria) this;
        }

        public Criteria andLast_login_ipEqualTo(String value) {
            addCriterion("last_login_ip =", value, "last_login_ip");
            return (Criteria) this;
        }

        public Criteria andLast_login_ipNotEqualTo(String value) {
            addCriterion("last_login_ip <>", value, "last_login_ip");
            return (Criteria) this;
        }

        public Criteria andLast_login_ipGreaterThan(String value) {
            addCriterion("last_login_ip >", value, "last_login_ip");
            return (Criteria) this;
        }

        public Criteria andLast_login_ipGreaterThanOrEqualTo(String value) {
            addCriterion("last_login_ip >=", value, "last_login_ip");
            return (Criteria) this;
        }

        public Criteria andLast_login_ipLessThan(String value) {
            addCriterion("last_login_ip <", value, "last_login_ip");
            return (Criteria) this;
        }

        public Criteria andLast_login_ipLessThanOrEqualTo(String value) {
            addCriterion("last_login_ip <=", value, "last_login_ip");
            return (Criteria) this;
        }

        public Criteria andLast_login_ipLike(String value) {
            addCriterion("last_login_ip like", value, "last_login_ip");
            return (Criteria) this;
        }

        public Criteria andLast_login_ipNotLike(String value) {
            addCriterion("last_login_ip not like", value, "last_login_ip");
            return (Criteria) this;
        }

        public Criteria andLast_login_ipIn(List<String> values) {
            addCriterion("last_login_ip in", values, "last_login_ip");
            return (Criteria) this;
        }

        public Criteria andLast_login_ipNotIn(List<String> values) {
            addCriterion("last_login_ip not in", values, "last_login_ip");
            return (Criteria) this;
        }

        public Criteria andLast_login_ipBetween(String value1, String value2) {
            addCriterion("last_login_ip between", value1, value2, "last_login_ip");
            return (Criteria) this;
        }

        public Criteria andLast_login_ipNotBetween(String value1, String value2) {
            addCriterion("last_login_ip not between", value1, value2, "last_login_ip");
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