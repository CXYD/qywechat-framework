package com.wk.wechat4j.base.http;

/**
 * HTTP messages consist of requests from client to server and responses from
 * server to client.
 * 
 * @className HttpMessage
 * @author jy
 * @date 2015��5��29��
 * @since JDK 1.6
 * @see
 */
public interface HttpMessage {
	/**
	 * HTTP��ͷ
	 * 
	 * @return
	 */
	HttpHeaders getHeaders();
}
