package shop.mtcoding.productapp_v5.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        LoginInterceptor loginIntercepter = new LoginInterceptor();
        registry.addInterceptor(loginIntercepter)
                .addPathPatterns("/**") // 적용할 URL, "/**" 는 모든 URL을 뜻함
                .excludePathPatterns("/", "/product", "/product/{productId}", "/loginForm", "/joinForm",
                        "/admin/loginForm"); // 제외할 URL
    }
}
