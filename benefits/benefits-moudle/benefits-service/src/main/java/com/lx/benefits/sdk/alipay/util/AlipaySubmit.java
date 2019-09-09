package com.lx.benefits.sdk.alipay.util;

import com.lx.benefits.sdk.alipay.sign.RSA;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
public class AlipaySubmit
{

    /**
     * 支付宝提供给商户的服务接入网关URL(新)
     */
    private static final String ALIPAY_GATEWAY_NEW = "https://openapi.alipay.com/gateway.do?";
    
    /**
     * 生成签名结果
     * @param sPara 要签名的数组
     * @return 签名结果字符串
     */
    public static String buildRequestMysign(Map<String, String> sPara,  String signType, String privateKey, String inputCharset)
    {
        String prestr = AlipayCore.createLinkString1(sPara); // 把数组所有元素，按照“参数=参数值”的模式用“&”字符拼接成字符串
        String mysign = "";
        if (signType.equals("RSA"))
        {
            mysign = RSA.sign(prestr, privateKey, inputCharset);
        }
        return mysign;
    }
    
    /**
     * 生成要请求给支付宝的参数数组
     * @param sParaTemp 请求前的参数数组
     * @return 要请求的参数数组
     */
    private static Map<String, String> buildRequestPara(Map<String, String> sParaTemp, String signType, String privateKey, String inputCharset)
    {
        // 除去数组中的空值和签名参数
        Map<String, String> sPara = AlipayCore.paraFilter(sParaTemp);
        // 生成签名结果
        String mysign = buildRequestMysign(sPara, signType, privateKey, inputCharset);

        // 签名结果与签名方式加入请求提交参数组中
        sPara.put("sign", mysign);
        sPara.put("sign_type", signType);

        return sPara;
    }
    
    /**
     * 建立请求，以表单HTML形式构造（默认）
     * @param sParaTemp 请求参数数组
     * @param strMethod 提交方式。两个值可选：post、get
     * @param strButtonName 确认按钮显示文字
     * @return 提交表单HTML文本
     */
    public static String buildRequest(Map<String, String> sParaTemp, String strMethod, String strButtonName, String inputCharset, String signType, String privateKey)
    {
        // 待请求参数数组
        Map<String, String> sPara = buildRequestPara(sParaTemp, signType, privateKey, inputCharset);
        List<String> keys = new ArrayList<String>(sPara.keySet());

        StringBuffer sbHtml = new StringBuffer();

        sbHtml.append(
                "<form id=\"alipaysubmit\" name=\"alipaysubmit\" action=\"" + ALIPAY_GATEWAY_NEW + "_input_charset=" + inputCharset + "\" method=\"" + strMethod + "\">");

        for (int i = 0; i < keys.size(); i++)
        {
            String name = (String)keys.get(i);
            String value = (String)sPara.get(name);

            sbHtml.append("<input type=\"hidden\" name=\"" + name + "\" value=\"" + value + "\"/>");
        }

        // submit按钮控件请不要含有name属性
        sbHtml.append("<input type=\"submit\" value=\"" + strButtonName + "\" style=\"display:none;\"></form>");
        // sbHtml.append("<script>document.forms['alipaysubmit'].submit();</script>");

        return sbHtml.toString();
    }


}
