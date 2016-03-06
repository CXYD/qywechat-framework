package com.wk.wechat4j.qy.model;

import java.io.Serializable;

import com.alibaba.fastjson.annotation.JSONCreator;
import com.alibaba.fastjson.annotation.JSONField;

/**
 * ��Ա����Ϣ�����
 *
 * @className ChatMute
 * @author jy
 * @date 2015��8��1��
 * @since JDK 1.6
 * @see
 */
public class ChatMute implements Serializable {

	private static final long serialVersionUID = 6734443056426236273L;

	@JSONField(name = "userid")
	private String userId;
	private int status;

	/**
	 * Ĭ�Ϲر������
	 *
	 * @param userid
	 */
	public ChatMute(String userId) {
		this.userId = userId;
	}

	/**
	 * ����trueʱ���������
	 *
	 * @param userid
	 *            ��Աuserid
	 * @param status
	 *            �Ƿ��������
	 */
	@JSONCreator
	public ChatMute(@JSONField(name = "userId") String userId,
			@JSONField(name = "status") boolean status) {
		this.userId = userId;
		this.status = status ? 1 : 0;
	}

	public String getUserId() {
		return userId;
	}

	public int getStatus() {
		return status;
	}

	@JSONField(serialize = false)
	public boolean getFormatStatus() {
		return status == 1;
	}

	@Override
	public String toString() {
		return "ChatMute [userId=" + userId + ", status=" + status + "]";
	}
}
