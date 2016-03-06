package com.wk.wechat4j.base.http;


import com.wk.wechat4j.base.model.Consts;
import com.wk.wechat4j.base.util.NameValue;
import com.wk.wechat4j.base.util.URLEncodingUtil;

/**
 * 键值对参数
 *
 * @className UrlParameter
 * @author jy
 * @date 2015年5月29日
 * @since JDK 1.6
 * @see
 */
public class URLParameter extends NameValue {

	private static final long serialVersionUID = -115491642760990655L;

	public URLParameter(String name, String value) {
		super(name, value);
	}

	public String encoding() {
		return String.format("%s=%s",
				URLEncodingUtil.encoding(getName(), Consts.UTF_8, true),
				URLEncodingUtil.encoding(getValue(), Consts.UTF_8, true));
	}

	@Override
	public String toString() {
		return String.format("[URLParameter name=%s, value=%s]", getName(),
				getValue());
	}
}
