package com.wk.wechat4j.qy.suite;

import com.alibaba.fastjson.JSON;
import com.wk.wechat4j.base.http.HttpParams;
import com.wk.wechat4j.base.model.WeixinAccount;
import com.wk.wechat4j.base.token.FileTokenStorager;
import com.wk.wechat4j.base.token.TokenStorager;
import com.wk.wechat4j.base.util.StringUtil;
import com.wk.wechat4j.base.util.Weixin4jConfigUtil;
import com.wk.wechat4j.qy.model.WeixinQyAccount;

import java.util.Arrays;

/**
 * ΢�ŵ������׼��������
 *
 * @className Weixin4jSuiteSettings
 * @author jy
 * @date 2016��1��28��
 * @since JDK 1.6
 * @see
 */
public class Weixin4jSuiteSettings {
	/**
	 * ΢����ҵ����Ϣ
	 */
	private final WeixinQyAccount weixinAccount;
	/**
	 * Http����
	 */
	private HttpParams httpParams;
	/**
	 * token�洢��ʽ Ĭ��ΪFileTokenStorager
	 */
	private TokenStorager tokenStorager;
	/**
	 * ϵͳ��ʱĿ¼
	 */
	private String tmpdir;

	/**
	 * Ĭ��ʹ��weixin4j.properties���õ���Ϣ
	 */
	public Weixin4jSuiteSettings() {
		this(JSON.parseObject(Weixin4jConfigUtil.getValue("account"),
				WeixinQyAccount.class));
	}

	/**
	 *
	 * @param providerCorpId
	 *            �����̵���ҵ��ID <font color="red">ʹ�÷�����APIʱ������</font>
	 * @param providerSecret
	 *            ������secret <font color="red">ʹ�÷�����APIʱ������</font>
	 * @param suites
	 *            �׼���Ϣ <font color="red">ʹ���׼�APIʱ������</font>
	 */
	public Weixin4jSuiteSettings(String providerCorpId, String providerSecret,
			WeixinAccount... suites) {
		this.weixinAccount = new WeixinQyAccount(providerCorpId, null,
				Arrays.asList(suites), providerSecret, null);
	}

	private Weixin4jSuiteSettings(WeixinQyAccount weixinAccount) {
		this.weixinAccount = weixinAccount;
	}

	public WeixinQyAccount getWeixinAccount() {
		return weixinAccount;
	}

	public HttpParams getHttpParams() {
		return httpParams;
	}

	public HttpParams getHttpParams0() {
		if (httpParams == null) {
			return new HttpParams();
		}
		return httpParams;
	}

	public String getTmpdir() {
		return tmpdir;
	}

	public String getTmpdir0() {
		if (StringUtil.isBlank(tmpdir)) {
			return Weixin4jConfigUtil.getClassPathValue("weixin4j.tmpdir",
					System.getProperty("java.io.tmpdir"));
		}
		return tmpdir;
	}

	public TokenStorager getTokenStorager() {
		return tokenStorager;
	}

	public TokenStorager getTokenStorager0() {
		if (tokenStorager == null) {
			return new FileTokenStorager(getTmpdir0());
		}
		return tokenStorager;
	}

	public void setHttpParams(HttpParams httpParams) {
		this.httpParams = httpParams;
	}

	public void setTmpdir(String tmpdir) {
		this.tmpdir = tmpdir;
	}

	public void setTokenStorager(TokenStorager tokenStorager) {
		this.tokenStorager = tokenStorager;
	}

	@Override
	public String toString() {
		return "Weixin4jSuiteSettings [weixinAccount=" + weixinAccount
				+ ", httpParams=" + httpParams + ",tokenStorager="
				+ tokenStorager + ", tmpdir=" + tmpdir + "]";
	}
}
