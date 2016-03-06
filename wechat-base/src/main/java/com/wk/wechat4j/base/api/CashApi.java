package com.wk.wechat4j.base.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.wk.wechat4j.base.exception.WeixinException;
import com.wk.wechat4j.base.http.weixin.WeixinRequestExecutor;
import com.wk.wechat4j.base.http.weixin.WeixinResponse;
import com.wk.wechat4j.base.http.weixin.WeixinSSLRequestExecutor;
import com.wk.wechat4j.base.model.WeixinPayAccount;
import com.wk.wechat4j.base.payment.PayURLConsts;
import com.wk.wechat4j.base.payment.mch.*;
import com.wk.wechat4j.base.util.DigestUtil;
import com.wk.wechat4j.base.util.RandomUtil;
import com.wk.wechat4j.base.xml.XmlStream;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * �ֽ�API
 *
 * @className CashApi
 * @author jy
 * @date 2015��3��28��
 * @since JDK 1.6
 * @see <a
 *      href="http://pay.weixin.qq.com/wiki/doc/api/cash_coupon.php?chapter=13_1">�ֽ���</a>
 * @see <a
 *      href="http://pay.weixin.qq.com/wiki/doc/api/mch_pay.php?chapter=14_1">��ҵ����</a>
 */
public class CashApi {

	private final WeixinPayAccount weixinAccount;

	public CashApi(WeixinPayAccount weixinAccount) {
		this.weixinAccount = weixinAccount;
	}

	/**
	 * ���ź�� ��ҵ��΢���û����˷��ֽ���
	 *
	 * @param ca
	 *            ��׺Ϊ*.p12��֤���ļ�
	 * @param redpacket
	 *            �����Ϣ
	 * @return ���Ž��
	 * @see com.wk.wechat4j.base.payment.mch.Redpacket
	 * @see com.wk.wechat4j.base.payment.mch.RedpacketSendResult
	 * @see <a
	 *      href="http://pay.weixin.qq.com/wiki/doc/api/cash_coupon.php?chapter=13_5">���ź���ӿ�˵��</a>
	 * @throws WeixinException
	 */
	public RedpacketSendResult sendRedpack(InputStream ca, Redpacket redpacket)
			throws WeixinException {
		JSONObject obj = (JSONObject) JSON.toJSON(redpacket);
		obj.put("nonce_str", RandomUtil.generateString(16));
		obj.put("mch_id", weixinAccount.getMchId());
		obj.put("sub_mch_id", weixinAccount.getSubMchId());
		obj.put("wxappid", weixinAccount.getId());
		String sign = DigestUtil.paysignMd5(obj, weixinAccount.getPaySignKey());
		obj.put("sign", sign);
		String param = XmlStream.map2xml(obj);
		WeixinResponse response = null;
		try {
			WeixinRequestExecutor weixinExecutor = new WeixinSSLRequestExecutor(
					weixinAccount.getCertificateKey(), ca);
			response = weixinExecutor
					.post(redpacket.getTotalNum() > 1 ? PayURLConsts.MCH_REDPACK_GROUPSEND_URL
							: PayURLConsts.MCH_REDPACKSEND_URL, param);
		} finally {
			if (ca != null) {
				try {
					ca.close();
				} catch (IOException e) {
					;
				}
			}
		}
		return response.getAsObject(new TypeReference<RedpacketSendResult>() {
		});
	}

	/**
	 * ��ѯ�����¼
	 *
	 * @param ca
	 *            ��׺Ϊ*.p12��֤���ļ�
	 * @param outTradeNo
	 *            �̻����ź�����̻�������
	 * @return �����¼
	 * @see com.wk.wechat4j.base.payment.mch.RedpacketRecord
	 * @see <a
	 *      href="http://pay.weixin.qq.com/wiki/doc/api/cash_coupon.php?chapter=13_6">��ѯ����ӿ�˵��</a>
	 * @throws WeixinException
	 */
	public RedpacketRecord queryRedpack(InputStream ca, String outTradeNo)
			throws WeixinException {
		Map<String, String> para = new HashMap<String, String>();
		para.put("nonce_str", RandomUtil.generateString(16));
		para.put("mch_id", weixinAccount.getMchId());
		para.put("bill_type", "MCHT");
		para.put("appid", weixinAccount.getId());
		para.put("mch_billno", outTradeNo);
		String sign = DigestUtil
				.paysignMd5(para, weixinAccount.getPaySignKey());
		para.put("sign", sign);
		String param = XmlStream.map2xml(para);
		WeixinResponse response = null;
		try {
			WeixinRequestExecutor weixinExecutor = new WeixinSSLRequestExecutor(
					weixinAccount.getCertificateKey(), ca);
			response = weixinExecutor.post(PayURLConsts.MCH_REDPACKQUERY_URL,
					param);
		} finally {
			if (ca != null) {
				try {
					ca.close();
				} catch (IOException e) {
					;
				}
			}
		}
		return response.getAsObject(new TypeReference<RedpacketRecord>() {
		});
	}

