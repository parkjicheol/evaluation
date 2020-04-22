package com.bgfnc.web.evaluation.service;

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

    public Optional<Member> findById(Integer seq) {
        return memberRepository.findById(seq);
    }

    public void deleteById(Integer seq) {
        memberRepository.deleteById(seq);
    }

    public Member save(Member member) {
        memberRepository.save(member);
        return member;
    }

    public void updateById(Integer seq, Member member) {
        Optional<Member> optionalMember = memberRepository.findById(seq);

        if (optionalMember.isPresent()) {
            optionalMember.get().setSeq(member.getSeq());
            optionalMember.get().setId(member.getId());
            optionalMember.get().setName(member.getName());
            optionalMember.get().setRegisterDate(member.getRegisterDate());

            memberRepository.save(member);
        }
    }

    public Boolean existsById(Integer seq) {
        return memberRepository.existsById(seq);
    }

}
