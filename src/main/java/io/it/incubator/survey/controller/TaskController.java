package io.it.incubator.survey.controller;

import io.it.incubator.survey.dto.TaskDto;
import io.it.incubator.survey.model.Task;
import io.it.incubator.survey.repo.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.util.List;

@RestController
@RequestMapping("/api/v1/")
public class TaskController {

    @Autowired
    private TaskRepository taskRepository;

    @GetMapping("/tasks")
    public List<TaskDto> getAllTasks() {
        return taskRepository.findAll().stream()
                .map(t -> t.toDto()).toList();
    }

    @PostMapping("/tasks")
    TaskDto newTask(@RequestBody TaskDto newTask) {
        Task task = taskRepository.save(newTask.toEntity());
        return task.toDto();
    }

    @GetMapping("/tasks/{id}")
    TaskDto one(@PathVariable Long id) {
        return taskRepository.findById(id).get().toDto();
    }
}
