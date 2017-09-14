package com.qkxmall.mall.nets;

/**
 * Created by Sunshine on 10/28/2015.
 */
public class API {

    //附加
    public static final String ADD = "http://www.qkxmall.com";
    //顶部广告
    public static final String ADVERTISEMENT = "http://www.qkxmall.com/?m=api&c=adv&a=lists";
    //惠购商品列表
    public static final String HUI_PRODUCTS_LIST = "http://www.qkxmall.com/?m=api&c=goods&a=lists";
    //TODO: Cart
    public static final String HUI_CART = "http://www.qkxmall.com/index.php?m=api&c=cart&a=lists";
    public static final String HUI_ADD_TO_CART = "http://www.qkxmall.com/?m=api&c=cart&a=add";
    public static final String HUI_UPDATE_CART_ITEM = "http://www.qkxmall" +
            ".com/?m=api&c=cart&a=update";
    public static final String HUI_DELETE_FROM_CART = "http://www.qkxmall" +
            ".com/?m=api&c=cart&a=delete";
    public static final String HUI_CLEAN_CART = "http://www.qkxmall.com/?m=api&c=cart&a=clear";
    //User info
    public static final String USER_INFO = "http://www.qkxmall.com/?m=api&c=member&a=detail";
    public static final String HUI_ORDER_LIST = "http://www.qkxmall.com/?m=api&c=order&a=lists";
    public static final String HUI_PRODUCTS_CATEGORY = "http://www.qkxmall" +
            ".com/?m=api&c=goods&a=category";
    public static final String HUI_PRODUCTS_DETAIL = "http://www.qkxmall" +
            ".com/?m=api&c=goods&a=detail";
    public static final String USER_ADDRESS_LIST = "http://www.qkxmall" +
            ".com/?m=api&c=address&a=lists";
    public static final String SET_DEFAULT_ADDRESS = "http://www.qkxmall" +
            ".com/?m=api&c=address&a=updateDetaultAddress";
    public static final String DELETE_ADDRESS = "http://www.qkxmall" +
            ".com/?m=api&c=address&a=deleteaddress";
    public static final String UPDATE_ADDRESS = "http://www.qkxmall" +
            ".com/?m=api&c=address&a=updateaddress";
    public static final String GET_REGION = "http://www.qkxmall.com/?m=api&c=address&a=getRegion";
    //慧购购物车支付
    public static final String HUI_CREATE_ORDER = "http://www.qkxmall.com/index" +
            ".php?m=api&c=order&a=addorder";
    public static final String GET_SMS_CODE = "http://www.qkxmall.com/index" +
            ".php?m=api&c=member&a=getvalidatecode";
    public static final String USER_REGISTER = "http://www.qkxmall.com/index" +
            ".php?m=api&c=member&a=reg";
    public static final String USER_LOGIN = "http://www.qkxmall.com/index" +
            ".php?m=api&c=member&a=login";
    public static final String RESET_PASSWORD = "http://www.qkxmall.com/index" +
            ".php?m=api&c=member&a=resetpwd";
    //http://www.qkxmall.com/index.php?m=api&c=member&a=modifyuserinfo&field=mobile_phone&uid=28
    // &v=13593754759
    public static final String UPDATE_USER_INFO = "http://www.qkxmall.com/index" +
            ".php?m=api&c=member&a=modifyuserinfo";
    public static final String SPGG = "http://www.qkxmall.com/?m=api&c=goods&a=speclist";

    public static final String BRAND = "http://www.qkxmall.com/?m=api&c=goods&a=brand";
    public static final String HUI_COMMENT_LIST = "http://www.qkxmall" +
            ".com/?m=api&c=goods&a=commentlist";
    public static final String CHECK_SMS = "http://www.qkxmall.com/?m=api&c=member&a=validatecode";


    //清空云购收藏夹
    public static final String EMPTY_FAVORITES = "http://www.qkxmall.com/index" +
            ".php?m=api&c=cloud&a=clearcart";
    //CLOUD
    public static final String MAIN_TITLE_CLOUD = "http://www.qkxmall" +
            ".com/?m=api&c=cloud&a=getlatestone";
    //即将揭晓one
    public static final String JJJX_ONE_URL = "http://www.qkxmall" +
            ".com/?m=api&c=cloud&a=getlatestone";

    //即将揭晓3
    public static final String JJJX_LIST_URL = "http://www.qkxmall.com/?m=api&c=cloud&a=getalllist";

    //即将揭晓grid3
    public static final String JJJX_THREE_URL = "https://www.qkxmall" +
            ".com/?m=api&c=cloud&a=getlatestlist";

    //精品推荐
    public static final String JPTJ_INFO_URL = "http://www.qkxmall" +
            ".com/?m=api&c=cloud&a=getcloudlist";

    //精品推荐列表
    public static final String JPTJ_LIST_URL = "http://www.qkxmall" +
            ".com/?m=api&c=cloud&a=getcloudlist";

    //精品推荐3列表
    public static final String JPTJ_THREE_URL = "http://www.qkxmall" +
            ".com/?m=api&c=cloud&a=getcloudlist&type=recommend";

