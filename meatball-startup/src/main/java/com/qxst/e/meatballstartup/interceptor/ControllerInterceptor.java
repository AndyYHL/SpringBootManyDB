package com.qxst.e.meatballstartup.interceptor;

import com.qxst.e.meatballstartup.util.SessionUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ControllerInterceptor implements HandlerInterceptor {

	private Logger logger = LogManager.getLogger(getClass());
	private SessionUtil<Map> sessionUtil = new SessionUtil<>();
	//需要拦截的数据
	private static List<String> uncheckUrls = new ArrayList<>();
	//不拦截的数据
	private static List<String> uncheckUrlEnds = new ArrayList<>();

	static {
		uncheckUrls.add("/login");

		uncheckUrlEnds.add(".js");
		uncheckUrlEnds.add(".css");
		uncheckUrlEnds.add(".gif");
		uncheckUrlEnds.add(".png");
		uncheckUrlEnds.add(".woff");
		uncheckUrlEnds.add(".ttf");
		uncheckUrlEnds.add(".txt");
		uncheckUrlEnds.add("/api/gis/login/userLogin");

	}
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {
		String url = request.getRequestURI();// 得到请求URL
		url = url.replace(request.getContextPath(), "");

		System.out.println("========preHandle=========");
		request.setAttribute("startTime",System.currentTimeMillis());
		System.out.println("startTime:"+ System.currentTimeMillis());
		return true;

		/*if(url.startsWith("/resources/")) { //不拦截的资源
			return true;
		}

		if(url.startsWith("/api/gis/")) { //不拦截的API接口请求
			return true;
		}

		for(String s : uncheckUrlEnds) { //不拦截的url
			if(url.endsWith(s)) {
				return true;
			}
		}

		if(isUserLogined()) {//已经登录
			return true;
		}
		else {//没有登录
			if(uncheckUrls.contains(url)) {//允许访问登录页面资源
				return true;
			}
			else {
				sendRedirectTo(request, response, "timeout", "/login");
				return false;
			}
		}*/
	} 
	
	public static HttpSession getSession() {
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		return attr.getRequest().getSession();
	}
	
	private boolean isUserLogined() {
		//未登录
		return sessionUtil.getLoginUser() != null;
	}
	
	private void sendRedirectTo(HttpServletRequest request, HttpServletResponse response, String type, String url) {
		try {
			sessionUtil.logout();
			String httpTag = request.getHeader("Request-By");
			if(httpTag != null && httpTag.equals("AgHttp")) {
				Map<String, Object> obj = new HashMap<String, Object>();
				obj.put("success", true);
				obj.put("result", type);  
				obj.put("info","登录超时或者权限被收回！请重新登录！");
				obj.put("redirectURL", request.getContextPath() + url);
			}
			else {
				response.sendRedirect(request.getContextPath() + url);
			}
		}
		catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}
	
	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3) throws Exception {
		System.out.println("========afterCompletion=========");
		Long start = (Long) arg0.getAttribute("startTime");
		System.out.println("耗时:"+(System.currentTimeMillis() - start));
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3) throws Exception {
		System.out.println("========postHandle=========");
		Long start = (Long) arg0.getAttribute("startTime");
		System.out.println("耗时:"+(System.currentTimeMillis() - start));
	}

}
