package io.it.incubator.survey.db;

import io.it.incubator.survey.repo.TaskRepository;
import java.io.IOException;
import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

@Component
public class CreateTaskComponent {

  @Autowired
  private ResourceLoader resourceLoader;

  @Autowired
  private TaskRepository taskRepository;
  @Bean
  public void execute() throws IOException {
    Resource classPathResource = resourceLoader.getResource("classpath:/tasks");
    Arrays.stream(classPathResource.getFile().listFiles()).toList().forEach(
        f -> System.out.println(f.getName())
    );
    System.out.println("size="+taskRepository.findAll().size());
  }


}
