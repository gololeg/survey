package io.it.incubator.survey.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "settings")
@Data
public class Setting {
    @Id
    private String name;

    @Column(name = "low_level_task_count")
    private int lowLevelTaskCount;
    @Column(name = "middle_level_task_count")
    private int middleLevelTaskCount;
    @Column(name = "high_level_task_count")
    private int highLevelTaskCount;
    @Column(name = "low_level_task_time")
    private int lowLevelTaskTime;
    @Column(name = "middle_level_task_time")
    private int middleLevelTaskTime;
    @Column(name = "high_level_task_time")
    private int highLevelTaskTime;

}
