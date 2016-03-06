package com.wk.wechat4j.base.model;

import com.alibaba.fastjson.annotation.JSONCreator;
import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;

/**
 * ΢���˺���Ϣ
 *
 * @className WeixinAccount
 * @author jy
 * @date 2014��11��18��
 * @since JDK 1.6
 * @see
 */
public class WeixinAccount implements Serializable {

	private static final long serialVersionUID = -6001008896414323534L;

	/**
	 * Ψһ����ݱ�ʶ
	 */
	private String id;
	/**
	 * ���ýӿڵ���Կ
	 */
	private String secret;

	@JSONCreator
	public WeixinAccount(@JSONField(name = "id") String id,
			@JSONField(name = "secret") String secret) {
		this.id = id;
		this.secret = secret;
	}

	public String getId() {
		return id;
	}

	public String getSecret() {
		return secret;
	}

	@Override
	public String toString() {
		return "id=" + id + ", secret=" + secret;
	}
}
