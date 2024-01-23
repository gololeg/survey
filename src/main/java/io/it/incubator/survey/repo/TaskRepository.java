package io.it.incubator.survey.repo;

import io.it.incubator.survey.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.print.Pageable;
import java.util.List;


public interface TaskRepository extends JpaRepository<Task, Long> {

List<Task> findByOrderByName();

}
