package com.shop.Controller.Product;

import com.shop.Entity.Product.ProCategory;
import com.shop.Service.Product.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productCategories")
public class ProductCategoryController {
    @Autowired
    private ProductCategoryService productCategoryService;

    @GetMapping("/getAll")
    public List<ProCategory> getAllProduct() {
        return productCategoryService.getAllProductCategory();
    }

    @GetMapping("/get/{id}")
    public ProCategory getProduct(@PathVariable Long id) {
        return productCategoryService.getProductCategory(id);
    }

    @PostMapping("/add")
    public void addProduct(@RequestBody ProCategory productCategory) {
        productCategoryService.addProductCategory(productCategory);
    }

    @PutMapping("/update")
    public void updateProduct(@RequestBody ProCategory productCategory) {
        productCategoryService.updateProductCategory(productCategory);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productCategoryService.deleteProductCategory(id);
    }
}
