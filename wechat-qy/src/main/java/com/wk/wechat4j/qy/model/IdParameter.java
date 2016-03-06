package com.wk.wechat4j.qy.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.wk.wechat4j.base.util.StringUtil;

import java.io.Serializable;
import java.util.*;

/**
 * id������
 *
 * @className IdParameter
 * @author jy
 * @date 2015��3��30��
 * @since JDK 1.6
 * @see
 */
public class IdParameter implements Serializable {

	private static final long serialVersionUID = -2689758682205591133L;

	public static final char SEPARATOR = '|';

	public static final String SEPARATORS = String.valueOf(SEPARATOR);

	@JSONField(name = "user")
	private List<String> userIds;
	@JSONField(name = "party")
	private List<Integer> partyIds;
	@JSONField(name = "tag")
	private List<Integer> tagIds;

	public IdParameter() {
		this.userIds = new ArrayList<String>();
		this.partyIds = new ArrayList<Integer>();
		this.tagIds = new ArrayList<Integer>();
	}

	/**
	 * ���ӳ�ԱID�б����֧��1000��
	 *
	 * @param userIds
	 * @return
	 */
	public IdParameter putUserIds(String... userIds) {
		this.userIds.addAll(Arrays.asList(userIds));
		return this;
	}

	/**
	 * ��������ID�б����֧��100��
	 *
	 * @param partyIds
	 * @return
	 */
	public IdParameter putPartyIds(Integer... partyIds) {
		this.partyIds.addAll(Arrays.asList(partyIds));
		return this;
	}

	/**
	 * ������ǩID�б�
	 *
	 * @param tagIds
	 * @return
	 */
	public IdParameter putTagIds(Integer... tagIds) {
		this.tagIds.addAll(Arrays.asList(tagIds));
		return this;
	}

	public List<String> getUserIds() {
		return userIds;
	}

	public List<Integer> getPartyIds() {
		return partyIds;
	}

	public List<Integer> getTagIds() {
		return tagIds;
	}

	public void setUserIds(List<String> userIds) {
		this.userIds = userIds;
	}

	public void setPartyIds(List<Integer> partyIds) {
		this.partyIds = partyIds;
	}

	public void setTagIds(List<Integer> tagIds) {
		this.tagIds = tagIds;
	}

	/**
	 * ����ĳЩ�ӿ���Ҫ��Ŀ����� �緢�Ϳͷ���Ϣ�ӿ�
	 * 
	 * @return
	 */
	public Map<String, String> getParameter() {
		Map<String, String> parameterMap = new HashMap<String, String>();
		if (userIds != null && !userIds.isEmpty()) {
			parameterMap.put("touser", StringUtil.join(userIds, SEPARATOR));
		}
		if (partyIds != null && !partyIds.isEmpty()) {
			parameterMap.put("toparty", StringUtil.join(partyIds, SEPARATOR));
		}
		if (tagIds != null && !tagIds.isEmpty()) {
			parameterMap.put("totag", StringUtil.join(tagIds, SEPARATOR));
		}
		return parameterMap;
	}

	@Override
	public String toString() {
		return "IdParameter [userIds=" + userIds + ", partyIds=" + partyIds
				+ ", tagIds=" + tagIds + "]";
	}
}
