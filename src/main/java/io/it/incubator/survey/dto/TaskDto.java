package io.it.incubator.survey.dto;

import io.it.incubator.survey.model.Answer;
import io.it.incubator.survey.model.Level;
import io.it.incubator.survey.model.Task;
import io.it.incubator.survey.model.Type;
import lombok.Builder;
import lombok.Data;
import org.postgresql.shaded.com.ongres.scram.common.bouncycastle.base64.Base64;

import java.nio.charset.StandardCharsets;
import java.util.List;

@Data
@Builder
public class TaskDto {

    private long id;
    private String name;
    private byte[] image;
    private String imageStr;
    private LevelDto level;
    private TypeDto type;
    private List<AnswerDto> answers;

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

}
