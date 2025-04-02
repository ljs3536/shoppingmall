package com.hertz.shoppingMall.member.repository;

import com.hertz.shoppingMall.member.model.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Member findByLoginId(String loginId);

    Page<Member> findAll(Pageable pageable);
}
