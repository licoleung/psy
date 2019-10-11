package cn.pourfeelings.psy.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author LicoLeung
 * @create 2019-04-08 13:22
 */
@Configuration
public class GeneralConfig extends WebMvcConfigurerAdapter {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/index").setViewName("index");
        registry.addViewController("/login").setViewName("user/login");
        registry.addViewController("/register").setViewName("user/register");
        registry.addViewController("/info").setViewName("user/info");
        registry.addViewController("/edit").setViewName("user/edit");
        registry.addViewController("/editPswPage").setViewName("user/editPsw");
        registry.addViewController("/chat").setViewName("consultation/chat");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(new LoginHandlerInterceptor()).addPathPatterns("/**").excludePathPatterns("/index.html", "/", "/user/login");
        registry.addInterceptor(new LoginHandlerInterceptor()).addPathPatterns("/ask","/test/**","/info","/consultation","/TeachertoChat","/toChat/**");
    }
}
