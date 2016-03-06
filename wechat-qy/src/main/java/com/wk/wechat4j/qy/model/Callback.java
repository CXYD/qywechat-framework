package com.wk.wechat4j.qy.model;

import java.io.Serializable;

import com.alibaba.fastjson.annotation.JSONCreator;
import com.alibaba.fastjson.annotation.JSONField;

/**
 * ����ĳЩ�ӿ�ʱ����Ļص���Ϣ
 *
 * @className Callback
 * @author jy
 * @date 2015��3��30��
 * @since JDK 1.6
 * @see
 */
public class Callback implements Serializable {

	private static final long serialVersionUID = 8575808461248605317L;

	/**
	 * ��ҵӦ�ý�����ҵ����������ķ���Э��͵�ַ��֧��http��httpsЭ��
	 */
	private String url;
	/**
	 * ��������ǩ��
	 */
	private String token;
	/**
	 * ������Ϣ��ļ��ܣ���AES��Կ��Base64����
	 */
	@JSONField(name = "encodingaeskey")
	private String aesKey;

	@JSONCreator
	public Callback(@JSONField(name = "url") String url,
			@JSONField(name = "token") String token,
			@JSONField(name = "aesKey") String aesKey) {
		this.url = url;
		this.token = token;
		this.aesKey = aesKey;
	}

	public String getUrl() {
		return url;
	}

	public String getToken() {
		return token;
	}

	public String getAesKey() {
		return aesKey;
	}

	@Override
	public String toString() {
		return "Callback [url=" + url + ", token=" + token + ", aesKey="
				+ aesKey + "]";
	}
}
