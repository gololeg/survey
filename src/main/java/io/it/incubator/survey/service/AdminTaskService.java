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
    Task task = new Task(0L, taskDto.getDescription(), taskDto.getName(), taskDto.getImage(),
        levelRepository.getReferenceById(taskDto.getLevel().getId()),
        typeRepository.getReferenceById(taskDto.getType().getId()), taskDto.getAnswers().stream()
        .map(a -> new Answer(a.getName(), a.getText(), a.getValue(), a.isRight(), null)).toList()
    );

    for (Answer a : task.getAnswers()) {
      a.setTask(task);
    }
    task.setCreateDate(LocalDateTime.now());
    return taskRepository.save(task).toDto();
  }
}
