package com.example.product.web;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.product.dto.CategoryRequest;
import com.example.product.dto.ImageRequest;
import com.example.product.dto.ImageResponse;
import com.example.product.dto.ProductRequest;
import com.example.product.dto.ProductResponse;
import com.example.product.services.ProductServiceImplement;
import com.example.product.services.ProductServiceInterface;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductControler {
    private final ProductServiceInterface service;

    @PostMapping("/category")
    public ResponseEntity<Long> creatCategory(@RequestBody CategoryRequest category){
        return ResponseEntity.ok(service.creatCategory(category));
    }

    @PostMapping
    public ResponseEntity<Long> creatProduct(@RequestBody ProductRequest product){
        return ResponseEntity.ok(service.creatProduct(product));
    }
    @PostMapping("/image")
    public void creatImage(@RequestParam("file") MultipartFile image, ImageRequest request){
        try {
            service.createImage(image, request);
        } catch (IOException ex) {
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Resource> imageById(@PathVariable Long id) throws MalformedURLException{
        ImageResponse image = service.imageById(id);
        Path file = Paths.get(ProductServiceImplement.UPLOAD_DIR).resolve(image.filename()).normalize();
        Resource resource = new UrlResource(file.toUri());
        return ResponseEntity.ok()
        .contentType(MediaType.IMAGE_JPEG)
        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment, filename =\""+resource.getFilename()+"\"")
        .body(resource);
    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> listProduct(){
        return ResponseEntity.ok(service.listProduct());
    }
    
}
