package com.wk.wechat4j.qy.token;

import com.alibaba.fastjson.TypeReference;
import com.wk.wechat4j.base.exception.WeixinException;
import com.wk.wechat4j.base.http.weixin.WeixinRequestExecutor;
import com.wk.wechat4j.base.http.weixin.WeixinResponse;
import com.wk.wechat4j.base.model.Token;
import com.wk.wechat4j.base.token.TokenCreator;
import com.wk.wechat4j.qy.type.URLConsts;

/**
 * ΢����ҵ��TOKEN����
 *
 * @className WeixinTokenCreator
 * @author jy
 * @date 2015��1��10��
 * @since JDK 1.6
 * @see <a
 *      href="http://qydev.weixin.qq.com/wiki/index.php?title=%E4%B8%BB%E5%8A%A8%E8%B0%83%E7%94%A8">΢����ҵ�Ż�ȡtoken˵��</a>
 * @see com.wk.wechat4j.base.model.Token
 */
public class WeixinTokenCreator implements TokenCreator {

	private final WeixinRequestExecutor weixinExecutor;
	private final String corpid;
	private final String corpsecret;

	/**
	 *
	 * @param corpid
	 *            ��ҵ��ID
	 * @param corpsecret
	 *            ��ҵ��secret
	 */
	public WeixinTokenCreator(String corpid, String corpsecret) {
		this.corpid = corpid;
		this.corpsecret = corpsecret;
		this.weixinExecutor = new WeixinRequestExecutor();
	}

	@Override
	public String getCacheKey() {
		return String.format("weixin4j_qy_token_%s", corpid);
	}

	@Override
	public Token createToken() throws WeixinException {
		String tokenUrl = String.format(URLConsts.ASSESS_TOKEN_URL, corpid,
				corpsecret);
		WeixinResponse response = weixinExecutor.get(tokenUrl);
		Token token = response.getAsObject(new TypeReference<Token>() {
		});
		token.setCreateTime(System.currentTimeMillis());
		token.setOriginalResult(response.getAsString());
		return token;
	}
}
