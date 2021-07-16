package net.reifenapp.reifenmodelle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;

// 
//exclude = {SecurityAutoConfiguration.class }
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
public class ReifenmodelleApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReifenmodelleApplication.class, args);
	}

	@Bean
	public CorsFilter corsFilter() {
		final CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
		configuration.setAllowedMethods(Arrays.asList("HEAD",
				"GET", "POST", "PUT", "DELETE", "OPTIONS"));
		configuration.setAllowCredentials(true);
		// setAllowedHeaders is important! Without it, OPTIONS preflight request
		// will fail with 403 Invalid CORS request
		configuration.setAllowedHeaders(Arrays.asList(
				"Origin, Accept", "X-Requested-With", "Access-Control-Request-Method",
				"Origin", "Access-Control-Allow-Origin", "Access-Control-Request-Headers",
				"Accept", "Authorization", "Cache-Control", "Content-Type")
		);
		configuration.setExposedHeaders(Arrays.asList(
				"Access-Control-Request-Method",
				"Origin", "Access-Control-Allow-Origin", "Access-Control-Allow-Credentials",
				"Accept", "Authorization", "Content-Type"));
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return new CorsFilter(source);
	}

}
