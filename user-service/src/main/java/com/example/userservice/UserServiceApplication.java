package com.example.userservice;

import com.example.userservice.filter.AuthFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.client.RestTemplate;
@ComponentScan("com.*")
@EnableJpaRepositories("com.example.userservice.repository")
@EntityScan("com.*")
@SpringBootApplication(exclude = org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class)
public class UserServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserServiceApplication.class, args);
	}

	@Bean
	public FilterRegistrationBean<AuthFilter> filterRegistrationBean() {
		FilterRegistrationBean<AuthFilter> filterRegistrationBean = new FilterRegistrationBean<>();
		filterRegistrationBean.setFilter(new AuthFilter());
		filterRegistrationBean.addUrlPatterns("/api/v1/users/admin/*");
		filterRegistrationBean.addUrlPatterns("/api/v1/bookings/*");
		return filterRegistrationBean;
	}

	@Bean
	public RestTemplate restTemplate(){
		return new RestTemplate();
	}

}
