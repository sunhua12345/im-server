package utouu.im.zookepper;

import java.util.Map;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.framework.state.ConnectionState;
import org.apache.curator.framework.state.ConnectionStateListener;

public class ZkConnectListener implements ConnectionStateListener{

	@Override
	public void stateChanged(CuratorFramework client, ConnectionState newState) {
		if(newState==ConnectionState.CONNECTED){
			//zookeeper链接成功!
			System.out.println("zookeeper链接成功!");
		}else if(newState ==ConnectionState.RECONNECTED){
			//zookeeper重连成功
			System.out.println("zookeeper重连成功");
		}else if(newState ==ConnectionState.LOST){
			//zookeeper链接丢失
			System.out.println("zookeeper链接丢失");
			//释放进程拥有的所有锁资源
			Map<String,InterProcessMutex> locks = ZookeeperTool.locks;
			for(InterProcessMutex lock:locks.values()){
				try {
					lock.release();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}else if(newState==ConnectionState.SUSPENDED){
			System.out.println("zookeeper链接暂停");
		}else if(newState==ConnectionState.READ_ONLY){
			System.out.println("zookeeper链接只读");
		}
	}

}
