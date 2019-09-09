package com.lx.benefits.bean.sdk.jd;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class JDOrderSubmitSKU implements Serializable {

	private static final long serialVersionUID = 7933235449582853084L;

	private Long skuId;

	private Integer num;

	private Boolean bNeedAnnex;

	private Boolean bNeedGift;

	private Double price;

	private Integer selected;

	public JDOrderSubmitSKU(Long skuId, Integer num) {
		this.skuId = skuId;
		this.num = num;
	}

	public JDOrderSubmitSKU(Long skuId, Integer num, Double price, Integer selected) {
		this.skuId = skuId;
		this.num = num;
		this.price = price;
		this.selected = selected;
	}
}
