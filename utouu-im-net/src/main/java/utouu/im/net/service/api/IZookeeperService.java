package utouu.im.net.service.api;


import java.io.UnsupportedEncodingException;

import utouu.im.net.GlobalServerSender.ServerNotify;
import utouu.im.net.tcp.mina.entity.vo.AccountOnlineVO;

public interface IZookeeperService {

	public void serverInit();

	public void updateOnlineAccounts(String onlinesKeysString);

	public AccountOnlineVO checkAccountOnline(String account);

	public void server_notify(ServerNotify notify) throws UnsupportedEncodingException, Exception;

	public void destory();

}
