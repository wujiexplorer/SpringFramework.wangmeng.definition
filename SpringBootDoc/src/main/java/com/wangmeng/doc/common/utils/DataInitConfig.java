package com.wangmeng.doc.common.utils;

import com.wangmeng.doc.common.constans.Constans;
import com.wangmeng.doc.model.Options;
import com.wangmeng.doc.repository.OptionsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Order(value=1)
@Component
public class DataInitConfig  implements CommandLineRunner {
	private static final Logger logger = LoggerFactory.getLogger(DataInitConfig.class);
	@Autowired
	OptionsRepository optionsRepository;

    @Override
    public void run(String... args) throws Exception {
    	logger.info("初始化加载数据");
    	List<Options> list =  optionsRepository.findAll();
    	for(Options o:list){
    		Constans.mapOptions.put(o.getOptionName(), o.getOptionValue());
    	}
    }
}
