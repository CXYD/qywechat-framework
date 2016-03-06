package com.wk.wechat4j.base.http.factory;

import com.wk.wechat4j.base.http.AbstractHttpResponse;
import com.wk.wechat4j.base.http.HttpHeaders;
import com.wk.wechat4j.base.http.HttpStatus;
import com.wk.wechat4j.base.http.HttpVersion;
import org.apache.http.Header;
import org.apache.http.ProtocolVersion;
import org.apache.http.StatusLine;

import java.io.IOException;


/**
 * HttpComponent4 Response:Requires Apache HttpComponents 4.2 or lower
 * 
 * @className HttpComponent4_1Response
 * @author jy
 * @date 2015Äê8ÔÂ18ÈÕ
 * @since JDK 1.6
 * @see
 */
public class HttpComponent4_1Response extends AbstractHttpResponse {
	private final org.apache.http.HttpResponse httpResponse;

	private HttpHeaders headers;
	private HttpVersion protocol;
	private HttpStatus status;

	public HttpComponent4_1Response(org.apache.http.HttpResponse httpResponse,
			byte[] content) throws IOException {
		super(content);
		this.httpResponse = httpResponse;
	}

	@Override
	public HttpHeaders getHeaders() {
		if (headers == null) {
			headers = new HttpHeaders();
			Header[] headers = httpResponse.getAllHeaders();
			for (Header header : headers) {
				this.headers.add(header.getName(), header.getValue());
			}
		}
		return headers;
	}

	@Override
	public HttpVersion getProtocol() {
		if (protocol == null) {
			ProtocolVersion version = httpResponse.getProtocolVersion();
			Header connection = httpResponse.getFirstHeader("Connection");
			protocol = new HttpVersion(version.getProtocol(),
					version.getMajor(), version.getMinor(), connection != null
							&& connection.getValue().equalsIgnoreCase(
									"keep-alive"));
		}
		return protocol;
	}

	@Override
	public HttpStatus getStatus() {
		if (status == null) {
			StatusLine statusLine = httpResponse.getStatusLine();
			status = new HttpStatus(statusLine.getStatusCode(),
					statusLine.getReasonPhrase());
		}
		return status;
	}

	@Override
	public void close() {
		try {
			httpResponse.getEntity().consumeContent();
		} catch (IOException e) {
			;
		}
	}
}
