package com.devsuperior.dsdeliver.core.ports.outbound;

import com.devsuperior.dsdeliver.core.entities.Order;

public interface CreateNewOrderDao {
    Order create(Order order);
}
