package com.wk.wechat4j.base.payment;

import com.alibaba.fastjson.annotation.JSONField;
import com.wk.wechat4j.base.type.SignType;

import javax.xml.bind.annotation.*;
import java.io.Serializable;

/**
 * ������Ϣ
 *
 * @className PayBaseInfo
 * @author jy
 * @date 2014��11��5��
 * @since JDK 1.6
 * @see
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class PayBaseInfo implements Serializable {

	private static final long serialVersionUID = 1843024880782466990L;

	/**
	 * ���ں�ID
	 */
	@JSONField(name = "appId")
	@XmlElement(name = "AppId")
	private String appId;
	/**
	 * ʱ���
	 */
	@JSONField(name = "timeStamp")
	@XmlElement(name = "TimeStamp")
	private String timeStamp;
	/**
	 * ����ַ���
	 */
	@JSONField(name = "nonceStr")
	@XmlElement(name = "NonceStr")
	private String nonceStr;
	/**
	 * ǩ�����
	 */
	@JSONField(name = "paySign")
	@XmlElement(name = "AppSignature")
	private String paySign;
	/**
	 * ǩ����ʽ
	 */
	@JSONField(name = "signType")
	@XmlElement(name = "SignMethod")
	private String signType;

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}

	public String getNonceStr() {
		return nonceStr;
	}

	public void setNonceStr(String nonceStr) {
		this.nonceStr = nonceStr;
	}

	public String getPaySign() {
		return paySign;
	}

	public void setPaySign(String paySign) {
		this.paySign = paySign;
	}

	public String getSignType() {
		return signType;
	}

	@XmlTransient
	@JSONField(serialize = false)
	public SignType getFormatSignType() {
		return signType != null ? SignType.valueOf(signType.toUpperCase())
				: null;
	}

	public void setSignType(SignType signType) {
		this.signType = signType != null ? signType.name() : null;
	}

	public PayBaseInfo() {
	}

	public PayBaseInfo(String appId, String timestamp, String noncestr) {
		this.appId = appId;
		this.timeStamp = timestamp;
		this.nonceStr = noncestr;
	}

	@Override
	public String toString() {
		return "appId=" + appId + ", timeStamp=" + timeStamp + ", nonceStr="
				+ nonceStr + ", paySign=" + paySign + ", signType=" + signType;
	}
}
