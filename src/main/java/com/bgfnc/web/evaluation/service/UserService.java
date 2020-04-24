package com.bgfnc.web.evaluation.service;

import com.bgfnc.web.evaluation.dto.UserVo;
import com.bgfnc.web.evaluation.model.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Optional;

@Service
@Transactional
public class UserService implements UserDetailsService {

    private final MemberService memberService;

    @Autowired
    public UserService(MemberService memberService) {
        this.memberService = memberService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Member> member = memberService.findByMemberId(username);

        if (!member.isPresent()) {
            throw new UsernameNotFoundException("회원정보를 확인할 수 없습니다.");
        }

        UserVo userVo = new UserVo();
        userVo.setSeq(member.get().getSeq());
        userVo.setId(member.get().getMemberId());
        userVo.setPassword(member.get().getPassword());
        userVo.setName(member.get().getName());
        //        // userVO.setRoles(getRoles(loginId));
        userVo.setRoles(Arrays.asList("USER", "ADMIN"));

        return userVo;
    }
}
