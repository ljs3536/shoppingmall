package com.hertz.shoppingMall.member.Repository;

import com.hertz.shoppingMall.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
