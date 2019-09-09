package com.lx.benefits.web.ao;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lx.benefits.bean.base.dto.MResultVO;
import com.lx.benefits.bean.base.dto.ResultInfo;
import com.lx.benefits.bean.constants.DAOConstant;
import com.lx.benefits.bean.dto.jd.exception.ServiceException;
import com.lx.benefits.bean.dto.mem.ClientUnionCode;
import com.lx.benefits.bean.dto.yianapi.PageInfo;
import com.lx.benefits.bean.dto.yianapi.client.*;
import com.lx.benefits.bean.entity.ent.ClientInfo;
import com.lx.benefits.bean.entity.memunioninfo.MemUnionInfo;
import com.lx.benefits.bean.enums.MResultInfo;
import com.lx.benefits.bean.enums.MemberUnionType;
import com.lx.benefits.bean.util.BeanUtil;
import com.lx.benefits.bean.util.JsonUtil;
import com.lx.benefits.service.yianapi.ClientInfoService;
import com.lx.benefits.service.yianapi.ClientUnionCodeService;
import com.lx.benefits.service.yianapi.UnionInfoService;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.formula.functions.T;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;

/**
 * Created by lidongri on 2018/12/1.
 */

@Service
public class ClientAO {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private ClientInfoService clientInfoService;

    @Resource
    private UnionInfoService unionInfoService;

    @Resource
    private ClientUnionCodeService clientUnionCodeService;



    public ClientResult<ClientTokenResp> token(ClientTokenReq req) {
        try {
            logger.info("M_CLIENT_TOKEN_REQ_PARAM:" + JsonUtil.toString(req));

            ClientInfo clientInfo = new ClientInfo();
            clientInfo.setClientId(req.getClient_id());
            clientInfo.setClientSecret(req.getClient_secret());

            ResultInfo<ClientInfo> resultInfo = clientInfoService.getToken(clientInfo);
            if (!resultInfo.isSuccess()) {
                return new ClientResult("access_token", resultInfo.getMsg().getMessage(), false);
            }
            ClientTokenResp resp = new ClientTokenResp();
            resp.setAccess_token(resultInfo.getData().getToken());
            resp.setExpires_in(resultInfo.getData().getExpiresIn().toString());

            return new ClientResult<>("access_token", "get access token success", true, resp);
        } catch (ServiceException se) {
            logger.error("M_CLIENT_TOKEN_ERROR", se);
            return new ClientResult<>("access_token", "get access token fail", false);
        } catch (Exception e) {
            logger.error("M_CLIENT_TOKEN_ERROR", e);
            return new ClientResult<>("access_token", "system error", false);
        }
    }

    /**
     * 根据跳转code第三方平台获取用户信息
     *
     * @param
     * @return
     */
    public ClientResult<MemberInfoResp> memberInfo(MemberInfoReq req) {
        try {

            logger.info("M_CLIENT_MEMBER_INFO_PARAM:" + JsonUtil.toString(req));

            ClientUnionCode codeQ = new ClientUnionCode();
            codeQ.setCode(req.getCode());

            long cur = System.currentTimeMillis();
            ClientUnionCode code = clientUnionCodeService.queryByClientUnionCode(codeQ);
            if (code == null) {
                return new ClientResult<>("get_user_info", "code error", false);
            }
            if (code.getExpires() < cur) {
                return new ClientResult<>("get_user_info", "code expired", false);
            }

            ClientInfo clientInfoQ = new ClientInfo();
            clientInfoQ.setToken(req.getToken());
            ClientInfo clientInfo = clientInfoService.queryByCientInfo(clientInfoQ);

            if (clientInfo == null) {
                return new ClientResult<>("get_user_info", "token error", false);
            }
            if (clientInfo.getExpires() < cur) {
                return new ClientResult<>("get_user_info", "token expired", false);
            }

            MemUnionInfo unionInfo = unionInfoService.getUnionInfo(code.getMemberId(), MemberUnionType.YIAN);
            if (unionInfo == null) {
                return new ClientResult<>("get_user_info", "user info not exist", false);
            }

            String res = unionInfo.getUnion_data();

            MemberInfoResp resp = JsonUtil.parseObject(res, MemberInfoResp.class);

            return new ClientResult<>("get_user_info", "get user info success", true, resp);
        } catch (ServiceException se) {
            logger.error("M_CLIENT_MEMBER_INFO_ERROR", se);
            return new ClientResult<>("get_user_info", "get user info fail", false);
        } catch (Exception e) {
            logger.error("M_CLIENT_MEMBER_INFO_ERROR", e);
            return new ClientResult<>("get_user_info", "system error", false);
        }
    }

