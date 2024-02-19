package com.intuit.stackoverflow.repository;

import com.intuit.stackoverflow.model.Answer;
import com.intuit.stackoverflow.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnswerRepository extends JpaRepository<Answer, Integer> {
    List<Answer> findAllByParentAnswerIsNullAndQuestionId(Integer questionId);
    List<Answer> findByAnswerContainingIgnoreCase(String query);

}
