package com.lx.benefits.bean.helper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.Properties;

/**
 * 自动读取配置文件
 * @author zhuss
 * @2016年1月4日 下午7:43:31
 */
@Service
public class PropertiesHelper {
	
	public static final String WX_ERROR_CODE = "authdeny";
	public static final String WX_APPID = "APPID";
	public static final String WX_SECRET = "SECRET";
	public static final String ACCESS_TOKEN = "ACCESS_TOKEN";

	@Value("${share_product_url}")
    public String shareProductUrl; 
	
	@Value("${share_topic_url}")
    public String shareTopicUrl; 
	
	@Value("${upload.tmp.path}")
	public String uploadTmpPath;
	
	@Value("${share_erweima_url}")
	public String shareErweimaUrl;
	
	@Value("${KG_TAXRATE}")
	public String isShowRate;

	/**一键关注引导页*/
	@Value("${FOLLOW_URL}")
	public String followUrl;

	/**跨境商品税费说明*/
	@Value("${SYNTHESIS_TAX_DESC}")
	public String synthesisTaxDesc="";

	/**直邮商品税费说明*/
	@Value("${POSTAL_TAX_DESC}")
	public String postalTaxDesc="";

	/**免税商品税费说明*/
	@Value("${FREE_TAX_DESC}")
	public String freeTaxDesc="";

	public final String offLineGroupbuyShareUrl="http://m.lixiangshop.com/group_detail.html?tid=TID&sku=SKU";


	
	// 利用属性文件的load的方法读取属性文件,key为文件中的字段名
	public static String readValue(String key) {
		String fileName = "/metainfo.properties";
		Properties props = new Properties();
		InputStream in = null;
		String value = "";
		try {
			in = PropertiesHelper.class.getClassLoader().getResourceAsStream(fileName);
			props.load(in);
			value = props.getProperty(key);
			in.close();
		} catch (Exception e) {
			return null;
		}
		return value;
	}
}
