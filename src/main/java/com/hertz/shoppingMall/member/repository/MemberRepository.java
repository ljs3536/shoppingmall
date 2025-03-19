package com.hertz.shoppingMall.member.repository;

import com.hertz.shoppingMall.member.model.MemberVO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<MemberVO, Long> {
}
