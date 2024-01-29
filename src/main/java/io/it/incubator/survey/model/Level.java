package io.it.incubator.survey.model;

import io.it.incubator.survey.dto.LevelDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "levels")
@Data
@NoArgsConstructor
public class Level {

  public Level(int id, String name) {
    this.id = id;
    this.name = name;
  }

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