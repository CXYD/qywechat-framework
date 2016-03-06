package com.wk.wechat4j.qy.model;

import java.io.Serializable;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * ��ҵ��Ӧ�õĸſ���Ϣ
 *
 * @className AgentOverview
 * @author jy
 * @date 2015��4��9��
 * @since JDK 1.6
 * @see
 */
public class AgentOverview implements Serializable {

	private static final long serialVersionUID = 5459274811502476460L;
	/**
	 * ��ҵӦ�õ�id
	 */
	@JSONField(name = "agentid")
	private int agentId;
	/**
	 * ��ҵӦ������
	 */
	private String name;
	/**
	 * ��ҵӦ�÷���ͷ��
	 */
	@JSONField(name = "square_logo_url")
	private String squareLogoUrl;
	/**
	 * ��ҵӦ��Բ��ͷ��
	 */
	@JSONField(name = "round_logo_url")
	private String roundLogoUrl;

	public int getAgentId() {
		return agentId;
	}

	public String getName() {
		return name;
	}

	public String getSquareLogoUrl() {
		return squareLogoUrl;
	}

	public String getRoundLogoUrl() {
		return roundLogoUrl;
	}

	// ---------- setter Ӧ��ȫ��ȥ��
	public void setAgentId(int agentId) {
		this.agentId = agentId;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setSquareLogoUrl(String squareLogoUrl) {
		this.squareLogoUrl = squareLogoUrl;
	}

	public void setRoundLogoUrl(String roundLogoUrl) {
		this.roundLogoUrl = roundLogoUrl;
	}

	@Override
	public String toString() {
		return "AgentOverview [agentId=" + agentId + ", name=" + name
				+ ", squareLogoUrl=" + squareLogoUrl + ", roundLogoUrl="
				+ roundLogoUrl + "]";
	}
}
