package io.it.incubator.survey.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LevelDto {
    private int id;
    private String name;
}
