package io.it.incubator.survey.service;

import io.it.incubator.survey.exception.AccessException;
import io.it.incubator.survey.model.Access;
import io.it.incubator.survey.repo.AccessRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccessService {

  @Autowired
  private AccessRepository accessRepository;

  public boolean checkAccess(String email) {
    Optional<Access> optionalAccess = accessRepository.findById(email);
    if (!optionalAccess.isPresent() || optionalAccess.get().getAttemptsCount() == 0) {
      throw new AccessException("User with email = " + email + " has no access to start survey");
    }
    Access access = optionalAccess.get();
    access.setAttemptsCount(access.getAttemptsCount() - 1);
    accessRepository.save(access);
    return true;
  }
}
