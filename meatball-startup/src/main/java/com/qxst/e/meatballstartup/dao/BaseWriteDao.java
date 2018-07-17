package com.qxst.e.meatballstartup.dao;

import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017-03-23.
 */
@Qualifier
public interface BaseWriteDao {
    /**
     * 保存全部字段
     * @param m
     * @return
     */
    int save(Map m);
    /**
     * 插入
     * @param m
     */
    Integer saveNotNull(Map m);

    /**
     * 根据ID号修改单个实体
     * @param m
     */
    Integer upt(Map m);

    /**
     * 根据ID号删除单个实体
     * @param m
     */
    Integer del(Map m);

    /**
     * 批量插入
     * @param list
     * @return
     */
    Integer insertTranscationList(List list);

}
