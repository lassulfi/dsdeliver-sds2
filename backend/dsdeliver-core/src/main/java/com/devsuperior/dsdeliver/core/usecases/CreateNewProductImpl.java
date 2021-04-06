package com.devsuperior.dsdeliver.core.usecases;

import com.devsuperior.dsdeliver.core.entities.Product;
import com.devsuperior.dsdeliver.core.exceptions.GenericException;
import com.devsuperior.dsdeliver.core.ports.inbound.CreateNewProduct;
import com.devsuperior.dsdeliver.core.ports.outbound.CreateProductDao;

public class CreateNewProductImpl implements CreateNewProduct {

    private CreateProductDao createProductDao;

    public CreateNewProductImpl(CreateProductDao createProductDao) {
        this.createProductDao = createProductDao;
    }
    
    public Product create(String name, Double price, String description, String imageUri) {
        try {
            Product obj = new Product(null, name, price, description, imageUri);
            obj = createProductDao.create(obj);
            return obj;
        } catch (Exception e) {
            throw new GenericException("Unexpected exception");
        }
    }
}
