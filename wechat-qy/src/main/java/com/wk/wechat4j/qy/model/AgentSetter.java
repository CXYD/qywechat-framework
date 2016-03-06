package com.wk.wechat4j.qy.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.wk.wechat4j.qy.type.ReportLocationType;

import java.io.Serializable;

/**
 * ������ҵ��Ӧ��
 *
 * @className AgentSetter
 * @author jy
 * @date 2015��3��16��
 * @since JDK 1.6
 * @see <a
 *      href="http://qydev.weixin.qq.com/wiki/index.php?title=%E8%AE%BE%E7%BD%AE%E4%BC%81%E4%B8%9A%E5%8F%B7%E5%BA%94%E7%94%A8">������ҵ��Ӧ��</a>
 */
public class AgentSetter implements Serializable {
	private static final long serialVersionUID = 5420335232308079801L;

	/**
	 * ��ҵӦ�õ�id
	 */
	@JSONField(name = "agentid")
	private int agentId;
	/**
	 * ��ҵӦ���Ƿ�򿪵���λ���ϱ�
	 */
	@JSONField(name = "report_location_flag")
	private ReportLocationType reportLocationType;
	/**
	 * ��ҵӦ��ͷ���mediaid��ͨ����ý��ӿ��ϴ�ͼƬ���mediaid���ϴ�����Զ��ü��ɷ��κ�Բ������ͷ��
	 */
	@JSONField(name = "logo_mediaid")
	private String logoMediaId;
	/**
	 * ��ҵӦ������
	 */
	private String name;
	/**
	 * ��ҵӦ������
	 */
	private String description;
	/**
	 * ��ҵӦ�ÿ�������
	 */
	@JSONField(name = "redirect_domain")
	private String redirectDomain;
	/**
	 * �Ƿ�����û����֪ͨ��0�������գ�1������
	 */
	@JSONField(name = "isreportuser")
	private boolean isReportUser;
	/**
	 * �Ƿ��ϱ��û�����Ӧ���¼���0�������գ�1�����ա���ҳ��Ӧ������ò���
	 */
	@JSONField(name = "isreportenter")
	private boolean isReportEnter;
	/**
	 * ��ҳ��Ӧ��url��url������http����https��ͷ����Ϣ��Ӧ������ò���
	 */
	@JSONField(name = "home_url")
	private String homeUrl;

	public AgentSetter(int agentId) {
		this.agentId = agentId;
	}

	public int getAgentId() {
		return agentId;
	}

	public ReportLocationType getReportLocationType() {
		return reportLocationType;
	}

	public String getLogoMediaId() {
		return logoMediaId;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public String getRedirectDomain() {
		return redirectDomain;
	}

	public String getHomeUrl() {
		return homeUrl;
	}

	// ---------- setter Ӧ��ȫ��ȥ��

	public void setReportLocationType(ReportLocationType reportLocationType) {
		this.reportLocationType = reportLocationType;
	}

	public void setLogoMediaid(String logoMediaId) {
		this.logoMediaId = logoMediaId;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setRedirectDomain(String redirectDomain) {
		this.redirectDomain = redirectDomain;
	}

	public void setAgentId(int agentId) {
		this.agentId = agentId;
	}

	public void setLogoMediaId(String logoMediaId) {
		this.logoMediaId = logoMediaId;
	}

	public void setReportUser(boolean isReportUser) {
		this.isReportUser = isReportUser;
	}

	public void setReportEnter(boolean isReportEnter) {
		this.isReportEnter = isReportEnter;
	}

	public void setHomeUrl(String homeUrl) {
		this.homeUrl = homeUrl;
	}

	@Override
	public String toString() {
		return "agentId=" + agentId + ", reportLocationType="
				+ reportLocationType + ", logoMediaId=" + logoMediaId
				+ ", name=" + name + ", description=" + description
				+ ", redirectDomain=" + redirectDomain + ", isReportUser="
				+ isReportUser + ", isReportEnter=" + isReportEnter
				+ ", homeUrl=" + homeUrl;
	}
}
