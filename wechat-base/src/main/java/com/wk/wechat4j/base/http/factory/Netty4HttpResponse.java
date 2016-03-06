package com.wk.wechat4j.base.http.factory;

import com.wk.wechat4j.base.http.AbstractHttpResponse;
import com.wk.wechat4j.base.http.HttpHeaders;
import com.wk.wechat4j.base.http.HttpStatus;
import com.wk.wechat4j.base.http.HttpVersion;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpResponseStatus;

import java.util.Map;


/**
 * Netty Respone::Requires Netty 4.x or higher
 * 
 * @className Netty4HttpResponse
 * @author jy
 * @date 2015Äê8ÔÂ30ÈÕ
 * @since JDK 1.6
 * @see
 */
public class Netty4HttpResponse extends AbstractHttpResponse {

	private final ChannelHandlerContext context;
	private final FullHttpResponse response;

	private HttpVersion protocol;
	private HttpStatus status;
	private volatile HttpHeaders headers;

	public Netty4HttpResponse(ChannelHandlerContext context,
			FullHttpResponse response, byte[] content) {
		super(content);
		this.context = context;
		this.response = response;
		this.response.retain();
	}

	@Override
	public HttpHeaders getHeaders() {
		if (this.headers == null) {
			HttpHeaders headers = new HttpHeaders();
			for (Map.Entry<String, String> entry : this.response.headers()) {
				headers.add(entry.getKey(), entry.getValue());
			}
			this.headers = headers;
		}
		return this.headers;
	}

	@Override
	public HttpVersion getProtocol() {
		if (protocol == null) {
			io.netty.handler.codec.http.HttpVersion version = response
					.getProtocolVersion();
			this.protocol = new HttpVersion(version.protocolName(),
					version.majorVersion(), version.majorVersion(),
					version.isKeepAliveDefault());
		}
		return protocol;
	}

	@Override
	public HttpStatus getStatus() {
		if (status == null) {
			HttpResponseStatus status = response.getStatus();
			this.status = new HttpStatus(status.code(), status.reasonPhrase());
		}
		return status;
	}

	@Override
	public void close() {
		this.response.release();
		this.context.close();
	}
}
