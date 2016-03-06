package com.wk.wechat4j.qy.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;
import com.alibaba.fastjson.parser.deserializer.ExtraProcessor;
import com.alibaba.fastjson.parser.deserializer.ParseProcess;
import com.alibaba.fastjson.serializer.NameFilter;
import com.wk.wechat4j.base.exception.WeixinException;
import com.wk.wechat4j.base.http.weixin.JsonResult;
import com.wk.wechat4j.base.http.weixin.WeixinResponse;
import com.wk.wechat4j.base.model.Button;
import com.wk.wechat4j.base.model.Token;
import com.wk.wechat4j.base.token.TokenHolder;
import com.wk.wechat4j.base.type.ButtonType;

import java.util.ArrayList;
import java.util.List;

/**
 * �˵����API
 *
 * @className MenuApi
 * @author jy.hu
 * @date 2014��9��25��
 * @since JDK 1.6
 * @see com.wk.wechat4j.base.model.Button
 */
public class MenuApi extends QyApi {

	private final TokenHolder tokenHolder;

	public MenuApi(TokenHolder tokenHolder) {
		this.tokenHolder = tokenHolder;
	}

	/**
	 * �Զ���˵�(����Ա��ӵ��Ӧ�õĹ���Ȩ�� ����Ӧ�ñ��������ڻص�ģʽ)
	 *
	 * @param buttons
	 *            �˵��б�
	 * @param agentid
	 *            Ӧ��ID
	 * @throws WeixinException
	 * @see <a
	 *      href="http://qydev.weixin.qq.com/wiki/index.php?title=%E5%88%9B%E5%BB%BA%E5%BA%94%E7%94%A8%E8%8F%9C%E5%8D%95">�����Զ���˵�</a>
	 * @see com.wk.wechat4j.base.model.Button
	 */
	public JsonResult createMenu(List<Button> buttons, int agentid)
			throws WeixinException {
		String menu_create_uri = getRequestUri("menu_create_uri");
		Token token = tokenHolder.getToken();
		JSONObject obj = new JSONObject();
		obj.put("button", buttons);
		WeixinResponse response = weixinExecutor
				.post(String.format(menu_create_uri, token.getAccessToken(),
						agentid), JSON.toJSONString(obj, new NameFilter() {
					@Override
					public String process(Object object, String name,
							Object value) {
						if (object instanceof Button && name.equals("content")) {
							ButtonType buttonType = ((Button) object).getType();
							if (buttonType != null) {
								if (ButtonType.view == buttonType) {
									return "url";
								} else if (ButtonType.media_id == buttonType
										|| ButtonType.view_limited == buttonType) {
									return "media_id";
								} else {
									return "key";
								}
							}
						}
						return name;
					}
				}));

		return response.getAsJsonResult();
	}

	/**
	 * ��ѯ�˵�(����Ա��ӵ��Ӧ�õĹ���Ȩ�� ����Ӧ�ñ��������ڻص�ģʽ��)
	 *
	 * @param agentid
	 *            Ӧ��ID
	 * @return �˵�����
	 * @throws WeixinException
	 * @see <a
	 *      href="http://qydev.weixin.qq.com/wiki/index.php?title=%E8%8E%B7%E5%8F%96%E8%8F%9C%E5%8D%95%E5%88%97%E8%A1%A8">��ѯ�˵�</a>
	 * @see com.wk.wechat4j.base.model.Button
	 */
	public List<Button> getMenu(int agentid) throws WeixinException {
		String menu_get_uri = getRequestUri("menu_get_uri");
		Token token = tokenHolder.getToken();
		WeixinResponse response = weixinExecutor.get(String.format(menu_get_uri,
				token.getAccessToken(), agentid));

		JSONArray buttons = response.getAsJson().getJSONObject("menu")
				.getJSONArray("button");
		List<Button> buttonList = new ArrayList<Button>(buttons.size());
		ParseProcess processor = new ExtraProcessor() {
			@Override
			public void processExtra(Object object, String key, Object value) {
				JSONPath.set(object, "$.content", value);
			}
		};
		for (int i = 0; i < buttons.size(); i++) {
			buttonList.add(JSON.parseObject(buttons.getString(i), Button.class,
					processor));
		}
		return buttonList;
	}

	/**
	 * ɾ���˵�(����Ա��ӵ��Ӧ�õĹ���Ȩ�� ����Ӧ�ñ��������ڻص�ģʽ)
	 *
	 * @param agentid
	 *            Ӧ��ID
	 * @throws WeixinException
	 * @see <a
	 *      href="http://qydev.weixin.qq.com/wiki/index.php?title=%E5%88%A0%E9%99%A4%E8%8F%9C%E5%8D%95">ɾ���˵�</a>
	 * @return ������
	 */
	public JsonResult deleteMenu(int agentid) throws WeixinException {
		String menu_delete_uri = getRequestUri("menu_delete_uri");
		Token token = tokenHolder.getToken();
		WeixinResponse response = weixinExecutor.get(String.format(
				menu_delete_uri, token.getAccessToken(), agentid));

		return response.getAsJsonResult();
	}
}
