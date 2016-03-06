package com.wk.wechat4j.base.type;

/**
 * 红包状态
 * @className RedpacketStatus
 * @author jy
 * @date 2015年6月4日
 * @since JDK 1.6
 * @see
 */
public enum RedpacketStatus {
	/**
	 * 发放中
	 */
	SENDING,
	/**
	 * 已发放待领取
	 */
	SENT,
	/**
	 * 发放失败
	 */
	FAILED,
	/**
	 * 已领取
	 */
	RECEIVED,
	/**
	 * 已退款
	 */
	REFUND;
}
