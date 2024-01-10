package io.it.incubator.survey.service;

import io.it.incubator.survey.dto.SurveySettingDto;
import io.it.incubator.survey.repo.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;

    private List<Long> getCurrentTaskIds() {
        return taskRepository.findAll().stream().map(t -> t.getId()).toList();
    }

    private String getExpiredDate() {
        Calendar calendar = Calendar.getInstance(); // gets a calendar using the default time zone and locale.
        calendar.add(Calendar.SECOND, 200);
        SimpleDateFormat f = new SimpleDateFormat("MM.DD.yyyy HH:mm:ss");
        return f.format(calendar.getTime());
    }

    public SurveySettingDto getSetting() {
        return SurveySettingDto.builder()
                .taskIds(getCurrentTaskIds())
                .surveyId(UUID.randomUUID().toString().replaceAll("-", ""))
                .expiredDate(getExpiredDate())
                .build();
    }
}
