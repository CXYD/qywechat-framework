package com.wk.wechat4j.base.payment;


/**
 * ֧��URL������
 *
 * @className PayURLConsts
 * @author jy
 * @date 2014��12��3��
 * @since JDK 1.6
 * @see
 */
public final class PayURLConsts {

	private static final String MCH_BASE_URL = "https://api.mch.weixin.qq.com";

	/**
	 * �̻�ƽ̨��ͳһ�������ɵ�url
	 */
	public static final String MCH_UNIFIEDORDER_URL = MCH_BASE_URL
			+ "/pay/unifiedorder";
	/**
	 * ������ѯ(�̻�ƽ̨)
	 */
	public static final String MCH_ORDERQUERY_URL = MCH_BASE_URL
			+ "/pay/orderquery";
	/**
	 * �رն���(�̻�ƽ̨)
	 */
	public static final String MCH_CLOSEORDER_URL = MCH_BASE_URL
			+ "/pay/closeorder";
	/**
	 * ���˵�����(�̻�ƽ̨)
	 */
	public static final String MCH_DOWNLOADBILL_URL = MCH_BASE_URL
			+ "/pay/downloadbill";
	/**
	 * �˿��ѯ(�̻�ƽ̨)
	 */
	public static final String MCH_REFUNDQUERY_URL = MCH_BASE_URL
			+ "/pay/refundquery";
	/**
	 * �˿�����(�̻�ƽ̨)
	 */
	public static final String MCH_REFUNDAPPLY_URL = MCH_BASE_URL
			+ "/secapi/pay/refund";
	/**
	 * ��������(�̻�ƽ̨)
	 */
	public static final String MCH_ORDERREVERSE_URL = MCH_BASE_URL
			+ "/secapi/pay/reverse";
	/**
	 * ��ɨ֧��&ˢ��֧��(�̻�ƽ̨)
	 */
	public static final String MCH_MICROPAY_URL = MCH_BASE_URL
			+ "/pay/micropay";
	/**
	 * �ӿ��ϱ�(�̻�ƽ̨)
	 */
	public static final String MCH_PAYREPORT_URL = MCH_BASE_URL
			+ "/payitil/report";
	/**
	 * �����ֽ���-��ͨ���(�̻�ƽ̨)
	 */
	public static final String MCH_REDPACKSEND_URL = MCH_BASE_URL
			+ "/mmpaymkttransfers/sendredpack";
	/**
	 * �����ֽ���-�ѱ���(�̻�ƽ̨)
	 */
	public static final String MCH_REDPACK_GROUPSEND_URL = MCH_BASE_URL
			+ "/mmpaymkttransfers/sendgroupredpack";
	/**
	 * ��ѯ�ֽ���(�̻�ƽ̨)
	 */
	public static final String MCH_REDPACKQUERY_URL = MCH_BASE_URL
			+ "/mmpaymkttransfers/gethbinfo";
	/**
	 * ��ҵ����˸���(�̻�ƽ̨)
	 */
	public static final String MCH_ENPAYMENT_URL = MCH_BASE_URL
			+ "/mmpaymkttransfers/promotion/transfers";
	/**
	 * ��ҵ�����ѯ(�̻�ƽ̨)
	 */
	public static final String MCH_ENPAYQUERY_URL = MCH_BASE_URL
			+ "/mmpaymkttransfers/gettransferinfo";
	/**
	 * ���Ŵ���ȯ(�̻�ƽ̨)
	 */
	public static final String MCH_COUPONSEND_URL = MCH_BASE_URL
			+ "/mmpaymkttransfers/send_coupon";
	/**
	 * ��ѯ����ȯ������Ϣ(�̻�ƽ̨)
	 */
	public static final String MCH_COUPONSTOCKQUERY_URL = MCH_BASE_URL
			+ "/mmpaymkttransfers/query_coupon_stock";
	/**
	 * ��ѯ����ȯ��ϸ��Ϣ(�̻�ƽ̨)
	 */
	public static final String MCH_COUPONDETAILQUERY_URL = MCH_BASE_URL
			+ "/promotion/query_coupon";
	/**
	 * ������ת��(�̻�ƽ̨)
	 */
	public static final String MCH_SHORTURL_URL = MCH_BASE_URL
			+ "/tools/shorturl";
	/**
	 * �̻�ƽ̨��native֧����url(ģʽ1)
	 */
	public static final String MCH_NATIVE_URL = "weixin://wxpay/bizpayurl?sign=%s&appid=%s&mch_id=%s&product_id=%s&time_stamp=%s&nonce_str=%s";

	/**
	 * WAP֧��
	 *
	 * @see <a
	 *      href="https://pay.weixin.qq.com/wiki/doc/api/wap.php?chapter=15_1">WAP֧��˵��</a>
	 */
	public static final String MCH_WAP_URL = "weixin://wap/pay?%s";
	/**
	 * ��Ȩ���ѯOPENID�ӿ�
	 */
	public static final String MCH_AUTHCODE_OPENID_URL = MCH_BASE_URL
			+ "/tools/authcodetoopenid";
}
