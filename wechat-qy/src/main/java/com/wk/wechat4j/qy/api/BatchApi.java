package com.wk.wechat4j.qy.api;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.wk.wechat4j.base.exception.WeixinException;
import com.wk.wechat4j.base.http.weixin.WeixinResponse;
import com.wk.wechat4j.base.model.Token;
import com.wk.wechat4j.base.token.TokenHolder;
import com.wk.wechat4j.qy.model.BatchResult;
import com.wk.wechat4j.qy.model.Callback;
import com.wk.wechat4j.qy.model.IdParameter;

/**
 * �����첽����API
 * <p>
 * �첽����ӿ����ڴ��������ݵĴ����ύ��ӿڼ����أ���ҵ�Ż��ں�̨����ִ������ִ����ɺ�ͨ�������¼�֪ͨ��ҵ��ȡ���
 * </p>
 *
 * @className BatchApi
 * @author jy
 * @date 2015��3��30��
 * @since JDK 1.6
 * @see <a
 *      href="http://qydev.weixin.qq.com/wiki/index.php?title=%E5%BC%82%E6%AD%A5%E4%BB%BB%E5%8A%A1%E6%8E%A5%E5%8F%A3">��������</a>
 */
public class BatchApi extends QyApi {

	private final TokenHolder tokenHolder;

	public BatchApi(TokenHolder tokenHolder) {
		this.tokenHolder = tokenHolder;
	}

	/**
	 * ���������Ա��ע
	 *
	 * @param parameter
	 *            ��ԱID,��ǩID,����ID
	 * @param callback
	 *            ��������ִ�н���Ļص���ַ����Ϣ
	 * @param tips
	 *            ���͵�΢���ϵ���ʾ�ֻ����֤�ſ���ʹ�ã�����ʹ��΢������ʱ�����ֶ�Ĭ��Ϊ�����עXXX��ҵ�š����ʼ�����ʱ�����ֶ���Ч��
	 * @return �첽����id����󳤶�Ϊ64�ַ�
	 * @see com.wk.wechat4j.qy.model.IdParameter
	 * @see com.wk.wechat4j.qy.model.Callback
	 * @see <a
	 *      href="http://qydev.weixin.qq.com/wiki/index.php?title=%E5%BC%82%E6%AD%A5%E4%BB%BB%E5%8A%A1%E6%8E%A5%E5%8F%A3#.E9.82.80.E8.AF.B7.E6.88.90.E5.91.98.E5.85.B3.E6.B3.A8">�����Ա��ע</a>
	 * @throws WeixinException
	 */
	public String inviteUser(IdParameter parameter, Callback callback,
			String tips) throws WeixinException {
		String batch_inviteuser_uri = getRequestUri("batch_inviteuser_uri");
		Token token = tokenHolder.getToken();
		JSONObject obj = new JSONObject();
		obj.putAll(parameter.getParameter());
		obj.put("callback", callback);
		obj.put("invite_tips", tips);
		WeixinResponse response = weixinExecutor.post(
				String.format(batch_inviteuser_uri, token.getAccessToken()),
				obj.toJSONString());
		return response.getAsJson().getString("jobid");
	}

	/**
	 * �������³�Ա,���ӿ���useridΪ����������������ҵ��ͨѶ¼��Ա��
	 *
	 * <p>
	 * 1.ģ���еĲ�������д����ID����������÷ֺŷָ�������ID����Ϊ����</br>
	 * 2.�ļ��д��ڡ�ͨѶ¼��Ҳ���ڵĳ�Ա�����³�Ա���ļ���ָ�����ֶ�ֵ </br> 3.�ļ��д��ڡ�ͨѶ¼�в����ڵĳ�Ա��ִ����Ӳ���</br>
	 * 4.ͨѶ¼�д��ڡ��ļ��в����ڵĳ�Ա�����ֲ���</br>
	 * </p>
	 *
	 * @param mediaId
	 *            ��user��Ϣ��cvs�ļ��ϴ����media_id
	 * @param callback
	 *            ��������ִ�н���Ļص���ַ����Ϣ
	 * @return �첽����id����󳤶�Ϊ64�ַ�
	 * @see {@link MediaApi#batchUploadUsers(java.util.List)}
	 * @see com.wk.wechat4j.qy.model.Callback
	 * @see <a
	 *      href="http://qydev.weixin.qq.com/wiki/index.php?title=%E5%BC%82%E6%AD%A5%E4%BB%BB%E5%8A%A1%E6%8E%A5%E5%8F%A3#.E5.A2.9E.E9.87.8F.E6.9B.B4.E6.96.B0.E6.88.90.E5.91.98">�������³�Ա</a>
	 * @throws WeixinException
	 */
	public String syncUser(String mediaId, Callback callback)
			throws WeixinException {
		String batch_syncuser_uri = getRequestUri("batch_syncuser_uri");
		return batch(batch_syncuser_uri, mediaId, callback);
	}

	private String batch(String batchUrl, String mediaId, Callback callback)
			throws WeixinException {
		Token token = tokenHolder.getToken();
		JSONObject obj = new JSONObject();
		obj.put("media_id", mediaId);
		obj.put("callback", callback);
		WeixinResponse response = weixinExecutor.post(
				String.format(batchUrl, token.getAccessToken()),
				obj.toJSONString());
		return response.getAsJson().getString("jobid");
	}

