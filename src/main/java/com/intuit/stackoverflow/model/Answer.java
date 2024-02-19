package com.intuit.stackoverflow.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String answer;
    @Nullable
    private Integer questionId;
    @Column(name = "upvote_count")
    private Integer upvoteCount;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Answer parentAnswer;

    // Collection of child answers
    @OneToMany(mappedBy = "parentAnswer")
    private List<Answer> childAnswers = new ArrayList<>();
}