    //分类推荐
    public static final String FLTJ_INFO_URL = "http://www.qkxmall" +
            ".com/?m=api&c=cloud&a=getcloudlist&type=hot&ctype=";

    //详细情况
    public static final String CLOUD_GOODS_DETAIL_URL = "http://www.qkxmall.com/index" +
            ".php?m=api&c=cloud&a=getcloudinfo";

    //云购首页广告
    public static final String CLOUD_BUY_ADS_URL = "http://www.qkxmall" +
            ".com/?m=api&c=adv&a=lists&pos_id=10";

    //云购获取所有数据
    public static final String CLOUD_GET_ALL_LIST_URL = "http://www.qkxmall" +
            ".com/?m=api&c=cloud&a=getalllist";

    //凑凑获取所有数据
    public static final String COUCOU_GET_ALL_LIST_URL = "https://www.qkxmall" +
            ".com/?m=api&c=cloud&a=getalllist";

    //即将揭晓的所有商品信息列表
    public static final String CLOUD_GET_JJJX_ALL_LIST_URL = "http://www.qkxmall" +
            ".com/?m=api&c=cloud&a=getlatestlist";

    //精品推荐所有商品的信息列表
    public static final String CLOUD_GET_JPTJ_ALL_LIST_URL = "http://www.qkxmall" +
            ".com/?m=api&c=cloud&a=getcloudlist";

    //获取类别
    public static final String CLOUD_GET_TYPE_LIST_URL = "http://www.qkxmall" +
            ".com/?m=api&c=cloud&a=getcloudtype";



    //添加云购收藏夹
    public static final String ADD_TO_CLOUD_COLLECT = "http://www.qkxmall.com/index" +
            ".php?m=api&c=cloud&a=addcloudcart";

    //云购收藏夹
    public static final String CLOUD_CART = "http://www.qkxmall.com/?m=api&c=cloud&a=getusercart";

    //云购订单
    public static final String CLOUD_ORDER = "http://www.qkxmall.com/?m=api&c=cloud&a=getusercloud";

    //惠购立即购买
    public static final String HUI_BUY_NOW = "http://www.qkxmall.com/?m=api&c=order&a=purchase";

    //账户充值
    public static final String ACCOUNT_RECHARGE = "http://www.qkxmall" +
            ".com/?m=api&c=member&a=recharge";


    //个人中心
    public static final String UPLOAD_IMAGE = "https://www.qkxmall.com/api/android/uploadify.php";


    //添加地址
    public static final String ADD_ADDRESS = "http://www.qkxmall.com/?m=api&c=address&a=addaddress";


    //云购评论列表
    public static final String CLOUD_EVALUATION = "http://www.qkxmall" +
            ".com/?m=api&c=cloud&a=cloudcommentlist";


    //惠购订单详情
    public static final String HUI_ORDER_DETAIL = "http://www.qkxmall" +
            ".com/?m=api&c=order&a=getorderdetail";


    //订单支付
    public static final String PAY_ORDER = "http://www.qkxmall.com/?m=api&c=order&a=payorder";


    //用户分享
    public static final String USER_SHARE = "http://www.qkxmall.com/?m=api&c=member&a=usershare";


    //云购支付
    public static final String CLOUD_PAY = "http://www.qkxmall.com/index.php?m=api&c=cloud&a=buy";
    //获取分类信息

    public static final String ALL_CATEGORY="https://www.qkxmall.com/index.php?m=api&c=goods&a=get_all_cate";
    //发起凑凑
    public static final String FQ_COUCOU = "https://www.qkxmall.com/?m=api&c=cloud&a=addCloud";

    //获取凑凑列表

    public static final String COUCOU_LIST = "https://www.qkxmall.com/?m=api&c=cloud&a=lists";

    //获取凑凑本期获奖list
    public static final String COUCOU_LIST_REWARD = "http://www.qkxmall.com/?m=api&c=cloud&a=getcloudtermlist";

    //提现接口
    public static final String TI_XIAN = "https://www.qkxmall.com/index.php?m=api&c=account&a=cash";
    //提现记录接口

//    public static final String ZHANG_DAN="https://www.qkxmall.com/index.php?m=api&c=account&a=lists";


    public static final String MAIN_POS_ID = "9";
    public static final String HUI_POS_ID = "11";
    public static final String CLOUD_POS_ID = "10";

    //惠购删除订单
    public static final String DEL_HUI_ORDER ="https://www.qkxmall.com/index.php?m=api&c=order&a=delOrder";

    //余额购买
    public static final String YUER_BUY ="https://www.qkxmall.com/?m=api&c=cloud&a=buy";

    //提现
    public static final String TIXIAN = "https://www.qkxmall.com/index.php?m=api&c=account&a=cash";

    //中奖后订单操作接口
    public static final String ORDER_OPER = "https://www.qkxmall.com/index.php?m=api&c=cloud&a=changestat";

    //账单明细
    public static final String ZHANGDAN_LIST = "https://www.qkxmall.com/index.php?m=api&c=account&a=lists";

    public static final String MAIN_BRAND="https://www.qkxmall.com/index.php?m=api&c=goods&a=recomm_brand";

    public API() {

    }
}
