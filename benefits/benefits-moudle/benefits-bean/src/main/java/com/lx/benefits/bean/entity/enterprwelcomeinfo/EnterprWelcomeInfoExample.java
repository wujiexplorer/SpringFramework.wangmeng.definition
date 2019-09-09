package com.lx.benefits.bean.entity.enterprwelcomeinfo;

import java.util.ArrayList;
import java.util.List;

public class EnterprWelcomeInfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public EnterprWelcomeInfoExample() {
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

        public Criteria andCustomIdIsNull() {
            addCriterion("customId is null");
            return (Criteria) this;
        }

        public Criteria andCustomIdIsNotNull() {
            addCriterion("customId is not null");
            return (Criteria) this;
        }

        public Criteria andCustomIdEqualTo(Long value) {
            addCriterion("customId =", value, "customId");
            return (Criteria) this;
        }

        public Criteria andCustomIdNotEqualTo(Long value) {
            addCriterion("customId <>", value, "customId");
            return (Criteria) this;
        }

        public Criteria andCustomIdGreaterThan(Long value) {
            addCriterion("customId >", value, "customId");
            return (Criteria) this;
        }

        public Criteria andCustomIdGreaterThanOrEqualTo(Long value) {
            addCriterion("customId >=", value, "customId");
            return (Criteria) this;
        }

        public Criteria andCustomIdLessThan(Long value) {
            addCriterion("customId <", value, "customId");
            return (Criteria) this;
        }

        public Criteria andCustomIdLessThanOrEqualTo(Long value) {
            addCriterion("customId <=", value, "customId");
            return (Criteria) this;
        }

        public Criteria andCustomIdIn(List<Long> values) {
            addCriterion("customId in", values, "customId");
            return (Criteria) this;
        }

        public Criteria andCustomIdNotIn(List<Long> values) {
            addCriterion("customId not in", values, "customId");
            return (Criteria) this;
        }

        public Criteria andCustomIdBetween(Long value1, Long value2) {
            addCriterion("customId between", value1, value2, "customId");
            return (Criteria) this;
        }

        public Criteria andCustomIdNotBetween(Long value1, Long value2) {
            addCriterion("customId not between", value1, value2, "customId");
            return (Criteria) this;
        }

        public Criteria andEnterprIdIsNull() {
            addCriterion("enterprId is null");
            return (Criteria) this;
        }

        public Criteria andEnterprIdIsNotNull() {
            addCriterion("enterprId is not null");
            return (Criteria) this;
        }

        public Criteria andEnterprIdEqualTo(Long value) {
            addCriterion("enterprId =", value, "enterprId");
            return (Criteria) this;
        }

        public Criteria andEnterprIdNotEqualTo(Long value) {
            addCriterion("enterprId <>", value, "enterprId");
            return (Criteria) this;
        }

        public Criteria andEnterprIdGreaterThan(Long value) {
            addCriterion("enterprId >", value, "enterprId");
            return (Criteria) this;
        }

        public Criteria andEnterprIdGreaterThanOrEqualTo(Long value) {
            addCriterion("enterprId >=", value, "enterprId");
            return (Criteria) this;
        }

        public Criteria andEnterprIdLessThan(Long value) {
            addCriterion("enterprId <", value, "enterprId");
            return (Criteria) this;
        }

        public Criteria andEnterprIdLessThanOrEqualTo(Long value) {
            addCriterion("enterprId <=", value, "enterprId");
            return (Criteria) this;
        }

        public Criteria andEnterprIdIn(List<Long> values) {
            addCriterion("enterprId in", values, "enterprId");
            return (Criteria) this;
        }

        public Criteria andEnterprIdNotIn(List<Long> values) {
            addCriterion("enterprId not in", values, "enterprId");
            return (Criteria) this;
        }

        public Criteria andEnterprIdBetween(Long value1, Long value2) {
            addCriterion("enterprId between", value1, value2, "enterprId");
            return (Criteria) this;
        }

