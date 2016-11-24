package utouu.im.net;

import java.io.UnsupportedEncodingException;
import java.util.HashSet;
import java.util.Set;

import utouu.im.net.service.api.IZookeeperService;

public class GlobalServerSender {
	public static void sendMsg(String[] toAccounts,String text) throws UnsupportedEncodingException, Exception {
		IZookeeperService zookeeperService = ServiceManager.getService(IZookeeperService.class);
		ServerNotify notify = new ServerNotify();
		notify.setText(text);
		for(int i=0;i<toAccounts.length;i++){
			if(!notify.getAccounts().contains(toAccounts[i])){
				notify.getAccounts().add(toAccounts[i]);
			}
		}
		zookeeperService.server_notify(notify);
	}
	
	public static class ServerNotify{
		private Set<String> accounts = new HashSet<String>();
		private String text;
		public String getText() {
			return text;
		}
		public void setText(String text) {
			this.text = text;
		}
		public Set<String> getAccounts() {
			return accounts;
		}
		public void setAccounts(Set<String> accounts) {
			this.accounts = accounts;
		}
	}
}
