package io.it.incubator.survey;

import io.it.incubator.survey.model.Task;
import io.it.incubator.survey.repo.LevelRepository;
import io.it.incubator.survey.repo.TaskRepository;
import io.it.incubator.survey.repo.TypeRepository;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;

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
            taskRepository.save(new Task("new2",
                    FileUtils.readFileToByteArray(new File("d://1.jpg")),
                    levelRepository.getReferenceById(1), typeRepository.getReferenceById(1)));
        } catch (Exception e) {
            e.printStackTrace();
        }
        taskRepository.count();

    }

}
