package com.empdep.microservices.api;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EmpdepApiGatewayConfiguration {

	@Bean
	public RouteLocator gatewayRouter(RouteLocatorBuilder builder) { 
		
		return builder.routes()
				.route(p -> p
					.path("/get")
					.filters(f -> f
							.addRequestHeader("MyHeader", "MyURI")
							.addRequestParameter("Param","MyValue"))
					.uri("http://httpbin.org:80"))
				.route(p -> p.path("/department-service/**")
						.uri("lb://department-service"))
				.route(p -> p.path("/employee-service/**")
						.uri("lb://employee-service"))
				.route(p -> p.path("/employee-service-feign/**")
						.uri("lb://employee-service"))
				.build();
	}
}
