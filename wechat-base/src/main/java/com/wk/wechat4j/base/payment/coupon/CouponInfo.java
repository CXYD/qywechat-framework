package com.wk.wechat4j.base.payment.coupon;

import com.alibaba.fastjson.annotation.JSONField;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * ����ȯ��Ϣ(����,�˿�������)
 *
 * @className CouponInfo
 * @author jy
 * @date 2015��3��24��
 * @since JDK 1.6
 * @see
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class CouponInfo implements Serializable {

	private static final long serialVersionUID = -8744999305258786901L;

	/**
	 * ����ȯ�������Ż�����ID
	 */
	@XmlElement(name = "coupon_batch_id")
	@JSONField(name = "coupon_batch_id")
	private String couponBatchId;
	/**
	 * ����ȯ�������Ż�ID
	 */
	@XmlElement(name = "coupon_id")
	@JSONField(name = "coupon_id")
	private String couponId;
	/**
	 * ��������ȯ�������Ż�֧�����
	 */
	@XmlElement(name = "coupon_fee")
	@JSONField(name = "coupon_fee")
	private Integer couponFee;

	public CouponInfo() {

	}

	public String getCouponBatchId() {
		return couponBatchId;
	}

	public String getCouponId() {
		return couponId;
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
		return couponFee != null ? couponFee.doubleValue() / 100d : 0d;
	}

	public void setCouponId(String couponId) {
		this.couponId = couponId;
	}

	@Override
	public String toString() {
		return "couponBatchId=" + couponBatchId + ", couponId=" + couponId
				+ ", couponFee=" + couponFee;
	}
}
