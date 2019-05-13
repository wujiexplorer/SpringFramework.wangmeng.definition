package com.wangmeng.itree;

import com.wangmeng.itree.common.config.ITreeConfig;
import com.wangmeng.itree.core.NettyHttpServer;
import com.wangmeng.itree.core.handle.ControllerMapping;
import com.wangmeng.itree.core.handle.ControllerReactor;
import com.wangmeng.itree.core.handle.FilterReactor;
import com.wangmeng.itree.model.FilterModel;
import com.wangmeng.itree.util.CommonUtil;

import java.util.List;
import java.util.Map;

/**
 * @author idea
 * @data 2019/4/27
 */
public class ITreeApplication {

    public static void start(Class clazz) throws InstantiationException, IllegalAccessException {
        ITreeConfig.APPLICATION_CLASS = clazz;
        ITreeConfig.init();
        Map<String, String> map = CommonUtil.scanController(clazz.getPackage().getName());
        for (String url : map.keySet()) {
            ControllerReactor.CONTROLLER_LIST.add(new ControllerMapping(url, map.get(url)));
        }
        List<FilterModel> filterModelList = CommonUtil.scanFilter(clazz.getPackage().getName());
        FilterReactor.FILTER_LIST = filterModelList;
        NettyHttpServer server = new NettyHttpServer(ITreeConfig.PORT);
        try {
            server.init();
        } catch (Exception e) {
            System.err.println("exception: " + e.getMessage());
        }
        System.out.println("server close!");
    }

    public static void main(String[] args) throws IllegalAccessException, InstantiationException {
        ITreeApplication.start(ITreeApplication.class);
    }
}
