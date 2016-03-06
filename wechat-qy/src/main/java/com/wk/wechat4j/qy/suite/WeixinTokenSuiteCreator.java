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
 * 微信企业号token创建(永久授权码)
 *
 * @className WeixinTokenSuiteCreator
 * @author jy
 * @date 2015年6月17日
 * @since JDK 1.6
 * @see <a
 *      href="http://qydev.weixin.qq.com/wiki/index.php?title=%E7%AC%AC%E4%B8%89%E6%96%B9%E5%BA%94%E7%94%A8%E6%8E%A5%E5%8F%A3%E8%AF%B4%E6%98%8E#.E8.8E.B7.E5.8F.96.E4.BC.81.E4.B8.9A.E5.8F.B7access_token">获取企业号access_token</a>
 * @see com.wk.wechat4j.base.model.Token
 */
public class WeixinTokenSuiteCreator implements TokenCreator {

	private final WeixinRequestExecutor weixinExecutor;
	private final SuitePerCodeHolder perCodeHolder;
	private final TokenHolder suiteTokenHolder;

	/**
	 *
	 * @param perCodeHolder
	 *            第三方套件永久授权码
	 * @param suiteTokenHolder
	 *            第三方套件凭证token
	 */
	public WeixinTokenSuiteCreator(SuitePerCodeHolder perCodeHolder,
			TokenHolder suiteTokenHolder) {
		this.perCodeHolder = perCodeHolder;
		this.suiteTokenHolder = suiteTokenHolder;
		this.weixinExecutor = new WeixinRequestExecutor();
	}

	@Override
	public String getCacheKey() {
		return String.format("weixin4j_qy_token_suite_%s:%s",
				perCodeHolder.getSuiteId(), perCodeHolder.getAuthCorpId()

		);
	}

	@Override
	public Token createToken() throws WeixinException {
		JSONObject obj = new JSONObject();
		obj.put("suite_id", perCodeHolder.getSuiteId());
		obj.put("auth_corpid", perCodeHolder.getAuthCorpId());
		obj.put("permanent_code", perCodeHolder.getPermanentCode());
		WeixinResponse response = weixinExecutor.post(
				String.format(URLConsts.TOKEN_SUITE_URL,
						suiteTokenHolder.getAccessToken()), obj.toJSONString());
		obj = response.getAsJson();
		Token token = new Token(obj.getString("access_token"));
		token.setExpiresIn(obj.getIntValue("expires_in"));
		token.setCreateTime(System.currentTimeMillis());
		token.setOriginalResult(response.getAsString());
		return token;
	}
}
