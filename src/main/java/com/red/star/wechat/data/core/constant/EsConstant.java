package com.red.star.wechat.data.core.constant;

public class EsConstant {

    public static final String ACCESS_LOG_NAME = "AccessLog";

    public static final String STAY_LOG_NAME = "StayLog";

    public static final String VIEW_LOG_NAME = "ViewLog";

    /**
     * 添加小程序点击日志
     */
    public static final String VIEW_LOG_URL = "/es/api/addView";

    /**
     * 添加接口请求日志
     */
    public static final String ACCESS_LOG_URL = "/es/api/addAccess";

    /**
     * 查询access日志
     */
    public static final String ACCESS_LOG_URL_QUERY = "/es/search/queryAccess";
}
