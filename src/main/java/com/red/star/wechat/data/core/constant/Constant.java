package com.red.star.wechat.data.core.constant;

/**
 * @author AMGuo
 * @Description
 * @date 2018/03/28 12:23
 */
public final class Constant {

    /**
     * 成功
     */
    public static final String MESSAGE_SUCCESS = "成功";
    /**
     * 失败
     */
    public static final String MESSAGE_FAILED = "失败";

    public static final String TEMPLATE_POST_TOUSER = "touser";

    public static final String TEMPLATE_POST_TEMPLATE_ID = "template_id";

    public static final String TEMPLATE_POST_PAGE = "page";

    public static final String TEMPLATE_POST_FORM_ID = "form_id";

    public static final String TEMPLATE_POST_DATA = "data";

    public static final String TEMPLATE_POST_DATA_VALUE = "value";

    public static final String TEMPLATE_POST_DATA_COLOR = "color";

    public static final String TEMPLATE_POST_DATA_1 = "keyword1";

    public static final String TEMPLATE_POST_DATA_2 = "keyword2";

    public static final String TEMPLATE_POST_DATA_3 = "keyword3";

    public static final String TEMPLATE_POST_EMPHASIS = "emphasis_keyword";

    public static final String TEMPLATE_POST_EMPHASIS_VALUE = "keyword1.DATA";

    public static final String MACALLINE = "红星美凯龙";

    public static final String ERROR_MSG_TEMPLATE_EMPTY_FORMID = "formId is null";

    public static final String STRING_NULL = "null";

    /**
     * 登入、登出状态
     */
    //登入
    public static final Integer TYPE_LOGONLOG_0 = 0;

    //登出
    public static final Integer TYPE_LOGONLOG_1 = 1;

    //主动登出
    public static final Integer TYPE_LOGONLOG_2 = 2;

    public static final String NONCE_QIAN_CHUAN = "nonce";

    public static final String TIMESTAMP_QIAN_CHUAN = "timestamp";

    public static final String CALL_APP_QIAN_CHUAN = "call_app";

    public static final String CALL_SECRET_QIAN_CHUAN = "call_secret";

    public static final String SIGN = "sign";

    public static final String QIAN_CHUAN_URL_ADD_WHITE = "/werenwu/staff/add_white_list";

    public static final String QIAN_CHUAN_URL_DEL_WHITE = "/werenwu/staff/del_white_list";

    public static final String QIAN_CHUAN_URL_GETSTAFFINFO = "/werenwu/staff/getStaffInfo";

    public static final String CMD = "cmd";

    public static final String CMD_WHITE_ADD = "white_add";

    public static final String CMD_WHITE_DEL = "white_del";

    public static final String CMD_GETSTAFFINFO = "staff_info";

    public static final String ERROR_MSG_FANS_NOT_EXIST = "该小程序用户不存在。";

    public static final String ERROR_MSG_IS_EMPLOYEE = "当前用户是导购，不能进行数据同步！";

    public static final String ERROR_MSG_MALL_MERCHANT = "同步失败：商场、商户信息与本系统不对应！";

    public static final String HEADER = "header";

    public static final String AUTHORIZATION = "Authorization";

    public static final String STRING = "string";

    public static final String VERSION = "version";

    public static final String SECRET = "secret";

    public static final String SECRET_VALUE = "wxxcx-test";

    public static final String AUTHORIZATION_VALUE = "Bearer";

    public static final String RESPONSE = "Response";

    public static final String DATAMAP = "dataMap";

    public static final String MSG_INFO_BACK_QRCODE = "编辑器返回二维码类型：{}，链接{}";

    public static final String RELATION_ENUMTYPE_IMAGE_TEMPLATE = "image_template";

    public static final String MSG_INFO_CREATE_RELATION = "创建二维码实体类{}";

    public static final String RELATION_FROM_ID = "from_id";

    public static final String RELATION_POSTER_ID = "poster_id";

    public static final String RELATION_EMPLOYEE_ID = "employee_id";

    public static final String RELATION_PATH = "path";

    public static final String RELATION_IMG_VIEW_ID = "img_view_id";

    public static final String IMAGE_POSTER_UPDATE = "更新编辑器二维码{}";

    public static final String IMAGE_FORMAT_DEFAULT = "JPEG";

    public static final String MSG_WARN_EMPTY_CODE = "二维码为空，请检查参数";

    public static final String IMAGE_POSTER_INSERT = "新增编辑器二维码{}";

    public static final String MSG_INFO_GETAPPQRCODE = "获取App渠道码接口：{}";

    public static final String MSG_INFO_GETWXSENCEQRCODE = "微信渠道二维码接口openid:{}。返回结果：{}";

    public static final String MSG_INFO_GETPOSTERH5QRCODE = "微信电子海报h5二维码接口openid:{}。poster_id：{}。channe_id:{}.结果:{}";

    public static final String IMAGE_VIEW_UPDATE = "更新编辑器图片：{}";

    public static final String RELATION_OMS_CODE = "omsCode";

    public static final String MSG_ERROR_IMAGE_VIEW_STEP_1 = "模板id或本地图片地址不为空";

