package com.wk.wechat4j.base.payment.mch;

import com.alibaba.fastjson.annotation.JSONField;
import com.wk.wechat4j.base.model.WeixinPayAccount;
import com.wk.wechat4j.base.payment.PayPackage;
import com.wk.wechat4j.base.type.TradeType;
import com.wk.wechat4j.base.util.RandomUtil;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

/**
 * JS֧����������
 *
 * @className MchPayPackage
 * @author jy
 * @date 2014��10��21��
 * @since JDK 1.6
 * @see
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class MchPayPackage extends PayPackage {

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
	 * ǩ�� <font color="red">�������������</font>
	 */
	private String sign;
	/**
	 * ��������JSAPI��NATIVE��APP ����
	 */
	@XmlElement(name = "trade_type")
	@JSONField(name = "trade_type")
	private String tradeType;
	/**
	 * �û����̻� appid �µ�Ψһ ��ʶ, trade_type Ϊ JSAPI ʱ,�˲����ش�
	 */
	@XmlElement(name = "openid")
	@JSONField(name = "openid")
	private String openId;
	/**
	 * ֻ�� trade_type Ϊ NATIVE �ҡ�ģʽһ�� ʱ��Ҫ��д �Ǳ���
	 */
	@XmlElement(name = "product_id")
	@JSONField(name = "product_id")
	private String productId;
	/**
	 * ָ��֧����ʽ:no_credit--ָ������ʹ�����ÿ�֧��
	 */
	@XmlElement(name = "limit_pay")
	@JSONField(name = "limit_pay")
	private String limitPay;

	protected MchPayPackage() {
		// jaxb required
	}

	public MchPayPackage(WeixinPayAccount weixinAccount, String openId,
			String body, String outTradeNo, double totalFee, String notifyUrl,
			String createIp, TradeType tradeType) {
		this(weixinAccount, openId, body, outTradeNo, totalFee, notifyUrl,
				createIp, tradeType, null);
	}

	public MchPayPackage(WeixinPayAccount weixinAccount, String openId,
			String body, String outTradeNo, double totalFee, String notifyUrl,
			String createIp, TradeType tradeType, String attach) {
		this(weixinAccount.getId(), weixinAccount.getMchId(), weixinAccount
				.getDeviceInfo(), body, outTradeNo, totalFee, notifyUrl,
				createIp, tradeType, openId, attach, null, null, null, null,
				null);
	}

	public MchPayPackage(String appId, String mchId, String deviceInfo,
			String body, String outTradeNo, double totalFee, String notifyUrl,
			String createIp, TradeType tradeType, String openId, String attach,
			Date timeStart, Date timeExpire, String goodsTag, String productId,
			String limitPay) {
		super(body, outTradeNo, totalFee, notifyUrl, createIp, attach,
				timeStart, timeExpire, goodsTag);
		this.appId = appId;
		this.mchId = mchId;
		this.deviceInfo = deviceInfo;
		this.nonceStr = RandomUtil.generateString(16);
		this.tradeType = tradeType.name();
		this.openId = openId;
		this.productId = productId;
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

	public String getTradeType() {
		return tradeType;
	}

	public String getOpenId() {
		return openId;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getLimitPay() {
		return limitPay;
	}

	public void setLimitPay(String limitPay) {
		this.limitPay = limitPay;
	}

	@Override
	public String toString() {
		return "MchPayPackage [appId=" + appId + ", mchId=" + mchId
				+ ", deviceInfo=" + deviceInfo + ", nonceStr=" + nonceStr
				+ ", sign=" + sign + ", tradeType=" + tradeType + ", openId="
				+ openId + ", productId=" + productId + ", " + super.toString()
				+ "]";
	}
}
