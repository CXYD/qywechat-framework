package com.wk.wechat4j.qy.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wk.wechat4j.base.exception.WeixinException;
import com.wk.wechat4j.base.http.weixin.JsonResult;
import com.wk.wechat4j.base.http.weixin.WeixinResponse;
import com.wk.wechat4j.base.model.Token;
import com.wk.wechat4j.base.token.TokenHolder;
import com.wk.wechat4j.qy.model.Party;

import java.util.List;

/**
 * ����API
 *
 * @className PartyApi
 * @author jy
 * @date 2014��11��18��
 * @since JDK 1.6
 * @see com.wk.wechat4j.qy.model.Party
 * @see <a
 *      href="http://qydev.weixin.qq.com/wiki/index.php?title=%E7%AE%A1%E7%90%86%E9%83%A8%E9%97%A8">������˵��</a>
 */
public class PartyApi extends QyApi {
	private final TokenHolder tokenHolder;

	public PartyApi(TokenHolder tokenHolder) {
		this.tokenHolder = tokenHolder;
	}

	/**
	 * ��������(�����ŵ�parentidΪ1)
	 *
	 * @param depart
	 *            ���Ŷ���
	 * @see com.wk.wechat4j.qy.model.Party
	 * @see <a
	 *      href="http://qydev.weixin.qq.com/wiki/index.php?title=%E7%AE%A1%E7%90%86%E9%83%A8%E9%97%A8#.E5.88.9B.E5.BB.BA.E9.83.A8.E9.97.A8">��������˵��</a>
	 * @return ����ID
	 * @throws WeixinException
	 */
	public int createParty(Party party) throws WeixinException {
		String department_create_uri = getRequestUri("department_create_uri");
		JSONObject obj = (JSONObject) JSON.toJSON(party);
		if (party.getParentId() < 1) {
			obj.remove("parentid");
		}
		if (party.getId() < 1) {
			obj.remove("id");
		}
		Token token = tokenHolder.getToken();
		WeixinResponse response = weixinExecutor.post(
				String.format(department_create_uri, token.getAccessToken()),
				obj.toJSONString());
		return response.getAsJson().getIntValue("id");
	}

	/**
	 * ���²���(����Ǳ�����ֶ�δָ�� �򲻸��¸��ֶ�֮ǰ������ֵ)
	 *
	 * @param depart
	 *            ���Ŷ���
	 * @see com.wk.wechat4j.qy.model.Party
	 * @see <a
	 *      href="http://qydev.weixin.qq.com/wiki/index.php?title=%E7%AE%A1%E7%90%86%E9%83%A8%E9%97%A8#.E6.9B.B4.E6.96.B0.E9.83.A8.E9.97.A8">���²���˵��</a>
	 * @return ������
	 * @throws WeixinException
	 */
	public JsonResult updateParty(Party party) throws WeixinException {
		if (party.getId() < 1) {
			throw new WeixinException("department id must gt 1");
		}
		String department_update_uri = getRequestUri("department_update_uri");
		JSONObject obj = (JSONObject) JSON.toJSON(party);
		if (party.getParentId() < 1) {
			obj.remove("parentid");
		}
		if (party.getOrder() < 0) {
			obj.remove("order");
		}
		Token token = tokenHolder.getToken();
		WeixinResponse response = weixinExecutor.post(
				String.format(department_update_uri, token.getAccessToken()),
				obj.toJSONString());
		return response.getAsJsonResult();
	}

	/**
	 * ��ѯ�����б�(�Բ��ŵ�order�ֶδ�С��������)
	 *
	 * @param partId
	 *            ����ID����ȡָ������ID�µ��Ӳ��� ����0��ʾ��ȡȫ���Ӳ���
	 * @see com.wk.wechat4j.qy.model.Party
	 * @see <a
	 *      href="http://qydev.weixin.qq.com/wiki/index.php?title=%E7%AE%A1%E7%90%86%E9%83%A8%E9%97%A8#.E8.8E.B7.E5.8F.96.E9.83.A8.E9.97.A8.E5.88.97.E8.A1.A8">��ȡ�����б�</a>
	 * @return �����б�
	 * @throws WeixinException
	 */
	public List<Party> listParty(int partId) throws WeixinException {
		String department_list_uri = getRequestUri("department_list_uri");
		if (partId > 0) {
			department_list_uri += String.format("&id=%d", partId);
		}
		Token token = tokenHolder.getToken();
		WeixinResponse response = weixinExecutor.get(String.format(
				department_list_uri, token.getAccessToken()));
		return JSON.parseArray(response.getAsJson().getString("department"),
				Party.class);
	}

	/**
	 * ɾ������(����ɾ�������ţ�����ɾ�������Ӳ��š���Ա�Ĳ���)
	 *
	 * @param partId
	 *            ����ID
	 * @see <a
	 *      href="http://qydev.weixin.qq.com/wiki/index.php?title=%E7%AE%A1%E7%90%86%E9%83%A8%E9%97%A8#.E5.88.A0.E9.99.A4.E9.83.A8.E9.97.A8">ɾ������˵��</a>
	 * @return ������
	 * @throws WeixinException
	 */
	public JsonResult deleteParty(int partId) throws WeixinException {
		String department_delete_uri = getRequestUri("department_delete_uri");
		Token token = tokenHolder.getToken();
		WeixinResponse response = weixinExecutor.post(String.format(
				department_delete_uri, token.getAccessToken(), partId));
		return response.getAsJsonResult();
	}
}
