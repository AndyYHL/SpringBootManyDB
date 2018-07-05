package com.qxst.e.meatballstartup.service.impl;

import com.qxst.e.meatballstartup.dao.first.read.UserInfoReadDao;
import com.qxst.e.meatballstartup.service.UserInfoService;
import com.qxst.e.meatballstartup.state.FinalJson;
import com.qxst.e.meatballstartup.util.json.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class UserInfoServiceimpl implements UserInfoService {
    @Autowired
    UserInfoReadDao userInfoReadDao;

    @Override
    public JsonUtil findLimit(Map map, JsonUtil jsonUtil) {
        if(map == null){
            jsonUtil.getInfo().setStatus(FinalJson.STATUS_NOTACCEPTABLE);
            jsonUtil.getInfo().setMessage("请求失败");
            return jsonUtil;
        }
        List<Map> dataList = userInfoReadDao.findLimit(map);
        int count = userInfoReadDao.findCount(map);
        jsonUtil.setData(dataList);
        jsonUtil.getExtlimit().setCount(count);
        //jsonUtil.getInfo().setStatus(FinalJson.STATUS_OK);
        jsonUtil.getInfo().setStatus(HttpStatus.OK.value());
        jsonUtil.getInfo().setMessage("请求成功"+HttpStatus.OK.getReasonPhrase());
        return jsonUtil;
    }
}
