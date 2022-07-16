package com.example.demo.catalog.common.domain.product;

import org.springframework.data.repository.Repository;

import java.util.Optional;

public interface ProductRepository extends Repository<Product, ProductId> {
    void save(Product product);

    Optional<Product> findById(ProductId id);

    void flush();
}
