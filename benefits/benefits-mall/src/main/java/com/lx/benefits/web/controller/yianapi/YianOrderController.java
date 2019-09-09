package com.lx.benefits.web.controller.yianapi;

import com.lx.benefits.bean.base.dto.MResultVO;
import com.lx.benefits.bean.dto.order.LogisticVO;
import com.lx.benefits.bean.dto.yianapi.YiAnOrderDetails;
import com.lx.benefits.bean.enums.MResultInfo;
import com.lx.benefits.bean.util.JsonUtil;
import com.lx.benefits.utils.RequestHelper;
import com.lx.benefits.web.ao.YiAnOrderAO;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lidongri on 2018/11/18.
 */
@Controller
@RequestMapping("/yianapi")
public class YianOrderController {


    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private YiAnOrderAO yiAnOrderAO;

    @ResponseBody
    @RequestMapping(value = "/ynorderdetails.htm", method = RequestMethod.POST)
    public String details(HttpServletRequest request) {
        try {
            String jsonStr = RequestHelper.getJsonStrByIO(request);
            YiAnOrderDetails detils = JsonUtil.parse(jsonStr, YiAnOrderDetails.class);
            if (detils == null || StringUtils.isBlank(detils.getOid())) {
                return JsonUtil.convertObjToStr(new MResultVO(MResultInfo.PARAM_ERROR));
            }
            if(detils == null || StringUtils.isBlank(detils.getCode())){
                return JsonUtil.convertObjToStr(new MResultVO("用户信息为空"));
            }
            return JsonUtil.convertObjToStr(yiAnOrderAO.details(detils));
        } catch (Exception e) {
            logger.error("M_YIAN_ORDER_ERROR", e);
            return JsonUtil.convertObjToStr(new MResultVO(MResultInfo.SYSTEM_ERROR));
        }
    }

    @ResponseBody
    @RequestMapping(value = "/track.htm", method = RequestMethod.POST)
    public String track(HttpServletRequest request) {
        try {
            String jsonStr = RequestHelper.getJsonStrByIO(request);
            YiAnOrderDetails detils = JsonUtil.parse(jsonStr, YiAnOrderDetails.class);
            if (detils == null || StringUtils.isBlank(detils.getOid())) {
                //return JsonUtil.convertObjToStr(new MResultVO(MResultInfo.PARAM_ERROR));
            }
            MResultVO<LogisticVO> res = yiAnOrderAO.track(detils);
            List<LogisticVO> voList = new ArrayList<>();
            if(res.getData()!=null){
                voList.add(res.getData());
            }

            return JsonUtil.convertObjToStr(new MResultVO<List<LogisticVO> >(MResultInfo.SUCCESS,voList));
        } catch (Exception e) {
            logger.error("M_YIAN_ORDER_ERROR", e);
            return JsonUtil.convertObjToStr(new MResultVO(MResultInfo.SYSTEM_ERROR));
        }
    }



}
