package com.example.product.mapper;

import org.springframework.stereotype.Component;

import com.example.product.dto.ImageRequest;
import com.example.product.dto.ImageResponse;
import com.example.product.model.Image;
import com.example.product.model.Product;


@Component
public class ImageMapper {

    public Image toImage(ImageRequest request){
        return Image.builder()
                .filename(request.filename())
                .product(Product.builder().productId(request.productId()).build())
                .build();
    }

    public Image toImageFromImageResponse(ImageResponse request){
        return Image.builder()
                .filename(request.filename())
                .product(Product.builder().productId(request.productId()).build())
                .build();
    }

    public ImageResponse froImage(Image image){
        return new ImageResponse(image.getImageId(), image.getFilename(), image.getProduct().getProductId());
    }
    
}
