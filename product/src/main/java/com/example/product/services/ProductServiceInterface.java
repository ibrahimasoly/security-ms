package com.example.product.services;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.example.product.dto.CategoryRequest;
import com.example.product.dto.ImageRequest;
import com.example.product.dto.ImageResponse;
import com.example.product.dto.ProductRequest;
import com.example.product.dto.ProductResponse;


public interface ProductServiceInterface {
    Long creatCategory(CategoryRequest request);
    Long creatProduct(ProductRequest product);
    void createImage(MultipartFile image, ImageRequest request) throws IOException;
    ImageResponse imageById(Long id);
    List<ProductResponse> listProduct();
    ProductResponse productById(Long id);
    List<ImageResponse> listImage(Long id);
}
