package io.it.incubator.survey.model;

import io.it.incubator.survey.dto.AnswerDto;
import io.it.incubator.survey.dto.LevelDto;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "answers")
public class Answer {

    public Answer() {
    }

    public Answer(String name, String text, String value, boolean isRight) {
        this.name = name;
        this.text = text;
        this.value = value;
        this.isRight = isRight;

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "text")
    private String text;

    @Column(name = "value")
    private String value;

    @Column(name = "is_right")
    private boolean isRight;

    @ManyToOne
    @JoinColumn(name = "task_id")
    private Task task;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public boolean isRight() {
        return isRight;
    }

    public void setRight(boolean right) {
        isRight = right;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public AnswerDto toDto(){
        return AnswerDto.builder()
                .id(getId())
                .name(getName())
                .value(getValue())
                .text(getText())
                .isRight(isRight())
                .build();
    }
}
