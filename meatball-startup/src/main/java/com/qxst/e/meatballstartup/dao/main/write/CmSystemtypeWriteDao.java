package com.qxst.e.meatballstartup.dao.main.write;

import com.qxst.e.meatballstartup.dao.BaseWriteDao;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * cm_systemtype / 系统类型 
 */
@Repository
public interface CmSystemtypeWriteDao extends BaseWriteDao {
    //继承BaseDao

    /**
     * 保存类型
     * @param map
     * @return
     */
    int insertTranscationType(Map map);

    /**
     * 根据Code进行数据更新
     * @param map
     * @return
     */
    int uptCode(Map map);
}
