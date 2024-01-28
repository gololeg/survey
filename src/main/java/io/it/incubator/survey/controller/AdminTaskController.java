package io.it.incubator.survey.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.it.incubator.survey.dto.AnswerDto;
import io.it.incubator.survey.dto.TaskDto;
import io.it.incubator.survey.model.Task;
import io.it.incubator.survey.repo.TaskRepository;
import io.it.incubator.survey.service.AdminTaskService;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping(value = "/admin")
public class AdminTaskController {

    @Autowired
    private AdminTaskService adminTaskService;

    @Autowired
    private TaskRepository taskRepository;

    @GetMapping(value = "/task/new")
    public ModelAndView newTask(Model model) {
        ModelAndView mv = new ModelAndView();
//        mv.getModel().put("task", TaskDto.builder().answers(List.of(
//                AnswerDto.builder().name("assss").text("qqqqq").build()
//                )).name("eeeeee").build());
        model.addAttribute("task", TaskDto.builder().answers(List.of(
                AnswerDto.builder().name("assss").text("qqqqq").build()
        )).name("eeeeee").build());

        mv.setViewName("newTask");
        return mv;
    }

    @GetMapping(value = "/task/all")
    public ModelAndView allTasks(Model model) {
        ModelAndView mv = new ModelAndView();
        mv.getModel().put("tasks", taskRepository.findByOrderByName().stream().map(t->t.toCommonDto()).toList());
        mv.setViewName("allTasks");
        return mv;
    }
    @GetMapping(value = "/task/edit/{taskId}")
    public ModelAndView editTasks(@PathVariable Long taskId) {
        ModelAndView mv = new ModelAndView();
        mv.getModel().put("task", taskRepository.findById(taskId).get().toDto());
        mv.setViewName("editTask");
        return mv;
    }

    @PostMapping(value = "/task/edit/{taskId}")
    public ModelAndView editTass(@PathVariable Long taskId, @ModelAttribute("task") TaskDto taskDto) throws IOException {
        ModelAndView mv = new ModelAndView();
        Task task = taskRepository.findById(taskId).get();
        task.setName(taskDto.getName());
        task.setDescription(taskDto.getDescription());
        task.setImage(taskDto.getFile().getBytes());
        taskRepository.save(task);
        mv.getModel().put("task", taskRepository.findById(taskId).get().toDto());
        mv.setViewName("editTask");
        return mv;
    }

    @PostMapping("/task/save")
    public ModelAndView save(
            @ModelAttribute("task") TaskDto task,
            BindingResult result, ModelMap model) throws IOException {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("newTask");
        task.setImage(task.getFile().getBytes());
        adminTaskService.save(task);
        return mv;

    }
}
