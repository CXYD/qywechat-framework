package com.wk.wechat4j.base.type;

/**
 * �˿�״̬
 *
 * @className RefundStatus
 * @author jy
 * @date 2014��11��2��
 * @since JDK 1.6
 * @see
 */
public enum RefundStatus {
	/**
	 * �˿�ɹ�
	 */
	SUCCESS,
	/**
	 * �˿�ʧ��
	 */
	FAIL,
	/**
	 * �˿����
	 */
	PROCESSING,
	/**
	 * δȷ��,��Ҫ�̻� ԭ�˿�����·���
	 */
	NOTSURE,
	/**
	 * ת�����,�˿���з����û��Ŀ����ϻ��߶�����,����ԭ·�˿����п�ʧ��,�ʽ�������̻����ֽ��ʺ�,��Ҫ�̻��˹���Ԥ,ͨ�����»��߲Ƹ�ͨת
	 * �˵ķ�ʽ�����˿
	 */
	CHANGE;
}
