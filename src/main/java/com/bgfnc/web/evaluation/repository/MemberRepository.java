package com.bgfnc.web.evaluation.repository;

import com.bgfnc.web.evaluation.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberRepository extends JpaRepository<Member, Integer> {

    public List<Member> findById(String id);

    public List<Member> findByName(String name);

    public List<Member> findByNameLike(String keyword);

}
