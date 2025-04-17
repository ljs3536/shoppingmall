package com.hertz.shoppingMall.product.repository;

import com.hertz.shoppingMall.member.model.Member;
import com.hertz.shoppingMall.product.model.Product;
import com.hertz.shoppingMall.utils.search.SearchRequestDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductRepositoryCustom {

    Page<Product> searchProducts(SearchRequestDto searchRequestDto, Pageable pageable);

    Page<Product> searchProductsByCreatedBy(SearchRequestDto searchRequestDto, Member member, Pageable pageable);
}
