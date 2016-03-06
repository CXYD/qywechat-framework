package com.wk.wechat4j.base.tuple;

import com.alibaba.fastjson.annotation.JSONCreator;
import com.alibaba.fastjson.annotation.JSONField;

import javax.xml.bind.annotation.XmlElement;

/**
 * ͼƬ����
 * <p>
 * <font color="red">�����ڡ��ͷ���Ϣ����Ⱥ����Ϣ������ҵ�ŵġ�������Ϣ��</font>
 * </p>
 *
 * @className Image
 * @author jy
 * @date 2014��9��29��
 * @since JDK 1.6
 * @see
 */
public class Image implements MassTuple, NotifyTuple, ChatTuple {

	private static final long serialVersionUID = 6928681900960656161L;

	@Override
	public String getMessageType() {
		return "image";
	}

	/**
	 * �ϴ����΢�ŷ��ص�ý��ID
	 */
	@JSONField(name = "media_id")
	@XmlElement(name = "MediaId")
	private String mediaId;

	@JSONCreator
	public Image(@JSONField(name = "mediaId") String mediaId) {
		this.mediaId = mediaId;
	}

	public String getMediaId() {
		return mediaId;
	}

	@Override
	public String toString() {
		return "Image [mediaId=" + mediaId + "]";
	}
}
