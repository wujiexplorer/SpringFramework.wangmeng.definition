package com.wangmeng.es.area.controller;

import com.wangmeng.es.area.entity.Area;
import com.wangmeng.es.area.service.AreaService;
import com.wangmeng.es.log.entity.Pages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "area")
public class AreaController {
   
   @Autowired
   private  ElasticsearchTemplate elasticSearchTemplate;
   @Autowired
   private AreaService areaService;
   
   @GetMapping(value="index")
   public String  index() {
		 return "area/index";
   }
   @PostMapping(value="list")
   public @ResponseBody Pages<Area> list(Integer pageNumber, Integer pageSize, String searchContent) {
	  return areaService.searchAreaPage(pageNumber, pageSize, searchContent);
   }

   @PostMapping("/save")
   public void save(@RequestBody Area area){
      areaService.saveArea(area) ;
   }
}
