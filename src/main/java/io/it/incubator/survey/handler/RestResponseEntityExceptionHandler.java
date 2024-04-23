package io.it.incubator.survey.handler;

import io.it.incubator.survey.dto.ResponseError;
import io.it.incubator.survey.exception.AuthException;
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
      = {RuntimeException.class})
  public ResponseEntity<Object> handleConflict(
      RuntimeException ex, WebRequest request) {
    ex.printStackTrace();
    String bodyOfResponse = "This should be application specific";
    return handleExceptionInternal(ex, new ResponseError(ex),
        new HttpHeaders(),
        ex instanceof AuthException ? HttpStatus.UNAUTHORIZED : HttpStatus.INTERNAL_SERVER_ERROR,
        request);
  }
}
