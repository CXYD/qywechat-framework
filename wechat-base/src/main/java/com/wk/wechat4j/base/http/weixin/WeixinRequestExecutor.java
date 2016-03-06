package com.wk.wechat4j.base.http.weixin;

import com.oracle.javafx.jmx.json.JSONException;
import com.wk.wechat4j.base.exception.WeixinException;
import com.wk.wechat4j.base.http.*;
import com.wk.wechat4j.base.http.apache.FormBodyPart;
import com.wk.wechat4j.base.http.apache.HttpMultipartMode;
import com.wk.wechat4j.base.http.apache.MultipartEntity;
import com.wk.wechat4j.base.http.entity.FormUrlEntity;
import com.wk.wechat4j.base.http.entity.HttpEntity;
import com.wk.wechat4j.base.http.entity.StringEntity;
import com.wk.wechat4j.base.http.factory.HttpClientFactory;
import com.wk.wechat4j.base.model.Consts;
import com.wk.wechat4j.base.util.StringUtil;
import com.wk.wechat4j.base.util.WeixinErrorUtil;
import com.wk.wechat4j.base.xml.XmlStream;

import java.util.Map;



/**
 * 负责微信请求的执行
 *
 * @className WeixinRequestExecutor
 * @author jy
 * @date 2015年8月15日
 * @since JDK 1.6
 * @see
 */
public class WeixinRequestExecutor {

	protected final HttpClient httpClient;
	protected final HttpParams params;

	public WeixinRequestExecutor() {
		this(new HttpParams());
	}

	public WeixinRequestExecutor(HttpParams params) {
		this.httpClient = HttpClientFactory.getInstance();
		this.params = params;
	}

	public WeixinResponse get(String url) throws WeixinException {
		HttpRequest request = new HttpRequest(HttpMethod.GET, url);
		return doRequest(request);
	}

	public WeixinResponse get(String url, Map<String, String> parameters)
			throws WeixinException {
		StringBuilder buf = new StringBuilder(url);
		if (parameters != null && !parameters.isEmpty()) {
			if (url.indexOf("?") < 0) {
				buf.append("?");
			} else {
				buf.append("&");
			}
			buf.append(FormUrlEntity.formatParameters(parameters));
		}
		return doRequest(new HttpRequest(HttpMethod.GET, buf.toString()));
	}

	public WeixinResponse post(String url) throws WeixinException {
		HttpRequest request = new HttpRequest(HttpMethod.POST, url);
		return doRequest(request);
	}

	public WeixinResponse post(String url, String body) throws WeixinException {
		HttpEntity entity = new StringEntity(body);
		HttpRequest request = new HttpRequest(HttpMethod.POST, url);
		request.setEntity(entity);
		return doRequest(request);
	}

	public WeixinResponse post(String url, FormBodyPart... bodyParts)
			throws WeixinException {
		MultipartEntity entity = new MultipartEntity(
				HttpMultipartMode.BROWSER_COMPATIBLE, null, Consts.UTF_8);
		for (FormBodyPart bodyPart : bodyParts) {
			entity.addPart(bodyPart);
		}
		HttpRequest request = new HttpRequest(HttpMethod.POST, url);
		request.setEntity(entity);
		return doRequest(request);
	}

	public WeixinResponse doRequest(HttpRequest request) throws WeixinException {
		request.setParams(params);
		try {
			HttpResponse httpResponse = httpClient.execute(request);
			HttpHeaders headers = httpResponse.getHeaders();
			WeixinResponse response = new WeixinResponse(httpResponse);
			String contentType = headers.getFirst(HttpHeaders.CONTENT_TYPE);
			String disposition = headers
					.getFirst(HttpHeaders.CONTENT_DISPOSITION);
			// json
			if (contentType
					.contains(ContentType.APPLICATION_JSON.getMimeType())
					|| (disposition != null && disposition.indexOf(".json") > 0)) {
				checkJson(response);
			} else if (contentType.contains(ContentType.TEXT_XML.getMimeType())) {
				checkXml(response);
			} else if (contentType.contains(ContentType.TEXT_PLAIN
					.getMimeType())
					|| contentType
							.contains(ContentType.TEXT_HTML.getMimeType())) {
				try {
					checkJson(response);
					return response;
				} catch (JSONException e) {
					;
				}
				try {
					checkXml(response);
					return response;
				} catch (IllegalArgumentException ex) {
					;
				}
				throw new WeixinException(response.getAsString());
			}
			return response;
		} catch (HttpClientException e) {
			throw new WeixinException(e);
		}
	}

	protected void checkJson(WeixinResponse response) throws WeixinException {
		JsonResult jsonResult = response.getAsJsonResult();
		response.setJsonResult(true);
		if (jsonResult.getCode() != 0) {
			if (StringUtil.isBlank(jsonResult.getDesc())) {
				jsonResult.setDesc(WeixinErrorUtil.getText(Integer
						.toString(jsonResult.getCode())));
			}
			throw new WeixinException(Integer.toString(jsonResult.getCode()),
					jsonResult.getDesc());
		}
	}

	protected void checkXml(WeixinResponse response) throws WeixinException {
		String xmlContent = response.getAsString();
		if (xmlContent.length() != xmlContent.replaceFirst("<retcode>",
				"<return_code>").length()) {
			// <?xml><root><data..../data></root>
			xmlContent = xmlContent.replaceFirst("<root>", "<xml>")
					.replaceFirst("<retcode>", "<return_code>")
					.replaceFirst("</retcode>", "</return_code>")
					.replaceFirst("<retmsg>", "<return_msg>")
					.replaceFirst("</retmsg>", "</return_msg>")
					.replaceFirst("</root>", "</xml>");
		}
		XmlResult xmlResult = XmlStream.fromXML(xmlContent, XmlResult.class);
		response.setText(xmlContent);
		response.setXmlResult(true);
		if ("0".equals(xmlResult.getReturnCode())) {
			return;
		}
		if (!com.wk.wechat4j.base.model.Consts.SUCCESS
				.equalsIgnoreCase(xmlResult.getReturnCode())) {
			throw new WeixinException(xmlResult.getReturnCode(),
					xmlResult.getReturnMsg());
		}
		if (!com.wk.wechat4j.base.model.Consts.SUCCESS
				.equalsIgnoreCase(xmlResult.getResultCode())) {
			throw new WeixinException(xmlResult.getErrCode(),
					xmlResult.getErrCodeDes());
		}
	}

	public HttpClient getExecuteClient() {
		return httpClient;
	}

	public HttpParams getExecuteParams() {
		return params;
	}
}
