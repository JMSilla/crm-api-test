package com.jmsilla.crmapitest.api.utils;

public class EnvUtils {
	public static String getContextPath() {
		return System.getenv("SERVER_SERVLET_CONTEXT_PATH");
	}
}
