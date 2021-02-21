package com.devsuperior.dsdeliver.core.usecases;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.devsuperior.dsdeliver.core.entities.Product;
import com.devsuperior.dsdeliver.core.exceptions.GenericException;
import com.devsuperior.dsdeliver.core.ports.CreateProductDao;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CreateNewProductTest {
    
    private static final String PRODUCT_NAME = "Product";
    private static final String PRODUCT_DESCRIPTION = "Product description";
    private static final String PRODUCT_URI = "https://imageUri.com";
    private static final Double PRODUCT_PRICE = 10.00;
    private static final Product PRODUCT = new Product(1L, PRODUCT_NAME, PRODUCT_PRICE, PRODUCT_DESCRIPTION, PRODUCT_URI);
    
    private Product obj = new Product(null, PRODUCT_NAME, PRODUCT_PRICE, PRODUCT_DESCRIPTION, PRODUCT_URI);
    
    private CreateProductDao createProductDaoMock;
    private CreateNewProduct createNewProductUseCase;

    @Before
    public void setup() {
        createProductDaoMock = mock(CreateProductDao.class);
        createNewProductUseCase = new CreateNewProduct(createProductDaoMock);
    }

    @Test
    public void shouldCreateNewProduct() {
        when(createProductDaoMock.create(obj)).thenReturn(PRODUCT);
        obj = createNewProductUseCase.create(PRODUCT_NAME, PRODUCT_PRICE, PRODUCT_DESCRIPTION, PRODUCT_URI);
        assertEquals(PRODUCT, obj);
    }

    @Test(expected = GenericException.class)
    public void shouldThrowGenericException() {
        when(createProductDaoMock.create(obj)).thenThrow(new RuntimeException("Generic Exception"));
        obj = createNewProductUseCase.create(PRODUCT_NAME, PRODUCT_PRICE, PRODUCT_DESCRIPTION, PRODUCT_URI);
    }
}
