package com.crazy.portal;

import com.crazy.portal.util.HttpClientUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.net.util.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.PostConstruct;

@EntityScan
@EnableCaching
@EnableScheduling
@SpringBootApplication
@EnableTransactionManagement(proxyTargetClass = true)
@Slf4j
public class PortalApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(PortalApplication.class, args);
	}

	@Value("${ecc.api.url}")
	private String ECC_API_URL;

	@Value("${ecc.auth.username}")
	private String ECC_USERNAME;

	@Value("${ecc.auth.password}")
	private String ECC_PASSWORD;

	@PostConstruct
	public void onStartup(){
		log.info("========================================【AppContext Start】");
		String authInfo = String.format("%s:%s",ECC_USERNAME,ECC_PASSWORD);
		String base64AuthInfo = Base64.encodeBase64String(authInfo.getBytes());
		HttpClientUtils.AUTH_SECRET = "Basic " + base64AuthInfo;
		HttpClientUtils.ECC_API_URL = ECC_API_URL;
		log.info("========================================【AppContext End】");
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(PortalApplication.class);
	}

}

