package io.it.incubator.survey.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.it.incubator.survey.dto.ResultDto;
import io.it.incubator.survey.dto.ResultTaskDto;
import io.it.incubator.survey.model.Answer;
import io.it.incubator.survey.model.ClientAnswer;
import io.it.incubator.survey.model.Task;
import io.it.incubator.survey.repo.AnswerRepository;
import io.it.incubator.survey.repo.ClientAnswerRepository;
import io.it.incubator.survey.repo.ClientSessionRepository;
import io.it.incubator.survey.repo.TaskRepository;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResultService {

  @Autowired
  private ClientSessionRepository clientSessionRepository;

  @Autowired
  private ClientAnswerRepository clientAnswerRepository;

  @Autowired
  private TaskRepository taskRepository;

  @Autowired
  private AnswerRepository answerRepository;

  public ResultDto getResult(String surveyId) throws JsonProcessingException {
    var clientSession = clientSessionRepository.findById(surveyId).get();
    String taskIdsStr = clientSession.getTaskIds();
    ObjectMapper om = new ObjectMapper();
    List<ResultTaskDto> results = new ArrayList<>();
    for (Long taskId : om.readValue(taskIdsStr, Long[].class)) {
      results.add(ResultTaskDto.builder()
          .task(taskRepository.findById(taskId).get().toDto())
          .clientAnswers(clientAnswerRepository.findBySurveyIdAndTaskId(surveyId, taskId)
              .stream().map(ca -> answerRepository.findById(ca.getAnswerId()).get().toDto())
              .toList())
          .build());
    }
    return ResultDto.builder()
        .results(results)
        .commonPercent(calcPercent(toMap(clientAnswerRepository.findBySurveyId(surveyId)),
            taskRepository.findAllById(Arrays.asList(om.readValue(taskIdsStr, Long[].class)))))
        .email(clientSession.getEmail())
        .build();

  }

  private float calcPercent(Map<Long, List<Long>> map, List<Task> tasks) {
    float p = 0;
    for (Task task : tasks) {
      if (map.get(task.getId()) == null) {
        map.put(task.getId(), new ArrayList<>());
      }
      String taskType = task.getType().getName();
      boolean isRadio = "RADIO".equals(taskType);
      float percentForOne = 100 / task.getAnswers().size();
      for (Answer answer : task.getAnswers()) {
        if (answer.getIsRight() && map.get(task.getId()).contains(answer.getId())) {
          p = (isRadio ? 100 : percentForOne) + p;
        } else if (!answer.getIsRight() && !map.get(task.getId()).contains(answer.getId())) {
          p = (isRadio ? 0 : percentForOne) + p;
        }
      }
      System.out.println("percent = " + p);
    }
    return p / tasks.size();
  }


  private static Map<Long, List<Long>> toMap(List<ClientAnswer> list) {
    Map<Long, List<Long>> map = new HashMap<>();
    for (ClientAnswer ca : list) {
      if (map.get(ca.getTaskId()) == null) {
        map.put(ca.getTaskId(), List.of(ca.getAnswerId()));
      } else {
        List<Long> answerIds = new ArrayList<>(map.get(ca.getTaskId()));
        answerIds.add(ca.getAnswerId());
        map.put(ca.getTaskId(), answerIds);
      }
    }
    return map;
  }
}
