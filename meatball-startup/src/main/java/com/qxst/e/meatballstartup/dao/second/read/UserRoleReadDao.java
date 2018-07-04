package com.qxst.e.meatballstartup.dao.second.read;

import com.qxst.e.meatballstartup.dao.BaseReadDao;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * Created by Administrator on 2017-10-20.
 */
@Repository
public interface UserRoleReadDao extends BaseReadDao<Map> {
    //继承BaseDao
}
