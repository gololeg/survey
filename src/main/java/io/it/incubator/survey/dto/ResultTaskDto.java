package io.it.incubator.survey.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResultTaskDto {
    private TaskDto task;
    private List<AnswerDto> clientAnswers;
}
