package io.it.incubator.survey.controller.rest;

import io.it.incubator.survey.model.User;
import io.it.incubator.survey.service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import javax.naming.AuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class UserController {

  @Autowired
  private UserService userService;

  @PostMapping(value = "/login")
  public boolean login(HttpServletResponse response, @RequestBody User user)
      throws AuthenticationException {
    response.addCookie(new Cookie("authId", userService.auth(user)));

    return true;
  }

  @GetMapping(value = "/auth/me")
  public boolean authMe() {
    return true;
  }

  @GetMapping(value = "/logout")
  public boolean logout(HttpServletResponse response) {
    response.addCookie(new Cookie("authId", null));
    return true;
  }

}
