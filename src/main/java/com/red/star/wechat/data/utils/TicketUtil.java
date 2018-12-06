package com.red.star.wechat.data.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.red.star.wechat.data.core.constant.Constant;
import com.red.star.wechat.data.entity.SysParam;
import org.apache.http.client.fluent.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class TicketUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(TicketUtil.class);

    /**
     * 优惠券列表
     *
     * @param status   -1 全部 0:免费优惠券;1:免费单品券;2:付费优惠券;3:礼品券;4:付费单品券;5:免费券包;6:付费券包
     * @param page
     * @param pageSize
     * @throws IOException
     */
    public static String ticketList(String mallCode, Integer status, Integer page, Integer pageSize) throws IOException {
        Map<String, String> param = new HashMap<String, String>();
        String sign = SignUtil.fetchSign(param);
        param.put("unit_code", mallCode);
        param.put("status", status.toString());
        param.put("start_row", page.toString());
        param.put("row_count", pageSize.toString());
        Request request = Request.Post(SysParam.MACALLINE_URL + "Foreign/GetTicketList");
        request.setHeader("Content-Type", "application/x-www-form-urlencoded");
        request.setHeader("redstar-call-app-id", SysParam.MACALLINE_APP_ID);
        request.setHeader("redstar-sign", sign);
        String resp = HttpUtil.fetch(request, param);
        return resp;
    }


    /**
     * 海报列表
     *
     * @param omsCode 为空，出参为空
     * @return
     * @throws IOException
     */
    public static String postList(String omsCode) throws IOException {
        Map<String, String> param = new HashMap<String, String>();
        String sign = SignUtil.fetchSign(param);
        param.put("unit_code", omsCode);
        Request request = Request.Post(SysParam.MACALLINE_URL + "poster/GetPosterList");
        request.setHeader("Content-Type", "application/x-www-form-urlencoded");
        request.setHeader("redstar-call-app-id", SysParam.MACALLINE_APP_ID);
        request.setHeader("redstar-sign", sign);
        String resp = HttpUtil.fetch(request, param);
        return resp;
    }

    /**
     * 海报列表
     *
     * @param omsCode 为空，出参为全部海报列表
     * @return
     * @throws IOException
     */
    public static String actPosterList(String omsCode) throws IOException {
        Map<String, String> param = new HashMap<String, String>();
        String sign = SignUtil.fetchSign(param);
        param.put("unit_code", omsCode);
        Request request = Request.Post(SysParam.MACALLINE_URL + "activity/GetPosterList");
        request.setHeader("Content-Type", "application/x-www-form-urlencoded");
        request.setHeader("redstar-call-app-id", SysParam.MACALLINE_APP_ID);
        request.setHeader("redstar-sign", sign);
        String resp = HttpUtil.fetch(request, param);
        return resp;
    }

    /**
     * 获取海报详情
     *
     * @param posterId
     * @return
     * @throws IOException
     */
    public static String posterDetail(String posterId) throws IOException {
        Map<String, String> param = new HashMap<String, String>();
        String sign = SignUtil.fetchSign(param);
        param.put("poster_id", posterId);
        Request request = Request.Post(SysParam.MACALLINE_URL + "poster/GetPosterShareInfo");
        request.setHeader("Content-Type", "application/x-www-form-urlencoded");
        request.setHeader("redstar-call-app-id", SysParam.MACALLINE_APP_ID);
        request.setHeader("redstar-sign", sign);
        String resp = HttpUtil.fetch(request, param);
        return resp;
    }


    /**
     * 获取券详情
     *
     * @param promotionTicketId
     * @return
     * @throws IOException
     */
    public static String getTicketDetail(String promotionTicketId) throws IOException {
        Map<String, String> param = new HashMap<String, String>();
        String sign = SignUtil.fetchSign(param);
        param.put("promotion_ticket_id", promotionTicketId);
        Request request = Request.Post(SysParam.MACALLINE_URL + "Foreign/GetTicketDetail");
        request.setHeader("Content-Type", "application/x-www-form-urlencoded");
        request.setHeader("redstar-call-app-id", SysParam.MACALLINE_APP_ID);
        request.setHeader("redstar-sign", sign);
        String resp = HttpUtil.fetch(request, param);
        return resp;

    }

    /**
     * 领券接口
     *
     * @param promotionTicketId
     * @param vipId
     * @param crmOpenId
     * @return
     * @throws IOException
     */
    public static String coupons(String promotionTicketId, String vipId, String crmOpenId) throws IOException {
        Map<String, String> param = new HashMap<String, String>();
        String sign = SignUtil.fetchSign(param);
        param.put("promotion_ticket_id", promotionTicketId);
        param.put("vip_id", vipId);
        param.put("crm_open_id", crmOpenId);
        Request request = Request.Post(SysParam.MACALLINE_URL + "Foreign/GetTicketDetail");
        request.setHeader("Content-Type", "application/x-www-form-urlencoded");
        request.setHeader("redstar-call-app-id", SysParam.MACALLINE_APP_ID);
        request.setHeader("redstar-sign", sign);
        String resp = HttpUtil.fetch(request, param);
        return resp;

    }


    /**
     * 获取formId
     *
     * @param openId
     * @return
     * @throws IOException
     */
    public static String getFormId(String openId) throws IOException {
        Map<String, String> param = new HashMap<String, String>();
        String sign = SignUtil.fetchSign(param);
        param.put("open_id", openId);
        Request request = Request.Post(SysParam.MACALLINE_URL + "user/getform");
        request.setHeader("Content-Type", "application/x-www-form-urlencoded");
        request.setHeader("redstar-call-app-id", SysParam.MACALLINE_APP_ID);
        request.setHeader("redstar-sign", sign);
        String resp = HttpUtil.fetch(request, param);
        if (!CheckUtil.isEmpty(resp)) {
            JSONObject jsonObject = JSONObject.parseObject(resp);
            if (!CheckUtil.isEmpty(jsonObject)) {
//				return jsonObject.getString("dataMap");
                JSONObject dataMap = jsonObject.getJSONObject("dataMap");
                if (!CheckUtil.isEmpty(dataMap)) {
                    System.err.println(dataMap.getString("vip_cell") + "11111" + dataMap.getString("vip_name"));
                    return dataMap.getString("form_id");
                }
            }
        }
        return null;
    }

    /**
     * 获取formId
     *
     * @param unionId
     * @return
     * @throws IOException
     */
    public static JSONObject getVipInfo(String unionId) throws IOException {
        Map<String, String> param = new HashMap<String, String>();
        String sign = SignUtil.fetchSign(param);
        param.put("union_id", unionId);
        Request request = Request.Post(SysParam.MACALLINE_URL + "user/GetUserByUnionid");
        request.setHeader("Content-Type", "application/x-www-form-urlencoded");
        request.setHeader("redstar-call-app-id", SysParam.MACALLINE_APP_ID);
        request.setHeader("redstar-sign", sign);
        String resp = HttpUtil.fetch(request, param);
        if (!CheckUtil.isEmpty(resp)) {
            JSONObject jsonObject = JSONObject.parseObject(resp);
            if (!CheckUtil.isEmpty(jsonObject)) {
                JSONObject dataMap = jsonObject.getJSONObject("dataMap");
                return dataMap;
            }
        }
        return null;
    }


    /**
     * 获取accessToken
     *
     * @return
     * @throws IOException
     */
    public static String getAccessToken() throws IOException {
        Map<String, String> param = new HashMap<String, String>();
        String sign = SignUtil.fetchSign(param);
        Request request = Request.Post(SysParam.MACALLINE_URL + "Foreign/gettoken");
        request.setHeader("Content-Type", "application/x-www-form-urlencoded");
        request.setHeader("redstar-call-app-id", SysParam.MACALLINE_APP_ID);
        request.setHeader("redstar-sign", sign);
        String back = HttpUtil.fetch(request, param);
        if (!CheckUtil.isEmpty(back)) {
            JSONObject jsonObject = JSONObject.parseObject(back);
            if (!CheckUtil.isEmpty(jsonObject)) {
                return jsonObject.getString("dataMap");
            }
        }
        return null;
    }

    public static String getMallUrl(String omsCode) throws IOException {
        Map<String, String> param = new HashMap<String, String>();
        String sign = SignUtil.fetchSign(param);
        param.put("unit_code", omsCode);
        Request request = Request.Post(SysParam.MACALLINE_URL + "poster/GetPosterList");
        request.setHeader("Content-Type", "application/x-www-form-urlencoded");
        request.setHeader("redstar-call-app-id", SysParam.MACALLINE_APP_ID);
        request.setHeader("redstar-sign", sign);
        String back = HttpUtil.fetch(request, param);
        if (!CheckUtil.isEmpty(back)) {
            JSONObject jsonObject = JSONObject.parseObject(back);
            if (!CheckUtil.isEmpty(jsonObject)) {
                return jsonObject.getString("dataMap");
            }
        }
        return null;
    }


    /**
     * 根据手机号,PromotionTicketId，TicketID进行核销操作
     *
     * @param cell
     * @param promotionTicketId
     * @param ticketId
     * @return
     * @throws IOException
     */
    public static Boolean verificationCouponList(String cell, Integer promotionTicketId, Integer ticketId, String ticketNo) throws IOException {
        Map<String, String> param = new HashMap<String, String>();
        String sign = SignUtil.fetchSign(param);
        param.put("cell", cell);
        param.put("promotion_ticket_id", CheckUtil.isEmpty(promotionTicketId.toString()) ? "" : promotionTicketId.toString());
        param.put("ticket_id", CheckUtil.isEmpty(ticketId.toString()) ? "" : ticketId.toString());
        param.put("ticket_no", CheckUtil.isEmpty(ticketNo) ? "" : ticketNo);
        Request request = Request.Post(SysParam.MACALLINE_URL + "Ticket/UsePromotionTicketByCell");
        request.setHeader("Content-Type", "application/x-www-form-urlencoded");
        request.setHeader("redstar-call-app-id", SysParam.MACALLINE_APP_ID);
        request.setHeader("redstar-sign", sign);
        String back = HttpUtil.fetch(request, param);
        if (!CheckUtil.isEmpty(back)) {
            JSONObject jsonObject = JSONObject.parseObject(back);
            if (jsonObject.get("code").equals(200)) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    /**
     * 根据omsCode获取openid,form_id,unit_code
     *
     * @param omsCode
     * @return
     * @throws IOException
     */
    public static JSONArray getOpenInfo(String omsCode) throws IOException {
        Assert.notNull(omsCode, "omsCode for null");
        Map<String, String> param = new HashMap<String, String>();
        String sign = SignUtil.fetchSign(param);
        param.put("unit_code", omsCode);
        Request request = Request.Post(SysParam.MACALLINE_URL + "Foreign/GetPosterFormId");
        request.setHeader("Content-Type", "application/x-www-form-urlencoded");
        request.setHeader("redstar-call-app-id", SysParam.MACALLINE_APP_ID);
        request.setHeader("redstar-sign", sign);
        String back = HttpUtil.fetch(request, param);
        if (!CheckUtil.isEmpty(back)) {
            JSONObject jsonObject = JSONObject.parseObject(back);
            if (!CheckUtil.isEmpty(jsonObject)) {
                return jsonObject.getJSONArray("dataMap");
            }
        }
        return null;
    }

    /**
     * 将已用的formId标记
     *
     * @param openid
     * @param formId
     * @return
     * @throws IOException
     */
    public static String changeStatus(String openid, String formId) throws IOException {
        Assert.notNull(openid, "openid for null");
        Assert.notNull(formId, "formId for null");
        Map<String, String> param = new HashMap<String, String>();
        String sign = SignUtil.fetchSign(param);
        param.put("open_id", openid);
        param.put("form_id", formId);
        Request request = Request.Post(SysParam.MACALLINE_URL + "User/UpdateForm");
        request.setHeader("Content-Type", "application/x-www-form-urlencoded");
        request.setHeader("redstar-call-app-id", SysParam.MACALLINE_APP_ID);
        request.setHeader("redstar-sign", sign);
        String back = HttpUtil.fetch(request, param);
        return back;
    }

    /**
     * 根据券包id获取openid，formid
     *
     * @param cpId
     * @return
     * @throws IOException
     */
    public static JSONArray getInfoByCpId(Integer cpId) throws IOException {
        Assert.notNull(cpId, "null of cpId");
        Map<String, String> param = new HashMap();
        String sign = SignUtil.fetchSign(param);
        param.put("sku_id", String.valueOf(cpId));
        Request request = Request.Post(SysParam.MACALLINE_URL + "Foreign/GetSKuFormId");
        request.setHeader("Content-Type", "application/x-www-form-urlencoded");
        request.setHeader("redstar-call-app-id", SysParam.MACALLINE_APP_ID);
        request.setHeader("redstar-sign", sign);
        String back = HttpUtil.fetch(request, param);
        if (!CheckUtil.isEmpty(back)) {
            JSONObject jsonObject = JSONObject.parseObject(back);
            if (!CheckUtil.isEmpty(jsonObject)) {
                return jsonObject.getJSONArray("dataMap");
            }
        }
        return null;
    }

    /**
     * 海报详情
     *
     * @param posterId
     * @return
     * @throws IOException
     */
    public static JSONObject fecthpPosterDetail(String posterId) throws IOException {
        Map<String, String> param = new HashMap<String, String>();
        String sign = SignUtil.fetchSign(param);
        param.put("poster_id", posterId);
        Request request = Request.Post(SysParam.MACALLINE_URL + "poster/GetPosterDetail");
        request.setHeader("Content-Type", "application/x-www-form-urlencoded");
        request.setHeader("redstar-call-app-id", SysParam.MACALLINE_APP_ID);
        request.setHeader("redstar-sign", sign);
        String resp = HttpUtil.fetch(request, param);
        if (!CheckUtil.isEmpty(resp)) {
            JSONObject jsonObject = JSONObject.parseObject(resp);
            if (!CheckUtil.isEmpty(jsonObject)) {
                return jsonObject.getJSONObject("dataMap");
            }
        }
        return null;
    }

    /**
     * 获取券详情
     *
     * @param ticketId
     * @return
     * @throws IOException
     */
    public static JSONObject fecthpTicketDetail(String ticketId) throws IOException {
        Map<String, String> param = new HashMap<String, String>();
        String sign = SignUtil.fetchSign(param);
        param.put("promotion_ticket_id", ticketId);
        Request request = Request.Post(SysParam.MACALLINE_URL + "ticket/GetTicketInfo");
        request.setHeader("Content-Type", "application/x-www-form-urlencoded");
        request.setHeader("redstar-call-app-id", SysParam.MACALLINE_APP_ID);
        request.setHeader("redstar-sign", sign);
        String resp = HttpUtil.fetch(request, param);
        if (!CheckUtil.isEmpty(resp)) {
            JSONObject jsonObject = JSONObject.parseObject(resp);
            if (!CheckUtil.isEmpty(jsonObject)) {
                return jsonObject.getJSONObject("dataMap");
            }
        }
        return null;
    }

    /**
     * 获取微信openid
     *
     * @param unionId
     * @return
     * @throws IOException
     */
    public static JSONObject getWxOpenidByUnionid(String unionId) throws IOException {
        Map<String, String> param = new HashMap<String, String>();
        String sign = SignUtil.fetchSign(param);
        param.put("union_id", unionId);
        Request request = Request.Post(SysParam.MACALLINE_URL + "user/GetWxOpenidByUnionid");
        request.setHeader("Content-Type", "application/x-www-form-urlencoded");
        request.setHeader("redstar-call-app-id", SysParam.MACALLINE_APP_ID);
        request.setHeader("redstar-sign", sign);
        String resp = HttpUtil.fetch(request, param);
        if (!CheckUtil.isEmpty(resp)) {
            JSONObject jsonObject = JSONObject.parseObject(resp);
            if (!CheckUtil.isEmpty(jsonObject)) {
                return jsonObject.getJSONObject("dataMap");
            }
        }
        return null;
    }

    /**
     * 获取导购
     *
     * @param unionId
     * @param openid
     * @return
     * @throws IOException
     */
    public static JSONObject getEmployee(String unionId, String openid) throws IOException {
        Map<String, String> param = new HashMap<String, String>();
        String sign = SignUtil.fetchSign(param);
        param.put("union_id", unionId);
        param.put("openid", openid);
        Request request = Request.Post(SysParam.MACALLINE_URL + "user/GetEmployee");
        request.setHeader("Content-Type", "application/x-www-form-urlencoded");
        request.setHeader("redstar-call-app-id", SysParam.MACALLINE_APP_ID);
        request.setHeader("redstar-sign", sign);
        String resp = HttpUtil.fetch(request, param);
        if (!CheckUtil.isEmpty(resp)) {
            JSONObject jsonObject = JSONObject.parseObject(resp);
            if (!CheckUtil.isEmpty(jsonObject)) {
                return jsonObject.getJSONObject("dataMap");
            }
        }
        return null;
    }

    /**
     * 数据同步-解绑导购
     *
     * @param employeeId
     * @return
     * @throws IOException
     */
    public static JSONObject unBindEmployee(Integer employeeId) throws IOException {
        Map<String, String> param = new HashMap<String, String>();
        String sign = SignUtil.fetchSign(param);
        param.put("Id", employeeId.toString());
        Request request = Request.Post(SysParam.MACALLINE_URL + "foreign/UpdateEmployee");
        request.setHeader("Content-Type", "application/x-www-form-urlencoded");
        request.setHeader("redstar-call-app-id", SysParam.MACALLINE_APP_ID);
        request.setHeader("redstar-sign", sign);
        String resp = HttpUtil.fetch(request, param);
        if (!CheckUtil.isEmpty(resp)) {
            JSONObject jsonObject = JSONObject.parseObject(resp);
            if (!CheckUtil.isEmpty(jsonObject)) {
                return jsonObject;
            }
        }
        return null;
    }

    /**
     * 微信电子海报h5二维码接口
     *
     * @param posterId
     * @param openId
     * @return
     * @throws IOException
     */
    public static JSONObject getPosterH5QrCode(String posterId, String openId, String channeld) throws IOException {
        Map<String, String> param = new HashMap<String, String>();
        String sign = SignUtil.fetchSign(param);
        param.put("poster_id", posterId);
        param.put("open_id", openId);
        param.put("channel_id", channeld);
        Request request = Request.Post(SysParam.MACALLINE_URL + "QrCode/GetPosterH5QrCode");
        request.setHeader("Content-Type", "application/x-www-form-urlencoded");
        request.setHeader("redstar-call-app-id", SysParam.MACALLINE_APP_ID);
        request.setHeader("redstar-sign", sign);
        String resp = HttpUtil.fetch(request, param);
        if (!CheckUtil.isEmpty(resp)) {
            JSONObject jsonObject = JSONObject.parseObject(resp);
            if (!CheckUtil.isEmpty(jsonObject)) {
                LOGGER.info(Constant.MSG_INFO_GETPOSTERH5QRCODE, posterId, openId, channeld, jsonObject);
                return jsonObject;
            }
        }
        return null;
    }

    /**
     * 微信渠道二维码接口
     *
     * @param openId
     * @return
     * @throws IOException
     */
    public static JSONObject getWxSenceQrCode(String openId) throws IOException {
        Map<String, String> param = new HashMap<String, String>();
        String sign = SignUtil.fetchSign(param);
        param.put("open_id", openId);
        Request request = Request.Post(SysParam.MACALLINE_URL + "QrCode/GetWxSenceQrCode");
        request.setHeader("Content-Type", "application/x-www-form-urlencoded");
        request.setHeader("redstar-call-app-id", SysParam.MACALLINE_APP_ID);
        request.setHeader("redstar-sign", sign);
        String resp = HttpUtil.fetch(request, param);
        if (!CheckUtil.isEmpty(resp)) {
            JSONObject jsonObject = JSONObject.parseObject(resp);
            if (!CheckUtil.isEmpty(jsonObject)) {
                LOGGER.info(Constant.MSG_INFO_GETWXSENCEQRCODE, openId, jsonObject);
                return jsonObject;
            }
        }
        return null;
    }

    /**
     * 获取App渠道码
     *
     * @param cell
     * @return
     * @throws IOException
     */
    public static JSONObject getAppQrCode(String cell) throws IOException {
        Map<String, String> param = new HashMap<String, String>();
        String sign = SignUtil.fetchSign(param);
        param.put("cell", cell);
        Request request = Request.Post(SysParam.MACALLINE_URL + "QrCode/GetAppQrCode");
        request.setHeader("Content-Type", "application/x-www-form-urlencoded");
        request.setHeader("redstar-call-app-id", SysParam.MACALLINE_APP_ID);
        request.setHeader("redstar-sign", sign);
        String resp = HttpUtil.fetch(request, param);
        if (!CheckUtil.isEmpty(resp)) {
            JSONObject jsonObject = JSONObject.parseObject(resp);
            if (!CheckUtil.isEmpty(jsonObject)) {
                LOGGER.info(Constant.MSG_INFO_GETAPPQRCODE, jsonObject);
                return jsonObject;
            }
        }
        return null;
    }

    /**
     * 图片编辑器时时查询接口(只有当天数据)
     *
     * @param channelId
     * @return
     * @throws IOException
     */
    public static JSONObject getChannelReport(String channelId) throws IOException {
        Map<String, String> param = new HashMap<String, String>();
        String sign = SignUtil.fetchSign(param);
        param.put("channel_id", channelId);
        Request request = Request.Post(SysParam.MACALLINE_URL + "foreign/GetChannelReport");
        request.setHeader("Content-Type", "application/x-www-form-urlencoded");
        request.setHeader("redstar-call-app-id", SysParam.MACALLINE_APP_ID);
        request.setHeader("redstar-sign", sign);
        String resp = HttpUtil.fetch(request, param);
        if (!CheckUtil.isEmpty(resp)) {
            JSONObject jsonObject = JSONObject.parseObject(resp);
            if (!CheckUtil.isEmpty(jsonObject)) {
                LOGGER.info(Constant.MSG_INFO_GETCHANNELREPORT, jsonObject);
                return jsonObject;
            }
        }
        return null;
    }

    /**
     * 图片编辑器历史查询接口(排除当天数据)
     *
     * @param channelId
     * @param beginDate
     * @param endDate
     * @return
     * @throws IOException
     */
    public static JSONObject GetChannelReportByDate(Integer channelId, Date beginDate, Date endDate) throws IOException {
        if (CheckUtil.isEmpty(channelId) || CheckUtil.isEmpty(beginDate) || CheckUtil.isEmpty(endDate)) {
            return null;
        }
        Map<String, String> param = new HashMap<String, String>();
        String sign = SignUtil.fetchSign(param);
        param.put("channel_id", String.valueOf(channelId));
        param.put("begin_date", DateNewUtil.formatDate(beginDate));
        param.put("end_date", DateNewUtil.formatDate(endDate));
        Request request = Request.Post(SysParam.MACALLINE_URL + "foreign/GetChannelReportByDate");
        request.setHeader("Content-Type", "application/x-www-form-urlencoded");
        request.setHeader("redstar-call-app-id", SysParam.MACALLINE_APP_ID);
        request.setHeader("redstar-sign", sign);
        String resp = HttpUtil.fetch(request, param);
        if (!CheckUtil.isEmpty(resp)) {
            JSONObject jsonObject = JSONObject.parseObject(resp);
            if (!CheckUtil.isEmpty(jsonObject)) {
                LOGGER.info(Constant.MSG_INFO_GETCHANNELREPORTBYDATE, JSONObject.toJSONString(param), jsonObject);
                return jsonObject;
            }
        }
        return null;
    }

    /**
     * 获取疯狂H5列表
     *
     * @param omsCode 不传，是查询总部
     * @return
     * @throws IOException
     */
    public static JSONObject getActH5List(String omsCode) throws IOException {
        Map<String, String> param = new HashMap<String, String>();
        String sign = SignUtil.fetchSign(param);
        param.put("unit_code", omsCode);
        Request request = Request.Post(SysParam.MACALLINE_URL + "H5/GetActH5List");
        request.setHeader("Content-Type", "application/x-www-form-urlencoded");
        request.setHeader("redstar-call-app-id", SysParam.MACALLINE_APP_ID);
        request.setHeader("redstar-sign", sign);
        String resp = HttpUtil.fetch(request, param);
        LOGGER.info(Constant.MSG_INFO_GETACTH5LIST, JSONObject.toJSONString(param), resp);
        if (!CheckUtil.isEmpty(resp)) {
            JSONObject jsonObject = JSONObject.parseObject(resp);
            if (!CheckUtil.isEmpty(jsonObject)) {
                return jsonObject;
            }
        }
        return null;
    }

    /**
     * 获取外部H5列表
     *
     * @return
     * @throws IOException
     */
    public static JSONObject getWBActList() throws IOException {
        Map<String, String> param = new HashMap<String, String>();
        String sign = SignUtil.fetchSign(param);
        Request request = Request.Post(SysParam.MACALLINE_URL + "H5/GetWBActList");
        request.setHeader("Content-Type", "application/x-www-form-urlencoded");
        request.setHeader("redstar-call-app-id", SysParam.MACALLINE_APP_ID);
        request.setHeader("redstar-sign", sign);
        String resp = HttpUtil.fetch(request, param);
        LOGGER.info(Constant.MSG_INFO_GETWBACTLIST, JSONObject.toJSONString(param), resp);
        if (!CheckUtil.isEmpty(resp)) {
            JSONObject jsonObject = JSONObject.parseObject(resp);
            if (!CheckUtil.isEmpty(jsonObject)) {
                return jsonObject;
            }
        }
        return null;
    }

    /**
     * 获取疯狂H5的二维码
     *
     * @param actId   疯狂H5的ID
     * @param unionid 导购员的unionid
     * @return
     * @throws IOException
     */
    public static JSONObject getActH5QrCode(String actId, String unionid) throws IOException {
        if (CheckUtil.isEmpty(actId) || CheckUtil.isEmpty(unionid)) {
            return null;
        }
        Map<String, String> param = new HashMap<String, String>();
        String sign = SignUtil.fetchSign(param);
        param.put("act_id", actId);
        param.put("unionid", unionid);
        Request request = Request.Post(SysParam.MACALLINE_URL + "QrCode/GetActH5QrCode");
        request.setHeader("Content-Type", "application/x-www-form-urlencoded");
        request.setHeader("redstar-call-app-id", SysParam.MACALLINE_APP_ID);
        request.setHeader("redstar-sign", sign);
        String resp = HttpUtil.fetch(request, param);
        LOGGER.info(Constant.MSG_INFO_GETACTH5QRCODE, JSONObject.toJSONString(param), resp);
        if (!CheckUtil.isEmpty(resp)) {
            JSONObject jsonObject = JSONObject.parseObject(resp);
            if (!CheckUtil.isEmpty(jsonObject)) {
                return jsonObject;
            }
        }
        return null;
    }

    /**
     * 获取外部H5的二维码
     *
     * @param actId   疯狂H5的ID
     * @param unionid 导购员的unionid
     * @return
     * @throws IOException
     */
    public static JSONObject getWBActQrCode(String actId, String unionid) throws IOException {
        if (CheckUtil.isEmpty(actId) || CheckUtil.isEmpty(unionid)) {
            return null;
        }
        Map<String, String> param = new HashMap<String, String>();
        String sign = SignUtil.fetchSign(param);
        param.put("act_id", actId);
        param.put("unionid", unionid);
        Request request = Request.Post(SysParam.MACALLINE_URL + "QrCode/GetWBActQrCode");
        request.setHeader("Content-Type", "application/x-www-form-urlencoded");
        request.setHeader("redstar-call-app-id", SysParam.MACALLINE_APP_ID);
        request.setHeader("redstar-sign", sign);
        String resp = HttpUtil.fetch(request, param);
        LOGGER.info(Constant.MSG_INFO_GETWBACTQRCODE, JSONObject.toJSONString(param), resp);
        if (!CheckUtil.isEmpty(resp)) {
            JSONObject jsonObject = JSONObject.parseObject(resp);
            if (!CheckUtil.isEmpty(jsonObject)) {
                return jsonObject;
            }
        }
        return null;
    }


    public static void main(String args[]) throws IOException, ParseException {
        Date date = DateNewUtil.parseAnyString("2018-08-20 00:00:00");
        Date date1 = DateNewUtil.parseAnyString("2018-09-04 00:00:00");
        System.err.println(postList("1009"));
        System.err.println(actPosterList("1009"));
    }


}
