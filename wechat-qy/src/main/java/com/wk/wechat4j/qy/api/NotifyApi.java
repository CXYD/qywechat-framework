package com.wk.wechat4j.qy.api;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wk.wechat4j.base.exception.WeixinException;
import com.wk.wechat4j.base.http.weixin.JsonResult;
import com.wk.wechat4j.base.http.weixin.WeixinResponse;
import com.wk.wechat4j.base.model.Token;
import com.wk.wechat4j.qy.message.CustomeMessage;
import com.wk.wechat4j.qy.message.NotifyMessage;
import com.wk.wechat4j.qy.model.IdParameter;
import com.wk.wechat4j.qy.type.KfType;
import com.wk.wechat4j.base.token.TokenHolder;
import com.wk.wechat4j.base.tuple.NotifyTuple;

/**
 * �ͷ���ϢAPI
 *
 * @className NotifyApi
 * @author jy.hu
 * @date 2014��11��21��
 * @since JDK 1.6
 * @see <a
 *      href="http://qydev.weixin.qq.com/wiki/index.php?title=%E5%8F%91%E9%80%81%E6%8E%A5%E5%8F%A3%E8%AF%B4%E6%98%8E">���ͽӿ�˵��</a>
 * @see <a
 *      href="http://qydev.weixin.qq.com/wiki/index.php?title=%E6%B6%88%E6%81%AF%E7%B1%BB%E5%9E%8B%E5%8F%8A%E6%95%B0%E6%8D%AE%E6%A0%BC%E5%BC%8F">���͸�ʽ˵��</a>
 * @see com.wk.wechat4j.base.tuple.Text
 * @see com.wk.wechat4j.base.tuple.Image
 * @see com.wk.wechat4j.base.tuple.Voice
 * @see com.wk.wechat4j.base.tuple.Video
 * @see com.wk.wechat4j.base.tuple.File
 * @see com.wk.wechat4j.base.tuple.News
 * @see com.wk.wechat4j.base.tuple.MpNews
 */
public class NotifyApi extends QyApi {

	private final TokenHolder tokenHolder;

	public NotifyApi(TokenHolder tokenHolder) {
		this.tokenHolder = tokenHolder;
	}

	/**
	 * ������Ϣ(��Ҫ����Ա��Ӧ����ʹ��Ȩ�ޣ����ռ���touser��toparty��totag�в鿴Ȩ�ޣ����򱾴ε���ʧ��)
	 * <p>
	 * 1�� ������Ա�б���ڴ����userid��ִ�з��ͣ���������ע�ⷵ�ؽ��˵��</br>
	 * 2��������Ա����ͨѶ¼Ȩ�޷�Χ�ڣ���ִ�з������񣬷����׸������userid</br>
	 * 3��������Ա����Ӧ�ÿɼ���Χ�ڣ���ִ�з������񣬷����׸������userid</br>
	 * </p>
	 *
	 * @param message
	 *            ��ͨ��Ϣ����
	 * @return �����Ȩ�ޣ��򱾴η���ʧ�ܣ�����ռ��˲����ڻ�δ��ע��������Ȼִ�С���������¾�������Ч�Ĳ���</br> { "errcode":
	 *         0, "errmsg": "ok", "invaliduser": "UserID1",
	 *         "invalidparty":"PartyID1", "invalidtag":"TagID1" }
	 * @throws WeixinException
	 * @see <a
	 *      href="http://qydev.weixin.qq.com/wiki/index.php?title=%E5%8F%91%E9%80%81%E6%8E%A5%E5%8F%A3%E8%AF%B4%E6%98%8E">���ͽӿ�˵��</a>
	 * @see <a
	 *      href="http://qydev.weixin.qq.com/wiki/index.php?title=%E6%B6%88%E6%81%AF%E7%B1%BB%E5%9E%8B%E5%8F%8A%E6%95%B0%E6%8D%AE%E6%A0%BC%E5%BC%8F">���͸�ʽ˵��</a>
	 * @see com.wk.wechat4j.base.tuple.Text
	 * @see com.wk.wechat4j.base.tuple.Image
	 * @see com.wk.wechat4j.base.tuple.Voice
	 * @see com.wk.wechat4j.base.tuple.Video
	 * @see com.wk.wechat4j.base.tuple.File
	 * @see com.wk.wechat4j.base.tuple.News
	 * @see com.wk.wechat4j.base.tuple.MpNews
	 * @see com.wk.wechat4j.qy.message.NotifyMessage
	 * @see com.wk.wechat4j.qy.model.IdParameter
	 */
	public IdParameter sendNotifyMessage(NotifyMessage message)
			throws WeixinException {
		NotifyTuple tuple = message.getTuple();
		Map<String, String> target = message.getTarget().getParameter();
		String msgtype = tuple.getMessageType();
		JSONObject obj = (JSONObject) JSON.toJSON(message);
		obj.put("msgtype", msgtype);
		obj.put(msgtype, tuple);
		if (target == null || target.isEmpty()) {
			obj.put("touser", "@all");
		} else {
			obj.putAll(target);
		}
		String message_send_uri = getRequestUri("message_send_uri");
		Token token = tokenHolder.getToken();
		WeixinResponse response = weixinExecutor.post(
				String.format(message_send_uri, token.getAccessToken()),
				obj.toJSONString());
		obj = response.getAsJson();
		IdParameter idParameter = new IdParameter();
		if (obj.containsKey("invaliduser")) {
			idParameter.setUserIds(Arrays.asList(obj.getString("invalidlist")
					.split(IdParameter.SEPARATORS)));
		}
		if (obj.containsKey("invalidparty")) {
			List<Integer> partyIds = new ArrayList<Integer>();
			for (String id : obj.getString("invalidlist").split(
					IdParameter.SEPARATORS)) {
				partyIds.add(Integer.parseInt(id));
			}
			idParameter.setPartyIds(partyIds);
		}
		if (obj.containsKey("invalidtag")) {
			List<Integer> tagIds = new ArrayList<Integer>();
			for (String id : obj.getString("invalidtag").split(
					IdParameter.SEPARATORS)) {
				tagIds.add(Integer.parseInt(id));
			}
			idParameter.setPartyIds(tagIds);
		}
		return idParameter;
	}

