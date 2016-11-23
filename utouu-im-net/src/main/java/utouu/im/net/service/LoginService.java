package utouu.im.net.service;


import utouu.im.annotation.IService;
import utouu.im.net.service.api.ILoginService;
import utouu.im.net.tcp.mina.entity.vo.OffChatMsgDetail;
@IService
public class LoginService extends BaseService implements ILoginService{

	@Override
	public OffChatMsgDetail pullOffLineChatMsgDetail(String account) {
		return null;
	}

}
