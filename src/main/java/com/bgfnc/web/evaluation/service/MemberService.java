package com.bgfnc.web.evaluation.service;

import com.bgfnc.web.evaluation.exception.ResourceNotFoundException;
import com.bgfnc.web.evaluation.model.Member;
import com.bgfnc.web.evaluation.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MemberService {

    private MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public List<Member> findAll() {
        return new ArrayList<>(memberRepository.findAll());
    }

    public Optional<Member> findById(Long memberSeq) {
        return memberRepository.findById(memberSeq);
    }

    public Optional<Member> findByMemberId(String memberId) {
        return memberRepository.findByMemberId(memberId);
    }

    public Member deleteById(Long memberSeq) {
        return memberRepository.findById(memberSeq)
                .map(member -> {
                    memberRepository.deleteById(memberSeq);
                    return member;
                }).orElseThrow(() -> new ResourceNotFoundException("Member not found with id " + memberSeq));
    }

    public Member save(Member memberRequest) {
        return memberRepository.save(memberRequest);
    }

    public Member updateById(Member memberRequest) {
        return memberRepository.findById(memberRequest.getSeq())
                .map(member -> {
                    return memberRepository.save(memberRequest);
                }).orElseThrow(() -> new ResourceNotFoundException("Member not found with id " + memberRequest.getSeq()));
    }

    public Boolean existsById(Long memberSeq) {
        return memberRepository.existsById(memberSeq);
    }

}
