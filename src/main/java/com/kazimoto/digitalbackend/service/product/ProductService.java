package com.kazimoto.digitalbackend.service.product;

import com.kazimoto.digitalbackend.dto.ProductDto;
import com.kazimoto.digitalbackend.entity.Product;
import com.kazimoto.digitalbackend.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

    public Product saveProduct(ProductDto productDto){
        Product product = new Product();
        product.setProductName(productDto.getProductName());
        product.setDescription(productDto.getDescription());

        return productRepository.save(product);
    }

    public Product getSingleProduct(String rowId){
        return productRepository.findByRowId(rowId)
                .orElseThrow(() -> new NoSuchElementException("No product with id " + rowId));
    }
}
