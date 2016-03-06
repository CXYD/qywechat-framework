package com.wk.wechat4j.qy.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wk.wechat4j.base.exception.WeixinException;
import com.wk.wechat4j.base.http.weixin.JsonResult;
import com.wk.wechat4j.base.http.weixin.WeixinResponse;
import com.wk.wechat4j.base.model.Token;
import com.wk.wechat4j.base.token.TokenHolder;
import com.wk.wechat4j.base.util.NameValue;
import com.wk.wechat4j.base.util.StringUtil;
import com.wk.wechat4j.qy.model.OUserInfo;
import com.wk.wechat4j.qy.model.User;
import com.wk.wechat4j.qy.type.InviteType;
import com.wk.wechat4j.qy.type.UserStatus;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * ��ԱAPI
 *
 * @className UserApi
 * @author jy
 * @date 2014��11��19��
 * @since JDK 1.6
 * @see com.wk.wechat4j.qy.model.User
 * @see <a href=
 *      "http://qydev.weixin.qq.com/wiki/index.php?title=%E7%AE%A1%E7%90%86%E6%88%90%E5%91%98">
 *      �����Ա˵��</a>
 */
public class UserApi extends QyApi {
	private final MediaApi mediaApi;
	private final TokenHolder tokenHolder;

	public UserApi(TokenHolder tokenHolder) {
		this.tokenHolder = tokenHolder;
		this.mediaApi = new MediaApi(tokenHolder);
	}

	/**
	 * ������Ա
	 *
	 * @param user
	 *            ��Ա����
	 * @see com.wk.wechat4j.qy.model.User
	 * @see <a href=
	 *      "http://qydev.weixin.qq.com/wiki/index.php?title=%E7%AE%A1%E7%90%86%E6%88%90%E5%91%98#.E5.88.9B.E5.BB.BA.E6.88.90.E5.91.98">
	 *      ������Ա˵��</a>
	 * @return ������
	 * @throws WeixinException
	 */
	public JsonResult createUser(User user) throws WeixinException {
		String user_create_uri = getRequestUri("user_create_uri");
		return excute(user_create_uri, user, null);
	}

	/**
	 * ������Ա
	 *
	 * @param user
	 *            ��Ա����
	 * @param avatar
	 *            ͷ���ļ� ��Ϊ��
	 * @see com.wk.wechat4j.qy.model.User
	 * @see <a href=
	 *      "http://qydev.weixin.qq.com/wiki/index.php?title=%E7%AE%A1%E7%90%86%E6%88%90%E5%91%98#.E5.88.9B.E5.BB.BA.E6.88.90.E5.91.98">
	 *      ������Ա˵��</a>
	 * @return ������
	 * @throws WeixinException
	 */
	public JsonResult createUser(User user, InputStream avatar)
			throws WeixinException {
		String user_create_uri = getRequestUri("user_create_uri");
		return excute(user_create_uri, user, avatar);
	}

	/**
	 * �����û�(����Ǳ�����ֶ�δָ�� �򲻸��¸��ֶ�֮ǰ������ֵ)
	 *
	 * @param user
	 *            ��Ա����
	 * @see com.wk.wechat4j.qy.model.User
	 * @see <a href=
	 *      "http://qydev.weixin.qq.com/wiki/index.php?title=%E7%AE%A1%E7%90%86%E6%88%90%E5%91%98#.E6.9B.B4.E6.96.B0.E6.88.90.E5.91.98">
	 *      ���³�Ա˵��</a>
	 * @return ������
	 * @throws WeixinException
	 */
	public JsonResult updateUser(User user) throws WeixinException {
		String user_update_uri = getRequestUri("user_update_uri");
		return excute(user_update_uri, user, null);
	}

