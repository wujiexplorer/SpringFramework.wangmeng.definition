package com.lx.benefits.service.yianapi.impl;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.lx.benefits.bean.dto.jd.exception.ServiceException;
import com.lx.benefits.bean.dto.yianapi.AccountVO;
import com.lx.benefits.bean.dto.yianapi.cache.TokenCacheTO;
import com.lx.benefits.bean.entity.employeeuserinfo.EmployeeUserInfo;
import com.lx.benefits.bean.entity.employeeuserinfo.EmployeeUserInfoExample;
import com.lx.benefits.bean.entity.memunioninfo.MemUnionInfo;
import com.lx.benefits.bean.enums.MemberUnionType;
import com.lx.benefits.bean.enums.yianapi.enums.YiAnAPI;
import com.lx.benefits.bean.enums.yianapi.model.*;
import com.lx.benefits.bean.util.JsonUtil;
import com.lx.benefits.bean.util.Response;
import com.lx.benefits.mapper.employeeuserinfo.EmployeeUserInfoMapper;
import com.lx.benefits.service.yianapi.UnionInfoService;
import com.lx.benefits.service.yianapi.YiAnService;
import com.lx.benefits.utils.AppLoginTokenGenUtil;
import com.lx.benefits.utils.TokenCacheHelper;
import com.lx.benefits.sdk.yian.YiAnHttpUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * Created by softw on 2019/3/1.
 */
@Service
public class YiAnServiceImpl implements YiAnService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private YiAnHttpUtil yiAnHttpUtil;

    @Autowired
    private EmployeeUserInfoMapper employeeUserInfoMapper;

    @Autowired
    private TokenCacheHelper tokenCacheHelper;

    @Autowired
    private UnionInfoService unionInfoService;

    @Override
    public JSONObject login(String code) {
        JSONObject jsonObject = new JSONObject();
        try {
            YiAnResult<YiAnTokenResult> tokenResult = yiAnHttpUtil.token();

            String token = tokenResult.getResult().getAccess_token();

            YiAnUserInfoRequest request = new YiAnUserInfoRequest(code);

            //String res = "{\"result_code\":\"code_cracked\",\"result_message\":\"fetch code from cache\",\"result\":{\"national_id_type\":\"身份证\",\"org_code\":\"bdmedical\",\"root_org_code\":\"bdmedical\",\"national_id\":\"210703198507292085\",\"from_application_name\":\"BB\",\"to_application_name\":\"xg\",\"user_id\":1032543,\"name\":\"屠舒\",\"language\":\"zh\",\"mobile\":\"15101117992\",\"ee_no\":\"bd10277207\",\"next\":\"\",\"params\":{\"tofuli\":\"welcome\",\"code\":\"cdc7bc12-34af-40a4-ae89-f80f67867dca\",\"sign\":\"5f56b349854cddd76792e6de2e9acd2b\"}},\"success\":true}";
            String res = yiAnHttpUtil.handle(YiAnAPI.GET_USER_INFO, request, token);
            logger.info("YIAN-GET-USER-INFO-RES:" + String.valueOf(res));
            YiAnResult<YiAnUserInfoResult> yiAnResult = JsonUtil.parseObject(res, new TypeReference<YiAnResult<YiAnUserInfoResult>>() {
            });

            if (yiAnResult == null || !yiAnResult.isSuccess()) {
                logger.error("YIAN-GET-USER-INFO-ERROR,RESULT:" + JsonUtil.toString(yiAnResult));
            }
			YiAnUserInfoResult yiAnUser = yiAnResult.getResult();
			MemUnionInfo unionInfoQ = new MemUnionInfo();
			String ee_no = yiAnUser.getEe_no();
			String org_code = yiAnUser.getOrg_code();
			if (StringUtils.isEmpty(ee_no) || StringUtils.isEmpty(org_code)) {
				throw new ServiceException("用户信息错误");
			}
			unionInfoQ.setEe_no(ee_no);
			unionInfoQ.setOrg_code(org_code);
			unionInfoQ.setType(MemberUnionType.YIAN.code);// 5
			MemUnionInfo memUnionInfo = unionInfoService.queryByUnionInfo(unionInfoQ);
			if (memUnionInfo == null) {
				String root_org_code = yiAnUser.getRoot_org_code();
				if (!StringUtils.isEmpty(ee_no)) {
					unionInfoQ.setOrg_code(root_org_code);
					memUnionInfo = unionInfoService.queryByUnionInfo(unionInfoQ);
				}
			}

			if (memUnionInfo == null) {
				throw new ServiceException("用户不存在,请导入数据");
			}
            
            // 查询员工是否存在
            EmployeeUserInfoExample example = new EmployeeUserInfoExample();
            example.createCriteria().andEmployeeNoEqualTo(yiAnUser.getEe_no());
            EmployeeUserInfo employeeUserInfo = employeeUserInfoMapper.fetchOneByExample(example);
            if (null == employeeUserInfo) {
                throw new ServiceException("用户不存在,请导入数据");
            }
            //已有联合登录信息
            logger.info("YI-AN-LOGIN-USER-EXIST:" + JsonUtil.toString(yiAnUser));
            //更新联合登录信息
            memUnionInfo.setUnion_user_id(String.valueOf(yiAnUser.getUser_id()));
            memUnionInfo.setUnion_data(JsonUtil.toString(yiAnUser));
            memUnionInfo.setOrg_code(yiAnUser.getOrg_code());
            memUnionInfo.setRoot_org_code(yiAnUser.getRoot_org_code());
            memUnionInfo.setUnion_user_id(yiAnUser.getUser_id()== null ? null: yiAnUser.getUser_id().toString());
            unionInfoService.updateById(memUnionInfo);

            String accessToken = AppLoginTokenGenUtil.getAppLoginToken(employeeUserInfo.getEmployeeId(), employeeUserInfo.getMobile(), employeeUserInfo.getPassword());
            tokenCacheHelper.setTokenCache(accessToken, new TokenCacheTO(employeeUserInfo.getMobile(), employeeUserInfo.getLoginName(), employeeUserInfo.getEmployeeId()));
            AccountVO accountVO = new AccountVO(accessToken, employeeUserInfo.getMobile(), employeeUserInfo.getLoginName());
            accountVO.setLoginName(employeeUserInfo.getLoginName());
            jsonObject.put("data",accountVO);
            return Response.succ(jsonObject);
        } catch (Exception e) {
            logger.error("YIAN-GET-USER-INFO-ERROR,", e);
            return Response.fail("系统异常");
        }

    }
}
