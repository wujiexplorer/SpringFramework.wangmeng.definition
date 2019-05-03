package com.wangmeng.seckill.repository;



import com.wangmeng.seckill.common.entity.Seckill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeckillRepository extends JpaRepository<Seckill, Long> {
	
	
}
