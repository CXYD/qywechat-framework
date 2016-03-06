package com.wk.wechat4j.qy.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wk.wechat4j.base.exception.WeixinException;
import com.wk.wechat4j.base.http.weixin.WeixinResponse;
import com.wk.wechat4j.base.model.Token;
import com.wk.wechat4j.base.token.TokenHolder;
import com.wk.wechat4j.base.util.StringUtil;
import com.wk.wechat4j.qy.model.OUserInfo;
import com.wk.wechat4j.qy.type.LoginTargetType;

/**
 * ���������API
 *
 * @className ProviderApi
 * @author jy
 * @date 2015��12��30��
 * @since JDK 1.6
 * @see <a
 *      href="http://qydev.weixin.qq.com/wiki/index.php?title=%E4%BC%81%E4%B8%9A%E5%8F%B7%E7%99%BB%E5%BD%95%E6%8E%88%E6%9D%83">��ҵ�ŵ�¼��Ȩ˵��</a>
 */
public class ProviderApi extends QyApi {
	private final TokenHolder providerTokenHolder;

	public ProviderApi(TokenHolder providerTokenHolder) {
		this.providerTokenHolder = providerTokenHolder;
	}

	/**
	 * �������׼���ȡ��ҵ�Ź���Ա��¼��Ϣ
	 *
	 * @param authCode
	 *            oauth2.0��Ȩ��ҵ�Ź���Ա��¼������code
	 * @return ��½��Ϣ
	 * @see <a
	 *      href="http://qydev.weixin.qq.com/wiki/index.php?title=%E8%8E%B7%E5%8F%96%E4%BC%81%E4%B8%9A%E7%AE%A1%E7%90%86%E5%91%98%E7%99%BB%E5%BD%95%E4%BF%A1%E6%81%AF">��Ȩ��ȡ��ҵ�Ź���Ա��¼��Ϣ</a>
	 * @see com.wk.wechat4j.qy.model.OUserInfo
	 * @throws WeixinException
	 */
	public OUserInfo getOUserInfoByCode(String authCode) throws WeixinException {
		String oauth_logininfo_uri = getRequestUri("oauth_logininfo_uri");
		WeixinResponse response = weixinExecutor.post(
				String.format(oauth_logininfo_uri,
						providerTokenHolder.getAccessToken()),
				String.format("{\"auth_code\":\"%s\"}", authCode));
		JSONObject obj = response.getAsJson();
		OUserInfo oUser = JSON.toJavaObject(obj, OUserInfo.class);
		oUser.getRedirectLoginInfo().setAccessToken(
				obj.getJSONObject("redirect_login_info").getString(
						"login_ticket"));
		providerTokenHolder.getTokenStorager().caching(
				getLoginTicketCacheKey(oUser.getCorpInfo().getCorpId()),
				oUser.getRedirectLoginInfo());
		return oUser;
	}

	private String getLoginTicketCacheKey(String coprId) {
		return String.format("wx_qy_provider_login_ticket_%s", coprId);
	}

	/**
	 * ��ȡ��¼��ҵ�Ź�����url
	 *
	 * @param corpId
	 *            <font color="red">oauth��Ȩ��corpid</font>
	 * @param targetType
	 *            ��¼��ת����ҵ�ź�̨��Ŀ��ҳ��
	 * @param agentId
	 *            ��Ȩ��Ӧ��id С��1ʱ�򲻴���
	 * @return ��½URL
	 * @see <a
	 *      href="http://qydev.weixin.qq.com/wiki/index.php?title=%E8%8E%B7%E5%8F%96%E7%99%BB%E5%BD%95%E4%BC%81%E4%B8%9A%E5%8F%B7%E5%AE%98%E7%BD%91%E7%9A%84url">��ȡ��¼��ҵ�Ź�����url</a>
	 * @throws WeixinException
	 */
	public String getLoginUrl(String corpId, LoginTargetType targetType,
			int agentId) throws WeixinException {
		Token token = providerTokenHolder.getTokenStorager().lookup(
				getLoginTicketCacheKey(corpId));
		if (token == null || StringUtil.isBlank(token.getAccessToken())) {
			throw new WeixinException("maybe oauth first?");
		}
		String oauth_loginurl_uri = getRequestUri("oauth_loginurl_uri");
		JSONObject obj = new JSONObject();
		obj.put("login_ticket", token.getAccessToken());
		obj.put("target", targetType.name());
		if (agentId > 0) {
			obj.put("agentid", agentId);
		}
		WeixinResponse response = weixinExecutor.post(
				String.format(oauth_loginurl_uri,
						providerTokenHolder.getAccessToken()),
				obj.toJSONString());
		return response.getAsJson().getString("login_url");
	}
}
