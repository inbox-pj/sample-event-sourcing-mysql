package com.usl.microservices.sampleeventsourcing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.util.JdkIdGenerator;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class SampleEventSourcingApplication {

	public static void main(String[] args) {
		SpringApplication.run(SampleEventSourcingApplication.class, args);
	}

	@Bean
	public Docket productApi() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.usl.microservices.sampleeventsourcing"))
				.paths(PathSelectors.any()).build()
				.apiInfo(new ApiInfoBuilder().version("1.0").title("Insurance Application API").description("Documentation of Insurance Application API v1.0").build());
	}
	
	@Bean
	public JdkIdGenerator getJdkIdGenerator() {
		return new JdkIdGenerator();
	}
	
}
