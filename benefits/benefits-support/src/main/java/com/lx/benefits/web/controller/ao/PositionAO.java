package com.lx.benefits.web.controller.ao;

import com.lx.benefits.bean.base.dto.MResultVO;
import com.lx.benefits.bean.constants.Constant;
import com.lx.benefits.bean.dto.position.CityTO;
import com.lx.benefits.bean.dto.position.ProvinceTO;
import com.lx.benefits.bean.dto.position.ProvincesVO;
import com.lx.benefits.bean.entity.basedistrictinfo.DistrictInfo;
import com.lx.benefits.bean.enums.MResultInfo;
import com.lx.benefits.service.basedistrictinfo.DistricitInfoService;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 地理位置
 * @author zhuss
 */
@Service
public class PositionAO {

	private static Logger log = LoggerFactory.getLogger(PositionAO.class);
	
	@Resource
	private DistricitInfoService districitInfoService;
	/**
	 * 首页 - 获取省份列表
	 * @return
	 */
	public MResultVO<List<ProvincesVO>> getProvList(){
		try{
			Map<String,Object> params = new HashMap<String,Object>();
			params.put("type", 3);
			params.put("isDelete", Constant.DELECTED.NO);
			List<DistrictInfo> districtInfoList = districitInfoService.queryByParam(params);
			List<ProvincesVO> pvlist = new ArrayList<>();
			if(CollectionUtils.isNotEmpty(districtInfoList)){
				for(DistrictInfo districtInfo:districtInfoList){
					List<DistrictInfo> children = districitInfoService.queryChildren(districtInfo.getId());
					List<ProvinceTO> childrenList = new ArrayList<>();
					if(CollectionUtils.isNotEmpty(children)){
						for(DistrictInfo child:children){
							childrenList.add(new ProvinceTO(child.getName(),child.getId().toString()));
						}
					}
					pvlist.add(new ProvincesVO(districtInfo.getName(),districtInfo.getId().toString(),childrenList));
				}
			}
			return new MResultVO<>(MResultInfo.SUCCESS,pvlist);
		}catch(Exception e){
			log.error("[API接口 - 获取省份列表 Exception] = {}", e);
			return new MResultVO<>(MResultInfo.CONN_ERROR);
		}
	} 
	
	/**
	 * 获取城市列表
	 * @param
	 * @return
	 */
	public MResultVO<List<CityTO>> getCityList(Long id){
		try{
			List<DistrictInfo> districtInfoList = districitInfoService.queryChildren(id);
			List<CityTO> cylist = new ArrayList<>();
			if(CollectionUtils.isNotEmpty(districtInfoList)){
				for(DistrictInfo districtInfo:districtInfoList){
					cylist.add(new CityTO(districtInfo.getName(),districtInfo.getId().toString()));
				}
			}
			return new MResultVO<>(MResultInfo.SUCCESS,cylist);
		}catch(Exception e){
			log.error("[API接口 - 获取城市列表 Exception] = {}",e);
			return new MResultVO<>(MResultInfo.CONN_ERROR);
		}
	} 
}
