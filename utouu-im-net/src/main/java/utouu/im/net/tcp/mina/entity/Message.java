package utouu.im.net.tcp.mina.entity;

import org.apache.mina.core.session.IoSession;

import utouu.im.obj.BaseObj;

public class Message implements BaseObj {
	private static final long serialVersionUID = 5873031559613630419L;
	private int code;
	private int length;
	private byte[] data;
	private IoSession ioSession;

	public IoSession getIoSession() {
		return ioSession;
	}

	public void setIoSession(IoSession ioSession) {
		this.ioSession = ioSession;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

	public void clearProp() {
		this.code = 0;
		this.length = 0;
		this.data = null;
		this.ioSession = null;
	}
}
