package com.wk.wechat4j.qy.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.ValueFilter;
import com.wk.wechat4j.base.exception.WeixinException;
import com.wk.wechat4j.base.http.weixin.JsonResult;
import com.wk.wechat4j.base.http.weixin.WeixinResponse;
import com.wk.wechat4j.base.model.Token;
import com.wk.wechat4j.base.token.TokenHolder;
import com.wk.wechat4j.qy.model.AgentInfo;
import com.wk.wechat4j.qy.model.AgentOverview;
import com.wk.wechat4j.qy.model.AgentSetter;
import com.wk.wechat4j.qy.model.User;

import java.util.List;

/**
 * ����Ӧ�ýӿ�
 *
 * @className AgentApi
 * @author jy
 * @date 2015��3��16��
 * @since JDK 1.6
 * @see <a
 *      href="http://qydev.weixin.qq.com/wiki/index.php?title=%E7%AE%A1%E7%90%86%E4%BC%81%E4%B8%9A%E5%8F%B7%E5%BA%94%E7%94%A8">����Ӧ�ýӿ�˵��</a>
 */
public class AgentApi extends QyApi {
	private final TokenHolder tokenHolder;

	public AgentApi(TokenHolder tokenHolder) {
		this.tokenHolder = tokenHolder;
	}

	/**
	 * ��ȡ��ҵ��ĳ��Ӧ�õĻ�����Ϣ������ͷ���ǳơ��ʺ����͡���֤���͡��ɼ���Χ����Ϣ
	 *
	 * @param agentid
	 *            ��Ȩ��Ӧ��id
	 * @return Ӧ����Ϣ
	 * @see com.wk.wechat4j.qy.model.AgentInfo
	 * @see <a
	 *      href="http://qydev.weixin.qq.com/wiki/index.php?title=%E8%8E%B7%E5%8F%96%E4%BC%81%E4%B8%9A%E5%8F%B7%E5%BA%94%E7%94%A8">��ҵ��Ӧ�õ���Ϣ</a>
	 * @throws WeixinException
	 */
	public AgentInfo getAgent(int agentid) throws WeixinException {
		String agent_get_uri = getRequestUri("agent_get_uri");
		Token token = tokenHolder.getToken();
		WeixinResponse response = weixinExecutor.get(String.format(agent_get_uri,
				token.getAccessToken(), agentid));
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
	 * @param agentSet
	 *            ������Ϣ
	 * @see com.wk.wechat4j.qy.model.AgentSetter
	 * @see <a
	 *      href="http://qydev.weixin.qq.com/wiki/index.php?title=%E8%AE%BE%E7%BD%AE%E4%BC%81%E4%B8%9A%E5%8F%B7%E5%BA%94%E7%94%A8">������ҵ����Ϣ</a>
	 * @return ������
	 * @throws WeixinException
	 */
	public JsonResult setAgent(AgentSetter agentSet) throws WeixinException {
		String agent_set_uri = getRequestUri("agent_set_uri");
		Token token = tokenHolder.getToken();
		WeixinResponse response = weixinExecutor.post(
				String.format(agent_set_uri, token.getAccessToken()),
				JSON.toJSONString(agentSet, typeFilter));
		return response.getAsJsonResult();
	}

	public final static ValueFilter typeFilter;
	static {
		typeFilter = new ValueFilter() {
			@Override
			public Object process(Object object, String name, Object value) {
				if (value instanceof Boolean) {
					return ((Boolean) value) ? 1 : 0;
				}
				if (value instanceof Enum) {
					return ((Enum<?>) value).ordinal();
				}
				return value;
			}
		};
	}

	/**
	 * ��ȡӦ�øſ��б�
	 *
	 * @see com.wk.wechat4j.qy.model.AgentOverview
	 * @see <a
	 *      href="http://qydev.weixin.qq.com/wiki/index.php?title=%E8%8E%B7%E5%8F%96%E5%BA%94%E7%94%A8%E6%A6%82%E5%86%B5%E5%88%97%E8%A1%A8">��ȡӦ�øſ�</a>
	 * @return Ӧ�øſ��б�
	 * @throws WeixinException
	 */
	public List<AgentOverview> listAgentOverview() throws WeixinException {
		String agent_list_uri = getRequestUri("agent_list_uri");
		Token token = tokenHolder.getToken();
		WeixinResponse response = weixinExecutor.get(String.format(agent_list_uri,
				token.getAccessToken()));

		return JSON.parseArray(response.getAsJson().getString("agentlist"),
				AgentOverview.class);
	}
}
