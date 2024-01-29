package io.it.incubator.survey.repo;

import io.it.incubator.survey.model.ClientSession;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ClientSessionRepository extends JpaRepository<ClientSession, String> {


}