        public Criteria andEnterprIdNotBetween(Long value1, Long value2) {
            addCriterion("enterprId not between", value1, value2, "enterprId");
            return (Criteria) this;
        }

        public Criteria andLogoPathIsNull() {
            addCriterion("logoPath is null");
            return (Criteria) this;
        }

        public Criteria andLogoPathIsNotNull() {
            addCriterion("logoPath is not null");
            return (Criteria) this;
        }

        public Criteria andLogoPathEqualTo(String value) {
            addCriterion("logoPath =", value, "logoPath");
            return (Criteria) this;
        }

        public Criteria andLogoPathNotEqualTo(String value) {
            addCriterion("logoPath <>", value, "logoPath");
            return (Criteria) this;
        }

        public Criteria andLogoPathGreaterThan(String value) {
            addCriterion("logoPath >", value, "logoPath");
            return (Criteria) this;
        }

        public Criteria andLogoPathGreaterThanOrEqualTo(String value) {
            addCriterion("logoPath >=", value, "logoPath");
            return (Criteria) this;
        }

        public Criteria andLogoPathLessThan(String value) {
            addCriterion("logoPath <", value, "logoPath");
            return (Criteria) this;
        }

        public Criteria andLogoPathLessThanOrEqualTo(String value) {
            addCriterion("logoPath <=", value, "logoPath");
            return (Criteria) this;
        }

        public Criteria andLogoPathLike(String value) {
            addCriterion("logoPath like", value, "logoPath");
            return (Criteria) this;
        }

        public Criteria andLogoPathNotLike(String value) {
            addCriterion("logoPath not like", value, "logoPath");
            return (Criteria) this;
        }

        public Criteria andLogoPathIn(List<String> values) {
            addCriterion("logoPath in", values, "logoPath");
            return (Criteria) this;
        }

        public Criteria andLogoPathNotIn(List<String> values) {
            addCriterion("logoPath not in", values, "logoPath");
            return (Criteria) this;
        }

        public Criteria andLogoPathBetween(String value1, String value2) {
            addCriterion("logoPath between", value1, value2, "logoPath");
            return (Criteria) this;
        }

        public Criteria andLogoPathNotBetween(String value1, String value2) {
            addCriterion("logoPath not between", value1, value2, "logoPath");
            return (Criteria) this;
        }

        public Criteria andPageBackgroundIsNull() {
            addCriterion("pageBackground is null");
            return (Criteria) this;
        }

        public Criteria andPageBackgroundIsNotNull() {
            addCriterion("pageBackground is not null");
            return (Criteria) this;
        }

        public Criteria andPageBackgroundEqualTo(String value) {
            addCriterion("pageBackground =", value, "pageBackground");
            return (Criteria) this;
        }

        public Criteria andPageBackgroundNotEqualTo(String value) {
            addCriterion("pageBackground <>", value, "pageBackground");
            return (Criteria) this;
        }

        public Criteria andPageBackgroundGreaterThan(String value) {
            addCriterion("pageBackground >", value, "pageBackground");
            return (Criteria) this;
        }

        public Criteria andPageBackgroundGreaterThanOrEqualTo(String value) {
            addCriterion("pageBackground >=", value, "pageBackground");
            return (Criteria) this;
        }

        public Criteria andPageBackgroundLessThan(String value) {
            addCriterion("pageBackground <", value, "pageBackground");
            return (Criteria) this;
        }

        public Criteria andPageBackgroundLessThanOrEqualTo(String value) {
            addCriterion("pageBackground <=", value, "pageBackground");
            return (Criteria) this;
        }

        public Criteria andPageBackgroundLike(String value) {
            addCriterion("pageBackground like", value, "pageBackground");
            return (Criteria) this;
        }

        public Criteria andPageBackgroundNotLike(String value) {
            addCriterion("pageBackground not like", value, "pageBackground");
            return (Criteria) this;
        }

        public Criteria andPageBackgroundIn(List<String> values) {
            addCriterion("pageBackground in", values, "pageBackground");
            return (Criteria) this;
        }

