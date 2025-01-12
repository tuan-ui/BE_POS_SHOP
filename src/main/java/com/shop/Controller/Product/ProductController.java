package com.shop.Controller.Product;

import com.shop.Entity.Product.Product;
import com.shop.Service.Product.DTO.ProductDTO;
import com.shop.Service.Product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/getAll")
    public List<Product> getAllProduct() {
        return productService.getAllProduct();
    }

    @GetMapping("/get/{id}")
    public Product getProduct(@PathVariable Long id) {
        return productService.getProduct(id);
    }

    @PostMapping("/add")
    public void addProduct(@RequestBody Product product) {
        productService.addProduct(product);
    }

    @PutMapping("/update")
    public void updateProduct(@RequestBody Product product) {
        productService.updateProduct(product);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
    }

    @GetMapping("/getDetailedProducts")
    public ResponseEntity<Page<ProductDTO>> getDetailedProducts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String[] sort) {

        List<Sort.Order> orders = new ArrayList<>();
        for (String sortOrder : sort) {
            String[] sortSplit = sortOrder.split(",");
            orders.add(new Sort.Order(Sort.Direction.fromString(sortSplit[1]), sortSplit[0]));
        }
        Pageable pageable = PageRequest.of(page, size, Sort.by(orders));

        Page<ProductDTO> productPage = productService.getDetailedProducts(pageable);

        return ResponseEntity.ok(productPage);
    }
}
