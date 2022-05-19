package com.lzlnb.config;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan("com.lzlnb")
@EnableAspectJAutoProxy
public class SpringConfig {
}
