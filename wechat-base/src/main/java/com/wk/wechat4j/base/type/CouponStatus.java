package com.wk.wechat4j.base.type;

/**
 * ����ȯ״̬
 *
 * @className CouponStatus
 * @author jy
 * @date 2015��3��27��
 * @since JDK 1.6
 * @see
 */
public enum CouponStatus {
	/**
	 * �Ѽ���
	 */
	ACTIVATED(2),
	/**
	 * ������
	 */
	LOCKED(4),
	/**
	 * ��ʵ��
	 */
	USED(8);
	private int val;

	CouponStatus(int val) {
		this.val = val;
	}

	public int getVal() {
		return val;
	}
}