        public Criteria andPageBackgroundNotIn(List<String> values) {
            addCriterion("pageBackground not in", values, "pageBackground");
            return (Criteria) this;
        }

        public Criteria andPageBackgroundBetween(String value1, String value2) {
            addCriterion("pageBackground between", value1, value2, "pageBackground");
            return (Criteria) this;
        }

        public Criteria andPageBackgroundNotBetween(String value1, String value2) {
            addCriterion("pageBackground not between", value1, value2, "pageBackground");
            return (Criteria) this;
        }

        public Criteria andNavBackgroundIsNull() {
            addCriterion("navBackground is null");
            return (Criteria) this;
        }

        public Criteria andNavBackgroundIsNotNull() {
            addCriterion("navBackground is not null");
            return (Criteria) this;
        }

        public Criteria andNavBackgroundEqualTo(String value) {
            addCriterion("navBackground =", value, "navBackground");
            return (Criteria) this;
        }

        public Criteria andNavBackgroundNotEqualTo(String value) {
            addCriterion("navBackground <>", value, "navBackground");
            return (Criteria) this;
        }

        public Criteria andNavBackgroundGreaterThan(String value) {
            addCriterion("navBackground >", value, "navBackground");
            return (Criteria) this;
        }

        public Criteria andNavBackgroundGreaterThanOrEqualTo(String value) {
            addCriterion("navBackground >=", value, "navBackground");
            return (Criteria) this;
        }

        public Criteria andNavBackgroundLessThan(String value) {
            addCriterion("navBackground <", value, "navBackground");
            return (Criteria) this;
        }

        public Criteria andNavBackgroundLessThanOrEqualTo(String value) {
            addCriterion("navBackground <=", value, "navBackground");
            return (Criteria) this;
        }

        public Criteria andNavBackgroundLike(String value) {
            addCriterion("navBackground like", value, "navBackground");
            return (Criteria) this;
        }

        public Criteria andNavBackgroundNotLike(String value) {
            addCriterion("navBackground not like", value, "navBackground");
            return (Criteria) this;
        }

        public Criteria andNavBackgroundIn(List<String> values) {
            addCriterion("navBackground in", values, "navBackground");
            return (Criteria) this;
        }

        public Criteria andNavBackgroundNotIn(List<String> values) {
            addCriterion("navBackground not in", values, "navBackground");
            return (Criteria) this;
        }

        public Criteria andNavBackgroundBetween(String value1, String value2) {
            addCriterion("navBackground between", value1, value2, "navBackground");
            return (Criteria) this;
        }

        public Criteria andNavBackgroundNotBetween(String value1, String value2) {
            addCriterion("navBackground not between", value1, value2, "navBackground");
            return (Criteria) this;
        }

        public Criteria andContactPhoneIsNull() {
            addCriterion("contactPhone is null");
            return (Criteria) this;
        }

        public Criteria andContactPhoneIsNotNull() {
            addCriterion("contactPhone is not null");
            return (Criteria) this;
        }

        public Criteria andContactPhoneEqualTo(String value) {
            addCriterion("contactPhone =", value, "contactPhone");
            return (Criteria) this;
        }

        public Criteria andContactPhoneNotEqualTo(String value) {
            addCriterion("contactPhone <>", value, "contactPhone");
            return (Criteria) this;
        }

        public Criteria andContactPhoneGreaterThan(String value) {
            addCriterion("contactPhone >", value, "contactPhone");
            return (Criteria) this;
        }

        public Criteria andContactPhoneGreaterThanOrEqualTo(String value) {
            addCriterion("contactPhone >=", value, "contactPhone");
            return (Criteria) this;
        }

        public Criteria andContactPhoneLessThan(String value) {
            addCriterion("contactPhone <", value, "contactPhone");
            return (Criteria) this;
        }

        public Criteria andContactPhoneLessThanOrEqualTo(String value) {
            addCriterion("contactPhone <=", value, "contactPhone");
            return (Criteria) this;
        }

        public Criteria andContactPhoneLike(String value) {
            addCriterion("contactPhone like", value, "contactPhone");
            return (Criteria) this;
        }

