package io.it.incubator.survey.controller;

import io.it.incubator.survey.dto.TaskDto;
import io.it.incubator.survey.repo.TaskRepository;
import io.it.incubator.survey.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;
import java.util.UUID;

@Controller
@RequestMapping(value = "/survey")
public class TestController {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private TaskService taskService;
    ;

    @GetMapping
    public ModelAndView getTestData() {
        ModelAndView mv = new ModelAndView();
        var settings = taskService.getSetting();
        mv.getModel().put("data", taskRepository.findById(settings.getTaskIds().get(0)).get().toDto());
        mv.getModel().put("surveyId", settings.getSurveyId());
        mv.getModel().put("settings", settings);
        mv.setViewName("welcome");
        return mv;
    }

    @GetMapping(value = "/result")
    public ModelAndView result() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("result");
        return mv;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/{surveyId}")
    public ModelAndView submit(@PathVariable String surveyId, @ModelAttribute("data") TaskDto data,
                               BindingResult result, ModelMap model) {
//        if (result.hasErrors()) {
//            return "error";
//        }
//        model.addAttribute("name", employee.getName());
//        model.addAttribute("contactNumber", employee.getContactNumber());
//        model.addAttribute("id", employee.getId());
        ModelAndView mv = new ModelAndView();
        mv.setViewName("welcome");
        mv.getModel().put("data", taskRepository.findById(data.getId()).get().toDto());
        mv.getModel().put("surveyId", surveyId);

        return mv;
    }
}
