package com.lx.benefits.mapper.card;

import com.lx.benefits.bean.entity.card.EmployeeCardCredit;
import com.lx.benefits.bean.entity.card.EmployeeCardCreditCondition;

import java.math.BigDecimal;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EmployeeCardCreditMapper {
    long countByExample(EmployeeCardCreditCondition example);

    int deleteByExample(EmployeeCardCreditCondition example);

    int deleteByPrimaryKey(Integer id);

    int insert(EmployeeCardCredit record);

    int insertSelective(EmployeeCardCredit record);

    List<EmployeeCardCredit> selectByExample(EmployeeCardCreditCondition example);

    EmployeeCardCredit selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") EmployeeCardCredit record, @Param("example") EmployeeCardCreditCondition example);

    int updateByExample(@Param("record") EmployeeCardCredit record, @Param("example") EmployeeCardCreditCondition example);

    int updateByPrimaryKeySelective(EmployeeCardCredit record);

    int updateByPrimaryKey(EmployeeCardCredit record);

	int updateCardCredit(@Param("employeeId")Integer employeeId, @Param("amount")BigDecimal amount);
	
	BigDecimal employeeStockAmount();
}