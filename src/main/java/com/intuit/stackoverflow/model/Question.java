package com.intuit.stackoverflow.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

//import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "question")
public class Question {
    @Id
    private Integer id;
    private String title;
    private String description;
    @Column(name = "upvote_count")
    private Integer upvoteCount;
    @JsonIgnore
    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "user_id")
    private User user;
    @OneToMany(mappedBy = "question")
    private List<Tag> tagList = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Question question = (Question) o;
        return Objects.equals(id, question.id) && Objects.equals(title, question.title) && Objects.equals(description, question.description) && Objects.equals(user, question.user) && Objects.equals(tagList, question.tagList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description, user, tagList);
    }
}
