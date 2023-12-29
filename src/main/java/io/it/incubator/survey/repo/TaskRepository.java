package io.it.incubator.survey.repo;

import io.it.incubator.survey.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TaskRepository extends JpaRepository<Task, Long> {

}
