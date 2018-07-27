package com.qxst.e.meatballstartup.dao.second.write;

import com.qxst.e.meatballstartup.dao.BaseWriteDao;
import org.springframework.stereotype.Repository;

/**
 * cr_attributes / 商品属性和属性值。若webSite=1688，此字段必填 
 */
@Repository
public interface CrAttributesWriteDao extends BaseWriteDao {
    //继承BaseDao
}
