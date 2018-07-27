package com.qxst.e.meatballstartup.dao.first.read;

import com.qxst.e.meatballstartup.dao.BaseReadDao;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * ce_userinfo / 用户表扩展表 
 */
@Repository
public interface CeUserinfoReadDao extends BaseReadDao<Map> {
    //继承BaseDao

}
