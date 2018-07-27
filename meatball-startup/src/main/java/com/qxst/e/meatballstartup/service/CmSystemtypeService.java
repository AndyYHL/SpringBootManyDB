package com.qxst.e.meatballstartup.service;

import com.qxst.e.meatballstartup.util.json.JsonUtil;

import java.util.Map;

public interface CmSystemtypeService {
    /**
     * 查询链接众智联邦的数据接口
     * @param map
     * @return
     */
    JsonUtil cpGoodsType(Map map, JsonUtil jsonUtil);

    /**
     * 根据类型获取产品信息
     * @param map
     * @param jsonUtil
     * @return
     */
    JsonUtil cpFindGoods(Map map, JsonUtil jsonUtil) throws Exception;

    /**
     * 根据产品ID获取产品的规格型号
     * @param map
     * @param jsonUtil
     * @return
     * @throws Exception
     */
    JsonUtil cpGetProducts(Map map,JsonUtil jsonUtil) throws Exception;
}
