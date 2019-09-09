package com.lx.benefits.bean.entity.memunioninfo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MemUnionInfoExample {
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

    public MemUnionInfoExample() {
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

        public Criteria andMem_idIsNull() {
            addCriterion("mem_id is null");
            return (Criteria) this;
        }

        public Criteria andMem_idIsNotNull() {
            addCriterion("mem_id is not null");
            return (Criteria) this;
        }

        public Criteria andMem_idEqualTo(Long value) {
            addCriterion("mem_id =", value, "mem_id");
            return (Criteria) this;
        }

        public Criteria andMem_idNotEqualTo(Long value) {
            addCriterion("mem_id <>", value, "mem_id");
            return (Criteria) this;
        }

        public Criteria andMem_idGreaterThan(Long value) {
            addCriterion("mem_id >", value, "mem_id");
            return (Criteria) this;
        }

        public Criteria andMem_idGreaterThanOrEqualTo(Long value) {
            addCriterion("mem_id >=", value, "mem_id");
            return (Criteria) this;
        }

        public Criteria andMem_idLessThan(Long value) {
            addCriterion("mem_id <", value, "mem_id");
            return (Criteria) this;
        }

        public Criteria andMem_idLessThanOrEqualTo(Long value) {
            addCriterion("mem_id <=", value, "mem_id");
            return (Criteria) this;
        }

        public Criteria andMem_idIn(List<Long> values) {
            addCriterion("mem_id in", values, "mem_id");
            return (Criteria) this;
        }

        public Criteria andMem_idNotIn(List<Long> values) {
            addCriterion("mem_id not in", values, "mem_id");
            return (Criteria) this;
        }

        public Criteria andMem_idBetween(Long value1, Long value2) {
            addCriterion("mem_id between", value1, value2, "mem_id");
            return (Criteria) this;
        }

        public Criteria andMem_idNotBetween(Long value1, Long value2) {
            addCriterion("mem_id not between", value1, value2, "mem_id");
            return (Criteria) this;
        }

        public Criteria andUnion_valIsNull() {
            addCriterion("union_val is null");
            return (Criteria) this;
        }

        public Criteria andUnion_valIsNotNull() {
            addCriterion("union_val is not null");
            return (Criteria) this;
        }

        public Criteria andUnion_valEqualTo(String value) {
            addCriterion("union_val =", value, "union_val");
            return (Criteria) this;
        }

        public Criteria andUnion_valNotEqualTo(String value) {
            addCriterion("union_val <>", value, "union_val");
            return (Criteria) this;
        }

        public Criteria andUnion_valGreaterThan(String value) {
            addCriterion("union_val >", value, "union_val");
            return (Criteria) this;
        }

        public Criteria andUnion_valGreaterThanOrEqualTo(String value) {
            addCriterion("union_val >=", value, "union_val");
            return (Criteria) this;
        }

        public Criteria andUnion_valLessThan(String value) {
            addCriterion("union_val <", value, "union_val");
            return (Criteria) this;
        }

        public Criteria andUnion_valLessThanOrEqualTo(String value) {
            addCriterion("union_val <=", value, "union_val");
            return (Criteria) this;
        }

        public Criteria andUnion_valLike(String value) {
            addCriterion("union_val like", value, "union_val");
            return (Criteria) this;
        }

        public Criteria andUnion_valNotLike(String value) {
            addCriterion("union_val not like", value, "union_val");
            return (Criteria) this;
        }

        public Criteria andUnion_valIn(List<String> values) {
            addCriterion("union_val in", values, "union_val");
            return (Criteria) this;
        }

        public Criteria andUnion_valNotIn(List<String> values) {
            addCriterion("union_val not in", values, "union_val");
            return (Criteria) this;
        }

        public Criteria andUnion_valBetween(String value1, String value2) {
            addCriterion("union_val between", value1, value2, "union_val");
            return (Criteria) this;
        }

        public Criteria andUnion_valNotBetween(String value1, String value2) {
            addCriterion("union_val not between", value1, value2, "union_val");
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

        public Criteria andIs_deletedIsNull() {
            addCriterion("is_deleted is null");
            return (Criteria) this;
        }

        public Criteria andIs_deletedIsNotNull() {
            addCriterion("is_deleted is not null");
            return (Criteria) this;
        }

        public Criteria andIs_deletedEqualTo(Boolean value) {
            addCriterion("is_deleted =", value, "is_deleted");
            return (Criteria) this;
        }

        public Criteria andIs_deletedNotEqualTo(Boolean value) {
            addCriterion("is_deleted <>", value, "is_deleted");
            return (Criteria) this;
        }

        public Criteria andIs_deletedGreaterThan(Boolean value) {
            addCriterion("is_deleted >", value, "is_deleted");
            return (Criteria) this;
        }

        public Criteria andIs_deletedGreaterThanOrEqualTo(Boolean value) {
            addCriterion("is_deleted >=", value, "is_deleted");
            return (Criteria) this;
        }

        public Criteria andIs_deletedLessThan(Boolean value) {
            addCriterion("is_deleted <", value, "is_deleted");
            return (Criteria) this;
        }

        public Criteria andIs_deletedLessThanOrEqualTo(Boolean value) {
            addCriterion("is_deleted <=", value, "is_deleted");
            return (Criteria) this;
        }

        public Criteria andIs_deletedIn(List<Boolean> values) {
            addCriterion("is_deleted in", values, "is_deleted");
            return (Criteria) this;
        }

        public Criteria andIs_deletedNotIn(List<Boolean> values) {
            addCriterion("is_deleted not in", values, "is_deleted");
            return (Criteria) this;
        }

        public Criteria andIs_deletedBetween(Boolean value1, Boolean value2) {
            addCriterion("is_deleted between", value1, value2, "is_deleted");
            return (Criteria) this;
        }

        public Criteria andIs_deletedNotBetween(Boolean value1, Boolean value2) {
            addCriterion("is_deleted not between", value1, value2, "is_deleted");
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

        public Criteria andUnion_dataIsNull() {
            addCriterion("union_data is null");
            return (Criteria) this;
        }

        public Criteria andUnion_dataIsNotNull() {
            addCriterion("union_data is not null");
            return (Criteria) this;
        }

        public Criteria andUnion_dataEqualTo(String value) {
            addCriterion("union_data =", value, "union_data");
            return (Criteria) this;
        }

        public Criteria andUnion_dataNotEqualTo(String value) {
            addCriterion("union_data <>", value, "union_data");
            return (Criteria) this;
        }

        public Criteria andUnion_dataGreaterThan(String value) {
            addCriterion("union_data >", value, "union_data");
            return (Criteria) this;
        }

        public Criteria andUnion_dataGreaterThanOrEqualTo(String value) {
            addCriterion("union_data >=", value, "union_data");
            return (Criteria) this;
        }

        public Criteria andUnion_dataLessThan(String value) {
            addCriterion("union_data <", value, "union_data");
            return (Criteria) this;
        }

        public Criteria andUnion_dataLessThanOrEqualTo(String value) {
            addCriterion("union_data <=", value, "union_data");
            return (Criteria) this;
        }

        public Criteria andUnion_dataLike(String value) {
            addCriterion("union_data like", value, "union_data");
            return (Criteria) this;
        }

        public Criteria andUnion_dataNotLike(String value) {
            addCriterion("union_data not like", value, "union_data");
            return (Criteria) this;
        }

        public Criteria andUnion_dataIn(List<String> values) {
            addCriterion("union_data in", values, "union_data");
            return (Criteria) this;
        }

        public Criteria andUnion_dataNotIn(List<String> values) {
            addCriterion("union_data not in", values, "union_data");
            return (Criteria) this;
        }

        public Criteria andUnion_dataBetween(String value1, String value2) {
            addCriterion("union_data between", value1, value2, "union_data");
            return (Criteria) this;
        }

        public Criteria andUnion_dataNotBetween(String value1, String value2) {
            addCriterion("union_data not between", value1, value2, "union_data");
            return (Criteria) this;
        }

        public Criteria andOrg_codeIsNull() {
            addCriterion("org_code is null");
            return (Criteria) this;
        }

        public Criteria andOrg_codeIsNotNull() {
            addCriterion("org_code is not null");
            return (Criteria) this;
        }

        public Criteria andOrg_codeEqualTo(String value) {
            addCriterion("org_code =", value, "org_code");
            return (Criteria) this;
        }

        public Criteria andOrg_codeNotEqualTo(String value) {
            addCriterion("org_code <>", value, "org_code");
            return (Criteria) this;
        }

        public Criteria andOrg_codeGreaterThan(String value) {
            addCriterion("org_code >", value, "org_code");
            return (Criteria) this;
        }

        public Criteria andOrg_codeGreaterThanOrEqualTo(String value) {
            addCriterion("org_code >=", value, "org_code");
            return (Criteria) this;
        }

        public Criteria andOrg_codeLessThan(String value) {
            addCriterion("org_code <", value, "org_code");
            return (Criteria) this;
        }

        public Criteria andOrg_codeLessThanOrEqualTo(String value) {
            addCriterion("org_code <=", value, "org_code");
            return (Criteria) this;
        }

        public Criteria andOrg_codeLike(String value) {
            addCriterion("org_code like", value, "org_code");
            return (Criteria) this;
        }

        public Criteria andOrg_codeNotLike(String value) {
            addCriterion("org_code not like", value, "org_code");
            return (Criteria) this;
        }

        public Criteria andOrg_codeIn(List<String> values) {
            addCriterion("org_code in", values, "org_code");
            return (Criteria) this;
        }

        public Criteria andOrg_codeNotIn(List<String> values) {
            addCriterion("org_code not in", values, "org_code");
            return (Criteria) this;
        }

        public Criteria andOrg_codeBetween(String value1, String value2) {
            addCriterion("org_code between", value1, value2, "org_code");
            return (Criteria) this;
        }

        public Criteria andOrg_codeNotBetween(String value1, String value2) {
            addCriterion("org_code not between", value1, value2, "org_code");
            return (Criteria) this;
        }

        public Criteria andEe_noIsNull() {
            addCriterion("ee_no is null");
            return (Criteria) this;
        }

        public Criteria andEe_noIsNotNull() {
            addCriterion("ee_no is not null");
            return (Criteria) this;
        }

        public Criteria andEe_noEqualTo(String value) {
            addCriterion("ee_no =", value, "ee_no");
            return (Criteria) this;
        }

        public Criteria andEe_noNotEqualTo(String value) {
            addCriterion("ee_no <>", value, "ee_no");
            return (Criteria) this;
        }

        public Criteria andEe_noGreaterThan(String value) {
            addCriterion("ee_no >", value, "ee_no");
            return (Criteria) this;
        }

        public Criteria andEe_noGreaterThanOrEqualTo(String value) {
            addCriterion("ee_no >=", value, "ee_no");
            return (Criteria) this;
        }

        public Criteria andEe_noLessThan(String value) {
            addCriterion("ee_no <", value, "ee_no");
            return (Criteria) this;
        }

        public Criteria andEe_noLessThanOrEqualTo(String value) {
            addCriterion("ee_no <=", value, "ee_no");
            return (Criteria) this;
        }

        public Criteria andEe_noLike(String value) {
            addCriterion("ee_no like", value, "ee_no");
            return (Criteria) this;
        }

        public Criteria andEe_noNotLike(String value) {
            addCriterion("ee_no not like", value, "ee_no");
            return (Criteria) this;
        }

        public Criteria andEe_noIn(List<String> values) {
            addCriterion("ee_no in", values, "ee_no");
            return (Criteria) this;
        }

        public Criteria andEe_noNotIn(List<String> values) {
            addCriterion("ee_no not in", values, "ee_no");
            return (Criteria) this;
        }

        public Criteria andEe_noBetween(String value1, String value2) {
            addCriterion("ee_no between", value1, value2, "ee_no");
            return (Criteria) this;
        }

        public Criteria andEe_noNotBetween(String value1, String value2) {
            addCriterion("ee_no not between", value1, value2, "ee_no");
            return (Criteria) this;
        }

        public Criteria andUnion_user_idIsNull() {
            addCriterion("union_user_id is null");
            return (Criteria) this;
        }

        public Criteria andUnion_user_idIsNotNull() {
            addCriterion("union_user_id is not null");
            return (Criteria) this;
        }

        public Criteria andUnion_user_idEqualTo(String value) {
            addCriterion("union_user_id =", value, "union_user_id");
            return (Criteria) this;
        }

        public Criteria andUnion_user_idNotEqualTo(String value) {
            addCriterion("union_user_id <>", value, "union_user_id");
            return (Criteria) this;
        }

        public Criteria andUnion_user_idGreaterThan(String value) {
            addCriterion("union_user_id >", value, "union_user_id");
            return (Criteria) this;
        }

        public Criteria andUnion_user_idGreaterThanOrEqualTo(String value) {
            addCriterion("union_user_id >=", value, "union_user_id");
            return (Criteria) this;
        }

        public Criteria andUnion_user_idLessThan(String value) {
            addCriterion("union_user_id <", value, "union_user_id");
            return (Criteria) this;
        }

        public Criteria andUnion_user_idLessThanOrEqualTo(String value) {
            addCriterion("union_user_id <=", value, "union_user_id");
            return (Criteria) this;
        }

        public Criteria andUnion_user_idLike(String value) {
            addCriterion("union_user_id like", value, "union_user_id");
            return (Criteria) this;
        }

        public Criteria andUnion_user_idNotLike(String value) {
            addCriterion("union_user_id not like", value, "union_user_id");
            return (Criteria) this;
        }

        public Criteria andUnion_user_idIn(List<String> values) {
            addCriterion("union_user_id in", values, "union_user_id");
            return (Criteria) this;
        }

        public Criteria andUnion_user_idNotIn(List<String> values) {
            addCriterion("union_user_id not in", values, "union_user_id");
            return (Criteria) this;
        }

        public Criteria andUnion_user_idBetween(String value1, String value2) {
            addCriterion("union_user_id between", value1, value2, "union_user_id");
            return (Criteria) this;
        }

        public Criteria andUnion_user_idNotBetween(String value1, String value2) {
            addCriterion("union_user_id not between", value1, value2, "union_user_id");
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