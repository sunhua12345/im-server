package utouu.im.net.service.api;

import utouu.im.net.tcp.mina.SessionClient;

public interface IChatService {

	public void primaryTextChat(SessionClient client, String otherAccount, String chatText);

}
