package io.it.incubator.survey.model;

import io.it.incubator.survey.dto.LevelDto;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "levels")
public class Level {

    public Level() {
    }

    @Id
    private int id;

    @Column(name = "name")
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToMany(mappedBy = "level")
    private List<Task> tasks;

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public LevelDto toDto(){
        return LevelDto.builder()
                .id(getId())
                .name(getName())
                .build();
    }
}