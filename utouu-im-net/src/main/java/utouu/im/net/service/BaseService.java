package utouu.im.net.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.utouu.commons.spring.jtt.JdbcTemplateProxy;
import com.utouu.commons.spring.jtt.JdbcTemplateTool;

public class BaseService{
	public final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	public JdbcTemplateProxy jdbcTemplateProxy;
	@Autowired
	public JdbcTemplate jdbcTemplate;
	@Autowired
	public JdbcTemplateTool jdbcTemplateTool;
}
