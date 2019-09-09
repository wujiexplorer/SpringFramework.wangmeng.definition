package com.lx.benefits.web.util;

import com.apollo.common.exception.BusinessException;
import com.lx.benefits.bean.constants.SiteConstant;
import com.lx.benefits.bean.vo.user.UserExtVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Objects;

//import static com.lx.benefits.web.constant.SiteConstant.USER_SESSION_KEY;
//import static com.lx.benefits.web.constant.SiteConstant.VALID_CODE;


/**
 * session 工具类
 */
public class HttpSessionUtil {

	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(HttpSessionUtil.class);

	public static void setCurrentUser(UserExtVO user) {
		HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
		HttpSession session = request.getSession();
		//密码置空(脱敏处理)
		user.setPassword(null);
		session.setAttribute(SiteConstant.USER_SESSION_KEY, user);
	}

	public static UserExtVO getCurrentUser() {
		HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
		HttpSession session = request.getSession();
		Object obj = session.getAttribute(SiteConstant.USER_SESSION_KEY);
		if (obj == null) {
			session.invalidate();
			return null;
		} else {
			return (UserExtVO) obj;
		}
	}

	public static Long getCurrentUserID() {
		UserExtVO account = getCurrentUser();
		if(account == null){
			throw new BusinessException(HttpStatus.UNAUTHORIZED + "","登录信息失效,请重新登录");
		}
        return account.getId();
	}

	/**
	 * 设置登陆验证码 session
	 * @param validateCode
	 */
	public static void setValidateCode(String validateCode){
		HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
		HttpSession session = request.getSession();
		session.setAttribute(SiteConstant.VALID_CODE, validateCode);
	}

	/**
	 * 获取登陆验证码 session
	 * @return
	 */
	public static String getValidateCode(){
		HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
		HttpSession session = request.getSession();
		Object obj = session.getAttribute(SiteConstant.VALID_CODE);
		if (obj == null) {
			return null;
		} else {
			return (String) obj;
		}
	}

	/**
	 * 移除验证码相关属性 session
	 */
	public static void removeValidateCodeAttr(){
		HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
		HttpSession session = request.getSession();
		session.removeAttribute(SiteConstant.VALID_CODE);
	}

	/**
	 * 退出登录操作
	 */
	public static void logout() {
		HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
		request.getSession().invalidate();
	}


}
