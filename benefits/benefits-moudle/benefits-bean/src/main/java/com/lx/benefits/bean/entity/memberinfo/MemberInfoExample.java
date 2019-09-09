package com.lx.benefits.bean.entity.memberinfo;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class MemberInfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public MemberInfoExample() {
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

        protected void addCriterionForJDBCDate(String condition, Date value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value.getTime()), property);
        }

        protected void addCriterionForJDBCDate(String condition, List<Date> values, String property) {
            if (values == null || values.size() == 0) {
                throw new RuntimeException("Value list for " + property + " cannot be null or empty");
            }
            List<java.sql.Date> dateList = new ArrayList<java.sql.Date>();
            Iterator<Date> iter = values.iterator();
            while (iter.hasNext()) {
                dateList.add(new java.sql.Date(iter.next().getTime()));
            }
            addCriterion(condition, dateList, property);
        }

        protected void addCriterionForJDBCDate(String condition, Date value1, Date value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value1.getTime()), new java.sql.Date(value2.getTime()), property);
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

        public Criteria andNick_nameIsNull() {
            addCriterion("nick_name is null");
            return (Criteria) this;
        }

        public Criteria andNick_nameIsNotNull() {
            addCriterion("nick_name is not null");
            return (Criteria) this;
        }

        public Criteria andNick_nameEqualTo(String value) {
            addCriterion("nick_name =", value, "nick_name");
            return (Criteria) this;
        }

        public Criteria andNick_nameNotEqualTo(String value) {
            addCriterion("nick_name <>", value, "nick_name");
            return (Criteria) this;
        }

        public Criteria andNick_nameGreaterThan(String value) {
            addCriterion("nick_name >", value, "nick_name");
            return (Criteria) this;
        }

        public Criteria andNick_nameGreaterThanOrEqualTo(String value) {
            addCriterion("nick_name >=", value, "nick_name");
            return (Criteria) this;
        }

        public Criteria andNick_nameLessThan(String value) {
            addCriterion("nick_name <", value, "nick_name");
            return (Criteria) this;
        }

        public Criteria andNick_nameLessThanOrEqualTo(String value) {
            addCriterion("nick_name <=", value, "nick_name");
            return (Criteria) this;
        }

        public Criteria andNick_nameLike(String value) {
            addCriterion("nick_name like", value, "nick_name");
            return (Criteria) this;
        }

        public Criteria andNick_nameNotLike(String value) {
            addCriterion("nick_name not like", value, "nick_name");
            return (Criteria) this;
        }

        public Criteria andNick_nameIn(List<String> values) {
            addCriterion("nick_name in", values, "nick_name");
            return (Criteria) this;
        }

        public Criteria andNick_nameNotIn(List<String> values) {
            addCriterion("nick_name not in", values, "nick_name");
            return (Criteria) this;
        }

        public Criteria andNick_nameBetween(String value1, String value2) {
            addCriterion("nick_name between", value1, value2, "nick_name");
            return (Criteria) this;
        }

        public Criteria andNick_nameNotBetween(String value1, String value2) {
            addCriterion("nick_name not between", value1, value2, "nick_name");
            return (Criteria) this;
        }

        public Criteria andPasswordIsNull() {
            addCriterion("password is null");
            return (Criteria) this;
        }

        public Criteria andPasswordIsNotNull() {
            addCriterion("password is not null");
            return (Criteria) this;
        }

        public Criteria andPasswordEqualTo(String value) {
            addCriterion("password =", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotEqualTo(String value) {
            addCriterion("password <>", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordGreaterThan(String value) {
            addCriterion("password >", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordGreaterThanOrEqualTo(String value) {
            addCriterion("password >=", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordLessThan(String value) {
            addCriterion("password <", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordLessThanOrEqualTo(String value) {
            addCriterion("password <=", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordLike(String value) {
            addCriterion("password like", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotLike(String value) {
            addCriterion("password not like", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordIn(List<String> values) {
            addCriterion("password in", values, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotIn(List<String> values) {
            addCriterion("password not in", values, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordBetween(String value1, String value2) {
            addCriterion("password between", value1, value2, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotBetween(String value1, String value2) {
            addCriterion("password not between", value1, value2, "password");
            return (Criteria) this;
        }

        public Criteria andSaltIsNull() {
            addCriterion("salt is null");
            return (Criteria) this;
        }

        public Criteria andSaltIsNotNull() {
            addCriterion("salt is not null");
            return (Criteria) this;
        }

        public Criteria andSaltEqualTo(String value) {
            addCriterion("salt =", value, "salt");
            return (Criteria) this;
        }

        public Criteria andSaltNotEqualTo(String value) {
            addCriterion("salt <>", value, "salt");
            return (Criteria) this;
        }

        public Criteria andSaltGreaterThan(String value) {
            addCriterion("salt >", value, "salt");
            return (Criteria) this;
        }

        public Criteria andSaltGreaterThanOrEqualTo(String value) {
            addCriterion("salt >=", value, "salt");
            return (Criteria) this;
        }

        public Criteria andSaltLessThan(String value) {
            addCriterion("salt <", value, "salt");
            return (Criteria) this;
        }

        public Criteria andSaltLessThanOrEqualTo(String value) {
            addCriterion("salt <=", value, "salt");
            return (Criteria) this;
        }

        public Criteria andSaltLike(String value) {
            addCriterion("salt like", value, "salt");
            return (Criteria) this;
        }

        public Criteria andSaltNotLike(String value) {
            addCriterion("salt not like", value, "salt");
            return (Criteria) this;
        }

        public Criteria andSaltIn(List<String> values) {
            addCriterion("salt in", values, "salt");
            return (Criteria) this;
        }

        public Criteria andSaltNotIn(List<String> values) {
            addCriterion("salt not in", values, "salt");
            return (Criteria) this;
        }

        public Criteria andSaltBetween(String value1, String value2) {
            addCriterion("salt between", value1, value2, "salt");
            return (Criteria) this;
        }

        public Criteria andSaltNotBetween(String value1, String value2) {
            addCriterion("salt not between", value1, value2, "salt");
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

        public Criteria andSexIsNull() {
            addCriterion("sex is null");
            return (Criteria) this;
        }

        public Criteria andSexIsNotNull() {
            addCriterion("sex is not null");
            return (Criteria) this;
        }

        public Criteria andSexEqualTo(Boolean value) {
            addCriterion("sex =", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexNotEqualTo(Boolean value) {
            addCriterion("sex <>", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexGreaterThan(Boolean value) {
            addCriterion("sex >", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexGreaterThanOrEqualTo(Boolean value) {
            addCriterion("sex >=", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexLessThan(Boolean value) {
            addCriterion("sex <", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexLessThanOrEqualTo(Boolean value) {
            addCriterion("sex <=", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexIn(List<Boolean> values) {
            addCriterion("sex in", values, "sex");
            return (Criteria) this;
        }

        public Criteria andSexNotIn(List<Boolean> values) {
            addCriterion("sex not in", values, "sex");
            return (Criteria) this;
        }

        public Criteria andSexBetween(Boolean value1, Boolean value2) {
            addCriterion("sex between", value1, value2, "sex");
            return (Criteria) this;
        }

        public Criteria andSexNotBetween(Boolean value1, Boolean value2) {
            addCriterion("sex not between", value1, value2, "sex");
            return (Criteria) this;
        }

        public Criteria andIs_mobile_verifiedIsNull() {
            addCriterion("is_mobile_verified is null");
            return (Criteria) this;
        }

        public Criteria andIs_mobile_verifiedIsNotNull() {
            addCriterion("is_mobile_verified is not null");
            return (Criteria) this;
        }

        public Criteria andIs_mobile_verifiedEqualTo(Boolean value) {
            addCriterion("is_mobile_verified =", value, "is_mobile_verified");
            return (Criteria) this;
        }

        public Criteria andIs_mobile_verifiedNotEqualTo(Boolean value) {
            addCriterion("is_mobile_verified <>", value, "is_mobile_verified");
            return (Criteria) this;
        }

        public Criteria andIs_mobile_verifiedGreaterThan(Boolean value) {
            addCriterion("is_mobile_verified >", value, "is_mobile_verified");
            return (Criteria) this;
        }

        public Criteria andIs_mobile_verifiedGreaterThanOrEqualTo(Boolean value) {
            addCriterion("is_mobile_verified >=", value, "is_mobile_verified");
            return (Criteria) this;
        }

        public Criteria andIs_mobile_verifiedLessThan(Boolean value) {
            addCriterion("is_mobile_verified <", value, "is_mobile_verified");
            return (Criteria) this;
        }

        public Criteria andIs_mobile_verifiedLessThanOrEqualTo(Boolean value) {
            addCriterion("is_mobile_verified <=", value, "is_mobile_verified");
            return (Criteria) this;
        }

        public Criteria andIs_mobile_verifiedIn(List<Boolean> values) {
            addCriterion("is_mobile_verified in", values, "is_mobile_verified");
            return (Criteria) this;
        }

        public Criteria andIs_mobile_verifiedNotIn(List<Boolean> values) {
            addCriterion("is_mobile_verified not in", values, "is_mobile_verified");
            return (Criteria) this;
        }

        public Criteria andIs_mobile_verifiedBetween(Boolean value1, Boolean value2) {
            addCriterion("is_mobile_verified between", value1, value2, "is_mobile_verified");
            return (Criteria) this;
        }

        public Criteria andIs_mobile_verifiedNotBetween(Boolean value1, Boolean value2) {
            addCriterion("is_mobile_verified not between", value1, value2, "is_mobile_verified");
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

        public Criteria andPlat_formIsNull() {
            addCriterion("plat_form is null");
            return (Criteria) this;
        }

        public Criteria andPlat_formIsNotNull() {
            addCriterion("plat_form is not null");
            return (Criteria) this;
        }

        public Criteria andPlat_formEqualTo(Boolean value) {
            addCriterion("plat_form =", value, "plat_form");
            return (Criteria) this;
        }

        public Criteria andPlat_formNotEqualTo(Boolean value) {
            addCriterion("plat_form <>", value, "plat_form");
            return (Criteria) this;
        }

        public Criteria andPlat_formGreaterThan(Boolean value) {
            addCriterion("plat_form >", value, "plat_form");
            return (Criteria) this;
        }

        public Criteria andPlat_formGreaterThanOrEqualTo(Boolean value) {
            addCriterion("plat_form >=", value, "plat_form");
            return (Criteria) this;
        }

        public Criteria andPlat_formLessThan(Boolean value) {
            addCriterion("plat_form <", value, "plat_form");
            return (Criteria) this;
        }

        public Criteria andPlat_formLessThanOrEqualTo(Boolean value) {
            addCriterion("plat_form <=", value, "plat_form");
            return (Criteria) this;
        }

        public Criteria andPlat_formIn(List<Boolean> values) {
            addCriterion("plat_form in", values, "plat_form");
            return (Criteria) this;
        }

        public Criteria andPlat_formNotIn(List<Boolean> values) {
            addCriterion("plat_form not in", values, "plat_form");
            return (Criteria) this;
        }

        public Criteria andPlat_formBetween(Boolean value1, Boolean value2) {
            addCriterion("plat_form between", value1, value2, "plat_form");
            return (Criteria) this;
        }

        public Criteria andPlat_formNotBetween(Boolean value1, Boolean value2) {
            addCriterion("plat_form not between", value1, value2, "plat_form");
            return (Criteria) this;
        }

        public Criteria andStateIsNull() {
            addCriterion("state is null");
            return (Criteria) this;
        }

        public Criteria andStateIsNotNull() {
            addCriterion("state is not null");
            return (Criteria) this;
        }

        public Criteria andStateEqualTo(Byte value) {
            addCriterion("state =", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotEqualTo(Byte value) {
            addCriterion("state <>", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateGreaterThan(Byte value) {
            addCriterion("state >", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateGreaterThanOrEqualTo(Byte value) {
            addCriterion("state >=", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLessThan(Byte value) {
            addCriterion("state <", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLessThanOrEqualTo(Byte value) {
            addCriterion("state <=", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateIn(List<Byte> values) {
            addCriterion("state in", values, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotIn(List<Byte> values) {
            addCriterion("state not in", values, "state");
            return (Criteria) this;
        }

        public Criteria andStateBetween(Byte value1, Byte value2) {
            addCriterion("state between", value1, value2, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotBetween(Byte value1, Byte value2) {
            addCriterion("state not between", value1, value2, "state");
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

        public Criteria andSourceIsNull() {
            addCriterion("source is null");
            return (Criteria) this;
        }

        public Criteria andSourceIsNotNull() {
            addCriterion("source is not null");
            return (Criteria) this;
        }

        public Criteria andSourceEqualTo(Boolean value) {
            addCriterion("source =", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceNotEqualTo(Boolean value) {
            addCriterion("source <>", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceGreaterThan(Boolean value) {
            addCriterion("source >", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceGreaterThanOrEqualTo(Boolean value) {
            addCriterion("source >=", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceLessThan(Boolean value) {
            addCriterion("source <", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceLessThanOrEqualTo(Boolean value) {
            addCriterion("source <=", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceIn(List<Boolean> values) {
            addCriterion("source in", values, "source");
            return (Criteria) this;
        }

        public Criteria andSourceNotIn(List<Boolean> values) {
            addCriterion("source not in", values, "source");
            return (Criteria) this;
        }

        public Criteria andSourceBetween(Boolean value1, Boolean value2) {
            addCriterion("source between", value1, value2, "source");
            return (Criteria) this;
        }

        public Criteria andSourceNotBetween(Boolean value1, Boolean value2) {
            addCriterion("source not between", value1, value2, "source");
            return (Criteria) this;
        }

        public Criteria andUser_nameIsNull() {
            addCriterion("user_name is null");
            return (Criteria) this;
        }

        public Criteria andUser_nameIsNotNull() {
            addCriterion("user_name is not null");
            return (Criteria) this;
        }

        public Criteria andUser_nameEqualTo(String value) {
            addCriterion("user_name =", value, "user_name");
            return (Criteria) this;
        }

        public Criteria andUser_nameNotEqualTo(String value) {
            addCriterion("user_name <>", value, "user_name");
            return (Criteria) this;
        }

        public Criteria andUser_nameGreaterThan(String value) {
            addCriterion("user_name >", value, "user_name");
            return (Criteria) this;
        }

        public Criteria andUser_nameGreaterThanOrEqualTo(String value) {
            addCriterion("user_name >=", value, "user_name");
            return (Criteria) this;
        }

        public Criteria andUser_nameLessThan(String value) {
            addCriterion("user_name <", value, "user_name");
            return (Criteria) this;
        }

        public Criteria andUser_nameLessThanOrEqualTo(String value) {
            addCriterion("user_name <=", value, "user_name");
            return (Criteria) this;
        }

        public Criteria andUser_nameLike(String value) {
            addCriterion("user_name like", value, "user_name");
            return (Criteria) this;
        }

        public Criteria andUser_nameNotLike(String value) {
            addCriterion("user_name not like", value, "user_name");
            return (Criteria) this;
        }

        public Criteria andUser_nameIn(List<String> values) {
            addCriterion("user_name in", values, "user_name");
            return (Criteria) this;
        }

        public Criteria andUser_nameNotIn(List<String> values) {
            addCriterion("user_name not in", values, "user_name");
            return (Criteria) this;
        }

        public Criteria andUser_nameBetween(String value1, String value2) {
            addCriterion("user_name between", value1, value2, "user_name");
            return (Criteria) this;
        }

        public Criteria andUser_nameNotBetween(String value1, String value2) {
            addCriterion("user_name not between", value1, value2, "user_name");
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

        public Criteria andTpinIsNull() {
            addCriterion("tpin is null");
            return (Criteria) this;
        }

        public Criteria andTpinIsNotNull() {
            addCriterion("tpin is not null");
            return (Criteria) this;
        }

        public Criteria andTpinEqualTo(String value) {
            addCriterion("tpin =", value, "tpin");
            return (Criteria) this;
        }

        public Criteria andTpinNotEqualTo(String value) {
            addCriterion("tpin <>", value, "tpin");
            return (Criteria) this;
        }

        public Criteria andTpinGreaterThan(String value) {
            addCriterion("tpin >", value, "tpin");
            return (Criteria) this;
        }

        public Criteria andTpinGreaterThanOrEqualTo(String value) {
            addCriterion("tpin >=", value, "tpin");
            return (Criteria) this;
        }

        public Criteria andTpinLessThan(String value) {
            addCriterion("tpin <", value, "tpin");
            return (Criteria) this;
        }

        public Criteria andTpinLessThanOrEqualTo(String value) {
            addCriterion("tpin <=", value, "tpin");
            return (Criteria) this;
        }

        public Criteria andTpinLike(String value) {
            addCriterion("tpin like", value, "tpin");
            return (Criteria) this;
        }

        public Criteria andTpinNotLike(String value) {
            addCriterion("tpin not like", value, "tpin");
            return (Criteria) this;
        }

        public Criteria andTpinIn(List<String> values) {
            addCriterion("tpin in", values, "tpin");
            return (Criteria) this;
        }

        public Criteria andTpinNotIn(List<String> values) {
            addCriterion("tpin not in", values, "tpin");
            return (Criteria) this;
        }

        public Criteria andTpinBetween(String value1, String value2) {
            addCriterion("tpin between", value1, value2, "tpin");
            return (Criteria) this;
        }

        public Criteria andTpinNotBetween(String value1, String value2) {
            addCriterion("tpin not between", value1, value2, "tpin");
            return (Criteria) this;
        }

        public Criteria andAdvert_fromIsNull() {
            addCriterion("advert_from is null");
            return (Criteria) this;
        }

        public Criteria andAdvert_fromIsNotNull() {
            addCriterion("advert_from is not null");
            return (Criteria) this;
        }

        public Criteria andAdvert_fromEqualTo(String value) {
            addCriterion("advert_from =", value, "advert_from");
            return (Criteria) this;
        }

        public Criteria andAdvert_fromNotEqualTo(String value) {
            addCriterion("advert_from <>", value, "advert_from");
            return (Criteria) this;
        }

        public Criteria andAdvert_fromGreaterThan(String value) {
            addCriterion("advert_from >", value, "advert_from");
            return (Criteria) this;
        }

        public Criteria andAdvert_fromGreaterThanOrEqualTo(String value) {
            addCriterion("advert_from >=", value, "advert_from");
            return (Criteria) this;
        }

        public Criteria andAdvert_fromLessThan(String value) {
            addCriterion("advert_from <", value, "advert_from");
            return (Criteria) this;
        }

        public Criteria andAdvert_fromLessThanOrEqualTo(String value) {
            addCriterion("advert_from <=", value, "advert_from");
            return (Criteria) this;
        }

        public Criteria andAdvert_fromLike(String value) {
            addCriterion("advert_from like", value, "advert_from");
            return (Criteria) this;
        }

        public Criteria andAdvert_fromNotLike(String value) {
            addCriterion("advert_from not like", value, "advert_from");
            return (Criteria) this;
        }

        public Criteria andAdvert_fromIn(List<String> values) {
            addCriterion("advert_from in", values, "advert_from");
            return (Criteria) this;
        }

        public Criteria andAdvert_fromNotIn(List<String> values) {
            addCriterion("advert_from not in", values, "advert_from");
            return (Criteria) this;
        }

        public Criteria andAdvert_fromBetween(String value1, String value2) {
            addCriterion("advert_from between", value1, value2, "advert_from");
            return (Criteria) this;
        }

        public Criteria andAdvert_fromNotBetween(String value1, String value2) {
            addCriterion("advert_from not between", value1, value2, "advert_from");
            return (Criteria) this;
        }

        public Criteria andTerm_of_validityIsNull() {
            addCriterion("term_of_validity is null");
            return (Criteria) this;
        }

        public Criteria andTerm_of_validityIsNotNull() {
            addCriterion("term_of_validity is not null");
            return (Criteria) this;
        }

        public Criteria andTerm_of_validityEqualTo(Date value) {
            addCriterionForJDBCDate("term_of_validity =", value, "term_of_validity");
            return (Criteria) this;
        }

        public Criteria andTerm_of_validityNotEqualTo(Date value) {
            addCriterionForJDBCDate("term_of_validity <>", value, "term_of_validity");
            return (Criteria) this;
        }

        public Criteria andTerm_of_validityGreaterThan(Date value) {
            addCriterionForJDBCDate("term_of_validity >", value, "term_of_validity");
            return (Criteria) this;
        }

        public Criteria andTerm_of_validityGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("term_of_validity >=", value, "term_of_validity");
            return (Criteria) this;
        }

        public Criteria andTerm_of_validityLessThan(Date value) {
            addCriterionForJDBCDate("term_of_validity <", value, "term_of_validity");
            return (Criteria) this;
        }

        public Criteria andTerm_of_validityLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("term_of_validity <=", value, "term_of_validity");
            return (Criteria) this;
        }

        public Criteria andTerm_of_validityIn(List<Date> values) {
            addCriterionForJDBCDate("term_of_validity in", values, "term_of_validity");
            return (Criteria) this;
        }

        public Criteria andTerm_of_validityNotIn(List<Date> values) {
            addCriterionForJDBCDate("term_of_validity not in", values, "term_of_validity");
            return (Criteria) this;
        }

        public Criteria andTerm_of_validityBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("term_of_validity between", value1, value2, "term_of_validity");
            return (Criteria) this;
        }

        public Criteria andTerm_of_validityNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("term_of_validity not between", value1, value2, "term_of_validity");
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