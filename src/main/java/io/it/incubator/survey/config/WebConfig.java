package io.it.incubator.survey.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.it.incubator.survey.filter.RequestResponseLoggingFilter;
import io.it.incubator.survey.repo.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerExceptionResolver;

@Configuration
public class WebConfig {

  @Autowired
  private SessionRepository sessionRepository;
  @Bean
  public WebServerFactoryCustomizer<ConfigurableServletWebServerFactory> enableDefaultServlet() {
    return factory -> factory.setRegisterDefaultServlet(true);
  }


  @Bean
  public ObjectMapper objectMapper() {
    return new ObjectMapper();
  }

  @Bean
  public FilterRegistrationBean<RequestResponseLoggingFilter> loggingFilter(){
    FilterRegistrationBean<RequestResponseLoggingFilter> registrationBean
        = new FilterRegistrationBean<>();



    registrationBean.setFilter(new RequestResponseLoggingFilter(sessionRepository, objectMapper()));
    registrationBean.addUrlPatterns("/api/v1/tasks/*");
    registrationBean.setOrder(1);

    return registrationBean;
  }
}
