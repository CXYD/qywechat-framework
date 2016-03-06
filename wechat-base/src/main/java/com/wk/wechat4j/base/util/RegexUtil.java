package com.wk.wechat4j.base.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * ������ʽ������
 *
 * @className RegexUtil
 * @author jy
 * @date 2015��12��8��
 * @since JDK 1.7
 * @see
 */
public final class RegexUtil {
	/**
	 * Content-disposition �е� filename��ȡ����
	 */
	private static final Pattern FILENAME_RGX = Pattern
			.compile("(?<=filename=\").*?(?=\")");

	/**
	 * �� Content-disposition��ȡ�ļ���
	 * 
	 * @param contentDisposition
	 * @return
	 */
	public static String regexFileNameFromContentDispositionHeader(
			String contentDisposition) {
		if (StringUtil.isBlank(contentDisposition)) {
			return null;
		}
		Matcher filenameMatcher = FILENAME_RGX.matcher(contentDisposition);
		return filenameMatcher.find() ? filenameMatcher.group() : null;
	}
}
