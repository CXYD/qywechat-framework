package com.wk.wechat4j.base.type;

/**
 * ����
 *
 * @className CurrencyType
 * @author jy
 * @date 2014��11��2��
 * @since JDK 1.6
 * @see
 */
public enum CurrencyType {
	CNY("�����"), HKD("��Ԫ"), TWD("̨��"), EUR("ŷԪ"), USD("��Ԫ"), GBP("Ӣ��"), JPY("��Ԫ");
	private String desc;

	CurrencyType(String desc) {
		this.desc = desc;
	}

	public String getDesc() {
		return desc;
	}
}
