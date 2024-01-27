package io.it.incubator.survey.controller.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.it.incubator.survey.dto.ResultDto;
import io.it.incubator.survey.dto.SurveySettingDto;
import io.it.incubator.survey.dto.TaskDto;
import io.it.incubator.survey.repo.TaskRepository;
import io.it.incubator.survey.service.AdminTaskService;
import io.it.incubator.survey.service.ClientAnswerService;
import io.it.incubator.survey.service.ResultService;
import io.it.incubator.survey.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@CrossOrigin(origins = "http://localhost:9000", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/")
public class TaskController {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private AdminTaskService adminTaskService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private ClientAnswerService clientAnswerService;

    @Autowired
    private ResultService resultService;


    @GetMapping("/tasks")
    public List<TaskDto> getAllTasks() {
        return taskRepository.findAll().stream()
                .map(t -> t.toDto()).toList();
    }

    @GetMapping("/tasks/start")
    public SurveySettingDto startSurvey() {
        return taskService.getSetting();
    }

    @GetMapping("/tasks/{surveyId}/result")
    public ResultDto result(@PathVariable String surveyId) throws JsonProcessingException {
        return resultService.getResult(surveyId);
    }


    @PostMapping("/tasks")
    TaskDto newTask(@RequestBody TaskDto newTask) throws IOException {
        return adminTaskService.save(newTask);
    }

    @PostMapping("/tasks/{surveyId}")
    public ResponseEntity<String> saveAnswer(@PathVariable String surveyId,
                                             @RequestBody TaskDto task) throws IOException {
        clientAnswerService.saveAnswers(task.getArs(), surveyId, task.getId());
        return ResponseEntity.ok(surveyId);
    }

    @GetMapping("/tasks/{id}")
    TaskDto one(@PathVariable Long id) {
        return taskRepository.findById(id).get().toDto();
    }
}