package com.qxst.e.meatballstartup.controller;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
/**
 * 全局的异常捕获
 */
public class GlobalDefaultExceptionHandler {
    /**
     * 处理 Exception 类型的异常
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Map<String,Object> exceptionHandler(HttpServletRequest request, HttpServletResponse response, Exception e) {
        if (AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class)!=null) {
            try {
                throw e;
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("url", request.getRequestURL());
        map.put("class",e.getClass());
        map.put("code", 500);
        map.put("cause",e.getCause());
        map.put("msg", e.getMessage());
        return map;
    }
}
