package io.it.incubator.survey.controller;

import io.it.incubator.survey.dto.TaskDto;
import io.it.incubator.survey.repo.TaskRepository;
import io.it.incubator.survey.service.ClientAnswerService;
import io.it.incubator.survey.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/survey")
public class SurveyTaskController {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private TaskService taskService;

@Autowired
private ClientAnswerService clientAnswerService;

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

    @GetMapping(value = "/{surveyId}/result")
    public ModelAndView result(@PathVariable String surveyId) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("result");
        return mv;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/{surveyId}")
    public ModelAndView submit(@PathVariable String surveyId, @ModelAttribute("data") TaskDto data,
                               BindingResult result, ModelMap model) {
        clientAnswerService.saveAnswers(data.getArs(), surveyId, data.getId());
        if(data.getNextTaskId() == 0){
            return new ModelAndView("redirect:/survey/" + surveyId + "/result");
        }
        ModelAndView mv = new ModelAndView();
        mv.setViewName("welcome");
        mv.getModel().put("data", taskRepository.findById(data.getNextTaskId()).get().toDto());
        mv.getModel().put("surveyId", surveyId);

        return mv;

    }
}