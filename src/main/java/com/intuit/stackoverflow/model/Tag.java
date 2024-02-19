package com.intuit.stackoverflow.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tag")
public class Tag {
    @Id
    private Integer id;
    @Column(name = "tag_name")
    private String tagName;
    @Column(name = "tag_description")
    private String tagDescription;
    @JsonIgnore
    @ManyToOne(targetEntity = Question.class)
    @JoinColumn(name = "question_id")
    private Question question;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tag tag = (Tag) o;
        return Objects.equals(id, tag.id) && Objects.equals(tagName, tag.tagName) && Objects.equals(tagDescription, tag.tagDescription);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, tagName, tagDescription, question);
    }
}