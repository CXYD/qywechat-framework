package com.wk.wechat4j.base.type;

/**
 * ���˵�����
 *
 * @className BillType
 * @author jy
 * @date 2014��10��31��
 * @since JDK 1.6
 * @see
 */
public enum BillType {
	/**
	 * ȫ��
	 */
	ALL(0),
	/**
	 * �ɹ�����
	 */
	SUCCESS(1),
	/**
	 * �˿��
	 */
	REFUND(2);
	private int val;

	BillType(int val) {
		this.val = val;
	}

	public int getVal() {
		return val;
	}
}
