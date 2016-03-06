package com.wk.wechat4j.base.token;


import com.wk.wechat4j.base.exception.WeixinException;
import com.wk.wechat4j.base.model.Token;

/**
 * ��token�Ļ����ȡ
 *
 * @className TokenHolder
 * @author jy
 * @date 2015��6��12��
 * @since JDK 1.6
 * @see TokenCreator
 * @see TokenStorager
 */
public class TokenHolder {

	/**
	 * token�Ĵ���
	 */
	private final TokenCreator tokenCreator;
	/**
	 * token�Ĵ洢
	 */
	private final TokenStorager tokenStorager;

	/**
	 *
	 * @param tokenCreator
	 *            token������
	 * @param tokenStorager
	 *            token������
	 */
	public TokenHolder(TokenCreator tokenCreator, TokenStorager tokenStorager) {
		this.tokenCreator = tokenCreator;
		this.tokenStorager = tokenStorager;
	}

	/**
	 * ��ȡtoken����
	 *
	 * @return
	 * @throws WeixinException
	 */
	public Token getToken() throws WeixinException {
		String cacheKey = tokenCreator.getCacheKey();
		Token token = tokenStorager.lookup(cacheKey);
		if (token == null) {
			token = tokenCreator.createToken();
			tokenStorager.caching(cacheKey, token);
		}
		return token;
	}

	/**
	 * ��ȡtoken�ַ���
	 *
	 * @return
	 * @throws WeixinException
	 */
	public String getAccessToken() throws WeixinException {
		return getToken().getAccessToken();
	}

	/**
	 * �ֶ�ˢ��token
	 *
	 * @return ˢ�º��token
	 * @throws WeixinException
	 */
	public Token refreshToken() throws WeixinException {
		String cacheKey = tokenCreator.getCacheKey();
		Token token = tokenCreator.createToken();
		tokenStorager.caching(cacheKey, token);
		return token;
	}

	public TokenStorager getTokenStorager() {
		return tokenStorager;
	}
}
