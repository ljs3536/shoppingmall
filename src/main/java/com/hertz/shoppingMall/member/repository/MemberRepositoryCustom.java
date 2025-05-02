package com.hertz.shoppingMall.member.repository;

import com.hertz.shoppingMall.member.model.Member;
import com.hertz.shoppingMall.member.model.Role;
import com.hertz.shoppingMall.utils.search.SearchRequestDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MemberRepositoryCustom {

    Page<Member> seearchMembers(SearchRequestDto requestDto, Pageable pageable);

    List<Member> findByRole(Role role);
}
