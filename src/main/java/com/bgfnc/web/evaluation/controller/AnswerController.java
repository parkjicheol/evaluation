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
    @GetMapping(value = "/{seq}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Answer> getAnswer(@PathVariable("seq") Integer seq) {
        Optional<Answer> answer = answerService.findById(seq);
        return new ResponseEntity<Answer>(answer.get(), HttpStatus.OK);
    }

    // 질문 번호로 삭제
    @DeleteMapping(value = "/{seq}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> deleteAnswer(@PathVariable("seq") Integer seq) {

        if (!answerService.existsById(seq)) {
            throw new ResourceNotFoundException("Answer not found with id " + seq);
        }

        answerService.deleteById(seq);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

    // 질문 번호로 수정
    @PutMapping(value = "/{seq}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Answer> updateAnswer(@PathVariable("seq") Integer seq, Answer answer) {

        if (!answerService.existsById(seq)) {
            throw new ResourceNotFoundException("Answer not found with id " + seq);
        }

        answerService.updateById(seq, answer);
        return new ResponseEntity<Answer>(answer, HttpStatus.OK);
    }

    // 질문 입력
    @PostMapping
    public ResponseEntity<Answer> save(Answer answer) {
        answer.setMember(new Member(2));
        answer.setRegisterDate(Calendar.getInstance());
        return new ResponseEntity<Answer>(answerService.save(answer), HttpStatus.OK);
    }

    // 질문 입력
    @GetMapping(value = "/saveAnswer", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Answer> save(HttpServletRequest req, Answer answer) {
        return new ResponseEntity<Answer>(answerService.save(answer), HttpStatus.OK);
    }

}
