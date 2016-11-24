package utouu.im.net.service.api;

import utouu.im.net.tcp.mina.entity.vo.AccountOnlineVO;

public interface IZookeeperService {

	public void serverInit();

	public void updateOnlineAccounts(String onlinesKeysString);

	public AccountOnlineVO checkAccountOnline(String account);

}
