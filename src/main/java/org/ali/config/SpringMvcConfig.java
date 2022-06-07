package org.ali.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@ComponentScan({"org.ali.controller","org.ali.config"})
@EnableWebMvc
public class SpringMvcConfig {
}
