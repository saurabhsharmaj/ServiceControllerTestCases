package com.example.easynotes;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.easynotes.exception.ProductAlreadyExistsException;
import com.example.easynotes.model.Product;
import com.example.easynotes.repository.ProductRepository;
import com.example.easynotes.service.ProductServiceImpl;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

//handles business logic
    //unit testing so mocking
    @Mock
    private ProductRepository productRepository;

    @Autowired
    @InjectMocks
    private ProductServiceImpl productService;
    private Product product1;
    private Product product2;
    List<Product> productList;

    @BeforeEach
    public void setUp() {
        productList = new ArrayList<>();
        product1 = new Product(1, "bread",20);
        product2 = new Product(2, "jam",200);
        productList.add(product1);
        productList.add(product2);
    }

    @AfterEach
    public void tearDown() {
        product1 = product2 = null;
        productList = null;
    }

    @Test
    void givenProductToAddShouldReturnAddedProduct() throws ProductAlreadyExistsException {

        //stubbing
        when(productRepository.save(any())).thenReturn(product1);
        productService.addProduct(product1);
       verify(productRepository,times(1)).save(any());

    }

    @Test
    public void GivenGetAllUsersShouldReturnListOfAllUsers(){
    productRepository.save(product1);
        //stubbing mock to return specific data
        when(productRepository.findAll()).thenReturn(productList);
        List<Product> productList1 =productService.getAllProducts();
        assertEquals(productList1,productList);
        verify(productRepository,times(1)).save(product1);
        verify(productRepository,times(1)).findAll();
    }

    @Test
    public void givenIdThenShouldReturnProductOfThatId() {

        Mockito.when(productRepository.findById(1)).thenReturn(Optional.ofNullable(product1));
        assertThat(productService.getProductByid(product1.getId())).isEqualTo(product1);
    }


}