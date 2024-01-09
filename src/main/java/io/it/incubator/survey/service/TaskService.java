package io.it.incubator.survey.service;

import io.it.incubator.survey.repo.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;

    public List<Long> getCurrentTaskIds() {
    return taskRepository.findAll().stream().map(t -> t.getId()).toList();
    }
}
