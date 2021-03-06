package com.wk.wechat4j.base.http;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Simple Response
 * 
 * @className SimpleHttpResponse
 * @author jy
 * @date 2015��8��14��
 * @since JDK 1.6
 * @see
 */
public class SimpleHttpResponse extends AbstractHttpResponse {

	private final HttpURLConnection connection;

	private HttpHeaders headers;
	private HttpVersion protocol;
	private HttpStatus status;

	public SimpleHttpResponse(HttpURLConnection connection, byte[] content) {
		super(content);
		this.connection = connection;
	}

	@Override
	public HttpVersion getProtocol() {
		String version = connection.getHeaderField(null);
		if (version == null) {
			return null;
		}
		if (protocol == null) {
			if (version.contains(HttpVersion.HTTP_1_0_STRING)) {
				protocol = HttpVersion.HTTP_1_0;
			} else if (version.contains(HttpVersion.HTTP_1_1_STRING)) {
				protocol = HttpVersion.HTTP_1_1;
			} else {
				String connect = connection.getHeaderField("Connection");
				protocol = new HttpVersion(version,
						"keep-alive".equalsIgnoreCase(connect));
			}
		}
		return protocol;
	}

	@Override
	public HttpHeaders getHeaders() {
		if (headers == null) {
			headers = new HttpHeaders();
			Map<String, List<String>> headerFields = connection
					.getHeaderFields();
			for (Iterator<Entry<String, List<String>>> headerIterator = headerFields
					.entrySet().iterator(); headerIterator.hasNext();) {
				Entry<String, List<String>> headerEntry = headerIterator.next();
				headers.put(headerEntry.getKey(), headerEntry.getValue());
			}
		}
		return headers;
	}

	@Override
	public HttpStatus getStatus() {
		if (status == null) {
			try {
				status = new HttpStatus(connection.getResponseCode(),
						connection.getResponseMessage());
			} catch (IOException e) {
				throw new RuntimeException("I/O Error on getStatus", e);
			}
		}
		return status;
	}

	@Override
	public void close() {
		connection.disconnect();
	}
}
