package com.hertz.shoppingMall.review.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReviewForm {

    @NotNull
    private Long orderItemId;

    @NotNull
    @Min(0)
    @Max(5)
    private int rating;

    @NotBlank
    @Size(max = 1000)
    private String content;

}
