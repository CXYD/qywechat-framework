package com.wk.wechat4j.base.tuple;

import com.alibaba.fastjson.annotation.JSONCreator;
import com.alibaba.fastjson.annotation.JSONField;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import java.util.LinkedList;
import java.util.List;

/**
 * ͼ�Ķ���(mpnews��Ϣ��news��Ϣ���ƣ���ͬ����ͼ����Ϣ���ݴ洢��΢�ź�̨������֧�ֱ���ѡ�ÿ��Ӧ��ÿ�������Է���100��)
 * <p>
 * <font color="red">�����ڡ�Ⱥ����Ϣ(����mediaId��articles�����ٱ���һ����ֵ)������ҵ�ŵĿͷ���Ϣ��</font>
 * </p>
 *
 * @className MpNews
 * @author jy
 * @date 2014��9��29��
 * @since JDK 1.6
 * @see
 */
public class MpNews implements MassTuple, NotifyTuple {

	private static final long serialVersionUID = 8853054484809101524L;

	/**
	 * ��������ͼ���б���
	 */
	private static final int MAX_ARTICLE_COUNT = 10;

	@Override
	public String getMessageType() {
		return "mpnews";
	}

	/**
	 * �ϴ�ͼ���б��΢�ŷ��ص�ý��ID
	 */
	@JSONField(name = "media_id")
	@XmlElement(name = "MediaId")
	private String mediaId;
	/**
	 * ͼ���б�
	 */
	@XmlTransient
	private LinkedList<MpArticle> articles;

	public MpNews() {
		this(null);
	}

	@JSONCreator
	public MpNews(@JSONField(name = "mediaId") String mediaId) {
		this.mediaId = mediaId;
		this.articles = new LinkedList<MpArticle>();
	}

	public MpNews addArticle(String thumbMediaId, String title, String content) {
		return addArticle(new MpArticle(thumbMediaId, title, content));
	}

	public MpNews addArticle(MpArticle... articles) {
		for (MpArticle article : articles) {
			this.articles.add(article);
		}
		return this;
	}

	public MpNews addFirstArticle(MpArticle article) {
		articles.addFirst(article);
		return this;
	}

	public MpNews addLastArticle(MpArticle article) {
		articles.addLast(article);
		return this;
	}

	public MpNews removeFirstArticle() {
		articles.removeFirst();
		return this;
	}

	public MpNews removeLastArticle() {
		articles.removeLast();
		return this;
	}

	@JSONField(serialize = false)
	@XmlTransient
	public boolean isMaxCount() {
		return articles.size() == MAX_ARTICLE_COUNT;
	}

	public List<MpArticle> getArticles() {
		if (articles.size() > MAX_ARTICLE_COUNT) {
			return articles.subList(0, MAX_ARTICLE_COUNT);
		} else {
			return articles;
		}
	}

	@JSONField(serialize = false)
	@XmlTransient
	public List<MpArticle> getFullArticles() {
		return articles;
	}

	public String getMediaId() {
		return mediaId;
	}

	@Override
	public String toString() {
		return "MpNews [articles=" + articles + ", mediaId=" + mediaId + "]";
	}
}
