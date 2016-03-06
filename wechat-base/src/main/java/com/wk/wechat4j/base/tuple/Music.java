package com.wk.wechat4j.base.tuple;

import com.alibaba.fastjson.annotation.JSONField;

import javax.xml.bind.annotation.XmlElement;

/**
 * ���ֶ���
 * <p>
 * <font color="red">�����ڡ��ͷ���Ϣ��</font>
 * </p>
 *
 * @className Music
 * @author jy
 * @date 2014��9��29��
 * @since JDK 1.6
 * @see
 */
public class Music implements NotifyTuple {

	private static final long serialVersionUID = -5952134916367253297L;

	@Override
	public String getMessageType() {
		return "music";
	}

	/**
	 * ���ֱ���
	 */
	@XmlElement(name = "Title")
	private String title;
	/**
	 * ��������
	 */
	@JSONField(name = "description")
	@XmlElement(name = "Description")
	private String desc;
	/**
	 * ��������
	 */
	@JSONField(name = "musicurl")
	@XmlElement(name = "MusicUrl")
	private String musicUrl;
	/**
	 * �������������ӣ�WIFI��������ʹ�ø����Ӳ�������
	 */
	@JSONField(name = "hqmusicurl")
	@XmlElement(name = "HQMusicUrl")
	private String hqMusicUrl;
	/**
	 * ����ͼ��ý��id��ͨ���ϴ���ý���ļ����õ���id
	 */
	@JSONField(name = "thumb_media_id")
	@XmlElement(name = "ThumbMediaId")
	private String thumbMediaId;

	public Music(String musicUrl, String hqMusicUrl, String thumbMediaId) {
		this(null, null, musicUrl, hqMusicUrl, thumbMediaId);
	}

	public Music(@JSONField(name = "title") String title,
			@JSONField(name = "desc") String desc,
			@JSONField(name = "musicUrl") String musicUrl,
			@JSONField(name = "hqMusicUrl") String hqMusicUrl,
			@JSONField(name = "thumbMediaId") String thumbMediaId) {
		this.title = title;
		this.desc = desc;
		this.musicUrl = musicUrl;
		this.hqMusicUrl = hqMusicUrl;
		this.thumbMediaId = thumbMediaId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getMusicUrl() {
		return musicUrl;
	}

	public String getHqMusicUrl() {
		return hqMusicUrl;
	}

	public String getThumbMediaId() {
		return thumbMediaId;
	}

	@Override
	public String toString() {
		return "Music [title=" + title + ", desc=" + desc + ", musicUrl="
				+ musicUrl + ", hqMusicUrl=" + hqMusicUrl + ", thumbMediaId="
				+ thumbMediaId + "]";
	}
}
