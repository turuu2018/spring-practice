
package com.practice.springpractice.config;

/**
 *
 * @author PDBD
 */


import java.util.Locale;
import java.util.logging.Logger;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.ResourceBundleViewResolver;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesView;
import org.springframework.web.servlet.view.tiles3.TilesViewResolver;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"com.practice.springpractice.controller"})
public class WebMvcConfig extends WebMvcConfigurerAdapter {
        
    private static final Logger LOGGER = Logger.getLogger(WebMvcConfig.class.getName());
    
       @Override 
    public void addInterceptors(InterceptorRegistry registry){
       LOGGER.info("Spring practice Adding interceptors ...");
       LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
       localeChangeInterceptor.setParamName("lang");
       registry.addInterceptor(localeChangeInterceptor);
    }
    
    @Bean
    public TilesConfigurer tilesConfigurer() {
        LOGGER.info("[Spring practice] Configuring tiles...");
        TilesConfigurer tilesConfigurer = new TilesConfigurer();
        tilesConfigurer.setDefinitions(new String[]{"/WEB-INF/**/tiles.xml"});
        tilesConfigurer.setCheckRefresh(true);
        return tilesConfigurer;
    }
    
    
    @Bean
    public TilesViewResolver tilesViewResolver() {
        LOGGER.info("[Spring practice  ]Configuring tiles view resolver...");
        TilesViewResolver tilesViewResolver = new TilesViewResolver();
        tilesViewResolver.setViewClass(TilesView.class);
        tilesViewResolver.setOrder(0);
        return tilesViewResolver;
    }

    @Bean
    public ResourceBundleViewResolver xlsViewResolver() {
        LOGGER.info("[Spring practice] Configuring resource bundle view resolver...");
        ResourceBundleViewResolver resourceBundleViewResolver = new ResourceBundleViewResolver();
        resourceBundleViewResolver.setBasename("views");
        resourceBundleViewResolver.setOrder(1);
        return resourceBundleViewResolver;
    }

    @Bean
    public ViewResolver viewResolver() {
        LOGGER.info("[Spring practice] Configuring view resolver...");
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/view/");
        viewResolver.setSuffix(".jsp");
        viewResolver.setOrder(2);
        return viewResolver;
    }
    
     
   
   
    
 
          
   @Bean
   public MessageSource messageSource() {
       LOGGER.info("Spring practice Configuring message source ...");
       ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
       messageSource.setBasename("/WEB-INF/messages/messages");
       messageSource.setCacheSeconds(0);
       messageSource.setDefaultEncoding("UTF-8");
       return messageSource;
   }
   
    @Override
    public void addResourceHandlers (ResourceHandlerRegistry registry)
    {
        LOGGER.info("[spring test] adding resource handlers...");
        registry.addResourceHandler("/static/**").addResourceLocations("/static/").setCachePeriod(604800);
        
    }
 
    @Override
    public Validator getValidator(){
        return vlidator();
    }    
    
    @Bean 
    public LocalValidatorFactoryBean vlidator(){
        LOGGER.info("Spring practice Configuring hibernate validator ...");
        LocalValidatorFactoryBean localValidatorFactoryBean = new LocalValidatorFactoryBean();
        localValidatorFactoryBean.setValidationMessageSource(messageSource());
        return localValidatorFactoryBean;
    }

@Bean 
public LocaleResolver localeResolver() {
    
    CookieLocaleResolver localeResolver = new CookieLocaleResolver();
    localeResolver.setDefaultLocale(new Locale("mn"));
    return localeResolver;
}

   
    
    
}
