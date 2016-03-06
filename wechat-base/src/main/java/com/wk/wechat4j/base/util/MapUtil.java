package com.wk.wechat4j.base.util;

import com.alibaba.fastjson.JSONObject;
import com.wk.wechat4j.base.model.Consts;
import com.wk.wechat4j.base.xml.ListsuffixResultSerializer;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * ǩ��������
 *
 * @className MapUtil
 * @author jy
 * @date 2014��10��31��
 * @since JDK 1.6
 * @see
 */
public class MapUtil {
	/**
	 * �����ַ���
	 *
	 * @param object
	 *            ����
	 * @param encoder
	 *            �Ƿ����
	 * @param lowerCase
	 *            �Ƿ�ת��Сд
	 * @param extra
	 *            ���Ӷ���
	 * @return
	 */
	public static String toJoinString(Object object, boolean encoder,
			boolean lowerCase, Map<String, String> extra) {
		Map<String, String> map = new HashMap<String, String>();
		JSONObject obj = null;
		if (object instanceof String) {
			obj = JSONObject.parseObject(object.toString());
		} else {
			obj = ListsuffixResultSerializer.serializeToJSON(object);
		}
		for (String key : obj.keySet()) {
			map.put(key, obj.getString(key));
		}
		if (extra != null && !extra.isEmpty()) {
			map.putAll(extra);
		}
		return toJoinString(map, encoder, lowerCase);
	}

	/**
	 * �����ַ���
	 *
	 * @param map
	 *            ����
	 * @param encoder
	 *            �Ƿ����
	 * @param lowerCase
	 *            �Ƿ�ת��Сд
	 * @return
	 */
	public static String toJoinString(Map<String, String> map, boolean encoder,
			boolean lowerCase) {
		map.remove("sign");
		map = new TreeMap<String, String>(map);
		StringBuilder sb = new StringBuilder();
		Set<Map.Entry<String, String>> set = map.entrySet();
		try {
			if (encoder && lowerCase) {
				for (Map.Entry<String, String> entry : set) {
					if (StringUtil.isBlank(entry.getValue())) {
						continue;
					}
					sb.append(entry.getKey().toLowerCase())
							.append("=")
							.append(URLEncoder.encode(entry.getValue(),
									Consts.UTF_8.name())).append("&");
				}
			} else if (encoder) {
				for (Map.Entry<String, String> entry : set) {
					if (StringUtil.isBlank(entry.getValue())) {
						continue;
					}
					sb.append(entry.getKey())
							.append("=")
							.append(URLEncoder.encode(entry.getValue(),
									Consts.UTF_8.name())).append("&");
				}
			} else if (lowerCase) {
				for (Map.Entry<String, String> entry : set) {
					if (StringUtil.isBlank(entry.getValue())) {
						continue;
					}
					sb.append(entry.getKey().toLowerCase()).append("=")
							.append(entry.getValue()).append("&");
				}
			} else {
				for (Map.Entry<String, String> entry : set) {
					if (StringUtil.isBlank(entry.getValue())) {
						continue;
					}
					sb.append(entry.getKey()).append("=")
							.append(entry.getValue()).append("&");
				}
			}
		} catch (UnsupportedEncodingException e) {
			;
		}
		sb.deleteCharAt(sb.length() - 1);
		return sb.toString();
	}
}
