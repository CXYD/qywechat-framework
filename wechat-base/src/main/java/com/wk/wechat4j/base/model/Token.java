package com.wk.wechat4j.base.model;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;

/**
 * access_token�ǹ��ںŵ�ȫ��ΨһƱ��,���ںŵ��ø��ӿ�ʱ����ʹ��access_token,���������access_token��Ч��Ϊ7200��,
 * �ظ���ȡ�������ϴλ�ȡ��access_tokenʧЧ
 *
 * @className Token
 * @author jy.hu
 * @date 2014��4��5��
 * @since JDK 1.6
 * @see <a
 *      href="http://mp.weixin.qq.com/wiki/11/0e4b294685f817b95cbed85ba5e82b8f.html">΢�Ź���ƽ̨��ȡtoken</a>
 * @see <a
 *      href="http://qydev.weixin.qq.com/wiki/index.php?title=%E4%B8%BB%E5%8A%A8%E8%B0%83%E7%94%A8">΢����ҵ�ŵ�����ģʽ</a>
 */
public class Token implements Serializable {

	private static final long serialVersionUID = -7564855472419104084L;

	/**
	 * ��ȡ����ƾ֤
	 */
	@JSONField(name = "access_token")
	private String accessToken;
	/**
	 * ƾ֤��Чʱ�䣬��λ����
	 */
	@JSONField(name = "expires_in")
	private int expiresIn;
	/**
	 * token������ʱ��
	 */
	private long createTime;
	/**
	 * ���󷵻ص�ԭʼ���
	 */
	private String originalResult;

	public Token() {
		this.createTime = System.currentTimeMillis() / 1000l;
	}

	public Token(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public int getExpiresIn() {
		return expiresIn;
	}

	public void setExpiresIn(int expiresIn) {
		this.expiresIn = expiresIn;
	}

	public long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}

	public String getOriginalResult() {
		return originalResult;
	}

	public void setOriginalResult(String originalResult) {
		this.originalResult = originalResult;
	}

	@Override
	public String toString() {
		return "Token [accessToken=" + accessToken + ", expiresIn=" + expiresIn
				+ ", createTime=" + createTime + "]";
	}
}
