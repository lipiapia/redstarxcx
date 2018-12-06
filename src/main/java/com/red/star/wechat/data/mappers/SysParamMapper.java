package com.red.star.wechat.data.mappers;

import com.red.star.wechat.data.core.base.MyMapper;
import com.red.star.wechat.data.entity.ViewLog;

import java.util.List;
import java.util.Map;

public interface SysParamMapper extends MyMapper<ViewLog> {

	/**
	 * 查询系统参数
	 */
	List<Map<String,String>> findSysParam();
}
