package com.intuit.stackoverflow.service.impl;

import com.intuit.stackoverflow.exceptions.AnswerException;
import com.intuit.stackoverflow.exceptions.QuestionException;
import com.intuit.stackoverflow.model.Answer;
import com.intuit.stackoverflow.model.Question;
import com.intuit.stackoverflow.repository.AnswerRepository;
import com.intuit.stackoverflow.repository.QuestionRepository;
import com.intuit.stackoverflow.requestHandler.CreateAnswerRequest;
import com.intuit.stackoverflow.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AnswerServiceImpl implements AnswerService {
    @Autowired
    AnswerRepository answerRepository;
    @Autowired
    QuestionRepository questionRepository;
    @Override
    public List<Answer> getAllAnswersForQuestion(Integer id) {
        return answerRepository.findAllByParentAnswerIsNullAndQuestionId(id);
    }

    @Override
    public Answer getAnswerById(Integer answerId) {
        return answerRepository.findById(answerId)
                .orElseThrow(() -> new AnswerException("Answer not found with id: " + answerId));
    }

    @Override
    public Answer createAnswer(CreateAnswerRequest request) {
        Answer answer = new Answer();
        answer.setAnswer(request.getAnswer());

        if (request.getParentAnswerId() != null) {
            Answer parentAnswer = answerRepository.findById(request.getParentAnswerId())
                    .orElseThrow(() -> new AnswerException("Parent Answer not found with id: " + request.getParentAnswerId()));
            answer.setParentAnswer(parentAnswer);
        } else if (request.getQuestionId() != null) {
            Question question = questionRepository.findById(request.getQuestionId())
                    .orElseThrow(() -> new AnswerException("Question not found with id: " + request.getQuestionId()));
            answer.setParentAnswer(null);
            answer.setQuestionId(question.getId());
        }
        answer.setUpvoteCount(0);
        return answerRepository.save(answer);
    }

    public Answer getAnswer(Integer id) {
        return answerRepository.findById(id)
                .orElseThrow(() -> new AnswerException("Answer not found with id: " + id));
    }
    @Override
    public Answer upvoteAnswer(Integer id) {
        Answer answer = getAnswer(id);
        answer.setUpvoteCount(answer.getUpvoteCount() + 1);
        return answerRepository.save(answer);

    }
}
