package com.devsuperior.dsdeliver.core.usecases;

import java.util.List;

import com.devsuperior.dsdeliver.core.entities.Product;
import com.devsuperior.dsdeliver.core.ports.FindAllProductsDao;

public class FindAllProducts {
    
    private FindAllProductsDao findAllProductsDao;

    public FindAllProducts(FindAllProductsDao findAllProductsDao) {
        this.findAllProductsDao = findAllProductsDao;
    }

    public List<Product> findAllByOrderByNameAsc() {
        return findAllProductsDao.findAllByOrderByNameAsc();
    }
}
