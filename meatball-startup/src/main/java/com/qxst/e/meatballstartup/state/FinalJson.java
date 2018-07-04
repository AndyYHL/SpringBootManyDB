package com.qxst.e.meatballstartup.state;

/**
 * 可以通过HttpStatus.OK 获取网页报文
 * Created by Administrator on 2017-04-05.
 */
public class FinalJson {
    //请求公共值
    public static final String appKey = "CheXiShiKey";
    //JSON返回请求令牌和私密钥匙
    public static final String access_key ="WeiXinConentKey"; //微信令牌

    public static final String access_key_android ="9WEAY6UGMJXBDBLD"; //android令牌
    public static final String access_key_ios ="3684ZJWVFVOALTA9"; //ios令牌

    public static final String private_key = "WHOXLL3HCIPY4AE2WJBLRP87E70NT7XF"; //私密钥匙

    public static final String baiduMap_key = "E4d8bcb7a7b3af88911d033df79ab13f"; //百度地图key
    //JSON 返回的标识
    public static final int STATUS_OK = 200; //请求成功
    public static final int STATUS_CREATED = 201; //用户新建或修改数据成功。
    public static final int STATUS_ACCEPTED = 202; //表示一个请求已经进入后台排队
    public static final int STATUS_DELETE = 204; //用户删除数据成功。
    public static final int STATUS_INVALIDREQUEST = 400; //用户发出的请求有错误，服务器没有进行新建或修改数据的操作，该操作是幂等的;
    public static final int STATUS_UNAUTHORIZED = 401; //表示用户没有权限（令牌、用户名、密码错误）。
    public static final int STATUS_FORBIDDEN = 403; //表示用户得到授权（与401错误相对），但是访问是被禁止的。
    public static final int STATUS_NOTFOUND = 404; //用户发出的请求针对的是不存在的记录，服务器没有进行操作，该操作是幂等的。
    public static final int STATUS_NOTACCEPTABLE = 406; //用户请求的格式不可得（比如用户请求JSON格式，但是只有XML格式）。
    public static final int STATUS_GONE  = 410; //用户请求的资源被永久删除，且不会再得到的。
    public static final int STATUS_UNPROCESABLE = 422; //当创建一个对象时，发生一个验证错误。
    public static final int STATUS_SERVERERROR = 500; //服务器发生错误，用户将无法判断发出的请求是否成功。
    public static final int STATUS_CHECKCODE = 300; //校验CheckCode正确
    //网络请求的格式
    public enum HttpMediaType{
        text,json
    }

    /**
     * 页面条数
     */
    public static final int pageSize = 10;

    /**
     * 页码
     * @param pageIndex
     * @return
     */
    public static int pageIndex(int pageIndex,int pageSize){
        return (pageIndex-1)*pageSize;
    }

    public static String GD_AMAP_KEY = "";
}
