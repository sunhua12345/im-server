package utouu.im;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.event.ContextRefreshedEvent;

import utouu.im.net.IMServerLoaded;

@SpringBootApplication
@ImportResource(locations={"classpath:spring-redis.xml"})
@Import(value={IMServerLoaded.class})
public class IMServerApplication implements ApplicationListener<ContextRefreshedEvent>{

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
	}
	
	public static void main(String[] args) {
		SpringApplication.run(IMServerApplication.class, args);
	}
}
