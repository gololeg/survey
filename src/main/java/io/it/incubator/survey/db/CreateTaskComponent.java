package io.it.incubator.survey.db;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.it.incubator.survey.model.Level;
import io.it.incubator.survey.model.Task;
import io.it.incubator.survey.repo.TaskRepository;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;

@Component
public class CreateTaskComponent {

  @Autowired
  private ResourceLoader resourceLoader;

  @Autowired
  private TaskRepository taskRepository;

  @Bean
  public boolean execute() throws IOException {
    Resource classPathResource = resourceLoader.getResource("classpath:/tasks/tasksForLoad.json");
    File f = File.createTempFile("prefix-", "-suffix");
    FileUtils.copyInputStreamToFile(classPathResource.getInputStream(), f);
    ObjectMapper om = new ObjectMapper();
    Task[] tasks = om.readValue(f, Task[].class);
    List<String> existingTaskNames = taskRepository.findAll().stream().map(t -> t.getName())
        .toList();
    for (Task t : tasks) {
      if (!existingTaskNames.contains(t.getName())) {
        try {
          classPathResource = resourceLoader.getResource("classpath:/tasks/" + t.getName());
          byte[] binaryData = FileCopyUtils.copyToByteArray(classPathResource.getInputStream());
          t.setImage(binaryData);
        }catch (Exception ex){

        }
        t.getAnswers().forEach(a -> a.setTask(t));
        t.setCreateDate(LocalDateTime.now());
        taskRepository.save(t);
      }
    }
    return true;
  }

}
