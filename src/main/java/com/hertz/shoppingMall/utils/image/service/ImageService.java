package com.hertz.shoppingMall.utils.image.service;

import com.hertz.shoppingMall.utils.image.model.Image;
import com.hertz.shoppingMall.utils.image.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ImageService {

    private final ImageRepository imageRepository;

    @Value("${file.access-url}")
    private String accessUrl;

    public String getImageUrl(Image image){
        if(image == null){ return null;}

        return accessUrl + image.getImageType().name() + "/"
                + image.getReferenceId() + "/" + image.getFileName();
    }

    // 모든 이미지 URL
    public List<String> getImageUrls(List<Image> images) {
        return images.stream()
                .map(this::getImageUrl)
                .collect(Collectors.toList());
    }
}
