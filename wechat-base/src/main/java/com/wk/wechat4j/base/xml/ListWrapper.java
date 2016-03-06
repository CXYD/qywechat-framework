package com.wk.wechat4j.base.xml;

import javax.xml.bind.annotation.XmlAnyElement;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ListWrapper<T> implements Serializable {

	private static final long serialVersionUID = 7550802632983954221L;

	private List<T> items;

	public ListWrapper() {
		items = new ArrayList<T>();
	}

	@XmlAnyElement(lax = true)
	public List<T> getItems() {
		return items;
	}
}
