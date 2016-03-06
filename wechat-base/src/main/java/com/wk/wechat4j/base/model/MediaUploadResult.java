package com.wk.wechat4j.base.model;

import com.alibaba.fastjson.annotation.JSONCreator;
import com.alibaba.fastjson.annotation.JSONField;
import com.wk.wechat4j.base.type.MediaType;

import java.io.Serializable;
import java.util.Date;

/**
 * 媒体文件上传结果
 *
 * @className MediaUploadResult
 * @author jy
 * @date 2015年7月25日
 * @since JDK 1.6
 * @see
 */
public class MediaUploadResult implements Serializable {

	private static final long serialVersionUID = -620630472640999536L;
	private String mediaId;
	private MediaType mediaType;
	private Date createdAt;

	@JSONCreator
	public MediaUploadResult(@JSONField(name = "media_id") String mediaId,
			@JSONField(name = "type") MediaType mediaType,
			@JSONField(name = "created_at") Date createdAt) {
		this.mediaId = mediaId;
		this.mediaType = mediaType;
		this.createdAt = createdAt;
	}

	public String getMediaId() {
		return mediaId;
	}

	public MediaType getMediaType() {
		return mediaType;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	@Override
	public String toString() {
		return "MediaUploadResult [mediaId=" + mediaId + ", mediaType=" + mediaType
				+ ", createdAt=" + createdAt + "]";
	}
}
