package com.wk.wechat4j.base.payment;

import com.wk.wechat4j.base.api.CashApi;
import com.wk.wechat4j.base.api.CouponApi;
import com.wk.wechat4j.base.api.Pay3Api;
import com.wk.wechat4j.base.exception.WeixinException;
import com.wk.wechat4j.base.exception.WeixinPayException;
import com.wk.wechat4j.base.http.weixin.XmlResult;
import com.wk.wechat4j.base.model.WeixinPayAccount;
import com.wk.wechat4j.base.payment.coupon.CouponDetail;
import com.wk.wechat4j.base.payment.coupon.CouponResult;
import com.wk.wechat4j.base.payment.coupon.CouponStock;
import com.wk.wechat4j.base.payment.mch.*;
import com.wk.wechat4j.base.type.BillType;
import com.wk.wechat4j.base.type.CurrencyType;
import com.wk.wechat4j.base.type.IdQuery;
import com.wk.wechat4j.base.type.TradeType;
import com.wk.wechat4j.base.util.Weixin4jSettings;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

/**
 * ΢��֧���ӿ�ʵ��
 *
 * @className WeixinPayProxy
 * @author jy
 * @date 2015��1��3��
 * @since JDK 1.6
 * @see <a href="http://pay.weixin.qq.com/wiki/doc/api/index.html">�̻�ƽ̨֧��API</a>
 */
public class WeixinPayProxy {

	private final Pay3Api pay3Api;
	private final CouponApi couponApi;
	private final CashApi cashApi;

	private final Weixin4jSettings settings;

	/**
	 * ʹ��weixin4j.properties���õ�֧���˺���Ϣ
	 */
	public WeixinPayProxy() {
		this(new Weixin4jSettings());
	}

	/**
	 *
	 * @param settings
	 *            ֧�����������Ϣ
	 * @see Weixin4jSettings
	 */
	public WeixinPayProxy(Weixin4jSettings settings) {
		this.settings = settings;
		this.pay3Api = new Pay3Api(settings.getWeixinPayAccount());
		this.couponApi = new CouponApi(settings.getWeixinPayAccount());
		this.cashApi = new CashApi(settings.getWeixinPayAccount());
	}

	/**
	 * ��ȡ΢���̻�֧����Ϣ
	 *
	 * @return
	 */
	public WeixinPayAccount getPayAccount() {
		return this.settings.getWeixinPayAccount();
	}

	/**
	 * ͳһ�µ��ӿ�</br>
	 * ����ɨ֧���������⣬�̻�ϵͳ�ȵ��øýӿ���΢��֧�������̨����Ԥ֧�����׵���������ȷ��Ԥ֧�����׻ػ���ʶ���ٰ�ɨ�롢JSAPI
	 * ��APP�Ȳ�ͬ�������ɽ��״�����֧����
	 *
	 * @param payPackage
	 *            ����������Ϣ�Ķ���
	 * @see com.wk.wechat4j.base.api.Pay3Api
	 * @see com.wk.wechat4j.base.payment.mch.MchPayPackage
	 * @see com.wk.wechat4j.base.payment.mch.PrePay
	 * @see <a
	 *      href="http://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=9_1">ͳһ�µ��ӿ�</a>
	 * @return Ԥ֧������
	 */
	public PrePay createPrePay(MchPayPackage payPackage)
			throws WeixinPayException {
		return pay3Api.createPrePay(payPackage);
	}

