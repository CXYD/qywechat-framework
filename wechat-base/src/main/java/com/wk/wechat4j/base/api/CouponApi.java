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
 * 代金券API
 *
 * @className CouponApi
 * @author jy
 * @date 2015年3月25日
 * @since JDK 1.6
 * @see <a href="http://pay.weixin.qq.com/wiki/doc/api/sp_coupon.php">代金券文档</a>
 */
public class CouponApi {

	private final WeixinRequestExecutor weixinExecutor;

	private final WeixinPayAccount weixinAccount;

	public CouponApi(WeixinPayAccount weixinAccount) {
		this.weixinAccount = weixinAccount;
		this.weixinExecutor = new WeixinRequestExecutor();
	}

	/**
	 * 发放代金券(需要证书)
	 *
	 * @param ca
	 *           后缀为*.p12的证书文件
	 * @param couponStockId
	 *            代金券批次id
	 * @param partnerTradeNo
	 *            商户发放凭据号（格式：商户id+日期+流水号），商户侧需保持唯一性
	 * @param openId
	 *            用户的openid
	 * @param opUserId
	 *            操作员帐号, 默认为商户号 可在商户平台配置操作员对应的api权限 可为空
	 * @return 发放结果
	 * @see com.wk.wechat4j.base.payment.coupon.CouponResult
	 * @see <a
	 *      href="http://pay.weixin.qq.com/wiki/doc/api/sp_coupon.php?chapter=12_3">发放代金券接口</a>
	 * @throws WeixinException
	 */
	public CouponResult sendCoupon(InputStream ca, String couponStockId,
			String partnerTradeNo, String openId, String opUserId)
			throws WeixinException {
		Map<String, String> map = baseMap();
		map.put("coupon_stock_id", couponStockId);
		map.put("partner_trade_no", partnerTradeNo);
		map.put("openid", openId);
		// openid记录数（目前支持num=1）
		map.put("openid_count", "1");
		// 操作员帐号, 默认为商户号 可在商户平台配置操作员对应的api权限
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
	 * 查询代金券批次
	 *
	 * @param couponStockId
	 *            代金券批次ID
	 * @return 代金券批次信息
	 * @see com.wk.wechat4j.base.payment.coupon.CouponStock
	 * @see <a
	 *      href="http://pay.weixin.qq.com/wiki/doc/api/sp_coupon.php?chapter=12_4">查询代金券批次信息</a>
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
	 * 查询代金券详细
	 *
	 * @param couponId
	 *            代金券ID
	 * @return 代金券详细信息
	 * @see com.wk.wechat4j.base.payment.coupon.CouponDetail
	 * @see <a
	 *      href="http://pay.weixin.qq.com/wiki/doc/api/sp_coupon.php?chapter=12_5">查询代金券详细信息</a>
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
	 * 接口请求基本数据
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