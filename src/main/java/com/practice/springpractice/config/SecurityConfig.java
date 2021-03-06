
package com.practice.springpractice.config;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

/**
 *
 * @author PDBD
 */

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)

public class SecurityConfig extends WebSecurityConfigurerAdapter{ 
    @Autowired
    private DataSource dataSource;
    @Autowired
    private UserDetailsService userDetailsService;
    
   @Bean
   public PasswordEncoder passwordEncoder(){
       return new BCryptPasswordEncoder();
    
}

@Bean
public PersistentTokenRepository persistentTokenReposity(){
    JdbcTokenRepositoryImpl tokenRepositoryImpl = new JdbcTokenRepositoryImpl();
    tokenRepositoryImpl.setDataSource(dataSource);
    return tokenRepositoryImpl;
    
}


   @Autowired
   public void configureGlobal (AuthenticationManagerBuilder auth) throws Exception{
       auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
       
   }

    @Override
    protected void configure (HttpSecurity httpSecurity) throws Exception {
        httpSecurity 
                 .authorizeRequests()
                .antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
                .and()
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/")
                .usernameParameter("username")
                .passwordParameter("password")
                .failureUrl("/login?error")
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login?logout")
                .and()
                .csrf()
                .and()
                .rememberMe()
                .rememberMeParameter("remember-me")
                .tokenRepository(persistentTokenReposity()).tokenValiditySeconds(86400);
        
    }

    
}