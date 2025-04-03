package com.hertz.shoppingMall.utils.page;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PageRequestDto {

    private int page = 0;           // 기본값 : 첫번째 페이지
    private int size = 10;          // 기본값 : 한 페이지에 10개
    private String sort = "id";     // 기본 정렬 기준
}