	/**
	 * �����û�(����Ǳ�����ֶ�δָ�� �򲻸��¸��ֶ�֮ǰ������ֵ)
	 *
	 * @param user
	 *            ��Ա����
	 * @param avatar
	 *            ͷ���ļ�
	 * @see com.wk.wechat4j.qy.model.User
	 * @see <a href=
	 *      "http://qydev.weixin.qq.com/wiki/index.php?title=%E7%AE%A1%E7%90%86%E6%88%90%E5%91%98#.E6.9B.B4.E6.96.B0.E6.88.90.E5.91.98">
	 *      ���³�Ա˵��</a>
	 * @return ������
	 * @throws WeixinException
	 */
	public JsonResult updateUser(User user, InputStream avatar)
			throws WeixinException {
		String user_update_uri = getRequestUri("user_update_uri");
		return excute(user_update_uri, user, avatar);
	}

	private JsonResult excute(String uri, User user, InputStream avatar)
			throws WeixinException {
		JSONObject obj = (JSONObject) JSON.toJSON(user);
		Object val = obj.remove("extattr");
		if (val != null) {
			JSONObject attrs = new JSONObject();
			attrs.put("attrs", val);
			obj.put("extattr", attrs);
		}
		val = obj.remove("status");
		if (val != null) {
			obj.put("enable", val);
		}
		if (avatar != null) {
			obj.put("avatar_mediaid", mediaApi.uploadMedia(0, avatar, null));
		} else {
			obj.put("avatar_mediaid", obj.remove("avatar"));
		}
		Token token = tokenHolder.getToken();
		WeixinResponse response = weixinExecutor.post(
				String.format(uri, token.getAccessToken()), obj.toJSONString());
		return response.getAsJsonResult();
	}

	/**
	 * ��ȡ��Ա
	 *
	 * @param userid
	 *            ��ԱΨһID
	 * @see com.wk.wechat4j.qy.model.User
	 * @see <a href=
	 *      "http://qydev.weixin.qq.com/wiki/index.php?title=%E7%AE%A1%E7%90%86%E6%88%90%E5%91%98#.E8.8E.B7.E5.8F.96.E6.88.90.E5.91.98">
	 *      ��ȡ��Ա˵��</a>
	 * @return ��Ա����
	 * @throws WeixinException
	 */
	public User getUser(String userid) throws WeixinException {
		String user_get_uri = getRequestUri("user_get_uri");
		Token token = tokenHolder.getToken();
		WeixinResponse response = weixinExecutor.get(String.format(
				user_get_uri, token.getAccessToken(), userid));
		JSONObject obj = response.getAsJson();
		Object attrs = obj.remove("extattr");
		User user = JSON.toJavaObject(obj, User.class);
		if (attrs != null) {
			user.setExtattr(JSON.parseArray(
					((JSONObject) attrs).getString("attrs"), NameValue.class));
		}
		return user;
	}

	/**
	 * ����code��ȡ�û���Ϣ
	 *
	 * @param code
	 *            ͨ��Ա����Ȩ��ȡ����code��ÿ��Ա����Ȩ���ϵ�code����һ����codeֻ��ʹ��һ�Σ�5����δ��ʹ���Զ�����
	 * @see com.wk.wechat4j.qy.model.User
	 * @return ��Ա����
	 * @see {@link #getUser(String)}
	 * @see {@link #getUserIdByCode(String,int)}
	 * @see <a href=
	 *      "http://qydev.weixin.qq.com/wiki/index.php?title=OAuth%E9%AA%8C%E8%AF%81%E6%8E%A5%E5%8F%A3">
	 *      oauth��Ȩ��ȡ�û���Ϣ</a>
	 * @throws WeixinException
	 */
	public User getUserByCode(String code) throws WeixinException {
		String[] userIds = getUserIdByCode(code);
		if (Boolean.parseBoolean(userIds[2])) {
			return getUser(openid2userid(userIds[0]));
		} else {
			return getUser(userIds[0]);
		}
	}

