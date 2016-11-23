package utouu.im.net.service.api;


import utouu.im.net.tcp.mina.entity.vo.OffChatMsgDetail;

public interface ILoginService {

	public OffChatMsgDetail pullOffLineChatMsgDetail(String account);

}
