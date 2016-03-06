package com.wk.wechat4j.base.token;


import com.wk.wechat4j.base.model.Token;

/**
 * token的存储
 *
 * @className TokenStorager
 * @author jy.hu
 * @date 2014年9月27日
 * @since JDK 1.6
 * @see com.wk.wechat4j.base.model.Token
 * @see MemoryTokenStorager
 * @see FileTokenStorager
 * @see RedisTokenStorager
 */
public interface TokenStorager extends CacheStorager<Token> {
}
