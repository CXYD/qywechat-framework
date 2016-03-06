package com.wk.wechat4j.qy.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wk.wechat4j.base.exception.WeixinException;
import com.wk.wechat4j.base.http.weixin.JsonResult;
import com.wk.wechat4j.base.http.weixin.WeixinResponse;
import com.wk.wechat4j.base.model.Token;
import com.wk.wechat4j.base.token.TokenCreator;
import com.wk.wechat4j.base.token.TokenHolder;
import com.wk.wechat4j.qy.model.AgentInfo;
import com.wk.wechat4j.qy.model.AgentSetter;
import com.wk.wechat4j.qy.model.OUserInfo;
import com.wk.wechat4j.qy.model.User;
import com.wk.wechat4j.qy.suite.*;

/**
 * ������Ӧ���׼�
 *
 * @className SuiteApi
 * @author jy
 * @date 2015��6��17��
 * @since JDK 1.6
 * @see <a
 *      href="http://qydev.weixin.qq.com/wiki/index.php?title=%E7%AC%AC%E4%B8%89%E6%96%B9%E5%BA%94%E7%94%A8%E6%8E%88%E6%9D%83">������Ӧ����Ȩ</a>
 */
public class SuiteApi extends QyApi {
	/**
	 * Ӧ���׼�token
	 */
	private final TokenHolder suiteTokenHolder;
	/**
	 * Ӧ���׼�ticket
	 */
	private final SuiteTicketHolder suiteTicketHolder;
	/**
	 * Ӧ���׼�pre_code
	 */
	private final TokenHolder suitePreCodeHolder;

	/**
	 *
	 * @param suiteTicketHolder
	 *            �׼�ticket��ȡ
	 */
	public SuiteApi(SuiteTicketHolder suiteTicketHolder) {
		this.suiteTicketHolder = suiteTicketHolder;
		this.suiteTokenHolder = new TokenHolder(new WeixinSuiteTokenCreator(
				suiteTicketHolder), suiteTicketHolder.getTokenStorager());
		this.suitePreCodeHolder = new TokenHolder(
				new WeixinSuitePreCodeCreator(suiteTokenHolder,
						suiteTicketHolder.getSuiteId()),
				suiteTicketHolder.getTokenStorager());
	}

	/**
	 * Ӧ���׼�token
	 *
	 * @return
	 */
	public TokenHolder getSuiteTokenHolder() {
		return this.suiteTokenHolder;
	}

	/**
	 * Ӧ���׼�ticket
	 *
	 * @return
	 */
	public SuiteTicketHolder getTicketHolder() {
		return this.suiteTicketHolder;
	}

	/**
	 * Ӧ���׼�Ԥ��Ȩ��
	 *
	 * @return
	 */
	public TokenHolder getPreCodeHolder() {
		return this.suitePreCodeHolder;
	}

	/**
	 * Ӧ���׼�������Ȩ�룺��ҵ�ŵ�������Ȩ��
	 *
	 * @param authCorpid
	 *            ��Ȩ��corpid
	 * @return
	 */
	public SuitePerCodeHolder getPerCodeHolder(String authCorpId) {
		return new SuitePerCodeHolder(authCorpId,
				suiteTicketHolder.getSuiteId(),
				suiteTicketHolder.getTokenStorager());
	}

	/**
	 * ��ȡ��ҵ��access_token(������Ȩ��)
	 *
	 * @param authCorpid
	 *            ��Ȩ��corpid
	 * @return ��ҵ��token
	 */
	public TokenHolder getTokenSuiteHolder(String authCorpId) {
		return new TokenHolder(new WeixinTokenSuiteCreator(
				getPerCodeHolder(authCorpId), suiteTokenHolder),
				suiteTicketHolder.getTokenStorager());
	}

