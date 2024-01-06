package io.it.incubator.survey.repo;

import io.it.incubator.survey.model.Answer;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AnswerRepository extends JpaRepository<Answer, Long> {

}
