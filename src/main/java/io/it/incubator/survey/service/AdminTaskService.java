package io.it.incubator.survey.service;

import io.it.incubator.survey.dto.TaskDto;
import io.it.incubator.survey.model.Answer;
import io.it.incubator.survey.model.Task;
import io.it.incubator.survey.repo.LevelRepository;
import io.it.incubator.survey.repo.TaskRepository;
import io.it.incubator.survey.repo.TypeRepository;
import java.io.IOException;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminTaskService {

  @Autowired
  private TaskRepository taskRepository;

  @Autowired
  private LevelRepository levelRepository;

  @Autowired
  private TypeRepository typeRepository;

  public TaskDto save(TaskDto taskDto) throws IOException {
    Task task;
    if (taskDto.getId() == 0L) {
      task = taskDto.toEntity(new Task());
      task.setCreateDate(LocalDateTime.now());
    } else {
      task = taskDto.toEntity(taskRepository.findById(taskDto.getId()).get());
    }
  task.setType(typeRepository.getReferenceById(taskDto.getType().getId()));
    task.setLevel(levelRepository.getReferenceById(taskDto.getLevel().getId()));
    return taskRepository.save(task).toDto();
  }
}
