package com.wk.wechat4j.base.payment.mch;

import com.alibaba.fastjson.annotation.JSONField;
import com.wk.wechat4j.base.type.MPPaymentCheckNameType;
import com.wk.wechat4j.base.util.DateUtil;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

/**
 * ��ҵ�����¼
 *
 * @className MPPaymentRecord
 * @author jy
 * @date 2015��6��23��
 * @since JDK 1.6
 * @see
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class MPPaymentRecord extends ApiResult {

	private static final long serialVersionUID = -1926873539419750498L;

	/**
	 * ΢�Ŷ���������
	 */
	@JSONField(name = "detail_id")
	@XmlElement(name = "detail_id")
	private String transactionId;
	/**
	 * �̻�������
	 */
	@JSONField(name = "partner_trade_no")
	@XmlElement(name = "partner_trade_no")
	private String outTradeNo;
	/**
	 * ����״̬ SUCCESS:ת�˳ɹ� FAILED:ת��ʧ��
	 */
	@JSONField(name = "status")
	@XmlElement(name = "status")
	private String transactionStatus;
	/**
	 * ���ʧ����Ӧ����ԭ��
	 */
	@JSONField(name = "reason")
	@XmlElement(name = "reason")
	private String failureReason;
	/**
	 * �տ��û�openid
	 */
	@JSONField(name = "openid")
	@XmlElement(name = "openid")
	private String openId;
	/**
	 * �տ��û�����
	 */
	@JSONField(name = "transfer_name")
	@XmlElement(name = "transfer_name")
	private String transferName;
	/**
	 * ������(��λΪ��)
	 */
	@JSONField(name = "payment_amount")
	@XmlElement(name = "payment_amount")
	private int paymentAmount;
	/**
	 * ת��ʱ��
	 */
	@JSONField(name = "transfer_time")
	@XmlElement(name = "transfer_time")
	private String transferTime;
	/**
	 * У���û�����ѡ��
	 *
	 * @see com.wk.wechat4j.mp.type.MPPaymentCheckNameType
	 */
	@XmlElement(name = "check_name")
	@JSONField(name = "check_name")
	private String checkNameType;
	/**
	 * ��ҵ����������Ϣ
	 */
	@XmlElement(name = "desc")
	private String desc;
	/**
	 * ʵ����֤��� PASS:ͨ�� FAILED:��ͨ��
	 */
	@JSONField(name = "check_name_result")
	@XmlElement(name = "check_name_result")
	private String checkNameResult;

	protected MPPaymentRecord() {
		// jaxb required
	}

	public String getTransactionId() {
		return transactionId;
	}

	public String getOutTradeNo() {
		return outTradeNo;
	}

	public String getTransactionStatus() {
		return transactionStatus;
	}

	/**
	 * ��ʽ������״̬
	 *
	 * @return
	 */
	@JSONField(serialize = false)
	public boolean getFormatTransactionStatus() {
		return "success".equalsIgnoreCase(transactionStatus);
	}

	public String getFailureReason() {
		return failureReason;
	}

	public String getOpenId() {
		return openId;
	}

	public String getTransferName() {
		return transferName;
	}

	public int getPaymentAmount() {
		return paymentAmount;
	}

	/**
	 * <font color="red">���ýӿڻ�ȡ��λΪ��,get����ת��ΪԪ����ʹ��</font>
	 *
	 * @return Ԫ��λ
	 */
	@JSONField(serialize = false)
	public double getFormatPaymentAmount() {
		return paymentAmount / 100d;
	}

	public String getTransferTime() {
		return transferTime;
	}

	/**
	 * ��ʽ��ת��ʱ��
	 *
	 * @return
	 */
	@JSONField(serialize = false)
	public Date getFormatTransferTime() {
		return transferTime != null ? DateUtil
				.parse2yyyyMMddHHmmss(transferTime) : null;
	}

	public String getCheckNameType() {
		return checkNameType;
	}

	@JSONField(serialize = false)
	public MPPaymentCheckNameType getFormatCheckNameType() {
		return checkNameType != null ? MPPaymentCheckNameType
				.valueOf(checkNameType) : null;
	}

	public String getDesc() {
		return desc;
	}

	public String getCheckNameResult() {
		return checkNameResult;
	}

	/**
	 * ��ʽ������״̬
	 * 
	 * @return
	 */
	@JSONField(serialize = false)
	public boolean getFormatCheckNameResult() {
		return "pass".equalsIgnoreCase(checkNameResult);
	}

	@Override
	public String toString() {
		return "MPPaymentRecord [transactionId=" + transactionId
				+ ", outTradeNo=" + outTradeNo + ", transactionStatus="
				+ getFormatTransactionStatus() + ", failureReason="
				+ failureReason + ", openId=" + openId + ", transferName="
				+ transferName + ", paymentAmount=" + getFormatPaymentAmount()
				+ ", transferTime=" + transferTime + ", checkNameType="
				+ checkNameType + ", desc=" + desc + ", checkNameResult="
				+ getFormatCheckNameResult() + ", " + super.toString() + "]";
	}
}
