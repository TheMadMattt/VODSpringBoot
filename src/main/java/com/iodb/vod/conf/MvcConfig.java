package com.iodb.vod.conf;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
	registry.addViewController("/login").setViewName("login");
	registry.addViewController("/register").setViewName("register");
	registry.addViewController("/home").setViewName("home");
	registry.addViewController("/movies").setViewName("movies");
	registry.addViewController("/admin/movies/new").setViewName("admin/movies/new");
	registry.addViewController("/admin/movies/deleteMovie/{id}").setViewName("movies");
	registry.addViewController("/admin/movies/updateMovie/{id}").setViewName("admin/movies/update");
	registry.addViewController("/admin/movies/update").setViewName("admin/movies/update");
	registry.addViewController("/admin/customers").setViewName("admin/customers");
	registry.addViewController("/admin/customers/deleteCustomer/{id}").setViewName("admin/customers/delete");
	registry.addViewController("/admin/new-customers").setViewName("admin/new-customers");
	registry.addViewController("/admin/new-customers/activate/{id}").setViewName("admin/new-customers/activate");
	
	registry.addViewController("/403").setViewName("/");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
	registry.addResourceHandler("/resources/**")
		.addResourceLocations("/resources/");
    }
}
