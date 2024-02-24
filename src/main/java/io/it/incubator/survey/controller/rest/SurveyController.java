package io.it.incubator.survey.controller.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.it.incubator.survey.dto.ResultDto;
import io.it.incubator.survey.dto.SurveySettingDto;
import io.it.incubator.survey.dto.TaskDto;
import io.it.incubator.survey.service.ClientAnswerService;
import io.it.incubator.survey.service.ResultService;
import io.it.incubator.survey.service.TaskService;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/survey")
public class SurveyController {

  @Autowired
  private TaskService taskService;

  @Autowired
  private ClientAnswerService clientAnswerService;

  @Autowired
  private ResultService resultService;

  @GetMapping("/start")
  public SurveySettingDto startSurvey() {
    return taskService.getSetting();
  }

  @GetMapping("/{surveyId}/result")
  public ResultDto result(@PathVariable String surveyId) throws JsonProcessingException {
    return resultService.getResult(surveyId);
  }

  @PostMapping("/{surveyId}")
  public ResponseEntity<String> saveAnswer(@PathVariable String surveyId,
      @RequestBody TaskDto task) throws IOException {
    clientAnswerService.saveAnswers(task.getArs(), surveyId, task.getId());
    return ResponseEntity.ok(surveyId);
  }

}
