package com.example.testproject.controller;

import com.example.testproject.dto.request.ProductAddDTO;
import com.example.testproject.dto.request.ProductUpdateDTO;
import com.example.testproject.model.Product;
import com.example.testproject.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@Validated
public class ProductController {

    private final ProductService service;

    @Autowired
    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping("/product/{name}")
    public ResponseEntity<?> getProductsByName(@Size(min = 1) @PathVariable String name) {
        List<Product> products = service.findProductByName(name);
        if (!products.isEmpty()) {
            return new ResponseEntity<>(products, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/product/")
    public ResponseEntity<?> addProduct(@Valid @RequestBody ProductAddDTO product) {
        return new ResponseEntity<>(service.addProduct(product), HttpStatus.OK);
    }

    @DeleteMapping("/product/")
    public ResponseEntity<?> deleteProductsById(@Min(0) @NotNull @PathParam("id") Integer id) {
        if (service.deleteProductById(id)) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/product/")
    public ResponseEntity<?> updateProduct(@Valid @RequestBody ProductUpdateDTO product) {
        if (service.updateProductDescriptionById(product)) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