	/**
	 * �����׼���Ȩ����:�����Ҫ��ĳ����Ȩ�������ã�����ñ��ӿڣ�Ŀǰ������������ЩӦ�ÿ�����Ȩ����������Ĭ����������Ӧ�ý�����Ȩ��
	 *
	 * @param appids
	 *            ���������Ȩ��Ӧ��id����1��2��3
	 * @return ������
	 * @throws WeixinException
	 * @see <a href=
	 *      "http://qydev.weixin.qq.com/wiki/index.php?title=%E7%AC%AC%E4%B8%89%E6%96%B9%E5%BA%94%E7%94%A8%E6%8E%A5%E5%8F%A3%E8%AF%B4%E6%98%8E#.E8.AE.BE.E7.BD.AE.E6.8E.88.E6.9D.83.E9.85.8D.E7.BD.AE"
	 *      >�����׼���Ȩ����</a>
	 */
	public JsonResult setSuiteSession(int... appids) throws WeixinException {
		String suite_set_session_uri = getRequestUri("suite_set_session_uri");
		JSONObject para = new JSONObject();
		para.put("pre_auth_code", suitePreCodeHolder.getAccessToken());
		JSONObject appid = new JSONObject();
		appid.put("appid", appids);
		para.put("session_info", appid);
		WeixinResponse response = weixinExecutor
				.post(String.format(suite_set_session_uri,
						suiteTokenHolder.getAccessToken()), para.toJSONString());
		return response.getAsJsonResult();
	}

	/**
	 * ��ȡ��ҵ�ŵ�������Ȩ��
	 *
	 * @param authCode
	 *            ��ʱ��Ȩ�������Ȩ�ɹ�ʱ������redirect_uri����ת��Ӧ���ṩ����վ��
	 * @return ��Ȩ�õ�����Ϣ
	 * @throws WeixinException
	 * @see com.wk.wechat4j.qy.model.OUserInfo
	 * @see <a href=
	 *      "http://qydev.weixin.qq.com/wiki/index.php?title=%E7%AC%AC%E4%B8%89%E6%96%B9%E5%BA%94%E7%94%A8%E6%8E%A5%E5%8F%A3%E8%AF%B4%E6%98%8E#.E8.8E.B7.E5.8F.96.E4.BC.81.E4.B8.9A.E5.8F.B7.E7.9A.84.E6.B0.B8.E4.B9.85.E6.8E.88.E6.9D.83.E7.A0.81"
	 *      >��ȡ��ҵ�ŵ�������Ȩ��</a>
	 */
	public OUserInfo exchangePermanentCode(String authCode)
			throws WeixinException {
		String suite_get_permanent_uri = getRequestUri("suite_get_permanent_uri");
		JSONObject obj = new JSONObject();
		obj.put("suite_id", suiteTicketHolder.getSuiteId());
		obj.put("auth_code", authCode);
		WeixinResponse response = weixinExecutor.post(
				String.format(suite_get_permanent_uri,
						suiteTokenHolder.getAccessToken()), obj.toJSONString());
		obj = response.getAsJson();
		obj.put("corp_info", obj.remove("auth_corp_info"));
		obj.put("user_info", obj.remove("auth_user_info"));
		OUserInfo oInfo = JSON.toJavaObject(obj, OUserInfo.class);
		// ΢����Ȩ��ҵ�ŵ�������Ȩ��
		SuitePerCodeHolder suitePerCodeHolder = getPerCodeHolder(oInfo
				.getCorpInfo().getCorpId());
		// ����΢����ҵ��access_token
		TokenCreator tokenCreator = new WeixinTokenSuiteCreator(
				suitePerCodeHolder, suiteTokenHolder);
		Token token = new Token(obj.getString("access_token"));
		token.setExpiresIn(obj.getIntValue("expires_in"));
		token.setCreateTime(System.currentTimeMillis());
		suiteTicketHolder.getTokenStorager().caching(
				tokenCreator.getCacheKey(), token);
		// ����΢����ҵ��������Ȩ��
		suitePerCodeHolder
				.cachingPermanentCode(obj.getString("permanent_code"));
		return oInfo;
	}

	/**
	 * ��ȡ��ҵ�ŵ���Ȩ��Ϣ
	 *
	 * @param authCorpId
	 *            ��Ȩ��corpid
	 * @return ��Ȩ����Ϣ
	 * @throws WeixinException
	 * @see com.wk.wechat4j.qy.model.OUserInfo
	 * @see <a
	 *      href="http://qydev.weixin.qq.com/wiki/index.php?title=%E7%AC%AC%E4%B8%89%E6%96%B9%E5%BA%94%E7%94%A8%E6%8E%A5%E5%8F%A3%E8%AF%B4%E6%98%8E#.E8.8E.B7.E5.8F.96.E4.BC.81.E4.B8.9A.E5.8F.B7.E7.9A.84.E6.8E.88.E6.9D.83.E4.BF.A1.E6.81.AF">��ȡ��ҵ�ŵ���Ȩ��Ϣ</a>
	 */
	public OUserInfo getOAuthInfo(String authCorpId) throws WeixinException {
		String suite_get_authinfo_uri = getRequestUri("suite_get_authinfo_uri");
		JSONObject obj = new JSONObject();
		obj.put("suite_id", suiteTicketHolder.getSuiteId());
		obj.put("auth_corpid", authCorpId);
		obj.put("permanent_code", getPerCodeHolder(authCorpId)
				.getPermanentCode());
		WeixinResponse response = weixinExecutor.post(
				String.format(suite_get_authinfo_uri,
						suiteTokenHolder.getAccessToken()), obj.toJSONString());
		obj = response.getAsJson();
		obj.put("corp_info", obj.remove("auth_corp_info"));
		obj.put("user_info", obj.remove("auth_user_info"));
		return JSON.toJavaObject(obj, OUserInfo.class);
	}