    /**
     * 根据怡安用户ID 第三方平台获取用户信息
     *
     * @param
     * @return
     */
    public ClientResult<MemberInfoResp> memberInfoById(MemberInfoReq req) {
        try {

            logger.info("M_CLIENT_MEMBER_INFO_BY_ID_PARAM:" + JsonUtil.toString(req));
            long cur = System.currentTimeMillis();

            ClientInfo clientInfoQ = new ClientInfo();
            clientInfoQ.setToken(req.getToken());
            ClientInfo clientInfo = clientInfoService.queryByCientInfo(clientInfoQ);

            if (clientInfo == null) {
                return new ClientResult<>("get_user_info_by_id", "token error", false);
            }
            if (clientInfo.getExpires() < cur) {
                return new ClientResult<>("get_user_info_by_id", "token expired", false);
            }
            MemUnionInfo unionInfoQuery = new MemUnionInfo();
            unionInfoQuery.setUnion_user_id(req.getUserId().toString());
            unionInfoQuery.setType(MemberUnionType.YIAN.code);
            MemUnionInfo unionInfo = unionInfoService.queryByUnionInfo(unionInfoQuery);
            if (unionInfo == null) {
                return new ClientResult<>("get_user_info_by_id", "user info not exist", false);
            }

            String res = unionInfo.getUnion_data();


            MemberInfoResp resp = JsonUtil.parseObject(res, MemberInfoResp.class);

            return new ClientResult<>("get_user_info_by_id", "get user info success", true, resp);
        } catch (ServiceException se) {
            logger.error("M_CLIENT_MEMBER_INFO_ERROR", se);
            return new ClientResult<>("get_user_info_by_id", "get user info fail", false);
        } catch (Exception e) {
            logger.error("M_CLIENT_MEMBER_INFO_ERROR", e);
            return new ClientResult<>("get_user_info_by_id", "system error", false);
        }
    }

    /**
     * 第三方平台获取用户信息 分页
     *
     * @param
     * @return
     */
    public ClientResult<ClientPageResp<List<MemberInfoResp>>> memberInfoList(MemberInfoReq req) {
        try {

            logger.info("M_CLIENT_MEMBER_INFO_LIST_PARAM:" + JsonUtil.toString(req));

            long cur = System.currentTimeMillis();
            ClientInfo clientInfoQ = new ClientInfo();
            clientInfoQ.setToken(req.getToken());
            ClientInfo clientInfo = clientInfoService.queryByCientInfo(clientInfoQ);

            if (clientInfo == null) {
                return new ClientResult<>("user_info_list", "token error", false);
            }
            if (clientInfo.getExpires() < cur) {
                return new ClientResult<>("user_info_list", "token expired", false);
            }


            MemUnionInfo unionInfoQ = new MemUnionInfo();
            unionInfoQ.setType(MemberUnionType.YIAN.code);

            Integer page = req.getPage();
            Integer size = req.getSize();

            if (page == null || page < 1) page = 1;
            if (size == null || size < 1 || size > 100) size = 100;

            List<String> orgCodes = new ArrayList<>();
            if(clientInfo.getOrgCode()!=null){
                for(String s: clientInfo.getOrgCode().split(",")){
                    if(StringUtils.isBlank(s)){
                        continue;
                    }
                    orgCodes.add(s.trim());
                }
            }

            if(CollectionUtils.isEmpty(orgCodes)){
                logger.error("M_CLIENT_MEMBER_LIST_ERROR:CLIENT_INFO:ORG_CODE_IS_BLANK" );
                return new ClientResult<>("user_info_list", "get user info list failed org code is blank", false);
            }

            Map<String, Object> param = BeanUtil.beanMap(unionInfoQ);
            List<DAOConstant.WHERE_ENTRY> whereList = new ArrayList<DAOConstant.WHERE_ENTRY>();
            whereList.add(new DAOConstant.WHERE_ENTRY("org_code", DAOConstant.MYBATIS_SPECIAL_STRING.INLIST, orgCodes.stream().map(e-> "'"+e+"'").collect(Collectors.toList())));
            param.put(DAOConstant.MYBATIS_SPECIAL_STRING.WHERE.name(), whereList);
            param.put(DAOConstant.MYBATIS_SPECIAL_STRING.ORDER_BY.name(), " id desc");

            ResultInfo<PageInfo<T>> resultInfo = unionInfoService.queryageByParam(param, new PageInfo<>(page, size));

            if (!resultInfo.isSuccess()) {
                logger.error("M_CLIENT_MEMBER_LIST_ERROR:" + JsonUtil.toString(resultInfo));
                return new ClientResult<>("user_info_list", "get user info list failed", false);
            }
            PageInfo<T> p = resultInfo.getData();

            if (p == null) {
                return new ClientResult<>("user_info_list", "get user info list success", true, new ClientPageResp<>());
            }

            ClientPageResp<List<MemberInfoResp>> resp = new ClientPageResp<>();

            resp.setTotal_page(p.getTotal());
            resp.setTotal_row(p.getRecords());
            resp.setSize(size);
            resp.setPage(page);

            if (CollectionUtils.isEmpty(p.getRows())) {
                return new ClientResult<>("user_info_list", "get user info list success", true, resp);
            }

            List<MemberInfoResp> memberInfoResps = new ArrayList<>();
            for (Object u : p.getRows()) {
                MemUnionInfo unionInfo = (MemUnionInfo)u;
                String res = unionInfo.getUnion_data();
                MemberInfoResp memberInfoResp = JsonUtil.parseObject(res, MemberInfoResp.class);
                memberInfoResps.add(memberInfoResp);
            }
            resp.setRows(memberInfoResps);

            return new ClientResult<>("user_info_list", "get user info list success", true, resp);
        } catch (ServiceException se) {
            logger.error("M_CLIENT_MEMBER_INFO_ERROR", se);
            return new ClientResult<>("user_info_list", "get user info list fail", false);
        } catch (Exception e) {
            logger.error("M_CLIENT_MEMBER_INFO_ERROR", e);
            return new ClientResult<>("user_info_list", "get user info list fail system error", false);
        }
    }


