package com.devsuperior.dsdeliver.core.ports.inbound;

import java.util.List;

import com.devsuperior.dsdeliver.core.entities.Product;

public interface FindAllProducts {
    List<Product> findAllByOrderByNameAsc();
}
