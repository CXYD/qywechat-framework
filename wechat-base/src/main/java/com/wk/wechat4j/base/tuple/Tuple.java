package com.wk.wechat4j.base.tuple;

import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;

/**
 * ��ϢԪ��
 *
 * @className Tuple
 * @author jy
 * @date 2015��4��19��
 * @since JDK 1.6
 * @see
 */
public interface Tuple extends Serializable {

	/**
	 * ��Ϣ����
	 * 
	 * @return
	 */
	@XmlTransient
	public String getMessageType();
}
