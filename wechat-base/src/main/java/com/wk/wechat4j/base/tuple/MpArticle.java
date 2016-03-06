package com.wk.wechat4j.base.tuple;

import com.alibaba.fastjson.annotation.JSONCreator;
import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;

/**
 * Ⱥ����Ϣͼ��(��Ϣ���ݴ洢��΢�ź�̨)
 *
 * @author jy.hu
 * @date 2014��4��26��
 * @since JDK 1.6
 */
public class MpArticle implements Serializable {

	private static final long serialVersionUID = 5583479943661639234L;

	/**
	 * ͼ����Ϣ����ͼ��media_id�������ڻ���֧��-�ϴ���ý���ļ��ӿ��л�� �ǿ�
	 */
	@JSONField(name = "thumb_media_id")
	private String thumbMediaId;
	/**
	 * ͼ����Ϣ�ķ���ͼƬ�ĵ�ַ��������������Ҳ����ʹ�����URL����ͼƬ���Լ��������У�Ȼ����ʾ���Լ���վ��
	 */
	@JSONField(name = "thumb_url")
	private String thumbUrl;
	/**
	 * ͼ����Ϣ������ ��Ϊ��
	 */
	private String author;
	/**
	 * ͼ����Ϣ�ı��� �ǿ�
	 */
	private String title;
	/**
	 * ��ͼ����Ϣҳ�������Ķ�ԭ�ġ����ҳ�� ��Ϊ��
	 */
	@JSONField(name = "content_source_url")
	private String sourceUrl;
	/**
	 * ͼ����Ϣҳ������ݣ�֧��HTML��ǩ �ǿ�
	 */
	private String content;
	/**
	 * ͼ����Ϣ������ ��Ϊ��
	 */
	private String digest;
	/**
	 * �Ƿ���ʾ���棬1Ϊ��ʾ��0Ϊ����ʾ ��Ϊ��
	 */
	@JSONField(name = "show_cover_pic")
	private String showCoverPic;
	/**
	 * ���ĵ�URL ��Ϊ��
	 */
	@JSONField(name = "content_url")
	private String contentUrl;
	/**
	 * ����ͼƬ��URL ��Ϊ��
	 */
	@JSONField(name = "cover_url")
	private String coverUrl;

	public MpArticle(String thumbMediaId, String title, String content) {
		this.thumbMediaId = thumbMediaId;
		this.title = title;
		this.content = content;
	}

	@JSONCreator
	public MpArticle(@JSONField(name = "thumbMediaId") String thumbMediaId,
			@JSONField(name = "thumbUrl") String thumbUrl,
			@JSONField(name = "author") String author,
			@JSONField(name = "title") String title,
			@JSONField(name = "sourceUrl") String sourceUrl,
			@JSONField(name = "content") String content,
			@JSONField(name = "digest") String digest,
			@JSONField(name = "showCoverPic") String showCoverPic,
			@JSONField(name = "contentUrl") String contentUrl,
			@JSONField(name = "coverUrl") String coverUrl) {
		this.thumbMediaId = thumbMediaId;
		this.thumbUrl = thumbUrl;
		this.author = author;
		this.title = title;
		this.sourceUrl = sourceUrl;
		this.content = content;
		this.digest = digest;
		this.showCoverPic = showCoverPic;
		this.contentUrl = contentUrl;
		this.coverUrl = coverUrl;
	}

	public String getThumbMediaId() {
		return thumbMediaId;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getTitle() {
		return title;
	}

	public String getSourceUrl() {
		return sourceUrl;
	}

	public void setSourceUrl(String sourceUrl) {
		this.sourceUrl = sourceUrl;
	}

	public String getContent() {
		return content;
	}

	public String getDigest() {
		return digest;
	}

	public void setDigest(String digest) {
		this.digest = digest;
	}

	public String getShowCoverPic() {
		return showCoverPic;
	}

	public void setShowCoverPic(boolean showCoverPic) {
		this.showCoverPic = showCoverPic ? "1" : "0";
	}

	public String getContentUrl() {
		return contentUrl;
	}

	public void setContentUrl(String contentUrl) {
		this.contentUrl = contentUrl;
	}

	public String getCoverUrl() {
		return coverUrl;
	}

	public void setCoverUrl(String coverUrl) {
		this.coverUrl = coverUrl;
	}

	public String getThumbUrl() {
		return thumbUrl;
	}

	@Override
	public String toString() {
		return "MpArticle [thumbMediaId=" + thumbMediaId + ",thumbUrl="
				+ thumbUrl + ", author=" + author + ", title=" + title
				+ ", sourceUrl=" + sourceUrl + ", content=" + content
				+ ", digest=" + digest + ", showCoverPic=" + showCoverPic
				+ ", contentUrl=" + contentUrl + ", coverUrl=" + coverUrl + "]";
	}
}
