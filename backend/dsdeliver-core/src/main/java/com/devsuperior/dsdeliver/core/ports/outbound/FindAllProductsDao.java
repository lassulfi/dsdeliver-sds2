package com.devsuperior.dsdeliver.core.ports.outbound;

import java.util.List;

import com.devsuperior.dsdeliver.core.entities.Product;

public interface FindAllProductsDao {
    List<Product> findAllByOrderByNameAsc();
}
