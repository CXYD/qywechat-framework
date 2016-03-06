package com.wk.wechat4j.base.payment.coupon;

import com.alibaba.fastjson.annotation.JSONField;
import com.wk.wechat4j.base.payment.mch.ApiResult;
import com.wk.wechat4j.base.type.CouponStatus;
import com.wk.wechat4j.base.type.CouponStockType;
import com.wk.wechat4j.base.type.CouponType;
import com.wk.wechat4j.base.util.DateUtil;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

/**
 * ����ȯ��ϸ
 *
 * @className CouponDetail
 * @author jy
 * @date 2015��3��27��
 * @since JDK 1.6
 * @see
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class CouponDetail extends ApiResult {

	private static final long serialVersionUID = -311265355895457070L;

	/**
	 * ����ȯ����Id
	 */
	@XmlElement(name = "coupon_stock_id")
	@JSONField(name = "coupon_stock_id")
	private String couponStockId;

	/**
	 * �������ͣ�1-�����ͣ�2-������
	 */
	@XmlElement(name = "coupon_stock_type")
	@JSONField(name = "coupon_stock_type")
	private int couponStockType;

	/**
	 * ����ȯid
	 */
	@XmlElement(name = "coupon_id")
	@JSONField(name = "coupon_id")
	private String couponId;
	/**
	 * ����ȯ��ֵ,��λ�Ƿ�
	 */
	@XmlElement(name = "coupon_value")
	@JSONField(name = "coupon_value")
	private int couponValue;

	/**
	 * ����ȯʹ������޶�,��λ�Ƿ�
	 */
	@XmlElement(name = "coupon_mininum")
	@JSONField(name = "coupon_mininum")
	private int couponMininum;
	/**
	 * ����ȯ����
	 */
	@XmlElement(name = "coupon_name")
	@JSONField(name = "coupon_name")
	private String couponName;
	/**
	 * ����ȯ״̬��2-�Ѽ��4-��������8-��ʵ��
	 */
	@XmlElement(name = "coupon_state")
	@JSONField(name = "coupon_state")
	private int couponStatus;
	/**
	 * ����ȯ���ͣ�1-����ȯ���ż���2-����ȯ���ż����⣬3-����ȯ���ż����ӣ�
	 */
	@XmlElement(name = "coupon_type")
	@JSONField(name = "coupon_type")
	private int couponType;
	/**
	 * ����ȯ����
	 */
	@XmlElement(name = "coupon_desc")
	@JSONField(name = "coupon_desc")
	private String couponDesc;

	/**
	 * ����ȯʵ��ʹ�ý��
	 */
	@XmlElement(name = "coupon_use_value")
	@JSONField(name = "coupon_use_value")
	private int couponUseValue;

	/**
	 * ����ȯʣ�������ʹ������£����ܻ����ȯʣ����
	 */
	@XmlElement(name = "coupon_remain_value")
	@JSONField(name = "coupon_remain_value")
	private int couponRemainValue;

	/**
	 * ��Ч��ʼʱ��:��ʽΪyyyyMMddhhmmss����2009��12��27��9��10��10���ʾΪ20091227091010��
	 */
	@XmlElement(name = "begin_time")
	@JSONField(name = "begin_time")
	private String beginTime;

	/**
	 * ��Ч����ʱ��:��ʽΪyyyyMMddhhmmss����2009��12��27��9��10��10���ʾΪ20091227091010��
	 */
	@XmlElement(name = "end_time")
	@JSONField(name = "end_time")
	private String endTime;

	/**
	 * ����ʱ��:��ʽΪyyyyMMddhhmmss����2009��12��27��9��10��10���ʾΪ20091227091010��
	 */
	@XmlElement(name = "send_time")
	@JSONField(name = "send_time")
	private String sendTime;

	/**
	 * ʹ��ʱ��:��ʽΪyyyyMMddhhmmss����2009��12��27��9��10��10���ʾΪ20091227091010��
	 */
	@XmlElement(name = "use_time")
	@JSONField(name = "use_time")
	private String useTime;

	/**
	 * ʹ�õ���:����ȯʹ�ú󣬹����Ĵ��յ�����
	 */
	@XmlElement(name = "trade_no")
	@JSONField(name = "trade_no")
	private String tradeNo;

	/**
	 * ���ķ��̻�id:����ȯʹ�ú����ķ��̻�id
	 */
	@XmlElement(name = "consumer_mch_id")
	@JSONField(name = "consumer_mch_id")
	private String consumerMchId;

	/**
	 * ���ķ��̻�����:����ȯʹ�ú����ķ��̻�����
	 */
	@XmlElement(name = "consumer_mch_name")
	@JSONField(name = "consumer_mch_name")
	private String consumerMchName;

	/**
	 * ���ķ��̻�appid:����ȯʹ�ú����ķ��̻�appid
	 */
	@XmlElement(name = "consumer_mch_appid")
	@JSONField(name = "consumer_mch_appid")
	private String consumerMchAppid;

	/**
	 * ������Դ:����ȯ������Դ
	 */
	@XmlElement(name = "send_source")
	@JSONField(name = "send_source")
	private String sendSource;

	/**
	 * �Ƿ�������ʹ��:�ô���ȯ�Ƿ�������ʹ�ñ�ʶ��1-��ʾ֧�ֲ���ʹ��
	 */
	@XmlElement(name = "is_partial_use")
	@JSONField(name = "is_partial_use")
	private int isPartialUse;

	public CouponDetail() {

	}

	public String getCouponStockId() {
		return couponStockId;
	}

	public int getCouponStockType() {
		return couponStockType;
	}

	@JSONField(serialize = false)
	public CouponStockType getFormatCouponStockType() {
		for (CouponStockType couponStockType : CouponStockType.values()) {
			if (couponStockType.getVal() == this.couponStockType) {
				return couponStockType;
			}
		}
		return null;
	}

	public String getCouponId() {
		return couponId;
	}

	public int getCouponValue() {
		return couponValue;
	}

	/**
	 * <font color="red">���ýӿڻ�ȡ��λΪ��,get����ת��ΪԪ����ʹ��</font>
	 *
	 * @return Ԫ��λ
	 */
	@JSONField(serialize = false)
	public double getFormatCouponValue() {
		return couponValue / 100d;
	}

	public int getCouponMininum() {
		return couponMininum;
	}

	/**
	 * <font color="red">���ýӿڻ�ȡ��λΪ��,get����ת��ΪԪ����ʹ��</font>
	 *
	 * @return Ԫ��λ
	 */
	@JSONField(serialize = false)
	public double getFormatCouponMininum() {
		return couponMininum / 100d;
	}

	public String getCouponName() {
		return couponName;
	}

	public int getCouponStatus() {
		return couponStatus;
	}

	@JSONField(serialize = false)
	public CouponStatus getFormatCouponStatus() {
		for (CouponStatus couponStatus : CouponStatus.values()) {
			if (couponStatus.getVal() == this.couponStatus) {
				return couponStatus;
			}
		}
		return null;
	}

	public int getCouponType() {
		return couponType;
	}

	@JSONField(deserialize = false, serialize = false)
	public CouponType getFormatCouponType() {
		for (CouponType couponType : CouponType.values()) {
			if (couponType.getVal() == this.couponType) {
				return couponType;
			}
		}
		return null;
	}

	public String getCouponDesc() {
		return couponDesc;
	}

	public int getCouponUseValue() {
		return couponUseValue;
	}

	/**
	 * <font color="red">���ýӿڻ�ȡ��λΪ��,get����ת��ΪԪ����ʹ��</font>
	 *
	 * @return Ԫ��λ
	 */
	@JSONField(serialize = false)
	public double getFormatCouponUseValue() {
		return couponUseValue / 100d;
	}

	public int getCouponRemainValue() {
		return couponRemainValue;
	}

	/**
	 * <font color="red">���ýӿڻ�ȡ��λΪ��,get����ת��ΪԪ����ʹ��</font>
	 *
	 * @return Ԫ��λ
	 */
	@JSONField(serialize = false)
	public double getFormatCouponRemainValue() {
		return couponRemainValue / 100d;
	}

	public String getBeginTime() {
		return beginTime;
	}

	@JSONField(serialize = false)
	public Date getFormatBeginTime() {
		return beginTime != null ? DateUtil.parse2yyyyMMddHHmmss(beginTime)
				: null;
	}

	public String getEndTime() {
		return endTime;
	}

	@JSONField(serialize = false)
	public Date getFormatEndTime() {
		return endTime != null ? DateUtil.parse2yyyyMMddHHmmss(endTime) : null;
	}

	public String getSendTime() {
		return sendTime;
	}

	@JSONField(serialize = false)
	public Date getFormatSendTime() {
		return sendTime != null ? DateUtil.parse2yyyyMMddHHmmss(sendTime)
				: null;
	}

	public String getUseTime() {
		return useTime;
	}

	@JSONField(serialize = false)
	public Date getFormatUseTime() {
		return useTime != null ? DateUtil.parse2yyyyMMddHHmmss(useTime) : null;
	}

	public String getTradeNo() {
		return tradeNo;
	}

	public String getConsumerMchId() {
		return consumerMchId;
	}

	public String getConsumerMchName() {
		return consumerMchName;
	}

	public String getConsumerMchAppid() {
		return consumerMchAppid;
	}

	public String getSendSource() {
		return sendSource;
	}

	public int getIsPartialUse() {
		return isPartialUse;
	}

	@JSONField(serialize = false)
	public boolean getFormatIsPartialUse() {
		return isPartialUse == 1;
	}

	@Override
	public String toString() {
		return "CouponDetail [couponStockId=" + couponStockId
				+ ", couponStockType=" + getFormatCouponStockType()
				+ ", couponId=" + couponId + ", couponValue="
				+ getFormatCouponValue() + ", couponMininum="
				+ getFormatCouponMininum() + ", couponName=" + couponName
				+ ", couponStatus=" + getCouponStatus() + ", couponType="
				+ getFormatCouponType() + ", couponDesc=" + couponDesc
				+ ", couponUseValue=" + getFormatCouponUseValue()
				+ ", couponRemainValue=" + getFormatCouponRemainValue()
				+ ", beginTime=" + getFormatBeginTime() + ", endTime="
				+ getFormatEndTime() + ", sendTime=" + getFormatSendTime()
				+ ", useTime=" + getFormatUseTime() + ", tradeNo=" + tradeNo
				+ ", consumerMchId=" + consumerMchId + ", consumerMchName="
				+ consumerMchName + ", consumerMchAppid=" + consumerMchAppid
				+ ", sendSource=" + sendSource + ", isPartialUse="
				+ getFormatIsPartialUse() + ", " + super.toString() + "]";
	}
}
