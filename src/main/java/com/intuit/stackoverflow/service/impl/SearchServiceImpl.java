package com.intuit.stackoverflow.service.impl;

import com.intuit.stackoverflow.model.Answer;
import com.intuit.stackoverflow.model.Question;
import com.intuit.stackoverflow.model.Tag;
import com.intuit.stackoverflow.repository.AnswerRepository;
import com.intuit.stackoverflow.repository.QuestionRepository;
import com.intuit.stackoverflow.repository.TagRepository;
import com.intuit.stackoverflow.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchServiceImpl implements SearchService {
    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private AnswerRepository answerRepository;

    @Override
    public List<Tag> searchTags(String query) {
        // Implement tag search logic using tagRepository
        return tagRepository.findByTagNameContainingIgnoreCase(query);
    }

    @Override
    public List<Question> searchQuestions(String query) {
        // Implement question search logic using questionRepository
        return questionRepository.findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase(query, query);
    }

    @Override
    public List<Answer> searchAnswers(String query) {
        // Implement answer search logic using answerRepository
        return answerRepository.findByAnswerContainingIgnoreCase(query);
    }
}
