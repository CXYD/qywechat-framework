package com.wk.wechat4j.base.token;


import com.wk.wechat4j.base.exception.WeixinException;
import com.wk.wechat4j.base.model.Token;

/**
 * TOKEN������
 *
 * @className TokenCreator
 * @author jy
 * @date 2015��1��10��
 * @since JDK 1.6
 * @see
 */
public interface TokenCreator {
	/**
	 * ���ػ���KEY������
	 *
	 * @return
	 */
	public String getCacheKey();

	/**
	 * ����token
	 * 
	 * @return
	 * @throws WeixinException
	 */
	public Token createToken() throws WeixinException;
}
