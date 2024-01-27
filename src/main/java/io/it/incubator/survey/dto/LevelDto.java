package io.it.incubator.survey.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LevelDto {

    public static String LOW_LEVEL = "LOW";
    public static String MIDDLE_LEVEL = "MIDDLE";
    public static String HIGH_LEVEL = "HIGH";
    private int id;
    private String name;


}
