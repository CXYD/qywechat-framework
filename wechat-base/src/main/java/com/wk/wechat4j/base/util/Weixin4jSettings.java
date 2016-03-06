package com.wk.wechat4j.base.util;

import com.alibaba.fastjson.JSON;
import com.wk.wechat4j.base.http.HttpParams;
import com.wk.wechat4j.base.model.WeixinAccount;
import com.wk.wechat4j.base.model.WeixinPayAccount;
import com.wk.wechat4j.base.token.FileTokenStorager;
import com.wk.wechat4j.base.token.TokenStorager;

/**
 * ΢���������
 *
 * @className Weixin4jSettings
 * @author jy
 * @date 2016��1��28��
 * @since JDK 1.6
 * @see
 */
public class Weixin4jSettings {
	/**
	 * ΢��֧���˺���Ϣ
	 */
	private WeixinPayAccount weixinPayAccount;
	/**
	 * ΢���˺���Ϣ
	 */
	private WeixinAccount weixinAccount;
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
	 * ֧���ӿ���Ҫ��֤���ļ�(*.p12)
	 */
	private String certificateFile;

	/**
	 * Ĭ��ʹ��weixin4j.properties���õ���Ϣ
	 */
	public Weixin4jSettings() {
		this(JSON.parseObject(Weixin4jConfigUtil.getValue("account"),
				WeixinPayAccount.class), "classpath:ca.p12");
	}

	/**
	 * ֧������ӿ�
	 *
	 * @param weixinPayAccount
	 *            �̻���Ϣ
	 * @param certificateFile
	 *            ֧���ӿ���Ҫ��֤���ļ�(*.p12),�����˿�ӿ�
	 */
	public Weixin4jSettings(WeixinPayAccount weixinPayAccount,
			String certificateFile) {
		this.weixinPayAccount = weixinPayAccount;
		this.certificateFile = certificateFile;
		this.weixinAccount = new WeixinAccount(weixinPayAccount.getId(),
				weixinPayAccount.getSecret());
	}

	/**
	 * ��ͨ����ӿ�
	 * 
	 * @param weixinAccount
	 */
	public Weixin4jSettings(WeixinAccount weixinAccount) {
		this.weixinAccount = weixinAccount;
	}

	public WeixinPayAccount getWeixinPayAccount() {
		return weixinPayAccount;
	}

	public WeixinAccount getWeixinAccount() {
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

	public String getCertificateFile() {
		return certificateFile;
	}

	public String getCertificateFile0() {
		if (StringUtil.isBlank(certificateFile)) {
			return Weixin4jConfigUtil.getClassPathValue(
					"weixin4j.certificate.file", "classpath:ca.p12");
		}
		return certificateFile;
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

	public void setCertificateFile(String certificateFile) {
		this.certificateFile = certificateFile;
	}

	@Override
	public String toString() {
		return "Weixin4jSettings [weixinAccount=" + weixinAccount
				+ ", httpParams=" + httpParams + ",tokenStorager="
				+ tokenStorager + ", tmpdir=" + tmpdir + ", certificateFile= "
				+ certificateFile + "]";
	}
}
