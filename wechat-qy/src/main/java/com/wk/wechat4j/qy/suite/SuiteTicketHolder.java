package com.wk.wechat4j.qy.suite;


import com.wk.wechat4j.base.exception.WeixinException;
import com.wk.wechat4j.base.model.Token;
import com.wk.wechat4j.base.token.TokenStorager;

/**
 * Ӧ���׼�ticket�Ĵ�ȡ
 *
 * @className SuiteTicketHolder
 * @author jy
 * @date 2015��6��22��
 * @since JDK 1.6
 * @see
 */
public class SuiteTicketHolder {

	private final String suiteId;
	private final String suiteSecret;
	private final TokenStorager tokenStorager;

	public SuiteTicketHolder(String suiteId, String suiteSecret,
			TokenStorager tokenStorager) {
		this.suiteId = suiteId;
		this.suiteSecret = suiteSecret;
		this.tokenStorager = tokenStorager;
	}

	/**
	 * ��ȡticket
	 *
	 * @return
	 * @throws WeixinException
	 */
	public String getTicket() throws WeixinException {
		return tokenStorager.lookup(getCacheKey()).getAccessToken();
	}

	/**
	 * ��ȡticket��key
	 *
	 * @return
	 */
	public String getCacheKey() {
		return String.format("qy_suite_ticket_%s", suiteId);
	}

	/**
	 * ����ticket
	 * 
	 * @param ticket
	 * @throws WeixinException
	 */
	public void cachingTicket(String ticket) throws WeixinException {
		Token token = new Token(ticket);
		token.setExpiresIn(-1);
		tokenStorager.caching(getCacheKey(), token);
	}

	public String getSuiteId() {
		return this.suiteId;
	}

	public String getSuiteSecret() {
		return this.suiteSecret;
	}

	public TokenStorager getTokenStorager() {
		return this.tokenStorager;
	}
}
