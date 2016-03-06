package com.wk.wechat4j.qy.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wk.wechat4j.base.exception.WeixinException;
import com.wk.wechat4j.base.http.weixin.JsonResult;
import com.wk.wechat4j.base.http.weixin.WeixinResponse;
import com.wk.wechat4j.base.model.Token;
import com.wk.wechat4j.base.token.TokenHolder;
import com.wk.wechat4j.qy.model.Contacts;
import com.wk.wechat4j.qy.model.IdParameter;
import com.wk.wechat4j.qy.model.Tag;
import com.wk.wechat4j.qy.model.User;

import java.util.Arrays;
import java.util.List;

/**
 * ��ǩAPI
 *
 * @className TagApi
 * @author jy
 * @date 2014��11��19��
 * @since JDK 1.6
 * @see <a href=
 *      "http://qydev.weixin.qq.com/wiki/index.php?title=%E7%AE%A1%E7%90%86%E6%A0%87%E7%AD%BE">
 *      �����ǩ</a>
 */
public class TagApi extends QyApi {
	private final TokenHolder tokenHolder;

	public TagApi(TokenHolder tokenHolder) {
		this.tokenHolder = tokenHolder;
	}

	/**
	 * ������ǩ(�����ı�ǩ���ڹ����飻Ĭ��Ϊ����״̬������״̬��ֻ�б�������ſ�����ɾ��Ա������״̬������������Ҳ������ɾ��Ա)
	 *
	 * @param tag
	 *            ��ǩ����</br> ��ǩ���ƣ�����Ϊ1~64���ֽڣ���ǩ��������������ǩ������</br> ��ǩid�����ͣ�
	 *            ָ���˲���ʱ�����ı�ǩ�����ɶ�Ӧ�ı�ǩid����ָ��ʱ����Ŀǰ����id������
	 * @see <a href=
	 *      "http://qydev.weixin.qq.com/wiki/index.php?title=%E7%AE%A1%E7%90%86%E6%A0%87%E7%AD%BE#.E5.88.9B.E5.BB.BA.E6.A0.87.E7.AD.BE">
	 *      ������ǩ˵��</a>
	 * @return ��ǩID
	 * @throws WeixinException
	 */
	public int createTag(Tag tag) throws WeixinException {
		String tag_create_uri = getRequestUri("tag_create_uri");
		Token token = tokenHolder.getToken();
		JSONObject obj = (JSONObject) JSON.toJSON(tag);
		if (obj.getIntValue("tagid") <= 0) {
			obj.remove("tagid");
		}
		WeixinResponse response = weixinExecutor.post(
				String.format(tag_create_uri, token.getAccessToken()),
				obj.toJSONString());
		return response.getAsJson().getIntValue("tagid");
	}

	/**
	 * ���±�ǩ(�����������ָ����ǩ�Ĵ�����)
	 *
	 * @param tag
	 *            ��ǩ��Ϣ
	 * @see <a href=
	 *      "http://qydev.weixin.qq.com/wiki/index.php?title=%E7%AE%A1%E7%90%86%E6%A0%87%E7%AD%BE#.E6.9B.B4.E6.96.B0.E6.A0.87.E7.AD.BE.E5.90.8D.E5.AD.97"
	 *      >���±�ǩ˵��</a>
	 * @return ������
	 * @see com.wk.wechat4j.qy.model.Tag
	 * @throws WeixinException
	 */
	public JsonResult updateTag(Tag tag) throws WeixinException {
		String tag_update_uri = getRequestUri("tag_update_uri");
		Token token = tokenHolder.getToken();
		WeixinResponse response = weixinExecutor.post(
				String.format(tag_update_uri, token.getAccessToken()),
				JSON.toJSONString(tag));
		return response.getAsJsonResult();
	}

	/**
	 * ɾ����ǩ(�����������ָ����ǩ�Ĵ����ߣ����ұ�ǩ�ĳ�Ա�б�Ϊ�ա�)
	 *
	 * @param tagId
	 *            ��ǩID
	 * @return ������
	 * @see <a href=
	 *      "http://qydev.weixin.qq.com/wiki/index.php?title=%E7%AE%A1%E7%90%86%E6%A0%87%E7%AD%BE#.E5.88.A0.E9.99.A4.E6.A0.87.E7.AD.BE">
	 *      ɾ����ǩ˵��</a>
	 * @throws WeixinException
	 */
	public JsonResult deleteTag(int tagId) throws WeixinException {
		String tag_delete_uri = getRequestUri("tag_delete_uri");
		Token token = tokenHolder.getToken();
		WeixinResponse response = weixinExecutor.get(String.format(
				tag_delete_uri, token.getAccessToken(), tagId));
		return response.getAsJsonResult();
	}

	/**
	 * ��ȡ��ǩ�б�
	 *
	 * @see <a href=
	 *      "http://qydev.weixin.qq.com/wiki/index.php?title=%E7%AE%A1%E7%90%86%E6%A0%87%E7%AD%BE#.E8.8E.B7.E5.8F.96.E6.A0.87.E7.AD.BE.E5.88.97.E8.A1.A8">
	 *      ��ȡ��ǩ�б�˵��</a>
	 * @return ��ǩ�б�
	 * @see com.wk.wechat4j.qy.model.Tag
	 * @throws WeixinException
	 */
	public List<Tag> listTag() throws WeixinException {
		String tag_list_uri = getRequestUri("tag_list_uri");
		Token token = tokenHolder.getToken();
		WeixinResponse response = weixinExecutor.get(String.format(
				tag_list_uri, token.getAccessToken()));
		return JSON.parseArray(response.getAsJson().getString("taglist"),
				Tag.class);
	}

