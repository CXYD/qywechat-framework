package com.wk.wechat4j.base.payment.mch;

import com.alibaba.fastjson.annotation.JSONField;
import com.wk.wechat4j.base.payment.coupon.CouponInfo;
import com.wk.wechat4j.base.type.CurrencyType;
import com.wk.wechat4j.base.type.RefundChannel;
import com.wk.wechat4j.base.type.RefundStatus;
import com.wk.wechat4j.base.xml.ListsuffixResult;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * V3�˿���ϸ
 *
 * @className RefundDetail
 * @author jy
 * @date 2014��11��6��
 * @since JDK 1.6
 * @see
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class RefundDetail extends ApiResult {

	private static final long serialVersionUID = -3687863914168618620L;

	/**
	 * �̻��˿��
	 */
	@XmlElement(name = "out_refund_no")
	@JSONField(name = "out_refund_no")
	private String outRefundNo;
	/**
	 * ΢���˿��
	 */
	@XmlElement(name = "refund_id")
	@JSONField(name = "refund_id")
	private String refundId;
	/**
	 * �˿�����:ORIGINAL��ԭ·�˿�,Ĭ�� BALANCE���˻ص����
	 */
	@XmlElement(name = "refund_channel")
	@JSONField(name = "refund_channel")
	private String refundChannel;
	/**
	 * �˿��ܽ��,��λΪ��,�����������˿�
	 */
	@XmlElement(name = "refund_fee")
	@JSONField(name = "refund_fee")
	private int refundFee;
	/**
	 * �˿��������
	 *
	 * @see CurrencyType
	 */
	@XmlElement(name = "refund_fee_type")
	@JSONField(name = "refund_fee_type")
	private String refundFeeType;
	/**
	 * �����ܽ��
	 */
	@XmlElement(name = "total_fee")
	@JSONField(name = "total_fee")
	private int totalFee;
	/**
	 * ��������������
	 *
	 * @see CurrencyType
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
	 * �ֽ�֧����������
	 *
	 * @see CurrencyType
	 */
	@XmlElement(name = "cash_fee_type")
	@JSONField(name = "cash_fee_type")
	private String cashFeeType;
	/**
	 * �ֽ��˿���
	 */
	@XmlElement(name = "cash_refund_fee")
	@JSONField(name = "cash_refund_fee")
	private Integer cashRefundFee;
	/**
	 * �ֽ��˿��������
	 *
	 * @see .CurrencyType
	 */
	@XmlElement(name = "cash_refund_fee_type")
	@JSONField(name = "cash_refund_fee_type")
	private String cashRefundFeeType;
	/**
	 * �˿�״̬
	 */
	@XmlElement(name = "refund_status")
	@JSONField(name = "refund_status")
	private String refundStatus;
	/**
	 * �ֽ�ȯ�˿���<=�˿���,�˿���-�ֽ�ȯ�˿���Ϊ�ֽ�
	 */
	@XmlElement(name = "coupon_refund_fee")
	@JSONField(name = "coupon_refund_fee")
	private Integer couponRefundFee;
	/**
	 * ����ȯ�������Ż�ʹ������ <font
	 * color="red">΢��֧���ĵ���д��coupon_count,��ʵ�ʲ����õ�����coupon_refund_count,�����Ǻš�
	 * </font>
	 */
	@XmlElement(name = "coupon_refund_count")
	@JSONField(name = "coupon_refund_count")
	private Integer couponRefundCount;
	/**
	 * ����ȯ��Ϣ
	 *
	 * @see .CouponInfo
	 */
	@ListsuffixResult
	private List<CouponInfo> couponList;

	protected RefundDetail() {
		// jaxb required
	}

	public String getOutRefundNo() {
		return outRefundNo;
	}

	public String getRefundId() {
		return refundId;
	}

	public String getRefundChannel() {
		return refundChannel;
	}

	@JSONField(serialize = false)
	public RefundChannel getFormatRefundChannel() {
		return refundChannel != null ? RefundChannel.valueOf(refundChannel
				.toUpperCase()) : null;
	}

	public int getRefundFee() {
		return refundFee;
	}

	public String getFeeType() {
		return feeType;
	}

	@JSONField(serialize = false)
	public CurrencyType getFormatFeeType() {
		return feeType != null ? CurrencyType.valueOf(feeType.toUpperCase())
				: null;
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

	public String getRefundStatus() {
		return refundStatus;
	}

	@JSONField(serialize = false)
	public RefundStatus getFormatRefundStatus() {
		return refundStatus != null ? RefundStatus.valueOf(refundStatus
				.toUpperCase()) : null;
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
	public double getFormatCouponRefundFee() {
		return couponRefundFee != null ? couponRefundFee.intValue() / 100d : 0d;
	}

	public String getRefundFeeType() {
		return refundFeeType;
	}

	@JSONField(serialize = false)
	public CurrencyType getFormatRefundFeeType() {
		return refundFeeType != null ? CurrencyType.valueOf(refundFeeType
				.toUpperCase()) : null;
	}

	public int getTotalFee() {
		return totalFee;
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

	public int getCashFee() {
		return cashFee;
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

	public String getCashFeeType() {
		return cashFeeType;
	}

	@JSONField(serialize = false)
	public CurrencyType getFormatCashFeeType() {
		return cashFeeType != null ? CurrencyType.valueOf(cashFeeType
				.toUpperCase()) : null;
	}

	public Integer getCashRefundFee() {
		return cashRefundFee;
	}

	/**
	 * <font color="red">���ýӿڻ�ȡ��λΪ��,get����ת��ΪԪ����ʹ��</font>
	 *
	 * @return Ԫ��λ
	 */
	@JSONField(serialize = false)
	public double getFormatCashRefundFee() {
		return cashRefundFee != null ? cashRefundFee.intValue() / 100d : 0d;
	}

	public String getCashRefundFeeType() {
		return cashRefundFeeType;
	}

	@JSONField(serialize = false)
	public CurrencyType getFormatCashRefundFeeType() {
		return cashRefundFeeType != null ? CurrencyType
				.valueOf(cashRefundFeeType.toUpperCase()) : null;
	}

	public Integer getCouponRefundCount() {
		return couponRefundCount;
	}

	public List<CouponInfo> getCouponList() {
		return couponList;
	}

	public void setCouponList(List<CouponInfo> couponList) {
		this.couponList = couponList;
	}

	@Override
	public String toString() {
		return "RefundDetail [outRefundNo=" + outRefundNo + ", refundId="
				+ refundId + ", refundChannel=" + refundChannel
				+ ", refundFee=" + getFormatRefundFee() + ", refundFeeType="
				+ refundFeeType + ", totalFee=" + getFormatTotalFee()
				+ ", feeType=" + feeType + ", cashFee=" + getFormatCashFee()
				+ ", cashFeeType=" + cashFeeType + ", cashRefundFee="
				+ getFormatCashRefundFee() + ", cashRefundFeeType="
				+ cashRefundFeeType + ", refundStatus=" + refundStatus
				+ ", couponRefundFee=" + getFormatCouponRefundFee()
				+ ", couponRefundCount=" + couponRefundCount + ", couponList="
				+ couponList + ", " + super.toString() + "]";
	}
}
