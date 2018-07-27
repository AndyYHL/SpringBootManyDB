package com.qxst.e.meatballstartup.dao.first.read;

import com.qxst.e.meatballstartup.dao.BaseReadDao;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * cr_device / 设备采集器 
 */
@Repository
public interface CrDeviceReadDao extends BaseReadDao<Map> {
    //继承BaseDao

}
