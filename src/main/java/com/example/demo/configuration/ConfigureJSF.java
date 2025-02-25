package com.example.demo.configuration;

import java.util.Arrays;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import jakarta.faces.webapp.FacesServlet;
import jakarta.servlet.ServletContext;

@Configuration
public class ConfigureJSF {

	@Bean
	public ServletRegistrationBean<FacesServlet> jsfServletRegistration(ServletContext servletContext) {
		servletContext.setInitParameter("com.sun.faces.forceLoadConfiguration", Boolean.TRUE.toString());
		
		var srb = new ServletRegistrationBean<FacesServlet>();
		srb.setServlet(new FacesServlet());
		srb.setUrlMappings(Arrays.asList("*.xhtml"));
		srb.setLoadOnStartup(1);
		return srb;
	}

    @Bean
	public com.sun.faces.config.ConfigureListener mojarraConfigureListener() {
		return new com.sun.faces.config.ConfigureListener();
	}
}
