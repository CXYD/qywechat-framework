package com.wk.wechat4j.qy.model;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * ����Ự��Ϣ
 *
 * @className ChatInfo
 * @author jy
 * @date 2015��7��31��
 * @since JDK 1.6
 * @see
 */
public class ChatInfo implements Serializable {

	private static final long serialVersionUID = 1899784347096501375L;
	/**
	 * �Ựid
	 */
	@JSONField(name = "chatid")
	private String chatId;
	/**
	 * �Ự����
	 */
	private String name;
	/**
	 * ����Աuserid
	 */
	private String owner;
	/**
	 * �Ự��Ա�б�
	 */
	@JSONField(name = "userlist")
	private List<String> members;

	protected ChatInfo() {

	}

	public ChatInfo(String chatId) {
		this.chatId = chatId;
	}

	public ChatInfo(String name, String owner, String... members) {
		this.name = name;
		this.owner = owner;
		this.members = Arrays.asList(members);
	}

	public String getChatId() {
		return chatId;
	}

	public String getName() {
		return name;
	}

	public String getOwner() {
		return owner;
	}

	public List<String> getMembers() {
		return members;
	}

	public void setMembers(List<String> members) {
		this.members = members;
	}

	public void setMembers(String... members) {
		this.members = Arrays.asList(members);
	}

	// ---------- setter Ӧ��ȫ��ȥ��

	public void setChatId(String chatId) {
		this.chatId = chatId;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	@Override
	public String toString() {
		return "ChatInfo [chatId=" + chatId + ", name=" + name + ", owner="
				+ owner + ", members=" + members + "]";
	}
}
