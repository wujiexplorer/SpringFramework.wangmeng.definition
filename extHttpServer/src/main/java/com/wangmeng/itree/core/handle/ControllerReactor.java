package com.wangmeng.itree.core.handle;

import com.wangmeng.itree.common.config.ITreeConfig;
import com.wangmeng.itree.common.constant.RequestConstants;
import com.wangmeng.itree.model.CssModel;
import com.wangmeng.itree.model.JsModel;
import com.wangmeng.itree.model.PageModel;
import com.wangmeng.itree.model.PicModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * 核心反应器
 *
 * @author idea
 * @date 2019/4/26
 * @Version V1.0
 */
public class ControllerReactor {

    private final static Logger LOGGER = LoggerFactory.getLogger(ControllerReactor.class);

    public static List<ControllerMapping> CONTROLLER_LIST = new ArrayList<>();

    public static List<String> IMG_TYPE_LIST=Arrays.asList(".jpg",".png",".jpeg",".bmp",".gif");

    /**
     * 匹配响应信息
     *
     * @param uri
     * @return
     */
    public static Object getClazzFromList(String uri) {
        if (uri.equals("/") || uri.equalsIgnoreCase("/index")) {
            PageModel pageModel;
            if(ITreeConfig.INDEX_CHANGE){
                pageModel= new PageModel();
                pageModel.setPagePath(ITreeConfig.INDEX_PAGE);
            }
            return new PageModel();
        }
        if (uri.endsWith(RequestConstants.HTML_TYPE)) {
            return new PageModel(uri);
        }
        if (uri.endsWith(RequestConstants.JS_TYPE)) {
            return new JsModel(uri);
        }
        if (uri.endsWith(RequestConstants.CSS_TYPE)) {
            return new CssModel(uri);
        }
        if (isPicTypeMatch(uri)) {
            return new PicModel(uri);
        }

        //查看是否是匹配json格式
        Optional<ControllerMapping> cmOpt = CONTROLLER_LIST.stream().filter((p) -> p.getUrl().equals(uri)).findFirst();
        if (cmOpt.isPresent()) {
            String className = cmOpt.get().getClazz();
            try {
                Class clazz = Class.forName(className);
                Object object = clazz.newInstance();
                return object;
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
                LOGGER.error("[MockController] 类加载异常，{}", e);
            }
        }

        //没有匹配到html，js，css，图片资源或者接口路径
        return null;
    }



    private static boolean isPicTypeMatch(String type){
        for(String curType:IMG_TYPE_LIST){
            if(type.endsWith(curType)){
                return true;
            }
        }
        return false;
    }


}
