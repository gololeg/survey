package io.it.incubator.survey.controller;

import io.it.incubator.survey.dto.SurveySettingDto;
import io.it.incubator.survey.dto.TaskDto;
import io.it.incubator.survey.model.Task;
import io.it.incubator.survey.repo.TaskRepository;
import io.it.incubator.survey.service.AdminTaskService;
import io.it.incubator.survey.service.ClientAnswerService;
import io.it.incubator.survey.service.TaskService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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


    @GetMapping("/tasks")
    public List<TaskDto> getAllTasks() {
        return taskRepository.findAll().stream()
                .map(t -> t.toDto()).toList();
    }

    @GetMapping("/tasks/start")
    public SurveySettingDto startSurvey() {
        return taskService.getSetting();
    }

    @GetMapping("/t")
    public ModelAndView getTestData() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("welcome");
        mv.getModel().put("data", "Welcome home man");

        return mv;
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
