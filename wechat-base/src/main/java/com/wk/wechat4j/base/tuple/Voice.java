package com.wk.wechat4j.base.tuple;

import com.alibaba.fastjson.annotation.JSONCreator;
import com.alibaba.fastjson.annotation.JSONField;

/**
 * ��������
 * <p>
 * <font color="red">�����ڡ��ͷ���Ϣ����Ⱥ����Ϣ��</font>
 * </p>
 *
 * @className Voice
 * @author jy
 * @date 2014��9��29��
 * @since JDK 1.6
 * @see
 */
public class Voice extends Image implements NotifyTuple {

	private static final long serialVersionUID = 8853054484809101524L;

	@Override
	public String getMessageType() {
		return "voice";
	}

	@JSONCreator
	public Voice(@JSONField(name = "mediaId") String mediaId) {
		super(mediaId);
	}
}
