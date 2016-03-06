package com.wk.wechat4j.base.model;

import com.alibaba.fastjson.annotation.JSONCreator;
import com.alibaba.fastjson.annotation.JSONField;
import com.wk.wechat4j.base.util.StringUtil;

/**
 * ΢��֧���˻�
 *
 * @className WeixinPayAccount
 * @author jy
 * @date 2015��6��26��
 * @since JDK 1.6
 * @see
 */
public class WeixinPayAccount extends WeixinAccount {

	private static final long serialVersionUID = -2791256176906048632L;
	/**
	 * ���ں�֧�����������ڼ��ܵ���Կ Key,����֤�̻�Ψһ���,PaySignKey ��Ӧ��֧�������е� appKey ֵ
	 */
	private String paySignKey;
	/**
	 * �Ƹ�ͨ�̻���ݵı�ʶ
	 */
	private String partnerId;
	/**
	 * �Ƹ�ͨ�̻�Ȩ����ԿKey
	 */
	private String partnerKey;
	/**
	 * ΢��֧��������̻���(�̻�ƽ̨��)
	 */
	private String mchId;
	/**
	 * ����֧��֤���ļ�������(�̻�ƽ̨��)
	 */
	private String certificateKey;
	/**
	 * ΢��֧����������̻��ţ�����ģʽ�±���(�̻�ƽ̨��)
	 */
	private String subMchId;
	/**
	 * ΢��֧��������豸��(�̻�ƽ̨��)
	 */
	private String deviceInfo;

	/**
	 * �̻�ƽ̨�汾(V3)�ֶ�
	 *
	 * @param appId
	 *            ���ں�Ψһ�����ID(����)
	 * @param appSecret
	 *            ���ýӿڵ�ƾ֤(�����д)
	 * @param paySignKey
	 *            ֧����Կ�ַ���(����)
	 * @param mchId
	 *            ΢��֧��������̻���(����)
	 */
	public WeixinPayAccount(String appId, String appSecret, String paySignKey,
			String mchId) {
		this(appId, appSecret, paySignKey, mchId, null, null, null, null, null);
	}

	/**
	 * ֧���̻���Ϣ
	 *
	 * @param appId
	 *            ���ں�Ψһ�����ID(����)
	 * @param appSecret
	 *            ���ýӿڵ�ƾ֤(�����д)
	 * @param paySignKey
	 *            ֧����Կ�ַ���(����)
	 * @param mchId
	 *            ΢��֧��������̻���(V3�̻�ƽ̨�����)
	 * @param certificateKey
	 *            ����֧��֤���ļ�������(�̻�ƽ̨��)
	 * @param subMchId
	 *            ΢��֧����������̻��ţ�����ģʽ�±���(V3�̻�ƽ̨�� �Ǳ���)
	 * @param deviceInfo
	 *            ΢��֧��������豸��(V3�̻�ƽ̨�� �Ǳ���)
	 * @param partnerId
	 *            �Ƹ�ͨ���̻���(V2�汾����)
	 * @param partnerKey
	 *            �Ƹ�ͨ�̻�Ȩ����ԿKey(V2�汾����)
	 */
	@JSONCreator
	public WeixinPayAccount(@JSONField(name = "id") String appId,
			@JSONField(name = "secret") String appSecret,
			@JSONField(name = "paySignKey") String paySignKey,
			@JSONField(name = "mchId") String mchId,
			@JSONField(name = "certificateKey") String certificateKey,
			@JSONField(name = "subMchId") String subMchId,
			@JSONField(name = "deviceInfo") String deviceInfo,
			@JSONField(name = "partnerId") String partnerId,
			@JSONField(name = "partnerKey") String partnerKey) {
		super(appId, appSecret);
		this.paySignKey = paySignKey;
		this.mchId = mchId;
		this.certificateKey = certificateKey;
		this.subMchId = subMchId;
		this.deviceInfo = deviceInfo;
		this.partnerId = partnerId;
		this.partnerKey = partnerKey;
	}

	public String getPaySignKey() {
		return paySignKey;
	}

	public String getPartnerId() {
		return partnerId;
	}

	public String getPartnerKey() {
		return partnerKey;
	}

	public String getMchId() {
		return mchId;
	}

	public String getSubMchId() {
		return subMchId;
	}

	public String getDeviceInfo() {
		return deviceInfo;
	}

	public String getCertificateKey() {
		return StringUtil.isBlank(certificateKey) ? mchId : certificateKey;
	}

	@Override
	public String toString() {
		return "WeixinPayAccount [" + super.toString() + ", paySignKey="
				+ paySignKey + ", partnerId=" + partnerId + ", partnerKey="
				+ partnerKey + ", mchId=" + mchId + ", certificateKey="
				+ getCertificateKey() + ", subMchId=" + subMchId
				+ ", deviceInfo=" + deviceInfo + "]";
	}
}
