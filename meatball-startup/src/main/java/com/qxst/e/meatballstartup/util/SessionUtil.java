package com.qxst.e.meatballstartup.util;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;

public class SessionUtil<Entity> {
	private static final String SessionUser = "SessionUser";
    /**
     * SessionUser  用户信息注册
     * @param entity
     */
	public void login(Entity entity) {
        getSession().setAttribute(SessionUser,entity);
	}

    /**
     * 用户信息登出
     */
	public void logout() {
		getSession().removeAttribute(SessionUser);
		getSession().invalidate();
	}

    /**
     * 返回Session中的用户信息
     * @return
     */
	public Entity getLoginUser() {
        Object sessionUser = getSession().getAttribute(SessionUser);
		if(sessionUser!=null){
			Entity entity =(Entity)sessionUser;
            return entity;
		}
		return null;
	}
	
	public static HttpSession getSession() {
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		return attr.getRequest().getSession();
	}
}
