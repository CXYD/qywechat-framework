package com.wk.wechat4j.base.token;


import com.wk.wechat4j.base.model.Token;

/**
 * token�Ĵ洢
 *
 * @className TokenStorager
 * @author jy.hu
 * @date 2014��9��27��
 * @since JDK 1.6
 * @see com.wk.wechat4j.base.model.Token
 * @see MemoryTokenStorager
 * @see FileTokenStorager
 * @see RedisTokenStorager
 */
public interface TokenStorager extends CacheStorager<Token> {
}
