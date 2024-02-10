package io.it.incubator.survey.filter;

import io.it.incubator.survey.dto.ResponseError;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler
    extends ResponseEntityExceptionHandler {

  @ExceptionHandler(value
      = {Exception.class})
  protected ResponseEntity<Object> handleConflict(
      RuntimeException ex, WebRequest request) {
    String bodyOfResponse = "This should be application specific";
    return handleExceptionInternal(ex, ResponseError.builder()
            .message(ex.getMessage())
            .localMessage(ex.getLocalizedMessage())
            .stackTrace(ExceptionUtils.getStackTrace(ex))
            .build(),
        new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
  }
}
