package com.devsuperior.dsdeliver.core.ports;

import java.util.Optional;

import com.devsuperior.dsdeliver.core.entities.Product;

public interface FindProductByIdDao {
    Optional<Product> findById(Long id);
}
