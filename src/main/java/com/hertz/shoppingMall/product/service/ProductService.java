package com.hertz.shoppingMall.product.service;

import com.hertz.shoppingMall.member.model.Member;
import com.hertz.shoppingMall.product.model.Product;
import com.hertz.shoppingMall.product.repository.ProductRepository;
import com.hertz.shoppingMall.utils.exception.image.component.SaveImageUtil;
import com.hertz.shoppingMall.utils.exception.image.model.ImageType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductService {

    private final ProductRepository productRepository;

    private final SaveImageUtil saveImageUtil;

    @Transactional  // 변경감지(dirty checking)
    public void saveProduct(Product product){
        //java.lang.IllegalStateException: 이미 존재하는 회원입니다. 에러 발생 시 처리 필요
        productRepository.save(product);
    }

    @Transactional  // 변경감지(dirty checking)
    public void updateProduct(Long productId, String name, int price, int stockQuantity, String description, Long memberId){
        Product findProduct = productRepository.findById(productId).orElse(null);
        if(findProduct != null){
            Member modifiedBy = new Member();
            modifiedBy.setId(memberId);
            findProduct.setPrice(price);
            findProduct.setName(name);
            findProduct.setStockQuantity(stockQuantity);
            findProduct.setDescription(description);
            findProduct.setModifiedBy(modifiedBy);
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
            saveImageUtil.saveImage(mainImage, ImageType.PRODUCT, product.getId(), true);
        }

        // 3️⃣ 서브 이미지 저장
        if (subImages != null) {
            for (MultipartFile subImage : subImages) {
                if (!subImage.isEmpty()) {
                    saveImageUtil.saveImage(subImage, ImageType.PRODUCT, product.getId(), false);
                }
            }
        }
    }

}
