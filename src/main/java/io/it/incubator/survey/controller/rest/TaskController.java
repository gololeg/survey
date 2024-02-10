package io.it.incubator.survey.controller.rest;

import io.it.incubator.survey.dto.TaskDto;
import io.it.incubator.survey.model.Task;
import io.it.incubator.survey.repo.TaskRepository;
import io.it.incubator.survey.service.AdminTaskService;
import io.it.incubator.survey.service.ResultService;
import java.io.IOException;
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
  public ResponseEntity<List<TaskDto>> getAllTasks() throws RuntimeException {
    return ResponseEntity.ok(taskRepository
        .findByOrderByCreateDateDesc().stream().map(t -> t.toCommonDto()).toList());
  }

  @PostMapping
  public ResponseEntity<TaskDto> newTask(@RequestBody TaskDto newTask) throws IOException {
    return ResponseEntity.ok(adminTaskService.save(newTask));
  }

  @RequestMapping(value = "/{taskId}", method = RequestMethod.PUT)
  public ResponseEntity<TaskDto> editTask(@RequestBody TaskDto taskDto, @PathVariable Long taskId)
      throws IOException {
    Task task = taskRepository.findById(taskId).get();
    task.setName(taskDto.getName());
    task.setDescription(taskDto.getDescription());
    task.setImage(taskDto.getFile().getBytes());
    return ResponseEntity.ok(taskRepository.save(task).toDto());
  }

  @GetMapping("/{taskId}")
  public ResponseEntity<TaskDto> one(@PathVariable Long taskId) {
    return ResponseEntity.ok(taskRepository.findById(taskId).get().toDto());
  }
}