	/**
	 * ���Ϳͷ���Ϣ
	 *
	 * @param message
	 *            �ͷ���Ϣ����
	 * @return ���ͽ��
	 * @see <a
	 *      href="http://qydev.weixin.qq.com/wiki/index.php?title=%E4%BC%81%E4%B8%9A%E5%AE%A2%E6%9C%8D%E6%8E%A5%E5%8F%A3%E8%AF%B4%E6%98%8E">�ͷ��ӿ�˵��</a>
	 * @see com.wk.wechat4j.base.tuple.Text
	 * @see com.wk.wechat4j.base.tuple.Image
	 * @see com.wk.wechat4j.base.tuple.Voice
	 * @see com.wk.wechat4j.base.tuple.Video
	 * @see com.wk.wechat4j.base.tuple.File
	 * @see com.wk.wechat4j.qy.message.CustomeMessage
	 * @throws WeixinException
	 */
	public JsonResult sendCustomeMessage(CustomeMessage message)
			throws WeixinException {
		NotifyTuple tuple = message.getTuple();
		String msgtype = tuple.getMessageType();
		JSONObject obj = (JSONObject) JSON.toJSON(message);
		obj.put("msgtype", msgtype);
		obj.put(msgtype, tuple);
		String message_kf_send_uri = getRequestUri("message_kf_send_uri");
		Token token = tokenHolder.getToken();
		WeixinResponse response = weixinExecutor.post(
				String.format(message_kf_send_uri, token.getAccessToken()),
				obj.toJSONString());
		return response.getAsJsonResult();
	}

	/**
	 * ��ȡ�ͷ��б�
	 *
	 * @param kfType
	 *            �ͷ����� Ϊ��ʱ����ȫ�����͵Ŀͷ�
	 * @return ��һ��Ԫ��Ϊ�ڲ��ͷ�(internal),�ڶ�������Ϊ�ⲿ�ͷ�(external)
	 * @see com.wk.wechat4j.qy.model.IdParameter
	 * @see <a
	 *      href="http://qydev.weixin.qq.com/wiki/index.php?title=%E4%BC%81%E4%B8%9A%E5%AE%A2%E6%9C%8D%E6%8E%A5%E5%8F%A3%E8%AF%B4%E6%98%8E">�ͷ��б�</a>
	 * @throws WeixinException
	 */
	public IdParameter[] getKfList(KfType kfType) throws WeixinException {
		String message_kf_list_uri = getRequestUri("message_kf_list_uri");
		if (kfType != null) {
			message_kf_list_uri += "&type=" + kfType.name();
		}
		Token token = tokenHolder.getToken();
		WeixinResponse response = weixinExecutor.get(String.format(
				message_kf_list_uri, token.getAccessToken()));
		JSONObject obj = response.getAsJson();
		return new IdParameter[] {
				obj.containsKey("internal") ? obj.getObject("internal",
						IdParameter.class) : null,
				obj.containsKey("external") ? obj.getObject("external",
						IdParameter.class) : null };
	}
}
