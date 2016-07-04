package fgarcia.test.protocols.server.web;

import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.ShallowEtagHeaderFilter;

import java.util.Collections;

@Configuration
public class Filter {

    /**
     * Filter used to add various header to http responses.
     * We are particularly interested by the content-length.
     */
    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean filterBean = new FilterRegistrationBean();
        filterBean.setFilter(new ShallowEtagHeaderFilter());
        filterBean.setUrlPatterns(Collections.singletonList("*"));
        return filterBean;
    }
}