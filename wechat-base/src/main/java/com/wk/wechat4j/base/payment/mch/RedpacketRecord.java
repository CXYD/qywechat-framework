package com.wk.wechat4j.base.payment.mch;

import com.alibaba.fastjson.annotation.JSONField;
import com.wk.wechat4j.base.http.weixin.XmlResult;
import com.wk.wechat4j.base.type.RedpacketSendType;
import com.wk.wechat4j.base.type.RedpacketStatus;
import com.wk.wechat4j.base.type.RedpacketType;
import com.wk.wechat4j.base.util.DateUtil;

import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * �����¼
 *
 * @className RedpacketRecord
 * @author jy
 * @date 2015��6��4��
 * @since JDK 1.6
 * @see
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class RedpacketRecord extends XmlResult {

	private static final long serialVersionUID = 929959747323918458L;

	/**
	 * �̻������ţ�ÿ�������ű���Ψһ�� ��ɣ� mch_id+yyyymmdd+10λһ���ڲ����ظ������֡�
	 */
	@XmlElement(name = "mch_billno")
	@JSONField(name = "mch_billno")
	private String outTradeNo;
	/**
	 * ΢��֧��������̻���
	 */
	@XmlElement(name = "mch_id")
	@JSONField(name = "mch_id")
	private String mchId;
	/**
	 * �������
	 */
	@XmlElement(name = "detail_id")
	@JSONField(name = "detail_id")
	private String repacketId;
	/**
	 * ���״̬
	 */
	@XmlElement(name = "status")
	private String status;
	/**
	 * ��������
	 */
	@XmlElement(name = "send_type")
	@JSONField(name = "send_type")
	private String sendType;
	/**
	 * �������
	 */
	@XmlElement(name = "hb_type")
	@JSONField(name = "hb_type")
	private String hbType;
	/**
	 * �������
	 */
	@XmlElement(name = "total_num")
	@JSONField(name = "total_num")
	private int totalNum;
	/**
	 * ����ܽ���λ�֣�
	 */
	@XmlElement(name = "total_amount")
	@JSONField(name = "total_amount")
	private int totalAmount;
	/**
	 * ����ʧ��ԭ��
	 */
	@XmlElement(name = "reason")
	private String reason;
	/**
	 * ����ʱ��
	 */
	@XmlElement(name = "send_time")
	@JSONField(name = "send_time")
	private String sendTime;
	/**
	 * ����˿�ʱ��
	 */
	@XmlElement(name = "refund_time")
	@JSONField(name = "refund_time")
	private String refundTime;
	/**
	 * ����˿���
	 */
	@XmlElement(name = "refund_amount")
	@JSONField(name = "refund_amount")
	private Integer refundAmount;
	/**
	 * ף����
	 */
	@XmlElement(name = "wishing")
	private String wishing;
	/**
	 * �����
	 */
	@XmlElement(name = "remark")
	private String remark;
	/**
	 * �����
	 */
	@XmlElement(name = "act_name")
	@JSONField(name = "act_name")
	private String actName;
	/**
	 * �ѱ�����ȡ�б�
	 */
	@XmlElement(name = "hbinfo")
	@XmlElementWrapper(name = "hblist")
	@JSONField(name = "hblist")
	private List<RedpacketReceiver> receivers;

	public String getOutTradeNo() {
		return outTradeNo;
	}

	public String getMchId() {
		return mchId;
	}

	public String getRepacketId() {
		return repacketId;
	}

	@JSONField(serialize = false)
	public RedpacketStatus getFormatStatus() {
		return status != null ? RedpacketStatus.valueOf(status.toUpperCase())
				: null;
	}

	@JSONField(serialize = false)
	public RedpacketSendType getFormatSendType() {
		return sendType != null ? RedpacketSendType.valueOf(sendType) : null;
	}

	@JSONField(serialize = false)
	public RedpacketType getFomatHbType() {
		return hbType != null ? RedpacketType.valueOf(hbType) : null;
	}

	public String getStatus() {
		return status;
	}

	public String getSendType() {
		return sendType;
	}

	public String getHbType() {
		return hbType;
	}

	public int getTotalNum() {
		return totalNum;
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

	public String getReason() {
		return reason;
	}

	public String getSendTime() {
		return sendTime;
	}

	@JSONField(serialize = false)
	public Date getFormatSendTime() {
		return sendTime != null ? DateUtil.parse2yyyyMMddHHmmss(sendTime)
				: null;
	}

	public String getRefundTime() {
		return refundTime;
	}

	@JSONField(serialize = false)
	public Date getFormatRefundTime() {
		return refundTime != null ? DateUtil.parse2yyyyMMddHHmmss(refundTime)
				: null;
	}

	public Integer getRefundAmount() {
		return refundAmount;
	}

	/**
	 * <font color="red">���ýӿڻ�ȡ��λΪ��,get����ת��ΪԪ����ʹ��</font>
	 *
	 * @return Ԫ��λ
	 */
	@JSONField(serialize = false)
	public double getFormatRefundAmount() {
		return refundAmount != null ? refundAmount.intValue() / 100d : 0d;
	}

	public String getWishing() {
		return wishing;
	}

	public String getRemark() {
		return remark;
	}

	public String getActName() {
		return actName;
	}

	public List<RedpacketReceiver> getReceivers() {
		return receivers;
	}

	@XmlRootElement
	@XmlAccessorType(XmlAccessType.FIELD)
	public static class RedpacketReceiver implements Serializable {

		private static final long serialVersionUID = 1L;

		/**
		 * ��ȡ�����Openid
		 */
		@XmlElement(name = "openid")
		private String openid;
		/**
		 * ��ȡ״̬
		 */
		@XmlElement(name = "status")
		private RedpacketStatus status;
		/**
		 * ��ȡ���
		 */
		private int amount;
		/**
		 * ��ȡʱ��
		 */
		@XmlElement(name = "rcv_time")
		@JSONField(name = "rcv_time")
		private String receiveTime;

		public String getOpenid() {
			return openid;
		}

		public RedpacketStatus getStatus() {
			return status;
		}

		public int getAmount() {
			return amount;
		}

		public String getReceiveTime() {
			return receiveTime;
		}

		/**
		 * <font color="red">���ýӿڻ�ȡ��λΪ��,get����ת��ΪԪ����ʹ��</font>
		 *
		 * @return Ԫ��λ
		 */
		@JSONField(serialize = false)
		public double getFormatAmount() {
			return amount / 100d;
		}

		@JSONField(serialize = false)
		public Date getFormatReceiveTime() {
			return receiveTime != null ? DateUtil
					.parse2yyyyMMddHHmmss(receiveTime) : null;
		}

		@Override
		public String toString() {
			return "RedpacketReceiver [openid=" + openid + ", status=" + status
					+ ", amount=" + getFormatAmount() + ", receiveTime="
					+ receiveTime + "]";
		}
	}

	@Override
	public String toString() {
		return "RedpacketRecord [outTradeNo=" + outTradeNo + ", mchId=" + mchId
				+ ", repacketId=" + repacketId + ", status=" + status
				+ ", sendType=" + sendType + ", hbType=" + hbType
				+ ", totalNum=" + totalNum + ", totalAmount="
				+ getFormatTotalAmount() + ", reason=" + reason + ", sendTime="
				+ sendTime + ", refundTime=" + refundTime + ", refundAmount="
				+ getFormatRefundAmount() + ", wishing=" + wishing
				+ ", remark=" + remark + ", actName=" + actName
				+ ", receivers=" + receivers + "]";
	}
}
