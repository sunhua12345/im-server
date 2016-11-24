package utouu.im.net.service.api;

import java.util.List;

import utouu.im.bean.dto.ChatStructDto;

public interface ILoginService {

	public List<ChatStructDto> pullOffLineChatMsgDetail(String account,int page,int pageLength);

}
