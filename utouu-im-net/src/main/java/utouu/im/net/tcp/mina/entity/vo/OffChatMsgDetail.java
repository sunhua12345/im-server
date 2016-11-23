package utouu.im.net.tcp.mina.entity.vo;

import java.util.ArrayList;
import java.util.List;

import utouu.im.bean.dto.ChatStructDto;

public class OffChatMsgDetail {
	private int offLinemsgCount;//离线消息总条数(大于99显示99)
	private List<ChatStructDto> dtos = new ArrayList<ChatStructDto>();//按时间倒叙最后20条数据
	public int getOffLinemsgCount() {
		return offLinemsgCount;
	}
	public void setOffLinemsgCount(int offLinemsgCount) {
		this.offLinemsgCount = offLinemsgCount;
	}
	public List<ChatStructDto> getDtos() {
		return dtos;
	}
	public void setDtos(List<ChatStructDto> dtos) {
		this.dtos = dtos;
	}
}
