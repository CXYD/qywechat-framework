package com.wk.wechat4j.base.tuple;

import com.alibaba.fastjson.annotation.JSONCreator;
import com.alibaba.fastjson.annotation.JSONField;

import javax.xml.bind.annotation.XmlElement;
import java.io.Serializable;

/**
 * �ͷ���Ϣͼ��
 *
 * @className Article
 * @author jy
 * @date 2014��9��29��
 * @since JDK 1.6
 * @see
 */
public class Article implements Serializable {

	private static final long serialVersionUID = -1231352700301456395L;

	/**
	 * ͼ����Ϣ����
	 */
	@XmlElement(name = "Title")
	private String title;
	/**
	 * ͼ����Ϣ����
	 */
	@JSONField(name = "description")
	@XmlElement(name = "Description")
	private String desc;
	/**
	 * ͼƬ���ӣ�֧��JPG��PNG��ʽ���Ϻõ�Ч��Ϊ��ͼ360*200��Сͼ200*200
	 */
	@JSONField(name = "picurl")
	@XmlElement(name = "PicUrl")
	private String picUrl;
	/**
	 * ���ͼ����Ϣ��ת����
	 */
	@XmlElement(name = "Url")
	private String url;

	@JSONCreator
	public Article(@JSONField(name = "title") String title,
			@JSONField(name = "desc") String desc,
			@JSONField(name = "picUrl") String picUrl,
			@JSONField(name = "url") String url) {
		this.title = title;
		this.desc = desc;
		this.picUrl = picUrl;
		this.url = url;
	}

	public String getTitle() {
		return title;
	}

	public String getDesc() {
		return desc;
	}

	public String getPicUrl() {
		return picUrl;
	}

	public String getUrl() {
		return url;
	}

	@Override
	public String toString() {
		return "Article [title=" + title + ", desc=" + desc + ", picUrl="
				+ picUrl + ", url=" + url + "]";
	}
}