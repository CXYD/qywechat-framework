package com.wk.wechat4j.qy.jssdk;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wk.wechat4j.base.exception.WeixinException;
import com.wk.wechat4j.base.model.Token;
import com.wk.wechat4j.base.token.TokenHolder;
import com.wk.wechat4j.base.util.DateUtil;
import com.wk.wechat4j.base.util.DigestUtil;
import com.wk.wechat4j.base.util.MapUtil;
import com.wk.wechat4j.base.util.RandomUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * JSSDK��ϵ��ɸѡ����
 *
 * @className JSSDKContactConfigurator
 * @author jy
 * @date 2015��12��25��
 * @since JDK 1.7
 * @see
 */
public class JSSDKContactConfigurator {
	private final TokenHolder ticketTokenHolder;
	private JSSDKContactParameter contactParameter;

	/**
	 * ticket������ �ɵ���WeixinProxy#getTicketHolder��ȡ
	 *
	 * @param ticketTokenHolder
	 */
	public JSSDKContactConfigurator(TokenHolder ticketTokenHolder) {
		this.ticketTokenHolder = ticketTokenHolder;
		this.contactParameter = new JSSDKContactParameter();
	}

	/**
	 * ��ѡ��Χ������ID�б�(���partyIdsΪ0���ʾ��ʾ�����������в���)
	 *
	 * @param departmentIds
	 * @return
	 */
	public JSSDKContactConfigurator partyIds(Integer... partyIds) {
		contactParameter.putPartyIds(partyIds);
		return this;
	}

	/**
	 * ��ѡ��Χ����ǩID�б�(���tagIdsΪ0���ʾ��ʾ���б�ǩ)
	 *
	 * @param tagIds
	 * @return
	 */
	public JSSDKContactConfigurator tagIds(Integer... tagIds) {
		contactParameter.putTagIds(tagIds);
		return this;
	}

	/**
	 * ��ѡ��Χ���û�ID�б�
	 *
	 * @param userIds
	 * @return
	 */
	public JSSDKContactConfigurator userIds(String... userIds) {
		contactParameter.putUserIds(userIds);
		return this;
	}

	/**
	 * ��ѡģʽ
	 *
	 * @return
	 */
	public JSSDKContactConfigurator singleMode() {
		contactParameter.setMode("single");
		return this;
	}

	/**
	 * ��ѡģʽ
	 *
	 * @return
	 */
	public JSSDKContactConfigurator multiMode() {
		contactParameter.setMode("multi");
		return this;
	}

	/**
	 * ���Ʋ���
	 *
	 * @return
	 */
	public JSSDKContactConfigurator limitDepartment() {
		contactParameter.putLimitType("department");
		return this;
	}

	/**
	 * ���Ʊ�ǩ
	 *
	 * @return
	 */
	public JSSDKContactConfigurator limitTag() {
		contactParameter.putLimitType("tag");
		return this;
	}

	/**
	 * �����û�
	 *
	 * @return
	 */
	public JSSDKContactConfigurator limitUser() {
		contactParameter.putLimitType("user");
		return this;
	}

	/**
	 * ��ѡ����ID
	 *
	 * @param selectedDepartmentIds
	 * @return
	 */
	public JSSDKContactConfigurator selectedDepartmentIds(
			Integer... selectedDepartmentIds) {
		contactParameter.putSelectedDepartmentIds(selectedDepartmentIds);
		return this;
	}

	/**
	 * ��ѡ��ǩID
	 *
	 * @param selectedTagIds
	 * @return
	 */
	public JSSDKContactConfigurator selectedTagIds(Integer... selectedTagIds) {
		contactParameter.putSelectedTagIds(selectedTagIds);
		return this;
	}

	/**
	 * ��ѡ�û�ID
	 *
	 * @param selectedUserIds
	 * @return
	 */
	public JSSDKContactConfigurator selectedUserIds(String... selectedUserIds) {
		contactParameter.putSelectedUserIds(selectedUserIds);
		return this;
	}

	/**
	 * ����config����JSON��
	 *
	 * @param url
	 *            ��ǰ��ҳ��URL��������#������沿��
	 * @return
	 * @throws WeixinException
	 */
	public String toJSONConfig(String url) throws WeixinException {
		return toJSONConfig(url, contactParameter);
	}

	/**
	 * ����config����JSON��
	 *
	 * @param url
	 *            ��ǰ��ҳ��URL��������#������沿��
	 * @param parameter
	 *            �Զ��崫���������
	 * @return
	 * @throws WeixinException
	 */
	public String toJSONConfig(String url, JSSDKContactParameter parameter)
			throws WeixinException {
		Map<String, String> signMap = new HashMap<String, String>();
		String timestamp = DateUtil.timestamp2string();
		String noncestr = RandomUtil.generateString(24);
		Token token = this.ticketTokenHolder.getToken();
		signMap.put("timestamp", timestamp);
		signMap.put("nonceStr", noncestr);
		signMap.put("group_ticket", token.getAccessToken());
		signMap.put("url", url);
		String sign = DigestUtil.SHA1(MapUtil
				.toJoinString(signMap, false, true));
		JSONObject config = new JSONObject();
		config.put("signature", sign);
		config.put("groupId", JSON.parseObject(token.getOriginalResult())
				.getString("group_id"));
		config.put("timestamp", timestamp);
		config.put("noncestr", noncestr);
		config.put("params", parameter);
		return config.toJSONString();
	}
}
