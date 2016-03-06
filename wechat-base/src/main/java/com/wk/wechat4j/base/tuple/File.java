package com.wk.wechat4j.base.tuple;

import com.alibaba.fastjson.annotation.JSONCreator;
import com.alibaba.fastjson.annotation.JSONField;

import javax.xml.bind.annotation.XmlElement;

/**
 * �ļ�����
 * <p>
 * <font color="red">��������ҵ�ŵġ��ͷ���Ϣ������������Ϣ��</font>
 * </p>
 *
 * @className File
 * @author jy
 * @date 2014��11��21��
 * @since JDK 1.6
 * @see
 */
public class File implements NotifyTuple, ChatTuple {

	private static final long serialVersionUID = -8149837316289636110L;

	@Override
	public String getMessageType() {
		return "file";
	}

	/**
	 * �ϴ����΢�ŷ��ص�ý��ID
	 */
	@JSONField(name = "media_id")
	@XmlElement(name = "MediaId")
	private String mediaId;

	@JSONCreator
	public File(@JSONField(name = "mediaId") String mediaId) {
		this.mediaId = mediaId;
	}

	public String getMediaId() {
		return mediaId;
	}

	@Override
	public String toString() {
		return "File [mediaId=" + mediaId + "]";
	}
}
