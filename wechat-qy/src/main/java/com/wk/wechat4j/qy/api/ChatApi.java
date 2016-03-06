package com.wk.wechat4j.qy.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wk.wechat4j.base.exception.WeixinException;
import com.wk.wechat4j.base.http.weixin.JsonResult;
import com.wk.wechat4j.base.http.weixin.WeixinResponse;
import com.wk.wechat4j.base.model.Token;
import com.wk.wechat4j.base.token.TokenHolder;
import com.wk.wechat4j.base.tuple.ChatTuple;
import com.wk.wechat4j.base.util.ObjectId;
import com.wk.wechat4j.base.util.StringUtil;
import com.wk.wechat4j.qy.message.ChatMessage;
import com.wk.wechat4j.qy.model.ChatInfo;
import com.wk.wechat4j.qy.model.ChatMute;
import com.wk.wechat4j.qy.type.ChatType;

import java.util.List;

/**
 * �Ự����ӿ�
 *
 * @className ChatApi
 * @author jy
 * @date 2015��7��31��
 * @since JDK 1.6
 * @see <a
 *      href="http://qydev.weixin.qq.com/wiki/index.php?title=%E4%BC%81%E4%B8%9A%E5%8F%B7%E6%B6%88%E6%81%AF%E6%9C%8D%E5%8A%A1">��ҵ����Ϣ����</a>
 */
public class ChatApi extends QyApi {
	private final TokenHolder tokenHolder;

	public ChatApi(TokenHolder tokenHolder) {
		this.tokenHolder = tokenHolder;
	}



	/**
	 * �����Ự <font color="red">����ỰidΪ��,������Զ�����һ��ΨһID</font>
	 *
	 * @param chatInfo
	 *            �Ự��Ϣ
	 * @return �ỰID
	 * @see com.wk.wechat4j.qy.model.ChatInfo
	 * @see <a
	 *      href="http://qydev.weixin.qq.com/wiki/index.php?title=%E4%BC%81%E4%B8%9A%E5%8F%B7%E6%B6%88%E6%81%AF%E6%8E%A5%E5%8F%A3%E8%AF%B4%E6%98%8E#.E5.88.9B.E5.BB.BA.E4.BC.9A.E8.AF.9D">�����Ự</a>
	 * @throws WeixinException
	 */
	public String createChat(ChatInfo chatInfo) throws WeixinException {
		String chatId = chatInfo.getChatId();
		JSONObject obj = (JSONObject) JSON.toJSON(chatInfo);
		if (StringUtil.isBlank(chatId)) {
			chatId = ObjectId.get().toHexString();
			obj.put("chatid", chatId);
		}
		String message_chat_create_uri = getRequestUri("message_chat_create_uri");
		Token token = tokenHolder.getToken();
		weixinExecutor.post(
				String.format(message_chat_create_uri, token.getAccessToken()),
				obj.toJSONString());
		return chatId;
	}

	/**
	 * ��ȡ�Ự
	 *
	 * @param chatId
	 *            �ỰID
	 * @return �Ự��Ϣ
	 * @see com.wk.wechat4j.qy.model.ChatInfo
	 * @see <a
	 *      href="http://qydev.weixin.qq.com/wiki/index.php?title=%E4%BC%81%E4%B8%9A%E5%8F%B7%E6%B6%88%E6%81%AF%E6%8E%A5%E5%8F%A3%E8%AF%B4%E6%98%8E#.E8.8E.B7.E5.8F.96.E4.BC.9A.E8.AF.9D">��ȡ�Ự</a>
	 * @throws WeixinException
	 */
	public ChatInfo getChat(String chatId) throws WeixinException {
		String message_chat_get_uri = getRequestUri("message_chat_get_uri");
		Token token = tokenHolder.getToken();
		WeixinResponse response = weixinExecutor.get(String.format(
				message_chat_get_uri, token.getAccessToken(), chatId));
		return response.getAsJson().getObject("chat_info", ChatInfo.class);
	}

	/**
	 * ���»Ự
	 *
	 * @param chatInfo
	 *            �Ự��Ϣ ���ٱ��ֻỰID����Ϊ��
	 * @param operator
	 *            ������userid
	 * @param addUsers
	 *            �Ự������Ա�б�
	 * @param deleteUsers
	 *            �Ự�˳���Ա�б�
	 * @return ������
	 * @see com.wk.wechat4j.qy.model.ChatInfo
	 * @see <a
	 *      href="http://qydev.weixin.qq.com/wiki/index.php?title=%E4%BC%81%E4%B8%9A%E5%8F%B7%E6%B6%88%E6%81%AF%E6%8E%A5%E5%8F%A3%E8%AF%B4%E6%98%8E#.E4.BF.AE.E6.94.B9.E4.BC.9A.E8.AF.9D.E4.BF.A1.E6.81.AF">�޸ĻỰ��Ϣ</a>
	 * @throws WeixinException
	 */
	public JsonResult updateChat(ChatInfo chatInfo, String operator,
			List<String> addUsers, List<String> deleteUsers)
			throws WeixinException {
		JSONObject obj = (JSONObject) JSON.toJSON(chatInfo);
		obj.remove("userlist");
		obj.put("op_user", operator);
		obj.put("add_user_list", addUsers);
		obj.put("del_user_list", deleteUsers);
		String message_chat_update_uri = getRequestUri("message_chat_update_uri");
		Token token = tokenHolder.getToken();
		WeixinResponse response = weixinExecutor.post(
				String.format(message_chat_update_uri, token.getAccessToken()),
				obj.toJSONString());
		return response.getAsJsonResult();
	}

