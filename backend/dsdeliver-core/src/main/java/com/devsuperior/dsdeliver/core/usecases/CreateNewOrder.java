package com.devsuperior.dsdeliver.core.usecases;

import java.time.Instant;
import java.util.Set;

import com.devsuperior.dsdeliver.core.entities.Order;
import com.devsuperior.dsdeliver.core.entities.OrderStatus;
import com.devsuperior.dsdeliver.core.entities.Product;
import com.devsuperior.dsdeliver.core.exceptions.ProductNotFoundException;
import com.devsuperior.dsdeliver.core.ports.CreateNewOrderDao;
import com.devsuperior.dsdeliver.core.ports.FindProductByIdDao;

public class CreateNewOrder {
    
    private FindProductByIdDao findProductByIdDao;
    private CreateNewOrderDao createNewOrderDao;

    public CreateNewOrder(FindProductByIdDao findProductByIdDao, CreateNewOrderDao createNewOrderDao) {
        this.findProductByIdDao = findProductByIdDao;
        this.createNewOrderDao = createNewOrderDao;
    }

    public Order create(String address, Double latitude, Double longitude, Set<Product> products) {
        Order order = new Order(null, address, latitude, longitude);
        for(Product p : products) {
            Product product = findProductByIdDao.findById(p.getId()).orElseThrow(() -> new ProductNotFoundException("Product not found. Id: " + p.getId()));
            order.getProducts().add(product);
        }
        return createNewOrderDao.create(order);
    }
}
