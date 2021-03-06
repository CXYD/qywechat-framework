package com.wk.wechat4j.base.http;

import com.wk.wechat4j.base.http.entity.FormUrlEntity;
import com.wk.wechat4j.base.http.entity.HttpEntity;

import java.util.Arrays;
import java.util.Set;


public abstract class AbstractHttpClient implements HttpClient {

	@Override
	public HttpResponse get(String url) throws HttpClientException {
		return execute(HttpMethod.GET, url);
	}

	@Override
	public HttpResponse get(String url, URLParameter... parameters)
			throws HttpClientException {
		return execute(HttpMethod.GET, url, parameters);
	}

	@Override
	public HttpHeaders head(String url) throws HttpClientException {
		return head(url, (URLParameter[]) null);
	}

	@Override
	public HttpHeaders head(String url, URLParameter... parameters)
			throws HttpClientException {
		return execute(HttpMethod.HEAD, url, parameters).getHeaders();
	}

	@Override
	public HttpResponse post(String url) throws HttpClientException {
		return execute(HttpMethod.POST, url);
	}

	@Override
	public HttpResponse post(String url, URLParameter... parameters)
			throws HttpClientException {
		HttpEntity entity = null;
		if (parameters != null && parameters.length > 0) {
			entity = new FormUrlEntity(Arrays.asList(parameters));
		}
		return post(url, entity);
	}

	@Override
	public HttpResponse post(String url, HttpEntity entity)
			throws HttpClientException {
		HttpRequest request = new HttpRequest(HttpMethod.POST, url);
		request.setEntity(entity);
		return execute(request);
	}

	@Override
	public void put(String url) throws HttpClientException {
		execute(HttpMethod.PUT, url);
	}

	@Override
	public void put(String url, URLParameter... parameters)
			throws HttpClientException {
		execute(HttpMethod.PUT, url, parameters);
	}

	@Override
	public void delete(String url) throws HttpClientException {
		execute(HttpMethod.DELETE, url);
	}

	@Override
	public void delete(String url, URLParameter... parameters)
			throws HttpClientException {
		execute(HttpMethod.DELETE, url, parameters);
	}

	@Override
	public Set<HttpMethod> options(String url) throws HttpClientException {
		return options(url, (URLParameter[]) null);
	}

	@Override
	public Set<HttpMethod> options(String url, URLParameter... parameters)
			throws HttpClientException {
		HttpHeaders headers = execute(HttpMethod.OPTIONS, url, parameters)
				.getHeaders();
		return headers.getAllow();
	}

	protected HttpResponse execute(HttpMethod method, String url)
			throws HttpClientException {
		return execute(new HttpRequest(method, url));
	}

	protected HttpResponse execute(HttpMethod method, String url,
			URLParameter... parameters) throws HttpClientException {
		StringBuilder buf = new StringBuilder(url);
		if (parameters != null && parameters.length > 0) {
			if (url.indexOf("?") < 0) {
				buf.append("?");
			} else {
				buf.append("&");
			}
			buf.append(FormUrlEntity.formatParameters(Arrays.asList(parameters)));
		}
		return execute(new HttpRequest(method, buf.toString()));
	}

	protected boolean hasError(HttpStatus status) {
		return (status.series() == HttpStatus.Series.CLIENT_ERROR || status
				.series() == HttpStatus.Series.SERVER_ERROR);
	}

	protected void handleResponse(HttpResponse response)
			throws HttpClientException {
		HttpStatus status = response.getStatus();
		HttpHeaders headers = response.getHeaders();
		String contentType = headers.getFirst(HttpHeaders.CONTENT_TYPE);
		boolean jsonResult = contentType != null
				&& contentType.contains(ContentType.APPLICATION_JSON
						.getMimeType());
		if (!jsonResult && hasError(status)) {
			switch (status.series()) {
			case CLIENT_ERROR:
			case SERVER_ERROR:
				throw new HttpClientException(String.format("%d %s",
						status.getStatusCode(), status.getStatusText()));
			default:
				throw new HttpClientException("Unknown status code [" + status
						+ "]");
			}
		}
	}
}
