package com.wk.wechat4j.base.http;

import com.wk.wechat4j.base.http.entity.HttpEntity;

import java.net.URI;


/**
 * HTTP ����
 *
 * @className HttpRequest
 * @author jy
 * @date 2015��5��29��
 * @since JDK 1.6
 * @see
 */
public class HttpRequest implements HttpMessage {
	/**
	 * ����ʽ
	 *
	 * @return
	 */
	private final HttpMethod method;

	/**
	 * ����·��
	 *
	 * @return
	 */
	private final URI uri;
	/**
	 * Э�����
	 *
	 * @return
	 */
	private HttpParams params;
	/**
	 * ���ݲ���
	 */
	private HttpEntity entity;
	/**
	 * �����ͷ
	 */
	private HttpHeaders headers;

	public HttpRequest(HttpMethod method, URI uri) {
		this.method = method;
		this.uri = uri;
	}

	public HttpRequest(HttpMethod method, String url) {
		this(method, URI.create(url));
	}

	public HttpMethod getMethod() {
		return method;
	}

	public URI getURI() {
		return uri;
	}

	public HttpParams getParams() {
		return params;
	}

	public void setParams(HttpParams params) {
		this.params = params;
	}

	public HttpEntity getEntity() {
		return entity;
	}

	public void setEntity(HttpEntity entity) {
		this.entity = entity;
	}

	public void setHeaders(HttpHeaders headers) {
		this.headers = headers;
	}

	@Override
	public HttpHeaders getHeaders() {
		return headers;
	}
}
