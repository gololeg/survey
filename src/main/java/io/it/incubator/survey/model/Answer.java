package io.it.incubator.survey.model;

import io.it.incubator.survey.dto.AnswerDto;
import io.it.incubator.survey.dto.TaskDto;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "answers")
@Data
@NoArgsConstructor
public class Answer {

    public Answer(String name, String text, String value, boolean isRight, Task task) {
        this.name = name;
        this.text = text;
        this.value = value;
        this.isRight = isRight;
        this.task = task;

    }

    public Answer(String text, boolean isRight, Task task) {
        this.text = text;
        this.isRight = isRight;
        this.task = task;
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
    private Boolean isRight;

    @ManyToOne
    @JoinColumn(name = "task_id")
    private Task task;

    public AnswerDto toDto() {
        return AnswerDto.builder()
                .id(getId())
                .name(getName())
                .value(getValue())
                .text(getText())
                .isRight(getIsRight())
                .build();
    }
}
