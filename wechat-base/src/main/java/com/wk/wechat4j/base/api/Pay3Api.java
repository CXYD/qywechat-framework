package com.wk.wechat4j.base.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.wk.wechat4j.base.exception.WeixinException;
import com.wk.wechat4j.base.exception.WeixinPayException;
import com.wk.wechat4j.base.http.weixin.WeixinRequestExecutor;
import com.wk.wechat4j.base.http.weixin.WeixinResponse;
import com.wk.wechat4j.base.http.weixin.WeixinSSLRequestExecutor;
import com.wk.wechat4j.base.http.weixin.XmlResult;
import com.wk.wechat4j.base.model.Consts;
import com.wk.wechat4j.base.model.WeixinPayAccount;
import com.wk.wechat4j.base.payment.MicroPayPackage;
import com.wk.wechat4j.base.payment.PayURLConsts;
import com.wk.wechat4j.base.payment.mch.*;
import com.wk.wechat4j.base.type.*;
import com.wk.wechat4j.base.util.*;
import com.wk.wechat4j.base.xml.ListsuffixResultDeserializer;
import com.wk.wechat4j.base.xml.XmlStream;

import java.io.*;
import java.net.URLEncoder;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * (�̻�ƽ̨��)֧��API
 *
 * @className Pay3Api
 * @author jy
 * @date 2014��10��28��
 * @since JDK 1.6
 * @see <a href="http://pay.weixin.qq.com/wiki/doc/api/index.html">�̻�ƽ̨API</a>
 */
public class Pay3Api {

	private final WeixinRequestExecutor weixinExecutor;
	private final WeixinPayAccount weixinAccount;

	public Pay3Api(WeixinPayAccount weixinAccount) {
		this.weixinAccount = weixinAccount;
		this.weixinExecutor = new WeixinRequestExecutor();
	}

	/**
	 * ͳһ�µ��ӿ�</br>
	 * ����ɨ֧���������⣬�̻�ϵͳ�ȵ��øýӿ���΢��֧�������̨����Ԥ֧�����׵���������ȷ��Ԥ֧�����׻ػ���ʶ���ٰ�ɨ�롢JSAPI
	 * ��APP�Ȳ�ͬ�������ɽ��״�����֧����
	 *
	 * @param payPackage
	 *            ����������Ϣ�Ķ���
	 * @see com.wk.wechat4j.base.payment.mch.MchPayPackage
	 * @see com.wk.wechat4j.base.payment.mch.PrePay
	 * @see <a
	 *      href="http://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=9_1">ͳһ�µ��ӿ�</a>
	 * @return Ԥ֧������
	 */
	public PrePay createPrePay(MchPayPackage payPackage)
			throws WeixinPayException {
		if (StringUtil.isBlank(payPackage.getSign())) {
			payPackage.setSign(DigestUtil.paysignMd5(payPackage,
					weixinAccount.getPaySignKey()));
		}
		String payJsRequestXml = XmlStream.toXML(payPackage);
		try {
			WeixinResponse response = weixinExecutor.post(
					PayURLConsts.MCH_UNIFIEDORDER_URL, payJsRequestXml);
			PrePay prePay = response.getAsObject(new TypeReference<PrePay>() {
			});
			if (!prePay.getReturnCode().equalsIgnoreCase(Consts.SUCCESS)) {
				throw new WeixinPayException(prePay.getReturnMsg(),
						prePay.getReturnCode());
			}
			if (!prePay.getResultCode().equalsIgnoreCase(Consts.SUCCESS)) {
				throw new WeixinPayException(prePay.getResultCode(),
						prePay.getErrCodeDes());
			}
			return prePay;
		} catch (WeixinException e) {
			throw new WeixinPayException(e.getErrorCode(), e.getErrorMsg());
		}
	}

	/**
	 * ����֧���������
	 *
	 * @param payPackage
	 *            ֧������
	 * @return ֧���������
	 * @see com.wk.wechat4j.base.payment.mch.JSAPIPayRequest JS֧��
	 * @see com.wk.wechat4j.base.payment.mch.NATIVEPayRequest ɨ��֧��
	 * @see com.wk.wechat4j.base.payment.mch.APPPayRequest APP֧��
	 * @see com.wk.wechat4j.base.payment.mch.WAPPayRequest WAP֧��
	 * @throws WeixinPayException
	 */
	public MchPayRequest createPayRequest(MchPayPackage payPackage)
			throws WeixinPayException {
		payPackage.setSign(DigestUtil.paysignMd5(payPackage,
				weixinAccount.getPaySignKey()));
		PrePay prePay = createPrePay(payPackage);
		String tradeType = payPackage.getTradeType();
		if (TradeType.APP.name().equalsIgnoreCase(tradeType)) {
			return new APPPayRequest(prePay.getPrepayId(), weixinAccount);
		} else if (TradeType.JSAPI.name().equalsIgnoreCase(tradeType)) {
			return new JSAPIPayRequest(prePay.getPrepayId(), weixinAccount);
		} else if (TradeType.NATIVE.name().equalsIgnoreCase(tradeType)) {
			return new NATIVEPayRequest(prePay.getPrepayId(),
					prePay.getCodeUrl(), weixinAccount);
		} else if (TradeType.WAP.name().equalsIgnoreCase(tradeType)) {
			return new WAPPayRequest(prePay.getPrepayId(), weixinAccount);
		} else if (TradeType.MICROPAY.name().equalsIgnoreCase(tradeType)) {
			throw new WeixinPayException("maybe use createMicroPay method?");
		} else {
			throw new WeixinPayException("unknown tradeType:" + tradeType);
		}
	}

