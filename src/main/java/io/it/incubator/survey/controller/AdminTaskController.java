package io.it.incubator.survey.controller;

import io.it.incubator.survey.dto.TaskDto;
import io.it.incubator.survey.model.Task;
import io.it.incubator.survey.repo.TaskRepository;
import io.it.incubator.survey.service.AdminTaskService;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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
    model.addAttribute("task", TaskDto.builder().build());

    mv.setViewName("newTask");
    return mv;
  }

  @GetMapping(value = "/task/view/{taskId}")
  public ModelAndView viewTask(Model model, @PathVariable Long taskId) {
    ModelAndView mv = new ModelAndView();
    mv.setViewName("viewTask");
    mv.getModel().put("task", taskRepository.findById(taskId).get().toDto());
    return mv;
  }

  @GetMapping(value = "/task/all")
  public ModelAndView allTasks(Model model) {
    ModelAndView mv = new ModelAndView();
    mv.getModel().put("tasks",
        taskRepository.findByOrderByCreateDateDesc().stream().map(t -> t.toCommonDto()).toList());
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
  public ModelAndView editTass(@PathVariable Long taskId, @ModelAttribute("task") TaskDto taskDto)
      throws IOException {
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
    mv.setViewName("allTasks");
    task.setImage(task.getFile().getBytes());
    adminTaskService.save(task);
    mv.getModel().put("tasks",
        taskRepository.findByOrderByCreateDateDesc().stream().map(t -> t.toCommonDto()).toList());
    return mv;

  }
}
