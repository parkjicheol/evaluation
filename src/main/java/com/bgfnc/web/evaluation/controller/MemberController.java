package com.bgfnc.web.evaluation.controller;

import com.bgfnc.web.evaluation.common.abs.AbstractBaseController;
import com.bgfnc.web.evaluation.exception.ResourceNotFoundException;
import com.bgfnc.web.evaluation.model.Member;
import com.bgfnc.web.evaluation.service.MemberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("member")
public class MemberController extends AbstractBaseController<MemberController> {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    // 모든 회원 조회
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Member>> getAllMembers() {
        List<Member> members = memberService.findAll();
        return new ResponseEntity<List<Member>>(members, HttpStatus.OK);
    }

    // 회원 번호로 조회
    @GetMapping(value = "/{seq}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Member> getMember(@PathVariable("seq") Integer seq) {
        Optional<Member> member = memberService.findById(seq);
        return new ResponseEntity<Member>(member.get(), HttpStatus.OK);
    }

    // 회원 번호로 삭제
    @DeleteMapping(value = "/{seq}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> deleteMember(@PathVariable("seq") Integer seq) {

        if (!memberService.existsById(seq)) {
            throw new ResourceNotFoundException("Question not found with id " + seq);
        }

        memberService.deleteById(seq);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

    // 회원 번호로 수정
    @PutMapping(value = "/{seq}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Member> updateMember(@PathVariable("seq") Integer seq, Member member) {

        if (!memberService.existsById(seq)) {
            throw new ResourceNotFoundException("Question not found with id " + seq);
        }

        memberService.updateById(seq, member);
        return new ResponseEntity<Member>(member, HttpStatus.OK);
    }

    // 회원 입력
    @PostMapping
    public ResponseEntity<Member> save(Member member) {
        member.setRegisterDate(Calendar.getInstance());
        return new ResponseEntity<Member>(memberService.save(member), HttpStatus.OK);
    }

    // 회원 입력
    @GetMapping(value = "/saveMember", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Member> save(HttpServletRequest req, Member member) {
        return new ResponseEntity<Member>(memberService.save(member), HttpStatus.OK);
    }

}