	/**
	 * ����֧�������������������
	 *
	 * @param tradeType
	 *            �������� <font color="red">������</font>
	 * @param openId
	 *            �û�ID <font color="red">tradeType=JSAPIʱ����</font>
	 * @param productId
	 *            ��ƷID <font color="red">tradeType=NATIVEʱ����</font>
	 * @param body
	 *            ��Ʒ���� <font color="red">������</font>
	 * @param detail
	 *            ��Ʒ������ϸ�б� �Ǳ�����
	 * @param outTradeNo
	 *            �̻��ڲ�Ψһ������ <font color="red">������</font>
	 * @param totalFee
	 *            ��Ʒ�ܶ� ��λԪ <font color="red">������</font>
	 * @param notifyUrl
	 *            ֧���ص�URL <font color="red">������</font>
	 * @param createIp
	 *            �������ɵĻ���IP <font color="red">������</font>
	 * @param attach
	 *            �������ݣ��ڲ�ѯAPI��֧��֪ͨ��ԭ�����أ����ֶ���Ҫ�����̻�Я���������Զ������� �Ǳ�����
	 * @param timeStart
	 *            ��������ʱ�䣬��ʽΪyyyyMMddHHmmss �Ǳ�����
	 * @param timeExpire
	 *            ����ʧЧʱ�䣬��ʽΪyyyyMMddHHmmss;ע�⣺���ʧЧʱ�����������5���� �Ǳ�����
	 * @param goodsTag
	 *            ��Ʒ��ǣ�����ȯ�������Żݹ��ܵĲ��� �Ǳ�����
	 * @param limitPay
	 *            ָ��֧����ʽ:no_credit--ָ������ʹ�����ÿ�֧�� �Ǳ�����
	 * @see com.wk.wechat4j.base.payment.mch.JSAPIPayRequest JS֧��
	 * @see com.wk.wechat4j.base.payment.mch.NATIVEPayRequest ɨ��֧��
	 * @see com.wk.wechat4j.base.payment.mch.APPPayRequest APP֧��
	 * @see com.wk.wechat4j.base.payment.mch.WAPPayRequest WAP֧��t
	 * @throws WeixinPayException
	 */
	public MchPayRequest createPayRequest(TradeType tradeType, String openId,
			String productId, String body, String detail, String outTradeNo,
			double totalFee, String notifyUrl, String createIp, String attach,
			Date timeStart, Date timeExpire, String goodsTag, String limitPay)
			throws WeixinPayException {
		MchPayPackage payPackage = new MchPayPackage(weixinAccount, openId,
				body, outTradeNo, totalFee, notifyUrl, createIp, tradeType);
		payPackage.setProductId(productId);
		payPackage.setAttach(attach);
		payPackage.setTimeStart(timeStart);
		payPackage.setTimeExpire(timeExpire);
		payPackage.setGoodsTag(goodsTag);
		payPackage.setLimitPay(limitPay);
		payPackage.setDetail(detail);
		return createPayRequest(payPackage);
	}

	/**
	 * ����JSAPI֧���������
	 *
	 * @param openId
	 *            �û�ID
	 * @param body
	 *            ��������
	 * @param outTradeNo
	 *            ������
	 * @param totalFee
	 *            �����ܶ� ��ʵ�ʽ��뼴��(Ԫ) ���캯����ת��Ϊ��
	 * @param notifyUrl
	 *            ֧��֪ͨ��ַ
	 * @param createIp
	 *            ip��ַ
	 * @see com.wk.wechat4j.base.payment.mch.JSAPIPayRequest
	 * @return JSAPI֧������
	 * @throws WeixinPayException
	 */
	public MchPayRequest createJSPayRequest(String openId, String body,
			String outTradeNo, double totalFee, String notifyUrl,
			String createIp) throws WeixinPayException {
		MchPayPackage payPackage = new MchPayPackage(weixinAccount, openId,
				body, outTradeNo, totalFee, notifyUrl, createIp,
				TradeType.JSAPI);
		return createPayRequest(payPackage);
	}

