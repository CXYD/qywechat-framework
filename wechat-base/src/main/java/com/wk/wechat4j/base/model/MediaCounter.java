package com.wk.wechat4j.base.model;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;

/**
 * ý���ز�����
 *
 * @className MediaCounter
 * @author jy
 * @date 2015��3��22��
 * @since JDK 1.6
 * @see
 */
public class MediaCounter implements Serializable {

	private static final long serialVersionUID = -1752502821323552783L;

	/**
	 * Ӧ���ز�����Ŀ(��ҵ�Ŷ���)
	 */
	@JSONField(name = "total_count")
	private int totalCount;
	/**
	 * �ļ��ز�����Ŀ(��ҵ�Ŷ���)
	 */
	@JSONField(name = "file_count")
	private int fileCount;
	/**
	 * ����������
	 */
	@JSONField(name = "voice_count")
	private long voiceCount;
	/**
	 * ��Ƶ������
	 */
	@JSONField(name = "video_count")
	private long videoCount;
	/**
	 * ͼƬ������
	 */
	@JSONField(name = "image_count")
	private long imageCount;
	/**
	 * ͼ��������
	 */
	@JSONField(name = "news_count")
	private long newsCount;

	public long getVoiceCount() {
		return voiceCount;
	}

	public void setVoiceCount(long voiceCount) {
		this.voiceCount = voiceCount;
	}

	public long getVideoCount() {
		return videoCount;
	}

	public void setVideoCount(long videoCount) {
		this.videoCount = videoCount;
	}

	public long getImageCount() {
		return imageCount;
	}

	public void setImageCount(long imageCount) {
		this.imageCount = imageCount;
	}

	public long getNewsCount() {
		return newsCount;
	}

	public void setNewsCount(long newsCount) {
		this.newsCount = newsCount;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getFileCount() {
		return fileCount;
	}

	public void setFileCount(int fileCount) {
		this.fileCount = fileCount;
	}

	@Override
	public String toString() {
		return "MediaCounter [totalCount=" + totalCount + ", fileCount="
				+ fileCount + ", voiceCount=" + voiceCount + ", videoCount="
				+ videoCount + ", imageCount=" + imageCount + ", newsCount="
				+ newsCount + "]";
	}
}