    public MResultVO<ClientCodeResp> code(ClientCodeReq req) {
        try {
            ResultInfo<ClientUnionCode> resultInfo = clientUnionCodeService.code(req.getUserid());
            if (!resultInfo.isSuccess()) {
                return new MResultVO(resultInfo.getMsg().getMessage());
            }
            if (resultInfo.getData() == null) return new MResultVO<>(MResultInfo.SUCCESS);

            MemUnionInfo unionInfoQuery = new MemUnionInfo();
            unionInfoQuery.setMem_id(req.getUserid());
            unionInfoQuery.setType(MemberUnionType.YIAN.code);

            MemUnionInfo unionInfo = unionInfoService.queryByUnionInfo(unionInfoQuery);

            if (unionInfo == null) return new MResultVO<ClientCodeResp>(MResultInfo.SUCCESS);

            String orgCode = unionInfo.getOrg_code();

            ClientInfo clientInfoQuery = new ClientInfo();
            clientInfoQuery.setOrgCode(orgCode);

            Map<String, Object> params = new HashMap<>();
            params.put(DAOConstant.MYBATIS_SPECIAL_STRING.LIKE.name(), "org_code like concat('%','" + orgCode + "','%') ");

            ClientInfo clientInfo = null;
            List<ClientInfo> clientInfos = clientInfoService.queryByParam(params);
            if (CollectionUtils.isEmpty(clientInfos)) {
                logger.error("M_CLIENT_CODE_ERROR_CLIENTINFO_IS_BLANK:" + String.valueOf(req.getClientname()));
                if (resultInfo.getData() == null) return new MResultVO<>(MResultInfo.SUCCESS);
            }

            boolean breaks = false;
            for (ClientInfo c : clientInfos) {
                if (StringUtils.isBlank(c.getOrgCode()))
                    continue;
                String[] sepOrgCodes = c.getOrgCode().split(",");
                for (String org : sepOrgCodes) {
                    if (orgCode.equalsIgnoreCase(org.trim()) ) {
                         clientInfo = c;
                         breaks = true;
                         break;
                    }
                }
                if(breaks) break;;
            }
            if (clientInfo == null) {
                logger.error("M_CLIENT_CODE_ERROR_CLIENT_INFO_IS_BLANK:" + String.valueOf(req.getClientname()));
                 return new MResultVO<>(MResultInfo.SUCCESS);

            }


            ClientCodeResp resp = new ClientCodeResp();
            resp.setCode(resultInfo.getData().getCode());
            resp.setUrl(clientInfo.getUrl());
            String url = clientInfo.getUrl();
            if (url != null && url.contains("?")) {
                String left = url.substring(0, url.indexOf("?") + 1);
                String right = url.substring(url.indexOf("?") + 1);
                String full = left + "code=" + resp.getCode();
                if (right.startsWith("&")) {
                    full = full + right;
                } else {
                    full = full + "&" + right;
                }
                resp.setFullurl(full);

            } else {
                resp.setFullurl(clientInfo.getUrl() + "?code=" + resp.getCode());
            }
            return new MResultVO<>(MResultInfo.SUCCESS, resp);
        } catch (ServiceException se) {
            logger.error("M_CLIENT_GET_CODE_ERROR", se);
            return new MResultVO<>(se.getMessage());
        } catch (Exception e) {
            logger.error("M_CLIENT_GET_CODE_ERROR", e);
            return new MResultVO<>(MResultInfo.SYSTEM_ERROR);
        }
    }

