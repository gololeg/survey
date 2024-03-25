package io.it.incubator.survey.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "client_sessions")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientSession {

  @Id
  @Column(name = "survey_id")
  private String surveyId;

  @Column(name = "expired_date")
  private String expiredDate;

  @Column(name = "task_ids")
  private String taskIds;

  @Column(name = "email")
  private String email;

  @Column(name = "create_date")
  private LocalDateTime createDate;
}
