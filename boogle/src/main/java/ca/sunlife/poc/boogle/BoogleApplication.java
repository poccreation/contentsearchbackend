package ca.sunlife.poc.boogle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@Configuration
@ComponentScan(basePackages = { "ca.sunlife.poc.boogle" })
@PropertySource(value = { "file:config/boogle.properties", "file:config/urlmapping.properties",
		"file:config/message.properties" })
public class BoogleApplication {

	public static void main(String[] args) {
		SpringApplication.run(BoogleApplication.class, args);
	}

}
