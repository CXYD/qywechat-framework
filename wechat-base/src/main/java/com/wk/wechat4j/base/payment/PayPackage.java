package com.wk.wechat4j.base.payment;

import com.alibaba.fastjson.annotation.JSONField;
import com.wk.wechat4j.base.util.DateUtil;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Date;

/**
 * ������Ϣ
 *
 * @className PayPackage
 * @author jy
 * @date 2014��12��18��
 * @since JDK 1.6
 * @see
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class PayPackage implements Serializable {

	private static final long serialVersionUID = 3450161267802545790L;

	/**
	 * ��Ʒ���� ����
	 */
	private String body;
	/**
	 * ��Ʒ���� �Ǳ���
	 */
	private String detail;
	/**
	 * �̻�ϵͳ�ڲ��Ķ����� ,32 ���ַ��� ���ɰ�����ĸ ,ȷ�� ���̻�ϵͳΨһ ����
	 */
	@XmlElement(name = "out_trade_no")
	@JSONField(name = "out_trade_no")
	private String outTradeNo;
	/**
	 * �����ܽ��,��λΪ��,���ܴ�С���� ����
	 */
	@XmlElement(name = "total_fee")
	@JSONField(name = "total_fee")
	private String totalFee;
	/**
	 * ֪ͨ��ַ����΢��֧���ɹ�֪ͨ ����
	 */
	@XmlElement(name = "notify_url")
	@JSONField(name = "notify_url")
	private String notifyUrl;
	/**
	 * �������ɵĻ��� IP ����
	 */
	@XmlElement(name = "spbill_create_ip")
	@JSONField(name = "spbill_create_ip")
	private String createIp;
	/**
	 * ��������,ԭ������ �Ǳ���
	 */
	private String attach;
	/**
	 * ��������ʱ��,��ʽΪ yyyyMMddHHmmss,�� 2009 �� 12��25��9��10��10���ʾΪ 20091225091010��ʱ�� Ϊ
	 * GMT+8 beijing����ʱ��ȡ ���̻������� �Ǳ���
	 */
	@XmlElement(name = "time_start")
	@JSONField(name = "time_start")
	private String timeStart;
	/**
	 * ����ʧЧʱ��,��Ϊ yyyyMMddHHmmss,�� 2009 �� 12��27��9��10��10���ʾΪ 20091227091010��ʱ�� Ϊ
	 * GMT+8 beijing����ʱ��ȡ ���̻�������Ʒ��� �Ǳ���
	 */
	@XmlElement(name = "time_expire")
	@JSONField(name = "time_expire")
	private String timeExpire;
	/**
	 * ��Ʒ���,���ֶβ��������,��ʹ������� �Ǳ���
	 */
	@XmlElement(name = "goods_tag")
	@JSONField(name = "goods_tag")
	private String goodsTag;

	protected PayPackage() {
		// jaxb required
	}

	/**
	 * ��������
	 *
	 * @param body
	 *            ��������
	 * @param outTradeNo
	 *            �̻��ڲ�ID
	 * @param totalFee
	 *            �����ܶ� <font color="red">��λΪԪ</font>
	 * @param notifyUrl
	 *            �ص���ַ
	 * @param createIp
	 *            ���ɶ������ݵĻ���IP
	 * @param attach
	 *            ��������
	 * @param timeStart
	 *            ��������ʱ��
	 * @param timeExpire
	 *            ����ʧЧʱ��
	 * @param goodsTag
	 *            �������
	 */
	public PayPackage(String body, String outTradeNo, double totalFee,
			String notifyUrl, String createIp, String attach, Date timeStart,
			Date timeExpire, String goodsTag) {
		this.body = body;
		this.outTradeNo = outTradeNo;
		this.totalFee = DateUtil.formaFee2Fen(totalFee);
		this.notifyUrl = notifyUrl;
		this.createIp = createIp;
		this.attach = attach;
		this.timeStart = timeStart != null ? DateUtil
				.fortmat2yyyyMMddHHmmss(timeStart) : null;
		this.timeExpire = timeExpire != null ? DateUtil
				.fortmat2yyyyMMddHHmmss(timeExpire) : null;
		this.goodsTag = goodsTag;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getOutTradeNo() {
		return outTradeNo;
	}

	public void setOutTradeNo(String outTradeNo) {
		this.outTradeNo = outTradeNo;
	}

	public String getTotalFee() {
		return totalFee;
	}

	/**
	 * <font color="red">��λΪԪ,�Զ���ʽ��Ϊ��</font>
	 *
	 * @param totalFee
	 *            �����ܶ� ��λΪԪ
	 */
	public void setTotalFee(double totalFee) {
		this.totalFee = DateUtil.formaFee2Fen(totalFee);
	}

	public String getNotifyUrl() {
		return notifyUrl;
	}

	public void setNotifyUrl(String notifyUrl) {
		this.notifyUrl = notifyUrl;
	}

	public String getCreateIp() {
		return createIp;
	}

	public void setCreateIp(String createIp) {
		this.createIp = createIp;
	}

	public String getAttach() {
		return attach;
	}

	public void setAttach(String attach) {
		this.attach = attach;
	}

	public String getTimeStart() {
		return timeStart;
	}

	public void setTimeStart(String timeStart) {
		this.timeStart = timeStart;
	}

	public void setTimeExpire(String timeExpire) {
		this.timeExpire = timeExpire;
	}

	public void setTimeStart(Date timeStart) {
		this.timeStart = timeStart != null ? DateUtil
				.fortmat2yyyyMMddHHmmss(timeStart) : null;
	}

	public String getTimeExpire() {
		return timeExpire;
	}

	public void setTimeExpire(Date timeExpire) {
		this.timeExpire = timeExpire != null ? DateUtil
				.fortmat2yyyyMMddHHmmss(timeExpire) : null;
	}

	public String getGoodsTag() {
		return goodsTag;
	}

	public void setGoodsTag(String goodsTag) {
		this.goodsTag = goodsTag;
	}

	@Override
	public String toString() {
		return "PayPackage [body=" + body + ", detail=" + detail
				+ ", outTradeNo=" + outTradeNo + ", totalFee=" + totalFee
				+ ", notifyUrl=" + notifyUrl + ", createIp=" + createIp
				+ ", attach=" + attach + ", timeStart=" + timeStart
				+ ", timeExpire=" + timeExpire + ", goodsTag=" + goodsTag + "]";
	}
}