	/**
	 * ����֧���������
	 *
	 * @param payPackage
	 *            ֧������
	 * @return ֧���������
	 * @see com.wk.wechat4j.base.api.Pay3Api
	 * @see com.wk.wechat4j.base.payment.mch.JSAPIPayRequest JS֧��
	 * @see com.wk.wechat4j.base.payment.mch.NATIVEPayRequest ɨ��֧��
	 * @see com.wk.wechat4j.base.payment.mch.APPPayRequest APP֧��
	 * @see com.wk.wechat4j.base.payment.mch.WAPPayRequest WAP֧��
	 * @throws WeixinPayException
	 */
	public MchPayRequest createPayRequest(MchPayPackage payPackage)
			throws WeixinPayException {
		return pay3Api.createPayRequest(payPackage);
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
	 * @see com.wk.wechat4j.base.api.Pay3Api
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
		return pay3Api.createPayRequest(tradeType, openId, productId, body,
				detail, outTradeNo, totalFee, notifyUrl, createIp, attach,
				timeStart, timeExpire, goodsTag, limitPay);
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
	 * @see com.wk.wechat4j.base.api.Pay3Api
	 * @see com.wk.wechat4j.base.payment.mch.JSAPIPayRequest
	 * @return JSAPI֧������
	 * @throws WeixinPayException
	 */
	public MchPayRequest createJSPayRequest(String openId, String body,
			String outTradeNo, double totalFee, String notifyUrl,
			String createIp) throws WeixinPayException {
		return pay3Api.createJSPayRequest(openId, body, outTradeNo, totalFee,
				notifyUrl, createIp);
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
	 * @see com.wk.wechat4j.base.api.Pay3Api
	 * @see <a
	 *      href="https://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=7_8&index=7">�ջ���ַ����</a>
	 * @return �༭��ַ����JSON��
	 */
	public String createAddressRequestJSON(String url, String oauthToken) {
		return pay3Api.createAddressRequestJSON(url, oauthToken);
	}

	/**
	 * ����Native֧��(ɨ��֧��)���ӡ�ģʽһ��
	 *
	 * @param productId
	 *            �붩��ID�ȼ�
	 * @return ֧������
	 * @see com.wk.wechat4j.base.api.Pay3Api
	 * @see <a href="http://pay.weixin.qq.com/wiki/doc/api/native.php">ɨ��֧��</a>
	 * @see <a
	 *      href="https://pay.weixin.qq.com/wiki/doc/api/native.php?chapter=6_4">ģʽһ</a>
	 */
	public String createNativePayRequestURL(String productId) {
		return pay3Api.createNativePayRequestURL(productId);
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
	 * @see com.wk.wechat4j.base.api.Pay3Api
	 * @see com.wk.wechat4j.base.payment.mch.NativePayResponse
	 * @see <a href="http://pay.weixin.qq.com/wiki/doc/api/native.php">ɨ��֧��</a>
	 * @see <a
	 *      href="https://pay.weixin.qq.com/wiki/doc/api/native.php?chapter=6_4">ģʽһ</a>
	 * @throws WeixinPayException
	 */
	public NativePayResponse createNativePayResponse(String productId,
			String body, String outTradeNo, double totalFee, String notifyUrl,
			String createIp) throws WeixinPayException {
		return pay3Api.createNativePayResponse(productId, body, outTradeNo,
				totalFee, notifyUrl, createIp);
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
	 * @see com.wk.wechat4j.base.api.Pay3Api
	 * @see com.wk.wechat4j.base.payment.mch.NATIVEPayRequest
	 * @see <a href="http://pay.weixin.qq.com/wiki/doc/api/native.php">ɨ��֧��</a>
	 * @see <a
	 *      href="https://pay.weixin.qq.com/wiki/doc/api/native.php?chapter=6_5">ģʽ��</a>
	 * @throws WeixinPayException
	 */
	public MchPayRequest createNativePayRequest(String productId, String body,
			String outTradeNo, double totalFee, String notifyUrl,
			String createIp) throws WeixinPayException {
		return pay3Api.createNativePayRequest(productId, body, outTradeNo,
				totalFee, notifyUrl, createIp);
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
	 * @see com.wk.wechat4j.base.api.Pay3Api
	 * @see com.wk.wechat4j.base.payment.mch.APPPayRequest
	 * @see <a
	 *      href="https://pay.weixin.qq.com/wiki/doc/api/app.php?chapter=8_1">APP֧��</a>
	 * @throws WeixinPayException
	 */
	public MchPayRequest createAppPayRequest(String body, String outTradeNo,
			double totalFee, String notifyUrl, String createIp)
			throws WeixinPayException {
		return pay3Api.createAppPayRequest(body, outTradeNo, totalFee,
				notifyUrl, createIp);
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
	 * @see com.wk.wechat4j.base.api.Pay3Api
	 * @see com.wk.wechat4j.base.payment.mch.WAPPayRequest
	 * @see <a
	 *      href="https://pay.weixin.qq.com/wiki/doc/api/wap.php?chapter=15_1">WAP֧��</a>
	 * @throws WeixinPayException
	 */
	public MchPayRequest createWAPPayRequest(String body, String outTradeNo,
			double totalFee, String notifyUrl, String createIp)
			throws WeixinPayException {
		return pay3Api.createWAPPayRequest(body, outTradeNo, totalFee,
				notifyUrl, createIp);
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
	 * @see com.wk.wechat4j.base.api.Pay3Api
	 * @see {@link #createMicroPay(MicroPayPackage)}
	 * @throws WeixinException
	 */
	public Order createMicroPay(String authCode, String body, String orderNo,
			double orderFee, String createIp) throws WeixinException {
		return pay3Api.createMicroPay(authCode, body, orderNo, orderFee,
				createIp);
	}

	/**
	 * �ύ��ɨ֧��:����Աʹ��ɨ���豸��ȡ΢���û�ˢ����Ȩ���Ժ󣬶�ά���������Ϣ�������̻�����̨�����̻�����̨�����̻���̨���øýӿڷ���֧��.
	 *
	 * @param payPackage
	 *            ������Ϣ
	 * @return ֧���Ķ�����Ϣ
	 * @throws WeixinException
	 * @see com.wk.wechat4j.base.api.Pay3Api
	 * @see com.wk.wechat4j.base.payment.MicroPayPackage
	 * @see com.wk.wechat4j.base.payment.mch.Order
	 * @see <a
	 *      href="http://pay.weixin.qq.com/wiki/doc/api/micropay.php?chapter=9_10">�ύ��ɨ֧��API</a>
	 */
	public Order createMicroPay(MicroPayPackage payPackage)
			throws WeixinException {
		return pay3Api.createMicroPay(payPackage);
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
	 * @since V3
	 * @see com.wk.wechat4j.base.payment.mch.Order
	 * @see com.wk.wechat4j.base.api.Pay3Api
	 * @see <a
	 *      href="http://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=9_2">������ѯAPI</a>
	 * @return ��������
	 * @throws WeixinException
	 */
	public Order orderQuery(IdQuery idQuery) throws WeixinException {
		return pay3Api.orderQuery(idQuery);
	}

	/**
	 * �����˿�(������Ҫ˫��֤��)</br>
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
	 *
	 * @return �˿�������
	 * @see com.wk.wechat4j.base.payment.mch.RefundResult
	 * @see com.wk.wechat4j.base.api.Pay3Api
	 * @see <a
	 *      href="http://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=9_4">�����˿�API</a>
	 * @since V3
	 * @throws WeixinException
	 */
	public com.wk.wechat4j.base.payment.mch.RefundResult refundApply(
			InputStream ca, IdQuery idQuery, String outRefundNo,
			double totalFee, double refundFee, CurrencyType refundFeeType,
			String opUserId) throws WeixinException {
		return pay3Api.refundApply(ca, idQuery, outRefundNo, totalFee,
				refundFee, refundFeeType, opUserId);
	}

	/**
	 * �˿�����(ȫ���˿�)
	 *
	 * @throws IOException
	 *
	 * @see {@link #refundApply(InputStream, IdQuery , String, double, double, String,CurrencyType)}
	 */
	public com.wk.wechat4j.base.payment.mch.RefundResult refundApply(
			IdQuery idQuery, String outRefundNo, double totalFee)
			throws WeixinException, IOException {
		return pay3Api.refundApply(
				new FileInputStream(settings.getCertificateFile0()), idQuery,
				outRefundNo, totalFee);
	}

	/**
	 * �˿��ѯ
	 * <p>
	 * �ύ�˿������ͨ�����øýӿڲ�ѯ�˿�״̬���˿���һ����ʱ������Ǯ֧�����˿�20�����ڵ��ˣ����п�֧�����˿�3�������պ����²�ѯ�˿�״̬��
	 * </p>
	 *
	 * @param idQuery
	 *            ���� refund_id��out_refund_no�� out_trade_no �� transaction_id
	 *            �ĸ���������һ��,���ȼ�Ϊ:
	 *            refund_id>out_refund_no>transaction_id>out_trade_no
	 * @return �˿��¼
	 * @see com.wk.wechat4j.base.api.Pay3Api
	 * @see com.wk.wechat4j.base.payment.mch.RefundRecord
	 * @see <a
	 *      href="http://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=9_5">�˿��ѯAPI</a>
	 * @since V3
	 * @throws WeixinException
	 */
	public RefundRecord refundQuery(IdQuery idQuery) throws WeixinException {
		return pay3Api.refundQuery(idQuery);
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
	 * @return excel���
	 * @since V2 & V3
	 * @see com.wk.wechat4j.base.api.Pay3Api
	 * @see <a
	 *      href="http://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=9_6">���ض��˵�API</a>
	 * @throws WeixinException
	 */
	public File downloadBill(Date billDate, BillType billType)
			throws WeixinException {
		return pay3Api.downloadBill(billDate, billType, settings.getTmpdir0());
	}

	/**
	 * ��������(��Ҫ֤��)</br> ��֧������ʧ��,������ϵͳ��ʱ��Ҫȡ������,���Ե��øýӿ�</br> �ӿ��߼�:֧
	 * ��ʧ�ܵĹص�,֧���ɹ��ĳ���֧��</br> <font color="red">7�����ڵĵ��ɳ���,��������֧���ĵ�
	 * ����ʵ����ͬ����������˿�ӿ�</font></br> <font
	 * color="red">���ÿۿ�ӿں������������ó���,��Ҫ�ȴ�5�����ϡ��ȵ��ò鵥�ӿ�,���û��ȷ�еķ���,�ٵ��ó���</font></br>
	 *
	 * @param ca
	 *            ֤���ļ�(V2�汾��׺Ϊ*.pfx,V3�汾��׺Ϊ*.p12)
	 * @param idQuery
	 *            �̻�ϵͳ�ڲ��Ķ�����, transaction_id �� out_trade_no ��ѡһ,���ͬʱ�������ȼ�:
	 *            transaction_id> out_trade_no
	 * @return �������
	 * @see com.wk.wechat4j.base.api.Pay3Api
	 * @since V3
	 * @throws WeixinException
	 */
	public ApiResult reverseOrder(InputStream ca, IdQuery idQuery)
			throws WeixinException {
		return pay3Api.reverseOrder(ca, idQuery);
	}

	/**
	 * ��������
	 *
	 * @param idQuery
	 *            transaction_id��out_trade_no ��ѡһ
	 * @return �������
	 * @see {@link #reverseOrder(InputStream, IdQuery )}
	 * @throws WeixinException
	 * @throws IOException
	 */
	public ApiResult reverseOrder(IdQuery idQuery) throws WeixinException,
			IOException {
		return pay3Api.reverseOrder(
				new FileInputStream(settings.getCertificateFile0()), idQuery);
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
	 * @return ִ�н��
	 * @see com.wk.wechat4j.base.api.Pay3Api
	 * @since V3
	 * @throws WeixinException
	 * @see <a
	 *      href="http://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=9_3">�رն���API</a>
	 */
	public ApiResult closeOrder(String outTradeNo) throws WeixinException {
		return pay3Api.closeOrder(outTradeNo);
	}

	/**
	 * native֧��URLת������:����ɨ��ԭ��֧��ģʽһ�еĶ�ά������ת�ɶ�����(weixin://wxpay/s/XXXXXX)����С��ά��������
	 * ������ɨ���ٶȺ;�ȷ�ȡ�
	 *
	 * @param url
	 *            ����native��ʶ��֧��URL
	 * @return ת����Ķ�����
	 * @see com.wk.wechat4j.base.api.Pay3Api
	 * @see <a
	 *      href="http://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=9_9">ת��������API</a>
	 * @since V3
	 * @throws WeixinException
	 */
	public String getPayShorturl(String url) throws WeixinException {
		return pay3Api.getShorturl(url);
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
	 * @see com.wk.wechat4j.base.api.Pay3Api
	 * @see <a
	 *      href="http://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=9_8">�ӿڲ����ϱ�API</a>
	 * @throws WeixinException
	 */
	public XmlResult interfaceReport(String interfaceUrl, int executeTime,
			String outTradeNo, String ip, Date time, XmlResult returnXml)
			throws WeixinException {
		return pay3Api.interfaceReport(interfaceUrl, executeTime, outTradeNo,
				ip, time, returnXml);
	}

	/**
	 * ���Ŵ���ȯ(��Ҫ֤��)
	 *
	 * @param ca
	 *            ��׺Ϊ*.p12��֤���ļ�
	 * @param couponStockId
	 *            ����ȯ����id
	 * @param partnerTradeNo
	 *            �̻�����ƾ�ݺţ���ʽ���̻�id+����+��ˮ�ţ����̻����豣��Ψһ��
	 * @param openId
	 *            �û���openid
	 * @param opUserId
	 *            ����Ա�ʺ�, Ĭ��Ϊ�̻��� �����̻�ƽ̨���ò���Ա��Ӧ��apiȨ�� ��Ϊ��
	 * @return ���Ž��
	 * @see com.wk.wechat4j.base.api.CouponApi
	 * @see com.wk.wechat4j.base.payment.coupon.CouponResult
	 * @see <a
	 *      href="http://pay.weixin.qq.com/wiki/doc/api/sp_coupon.php?chapter=12_3">���Ŵ���ȯ�ӿ�</a>
	 * @throws WeixinException
	 */
	public CouponResult sendCoupon(InputStream ca, String couponStockId,
			String partnerTradeNo, String openId, String opUserId)
			throws WeixinException {
		return couponApi.sendCoupon(ca, couponStockId, partnerTradeNo, openId,
				opUserId);
	}

	/**
	 * ���Ŵ���ȯ
	 *
	 * @see {@link com.wk.wechat4j.base.payment.WeixinPayProxy#sendCoupon(InputStream, String, String, String, String)}
	 */
	public CouponResult sendCoupon(String couponStockId, String partnerTradeNo,
			String openId) throws WeixinException, IOException {
		return couponApi.sendCoupon(
				new FileInputStream(settings.getCertificateFile0()),
				couponStockId, partnerTradeNo, openId, null);
	}

	/**
	 * ��ѯ����ȯ����
	 *
	 * @param couponStockId
	 *            ����ȯ����ID
	 * @return ����ȯ������Ϣ
	 * @see com.wk.wechat4j.base.api.CouponApi
	 * @see com.wk.wechat4j.base.payment.coupon.CouponStock
	 * @see <a
	 *      href="http://pay.weixin.qq.com/wiki/doc/api/sp_coupon.php?chapter=12_4">��ѯ����ȯ��Ϣ</a>
	 * @throws WeixinException
	 */
	public CouponStock queryCouponStock(String couponStockId)
			throws WeixinException {
		return couponApi.queryCouponStock(couponStockId);
	}

	/**
	 * ��ѯ����ȯ��ϸ
	 *
	 * @param couponId
	 *            ����ȯID
	 * @return ����ȯ��ϸ��Ϣ
	 * @see com.wk.wechat4j.base.api.CouponApi
	 * @see com.wk.wechat4j.base.payment.coupon.CouponDetail
	 * @see <a
	 *      href="http://pay.weixin.qq.com/wiki/doc/api/sp_coupon.php?chapter=12_5">��ѯ����ȯ��ϸ��Ϣ</a>
	 * @throws WeixinException
	 */
	public CouponDetail queryCouponDetail(String couponId)
			throws WeixinException {
		return couponApi.queryCouponDetail(couponId);
	}

	/**
	 * ���ź�� ��ҵ��΢���û����˷��ֽ���
	 *
	 * @param ca
	 *            ��׺Ϊ*.p12��֤���ļ�
	 * @param redpacket
	 *            �����Ϣ
	 * @return ���Ž��
	 * @see com.wk.wechat4j.base.api.CashApi
	 * @see com.wk.wechat4j.base.payment.mch.Redpacket
	 * @see com.wk.wechat4j.base.payment.mch.RedpacketSendResult
	 * @see <a
	 *      href="http://pay.weixin.qq.com/wiki/doc/api/cash_coupon.php?chapter=13_5">����ӿ�˵��</a>
	 * @throws WeixinException
	 */
	public RedpacketSendResult sendRedpack(InputStream ca, Redpacket redpacket)
			throws WeixinException {
		return cashApi.sendRedpack(ca, redpacket);
	}

	/**
	 * ���ź��
	 *
	 * @see {@link com.wk.wechat4j.base.payment.WeixinPayProxy#sendRedpack(InputStream, Redpacket)}
	 */
	public RedpacketSendResult sendRedpack(Redpacket redpacket)
			throws WeixinException, IOException {
		return cashApi.sendRedpack(
				new FileInputStream(settings.getCertificateFile0()), redpacket);
	}

	/**
	 * ��ѯ�����¼
	 *
	 * @param ca
	 *            ��׺Ϊ*.p12��֤���ļ�
	 * @param outTradeNo
	 *            �̻����ź�����̻�������
	 * @return �����¼
	 * @see com.wk.wechat4j.base.api.CashApi
	 * @see com.wk.wechat4j.base.payment.mch.RedpacketRecord
	 * @see <a
	 *      href="http://pay.weixin.qq.com/wiki/doc/api/cash_coupon.php?chapter=13_6">��ѯ����ӿ�˵��</a>
	 * @throws WeixinException
	 */
	public RedpacketRecord queryRedpack(InputStream ca, String outTradeNo)
			throws WeixinException {
		return cashApi.queryRedpack(ca, outTradeNo);
	}

	/**
	 * ��ѯ���
	 *
	 * @see {@link com.wk.wechat4j.base.payment.WeixinPayProxy#queryRedpack(InputStream,String)}
	 */
	public RedpacketRecord queryRedpack(String outTradeNo)
			throws WeixinException, IOException {
		return cashApi
				.queryRedpack(
						new FileInputStream(settings.getCertificateFile0()),
						outTradeNo);
	}

	/**
	 * ��ҵ���� ʵ����ҵ����˸����Բ����п����������̻��� �ṩͨ��API�����ҵ����Ĺ��ܡ� ����Ŀǰ�ı�����ҵ��ͻ��˱������������⡣
	 *
	 * @param ca
	 *            ��׺Ϊ*.p12��֤���ļ�
	 * @param mpPayment
	 *            ������Ϣ
	 * @return ������
	 * @see com.wk.wechat4j.base.api.CashApi
	 * @see com.wk.wechat4j.base.payment.mch.MPPayment
	 * @see com.wk.wechat4j.base.payment.mch.MPPaymentResult
	 * @see <a
	 *      href="http://pay.weixin.qq.com/wiki/doc/api/mch_pay.php?chapter=14_1">��ҵ����</a>
	 * @throws WeixinException
	 */
	public MPPaymentResult mpPayment(InputStream ca, MPPayment mpPayment)
			throws WeixinException {
		return cashApi.mchPayment(ca, mpPayment);
	}

	/**
	 * ��ҵ����
	 *
	 * @see {@link com.wk.wechat4j.base.payment.WeixinPayProxy#mpPayment(InputStream, MPPayment)}
	 */
	public MPPaymentResult mpPayment(MPPayment mpPayment)
			throws WeixinException, IOException {
		return cashApi.mchPayment(
				new FileInputStream(settings.getCertificateFile0()), mpPayment);
	}

	/**
	 * ��ҵ�����ѯ �����̻�����ҵ����������н����ѯ�����ظ��������ϸ���
	 *
	 * @param ca
	 *            ��׺Ϊ*.p12��֤���ļ�
	 * @param outTradeNo
	 *            �̻�������ҵ����APIʱʹ�õ��̻�������
	 * @return �����¼
	 * @see com.wk.wechat4j.base.api.CashApi
	 * @see com.wk.wechat4j.base.payment.mch.MPPaymentRecord
	 * @see <a
	 *      href="http://pay.weixin.qq.com/wiki/doc/api/mch_pay.php?chapter=14_3">��ҵ�����ѯ</a>
	 * @throws WeixinException
	 */
	public MPPaymentRecord mpPaymentQuery(InputStream ca, String outTradeNo)
			throws WeixinException {
		return cashApi.mchPaymentQuery(ca, outTradeNo);
	}

	/**
	 * ��ҵ�����ѯ
	 *
	 * @see {@link com.wk.wechat4j.base.payment.WeixinPayProxy#mpPaymentQuery(InputStream, String)}
	 */
	public MPPaymentRecord mpPaymentQuery(String outTradeNo)
			throws WeixinException, IOException {
		return cashApi
				.mchPaymentQuery(
						new FileInputStream(settings.getCertificateFile0()),
						outTradeNo);
	}

	/**
	 * ��Ȩ���ѯOPENID�ӿ�
	 *
	 * @param authCode
	 *            ɨ��֧����Ȩ�룬�豸��ȡ�û�΢���е�������߶�ά����Ϣ
	 * @return ��ѯ���
	 * @see com.wk.wechat4j.base.api.CashApi
	 * @see com.wk.wechat4j.base.payment.mch.OpenIdResult
	 * @see <a
	 *      href="https://pay.weixin.qq.com/wiki/doc/api/micropay.php?chapter=9_13&index=9">��Ȩ���ѯOPENID</a>
	 * @throws WeixinException
	 */
	public OpenIdResult authCode2openId(String authCode) throws WeixinException {
		return pay3Api.authCode2openId(authCode);
	}

	public final static String VERSION = "1.6.7";
}
