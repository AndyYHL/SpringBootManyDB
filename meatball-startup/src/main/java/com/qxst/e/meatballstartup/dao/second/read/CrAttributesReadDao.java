package com.qxst.e.meatballstartup.dao.second.read;

import com.qxst.e.meatballstartup.dao.BaseReadDao;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * cr_attributes / 商品属性和属性值。若webSite=1688，此字段必填 
 */
@Repository
public interface CrAttributesReadDao extends BaseReadDao<Map> {
    //继承BaseDao

}
