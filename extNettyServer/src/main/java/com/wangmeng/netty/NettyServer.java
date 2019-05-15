package com.wangmeng.netty;

/**
 * User:wangmeng
 * Date:2019/5/15
 * Time:9:50
 * Verision:2.x
 * Description:TODO
 **/

import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.*;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;
import org.jboss.netty.handler.codec.string.StringDecoder;
import org.jboss.netty.handler.codec.string.StringEncoder;

import java.net.InetSocketAddress;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.Executors;

    /**
     * 建立netty服务端，用来接受客户端发送的数据
     * @author 小京
     *
     */
    public class NettyServer extends Thread {
        public static void main(String[] args) {

            ServerBootstrap bootstrap = new ServerBootstrap(new NioServerSocketChannelFactory(Executors.newCachedThreadPool(), Executors.newCachedThreadPool()));

            bootstrap.setPipelineFactory(new ChannelPipelineFactory() {
                public ChannelPipeline getPipeline() throws Exception {
                    return Channels.pipeline(new StringDecoder(), new StringEncoder(), new ServerHandler());
                }
            });

            Channel bind = bootstrap.bind(new InetSocketAddress(8080));
            System.out.println("服务器已经启动，监听端口: " + bind.getLocalAddress() + "， 等待客户端注册。。。");
            //Test02 t = new Test02();
            //t.start();

        }

        private static class ServerHandler extends SimpleChannelHandler {
            @Override
            public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {

                if (e.getMessage() instanceof String) {
                    String message = (String) e.getMessage();
                    System.out.println("客户端:"+e.getChannel().getRemoteAddress()+"  "+"发来:" + message);
                    System.out.println(message);
                    // 报文解析入库方法
                    //JX t = new JX();
                    //t.add(message);
                    System.out.println("插入报文成功！！！");
                    //返回客户端消息，需要根据sl651规约决定。
//                    HF hf = new HF();
//                    String str = hf.funt(message);
//                    System.out.println(str);
//                    e.getChannel().write(str);
                    System.out.println("\n等待客户端输入。。。");

                }

                if (e.getMessage() instanceof String) {
                    String message =e.getRemoteAddress().toString();
                }
                super.messageReceived(ctx, e);
            }

            @Override
            public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) throws Exception {
                super.exceptionCaught(ctx, e);
            }

            @Override
            public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
                System.out.println("有一个客户端注册上来了。。。");
                System.out.println("客户端IP与端口:" + e.getChannel().getRemoteAddress());
                System.out.println("服务器IP与端口:" + e.getChannel().getLocalAddress());
                System.out.println("\n等待客户端输入。。。");
                super.channelConnected(ctx, e);
            }
        }
    }
