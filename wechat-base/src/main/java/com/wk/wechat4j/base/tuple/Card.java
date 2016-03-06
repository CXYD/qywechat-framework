package com.wk.wechat4j.base.tuple;

import com.alibaba.fastjson.annotation.JSONCreator;
import com.alibaba.fastjson.annotation.JSONField;

import javax.xml.bind.annotation.XmlElement;

/**
 * ��ȯ����
 * <p>
 * <font color="red">�����ڡ�Ⱥ����Ϣ��</font>
 * </p>
 *
 * @className Card
 * @author jy
 * @date 2015��6��8��
 * @since JDK 1.6
 * @see
 */
public class Card implements MassTuple {

	private static final long serialVersionUID = 6119453633595102147L;

	@Override
	public String getMessageType() {
		return "wxcard";
	}

	/**
	 * �ϴ����΢�ŷ��ص�ý��ID
	 */
	@JSONField(name = "card_id")
	@XmlElement(name = "CardId")
	private String cardId;

	@JSONCreator
	public Card(@JSONField(name = "cardId") String cardId) {
		this.cardId = cardId;
	}

	public String getCardId() {
		return cardId;
	}

	@Override
	public String toString() {
		return "Card [cardId=" + cardId + "]";
	}
}
