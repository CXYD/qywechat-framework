package com.wk.wechat4j.base.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.util.TypeUtils;
import com.wk.wechat4j.base.tuple.MpArticle;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * ý���ز���Ϣ
 *
 * @className MediaItem
 * @author jy
 * @date 2015��3��22��
 * @since JDK 1.6
 * @see
 */
public class MediaItem implements Serializable {

	private static final long serialVersionUID = -2923028664954250134L;

	/**
	 * ý���ز�ID
	 */
	@JSONField(name = "media_id")
	private String mediaId;
	/**
	 * ý���ز�����
	 */
	private String name;
	/**
	 * ý���ز�������ʱ��
	 */
	@JSONField(name = "update_time")
	private String updateTime;
	/**
	 * ͼ���ز��б�
	 */
	@JSONField(name = "articles")
	private List<MpArticle> articles;

	public String getMediaId() {
		return mediaId;
	}

	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	@JSONField(serialize = false)
	public Date getForamtUpdateTime() {
		return updateTime != null ? TypeUtils.castToDate(updateTime) : null;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public List<MpArticle> getArticles() {
		return articles;
	}

	public void setArticles(List<MpArticle> articles) {
		this.articles = articles;
	}

	@Override
	public String toString() {
		return "MediaItem [mediaId=" + mediaId + ", name=" + name
				+ ", updateTime=" + updateTime + ", articles=" + articles + "]";
	}
}
