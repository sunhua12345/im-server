package utouu.im.bean.dto;

import utouu.im.bean.dto.utils.StringHelper;

public class ChatStructDto {
	private String msgid = StringHelper.randUUID();
	private String fromAccount;
	private String toAccount;
	private long sendTime;
	private String body;
	private boolean offlineMsg;
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
	public boolean isOfflineMsg() {
		return offlineMsg;
	}
	public void setOfflineMsg(boolean offlineMsg) {
		this.offlineMsg = offlineMsg;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
}
