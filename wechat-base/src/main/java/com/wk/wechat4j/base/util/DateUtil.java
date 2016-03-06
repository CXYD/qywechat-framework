package com.wk.wechat4j.base.util;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * ���ڹ�����
 *
 * @className DateUtil
 * @author jy
 * @date 2014��10��31��
 * @since JDK 1.6
 * @see
 */
public class DateUtil {
	private static final String yyyyMMdd = "yyyyMMdd";
	private static final String yyyy_MM_dd = "yyyy-MM-dd";
	private static final String yyyyMMddHHmmss = "yyyyMMddHHmmss";

	/**
	 * ���ڶ���ת��Ϊyyyymmdd���ַ�����ʽ
	 *
	 * @param date
	 *            ���ڶ���
	 * @return
	 */
	public static String fortmat2yyyyMMdd(Date date) {
		return new SimpleDateFormat(yyyyMMdd).format(date);
	}

	/**
	 * ���ڶ���ת��Ϊyyyy_mm_dd���ַ�����ʽ
	 *
	 * @param date
	 *            ���ڶ���
	 * @return
	 */
	public static String fortmat2yyyy_MM_dd(Date date) {
		return new SimpleDateFormat(yyyy_MM_dd).format(date);
	}

	/**
	 * ���ڶ���ת��Ϊyyyymmddhhmmss���ַ�����ʽ
	 *
	 * @param date
	 *            ���ڶ���
	 * @return
	 */
	public static String fortmat2yyyyMMddHHmmss(Date date) {
		return new SimpleDateFormat(yyyyMMddHHmmss).format(date);
	}

	/**
	 * yyyymmddhhmmss��ʽ���ַ���ת��Ϊ���ڶ���
	 *
	 * @param date
	 *            �����ַ���
	 * @return
	 */
	public static Date parse2yyyyMMddHHmmss(String date) {
		try {
			return new SimpleDateFormat(yyyyMMddHHmmss).parse(date);
		} catch (ParseException e) {
			;
		}
		return null;
	}

	/**
	 * ��λΪ�ֵĽ���ʽ��ΪԪ���ַ�����ʽ
	 *
	 * @param fee
	 *            ��� ��λΪ��
	 * @return
	 */
	public static String formaFee2Fen(double fee) {
		return new DecimalFormat("#").format(fee * 100);
	}

	/**
	 * ��ǰʱ���ת��Ϊ����ַ�����ʽ
	 * 
	 * @return
	 */
	public static String timestamp2string() {
		return String.valueOf(System.currentTimeMillis() / 1000);
	}
}
