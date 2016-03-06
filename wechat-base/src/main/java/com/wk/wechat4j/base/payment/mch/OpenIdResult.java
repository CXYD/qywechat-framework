package com.wk.wechat4j.base.payment.mch;

import com.alibaba.fastjson.annotation.JSONField;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * authcode2openid
 * 
 * @className OpenIdResult
 * @author jy
 * @date 2015��7��23��
 * @since JDK 1.6
 * @see
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class OpenIdResult extends ApiResult {

	private static final long serialVersionUID = 902743989722741814L;

	/**
	 * �û����̻�appid�µ�Ψһ��ʶ
	 */
	@XmlElement(name = "openid")
	@JSONField(name = "openid")
	private String openId;

	public String getOpenId() {
		return openId;
	}

	@Override
	public String toString() {
		return "OpenIdResult [openId=" + openId + ", "
				+ super.toString() + "]";
	}
}
