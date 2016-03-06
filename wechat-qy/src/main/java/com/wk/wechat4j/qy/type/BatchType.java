package com.wk.wechat4j.qy.type;

/**
 * �첽���������
 *
 * @className BatchType
 * @author jy
 * @date 2015��3��31��
 * @since JDK 1.6
 * @see
 */
public enum BatchType {
	/**
	 * �������³�Ա
	 */
	SYNC_USER(1),
	/**
	 * ȫ�����ǳ�Ա
	 */
	REPLACE_USER(2),
	/**
	 * �����Ա��ע
	 */
	INVITE_USER(3),
	/**
	 * ȫ�����ǲ���
	 */
	REPLACE_PARTY(4);

	private int code;

	BatchType(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}
}
