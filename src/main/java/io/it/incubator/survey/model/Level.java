package io.it.incubator.survey.model;

import io.it.incubator.survey.dto.LevelDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "levels")
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Level {

  @Id
  private int id;

  @Column(name = "name")
  private String name;

  @OneToMany(mappedBy = "level")
  private List<Task> tasks;

  public LevelDto toDto() {
    return LevelDto.builder()
        .id(getId())
        .name(getName())
        .build();
  }

}