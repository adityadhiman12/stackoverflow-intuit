package com.intuit.stackoverflow.requestHandler;


import com.intuit.stackoverflow.model.Question;
import com.intuit.stackoverflow.model.Tag;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TagRequest {
    private Tag tag;
    private Question question;
}
