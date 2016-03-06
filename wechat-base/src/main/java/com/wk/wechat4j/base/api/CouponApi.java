package com.wk.wechat4j.base.api;

import com.alibaba.fastjson.TypeReference;
import com.wk.wechat4j.base.exception.WeixinException;
import com.wk.wechat4j.base.http.weixin.WeixinRequestExecutor;
import com.wk.wechat4j.base.http.weixin.WeixinResponse;
import com.wk.wechat4j.base.http.weixin.WeixinSSLRequestExecutor;
import com.wk.wechat4j.base.model.WeixinPayAccount;
import com.wk.wechat4j.base.payment.PayURLConsts;
import com.wk.wechat4j.base.payment.coupon.CouponDetail;
import com.wk.wechat4j.base.payment.coupon.CouponResult;
import com.wk.wechat4j.base.payment.coupon.CouponStock;
import com.wk.wechat4j.base.util.DigestUtil;
import com.wk.wechat4j.base.util.RandomUtil;
import com.wk.wechat4j.base.util.StringUtil;
import com.wk.wechat4j.base.xml.XmlStream;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * ����ȯAPI
 *
 * @className CouponApi
 * @author jy
 * @date 2015��3��25��
 * @since JDK 1.6
 * @see <a href="http://pay.weixin.qq.com/wiki/doc/api/sp_coupon.php">����ȯ�ĵ�</a>
 */
public class CouponApi {

	private final WeixinRequestExecutor weixinExecutor;

	private final WeixinPayAccount weixinAccount;

	public CouponApi(WeixinPayAccount weixinAccount) {
		this.weixinAccount = weixinAccount;
		this.weixinExecutor = new WeixinRequestExecutor();
	}

	/**
	 * ���Ŵ���ȯ(��Ҫ֤��)
	 *
	 * @param ca
	 *           ��׺Ϊ*.p12��֤���ļ�
	 * @param couponStockId
	 *            ����ȯ����id
	 * @param partnerTradeNo
	 *            �̻�����ƾ�ݺţ���ʽ���̻�id+����+��ˮ�ţ����̻����豣��Ψһ��
	 * @param openId
	 *            �û���openid
	 * @param opUserId
	 *            ����Ա�ʺ�, Ĭ��Ϊ�̻��� �����̻�ƽ̨���ò���Ա��Ӧ��apiȨ�� ��Ϊ��
	 * @return ���Ž��
	 * @see com.wk.wechat4j.base.payment.coupon.CouponResult
	 * @see <a
	 *      href="http://pay.weixin.qq.com/wiki/doc/api/sp_coupon.php?chapter=12_3">���Ŵ���ȯ�ӿ�</a>
	 * @throws WeixinException
	 */
	public CouponResult sendCoupon(InputStream ca, String couponStockId,
			String partnerTradeNo, String openId, String opUserId)
			throws WeixinException {
		Map<String, String> map = baseMap();
		map.put("coupon_stock_id", couponStockId);
		map.put("partner_trade_no", partnerTradeNo);
		map.put("openid", openId);
		// openid��¼����Ŀǰ֧��num=1��
		map.put("openid_count", "1");
		// ����Ա�ʺ�, Ĭ��Ϊ�̻��� �����̻�ƽ̨���ò���Ա��Ӧ��apiȨ��
		if (StringUtil.isBlank(opUserId)) {
			opUserId = weixinAccount.getMchId();
		}
		map.put("op_user_id", opUserId);
		map.put("version", "1.0");
		map.put("type", "XML");
		String sign = DigestUtil.paysignMd5(map, weixinAccount.getPaySignKey());
		map.put("sign", sign);
		String param = XmlStream.map2xml(map);
		WeixinResponse response = null;
		try {
			WeixinRequestExecutor weixinExecutor = new WeixinSSLRequestExecutor(
					weixinAccount.getCertificateKey(), ca);
			response = weixinExecutor.post(PayURLConsts.MCH_COUPONSEND_URL,
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
		return response.getAsObject(new TypeReference<CouponResult>() {
		});
	}

	/**
	 * ��ѯ����ȯ����
	 *
	 * @param couponStockId
	 *            ����ȯ����ID
	 * @return ����ȯ������Ϣ
	 * @see com.wk.wechat4j.base.payment.coupon.CouponStock
	 * @see <a
	 *      href="http://pay.weixin.qq.com/wiki/doc/api/sp_coupon.php?chapter=12_4">��ѯ����ȯ������Ϣ</a>
	 * @throws WeixinException
	 */
	public CouponStock queryCouponStock(String couponStockId)
			throws WeixinException {
		Map<String, String> map = baseMap();
		map.put("coupon_stock_id", couponStockId);
		String sign = DigestUtil.paysignMd5(map, weixinAccount.getPaySignKey());
		map.put("sign", sign);
		String param = XmlStream.map2xml(map);
		WeixinResponse response = weixinExecutor.post(
				PayURLConsts.MCH_COUPONSTOCKQUERY_URL, param);
		return response.getAsObject(new TypeReference<CouponStock>() {
		});
	}

	/**
	 * ��ѯ����ȯ��ϸ
	 *
	 * @param couponId
	 *            ����ȯID
	 * @return ����ȯ��ϸ��Ϣ
	 * @see com.wk.wechat4j.base.payment.coupon.CouponDetail
	 * @see <a
	 *      href="http://pay.weixin.qq.com/wiki/doc/api/sp_coupon.php?chapter=12_5">��ѯ����ȯ��ϸ��Ϣ</a>
	 * @throws WeixinException
	 */
	public CouponDetail queryCouponDetail(String couponId)
			throws WeixinException {
		Map<String, String> map = baseMap();
		map.put("coupon_id", couponId);
		String sign = DigestUtil.paysignMd5(map, weixinAccount.getPaySignKey());
		map.put("sign", sign);
		String param = XmlStream.map2xml(map);
		WeixinResponse response = weixinExecutor.post(
				PayURLConsts.MCH_COUPONDETAILQUERY_URL, param);
		return response.getAsObject(new TypeReference<CouponDetail>() {
		});
	}

	/**
	 * �ӿ������������
	 * 
	 * @return
	 */
	private Map<String, String> baseMap() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("appid", weixinAccount.getId());
		map.put("mch_id", weixinAccount.getMchId());
		map.put("nonce_str", RandomUtil.generateString(16));
		if (StringUtil.isNotBlank(weixinAccount.getDeviceInfo())) {
			map.put("device_info", weixinAccount.getDeviceInfo());
		}
		if (StringUtil.isNotBlank(weixinAccount.getSubMchId())) {
			map.put("sub_mch_id", weixinAccount.getSubMchId());
		}
		return map;
	}
}