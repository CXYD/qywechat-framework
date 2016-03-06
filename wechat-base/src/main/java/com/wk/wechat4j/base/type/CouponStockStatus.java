package com.wk.wechat4j.base.type;

/**
 * ����ȯ����״̬
 *
 * @className CouponStockStatus
 * @author jy
 * @date 2015��3��27��
 * @since JDK 1.6
 * @see
 */
public enum CouponStockStatus {
	/**
	 * δ����
	 */
	INACTIVE(1),
	/**
	 * ������
	 */
	APPROVAL_PROCESS(2),
	/**
	 * �Ѽ���
	 */
	ACTIVATED(4),
	/**
	 * ������
	 */
	SUPERSEDED(8),
	/**
	 * ��ֹ����
	 */
	SUSPEND(16);
	private int val;

	CouponStockStatus(int val) {
		this.val = val;
	}

	public int getVal() {
		return val;
	}
}
