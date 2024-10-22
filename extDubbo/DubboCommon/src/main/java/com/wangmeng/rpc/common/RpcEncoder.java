package com.wangmeng.rpc.common;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

//对传递的消息进行编码, 因为是请求/响应对象的传递,先编码为字节数组在发送到服务器解码
public class RpcEncoder extends MessageToByteEncoder {
    // 传递的数据的对象类型
    private Class genericClass;

    public RpcEncoder(Class genericClass) {
        this.genericClass = genericClass;
    }

    @Override
    protected void encode(ChannelHandlerContext ctx, Object msg, ByteBuf out) throws Exception {
        if(genericClass.isInstance(msg)){
            //序列化请求消息为字节数组
            byte[] bytes = SerializationUtil.serialize(msg);
            // 把数据写入到下一个通道(channel)或者是发往服务端
            out.writeBytes(bytes);
        }
    }
}
