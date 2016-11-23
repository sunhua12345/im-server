package utouu.im.net.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import com.utouu.commons.spring.jtt.JdbcTemplateProxy;
import com.utouu.commons.spring.jtt.JdbcTemplateTool;

@Configuration
public class DataBaseConfig {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Bean
	public JdbcTemplateTool jdbcTemplateTool() {
		JdbcTemplateTool jdbcTemplateTool = new JdbcTemplateTool();
		jdbcTemplateTool.setJdbcTemplate(jdbcTemplate);
		return jdbcTemplateTool;
	}

	@Bean
	public JdbcTemplateProxy jdbcTemplateProxy() {
		JdbcTemplateProxy jdbcTemplateProxy = new JdbcTemplateProxy();
		jdbcTemplateProxy.setJdbcTemplate(jdbcTemplate);
		return jdbcTemplateProxy;
	}
}
