package com.devsuperior.dsdeliver.core.usecases;

import java.util.List;

import com.devsuperior.dsdeliver.core.entities.Product;
import com.devsuperior.dsdeliver.core.exceptions.GenericException;
import com.devsuperior.dsdeliver.core.ports.FindAllProductsDao;

public class FindAllProducts {
    
    private FindAllProductsDao findAllProductsDao;

    public FindAllProducts(FindAllProductsDao findAllProductsDao) {
        this.findAllProductsDao = findAllProductsDao;
    }

    public List<Product> findAllByOrderByNameAsc() {
        try {
            return findAllProductsDao.findAllByOrderByNameAsc();
        } catch (Exception e) {
            throw new GenericException("Unexpected Exception");
        }
    }
}
