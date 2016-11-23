package utouu.im.net.http.intercepter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class MvcIntercepterConfig extends WebMvcConfigurerAdapter{
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(accountLoginIntercepter()).addPathPatterns("/v1/**");
	}
	
	@Bean
	public V1AccountLoginIntercepter accountLoginIntercepter(){
		V1AccountLoginIntercepter v1AccountLoginIntercepter = new V1AccountLoginIntercepter();
		return v1AccountLoginIntercepter;
	}
}
