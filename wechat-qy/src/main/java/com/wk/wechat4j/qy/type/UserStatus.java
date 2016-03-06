package com.wk.wechat4j.qy.type;

/**
 * ��Ա״̬
 *
 * @className UserStatus
 * @author jy
 * @date 2014��11��19��
 * @since JDK 1.6
 * @see
 */
public enum UserStatus {
	/**
	 * 0=ȫ��
	 */
	BOTH(0),
	/**
	 * 1=�ѹ�ע
	 */
	FOLLOW(1),
	/**
	 * 2=�Ѷ���
	 */
	FROZEN(2),
	/**
	 * 4=δ��ע
	 */
	UNFOLLOW(4);
	private int val;

	UserStatus(int val) {
		this.val = val;
	}

	public int getVal() {
		return val;
	}
}
