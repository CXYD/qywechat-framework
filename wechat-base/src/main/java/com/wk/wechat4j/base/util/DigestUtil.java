package com.wk.wechat4j.base.util;

import com.wk.wechat4j.base.model.Consts;
import com.wk.wechat4j.base.payment.mch.NativePayNotify;
import com.wk.wechat4j.base.xml.XmlStream;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;


/**
 * ǩ��������
 *
 * @className DigestUtil
 * @author jy
 * @date 2015��5��6��
 * @since JDK 1.6
 * @see
 */
public final class DigestUtil {

	private static MessageDigest getDigest(final String algorithm) {
		try {
			return MessageDigest.getInstance(algorithm);
		} catch (final NoSuchAlgorithmException e) {
			throw new IllegalArgumentException(e);
		}
	}

	/**
	 * SHA1ǩ��
	 *
	 * @param content
	 *            ��ǩ���ַ���
	 * @return ǩ������ַ���
	 */
	public static String SHA1(String content) {
		byte[] data = StringUtil.getBytesUtf8(content);
		return HexUtil.encodeHexString(getDigest(Consts.SHA1).digest(data));
	}

	/**
	 * SHAǩ��
	 *
	 * @param content
	 *            ��ǩ���ַ���
	 * @return ǩ������ַ���
	 */
	public static String SHA(String content) {
		byte[] data = StringUtil.getBytesUtf8(content);
		return HexUtil.encodeHexString(getDigest(Consts.SHA).digest(data));
	}

	/**
	 * MD5ǩ��
	 *
	 * @param content
	 *            ��ǩ���ַ���
	 * @return ǩ������ַ���
	 */
	public static String MD5(String content) {
		byte[] data = StringUtil.getBytesUtf8(content);
		return HexUtil.encodeHexString(getDigest(Consts.MD5).digest(data));
	}

	/**
	 * md5ǩ��(һ������V3.x֧���ӿ�)
	 *
	 * @param obj
	 *            ǩ������
	 * @param paySignKey
	 *            ֧��API����Կ
	 * @return
	 */
	public static String paysignMd5(Object obj, String paySignKey) {
		StringBuilder sb = new StringBuilder();
		// a--->string1
		sb.append(MapUtil.toJoinString(obj, false, false, null));
		// b--->
		// �� string1 ���ƴ���� key=paternerKey �õ� stringSignTemp �ַ���,�� ��
		// stringSignTemp ���� md5 ����
		// �ٽ��õ��� �ַ��������ַ�ת��Ϊ��д ,�õ� sign ֵ signValue��
		sb.append("&key=").append(paySignKey);
		return MD5(sb.toString()).toUpperCase();
	}

	/**
	 * shaǩ��(һ������V2.x֧���ӿ�)
	 *
	 * @param obj
	 *            ǩ������
	 * @param paySignKey
	 *            ֧��API����Կ<font color="red">��ע������Ž�ȥ����put("appKey",
	 *            paySignKey)</font>
	 * @return
	 */
	public static String paysignSha(Object obj, String paySignKey) {
		Map<String, String> extra = new HashMap<String, String>();
		if (StringUtil.isNotBlank(paySignKey)) {
			extra.put("appKey", paySignKey);
		}
		return SHA1(MapUtil.toJoinString(obj, false, true, extra));
	}

	/**
	 * packageƴ��ǩ��(һ������V2.x֧���ӿ�)
	 *
	 * @param signObj
	 *            ǩ������ �� PayPackageV2
	 * @param signKey
	 *            ǩ��key
	 * @return
	 */
	public static String packageSign(Object signObj, String signKey) {
		StringBuilder sb = new StringBuilder();
		// a.�����д�����������ֶ����� ASCII ���С��������(�ֵ���) ��,
		// ʹ�� URL ��ֵ �Եĸ�ʽ(�� key1=value1&key2=value2...)ƴ�ӳ��ַ��� string1
		// ע��:ֵΪ�յĲ���������ǩ��
		sb.append(MapUtil.toJoinString(signObj, false, false, null));
		// b--->
		// �� string1 ���ƴ���� key=signKey �õ� stringSignTemp �ַ���,�� ��
		// stringSignTemp ���� md5 ����
		// �ٽ��õ��� �ַ��������ַ�ת��Ϊ��д ,�õ� sign ֵ signValue��
		sb.append("&key=").append(signKey);
		// c---> & d---->
		String sign = DigestUtil.MD5(sb.toString()).toUpperCase();
		sb.delete(0, sb.length());
		// c.�Դ�����������м�ֵ�Ե� value ���� urlencode ת�������ƴ�ӳ��ַ��� string2
		sb.append(MapUtil.toJoinString(signObj, true, false, null))
				.append("&sign=").append(sign);

		return sb.toString();
	}

	public static void main(String[] args) throws FileNotFoundException {
		NativePayNotify notify = XmlStream.fromXML(new FileInputStream(
						new File("/Users/jy/Downloads/weixin4j.xml")),
				NativePayNotify.class);
		notify.setSign(null);
		System.err.println(paysignMd5(notify, "GATFzDwbQdbbci3QEQxX2rUBvwTrsMiZ"));
	}
}
