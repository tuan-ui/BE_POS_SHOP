package com.shop.Controller.Store;

import com.shop.Entity.Store.Store;
import com.shop.Service.Store.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/store")
public class StoreController {
    @Autowired
    private StoreService storeService;

    @GetMapping("/getAll")
    public List<Store> getAllStore() {
        return storeService.getAllStore();
    }

    @GetMapping("/get/{id}")
    public Store getStore(@PathVariable Long id) {
        return storeService.getStore(id);
    }

    @PostMapping("/add")
    public void addStore(@RequestBody Store store) {
        storeService.addStore(store);
    }

    @PutMapping("/update")
    public void updateStore(@RequestBody Store store) {
        storeService.updateStore(store);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteStore(@PathVariable Long id) {
        storeService.deleteStore(id);
    }
}
