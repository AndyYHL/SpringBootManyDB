package com.qxst.e.meatballstartup.service.impl;

import com.qxst.e.meatballstartup.dao.first.read.UserInfoReadDao;
import com.qxst.e.meatballstartup.service.UserInfoService;
import com.qxst.e.meatballstartup.state.FinalJson;
import com.qxst.e.meatballstartup.util.json.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Component
public class UserInfoServiceimpl implements UserInfoService {
    @Autowired
    UserInfoReadDao userInfoReadDao;

    @Override
    @Transactional(value = "firstTransactionManager") //开启事务管理
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
        //处理返回结果
        //jsonUtil.getInfo().setStatus(FinalJson.STATUS_OK);
        jsonUtil.getInfo().setStatus(HttpStatus.OK.value());
        jsonUtil.getInfo().setMessage("请求成功"+HttpStatus.OK.getReasonPhrase());
        return jsonUtil;
    }
}