    public MResultVO<Boolean> getUnionInfo(ClientCodeReq req) {
        try {

            ResultInfo<MemUnionInfo> resultInfo = clientUnionCodeService.getUnoinInfo(req.getUserid());
            if (!resultInfo.isSuccess()) {
                return new MResultVO(resultInfo.getMsg().getMessage());
            }

            if (resultInfo.getData() != null) new MResultVO<>(MResultInfo.SUCCESS, false);

            String orgCode = resultInfo.getData().getOrg_code();

            ClientInfo clientInfoQuery = new ClientInfo();
            clientInfoQuery.setOrgCode(orgCode);
            Integer count = clientInfoService.queryByCount(clientInfoQuery);
            if (count != null && count > 0) {
                return new MResultVO<>(MResultInfo.SUCCESS, true);
            }
            return new MResultVO<>(MResultInfo.SUCCESS, false);
        } catch (ServiceException se) {
            logger.error("M_CLIENT_GET_UNION_INFO_ERROR", se);
            return new MResultVO<>(se.getMessage());
        } catch (Exception e) {
            logger.error("M_CLIENT_GET_UNION_INFO_ERROR", e);
            return new MResultVO<>(MResultInfo.SYSTEM_ERROR);
        }
    }

    /**
     * 根据eeNo+orgCode 或者 eeNo+nationalId 第三方平台获取用户信息
     *
     * @param
     * @return
     */
    public ClientResult<MemberInfoResp> memberInfoByEeNoOrgCodeOrNationalId(MemberInfoReq req) {
        try {

            logger.info("M_CLIENT_MEMBER_INFO_MUL_PARAM:" + JsonUtil.toString(req));

            ClientInfo clientInfoQ = new ClientInfo();
            clientInfoQ.setToken(req.getToken());
            ClientInfo clientInfo = clientInfoService.queryByCientInfo(clientInfoQ);

            if (clientInfo == null) {
                return new ClientResult<>("get_user_info_mul", "token error", false);
            }
            long cur = System.currentTimeMillis();
            if (clientInfo.getExpires() < cur) {
                return new ClientResult<>("get_user_info_mul", "token expired", false);
            }

            Map<String, Object> params = new HashMap<String, Object>();
            if (StringUtils.isNotBlank(req.getNationalId())) {
                params.put(DAOConstant.MYBATIS_SPECIAL_STRING.LIKE.name(),
                        " union_data like concat ('%','" + req.getNationalId() + "','%')");
            }
            params.put("eeNo", req.getEeNo());
            if (StringUtils.isNotBlank(req.getOrgCode())) {
                params.put("orgCode", req.getOrgCode());
            }

            MemUnionInfo unionInfo = unionInfoService.queryByParams(params);
            if (unionInfo == null) {
                return new ClientResult<>("get_user_info_mul", "user info not exist", false);
            }
            String res = unionInfo.getUnion_data();
            MemberInfoResp resp = JsonUtil.parseObject(res, MemberInfoResp.class);

            return new ClientResult<>("get_user_info_mul", "get user info mul success", true, resp);
        } catch (ServiceException se) {
            logger.error("M_CLIENT_MEMBER_INFO_ERROR", se);
            return new ClientResult<>("get_user_info_mul", "get user info mul fail", false);
        } catch (Exception e) {
            logger.error("M_CLIENT_MEMBER_INFO_ERROR", e);
            return new ClientResult<>("get_user_info_mul", "system error", false);
        }
    }
}
