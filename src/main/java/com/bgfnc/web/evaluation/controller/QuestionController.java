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
    @GetMapping(value = "/{questionSeq}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Question> getQuestion(@PathVariable("questionSeq") Integer questionSeq) {
        Optional<Question> question = questionService.findById(questionSeq);

        if (!question.isPresent()) {
            throw new ResourceNotFoundException("Question not found with id " + questionSeq);
        }

        return new ResponseEntity<Question>(question.get(), HttpStatus.OK);
    }

    // 질문 번호로 삭제
    @DeleteMapping(value = "/{questionSeq}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteQuestion(@PathVariable("questionSeq") Integer questionSeq) {

        if (!questionService.existsById(questionSeq)) {
            throw new ResourceNotFoundException("Question not found with id " + questionSeq);
        }

        return questionService.deleteById(questionSeq);
    }

    // 질문 번호로 수정
    @PatchMapping(value = "/{questionSeq}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Question> updateQuestion(@PathVariable("questionSeq") Integer questionSeq, Question questionRequest) {

        if (!questionService.existsById(questionSeq)) {
            throw new ResourceNotFoundException("Question not found with id " + questionSeq);
        }

        questionRequest.setMember(new Member(2));
        questionRequest.setUpdateDate(Calendar.getInstance());
        return new ResponseEntity<Question>(questionService.updateById(questionSeq, questionRequest), HttpStatus.OK);
    }

    // 질문 입력
    @PostMapping
    public ResponseEntity<Question> saveQuestion(Question questionRequest) {
        questionRequest.setMember(new Member(2));
        questionRequest.setRegisterDate(Calendar.getInstance());
        return new ResponseEntity<Question>(questionService.save(questionRequest), HttpStatus.OK);
    }

}
