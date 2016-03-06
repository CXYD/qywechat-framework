package com.wk.wechat4j.qy.model;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.annotation.JSONField;
import com.wk.wechat4j.base.http.weixin.JsonResult;
import com.wk.wechat4j.qy.type.BatchStatus;
import com.wk.wechat4j.qy.type.BatchType;

/**
 * �첽����ִ�н��
 *
 * @className BatchResult
 * @author jy
 * @date 2015��3��31��
 * @since JDK 1.6
 * @see
 */
public class BatchResult extends JsonResult {

	private static final long serialVersionUID = 4985338631992208903L;
	/**
	 * ����״̬
	 */
	private int status;
	/**
	 * ��������
	 */
	private String type;
	/**
	 * ��������������
	 */
	private int total;
	/**
	 * Ŀǰ���аٷֱȣ����������ʱΪ100
	 */
	@JSONField(name = "percentage")
	private int percentAge;
	/**
	 * Ԥ��ʣ��ʱ�䣨��λ�����ӣ������������ʱΪ0
	 */
	@JSONField(name = "remaintime")
	private int remainTime;
	/**
	 * ��ϸ�Ĵ����� TODO
	 */
	private JSONArray result;

	public int getStatus() {
		return status;
	}

	@JSONField(serialize = false)
	public BatchStatus getFormatStatus() {
		return BatchStatus.values()[status - 1];
	}

	public String getType() {
		return type;
	}

	@JSONField(serialize = false)
	public BatchType getFormatType() {
		return type != null ? BatchType.valueOf(type.toUpperCase()) : null;
	}

	public int getTotal() {
		return total;
	}

	public int getPercentAge() {
		return percentAge;
	}

	public int getRemainTime() {
		return remainTime;
	}

	public JSONArray getResult() {
		return result;
	}

	// ---------- setter Ӧ��ȫ��ȥ��

	public void setStatus(int status) {
		this.status = status;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public void setPercentAge(int percentAge) {
		this.percentAge = percentAge;
	}

	public void setRemainTime(int remainTime) {
		this.remainTime = remainTime;
	}

	public void setResult(JSONArray result) {
		this.result = result;
	}

	@Override
	public String toString() {
		return "BatchResult [status=" + status + ", type=" + type + ", total="
				+ total + ", percentAge=" + percentAge + ", remainTime="
				+ remainTime + ", result=" + result + "]";
	}
}
