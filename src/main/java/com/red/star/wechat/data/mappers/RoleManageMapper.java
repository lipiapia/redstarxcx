package com.red.star.wechat.data.mappers;

import com.red.star.wechat.data.core.base.MyMapper;
import com.red.star.wechat.data.entity.vo.RoleInfo;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface RoleManageMapper extends MyMapper<RoleInfo> {

    @Select("SELECT * FROM t_role ")
    List<RoleInfo> selectRoleInfo();
}
