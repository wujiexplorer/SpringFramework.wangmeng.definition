package com.lx.benefits.service.cardkey;

import java.util.List;

import com.lx.benefits.bean.base.dto.PageBean;
import com.lx.benefits.bean.base.dto.PageResultBean;
import com.lx.benefits.bean.dto.enterpriseadmin.employee.ImportReqDto;
import com.lx.benefits.bean.entity.cardkey.CardKeyStorage;
import com.lx.benefits.bean.vo.cardkey.CardKeyVO;

public interface CardKeyService {
	/**
	 * 
	 * @Title: 卡密列表   
	 * @Description:    
	 * @param: @param sku
	 * @param: @param cardKeyVO
	 * @param: @param pageBean
	 * @param: @return      
	 * @return: PageResultBean<CardKeyStorage>      
	 * @throws
	 */
	PageResultBean<CardKeyStorage> cardKeyList(Integer sku, CardKeyVO cardKeyVO, PageBean pageBean);
	
	/**
	 * 
	 * @Title: 入库   
	 * @Description:    
	 * @param: @param sku,reqDto
	 * @param: @return      
	 * @return: Object      
	 * @throws
	 */
	Object store(Integer sku, ImportReqDto reqDto);
	
	/**
	 * 
	 * @Title: 卡密作废   
	 * @Description:    
	 * @param: @param 主键id cardKeyStorage
	 * @param: @return      
	 * @return: Object      
	 * @throws
	 */
	Object invalid(Integer id, CardKeyStorage cardKeyStorage);
	
	/**
	 * 查询商品级订单
	 * @Title: listItemOrderNumbers   
	 * @Description:    
	 * @param: @param buyerUserId
	 * @param: @return      
	 * @return: List<Long>      
	 * @throws
	 */
	List<Long> listItemOrderNumbers(Long buyerUserId, Integer startRecord, Integer pageSize);
	
	/**
	 * 查询商品级订单数量
	 * @Title: countOrderNumber   
	 * @Description:    
	 * @param: @param buyerUserId
	 * @param: @return      
	 * @return: int      
	 * @throws
	 */
	int countOrderNumber(Long buyerUserId);
	
	List<Long> getItemOrderNumbers(Long buyerUserId,List<Long> sellerOrders);
}
