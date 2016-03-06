package com.wk.wechat4j.base.payment.mch;

import com.alibaba.fastjson.annotation.JSONField;
import com.wk.wechat4j.base.type.CurrencyType;
import com.wk.wechat4j.base.xml.ListsuffixResult;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * V3�˿��¼
 *
 * @className RefundRecord
 * @author jy
 * @date 2014��11��1��
 * @since JDK 1.6
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class RefundRecord extends ApiResult {

	private static final long serialVersionUID = -2971132874939642721L;

	/**
	 * ΢�Ŷ�����
	 */
	@XmlElement(name = "transaction_id")
	@JSONField(name = "transaction_id")
	private String transactionId;
	/**
	 * �̻�������
	 */
	@XmlElement(name = "out_trade_no")
	@JSONField(name = "out_trade_no")
	private String outTradeNo;
	/**
	 * �����ܽ��
	 */
	@XmlElement(name = "total_fee")
	@JSONField(name = "total_fee")
	private int totalFee;
	/**
	 * ��������������
	 *
	 * @see com.foxinmy.weixin4j.mp.type.CurrencyType
	 */
	@XmlElement(name = "fee_type")
	@JSONField(name = "fee_type")
	private String feeType;
	/**
	 * �ֽ�֧�����
	 */
	@XmlElement(name = "cash_fee")
	@JSONField(name = "cash_fee")
	private int cashFee;
	/**
	 * �ֽ�֧������������
	 *
	 * @see CurrencyType
	 */
	@XmlElement(name = "cash_fee_type")
	@JSONField(name = "cash_fee_type")
	private String cashFeeType;
	/**
	 * �˿��ܽ��
	 */
	@XmlElement(name = "refund_fee")
	@JSONField(name = "refund_fee")
	private int refundFee;
	/**
	 * ����ȯ�������Ż��˿���=�������-�ֽ��˿��ע�⣺�����������˻�
	 */
	@XmlElement(name = "coupon_refund_fee")
	@JSONField(name = "coupon_refund_fee")
	private Integer couponRefundFee;
	/**
	 * �˿����
	 */
	@XmlElement(name = "refund_count")
	@JSONField(name = "refund_count")
	private int refundCount;
	/**
	 * �˿�����
	 *
	 * @see com.wk.wechat4j.base.payment.mch.RefundDetail
	 */
	@ListsuffixResult({ "^out_refund_no(_\\d)$", "^refund_.*(_\\d)$" })
	private List<RefundDetail> refundList;

	protected RefundRecord() {
		// jaxb required
	}

	public String getTransactionId() {
		return transactionId;
	}

	public String getOutTradeNo() {
		return outTradeNo;
	}

	/**
	 * <font color="red">���ýӿڻ�ȡ��λΪ��,get����ת��ΪԪ����ʹ��</font>
	 *
	 * @return Ԫ��λ
	 */
	@JSONField(serialize = false)
	public double getFormatCashFee() {
		return cashFee / 100d;
	}

	public int getCashFee() {
		return cashFee;
	}

	public String getFeeType() {
		return feeType;
	}

	public String getCashFeeType() {
		return cashFeeType;
	}

	@JSONField(serialize = false)
	public CurrencyType getFormatFeeType() {
		return feeType != null ? CurrencyType.valueOf(feeType.toUpperCase())
				: null;
	}

	@JSONField(serialize = false)
	public CurrencyType getFormatCashFeeType() {
		return cashFeeType != null ? CurrencyType.valueOf(cashFeeType
				.toUpperCase()) : null;
	}

	/**
	 * <font color="red">���ýӿڻ�ȡ��λΪ��,get����ת��ΪԪ����ʹ��</font>
	 *
	 * @return Ԫ��λ
	 */
	@JSONField(serialize = false)
	public double getFormatCouponRefundFee() {
		return couponRefundFee != null ? couponRefundFee.intValue() / 100d : 0d;
	}

	public Integer getCouponRefundFee() {
		return couponRefundFee;
	}

	/**
	 * <font color="red">���ýӿڻ�ȡ��λΪ��,get����ת��ΪԪ����ʹ��</font>
	 *
	 * @return Ԫ��λ
	 */
	@JSONField(serialize = false)
	public double getFormatTotalFee() {
		return totalFee / 100d;
	}

	public int getTotalFee() {
		return totalFee;
	}

	public int getRefundCount() {
		return refundCount;
	}

	public List<RefundDetail> getRefundList() {
		return refundList;
	}

	public void setRefundList(List<RefundDetail> refundList) {
		this.refundList = refundList;
	}

	public int getRefundFee() {
		return refundFee;
	}

	/**
	 * <font color="red">���ýӿڻ�ȡ��λΪ��,get����ת��ΪԪ����ʹ��</font>
	 *
	 * @return Ԫ��λ
	 */
	@JSONField(serialize = false)
	public double getFormatRefundFee() {
		return refundFee / 100d;
	}

	@Override
	public String toString() {
		return "RefundRecord [transactionId=" + transactionId + ", outTradeNo="
				+ outTradeNo + ", totalFee=" + getFormatTotalFee()
				+ ", feeType=" + feeType + ", cashFee=" + getFormatCashFee()
				+ ", cashFeeType=" + cashFeeType + ", refundFee="
				+ getFormatRefundFee() + ", couponRefundFee="
				+ getFormatCouponRefundFee() + ", refundCount=" + refundCount
				+ ", refundList=" + refundList + ", " + super.toString() + "]";
	}
}
