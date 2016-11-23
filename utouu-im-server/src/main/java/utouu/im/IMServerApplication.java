package utouu.im;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.event.ContextRefreshedEvent;
import utouu.im.thread.WorkManager;

@SpringBootApplication
@ImportResource(locations={"classpath:spring-redis.xml"})
public class IMServerApplication implements ApplicationListener<ContextRefreshedEvent>{

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		WorkManager.getManager().submit(IMServerStart.class);
	}
	
	public static void main(String[] args) {
		SpringApplication.run(IMServerApplication.class, args);
	}
}
