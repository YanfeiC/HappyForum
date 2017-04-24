package space.shadowc;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by cyf on 17-4-20.
 */
@Configuration
public class HappyWebMvcConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){

        registry.addResourceHandler("resources/**").addResourceLocations("file:///var/www/resources/");

    }
}
