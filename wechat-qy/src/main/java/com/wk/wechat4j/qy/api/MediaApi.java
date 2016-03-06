package com.wk.wechat4j.qy.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.PropertyFilter;
import com.wk.wechat4j.base.exception.WeixinException;
import com.wk.wechat4j.base.http.*;
import com.wk.wechat4j.base.http.apache.ByteArrayBody;
import com.wk.wechat4j.base.http.apache.FormBodyPart;
import com.wk.wechat4j.base.http.apache.InputStreamBody;
import com.wk.wechat4j.base.http.weixin.JsonResult;
import com.wk.wechat4j.base.http.weixin.WeixinResponse;
import com.wk.wechat4j.base.model.*;
import com.wk.wechat4j.base.token.TokenHolder;
import com.wk.wechat4j.base.tuple.MpArticle;
import com.wk.wechat4j.base.type.MediaType;
import com.wk.wechat4j.base.util.*;
import com.wk.wechat4j.qy.model.Callback;
import com.wk.wechat4j.qy.model.Party;
import com.wk.wechat4j.qy.model.User;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.*;

/**
 * ý�����API
 *
 * @className MediaApi
 * @author jy.hu
 * @date 2014��9��25��
 * @since JDK 1.6
 * @see <a
 *      href="http://qydev.weixin.qq.com/wiki/index.php?title=%E7%AE%A1%E7%90%86%E7%B4%A0%E6%9D%90%E6%96%87%E4%BB%B6">�����ز��ļ�</a>
 * @see com.wk.wechat4j.base.type.MediaType
 */
public class MediaApi extends QyApi {

	private final TokenHolder tokenHolder;

	public MediaApi(TokenHolder tokenHolder) {
		this.tokenHolder = tokenHolder;
	}

	/**
	 * �ϴ�ͼ����Ϣ�ڵ�ͼƬ:�����ϴ�ͼƬ����ҵ�ŷ���ˣ��ӿڷ���ͼƬurl����ע�⣬��url��������ͼ����Ϣ�ķ��ͣ�
	 * ��ÿ����ҵÿ�����ֻ���ϴ�100��ͼƬ��
	 *
	 * @param is
	 *            ͼƬ����
	 * @param fileName
	 *            �ļ���
	 * @see <a
	 *      href="http://qydev.weixin.qq.com/wiki/index.php?title=%E4%B8%8A%E4%BC%A0%E5%9B%BE%E6%96%87%E6%B6%88%E6%81%AF%E5%86%85%E7%9A%84%E5%9B%BE%E7%89%87">�ϴ�ͼ����Ϣ�ڵ�ͼƬ</a>
	 * @return ͼƬurl
	 * @throws WeixinException
	 */
	public String uploadImage(InputStream is, String fileName)
			throws WeixinException {
		if (StringUtil.isBlank(fileName)) {
			fileName = ObjectId.get().toHexString();
		}
		if (StringUtil.isBlank(FileUtil.getFileExtension(fileName))) {
			fileName = String.format("%s.jpg", fileName);
		}
		String media_uploadimg_uri = getRequestUri("media_uploadimg_uri");
		Token token = tokenHolder.getToken();
		WeixinResponse response = weixinExecutor.post(String.format(
				media_uploadimg_uri, token.getAccessToken()),
				new FormBodyPart("media", new InputStreamBody(is,
						ContentType.IMAGE_JPG.getMimeType(), fileName)));
		return response.getAsJson().getString("url");
	}

