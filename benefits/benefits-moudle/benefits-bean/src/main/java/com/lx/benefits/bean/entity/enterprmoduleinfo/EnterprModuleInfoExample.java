package com.lx.benefits.bean.entity.enterprmoduleinfo;

import java.util.ArrayList;
import java.util.List;

public class EnterprModuleInfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public EnterprModuleInfoExample() {
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

        public Criteria andModuleIdIsNull() {
            addCriterion("moduleId is null");
            return (Criteria) this;
        }

        public Criteria andModuleIdIsNotNull() {
            addCriterion("moduleId is not null");
            return (Criteria) this;
        }

        public Criteria andModuleIdEqualTo(Long value) {
            addCriterion("moduleId =", value, "moduleId");
            return (Criteria) this;
        }

        public Criteria andModuleIdNotEqualTo(Long value) {
            addCriterion("moduleId <>", value, "moduleId");
            return (Criteria) this;
        }

        public Criteria andModuleIdGreaterThan(Long value) {
            addCriterion("moduleId >", value, "moduleId");
            return (Criteria) this;
        }

        public Criteria andModuleIdGreaterThanOrEqualTo(Long value) {
            addCriterion("moduleId >=", value, "moduleId");
            return (Criteria) this;
        }

        public Criteria andModuleIdLessThan(Long value) {
            addCriterion("moduleId <", value, "moduleId");
            return (Criteria) this;
        }

        public Criteria andModuleIdLessThanOrEqualTo(Long value) {
            addCriterion("moduleId <=", value, "moduleId");
            return (Criteria) this;
        }

        public Criteria andModuleIdIn(List<Long> values) {
            addCriterion("moduleId in", values, "moduleId");
            return (Criteria) this;
        }

        public Criteria andModuleIdNotIn(List<Long> values) {
            addCriterion("moduleId not in", values, "moduleId");
            return (Criteria) this;
        }

        public Criteria andModuleIdBetween(Long value1, Long value2) {
            addCriterion("moduleId between", value1, value2, "moduleId");
            return (Criteria) this;
        }

