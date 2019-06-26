package com.demo.config;

import com.demo.config.converter.ObjectHttpMessageConverter;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.demo.config.annotation.AwesomeParamMethodArgumentResolver;
import com.demo.config.annotation.MatchPathVariableMethodArgumentResolver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    @Value("${is_underscore_param_name:false}")
    boolean isUnderscoreName;

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        super.addArgumentResolvers(argumentResolvers);
        AwesomeParamMethodArgumentResolver resolver = new AwesomeParamMethodArgumentResolver();
        resolver.setLowerUnderscoreName(isUnderscoreName);
        argumentResolvers.add(resolver);
        argumentResolvers.add(new MatchPathVariableMethodArgumentResolver());
    }

    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        ObjectHttpMessageConverter converter = new ObjectHttpMessageConverter();
        if (isUnderscoreName) {
            converter.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
        }
        converters.add(converter);
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        super.addCorsMappings(registry);
        //TIPS: consider limit CROS in production env
        registry.addMapping("/**")
                .allowedMethods("GET", "HEAD", "PUT", "DELETE", "POST", "PATCH")
                .allowedOrigins("*");
    }

//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/docApi/swagger-ui.html**").addResourceLocations("classpath:/META-INF/resources/swagger-ui.html");
//        registry.addResourceHandler("/docApi/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
//        super.addResourceHandlers(registry);
//    }
//
//    @Override
//    public void addViewControllers(ViewControllerRegistry registry) {
//        registry.addRedirectViewController("/docApi/v2/api-docs", "/v2/api-docs");
//        registry.addRedirectViewController("/docApi/swagger-resources/configuration/ui", "/swagger-resources/configuration/ui");
//        registry.addRedirectViewController("/docApi/swagger-resources/configuration/security", "/swagger-resources/configuration/security");
//        registry.addRedirectViewController("/docApi/swagger-resources", "/swagger-resources");
//
//        super.addViewControllers(registry);
//    }

}
