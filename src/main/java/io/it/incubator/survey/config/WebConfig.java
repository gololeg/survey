package io.it.incubator.survey.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.it.incubator.survey.filter.RequestResponseLoggingFilter;
import io.it.incubator.survey.repo.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
@Autowired
  private SessionRepository sessionRepository;
  @Autowired
  @Value("${admin.ui.web.url}")
  private String adminUrl;

  @Override
  public void addCorsMappings(CorsRegistry registry) {
    registry.addMapping("/api/v1/tasks/**")
        .allowedOrigins(adminUrl)
        .allowCredentials(true);
    registry.addMapping("/api/v1/settings/**")
        .allowedOrigins(adminUrl)
        .allowCredentials(true);
    registry.addMapping("/api/v1/login/**")
        .allowedOrigins(adminUrl)
        .allowCredentials(true);
    registry.addMapping("/api/v1/survey/**")
        .allowedOrigins(adminUrl)
        .allowCredentials(true);
    registry.addMapping("/api/v1/auth/**")
        .allowedOrigins(adminUrl)
        .allowCredentials(true);
  }

  @Bean
  public String adminUrl(){
    return adminUrl;
  }
  @Bean
  public WebServerFactoryCustomizer<ConfigurableServletWebServerFactory> enableDefaultServlet() {
    return factory -> factory.setRegisterDefaultServlet(true);
  }


  @Bean
  public ObjectMapper objectMapper() {
    return new ObjectMapper();
  }

  @Bean
  public FilterRegistrationBean<RequestResponseLoggingFilter> loggingFilter() {
    FilterRegistrationBean<RequestResponseLoggingFilter> registrationBean
        = new FilterRegistrationBean<>();

    registrationBean.setFilter(
        new RequestResponseLoggingFilter(sessionRepository, objectMapper(), adminUrl));

    registrationBean.addUrlPatterns("/test/*");
//    registrationBean.setOrder(2);

    return registrationBean;
  }
}
