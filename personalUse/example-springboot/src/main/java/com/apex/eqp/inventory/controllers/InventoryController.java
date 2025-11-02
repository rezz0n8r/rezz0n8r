package com.apex.eqp.inventory.controllers;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.apex.eqp.inventory.entities.Product;
import com.apex.eqp.inventory.services.ProductService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping(value = "api/inventory/product")
public class InventoryController {

    private final ProductService productService;
    
    /**
     *  HERE IS HOW YOU WIRE APPLICATION.PROPERTY VALUES INTO THE APPLICATION:
     */
    @Value("${custom.app.values.label}")
    private String injectedProp;
    
    /**
     * These are keys in the file: application.properties
     */
    @Value("${custom.app.values.count}")
    int count;

    @GetMapping
    public ResponseEntity<Collection<Product>> getAllProducts() {
    	log.info("Got the property-injected value: ");
    	log.info(injectedProp); //This is Working (being logged)
    	log.info("and the 'count' property is wired in as: ");
    	log.info(""+count);
        return ResponseEntity.ok(productService.getAllProduct());
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        return ResponseEntity.ok(productService.save(product));
    }

    @GetMapping("/{id}")
    ResponseEntity<Product> findProduct(@PathVariable Integer id) {
        Optional<Product> byId = productService.findById(id);

        return byId.map(ResponseEntity::ok).orElse(null);
    }
    
    @PutMapping
    ResponseEntity<Product> updateProduct(@RequestBody Product modifiedProduct) {
    	
    	Product updatedProdFromDB = productService.updateProduct(modifiedProduct);
    	if(updatedProdFromDB == null) {
    		return ResponseEntity.internalServerError(null);
    	}
    }
}
