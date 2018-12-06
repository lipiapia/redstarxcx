package com.red.star.wechat.data.entity;

import lombok.Data;

@Data
public class SysParam {

    //模板消息
    public static String WX_TEMP_GUEST_CARD_ID = "IZhIF_ceNpZrJH5BF_GKeNQazyHnBviJafWpxsKpCVE";//购买蓄客卡发送给B
    public static String WX_TEMP_POSTER_CARD_ID = "FYOSltgCMO8yCkxgkGTVeiAmenG4RW5PnL3w4Ug47co";//后台蓄客卡和海报群发模板
    /**
     * 消息模板ID: 商户海报通知用户
     **/
    public static String WX_TEMP_MERCHANT_POSTER_ID = "aWtPK7nlAHB2vLyAe8fXcjV3myFLdytU-tC-2TLmIFU";
    /**
     * 消息模板ID: 客服聊天通知
     */
    public static String WX_TEMP_CHAT_NOTICE_CUSTOMER_ID = "xprsGMUTIMZGD5omUFNOvqnWKDCh1_WonxppCK_tfJ0";
    /**
     * 消息模板ID: C端用户聊天通知
     */
    public static String WX_TEMP_CHAT_NOTICE_CFANS_ID = "XYeurfAXGQ02Z4TsJmkT3I_ZFanf1vU1_wdKHYEOX_Q";

    /**
     * 消息模板ID: C端用户提问回复通知
     */
    public static String WX_TEMP_CHAT_NOTICE_CFANS_ID_BY_QUESTION = "XYeurfAXGQ02Z4TsJmkT3I_ZFanf1vU1_wdKHYEOX_Q";

    /**
     * 消息模板ID: 客户关系管理推送
     */
    public static String WX_TEMP_CUSTOMER_MANAGE_PUSH = "cRO1Tv_U9nQLL0qw19oFXXiCsqn6O6";

    //文件服务器
    public static String MACALLIEN_FILE_TOKEN_URL = "http://urms.uat1.rs.com/passport/session/";//获取token
    public static String MACALLINE_FILE_EMPLOYEE_ID = "51045443";//员工号
    public static String MACALLINE_FILE_UPLOAD_URL = "http://file-yun.uat1.rs.com/file/public/upload/e";//图片上传地址

    //短信接口
    public static String MACALLINE_SMS_URL = "http://sms.dev.rs.com/msg/sendMessageV2";

    //ES服务
    public static String ES_SERVER_URL = "http://103.10.1.200:9400";
    //ES秘钥
    public static String ES_SECRET = "ZyyQVZYXiisnHQuhABZ7chqvw9BfdtSq1";


    /**
     * =========wx-server param===========
     **/
    //券包接口
    public static String MACALLINE_URL = "https://wxtest.chinaredstar.com/api/";
    public static String MACALLINE_APP_ID = "testxcx";
    public static String MACALLINE_APP_SECRET = "testxcx";

    //蓄客卡二维码扫描跳转H5
    public static String H5_GUEST_CARD_URL = "http://chinaredstar.yinga.cc/ticket/xcx_ticket_info.aspx?app_key=6309955645";

    //小程序页面
    public static String WX_PAGE_CARD_STORE = "newPages/pageC/stay_userinfo/stay_userinfo";//蓄客卡留资页面
    public static String WX_TEMP_PAGE_CARD_B = "newPages/pageB/index_B/index_b";//蓄客卡B端模板消息页面
    public static String WX_TEMP_PAGE_MERCHANT_POSTER = "newPages/pageC/DM_index/DM_index"; // 商户海报页面
    public static String WX_PAGE_UNION = "newPages/pageB/union/union";//券详情页面
    //小程序首页
    public static String MALL_OMS_CODE = "1041";
    public static String MALL_ACTIVITY_MESSAGE = "10016#2222元券包;10110#2222元券包;10123#2222元券包;10171#2222元券包;10057#2222元券包;10099#2222元券包;";
    public static String MALL_WELCOME_MESSAGE = "10016#4月22日，政务区红星美凯龙6周年庆，与您不“棋”而遇。具体请点击：;10110#4月5日—4月15日，来大连华南商场，上千爆款尖货线上疯抢。活动详情请点击：;10123#4.28日来红星美凯龙烟台建材商场，实力歌手平安助阵，上百款设计尖货限时抢购！具体请点击：;10171#4月21日 来合肥四里河商场，听刘嘉亮演唱会，你到底爱谁！更有特价爆款 1元竞拍等您来拿！具体请点击：;10057#4月15日，红星美凯龙家居节重金压城，百万黄金，星耀周庄！具体请点击：;10099#3月31日-4月1日来济南天桥商场，全民券包翻百倍疯抢。;";

    //接口
    public static String APPLETS_API_SECRET = "wxxcx-test";//小程序接口标识
    public static String REDSTAR_API_SIGN_SECRET = "j6s7oLNhb1j81wS302G41lpHDEWlhVMZ";//蓄客卡接口调用-张乾


