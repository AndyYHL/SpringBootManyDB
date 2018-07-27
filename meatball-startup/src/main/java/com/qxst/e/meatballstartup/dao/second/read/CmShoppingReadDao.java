package com.qxst.e.meatballstartup.dao.second.read;

import com.qxst.e.meatballstartup.dao.BaseReadDao;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * cm_shopping / 商品表 
 */
@Repository
public interface CmShoppingReadDao extends BaseReadDao<Map> {
    //继承BaseDao

    /**
     * 根据外键查询是否存在
     * @param map
     * @return
     */
    Map findObjectId(Map map);
}
