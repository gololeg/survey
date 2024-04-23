package io.it.incubator.survey.dto;

import io.it.incubator.survey.model.Answer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AnswerDto {

  private Long id;
  private String name;

  private String text;
  private String value;
  private boolean isRight;

  public Answer toEntity(Answer answer) {
    answer.setId(this.id);
    answer.setName(this.name);
    answer.setText(this.text);
    answer.setValue(this.value);
    answer.setIsRight(this.isRight());
    return answer;
  }
}
