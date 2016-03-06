package com.wk.wechat4j.qy.suite;

import com.alibaba.fastjson.JSONObject;
import com.wk.wechat4j.base.exception.WeixinException;
import com.wk.wechat4j.base.http.weixin.WeixinRequestExecutor;
import com.wk.wechat4j.base.http.weixin.WeixinResponse;
import com.wk.wechat4j.base.model.Token;
import com.wk.wechat4j.base.token.TokenCreator;
import com.wk.wechat4j.base.token.TokenHolder;
import com.wk.wechat4j.qy.type.URLConsts;

/**
 * 微信企业号应用套件预授权码创建
 *
 * @className WeixinSuitePreCodeCreator
 * @author jy
 * @date 2015年6月17日
 * @since JDK 1.6
 * @see <a
 *      href="http://qydev.weixin.qq.com/wiki/index.php?title=%E7%AC%AC%E4%B8%89%E6%96%B9%E5%BA%94%E7%94%A8%E6%8E%A5%E5%8F%A3%E8%AF%B4%E6%98%8E#.E8.8E.B7.E5.8F.96.E9.A2.84.E6.8E.88.E6.9D.83.E7.A0.81">获取应用套件预授权码</a>
 * @see com.wk.wechat4j.base.model.Token
 */
public class WeixinSuitePreCodeCreator implements TokenCreator {

	private final WeixinRequestExecutor weixinExecutor;
	private final TokenHolder suiteTokenHolder;
	private final String suiteId;

	/**
	 *
	 * @param suiteTokenHolder
	 *            应用套件的token
	 * @param suiteId
	 *            应用套件ID
	 */
	public WeixinSuitePreCodeCreator(TokenHolder suiteTokenHolder,
			String suiteId) {
		this.suiteTokenHolder = suiteTokenHolder;
		this.suiteId = suiteId;
		this.weixinExecutor = new WeixinRequestExecutor();
	}

	@Override
	public String getCacheKey() {
		return String.format("weixin4j_qy_suite_precode_%s", suiteId);
	}

	@Override
	public Token createToken() throws WeixinException {
		WeixinResponse response = weixinExecutor.post(
				String.format(URLConsts.SUITE_PRE_CODE_URL,
						suiteTokenHolder.getAccessToken()),
				String.format("{\"suite_id\":\"%s\"}", suiteId));
		JSONObject result = response.getAsJson();
		Token token = new Token(result.getString("pre_auth_code"));
		token.setExpiresIn(result.getIntValue("expires_in"));
		token.setCreateTime(System.currentTimeMillis());
		token.setOriginalResult(response.getAsString());
		return token;
	}
}
