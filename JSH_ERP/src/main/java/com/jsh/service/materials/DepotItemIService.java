package com.jsh.service.materials;

import java.io.InputStream;
import java.util.List;

import net.sf.json.JSONArray;

import com.jsh.base.BaseIService;
import com.jsh.util.JshException;
import com.jsh.model.po.Asset;
import com.jsh.model.po.DepotHead;
import com.jsh.model.po.DepotItem;
import com.jsh.util.PageUtil;

public interface DepotItemIService extends BaseIService<DepotItem>
{
	void findByType(PageUtil<DepotItem> depotItem, String type,Integer ProjectId, Long MId, String MonthTime,Boolean isPrev)throws JshException;

	void findByTypeAndMaterialId(PageUtil<DepotItem> depotItem, String type, Long MId)throws JshException;

	void findDetailByTypeAndMaterialId(PageUtil<DepotItem> depotItem, Long MId)throws JshException;

	void findPriceByType(PageUtil<DepotItem> depotItem, String type,Integer ProjectId, Long MId, String MonthTime,Boolean isPrev)throws JshException;
	
	void buyOrSale(PageUtil<DepotItem> depotItem, String type, String subType, Long MId, String MonthTime, String sumType)throws JshException;

	void findGiftByType(PageUtil<DepotItem> depotItem, String subType,Integer ProjectId, Long MId, String type)throws JshException;

	/**
	 * 导出信息
	 * @return
	 */
	InputStream exmportExcel(String isAllPage,JSONArray dataArray)throws JshException;
}
