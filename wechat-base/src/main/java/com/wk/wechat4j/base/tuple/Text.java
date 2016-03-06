package com.wk.wechat4j.base.tuple;

import com.alibaba.fastjson.annotation.JSONCreator;
import com.alibaba.fastjson.annotation.JSONField;

/**
 * �ı�����
 * <p>
 * <font color="red">�����ڡ��ͷ���Ϣ����Ⱥ����Ϣ������ҵ�ŵġ�������Ϣ��</font>
 * </p>
 *
 * @className Text
 * @author jy
 * @date 2014��9��29��
 * @since JDK 1.6
 * @see
 */
public class Text implements MassTuple, NotifyTuple, ChatTuple {

	private static final long serialVersionUID = 520050144519064503L;

	@Override
	public String getMessageType() {
		return "text";
	}

	/**
	 * ����
	 */
	private String content;

	@JSONCreator
	public Text(@JSONField(name = "content") String content) {
		this.content = content;
	}

	public String getContent() {
		return content;
	}

	@Override
	public String toString() {
		return "Text [content=" + content + "]";
	}
}
