package com.intuit.stackoverflow.repository;

import com.intuit.stackoverflow.model.Question;
import com.intuit.stackoverflow.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

//import java.awt.print.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Integer> {
    List<Question> findByUser(User user);
    List<Question> findTopByOrderByUpvoteCountDesc(Pageable pageable);

    List<Question> findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase(String titleQuery, String descriptionQuery);


}
