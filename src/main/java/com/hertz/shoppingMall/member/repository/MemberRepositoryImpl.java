package com.hertz.shoppingMall.member.repository;

import com.hertz.shoppingMall.member.model.Member;
import com.hertz.shoppingMall.member.model.QMember;
import com.hertz.shoppingMall.utils.search.SearchRequestDto;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<Member> seearchMembers(SearchRequestDto searchRequestDto, Pageable pageable) {

        QMember member = QMember.member;
        BooleanBuilder builder = new BooleanBuilder();

        if (searchRequestDto.getSearchType() != null && searchRequestDto.getKeyword() != null) {
            String keyword = searchRequestDto.getKeyword();
            switch (searchRequestDto.getSearchType()) {
                case "loginId":
                    builder.and(member.loginId.containsIgnoreCase(keyword));
                    break;
                case "username":
                    builder.and(member.username.containsIgnoreCase(keyword));
                    break;
            }
        }

        JPAQuery<Member> query = queryFactory.selectFrom(member).where(builder);
        List<Member> members = query.offset(pageable.getOffset()).limit(pageable.getPageSize()).fetch();
        long total = query.fetchCount();

        return new PageImpl<>(members, pageable, total);
    }
}
