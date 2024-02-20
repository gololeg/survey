package io.it.incubator.survey.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "accesses")
@Data
@NoArgsConstructor
public class Access {

  @Id
  private String email;

  @Column(name = "attempts_count")
  private int attemptsCount;

}
