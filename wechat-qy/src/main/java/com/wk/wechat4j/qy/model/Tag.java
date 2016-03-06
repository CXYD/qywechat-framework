package com.wk.wechat4j.qy.model;

import java.io.Serializable;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * ��ǩ����
 *
 * @className Tag
 * @author jy
 * @date 2014��11��24��
 * @since JDK 1.6
 * @see
 */
public class Tag implements Serializable {

	private static final long serialVersionUID = 5204620476267654921L;

	/**
	 * ��ǩID
	 */
	@JSONField(name = "tagid")
	private int id;
	/**
	 * ��ǩ����
	 */
	@JSONField(name = "tagname")
	private String name;

	protected Tag() {

	}

	public Tag(String name) {
		this.name = name;
	}

	public Tag(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	// ---------- setter Ӧ��ȫ��ȥ��

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Tag [id=" + id + ", name=" + name + "]";
	}
}
