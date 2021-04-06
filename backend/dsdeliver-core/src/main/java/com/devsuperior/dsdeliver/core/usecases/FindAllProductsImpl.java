package com.devsuperior.dsdeliver.core.usecases;

import java.util.List;

import com.devsuperior.dsdeliver.core.entities.Product;
import com.devsuperior.dsdeliver.core.exceptions.GenericException;
import com.devsuperior.dsdeliver.core.ports.inbound.FindAllProducts;
import com.devsuperior.dsdeliver.core.ports.outbound.FindAllProductsDao;

public class FindAllProductsImpl implements FindAllProducts {
    
    private FindAllProductsDao findAllProductsDao;

    public FindAllProductsImpl(FindAllProductsDao findAllProductsDao) {
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
