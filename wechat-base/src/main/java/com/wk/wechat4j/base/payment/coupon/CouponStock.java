package com.wk.wechat4j.base.payment.coupon;

import com.alibaba.fastjson.annotation.JSONField;
import com.wk.wechat4j.base.payment.mch.ApiResult;
import com.wk.wechat4j.base.type.CouponStockStatus;
import com.wk.wechat4j.base.type.CouponType;
import com.wk.wechat4j.base.util.DateUtil;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

/**
 * ����ȯ��Ϣ
 *
 * @className CouponStock
 * @author jy
 * @date 2015��3��27��
 * @since JDK 1.6
 * @see
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class CouponStock extends ApiResult {

	private static final long serialVersionUID = -8627202879200080499L;

	/**
	 * ����ȯ����ID
	 */
	@XmlElement(name = "coupon_stock_id")
	@JSONField(name = "coupon_stock_id")
	private String couponStockId;
	/**
	 * ����ȯ����
	 */
	@XmlElement(name = "coupon_name")
	@JSONField(name = "coupon_name")
	private String couponName;
	/**
	 * ����ȯ���
	 */
	@XmlElement(name = "coupon_value")
	@JSONField(name = "coupon_value")
	private int couponValue;
	/**
	 * ����ȯʹ������޶�
	 */
	@XmlElement(name = "coupon_mininumn")
	@JSONField(name = "coupon_mininumn")
	private Integer couponMininumn;
	/**
	 * ����ȯ���ͣ�1-����ȯ���ż���2-����ȯ���ż����⣬3-����ȯ���ż�����
	 */
	@XmlElement(name = "coupon_type")
	@JSONField(name = "coupon_type")
	private int couponType;
	/**
	 * ����״̬: 1-δ���2-�����У�4-�Ѽ��8-�����ϣ�16-��ֹ���ţ�
	 */
	@XmlElement(name = "coupon_stock_status")
	@JSONField(name = "coupon_stock_status")
	private int couponStockStatus;
	/**
	 * ����ȯ����
	 */
	@XmlElement(name = "coupon_total")
	@JSONField(name = "coupon_total")
	private int couponTotal;
	/**
	 * ����ȯÿ�����������ȡ������, ���Ϊ0�����ʾû������
	 */
	@XmlElement(name = "max_quota")
	@JSONField(name = "max_quota")
	private Integer maxQuota;
	/**
	 * ����ȯ��������
	 */
	@XmlElement(name = "locked_num")
	@JSONField(name = "locked_num")
	private Integer lockedNum;
	/**
	 * ����ȯ��ʹ������
	 */
	@XmlElement(name = "used_num")
	@JSONField(name = "used_num")
	private Integer usedNum;
	/**
	 * ����ȯ�Ѿ����͵�����
	 */
	@XmlElement(name = "is_send_num")
	@JSONField(name = "is_send_num")
	private Integer sendNum;
	/**
	 * ��Ч��ʼʱ�� ��ʽΪyyyyMMddhhmmss����2009��12��27��9��10��10���ʾΪ20091227091010��
	 */
	@XmlElement(name = "begin_time")
	@JSONField(name = "begin_time")
	private String beginTime;
	/**
	 * ��Ч����ʱ�� ��ʽΪyyyyMMddhhmmss����2009��12��27��9��10��10���ʾΪ20091227091010��
	 */
	@XmlElement(name = "end_time")
	@JSONField(name = "end_time")
	private String endTime;
	/**
	 * ����ʱ�� ��ʽΪyyyyMMddhhmmss����2009��12��27��9��10��10���ʾΪ20091227091010��
	 */
	@XmlElement(name = "create_time")
	@JSONField(name = "create_time")
	private String createTime;
	/**
	 * ����ȯԤ����
	 */
	@XmlElement(name = "coupon_budget")
	@JSONField(name = "coupon_budget")
	private Integer couponBudget;

	public CouponStock() {

	}

	public String getCouponStockId() {
		return couponStockId;
	}

	public String getCouponName() {
		return couponName;
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

	public Integer getCouponMininumn() {
		return couponMininumn;
	}

	/**
	 * <font color="red">���ýӿڻ�ȡ��λΪ��,get����ת��ΪԪ����ʹ��</font>
	 *
	 * @return Ԫ��λ
	 */
	@JSONField(serialize = false)
	public double getFormatCouponMininumn() {
		return couponMininumn != null ? couponMininumn.intValue() / 100d : 0d;
	}

	public int getCouponType() {
		return couponType;
	}

	@JSONField(serialize = false)
	public CouponType getFormatCouponType() {
		for (CouponType couponType : CouponType.values()) {
			if (couponType.getVal() == this.couponType) {
				return couponType;
			}
		}
		return null;
	}

	public int getCouponStockStatus() {
		return couponStockStatus;
	}

	@JSONField(serialize = false)
	public CouponStockStatus getFormatCouponStockStatus() {
		for (CouponStockStatus couponStockStatus : CouponStockStatus.values()) {
			if (couponStockStatus.getVal() == this.couponStockStatus) {
				return couponStockStatus;
			}
		}
		return null;
	}

	public int getCouponTotal() {
		return couponTotal;
	}

	public Integer getMaxQuota() {
		return maxQuota;
	}

	/**
	 * <font color="red">���ýӿڻ�ȡ��λΪ��,get����ת��ΪԪ����ʹ��</font>
	 *
	 * @return Ԫ��λ
	 */
	@JSONField(serialize = false)
	public double getFormatMaxQuota() {
		return maxQuota != null ? maxQuota.intValue() / 100d : 0d;
	}

	public Integer getLockedNum() {
		return lockedNum;
	}

	/**
	 * <font color="red">���ýӿڻ�ȡ��λΪ��,get����ת��ΪԪ����ʹ��</font>
	 *
	 * @return Ԫ��λ
	 */
	@JSONField(serialize = false)
	public double getFormatLockedNum() {
		return lockedNum != null ? lockedNum.intValue() / 100d : 0d;
	}

	public Integer getUsedNum() {
		return usedNum;
	}

	/**
	 * <font color="red">���ýӿڻ�ȡ��λΪ��,get����ת��ΪԪ����ʹ��</font>
	 *
	 * @return Ԫ��λ
	 */
	@JSONField(serialize = false)
	public double getFormatUsedNum() {
		return usedNum != null ? usedNum.intValue() / 100d : 0d;
	}

	public Integer getSendNum() {
		return sendNum;
	}

	/**
	 * <font color="red">���ýӿڻ�ȡ��λΪ��,get����ת��ΪԪ����ʹ��</font>
	 *
	 * @return Ԫ��λ
	 */
	@JSONField(serialize = false)
	public double getFormatSendNum() {
		return sendNum != null ? sendNum.intValue() / 100d : 0d;
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

	public String getCreateTime() {
		return createTime;
	}

	@JSONField(serialize = false)
	public Date getFormatCreateTime() {
		return createTime != null ? DateUtil.parse2yyyyMMddHHmmss(createTime)
				: null;
	}

	public Integer getCouponBudget() {
		return couponBudget;
	}

	/**
	 * <font color="red">���ýӿڻ�ȡ��λΪ��,get����ת��ΪԪ����ʹ��</font>
	 *
	 * @return Ԫ��λ
	 */
	@JSONField(serialize = false)
	public double getFormatCouponBudget() {
		return couponBudget != null ? couponBudget.intValue() / 100d : 0d;
	}

	@Override
	public String toString() {
		return "CouponDetail [couponStockId=" + couponStockId + ", couponName="
				+ couponName + ", couponValue=" + getFormatCouponValue()
				+ ", couponMininumn=" + getFormatCouponMininumn()
				+ ", couponType=" + getFormatCouponType()
				+ ", couponStockStatus=" + getFormatCouponStockStatus()
				+ ", couponTotal=" + couponTotal + ", maxQuota="
				+ getFormatMaxQuota() + ", lockedNum=" + getFormatLockedNum()
				+ ", usedNum=" + getFormatUsedNum() + ", sendNum="
				+ getFormatSendNum() + ", beginTime=" + beginTime
				+ ", endTime=" + endTime + ", createTime=" + createTime
				+ ", couponBudget=" + getFormatCouponBudget() + ", "
				+ super.toString() + "]";
	}
}
