package com.wk.wechat4j.qy.message;

import com.alibaba.fastjson.annotation.JSONField;
import com.wk.wechat4j.base.tuple.NotifyTuple;

import java.io.Serializable;

/**
 * �ͷ���Ϣ����
 *
 * @className CustomeMessage
 * @author jy
 * @date 2015��11��20��
 * @since JDK 1.6
 * @see com.wk.wechat4j.base.tuple.Text
 * @see com.wk.wechat4j.base.tuple.Image
 * @see com.wk.wechat4j.base.tuple.Voice
 * @see com.wk.wechat4j.base.tuple.Video
 * @see com.wk.wechat4j.base.tuple.File
 * @see <a
 *      href="http://qydev.weixin.qq.com/wiki/index.php?title=%E4%BC%81%E4%B8%9A%E5%AE%A2%E6%9C%8D%E6%8E%A5%E5%8F%A3%E8%AF%B4%E6%98%8E">�ͷ���Ϣ</a>
 */
public class CustomeMessage implements Serializable {

	private static final long serialVersionUID = -3620361273175868681L;

	private CustomeTarget sender;
	private CustomeTarget receiver;
	/**
	 * ��Ϣ����
	 */
	@JSONField(serialize = false)
	private NotifyTuple tuple;

	public CustomeMessage(CustomeTarget sender, CustomeTarget receiver,
			NotifyTuple tuple) {
		this.sender = sender;
		this.receiver = receiver;
		this.tuple = tuple;
	}

	public CustomeTarget getSender() {
		return sender;
	}

	public CustomeTarget getReceiver() {
		return receiver;
	}

	public NotifyTuple getTuple() {
		return tuple;
	}

	/**
	 * �û�����
	 *
	 * @className CustomeIdType
	 * @author jy
	 * @date 2015��11��20��
	 * @since JDK 1.6
	 * @see
	 */
	public enum CustomeIdType {
		/**
		 * �ͷ�
		 */
		kf,
		/**
		 * ��ҵԱ��userid
		 */
		userid,
		/**
		 * ���ں�openid
		 */
		openid
	}

	/**
	 * ��Ϣ����
	 *
	 * @className CustomeTarget
	 * @author jy
	 * @date 2015��11��20��
	 * @since JDK 1.6
	 * @see
	 */
	public static class CustomeTarget implements Serializable {

		private static final long serialVersionUID = 1L;

		private CustomeIdType type;
		private String id;

		public CustomeTarget(CustomeIdType type, String id) {
			this.type = type;
			this.id = id;
		}

		public CustomeIdType getType() {
			return type;
		}

		public String getId() {
			return id;
		}
	}

	@Override
	public String toString() {
		return "CustomeMessage [sender=" + sender + ", receiver=" + receiver
				+ ", tuple=" + tuple + "]";
	}
}