	/**
	 * ����code��ȡ��ԱID��Ϣ
	 *
	 * @param code
	 *            ͨ��Ա����Ȩ��ȡ����code��ÿ��Ա����Ȩ���ϵ�code����һ����codeֻ��ʹ��һ�Σ�5����δ��ʹ���Զ�����
	 * @return ����Ԫ�ص����� <font color="red">��һ��Ԫ��ΪuserId����openId
	 *         �ڶ���Ԫ��ΪdeviceId</font>
	 * @see <a href=
	 *      "http://qydev.weixin.qq.com/wiki/index.php?title=OAuth%E9%AA%8C%E8%AF%81%E6%8E%A5%E5%8F%A3">
	 *      oauth��Ȩ��ȡ�û���Ϣ</a>
	 * @throws WeixinException
	 */
	public String[] getUserIdByCode(String code) throws WeixinException {
		String user_getid_uri = getRequestUri("user_getid_uri");
		Token token = tokenHolder.getToken();
		WeixinResponse response = weixinExecutor.get(String.format(
				user_getid_uri, token.getAccessToken(), code));
		JSONObject result = response.getAsJson();
		String userId = result.getString("UserId");
		boolean need2 = false;
		if (StringUtil.isBlank(userId)) {
			userId = result.getString("OpenId");
			need2 = true;
		}
		return new String[] { userId, result.getString("DeviceId"),
				Boolean.toString(need2) };
	}

	/**
	 * ��ȡ��ҵ�Ź���Ա��¼��Ϣ
	 *
	 * @param providerToken
	 *            �ṩ�̵�token
	 * @param authCode
	 *            oauth2.0��Ȩ��ҵ�Ź���Ա��¼������code
	 * @return ��½��Ϣ
	 * @see <a href=
	 *      "http://qydev.weixin.qq.com/wiki/index.php?title=%E8%8E%B7%E5%8F%96%E4%BC%81%E4%B8%9A%E7%AE%A1%E7%90%86%E5%91%98%E7%99%BB%E5%BD%95%E4%BF%A1%E6%81%AF">
	 *      ��Ȩ��ȡ��ҵ�Ź���Ա��¼��Ϣ</a>
	 * @see com.wk.wechat4j.qy.model.OUserInfo
	 * @throws WeixinException
	 */
	public OUserInfo getOUserInfoByCode(String providerToken, String authCode)
			throws WeixinException {
		String oauth_logininfo_uri = getRequestUri("oauth_logininfo_uri");
		WeixinResponse response = weixinExecutor.post(
				String.format(oauth_logininfo_uri, providerToken),
				String.format("{\"auth_code\":\"%s\"}", authCode));
		return JSON.parseObject(response.getAsString(), OUserInfo.class);
	}

	/**
	 * ��ȡ���ų�Ա
	 *
	 * @param partyId
	 *            ����ID ����
	 * @param fetchChild
	 *            �Ƿ�ݹ��ȡ�Ӳ�������ĳ�Ա �Ǳ���
	 * @param userStatus
	 *            ��Ա״̬ status�ɵ��� �Ǳ��� δ��д��Ĭ��Ϊδ��ע(4)
	 * @param findDetail
	 *            �Ƿ��ȡ��ϸ��Ϣ
	 * @see com.wk.wechat4j.qy.model.User
	 * @see <a href=
	 *      "http://qydev.weixin.qq.com/wiki/index.php?title=%E7%AE%A1%E7%90%86%E6%88%90%E5%91%98#.E8.8E.B7.E5.8F.96.E9.83.A8.E9.97.A8.E6.88.90.E5.91.98">
	 *      ��ȡ���ų�Ա˵��</a>
	 * @return ��Ա�б�
	 * @throws WeixinException
	 */
	public List<User> listUser(int departId, boolean fetchChild,
			UserStatus userStatus, boolean findDetail) throws WeixinException {
		String user_list_uri = findDetail ? getRequestUri("user_list_uri")
				: getRequestUri("user_slist_uri");
		Token token = tokenHolder.getToken();
		if (userStatus == null) {
			userStatus = UserStatus.UNFOLLOW;
		}
		WeixinResponse response = weixinExecutor.get(String.format(
				user_list_uri, token.getAccessToken(), departId, fetchChild ? 1
						: 0, userStatus.getVal()));
		List<User> list = null;
		if (findDetail) {
			JSONArray arrays = response.getAsJson().getJSONArray("userlist");
			list = new ArrayList<User>(arrays.size());
			for (int i = 0; i < arrays.size(); i++) {
				JSONObject obj = arrays.getJSONObject(i);
				Object attrs = obj.remove("extattr");
				User user = JSON.toJavaObject(obj, User.class);
				if (attrs != null) {
					user.setExtattr(JSON.parseArray(
							((JSONObject) attrs).getString("attrs"),
							NameValue.class));
				}
				list.add(user);
			}
		} else {
			list = JSON.parseArray(response.getAsJson().getString("userlist"),
					User.class);
		}
		return list;
	}

