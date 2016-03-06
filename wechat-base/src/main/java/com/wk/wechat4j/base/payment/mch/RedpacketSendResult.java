package com.wk.wechat4j.base.payment.mch;

import com.alibaba.fastjson.annotation.JSONField;
import com.wk.wechat4j.base.http.weixin.XmlResult;
import com.wk.wechat4j.base.util.DateUtil;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

/**
 * ���ͺ�����
 *
 * @className RedpacketSendResult
 * @author jy
 * @date 2015��4��1��
 * @since JDK 1.6
 * @see
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class RedpacketSendResult extends XmlResult {

	private static final long serialVersionUID = 5611847899634131711L;
	/**
	 * ΢�ŷ���Ĺ����˺�
	 */
	@XmlElement(name = "wxappid")
	@JSONField(name = "wxappid")
	private String appId;
	/**
	 * ΢��֧��������̻���
	 */
	@XmlElement(name = "mch_id")
	@JSONField(name = "mch_id")
	private String mchId;
	/**
	 * �̻������ţ�ÿ�������ű���Ψһ�� ��ɣ� mch_id+yyyymmdd+10λһ���ڲ����ظ������֡�
	 */
	@XmlElement(name = "mch_billno")
	@JSONField(name = "mch_billno")
	private String outTradeNo;
	/**
	 * ���պ�����û���openid
	 */
	@XmlElement(name = "re_openid")
	@JSONField(name = "re_openid")
	private String openId;
	/**
	 * ������ ��λΪ��
	 */
	@XmlElement(name = "total_amount")
	@JSONField(name = "total_amount")
	private int totalAmount;
	/**
	 * ���ųɹ�ʱ��
	 */
	@XmlElement(name = "send_time")
	@JSONField(name = "send_time")
	private String sendTime;
	/**
	 * ΢�ŵ���
	 */
	@XmlElement(name = "send_listid")
	@JSONField(name = "send_listid")
	private String sendListid;

	protected RedpacketSendResult() {
		// jaxb required
	}

	public String getAppId() {
		return appId;
	}

	public String getMchId() {
		return mchId;
	}

	public String getOutTradeNo() {
		return outTradeNo;
	}

	public String getOpenId() {
		return openId;
	}

	public int getTotalAmount() {
		return totalAmount;
	}

	/**
	 * <font color="red">���ýӿڻ�ȡ��λΪ��,get����ת��ΪԪ����ʹ��</font>
	 *
	 * @return Ԫ��λ
	 */
	@JSONField(serialize = false)
	public double getFormatTotalAmount() {
		return totalAmount / 100d;
	}

	public String getSendTime() {
		return sendTime;
	}

	@JSONField(serialize = false)
	public Date getFormatSendTime() {
		return DateUtil.parse2yyyyMMddHHmmss(sendTime);
	}

	public String getSendListid() {
		return sendListid;
	}

	@Override
	public String toString() {
		return "RedpacketSendResult [appId=" + appId + ", mchId=" + mchId
				+ ", outTradeNo=" + outTradeNo + ", openId=" + openId
				+ ", totalAmount=" + totalAmount + ", " + super.toString()
				+ "]";
	}
}
