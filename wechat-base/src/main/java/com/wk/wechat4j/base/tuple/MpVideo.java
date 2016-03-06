package com.wk.wechat4j.base.tuple;

import com.alibaba.fastjson.annotation.JSONCreator;
import com.alibaba.fastjson.annotation.JSONField;

import javax.xml.bind.annotation.XmlElement;

/**
 * Ⱥ����Ƶ����
 * <p>
 * <font color="red">�����ڡ�Ⱥ����Ϣ��</font>
 * </p>
 *
 * @className MpVideo
 * @author jy
 * @date 2014��9��29��
 * @since JDK 1.6
 * @see
 */
public class MpVideo implements MassTuple {

	private static final long serialVersionUID = 2167437425244069128L;

	@Override
	public String getMessageType() {
		return "mpvideo";
	}

	/**
	 * �ϴ���Ƶ��΢�ŷ��ص�ý��ID
	 */
	@JSONField(name = "media_id")
	@XmlElement(name = "MediaId")
	private String mediaId;

	@JSONCreator
	public MpVideo(@JSONField(name = "mediaId") String mediaId) {
		this.mediaId = mediaId;
	}

	public String getMediaId() {
		return mediaId;
	}

	@Override
	public String toString() {
		return "MpVideo [mediaId=" + mediaId + "]";
	}
}
