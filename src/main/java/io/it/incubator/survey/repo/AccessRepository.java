package io.it.incubator.survey.repo;

import io.it.incubator.survey.model.Access;
import io.it.incubator.survey.model.Setting;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AccessRepository extends JpaRepository<Access, String> {

}
