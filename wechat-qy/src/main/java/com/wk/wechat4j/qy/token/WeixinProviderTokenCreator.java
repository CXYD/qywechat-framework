package com.wk.wechat4j.qy.token;

import com.alibaba.fastjson.JSONObject;
import com.wk.wechat4j.base.exception.WeixinException;
import com.wk.wechat4j.base.http.weixin.WeixinRequestExecutor;
import com.wk.wechat4j.base.http.weixin.WeixinResponse;
import com.wk.wechat4j.base.model.Token;
import com.wk.wechat4j.base.token.TokenCreator;
import com.wk.wechat4j.qy.type.URLConsts;

/**
 * ΢����ҵ��Ӧ���ṩ��ƾ֤����
 *
 * @className WeixinTokenCreator
 * @author jy
 * @date 2015��1��10��
 * @since JDK 1.6
 * @see <a
 *      href="http://qydev.weixin.qq.com/wiki/index.php?title=%E8%8E%B7%E5%8F%96%E5%BA%94%E7%94%A8%E6%8F%90%E4%BE%9B%E5%95%86%E5%87%AD%E8%AF%81">��ȡӦ���ṩ��ƾ֤</a>
 * @see com.wk.wechat4j.base.model.Token
 */
public class WeixinProviderTokenCreator implements TokenCreator {

	private final WeixinRequestExecutor weixinExecutor;
	private final String corpid;
	private final String providersecret;

	/**
	 *
	 * @param corpid
	 *            ��ҵ��ID
	 * @param providersecret
	 *            ��ҵ���ṩ�̵�secret
	 */
	public WeixinProviderTokenCreator(String corpid, String providersecret) {
		this.corpid = corpid;
		this.providersecret = providersecret;
		this.weixinExecutor = new WeixinRequestExecutor();
	}

	@Override
	public String getCacheKey() {
		return String.format("weixin4j_qy_provider_token_%s", corpid);
	}

	@Override
	public Token createToken() throws WeixinException {
		JSONObject obj = new JSONObject();
		obj.put("corpid", corpid);
		obj.put("provider_secret", providersecret);
		WeixinResponse response = weixinExecutor.post(URLConsts.PROVIDER_TOKEN_URL,
				obj.toJSONString());
		obj = response.getAsJson();
		Token token = new Token(obj.getString("provider_access_token"));
		token.setExpiresIn(obj.getIntValue("expires_in"));
		token.setCreateTime(System.currentTimeMillis());
		token.setOriginalResult(response.getAsString());
		return token;
	}
}
