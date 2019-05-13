package com.wangmeng.itree.core.handle;

import com.wangmeng.itree.common.constant.RequestConstants;
import com.wangmeng.itree.core.handle.response.ResponCoreHandle;
import com.wangmeng.itree.model.CssModel;
import com.wangmeng.itree.model.JsModel;
import com.wangmeng.itree.model.PageModel;
import com.wangmeng.itree.model.PicModel;
import com.wangmeng.itree.util.CommonUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpResponseStatus;

import java.io.IOException;

import static com.wangmeng.itree.core.handle.response.ResponCoreHandle.response404Html;


/**
 * 静态文件处理器
 *
 * @author linhao
 * @date 2019/4/30
 * @Version V1.0
 */
public class StaticFileHandler {


    /**
     * 静态文件处理器
     *
     * @param object
     * @return
     * @throws IOException
     */
    public static Object responseHandle(Object object, ChannelHandlerContext ctx, FullHttpRequest fullHttpRequest) throws IOException {
        String result;
        FullHttpResponse response = null;
        //接口的404处理模块
        if (object == null) {
            result = CommonUtil.read404Html();
            return ResponCoreHandle.responseHtml(HttpResponseStatus.OK, result);

        } else if (object instanceof JsModel) {

            JsModel jsModel = (JsModel) object;
            result = CommonUtil.readFileFromResource(jsModel.getUrl());
            response = notFoundHandler(result);
            return (response == null) ? ResponCoreHandle.responseJs(HttpResponseStatus.OK, result) : response;

        } else if (object instanceof CssModel) {

            CssModel cssModel = (CssModel) object;
            result = CommonUtil.readFileFromResource(cssModel.getUrl());
            response = notFoundHandler(result);
            return (response == null) ? ResponCoreHandle.responseCss(HttpResponseStatus.OK, result) : response;

        }//初始化页面
        else if (object instanceof PageModel) {

            PageModel pageModel = (PageModel) object;
            if (pageModel.getCode() == RequestConstants.INDEX_CODE) {
                result = CommonUtil.readIndexHtml(pageModel.getPagePath());
            } else {
                result = CommonUtil.readFileFromResource(pageModel.getPagePath());
            }

            return ResponCoreHandle.responseHtml(HttpResponseStatus.OK, result);

        } else if (object instanceof PicModel) {
            PicModel picModel = (PicModel) object;
            ResponCoreHandle.writePic(picModel.getUrl(), ctx, fullHttpRequest);
            return picModel;
        }
        return null;

    }


    /**
     * 专门防止静态资源未有读取的情况
     *
     * @param result
     * @return
     */
    private static FullHttpResponse notFoundHandler(String result) throws IOException {
        if (RequestConstants.PAGE_NOT_FOUND.equals(result)) {
            return response404Html();
        }
        return null;
    }

}
