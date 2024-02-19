package com.intuit.stackoverflow.controllers;

import com.intuit.stackoverflow.model.Question;
import com.intuit.stackoverflow.model.Tag;
import com.intuit.stackoverflow.model.User;
import com.intuit.stackoverflow.requestHandler.CreateQuestionRequest;
import com.intuit.stackoverflow.responseHandler.ResponseHandler;
import com.intuit.stackoverflow.service.QuestionService;
import com.intuit.stackoverflow.service.UserService;
import netscape.javascript.JSObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/question")
public class QuestionController {
    @Autowired
    QuestionService questionService;
    @Autowired
    UserService userService;


    @GetMapping("/top")
    public ResponseEntity<List<Question>> getTopQuestions(@RequestParam(required = false) String sortBy, @RequestParam(required = false) Integer limit) {
        List<Question> topQuestions;
        if ("upvote".equalsIgnoreCase(sortBy)) {
            topQuestions = questionService.getTopQuestionsByUpvoteCount(limit);
        }else {
            // Default to sorting by upvote count if sortBy is not provided or invalid
            topQuestions = questionService.getTopQuestionsByUpvoteCount(limit);
        }

        return new ResponseEntity<>(topQuestions, HttpStatus.OK);
    }
    @PostMapping("/create")
    public ResponseEntity<Question> createQuestionHandler(@RequestBody CreateQuestionRequest request) {
        Question question = request.getQuestion();
        User user = request.getUser();
        question.setUser(user);
        Question createdQuestion = questionService.createQuestion(question);
        user.getQuestions().add(createdQuestion);
        return new ResponseEntity<>(createdQuestion,HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Question> deleteQuestionHandler(@PathVariable("id") Integer id) {
        Question question1 = questionService.deleteQuestion(id);
        return new ResponseEntity<>(question1, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Question> updateQuestionHandler(@PathVariable("id") Integer id, @RequestBody Question question) {
        Question question1 = questionService.updateQuestion(id, question);
        return new ResponseEntity<>(question1, HttpStatus.ACCEPTED);
    }


    @PostMapping("/{id}/upvote")
    public ResponseEntity<Question> upvoteQuestion(@PathVariable Integer id) {
        Question question = questionService.upvoteQuestion(id);
        return new ResponseEntity<>(question, HttpStatus.OK);
    }
}
