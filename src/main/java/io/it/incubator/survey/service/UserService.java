package io.it.incubator.survey.service;

import io.it.incubator.survey.exception.AuthException;
import io.it.incubator.survey.model.Session;
import io.it.incubator.survey.model.User;
import io.it.incubator.survey.repo.SessionRepository;
import io.it.incubator.survey.repo.UserRepository;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;
import javax.naming.AuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  @Autowired
  private UserRepository userRepository;
  @Autowired
  private SessionRepository sessionRepository;

  public String auth(User user) throws AuthenticationException {
    Optional<User> userOptional = userRepository.findByLoginAndPassword(user.getLogin(),
        user.getPassword());
    if (!userOptional.isPresent()) {
      throw new AuthException("Login or password is not exist");
    }
    var sessionId = UUID.randomUUID().toString();
    sessionRepository.save(
        new Session(sessionId, user.getLogin(), LocalDateTime.now()));
    return sessionId;
  }

}