	/**
	 * <p>
	 * ���ɱ༭��ַ����
	 * </p>
	 *
	 * err_msg edit_address:ok��ȡ�༭�ջ���ַ�ɹ�</br> edit_address:fail��ȡ�༭�ջ���ַʧ��</br>
	 * userName �ջ�������</br> telNumber �ջ��˵绰</br> addressPostalCode �ʱ�</br>
	 * proviceFirstStageName �����ջ���ַ��һ����ַ</br> addressCitySecondStageName
	 * �����ջ���ַ�ڶ�����ַ</br> addressCountiesThirdStageName �����ջ���ַ��������ַ</br>
	 * addressDetailInfo ��ϸ�ջ���ַ��Ϣ</br> nationalCode �ջ���ַ������</br>
	 *
	 * @param url
	 *            ��ǰ����ҳ��URL
	 * @param oauthToken
	 *            oauth��Ȩʱ������token
	 * @see <a
	 *      href="https://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=7_8&index=7">�ջ���ַ����</a>
	 * @return �༭��ַ����JSON��
	 */
	public String createAddressRequestJSON(String url, String oauthToken) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("appId", weixinAccount.getId());
		map.put("timeStamp", DateUtil.timestamp2string());
		map.put("nonceStr", RandomUtil.generateString(16));
		map.put("url", url);
		map.put("accessToken", oauthToken);
		String sign = DigestUtil.SHA1(MapUtil.toJoinString(map, false, true,
				null));
		map.remove("url");
		map.remove("accessToken");
		map.put("scope", "jsapi_address");
		map.put("signType", SignType.SHA1.name().toLowerCase());
		map.put("addrSign", sign);
		return JSON.toJSONString(map);
	}

	/**
	 * ����Native֧��(ɨ��֧��)���ӡ�ģʽһ��
	 *
	 * @param productId
	 *            �붩��ID�ȼ�
	 * @return ֧������
	 * @see <a href="http://pay.weixin.qq.com/wiki/doc/api/native.php">ɨ��֧��</a>
	 * @see <a
	 *      href="https://pay.weixin.qq.com/wiki/doc/api/native.php?chapter=6_4">ģʽһ</a>
	 */
	public String createNativePayRequestURL(String productId) {
		Map<String, String> map = new HashMap<String, String>();
		String timestamp = DateUtil.timestamp2string();
		String noncestr = RandomUtil.generateString(16);
		map.put("appid", weixinAccount.getId());
		map.put("mch_id", weixinAccount.getMchId());
		map.put("time_stamp", timestamp);
		map.put("nonce_str", noncestr);
		map.put("product_id", productId);
		String sign = DigestUtil.paysignMd5(map, weixinAccount.getPaySignKey());
		return String.format(PayURLConsts.MCH_NATIVE_URL, sign,
				weixinAccount.getId(), weixinAccount.getMchId(), productId,
				timestamp, noncestr);
	}

	/**
	 * ����Native֧��(ɨ��֧��)�ص�����ģʽһ��
	 *
	 * @param productId
	 *            ��ƷID
	 * @param body
	 *            ��Ʒ����
	 * @param outTradeNo
	 *            �̻��ڲ�Ψһ������
	 * @param totalFee
	 *            ��Ʒ�ܶ� ��λԪ
	 * @param notifyUrl
	 *            ֧���ص�URL
	 * @param createIp
	 *            �������ɵĻ��� IP
	 * @return Native�ص�����
	 * @see com.wk.wechat4j.base.payment.mch.NativePayResponse
	 * @see <a href="http://pay.weixin.qq.com/wiki/doc/api/native.php">ɨ��֧��</a>
	 * @see <a
	 *      href="https://pay.weixin.qq.com/wiki/doc/api/native.php?chapter=6_4">ģʽһ</a>
	 * @throws WeixinPayException
	 */
	public NativePayResponse createNativePayResponse(String productId,
			String body, String outTradeNo, double totalFee, String notifyUrl,
			String createIp) throws WeixinPayException {
		MchPayPackage payPackage = new MchPayPackage(weixinAccount, null, body,
				outTradeNo, totalFee, notifyUrl, createIp, TradeType.NATIVE);
		payPackage.setProductId(productId);
		PrePay prePay = createPrePay(payPackage);
		return new NativePayResponse(weixinAccount, prePay.getPrepayId());
	}

	/**
	 * ����Native֧��(ɨ��֧��)���ӡ�ģʽ����
	 *
	 * @param productId
	 *            ��ƷID
	 * @param body
	 *            ��Ʒ����
	 * @param outTradeNo
	 *            �̻��ڲ�Ψһ������
	 * @param totalFee
	 *            ��Ʒ�ܶ� ��λԪ
	 * @param notifyUrl
	 *            ֧���ص�URL
	 * @param createIp
	 *            �������ɵĻ��� IP
	 * @return Native֧������
	 * @see com.wk.wechat4j.base.payment.mch.NATIVEPayRequest
	 * @see <a href="http://pay.weixin.qq.com/wiki/doc/api/native.php">ɨ��֧��</a>
	 * @see <a
	 *      href="https://pay.weixin.qq.com/wiki/doc/api/native.php?chapter=6_5">ģʽ��</a>
	 * @throws WeixinPayException
	 */
	public MchPayRequest createNativePayRequest(String productId, String body,
			String outTradeNo, double totalFee, String notifyUrl,
			String createIp) throws WeixinPayException {
		MchPayPackage payPackage = new MchPayPackage(weixinAccount, null, body,
				outTradeNo, totalFee, notifyUrl, createIp, TradeType.NATIVE);
		return createPayRequest(payPackage);
	}

	/**
	 * ����APP֧���������
	 *
	 * @param body
	 *            ��Ʒ����
	 * @param outTradeNo
	 *            �̻��ڲ�Ψһ������
	 * @param totalFee
	 *            ��Ʒ�ܶ� ��λԪ
	 * @param notifyUrl
	 *            ֧���ص�URL
	 * @param createIp
	 *            �������ɵĻ��� IP
	 * @return APP֧������
	 * @see com.wk.wechat4j.base.payment.mch.APPPayRequest
	 * @see <a
	 *      href="https://pay.weixin.qq.com/wiki/doc/api/app.php?chapter=8_1">APP֧��</a>
	 * @throws WeixinPayException
	 */
	public MchPayRequest createAppPayRequest(String body, String outTradeNo,
			double totalFee, String notifyUrl, String createIp)
			throws WeixinPayException {
		MchPayPackage payPackage = new MchPayPackage(weixinAccount, null, body,
				outTradeNo, totalFee, notifyUrl, createIp, TradeType.APP);
		return createPayRequest(payPackage);
	}

	/**
	 * ����WAP֧���������
	 *
	 * @param body
	 *            ��Ʒ����
	 * @param outTradeNo
	 *            �̻��ڲ�Ψһ������
	 * @param totalFee
	 *            ��Ʒ�ܶ� ��λԪ
	 * @param notifyUrl
	 *            ֧���ص�URL
	 * @param createIp
	 *            �������ɵĻ��� IP
	 * @return WAP֧������
	 * @see com.wk.wechat4j.base.payment.mch.WAPPayRequest
	 * @see <a
	 *      href="https://pay.weixin.qq.com/wiki/doc/api/wap.php?chapter=15_1">WAP֧��</a>
	 * @throws WeixinPayException
	 */
	public MchPayRequest createWAPPayRequest(String body, String outTradeNo,
			double totalFee, String notifyUrl, String createIp)
			throws WeixinPayException {
		MchPayPackage payPackage = new MchPayPackage(weixinAccount, null, body,
				outTradeNo, totalFee, notifyUrl, createIp, TradeType.WAP);
		return createPayRequest(payPackage);
	}

	/**
	 * �ύ��ɨ֧��
	 *
	 * @param authCode
	 *            ɨ��֧����Ȩ�� ,�豸��ȡ�û�΢���е�������߶�ά����Ϣ
	 * @param body
	 *            ��Ʒ����
	 * @param orderNo
	 *            �̻��ڲ�Ψһ������
	 * @param orderFee
	 *            ��Ʒ�ܶ� ��λԪ
	 * @param createIp
	 *            �������ɵĻ��� IP
	 * @return ֧���Ķ�����Ϣ
	 * @see {@link #createMicroPay(MicroPayPackage)}
	 * @throws WeixinException
	 */
	public Order createMicroPay(String authCode, String body, String orderNo,
			double orderFee, String createIp) throws WeixinException {
		MicroPayPackage payPackage = new MicroPayPackage(weixinAccount,
				authCode, body, orderNo, orderFee, createIp);
		return createMicroPay(payPackage);
	}

	/**
	 * �ύ��ɨ֧��:����Աʹ��ɨ���豸��ȡ΢���û�ˢ����Ȩ���Ժ󣬶�ά���������Ϣ�������̻�����̨�����̻�����̨�����̻���̨���øýӿڷ���֧��.
	 *
	 * @param payPackage
	 *            ������Ϣ
	 * @return ֧���Ķ�����Ϣ
	 * @throws WeixinException
	 * @see com.wk.wechat4j.base.payment.mch.Order
	 * @see <a
	 *      href="http://pay.weixin.qq.com/wiki/doc/api/micropay.php?chapter=9_10">�ύ��ɨ֧��API</a>
	 */
	public Order createMicroPay(MicroPayPackage payPackage)
			throws WeixinException {
		String sign = DigestUtil.paysignMd5(payPackage,
				weixinAccount.getPaySignKey());
		payPackage.setSign(sign);
		String para = XmlStream.toXML(payPackage);
		WeixinResponse response = weixinExecutor.post(
				PayURLConsts.MCH_MICROPAY_URL, para);
		return response
				.getAsObject(new TypeReference<com.wk.wechat4j.base.payment.mch.Order>() {
				});
	}

	/**
	 * ������ѯ
	 * <p>
	 * ���̻���̨�����硢�������ȳ����쳣���̻�ϵͳ����δ���յ�֧��֪ͨ��</br> ����֧���ӿں󣬷���ϵͳ�����δ֪����״̬�����</br>
	 * ���ñ�ɨ֧��API������USERPAYING��״̬��</br> ���ùص������ӿ�API֮ǰ����ȷ��֧��״̬��
	 * </P>
	 *
	 * @param idQuery
	 *            �̻�ϵͳ�ڲ��Ķ�����, transaction_id��out_trade_no �� ѡһ,���ͬʱ�������ȼ�:
	 *            transaction_id> out_trade_no
	 * @return ������Ϣ
	 * @see com.wk.wechat4j.base.payment.mch.Order
	 * @see <a
	 *      href="http://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=9_2">������ѯAPI</a>
	 * @since V3
	 * @throws WeixinException
	 */
	public Order orderQuery(IdQuery idQuery) throws WeixinException {
		Map<String, String> map = baseMap(idQuery);
		String sign = DigestUtil.paysignMd5(map, weixinAccount.getPaySignKey());
		map.put("sign", sign);
		String param = XmlStream.map2xml(map);
		WeixinResponse response = weixinExecutor.post(
				PayURLConsts.MCH_ORDERQUERY_URL, param);
		return ListsuffixResultDeserializer.deserialize(response.getAsString(),
				Order.class);
	}

	/**
	 * �����˿�(������Ҫ˫��֤��)
	 * <p>
	 * �����׷���֮��һ��ʱ���ڣ�������һ������ҵ�ԭ����Ҫ�˿�ʱ�����ҿ���ͨ���˿�ӿڽ�֧�����˻�����ң�΢��֧�������յ��˿���������֤�ɹ�֮��
	 * �����˿����֧���ԭ·�˵�����ʺ��ϡ�
	 * </p>
	 * <p style="color:red">
	 * 1.����ʱ�䳬������Ķ����޷��ύ�˿
	 * 2.΢��֧���˿�֧�ֵ��ʽ��׷ֶ���˿����˿���Ҫ�ύԭ֧���������̻������ź����ò�ͬ���˿�š�һ���˿�ʧ�ܺ������ύ
	 * ��Ҫ����ԭ�����˿�š����˿���ܳ����û�ʵ��֧����
	 * </p>
	 *
	 * @param ca
	 *            ��׺Ϊ*.p12��֤���ļ�
	 * @param idQuery
	 *            �̻�ϵͳ�ڲ��Ķ�����, transaction_id �� out_trade_no ��ѡһ,���ͬʱ�������ȼ�:
	 *            transaction_id> out_trade_no
	 * @param outRefundNo
	 *            �̻�ϵͳ�ڲ����˿��,�� ��ϵͳ�ڲ�Ψһ,ͬһ�˿�Ŷ������ֻ��һ��
	 * @param totalFee
	 *            �����ܽ��,��λΪԪ
	 * @param refundFee
	 *            �˿��ܽ��,��λΪԪ,�����������˿�
	 * @param refundFeeType
	 *            �������ͣ�����ISO 4217��׼����λ��ĸ���룬Ĭ������ң�CNY
	 * @param opUserId
	 *            ����Ա�ʺ�, Ĭ��Ϊ�̻���
	 * @return �˿�������
	 * @see com.wk.wechat4j.base.payment.mch.RefundResult
	 * @see <a
	 *      href="http://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=9_4">�����˿�API</a>
	 * @since V3
	 * @throws WeixinException
	 */
	public RefundResult refundApply(InputStream ca, IdQuery idQuery,
			String outRefundNo, double totalFee, double refundFee,
			CurrencyType refundFeeType, String opUserId) throws WeixinException {
		WeixinResponse response = null;
		try {
			Map<String, String> map = baseMap(idQuery);
			map.put("out_refund_no", outRefundNo);
			map.put("total_fee", DateUtil.formaFee2Fen(totalFee));
			map.put("refund_fee", DateUtil.formaFee2Fen(refundFee));
			if (StringUtil.isBlank(opUserId)) {
				opUserId = weixinAccount.getMchId();
			}
			map.put("op_user_id", opUserId);
			if (refundFeeType == null) {
				refundFeeType = CurrencyType.CNY;
			}
			map.put("refund_fee_type", refundFeeType.name());
			String sign = DigestUtil.paysignMd5(map,
					weixinAccount.getPaySignKey());
			map.put("sign", sign);
			String param = XmlStream.map2xml(map);
			WeixinRequestExecutor weixinExecutor = new WeixinSSLRequestExecutor(
					weixinAccount.getCertificateKey(), ca);
			response = weixinExecutor.post(PayURLConsts.MCH_REFUNDAPPLY_URL,
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
		return response.getAsObject(new TypeReference<RefundResult>() {
		});
	}

	/**
	 * �˿�����(ȫ���˿�)
	 *
	 * @param ca
	 *            ��׺Ϊ*.p12��֤���ļ�
	 * @param idQuery
	 *            �̻�ϵͳ�ڲ��Ķ�����, transaction_id �� out_trade_no ��ѡһ,���ͬʱ�������ȼ�:
	 *            transaction_id> out_trade_no
	 * @param outRefundNo
	 *            �̻�ϵͳ�ڲ����˿��,�� ��ϵͳ�ڲ�Ψһ,ͬһ�˿�Ŷ������ֻ��һ��
	 * @param totalFee
	 *            �����ܽ��,��λΪԪ
	 * @see {@link #refundApply(InputStream, IdQuery , String, double, double,CurrencyType, String)}
	 */
	public RefundResult refundApply(InputStream ca, IdQuery idQuery,
			String outRefundNo, double totalFee) throws WeixinException {
		return refundApply(ca, idQuery, outRefundNo, totalFee, totalFee, null,
				null);
	}

	/**
	 * ��������(��Ҫ֤��)</br> ��֧������ʧ��,������ϵͳ��ʱ��Ҫȡ������,���Ե��øýӿ�</br> �ӿ��߼�:֧
	 * ��ʧ�ܵĹص�,֧���ɹ��ĳ���֧��</br> <font color="red">7�����ڵĵ��ɳ���,��������֧���ĵ�
	 * ����ʵ����ͬ����������˿�ӿ�</font></br> <font
	 * color="red">���ÿۿ�ӿں������������ó���,��Ҫ�ȴ�5�����ϡ��ȵ��ò鵥�ӿ�,���û��ȷ�еķ���,�ٵ��ó���</font></br>
	 *
	 * @param ca
	 *            ��׺Ϊ*.p12��֤���ļ�
	 * @param idQuery
	 *            �̻�ϵͳ�ڲ��Ķ�����, transaction_id �� out_trade_no ��ѡһ,���ͬʱ�������ȼ�:
	 *            transaction_id> out_trade_no
	 * @return �������
	 * @since V3
	 * @throws WeixinException
	 */
	public ApiResult reverseOrder(InputStream ca, IdQuery idQuery)
			throws WeixinException {
		try {
			WeixinRequestExecutor weixinExecutor = new WeixinSSLRequestExecutor(
					weixinAccount.getCertificateKey(), ca);
			Map<String, String> map = baseMap(idQuery);
			String sign = DigestUtil.paysignMd5(map,
					weixinAccount.getPaySignKey());
			map.put("sign", sign);
			String param = XmlStream.map2xml(map);
			WeixinResponse response = weixinExecutor.post(
					PayURLConsts.MCH_ORDERREVERSE_URL, param);
			return response.getAsObject(new TypeReference<ApiResult>() {
			});
		} finally {
			if (ca != null) {
				try {
					ca.close();
				} catch (IOException e) {
					;
				}
			}
		}
	}

	/**
	 * native֧��URLת�����ӣ�����ɨ��ԭ��֧��ģʽһ�еĶ�ά������ת�ɶ�����(weixin://wxpay/s/XXXXXX)����С��ά��������
	 * ������ɨ���ٶȺ;�ȷ�ȡ�
	 *
	 * @param url
	 *            ����native��ʶ��֧��URL
	 * @return ת����Ķ�����
	 * @throws WeixinException
	 * @see <a
	 *      href="http://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=9_9">ת��������API</a>
	 */
	public String getShorturl(String url) throws WeixinException {
		Map<String, String> map = baseMap(null);
		try {
			map.put("long_url", URLEncoder.encode(url, Consts.UTF_8.name()));
		} catch (UnsupportedEncodingException ignore) {
			;
		}
		String sign = DigestUtil.paysignMd5(map, weixinAccount.getPaySignKey());
		map.put("sign", sign);
		String param = XmlStream.map2xml(map);
		WeixinResponse response = weixinExecutor.post(
				PayURLConsts.MCH_SHORTURL_URL, param);
		map = XmlStream.xml2map(response.getAsString());
		return map.get("short_url");
	}

	/**
	 * �رն���
	 * <p>
	 * �̻�����֧��ʧ����Ҫ�����µ������·���֧����Ҫ��ԭ�����ŵ��ùص��������ظ�֧����ϵͳ�µ����û�֧����ʱ��ϵͳ�˳��������������û�����
	 * ������ùص��ӿ�,����ص�ʧ��,�������� ��֧���밴����֧����������������е���,���ùص��ɹ���,΢�ź�̨�����������˿
	 * </p>
	 *
	 * @param outTradeNo
	 *            �̻�ϵͳ�ڲ��Ķ�����
	 * @return ������
	 * @since V3
	 * @throws WeixinException
	 * @see <a
	 *      href="http://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=9_3">�رն���API</a>
	 */
	public ApiResult closeOrder(String outTradeNo) throws WeixinException {
		Map<String, String> map = baseMap(new IdQuery(outTradeNo,
				IdType.TRADENO));
		String sign = DigestUtil.paysignMd5(map, weixinAccount.getPaySignKey());
		map.put("sign", sign);
		String param = XmlStream.map2xml(map);
		WeixinResponse response = weixinExecutor.post(
				PayURLConsts.MCH_CLOSEORDER_URL, param);
		return response.getAsObject(new TypeReference<ApiResult>() {
		});
	}

	/**
	 * ���ض��˵�<br>
	 * 1.΢�Ų�δ�ɹ��µ��Ľ��ײ�������ڶ��˵��С�֧���ɹ������Ľ��׻�����ڶ��� ����,��ԭ֧����������һ��,bill_type Ϊ
	 * REVOKED;<br>
	 * 2.΢���ڴ��� 9 ����������ǰһ��Ķ��˵�,�����̻� 9 �����ٻ�ȡ;<br>
	 * 3.���˵����漰�����ֶε�λΪ��Ԫ����<br>
	 *
	 * @param billDate
	 *            ���ض��˵�������
	 * @param billType
	 *            ���ض��˵������� ALL,���ص������ж�����Ϣ, Ĭ��ֵ SUCCESS,���ص��ճɹ�֧���Ķ���
	 *            REFUND,���ص����˿��
	 * @param billPath
	 *            ���˵�����·��
	 * @return excel���
	 * @since V3
	 * @see <a
	 *      href="http://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=9_6">���ض��˵�API</a>
	 * @throws WeixinException
	 */
	public File downloadBill(Date billDate, BillType billType, String billPath)
			throws WeixinException {
		if (billDate == null) {
			Calendar now = Calendar.getInstance();
			now.add(Calendar.DAY_OF_MONTH, -1);
			billDate = now.getTime();
		}
		if (billType == null) {
			billType = BillType.ALL;
		}
		String formatBillDate = DateUtil.fortmat2yyyyMMdd(billDate);
		String fileName = String.format("%s_%s_%s.txt", formatBillDate,
				billType.name().toLowerCase(), weixinAccount.getId());
		File file = new File(String.format("%s/%s", billPath, fileName));
		if (file.exists()) {
			return file;
		}
		Map<String, String> map = baseMap(null);
		map.put("bill_date", formatBillDate);
		map.put("bill_type", billType.name());
		String sign = DigestUtil.paysignMd5(map, weixinAccount.getPaySignKey());
		map.put("sign", sign);
		String param = XmlStream.map2xml(map);
		WeixinResponse response = weixinExecutor.post(
				PayURLConsts.MCH_DOWNLOADBILL_URL, param);

		BufferedReader reader = null;
		BufferedWriter writer = null;
		try {
			writer = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(file), Consts.GBK));
			reader = new BufferedReader(new InputStreamReader(
					response.getBody(), com.wk.wechat4j.base.model.Consts.GBK));
			String line = null;
			while ((line = reader.readLine()) != null) {
				writer.write(line);
				writer.newLine();
			}
		} catch (IOException e) {
			throw new WeixinException(e);
		} finally {
			try {
				if (reader != null) {
					reader.close();
				}
				if (writer != null) {
					writer.close();
				}
			} catch (IOException ignore) {
				;
			}
		}
		return file;
	}

	/**
	 * �˿��ѯ
	 *
	 * <p>
	 * �ύ�˿������ͨ�����øýӿڲ�ѯ�˿�״̬���˿���һ����ʱ������Ǯ֧�����˿�20�����ڵ��ˣ����п�֧�����˿�3�������պ����²�ѯ�˿�״̬��
	 * </p>
	 *
	 * @param idQuery
	 *            ���� refund_id��out_refund_no�� out_trade_no �� transaction_id
	 *            �ĸ���������һ��,���ȼ�Ϊ:
	 *            refund_id>out_refund_no>transaction_id>out_trade_no
	 * @return �˿��¼
	 * @see com.wk.wechat4j.base.payment.mch.RefundRecord
	 * @see com.wk.wechat4j.base.payment.mch.RefundDetail
	 * @see <a
	 *      href="http://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=9_5">�˿��ѯAPI</a>
	 * @since V3
	 * @throws WeixinException
	 */
	public RefundRecord refundQuery(IdQuery idQuery) throws WeixinException {
		Map<String, String> map = baseMap(idQuery);
		String sign = DigestUtil.paysignMd5(map, weixinAccount.getPaySignKey());
		map.put("sign", sign);
		String param = XmlStream.map2xml(map);
		WeixinResponse response = weixinExecutor.post(
				PayURLConsts.MCH_REFUNDQUERY_URL, param);
		return ListsuffixResultDeserializer.deserialize(response.getAsString(),
				RefundRecord.class);
	}

	/**
	 * �ӿ��ϱ�
	 *
	 * @param interfaceUrl
	 *            �ϱ���Ӧ�Ľӿڵ����� URL, ����: https://api.mch.weixin.q
	 *            q.com/pay/unifiedorder
	 * @param executeTime
	 *            �ӿں�ʱ���,��λΪ����
	 * @param outTradeNo
	 *            �̻�ϵͳ�ڲ��Ķ�����,�� ���������ϱ�ʱ�ṩ����̻������ŷ���΢��֧������ ����߷���������
	 * @param ip
	 *            ����ӿڵ���ʱ�Ļ��� IP
	 * @param time
	 *            ?�̻����øýӿ�ʱ�̻��Լ� ϵͳ��ʱ��
	 * @param returnXml
	 *            ���ýӿڷ��صĻ�������
	 * @return ������
	 * @throws WeixinException
	 * @see <a
	 *      href="http://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=9_8">�ӿڲ����ϱ�API</a>
	 */
	@SuppressWarnings("unchecked")
	public XmlResult interfaceReport(String interfaceUrl, int executeTime,
			String outTradeNo, String ip, Date time, XmlResult returnXml)
			throws WeixinException {
		Map<String, String> map = baseMap(null);
		map.put("interface_url", interfaceUrl);
		map.put("execute_time_", Integer.toString(executeTime));
		map.put("out_trade_no", outTradeNo);
		map.put("user_ip", ip);
		map.put("time", DateUtil.fortmat2yyyyMMddHHmmss(time));
		map.putAll((Map<String, String>) JSON.toJSON(returnXml));
		String sign = DigestUtil.paysignMd5(map, weixinAccount.getPaySignKey());
		map.put("sign", sign);
		String param = XmlStream.map2xml(map);
		WeixinResponse response = weixinExecutor.post(
				PayURLConsts.MCH_PAYREPORT_URL, param);
		return response.getAsXmlResult();
	}

	/**
	 * ��Ȩ���ѯOPENID�ӿ�
	 *
	 * @param authCode
	 *            ɨ��֧����Ȩ�룬�豸��ȡ�û�΢���е�������߶�ά����Ϣ
	 * @return ��ѯ���
	 * @see com.wk.wechat4j.base.payment.mch.OpenIdResult
	 * @see <a
	 *      href="https://pay.weixin.qq.com/wiki/doc/api/micropay.php?chapter=9_13&index=9">��Ȩ���ѯOPENID</a>
	 * @throws WeixinException
	 */
	public OpenIdResult authCode2openId(String authCode) throws WeixinException {
		Map<String, String> map = baseMap(null);
		map.put("auth_code", authCode);
		String sign = DigestUtil.paysignMd5(map, weixinAccount.getPaySignKey());
		map.put("sign", sign);
		String param = XmlStream.map2xml(map);
		WeixinResponse response = weixinExecutor.post(
				PayURLConsts.MCH_AUTHCODE_OPENID_URL, param);
		return response.getAsObject(new TypeReference<OpenIdResult>() {
		});
	}

	/**
	 * V3�ӿ������������
	 * 
	 * @return
	 */
	private Map<String, String> baseMap(IdQuery idQuery) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("appid", weixinAccount.getId());
		map.put("mch_id", weixinAccount.getMchId());
		map.put("nonce_str", RandomUtil.generateString(16));
		if (StringUtil.isNotBlank(weixinAccount.getDeviceInfo())) {
			map.put("device_info", weixinAccount.getDeviceInfo());
		}
		if (idQuery != null) {
			map.put(idQuery.getType().getName(), idQuery.getId());
		}
		return map;
	}
}
