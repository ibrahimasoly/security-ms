package com.example.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.product.model.Categorys;

@Repository
public interface CategoryRepository extends JpaRepository<Categorys, Long>{
    
}
