package io.it.incubator.survey.controller.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.it.incubator.survey.dto.ResultDto;
import io.it.incubator.survey.dto.SurveySettingDto;
import io.it.incubator.survey.dto.TaskDto;
import io.it.incubator.survey.model.Task;
import io.it.incubator.survey.repo.TaskRepository;
import io.it.incubator.survey.service.AccessService;
import io.it.incubator.survey.service.ClientAnswerService;
import io.it.incubator.survey.service.ResultService;
import io.it.incubator.survey.service.TaskService;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
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
  private TaskRepository taskRepository;

  @Autowired
  private ClientAnswerService clientAnswerService;

  @Autowired
  private ResultService resultService;
  @Autowired
  private AccessService accessService;

  @GetMapping("/start/{email}")
  public SurveySettingDto startSurvey(@PathVariable String email)
      throws JsonProcessingException, UnsupportedEncodingException {
    return taskService.getSetting(email);
  }

  @GetMapping("/{surveyId}/result")
  public ResultDto result(@PathVariable String surveyId) throws JsonProcessingException {
    return resultService.getResult(surveyId);
  }

  @PostMapping("/{surveyId}")
  public ResponseEntity<String> saveAnswer(@PathVariable String surveyId,
      @RequestBody TaskDto task) throws IOException, ParseException {
    accessService.checkTimeout(surveyId);

    clientAnswerService.saveAnswers(task.getArs(), surveyId, task.getId());
    return ResponseEntity.ok(surveyId);
  }

  @GetMapping("/task/{taskId}")
  public ResponseEntity<TaskDto> one(@PathVariable Long taskId) {
    return ResponseEntity.ok(taskRepository.findById(taskId).get().toDto());
  }

}
