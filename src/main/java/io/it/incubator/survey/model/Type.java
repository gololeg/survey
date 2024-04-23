package io.it.incubator.survey.model;

import io.it.incubator.survey.dto.TypeDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "types")
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Type {

  @Id
  private int id;

  @Column(name = "name")
  private String name;

  public TypeDto toDto() {
    return TypeDto.builder()
        .id(getId())
        .name(getName())
        .build();
  }
}