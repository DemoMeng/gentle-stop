package com.mqz.gentle.stop;

import com.mqz.gentle.stop.A4better.GentleStopConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GentleStopApplication {

	public static void main(String[] args) {

		/** 优雅停机配置 */
		ConfigurableApplicationContext run = SpringApplication.run(GentleStopApplication.class, args);
		run.registerShutdownHook();

		//SpringApplication.run(GentleStopApplication.class, args);
	}

	/**
	 * 优雅停机配置 bean注入到启动类
	 * @return
	 */
	@Bean
	public GentleStopConfig elegantShutdownConfig() {
		return new GentleStopConfig();
	}

	/**
	 * 优雅停机 ServerBean工厂注入
	 * @return
	 */
	@Bean
	public ServletWebServerFactory servletContainer() {
		TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory();
		tomcat.addConnectorCustomizers(elegantShutdownConfig());
		return tomcat;
	}


}
