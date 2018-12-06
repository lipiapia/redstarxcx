package com.red.star.wechat.data.mappers;

import com.red.star.wechat.data.core.base.MyMapper;
import com.red.star.wechat.data.entity.ApiClient;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @author nofish.yan@gmail.com
 * @date 2018/7/12.
 * api客户端认证数据访问接口
 */
public interface ApiClientMapper extends MyMapper<ApiClient> {

    @Select("SELECT * FROM t_api_client WHERE app_id = #{appId} AND status = 0")
    ApiClient findByAppId(@Param("appId") String appId);

}
