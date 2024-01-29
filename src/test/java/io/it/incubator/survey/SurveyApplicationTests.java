package io.it.incubator.survey;

import io.it.incubator.survey.model.Answer;
import io.it.incubator.survey.model.ClientAnswer;
import io.it.incubator.survey.model.Task;
import io.it.incubator.survey.repo.AnswerRepository;
import io.it.incubator.survey.repo.LevelRepository;
import io.it.incubator.survey.repo.TaskRepository;
import io.it.incubator.survey.repo.TypeRepository;
import io.it.incubator.survey.service.TaskService;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.io.FileUtils;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SurveyApplicationTests {

  @Autowired
  TaskRepository taskRepository;

  @Autowired
  LevelRepository levelRepository;

  @Autowired
  TypeRepository typeRepository;

  @Autowired
  AnswerRepository answerRepository;


  @Autowired
  TaskService taskService;

  @Test
  void contextLoads() {
  }

  @Test
  void testRandomList() {
    taskService.getCurrentTaskIds(6, Lists.newArrayList(1L, 2L, 3L));
  }

  @Test
  void testToMap() {
    List<ClientAnswer> list = List.of(new ClientAnswer("", 3, 1)
        , new ClientAnswer("", 1, 1)
        , new ClientAnswer("", 1, 1));
    Map<Long, List<Long>> map = new HashMap<>();
    for (ClientAnswer ca : list) {
      if (map.get(ca.getTaskId()) == null) {
        map.put(ca.getTaskId(), List.of(ca.getAnswerId()));
      } else {
        List<Long> answerIds = new ArrayList<>(map.get(ca.getTaskId()));
        answerIds.add(ca.getAnswerId());
        map.put(ca.getTaskId(), answerIds);
      }

    }
    System.out.println("ddd=" + map);
  }


  //    @Test
  void testDB() throws Exception {

    Answer a1 = new Answer("name1", "text", "value", true, null);
    Answer a2 = new Answer("name2", "text", "value", true, null);
    byte[] b = FileUtils.readFileToByteArray(new File("d://1.jpg"));
    System.out.println("jjjjj=" +
        answerRepository.findById(105L).get().getText());
    Task task = new Task(0L, "Task Task Task Task Task ", "new2",
        FileUtils.readFileToByteArray(new File("d://1.jpg")),
        levelRepository.getReferenceById(1),
        typeRepository.getReferenceById(1),
        List.of(a1, a2));
    a1.setTask(task);
    a2.setTask(task);
//        taskRepository.save(task);

    taskRepository.count();

  }

}
