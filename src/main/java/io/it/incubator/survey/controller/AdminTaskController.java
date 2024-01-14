package io.it.incubator.survey.controller;

import io.it.incubator.survey.dto.AnswerDto;
import io.it.incubator.survey.dto.TaskDto;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Controller;
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

    @GetMapping(value = "/task/new")
    public ModelAndView newTask() {
        ModelAndView mv = new ModelAndView();
        mv.getModel().put("task", TaskDto.builder().answers(List.of(
                AnswerDto.builder().name("assss").text("qqqqq").build()
                )).name("eeeeee").build());
        mv.setViewName("newTask2");
        return mv;
    }

    @PostMapping("/task/save")
    public ModelAndView save(
            @ModelAttribute("task") TaskDto task,
            BindingResult result, ModelMap model) throws IOException {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("newTask");
        System.out.println("img=" + Base64.encodeBase64String(task.getFile().getBytes()));

        return mv;

    }
}
