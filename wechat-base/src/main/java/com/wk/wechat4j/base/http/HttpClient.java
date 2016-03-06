package com.wk.wechat4j.base.http;

import com.wk.wechat4j.base.http.entity.HttpEntity;

import java.util.Set;


/**
 * HTTP �ӿ�
 *
 * @className HttpClient
 * @author jy
 * @date 2015��5��30��
 * @since JDK 1.6
 * @see
 */
public interface HttpClient {

	// get
	HttpResponse get(String url) throws HttpClientException;

	HttpResponse get(String url, URLParameter... parameters)
			throws HttpClientException;

	// head
	HttpHeaders head(String url) throws HttpClientException;

	HttpHeaders head(String url, URLParameter... parameters)
			throws HttpClientException;

	// post
	HttpResponse post(String url) throws HttpClientException;

	HttpResponse post(String url, URLParameter... parameters)
			throws HttpClientException;

	HttpResponse post(String url, HttpEntity httpEntity)
			throws HttpClientException;

	// put
	void put(String url) throws HttpClientException;

	void put(String url, URLParameter... parameters) throws HttpClientException;

	// delete
	void delete(String url) throws HttpClientException;

	void delete(String url, URLParameter... parameters)
			throws HttpClientException;

	// OPTIONS
	Set<HttpMethod> options(String url) throws HttpClientException;

	Set<HttpMethod> options(String url, URLParameter... parameters)
			throws HttpClientException;

	/**
	 * ��������
	 *
	 * @param request
	 *            �������
	 * @return ��Ӧ����
	 * @throws HttpClientException
	 */
	HttpResponse execute(HttpRequest request) throws HttpClientException;
}
