package com.wk.wechat4j.qy.type;

/**
 * �첽�����״̬
 *
 * @className BatchStatus
 * @author jy
 * @date 2015��3��31��
 * @since JDK 1.6
 * @see
 */
public enum BatchStatus {
	/**
	 * ��ʼ
	 */
	START(1),
	/**
	 * ������
	 */
	PROCESS(2),
	/**
	 * ���
	 */
	DONE(3);
	private int code;

	BatchStatus(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}
}
