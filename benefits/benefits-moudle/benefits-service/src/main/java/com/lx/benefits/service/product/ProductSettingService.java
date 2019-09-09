package com.lx.benefits.service.product;

import java.util.List;

import com.lx.benefits.bean.entity.product.ProductSetting;

public interface ProductSettingService {

	String PRODUCT_GOODS_SALERATE_SETTING = "goodsSaleRate";
	String ORDER_SUBMIT_BLACK_ENTERPR = "orderSubBlackEnter";

	String getSettingVallue(String settingKey);

	ProductSetting getSetting(String settingKey);

	int updateSettingValue(String settingKey, String settingValue);

	List<String> getOrerSubmitBlackEnterprId();

}
