package com.lx.benefits.mapper.user;

import com.lx.benefits.bean.base.dto.PageBean;
import com.lx.benefits.bean.entity.product.ProductEntity;
import com.lx.benefits.bean.entity.user.UserCollection;
import com.lx.benefits.bean.entity.user.UserCollectionCondition;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserCollectionMapper {
    long countByExample(UserCollectionCondition example);

    int deleteByExample(UserCollectionCondition example);

    int deleteByPrimaryKey(Long id);

    int insert(UserCollection record);

    int insertSelective(UserCollection record);

    List<UserCollection> selectByExample(UserCollectionCondition example);

    UserCollection selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") UserCollection record, @Param("example") UserCollectionCondition example);

    int updateByExample(@Param("record") UserCollection record, @Param("example") UserCollectionCondition example);

    int updateByPrimaryKeySelective(UserCollection record);

    int updateByPrimaryKey(UserCollection record);

	int countProducts(@Param("userId")Long userId);

	List<ProductEntity> selectProducts(@Param("userId")Long userId, @Param("pageBean")PageBean pageBean);
}