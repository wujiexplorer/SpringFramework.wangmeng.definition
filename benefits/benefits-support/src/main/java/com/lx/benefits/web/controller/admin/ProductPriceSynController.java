package com.lx.benefits.web.controller.admin;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lx.benefits.bean.util.Response;
import com.lx.benefits.task.JDPriceTask;
import com.lx.benefits.task.YXPriceTask;

@RestController
@RequestMapping("/admin/product/syn")
public class ProductPriceSynController {

	@Autowired
	private YXPriceTask yxPriceTask;
	@Autowired
	private JDPriceTask jdPriceTask;
	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	@RequestMapping(value = "")
	public Object synProduatPrice() {
		return Response.fail("参数错误");
	}

	@RequestMapping(value = "", params = "supplierid=1")
	public Object synJdProduatPrice() {
		String key = "synProduatPrice_1";
		ValueOperations<String, String> opsForValue = stringRedisTemplate.opsForValue();
		String value = opsForValue.get(key);
		if (value != null) {
			return Response.fail("两次同步时间需大于30分钟");
		}
		new Thread(() -> {
			jdPriceTask.updateJDPriceAndStatus();
		}).start();
		opsForValue.set(key, new Date().toString(), 30, TimeUnit.MINUTES);
		return Response.succ("京东商品价格同步开始");
	}

	@RequestMapping(value = "", params = "supplierid=3")
	public Object synYXProduatPrice() {
		String key = "synProduatPrice_3";
		ValueOperations<String, String> opsForValue = stringRedisTemplate.opsForValue();
		String value = opsForValue.get(key);
		if (value != null) {
			return Response.fail("两次同步时间需大于30分钟");
		}
		new Thread(() -> {
			yxPriceTask.updateYXPrice();
		}).start();
		opsForValue.set(key, new Date().toString(), 30, TimeUnit.MINUTES);
		return Response.succ("严选商品价格同步开始");
	}
}
