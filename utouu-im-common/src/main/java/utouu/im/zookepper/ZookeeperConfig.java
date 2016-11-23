package utouu.im.zookepper;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class ZookeeperConfig {

	@Value("${zookeeper.servers}")
	private String servers;

	@Bean
	public CuratorFramework curatorFramework() {
		RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 300);
		CuratorFramework client = CuratorFrameworkFactory.newClient(servers, retryPolicy);
		return client;
	}

	public static final long TRY_LOCKING_TIMEOUT = 3L;
}
