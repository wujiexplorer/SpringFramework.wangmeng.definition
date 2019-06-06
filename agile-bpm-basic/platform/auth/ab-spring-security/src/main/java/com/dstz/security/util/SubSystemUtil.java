package com.dstz.security.util;

import com.dstz.base.core.util.StringUtil;
import com.dstz.base.rest.util.CookieUitl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SubSystemUtil {

    /**
     * 获取上下文系统ID
     *
     * @param req
     * @return
     */
    public static String getSystemId(HttpServletRequest req) {
        String systemAlias = CookieUitl.getValueByName("system", req);
        if (StringUtil.isEmpty(systemAlias)) return "agilebpm";
        return systemAlias;
    }

    /**
     * 设置系统id。
     *
     * @param req
     * @param response
     * @param systemId
     */
    public static void setSystemId(HttpServletRequest req, HttpServletResponse response, String systemAlias) {
        CookieUitl.addCookie("system", systemAlias,CookieUitl.cookie_no_expire);
    }
}
