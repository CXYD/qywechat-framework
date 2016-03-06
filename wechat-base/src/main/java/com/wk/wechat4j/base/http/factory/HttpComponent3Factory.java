package com.wk.wechat4j.base.http.factory;

import org.apache.commons.httpclient.HttpClient;

/**
 * ʹ��Apache��HttpClient3.x
 *
 * @className HttpComponent3Factory
 * @author jy
 * @date 2015��8��12��
 * @since JDK 1.6
 * @see
 */
public class HttpComponent3Factory extends HttpClientFactory {

	public HttpComponent3Factory() {
		// odd code
		Class<HttpClient> _dead = HttpClient.class;
	}

	@Override
	public com.wk.wechat4j.base.http.HttpClient newInstance() {
		return new HttpComponent3(new HttpClient());
	}
}
