package com.wk.wechat4j.qy.model;

import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * ��ҵ��Ӧ�õ�������Ϣ
 *
 * @className AgentInfo
 * @author jy
 * @date 2015��3��17��
 * @since JDK 1.6
 * @see
 */
public class AgentInfo extends AgentSetter {

	private static final long serialVersionUID = -8975132919768696174L;

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
	/**
	 * ��ҵӦ�ÿɼ���Χ����Ա�������а���userid�͹�ע״̬state
	 */
	@JSONField(deserialize = false)
	private List<User> allowUsers;
	/**
	 * ��ҵӦ�ÿɼ���Χ�����ţ�
	 */
	@JSONField(deserialize = false)
	private List<Integer> allowPartys;
	/**
	 * ��ҵӦ�ÿɼ���Χ����ǩ��
	 */
	@JSONField(deserialize = false)
	private List<Integer> allowTags;
	/**
	 * ��ҵӦ���Ƿ񱻽���
	 */
	private boolean close;

	public AgentInfo() {
		super(0);
	}

	public String getSquareLogoUrl() {
		return squareLogoUrl;
	}

	public String getRoundLogoUrl() {
		return roundLogoUrl;
	}

	public List<User> getAllowUsers() {
		return allowUsers;
	}

	public void setAllowUsers(List<User> allowUsers) {
		this.allowUsers = allowUsers;
	}

	public List<Integer> getAllowPartys() {
		return allowPartys;
	}

	public void setAllowPartys(List<Integer> allowPartys) {
		this.allowPartys = allowPartys;
	}

	public List<Integer> getAllowTags() {
		return allowTags;
	}

	public void setAllowTags(List<Integer> allowTags) {
		this.allowTags = allowTags;
	}

	public boolean isClose() {
		return close;
	}

	// ---------- setter Ӧ��ȫ��ȥ��

	public void setSquareLogoUrl(String squareLogoUrl) {
		this.squareLogoUrl = squareLogoUrl;
	}

	public void setRoundLogoUrl(String roundLogoUrl) {
		this.roundLogoUrl = roundLogoUrl;
	}

	public void setClose(boolean close) {
		this.close = close;
	}

	@Override
	public String toString() {
		return "AgentInfo [squareLogoUrl=" + squareLogoUrl + ", roundLogoUrl="
				+ roundLogoUrl + ", allowUsers=" + allowUsers
				+ ", allowPartys=" + allowPartys + ", allowTags=" + allowTags
				+ ", close=" + close + ", " + super.toString() + "]";
	}
}
