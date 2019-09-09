package com.lx.benefits.dao.enterprbannerinfo;




import com.lx.benefits.bean.entity.enterprbannerinfo.EnterprBannerInfo;
import com.lx.benefits.bean.entity.enterprbannerinfo.EnterprBannerInfoExample;

import java.util.List;

/**
 * @author by Ginger on 2017/8/23.
 */
public interface EnterprBannerInfoDao {

    Long insert(EnterprBannerInfo enterprBannerInfo);


    Integer updateByPrimaryKeySelective(EnterprBannerInfo enterprBannerInfo);

    List<EnterprBannerInfo> find(EnterprBannerInfoExample example);


    Integer count(EnterprBannerInfoExample example);


}
