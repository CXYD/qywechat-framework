package com.wk.wechat4j.base.type;

/**
 * ����ȯ��������
 *
 * @className CouponStockType
 * @author jy
 * @date 2015��3��27��
 * @since JDK 1.6
 * @see
 */
public enum CouponStockType {
	/**
	 * ������
	 */
	BATCH(1),
	/**
	 * ������
	 */
	TRIGGER(2);
	private int val;

	CouponStockType(int val) {
		this.val = val;
	}

	public int getVal() {
		return val;
	}
}
