package com.qxst.e.meatballstartup.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.qxst.e.meatballstartup.dao.main.read.CmSystemtypeReadDao;
import com.qxst.e.meatballstartup.dao.main.write.CmSystemtypeWriteDao;
import com.qxst.e.meatballstartup.dao.second.read.CmShoppingReadDao;
import com.qxst.e.meatballstartup.dao.second.write.CmShoppingWriteDao;
import com.qxst.e.meatballstartup.service.CmSystemtypeService;
import com.qxst.e.meatballstartup.state.ClientApiFinal;
import com.qxst.e.meatballstartup.state.FinalJson;
import com.qxst.e.meatballstartup.util.OkHttpUtil;
import com.qxst.e.meatballstartup.util.json.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class CmSystemtypeServiceimpl implements CmSystemtypeService {
    //注入类型
    @Resource
    CmSystemtypeWriteDao cmSystemtypeWriteDao;
    @Resource
    CmSystemtypeReadDao cmSystemtypeReadDao;
    @Autowired
    CmShoppingWriteDao cmShoppingWriteDao;
    @Resource
    CmShoppingReadDao cmShoppingReadDao;

    @Override
    public JsonUtil cpGoodsType(Map map, JsonUtil jsonUtil) {
        JSONArray content = (JSONArray) map.get("content");
        Map imdate = Maps.newHashMap();
        List list = new ArrayList();
        if (content.size() > 0)
            for (Object o: content) {
                Map mdate = Maps.newHashMap();
                JSONObject jsonObject = (JSONObject)o;
                mdate.put("name",jsonObject.get("name"));
                mdate.put("tname","财税服务");
                mdate.put("typecode","CSFW");
                mdate.put("code",jsonObject.get("objectId").toString());
                mdate.put("picture",jsonObject.get("icon"));
                imdate.put("data",mdate);
                //判断是否存在
                Map typeMap = cmSystemtypeReadDao.findCode(mdate);
                if(typeMap !=null){
                    mdate.put("id",typeMap.get("id"));
                    cmSystemtypeWriteDao.uptCode(imdate);
                }else{
                    cmSystemtypeWriteDao.saveNotNull(imdate);
                }

                list.add(mdate);
            }

        jsonUtil.setData(list);
        //添加类型 - 批量添加类型
        //cmSystemtypeWriteDao.insertTranscationType(JSON.parseObject(JSON.toJSONString(jsonUtil),Map.class));
        return jsonUtil;
    }

    @Override
    public JsonUtil cpFindGoods(Map map, JsonUtil jsonUtil) throws Exception {
        Map mapData = (Map) jsonUtil.getData();
        Map zzlbMap = Maps.newHashMap();
        if (mapData.keySet().contains("subject"))
            zzlbMap.put("name",mapData.get("subject"));
        if (mapData.keySet().contains("sys_type_id"))
            zzlbMap.put("goodsType",mapData.get("sys_type_id"));
        if (mapData.keySet().contains("billingtype"))
            zzlbMap.put("billingType",mapData.get("billingType"));

        String json = OkHttpUtil.get(ClientApiFinal.zzlb+ClientApiFinal.findGoods,zzlbMap);

        Map map1 = JSON.parseObject(json,Map.class);
        JSONArray content = (JSONArray) map1.get("content");
        Map imdate = Maps.newHashMap();
        List list = new ArrayList();
        if (content.size() > 0)
            for (Object o: content) {
                Map mdate = Maps.newHashMap();
                JSONObject jsonObject = (JSONObject)o;
                mdate.put("subject",jsonObject.get("name")); //商品名称
                mdate.put("typename",jsonObject.get("goodsType")); //类型名称

                Map mCode = Maps.newHashMap();
                mCode.put("code",jsonObject.get("goodsTypeId"));
                Map s = cmSystemtypeReadDao.findCode(mCode);
                mdate.put("sys_type_id",s.get("id"));//类型ID

                Map mCodes = Maps.newHashMap();
                mCodes.put("code",jsonObject.get("billingType"));
                s = cmSystemtypeReadDao.findCode(mCodes);
                mdate.put("billingtype",s.get("id")); //计费类型
                mdate.put("billingtypename",s.get("name")); //计费名称
                String pricerange = jsonObject.get("price").toString();
                mdate.put("price_region",pricerange); //类型名称
                if (pricerange.indexOf("-")>0)
                {
                    String [] pricer = pricerange.split("-");
                    mdate.put("pricerange_one",pricer[0].replaceAll("￥",""));
                    mdate.put("pricerange_two",pricer[1].replaceAll("元",""));
                }
                //jsonObject.get("price"); //价格描述
                mdate.put("price_name",jsonObject.get("priceName")); //商品价格名称
                mdate.put("price_remark",jsonObject.get("priceRemark")); //商品价格备注
                mdate.put("picture",jsonObject.get("coverImg")); //商品图片
                mdate.put("buynum",jsonObject.get("buyNum")); //商品购买次数
                mdate.put("brief",jsonObject.get("outline")); //商品概述
                mdate.put("description",jsonObject.get("description")); //商品描述富文本
                mdate.put("serviceagency_id","1"); //商品描述富文本
                mdate.put("object_id",jsonObject.get("objectId"));
                mdate.put("issetmeal","1");
                imdate.put("data",mdate);
                Map shpingMap = cmShoppingReadDao.findObjectId(imdate);
                if(shpingMap!=null){
                    mdate.put("id",shpingMap.get("id"));
                    cmShoppingWriteDao.upt(imdate);
                }else{
                    cmShoppingWriteDao.saveNotNull(imdate);
                }
                list.add(mdate);
            }

        jsonUtil.setData(list);
        return jsonUtil;
    }

    @Override
    public JsonUtil cpGetProducts(Map map, JsonUtil jsonUtil) throws Exception {
        Map mapData = (Map) jsonUtil.getData();
        Map zzlbMap = Maps.newHashMap();

        Map jmap = cmShoppingReadDao.find(JSON.parseObject(JSON.toJSONString(jsonUtil),Map.class));
        if(jmap == null){
            jsonUtil.getInfo().setStatus(FinalJson.STATUS_INVALIDREQUEST);
            jsonUtil.getInfo().setMessage("产品ID不能为空");
            return jsonUtil;
        }
        zzlbMap.put("goodsId",jmap.get("object_id"));

        String json = OkHttpUtil.get(ClientApiFinal.zzlb+ClientApiFinal.getProducts,zzlbMap);
        Map map1 = JSON.parseObject(json,Map.class);
        JSONArray content = (JSONArray) map1.get("content");
        Map imdate = Maps.newHashMap();
        List list = new ArrayList();
        if (content.size() > 0)
            for (Object o: content) {
                Map mdate = Maps.newHashMap();
                JSONObject jsonObject = (JSONObject)o;
                mdate.put("shopping_id",mapData.get("id"));
                mdate.put("name",jsonObject.get("name")); //商品名称
                mdate.put("price",jsonObject.get("price")); //产品价格
                mdate.put("brief",jsonObject.get("outline")); //产品描述
                mdate.put("description",jsonObject.get("content")); //产品内容
                mdate.put("unit",jsonObject.get("specification")); //产品规格
                //mdate.put("price",jsonObject.get("companies")); //可购买该产品的公司名称
                mdate.put("skucode",jsonObject.get("objectId")); //产品id
                mdate.put("source","1");
                list.add(mdate);
            }

        jsonUtil.setData(list);
        return jsonUtil;
    }
}
