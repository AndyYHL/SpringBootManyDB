package com.qxst.e.meatballstartup.controller.api.zzlb;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.qxst.e.meatballstartup.dao.main.read.CmSystemtypeReadDao;
import com.qxst.e.meatballstartup.dao.main.write.CmSystemtypeWriteDao;
import com.qxst.e.meatballstartup.service.CmSystemtypeService;
import com.qxst.e.meatballstartup.state.ClientApiFinal;
import com.qxst.e.meatballstartup.util.OkHttpUtil;
import com.qxst.e.meatballstartup.util.json.JsonUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

@RestController
@RequestMapping(value = ClientApiFinal.version+"fat/")
@ResponseBody
public class FederationController {
    @Resource
    CmSystemtypeService cmSystemtypeService;
    /**
     * 获取财税服务的类型
     * @param jsonUtil
     * @return
     * @throws Exception
     */
    @RequestMapping(value="goodsType",method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
    public @ResponseBody JsonUtil goodsType(@RequestBody JsonUtil jsonUtil) throws Exception {
        String json = OkHttpUtil.httpGet(ClientApiFinal.zzlb+ClientApiFinal.goodsType);
        Map map = JSON.parseObject(json,Map.class);

        return cmSystemtypeService.cpGoodsType(map,jsonUtil);
    }

    /**
     * 根据类型获取产品信息
     * @param jsonUtil
     * @return 测试产品使用：279771
     * @throws Exception
     */
    @RequestMapping(value="findGoods",method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
    public @ResponseBody JsonUtil findGoods(@RequestBody JsonUtil jsonUtil) throws Exception {
        Map<String,Object> mapUtil = JSON.parseObject(JSON.toJSONString(jsonUtil),Map.class);

        return cmSystemtypeService.cpFindGoods(mapUtil,jsonUtil);
    }

    /**
     * 根据产品获取产品规格
     * @param jsonUtil
     * @return 测试产品使用：279771
     * @throws Exception
     */
    @RequestMapping(value="getProducts",method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
    public @ResponseBody JsonUtil getProducts(@RequestBody JsonUtil jsonUtil) throws Exception {
        Map<String,Object> mapUtil = JSON.parseObject(JSON.toJSONString(jsonUtil),Map.class);

        return cmSystemtypeService.cpGetProducts(mapUtil,jsonUtil);
    }
}
