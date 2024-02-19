package com.intuit.stackoverflow.controllers;

import com.intuit.stackoverflow.model.Answer;
import com.intuit.stackoverflow.model.Question;
import com.intuit.stackoverflow.model.Tag;
import com.intuit.stackoverflow.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/search")
public class SearchController {
    @Autowired
    private SearchService searchService;

    @GetMapping("/tags")
    public ResponseEntity<List<Tag>> searchTags(@RequestParam String query) {
        List<Tag> tags = searchService.searchTags(query);
        return new ResponseEntity<>(tags, HttpStatus.OK);
    }

    @GetMapping("/questions")
    public ResponseEntity<List<Question>> searchQuestions(@RequestParam String query) {
        List<Question> questions = searchService.searchQuestions(query);
        return new ResponseEntity<>(questions, HttpStatus.OK);
    }

    @GetMapping("/answers")
    public ResponseEntity<List<Answer>> searchAnswers(@RequestParam String query) {
        List<Answer> answers = searchService.searchAnswers(query);
        return new ResponseEntity<>(answers, HttpStatus.OK);
    }
}
