package com.wk.wechat4j.base.payment.mch;

import com.wk.wechat4j.base.model.WeixinPayAccount;
import com.wk.wechat4j.base.payment.PayRequest;
import com.wk.wechat4j.base.type.TradeType;

/**
 * NATIVE扫码支付(模式二)
 *
 * @className NATIVEPayRequest
 * @author jy
 * @date 2015年12月25日
 * @since JDK 1.6
 * @see com.wk.wechat4j.base.payment.mch.PrePay
 * @see com.wk.wechat4j.base.payment.PayRequest
 * @see <a
 *      href="https://pay.weixin.qq.com/wiki/doc/api/native.php?chapter=6_5">NATIVE扫码支付(模式二)</a>
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
	 * <font color="red">只做查看之用,请不要尝试作为支付请求</font>
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
