package codeexamples.springboot;

import lombok.RequiredArgsConstructor;

import org.ambulocetus.example.entities.Product;
import org.ambulocetus.example.entities.RecalledProduct;
import org.ambulocetus.example.helpers.ProductFilter;
import org.ambulocetus.example.repositories.InventoryRepository;
import org.ambulocetus.example.repositories.RecalledProductRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.function.Predicate.not;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final InventoryRepository inventoryRepository;
    private final RecalledProductRepository recalledProductRepository;

    @Transactional
    public Product save(Product product) {
        return inventoryRepository.save(product);
    }

    public Collection<Product> getAllProduct() {
    	List<RecalledProduct>recalledProds = recalledProductRepository.findAll();
        Set<String> recalledNames = new HashSet<>();
    	for(RecalledProduct recalledProd: recalledProds) {
        	recalledNames.add(recalledProd.getName());
        }
    	ProductFilter filter = new ProductFilter(recalledNames);
        return filter.removeRecalledFrom(inventoryRepository.findAll());
    }

    public Optional<Product> findById(Integer id) {
        return inventoryRepository.findById(id);
    }
}
