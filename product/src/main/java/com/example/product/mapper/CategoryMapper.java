package com.example.product.mapper;

import org.springframework.stereotype.Component;

import com.example.product.dto.CategoryRequest;
import com.example.product.model.Categorys;

@Component
public class CategoryMapper {


    public Categorys toCategory(CategoryRequest request){
        return  Categorys.builder()
                    .name(request.name())
                    .description(request.description())
                    .build();
    }
}
