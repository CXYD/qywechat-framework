package com.wk.wechat4j.qy.api;

import com.wk.wechat4j.base.api.BaseApi;

import java.util.ResourceBundle;


/**
 * ΢����ҵ��API
 *
 * @className QyApi
 * @author jy.hu
 * @date 2014��11��18��
 * @since JDK 1.6
 * @see com.wk.wechat4j.qy.api.BaseApi
 * @see <a href="http://qydev.weixin.qq.com/wiki/index.php">api�ĵ�</a>
 */
public class QyApi extends BaseApi {

	private final static ResourceBundle WEIXIN_BUNDLE;
	static {
		WEIXIN_BUNDLE = ResourceBundle
				.getBundle("com/foxinmy/weixin4j/qy/api/weixin");
	}

	@Override
	protected ResourceBundle weixinBundle() {
		return WEIXIN_BUNDLE;
	}
}
