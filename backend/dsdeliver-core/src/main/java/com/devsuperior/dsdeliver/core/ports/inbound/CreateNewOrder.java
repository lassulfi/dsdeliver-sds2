package com.devsuperior.dsdeliver.core.ports.inbound;

import java.util.Set;

import com.devsuperior.dsdeliver.core.entities.Order;
import com.devsuperior.dsdeliver.core.entities.Product;
import com.devsuperior.dsdeliver.core.exceptions.ProductNotFoundException;

public interface CreateNewOrder {
    Order create(String address, Double latitude, Double longitude, Set<Product> products) throws ProductNotFoundException;
}
