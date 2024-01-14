package io.it.incubator.survey.dto;

import io.it.incubator.survey.model.Answer;
import io.it.incubator.survey.model.Level;
import io.it.incubator.survey.model.Task;
import io.it.incubator.survey.model.Type;
import lombok.Builder;
import lombok.Data;
import org.postgresql.shaded.com.ongres.scram.common.bouncycastle.base64.Base64;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class TaskDto {

    private long id;
    private long nextTaskId;
    private String name;
    private byte[] image;
    private MultipartFile file;
    private LevelDto level;
    private TypeDto type;
    private List<AnswerDto> answers;
    private List<Long> ars;
    private List<String> strAnswers;

    private String description;

    public String getImageStr() {
        return "data:image/jpg;base64," + Base64.toBase64String(getImage());
    }

    public Task toEntity() {
        Task task = new Task(
                getId(),
                getDescription(),
                getName(),
                getImage(),
                new Level(getLevel().getId(), getLevel().getName()),
                new Type(getType().getId(), getType().getName()),
                null);
        task.setAnswers(getAnswers().stream()
                .map(a -> new Answer(a.getName(), a.getText(), a.getValue(), a.isRight(), task))
                .toList());
        return task;
    }

    public List<AnswerDto> getAnswers() {
        return answers == null ? new ArrayList<AnswerDto>() : answers;
    }
}
