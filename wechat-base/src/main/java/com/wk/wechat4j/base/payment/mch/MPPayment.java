package com.wk.wechat4j.base.payment.mch;

import com.alibaba.fastjson.annotation.JSONField;
import com.wk.wechat4j.base.type.MPPaymentCheckNameType;
import com.wk.wechat4j.base.util.DateUtil;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * ��ҵ����
 *
 * @className MPPayment
 * @author jy
 * @date 2015��4��1��
 * @since JDK 1.6
 * @see
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class MPPayment implements Serializable {

	private static final long serialVersionUID = 3734639674346425312L;
	/**
	 * �̻�������
	 */
	@XmlElement(name = "partner_trade_no")
	@JSONField(name = "partner_trade_no")
	private String outTradeNo;
	/**
	 * ���պ�����û���openid
	 */
	@JSONField(name = "openid")
	@XmlElement(name = "openid")
	private String openId;
	/**
	 * У���û�����ѡ��
	 *
	 * @see com.wk.wechat4j.mp.type.MPPaymentCheckNameType
	 */
	@XmlElement(name = "check_name")
	@JSONField(name = "check_name")
	private MPPaymentCheckNameType checkNameType;
	/**
	 * �տ��û���ʵ������ ���check_name����ΪFORCE_CHECK��OPTION_CHECK��������û���ʵ���� ��ѡ
	 */
	@XmlElement(name = "re_user_name")
	@JSONField(name = "re_user_name")
	private String userName;
	/**
	 * ��ҵ����������Ϣ
	 */
	private String desc;
	/**
	 * ������
	 */
	private String amount;
	/**
	 * ���ýӿڵĻ���Ip��ַ
	 */
	@XmlElement(name = "spbill_create_ip")
	@JSONField(name = "spbill_create_ip")
	private String clientIp;

	protected MPPayment() {
		// jaxb required
	}

	/**
	 * ��ҵ����
	 * @param outTradeNo �̻��Ķ�����
	 * @param openId �û���openid
	 * @param checkNameType У���û�����ѡ��
	 * @param desc ����
	 * @param amount ���
	 * @param clientIp ���ýӿ�IP
	 */
	public MPPayment(String outTradeNo, String openId,
			MPPaymentCheckNameType checkNameType, String desc, double amount,
			String clientIp) {
		this.outTradeNo = outTradeNo;
		this.openId = openId;
		this.checkNameType = checkNameType;
		this.desc = desc;
		this.amount = DateUtil.formaFee2Fen(amount);
		this.clientIp = clientIp;
	}

	public String getOutTradeNo() {
		return outTradeNo;
	}

	public String getOpenId() {
		return openId;
	}

	public MPPaymentCheckNameType getCheckNameType() {
		return checkNameType;
	}

	public String getUserName() {
		return userName;
	}

	public String getDesc() {
		return desc;
	}

	public String getAmount() {
		return amount;
	}

	public String getClientIp() {
		return clientIp;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Override
	public String toString() {
		return "MPPayment [outTradeNo=" + outTradeNo + ", openId=" + openId
				+ ", checkNameType=" + checkNameType + ", userName=" + userName
				+ ", desc=" + desc + ", amount=" + amount + ", clientIp="
				+ clientIp + "]";
	}
}
