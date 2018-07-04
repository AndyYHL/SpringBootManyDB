package com.qxst.e.meatballstartup.service;

import com.qxst.e.meatballstartup.util.json.JsonUtil;

import java.util.Map;

/**
 * Created by Administrator on 2017-12-20.
 */
public interface UserInfoService {
    /**
     * 获取用户
     * @param map
     * @return
     */
    JsonUtil findLimit(Map map, JsonUtil jsonUtil);
}
