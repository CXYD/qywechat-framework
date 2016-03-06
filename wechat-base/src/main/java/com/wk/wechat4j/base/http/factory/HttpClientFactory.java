package com.wk.wechat4j.base.http.factory;

import com.wk.wechat4j.base.http.HttpClient;
import com.wk.wechat4j.base.http.HttpClientException;

import javax.net.ssl.SSLContext;
import javax.net.ssl.X509TrustManager;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;


/**
 * HttpClient����������:�ο�netty��InternalLoggerFactory
 *
 * @className HttpClientFactory
 * @author jy
 * @date 2015��8��12��
 * @since JDK 1.6
 * @see
 */
public abstract class HttpClientFactory {

	/**
	 * Ĭ�ϵ�HttpClient
	 */
	private static volatile HttpClientFactory defaultFactory = newDefaultFactory();

	/**
	 * NettyHttpClient -> ApacheHttpClient ->
	 * SimpleHttpClient(HttpURLConnection)
	 *
	 * @return
	 */
	private static HttpClientFactory newDefaultFactory() {
		HttpClientFactory f;
		try {
			f = new Netty4HttpClientFactory();
		} catch (Throwable e1) {
			try {
				f = new HttpComponent4Factory();
			} catch (Throwable e2) {
				try {
					f = new HttpComponent3Factory();
				} catch (Throwable e3) {
					f = new SimpleHttpClientFactory();
				}
			}
		}
		return f;
	}

	/**
	 * ��ȡĬ�ϵ�HttpClient
	 *
	 * @return
	 */
	public static HttpClientFactory getDefaultFactory() {
		return defaultFactory;
	}

	/**
	 * ��ʽ����Ĭ�ϵ�HttpClient
	 *
	 * @param defaultFactory
	 */
	public static void setDefaultFactory(HttpClientFactory defaultFactory) {
		if (defaultFactory == null) {
			throw new NullPointerException("defaultFactory");
		}
		HttpClientFactory.defaultFactory = defaultFactory;
	}

	/**
	 * ��ȡHttpClientʵ��
	 *
	 * @return
	 */
	public static HttpClient getInstance() {
		return getDefaultFactory().newInstance();
	}

	/**
	 * ��ȡHttpClientʵ��
	 * 
	 * @return
	 */
	public abstract HttpClient newInstance();

	public static SSLContext allowSSLContext() throws HttpClientException {
		try {
			SSLContext sslContext = SSLContext.getInstance("TLS");
			sslContext.init(null,
					new X509TrustManager[] { createX509TrustManager() },
					new java.security.SecureRandom());
			return sslContext;
		} catch (NoSuchAlgorithmException e) {
			throw new HttpClientException(
					"Create SSLContext NoSuchAlgorithmException:", e);
		} catch (KeyManagementException e) {
			throw new HttpClientException(
					"Create SSLContext KeyManagementException:", e);
		}
	}

	protected static X509TrustManager createX509TrustManager() {
		return new X509TrustManager() {
			@Override
			public X509Certificate[] getAcceptedIssuers() {
				return null;
			}

			@Override
			public void checkServerTrusted(
					X509Certificate[] paramArrayOfX509Certificate,
					String paramString) throws CertificateException {
			}

			@Override
			public void checkClientTrusted(
					X509Certificate[] paramArrayOfX509Certificate,
					String paramString) throws CertificateException {
			}
		};
	}
}
