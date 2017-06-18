
package com.practice.springpractice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

/**
 *
 * @author PDBD
 */
@Configuration
@ComponentScan(basePackages = {"com.practice.springpractice"})
//@PropertySource("classpath:properties/config_${spring.profiles.active:development}.properties")

@Import({WebMvcConfig.class, DatabaseConfig.class, SecurityConfig.class})
public class RootConfig {
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

}
