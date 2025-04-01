package com.hertz.shoppingMall.product.service;

import com.hertz.shoppingMall.member.model.Member;
import com.hertz.shoppingMall.product.dto.ProductForm;
import com.hertz.shoppingMall.product.model.Product;
import com.hertz.shoppingMall.product.repository.ProductRepository;
import com.hertz.shoppingMall.utils.exception.image.component.SaveImageUtil;
import com.hertz.shoppingMall.utils.exception.image.model.Image;
import com.hertz.shoppingMall.utils.exception.image.model.ImageType;
import com.hertz.shoppingMall.utils.exception.image.repository.ImageRepository;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductService {

    private final ProductRepository productRepository;

    private final SaveImageUtil saveImageUtil;

    private final ImageRepository imageRepository;

    @Transactional  // 변경감지(dirty checking)
    public void saveProduct(Product product){
        //java.lang.IllegalStateException: 이미 존재하는 회원입니다. 에러 발생 시 처리 필요
        productRepository.save(product);
    }

    @Transactional  // 변경감지(dirty checking)
    public void updateProduct(ProductForm form, Long memberId) throws IOException {
        Product findProduct = productRepository.findById(form.getId()).orElse(null);

        if(findProduct != null){

            Member modifiedBy = new Member();
            modifiedBy.setId(memberId);
            findProduct.setPrice(form.getPrice());
            findProduct.setName(form.getName());
            findProduct.setStockQuantity(form.getStockQuantity());
            findProduct.setDescription(form.getDescription());
            findProduct.setModifiedBy(modifiedBy);

            // 메인 이미지 처리
            if(form.getMainImage() != null && !form.getMainImage().isEmpty()) {
                if (findProduct.getMainImageUrl() != null) {
                    saveImageUtil.deleteImageFile(findProduct.getMainImage());
                }
                //새로운 메인 이미지 등록일 시
                if (findProduct.getMainImage() == null) {
                    Image saveImage = saveImageUtil.saveImage(new Image(), form.getMainImage(), ImageType.PRODUCT, findProduct.getId(), true);
                    imageRepository.save(saveImage);
                } else {    // 메인 이미지 수정 시
                    saveImageUtil.saveImage(findProduct.getMainImage(), form.getMainImage(), ImageType.PRODUCT, findProduct.getId(), true);
                }
            }

            // 서브 이미지 처리
            // 기존 서브 이미지 삭제 (deleteSubImages[i]가 true이면 삭제)
            if (form.getSubImageIds() != null && form.getDeleteSubImages() != null) {
                for (int i = 0; i < form.getSubImageIds().size(); i++) {
                    if (form.getDeleteSubImages().get(i)) {
                        Image subImage = imageRepository.findById(form.getSubImageIds().get(i)).orElse(null);
                        if (subImage != null) {
                            saveImageUtil.deleteImageFile(subImage); // 파일 삭제
                            imageRepository.delete(subImage); // DB 삭제
                        }
                    }
                }
            }

            // 새 서브 이미지 추가
            if(form.getSubImages() != null && !form.getSubImages().isEmpty()) {
                for(MultipartFile subImage : form.getSubImages()) {
                    if(subImage != null && !subImage.isEmpty()) {
                        Image saveImage = saveImageUtil.saveImage(new Image(), subImage, ImageType.PRODUCT, findProduct.getId(), false);
                        imageRepository.save(saveImage);
                    }
                }
            }
        }
    }

    public List<Product> getProductAll(){
        return productRepository.findAll();
    }

    public Product getProduct(Long productId){
        return productRepository.findById(productId).orElse(null);
    }

    public List<Product> getProductListBySeller(Long memberId){
        Member member = new Member();
        member.setId(memberId);
        return productRepository.findByCreatedBy(member);
    }


    public void saveProductWithImages(Product product, MultipartFile mainImage, List<MultipartFile> subImages) throws IOException {
        // 1️⃣ 상품 저장 (먼저 저장해야 ID를 사용할 수 있음)
        productRepository.save(product);

        // 2️⃣ 메인 이미지 저장
        if (mainImage != null && !mainImage.isEmpty()) {
            Image saveImage = saveImageUtil.saveImage(new Image(),mainImage, ImageType.PRODUCT, product.getId(), true);
            imageRepository.save(saveImage);
        }

        // 3️⃣ 서브 이미지 저장
        if (subImages != null) {
            for (MultipartFile subImage : subImages) {
                if (!subImage.isEmpty()) {
                    Image saveImage = saveImageUtil.saveImage(new Image(),subImage, ImageType.PRODUCT, product.getId(), false);
                    imageRepository.save(saveImage);
                }
            }
        }
    }


    public List<Product> getProductsByIds(List<Long> productIds) {
        return productRepository.findByIdIn(productIds);
    }
}
