package io.it.incubator.survey.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.it.incubator.survey.dto.ResultDto;
import io.it.incubator.survey.dto.ResultTaskDto;
import io.it.incubator.survey.model.ClientSession;
import io.it.incubator.survey.repo.AnswerRepository;
import io.it.incubator.survey.repo.ClientAnswerRepository;
import io.it.incubator.survey.repo.ClientSessionRepository;
import io.it.incubator.survey.repo.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ResultService {

@Autowired
private ClientSessionRepository clientSessionRepository;

@Autowired
private ClientAnswerRepository clientAnswerRepository;

@Autowired
private TaskRepository taskRepository;

@Autowired
private AnswerRepository answerRepository;

    public ResultDto getResult(String surveyId) throws JsonProcessingException {
        String taskIdsStr = clientSessionRepository.findById(surveyId).get().getTaskIds();
        ObjectMapper om = new ObjectMapper();
        List<ResultTaskDto> results = new ArrayList<>();
        for(Long taskId : om.readValue(taskIdsStr, Long[].class)){
            results.add(ResultTaskDto.builder()
                            .task(taskRepository.findById(taskId).get().toDto())
                            .clientAnswers(clientAnswerRepository.findBySurveyIdAndTaskId(surveyId, taskId)
                                    .stream().map(ca -> answerRepository.findById(ca.getAnswerId()).get().toDto()).toList())
                    .build());
        }
        return ResultDto.builder()
                .results(results)
                .build();

    }
}