	/**
	 * �ϴ�ý���ļ�:�ֱ���ͼƬ��image����������voice������Ƶ��video������ͨ�ļ�(file)
	 * <p>
	 * ��������·���{"type":"TYPE","media_id":"MEDIA_ID","created_at":123456789},
	 * �����׳��쳣.
	 * </p>
	 *
	 * @param agentid
	 *            ��ҵӦ��ID(<font color="red">����0ʱ��Ϊ�ϴ�����ý���ļ�</font>)
	 * @param is
	 *            ý��������
	 * @param fileName
	 *            �ļ���
	 * @return �ϴ���΢�ŷ��������ص�ý���ʶ
	 * @see com.wk.wechat4j.base.model.MediaUploadResult
	 * @see <a
	 *      href="http://qydev.weixin.qq.com/wiki/index.php?title=%E4%B8%8A%E4%BC%A0%E4%B8%B4%E6%97%B6%E7%B4%A0%E6%9D%90%E6%96%87%E4%BB%B6">�ϴ���ʱ�ز��ļ�˵��</a>
	 * @see <a
	 *      href="http://http://qydev.weixin.qq.com/wiki/index.php?title=%E4%B8%8A%E4%BC%A0%E6%B0%B8%E4%B9%85%E7%B4%A0%E6%9D%90">�ϴ������ز��ļ�˵��</a>
	 * @throws WeixinException
	 */
	public MediaUploadResult uploadMedia(int agentid, InputStream is,
			String fileName) throws WeixinException {
		byte[] content;
		try {
			content = IOUtil.toByteArray(is);
		} catch (IOException e) {
			throw new WeixinException(e);
		}
		if (StringUtil.isBlank(fileName)) {
			fileName = ObjectId.get().toHexString();
		}
		String suffixName = FileUtil.getFileExtension(fileName);
		if (StringUtil.isBlank(suffixName)) {
			suffixName = FileUtil
					.getFileType(new ByteArrayInputStream(content));
			fileName = String.format("%s.%s", fileName, suffixName);
		}
		MediaType mediaType = MediaType.file;
		if (",bmp,png,jpeg,jpg,gif,"
				.contains(String.format(",%s,", suffixName))) {
			mediaType = MediaType.image;
		} else if (",mp3,wma,wav,amr,".contains(String.format(",%s,",
				suffixName))) {
			mediaType = MediaType.voice;
		} else if (",rm,rmvb,wmv,avi,mpg,mpeg,mp4,".contains(String.format(
				",%s,", suffixName))) {
			mediaType = MediaType.video;
		}
		Token token = tokenHolder.getToken();
		try {
			WeixinResponse response = null;
			if (agentid > 0) {
				String material_media_upload_uri = getRequestUri("material_media_upload_uri");
				response = weixinExecutor.post(String.format(
						material_media_upload_uri, token.getAccessToken(),
						mediaType.name(), agentid), new FormBodyPart("media",
						new ByteArrayBody(content, mediaType.getContentType()
								.getMimeType(), fileName)));
				return new MediaUploadResult(response.getAsJson().getString(
						"media_id"), mediaType, new Date());
			} else {
				String media_upload_uri = getRequestUri("media_upload_uri");
				response = weixinExecutor.post(String.format(media_upload_uri,
						token.getAccessToken(), mediaType.name()),
						new FormBodyPart("media", new ByteArrayBody(content,
								mediaType.getContentType().getMimeType(),
								fileName)));
				JSONObject obj = response.getAsJson();
				return new MediaUploadResult(obj.getString("media_id"),
						obj.getObject("type", MediaType.class), new Date(
								obj.getLong("created_at") * 1000l));
				/*
				 * return response.getAsObject(new
				 * TypeReference<MediaUploadResult>() { });
				 */
			}
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					;
				}
			}
		}
	}

	/**
	 * ����ý���ļ�
	 *
	 * @param agentid
	 *            ��ҵӦ��Id(<font color="red">����0ʱ��Ϊ��ȡ����ý���ļ�</font>)
	 * @param mediaId
	 *            ý��ID
	 * @return ý�����ؽ��
	 * @see com.wk.wechat4j.base.model.MediaDownloadResult
	 * @see <a
	 *      href="http://qydev.weixin.qq.com/wiki/index.php?title=%E8%8E%B7%E5%8F%96%E4%B8%B4%E6%97%B6%E7%B4%A0%E6%9D%90%E6%96%87%E4%BB%B6">��ȡ��ʱý��˵��</a>
	 * @see <a
	 *      href="http://qydev.weixin.qq.com/wiki/index.php?title=%E8%8E%B7%E5%8F%96%E6%B0%B8%E4%B9%85%E7%B4%A0%E6%9D%90">��ȡ����ý��˵��</a>
	 * @throws WeixinException
	 */
	public MediaDownloadResult downloadMedia(int agentid, String mediaId)
			throws WeixinException {
		Token token = tokenHolder.getToken();
		try {
			HttpRequest request = null;
			if (agentid > 0) {
				String material_media_download_uri = getRequestUri("material_media_download_uri");
				request = new HttpRequest(HttpMethod.GET, String.format(
						material_media_download_uri, token.getAccessToken(),
						mediaId, agentid));
			} else {
				String media_download_uri = getRequestUri("media_download_uri");
				request = new HttpRequest(HttpMethod.GET, String.format(
						media_download_uri, token.getAccessToken(), mediaId));
			}
			HttpParams params = weixinExecutor.getExecuteParams();
			request.setParams(params);
			HttpResponse response = weixinExecutor.getExecuteClient().execute(
					request);
			byte[] content = IOUtil.toByteArray(response.getBody());
			HttpHeaders headers = response.getHeaders();
			String contentType = headers.getFirst(HttpHeaders.CONTENT_TYPE);
			String disposition = headers
					.getFirst(HttpHeaders.CONTENT_DISPOSITION);
			if (contentType
					.contains(ContentType.APPLICATION_JSON.getMimeType())
					|| (disposition != null && disposition.indexOf(".json") > 0)) {
				JsonResult jsonResult = JSON.parseObject(content, 0,
						content.length, Consts.UTF_8.newDecoder(),
						JsonResult.class);
				if (jsonResult.getCode() != 0) {
					if (StringUtil.isBlank(jsonResult.getDesc())) {
						jsonResult.setDesc(WeixinErrorUtil.getText(Integer
								.toString(jsonResult.getCode())));
					}
					throw new WeixinException(Integer.toString(jsonResult
							.getCode()), jsonResult.getDesc());
				}
			}
			String fileName = RegexUtil
					.regexFileNameFromContentDispositionHeader(disposition);
			if (StringUtil.isBlank(fileName)) {
				fileName = String.format("%s.%s", mediaId,
						contentType.split("/")[1]);
			}
			return new MediaDownloadResult(content,
					ContentType.create(contentType), fileName);
		} catch (IOException e) {
			throw new WeixinException("I/O Error on getBody", e);
		} catch (HttpClientException e) {
			throw new WeixinException(e);
		}
	}

	/**
	 * �ϴ�����ͼ���ز�
	 * <p>
	 * �������������ز�Ҳ�����ڹ���ƽ̨�����زĹ���ģ���п���,�����زĵ������������޵ģ������������ͼ����Ϣ�زĺ�ͼƬ�زĵ�����Ϊ5000��
	 * ��������Ϊ1000
	 * </P>
	 *
	 * @param agentid
	 *            ��ҵӦ�õ�id
	 * @param articles
	 *            ͼ���б�
	 * @return �ϴ���΢�ŷ��������ص�ý���ʶ
	 * @throws WeixinException
	 * @see <a
	 *      href="http://qydev.weixin.qq.com/wiki/index.php?title=%E4%B8%8A%E4%BC%A0%E6%B0%B8%E4%B9%85%E7%B4%A0%E6%9D%90">�ϴ�����ý���ز�</a>
	 * @see com.wk.wechat4j.base.tuple.MpArticle
	 */
	public String uploadMaterialArticle(int agentid, List<MpArticle> articles)
			throws WeixinException {
		Token token = tokenHolder.getToken();
		String material_article_upload_uri = getRequestUri("material_article_upload_uri");
		JSONObject obj = new JSONObject();
		obj.put("agentid", agentid);
		JSONObject news = new JSONObject();
		news.put("articles", articles);
		obj.put("mpnews", news);
		WeixinResponse response = weixinExecutor.post(
				String.format(material_article_upload_uri,
						token.getAccessToken()), obj.toJSONString());

		return response.getAsJson().getString("media_id");
	}

	/**
	 * ɾ������ý���ز�
	 *
	 * @param agentid
	 *            ��ҵӦ��ID
	 * @param mediaId
	 *            ý���زĵ�media_id
	 * @return ������
	 * @throws WeixinException
	 * @see <a
	 *      href="http://qydev.weixin.qq.com/wiki/index.php?title=%E5%88%A0%E9%99%A4%E6%B0%B8%E4%B9%85%E7%B4%A0%E6%9D%90">ɾ������ý���ز�</a>
	 */
	public JsonResult deleteMaterialMedia(int agentid, String mediaId)
			throws WeixinException {
		Token token = tokenHolder.getToken();
		String material_media_del_uri = getRequestUri("material_media_del_uri");
		WeixinResponse response = weixinExecutor.get(String.format(
				material_media_del_uri, token.getAccessToken(), mediaId,
				agentid));
		return response.getAsJsonResult();
	}

	/**
	 * ��������ͼ���ز�
	 *
	 * @param agentid
	 *            ��ҵӦ��ID
	 * @param mediaId
	 *            ý���زĵ�media_id
	 * @return ͼ���б�
	 * @throws WeixinException
	 * @see {@link #downloadMedia(int, String)}
	 * @see com.wk.wechat4j.base.tuple.MpArticle
	 */
	public List<MpArticle> downloadArticle(int agentid, String mediaId)
			throws WeixinException {
		MediaDownloadResult result = downloadMedia(agentid, mediaId);
		byte[] content = result.getContent();
		JSONObject obj = JSON.parseObject(content, 0, content.length,
				Consts.UTF_8.newDecoder(), JSONObject.class);
		return JSON.parseArray(obj.getJSONObject("mpnews")
				.getString("articles"), MpArticle.class);
	}

	/**
	 * �޸�����ͼ���ز�
	 *
	 * @param agentid
	 *            ��ҵӦ�õ�id
	 * @param mediaId
	 *            �ϴ����media_id
	 * @param articles
	 *            ͼ���б�
	 * @return �������
	 * @throws WeixinException
	 * @see <a
	 *      href="http://qydev.weixin.qq.com/wiki/index.php?title=%E4%BF%AE%E6%94%B9%E6%B0%B8%E4%B9%85%E5%9B%BE%E6%96%87%E7%B4%A0%E6%9D%90">�޸�����ý���ز�</a>
	 * @see com.wk.wechat4j.base.tuple.MpArticle
	 */
	public String updateMaterialArticle(int agentid, String mediaId,
			List<MpArticle> articles) throws WeixinException {
		Token token = tokenHolder.getToken();
		String material_article_update_uri = getRequestUri("material_article_update_uri");
		JSONObject obj = new JSONObject();
		obj.put("agentid", agentid);
		JSONObject news = new JSONObject();
		news.put("articles", articles);
		obj.put("mpnews", news);
		obj.put("media_id", mediaId);
		WeixinResponse response = weixinExecutor.post(
				String.format(material_article_update_uri,
						token.getAccessToken()), obj.toJSONString());

		return response.getAsJson().getString("media_id");
	}

	/**
	 * ��ȡ����ý���زĵ�����
	 *
	 * @param agentid
	 *            ��ҵӦ��id
	 * @return ��������
	 * @throws WeixinException
	 * @see com.wk.wechat4j.base.model.MediaCounter
	 * @see <a
	 *      href="http://qydev.weixin.qq.com/wiki/index.php?title=%E8%8E%B7%E5%8F%96%E7%B4%A0%E6%9D%90%E6%80%BB%E6%95%B0">��ȡ�ز�����</a>
	 */
	public MediaCounter countMaterialMedia(int agentid) throws WeixinException {
		Token token = tokenHolder.getToken();
		String material_media_count_uri = getRequestUri("material_media_count_uri");
		WeixinResponse response = weixinExecutor.get(String.format(
				material_media_count_uri, token.getAccessToken(), agentid));
		JSONObject result = response.getAsJson();
		MediaCounter counter = JSON.toJavaObject(result, MediaCounter.class);
		counter.setNewsCount(result.getIntValue("mpnews_count"));
		return counter;
	}

	/**
	 * ��ȡý���زļ�¼�б�
	 *
	 * @param agentid
	 *            ��ҵӦ��ID
	 * @param mediaType
	 *            �زĵ����ͣ�ͼƬ��image������Ƶ��video�������� ��voice����ͼ�ģ�news�����ļ���file��
	 * @param pageable
	 *            ��ҳ����
	 * @return ý���زĵļ�¼����
	 * @throws WeixinException
	 * @see com.wk.wechat4j.base.model.MediaRecord
	 * @see com.wk.wechat4j.base.type.MediaType
	 * @see com.wk.wechat4j.base.model.MediaItem
	 * @see com.wk.wechat4j.base.model.Pageable
	 * @see com.wk.wechat4j.base.model.Pagedata
	 * @see <a
	 *      href="http://qydev.weixin.qq.com/wiki/index.php?title=%E8%8E%B7%E5%8F%96%E7%B4%A0%E6%9D%90%E5%88%97%E8%A1%A8">��ȡ�ز��б�</a>
	 */
	public MediaRecord listMaterialMedia(int agentid, MediaType mediaType,
			Pageable pageable) throws WeixinException {
		Token token = tokenHolder.getToken();
		String material_media_list_uri = getRequestUri("material_media_list_uri");
		JSONObject obj = new JSONObject();
		obj.put("agentid", agentid);
		obj.put("type",
				mediaType == MediaType.news ? "mpnews" : mediaType.name());
		obj.put("offset", pageable.getOffset());
		obj.put("count", pageable.getPageSize());
		WeixinResponse response = weixinExecutor.post(
				String.format(material_media_list_uri, token.getAccessToken()),
				obj.toJSONString());
		obj = response.getAsJson();

		MediaRecord mediaRecord = JSON.toJavaObject(obj, MediaRecord.class);
		if (mediaType == MediaType.news) {
			mediaRecord.setItems(JSON.parseArray(obj.getString("itemlist"),
					MediaItem.class));
		}
		mediaRecord.setMediaType(mediaType);
		mediaRecord.setPageable(pageable);
		return mediaRecord;
	}

	/**
	 * ��ȡȫ����ý���ز�
	 *
	 * @param agentid
	 *            ��ҵӦ��id
	 * @param mediaType
	 *            ý������
	 * @return �ز��б�
	 * @see {@link #listMaterialMedia(int,MediaType, Pageable)}
	 * @throws WeixinException
	 */
	public List<MediaItem> listAllMaterialMedia(int agentid, MediaType mediaType)
			throws WeixinException {
		Pageable pageable = new Pageable(1, 20);
		List<MediaItem> mediaList = new ArrayList<MediaItem>();
		MediaRecord mediaRecord = null;
		for (;;) {
			mediaRecord = listMaterialMedia(agentid, mediaType, pageable);
			if (mediaRecord.getItems() == null
					|| mediaRecord.getItems().isEmpty()) {
				break;
			}
			mediaList.addAll(mediaRecord.getItems());
			if (!mediaRecord.getPagedata().hasNext()) {
				break;
			}
			pageable = pageable.next();
		}
		return mediaList;
	}

	/**
	 * �����ϴ���Ա
	 *
	 * @param users
	 *            ��Ա�б�
	 * @see {@link BatchApi#syncUser(String,Callback)}
	 * @see {@link BatchApi#replaceUser(String,Callback)}
	 * @see <a
	 *      href="http://qydev.weixin.qq.com/wiki/index.php?title=%E5%BC%82%E6%AD%A5%E4%BB%BB%E5%8A%A1%E6%8E%A5%E5%8F%A3#.E9.80.9A.E8.AE.AF.E5.BD.95.E6.9B.B4.E6.96.B0">��������</a>
	 * @return �ϴ����mediaId
	 * @throws WeixinException
	 */
	public String batchUploadUsers(List<User> users) throws WeixinException {
		return batchUpload("batch_syncuser.cvs", users);
	}

	/**
	 * �����ϴ�����
	 *
	 * @param parties
	 *            �����б�
	 * @see {@link BatchApi#replaceParty(String,Callback)}
	 * @see <a
	 *      href="http://qydev.weixin.qq.com/wiki/index.php?title=%E5%BC%82%E6%AD%A5%E4%BB%BB%E5%8A%A1%E6%8E%A5%E5%8F%A3#.E9.80.9A.E8.AE.AF.E5.BD.95.E6.9B.B4.E6.96.B0">��������</a>
	 * @return �ϴ����mediaId
	 * @throws WeixinException
	 */
	public String batchUploadParties(List<Party> parties)
			throws WeixinException {
		return batchUpload("batch_replaceparty.cvs", parties);
	}

	private <T> String batchUpload(String batchName, List<T> models)
			throws WeixinException {
		StringWriter writer = new StringWriter();
		try {
			JSONObject csvObj = JSON.parseObject(weixinBundle().getString(
					batchName));
			JSONArray columns = csvObj.getJSONArray("column");
			writer.write(csvObj.getString("header"));
			final Map<String, Object> column = new LinkedHashMap<String, Object>();
			for (Object col : columns) {
				column.put(col.toString(), "");
			}
			writer.write("\r\n");
			for (T model : models) {
				JSON.toJSONString(model, new PropertyFilter() {
					@Override
					public boolean apply(Object object, String name,
							Object value) {
						if (column.containsKey(name)) {
							if (value instanceof Collection) {
								column.put(name,
										StringUtil.join(((Collection<?>) value)
												.iterator(), ';'));
							} else {
								column.put(name, value);
							}
						}
						return true;
					}
				});
				writer.write(StringUtil.join(column.values(), ','));
				writer.write("\r\n");
			}
			return uploadMedia(
					0,
					new ByteArrayInputStream(writer.getBuffer().toString()
							.getBytes(Consts.UTF_8)), batchName).getMediaId();
		} finally {
			try {
				writer.close();
			} catch (IOException e) {
				;
			}
		}
	}
}
