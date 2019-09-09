package com.lx.benefits.mapper.cardkey;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lx.benefits.bean.entity.cardkey.CardKeyStorage;
import com.lx.benefits.bean.entity.cardkey.CardKeyStorageExample;
import com.lx.benefits.bean.entity.order.Order;
import com.lx.benefits.bean.vo.cardkey.CardKeyInfo;

public interface CardKeyStorageMapper {
    long countByExample(CardKeyStorageExample example);

    int deleteByExample(CardKeyStorageExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CardKeyStorage record);

    int insertSelective(CardKeyStorage record);

    List<CardKeyStorage> selectByExample(CardKeyStorageExample example);

    CardKeyStorage selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CardKeyStorage record, @Param("example") CardKeyStorageExample example);

    int updateByExample(@Param("record") CardKeyStorage record, @Param("example") CardKeyStorageExample example);

    int updateByPrimaryKeySelective(CardKeyStorage record);

    int updateByPrimaryKey(CardKeyStorage record);
    
    int insertRecord(@Param("record") List<CardKeyStorage> record);
    
    List<CardKeyInfo> selectSkuNum(@Param("currentDate") String currentDate);
    
    int updateStatusBySkuAndNum(@Param("record") CardKeyStorage record,@Param("virtualOrders") List<Order> virtualOrders);
    
    List<Long> listItemOrderNumbers(@Param("buyerUserId") Long buyerUserId,@Param("startRecord") Integer startRecord, @Param("pageSize")Integer pageSize );
    
    int countOrderNumber(@Param("buyerUserId")Long buyerUserId);
    
    int updateBySellerOrderNumber(@Param("buyerUserId") Long buyerUserId,@Param("sellerOrderNumber") Long sellerOrderNumber);
    
    List<Long> getItemOrderNumbers(@Param("buyerUserId") Long buyerUserId,@Param("sellerOrders") List<Long> sellerOrders);
}