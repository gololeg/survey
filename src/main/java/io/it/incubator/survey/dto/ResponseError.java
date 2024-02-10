package io.it.incubator.survey.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResponseError {
  private String message;
  private String localMessage;
  private String cause;
  private String stackTrace;

}
