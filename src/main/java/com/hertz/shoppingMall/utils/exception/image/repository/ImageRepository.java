package com.hertz.shoppingMall.utils.exception.image.repository;

import com.hertz.shoppingMall.utils.exception.image.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {
}
