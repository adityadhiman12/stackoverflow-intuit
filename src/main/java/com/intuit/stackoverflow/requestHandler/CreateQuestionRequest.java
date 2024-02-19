package com.intuit.stackoverflow.requestHandler;

import com.intuit.stackoverflow.model.Question;
import com.intuit.stackoverflow.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateQuestionRequest {
    private Question question;
    private User user;
}