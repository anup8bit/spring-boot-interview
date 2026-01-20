package anup8bit.com.ecommerce.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}

/**
 * What happen if this configration file does not exist in this application, Then
 *
 * Error - Could not autowire. No beans of 'RestTemplate' type found.
 *
 * This error is expected because RestTemplate is not auto-registered unless you define it explicitly. Fix it by adding a configuration bean.
 */

/**
 * Common mistakes to double-check:
 * Config class must be under a package scanned by Spring Boot
 * Don’t mark RestTemplate with @Component
 * If using Spring Boot 3+, this is still required
 * Production notes (for your microservices + Kong setup):
 * Add ClientHttpRequestInterceptor for JWT propagation
 * Prefer OpenFeign long-term (Spring Cloud)
 * If you want, I can show:
 * JWT interceptor setup
 * Feign replacement (drop-in)
 */