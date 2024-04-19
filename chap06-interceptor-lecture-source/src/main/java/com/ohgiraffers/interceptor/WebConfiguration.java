package com.ohgiraffers.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(new StopWatchInterceptor(new MenuService()))
                .addPathPatterns("/*")
                /* static 하위의 정적 리소스는 인터셉터가 적용되지 않도록 한다. */
                .excludePathPatterns("/css/**")
                .excludePathPatterns("/images/**")
                .excludePathPatterns("/js/**")
                /* /error로 포워딩 되는 경로도 제외해주어야 한다. */
                .excludePathPatterns("/error");
    }
}
