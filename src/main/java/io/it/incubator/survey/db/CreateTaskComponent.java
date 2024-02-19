package io.it.incubator.survey.db;

import io.it.incubator.survey.repo.TaskRepository;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
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
    Resource classPathResource = resourceLoader.getResource("classpath:/static/");
    byte[] binaryData = FileCopyUtils.copyToByteArray(classPathResource.getInputStream());
    File f = File.createTempFile("prefix-", "-suffix");
    FileUtils.copyInputStreamToFile(classPathResource.getInputStream(), f);
    getResources("classpath:/static/", resourceLoader).forEach(n -> System.out.println(n.getName()));
    System.out.println(getResources("classpath:/tasks/", resourceLoader).size());
//    Arrays.stream(new File(new String(binaryData)).listFiles()).toList().forEach(
//        f -> System.out.println(f.getName())
//    );
    System.out.println("size=" + taskRepository.findAll().size());
    return true;
  }

  public static List<File> getResources(final String path, ResourceLoader loader) throws IOException {
    try (
        final InputStream is = loader.getResource(path).getInputStream();
        final InputStreamReader isr = new InputStreamReader(is, StandardCharsets.UTF_8);
        final BufferedReader br = new BufferedReader(isr)) {
      return br.lines()
          .map(l -> path + "/" + l)
          .map(r -> {
            try {
              return loader.getResource(r).getFile();
            } catch (IOException e) {
              throw new RuntimeException(e);
            }
          })
          .toList();
    }
  }
}
