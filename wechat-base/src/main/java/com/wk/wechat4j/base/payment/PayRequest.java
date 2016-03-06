package com.wk.wechat4j.base.payment;

import com.alibaba.fastjson.annotation.JSONField;
import com.wk.wechat4j.base.util.DateUtil;
import com.wk.wechat4j.base.util.RandomUtil;

import javax.xml.bind.annotation.*;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class PayRequest extends PayBaseInfo {

	private static final long serialVersionUID = -453746488398523883L;

	/**
	 * ����������չ ������Ϣ��ɸ��ַ���
	 */
	@XmlElement(name = "Package")
	@JSONField(name = "package")
	private String packageInfo;

	/**
	 * �����ֶ�
	 */
	@XmlTransient
	@JSONField(serialize = false)
	private String prepayId;
	/**
	 * �����ֶ�
	 */
	@XmlTransient
	@JSONField(serialize = false)
	private String partnerId;

	protected PayRequest() {
		// jaxb required
	}

	public PayRequest(String appId, String packageInfo) {
		super(appId, DateUtil.timestamp2string(), RandomUtil.generateString(16));
		this.packageInfo = packageInfo;
	}

	public String getPackageInfo() {
		return packageInfo;
	}

	public void setPackageInfo(String packageInfo) {
		this.packageInfo = packageInfo;
	}

	public String getPrepayId() {
		return prepayId;
	}

	public void setPrepayId(String prepayId) {
		this.prepayId = prepayId;
	}

	public String getPartnerId() {
		return partnerId;
	}

	public void setPartnerId(String partnerId) {
		this.partnerId = partnerId;
	}

	@Override
	public String toString() {
		return "package" + packageInfo + ", " + super.toString();
	}
}
