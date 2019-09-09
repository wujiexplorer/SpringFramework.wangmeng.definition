package com.lx.benefits.service.enterprfestival;


import com.alibaba.fastjson.JSONObject;
import com.lx.benefits.bean.base.dto.PageBean;
import com.lx.benefits.bean.base.dto.PageResultBean;
import com.lx.benefits.bean.dto.admin.campaign.FestivalPacketInfoBean;
import com.lx.benefits.bean.dto.admin.campaign.FestivalPacketInfoDto;
import com.lx.benefits.bean.dto.admin.campaign.FestivalPacketQueryReqDto;
import com.lx.benefits.bean.dto.admin.campaign.ImportCampainReqDto;
import com.lx.benefits.bean.entity.enterprfestivalpacket.EnterprFestivalPacket;
import com.lx.benefits.bean.entity.enterprfestivalpacket.EnterprFestivalPacketExample;
import com.lx.benefits.bean.entity.product.ProductEntity;

import java.util.List;

/**
 * @author by Ginger on 2017/8/23.
 */
public interface EnterprFestivalService {


    JSONObject insert(FestivalPacketInfoDto req);

    Long update(FestivalPacketInfoDto req);

    Long insert(EnterprFestivalPacket enterprFestival);

    JSONObject findById(Long campaignId, boolean isFront);

    JSONObject findByName(FestivalPacketQueryReqDto reqDto, boolean isFront);


    Integer update(EnterprFestivalPacket enterprFestival, EnterprFestivalPacketExample example);

    List<EnterprFestivalPacket> find(EnterprFestivalPacketExample example);


    Integer count(EnterprFestivalPacketExample example);

    JSONObject batchImport(ImportCampainReqDto req);

    JSONObject saleImport(ImportCampainReqDto req);

    List<EnterprFestivalPacket> queryByParam(EnterprFestivalPacketExample example);

    EnterprFestivalPacket getById(Long campaignId);

    List<FestivalPacketInfoBean> getPCFestivalProducts(Long campaignId, PageBean pageBean);

	boolean isExist(Long campaignId, Long spuCode);

	PageResultBean<ProductEntity> getPCFestivalTopicProducts(Long campaignId, Integer topicId, PageBean pageBean);
}
