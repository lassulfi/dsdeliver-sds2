package com.devsuperior.dsdeliver.core.usecases;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import com.devsuperior.dsdeliver.core.entities.Order;
import com.devsuperior.dsdeliver.core.entities.Product;
import com.devsuperior.dsdeliver.core.exceptions.GenericException;
import com.devsuperior.dsdeliver.core.exceptions.ProductNotFoundException;
import com.devsuperior.dsdeliver.core.ports.inbound.CreateNewOrder;
import com.devsuperior.dsdeliver.core.ports.outbound.CreateNewOrderDao;
import com.devsuperior.dsdeliver.core.ports.outbound.FindProductByIdDao;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CreateNewOrderTest {

    private static final Set<Product> ORDER_PRODUCTS = new HashSet<>(Arrays.asList(new Product(1L, "PRODUCT 1", 10.00, "Product 1", "http://image1.com")));
    private static final Product PRODUCT = new Product(1L, "PRODUCT 1", 10.00, "Product 1", "http://image1.com");
    private static final Long PRODUCT_ID = 1L;
    private static final String ADDRESS = "MY ADDRESS";
    private static final Double LATITUDE = 10.00;
    private static final Double LONGITUDE = 10.00;
    private static final Order ORDER = new Order(1L, ADDRESS, LATITUDE, LONGITUDE);
    private Order order = new Order(null, ADDRESS, LATITUDE, LONGITUDE);
    private static final Long INVALID_ID = 2L;
    private static final Set<Product> INVALID_ORDER_PRODUCTS = new HashSet<>(Arrays.asList(new Product(2L, "PRODUCT 2", 10.00, "Product 2", "http://image2.com")));

    private CreateNewOrder createNewOrderUseCase;
    private FindProductByIdDao findProductByIdMock;
    private CreateNewOrderDao createNewOrderMock;

    @Before
    public void setup() {
        findProductByIdMock = mock(FindProductByIdDao.class);
        createNewOrderMock = mock(CreateNewOrderDao.class);
        createNewOrderUseCase = new CreateNewOrderImpl(findProductByIdMock, createNewOrderMock);
    }

    @Test
    public void shouldCreateNewOrder() throws ProductNotFoundException {
        when(findProductByIdMock.findById(PRODUCT_ID)).thenReturn(Optional.of(PRODUCT));
        when(createNewOrderMock.create(order)).thenReturn(ORDER);

        order = createNewOrderUseCase.create(ADDRESS, LATITUDE, LONGITUDE, ORDER_PRODUCTS);
        
        assertEquals(ORDER, order);
    }
    
    @Test(expected = ProductNotFoundException.class)
    public void shouldThrowProductNotFoundException() throws ProductNotFoundException {        
        when(findProductByIdMock.findById(INVALID_ID)).thenReturn(Optional.empty());
        
        order = createNewOrderUseCase.create(ADDRESS, LATITUDE, LONGITUDE, INVALID_ORDER_PRODUCTS);
    }
    
    @Test(expected = GenericException.class)
    public void shouldThrowGenericException() throws ProductNotFoundException {
        when(findProductByIdMock.findById(PRODUCT_ID)).thenThrow(new RuntimeException("Generic Exception"));

        order = createNewOrderUseCase.create(ADDRESS, LATITUDE, LONGITUDE, ORDER_PRODUCTS);
    }
}
