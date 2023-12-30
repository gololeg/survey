package io.it.incubator.survey.model;

import io.it.incubator.survey.dto.TypeDto;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "types")
public class Type {

    public Type() {
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

    @OneToMany(mappedBy = "type")
    private List<Task> tasks;

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public TypeDto toDto() {
        return TypeDto.builder()
                .id(getId())
                .name(getName())
                .build();
    }
}