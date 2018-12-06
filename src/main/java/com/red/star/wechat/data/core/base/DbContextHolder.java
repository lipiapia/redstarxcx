package com.red.star.wechat.data.core.base;

/**
 * Created by xulonglong on 15/11/5.
 */
public class DbContextHolder {

	private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();

	public static void setDbType(String dbType) {
		contextHolder.set(dbType);
	}

	public static String getDbType() {
		return ((String) contextHolder.get());
	}

	public static void clearDbType() {
		contextHolder.remove();
	}

}