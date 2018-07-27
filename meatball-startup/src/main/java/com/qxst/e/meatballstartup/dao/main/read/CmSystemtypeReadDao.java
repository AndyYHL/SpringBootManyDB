package com.qxst.e.meatballstartup.dao.main.read;

import com.qxst.e.meatballstartup.dao.BaseReadDao;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * cm_systemtype / 系统类型 
 */
@Repository
public interface CmSystemtypeReadDao extends BaseReadDao<Map> {
    //继承BaseDao
    /**
     * 根据类型编码查询类型
     * @param map
     * @return
     */
    Map findCode(Map map);
}
