package com.wk.wechat4j.base.http.weixin;

import com.alibaba.fastjson.annotation.JSONField;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * ���ýӿڷ���xml��ʽ
 *
 * @className XmlResult
 * @author jy
 * @date 2014��11��1��
 * @since JDK 1.6
 * @see
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class XmlResult implements Serializable {

	private static final long serialVersionUID = -6185313616955051150L;

	/**
	 * ���ֶ���ͨ�ű�ʶ,�ǽ��� ��ʶ,�����Ƿ�ɹ���Ҫ�� �� result_code ���жϷǿ�
	 */
	@XmlElement(name = "return_code")
	@JSONField(name = "return_code")
	private String returnCode;
	/**
	 * ������Ϣ,��� ��,Ϊ����ԭ�� ����Ϊ��
	 */
	@XmlElement(name = "return_msg")
	@JSONField(name = "return_msg")
	private String returnMsg;
	/**
	 * ҵ����SUCCESS/FAIL �ǿ�
	 */
	@XmlElement(name = "result_code")
	@JSONField(name = "result_code")
	private String resultCode;
	/**
	 * ������� ��Ϊ��
	 */
	@XmlElement(name = "err_code")
	@JSONField(name = "err_code")
	private String errCode;
	/**
	 * �����Ϣ���� ��Ϊ��
	 */
	@XmlElement(name = "err_code_des")
	@JSONField(name = "err_code_des")
	private String errCodeDes;

	public XmlResult() {
	}

	public XmlResult(String returnCode, String returnMsg) {
		this.returnCode = returnCode;
		this.returnMsg = returnMsg;
	}

	public String getReturnCode() {
		return returnCode;
	}

	public String getReturnMsg() {
		return returnMsg;
	}

	public String getResultCode() {
		return resultCode;
	}

	public String getErrCode() {
		return errCode;
	}

	public String getErrCodeDes() {
		return errCodeDes;
	}

	public void setReturnCode(String returnCode) {
		this.returnCode = returnCode;
	}

	public void setReturnMsg(String returnMsg) {
		this.returnMsg = returnMsg;
	}

	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}

	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}

	public void setErrCodeDes(String errCodeDes) {
		this.errCodeDes = errCodeDes;
	}

	@Override
	public String toString() {
		return "returnCode=" + returnCode + ", returnMsg=" + returnMsg
				+ ", resultCode=" + resultCode + ", errCode=" + errCode
				+ ", errCodeDes=" + errCodeDes;
	}
}
