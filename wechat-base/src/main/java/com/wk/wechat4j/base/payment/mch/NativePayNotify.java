package com.wk.wechat4j.base.payment.mch;

import com.alibaba.fastjson.annotation.JSONField;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Native֧���ص�ʱPOST����Ϣ
 *
 * @className PayNativeNotify
 * @author jy
 * @date 2014��10��30��
 * @since JDK 1.6
 * @see
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class NativePayNotify extends OpenIdResult {

	private static final long serialVersionUID = 4515471400239795492L;
	/**
	 * �û��Ƿ��ע�����˺�,Y- ��ע,N-δ��ע,���ڹ��� �˺�����֧����Ч
	 */
	@XmlElement(name = "is_subscribe")
	@JSONField(name = "is_subscribe")
	private String isSubscribe;
	/**
	 * ��ƷID ����Ϊ����ID
	 */
	@XmlElement(name = "product_id")
	@JSONField(name = "product_id")
	private String productId;

	protected NativePayNotify() {
		// jaxb required
	}

	public String getProductId() {
		return productId;
	}

	public String getIsSubscribe() {
		return isSubscribe;
	}

	@Override
	public String toString() {
		return "NativePayNotify [productId=" + productId + ", isSubscribe="
				+ isSubscribe + ", " + super.toString() + "]";
	}
}
