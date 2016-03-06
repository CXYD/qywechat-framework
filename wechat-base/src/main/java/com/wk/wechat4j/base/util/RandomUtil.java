package com.wk.wechat4j.base.util;

import java.util.Random;
import java.util.UUID;

/**
 * ����빤����
 *
 * @className RandomUtil
 * @author jy
 * @date 2014��10��22��
 * @since JDK 1.6
 * @see
 */
public class RandomUtil {

	private static final String ALLCHAR = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private static final String LETTERCHAR = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private static final String NUMBERCHAR = "0123456789";

	/**
	 * ����һ������������ַ���(�������ֺʹ�Сд��ĸ)
	 *
	 * @param length
	 *            ������ĳ���
	 * @return
	 */
	public static String generateString(int length) {
		StringBuilder sb = new StringBuilder(length);
		Random random = new Random();
		for (int i = 0; i < length; i++) {
			sb.append(ALLCHAR.charAt(random.nextInt(ALLCHAR.length())));
		}
		return sb.toString();
	}

	/**
	 * ����һ������������������ַ���(ֻ��������)
	 *
	 * @param length
	 *            ������ĳ���
	 * @return
	 */
	public static String generateStringByNumberChar(int length) {
		StringBuilder sb = new StringBuilder(length);
		Random random = new Random();
		for (int i = 0; i < length; i++) {
			sb.append(NUMBERCHAR.charAt(random.nextInt(NUMBERCHAR.length())));
		}
		return sb.toString();
	}

	/**
	 * ����һ���������������ĸ�ַ���(ֻ������Сд��ĸ)
	 *
	 * @param length
	 *            ������ĳ���
	 * @return
	 */
	public static String generateStringByLetterCharr(int length) {
		StringBuilder sb = new StringBuilder(length);
		Random random = new Random();
		for (int i = 0; i < length; i++) {
			sb.append(LETTERCHAR.charAt(random.nextInt(LETTERCHAR.length())));
		}
		return sb.toString();
	}

	/**
	 * ����һ���������������д��ĸ�ַ���(ֻ������Сд��ĸ)
	 *
	 * @param length
	 *            ������ĳ���
	 * @return
	 */
	public static String generateLowerString(int length) {
		return generateStringByLetterCharr(length).toLowerCase();
	}

	/**
	 * ����һ�������������Сд��ĸ�ַ���(ֻ������Сд��ĸ)
	 *
	 * @param length
	 *            ������ĳ���
	 * @return
	 */
	public static String generateUpperString(int length) {
		return generateStringByLetterCharr(length).toUpperCase();
	}

	/**
	 * �����ȡUUID�ַ���(���л���)
	 *
	 * @return UUID�ַ���
	 */
	public static String getUUID() {
		String uuid = UUID.randomUUID().toString();
		return uuid.substring(0, 8) + uuid.substring(9, 13)
				+ uuid.substring(14, 18) + uuid.substring(19, 23)
				+ uuid.substring(24);
	}

	public static void main(String[] args) {
		System.out.println(System.nanoTime());
		System.out.println(System.currentTimeMillis());
	}
}
