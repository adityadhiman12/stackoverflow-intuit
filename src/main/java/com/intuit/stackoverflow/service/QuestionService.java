package com.intuit.stackoverflow.service;

import com.intuit.stackoverflow.model.Question;
import com.intuit.stackoverflow.model.Tag;

import java.util.List;

public interface QuestionService {
    Question createQuestion(Question question);
    Question deleteQuestion(Integer id);
    Question updateQuestion(Integer id, Question question);
    Question upvoteQuestion(Integer id);
    List<Question> getTopQuestionsByUpvoteCount(Integer limit);
}
