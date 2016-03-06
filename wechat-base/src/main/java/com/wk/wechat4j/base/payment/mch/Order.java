package com.wk.wechat4j.base.payment.mch;

import com.alibaba.fastjson.annotation.JSONField;
import com.wk.wechat4j.base.payment.coupon.CouponInfo;
import com.wk.wechat4j.base.type.BankType;
import com.wk.wechat4j.base.type.CurrencyType;
import com.wk.wechat4j.base.type.TradeState;
import com.wk.wechat4j.base.type.TradeType;
import com.wk.wechat4j.base.util.DateUtil;
import com.wk.wechat4j.base.xml.ListsuffixResult;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;
import java.util.List;

/**
 * V3������Ϣ
 *
 * @className Order
 * @author jy
 * @date 2014��11��2��
 * @since JDK 1.6
 * @see
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Order extends ApiResult {

	private static final long serialVersionUID = 5636828325595317079L;
	/**
	 * ����״̬
	 *
	 * @see com.wk.wechat4j.base.type.TradeState
	 */
	@XmlElement(name = "trade_state")
	@JSONField(name = "trade_state")
	private String tradeState;
	/**
	 * �û���openid
	 */
	@XmlElement(name = "openid")
	@JSONField(name = "openid")
	private String openId;
	/**
	 * �û��Ƿ��ע�����˺�,Y- ��ע,N-δ��ע,���ڹ��� �˺�����֧����Ч
	 */
	@XmlElement(name = "is_subscribe")
	@JSONField(name = "is_subscribe")
	private String isSubscribe;
	/**
	 * ��������
	 *
	 * @see com.wk.wechat4j.base.type.TradeType
	 */
	@XmlElement(name = "trade_type")
	@JSONField(name = "trade_type")
	private String tradeType;
	/**
	 * ��������
	 */
	@XmlElement(name = "bank_type")
	@JSONField(name = "bank_type")
	private String bankType;
	/**
	 * �����ܽ��,��λΪ��
	 */
	@XmlElement(name = "total_fee")
	@JSONField(name = "total_fee")
	private Integer totalFee;
	/**
	 * �ֽ�ȯ֧�����<=�����ܽ� ��,�����ܽ��-�ֽ�ȯ��� Ϊ�ֽ�֧�����
	 */
	@XmlElement(name = "coupon_fee")
	@JSONField(name = "coupon_fee")
	private Integer couponFee;
	/**
	 * ����ȯ�������Ż�ʹ������
	 */
	@XmlElement(name = "coupon_count")
	@JSONField(name = "coupon_count")
	private Integer couponCount;
	/**
	 * ����ȯ��Ϣ ��֤ǩ���е��鷳
	 */
	@ListsuffixResult
	private List<CouponInfo> couponList;
	/**
	 * �ֽ�֧�����
	 */
	@XmlElement(name = "cash_fee")
	@JSONField(name = "cash_fee")
	private Integer cashFee;
	/**
	 * ��������,���� ISO 4217 ��׼����λ��ĸ����,Ĭ�������:CNY
	 *
	 * @see com.wk.wechat4j.base.mp.type.CurrencyType
	 */
	@XmlElement(name = "fee_type")
	@JSONField(name = "fee_type")
	private String feeType;
	/**
	 * ΢��֧��������
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
	 * �̼����ݰ�
	 */
	private String attach;
	/**
	 * ֧�����ʱ��,��ʽΪ yyyyMMddhhmmss
	 */
	@XmlElement(name = "time_end")
	@JSONField(name = "time_end")
	private String timeEnd;
	/**
	 * ����״̬����
	 */
	@XmlElement(name = "trade_state_desc")
	@JSONField(name = "trade_state_desc")
	private String tradeStateDesc;

	protected Order() {
		// jaxb required
	}

	@JSONField(serialize = false)
	public TradeState getFormatTradeState() {
		return tradeState != null ? TradeState
				.valueOf(tradeState.toUpperCase()) : null;
	}

	public String getOpenId() {
		return openId;
	}

	public String getIsSubscribe() {
		return isSubscribe;
	}

	@JSONField(serialize = false)
	public boolean getFormatIsSubscribe() {
		return isSubscribe != null && isSubscribe.equalsIgnoreCase("y");
	}

	@JSONField(serialize = false)
	public TradeType getFormatTradeType() {
		return tradeType != null ? TradeType.valueOf(tradeType.toUpperCase())
				: null;
	}

	public String getBankType() {
		return bankType;
	}

	@JSONField(serialize = false)
	public BankType getFormatBankType() {
		return bankType != null ? BankType.valueOf(bankType.toUpperCase())
				: null;
	}

	public Integer getTotalFee() {
		return totalFee;
	}

	/**
	 * <font color="red">���ýӿڻ�ȡ��λΪ��,get����ת��ΪԪ����ʹ��</font>
	 *
	 * @return Ԫ��λ
	 */
	@JSONField(serialize = false)
	public double getFormatTotalFee() {
		return totalFee != null ? totalFee / 100d : 0d;
	}

	public Integer getCouponFee() {
		return couponFee;
	}

	/**
	 * <font color="red">���ýӿڻ�ȡ��λΪ��,get����ת��ΪԪ����ʹ��</font>
	 *
	 * @return Ԫ��λ
	 */
	@JSONField(serialize = false)
	public double getFormatCouponFee() {
		return couponFee != null ? couponFee / 100d : 0d;
	}

	public Integer getCouponCount() {
		return couponCount;
	}

	public Integer getCashFee() {
		return cashFee;
	}

	/**
	 * <font color="red">���ýӿڻ�ȡ��λΪ��,get����ת��ΪԪ����ʹ��</font>
	 *
	 * @return Ԫ��λ
	 */
	@JSONField(serialize = false)
	public double getFormatCashFee() {
		return cashFee != null ? cashFee / 100d : 0d;
	}

	@JSONField(serialize = false)
	public CurrencyType getFormatFeeType() {
		return feeType != null ? CurrencyType.valueOf(feeType.toUpperCase())
				: null;
	}

	public String getTradeState() {
		return tradeState;
	}

	public String getTradeType() {
		return tradeType;
	}

	public String getFeeType() {
		return feeType;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public String getOutTradeNo() {
		return outTradeNo;
	}

	public String getAttach() {
		return attach;
	}

	public String getTimeEnd() {
		return timeEnd;
	}

	@JSONField(serialize = false)
	public Date getFormatTimeEnd() {
		return timeEnd != null ? DateUtil.parse2yyyyMMddHHmmss(timeEnd) : null;
	}

	public String getTradeStateDesc() {
		return tradeStateDesc;
	}

	public List<CouponInfo> getCouponList() {
		return couponList;
	}

	public void setCouponList(List<CouponInfo> couponList) {
		this.couponList = couponList;
	}

	@Override
	public String toString() {
		return "Order [tradeState=" + tradeState + ", openId=" + openId
				+ ", isSubscribe=" + isSubscribe + ", tradeType=" + tradeType
				+ ", bankType=" + bankType + ", feeType=" + feeType
				+ ", transactionId=" + transactionId + ", outTradeNo="
				+ outTradeNo + ", attach=" + attach + ", timeEnd=" + timeEnd
				+ ", totalFee=" + getFormatTotalFee() + ", couponFee="
				+ getFormatCouponFee() + ", couponCount=" + couponCount
				+ ", couponList=" + couponList + ", cashFee="
				+ getFormatCashFee() + ", timeEnd=" + getFormatTimeEnd()
				+ ", tradeStateDesc=" + tradeStateDesc + ", "
				+ super.toString() + "]";
	}
}
