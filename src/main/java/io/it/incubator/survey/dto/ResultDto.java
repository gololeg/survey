package io.it.incubator.survey.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
