package com.wk.wechat4j.base.http.factory;

import com.wk.wechat4j.base.http.AbstractHttpClient;
import com.wk.wechat4j.base.http.HttpClientException;
import com.wk.wechat4j.base.http.HttpHeaders;
import com.wk.wechat4j.base.http.HttpMethod;
import com.wk.wechat4j.base.http.apache.MultipartEntity;
import com.wk.wechat4j.base.http.entity.HttpEntity;
import com.wk.wechat4j.base.util.StringUtil;
import org.apache.http.client.methods.*;
import org.apache.http.conn.ssl.X509HostnameVerifier;
import org.apache.http.entity.AbstractHttpEntity;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.cert.X509Certificate;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;


/**
 * Apache HttpComponents 4.x
 * 
 * @className HttpComponent4
 * @author jy
 * @date 2015Äê8ÔÂ18ÈÕ
 * @since JDK 1.6
 * @see
 */
public abstract class HttpComponent4 extends AbstractHttpClient {
	protected HttpRequestBase createHttpRequest(HttpMethod method,
			java.net.URI uri) throws HttpClientException {
		if (method == HttpMethod.GET) {
			return new HttpGet(uri);
		} else if (method == HttpMethod.HEAD) {
			return new HttpHead(uri);
		} else if (method == HttpMethod.POST) {
			return new HttpPost(uri);
		} else if (method == HttpMethod.PUT) {
			return new HttpPut(uri);
		} else if (method == HttpMethod.DELETE) {
			return new HttpDelete(uri);
		} else if (method == HttpMethod.OPTIONS) {
			return new HttpOptions(uri);
		} else if (method == HttpMethod.TRACE) {
			return new HttpTrace(uri);
		} else {
			throw new HttpClientException("unknown request method " + method
					+ " for " + uri);
		}
	}

	protected void addHeaders(HttpHeaders headers, HttpRequestBase uriRequest) {
		if (headers == null) {
			headers = new HttpHeaders();
		}
		// Add default accept headers
		if (!headers.containsKey(HttpHeaders.ACCEPT)) {
			headers.set(HttpHeaders.ACCEPT, "*/*");
		}
		// Add default user agent
		if (!headers.containsKey(HttpHeaders.USER_AGENT)) {
			headers.set(HttpHeaders.USER_AGENT, "apache/httpclient4");
		}
		for (Iterator<Entry<String, List<String>>> headerIterator = headers
				.entrySet().iterator(); headerIterator.hasNext();) {
			Entry<String, List<String>> header = headerIterator.next();
			if (HttpHeaders.COOKIE.equalsIgnoreCase(header.getKey())) {
				uriRequest.setHeader(header.getKey(),
						StringUtil.join(header.getValue(), ';'));
			} else {
				for (String headerValue : header.getValue()) {
					uriRequest.setHeader(header.getKey(),
							headerValue != null ? headerValue : "");
				}
			}
		}
	}

	protected void addEntity(HttpEntity entity, HttpRequestBase uriRequest)
			throws IOException {
		if (entity != null) {
			AbstractHttpEntity httpEntity = null;
			if (entity instanceof MultipartEntity) {
				ByteArrayOutputStream os = new ByteArrayOutputStream();
				entity.writeTo(os);
				os.flush();
				httpEntity = new org.apache.http.entity.ByteArrayEntity(
						os.toByteArray());
				os.close();
			} else {
				httpEntity = new InputStreamEntity(entity.getContent(),
						entity.getContentLength());
			}
			httpEntity.setContentType(entity.getContentType().toString());
			((HttpEntityEnclosingRequestBase) uriRequest).setEntity(httpEntity);
		}
	}

	protected byte[] getContent(org.apache.http.HttpResponse httpResponse)
			throws IOException {
		return EntityUtils.toByteArray(httpResponse.getEntity());
	}

	protected static class CustomHostnameVerifier implements
			X509HostnameVerifier {

		private final HostnameVerifier hostnameVerifier;

		public CustomHostnameVerifier(HostnameVerifier hostnameVerifier) {
			this.hostnameVerifier = hostnameVerifier;
		}

		@Override
		public boolean verify(String hostname, SSLSession session) {
			return hostnameVerifier.verify(hostname, session);
		}

		@Override
		public void verify(String host, SSLSocket ssl) throws IOException {
		}

		@Override
		public void verify(String host, X509Certificate cert)
				throws SSLException {
		}

		@Override
		public void verify(String host, String[] cns, String[] subjectAlts)
				throws SSLException {
		}
	}
}
