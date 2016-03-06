package com.wk.wechat4j.qy.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.wk.wechat4j.base.model.Gender;
import com.wk.wechat4j.base.util.NameValue;
import com.wk.wechat4j.qy.type.UserStatus;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

/**
 * ���ų�Ա����
 *
 * @className User
 * @author jy
 * @date 2014��11��19��
 * @since JDK 1.6
 * @see <a
 *      href="http://qydev.weixin.qq.com/wiki/index.php?title=%E7%AE%A1%E7%90%86%E6%88%90%E5%91%98">�����Ա˵��</a>
 */
public class User implements Serializable {

	private static final long serialVersionUID = 4747301605060801611L;
	/**
	 * ���� Ա��UserID����Ӧ����˵��ʺţ���ҵ�ڱ���Ψһ������Ϊ1~64���ַ�
	 */
	@JSONField(name = "userid")
	private String userId;
	/**
	 * ���� ��Ա���ơ�����Ϊ1~64���ַ�
	 */
	private String name;
	/**
	 * �Ǳ��� ��Ա��������id�б�ע�⣬ÿ�����ŵ�ֱ��Ա������Ϊ1000��
	 */
	@JSONField(name = "department")
	private List<Integer> partyIds;
	/**
	 * �Ǳ��� ְλ��Ϣ������Ϊ0~64���ַ�
	 */
	private String position;
	/**
	 * �Ǳ��� �ֻ����롣��ҵ�ڱ���Ψһ��mobile/weixinid/email���߲���ͬʱΪ��
	 */
	private String mobile;
	/**
	 * �Ǳ��� �Ա�gender=0��ʾ�У�=1��ʾŮ��Ĭ��gender=0
	 */
	private Integer gender;
	/**
	 * �Ǳ��� �칫�绰������Ϊ0~64���ַ�
	 */
	private String tel;
	/**
	 * �Ǳ��� ���䡣����Ϊ0~64���ַ�����ҵ�ڱ���Ψһ
	 */
	private String email;
	/**
	 * �Ǳ��� ΢�źš���ҵ�ڱ���Ψһ
	 */
	@JSONField(name = "weixinid")
	private String weixinId;
	/**
	 * ͷ��url��ע�����Ҫ��ȡСͼ��url����"/0"�ĳ�"/64"����
	 */
	private String avatar;
	/**
	 * ��ע״̬: 1=�ѹ�ע��2=�Ѷ��ᣬ4=δ��ע
	 */
	private Integer status;
	/**
	 * �Ǳ��� ��չ���ԡ���չ������Ҫ��WEB����˴��������Ч���������δ֪���Եĸ�ֵ
	 */
	private List<NameValue> extattr;

	protected User() {
	}

	public User(String userId, String name) {
		this.userId = userId;
		this.name = name;
	}

	public String getUserId() {
		return userId;
	}

	public String getName() {
		return name;
	}

	public List<Integer> getPartyIds() {
		return partyIds;
	}

	public void setPartyIds(Integer... partyIds) {
		this.partyIds = Arrays.asList(partyIds);
	}

	public String getPosition() {
		return position;
	}

	public String getMobile() {
		return mobile;
	}

	public Integer getGender() {
		return gender;
	}

	@JSONField(serialize = false)
	public Gender getFormatGender() {
		if (gender != null) {
			if (gender.intValue() == 0) {
				return Gender.male;
			} else if (gender.intValue() == 1) {
				return Gender.female;
			} else {
				return Gender.unknown;
			}
		}
		return null;
	}

	public String getTel() {
		return tel;
	}

	public String getEmail() {
		return email;
	}

	public String getWeixinId() {
		return weixinId;
	}

	public String getAvatar() {
		return avatar;
	}

	@JSONField(serialize = false)
	public UserStatus getFormatStatus() {
		if (status != null) {
			for (UserStatus userStatus : UserStatus.values()) {
				if (userStatus.getVal() == status) {
					return userStatus;
				}
			}
		}
		return null;
	}

	public Integer getStatus() {
		return status;
	}

	@JSONField(serialize = false)
	public Boolean getFormatEnable() {
		if (status != null) {
			return status.intValue() != 2;
		}
		return Boolean.FALSE;
	}

	public void setEnable(boolean enable) {
		this.status = enable ? 1 : 0;
	}

	public List<NameValue> getExtattr() {
		return extattr;
	}

	public void setExtattr(List<NameValue> extattr) {
		this.extattr = extattr;
	}

	public void setExtattr(NameValue... extattr) {
		this.extattr = Arrays.asList(extattr);
	}

	public void pushExattr(String name, String value) {
		extattr.add(new NameValue(name, value));
	}

	// ---------- setter Ӧ��ȫ��ȥ��

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPartyIds(List<Integer> partyIds) {
		this.partyIds = partyIds;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setWeixinId(String weixinId) {
		this.weixinId = weixinId;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", name=" + name + ", partyIds="
				+ partyIds + ", position=" + position + ", mobile=" + mobile
				+ ", gender=" + gender + ", tel=" + tel + ", email=" + email
				+ ", weixinId=" + weixinId + ", avatar=" + avatar + ", status="
				+ status + ", extattr=" + extattr + "]";
	}
}
