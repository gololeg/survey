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
@Table(name = "sessions")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Session {

  @Id
  @Column(name = "session_id")
  private String sessionId;

  @Column(name = "login")
  private String login;

  @Column(name = "last_active_date")
  private LocalDateTime lastActiveDate;

}
