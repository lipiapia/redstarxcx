package com.red.star.wechat.data.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.red.star.wechat.data.core.base.RedisRunner;
import com.red.star.wechat.data.core.constant.Constant;
import com.red.star.wechat.data.entity.SysParam;
import org.apache.http.client.fluent.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 * @author AMGuo
 * @Description
 * @date 2018/03/01 11:04
 */
public class WeiXinUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(WeiXinUtil.class);

    private static final String URL_PREFIX = "https://api.weixin.qq.com";

    private static final String CODE_URL_PREFIX = "https://mp.weixin.qq.com";

    private static final String APPID = "wx38defc1096ce180d";

    private static final String APPSECRET = "9e89a159d9babee2727591da98043d09";

    //获取access_token
    public static String ACCESS_TOKEN_URL = String.format(
            "%s/cgi-bin/token?grant_type=client_credential&appid=%s&secret=%s", URL_PREFIX, APPID, APPSECRET
    );

    //获取ticket
    public static String TICKET_URL = String.format(
            "%s/cgi-bin/qrcode/create?access_token=%s", URL_PREFIX, "%s"
    );

    //获取二维码链接
    public static String CODE_URL = String.format("%s/cgi-bin/showqrcode?ticket=%s", CODE_URL_PREFIX, "%s");

    //获取小程序二维码链接
    public static String WX_CODE_URL = String.format("%s/wxa/getwxacodeunlimit?access_token=%s", URL_PREFIX, "%s");

    //小程序模板信息接口
    public static String WX_MESSAGE_URL = String.format("%s/cgi-bin/message/wxopen/template/send?access_token=%s",
            URL_PREFIX, "%s");


    /**
     * 获取Access Token
     *
     * @param redisRunner
     * @return
     */
    public static String fetchAccessToken(RedisRunner redisRunner) throws IOException {
        return TicketUtil.getAccessToken();
    }


    /**
     * 获取小程序二维码url
     *
     * @param redisRunner
     * @return 小程序二维码url
     * @throws UnsupportedEncodingException
     */
    public static String fecthWXCodeUrl(RedisRunner redisRunner) throws IOException {
        String accessToken = fetchAccessToken(redisRunner);
        String wxCodeUrl = String.format(WX_CODE_URL, accessToken);
        return wxCodeUrl;
    }

    public static String fecthWXCodeUrl() throws IOException {
        String accessToken = TicketUtil.getAccessToken();
        String wxCodeUrl = String.format(WX_CODE_URL, accessToken);
        return wxCodeUrl;
    }

    /**
     * 获取小程序模板信息url
     *
     * @param redisRunner
     * @return 小程序模板信息url
     */
    public static String fecthWXMessageUrl(RedisRunner redisRunner) throws IOException {
        String accessToken = fetchAccessToken(redisRunner);
        String wxMessageUrl = String.format(WX_MESSAGE_URL, accessToken);
        return wxMessageUrl;
    }

    /**
     * 获取小程序模板信息url
     *
     * @return 小程序模板信息url
     */
    public static String fecthWXMessageUrlNew() throws IOException {
        String accessToken = TicketUtil.getAccessToken();
        String wxMessageUrl = String.format(WX_MESSAGE_URL, accessToken);
        return wxMessageUrl;
    }

    /**
     * 发送小程序模板信息
     *
     * @param openId
     * @param formId
     * @param data            模板消息内容
     * @param emphasisKeyword 需要放大的字段
     * @param page            模板消息链接
     * @return
     * @throws IOException
     */
    public static Map fecthWXMessage(String openId, String formId, Map data, String emphasisKeyword, String page) throws IOException {
        return sendTemplateMsg(SysParam.WX_TEMP_POSTER_CARD_ID, openId, formId, data, emphasisKeyword, page);
    }

    /**
     * 发送模版消息
     *
     * @param templateId      模板消息ID
     * @param openId          接收者（用户）的 openid
     * @param formId          表单提交场景下，为 submit 事件带上的 formId；支付场景下，为本次支付的 prepay_id
     * @param data            模板内容，不填则下发空模板
     * @param emphasisKeyword 模板需要放大的关键词，不填则默认无放大
     * @param page            小程序页面（点击模板消息跳转）
     * @return 发送模版消息结果数据集
     * @throws IOException
     */
    public static Map sendTemplateMsg(String templateId, String openId, String formId, Map data, String emphasisKeyword, String page) throws IOException {
        if (CheckUtil.isEmpty(templateId) || CheckUtil.isEmpty(openId) || CheckUtil.isEmpty(data)) {
            return new HashMap();
        }
        Map templateMsg = new HashMap();
        Map result = new HashMap();
        //判断formId为空
        if (CheckUtil.isEmpty(formId) || Constant.STRING_NULL.equals(formId)) {
            result.put("body", Constant.ERROR_MSG_TEMPLATE_EMPTY_FORMID);
            result.put("flag", false);
            return result;
        }
        //发送给
        templateMsg.put(Constant.TEMPLATE_POST_TOUSER, openId);
        //template_id
        templateMsg.put(Constant.TEMPLATE_POST_TEMPLATE_ID, templateId);
        //page
        templateMsg.put(Constant.TEMPLATE_POST_PAGE, page);
        //form_id
        templateMsg.put(Constant.TEMPLATE_POST_FORM_ID, formId);
        //data
        templateMsg.put(Constant.TEMPLATE_POST_DATA, data);
        templateMsg.put(Constant.TEMPLATE_POST_EMPHASIS, emphasisKeyword);
        String WXMessageUrl = WeiXinUtil.fecthWXMessageUrlNew();
        Request request = Request.Post(WXMessageUrl);
        String body = HttpUtil.fetch(request, JSONObject.toJSONString(templateMsg));
        result.put("body", body);
        if (!CheckUtil.isEmpty(body)) {
            JSONObject jsonObject = JSONObject.parseObject(body);
            if (!CheckUtil.isEmpty(jsonObject) && Integer.valueOf(0).equals(jsonObject.getInteger("errcode"))) {
                //将formId标记过期
                TicketUtil.changeStatus(openId, formId);
                result.put("flag", true);
                return result;
            } else {
                //将formId标记过期
                TicketUtil.changeStatus(openId, formId);
                //模板消息推送重试
                String formIdNew = TicketUtil.getFormId(openId);
                return fecthWXMessage(openId, formIdNew, data, emphasisKeyword, page);
            }
        } else {
            //将formId标记过期
            TicketUtil.changeStatus(openId, formId);
            //模板消息推送重试
            String formIdNew = TicketUtil.getFormId(openId);
            return fecthWXMessage(openId, formIdNew, data, emphasisKeyword, page);
        }
    }

    /**
     * 生成小程序二维码方法
     *
     * @param path 扫码跳转地址
     * @param map  scene
     * @return 图片base64  需在最前面加上  data:image/jpeg;base64,
     * @throws IOException
     */
    public static String backWXCodeImageBase64(String path, Map<String, String> map) throws IOException {
        String scene = getStringByMap(map);
        return Base64.getEncoder().encodeToString(backWXCodeImageByte(path, scene, false));
    }

    /**
     * 生成小程序二维码，返回链接
     *
     * @param path
     * @param scene
     * @return
     * @throws IOException
     */
    public static String backWXCodeImageUrl(String path, String scene) throws IOException {
        byte[] imageByte = backWXCodeImageByte(path, scene, false);
        String fileUrl = "";
        if (!CheckUtil.isEmpty(imageByte)) {
            fileUrl = FileUtil.uploadFile(UuidUtil.generateShortUuid() + ".png", imageByte);
        }
        return fileUrl;
    }

    /**
     * 根据map返回String字符串拼接
     *
     * @param map
     * @return
     */
    public static String getStringByMap(Map<String, String> map) {
        String scene = "";
        for (Map.Entry entry : map.entrySet()) {
            if (!CheckUtil.isEmpty(entry)) {
                scene += entry.getKey() + "=" + entry.getValue() + "&";
            }
        }
        if (!CheckUtil.isEmpty(scene) && "&".equals(scene.substring(scene.length() - 1, scene.length()))) {
            scene = scene.substring(0, scene.length() - 1);
        }
        return scene;
    }

    /**
     * 生成小程序码
     *
     * @param path
     * @param map
     * @param isHyaLine
     * @return
     * @throws IOException
     */
    public static byte[] backWXCodeImageByte(String path, Map<String, String> map, boolean isHyaLine) throws IOException {
        String scene = getStringByMap(map);
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("scene", scene);
        param.put("page", path);
        param.put("is_hyaline", isHyaLine);
        Request request = Request.Post(WeiXinUtil.fecthWXCodeUrl());
        return HttpUtil.fetchByte(request, JSONObject.toJSONString(param));
    }

    /**
     * 生成二维码图片
     */
    public static byte[] backWXCodeImageByte(String path, String scene, boolean isHyaLine) throws IOException {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("scene", scene);
        param.put("path", path);
        param.put("is_hyaline", isHyaLine);
        Request request = Request.Post(WeiXinUtil.fecthWXCodeUrl());
        return HttpUtil.fetchByte(request, JSONObject.toJSONString(param));
    }

    /**
     * 增加白名单成员（导购员）——仟传
     * werenwu/staff/add_white_list?
     * call_app=call_app&
     * nonce=nonce&
     * timestamp=timestamp&
     * cmd=white_add&
     * sign=sign
     *
     * @param openid     公众号openid
     * @param name
     * @param mallSerial
     * @param tel
     * @param identity   员工身份 如：1 员工  2 导购  。。。。
     * @return
     */
    public static String addWhiteList(String openid, String name, String mallSerial, String tel, int identity) throws IOException {
        if (!CheckUtil.isEmpty(openid) && !CheckUtil.isEmpty(name) && !CheckUtil.isEmpty(mallSerial) && !CheckUtil.isEmpty(tel)) {
            Map<String, String> param = new HashMap<String, String>();
            List<Map> list = new ArrayList<>();
            Map map = new HashMap();
            map.put("openid", openid);
            map.put("name", name);
            map.put("mall_serial", mallSerial);
            map.put("tel", tel);
            map.put("identity", identity);
            list.add(map);
            String nonce = SignUtil.createNonceStr();
            String timestamp = SignUtil.createTimestamp();
            param.put(Constant.CALL_APP_QIAN_CHUAN, SysParam.QIAN_CHUAN_CALL_APP);
            param.put(Constant.NONCE_QIAN_CHUAN, nonce);
            param.put(Constant.TIMESTAMP_QIAN_CHUAN, timestamp);
            param.put(Constant.CMD, Constant.CMD_WHITE_ADD);
            String sign = SignUtil.fetchQCStarSign(param);
            Request request = Request.Post(SysParam.QIAN_CHUAN_BASE_URL + Constant.QIAN_CHUAN_URL_ADD_WHITE +
                    "?" + Constant.CALL_APP_QIAN_CHUAN + "=" + SysParam.QIAN_CHUAN_CALL_APP +
                    "&" + Constant.NONCE_QIAN_CHUAN + "=" + nonce + "&" +
                    Constant.TIMESTAMP_QIAN_CHUAN + "=" + timestamp +
                    "&" + Constant.CMD + "=" + Constant.CMD_WHITE_ADD + "&" + Constant.SIGN + "=" + sign);
            String body = HttpUtil.fetch(request, JSON.toJSONString(list));
            return body;
        }
        return null;
    }

    /**
     * 删除白名单成员——仟传
     *
     * @param openid 公众号openid
     * @return
     * @throws IOException
     */
    public static String delWhiteList(String openid) throws IOException {
        if (!CheckUtil.isEmpty(openid)) {
            Map<String, String> param = new HashMap<String, String>();
            List<String> list = new ArrayList<>();
            list.add(openid);
            String nonce = SignUtil.createNonceStr();
            String timestamp = SignUtil.createTimestamp();
            param.put(Constant.CALL_APP_QIAN_CHUAN, SysParam.QIAN_CHUAN_CALL_APP);
            param.put(Constant.NONCE_QIAN_CHUAN, nonce);
            param.put(Constant.TIMESTAMP_QIAN_CHUAN, timestamp);
            param.put(Constant.CMD, Constant.CMD_WHITE_DEL);
            String sign = SignUtil.fetchQCStarSign(param);
            Request request = Request.Post(SysParam.QIAN_CHUAN_BASE_URL + Constant.QIAN_CHUAN_URL_DEL_WHITE +
                    "?" + Constant.CALL_APP_QIAN_CHUAN + "=" + SysParam.QIAN_CHUAN_CALL_APP +
                    "&" + Constant.NONCE_QIAN_CHUAN + "=" + nonce + "&" +
                    Constant.TIMESTAMP_QIAN_CHUAN + "=" + timestamp +
                    "&" + Constant.CMD + "=" + Constant.CMD_WHITE_DEL + "&" + Constant.SIGN + "=" + sign);
            String body = HttpUtil.fetch(request, JSON.toJSONString(list));
            return body;
        }
        return null;
    }

    /**
     * 获取用户数据-仟传
     *
     * @param token
     * @return
     * @throws IOException
     */
    public static String getStaffInfo(String token) throws IOException {
        if (!CheckUtil.isEmpty(token)) {
            Map<String, String> param = new HashMap<String, String>();
            Map map = new HashMap();
            map.put("token", token);
            String nonce = SignUtil.createNonceStr();
            String timestamp = SignUtil.createTimestamp();
            param.put(Constant.CALL_APP_QIAN_CHUAN, SysParam.QIAN_CHUAN_CALL_APP);
            param.put(Constant.NONCE_QIAN_CHUAN, nonce);
            param.put(Constant.TIMESTAMP_QIAN_CHUAN, timestamp);
            param.put(Constant.CMD, Constant.CMD_GETSTAFFINFO);
            String sign = SignUtil.fetchQCStarSign(param);
            Request request = Request.Post(SysParam.QIAN_CHUAN_BASE_URL + Constant.QIAN_CHUAN_URL_GETSTAFFINFO +
                    "?" + Constant.CALL_APP_QIAN_CHUAN + "=" + SysParam.QIAN_CHUAN_CALL_APP +
                    "&" + Constant.NONCE_QIAN_CHUAN + "=" + nonce + "&" +
                    Constant.TIMESTAMP_QIAN_CHUAN + "=" + timestamp +
                    "&" + Constant.CMD + "=" + Constant.CMD_GETSTAFFINFO + "&" + Constant.SIGN + "=" + sign);
            String body = HttpUtil.fetch(request, JSON.toJSONString(map));
            LOGGER.info(Constant.RESULT_getStaffInfo, JSON.toJSONString(map), body);
            return body;
        }
        return null;
    }


    /**
     * 获取某个商场下的所有的H5列表
     *
     * @param omsCode
     * @return
     * @throws IOException
     */
    public static String getAllH5List(String omsCode) throws IOException {
        if (!CheckUtil.isEmpty(omsCode)) {
            Map<String, String> param = new HashMap<String, String>();
            Map map = new HashMap();
            map.put("code", omsCode);
            map.put("page", 1);
            map.put("pagesize", 5000);
            String nonce = SignUtil.createNonceStr();
            String timestamp = SignUtil.createTimestamp();
            param.put(Constant.CALL_APP_QIAN_CHUAN, SysParam.QIAN_CHUAN_CALL_APP);
            param.put(Constant.NONCE_QIAN_CHUAN, nonce);
            param.put(Constant.TIMESTAMP_QIAN_CHUAN, timestamp);
            param.put(Constant.CMD, Constant.CMD_TASK_LIST);
            String sign = SignUtil.fetchQCStarSign(param);
            Request request = Request.Post(SysParam.QIAN_CHUAN_BASE_URL + Constant.QIAN_CHUAN_URL_GET_ALL_H5_LIST +
                    "?" + Constant.CALL_APP_QIAN_CHUAN + "=" + SysParam.QIAN_CHUAN_CALL_APP +
                    "&" + Constant.NONCE_QIAN_CHUAN + "=" + nonce + "&" +
                    Constant.TIMESTAMP_QIAN_CHUAN + "=" + timestamp +
                    "&" + Constant.CMD + "=" + Constant.CMD_TASK_LIST + "&" + Constant.SIGN + "=" + sign);
            String body = HttpUtil.fetch(request, JSON.toJSONString(map));
            LOGGER.info(Constant.RESULT_TASK_LIST, JSON.toJSONString(map), body);
            return body;
        }
        return null;
    }


    /**
     * 获取某个参与H5任务的参与者信息
     *
     * @param eventid
     * @param unionid
     * @return
     * @throws IOException
     */
    public static String getDetailTask(String eventid, String unionid) throws IOException {
        if (!CheckUtil.isEmpty(eventid) && !CheckUtil.isEmpty(unionid)) {
            Map<String, String> param = new HashMap<String, String>();
            Map map = new HashMap();
            map.put("eventid", eventid);
            map.put("unionid", unionid);
            String nonce = SignUtil.createNonceStr();
            String timestamp = SignUtil.createTimestamp();
            param.put(Constant.CALL_APP_QIAN_CHUAN, SysParam.QIAN_CHUAN_CALL_APP);
            param.put(Constant.NONCE_QIAN_CHUAN, nonce);
            param.put(Constant.TIMESTAMP_QIAN_CHUAN, timestamp);
            param.put(Constant.CMD, Constant.CMD_GETDETAILTASK);
            String sign = SignUtil.fetchQCStarSign(param);
            Request request = Request.Post(SysParam.QIAN_CHUAN_BASE_URL + Constant.QIAN_CHUAN_URL_GET_DETAIL_TASK +
                    "?" + Constant.CALL_APP_QIAN_CHUAN + "=" + SysParam.QIAN_CHUAN_CALL_APP +
                    "&" + Constant.NONCE_QIAN_CHUAN + "=" + nonce + "&" +
                    Constant.TIMESTAMP_QIAN_CHUAN + "=" + timestamp +
                    "&" + Constant.CMD + "=" + Constant.CMD_GETDETAILTASK + "&" + Constant.SIGN + "=" + sign);
            String body = HttpUtil.fetch(request, JSON.toJSONString(map));
            LOGGER.info(Constant.RESULT_GETDETAILTASK, JSON.toJSONString(map), body);
            return body;
        }
        return null;
    }

    public static void main(String[] args) throws IOException {
//        String a = addWhiteList("opkpxt3fePX7Kk2cT4sPYVyvgNAA", "test1", "1009", "15205607212");
//        String d = delWhiteList("opkpxt3fePX7Kk2cT4sPYVyvgNAA");
//        System.err.println(a);
//        System.err.println(getAllH5List("1229"));
//        System.err.println(getDetailTask("122251","ofI3owDiP0iaW4FjVyMCd-pzytD0"));
        //map.put("openid", openid);
        //            map.put("name", name);
        //            map.put("mall_serial", mallSerial);
        //            map.put("tel", tel);
        //            map.put("identity", identity);
//        System.out.println(addWhiteList("opkpxt3fePX7Kk2cT4sPYVyvgNAB", "11", "1009", "15205607212", 1));
        //System.out.println(delWhiteList("opkpxt3fePX7Kk2cT4sPYVyvgNAB"));
    }


}
