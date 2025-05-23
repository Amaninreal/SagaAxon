package com.progressivecoder.ordermanagement.orderservice;

import com.thoughtworks.xstream.XStream;
import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class OrderServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderServiceApplication.class, args);
	}

	@PostConstruct
	public void configureXStream() {
		XStream.setupDefaultSecurity(new XStream());
		System.setProperty("com.thoughtworks.xstream.security.allowTypesByRegExp",
				"com\\.progressivecoder\\.ecommerce\\.commands\\..*");
		System.setProperty("com.thoughtworks.xstream.security", "ALL");
	}
}