	/**
	 * ��ȡ����������״̬��Ա(�����еݹ�)
	 *
	 * @param departId
	 *            ����ID
	 * @see {@link #listUser(int, boolean, UserStatus )}
	 * @return ��Ա�б�
	 * @throws WeixinException
	 */
	public List<User> listUser(int departId) throws WeixinException {
		return listUser(departId, false, UserStatus.BOTH, false);
	}

	/**
	 * ɾ����Ա
	 *
	 * @param userid
	 *            ��ԱID
	 * @see <a href=
	 *      "http://qydev.weixin.qq.com/wiki/index.php?title=%E7%AE%A1%E7%90%86%E6%88%90%E5%91%98#.E5.88.A0.E9.99.A4.E6.88.90.E5.91.98">
	 *      ɾ����Ա˵��</a>
	 * @return ������
	 * @throws WeixinException
	 */
	public JsonResult deleteUser(String userid) throws WeixinException {
		String user_delete_uri = getRequestUri("user_delete_uri");
		Token token = tokenHolder.getToken();
		WeixinResponse response = weixinExecutor.get(String.format(
				user_delete_uri, token.getAccessToken(), userid));
		return response.getAsJsonResult();
	}

	/**
	 * ����ɾ����Ա
	 *
	 * @param userIds
	 *            ��Ա�б�
	 * @see <a href=
	 *      "http://qydev.weixin.qq.com/wiki/index.php?title=%E7%AE%A1%E7%90%86%E6%88%90%E5%91%98#.E6.89.B9.E9.87.8F.E5.88.A0.E9.99.A4.E6.88.90.E5.91.98"
	 *      >����ɾ����Ա˵��</a>
	 * @return ������
	 * @throws WeixinException
	 */
	public JsonResult batchDeleteUser(List<String> userIds)
			throws WeixinException {
		JSONObject obj = new JSONObject();
		obj.put("useridlist", userIds);
		String user_delete_uri = getRequestUri("user_batchdelete_uri");
		Token token = tokenHolder.getToken();
		WeixinResponse response = weixinExecutor.post(String.format(
				user_delete_uri, token.getAccessToken(), obj.toJSONString()));
		return response.getAsJsonResult();
	}

	/**
	 * ����������֤�ɹ�ʱ����(����Ա��ӵ��userid��ӦԱ���Ĺ���Ȩ��)
	 *
	 * @param userid
	 *            ��ԱID
	 * @return ���ý��
	 * @see <a href=
	 *      "http://qydev.weixin.qq.com/wiki/index.php?title=%E5%85%B3%E6%B3%A8%E4%B8%8E%E5%8F%96%E6%B6%88%E5%85%B3%E6%B3%A8">
	 *      ������֤˵��</a>
	 * @throws WeixinException
	 */
	public JsonResult authsucc(String userId) throws WeixinException {
		String user_authsucc_uri = getRequestUri("user_authsucc_uri");
		Token token = tokenHolder.getToken();
		WeixinResponse response = weixinExecutor.get(String.format(
				user_authsucc_uri, token.getAccessToken(), userId));
		return response.getAsJsonResult();
	}

