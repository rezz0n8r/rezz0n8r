package codeexamples.springboot;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.ambulocetus.example.entities.Product;

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
