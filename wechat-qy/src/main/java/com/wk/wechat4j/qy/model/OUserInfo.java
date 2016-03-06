package com.wk.wechat4j.qy.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.wk.wechat4j.base.model.Token;
import com.wk.wechat4j.qy.type.AgentAuthType;

import java.io.Serializable;
import java.util.List;

/**
 * ��ҵ��oauth��Ȩ��½��Ϣ&������Ӧ����Ȩ��Ϣ
 *
 * @className OUserInfo
 * @author jy
 * @date 2015��6��12��
 * @since JDK 1.6
 * @see
 */
public class OUserInfo implements Serializable {

	private static final long serialVersionUID = -567063562050171293L;
	/**
	 * �Ƿ�ϵͳ����Ա
	 */
	@JSONField(name = "is_sys")
	private boolean isSysAdmin;
	/**
	 * �Ƿ��ڲ�����Ա
	 */
	@JSONField(name = "is_inner")
	private boolean isInnerAdmin;
	/**
	 * ��½����Ա��Ϣ
	 */
	@JSONField(name = "user_info")
	private User adminInfo;
	/**
	 * ��Ȩ����ҵ��Ϣ
	 */
	@JSONField(name = "corp_info")
	private CorpInfo corpInfo;
	/**
	 * �ù���Ա�ڸ��ṩ������ʹ�õ�Ӧ���б�
	 */
	@JSONField(name = "agent")
	private List<AgentItem> agentInfo;
	/**
	 * �ù���Աӵ�е�ͨѶ¼Ȩ��
	 */
	@JSONField(name = "auth_info")
	private AuthInfo authInfo;
	/**
	 * ��¼��ת��Ʊ����Ϣ
	 */
	@JSONField(name = "redirect_login_info")
	private Token redirectLoginInfo;

	public boolean isSysAdmin() {
		return isSysAdmin;
	}

	public boolean isInnerAdmin() {
		return isInnerAdmin;
	}

	public User getAdminInfo() {
		return adminInfo;
	}

	public CorpInfo getCorpInfo() {
		return corpInfo;
	}

	public List<AgentItem> getAgentInfo() {
		return agentInfo;
	}

	public AuthInfo getAuthInfo() {
		return authInfo;
	}

	public Token getRedirectLoginInfo() {
		return redirectLoginInfo;
	}

	// ---------- setter Ӧ��ȫ��ȥ��

	public void setSysAdmin(boolean isSysAdmin) {
		this.isSysAdmin = isSysAdmin;
	}

	public void setInnerAdmin(boolean isInnerAdmin) {
		this.isInnerAdmin = isInnerAdmin;
	}

	public void setAdminInfo(User adminInfo) {
		this.adminInfo = adminInfo;
	}

	public void setCorpInfo(CorpInfo corpInfo) {
		this.corpInfo = corpInfo;
	}

	public void setAgentInfo(List<AgentItem> agentInfo) {
		this.agentInfo = agentInfo;
	}

	public void setAuthInfo(AuthInfo authInfo) {
		this.authInfo = authInfo;
	}

	public void setRedirectLoginInfo(Token redirectLoginInfo) {
		this.redirectLoginInfo = redirectLoginInfo;
	}

	@Override
	public String toString() {
		return "OUserInfo [isSysAdmin=" + isSysAdmin + ", isInnerAdmin="
				+ isInnerAdmin + ", adminInfo=" + adminInfo + ", corpInfo="
				+ corpInfo + ", agentInfo=" + agentInfo + ", authInfo="
				+ authInfo + ", redirectLoginInfo=" + redirectLoginInfo + "]";
	}

	/**
	 * ��Ȩ��Ϣ
	 *
	 * @className AuthInfo
	 * @author jy
	 * @date 2015��6��22��
	 * @since JDK 1.6
	 * @see
	 */
	public static class AuthInfo implements Serializable {

		private static final long serialVersionUID = -4290240764958942370L;
		/**
		 * ��Ȩ��Ӧ����Ϣ
		 */
		@JSONField(name = "agent")
		private List<AgentItem> agentItems;
		/**
		 * ��Ȩ��ͨѶ¼����
		 */
		@JSONField(name = "department")
		private List<DepartItem> departItems;

		public List<AgentItem> getAgentItems() {
			return agentItems;
		}

		public List<DepartItem> getDepartItems() {
			return departItems;
		}

		// ---------- setter Ӧ��ȫ��ȥ��

		public void setAgentItems(List<AgentItem> agentItems) {
			this.agentItems = agentItems;
		}

		public void setDepartItems(List<DepartItem> departItems) {
			this.departItems = departItems;
		}

		@Override
		public String toString() {
			return "AuthInfo [agentItems=" + agentItems + ", departItems="
					+ departItems + "]";
		}
	}

	/**
	 * ��Ȩ��Ӧ����Ϣ
	 *
	 * @className AgentItem
	 * @author jy
	 * @date 2015��6��22��
	 * @since JDK 1.6
	 * @see
	 */
	public static class AgentItem extends AgentOverview {

		private static final long serialVersionUID = -1188968391623633559L;
		/**
		 * ����Ա��Ӧ�õ�Ȩ��
		 */
		@JSONField(name = "auth_type")
		private int authType;
		/**
		 * �������׼��еĶ�ӦӦ��id
		 */
		@JSONField(name = "appid")
		private int appId;
		/**
		 * ��Ȩ��Ӧ������Ȩ���飬Ŀǰ����get_location����ʾ�Ƿ���Ȩ������Ӧ�û�ȡ����λ�õĿ���
		 */
		@JSONField(name = "api_group")
		private List<String> apiGroup;

		public int getAuthType() {
			return authType;
		}

		@JSONField(serialize = false)
		public AgentAuthType getFormatAuthType() {
			if (authType == 0) {
				return AgentAuthType.USE;
			} else if (authType == 1) {
				return AgentAuthType.MANAGE;
			}
			return null;
		}

		public int getAppId() {
			return appId;
		}

		public List<String> getApiGroup() {
			return apiGroup;
		}

		// ---------- setter Ӧ��ȫ��ȥ��

		public void setAuthType(int authType) {
			this.authType = authType;
		}

		public void setAppId(int appId) {
			this.appId = appId;
		}

		public void setApiGroup(List<String> apiGroup) {
			this.apiGroup = apiGroup;
		}

		@Override
		public String toString() {
			return "AgentItem [authType=" + authType + ", appId=" + appId
					+ ", apiGroup=" + apiGroup + ", " + super.toString() + "]";
		}
	}

	/**
	 * ��Ȩ��ͨѶ¼����
	 *
	 * @className DepartItem
	 * @author jy
	 * @date 2015��6��22��
	 * @since JDK 1.6
	 * @see
	 */
	public static class DepartItem extends Party {

		private static final long serialVersionUID = 556556672204642407L;

		/**
		 * �Ƿ���иò��ŵ�дȨ��
		 */
		private boolean writable;

		public boolean isWritable() {
			return writable;
		}

		// ---------- setter Ӧ��ȫ��ȥ��
		public void setWritable(boolean writable) {
			this.writable = writable;
		}

		@Override
		public String toString() {
			return "DepartItem [writable=" + writable + ", " + super.toString()
					+ "]";
		}
	}
}
