package com.red.star.wechat.data.core.base;


import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * Created by xulonglong on 15/11/5.
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

	@Override
	protected Object determineCurrentLookupKey() {
		return DbContextHolder.getDbType();
	}

}