package com.wk.wechat4j.qy.model;

import java.io.Serializable;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * ���Ŷ���
 *
 * @className Party
 * @author jy
 * @date 2014��11��18��
 * @since JDK 1.6
 * @see <a
 *      href="http://qydev.weixin.qq.com/wiki/index.php?title=%E7%AE%A1%E7%90%86%E9%83%A8%E9%97%A8">������˵��</a>
 */
public class Party implements Serializable {

	private static final long serialVersionUID = -2567893218591084288L;
	/**
	 * ����ID,ָ��ʱ�������1,��ָ��ʱ���Զ�����.
	 */
	private int id;
	/**
	 * �������ơ���������Ϊ1~64���ַ�
	 */
	private String name;
	/**
	 * ���ײ���id��������idΪ1
	 */
	@JSONField(name = "parentid")
	private int parentId;
	/**
	 * �ڸ������еĴ��򡣴�1��ʼ������Խ������Խ����
	 */
	private int order;

	protected Party() {

	}

	public Party(String name) {
		this.name = name;
	}

	public Party(int id, String name, int parentId) {
		this.id = id;
		this.name = name;
		this.parentId = parentId;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public int getParentId() {
		return parentId;
	}

	public int getOrder() {
		return order;
	}

	// ---------- setter Ӧ��ȫ��ȥ��

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	@Override
	public String toString() {
		return "Party [id=" + id + ", name=" + name + ", parentId=" + parentId
				+ ", order=" + order + "]";
	}
}