	/**
	 * ��ȡ��ǩ��Ա(��������ӵ�С���ȡ��ǩ��Ա���Ľӿ�Ȩ�ޣ������б�������������Ͻ��Χ�ĳ�Ա��)
	 *
	 * @param tagId
	 *            ��ǩID
	 * @see com.wk.wechat4j.qy.model.Contacts
	 * @see com.wk.wechat4j.qy.model.User
	 * @see <a href=
	 *      "http://qydev.weixin.qq.com/wiki/index.php?title=%E7%AE%A1%E7%90%86%E6%A0%87%E7%AD%BE#.E8.8E.B7.E5.8F.96.E6.A0.87.E7.AD.BE.E6.88.90.E5.91.98">
	 *      ��ȡ��ǩ��Ա˵��</a>
	 * @return ��Ա�б�<font color="red">Contacts#getUsers</font>�Ͳ����б�<font
	 *         color="red">Contacts#getPartyIds</font>
	 * @throws WeixinException
	 */
	public Contacts getTagUsers(int tagId) throws WeixinException {
		String tag_get_user_uri = getRequestUri("tag_get_user_uri");
		Token token = tokenHolder.getToken();
		WeixinResponse response = weixinExecutor.get(String.format(
				tag_get_user_uri, token.getAccessToken(), tagId));
		JSONObject obj = response.getAsJson();
		Contacts contacts = new Contacts();
		contacts.setUsers(JSON.parseArray(obj.getString("userlist"), User.class));
		contacts.setPartyIds(JSON.parseArray(obj.getString("partylist"),
				Integer.class));
		contacts.putTagIds(tagId);
		return contacts;
	}

	/**
	 * ������ǩ��Ա(��ǩ�Թ�����ɼ���δ��������Ա���ڹ������Ͻ��Χ��)<br>
	 * <font color="red">������userid��partyid�Ƿ�������text������</font>
	 *
	 * @param tagId
	 *            ��ǩID
	 * @param userIds
	 *            ��ҵ��ԱID�б�ע�⣺userlist��partylist����ͬʱΪ�գ��������󳤶Ȳ�����1000
	 * @param partyIds
	 *            ��ҵ����ID�б�ע�⣺userlist��partylist����ͬʱΪ�գ��������󳤶Ȳ�����100
	 * @see <a href=
	 *      "http://qydev.weixin.qq.com/wiki/index.php?title=%E7%AE%A1%E7%90%86%E6%A0%87%E7%AD%BE#.E5.A2.9E.E5.8A.A0.E6.A0.87.E7.AD.BE.E6.88.90.E5.91.98">
	 *      ������ǩ��Ա˵��</a>
	 * @see com.wk.wechat4j.qy.model.IdParameter
	 * @return ������
	 * @throws WeixinException
	 */
	public IdParameter addTagUsers(int tagId, List<String> userIds,
			List<Integer> partyIds) throws WeixinException {
		String tag_add_user_uri = getRequestUri("tag_add_user_uri");
		return excuteUsers(tag_add_user_uri, tagId, userIds, partyIds);
	}

	/**
	 * ɾ����ǩ��Ա(��ǩ�Թ�����δ��������Ա���ڹ������Ͻ��Χ)<br>
	 * <font color="red">������userid��partyid�Ƿ�������text������</font>
	 *
	 * @param tagId
	 *            ��ǩID
	 * @param userIds
	 *            ��ҵ��ԱID�б�ע�⣺userlist��partylist����ͬʱΪ��
	 * @param partyIds
	 *            ��ҵ����ID�б�ע�⣺userlist��partylist����ͬʱΪ��
	 * @see <a href=
	 *      "http://qydev.weixin.qq.com/wiki/index.php?title=%E7%AE%A1%E7%90%86%E6%A0%87%E7%AD%BE#.E5.88.A0.E9.99.A4.E6.A0.87.E7.AD.BE.E6.88.90.E5.91.98">
	 *      ɾ����ǩ��Ա˵��</a>
	 * @see com.wk.wechat4j.qy.model.IdParameter
	 * @return ������
	 * @throws WeixinException
	 */
	public IdParameter deleteTagUsers(int tagId, List<String> userIds,
			List<Integer> partyIds) throws WeixinException {
		String tag_delete_user_uri = getRequestUri("tag_delete_user_uri");
		return excuteUsers(tag_delete_user_uri, tagId, userIds, partyIds);
	}

	private IdParameter excuteUsers(String uri, int tagId,
			List<String> userIds, List<Integer> partyIds)
			throws WeixinException {
		JSONObject obj = new JSONObject();
		obj.put("tagid", tagId);
		obj.put("userlist", userIds);
		obj.put("partylist", partyIds);
		Token token = tokenHolder.getToken();
		WeixinResponse response = weixinExecutor.post(
				String.format(uri, token.getAccessToken()), obj.toJSONString());
		obj = response.getAsJson();
		IdParameter idParameter = new IdParameter();
		if (obj.containsKey("invalidlist")) {
			idParameter.setUserIds(Arrays.asList(obj.getString("invalidlist")
					.split(IdParameter.SEPARATORS)));
		}
		if (obj.containsKey("partylist")) {
			idParameter.setPartyIds(JSON.parseArray(obj.getString("partylist"),
					Integer.class));
		}
		return idParameter;
	}
}
