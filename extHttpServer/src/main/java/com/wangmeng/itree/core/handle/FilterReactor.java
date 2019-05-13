package com.wangmeng.itree.core.handle;

import com.wangmeng.itree.common.BaseFilter;
import com.wangmeng.itree.model.ControllerRequest;
import com.wangmeng.itree.model.FilterModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @author linhao
 * @date 2019/4/30
 * @Version V1.0
 */
public class FilterReactor {

    private final static Logger LOGGER = LoggerFactory.getLogger(FilterReactor.class);

    public static List<FilterModel> FILTER_LIST=new ArrayList<>();


    public static void preHandler(ControllerRequest controllerRequest){
        for (FilterModel filterModel : FILTER_LIST) {
            BaseFilter filter= (BaseFilter) filterModel.getFilter();
            filter.beforeFilter(controllerRequest);
        }
    }


    public static void aftHandler(ControllerRequest controllerRequest){
        for (FilterModel filterModel : FILTER_LIST) {
            BaseFilter filter= (BaseFilter) filterModel.getFilter();
            filter.afterFilter(controllerRequest);
        }
    }
}
