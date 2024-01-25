package io.it.incubator.survey.repo;

import io.it.incubator.survey.model.ClientSession;
import io.it.incubator.survey.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.print.Pageable;
import java.util.List;


public interface ClientSessionRepository extends JpaRepository<ClientSession, String> {


}
