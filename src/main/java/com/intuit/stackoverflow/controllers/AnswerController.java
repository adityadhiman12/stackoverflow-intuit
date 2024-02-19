package com.intuit.stackoverflow.controllers;

import com.intuit.stackoverflow.model.Answer;
import com.intuit.stackoverflow.requestHandler.CreateAnswerRequest;
import com.intuit.stackoverflow.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/answers")
public class AnswerController {
    @Autowired
    AnswerService answerService;

    @GetMapping("/question/{questionId}")
    public List<Answer> getAllAnswersByQuestion(@PathVariable int questionId) {
        return answerService.getAllAnswersForQuestion(questionId);
    }

    @GetMapping("/answer/{answerId}")
    public List<Answer> getAnswerById(@PathVariable int answerId) {
        return answerService.getAllAnswersForQuestion(answerId);
    }

    @PostMapping("/create")
    public ResponseEntity<Answer> createAnswer(@RequestBody CreateAnswerRequest request) {
        Answer createdAnswer = answerService.createAnswer(request);
        return new ResponseEntity<>(createdAnswer, HttpStatus.CREATED);
    }
    @PostMapping("/{id}/upvote")
    public ResponseEntity<Answer> upvoteAnswer(@PathVariable Integer id) {
        Answer answer = answerService.upvoteAnswer(id);
        return new ResponseEntity<>(answer, HttpStatus.OK);
    }
}
