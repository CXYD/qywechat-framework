package com.wk.wechat4j.base.type;

/**
 * ����״̬
 *
 * @className TradeState
 * @author jy
 * @date 2014��11��2��
 * @since JDK 1.6
 * @see
 */
public enum TradeState {
	/**
	 * ֧���ɹ�
	 */
	SUCCESS,
	/**
	 * ת���˿�
	 */
	REFUND,
	/**
	 * δ֧��
	 */
	NOTPAY,
	/**
	 * �ѹر�
	 */
	CLOSED,
	/**
	 * �ѳ���
	 */
	REVOKED,
	/**
	 * �û�֧����
	 */
	USERPAYING,
	/**
	 * δ֧��(��������� ȷ��֧����ʱ)
	 */
	NOPAY,
	/**
	 * ֧��ʧ��(���� ԭ��,�����з���ʧ��)
	 */
	PAYERROR;
}
