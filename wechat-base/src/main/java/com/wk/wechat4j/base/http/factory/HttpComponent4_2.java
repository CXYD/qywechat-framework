package com.wk.wechat4j.base.http.factory;

import com.wk.wechat4j.base.http.HttpClientException;
import com.wk.wechat4j.base.http.HttpParams;
import com.wk.wechat4j.base.http.HttpRequest;
import com.wk.wechat4j.base.http.HttpResponse;
import com.wk.wechat4j.base.model.Consts;
import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.config.RequestConfig.Builder;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.config.ConnectionConfig;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.X509HostnameVerifier;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;

import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.net.InetSocketAddress;

/**
 * Requires Apache HttpComponents 4.3 or higher
 * 
 * @className HttpComponent4_2
 * @author jy
 * @date 2015Äê8ÔÂ18ÈÕ
 * @since JDK 1.6
 * @see
 */
public class HttpComponent4_2 extends HttpComponent4 {

	private CloseableHttpClient httpClient;

	public HttpComponent4_2() {
		this.httpClient = HttpClientBuilder
				.create()
				.setDefaultConnectionConfig(
						ConnectionConfig.custom().setCharset(Consts.UTF_8)
								.build()).build();
	}

	@Override
	public HttpResponse execute(HttpRequest request) throws HttpClientException {
		HttpResponse response = null;
		try {
			HttpRequestBase uriRequest = createHttpRequest(request.getMethod(),
					request.getURI());
			boolean useSSL = "https".equals(request.getURI().getScheme());
			SSLContext sslContext = null;
			X509HostnameVerifier hostnameVerifier = null;
			HttpParams params = request.getParams();
			if (params != null) {
				Builder requestConfig = RequestConfig.custom()
						.setSocketTimeout(params.getSocketTimeout())
						.setConnectTimeout(params.getConnectTimeout())
						.setConnectionRequestTimeout(params.getReadTimeout());
				if (params.getProxy() != null) {
					InetSocketAddress socketAddress = (InetSocketAddress) params
							.getProxy().address();
					HttpHost proxy = new HttpHost(socketAddress.getHostName(),
							socketAddress.getPort());
					requestConfig.setProxy(proxy);
					useSSL = false;
				}
				uriRequest.setConfig(requestConfig.build());
				sslContext = params.getSSLContext();
				if (params.getHostnameVerifier() != null) {
					hostnameVerifier = new CustomHostnameVerifier(
							params.getHostnameVerifier());
				}
			}
			if (useSSL) {
				if (sslContext == null) {
					sslContext = HttpClientFactory.allowSSLContext();
				}
				if (hostnameVerifier == null) {
					hostnameVerifier = SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER;
				}
				httpClient = HttpClients
						.custom()
						.setHostnameVerifier(hostnameVerifier)
						.setDefaultConnectionConfig(
								ConnectionConfig.custom()
										.setCharset(Consts.UTF_8).build())
						.setSslcontext(sslContext).build();
			}
			addHeaders(request.getHeaders(), uriRequest);
			addEntity(request.getEntity(), uriRequest);
			CloseableHttpResponse httpResponse = httpClient.execute(uriRequest);
			response = new HttpComponent4_2Response(httpResponse,
					getContent(httpResponse));
			handleResponse(response);
		} catch (IOException e) {
			throw new HttpClientException("I/O error on "
					+ request.getMethod().name() + " request for \""
					+ request.getURI().toString() + "\":" + e.getMessage(), e);
		} finally {
			if (response != null) {
				response.close();
			}
		}
		return response;
	}
}
