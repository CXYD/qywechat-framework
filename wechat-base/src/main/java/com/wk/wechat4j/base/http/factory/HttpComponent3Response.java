package com.wk.wechat4j.base.http.factory;

import com.wk.wechat4j.base.http.AbstractHttpResponse;
import com.wk.wechat4j.base.http.HttpHeaders;
import com.wk.wechat4j.base.http.HttpStatus;
import com.wk.wechat4j.base.http.HttpVersion;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.protocol.Protocol;

import java.io.IOException;


/**
 * HttpComponent3 Response
 * 
 * @className HttpComponent3Response
 * @author jy
 * @date 2015��8��17��
 * @since JDK 1.6
 * @see
 */
public class HttpComponent3Response extends AbstractHttpResponse {

	private final HttpMethod httpMethod;

	private HttpHeaders headers;
	private HttpVersion protocol;
	private HttpStatus status;

	public HttpComponent3Response(HttpMethod httpMethod) throws IOException {
		super(httpMethod.getResponseBody());
		this.httpMethod = httpMethod;
	}

	@Override
	public HttpHeaders getHeaders() {
		if (headers == null) {
			headers = new HttpHeaders();
			Header[] headers = httpMethod.getResponseHeaders();
			for (Header header : headers) {
				this.headers.add(header.getName(), header.getValue());
			}
		}
		return headers;
	}

	@Override
	public HttpVersion getProtocol() {
		org.apache.commons.httpclient.HttpVersion version = httpMethod
				.getParams().getVersion();
		if (version == null) {
			return null;
		}
		Header connection = httpMethod.getResponseHeader("Connection");
		if (protocol == null) {
			protocol = new HttpVersion("HTTP", version.getMinor(),
					version.getMajor(), connection != null
							&& "keep-alive".equalsIgnoreCase(connection
									.getValue()));
		}
		return protocol;
	}

	@Override
	public HttpStatus getStatus() {
		if (status == null) {
			status = new HttpStatus(httpMethod.getStatusCode(),
					httpMethod.getStatusText());
		}
		return status;
	}

	@Override
	public void close() {
		httpMethod.releaseConnection();
		Protocol.unregisterProtocol("https");
	}
}
