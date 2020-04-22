package com.bgfnc.web.evaluation.repository;

import com.bgfnc.web.evaluation.model.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Integer> {

    public List<Answer> findByDescriptionLike(String keyword);

}