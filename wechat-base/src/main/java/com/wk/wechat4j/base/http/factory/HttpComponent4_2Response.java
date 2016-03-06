package com.wk.wechat4j.base.http.factory;

import com.wk.wechat4j.base.http.AbstractHttpResponse;
import com.wk.wechat4j.base.http.HttpHeaders;
import com.wk.wechat4j.base.http.HttpStatus;
import com.wk.wechat4j.base.http.HttpVersion;
import org.apache.http.Header;
import org.apache.http.ProtocolVersion;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;

import java.io.IOException;


/**
 * HttpComponent4 Response:Requires Apache HttpComponents 4.3 or higher
 * 
 * @className HttpComponent4_2Response
 * @author jy
 * @date 2015��8��18��
 * @since JDK 1.6
 * @see
 */
public class HttpComponent4_2Response extends AbstractHttpResponse {

	private final CloseableHttpResponse httpResponse;

	private HttpHeaders headers;
	private HttpVersion protocol;
	private HttpStatus status;

	public HttpComponent4_2Response(CloseableHttpResponse httpResponse,
			byte[] content) {
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
			EntityUtils.consume(httpResponse.getEntity());
			httpResponse.close();
		} catch (IOException ex) {
			;
		}
	}
}
