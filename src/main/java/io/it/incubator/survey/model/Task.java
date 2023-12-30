package io.it.incubator.survey.model;

import io.it.incubator.survey.dto.LevelDto;
import io.it.incubator.survey.dto.TaskDto;
import io.it.incubator.survey.dto.TypeDto;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "tasks")
public class Task {
    public Task() {
    }

    public Task(String name, byte[] image, Level level, Type type, List answers) {
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

    @ManyToOne
    @JoinColumn(name = "level_id")
    private Level level;

    @ManyToOne
    @JoinColumn(name = "type_id")
    private Type type;

    @OneToMany(mappedBy = "task", cascade = {CascadeType.PERSIST})
    private List<Answer> answers;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    public TaskDto toDto() {
        return TaskDto.builder()
                .id(getId())
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
}
