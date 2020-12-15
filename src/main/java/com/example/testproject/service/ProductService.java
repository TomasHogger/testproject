package com.example.testproject.service;

import com.example.testproject.dto.request.ProductAddDTO;
import com.example.testproject.dto.request.ProductUpdateDTO;
import com.example.testproject.model.Product;
import com.example.testproject.repository.ProductRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository repository;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }


    public List<Product> findProductByName(String name) {
        if (name.isEmpty()) {
            return new ArrayList<>();
        }
        return repository.findProductsByName(name);
    }

    public Integer addProduct(ProductAddDTO productAddDTO) {
        Product product = objectMapper.convertValue(productAddDTO, Product.class);
        repository.save(product);
        return product.getId();
    }

    public boolean deleteProductById(Integer id) {
        if (repository.findById(id).isEmpty()) {
            return false;
        }
        repository.deleteById(id);
        return true;
    }

    public boolean updateProductDescriptionById(ProductUpdateDTO productUpdateDTO) {
        Optional<Product> product = repository.findById(productUpdateDTO.getId());
        if (product.isEmpty()) {
            return false;
        }
        product.get().setDescription(productUpdateDTO.getDescription());
        repository.save(product.get());
        return true;
    }
}
