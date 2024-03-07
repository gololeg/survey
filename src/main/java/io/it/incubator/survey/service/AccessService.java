package io.it.incubator.survey.service;

import io.it.incubator.survey.exception.AccessException;
import io.it.incubator.survey.model.Access;
import io.it.incubator.survey.model.ClientSession;
import io.it.incubator.survey.repo.AccessRepository;
import io.it.incubator.survey.repo.ClientSessionRepository;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccessService {

  @Autowired
  private AccessRepository accessRepository;

  @Autowired
  private ClientSessionRepository clientSessionRepository;

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

  public void checkTimeout(String surveyId) throws ParseException {
    Optional<ClientSession> clientSessionOptional = clientSessionRepository.findById(surveyId);
    if (clientSessionOptional.isPresent()) {
      SimpleDateFormat f = new SimpleDateFormat("MM.dd.yyyy HH:mm:ss");
      String expiredDate = clientSessionOptional.get().getExpiredDate();
      if (f.parse(expiredDate).after(new Date())) {
        return;
      }
    }
    throw new AccessException("Survey timeout exception.");
  }
}
