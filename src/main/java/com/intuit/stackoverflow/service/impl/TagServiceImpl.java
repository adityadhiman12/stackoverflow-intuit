package com.intuit.stackoverflow.service.impl;

import com.intuit.stackoverflow.exceptions.QuestionException;
import com.intuit.stackoverflow.exceptions.TagException;
import com.intuit.stackoverflow.model.Question;
import com.intuit.stackoverflow.model.Tag;
import com.intuit.stackoverflow.repository.QuestionRepository;
import com.intuit.stackoverflow.repository.TagRepository;
import com.intuit.stackoverflow.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TagServiceImpl implements TagService {
    @Autowired
    TagRepository tagRepository;
    @Autowired
    QuestionRepository questionRepository;
    @Override
    public Tag removeTag(Integer id) {
        Optional<Tag> tag = tagRepository.findById(id);
        if(tag.isPresent()){
            Tag tag1 = tag.get();
            tagRepository.delete(tag1);
            return tag1;
        } else {
            throw new TagException("Tag is not present with id" + id);
        }
    }

    @Override
    public Tag addTag(Tag tag) {
        Optional<Tag> existingTag = tagRepository.findById(tag.getId());
        if (existingTag.isEmpty()) {
            // Check if the associated question exists
            Optional<Question> existingQuestion = questionRepository.findById(tag.getQuestion().getId());
            if (existingQuestion.isPresent()) {
                // Set the question for the tag and save
                tag.setQuestion(existingQuestion.get());
                return tagRepository.save(tag);
            } else {
                throw new QuestionException("Question not found with id: " + tag.getQuestion());
            }
        } else {
            throw new TagException("Tag is already present with id " + tag.getId());
        }
    }
}
