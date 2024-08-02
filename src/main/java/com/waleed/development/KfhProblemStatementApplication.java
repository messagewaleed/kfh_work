package com.waleed.development;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableCaching
@EnableSwagger2
public class KfhProblemStatementApplication {

	public static void main(String[] args) {
		SpringApplication.run(KfhProblemStatementApplication.class, args);
	}

}
