package utouu.im.net.service;

import java.util.List;

import org.springframework.stereotype.Service;

import utouu.im.bean.dto.ChatStructDto;
import utouu.im.net.service.api.ILoginService;

@Service(value="ILoginService")
public class LoginService extends BaseService implements ILoginService {

	@Override
	public List<ChatStructDto> pullOffLineChatMsgDetail(String account, int page, int pageLength) {
		return null;
	}

}
