package io.it.incubator.survey.controller;

import io.it.incubator.survey.dto.TaskDto;
import io.it.incubator.survey.model.Task;
import io.it.incubator.survey.repo.TaskRepository;
import io.it.incubator.survey.service.AdminTaskService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:9000", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/")
public class TaskController {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private AdminTaskService adminTaskService;




    @GetMapping("/tasks")
    public List<TaskDto> getAllTasks() {
        return taskRepository.findAll().stream()
                .map(t -> t.toDto()).toList();
    }

    @GetMapping("/t")
    public ModelAndView getTestData() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("welcome");
        mv.getModel().put("data", "Welcome home man");

        return mv;
    }

    @PostMapping("/tasks")
    TaskDto newTask(@RequestBody TaskDto newTask) throws IOException {
        return adminTaskService.save(newTask);
    }

    @GetMapping("/tasks/{id}")
    TaskDto onhe(HttpServletRequest request, @PathVariable Long id) {
        Iterator<String> it = request.getHeaderNames().asIterator();
        while (it.hasNext()){
            System.out.println(it.next());
        }
        return taskRepository.findById(id).get().toDto();
    }
}
