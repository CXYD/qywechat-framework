package com.wk.wechat4j.qy.api;

import com.wk.wechat4j.base.model.Consts;
import com.wk.wechat4j.base.model.WeixinAccount;
import com.wk.wechat4j.base.util.Weixin4jConfigUtil;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;


/**
 * ��ҵ��oauth��Ȩ
 *
 * @className OauthApi
 * @author jy
 * @date 2015��6��11��
 * @since JDK 1.6
 * @see <a
 *      href="http://qydev.weixin.qq.com/wiki/index.php?title=OAuth%E9%AA%8C%E8%AF%81%E6%8E%A5%E5%8F%A3">��ҵ���û������Ȩ˵��</a>
 * @see <a
 *      href="http://qydev.weixin.qq.com/wiki/index.php?title=%E4%BC%81%E4%B8%9A%E5%8F%B7%E7%99%BB%E5%BD%95%E6%8E%88%E6%9D%83">��ҵ�ŵ������ṩ����Ȩ˵��</a>
 * @see <a
 *      href="http://qydev.weixin.qq.com/wiki/index.php?title=%E7%AC%AC%E4%B8%89%E6%96%B9%E5%BA%94%E7%94%A8%E6%8E%88%E6%9D%83">��ҵ�ŵ������׼�Ӧ����Ȩ˵��</a>
 */
public class OauthApi extends QyApi {
	private final WeixinAccount account;

	public OauthApi() {
		this(Weixin4jConfigUtil.getWeixinAccount());
	}

	public OauthApi(WeixinAccount account) {
		this.account = account;
	}

	/**
	 * ��ҵ���û������Ȩ
	 *
	 * @see {@link #getUserAuthorizeURL(String, String,String)}
	 *
	 * @return ������Ȩ��URL
	 */
	public String getUserAuthorizeURL() {
		String corpId = account.getId();
		String redirectUri = Weixin4jConfigUtil
				.getValue("user.oauth.redirect.uri");
		return getUserAuthorizeURL(corpId, redirectUri, "state");
	}

	/**
	 * ��ҵ���û������Ȩ
	 *
	 * @param corpId
	 *            ��ҵ�ŵ�corpid
	 * @param redirectUri
	 *            �ض����ַ
	 * @param state
	 *            ���ڱ�������ͻص���״̬
	 * @return ������Ȩ��URL
	 * @see <a
	 *      href="http://qydev.weixin.qq.com/wiki/index.php?title=%E4%BC%81%E4%B8%9A%E5%8F%B7%E7%99%BB%E5%BD%95%E6%8E%88%E6%9D%83">��ҵ���û������Ȩ</a>
	 */
	public String getUserAuthorizeURL(String corpId, String redirectUri,
			String state) {
		String oauth_uri = getRequestUri("user_oauth_uri");
		try {
			return String.format(oauth_uri, corpId,
					URLEncoder.encode(redirectUri, Consts.UTF_8.name()), state);
		} catch (UnsupportedEncodingException e) {
			;
		}
		return "";
	}

	/**
	 * ��ҵ�ŵ������ṩ����Ȩ
	 *
	 * @see {@link #getThirdAuthorizeURL(String, String,String)}
	 *
	 * @return ������Ȩ��URL
	 */
	public String getThirdAuthorizeURL() {
		String corpId = account.getId();
		String redirectUri = Weixin4jConfigUtil
				.getValue("third.oauth.redirect.uri");
		return getThirdAuthorizeURL(corpId, redirectUri, "state");
	}

	/**
	 * ��ҵ�ŵ�½��Ȩ
	 *
	 * @param corpId
	 *            ��ҵ�ţ��ṩ�̣���corpid
	 * @param redirectUri
	 *            �ض����ַ
	 * @param state
	 *            ���ڱ�������ͻص���״̬����Ȩ�����ԭ�����ظ�������
	 * @return ������Ȩ��URL
	 * @see ProviderApi
	 * @see {@link com.wk.wechat4j.qy.WeixinSuiteProxy#getOUserInfoByCode(String)}
	 * @see <a
	 *      href="http://qydev.weixin.qq.com/wiki/index.php?title=%E4%BC%81%E4%B8%9A%E5%8F%B7%E7%99%BB%E5%BD%95%E6%8E%88%E6%9D%83">��ҵ�ŵ������ṩ����Ȩ</a>
	 */
	public String getThirdAuthorizeURL(String corpId, String redirectUri,
			String state) {
		String oauth_uri = getRequestUri("provider_oauth_uri");
		try {
			return String.format(oauth_uri, corpId,
					URLEncoder.encode(redirectUri, Consts.UTF_8.name()), state);
		} catch (UnsupportedEncodingException e) {
			;
		}
		return "";
	}

	/**
	 * Ӧ���׼���Ȩ
	 *
	 * @see {@link #getSuiteAuthorizeURL(String,String, String,String)}
	 * @param suiteId
	 *            �׼�ID
	 * @param preAuthCode
	 *            Ԥ��Ȩ��
	 * @return
	 */
	public String getSuiteAuthorizeURL(String suiteId, String preAuthCode) {
		String redirectUri = Weixin4jConfigUtil
				.getValue("suite.oauth.redirect.uri");
		return getSuiteAuthorizeURL(suiteId, preAuthCode, redirectUri, "state");
	}

	/**
	 * Ӧ���׼���Ȩ
	 *
	 * @param suiteId
	 *            �׼�ID
	 * @param preAuthCode
	 *            Ԥ��Ȩ��
	 * @param redirectUri
	 *            ��Ȩ���ض���url
	 * @param state
	 *            �ص���ԭ������
	 * @see <a href="http://qydev.weixin.qq.com/wiki/index.php?title
	 *      =%E4%BC%81%E4%B8%9A%E5%8F%B7%E7%AE%A1%E7%90%86%E5%91%98%E6%
	 *      8E%88%E6%9D%83%E5%BA%94%E7%94%A8">��ҵ�ŵ�����Ӧ���׼���Ȩ</a>
	 * @return ������Ȩ��URL
	 */
	public String getSuiteAuthorizeURL(String suiteId, String preAuthCode,
			String redirectUri, String state) {
		String suite_oauth_uri = getRequestUri("suite_oauth_uri");
		try {
			return String.format(suite_oauth_uri, suiteId, preAuthCode,
					URLEncoder.encode(redirectUri, Consts.UTF_8.name()), state);
		} catch (UnsupportedEncodingException e) {
			;
		}
		return "";
	}
}
