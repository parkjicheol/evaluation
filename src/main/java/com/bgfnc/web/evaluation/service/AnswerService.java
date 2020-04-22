package com.bgfnc.web.evaluation.service;

import com.bgfnc.web.evaluation.model.Answer;
import com.bgfnc.web.evaluation.repository.AnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AnswerService {

    private AnswerRepository answerRepository;

    @Autowired
    public AnswerService(AnswerRepository answerRepository) {
        this.answerRepository = answerRepository;
    }

    public List<Answer> findAll() {
        return new ArrayList<>(answerRepository.findAll());
    }

    public Optional<Answer> findById(Integer seq) {
        return answerRepository.findById(seq);
    }

    public void deleteById(Integer seq) {
        answerRepository.deleteById(seq);
    }

    public Answer save(Answer answer) {
        answerRepository.save(answer);
        return answer;
    }

    public void updateById(Integer seq, Answer answer) {
        Optional<Answer> optionalAnswer = answerRepository.findById(seq);

        if (optionalAnswer.isPresent()) {
            optionalAnswer.get().setSeq(answer.getSeq());
            optionalAnswer.get().setDescription(answer.getDescription());
            optionalAnswer.get().setRegisterDate(answer.getRegisterDate());
            optionalAnswer.get().setUpdateDate(answer.getUpdateDate());
            answerRepository.save(answer);
        }
    }

    public Boolean existsById(Integer seq) {
        return answerRepository.existsById(seq);
    }
    
}
