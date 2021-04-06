package com.devsuperior.dsdeliver.core.usecases;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import com.devsuperior.dsdeliver.core.entities.Product;
import com.devsuperior.dsdeliver.core.exceptions.GenericException;
import com.devsuperior.dsdeliver.core.ports.inbound.FindAllProducts;
import com.devsuperior.dsdeliver.core.ports.outbound.FindAllProductsDao;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class FindAllProductsTest {

    private static final List<Product> PRODUCTS_LIST = Arrays.asList(
            new Product(1L, "PRODUCT 1", 10.00, "Product 1", "http://image1.com"),
            new Product(2L, "PRODUCT 2", 10.00, "Product 2", "http://image2.com"),
            new Product(3L, "PRODUCT 3", 10.00, "Product 3", "http://image3.com"));

    private FindAllProducts findAllProductsUsecase;
    private FindAllProductsDao findAllProductsMock;

    @Before
    public void setup() {
        findAllProductsMock = mock(FindAllProductsDao.class);
        findAllProductsUsecase = new FindAllProductsImpl(findAllProductsMock);
    }

    @Test
    public void shouldReturnAListOfProducts() {
        when(findAllProductsMock.findAllByOrderByNameAsc()).thenReturn(PRODUCTS_LIST);

        List<Product> products = findAllProductsUsecase.findAllByOrderByNameAsc();
        assertThat(products, is(PRODUCTS_LIST));
        assertEquals(3, products.size());
    }

    @Test(expected = GenericException.class)
    public void shouldThrowGenericException() {
        when(findAllProductsMock.findAllByOrderByNameAsc()).thenThrow(new RuntimeException("Generic Exception"));
        findAllProductsUsecase.findAllByOrderByNameAsc();
    }
}
