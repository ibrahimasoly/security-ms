package com.example.product.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import com.example.product.dto.ProductRequest;
import com.example.product.dto.ProductResponse;
import com.example.product.model.Categorys;
import com.example.product.model.Image;
import com.example.product.model.Product;
import com.example.product.repository.ImageRepository;


@Component
public class ProductMapper {
     private final  ImageRepository imageRepository;
     private final ImageMapper mapper;

    public ProductMapper(ImageRepository imageRepository, ImageMapper mapper) {
        this.imageRepository = imageRepository;
        this.mapper = mapper;
    }


    public Product toProduct(ProductRequest productRequest){
        return  Product.builder()
                    .productId(productRequest.productId())
                    .name(productRequest.name())
                    .description(productRequest.description())
                    .price(productRequest.price())
                    .category(Categorys.builder().categoryId(productRequest.categoryId()).build())
                    .build();
    }
    public ProductRequest FromProduct(Product product){
        return  new ProductRequest(
            product.getProductId(), 
            product.getName(), 
            product.getDescription(), 
            product.getPrice(), 
            product.getQuantity(), 
            product.getCategory().getCategoryId()
        );
    }
    public ProductResponse FromProductToProductResponse(Product product){
        List<Image> image = imageRepository.findAllByProductId(product.getProductId());
        return  new ProductResponse(
            product.getName(), 
            product.getDescription(), 
            product.getPrice(), 
            product.getQuantity(), 
            product.getCategory().getCategoryId(),
            image.stream().map(mapper::froImage).toList()
        );
    }
    
}
