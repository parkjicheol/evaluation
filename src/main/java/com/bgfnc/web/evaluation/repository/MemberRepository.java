package com.bgfnc.web.evaluation.repository;

import com.bgfnc.web.evaluation.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    public Optional<Member> findByMemberId(String memberId);

    public List<Member> findByName(String name);

    public List<Member> findByNameLike(String keyword);

}
