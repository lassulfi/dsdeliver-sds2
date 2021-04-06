package com.devsuperior.dsdeliver.core.ports.outbound;
import com.devsuperior.dsdeliver.core.entities.Product;

public interface CreateProductDao {
    Product create(Product product);    
}
