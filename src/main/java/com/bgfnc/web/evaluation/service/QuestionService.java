package com.bgfnc.web.evaluation.service;

import com.bgfnc.web.evaluation.model.Member;
import com.bgfnc.web.evaluation.model.Question;
import com.bgfnc.web.evaluation.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {

    private QuestionRepository questionRepository;

    @Autowired
    public QuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    public List<Question> findAll() {
        return new ArrayList<>(questionRepository.findAll());
    }

    public Optional<Question> findById(Integer seq) {
        return questionRepository.findById(seq);
    }

    public void deleteById(Integer seq) {
        questionRepository.deleteById(seq);
    }

    public Question save(Question question) {
        questionRepository.save(question);
        return question;
    }

    public void updateById(Integer seq, Question question) {
        Optional<Question> optionalQuestion = questionRepository.findById(seq);

        if (optionalQuestion.isPresent()) {
            optionalQuestion.get().setSeq(question.getSeq());
            optionalQuestion.get().setTitle(question.getTitle());
            optionalQuestion.get().setDescription(question.getDescription());
            optionalQuestion.get().setRegisterDate(question.getRegisterDate());
            optionalQuestion.get().setUpdateDate(question.getUpdateDate());
            questionRepository.save(question);
        }
    }

    public Boolean existsById(Integer seq) {
        return questionRepository.existsById(seq);
    }

}
