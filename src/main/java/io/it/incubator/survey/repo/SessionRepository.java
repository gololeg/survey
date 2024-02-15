package io.it.incubator.survey.repo;

import io.it.incubator.survey.model.ClientSession;
import io.it.incubator.survey.model.Session;
import org.springframework.data.jpa.repository.JpaRepository;


public interface SessionRepository extends JpaRepository<Session, String> {


}
