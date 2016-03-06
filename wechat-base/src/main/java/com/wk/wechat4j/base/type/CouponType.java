package com.wk.wechat4j.base.type;

/**
 * ����ȯ����
 *
 * @className CouponType
 * @author jy
 * @date 2015��3��27��
 * @since JDK 1.6
 * @see
 */
public enum CouponType {
	/**
	 * ʹ�����ż�
	 */
	NO_THRESHOLD(1),
	/**
	 * ʹ�����ż�
	 */
	HAS_THRESHOLD(2),
	/**
	 * �ż�����
	 */
	THRESHOLD_PLUS(3);
	private int val;

	CouponType(int val) {
		this.val = val;
	}

	public int getVal() {
		return val;
	}
}
