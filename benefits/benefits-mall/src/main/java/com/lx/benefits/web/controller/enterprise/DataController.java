package com.lx.benefits.web.controller.enterprise;

import com.alibaba.fastjson.JSONObject;
import com.lx.benefits.bean.util.Response;
import com.lx.benefits.mapper.express.entity.ExpressInfo;
import com.lx.benefits.service.express.ExpressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * 基本数据控制层
 *
 * @author gaosong
 * @date 2019/1/28.
 */
@RestController("dataController")
@RequestMapping("/enterprise/data")
public class DataController extends BaseEnterpriseController {

    @Autowired
    private ExpressService expressService;

    /**
     * 物流信息
     *
     * @return
     */
    @RequestMapping(value = "/queryExpress", method = RequestMethod.GET)
    public JSONObject queryExpress() {
        JSONObject jsonObject = new JSONObject();
        List<ExpressInfo> list = expressService.selectByExample(null);
        jsonObject.put("list", list);
        return Response.succ(jsonObject);
    }

}
