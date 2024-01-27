package io.it.incubator.survey.repo;

import io.it.incubator.survey.model.ClientAnswer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ClientAnswerRepository extends JpaRepository<ClientAnswer, Long> {
    List<ClientAnswer> findBySurveyIdAndTaskId(String surveyId, Long taskId);
    List<ClientAnswer> findBySurveyId(String surveyId);
}
