package com.bgfnc.web.evaluation.controller;

import com.bgfnc.web.evaluation.common.abs.AbstractBaseController;
import com.bgfnc.web.evaluation.exception.ResourceNotFoundException;
import com.bgfnc.web.evaluation.model.Member;
import com.bgfnc.web.evaluation.model.Answer;
import com.bgfnc.web.evaluation.service.AnswerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("answer")
public class AnswerController extends AbstractBaseController<AnswerController> {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final AnswerService answerService;

    @Autowired
    public AnswerController(AnswerService answerService) {
        this.answerService = answerService;
    }

    // 모든 질문 조회
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Answer>> getAllAnswers() {
        List<Answer> answers = answerService.findAll();
        return new ResponseEntity<List<Answer>>(answers, HttpStatus.OK);
    }

    // 질문 번호로 조회
    @GetMapping(value = "/{answerSeq}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Answer> getAnswer(@PathVariable("answerSeq") Integer answerSeq) {
        Optional<Answer> answer = answerService.findById(answerSeq);

        if (!answer.isPresent()) {
            throw new ResourceNotFoundException("Answer not found with id " + answerSeq);
        }

        return new ResponseEntity<Answer>(answer.get(), HttpStatus.OK);
    }

    // 질문 번호로 삭제
    @DeleteMapping(value = "/{answerSeq}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteAnswer(@PathVariable("answerSeq") Integer answerSeq) {

        if (!answerService.existsById(answerSeq)) {
            throw new ResourceNotFoundException("Answer not found with id " + answerSeq);
        }

        return answerService.deleteById(answerSeq);
    }

    // 질문 번호로 수정
    @PatchMapping
    public ResponseEntity<Answer> updateAnswer(Integer answerSeq, Answer answerRequest) {

        if (!answerService.existsById(answerSeq)) {
            throw new ResourceNotFoundException("Answer not found with id " + answerSeq);
        }

        return new ResponseEntity<Answer>(answerService.updateByAnswer(answerSeq, answerRequest), HttpStatus.OK);
    }

    // 질문 입력
    @PostMapping
    public ResponseEntity<Answer> save(Integer questionSeq, Answer answerRequest) {
        answerRequest.setMember(new Member(2));
        answerRequest.setRegisterDate(Calendar.getInstance());
        return new ResponseEntity<Answer>(answerService.save(questionSeq, answerRequest), HttpStatus.OK);
    }

    // 질문 입력
    @GetMapping(value = "/saveAnswer", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Answer> save(Integer questionSeq, HttpServletRequest request, Answer answerRequest) {
        return new ResponseEntity<Answer>(answerService.save(questionSeq, answerRequest), HttpStatus.OK);
    }

}
