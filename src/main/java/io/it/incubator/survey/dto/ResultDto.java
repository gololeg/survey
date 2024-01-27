package io.it.incubator.survey.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResultDto {
    private List<ResultTaskDto> results;
    private float lowLevelPercent;
    private float middleLevelPercent;
    private float highLevelPercent;
    private float commonPercent;
}
