package com.apex.eqp.inventory;

import com.apex.eqp.inventory.controllers.InventoryController;
import com.apex.eqp.inventory.entities.RecalledProduct;
import com.apex.eqp.inventory.repositories.InventoryRepository;
import com.apex.eqp.inventory.repositories.RecalledProductRepository;
import com.apex.eqp.inventory.services.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Collections;
import java.util.List;

@ExtendWith(MockitoExtension.class)
@RequiredArgsConstructor
class InventoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @InjectMocks
    InventoryController inventoryController;
    
    @InjectMocks
    RecalledProductRepository repository;

    @Mock
    ProductService productService;

    @BeforeEach
    public void before() {
        mockMvc = MockMvcBuilders
                .standaloneSetup(inventoryController)
                .build();
        List<RecalledProduct> testProdsList = Collections.<RecalledProduct> emptyList();
        System.out.println("The testProdsList is: "+testProdsList);
        
        Mockito.when(repository.findAll()).thenReturn(testProdsList);
    }

    @SneakyThrows
    @Test
    void shouldCallController() {
        mockMvc.perform(
                get("/api/inventory/product")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());
    }

}
