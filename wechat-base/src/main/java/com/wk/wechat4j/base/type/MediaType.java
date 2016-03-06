package com.wk.wechat4j.base.type;


import com.wk.wechat4j.base.http.ContentType;

/**
 * �ϴ���ý������</br>
 * <p>
 * ����ƽ̨�ϴ�����:</br> ͼƬ(image): 2MB,֧��bmp/png/jpeg/jpg/gif��ʽ</br>
 * ����(voice):2MB,���ų��Ȳ�����60s,֧��mp3/wma/wav/amr��ʽ</br>
 * ��Ƶ(video):10MB,֧��rm/rmvb/wmv/avi/mpg/mpeg/mp4��ʽ</br>
 * ����ͼ(thumb):64KB,֧��JPG��ʽ</br>
 * </p>
 * <p>
 * ��ҵ���ϴ�����:</br> ͼƬ��image��:1MB��֧��bmp/png/jpeg/jpg/gif��ʽ</br>
 * ������voice����2MB�����ų��Ȳ�����60s��֧��mp3/wma/wav/amr��ʽ</br>
 * ��Ƶ��video����10MB��֧��rm/rmvb/wmv/avi/mpg/mpeg/mp4��ʽ</br> ��ͨ�ļ���file����20MB</br>
 * </p>
 * <p>
 * <font color='red'>��ʱý���ļ��ں�̨����ʱ��Ϊ3��,��3���media_idʧЧ</font>
 * </p>
 *
 * @author jy.hu
 * @date 2014��4��2��
 * @since JDK 1.6
 */
public enum MediaType {
	image(ContentType.IMAGE_JPG), voice(ContentType.AUDIO_MP3), video(
			ContentType.VIDEO_MPEG4), thumb(ContentType.IMAGE_JPG), file(
			ContentType.MULTIPART_FORM_DATA), news(
			ContentType.MULTIPART_FORM_DATA);

	MediaType(ContentType contentType) {
		this.contentType = contentType;
	}

	private ContentType contentType;

	public ContentType getContentType() {
		return contentType;
	}
}
