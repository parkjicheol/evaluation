package com.bgfnc.web.evaluation.controller;

import com.bgfnc.web.evaluation.common.abs.AbstractBaseController;
import com.bgfnc.web.evaluation.common.util.EncoderUtil;
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

import java.security.NoSuchAlgorithmException;
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
    @GetMapping(value = "/{memberSeq}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Member> getMember(@PathVariable("memberSeq") Long memberSeq) {
        Optional<Member> member = memberService.findById(memberSeq);

        if (!member.isPresent()) {
            throw new ResourceNotFoundException("Member not found with id " + memberSeq);
        }

        return new ResponseEntity<Member>(member.get(), HttpStatus.OK);
    }

    // 회원 번호로 삭제
    @DeleteMapping(value = "/{memberSeq}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> deleteMember(@PathVariable("memberSeq") Long memberSeq) {
        memberService.deleteById(memberSeq);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

    // 회원 번호로 수정
    @PatchMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Member> updateMember(Member memberRequest) throws NoSuchAlgorithmException {
        memberRequest.setPassword(EncoderUtil.encodeSha256(memberRequest.getPassword()));
        memberRequest.setUpdateDate(Calendar.getInstance());
        return new ResponseEntity<Member>(memberService.updateById(memberRequest), HttpStatus.OK);
    }

    // 회원 입력
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Member> saveMember(Member memberRequest) throws NoSuchAlgorithmException {
        memberRequest.setPassword(EncoderUtil.encodeSha256(memberRequest.getPassword()));
        memberRequest.setRegisterDate(Calendar.getInstance());
        return new ResponseEntity<Member>(memberService.save(memberRequest), HttpStatus.OK);
    }

}
