package com.itstyle.test;

import com.wangmeng.common.constants.PayType;
import com.wangmeng.common.constants.PayWay;
import com.wangmeng.common.model.Product;
import com.wangmeng.modules.alipay.service.IAliPayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.alipay.demo.trade.config.Configs;

@SpringBootApplication
@ComponentScan(basePackages={"com.itstyle"})
public class AliPayTest implements CommandLineRunner {
	@Autowired
	private IAliPayService aliPayService;

	public static void main(String[] args) {
		SpringApplication.run(AliPayTest.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		try {
			Configs.init("zfbinfo.properties");
			Product product = new Product();
			product.setAttach("测试");
			product.setBody("两个苹果八毛钱");
			product.setFrontUrl("https");
			product.setOutTradeNo("20170730");
			product.setPayType(PayType.ALI.getCode());
			product.setPayWay(PayWay.PC.getCode());
			product.setProductId("111111");
			product.setTotalFee("10");
			aliPayService.aliPay(product);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