	/**
	 * �������ǳ�Ա,���ӿ���useridΪ������ȫ��������ҵ��ͨѶ¼��Ա��������ɺ���ҵ��ͨѶ¼��Ա���ύ���ļ���ȫ����һ�¡�
	 * <p>
	 * 1.ģ���еĲ�������д����ID����������÷ֺŷָ�������ID����Ϊ����</br> 2.�ļ��д��ڡ�ͨѶ¼��Ҳ���ڵĳ�Ա����ȫ���ļ�Ϊ׼</br>
	 * 3.�ļ��д��ڡ�ͨѶ¼�в����ڵĳ�Ա��ִ����Ӳ���</br>
	 * 4.ͨѶ¼�д��ڡ��ļ��в����ڵĳ�Ա��ִ��ɾ�����������ڰ�ȫ���ǣ������Ҫɾ���ĳ�Ա����50�ˣ�
	 * �Ҷ�������������20%���ϣ�ϵͳ����ֹ���벢������Ӧ�Ĵ�����
	 * </p>
	 *
	 * @param mediaId
	 *            ��userid��Ϣ��cvs�ļ��ϴ����media_id
	 * @param callback
	 *            ��������ִ�н���Ļص���ַ����Ϣ
	 * @return �첽����id����󳤶�Ϊ64�ַ�
	 * @see {@link MediaApi#batchUploadUsers(java.util.List)}
	 * @see com.foxinmy.weixin4j.qy.model.Callback
	 * @see <a
	 *      href="http://qydev.weixin.qq.com/wiki/index.php?title=%E5%BC%82%E6%AD%A5%E4%BB%BB%E5%8A%A1%E6%8E%A5%E5%8F%A3#.E5.85.A8.E9.87.8F.E8.A6.86.E7.9B.96.E6.88.90.E5.91.98">�������ǳ�Ա</a>
	 * @throws WeixinException
	 */
	public String replaceUser(String mediaId, Callback callback)
			throws WeixinException {
		String batch_replaceuser_uri = getRequestUri("batch_replaceuser_uri");
		return batch(batch_replaceuser_uri, mediaId, callback);
	}

	/**
	 * �������ǲ���,���ӿ���partyidΪ����ȫ��������ҵ��ͨѶ¼��֯�ܹ���������ɺ���ҵ��ͨѶ¼��֯�ܹ����ύ���ļ���ȫ����һ�¡�
	 * <p>
	 * 1.�ļ��д��ڡ�ͨѶ¼��Ҳ���ڵĲ��ţ�ִ���޸Ĳ���</br> 2.�ļ��д��ڡ�ͨѶ¼�в����ڵĲ��ţ�ִ����Ӳ���</br>
	 * 3.�ļ��в����ڡ�ͨѶ¼�д��ڵĲ��ţ�������Ϊ��ʱ��ִ��ɾ������</br>
	 * 4.CSV�ļ��У��������ơ�����ID��������IDΪ�����ֶΣ�����ID����Ϊ���֣�����Ϊ��ѡ�ֶΣ��ÿջ���0���޸�����
	 * </p>
	 *
	 * @param mediaId
	 *            ��partyid��Ϣ��cvs�ļ��ϴ����media_id
	 * @param callback
	 *            ��������ִ�н���Ļص���ַ����Ϣ
	 * @return �첽����id����󳤶�Ϊ64�ַ�
	 * @see {@link MediaApi#batchUploadParties(java.util.List)}
	 * @see com.wk.wechat4j.qy.model.Callback
	 * @see <a
	 *      href="http://qydev.weixin.qq.com/wiki/index.php?title=%E5%BC%82%E6%AD%A5%E4%BB%BB%E5%8A%A1%E6%8E%A5%E5%8F%A3#.E5.85.A8.E9.87.8F.E8.A6.86.E7.9B.96.E9.83.A8.E9.97.A8">�������ǲ���</a>
	 * @throws WeixinException
	 */
	public String replaceParty(String mediaId, Callback callback)
			throws WeixinException {
		String batch_replaceparty_uri = getRequestUri("batch_replaceparty_uri");
		return batch(batch_replaceparty_uri, mediaId, callback);
	}

	/**
	 * ��ȡ�첽����ִ�еĽ��
	 *
	 * @param jobId
	 *            ����ID
	 * @return ִ�н������
	 * @see com.wk.wechat4j.qy.model.BatchResult
	 * @see <a
	 *      href="http://qydev.weixin.qq.com/wiki/index.php?title=%E5%BC%82%E6%AD%A5%E4%BB%BB%E5%8A%A1%E6%8E%A5%E5%8F%A3#.E8.8E.B7.E5.8F.96.E5.BC.82.E6.AD.A5.E4.BB.BB.E5.8A.A1.E7.BB.93.E6.9E.9C">��ȡ�첽����ִ�н��</a>
	 * @throws WeixinException
	 */
	public BatchResult getBatchResult(String jobId) throws WeixinException {
		Token token = tokenHolder.getToken();
		String batch_getresult_uri = getRequestUri("batch_getresult_uri");
		WeixinResponse response = weixinExecutor.get(String.format(
				batch_getresult_uri, token.getAccessToken(), jobId));
		return response.getAsObject(new TypeReference<BatchResult>() {
		});
	}
}
