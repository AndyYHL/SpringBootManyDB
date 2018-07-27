package com.qxst.e.meatballstartup.dao.second.read;

import com.qxst.e.meatballstartup.dao.BaseReadDao;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * cm_order / 订单表 
 */
@Repository
public interface CmOrderReadDao extends BaseReadDao<Map> {
    //继承BaseDao

}
