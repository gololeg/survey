package io.it.incubator.survey.dto;

import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SurveySettingDto {

  private List<Long> taskIds;
  private String surveyId;
  private String expiredDate;

}
