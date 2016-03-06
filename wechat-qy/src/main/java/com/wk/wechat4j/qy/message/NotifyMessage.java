package com.wk.wechat4j.qy.message;

import com.alibaba.fastjson.annotation.JSONField;
import com.wk.wechat4j.base.tuple.NotifyTuple;
import com.wk.wechat4j.qy.model.IdParameter;

import java.io.Serializable;

/**
 * ��Ϣ���Ѷ���
 *
 * @className NotifyMessage
 * @author jy
 * @date 2014��11��22��
 * @since JDK 1.6
 * @see com.wk.wechat4j.base.tuple.Text
 * @see com.wk.wechat4j.base.tuple.Image
 * @see com.wk.wechat4j.base.tuple.Voice
 * @see com.wk.wechat4j.base.tuple.Video
 * @see com.wk.wechat4j.base.tuple.File
 * @see com.wk.wechat4j.base.tuple.News
 * @see com.wk.wechat4j.base.tuple.MpNews
 * @see <a
 *      href="http://qydev.weixin.qq.com/wiki/index.php?title=%E6%B6%88%E6%81%AF%E7%B1%BB%E5%9E%8B%E5%8F%8A%E6%95%B0%E6%8D%AE%E6%A0%BC%E5%BC%8F">��Ϣ˵��</a>
 */
public class NotifyMessage implements Serializable {

	private static final long serialVersionUID = 1219589414293000383L;

	/**
	 * ��ҵӦ�õ�id�����͡�����Ӧ�õ�����ҳ��鿴
	 */
	@JSONField(name = "agentid")
	private int agentId;
	/**
	 * ��ʾ�Ƿ��Ǳ�����Ϣ��0��ʾ��1��ʾ�ǣ�Ĭ��0
	 */
	private int safe;
	/**
	 * ��Ϣ����
	 */
	@JSONField(serialize = false)
	private NotifyTuple tuple;
	/**
	 * ���Ͷ���
	 */
	@JSONField(serialize = false)
	private IdParameter target;

	public NotifyMessage(int agentid, NotifyTuple tuple) {
		this(agentid, tuple, new IdParameter(), false);
	}

	public NotifyMessage(int agentId, NotifyTuple tuple, IdParameter target,
			boolean isSafe) {
		this.agentId = agentId;
		this.safe = isSafe ? 1 : 0;
		this.tuple = tuple;
		this.target = target;
	}

	public int getSafe() {
		return safe;
	}

	public void setSafe(boolean isSafe) {
		this.safe = isSafe ? 1 : 0;
	}

	public int getAgentId() {
		return agentId;
	}

	public NotifyTuple getTuple() {
		return tuple;
	}

	public IdParameter getTarget() {
		return target;
	}

	@Override
	public String toString() {
		return "NotifyMessage [agentId=" + agentId + ", safe=" + safe
				+ ", tuple=" + tuple + ", target=" + target + "]";
	}
}
