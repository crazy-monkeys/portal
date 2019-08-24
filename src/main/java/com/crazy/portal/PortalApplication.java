package com.crazy.portal;

import com.crazy.portal.util.SpringBeanTool;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;


@EnableTransactionManagement
@EntityScan
@EnableCaching
@EnableScheduling
@SpringBootApplication
public class PortalApplication extends SpringBootServletInitializer {

	@Resource
	private ApplicationContext applicationContext;

	public static void main(String[] args) {
		SpringApplication.run(PortalApplication.class, args);
	}

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		super.onStartup(servletContext);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(PortalApplication.class);
	}

	@PostConstruct
	public void inital() {
		SpringBeanTool.setApplicationContext(applicationContext);
	}
}

