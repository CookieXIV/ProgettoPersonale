package com.Github.ProgettoPersonale.Repositories;

import com.Github.ProgettoPersonale.Entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {

    List<Product> findByNameIgnoreCase(String name);
}
