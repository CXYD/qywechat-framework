package com.wk.wechat4j.qy.message;

import com.alibaba.fastjson.annotation.JSONCreator;
import com.alibaba.fastjson.annotation.JSONField;
import com.wk.wechat4j.base.tuple.ChatTuple;
import com.wk.wechat4j.qy.type.ChatType;

import java.io.Serializable;

/**
 * �Ự��Ϣ����
 *
 * @className ChatMessage
 * @author jy
 * @date 2015��8��1��
 * @since JDK 1.6
 * @see com.wk.wechat4j.base.tuple.Text
 * @see com.wk.wechat4j.base.tuple.Image
 * @see com.wk.wechat4j.base.tuple.File
 */
public class ChatMessage implements Serializable {

	private static final long serialVersionUID = -4973029130270955777L;

	/**
	 * ��Աid|�Ựid
	 */
	private String targetId;
	/**
	 * Ⱥ��|����
	 */
	private ChatType chatType;
	/**
	 * ������id
	 */
	private String senderId;
	/**
	 * ��Ϣ����
	 */
	private ChatTuple chatTuple;

	@JSONCreator
	public ChatMessage(@JSONField(name = "targetId") String targetId,
			@JSONField(name = "chatType") ChatType chatType,
			@JSONField(name = "senderId") String senderId,
			@JSONField(name = "chatTuple") ChatTuple chatTuple) {
		this.targetId = targetId;
		this.chatType = chatType;
		this.senderId = senderId;
		this.chatTuple = chatTuple;
	}

	public String getTargetId() {
		return targetId;
	}

	public ChatType getChatType() {
		return chatType;
	}

	public String getSenderId() {
		return senderId;
	}

	public ChatTuple getChatTuple() {
		return chatTuple;
	}

	@Override
	public String toString() {
		return "ChatMessage [targetId=" + targetId + ", chatType=" + chatType
				+ ", senderId=" + senderId + ", chatTuple=" + chatTuple + "]";
	}
}
