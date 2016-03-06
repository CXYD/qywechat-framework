package com.wk.wechat4j.base.payment;

import com.alibaba.fastjson.annotation.JSONField;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


/**
 * JSAPI֧���ص�ʱ��POST��Ϣ
 *
 * @className JsPayNotify
 * @author jy
 * @date 2014��8��19��
 * @since JDK 1.6
 * @see
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class JsPayNotify extends PayBaseInfo {

	private static final long serialVersionUID = -4659030958445259803L;

	/**
	 * �û���openid
	 */
	@JSONField(name = "OpenId")
	@XmlElement(name = "OpenId")
	private String openId;
	/**
	 * �Ƿ��ע���ں�
	 */
	@JSONField(name = "IsSubscribe")
	@XmlElement(name = "IsSubscribe")
	private int isSubscribe;

	public JsPayNotify() {

	}

	public String getOpenId() {
		return openId;
	}

	public int getIsSubscribe() {
		return isSubscribe;
	}

	@JSONField(serialize = false)
	public boolean getFormatIsSubscribe() {
		return isSubscribe == 1;
	}

	@Override
	public String toString() {
		return "openId=" + openId + ", isSubscribe=" + getFormatIsSubscribe()
				+ ", " + super.toString();
	}
}
