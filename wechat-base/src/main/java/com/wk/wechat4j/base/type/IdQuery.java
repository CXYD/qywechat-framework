package com.wk.wechat4j.base.type;

import java.io.Serializable;

/**
 * ID��ѯ
 *
 * @className IdQuery
 * @author jy
 * @date 2014��11��1��
 * @since JDK 1.6
 * @see
 */
public class IdQuery implements Serializable {

	private static final long serialVersionUID = -5273675987521807370L;
	/**
	 * idֵ
	 */
	private String id;
	/**
	 * id����
	 * 
	 * @see com.foxinmy.weixin4j.type.IdType
	 */
	private IdType type;

	public IdQuery(String id, IdType idType) {
		this.id = id;
		this.type = idType;
	}

	public String getId() {
		return id;
	}

	public IdType getType() {
		return type;
	}

	@Override
	public String toString() {
		return String.format("%s=%s", type.getName(), id);
	}
}