	/**
	 * ��ȡ��ҵ��Ӧ��
	 *
	 * @param authCorpId
	 *            ��Ȩ��corpid
	 * @param agentid
	 *            ��Ȩ��Ӧ��id
	 * @return Ӧ����Ϣ
	 * @see com.wk.wechat4j.qy.model.AgentInfo
	 * @see <a
	 *      href="http://qydev.weixin.qq.com/wiki/index.php?title=%E7%AC%AC%E4%B8%89%E6%96%B9%E5%BA%94%E7%94%A8%E6%8E%A5%E5%8F%A3%E8%AF%B4%E6%98%8E#.E8.8E.B7.E5.8F.96.E4.BC.81.E4.B8.9A.E5.8F.B7.E5.BA.94.E7.94.A8">��ȡ��ҵ��Ӧ��</a>
	 * @throws WeixinException
	 */
	public AgentInfo getAgent(String authCorpId, int agentid)
			throws WeixinException {
		String suite_get_agent_uri = getRequestUri("suite_get_agent_uri");
		JSONObject obj = new JSONObject();
		obj.put("suite_id", suiteTicketHolder.getSuiteId());
		obj.put("auth_corpid", authCorpId);
		obj.put("permanent_code", getPerCodeHolder(authCorpId)
				.getPermanentCode());
		obj.put("agentid", agentid);
		WeixinResponse response = weixinExecutor.post(
				String.format(suite_get_agent_uri,
						suiteTokenHolder.getAccessToken()), obj.toJSONString());
		JSONObject jsonObj = response.getAsJson();
		AgentInfo agent = JSON.toJavaObject(jsonObj, AgentInfo.class);
		agent.setAllowUsers(JSON.parseArray(
				jsonObj.getJSONObject("allow_userinfos").getString("user"),
				User.class));
		agent.setAllowPartys(JSON.parseArray(
				jsonObj.getJSONObject("allow_partys").getString("partyid"),
				Integer.class));
		agent.setAllowTags(JSON.parseArray(jsonObj.getJSONObject("allow_tags")
				.getString("tagid"), Integer.class));
		return agent;
	}

	/**
	 * ������ҵӦ�õ�ѡ��������Ϣ���磺����λ���ϱ���
	 *
	 * @param authCorpId
	 *            ��Ȩ��corpid
	 * @param agentSet
	 *            ������Ϣ
	 * @see com.wk.wechat4j.qy.model.AgentSetter
	 * @see <a
	 *      href="http://qydev.weixin.qq.com/wiki/index.php?title=%E8%AE%BE%E7%BD%AE%E4%BC%81%E4%B8%9A%E5%8F%B7%E5%BA%94%E7%94%A8">������ҵ����Ϣ</a>
	 * @return ������
	 * @throws WeixinException
	 */
	public JsonResult setAgent(String authCorpId, AgentSetter agentSet)
			throws WeixinException {
		String suite_set_agent_uri = getRequestUri("suite_set_agent_uri");
		JSONObject obj = new JSONObject();
		obj.put("suite_id", suiteTicketHolder.getSuiteId());
		obj.put("auth_corpid", authCorpId);
		obj.put("permanent_code", getPerCodeHolder(authCorpId)
				.getPermanentCode());
		obj.put("agent", agentSet);
		WeixinResponse response = weixinExecutor.post(
				String.format(suite_set_agent_uri,
						suiteTokenHolder.getAccessToken()),
				JSON.toJSONString(obj, AgentApi.typeFilter));
		return response.getAsJsonResult();
	}
}
