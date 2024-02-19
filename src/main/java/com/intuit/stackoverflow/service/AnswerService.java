package com.intuit.stackoverflow.service;

import com.intuit.stackoverflow.model.Answer;
import com.intuit.stackoverflow.requestHandler.CreateAnswerRequest;

import java.util.List;

public interface AnswerService {
    List<Answer> getAllAnswersForQuestion(Integer questionId);
    Answer getAnswerById(Integer answerId);
    Answer createAnswer(CreateAnswerRequest request);
    Answer upvoteAnswer(Integer id);

}
