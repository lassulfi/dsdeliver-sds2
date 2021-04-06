package com.devsuperior.dsdeliver.core.ports.inbound;

import com.devsuperior.dsdeliver.core.entities.Product;

public interface CreateNewProduct {
    Product create(String name, Double price, String description, String imageUri);
}
