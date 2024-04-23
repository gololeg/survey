package io.it.incubator.survey.dto;

import io.it.incubator.survey.model.Answer;
import io.it.incubator.survey.model.Task;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.postgresql.shaded.com.ongres.scram.common.bouncycastle.base64.Base64;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaskDto {

  private long id;
  private String name;
  private byte[] image;
  private LevelDto level;
  private TypeDto type;
  private List<AnswerDto> answers;

  private String description;

  private boolean isActive;


  public Task toEntity(Task task) {
    task.setId(this.id);
    task.setDescription(this.description);
    task.setActive(this.isActive);
    task.setName(this.name);
    task.setImage(this.image);
    if (task.getId() == 0L) {
      task.setAnswers(this.getAnswers().stream().map(a -> {
        var answer = a.toEntity(new Answer());
        answer.setTask(task);
        return answer;
      }).toList());
    } else {
      task.getAnswers().forEach(
          a -> {
            var answerDtoOptional = getAnswers().stream()
                .filter(ans -> a.getId().equals(ans.getId())).findFirst();
            if (answerDtoOptional.isPresent()) {
              answerDtoOptional.get()
                  .toEntity(a);
            }
          });
    }
    return task;
  }

}
