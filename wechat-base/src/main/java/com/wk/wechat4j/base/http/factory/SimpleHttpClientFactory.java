package com.wk.wechat4j.base.http.factory;


import com.wk.wechat4j.base.http.HttpClient;
import com.wk.wechat4j.base.http.SimpleHttpClient;

/**
 * HttpURLConnection�ļ�ʵ��
 *
 * @className SimpleHttpClientFactory
 * @author jy
 * @date 2015��8��12��
 * @since JDK 1.6
 * @see com.wk.wechat4j.base.http.SimpleHttpClient
 */
public class SimpleHttpClientFactory extends HttpClientFactory {

	@Override
	public HttpClient newInstance() {
		return new SimpleHttpClient();
	}
}
