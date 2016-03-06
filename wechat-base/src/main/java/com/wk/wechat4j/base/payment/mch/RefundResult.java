package com.wk.wechat4j.base.payment.mch;

import com.alibaba.fastjson.annotation.JSONField;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * V3�˿�������
 *
 * @className RefundResult
 * @author jy
 * @date 2014��11��6��
 * @since JDK 1.6
 * @see
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class RefundResult extends RefundDetail {

	private static final long serialVersionUID = -3687863914168618620L;

	/**
	 * ΢�Ŷ�����
	 */
	@XmlElement(name = "transaction_id")
	@JSONField(name = "transaction_id")
	private String transactionId;
	/**
	 * �̻�ϵͳ�ڲ��Ķ�����
	 */
	@XmlElement(name = "out_trade_no")
	@JSONField(name = "out_trade_no")
	private String outTradeNo;

	protected RefundResult() {
		// jaxb required
	}
	
	public String getTransactionId() {
		return transactionId;
	}

	public String getOutTradeNo() {
		return outTradeNo;
	}

	@Override
	public String toString() {
		return "RefundResult [transactionId=" + transactionId + ", outTradeNo="
				+ outTradeNo + ", " + super.toString() + "]";
	}
}
