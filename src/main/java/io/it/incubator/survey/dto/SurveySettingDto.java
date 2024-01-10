package io.it.incubator.survey.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
public class SurveySettingDto {
    private List<Long> taskIds;
    private String surveyId;
    private String expiredDate;

}
