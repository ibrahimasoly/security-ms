package com.example.product.services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.product.dto.CategoryRequest;
import com.example.product.dto.ImageRequest;
import com.example.product.dto.ImageResponse;
import com.example.product.dto.ProductRequest;
import com.example.product.dto.ProductResponse;
import com.example.product.exception.EntityNotfoundExecption;
import com.example.product.mapper.CategoryMapper;
import com.example.product.mapper.ImageMapper;
import com.example.product.mapper.ProductMapper;
import com.example.product.model.Categorys;
import com.example.product.model.Image;
import com.example.product.model.Product;
import com.example.product.repository.CategoryRepository;
import com.example.product.repository.ImageRepository;
import com.example.product.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductServiceImplement implements  ProductServiceInterface {
    private final ProductRepository  productRepository;
    private final ImageRepository imageRepository;
    private final CategoryRepository categoryRepository;
    private final ProductMapper productMapper;
    private final CategoryMapper categoryMapper;
    private final ImageMapper imageMapper;
    public static final String UPLOAD_DIR ="C:\\IC\\imagefile";

    @Override
    public Long creatCategory(CategoryRequest request) {
        Categorys categorys = categoryMapper.toCategory(request);
        System.out.println(categorys);
        return categoryRepository.save(categorys).getCategoryId();
    }

    @Override
    public Long creatProduct(ProductRequest product) {
        Product product1 = productMapper.toProduct(product);
        return productRepository.save(product1).getProductId();
    }

    @Override
    public void createImage(MultipartFile image, ImageRequest request) throws IOException {
       ImageRequest imageRequest = new ImageRequest(
        image.getOriginalFilename(), 
        request.productId()
        );

        Path filPath = Paths.get(UPLOAD_DIR, image.getOriginalFilename());
        Files.copy(image.getInputStream(), filPath);

        Image image1 = imageMapper.toImage(imageRequest);
        imageRepository.save(image1);
        
    }

    @Override
    public ImageResponse imageById(Long id) {
        return imageRepository.findById(id).map(imageMapper::froImage).orElseThrow();
    }

    @Override
    public List<ProductResponse> listProduct() {
        return productRepository.findAll()
                    .stream()
                    .map(productMapper::FromProductToProductResponse)
                    .toList();
    }

    @Override
    public ProductResponse productById(Long id) {
       return productRepository.findById(id)
                        .map(productMapper::FromProductToProductResponse)
                        .orElseThrow(()-> new EntityNotfoundExecption("Pas de produit avec cet Id"+id)
                         );
    }

    @Override
    public List<ImageResponse> listImage(Long id) {
        return imageRepository.findAllByProductId(id).stream().map(imageMapper::froImage).toList();
    }

    

   

   
    
}
