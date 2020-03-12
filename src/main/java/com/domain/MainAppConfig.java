package com.domain;

import java.util.Properties;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jms.JmsAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication(exclude = JmsAutoConfiguration.class)
public class MainAppConfig extends SpringBootServletInitializer {

	public static void main(String[] args) {
		(new SpringApplicationBuilder(MainAppConfig.class)).sources(new Class[] { MainAppConfig.class })
				.properties(getProperties()).run(args);
	}

	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(new Class[] { MainAppConfig.class }).properties(getProperties());
	}

	static Properties getProperties() {
		Properties props = new Properties();
		props.put("spring.config.location", "file:${catalina.home}/okm-integration.properties");
		return props;
	}

}
