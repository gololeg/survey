package io.it.incubator.survey.dto;

import io.it.incubator.survey.model.Task;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AnswerDto {
    private Long id;
    private String name;
    private String text;
    private String value;
    private boolean isRight;

}
