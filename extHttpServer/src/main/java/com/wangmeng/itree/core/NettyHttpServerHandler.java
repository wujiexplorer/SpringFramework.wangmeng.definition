package com.wangmeng.itree.core;

import com.alibaba.fastjson.JSON;
import com.wangmeng.itree.common.BaseController;
import com.wangmeng.itree.core.handle.StaticFileHandler;
import com.wangmeng.itree.core.handle.response.BaseResponse;
import com.wangmeng.itree.core.handle.response.ResponCoreHandle;
import com.wangmeng.itree.core.invoke.ControllerCglib;
import com.wangmeng.itree.model.ControllerRequest;
import com.wangmeng.itree.model.PicModel;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.HttpResponseStatus;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import static com.wangmeng.itree.core.ParameterHandler.getHeaderData;
import static com.wangmeng.itree.core.handle.ControllerReactor.getClazzFromList;
import static com.wangmeng.itree.core.handle.FilterReactor.aftHandler;
import static com.wangmeng.itree.core.handle.FilterReactor.preHandler;
import static com.wangmeng.itree.util.CommonUtil.*;

/**
 * @author idea
 * @data 2019/4/26
 */
@Slf4j
public class NettyHttpServerHandler extends SimpleChannelInboundHandler<FullHttpRequest> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, FullHttpRequest fullHttpRequest) throws Exception {
        String uri = getUri(fullHttpRequest.getUri());
        Object object = getClazzFromList(uri);
        String result = "recive msg";
        Object response = null;

        //静态文件处理
        response = StaticFileHandler.responseHandle(object, ctx, fullHttpRequest);

        if (!(response instanceof FullHttpResponse) && !(response instanceof PicModel)) {

            //接口处理
            if (isContaionInterFace(object, BaseController.class)) {
                ControllerCglib cc = new ControllerCglib();
                Object proxyObj = cc.getTarget(object);
                Method[] methodArr = null;
                Method aimMethod = null;


                if (fullHttpRequest.method().equals(HttpMethod.GET)) {
                    methodArr = proxyObj.getClass().getMethods();
                    aimMethod = getMethodByName(methodArr, "doGet");
                } else if (fullHttpRequest.method().equals(HttpMethod.POST)) {
                    methodArr = proxyObj.getClass().getMethods();
                    aimMethod = getMethodByName(methodArr, "doPost");
                }

                //代理执行method
                if (aimMethod != null) {
                    ControllerRequest controllerRequest=paramterHandler(fullHttpRequest);
                    preHandler(controllerRequest);
                    BaseResponse baseResponse = (BaseResponse) aimMethod.invoke(proxyObj, controllerRequest);
                    aftHandler(controllerRequest);
                    result = JSON.toJSONString(baseResponse);
                }
            }
            response = ResponCoreHandle.responseHtml(HttpResponseStatus.OK, result);
        }
        ctx.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);
    }


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
    }


    /**
     * 处理请求的参数内容
     *
     * @param fullHttpRequest
     * @return
     */
    private ControllerRequest paramterHandler(FullHttpRequest fullHttpRequest) {
        //参数处理部分内容
        Map<String, Object> paramMap = new HashMap<>(60);
        if (fullHttpRequest.method() == HttpMethod.GET) {
            paramMap = ParameterHandler.getGetParamsFromChannel(fullHttpRequest);
        } else if (fullHttpRequest.getMethod() == HttpMethod.POST) {
            paramMap = ParameterHandler.getPostParamsFromChannel(fullHttpRequest);
        }
        Map<String, String> headers = getHeaderData(fullHttpRequest);

        ControllerRequest ctr = new ControllerRequest();
        ctr.setParams(paramMap);
        ctr.setHeader(headers);
        return ctr;
    }


}
