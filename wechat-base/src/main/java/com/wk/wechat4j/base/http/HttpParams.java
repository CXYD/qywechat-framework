package com.wk.wechat4j.base.http;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import java.net.Proxy;

/**
 * Http ����
 *
 * @className HttpParams
 * @author jy
 * @date 2015��8��13��
 * @since JDK 1.6
 * @see
 */
public final class HttpParams {

	private boolean allowUserInteraction = true;
	private int connectTimeout = 5000;
	private int socketTimeout = 5000;
	private int readTimeout = 5000;
	private int chunkSize = 4096;
	private long ifModifiedSince = 0l;
	private boolean followRedirects = false;

	/**
	 * �������
	 */
	private Proxy proxy;
	/**
	 * SSL����
	 */
	private SSLContext sslContext;
	/**
	 * hostname����
	 */
	private HostnameVerifier hostnameVerifier;

	public boolean isAllowUserInteraction() {
		return allowUserInteraction;
	}

	public void setAllowUserInteraction(boolean allowUserInteraction) {
		this.allowUserInteraction = allowUserInteraction;
	}

	public int getConnectTimeout() {
		return connectTimeout;
	}

	public void setConnectTimeout(int connectTimeout) {
		this.connectTimeout = connectTimeout;
	}

	public int getSocketTimeout() {
		return socketTimeout;
	}

	public void setSocketTimeout(int socketTimeout) {
		this.socketTimeout = socketTimeout;
	}

	public int getReadTimeout() {
		return readTimeout;
	}

	public void setReadTimeout(int readTimeout) {
		this.readTimeout = readTimeout;
	}

	public int getChunkSize() {
		return chunkSize;
	}

	public void setChunkSize(int chunkSize) {
		this.chunkSize = chunkSize;
	}

	public long getIfModifiedSince() {
		return ifModifiedSince;
	}

	public void setIfModifiedSince(long ifModifiedSince) {
		this.ifModifiedSince = ifModifiedSince;
	}

	public boolean isFollowRedirects() {
		return followRedirects;
	}

	public void setFollowRedirects(boolean followRedirects) {
		this.followRedirects = followRedirects;
	}

	public Proxy getProxy() {
		return proxy;
	}

	public void setProxy(Proxy proxy) {
		this.proxy = proxy;
	}

	public SSLContext getSSLContext() {
		return sslContext;
	}

	public void setSSLContext(SSLContext sslContext) {
		this.sslContext = sslContext;
	}

	public HostnameVerifier getHostnameVerifier() {
		return hostnameVerifier;
	}

	public void setHostnameVerifier(HostnameVerifier hostnameVerifier) {
		this.hostnameVerifier = hostnameVerifier;
	}
}
