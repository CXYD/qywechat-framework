package com.wk.wechat4j.base.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.wk.wechat4j.base.type.MediaType;

import java.io.Serializable;
import java.util.List;

/**
 * ý���زļ�¼
 *
 * @className MediaRecord
 * @author jy
 * @date 2015��3��22��
 * @since JDK 1.6
 * @see
 */
public class MediaRecord implements Serializable {

	private static final long serialVersionUID = 7017503153256241457L;

	/**
	 * �����͵��زĵ�����
	 */
	@JSONField(name = "total_count")
	private int totalCount;
	/**
	 * ���ε��û�ȡ���زĵ�����
	 */
	@JSONField(name = "item_count")
	private int itemCount;
	/**
	 * ý������
	 */
	@JSONField(serialize = false, deserialize = false)
	private MediaType mediaType;
	/**
	 * ý����Ϣ
	 */
	@JSONField(name = "item")
	private List<MediaItem> items;

	/**
	 * ��ҳ��Ϣ
	 */
	@JSONField(serialize = false, deserialize = false)
	private Pageable pageable;
	@JSONField(serialize = false, deserialize = false)
	private Pagedata<MediaItem> pagedata;

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getItemCount() {
		return itemCount;
	}

	public void setItemCount(int itemCount) {
		this.itemCount = itemCount;
	}

	public MediaType getMediaType() {
		return mediaType;
	}

	public void setMediaType(MediaType mediaType) {
		this.mediaType = mediaType;
	}

	public List<MediaItem> getItems() {
		return items;
	}

	public void setItems(List<MediaItem> items) {
		this.items = items;
	}

	public Pageable getPageable() {
		return pageable;
	}

	public void setPageable(Pageable pageable) {
		this.pageable = pageable;
	}

	public Pagedata<MediaItem> getPagedata() {
		if (pagedata == null) {
			pagedata = new Pagedata<MediaItem>(pageable, totalCount, items);
		}
		return pagedata;
	}

	@Override
	public String toString() {
		return "MediaRecord [totalCount=" + totalCount + ", itemCount="
				+ itemCount + ", mediaType=" + mediaType + ", items=" + items
				+ ", pageable=" + pageable + "]";
	}
}
