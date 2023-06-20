package ca.sunlife.poc.boogle.config;

import java.util.Arrays;
import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class BoogleConfig {

	@Bean
	public WebClient webClient() {
		return WebClient.builder().build();
	}
	
	@Bean
	public CorsFilter corsFilter() {
	    final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	    final CorsConfiguration config = new CorsConfiguration();
	    config.setAllowCredentials(true);
	    config.setAllowedOrigins(Collections.singletonList("http://localhost:3000"));
	    config.setAllowedHeaders(Arrays.asList("*"));
	    config.setAllowedMethods(Arrays.asList("GET", "POST"));
	    source.registerCorsConfiguration("/**", config);
	    return new CorsFilter(source);
	}

}
