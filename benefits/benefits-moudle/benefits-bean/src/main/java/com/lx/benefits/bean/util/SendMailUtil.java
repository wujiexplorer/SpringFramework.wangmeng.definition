package com.lx.benefits.bean.util;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Created by softw on 2019/3/8.
 */
public class SendMailUtil {

    private final static Logger logger = LoggerFactory.getLogger(SendMailUtil.class);

    private static final String ALIDM_SMTP_HOST = "smtpdm.aliyun.com";
    // 25 或"80"
    private static final String ALIDM_SMTP_PORT = "25";
    /**
     * 发邮件地址
     */
    private static String sendMailAddress  = "cs@cs.mail.lixiangshop.com";
    /**
     * 是否回信配置
     */
    private static Boolean isReply = false;
    /**
     * 回信地址
     */
    private static String replyMailAddress = "cs@seagoor.com";

    private static String sendAuthCode = "FUli147852";
    /**
     * 发件人名称
     */
    private static String sendMailUser = "福粒科技";

    private static Properties getProperties() {
        // 配置发送邮件的环境属性
        Properties props = new Properties();
        // 表示SMTP发送邮件，需要进行身份验证
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", ALIDM_SMTP_HOST);
//        props.put("mail.smtp.port", ALIDM_SMTP_PORT);
        // 如果使用ssl，则去掉使用25端口的配置，进行如下配置,
         props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
         props.put("mail.smtp.socketFactory.port", "465");
         props.put("mail.smtp.port", "465");
        // 发件人的账号，填写控制台配置的发信地址,比如xxx@xxx.com
        props.put("mail.user",sendMailAddress);
        // 访问SMTP服务时需要提供的密码(在控制台选择发信地址进行设置)
        props.put("mail.password",sendAuthCode);
        return  props;
    }

    private static  MimeMessage getMimeMessage() {
        Properties props = getProperties();
        // 构建授权信息，用于进行SMTP进行身份验证
        Authenticator authenticator = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                // 用户名、密码
                String userName = props.getProperty("mail.user");
                String password = props.getProperty("mail.password");
                PasswordAuthentication authentication = new PasswordAuthentication(userName, password);
                return authentication;
            }
        };
        // 使用环境属性和授权信息，创建邮件会话
        Session mailSession = Session.getInstance(props, authenticator);
        // 创建邮件消息
        MimeMessage message = new MimeMessage(mailSession){};
        return message;
    }

    /**
     * 单一发送邮件
     */
    public static  void  singleSendMail (String receiveMailUser,String title,String body) {
        try {
        	logger.info("发送邮件开始");
            MimeMessage message = getMimeMessage();
            // 设置发件人邮件地址和名称。填写控制台配置的发信地址,比如xxx@xxx.com。和上面的mail.user保持一致。名称用户可以自定义填写。
            InternetAddress from = new InternetAddress(sendMailAddress, MimeUtility.encodeText(sendMailUser,MimeUtility.mimeCharset("UTF-8"),null));
            message.setFrom(from);
            //可选。设置回信地址
            if (isReply) {
                Address[] a = new Address[1];
                a[0] = new InternetAddress(replyMailAddress);
                message.setReplyTo(a);
            }
            // 设置收件人邮件地址，比如yyy@yyy.com
            InternetAddress to = new InternetAddress(receiveMailUser);
            message.setRecipient(MimeMessage.RecipientType.TO, to);

            // 设置邮件标题
            message.setSubject(MimeUtility.encodeText(title,MimeUtility.mimeCharset("UTF-8"),null));
            // 设置邮件的内容体
            message.setContent(body, "text/html;charset=UTF-8");
            // 发送邮件
            Transport.send(message);
        } catch (MessagingException e) {
            String err = e.getMessage();
            // 在这里处理message内容， 格式是固定的
            System.out.println(err);
        } catch (UnsupportedEncodingException e) {
            logger.error("发送邮件异常UnsupportedEncoding{}",e.getMessage());
        } catch (Exception e) {
            logger.error("发送邮件异常{}",e.getMessage());
        }
        logger.info("发送邮件结束");
    }

    /**
     * 批量发送邮件
     */
    public static void batchSendMail(List<String> receiveMailUser, String title, String body) {
        try {
            logger.info("发送邮件开始");
            MimeMessage message = getMimeMessage();
            // 设置发件人邮件地址和名称。填写控制台配置的发信地址,比如xxx@xxx.com。和上面的mail.user保持一致。名称用户可以自定义填写。
            InternetAddress from = new InternetAddress(sendMailAddress, sendMailUser);
            message.setFrom(from);
            //可选。设置回信地址
            if (isReply) {
                Address[] a = new Address[1];
                a[0] = new InternetAddress(replyMailAddress);
                message.setReplyTo(a);
            }
            // 设置收件人邮件地址，比如yyy@yyy.com
            //如果同时发给多人，才将上面两行替换为如下（因为部分收信系统的一些限制，尽量每次投递给一个人；同时我们限制单次允许发送的人数是30人）：
            InternetAddress[] adds = new InternetAddress[receiveMailUser.size()];
            for (int i=0;i<receiveMailUser.size();i++) {
                String toAddress = receiveMailUser.get(i);
                if (!StringUtil.isEmpty(toAddress)) {
                    adds[i] = new InternetAddress(receiveMailUser.get(i));
                }
            }
            message.setRecipients(Message.RecipientType.TO, adds);
            // 设置邮件标题
            message.setSubject(title);
            // 设置邮件的内容体
            message.setContent(body, "text/html;charset=UTF-8");
            // 发送邮件
            Transport.send(message);
        } catch (MessagingException e) {
            String err = e.getMessage();
            // 在这里处理message内容， 格式是固定的
            logger.error("发送邮件异常{}",err);
        } catch (UnsupportedEncodingException e) {
            logger.error("发送邮件异常UnsupportedEncoding{}",e.getMessage());
        } catch (Exception e) {
            logger.error("发送邮件异常{}",e.getMessage());
        }
        logger.info("发送邮件结束");
    }
    public static void main(String[] args) {
        String receiveUser = "softwaregs@163.com";
        String title = "测试";
        String body ="测试邮件";
        List<String> receiveMailUser = new ArrayList<>();
        receiveMailUser.add(receiveUser);
        //singleSendMail(receiveUser,"测试邮箱提醒--节日","邮件通知验证是否能收到邮件提醒");
        batchSendMail(receiveMailUser,"测试邮箱提醒--节日","邮件通知验证是否能收到邮件提醒");
    }

}
