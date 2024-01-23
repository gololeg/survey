package io.it.incubator.survey.controller.rest;

import io.it.incubator.survey.dto.TaskDto;
import io.it.incubator.survey.model.Setting;
import io.it.incubator.survey.repo.SettingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@CrossOrigin(origins = "http://localhost:9000", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/")
public class SettingController {

    @Autowired
    private SettingRepository settingRepository;

    @GetMapping("/setting")
    public Setting get() {
        return settingRepository.findAll().isEmpty() ?
                new Setting("GLOBAL") : settingRepository.findAll().get(0);
    }

    @PostMapping("/setting")
    public Setting save(@RequestBody Setting setting) throws IOException {
        setting.setName("GLOBAL");
        return settingRepository.save(setting);
    }

}
