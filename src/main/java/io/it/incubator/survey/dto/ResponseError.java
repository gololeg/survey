package io.it.incubator.survey.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.exception.ExceptionUtils;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResponseError {

  public ResponseError(Exception ex) {
    this.message = ex.getMessage();
    this.localMessage = ex.getLocalizedMessage();
//    this.cause = ex.getCause().getMessage();
    this.stackTrace = ExceptionUtils.getStackTrace(ex);
  }

  private String message;
  private String localMessage;
  private String cause;
  private String stackTrace;

}