        public Criteria andContactPhoneNotLike(String value) {
            addCriterion("contactPhone not like", value, "contactPhone");
            return (Criteria) this;
        }

        public Criteria andContactPhoneIn(List<String> values) {
            addCriterion("contactPhone in", values, "contactPhone");
            return (Criteria) this;
        }

        public Criteria andContactPhoneNotIn(List<String> values) {
            addCriterion("contactPhone not in", values, "contactPhone");
            return (Criteria) this;
        }

        public Criteria andContactPhoneBetween(String value1, String value2) {
            addCriterion("contactPhone between", value1, value2, "contactPhone");
            return (Criteria) this;
        }

        public Criteria andContactPhoneNotBetween(String value1, String value2) {
            addCriterion("contactPhone not between", value1, value2, "contactPhone");
            return (Criteria) this;
        }

        public Criteria andContactEmailIsNull() {
            addCriterion("contactEmail is null");
            return (Criteria) this;
        }

        public Criteria andContactEmailIsNotNull() {
            addCriterion("contactEmail is not null");
            return (Criteria) this;
        }

        public Criteria andContactEmailEqualTo(String value) {
            addCriterion("contactEmail =", value, "contactEmail");
            return (Criteria) this;
        }

        public Criteria andContactEmailNotEqualTo(String value) {
            addCriterion("contactEmail <>", value, "contactEmail");
            return (Criteria) this;
        }

        public Criteria andContactEmailGreaterThan(String value) {
            addCriterion("contactEmail >", value, "contactEmail");
            return (Criteria) this;
        }

        public Criteria andContactEmailGreaterThanOrEqualTo(String value) {
            addCriterion("contactEmail >=", value, "contactEmail");
            return (Criteria) this;
        }

        public Criteria andContactEmailLessThan(String value) {
            addCriterion("contactEmail <", value, "contactEmail");
            return (Criteria) this;
        }

        public Criteria andContactEmailLessThanOrEqualTo(String value) {
            addCriterion("contactEmail <=", value, "contactEmail");
            return (Criteria) this;
        }

        public Criteria andContactEmailLike(String value) {
            addCriterion("contactEmail like", value, "contactEmail");
            return (Criteria) this;
        }

        public Criteria andContactEmailNotLike(String value) {
            addCriterion("contactEmail not like", value, "contactEmail");
            return (Criteria) this;
        }

        public Criteria andContactEmailIn(List<String> values) {
            addCriterion("contactEmail in", values, "contactEmail");
            return (Criteria) this;
        }

        public Criteria andContactEmailNotIn(List<String> values) {
            addCriterion("contactEmail not in", values, "contactEmail");
            return (Criteria) this;
        }

        public Criteria andContactEmailBetween(String value1, String value2) {
            addCriterion("contactEmail between", value1, value2, "contactEmail");
            return (Criteria) this;
        }

        public Criteria andContactEmailNotBetween(String value1, String value2) {
            addCriterion("contactEmail not between", value1, value2, "contactEmail");
            return (Criteria) this;
        }

        public Criteria andIsDeletedIsNull() {
            addCriterion("isDeleted is null");
            return (Criteria) this;
        }

        public Criteria andIsDeletedIsNotNull() {
            addCriterion("isDeleted is not null");
            return (Criteria) this;
        }

        public Criteria andIsDeletedEqualTo(Boolean value) {
            addCriterion("isDeleted =", value, "isDeleted");
            return (Criteria) this;
        }

        public Criteria andIsDeletedNotEqualTo(Boolean value) {
            addCriterion("isDeleted <>", value, "isDeleted");
            return (Criteria) this;
        }

        public Criteria andIsDeletedGreaterThan(Boolean value) {
            addCriterion("isDeleted >", value, "isDeleted");
            return (Criteria) this;
        }

        public Criteria andIsDeletedGreaterThanOrEqualTo(Boolean value) {
            addCriterion("isDeleted >=", value, "isDeleted");
            return (Criteria) this;
        }

