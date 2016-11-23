package utouu.im.bean.dto;

import utouu.im.bean.dto.entity.ChatBody;
import utouu.im.bean.dto.utils.StringHelper;

public class ChatStructDto {
	private String msgid = StringHelper.randUUID();
	private String fromAccount;
	private String toAccount;
	private long sendTime;
	private ChatBody body;
	public String getMsgid() {
		return msgid;
	}
	public void setMsgid(String msgid) {
		this.msgid = msgid;
	}
	public String getFromAccount() {
		return fromAccount;
	}
	public void setFromAccount(String fromAccount) {
		this.fromAccount = fromAccount;
	}
	public String getToAccount() {
		return toAccount;
	}
	public void setToAccount(String toAccount) {
		this.toAccount = toAccount;
	}
	public long getSendTime() {
		return sendTime;
	}
	public void setSendTime(long sendTime) {
		this.sendTime = sendTime;
	}
	public ChatBody getBody() {
		return body;
	}
	public void setBody(ChatBody body) {
		this.body = body;
	}
}
