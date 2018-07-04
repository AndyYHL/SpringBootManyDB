package com.qxst.e.meatballstartup.dao.first.read;

import com.qxst.e.meatballstartup.dao.BaseReadDao;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * Created by Administrator on 2017-10-20.
 */
@Repository
public interface UserInfoReadDao extends BaseReadDao<Map> {
    //继承BaseDao

    /**
     * 用户登录
     * @param map
     * @return
     */
    Map uLogin(Map map);

    /**
     * 获取用户的权限
     * @param map
     * @return
     */
    Map uPlevel(Map map);
}
