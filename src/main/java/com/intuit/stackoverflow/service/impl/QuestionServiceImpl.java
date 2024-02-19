package com.intuit.stackoverflow.service.impl;

import com.intuit.stackoverflow.exceptions.QuestionException;
import com.intuit.stackoverflow.model.Question;
import com.intuit.stackoverflow.model.Tag;
import com.intuit.stackoverflow.model.User;
import com.intuit.stackoverflow.repository.QuestionRepository;
import com.intuit.stackoverflow.repository.TagRepository;
import com.intuit.stackoverflow.repository.UserRepository;
import com.intuit.stackoverflow.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import java.util.Optional;

@Service
public class QuestionServiceImpl implements QuestionService {
    @Autowired
    QuestionRepository questionRepository;
    @Autowired
    TagRepository tagRepository;
    @Autowired
    UserRepository userRepository;

    @Override
    public Question createQuestion(Question question) {
        if (question.getTitle() == null || question.getTitle().isEmpty()) {
            throw new QuestionException("Question title is required");
        }
        if (question.getUser() == null || question.getUser().getId() == null) {
            throw new QuestionException("User information is required");
        }
        User user = userRepository.findById(question.getUser().getId())
                .orElseThrow(() -> new QuestionException("User not found with ID: " + question.getUser().getId()));

        List<Question> existingQuestions = questionRepository.findByUser(user);
        if (existingQuestions.stream().anyMatch(q -> q.getTitle().equals(question.getTitle()))) {
            throw new QuestionException("Question with the same title already exists for the user");
        }

        // Save each Tag before saving the Question
        List<Tag> tagList = question.getTagList();
        for (Tag tag : tagList) {
            // Check if Tag with the same ID already exists
            Optional<Tag> existingTag = tagRepository.findById(tag.getId());
            if (existingTag.isEmpty()) {
                tagRepository.save(tag);
            }
            else {
                throw new QuestionException("Tag with ID " + tag.getId() + " is already present");
            }
        }

        question.setUpvoteCount(0);
        Question savedQuestion = questionRepository.save(question);
        for (Tag tag : tagList) {
            Optional<Tag> optionalTag = tagRepository.findById(tag.getId());
            if(optionalTag.isPresent()) {
                Tag t = optionalTag.get();
                t.setQuestion(savedQuestion);
                tagRepository.save(t);
            }
        }
        return question;
    }

    public Question getQuestion(Integer id) {
        return questionRepository.findById(id)
                .orElseThrow(() -> new QuestionException("Question not found with id: " + id));
    }

    @Override
    public Question deleteQuestion(Integer id) {
        Optional<Question> question1 = questionRepository.findById(id);
        if(question1.isPresent()) {
            Question q= question1.get();
            questionRepository.deleteById(id);
            return q;
        }
        else {
            throw new QuestionException("Question not found by this id" + id);
        }
    }

    @Override
    public Question updateQuestion(Integer id, Question question) {
        Optional<Question> question1 = questionRepository.findById(id);
        if(question1.isPresent()) {
            Question q = question1.get();
            q.setTitle(question.getTitle());
            q.setDescription(question.getDescription());
            questionRepository.save(q);
            return q;
        }
        else {
            throw new QuestionException("Question not found by this id"+id+" to update");
        }
    }

    @Override
    public Question upvoteQuestion(Integer id) {
        Question question = getQuestion(id);
        question.setUpvoteCount(question.getUpvoteCount() + 1);
        return questionRepository.save(question);
    }

    @Override
    public List<Question> getTopQuestionsByUpvoteCount(Integer limit) {
        return questionRepository.findTopByOrderByUpvoteCountDesc((Pageable) PageRequest.of(0, limit));
    }

}
