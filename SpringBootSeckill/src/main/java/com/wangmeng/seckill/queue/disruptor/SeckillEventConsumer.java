package com.wangmeng.seckill.queue.disruptor;


import com.lmax.disruptor.EventHandler;
import com.wangmeng.seckill.common.config.SpringUtil;
import com.wangmeng.seckill.service.ISeckillService;

/**
 * 消费者(秒杀处理器)
 * 创建者 科帮网
 */
public class SeckillEventConsumer implements EventHandler<SeckillEvent> {
	
	private ISeckillService seckillService = (ISeckillService) SpringUtil.getBean("seckillService");
	
	public void onEvent(SeckillEvent seckillEvent, long seq, boolean bool) throws Exception {
		seckillService.startSeckil(seckillEvent.getSeckillId(), seckillEvent.getUserId());
	}
}
