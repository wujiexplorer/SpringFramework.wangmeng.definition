package com.lx.benefits.bean.dto.spec;

import java.math.BigDecimal;
import java.util.List;

import com.lx.benefits.bean.entity.seckill.Seckill;
import com.lx.benefits.bean.entity.voucher.Voucher;
import lombok.Data;

@Data
public class SkuBean {

	private Long skuId;
	private String skuCode;
	private String goodsName;// 商品名称
	private String goodsNameEn;// 商品名称
	private BigDecimal goodsPrice;// 商品价格
	private String goodsImage;// 商品图片
	private List<String> imgUrlList;// 商品轮播图
	private Integer goodsStorge;// 库存
	private Integer status;// 状态
	private String specLinkeId;// 规格信息（specValueId_specValueId）
	private Boolean flag;//是否暂停销售
	private List<Voucher> vouchers;//优惠卷列表
	private Seckill seckill;

}
