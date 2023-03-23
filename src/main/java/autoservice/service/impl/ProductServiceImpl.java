package autoservice.service.impl;

import autoservice.model.Product;
import autoservice.repository.ProductRepository;
import autoservice.service.ProductService;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product saveOrUpdate(Product entity) {
        return productRepository.save(entity);
    }

    @Override
    public Product get(Long id) {
        return productRepository.getById(id);
    }
}
