package com.wk.wechat4j.base.http;

import java.io.InputStream;

/**
 * HTTP ��Ӧ
 *
 * @className HttpResponse
 * @author jy
 * @date 2015��5��30��
 * @since JDK 1.6
 * @see
 */
public interface HttpResponse extends HttpMessage {
	/**
	 * HTTPЭ��
	 *
	 * @return
	 */
	HttpVersion getProtocol();

	/**
	 * ��Ӧ״̬
	 *
	 * @return
	 */
	HttpStatus getStatus();

	/**
	 * ��Ӧ����
	 *
	 * @return
	 */
	InputStream getBody();

	/**
	 * ��Ӧ����
	 *
	 * @return
	 */
	byte[] getContent();

	/**
	 * �ͷ���Դ
	 */
	void close();
}
