package io.it.incubator.survey.filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * A servlet filter to log request and response The logging implementation is pretty native and for
 * demonstration only
 *
 * @author hemant
 */
@Component
@Order(1)
public class RequestResponseLoggingFilter implements Filter {

  private final static Logger LOG = LoggerFactory.getLogger(RequestResponseLoggingFilter.class);

  @Override
  public int hashCode() {
    return super.hashCode();
  }

  public void init(final FilterConfig filterConfig) {
    LOG.info("Initializing filter :{}", this);
  }

  @Override
  public void doFilter(final ServletRequest request, final ServletResponse response,
      final FilterChain chain)
      throws IOException, ServletException {
    HttpServletRequest req = (HttpServletRequest) request;
    HttpServletResponse res = (HttpServletResponse) response;
    LOG.info("Logging Request:" + req.getHeader("Authorization"));
//    LOG.info("Logging Request:" + req.getCookies().length);
    chain.doFilter(request, response);
    LOG.info("Logging Response :{}", res.getContentType());
  }

  @Override
  public void destroy() {
    LOG.warn("Destructing filter :{}", this);
  }
}