package utouu.im.net.zookeeper;
//package cn.utsoft.uhoo.common.zookeeper;
//
//import org.apache.curator.RetryPolicy;
//import org.apache.curator.framework.CuratorFramework;
//import org.apache.curator.framework.CuratorFrameworkFactory;
//import org.apache.curator.retry.ExponentialBackoffRetry;
//
//public class ZookeeperTest {
//	public static void main(String[] args) throws Exception {
//		String servers = "127.0.0.1:2181";
//		RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 300);
//		CuratorFramework client = CuratorFrameworkFactory.newClient(servers, retryPolicy);
//		ZookeeperTool.initZkClient(client);
//		int i=0;
//		while(true){
//			System.out.println("开始执行放队列:#################测试"+i);
////			if(i<3){
////				ZookeeperTool.setData("test", "hahaha".getBytes());
////			}else if(i>=3&&i<6){
////				ZookeeperTool.setData("test", "测试".getBytes());
////			}else if(i>=6&&i<8){
////				ZookeeperTool.deleteZnode("test");
////			}else if(i>=8){
//				ZookeeperTool.pushQueue(""+i,"#################测试"+i);
////			}
//			i++;
////			Thread.sleep(1000);
//		}
//	}
//}
