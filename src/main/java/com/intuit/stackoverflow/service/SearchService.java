package com.intuit.stackoverflow.service;

import com.intuit.stackoverflow.model.Answer;
import com.intuit.stackoverflow.model.Question;
import com.intuit.stackoverflow.model.Tag;

import java.util.List;


public interface SearchService {

    List<Tag> searchTags(String query);

    List<Question> searchQuestions(String query);

    List<Answer> searchAnswers(String query);
}
