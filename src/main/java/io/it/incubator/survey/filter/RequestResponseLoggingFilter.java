package io.it.incubator.survey.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.it.incubator.survey.dto.ResponseError;
import io.it.incubator.survey.exception.AuthException;
import io.it.incubator.survey.model.Session;
import io.it.incubator.survey.repo.SessionRepository;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class RequestResponseLoggingFilter implements Filter {

  public RequestResponseLoggingFilter(SessionRepository sessionRepository,
      ObjectMapper objectMapper, String adminUrl) {
    this.sessionRepository = sessionRepository;
    this.objectMapper = objectMapper;
    this.adminUrl = adminUrl;
  }

  private static int SESSION_MINUTES_TIMEOUT = 15;
  private SessionRepository sessionRepository;
  private ObjectMapper objectMapper;
  private String adminUrl;

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
    String authErrorMessage = null;
    boolean isAuthMeReq = req.getRequestURI().toString().equals("/api/v1/auth/me");
    if (req.getRequestURI().toString().startsWith("/api/v1/tasks") ||
        req.getRequestURI().toString().startsWith("/api/v1/settings") || isAuthMeReq) {
      authErrorMessage = "Auth token defined wrongly";
      Cookie[] cookies = req.getCookies();
      if (cookies != null) {
        System.out.println("cookies=" + objectMapper.writeValueAsString(cookies));
        Optional<Cookie> authCookie = Arrays.stream(cookies)
            .filter(c -> "authId".equals(c.getName())).findFirst();
        if (authCookie.isPresent()) {
          Optional<Session> optionalSession = sessionRepository.findById(
              authCookie.get().getValue());
          if (optionalSession.isPresent()) {
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.MINUTE, -SESSION_MINUTES_TIMEOUT);
            System.out.println("date1=" + optionalSession.get().getLastActiveDate());
            System.out.println(
                "date2=" + LocalDateTime.ofInstant(calendar.toInstant(), calendar.getTimeZone()
                    .toZoneId()));
            if (optionalSession.get().getLastActiveDate()
                .isBefore(LocalDateTime.ofInstant(calendar.toInstant(), calendar.getTimeZone()
                    .toZoneId()))) {
              authErrorMessage = "Token is expired.";
            } else {
              if (!isAuthMeReq) {

                Session session = optionalSession.get();
                session.setLastActiveDate(LocalDateTime.now());
                sessionRepository.save(session);
              }
              authErrorMessage = null;
            }

          }
        }
      }
    }
    if (authErrorMessage != null && !"OPTIONS".equals(req.getMethod())) {
      res.setStatus(HttpStatus.UNAUTHORIZED.value());
//      res.setStatus(200);
      res.setContentType("application/json");
      res.setHeader("Access-Control-Allow-Origin", adminUrl);
      res.setHeader("Access-Control-Allow-Credentials", "true");
      PrintWriter out = res.getWriter();
      out.print(objectMapper.writeValueAsString(
          new ResponseError(new AuthException(authErrorMessage))));
      out.flush();
      return;
    }
    chain.doFilter(request, response);
  }

  @Override
  public void destroy() {
    LOG.warn("Destructing filter :{}", this);
  }
}