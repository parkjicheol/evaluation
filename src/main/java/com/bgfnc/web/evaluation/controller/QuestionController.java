package com.bgfnc.web.evaluation.controller;

import com.bgfnc.web.evaluation.common.abs.AbstractBaseController;
import com.bgfnc.web.evaluation.exception.ResourceNotFoundException;
import com.bgfnc.web.evaluation.model.Member;
import com.bgfnc.web.evaluation.model.Question;
import com.bgfnc.web.evaluation.service.QuestionService;
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
@RequestMapping("question")
public class QuestionController extends AbstractBaseController<QuestionController> {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final QuestionService questionService;

    @Autowired
    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    // 모든 질문 조회
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Question>> getAllQuestions() {
        List<Question> questions = questionService.findAll();
        return new ResponseEntity<List<Question>>(questions, HttpStatus.OK);
    }

    // 질문 번호로 조회
    @GetMapping(value = "/{seq}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Question> getQuestion(@PathVariable("seq") Integer seq) {
        Optional<Question> question = questionService.findById(seq);
        return new ResponseEntity<Question>(question.get(), HttpStatus.OK);
    }

    // 질문 번호로 삭제
    @DeleteMapping(value = "/{seq}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteQuestion(@PathVariable("seq") Integer questionSeq) {

        if (!questionService.existsById(questionSeq)) {
            throw new ResourceNotFoundException("Question not found with id " + questionSeq);
        }

        return questionService.deleteById(questionSeq);
    }

    // 질문 번호로 수정
    @PutMapping(value = "/{questionSeq}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Question> updateQuestion(@PathVariable("questionSeq") Integer questionSeq, Question question) {

        if (!questionService.existsById(questionSeq)) {
            throw new ResourceNotFoundException("Question not found with id " + questionSeq);
        }

        return new ResponseEntity<Question>(questionService.updateById(questionSeq, question), HttpStatus.OK);
    }

    // 질문 입력
    @PostMapping
    public ResponseEntity<Question> save(Question question) {
        question.setMember(new Member(2));
        question.setRegisterDate(Calendar.getInstance());
        return new ResponseEntity<Question>(questionService.save(question), HttpStatus.OK);
    }

    // 질문 입력
    @GetMapping(value = "/saveQuestion", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Question> save(HttpServletRequest req, Question question) {
        return new ResponseEntity<Question>(questionService.save(question), HttpStatus.OK);
    }

}
