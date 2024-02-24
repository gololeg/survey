package io.it.incubator.survey.controller.rest;

import io.it.incubator.survey.model.Access;
import io.it.incubator.survey.model.Setting;
import io.it.incubator.survey.repo.AccessRepository;
import io.it.incubator.survey.repo.SettingRepository;
import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/")
public class SettingController {

  @Autowired
  private SettingRepository settingRepository;
  @Autowired
  private AccessRepository accessRepository;

  @GetMapping("/settings")
  public Setting get() {
    return settingRepository.findAll().isEmpty() ?
        new Setting("GLOBAL") : settingRepository.findAll().get(0);
  }

  @PostMapping("/settings")
  public Setting save(@RequestBody Setting setting) throws IOException {
    setting.setName("GLOBAL");
    return settingRepository.save(setting);
  }

  @GetMapping("/settings/accesses/{email}")
  public Access getAccess(@PathVariable String email) {
    return accessRepository.findById(email).get();
  }

  @PostMapping("/settings/accesses")
  public Access saveAccess(@RequestBody Access access) {
    return accessRepository.save(access);
  }

  @PutMapping("/settings/accesses/{email}")
  public Access editAccess(@PathVariable String email, @RequestBody int attemptsCount) {
    Access access = accessRepository.findById(email).get();
    access.setAttemptsCount(attemptsCount);
    return accessRepository.save(access);
  }

  @GetMapping("/settings/accesses")
  public List<Access> getAllAccess() {
    return accessRepository.findAll();
  }
}
