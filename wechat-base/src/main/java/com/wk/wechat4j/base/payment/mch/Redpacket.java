package com.wk.wechat4j.base.payment.mch;

import com.alibaba.fastjson.annotation.JSONField;
import com.wk.wechat4j.base.util.DateUtil;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * ���
 *
 * @className Redpacket
 * @author jy
 * @date 2015��3��28��
 * @since JDK 1.6
 * @see <a
 *      href="http://pay.weixin.qq.com/wiki/doc/api/cash_coupon.php?chapter=13_1">��ͨ���</a>
 * @see <a
 *      href="https://pay.weixin.qq.com/wiki/doc/api/cash_coupon.php?chapter=16_1">�ѱ���</a>
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Redpacket implements Serializable {

	private static final long serialVersionUID = -7021352305575714281L;
	/**
	 * �̻������ţ�ÿ�������ű���Ψһ�� ��ɣ� mch_id+yyyymmdd+10λһ���ڲ����ظ������֡�
	 */
	@XmlElement(name = "mch_billno")
	@JSONField(name = "mch_billno")
	private String outTradeNo;
	/**
	 * ������������� ����
	 */
	@XmlElement(name = "send_name")
	@JSONField(name = "send_name")
	private String sendName;
	/**
	 * ���պ�����û���openid
	 */
	@XmlElement(name = "re_openid")
	@JSONField(name = "re_openid")
	private String openid;
	/**
	 * �������λ��
	 */
	@XmlElement(name = "total_amount")
	@JSONField(name = "total_amount")
	private String totalAmount;
	/**
	 * �������������
	 */
	@XmlElement(name = "total_num")
	@JSONField(name = "total_num")
	private int totalNum;
	/**
	 * ���������÷�ʽ(�ѱ���) ALL_RAND��ȫ�����,�̻�ָ���ܽ��ͺ����������������΢��֧������������������
	 */
	@XmlElement(name = "amt_type")
	@JSONField(name = "amt_type")
	private String amtType;
	/**
	 * ���ף����
	 */
	private String wishing;
	/**
	 * ip��ַ
	 */
	@XmlElement(name = "client_ip")
	@JSONField(name = "client_ip")
	private String clientIp;
	/**
	 * �����
	 */
	@XmlElement(name = "act_name")
	@JSONField(name = "act_name")
	private String actName;
	/**
	 * ��ע
	 */
	private String remark;

	protected Redpacket() {
		// jaxb required
	}

	/**
	 * ���
	 *
	 * @param outTradeNo
	 *            �̻���һ���ڲ����ظ��Ķ����� �ӿڸ����̻�������֧������ ����ֳ�ʱ���ٵ���
	 * @param sendName
	 *            �������������
	 * @param openid
	 *            �����պ�����û���openid
	 * @param totalAmount
	 *            ������ <font color="red">��λΪԪ,�Զ���ʽ��Ϊ��</font>
	 * @param totalNum
	 *            ������������� ����1��Ϊ�ѱ���
	 */
	public Redpacket(String outTradeNo, String sendName, String openid,
			double totalAmount, int totalNum) {
		this.outTradeNo = outTradeNo;
		this.sendName = sendName;
		this.openid = openid;
		this.totalAmount = DateUtil.formaFee2Fen(totalAmount);
		this.totalNum = totalNum;
		this.amtType = totalNum > 1 ? "ALL_RAND" : null;
	}

	public String getOutTradeNo() {
		return outTradeNo;
	}

	public String getSendName() {
		return sendName;
	}

	public String getOpenid() {
		return openid;
	}

	public String getTotalAmount() {
		return totalAmount;
	}

	public int getTotalNum() {
		return totalNum;
	}

	public String getWishing() {
		return wishing;
	}

	public void setWishing(String wishing) {
		this.wishing = wishing;
	}

	public String getAmtType() {
		return amtType;
	}

	public String getClientIp() {
		return clientIp;
	}

	public void setClientIp(String clientIp) {
		this.clientIp = clientIp;
	}

	public String getActName() {
		return actName;
	}

	public void setActName(String actName) {
		this.actName = actName;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	public String toString() {
		return "Redpacket [ sendName=" + sendName + ", openid=" + openid
				+ ", amtType=" + amtType + ", totalAmount=" + totalAmount
				+ ", totalNum=" + totalNum + ", wishing=" + wishing
				+ ", clientIp=" + clientIp + ", actName=" + actName
				+ ", remark=" + remark + "]";
	}
}
