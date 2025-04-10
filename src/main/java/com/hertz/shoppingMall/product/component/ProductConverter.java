package com.hertz.shoppingMall.product.component;

import com.hertz.shoppingMall.product.dto.ProductDto;
import com.hertz.shoppingMall.product.dto.ProductForm;
import com.hertz.shoppingMall.product.model.Product;
import com.hertz.shoppingMall.utils.exception.image.model.Image;
import com.hertz.shoppingMall.utils.exception.image.model.ImageType;
import com.hertz.shoppingMall.utils.exception.image.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ProductConverter {

    private final ImageService imageService;

    public ProductForm convertToForm(Product product) {
        ProductForm form = new ProductForm();
        form.setId(product.getId());
        form.setName(product.getName());
        form.setDescription(product.getDescription());
        form.setPrice(product.getPrice());
        form.setStockQuantity(product.getStockQuantity());
        form.setCategory(product.getCategory());

        // 이미지 URL 설정
        Image mainImage = product.getMainImage();
        if(mainImage == null){
            form.setSubImageUrl(imageService.getImageUrls(product.getSubImages()).stream()
                    .filter(url -> !url.equals(form.getMainImageUrl()))  // 메인 이미지 제외
                    .collect(Collectors.toList()));
            form.setSubImageIds(product.getSubImages().stream().map(Image::getId)
                    .collect(Collectors.toList()));
        }else {
            form.setMainImageUrl(imageService.getImageUrl(product.getMainImage()));
            form.setSubImageUrl(imageService.getImageUrls(product.getSubImages()).stream()
                    .filter(url -> !url.equals(form.getMainImageUrl()))  // 메인 이미지 제외
                    .collect(Collectors.toList()));
            form.setSubImageIds(product.getSubImages().stream().map(Image::getId)
                    .filter(id -> !id.equals(product.getMainImage().getId())) //메인 이미지 제외
                    .collect(Collectors.toList()));
        }
        return form;
    }

    public ProductDto convertToDto(Product product){
        ProductDto dto = new ProductDto();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setDescription(product.getDescription());
        dto.setPrice(product.getPrice());
        dto.setStockQuantity(product.getStockQuantity());
        dto.setCategory(product.getCategory().getName());

        // 이미지 URL 설정
        Image mainImage = product.getMainImage();
        if(mainImage == null){
            dto.setSubImageUrl(imageService.getImageUrls(product.getSubImages()).stream()
                    .filter(url -> !url.equals(dto.getMainImageUrl()))  // 메인 이미지 제외
                    .collect(Collectors.toList()));
            dto.setSubImageIds(product.getSubImages().stream().map(Image::getId)
                    .collect(Collectors.toList()));
        }else {
            dto.setMainImageUrl(imageService.getImageUrl(product.getMainImage()));
            dto.setSubImageUrl(imageService.getImageUrls(product.getSubImages()).stream()
                    .filter(url -> !url.equals(dto.getMainImageUrl()))  // 메인 이미지 제외
                    .collect(Collectors.toList()));
            dto.setSubImageIds(product.getSubImages().stream().map(Image::getId)
                    .filter(id -> !id.equals(product.getMainImage().getId())) //메인 이미지 제외
                    .collect(Collectors.toList()));
        }
        return dto;
    }

    public List<ProductForm> convertToFormList(List<Product> products) {
        return products.stream()
                .map(this::convertToForm)
                .collect(Collectors.toList());
    }

    public Page<ProductForm> convertToFormPage(Page<Product> productPage){
        return productPage.map(this::convertToForm);
    }

    public List<ProductDto> convertToDtoList(List<Product> products){
        return products.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }
}