    public static final String MSG_ERROR_IMAGE_VIEW_STEP_2 = "样式id不为空";

    public static final String MSG_ERROR_IMAGE_VIEW_STEP_3 = "头像地址不为空";

    public static final String MSG_ERROR_IMAGE_VIEW_STEP_4 = "自定义文字不为空";

    public static final String MSG_ERROR_IMAGE_VIEW_STEP_5 = "二维码id不为空";

    public static final String MSG_INFO_GETCHANNELREPORT = "图片编辑器时时查询接口(只有当天数据):{}";

    public static final String MSG_INFO_GETCHANNELREPORTBYDATE = "图片编辑器历史查询接口(排除当天数据)入参：{}。出参:{}";

    public static final String MSG_ERROR_IMAGE_VIEW_STEP_6 = "图片地址不为空";

    public static final String IMAGE_POSTER_TYPE_0 = "小程序";

    public static final String IMAGE_POSTER_TYPE_1 = "h5";

    public static final String IMAGE_POSTER_TYPE_2 = "app下载";

    public static final String IMAGE_POSTER_TYPE_3 = "公众号";

    public static final String MSG_ERROR_IMAGE_VIEW_STEP = "imageViewId不为空";

    public static final String MSG_ERROR_GET_INVITECODE = "获取app邀请码出错：{}";

    public static final String RESULT_getStaffInfo = "getStaffInfo接口，入参：{}，出参：{}";

    public static final String MSG_INFO_SYNCWXGZBIND = "绑定导购、管理员数据同步到微信后台：";

    public static final String MSG_ERROR_SYNCWXGZBIND = "绑定导购、管理员数据同步到微信后台出错：";

    public static final String MSG_INFO_REQUEST_NOTIFYCALLEVENT = "notifyCallEvent接口入参：{}";

    public static final String MSG_INFO_LG_VOICE_INSERT = "通话记录入库：{}，条数：{}";

    public static final String MSG_INFO_CALL_RECORDS_UPDATE = "呼叫记录更新条数：{}";

    public static final String MSG_INFO_VOICE_CALLBACK = "华为隐私通话回调：{}";

    public static final String MSG_INFO_INIT_VIRTUALNUMBER = "虚拟号码初始化:{}";

    public static final String MSG_INFO_PARTY_VIRTUALNUMBER = "手机号：{}目前已绑定的虚拟号码为：{}";

    public static final String MSG_INFO_RETURN_VIRTUALNUMBER = "手机号a：{},手机号b：{}，返回的虚拟号码为{}";

    public static final Integer INTEGER_1 = 1;

    public static final Integer INTEGER_2 = 2;

    public static final Integer INTEGER_3 = 3;

    public static final Integer INTEGER_4 = 4;

    public static final Integer INTEGER_5 = 5;

    public static final Integer INTEGER_6 = 6;

    public static final String MSG_INFO_GETACTH5LIST = "获取疯狂H5列表接口。入参：{}，出参：{}";

    public static final String MSG_INFO_GETWBACTLIST = "获取外部H5列表接口。入参：{}，出参：{}";

    public static final String MSG_INFO_GETACTH5QRCODE = "获取疯狂H5的二维码接口。入参：{}，出参：{}";

    public static final String MSG_INFO_GETWBACTQRCODE = "获取外部H5的二维码接口。入参：{}，出参：{}";

    public static final Integer INTEGER_10 = 10;

    public static final Integer INTEGER_0 = 0;

    public static final String QIAN_CHUAN_URL_GET_ALL_H5_LIST = "/werenwu/h5/get_all_h5_list";

    public static final String CMD_TASK_LIST = "all_h5_list";

    public static final String RESULT_TASK_LIST = "get_all_h5_list接口，入参：{}，出参：{}";;

    public static final String QIAN_CHUAN_URL_GET_DETAIL_TASK = "/werenwu/h5/get_detail_task";

    public static final String CMD_GETDETAILTASK = "get_detail_task";

    public static final String RESULT_GETDETAILTASK = "get_detail_task接口，入参：{}，出参：{}";;;

    public static final String IMAGE_POSTER_TYPE_4 = "外部h5";

    public static final String IMAGE_POSTER_TYPE_5 = "竖版h5";

    public static final String IMAGE_POSTER_TYPE_6 = "疯狂h5";

    public static final String PARAM_FROMOPENID = "fromOpenId";

    public static final String PARAM_FROMUNIONID = "fromUnionId";

    public static final String PARAM_SOURCEOPENID = "sourceOpenId";

    public static final String PARAM_SOURCEUNIONID = "sourceUnionId";

    public static final String PARAM_LAYER = "layer";

    public static final String PARAM_UUID = "uuid";

    public static final String PARAM_URL = "url";

    public static final String MSG_INFO_BINDEMPLOYEE = "绑定导购接口。入参：{}，出参：{}";

    public static final String MSG_INFO_SYNC_VIP = "同步会员信息，";

    public static final String MSG_ERROR_FAILED = "失败";

    public static final String WX_LINE_COLOR = "{\"r\":\"0\",\"g\":\"0\",\"b\":\"0\"}";

    public static final String WX_SCENE = "1";
}
