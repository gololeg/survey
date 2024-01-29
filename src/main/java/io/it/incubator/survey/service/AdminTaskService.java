package io.it.incubator.survey.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.it.incubator.survey.dto.TaskDto;
import io.it.incubator.survey.model.Answer;
import io.it.incubator.survey.model.Task;
import io.it.incubator.survey.repo.LevelRepository;
import io.it.incubator.survey.repo.TaskRepository;
import io.it.incubator.survey.repo.TypeRepository;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
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
    ObjectMapper om = new ObjectMapper();
    Answer[] arr = om.readValue(taskDto.getStrAnswers(), Answer[].class);
    List<Answer> answers =
        Arrays.asList(om.readValue(taskDto.getStrAnswers(), Answer[].class));
    Task task = new Task(0L, taskDto.getDescription(), "new2",
        taskDto.getImage(),
        levelRepository.getReferenceById(taskDto.getLevel().getId()),
        typeRepository.getReferenceById(taskDto.getType().getId()),
        answers
    );

    for (Answer a : task.getAnswers()) {
      a.setTask(task);
    }
    return taskRepository.save(task).toDto();
  }
}