    /**
     * =========work-server param===========
     **/
    //小程序页面
    public static String WX_PAGE_CARD_REPORT = "newPages/pageB/forms/forms";//B端蓄客卡报表页面
    public static String WX_TEMP_PAGE_POSTER_CARD = "newPages/pageC/index_C/index_C";//蓄客卡和海报群发模板消息页面
    public static String WORK_POSTER_SEND_DAY = "7";//海报发送周期
    /**
     * 客户信息报表页面
     **/
    public static String WX_PAGE_CUSTOMER_INFO = "newPages/pageB/statistics/statistics";


    /**
     * 导购员欢迎语
     **/
    public static String WELCOME_EMPLOYEE = "";
    /**
     * 联盟组欢迎语
     **/
    public static String WELCOME_UNION = "";
    /**
     * 联盟组欢迎语(没买蓄客卡，联盟组有蓄客卡)
     **/
    public static String WELCOME_UNION_2 = "您好，您当前咨询的联盟群为{0}，活动时间：{1}-{2}，【{3}】，在下方对话框中输入想了解的活动或产品信息，马上会有家居建材营销顾问为您服务哦~";
    /**
     * 联盟组欢迎语(没买蓄客卡，联盟组没有设置蓄客卡)
     **/
    public static String WELCOME_UNION_3 = "在下方对话框中输入想了解的活动或产品信息，马上会有家居建材营销顾问为您服务哦~";
    /**
     * 接待组欢迎语
     **/
    public static String WELCOME_RECEPTION = "";

    /**
     * 欢迎词缺省
     */
    //联盟组欢迎词1
    public static String WELCOME_GROUP_1 = "您已成功购买了{0}-{1}<{2}>活动的邀请卡！点击(icon)可以与您的售卡人员{3}直接沟通。";
    //联盟组欢迎词2
    public static String WELCOME_GROUP_2 = "本次联盟活动还提供：（{0}），如你对本联盟其他品牌产品有需求，欢迎点击(icon)直接沟通。";
    //导购员欢迎词1
    public static String WELCOME_EMPLOYEE_1 = "{0}-{1}来{2}商场{3}品牌，【{4}】。立即点击icon，可以和{5}品牌{6}咨询选购心仪产品。";
    //导购员欢迎词2
    public static String WELCOME_EMPLOYEE_2 = "如果你对红星美凯龙其他家居建材品类、品牌产品感兴趣，欢迎点击icon选择品类直接沟通。";
    //商场欢迎词
    public static String WELCOME_MALL_1 = "如果你对红星美凯龙其他家居建材品类、品牌产品感兴趣，欢迎点击icon选择品类直接沟通。";


    /**
     * 商户海报默认头部背景图
     **/
    public static String DEFAULT_MERCHANT_POSTER_IMAGE = "http://img1.uat1.rs.com/g1/M00/03/13/wKh8ylrXEHGADvIzAAJcBqYkTfo659.png!";
    /**
     * 商户海报默认分享标题
     **/
    public static String DEFAULT_MERCHANT_POSTER_SHARE_TITLE = "【#{BRAND_NAME}】（品牌）又有新活动，快来查看#{EMPLOYEE}（导购）的海报！";
    /**
     * 商户海报模板消息内容
     **/
    public static String TEMP_MERCHANT_POSTER_KEYWORD1 = "您关注的#{BRAND_NAME}品牌#{EMPLOYEE}导购员海报，有新动态啦！";
    public static String TEMP_MERCHANT_POSTER_KEYWORD2 = "您关注的【红星美凯龙#{MALL}商场】{BRAND_NAME}品牌#{EMPLOYEE}导购员海报，有了新动态，请及时查看！";
    public static String TEMP_MERCHANT_POSTER_KEYWORD3 = "#{EMPLOYEE}导购员";
    public static String TEMP_MERCHANT_POSTER_KEYWORD4 = "#{BRAND_NAME}品牌";
    public static String TEMP_MERCHANT_POSTER_KEYWORD5 = "#{MALL_ADDRESS}";

    /**
     * wx后台版本号
     */
    public static String SYSTEM_WX_VERSION = "1.28";

