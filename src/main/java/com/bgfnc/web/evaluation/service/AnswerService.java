package com.bgfnc.web.evaluation.service;

import com.bgfnc.web.evaluation.exception.ResourceNotFoundException;
import com.bgfnc.web.evaluation.model.Answer;
import com.bgfnc.web.evaluation.repository.AnswerRepository;
import com.bgfnc.web.evaluation.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@Service
public class AnswerService {

    private QuestionRepository questionRepository;
    private AnswerRepository answerRepository;

    @Autowired
    public AnswerService(QuestionRepository questionRepository, AnswerRepository answerRepository) {
        this.questionRepository = questionRepository;
        this.answerRepository = answerRepository;
    }

    public List<Answer> findAll() {
        return new ArrayList<>(answerRepository.findAll());
    }

    public Optional<Answer> findById(Integer answerSeq) {
        return answerRepository.findById(answerSeq);
    }

    public ResponseEntity<?> deleteById(Integer answerSeq) {
        return answerRepository.findById(answerSeq)
                .map(answer -> {
                    answerRepository.delete(answer);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("Answer not found with id " + answerSeq));
    }

    public Answer save(Integer questionSeq, Answer answerRequest) {
        return questionRepository.findById(questionSeq)
                .map(question -> {
                    answerRequest.setQuestion(question);
                    return answerRepository.save(answerRequest);
                }).orElseThrow(() -> new ResourceNotFoundException("Question not found with id " + questionSeq));
    }

    public Answer updateByAnswer(Answer answerRequest) {
        return answerRepository.findById(answerRequest.getSeq())
                .map(answer -> {
                    return answerRepository.save(answer);
                }).orElseThrow(() -> new ResourceNotFoundException("Answer not found with id " + answerRequest.getSeq()));
    }

    public Boolean existsById(Integer answerSeq) {
        return answerRepository.existsById(answerSeq);
    }
    
}
