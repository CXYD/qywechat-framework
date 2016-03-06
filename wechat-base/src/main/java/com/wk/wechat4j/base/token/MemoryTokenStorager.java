package com.wk.wechat4j.base.token;

import com.wk.wechat4j.base.exception.WeixinException;
import com.wk.wechat4j.base.model.Token;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * ���ڴ汣��TOKEN(���Ƽ�ʹ��)
 *
 * @className MemoryTokenStorager
 * @author jy
 * @date 2016��1��24��
 * @since JDK 1.6
 * @see
 */
public class MemoryTokenStorager implements TokenStorager {

	private final Map<String, Token> CONMAP;

	public MemoryTokenStorager() {
		this.CONMAP = new ConcurrentHashMap<String, Token>();
	}

	@Override
	public Token lookup(String cacheKey) throws WeixinException {
		Token token = this.CONMAP.get(cacheKey);
		if (token != null) {
			if ((token.getCreateTime() + (token.getExpiresIn() * 1000l) - 2) > System
					.currentTimeMillis()) {
				return token;
			}
		}
		return null;
	}

	@Override
	public void caching(String cacheKey, Token token) throws WeixinException {
		this.CONMAP.put(cacheKey, token);
	}

	@Override
	public Token evict(String cacheKey) {
		return this.CONMAP.remove(cacheKey);
	}

	@Override
	public void clear() {
		this.CONMAP.clear();
	}
}
