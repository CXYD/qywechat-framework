package com.wk.wechat4j.qy.jssdk;

import com.alibaba.fastjson.annotation.JSONField;
import com.wk.wechat4j.qy.model.IdParameter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * JSSDK��ϵ��ɸѡ����
 *
 * @className JSSDKContactParameter
 * @author jy
 * @date 2015��12��25��
 * @since JDK 1.6
 * @see
 */
public class JSSDKContactParameter extends IdParameter {
	private static final long serialVersionUID = 1863797419140279996L;

	/**
	 * ѡ��ģʽ single/multi
	 */
	private String mode;
	/**
	 * ѡ���������� department/tag/user
	 */
	@JSONField(name = "type")
	private List<String> limitTypes;
	/**
	 * ��ѡ�û�ID
	 */
	private List<String> selectedUserIds;
	/**
	 * ��ѡ����ID
	 */
	private List<Integer> selectedDepartmentIds;
	/**
	 * ��ѡ��ǩID
	 */
	private List<Integer> selectedTagIds;

	public JSSDKContactParameter() {
		super();
		this.selectedUserIds = new ArrayList<String>();
		this.selectedTagIds = new ArrayList<Integer>();
		this.selectedDepartmentIds = new ArrayList<Integer>();
		this.limitTypes = new ArrayList<String>();
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public List<String> getLimitTypes() {
		return limitTypes;
	}

	public void setLimitTypes(List<String> limitTypes) {
		this.limitTypes = limitTypes;
	}

	public void putLimitType(String... limitTypes) {
		this.limitTypes.addAll(Arrays.asList(limitTypes));
	}

	public List<String> getSelectedUserIds() {
		return selectedUserIds;
	}

	public void setSelectedUserIds(List<String> selectedUserIds) {
		this.selectedUserIds = selectedUserIds;
	}

	public void putSelectedUserIds(String... selectedUserIds) {
		this.selectedUserIds.addAll(Arrays.asList(selectedUserIds));
	}

	public List<Integer> getSelectedDepartmentIds() {
		return selectedDepartmentIds;
	}

	public void setSelectedDepartmentIds(List<Integer> selectedDepartmentIds) {
		this.selectedDepartmentIds = selectedDepartmentIds;
	}

	public void putSelectedDepartmentIds(Integer... selectedDepartmentIds) {
		this.selectedDepartmentIds.addAll(Arrays.asList(selectedDepartmentIds));
	}

	public List<Integer> getSelectedTagIds() {
		return selectedTagIds;
	}

	public void setSelectedTagIds(List<Integer> selectedTagIds) {
		this.selectedTagIds = selectedTagIds;
	}

	public void putSelectedTagIds(Integer... selectedTagIds) {
		this.selectedTagIds.addAll(Arrays.asList(selectedTagIds));
	}

	@Override
	public String toString() {
		return "JSSDKContactParameter [mode=" + mode + ", limitTypes="
				+ limitTypes + ", selectedUserIds=" + selectedUserIds
				+ ", selectedDepartmentIds=" + selectedDepartmentIds
				+ ", selectedTagIds=" + selectedTagIds + ", "
				+ super.toString() + "]";
	}
}
