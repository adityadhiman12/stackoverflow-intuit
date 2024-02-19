package com.intuit.stackoverflow.requestHandler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CreateAnswerRequest {
    private String answer;
    private Integer questionId;
    private Integer parentAnswerId;
}