	/**
	 * ��ҵ���� ʵ����ҵ����˸����Բ����п����������̻��� �ṩͨ��API�����ҵ����Ĺ��ܡ� ����Ŀǰ�ı�����ҵ��ͻ��˱������������⡣
	 *
	 * @param ca
	 *            ��׺Ϊ*.p12��֤���ļ�
	 * @param mpPayment
	 *            ������Ϣ
	 * @return ������
	 * @see com.wk.wechat4j.base.payment.mch.MPPayment
	 * @see com.wk.wechat4j.base.payment.mch.MPPaymentResult
	 * @see <a
	 *      href="http://pay.weixin.qq.com/wiki/doc/api/mch_pay.php?chapter=14_1">��ҵ����</a>
	 * @throws WeixinException
	 */
	public MPPaymentResult mchPayment(InputStream ca, MPPayment mpPayment)
			throws WeixinException {
		JSONObject obj = (JSONObject) JSON.toJSON(mpPayment);
		obj.put("nonce_str", RandomUtil.generateString(16));
		obj.put("mchid", weixinAccount.getMchId());
		obj.put("sub_mch_id", weixinAccount.getSubMchId());
		obj.put("mch_appid", weixinAccount.getId());
		obj.put("device_info", weixinAccount.getDeviceInfo());
		String sign = DigestUtil.paysignMd5(obj, weixinAccount.getPaySignKey());
		obj.put("sign", sign);
		String param = XmlStream.map2xml(obj);
		WeixinResponse response = null;
		try {
			WeixinRequestExecutor weixinExecutor = new WeixinSSLRequestExecutor(
					weixinAccount.getCertificateKey(), ca);
			response = weixinExecutor.post(PayURLConsts.MCH_ENPAYMENT_URL,
					param);
		} finally {
			if (ca != null) {
				try {
					ca.close();
				} catch (IOException e) {
					;
				}
			}
		}
		String text = response.getAsString()
				.replaceFirst("<mch_appid>", "<appid>")
				.replaceFirst("</mch_appid>", "</appid>")
				.replaceFirst("<mchid>", "<mch_id>")
				.replaceFirst("</mchid>", "</mch_id>");
		return XmlStream.fromXML(text, MPPaymentResult.class);
	}

	/**
	 * ��ҵ�����ѯ �����̻�����ҵ����������н����ѯ�����ظ��������ϸ���
	 *
	 * @param ca
	 *           ��׺Ϊ*.p12��֤���ļ�
	 * @param outTradeNo
	 *            �̻�������ҵ����APIʱʹ�õ��̻�������
	 * @return �����¼
	 * @see com.wk.wechat4j.base.payment.mch.MPPaymentRecord
	 * @see <a
	 *      href="http://pay.weixin.qq.com/wiki/doc/api/mch_pay.php?chapter=14_3">��ҵ�����ѯ</a>
	 * @throws WeixinException
	 */
	public MPPaymentRecord mchPaymentQuery(InputStream ca, String outTradeNo)
			throws WeixinException {
		JSONObject obj = new JSONObject();
		obj.put("nonce_str", RandomUtil.generateString(16));
		obj.put("mch_id", weixinAccount.getMchId());
		obj.put("appid", weixinAccount.getId());
		obj.put("partner_trade_no", outTradeNo);
		String sign = DigestUtil.paysignMd5(obj, weixinAccount.getPaySignKey());
		obj.put("sign", sign);
		String param = XmlStream.map2xml(obj);
		WeixinResponse response = null;
		try {
			WeixinRequestExecutor weixinExecutor = new WeixinSSLRequestExecutor(
					weixinAccount.getCertificateKey(), ca);
			response = weixinExecutor.post(PayURLConsts.MCH_ENPAYQUERY_URL,
					param);
		} finally {
			if (ca != null) {
				try {
					ca.close();
				} catch (IOException e) {
					;
				}
			}
		}
		return response.getAsObject(new TypeReference<MPPaymentRecord>() {
		});
	}
}
