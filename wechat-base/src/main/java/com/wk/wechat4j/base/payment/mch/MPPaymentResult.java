package com.wk.wechat4j.base.payment.mch;

import com.alibaba.fastjson.annotation.JSONField;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * ��ҵ������
 *
 * @className MPPaymentResult
 * @author jy
 * @date 2015��4��1��
 * @since JDK 1.6
 * @see
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class MPPaymentResult extends ApiResult {

	private static final long serialVersionUID = 1110472826089211646L;

	/**
	 * ΢�Ŷ���������
	 */
	@JSONField(name = "payment_no")
	@XmlElement(name = "payment_no")
	private String transactionId;
	/**
	 * �̻�������
	 */
	@JSONField(name = "partner_trade_no")
	@XmlElement(name = "partner_trade_no")
	private String outTradeNo;
	/**
	 * ֧��ʱ��
	 */
	@JSONField(name = "payment_time")
	@XmlElement(name = "payment_time")
	private String paymentTime;

	protected MPPaymentResult() {
		// jaxb required
	}
	
	public String getTransactionId() {
		return transactionId;
	}

	public String getOutTradeNo() {
		return outTradeNo;
	}

	public String getPaymentTime() {
		return paymentTime;
	}

	@Override
	public String toString() {
		return "MPPaymentResult [transactionId=" + transactionId
				+ ", outTradeNo=" + outTradeNo + ", paymentTime=" + paymentTime
				+ "]";
	}
}
