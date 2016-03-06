package com.wk.wechat4j.base.payment.mch;


import com.wk.wechat4j.base.model.WeixinPayAccount;

public abstract class AbstractPayRequest implements MchPayRequest {

	private final String prePayId;
	private final WeixinPayAccount payAccount;

	public AbstractPayRequest(String prePayId, WeixinPayAccount payAccount) {
		this.prePayId = prePayId;
		this.payAccount = payAccount;
	}

	@Override
	public String getPrePayId() {
		return this.prePayId;
	}

	@Override
	public WeixinPayAccount getPayAccount() {
		return this.payAccount;
	}
}
