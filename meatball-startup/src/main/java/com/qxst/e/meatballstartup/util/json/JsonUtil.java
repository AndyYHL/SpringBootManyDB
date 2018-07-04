package com.qxst.e.meatballstartup.util.json;


import com.qxst.e.meatballstartup.state.FinalJson;

import java.util.List;

/**
 * Created by Administrator on 2017-06-13.
 */
public class JsonUtil {
    private Object data; //请求参数
    private Info info; //公共参数
    private ExtLimit extlimit; //分页数据
    private Object other; //其他参数

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Info getInfo() {
        return info;
    }

    public void setInfo(Info info) {
        this.info = info;
    }

    public ExtLimit getExtlimit() {
        return extlimit;
    }

    public void setExtlimit(ExtLimit extlimit) {
        this.extlimit = extlimit;
    }

    public Object getOther() {
        return other;
    }

    public void setOther(Object other) {
        this.other = other;
    }


    /**
     * 设置分页返回参数
     * @param list 返回的数据
     * @param integer 总条数
     * @param jsonUtil 返回的数据
     * @return
     */
    public static JsonUtil setParameter(List list, Integer integer, JsonUtil jsonUtil){

        jsonUtil.setData(list);
        jsonUtil.getExtlimit().setCount(integer);

        jsonUtil.getInfo().setMessage("获取成功！");
        jsonUtil.getInfo().setStatus(FinalJson.STATUS_OK);
        jsonUtil.getInfo().setTimestamp(String.valueOf(System.currentTimeMillis()));

        return jsonUtil;
    }

    /**
     * 常规返回数据
     * @param message 返回说明
     * @param integer 状态
     * @param jsonUtil 参数
     * @return
     */
    public static JsonUtil setAddUptParameter(String message,Integer integer, JsonUtil jsonUtil){
        jsonUtil.getInfo().setMessage(message);
        jsonUtil.getInfo().setStatus(integer);
        jsonUtil.getInfo().setTimestamp(String.valueOf(System.currentTimeMillis()));

        return jsonUtil;
    }
}
