package com.qxst.e.meatballstartup.dao;

import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017-03-23.
 */
@Qualifier
public interface BaseReadDao<Entity> {

    /**
     * 根据ID号查询单个实体
     * @param m
     */
    Entity find(Map m);

    /**
     * 根据实体对象查询
     * @param m
     * @return
     */
    List<Entity> findEntity(Map m);

    /**
     * 由分页信息查询分页记录
     * @param m
     * @return
     */
    List<Entity> findLimit(Map m);


    /**
     * 为分页查询出记录总数
     * @param m
     * @return
     */
    Integer findCount(Map m);
}
