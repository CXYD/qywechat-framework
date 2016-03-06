package com.wk.wechat4j.base.type;

/**
 * ID����
 *
 * @className IdType
 * @author jy
 * @date 2014��11��1��
 * @since JDK 1.6
 * @see
 */
public enum IdType {
	/**
	 * ΢���˿��
	 */
	REFUNDID("refund_id"),
	/**
	 * ΢�Ŷ�����
	 */
	TRANSACTIONID("transaction_id"),
	/**
	 * �̻�������
	 */
	TRADENO("out_trade_no"),
	/**
	 * �̻��˿��
	 */
	REFUNDNO("out_refund_no");
	private String name;

	IdType(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
