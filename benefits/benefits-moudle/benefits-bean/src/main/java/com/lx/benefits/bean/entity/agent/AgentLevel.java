package com.lx.benefits.bean.entity.agent;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.alibaba.fastjson.JSON;

public class AgentLevel implements Serializable {
	private Integer id;
	@NotEmpty(message = "等级名称不能为空!")
	private String name;
	@NotNull(message = "收益方式不能为空!")
	private Integer profitType;
	@NotNull(message = "返点模式方式不能为空!")
	private Integer rebateType;
	@NotNull(message = "收益比例不能为空!")
	private BigDecimal profitProportion;
	@NotNull(message = "品牌建设费不能为空！")
	private BigDecimal brandBuildingFee;
	@NotNull(message = "营销费用不能为空！")
	private BigDecimal marketFee;
	@NotNull(message = "团队建设费用不能为空！")
	private BigDecimal teamBuildingFee;
	@NotNull(message = "介绍人奖励不能为空！")
	private BigDecimal introducerAward;
	@NotNull(message = "是否代运营不能为空！")
	private Integer isProxyOperative;

	private static final long serialVersionUID = 1L;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	public Integer getProfitType() {
		return profitType;
	}

	public void setProfitType(Integer profitType) {
		this.profitType = profitType;
	}

	public Integer getRebateType() {
		return rebateType;
	}

	public void setRebateType(Integer rebateType) {
		this.rebateType = rebateType;
	}

	public BigDecimal getProfitProportion() {
		return profitProportion;
	}

	public void setProfitProportion(BigDecimal profitProportion) {
		this.profitProportion = profitProportion;
	}

	public BigDecimal getBrandBuildingFee() {
		return brandBuildingFee;
	}

	public void setBrandBuildingFee(BigDecimal brandBuildingFee) {
		this.brandBuildingFee = brandBuildingFee;
	}

	public BigDecimal getMarketFee() {
		return marketFee;
	}

	public void setMarketFee(BigDecimal marketFee) {
		this.marketFee = marketFee;
	}

	public BigDecimal getTeamBuildingFee() {
		return teamBuildingFee;
	}

	public void setTeamBuildingFee(BigDecimal teamBuildingFee) {
		this.teamBuildingFee = teamBuildingFee;
	}

	public BigDecimal getIntroducerAward() {
		return introducerAward;
	}

	public void setIntroducerAward(BigDecimal introducerAward) {
		this.introducerAward = introducerAward;
	}

	public Integer getIsProxyOperative() {
		return isProxyOperative;
	}

	public void setIsProxyOperative(Integer isProxyOperative) {
		this.isProxyOperative = isProxyOperative;
	}

	@Override
	public String toString() {
		return "AgentLevel" + JSON.toJSONString(this);
	}

}
