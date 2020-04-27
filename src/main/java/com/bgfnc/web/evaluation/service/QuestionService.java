package com.bgfnc.web.evaluation.service;

import com.bgfnc.web.evaluation.exception.ResourceNotFoundException;
import com.bgfnc.web.evaluation.model.Question;
import com.bgfnc.web.evaluation.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    public Optional<Question> findById(Integer questionSeq) {
        return questionRepository.findById(questionSeq);
    }

    public ResponseEntity<?> deleteById(Integer questionSeq) {
        return questionRepository.findById(questionSeq)
                .map(question -> {
                    questionRepository.delete(question);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("Question not found with id " + questionSeq));
    }

    public Question save(Question questionSeq) {
        return questionRepository.save(questionSeq);
    }

    public Question updateById(Question questionRequest) {
        return questionRepository.findById(questionRequest.getSeq())
                .map(question -> {
                    return questionRepository.save(questionRequest);
                }).orElseThrow(() -> new ResourceNotFoundException("Question not found with id " + questionRequest.getSeq()));
    }

    public Boolean existsById(Integer questionSeq) {
        return questionRepository.existsById(questionSeq);
    }

}
