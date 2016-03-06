package com.wk.wechat4j.base.api;

import com.wk.wechat4j.base.http.weixin.WeixinRequestExecutor;

import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * API����
 *
 * @className BaseApi
 * @author jy.hu
 * @date 2014��9��26��
 * @since JDK 1.6
 * @see <a href="http://mp.weixin.qq.com/wiki/index.php">΢�Ź���ƽ̨API�ĵ�</a>
 * @see <a href="http://qydev.weixin.qq.com/wiki/index.php">΢����ҵ��API�ĵ�</a>
 */
public abstract class BaseApi {

	protected final WeixinRequestExecutor weixinExecutor;

	protected abstract ResourceBundle weixinBundle();

	public BaseApi() {
		this.weixinExecutor = new WeixinRequestExecutor();
	}

	protected String getRequestUri(String key) {
		String url = weixinBundle().getString(key);
		Pattern p = Pattern.compile("(\\{[^\\}]*\\})");
		Matcher m = p.matcher(url);
		StringBuffer sb = new StringBuffer();
		String sub = null;
		while (m.find()) {
			sub = m.group();
			m.appendReplacement(sb,
					getRequestUri(sub.substring(1, sub.length() - 1)));
		}
		m.appendTail(sb);
		return sb.toString();
	}
}
