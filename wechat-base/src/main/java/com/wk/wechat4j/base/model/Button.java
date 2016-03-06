package com.wk.wechat4j.base.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.wk.wechat4j.base.type.ButtonType;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * �˵���ť
 * <p>
 * Ŀǰ�Զ���˵�������3��һ���˵�,ÿ��һ���˵�������5�������˵�,һ���˵����4������,�����˵����7������,������Ĳ��ֽ�����"..."����
 * ��ע��,�����Զ���˵���,����΢�ſͻ��˻���,��Ҫ24Сʱ΢�ſͻ��˲Ż�չ�ֳ���,�������ʱ���Գ���ȡ����ע�����˺ź��ٴι�ע,����Կ����������Ч��
 * </p>
 *
 * @className Button
 * @author jy.hu
 * @date 2014��4��5��
 * @since JDK 1.6
 * @see com.wk.wechat4j.base.type.ButtonType
 */
public class Button implements Serializable {

	private static final long serialVersionUID = -6422234732203854866L;

	/**
	 * �˵����⣬������16���ֽڣ��Ӳ˵�������40���ֽ�
	 */
	private String name;
	/**
	 * �˵����� </br> <font color="red">
	 * ����ƽ̨�������ܹ����õĲ˵�������view��text��img��photo��video��voice </font>
	 *
	 * @see com.wk.wechat4j.base.type.ButtonType
	 */
	private ButtonType type;
	/**
	 * �˵�KEYֵ,����type�����Ͷ���,������Ϣ�ӿ�����,������128�ֽ�.
	 * <p>
	 * ���������õ��Զ���˵���</br> Text:�������ֵ�value�� Img��voice������mediaID��value��
	 * Video��������Ƶ�������ӵ�value��</br> News������ͼ����Ϣ��news_info�� View���������ӵ�url��</br>
	 * <p>
	 * ʹ��API���õ��Զ���˵���</br>
	 * click��scancode_push��scancode_waitmsg��pic_sysphoto��pic_photo_or_album
	 * ��</br>
	 * pic_weixin��location_select������Ϊkey��view������Ϊurl;media_id��view_limited
	 * ������Ϊmedia_id
	 * </p>
	 * </p>
	 */
	private Serializable content;
	/**
	 * �����˵����飬����ӦΪ1~5��
	 */
	@JSONField(name = "sub_button")
	private List<Button> subs;

	protected Button() {
		this.subs = new ArrayList<Button>();
	}

	/**
	 * ����һ���˵�
	 *
	 * @param name
	 *            �˵���ʾ������
	 * @param content
	 *            ��buttonTypeΪviewʱcontent����Ϊurl,����Ϊkey.
	 * @param type
	 *            ��ť����
	 */
	public Button(String name, String content, ButtonType type) {
		this.name = name;
		this.content = content;
		this.type = type;
		this.subs = new ArrayList<Button>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ButtonType getType() {
		return type;
	}

	public void setType(ButtonType type) {
		this.type = type;
	}

	public Serializable getContent() {
		return content;
	}

	public void setContent(Serializable content) {
		this.content = content;
	}

	public List<Button> getSubs() {
		return subs;
	}

	public void setSubs(List<Button> subs) {
		this.subs = subs;
	}

	public Button pushSub(Button btn) {
		this.subs.add(btn);
		return this;
	}

	@Override
	public String toString() {
		return "Button [name=" + name + ", type=" + type + ", content="
				+ content + ", subs=" + subs + "]";
	}
}
