package com.wk.wechat4j.base.http.weixin;

import com.wk.wechat4j.base.exception.WeixinException;
import com.wk.wechat4j.base.model.Consts;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import java.io.InputStream;
import java.security.KeyStore;


/**
 * ΢��ssl����
 *
 * @className WeixinSSLRequestExecutor
 * @author jy
 * @date 2015��8��17��
 * @since JDK 1.6
 * @see
 */
public class WeixinSSLRequestExecutor extends WeixinRequestExecutor {

	private final SSLContext sslContext;

	public WeixinSSLRequestExecutor(String password, InputStream inputStream)
			throws WeixinException {
		try {
			KeyStore keyStore = KeyStore.getInstance(Consts.PKCS12);
			keyStore.load(inputStream, password.toCharArray());
			KeyManagerFactory kmf = KeyManagerFactory
					.getInstance(Consts.SunX509);
			kmf.init(keyStore, password.toCharArray());
			sslContext = SSLContext.getInstance("TLS");
			sslContext.init(kmf.getKeyManagers(), null,
					new java.security.SecureRandom());
		} catch (Exception e) {
			throw new WeixinException("Key load error", e);
		}
		params.setSSLContext(sslContext);
	}

	public WeixinSSLRequestExecutor(SSLContext sslContext) {
		this.sslContext = sslContext;
		params.setSSLContext(sslContext);
	}

	public SSLContext getSSLContext() {
		return sslContext;
	}
}