	/**
	 * �˳��Ự
	 *
	 * @param chatId
	 *            �ỰID
	 * @param operator
	 *            ������userid
	 * @return ������
	 * @see <a
	 *      href="http://qydev.weixin.qq.com/wiki/index.php?title=%E4%BC%81%E4%B8%9A%E5%8F%B7%E6%B6%88%E6%81%AF%E6%8E%A5%E5%8F%A3%E8%AF%B4%E6%98%8E#.E9.80.80.E5.87.BA.E4.BC.9A.E8.AF.9D">�˳��Ự</a>
	 * @throws WeixinException
	 */
	public JsonResult quitChat(String chatId, String operator)
			throws WeixinException {
		JSONObject obj = new JSONObject();
		obj.put("chatid", chatId);
		obj.put("op_user", operator);
		String message_chat_quit_uri = getRequestUri("message_chat_quit_uri");
		Token token = tokenHolder.getToken();
		WeixinResponse response = weixinExecutor.post(
				String.format(message_chat_quit_uri, token.getAccessToken()),
				obj.toJSONString());
		return response.getAsJsonResult();
	}

	/**
	 * ����Ựδ��״̬
	 *
	 * @param targetId
	 *            �Ựֵ��Ϊuserid|chatid���ֱ��ʾ����Աid|�Ựid
	 * @param owner
	 *            �Ự�����ߵ�userid
	 * @param chatType
	 *            �Ự���ͣ�single|group���ֱ��ʾ��Ⱥ��|����
	 * @return ������
	 * @see <a
	 *      href="http://qydev.weixin.qq.com/wiki/index.php?title=%E4%BC%81%E4%B8%9A%E5%8F%B7%E6%B6%88%E6%81%AF%E6%8E%A5%E5%8F%A3%E8%AF%B4%E6%98%8E#.E6.B8.85.E9.99.A4.E4.BC.9A.E8.AF.9D.E6.9C.AA.E8.AF.BB.E7.8A.B6.E6.80.81">����Ựδ��״̬</a>
	 * @throws WeixinException
	 */
	public JsonResult clearChatNotify(String targetId, String owner,
			ChatType chatType) throws WeixinException {
		JSONObject chat = new JSONObject();
		chat.put("type", chatType.name());
		chat.put("id", targetId);
		String message_chat_clearnotify_uri = getRequestUri("message_chat_clearnotify_uri");
		Token token = tokenHolder.getToken();
		WeixinResponse response = weixinExecutor.post(
				String.format(message_chat_clearnotify_uri,
						token.getAccessToken()),
				String.format("{\"op_user\": \"%s\",\"chat\":%s", owner,
						chat.toJSONString()));
		return response.getAsJsonResult();
	}

	/**
	 * ���ó�Ա���յ�����Ϣ�Ƿ����ѡ���Ҫ���������ڶԽ���ҵim������״̬�����Ա��������״̬ʱ���������øó�Ա����Ϣ����š�����Ա����ʱ���ر������״̬
	 * ����΢�Ŷ˽������ѡ�
	 *
	 * @param chatMutes
	 *            ���Ѳ���
	 * @see com.wk.wechat4j.qy.model.ChatMute
	 * @see <a href=
	 *      "http://qydev.weixin.qq.com/wiki/index.php?title=%E4%BC%81%E4%B8%9A%E5%8F%B7%E6%B6%88%E6%81%AF%E6%8E%A5%E5%8F%A3%E8%AF%B4%E6%98%8E#.E8.AE.BE.E7.BD.AE.E6.88.90.E5.91.98.E6.96.B0.E6.B6.88.E6.81.AF.E5.85.8D.E6.89.93.E6.89.B0"
	 *      >���ó�Ա����Ϣ�����</a>
	 * @return �б��в����ڵĳ�Ա��ʣ��Ϸ���Ա�����ִ�С�
	 * @throws WeixinException
	 */
	public List<String> setChatMute(List<ChatMute> chatMutes)
			throws WeixinException {
		JSONObject mute = new JSONObject();
		mute.put("user_mute_list", chatMutes);
		String message_chat_setmute_uri = getRequestUri("message_chat_setmute_uri");
		Token token = tokenHolder.getToken();
		WeixinResponse response = weixinExecutor
				.post(String.format(message_chat_setmute_uri,
						token.getAccessToken()), mute.toJSONString());
		return JSON.parseArray(response.getAsJson().getString("invaliduser"),
				String.class);
	}

	/**
	 * ������Ϣ
	 *
	 * @param message
	 *            ��Ϣ����
	 * @return ������
	 * @see com.wk.wechat4j.qy.message.ChatMessage
	 * @see <a
	 *      href="http://qydev.weixin.qq.com/wiki/index.php?title=%E4%BC%81%E4%B8%9A%E5%8F%B7%E6%B6%88%E6%81%AF%E6%8E%A5%E5%8F%A3%E8%AF%B4%E6%98%8E#.E5.8F.91.E6.B6.88.E6.81.AF">������Ϣ</a>
	 * @throws WeixinException
	 */
	public JsonResult sendChatMessage(ChatMessage message)
			throws WeixinException {
		ChatTuple tuple = message.getChatTuple();
		String msgtype = tuple.getMessageType();
		JSONObject msg = new JSONObject();
		JSONObject receiver = new JSONObject();
		receiver.put("id", message.getTargetId());
		receiver.put("type", message.getChatType().name());
		msg.put("receiver", receiver);
		msg.put("sender", message.getSenderId());
		msg.put("msgtype", msgtype);
		msg.put(msgtype, tuple);
		String message_chat_send_uri = getRequestUri("message_chat_send_uri");
		Token token = tokenHolder.getToken();
		WeixinResponse response = weixinExecutor.post(
				String.format(message_chat_send_uri, token.getAccessToken()),
				msg.toJSONString());
		return response.getAsJsonResult();
	}
}
