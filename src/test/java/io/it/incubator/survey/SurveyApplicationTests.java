package io.it.incubator.survey;

import io.it.incubator.survey.model.Answer;
import io.it.incubator.survey.model.Task;
import io.it.incubator.survey.repo.LevelRepository;
import io.it.incubator.survey.repo.TaskRepository;
import io.it.incubator.survey.repo.TypeRepository;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.util.List;

@SpringBootTest
class SurveyApplicationTests {
    @Autowired
    TaskRepository taskRepository;

    @Autowired
    LevelRepository levelRepository;

    @Autowired
    TypeRepository typeRepository;


    @Test
    void contextLoads() {
    }

    @Test
    void testDB() {
        try {
            Answer a1 = new Answer("name1", "text", "value", true);
            Answer a2 = new Answer("name2", "text", "value", true);
            Task task = new Task("new2",
                    FileUtils.readFileToByteArray(new File("d://1.jpg")),
                    levelRepository.getReferenceById(1),
                    typeRepository.getReferenceById(1),
                    List.of(a1, a2));
            a1.setTask(task);
            a2.setTask(task);
            taskRepository.save(task);

        } catch (Exception e) {
            e.printStackTrace();
        }
        taskRepository.count();

    }

}
