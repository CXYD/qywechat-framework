package com.wk.wechat4j.qy.model;

import com.alibaba.fastjson.annotation.JSONCreator;
import com.alibaba.fastjson.annotation.JSONField;
import com.wk.wechat4j.base.model.WeixinAccount;

import java.util.List;

/**
 * ΢����ҵ����Ϣ
 *
 * @className WeixinQyAccount
 * @author jy
 * @date 2014��11��18��
 * @since JDK 1.6
 * @see <a href=
 *      "https://qy.weixin.qq.com/cgi-bin/home?lang=zh_CN&token=685923034#setting"
 *      >��ҵ������</a>
 */
public class WeixinQyAccount extends WeixinAccount {

	private static final long serialVersionUID = 3689999353867189585L;
	/**
	 * ���Ӧ���׼���Ϣ
	 */
	private List<WeixinAccount> suiteAccounts;
	/**
	 * �������ṩ��secret(��ҵ�ŵ�½)
	 */
	private String providerSecret;
	/**
	 * ��Ϣ����secret(��ҵ������)
	 */
	private String chatSecret;

	/**
	 *
	 * @param corpid
	 *            ��ҵID ����
	 * @param corpsecret
	 *            �������ƾ֤��Կ ʹ����ͨ�ӿ�(WeixinProxy����)������д
	 * @param suites
	 *            Ӧ���׼����� ʹ���׼��ӿ�(WeixinSuiteProxy#SuiteApi)������д
	 * @param providerSecret
	 *            �������ṩ��secret(��ҵ�ŵ�½) ʹ�÷����̽ӿ�(WeixinSuiteProxy#ProviderApi)������
	 * @param chatSecret
	 *            ��Ϣ����secret(��ҵ������) ������;
	 */
	@JSONCreator
	public WeixinQyAccount(@JSONField(name = "id") String corpid,
			@JSONField(name = "secret") String corpsecret,
			@JSONField(name = "suites") List<WeixinAccount> suiteAccounts,
			@JSONField(name = "providerSecret") String providerSecret,
			@JSONField(name = "chatSecret") String chatSecret) {
		super(corpid, corpsecret);
		this.suiteAccounts = suiteAccounts;
		this.providerSecret = providerSecret;
		this.chatSecret = chatSecret;
	}

	public List<WeixinAccount> getSuiteAccounts() {
		return suiteAccounts;
	}

	public String getProviderSecret() {
		return providerSecret;
	}

	public String getChatSecret() {
		return chatSecret;
	}

	public WeixinAccount[] suiteAccountsToArray() {
		return suiteAccounts != null ? suiteAccounts
				.toArray(new WeixinAccount[suiteAccounts.size()]) : null;
	}

	@Override
	public String toString() {
		return "WeixinQyAccount [" + super.toString() + ", suiteAccounts="
				+ suiteAccounts + ", providerSecret=" + providerSecret
				+ ",  chatSecret=" + chatSecret + "]";
	}
}
