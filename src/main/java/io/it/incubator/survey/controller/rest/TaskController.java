package io.it.incubator.survey.controller.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.it.incubator.survey.dto.ResultDto;
import io.it.incubator.survey.dto.SurveySettingDto;
import io.it.incubator.survey.dto.TaskDto;
import io.it.incubator.survey.model.Task;
import io.it.incubator.survey.repo.TaskRepository;
import io.it.incubator.survey.service.AdminTaskService;
import io.it.incubator.survey.service.ClientAnswerService;
import io.it.incubator.survey.service.ResultService;
import io.it.incubator.survey.service.TaskService;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:9000", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/tasks")
public class TaskController {

  @Autowired
  private TaskRepository taskRepository;

  @Autowired
  private AdminTaskService adminTaskService;
  private ResultService resultService;

  @GetMapping
  public List<TaskDto> getAllTasks() {
    List<TaskDto> list= taskRepository
        .findByOrderByCreateDateDesc().stream().map(t -> t.toCommonDto()).toList();
    return new ArrayList<>(list);
//        taskRepository.findAll().stream()
//        .map(t -> t.toCommonDto()).toList();
  }

  @PostMapping
  TaskDto newTask(@RequestBody TaskDto newTask) throws IOException {
    return adminTaskService.save(newTask);
  }

  @RequestMapping(value = "/{taskId}", method = RequestMethod.PUT)
  public TaskDto editTask(@RequestBody TaskDto taskDto, @PathVariable Long taskId)
      throws IOException {
    Task task = taskRepository.findById(taskId).get();
    task.setName(taskDto.getName());
    task.setDescription(taskDto.getDescription());
    task.setImage(taskDto.getFile().getBytes());
    return taskRepository.save(task).toDto();
  }

  @GetMapping("/{taskId}")
  TaskDto one(@PathVariable Long taskId) {
    return taskRepository.findById(taskId).get().toDto();
  }
}
