package utouu.im.net.tcp.mina.node.work;

import utouu.im.net.tcp.mina.node.ServerNodeFactory;
import utouu.im.net.tcp.mina.node.ServerNodeInfo;
import utouu.im.thread.AynWork;
import utouu.im.thread.WorkManager;

public class TryReconnectNodeServer extends AynWork {
	private static final long serialVersionUID = -3859673948563634275L;
	private ServerNodeInfo serverNode;

	@Override
	public void init(Object... objs) throws Exception {
		this.serverNode = (ServerNodeInfo) objs[0];
	}

	@Override
	public void run() {
		// 尝试十二次重连,总共2分钟
		try {
			for (int i = 0; i < 12; i++) {
				if (!ServerNodeFactory.openServerNodeSessions.containsKey(this.serverNode.toString())) {
					System.out.println("正在尝试第"+(i+1)+"次重新链接!!!!!!");
					WorkManager.getManager().submit(NodeClientConnectWork.class, this.serverNode);
					Thread.sleep(10000);
				} else {
					//重连成功!
					System.out.println("############重新链接成功!!!!!!!!!!!!!!");
					return;
				}
			}
			System.out.println("重连失败!检查服务器是否开启!");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
