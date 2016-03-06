package com.wk.wechat4j.base.payment.mch;

import com.wk.wechat4j.base.model.WeixinPayAccount;
import com.wk.wechat4j.base.payment.PayRequest;
import com.wk.wechat4j.base.type.TradeType;

/**
 * NATIVEɨ��֧��(ģʽ��)
 *
 * @className NATIVEPayRequest
 * @author jy
 * @date 2015��12��25��
 * @since JDK 1.6
 * @see com.wk.wechat4j.base.payment.mch.PrePay
 * @see com.wk.wechat4j.base.payment.PayRequest
 * @see <a
 *      href="https://pay.weixin.qq.com/wiki/doc/api/native.php?chapter=6_5">NATIVEɨ��֧��(ģʽ��)</a>
 */
public class NATIVEPayRequest extends AbstractPayRequest {

	private final String codeUrl;

	public NATIVEPayRequest(String prePayId, String codeUrl,
			WeixinPayAccount payAccount) {
		super(prePayId, payAccount);
		this.codeUrl = codeUrl;
	}

	@Override
	public TradeType getTradeType() {
		return TradeType.NATIVE;
	}

	/**
	 * <font color="red">ֻ���鿴֮��,�벻Ҫ������Ϊ֧������</font>
	 */
	@Override
	public PayRequest toRequestObject() {
		return new PayRequest(getPayAccount().getId(), "code_url=" + codeUrl);
	}

	@Override
	public String toRequestString() {
		return this.codeUrl;
	}
}