        public Criteria andModuleIdNotBetween(Long value1, Long value2) {
            addCriterion("moduleId not between", value1, value2, "moduleId");
            return (Criteria) this;
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

        public Criteria andModuleNameIsNull() {
            addCriterion("moduleName is null");
            return (Criteria) this;
        }

        public Criteria andModuleNameIsNotNull() {
            addCriterion("moduleName is not null");
            return (Criteria) this;
        }

        public Criteria andModuleNameEqualTo(String value) {
            addCriterion("moduleName =", value, "moduleName");
            return (Criteria) this;
        }

        public Criteria andModuleNameNotEqualTo(String value) {
            addCriterion("moduleName <>", value, "moduleName");
            return (Criteria) this;
        }

        public Criteria andModuleNameGreaterThan(String value) {
            addCriterion("moduleName >", value, "moduleName");
            return (Criteria) this;
        }

        public Criteria andModuleNameGreaterThanOrEqualTo(String value) {
            addCriterion("moduleName >=", value, "moduleName");
            return (Criteria) this;
        }

        public Criteria andModuleNameLessThan(String value) {
            addCriterion("moduleName <", value, "moduleName");
            return (Criteria) this;
        }

        public Criteria andModuleNameLessThanOrEqualTo(String value) {
            addCriterion("moduleName <=", value, "moduleName");
            return (Criteria) this;
        }

        public Criteria andModuleNameLike(String value) {
            addCriterion("moduleName like", value, "moduleName");
            return (Criteria) this;
        }

        public Criteria andModuleNameNotLike(String value) {
            addCriterion("moduleName not like", value, "moduleName");
            return (Criteria) this;
        }

        public Criteria andModuleNameIn(List<String> values) {
            addCriterion("moduleName in", values, "moduleName");
            return (Criteria) this;
        }

        public Criteria andModuleNameNotIn(List<String> values) {
            addCriterion("moduleName not in", values, "moduleName");
            return (Criteria) this;
        }

        public Criteria andModuleNameBetween(String value1, String value2) {
            addCriterion("moduleName between", value1, value2, "moduleName");
            return (Criteria) this;
        }

        public Criteria andModuleNameNotBetween(String value1, String value2) {
            addCriterion("moduleName not between", value1, value2, "moduleName");
            return (Criteria) this;
        }

        public Criteria andModuleLinkIsNull() {
            addCriterion("moduleLink is null");
            return (Criteria) this;
        }

        public Criteria andModuleLinkIsNotNull() {
            addCriterion("moduleLink is not null");
            return (Criteria) this;
        }

        public Criteria andModuleLinkEqualTo(String value) {
            addCriterion("moduleLink =", value, "moduleLink");
            return (Criteria) this;
        }

        public Criteria andModuleLinkNotEqualTo(String value) {
            addCriterion("moduleLink <>", value, "moduleLink");
            return (Criteria) this;
        }

        public Criteria andModuleLinkGreaterThan(String value) {
            addCriterion("moduleLink >", value, "moduleLink");
            return (Criteria) this;
        }

        public Criteria andModuleLinkGreaterThanOrEqualTo(String value) {
            addCriterion("moduleLink >=", value, "moduleLink");
            return (Criteria) this;
        }

        public Criteria andModuleLinkLessThan(String value) {
            addCriterion("moduleLink <", value, "moduleLink");
            return (Criteria) this;
        }

        public Criteria andModuleLinkLessThanOrEqualTo(String value) {
            addCriterion("moduleLink <=", value, "moduleLink");
            return (Criteria) this;
        }

        public Criteria andModuleLinkLike(String value) {
            addCriterion("moduleLink like", value, "moduleLink");
            return (Criteria) this;
        }

        public Criteria andModuleLinkNotLike(String value) {
            addCriterion("moduleLink not like", value, "moduleLink");
            return (Criteria) this;
        }

        public Criteria andModuleLinkIn(List<String> values) {
            addCriterion("moduleLink in", values, "moduleLink");
            return (Criteria) this;
        }

        public Criteria andModuleLinkNotIn(List<String> values) {
            addCriterion("moduleLink not in", values, "moduleLink");
            return (Criteria) this;
        }

        public Criteria andModuleLinkBetween(String value1, String value2) {
            addCriterion("moduleLink between", value1, value2, "moduleLink");
            return (Criteria) this;
        }

        public Criteria andModuleLinkNotBetween(String value1, String value2) {
            addCriterion("moduleLink not between", value1, value2, "moduleLink");
            return (Criteria) this;
        }

        public Criteria andModulePicIsNull() {
            addCriterion("modulePic is null");
            return (Criteria) this;
        }

        public Criteria andModulePicIsNotNull() {
            addCriterion("modulePic is not null");
            return (Criteria) this;
        }

        public Criteria andModulePicEqualTo(String value) {
            addCriterion("modulePic =", value, "modulePic");
            return (Criteria) this;
        }

        public Criteria andModulePicNotEqualTo(String value) {
            addCriterion("modulePic <>", value, "modulePic");
            return (Criteria) this;
        }

        public Criteria andModulePicGreaterThan(String value) {
            addCriterion("modulePic >", value, "modulePic");
            return (Criteria) this;
        }

        public Criteria andModulePicGreaterThanOrEqualTo(String value) {
            addCriterion("modulePic >=", value, "modulePic");
            return (Criteria) this;
        }

        public Criteria andModulePicLessThan(String value) {
            addCriterion("modulePic <", value, "modulePic");
            return (Criteria) this;
        }

        public Criteria andModulePicLessThanOrEqualTo(String value) {
            addCriterion("modulePic <=", value, "modulePic");
            return (Criteria) this;
        }

        public Criteria andModulePicLike(String value) {
            addCriterion("modulePic like", value, "modulePic");
            return (Criteria) this;
        }

        public Criteria andModulePicNotLike(String value) {
            addCriterion("modulePic not like", value, "modulePic");
            return (Criteria) this;
        }

        public Criteria andModulePicIn(List<String> values) {
            addCriterion("modulePic in", values, "modulePic");
            return (Criteria) this;
        }

        public Criteria andModulePicNotIn(List<String> values) {
            addCriterion("modulePic not in", values, "modulePic");
            return (Criteria) this;
        }

        public Criteria andModulePicBetween(String value1, String value2) {
            addCriterion("modulePic between", value1, value2, "modulePic");
            return (Criteria) this;
        }

        public Criteria andModulePicNotBetween(String value1, String value2) {
            addCriterion("modulePic not between", value1, value2, "modulePic");
            return (Criteria) this;
        }

        public Criteria andBackgroundIsNull() {
            addCriterion("background is null");
            return (Criteria) this;
        }

        public Criteria andBackgroundIsNotNull() {
            addCriterion("background is not null");
            return (Criteria) this;
        }

        public Criteria andBackgroundEqualTo(String value) {
            addCriterion("background =", value, "background");
            return (Criteria) this;
        }

        public Criteria andBackgroundNotEqualTo(String value) {
            addCriterion("background <>", value, "background");
            return (Criteria) this;
        }

        public Criteria andBackgroundGreaterThan(String value) {
            addCriterion("background >", value, "background");
            return (Criteria) this;
        }

        public Criteria andBackgroundGreaterThanOrEqualTo(String value) {
            addCriterion("background >=", value, "background");
            return (Criteria) this;
        }

        public Criteria andBackgroundLessThan(String value) {
            addCriterion("background <", value, "background");
            return (Criteria) this;
        }

        public Criteria andBackgroundLessThanOrEqualTo(String value) {
            addCriterion("background <=", value, "background");
            return (Criteria) this;
        }

        public Criteria andBackgroundLike(String value) {
            addCriterion("background like", value, "background");
            return (Criteria) this;
        }

        public Criteria andBackgroundNotLike(String value) {
            addCriterion("background not like", value, "background");
            return (Criteria) this;
        }

        public Criteria andBackgroundIn(List<String> values) {
            addCriterion("background in", values, "background");
            return (Criteria) this;
        }

        public Criteria andBackgroundNotIn(List<String> values) {
            addCriterion("background not in", values, "background");
            return (Criteria) this;
        }

        public Criteria andBackgroundBetween(String value1, String value2) {
            addCriterion("background between", value1, value2, "background");
            return (Criteria) this;
        }

        public Criteria andBackgroundNotBetween(String value1, String value2) {
            addCriterion("background not between", value1, value2, "background");
            return (Criteria) this;
        }

        public Criteria andSelectedBackgroundIsNull() {
            addCriterion("selectedBackground is null");
            return (Criteria) this;
        }

        public Criteria andSelectedBackgroundIsNotNull() {
            addCriterion("selectedBackground is not null");
            return (Criteria) this;
        }

        public Criteria andSelectedBackgroundEqualTo(String value) {
            addCriterion("selectedBackground =", value, "selectedBackground");
            return (Criteria) this;
        }

        public Criteria andSelectedBackgroundNotEqualTo(String value) {
            addCriterion("selectedBackground <>", value, "selectedBackground");
            return (Criteria) this;
        }

        public Criteria andSelectedBackgroundGreaterThan(String value) {
            addCriterion("selectedBackground >", value, "selectedBackground");
            return (Criteria) this;
        }

        public Criteria andSelectedBackgroundGreaterThanOrEqualTo(String value) {
            addCriterion("selectedBackground >=", value, "selectedBackground");
            return (Criteria) this;
        }

        public Criteria andSelectedBackgroundLessThan(String value) {
            addCriterion("selectedBackground <", value, "selectedBackground");
            return (Criteria) this;
        }

        public Criteria andSelectedBackgroundLessThanOrEqualTo(String value) {
            addCriterion("selectedBackground <=", value, "selectedBackground");
            return (Criteria) this;
        }

        public Criteria andSelectedBackgroundLike(String value) {
            addCriterion("selectedBackground like", value, "selectedBackground");
            return (Criteria) this;
        }

        public Criteria andSelectedBackgroundNotLike(String value) {
            addCriterion("selectedBackground not like", value, "selectedBackground");
            return (Criteria) this;
        }

        public Criteria andSelectedBackgroundIn(List<String> values) {
            addCriterion("selectedBackground in", values, "selectedBackground");
            return (Criteria) this;
        }

        public Criteria andSelectedBackgroundNotIn(List<String> values) {
            addCriterion("selectedBackground not in", values, "selectedBackground");
            return (Criteria) this;
        }

        public Criteria andSelectedBackgroundBetween(String value1, String value2) {
            addCriterion("selectedBackground between", value1, value2, "selectedBackground");
            return (Criteria) this;
        }

        public Criteria andSelectedBackgroundNotBetween(String value1, String value2) {
            addCriterion("selectedBackground not between", value1, value2, "selectedBackground");
            return (Criteria) this;
        }

        public Criteria andModuleSortIsNull() {
            addCriterion("moduleSort is null");
            return (Criteria) this;
        }

        public Criteria andModuleSortIsNotNull() {
            addCriterion("moduleSort is not null");
            return (Criteria) this;
        }

        public Criteria andModuleSortEqualTo(Integer value) {
            addCriterion("moduleSort =", value, "moduleSort");
            return (Criteria) this;
        }

        public Criteria andModuleSortNotEqualTo(Integer value) {
            addCriterion("moduleSort <>", value, "moduleSort");
            return (Criteria) this;
        }

        public Criteria andModuleSortGreaterThan(Integer value) {
            addCriterion("moduleSort >", value, "moduleSort");
            return (Criteria) this;
        }

        public Criteria andModuleSortGreaterThanOrEqualTo(Integer value) {
            addCriterion("moduleSort >=", value, "moduleSort");
            return (Criteria) this;
        }

        public Criteria andModuleSortLessThan(Integer value) {
            addCriterion("moduleSort <", value, "moduleSort");
            return (Criteria) this;
        }

        public Criteria andModuleSortLessThanOrEqualTo(Integer value) {
            addCriterion("moduleSort <=", value, "moduleSort");
            return (Criteria) this;
        }

        public Criteria andModuleSortIn(List<Integer> values) {
            addCriterion("moduleSort in", values, "moduleSort");
            return (Criteria) this;
        }

        public Criteria andModuleSortNotIn(List<Integer> values) {
            addCriterion("moduleSort not in", values, "moduleSort");
            return (Criteria) this;
        }

        public Criteria andModuleSortBetween(Integer value1, Integer value2) {
            addCriterion("moduleSort between", value1, value2, "moduleSort");
            return (Criteria) this;
        }

        public Criteria andModuleSortNotBetween(Integer value1, Integer value2) {
            addCriterion("moduleSort not between", value1, value2, "moduleSort");
            return (Criteria) this;
        }

        public Criteria andStartTimeIsNull() {
            addCriterion("startTime is null");
            return (Criteria) this;
        }

        public Criteria andStartTimeIsNotNull() {
            addCriterion("startTime is not null");
            return (Criteria) this;
        }

        public Criteria andStartTimeEqualTo(Integer value) {
            addCriterion("startTime =", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeNotEqualTo(Integer value) {
            addCriterion("startTime <>", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeGreaterThan(Integer value) {
            addCriterion("startTime >", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeGreaterThanOrEqualTo(Integer value) {
            addCriterion("startTime >=", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeLessThan(Integer value) {
            addCriterion("startTime <", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeLessThanOrEqualTo(Integer value) {
            addCriterion("startTime <=", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeIn(List<Integer> values) {
            addCriterion("startTime in", values, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeNotIn(List<Integer> values) {
            addCriterion("startTime not in", values, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeBetween(Integer value1, Integer value2) {
            addCriterion("startTime between", value1, value2, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeNotBetween(Integer value1, Integer value2) {
            addCriterion("startTime not between", value1, value2, "startTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeIsNull() {
            addCriterion("endTime is null");
            return (Criteria) this;
        }

        public Criteria andEndTimeIsNotNull() {
            addCriterion("endTime is not null");
            return (Criteria) this;
        }

        public Criteria andEndTimeEqualTo(Integer value) {
            addCriterion("endTime =", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotEqualTo(Integer value) {
            addCriterion("endTime <>", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeGreaterThan(Integer value) {
            addCriterion("endTime >", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeGreaterThanOrEqualTo(Integer value) {
            addCriterion("endTime >=", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeLessThan(Integer value) {
            addCriterion("endTime <", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeLessThanOrEqualTo(Integer value) {
            addCriterion("endTime <=", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeIn(List<Integer> values) {
            addCriterion("endTime in", values, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotIn(List<Integer> values) {
            addCriterion("endTime not in", values, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeBetween(Integer value1, Integer value2) {
            addCriterion("endTime between", value1, value2, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotBetween(Integer value1, Integer value2) {
            addCriterion("endTime not between", value1, value2, "endTime");
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

        //isshow start
        public Criteria andIsShowIsNull() {
            addCriterion("isShow is null");
            return (Criteria) this;
        }

        public Criteria andIsShowIsNotNull() {
            addCriterion("isShow is not null");
            return (Criteria) this;
        }

        public Criteria andIsShowEqualTo(Integer value) {
            addCriterion("isShow =", value, "isShow");
            return (Criteria) this;
        }

        public Criteria andIsShowNotEqualTo(Integer value) {
            addCriterion("isShow <>", value, "isShow");
            return (Criteria) this;
        }

        public Criteria andIsShowGreaterThan(Integer value) {
            addCriterion("isShow >", value, "isShow");
            return (Criteria) this;
        }

        public Criteria andIsShowGreaterThanOrEqualTo(Integer value) {
            addCriterion("isShow >=", value, "isShow");
            return (Criteria) this;
        }

        public Criteria andIsShowLessThan(Integer value) {
            addCriterion("isShow <", value, "isShow");
            return (Criteria) this;
        }

        public Criteria andIsShowLessThanOrEqualTo(Integer value) {
            addCriterion("isShow <=", value, "isShow");
            return (Criteria) this;
        }

        public Criteria andIsShowIn(List<Integer> values) {
            addCriterion("isShow in", values, "isShow");
            return (Criteria) this;
        }

        public Criteria andIsShowNotIn(List<Integer> values) {
            addCriterion("isShow not in", values, "isShow");
            return (Criteria) this;
        }

        public Criteria andIsShowBetween(Integer value1, Integer value2) {
            addCriterion("isShow between", value1, value2, "isShow");
            return (Criteria) this;
        }

        public Criteria andIsShowNotBetween(Integer value1, Integer value2) {
            addCriterion("isShow not between", value1, value2, "isDeleted");
            return (Criteria) this;
        }
        //isshow end

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