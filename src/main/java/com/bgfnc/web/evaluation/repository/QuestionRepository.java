package com.bgfnc.web.evaluation.repository;

import com.bgfnc.web.evaluation.model.Member;
import com.bgfnc.web.evaluation.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer> {

    public List<Question> findByTitleLike(String keyword);

    public List<Question> findByDescriptionLike(String keyword);

}