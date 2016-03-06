package com.wk.wechat4j.base.model;

import com.wk.wechat4j.base.http.ContentType;

import java.io.Serializable;


/**
 * ý���ļ����ؽ��
 *
 * @className MediaDownloadResult
 * @author jy
 * @date 2015��7��25��
 * @since JDK 1.6
 * @see
 */
public class MediaDownloadResult implements Serializable {

	private static final long serialVersionUID = -7090523911701729058L;
	/**
	 * ����
	 */
	private byte[] content;
	/**
	 * ����
	 */
	private ContentType contentType;
	/**
	 * �ļ���
	 */
	private String fileName;

	public byte[] getContent() {
		return content;
	}

	public ContentType getContentType() {
		return contentType;
	}

	public String getFileName() {
		return fileName;
	}

	public MediaDownloadResult(byte[] content, ContentType contentType,
			String fileName) {
		this.content = content;
		this.contentType = contentType;
		this.fileName = fileName;
	}

	@Override
	public String toString() {
		return "MediaDownloadResult [content=..., contentType=" + contentType
				+ ", fileName=" + fileName + "]";
	}
}
