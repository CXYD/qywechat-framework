package com.wk.wechat4j.base.token;


import com.wk.wechat4j.base.exception.WeixinException;

/**
 * cache�洢
 *
 * @className CacheStorager
 * @author jy
 * @date 2015��6��22��
 * @since JDK 1.6
 * @see
 */
public interface CacheStorager<T> {
	/**
	 * ���һ����еĶ���
	 *
	 * @param cacheKey
	 *            ����key
	 * @return
	 * @throws WeixinException
	 */
	T lookup(String cacheKey) throws WeixinException;

	/**
	 * �����µĶ���
	 *
	 * @param cacheKey
	 *            ����key
	 *
	 * @param t
	 *            ��Ҫ����Ķ���
	 * @throws WeixinException
	 */
	void caching(String cacheKey, T t) throws WeixinException;

	/**
	 * �Ƴ��������
	 *
	 * @param cacheKey
	 *            ����key
	 * @return �Ƴ��Ķ���
	 */
	T evict(String cacheKey);

	/**
	 * ������л������(<font color="red">������</font>)
	 */
	void clear();
}
