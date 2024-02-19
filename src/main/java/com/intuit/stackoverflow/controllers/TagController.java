package com.intuit.stackoverflow.controllers;

import com.intuit.stackoverflow.model.Question;
import com.intuit.stackoverflow.model.Tag;
import com.intuit.stackoverflow.requestHandler.TagRequest;
import com.intuit.stackoverflow.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tag")
public class TagController {
    @Autowired
    TagService tagService;

    @PostMapping("/add")
    public ResponseEntity<Tag> addTagController(@RequestBody TagRequest request) {
        Tag tag = request.getTag();
        Question question = request.getQuestion();
        tag.setQuestion(question);

        Tag addedTag = tagService.addTag(tag);
        return new ResponseEntity<>(addedTag, HttpStatus.CREATED);
    }

    @DeleteMapping("tag/{id}")
    public ResponseEntity<Tag> removeTagController(@PathVariable Integer id) {
        Tag tag1 = tagService.removeTag(id);
        return new ResponseEntity<>(tag1, HttpStatus.OK);
    }

}
