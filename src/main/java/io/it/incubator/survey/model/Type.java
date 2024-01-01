package io.it.incubator.survey.model;

import io.it.incubator.survey.dto.TypeDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "types")
@Data
@NoArgsConstructor
public class Type {
    public Type(int id, String name) {
        this.id = id;
        this.name = name;
    }

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