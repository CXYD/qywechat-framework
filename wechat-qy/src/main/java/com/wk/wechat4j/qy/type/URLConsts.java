package com.wk.wechat4j.qy.type;

/**
 * URL������
 *
 * @className URLConsts
 * @author jy
 * @date 2014��12��3��
 * @since JDK 1.6
 * @see
 */
public final class URLConsts {
	/**
	 *
	 */
	public static final String BASE_URL = "https://qyapi.weixin.qq.com/cgi-bin";
	/**
	 * ��ҵ�Ż�ȡtoken��url
	 */
	public static final String ASSESS_TOKEN_URL = BASE_URL
			+ "/gettoken?corpid=%s&corpsecret=%s";
	/**
	 * ��ҵ���ṩ�̻�ȡtoken��url
	 */
	public static final String PROVIDER_TOKEN_URL = BASE_URL
			+ "/service/get_provider_token";
	/**
	 * ��ҵ��jssdk��ȡtoken��url
	 */
	public static final String JS_TICKET_URL = BASE_URL
			+ "/get_jsapi_ticket?access_token=%s";
	/**
	 * ��ҵ�Ż�ȡticket��url
	 */
	public static final String TICKET_URL = BASE_URL
			+ "/ticket/get?access_token=%s&type=%s";
	/**
	 * ��ҵ�ŵ�����Ӧ���׼���ȡtoken��url
	 */
	public static final String SUITE_TOKEN_URL = BASE_URL
			+ "/service/get_suite_token";

	/**
	 * ��ҵ�ŵ�����Ӧ���׼���ȡԤ��Ȩ���url
	 */
	public static final String SUITE_PRE_CODE_URL = BASE_URL
			+ "/service/get_pre_auth_code?suite_access_token=%s";

	/**
	 * ��ҵ�ŵ�����Ӧ���׼���ȡ��ҵ��access_token��url
	 */
	public static final String TOKEN_SUITE_URL = BASE_URL
			+ "/service/get_corp_token?suite_access_token=%s";
}
