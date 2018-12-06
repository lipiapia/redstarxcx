package com.red.star.wechat.data.mappers;


import com.red.star.wechat.data.core.base.MyMapper;
import com.red.star.wechat.data.entity.AccessLog;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface AccessLogMapper extends MyMapper<AccessLog> {

    @Select("SELECT DISTINCT t.open_id FROM `lg_access` t where datediff(NOW(),t.time)<=3 and t.open_id is not null;")
    public List<String> findAccessLogByDay(Integer days);

}
