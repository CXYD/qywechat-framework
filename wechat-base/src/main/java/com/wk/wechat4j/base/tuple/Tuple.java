package com.wk.wechat4j.base.tuple;

import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;

/**
 * 消息元件
 *
 * @className Tuple
 * @author jy
 * @date 2015年4月19日
 * @since JDK 1.6
 * @see
 */
public interface Tuple extends Serializable {

	/**
	 * 消息类型
	 * 
	 * @return
	 */
	@XmlTransient
	public String getMessageType();
}