    /**
     * **************************用户行为描述*************************
     */
    /**
     * 购买蓄客卡
     */
    public static String STAT_DYNAMIC_TAG_DESC_1 = "从导购{0}购买了{1}联盟活动的蓄客卡";
    /**
     * 查看本商场DM
     */
    public static String STAT_DYNAMIC_TAG_DESC_2 = "查看了{0}活动的商场海报";
    /**
     * 点击本商场DM爆款
     */
    public static String STAT_DYNAMIC_TAG_DESC_3 = "点击了{0}活动的爆款：{1}";
    /**
     * 领取商场DM爆款券
     */
    public static String STAT_DYNAMIC_TAG_DESC_4 = "领取了{0}活动的爆款：{1}";
    /**
     * 点击领取本商场DM优惠券
     */
    public static String STAT_DYNAMIC_TAG_DESC_5 = "领取了{0}活动的优惠券";
    /**
     * 查看商户DM
     */
    public static String STAT_DYNAMIC_TAG_DESC_6 = "查看了你的个人海报";
    /**
     * 点击商户DM内图片
     */
    public static String STAT_DYNAMIC_TAG_DESC_7 = "查看了你的个人海报{0}时间发的商品";
    /**
     * 转发商户DM
     */
    public static String STAT_DYNAMIC_TAG_DESC_8 = "转发了你的个人海报";
    /**
     * 顾客在联盟群未发言退出
     */
    public static String STAT_DYNAMIC_TAG_DESC_9 = "有{0}需求，但未发起沟通";
    /**
     * 顾客在品类群未发言退出
     */
    public static String STAT_DYNAMIC_TAG_DESC_10 = "有{0}需求，但未发起沟通";
    /**
     * 顾客私聊未发言退出
     */
    public static String STAT_DYNAMIC_TAG_DESC_11 = "对你的品牌感兴趣，但未发起沟通";
    /**
     * 顾客下单时间、下单品类（未实现其功能）
     */
    public static String STAT_DYNAMIC_TAG_DESC_12 = "{0}购买了家具品类商品";
    /**
     * 家装
     */
    public static String STAT_DYNAMIC_TAG_DESC_13 = "想要找装修公司";

    public static String MACALLINE_LOGOUT_URL = "http://114.215.184.61:9907/Login.aspx";

    /**
     * 游客openId
     */
    public static String VISITOR_OPENID = "";

    /**
     * ftp相关参数
     */
    public static String FTP_HOST = "114.215.184.61";
    public static String FTP_USERNAME = "hxmkl_ftp";
    public static String FTP_PASSWORD = "HxmklFtp123";
    public static String FTP_REMOTE_PATH = "";

    /**
     * 仟传数据
     */
    public static String QIAN_CHUAN_CALL_APP = "test";

    public static String QIAN_CHUAN_CALL_SECRET = "test789";

    /**
     * 仟传接口host
     */
    public static String QIAN_CHUAN_BASE_URL = "http://redstar.api.scrm.weisgj.com";

    /**
     * 首页问答城市信息
     */
    public static String WX_TEMP_PAGE_CITY = "city";

    /**
     * 图片模板样式
     */
    public static String IMAGE_STYLE_INFO = "rightBottom#右下;bottom#底部;";

    /**
     * 选择二维码
     */
    public static String IMAGE_CHOICE_QRCODE = "app下载;微信服务号;小程序;H5海报";

    /**
     * 爱情魔方返回文案
     **/
    public static String WX_ASKANSWER_CONTENT1 = "注定无缘，早拍早散";
    public static String WX_ASKANSWER_CONTENT2 = "你傻呀，再等ta也不会爱上你";
    public static String WX_ASKANSWER_CONTENT3 = "落花虽有意，流水却无情";
    public static String WX_ASKANSWER_CONTENT4 = "此人靠得住，母猪能上树";
    public static String WX_ASKANSWER_CONTENT5 = "想爱就爱吧，反正迟早要分的";
    public static String WX_ASKANSWER_CONTENT6 = "勾搭全靠嘴，相爱就是互怼";
    public static String WX_ASKANSWER_CONTENT7 = "想勾搭，先聊骚";
    public static String WX_ASKANSWER_CONTENT8 = "有想法就说，看对眼就上";
    public static String WX_ASKANSWER_CONTENT9 = "“基”情四射，眉目传情";
    public static String WX_ASKANSWER_CONTENT10 = "除非处女座，已然是真爱";
    public static String WX_ASKANSWER_CONTENT11 = "错过ta，再不会遇到更对的人";
    public static String WX_ASKANSWER_CONTENT12 = "天作之合，三生三世在一起";


    public static String IMAGE_POSTER_URL_1_1 = "newPages/pageC/poster/poster1_1";

    public static String VOICE_VIRTUAL_NUMBERS = "8617177791639;8617177791638";
    // 小程序H5链接
    public static String MINI_PROGRAM_H5_URL;

    public static String QIAN_CHUAN_HB_URL = "http://scrm.weisgj.com/H5Make/page.html?task_id={0}&tar_foid={1}";

    /**
     * 龙翼店铺详情接口
     */
    public static String SHOP_DETAIL = "http://rtapi.uat1.rs.com/shop/v1.0.0/listShopInfoByIds";

    // 商户海报链接
    public static String POSTER_MERCHANT_URL;

    /**
     * 小程序首页地址
     */
    public static String WX_HOME_C_URL = "newPages/pageC/home/home_c";


}