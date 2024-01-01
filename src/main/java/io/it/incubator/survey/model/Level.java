package io.it.incubator.survey.model;

import io.it.incubator.survey.dto.LevelDto;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

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