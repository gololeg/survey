package io.it.incubator.survey.dto;

import io.it.incubator.survey.model.Answer;
import io.it.incubator.survey.model.Level;
import io.it.incubator.survey.model.Task;
import io.it.incubator.survey.model.Type;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class TaskDto {

    private long id;
    private String name;
    private byte[] image;
    private LevelDto level;
    private TypeDto type;
    private List<AnswerDto> answers;

    public Task toEntity() {
        Task task = new Task(getName(),
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
