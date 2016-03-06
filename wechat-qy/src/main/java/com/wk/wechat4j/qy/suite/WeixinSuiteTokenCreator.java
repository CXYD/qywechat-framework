    package com.wk.wechat4j.qy.suite;

	import com.alibaba.fastjson.JSONObject;
	import com.wk.wechat4j.base.exception.WeixinException;
	import com.wk.wechat4j.base.http.weixin.WeixinRequestExecutor;
	import com.wk.wechat4j.base.http.weixin.WeixinResponse;
	import com.wk.wechat4j.base.model.Token;
	import com.wk.wechat4j.base.token.TokenCreator;
	import com.wk.wechat4j.qy.type.URLConsts;

	/**
 * 微信企业号应用套件凭证创建
 *
 * @className WeixinSuiteTokenCreator
 * @author jy
 * @date 2015年6月17日
 * @since JDK 1.6
 * @see <a
 *      href="http://qydev.weixin.qq.com/wiki/index.php?title=%E7%AC%AC%E4%B8%89%E6%96%B9%E5%BA%94%E7%94%A8%E6%8E%A5%E5%8F%A3%E8%AF%B4%E6%98%8E#.E8.8E.B7.E5.8F.96.E5.BA.94.E7.94.A8.E5.A5.97.E4.BB.B6.E4.BB.A4.E7.89.8C">获取应用套件凭证</a>
 * @see com.wk.wechat4j.base.model.Token
 */
public class WeixinSuiteTokenCreator implements TokenCreator {

	private final WeixinRequestExecutor weixinExecutor;
	private final SuiteTicketHolder ticketHolder;

	/**
	 *
	 * @param stringStorager
	 *            套件ticket存取器
	 */
	public WeixinSuiteTokenCreator(SuiteTicketHolder ticketHolder) {
		this.ticketHolder = ticketHolder;
		this.weixinExecutor = new WeixinRequestExecutor();
	}

	@Override
	public String getCacheKey() {
		return String.format("weixin4j_qy_suite_token_%s", ticketHolder.getSuiteId());
	}

	@Override
	public Token createToken() throws WeixinException {
		JSONObject obj = new JSONObject();
		obj.put("suite_id", ticketHolder.getSuiteId());
		obj.put("suite_secret", ticketHolder.getSuiteSecret());
		obj.put("suite_ticket", ticketHolder.getTicket());
		WeixinResponse response = weixinExecutor.post(URLConsts.SUITE_TOKEN_URL,
				obj.toJSONString());
		obj = response.getAsJson();
		Token token = new Token(obj.getString("suite_access_token"));
		token.setExpiresIn(obj.getIntValue("expires_in"));
		token.setCreateTime(System.currentTimeMillis());
		token.setOriginalResult(response.getAsString());
		return token;
	}
}
