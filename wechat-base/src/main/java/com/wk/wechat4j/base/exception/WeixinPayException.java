package com.wk.wechat4j.base.exception;

/**
 * ����΢��֧���׳����쳣
 *
 * @className WeixinPayException
 * @author jy
 * @date 2014��10��28��
 * @since JDK 1.6
 * @see
 */
public class WeixinPayException extends WeixinException {
	private static final long serialVersionUID = 7148145661883468514L;

	public WeixinPayException(String errorMsg) {
		super(errorMsg);
	}

	public WeixinPayException(String errorCode, String errorMsg) {
		super(errorCode, errorMsg);
	}
}
