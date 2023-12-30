package io.it.incubator.survey.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
}
