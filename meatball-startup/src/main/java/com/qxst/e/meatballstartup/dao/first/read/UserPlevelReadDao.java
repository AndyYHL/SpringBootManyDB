package com.qxst.e.meatballstartup.dao.first.read;

import com.qxst.e.meatballstartup.dao.BaseReadDao;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * Created by Administrator on 2017-10-20.
 */
@Repository
public interface UserPlevelReadDao extends BaseReadDao<Map> {
    //继承BaseDao
}
