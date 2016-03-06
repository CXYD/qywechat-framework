package com.wk.wechat4j.base.util;

import com.alibaba.fastjson.JSON;
import com.wk.wechat4j.base.model.WeixinAccount;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * ���ں�������Ϣ class·����weixin4j.properties�ļ�
 *
 * @className Weixin4jConfigUtil
 * @author jy
 * @date 2014��10��31��
 * @since JDK 1.6
 * @see
 */
public class Weixin4jConfigUtil {
	private final static String CLASSPATH_PREFIX = "classpath:";
	private final static String CLASSPATH_VALUE;
	private static ResourceBundle weixinBundle;
	static {
		CLASSPATH_VALUE = Thread.currentThread().getContextClassLoader()
				.getResource("").getPath();
		try {
			weixinBundle = ResourceBundle.getBundle("weixin4j");
		} catch (MissingResourceException e) {
			;
		}
	}

	private final static String WEIXIN4J_PREFIX = "weixin4j";

	private static String wrapKeyName(String key) {
		if (!key.startsWith(WEIXIN4J_PREFIX)) {
			return String.format("%s.%s", WEIXIN4J_PREFIX, key);
		}
		return key;
	}

	/**
	 * ��ȡweixin4j.properties�ļ��е�keyֵ
	 *
	 * @param key
	 * @return
	 */
	public static String getValue(String key) {
		String wrapKey = wrapKeyName(key);
		return System.getProperty(wrapKey, weixinBundle.getString(wrapKey));
	}

	/**
	 * key������ʱ�򷵻ش����Ĭ��ֵ
	 *
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	public static String getValue(String key, String defaultValue) {
		String value = defaultValue;
		try {
			value = getValue(key);
			if (StringUtil.isBlank(value)) {
				value = defaultValue;
			}
		} catch (MissingResourceException e) {
			;
		} catch (NullPointerException e) {
			;
		}
		return value;
	}

	/**
	 * �ж������Ƿ����[classpath:]���������ƴ����Ŀ·���󷵻� һ�������ļ��ľ���·����ȡ
	 * 
	 * @param key
	 * @return
	 */
	public static String getClassPathValue(String key) {
		return getValue(key).replaceFirst(CLASSPATH_PREFIX, CLASSPATH_VALUE);
	}

	/**
	 * 
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	public static String getClassPathValue(String key, String defaultValue) {
		return getValue(key, defaultValue).replaceFirst(CLASSPATH_PREFIX,
				CLASSPATH_VALUE);
	}

	public static WeixinAccount getWeixinAccount() {
		WeixinAccount account = null;
		try {
			account = JSON
					.parseObject(getValue("account"), WeixinAccount.class);
		} catch (NullPointerException e) {
			System.err
					.println("'weixin4j.account' key not found in weixin4j.properties.");
		} catch (MissingResourceException e) {
			System.err
					.println("'weixin4j.account' key not found in weixin4j.properties.");
		}
		return account;
	}
}
