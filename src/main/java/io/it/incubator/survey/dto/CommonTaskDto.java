package io.it.incubator.survey.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommonTaskDto {

    private long id;
    private String name;
    private String description;
}
