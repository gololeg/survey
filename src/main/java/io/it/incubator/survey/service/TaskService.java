package io.it.incubator.survey.service;

import io.it.incubator.survey.dto.SurveySettingDto;
import io.it.incubator.survey.model.ClientSession;
import io.it.incubator.survey.repo.ClientSessionRepository;
import io.it.incubator.survey.repo.SettingRepository;
import io.it.incubator.survey.repo.TaskRepository;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskService {

  @Autowired
  private TaskRepository taskRepository;

  @Autowired
  private ClientSessionRepository clientSessionRepository;
  @Autowired
  private SettingRepository settingRepository;

  public List<Long> getCurrentTaskIds(int count, List<Long> ids) {
    Random rand = new Random();
    List<Long> newIds = new ArrayList<>();
    for (int i = 0; i < count; i++) {
      if (ids.size() == 0) {
        break;
      }
      int randomIndex = rand.nextInt(ids.size());
      newIds.add(ids.get(randomIndex));
      ids.remove(randomIndex);
    }
    return newIds;

  }

  private String getExpiredDate(int seconds) {
    Calendar calendar = Calendar.getInstance(); // gets a calendar using the default time zone and locale.
    calendar.add(Calendar.SECOND, seconds);
    SimpleDateFormat f = new SimpleDateFormat("MM.DD.yyyy HH:mm:ss");
    return f.format(calendar.getTime());
  }

  public SurveySettingDto getSetting() {
    var setting = settingRepository.findById("GLOBAL").get();
    var allTasks = taskRepository.findAll();
    var taskLowIds = getCurrentTaskIds(setting.getLowLevelTaskCount(),
        new ArrayList<>(allTasks.stream()
            .filter(t -> t.getLevel().getName().equals("LOW"))
            .map(t -> t.getId()).toList()));
    var taskMiddleIds = getCurrentTaskIds(setting.getMiddleLevelTaskCount(),
        new ArrayList<>(allTasks.stream()
            .filter(t -> t.getLevel().getName().equals("MIDDLE"))
            .map(t -> t.getId()).toList()));
    var taskHighIds = getCurrentTaskIds(setting.getHighLevelTaskCount(),
        new ArrayList<>(allTasks.stream()
            .filter(t -> t.getLevel().getName().equals("HIGH"))
            .map(t -> t.getId()).toList()));
    List<Long> taskIds = Stream.of(taskLowIds, taskMiddleIds, taskHighIds)
        .flatMap(Collection::stream).collect(Collectors.toList());
    String expiredDate = getExpiredDate(setting.getSurveyDuration(
        taskLowIds.size()
        , taskMiddleIds.size()
        , taskHighIds.size()));
    String surveyId = UUID.randomUUID().toString().replaceAll("-", "");
    clientSessionRepository.save(new ClientSession(surveyId, expiredDate, taskIds.toString()));
    return SurveySettingDto.builder()
        .taskIds(taskIds)
        .surveyId(surveyId)
        .expiredDate(expiredDate)
        .build();
  }
}
