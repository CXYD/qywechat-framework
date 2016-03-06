package com.wk.wechat4j.base.payment.coupon;

import com.alibaba.fastjson.annotation.JSONField;
import com.wk.wechat4j.base.payment.mch.ApiResult;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * ����ȯ���Ž��
 *
 * @className CouponResult
 * @author jy
 * @date 2015��3��25��
 * @since JDK 1.6
 * @see
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class CouponResult extends ApiResult {

	private static final long serialVersionUID = -1996967923720149124L;

	/**
	 * ����ȯ����id
	 */
	@XmlElement(name = "coupon_stock_id")
	@JSONField(name = "coupon_stock_id")
	private String couponStockId;
	/**
	 * ���ؼ�¼��
	 */
	@XmlElement(name = "resp_count")
	@JSONField(name = "resp_count")
	private int responseCount;
	/**
	 * �ɹ���¼��
	 */
	@XmlElement(name = "success_count")
	@JSONField(name = "success_count")
	private int successCount;
	/**
	 * ʧ�ܼ�¼��
	 */
	@XmlElement(name = "failed_count")
	@JSONField(name = "failed_count")
	private int failedCount;
	/**
	 * �û����̻�appid�µ�Ψһ��ʶ
	 */
	@XmlElement(name = "openid")
	@JSONField(name = "openid")
	private String openId;
	/**
	 * ������ SUCCESS����FAILED
	 */
	@XmlElement(name = "ret_code")
	@JSONField(name = "ret_code")
	private String retCode;
	/**
	 * ����ȯid
	 */
	@XmlElement(name = "coupon_id")
	@JSONField(name = "coupon_id")
	private String couponId;
	/**
	 * ʧ��������Ϣ�����磺���û��Ѵ��������ޡ�
	 */
	@XmlElement(name = "ret_msg")
	@JSONField(name = "ret_msg")
	private String retMsg;

	public CouponResult() {

	}

	public String getCouponStockId() {
		return couponStockId;
	}

	public int getResponseCount() {
		return responseCount;
	}

	public int getSuccessCount() {
		return successCount;
	}

	public int getFailedCount() {
		return failedCount;
	}

	public String getOpenId() {
		return openId;
	}

	public String getRetCode() {
		return retCode;
	}

	public String getCouponId() {
		return couponId;
	}

	public String getRetMsg() {
		return retMsg;
	}

	@Override
	public String toString() {
		return "CouponResult [couponStockId=" + couponStockId
				+ ", responseCount=" + responseCount + ", successCount="
				+ successCount + ", failedCount=" + failedCount + ", openId="
				+ openId + ", retCode=" + retCode + ", couponId=" + couponId
				+ ", retMsg=" + retMsg + "]";
	}
}
