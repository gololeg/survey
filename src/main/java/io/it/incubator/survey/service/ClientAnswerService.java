package io.it.incubator.survey.service;

import io.it.incubator.survey.model.ClientAnswer;
import io.it.incubator.survey.repo.ClientAnswerRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientAnswerService {

  @Autowired
  private ClientAnswerRepository clientAnswerRepository;

  public void saveAnswers(List<Long> answerIds, String surveyId, Long taskId) {
    List<ClientAnswer> clientAnswers =
        clientAnswerRepository.findBySurveyIdAndTaskId(surveyId, taskId);
    if (clientAnswers.size() > 0) {
      clientAnswerRepository.deleteAll(clientAnswers);
    }
    if (answerIds != null) {
      answerIds.forEach(answerId -> clientAnswerRepository.save(
          new ClientAnswer(surveyId, taskId, answerId)));
    }

  }
}
