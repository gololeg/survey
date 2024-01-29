package io.it.incubator.survey.repo;

import io.it.incubator.survey.model.ClientAnswer;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ClientAnswerRepository extends JpaRepository<ClientAnswer, Long> {

  List<ClientAnswer> findBySurveyIdAndTaskId(String surveyId, Long taskId);

  List<ClientAnswer> findBySurveyId(String surveyId);
}
