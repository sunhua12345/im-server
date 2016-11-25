package utouu.im.net.tcp.mina.work;

import java.util.Set;

import com.alibaba.fastjson.JSONObject;

import utouu.im.net.GlobalServerSender.ServerNotify;
import utouu.im.net.service.ZookeeperService;
import utouu.im.net.tcp.mina.IMQueue;
import utouu.im.net.tcp.mina.SessionClient;
import utouu.im.net.tcp.mina.cache.ServerCache;
import utouu.im.net.tcp.mina.utils.IoSender;
import utouu.im.protobuf.pb.MsgCode.GameCode;
import utouu.im.protobuf.pb.MsgServerBroad.NotifyServerBroadMsg;
import utouu.im.thread.QueueWork;
import utouu.im.thread.WorkQueue;

public class ServerNotifyWork extends QueueWork{
	private static final long serialVersionUID = -5130903517644182315L;
	private String data;
	@Override
	public void init(Object... objs) throws Exception {
		this.data = (String) objs[0];
	}

	@Override
	public void run() {
		ServerNotify notify = JSONObject.parseObject(data, ServerNotify.class);
		String text = notify.getText();
		Set<String> accounts = notify.getAccounts();
		NotifyServerBroadMsg.Builder builder = NotifyServerBroadMsg.newBuilder();
		builder.setText(text);
		for(String account:accounts){
			SessionClient client=ServerCache.getClient(account);
			System.out.println("服务器节点:"+ZookeeperService.address+":"+ZookeeperService.port+"收到数据:"+text);
			if(client==null){
				//该账号不在当前服务器节点
				continue;
			}
			IoSender.sendMsg(client, GameCode.NOTIFY_SERVER_BROAD_MSG, builder);
		}
	}

	@Override
	public WorkQueue getWorkQueue() {
		return IMQueue.IM_ROLE_QUEUE;
	}

}
