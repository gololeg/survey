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
    private Float lowLevelPercent;
    private Float middleLevelPercent;
    private Float highLevelPercent;
    private Float commonPercent;
}
