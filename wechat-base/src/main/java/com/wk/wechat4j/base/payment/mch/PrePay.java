package com.wk.wechat4j.base.payment.mch;

import com.wk.wechat4j.base.type.TradeType;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * V3Ԥ������Ϣ
 *
 * @className PrePay
 * @author jy
 * @date 2014��10��21��
 * @since JDK 1.6
 * @see
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class PrePay extends ApiResult {

	private static final long serialVersionUID = -8430005768959715444L;

	/**
	 * ���ýӿ��ύ�Ľ������ͣ�ȡֵ���£�JSAPI��NATIVE��APP��
	 *
	 * @see TradeType
	 */
	@XmlElement(name = "trade_type")
	private TradeType tradeType;
	/**
	 * ΢�����ɵ�Ԥ֧���ػ���ʶ�����ں����ӿڵ�����ʹ�ã���ֵ��Ч��Ϊ2Сʱ
	 */
	@XmlElement(name = "prepay_id")
	private String prepayId;
	/**
	 * trade_type Ϊ NATIVE ���� ����,�˲�����ֱ�����ɶ� ά��չʾ��������ɨ��֧�� ����Ϊ��
	 */
	@XmlElement(name = "code_url")
	private String codeUrl;

	protected PrePay() {
		// jaxb required
	}

	public PrePay(String returnCode, String returnMsg) {
		super(returnCode, returnMsg);
	}

	public TradeType getTradeType() {
		return tradeType;
	}

	public void setTradeType(TradeType tradeType) {
		this.tradeType = tradeType;
	}

	public String getPrepayId() {
		return prepayId;
	}

	public void setPrepayId(String prepayId) {
		this.prepayId = prepayId;
	}

	public String getCodeUrl() {
		return codeUrl;
	}

	public void setCodeUrl(String codeUrl) {
		this.codeUrl = codeUrl;
	}

	@Override
	public String toString() {
		return "PrePay [tradeType=" + tradeType + ", prepayId=" + prepayId
				+ ", codeUrl=" + codeUrl + ", " + super.toString() + "]";
	}
}
