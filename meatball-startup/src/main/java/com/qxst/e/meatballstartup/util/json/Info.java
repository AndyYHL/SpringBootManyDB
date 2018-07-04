package com.qxst.e.meatballstartup.util.json;

/**
 * Created by Administrator on 2017/5/24.
 */
public class Info {
    private String type; //模块
    private String action; //请求功能
    private String appkey; //APP校验码
    private String usource; //APP用户来源
    private String token; //用户校验规则
    private String checkcode; //其它校验码
    private String timestamp; //时间戳
    private String url; //需要跳转地址或者请求来源地址等
    private Integer status; //处理的结果
    private String message; //附加信息，错误等情况下将原因返回.如果是添加，将新主键保存到此信息
    private String version; //版本信息。部分数据可能需要版本信息进行判断

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getAppkey() {
        return appkey;
    }

    public void setAppkey(String appkey) {
        this.appkey = appkey;
    }

    public String getUsource() {
        return usource;
    }

    public void setUsource(String usource) {
        this.usource = usource;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getCheckcode() {
        return checkcode;
    }

    public void setCheckcode(String checkcode) {
        this.checkcode = checkcode;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
