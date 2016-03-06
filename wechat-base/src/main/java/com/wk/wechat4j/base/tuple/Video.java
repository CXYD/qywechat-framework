package com.wk.wechat4j.base.tuple;

import com.alibaba.fastjson.annotation.JSONCreator;
import com.alibaba.fastjson.annotation.JSONField;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * ��Ƶ����
 * <p>
 * <font color="red">�����ڡ��ͷ���Ϣ��</font>
 * </p>
 *
 * @className Video
 * @author jy
 * @date 2014��9��29��
 * @since JDK 1.6
 * @see
 */
public class Video implements NotifyTuple {

	private static final long serialVersionUID = 2167437425244069128L;

	@Override
	public String getMessageType() {
		return "video";
	}

	/**
	 * �ϴ���Ƶ΢�ŷ��ص�ý��ID
	 */
	@JSONField(name = "media_id")
	@XmlElement(name = "MediaId")
	private String mediaId;
	/**
	 * ����ͼ��ý��ID(�ͷ���Ϣ)
	 */
	@JSONField(name = "thumb_media_id")
	@XmlTransient
	private String thumbMediaId;
	/**
	 * ��Ƶ����
	 */
	@XmlElement(name = "Title")
	private String title;
	/**
	 * ��Ƶ����
	 */
	@JSONField(name = "description")
	@XmlElement(name = "Description")
	private String desc;

	/**
	 * ��ҵ�ŵ���Ƶ��Ϣ����Ҫ����ͼ
	 *
	 * @param mediaId
	 *            ��Ƶý���ļ�id�����Ե����ϴ���ʱ�زĻ��������زĽӿڻ�ȡ
	 * @param title
	 *            ��Ƶ����
	 * @param desc
	 *            ��Ƶ����
	 */
	public Video(String mediaId, String title, String desc) {
		this(mediaId, null, title, desc);
	}

	@JSONCreator
	public Video(@JSONField(name = "mediaId") String mediaId,
			@JSONField(name = "thumbMediaId") String thumbMediaId,
			@JSONField(name = "title") String title,
			@JSONField(name = "desc") String desc) {
		this.mediaId = mediaId;
		this.thumbMediaId = thumbMediaId;
		this.title = title;
		this.desc = desc;
	}

	public String getMediaId() {
		return mediaId;
	}

	public String getThumbMediaId() {
		return thumbMediaId;
	}

	public String getTitle() {
		return title;
	}

	public String getDesc() {
		return desc;
	}

	@Override
	public String toString() {
		return "Video [thumbMediaId=" + thumbMediaId + ", title=" + title
				+ ", desc=" + desc + ", mediaId=" + mediaId + "]";
	}
}
