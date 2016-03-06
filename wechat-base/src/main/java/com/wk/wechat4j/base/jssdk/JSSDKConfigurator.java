package com.wk.wechat4j.base.jssdk;

import com.alibaba.fastjson.JSONObject;
import com.wk.wechat4j.base.exception.WeixinException;
import com.wk.wechat4j.base.token.TokenHolder;
import com.wk.wechat4j.base.util.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * JSSDK������
 *
 * @className JSSDKConfigurator
 * @author jy
 * @date 2015��12��23��
 * @since JDK 1.6
 * @see
 */
public class JSSDKConfigurator {
	private final TokenHolder ticketTokenHolder;
	private JSONObject config;
	private Set<JSSDKAPI> apis;

	/**
	 * ticket������ �ɵ���WeixinProxy#getTicketHolder��ȡ
	 *
	 * @param ticketTokenHolder
	 */
	public JSSDKConfigurator(TokenHolder ticketTokenHolder) {
		this.ticketTokenHolder = ticketTokenHolder;
		this.config = new JSONObject();
		this.apis = new HashSet<JSSDKAPI>();
	}

	/**
	 * ��������ģʽ,���õ�����api�ķ���ֵ���ڿͻ���alert��������Ҫ�鿴����Ĳ�����������pc�˴򿪣�������Ϣ��ͨ��log�����
	 * ����pc��ʱ�Ż��ӡ��
	 *
	 * @return
	 */
	public JSSDKConfigurator debugMode() {
		config.put("debug", true);
		return this;
	}

	/**
	 * ���ںŵ�Ψһ��ʶ �������ȡweixin4j.properties#account�е�id
	 *
	 * @param appId
	 * @return
	 */
	public JSSDKConfigurator appId(String appId) {
		config.put("appId", appId);
		return this;
	}

	/**
	 * ��Ҫʹ�õ�JS�ӿ��б�
	 *
	 * @see JSSDKAPI
	 * @param apis
	 * @return
	 */
	public JSSDKConfigurator apis(JSSDKAPI... apis) {
		for (JSSDKAPI api : apis) {
			this.apis.add(api);
		}
		return this;
	}

	/**
	 * ��Ҫʹ�õ�JS�ӿ��б�
	 *
	 * @see JSSDKAPI
	 * @param apis
	 * @return
	 */
	public JSSDKConfigurator apis(JSSDKAPI[]... apis) {
		for (JSSDKAPI[] api : apis) {
			for (JSSDKAPI apii : api) {
				this.apis.add(apii);
			}
		}
		return this;
	}

	/**
	 * ����config����JSON��
	 *
	 * @param url
	 *            ��ǰ��ҳ��URL��������#������沿��
	 * @return
	 * @see <a
	 *      href="http://mp.weixin.qq.com/wiki/11/74ad127cc054f6b80759c40f77ec03db.html#.E6.AD.A5.E9.AA.A4.E4.B8.89.EF.BC.9A.E9.80.9A.E8.BF.87config.E6.8E.A5.E5.8F.A3.E6.B3.A8.E5.85.A5.E6.9D.83.E9.99.90.E9.AA.8C.E8.AF.81.E9.85.8D.E7.BD.AE">ͨ��config�ӿ�ע��Ȩ����֤����</a>
	 * @throws WeixinException
	 */
	public String toJSONConfig(String url) throws WeixinException {
		Map<String, String> signMap = new HashMap<String, String>();
		String timestamp = DateUtil.timestamp2string();
		String noncestr = RandomUtil.generateString(24);
		signMap.put("timestamp", timestamp);
		signMap.put("noncestr", noncestr);
		signMap.put("jsapi_ticket", this.ticketTokenHolder.getAccessToken());
		signMap.put("url", url);
		String sign = DigestUtil.SHA1(MapUtil.toJoinString(signMap, false,
				false));
		if (StringUtil.isBlank(config.getString("appId"))) {
			config.put("appId", Weixin4jConfigUtil.getWeixinAccount().getId());
		}
		if (StringUtil.isBlank(config.getString("debug"))) {
			config.put("debug", false);
		}
		if (apis.isEmpty()) {
			throw new WeixinException("jsapilist not be empty");
		}
		config.put("timestamp", timestamp);
		config.put("nonceStr", noncestr);
		config.put("signature", sign);
		config.put("jsApiList", apis.toArray());
		return config.toJSONString();
	}
}
