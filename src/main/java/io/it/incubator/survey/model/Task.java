package io.it.incubator.survey.model;

import io.it.incubator.survey.dto.LevelDto;
import io.it.incubator.survey.dto.TaskDto;
import io.it.incubator.survey.dto.TypeDto;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tasks")
@Data
@NoArgsConstructor
public class Task {

  public Task(Long id, String description, String name, byte[] image, Level level, Type type,
      List answers) {
    this.id = id;
    this.description = description;
    this.name = name;
    this.image = image;
    this.level = level;
    this.type = type;
    this.answers = answers;
  }

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Column(name = "name")
  private String name;

  @Column(name = "img")
  private byte[] image;
  @Column(name = "create_date")
  private LocalDateTime createDate;

  @Column(name = "is_active")
  private boolean isActive;

  @ManyToOne(cascade = {CascadeType.MERGE})
  @JoinColumn(name = "level_id")
  private Level level;

  @ManyToOne
  @JoinColumn(name = "type_id")
  private Type type;

  @OneToMany(mappedBy = "task", cascade = {CascadeType.PERSIST})
  private List<Answer> answers;

  @Column(name = "description")
  private String description;

  public TaskDto toDtoWithRightAnswers() {
    TaskDto taskDto = toDto();
    taskDto.setAnswers(getAnswers().stream().map(a -> a.toDtoWithRightAnswer()).toList());
    return taskDto;
  }

  public TaskDto toDto() {
    return TaskDto.builder()
        .id(getId())
        .isActive(isActive())
        .description(getDescription())
        .name(getName())
        .image(getImage())
        .level(LevelDto.builder()
            .id(getLevel().getId())
            .name(getLevel().getName())
            .build())
        .type(TypeDto.builder()
            .id(getType().getId())
            .name(getType().getName())
            .build())
        .answers(getAnswers().stream().map(a -> a.toDto()).toList())
        .build();
  }

  public TaskDto toCommonDto() {
    return TaskDto.builder()
        .id(getId())
        .description(getDescription())
        .name(getName())
        .level(LevelDto.builder()
            .id(getLevel().getId())
            .name(getLevel().getName())
            .build())
        .type(TypeDto.builder()
            .id(getType().getId())
            .name(getType().getName())
            .build())
        .image("".getBytes())
        .build();
  }
}