        public Criteria andIsDeletedLessThan(Boolean value) {
            addCriterion("isDeleted <", value, "isDeleted");
            return (Criteria) this;
        }

        public Criteria andIsDeletedLessThanOrEqualTo(Boolean value) {
            addCriterion("isDeleted <=", value, "isDeleted");
            return (Criteria) this;
        }

        public Criteria andIsDeletedIn(List<Boolean> values) {
            addCriterion("isDeleted in", values, "isDeleted");
            return (Criteria) this;
        }

        public Criteria andIsDeletedNotIn(List<Boolean> values) {
            addCriterion("isDeleted not in", values, "isDeleted");
            return (Criteria) this;
        }

        public Criteria andIsDeletedBetween(Boolean value1, Boolean value2) {
            addCriterion("isDeleted between", value1, value2, "isDeleted");
            return (Criteria) this;
        }

        public Criteria andIsDeletedNotBetween(Boolean value1, Boolean value2) {
            addCriterion("isDeleted not between", value1, value2, "isDeleted");
            return (Criteria) this;
        }

        public Criteria andCreatedIsNull() {
            addCriterion("created is null");
            return (Criteria) this;
        }

        public Criteria andCreatedIsNotNull() {
            addCriterion("created is not null");
            return (Criteria) this;
        }

        public Criteria andCreatedEqualTo(Integer value) {
            addCriterion("created =", value, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedNotEqualTo(Integer value) {
            addCriterion("created <>", value, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedGreaterThan(Integer value) {
            addCriterion("created >", value, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedGreaterThanOrEqualTo(Integer value) {
            addCriterion("created >=", value, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedLessThan(Integer value) {
            addCriterion("created <", value, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedLessThanOrEqualTo(Integer value) {
            addCriterion("created <=", value, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedIn(List<Integer> values) {
            addCriterion("created in", values, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedNotIn(List<Integer> values) {
            addCriterion("created not in", values, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedBetween(Integer value1, Integer value2) {
            addCriterion("created between", value1, value2, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedNotBetween(Integer value1, Integer value2) {
            addCriterion("created not between", value1, value2, "created");
            return (Criteria) this;
        }

        public Criteria andUpdatedIsNull() {
            addCriterion("updated is null");
            return (Criteria) this;
        }

        public Criteria andUpdatedIsNotNull() {
            addCriterion("updated is not null");
            return (Criteria) this;
        }

        public Criteria andUpdatedEqualTo(Integer value) {
            addCriterion("updated =", value, "updated");
            return (Criteria) this;
        }

        public Criteria andUpdatedNotEqualTo(Integer value) {
            addCriterion("updated <>", value, "updated");
            return (Criteria) this;
        }

        public Criteria andUpdatedGreaterThan(Integer value) {
            addCriterion("updated >", value, "updated");
            return (Criteria) this;
        }

        public Criteria andUpdatedGreaterThanOrEqualTo(Integer value) {
            addCriterion("updated >=", value, "updated");
            return (Criteria) this;
        }

        public Criteria andUpdatedLessThan(Integer value) {
            addCriterion("updated <", value, "updated");
            return (Criteria) this;
        }

        public Criteria andUpdatedLessThanOrEqualTo(Integer value) {
            addCriterion("updated <=", value, "updated");
            return (Criteria) this;
        }

        public Criteria andUpdatedIn(List<Integer> values) {
            addCriterion("updated in", values, "updated");
            return (Criteria) this;
        }

        public Criteria andUpdatedNotIn(List<Integer> values) {
            addCriterion("updated not in", values, "updated");
            return (Criteria) this;
        }

        public Criteria andUpdatedBetween(Integer value1, Integer value2) {
            addCriterion("updated between", value1, value2, "updated");
            return (Criteria) this;
        }

        public Criteria andUpdatedNotBetween(Integer value1, Integer value2) {
            addCriterion("updated not between", value1, value2, "updated");
            return (Criteria) this;
        }

        public Criteria andTypeEqualTo(Integer value) {
            addCriterion("type =", value, "type");
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