package io.it.incubator.survey.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "client_answers")
@Data
@NoArgsConstructor
public class ClientAnswer {

  public ClientAnswer(String surveyId, long taskId, long answerId) {
    this.surveyId = surveyId;
    this.taskId = taskId;
    this.answerId = answerId;
  }

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Column(name = "survey_id")
  private String surveyId;

  @Column(name = "task_id")
  private long taskId;

  @Column(name = "answer_id")
  private long answerId;

}
