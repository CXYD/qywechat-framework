package com.wk.wechat4j.base.payment.mch;

import com.wk.wechat4j.base.model.Consts;
import com.wk.wechat4j.base.model.WeixinPayAccount;
import com.wk.wechat4j.base.payment.PayRequest;
import com.wk.wechat4j.base.payment.PayURLConsts;
import com.wk.wechat4j.base.type.TradeType;
import com.wk.wechat4j.base.util.DigestUtil;
import com.wk.wechat4j.base.util.MapUtil;
import com.wk.wechat4j.base.util.URLEncodingUtil;

/**
 * WAP֧��
 *
 * @className WAPPayRequest
 * @author jy
 * @date 2015��12��25��
 * @since JDK 1.6
 * @see com.wk.wechat4j.base.payment.mch.PrePay
 * @see com.wk.wechat4j.base.payment.PayRequest
 * @see <a
 *      href="https://pay.weixin.qq.com/wiki/doc/api/wap.php?chapter=15_1">WAP֧��</a>
 */
public class WAPPayRequest extends AbstractPayRequest {

	public WAPPayRequest(String prePayId, WeixinPayAccount payAccount) {
		super(prePayId, payAccount);
	}

	@Override
	public TradeType getTradeType() {
		return TradeType.WAP;
	}

	/**
	 * <font color="red">ֻ���鿴֮��,�벻Ҫ������Ϊ֧������</font>
	 */
	@Override
	public PayRequest toRequestObject() {
		PayRequest payRequest = new PayRequest(getPayAccount().getId(),
				getTradeType().name());
		payRequest.setPrepayId(getPrePayId());
		return payRequest;
	}

	@Override
	public String toRequestString() {
		PayRequest payRequest = toRequestObject();
		String original = MapUtil.toJoinString(payRequest, true, true, null);
		String sign = DigestUtil.MD5(
				String.format("%s&key=%s", original, getPayAccount()
						.getPaySignKey())).toUpperCase();
		return String.format(PayURLConsts.MCH_WAP_URL, URLEncodingUtil
				.encoding(String.format("%s&sign=%s", original, sign),
						Consts.UTF_8, true));
	}
}
