package com.wk.wechat4j.base.payment.mch;


import com.wk.wechat4j.base.model.WeixinPayAccount;
import com.wk.wechat4j.base.payment.PayRequest;
import com.wk.wechat4j.base.type.TradeType;

/**
 * ֧������ӿ�
 *
 * @className MchPayRequest
 * @author jy
 * @date 2015��12��25��
 * @since JDK 1.6
 * @see JSAPIPayRequest JS֧��
 * @see NATIVEPayRequest ɨ��֧��
 * @see APPPayRequest APP֧��
 * @see WAPPayRequest WAP֧��
 */
public interface MchPayRequest {
	/**
	 * Ԥ֧������ID
	 *
	 * @return
	 */
	public String getPrePayId();

	/**
	 * �̻���Ϣ
	 *
	 * @return
	 */
	public WeixinPayAccount getPayAccount();

	/**
	 * ��������
	 *
	 * @return
	 */
	public TradeType getTradeType();

	/**
	 * ֧�������ַ���
	 *
	 * @return
	 */
	public String toRequestString();

	/**
	 * ֧���������
	 * 
	 * @return
	 */
	public PayRequest toRequestObject();
}
