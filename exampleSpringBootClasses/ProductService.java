package codeexamples.springboot;

import java.util.Collection;
import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class ProductService {

    private final InventoryRepository inventoryRepository;

    public Product save(Product product) {
        return inventoryRepository.save(product);
    }

    public Collection<Product> getAllProducts() {
        return inventoryRepository.findAll();
    }

    public Optional<Product> findById(Integer id) {
        return inventoryRepository.findById(id);
    }
}
