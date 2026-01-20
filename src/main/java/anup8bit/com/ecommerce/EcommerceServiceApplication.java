package anup8bit.com.ecommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class EcommerceServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcommerceServiceApplication.class, args);
	}

}