	/**
	 * �����Ա��ע(����Ա��ӵ�иó�Ա�Ĳ鿴Ȩ��)
	 *
	 * @param userId
	 *            ��ԱID
	 * @param tips
	 *            ���͵�΢���ϵ���ʾ�ֻ����֤�ſ���ʹ�ã�����ʹ��΢������ʱ�����ֶ�Ĭ��Ϊ�����עXXX��ҵ�š����ʼ�����ʱ�����ֶ���Ч��
	 * @return ��������
	 * @see <a href=
	 *      "http://qydev.weixin.qq.com/wiki/index.php?title=%E7%AE%A1%E7%90%86%E6%88%90%E5%91%98#.E9.82.80.E8.AF.B7.E6.88.90.E5.91.98.E5.85.B3.E6.B3.A8">
	 *      �����Ա��ע˵��</a>
	 * @throws WeixinException
	 */
	public InviteType inviteUser(String userId, String tips)
			throws WeixinException {
		JSONObject obj = new JSONObject();
		obj.put("userid", userId);
		obj.put("invite_tips", tips);
		String invite_user_uri = getRequestUri("invite_user_uri");
		Token token = tokenHolder.getToken();
		WeixinResponse response = weixinExecutor.post(
				String.format(invite_user_uri, token.getAccessToken()),
				obj.toJSONString());
		int type = response.getAsJson().getIntValue("type");
		if (type == 1) {
			return InviteType.WEIXIN;
		} else if (type == 2) {
			return InviteType.EMAIL;
		} else {
			return null;
		}
	}

	/**
	 * useridת����openid:�ýӿ�ʹ�ó���Ϊ΢��֧����΢�ź������ҵת�ˣ���ҵ���û���ʹ��΢��֧���Ĺ���ʱ��
	 * ��Ҫ���н���ҵ�ŵ�useridת��openid�� ��ʹ��΢�ź������ʱ����Ҫ��Ӧ��id��useridת��appid��openid����ʹ�á�
	 *
	 * @param userid
	 *            ��ҵ���ڵĳ�Աid ����
	 * @param agentid
	 *            ��Ҫ���ͺ����Ӧ��ID����ֻ��ʹ��΢��֧������ҵת�ˣ�������ò��� ����0���������
	 * @return ������� ��һ��Ԫ��Ϊ��Ӧ��openid �ڶ���Ԫ����ΪӦ�õ�appid(�����)
	 * @throws WeixinException
	 * @see <a href=
	 *      "http://qydev.weixin.qq.com/wiki/index.php?title=Userid%E4%B8%8Eopenid%E4%BA%92%E6%8D%A2%E6%8E%A5%E5%8F%A3">
	 *      useridת����openid</a>
	 */
	public String[] userid2openid(String userid, int agentid)
			throws WeixinException {
		JSONObject obj = new JSONObject();
		obj.put("userid", userid);
		if (agentid > 0) {
			obj.put("agentid", agentid);
		}
		String userid2openid_uri = getRequestUri("userid2openid_uri");
		WeixinResponse response = weixinExecutor.post(
				String.format(userid2openid_uri, tokenHolder.getAccessToken()),
				obj.toJSONString());
		obj = response.getAsJson();
		return new String[] { obj.getString("openid"), obj.getString("appid") };
	}

	/**
	 * openidת����userid:�ýӿ���ҪӦ����ʹ��΢��֧����΢�ź������ҵת��֮��Ľ����ѯ��
	 * ��������Ҫ֪��ĳ������¼���openid��Ӧ��ҵ���ڳ�Ա����Ϣʱ������ͨ�����øýӿڽ���ת����ѯ��
	 *
	 * @param openid
	 *            ��ʹ��΢��֧����΢�ź������ҵת��֮�󣬷��ؽ����openid
	 * @return ��openid����ҵ���ж�Ӧ�ĳ�Աuserid
	 * @throws WeixinException
	 * @see <a href=
	 *      "http://qydev.weixin.qq.com/wiki/index.php?title=Userid%E4%B8%8Eopenid%E4%BA%92%E6%8D%A2%E6%8E%A5%E5%8F%A3">
	 *      openidת����userid</a>
	 */
	public String openid2userid(String openid) throws WeixinException {
		String openid2userid_uri = getRequestUri("openid2userid_uri");
		WeixinResponse response = weixinExecutor.post(
				String.format(openid2userid_uri, tokenHolder.getAccessToken()),
				String.format("{\"openid\": \"%s\"}", openid));
		return response.getAsJson().getString("userid");
	}
}
