package io.it.incubator.survey.controller;

import io.it.incubator.survey.model.Setting;
import io.it.incubator.survey.repo.SettingRepository;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/admin")
public class AdminSettingController {

  @Autowired
  private SettingRepository settingRepository;

  @GetMapping(value = "/setting")
  public ModelAndView get(Model model) {
    ModelAndView mv = new ModelAndView();
    mv.getModel().put("setting", settingRepository.findAll().isEmpty() ?
        new Setting("GLOBAL") : settingRepository.findAll().get(0));
    mv.setViewName("setting");
    return mv;
  }

  @PostMapping("/setting")
  public ModelAndView save(
      @ModelAttribute("setting") Setting setting,
      BindingResult result, ModelMap model) throws IOException {
    ModelAndView mv = new ModelAndView();
    mv.setViewName("setting");
    mv.getModel().put("setting", settingRepository.save(setting));
    return mv;

  }
}
