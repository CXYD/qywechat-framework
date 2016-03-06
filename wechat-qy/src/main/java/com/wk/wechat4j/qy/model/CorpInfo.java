package com.wk.wechat4j.qy.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.wk.wechat4j.qy.type.CorpType;

import java.io.Serializable;

/**
 * ��Ȩ����ҵ����Ϣ
 *
 * @className CorpInfo
 * @author jy
 * @date 2015��6��12��
 * @since JDK 1.6
 * @see
 */
public class CorpInfo implements Serializable {

	private static final long serialVersionUID = 1251033124778972419L;
	/**
	 * ��Ȩ����ҵ��id
	 */
	@JSONField(name = "corpid")
	private String corpId;
	/**
	 * ��Ȩ����ҵ������
	 */
	@JSONField(name = "corp_name")
	private String corpName;
	/**
	 * ��ҵ����ͷ��
	 */
	@JSONField(name = "corp_square_logo_url")
	private String squareLogoUrl;
	/**
	 * ��ҵԲ��ͷ��
	 */
	@JSONField(name = "corp_round_logo_url")
	private String roundLogoUrl;
	/**
	 * ��Ȩ����ҵ������
	 */
	@JSONField(name = "corp_type")
	private String corpType;
	/**
	 * ��Ȩ����ҵ���û���ģ
	 */
	@JSONField(name = "corp_user_max")
	private Integer userMax;
	/**
	 * ��Ȩ����ҵ��Ӧ�ù�ģ
	 */
	@JSONField(name = "corp_agent_max")
	private Integer agentMax;
	/**
	 * ��Ȩ����ҵ�Ŷ�ά��
	 */
	@JSONField(name = "corp_wxqrcode")
	private String wxQrCode;

	public String getCorpId() {
		return corpId;
	}

	public String getCorpType() {
		return corpType;
	}

	@JSONField(serialize = false)
	public CorpType getFormatCorpType() {
		return corpType != null ? CorpType.valueOf(corpType) : null;
	}

	public String getCorpName() {
		return corpName;
	}

	public String getSquareLogoUrl() {
		return squareLogoUrl;
	}

	public String getRoundLogoUrl() {
		return roundLogoUrl;
	}

	public Integer getUserMax() {
		return userMax;
	}

	public Integer getAgentMax() {
		return agentMax;
	}

	public String getWxQrCode() {
		return wxQrCode;
	}

	// ---------- setter Ӧ��ȫ��ȥ��

	public void setCorpId(String corpId) {
		this.corpId = corpId;
	}

	public void setCorpName(String corpName) {
		this.corpName = corpName;
	}

	public void setSquareLogoUrl(String squareLogoUrl) {
		this.squareLogoUrl = squareLogoUrl;
	}

	public void setRoundLogoUrl(String roundLogoUrl) {
		this.roundLogoUrl = roundLogoUrl;
	}

	public void setCorpType(String corpType) {
		this.corpType = corpType;
	}

	public void setUserMax(Integer userMax) {
		this.userMax = userMax;
	}

	public void setAgentMax(Integer agentMax) {
		this.agentMax = agentMax;
	}

	public void setWxQrCode(String wxQrCode) {
		this.wxQrCode = wxQrCode;
	}

	@Override
	public String toString() {
		return "CorpInfo [corpType=" + corpId + ", corpName=" + corpName
				+ ", squareLogoUrl=" + squareLogoUrl + ", roundLogoUrl="
				+ roundLogoUrl + ", corpType=" + corpType + ", userMax="
				+ userMax + ", agentMax=" + agentMax + ", wxQrCode=" + wxQrCode
				+ "]";
	}
}