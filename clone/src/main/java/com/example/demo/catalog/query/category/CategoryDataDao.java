package com.example.demo.catalog.query.category;

import com.example.demo.catalog.common.domain.category.CategoryId;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

public interface CategoryDataDao extends Repository<CategoryData, CategoryId> {
    Optional<CategoryData> findById(CategoryId id);

    List<CategoryData> findAll();
}
