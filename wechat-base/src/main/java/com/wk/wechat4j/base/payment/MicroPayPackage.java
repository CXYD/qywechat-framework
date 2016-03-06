package com.wk.wechat4j.base.payment;

import com.alibaba.fastjson.annotation.JSONField;
import com.wk.wechat4j.base.model.WeixinPayAccount;
import com.wk.wechat4j.base.util.RandomUtil;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

/**
 * ˢ��֧����������
 *
 * @className MicroPayPackage
 * @author jy
 * @date 2014��11��17��
 * @since JDK 1.6
 * @see
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class MicroPayPackage extends PayPackage {

	private static final long serialVersionUID = 8944928173669656177L;
	/**
	 * ΢�ŷ���Ĺ����˺� ����
	 */
	@XmlElement(name = "appid")
	@JSONField(name = "appid")
	private String appId;
	/**
	 * ΢��֧��������̻��� ����
	 */
	@XmlElement(name = "mch_id")
	@JSONField(name = "mch_id")
	private String mchId;
	/**
	 * ΢��֧��������ն��豸�� �Ǳ���
	 */
	@XmlElement(name = "device_info")
	@JSONField(name = "device_info")
	private String deviceInfo;
	/**
	 * ����ַ���,������ 32 λ ����
	 */
	@XmlElement(name = "nonce_str")
	@JSONField(name = "nonce_str")
	private String nonceStr;
	/**
	 * ǩ�� <font color="red">�����߲��ع�ע</font>
	 */
	private String sign;
	/**
	 * ɨ��֧����Ȩ�� ,�豸��ȡ�û�΢���е�������߶�ά����Ϣ
	 */
	@XmlElement(name = "auth_code")
	@JSONField(name = "auth_code")
	private String authCode;
	/**
	 * ָ��֧����ʽ:no_credit--ָ������ʹ�����ÿ�֧��
	 */
	@XmlElement(name = "limit_pay")
	@JSONField(name = "limit_pay")
	private String limitPay;

	protected MicroPayPackage() {
		// jaxb required
	}

	public MicroPayPackage(WeixinPayAccount weixinAccount, String authCode,
			String body, String outTradeNo, double totalFee, String createIp) {
		this(weixinAccount.getId(), weixinAccount.getMchId(), weixinAccount
				.getDeviceInfo(), authCode, body, outTradeNo, totalFee,
				createIp, null, null, null, null, null);
	}

	public MicroPayPackage(String appId, String mchId, String deviceInfo,
			String authCode, String body, String outTradeNo, double totalFee,
			String createIp, String attach, Date timeStart, Date timeExpire,
			String goodsTag, String limitPay) {
		super(body, outTradeNo, totalFee, null, createIp, null, timeStart,
				timeExpire, goodsTag);
		this.appId = appId;
		this.mchId = mchId;
		this.deviceInfo = deviceInfo;
		this.nonceStr = RandomUtil.generateString(16);
		this.authCode = authCode;
		this.limitPay = limitPay;
	}

	public String getAppId() {
		return appId;
	}

	public String getMchId() {
		return mchId;
	}

	public String getDeviceInfo() {
		return deviceInfo;
	}

	public String getNonceStr() {
		return nonceStr;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getAuthCode() {
		return authCode;
	}

	public void setAuthCode(String authCode) {
		this.authCode = authCode;
	}

	public String getLimitPay() {
		return limitPay;
	}

	public void setLimitPay(String limitPay) {
		this.limitPay = limitPay;
	}

	@Override
	public String toString() {
		return "MicroPayPackage [appId=" + appId + ", mchId=" + mchId
				+ ", deviceInfo=" + deviceInfo + ", nonceStr=" + nonceStr
				+ ", sign=" + sign + ", authCode=" + authCode + ", "
				+ super.toString() + "]";
	}
}
