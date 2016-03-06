package com.wk.wechat4j.base.type;

/**
 * �˿�����
 *
 * @className RefundType
 * @author jy
 * @date 2014��12��31��
 * @since JDK 1.6
 * @see
 */
public enum RefundType {
	/**
	 * 1:�̻�������˿�;
	 */
	BALANCE(1),
	/**
	 * 2:�ֽ��ʺ� �˿�;
	 */
	CASH(2),
	/**
	 * 3:�����̻����˿�,���̻�������, �����ֽ��ʺ��˿ ʹ�� 2 �� 3 ʱ,����ϵ�� ��ͨ��ͨ�˹���
	 */
	BOTH(3);

	private int val;

	RefundType(int val) {
		this.val = val;
	}

	public int getVal() {
		return val;
	}
}
