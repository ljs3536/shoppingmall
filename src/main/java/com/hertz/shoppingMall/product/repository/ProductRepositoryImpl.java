package com.hertz.shoppingMall.product.repository;

import com.hertz.shoppingMall.member.model.Member;
import com.hertz.shoppingMall.product.model.Product;
import com.hertz.shoppingMall.product.model.QProduct;
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
public class ProductRepositoryImpl implements ProductRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<Product> searchProducts(SearchRequestDto searchRequestDto, Pageable pageable) {
        QProduct product = QProduct.product;
        BooleanBuilder builder = new BooleanBuilder();

        if (searchRequestDto.getSearchType() != null && searchRequestDto.getKeyword() != null) {
            String keyword = searchRequestDto.getKeyword();
            switch (searchRequestDto.getSearchType()) {
                case "productName":
                    builder.and(product.name.containsIgnoreCase(keyword));
                    break;
                case "createdBy":
                    builder.and(product.createdBy.username.containsIgnoreCase(keyword));
                    break;
            }
        }

        JPAQuery<Product> query = queryFactory.selectFrom(product).where(builder);
        List<Product> products = query.offset(pageable.getOffset()).limit(pageable.getPageSize()).fetch();
        long total = query.fetchCount();

        return new PageImpl<>(products, pageable, total);
    }

    @Override
    public Page<Product> searchProductsByCreatedBy(SearchRequestDto searchRequestDto, Member member, Pageable pageable) {
        QProduct product = QProduct.product;
        BooleanBuilder builder = new BooleanBuilder();

        builder.and(product.createdBy.id.eq(member.getId()));
        if (searchRequestDto.getSearchType() != null && searchRequestDto.getKeyword() != null) {
            String keyword = searchRequestDto.getKeyword();
            switch (searchRequestDto.getSearchType()) {
                case "productName":
                    builder.and(product.name.containsIgnoreCase(keyword));
                    break;
                case "createdBy":
                    builder.and(product.createdBy.username.containsIgnoreCase(keyword));
                    break;
            }
        }

        JPAQuery<Product> query = queryFactory.selectFrom(product).where(builder);
        List<Product> products = query.offset(pageable.getOffset()).limit(pageable.getPageSize()).fetch();
        long total = query.fetchCount();

        return new PageImpl<>(products, pageable, total);
    }
}
