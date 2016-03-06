package com.wk.wechat4j.qy.suite;


import com.wk.wechat4j.base.exception.WeixinException;
import com.wk.wechat4j.base.model.Token;
import com.wk.wechat4j.base.token.TokenStorager;

/**
 * Ӧ���׼�������Ȩ��Ĵ�ȡ
 *
 * @className SuitePerCodeHolder
 * @author jy
 * @date 2015��6��22��
 * @since JDK 1.6
 * @see
 */
public class SuitePerCodeHolder {

	private final String authCorpId;
	private final String suiteId;
	private final TokenStorager tokenStorager;

	public SuitePerCodeHolder(String authCorpId, String suiteId,
			TokenStorager tokenStorager) {
		this.authCorpId = authCorpId;
		this.suiteId = suiteId;
		this.tokenStorager = tokenStorager;
	}

	/**
	 * ����������Ȩ��
	 *
	 * @param permanentCode
	 * @throws WeixinException
	 */
	public void cachingPermanentCode(String permanentCode)
			throws WeixinException {
		Token token = new Token(permanentCode);
		token.setExpiresIn(-1);
		tokenStorager.caching(getCacheKey(), token);
	}

	/**
	 * ��ȡ������Ȩ���key
	 *
	 * @return
	 */
	public String getCacheKey() {
		return String.format("qy_suite_percode_%s:%s", suiteId, authCorpId);
	}

	/**
	 * ����������Ȩ��
	 * 
	 * @return
	 * @throws WeixinException
	 */
	public String getPermanentCode() throws WeixinException {
		return tokenStorager.lookup(getCacheKey()).getAccessToken();
	}

	public String getSuiteId() {
		return this.suiteId;
	}

	public String getAuthCorpId() {
		return this.authCorpId;
	}
}
