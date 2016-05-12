package com.yunbao.m4.platform.util;

import org.apache.log4j.Logger;

public class LogUtil {
	//protected static final Logger LOG = Logger.getLogger();

	public static Logger getLogger(Class<?> clazz){
		return Logger.getLogger(clazz);
	}
	
	public static void error(Class<?> clazz, String log) {
		Logger LOG = Logger.getLogger(clazz);
		LOG.error(log);
	}

	public static void debug(Class<?> clazz, String log) {
		Logger LOG = Logger.getLogger(clazz);
		LOG.debug(log);
	}
}
