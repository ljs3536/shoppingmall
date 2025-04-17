package com.hertz.shoppingMall.member.repository;

import com.hertz.shoppingMall.member.model.Member;
import com.hertz.shoppingMall.utils.search.SearchRequestDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MemberRepositoryCustom {

    Page<Member> seearchMembers(SearchRequestDto requestDto, Pageable pageable);
}
