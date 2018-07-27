package com.qxst.e.meatballstartup.dao.first.read;

import com.qxst.e.meatballstartup.dao.BaseReadDao;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * cr_device_data / 采集数据 
 */
@Repository
public interface CrDeviceDataReadDao extends BaseReadDao<Map> {
    //继承BaseDao

}
