package com.hertz.shoppingMall.utils.image.repository;

import com.hertz.shoppingMall.utils.image.model.Image;
import com.hertz.shoppingMall.utils.image.model.ImageType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ImageRepository extends JpaRepository<Image, Long> {
    void deleteByReferenceId(Long referenceId);

    Optional<Image> findByReferenceIdAndImageTypeAndIsMainTrue(Long referenceId, ImageType imageType);

    List<Image> findByReferenceIdAndImageType(Long referenceId, ImageType imageType);
}
