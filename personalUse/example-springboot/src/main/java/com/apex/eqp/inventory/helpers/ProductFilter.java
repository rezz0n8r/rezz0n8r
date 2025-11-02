package com.apex.eqp.inventory.helpers;

import com.apex.eqp.inventory.entities.Product;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ProductFilter {

    private final Set<String> recalledProducts;

    public ProductFilter(Set<String> recalledProducts) {
        this.recalledProducts = recalledProducts;
    }

    public List<Product> removeRecalledFrom(Collection<Product> allProduct) {
        return allProduct.stream().filter(product -> 
        	 filterByName(product)
        	).collect(Collectors.toList()
        			);
    }

    private boolean filterByName(Product product) {
        return (!(recalledProducts.contains(product.getName())));
    }
}
