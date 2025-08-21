package com.example.coderwing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class InventoryController {

    @Autowired
    private InventoryRepo inventoryRepo;

    @GetMapping
    public String hello(){
        return "Hy this is Mirajuddin Sheikh... Machine Test --> " +
                "/all -> view all products " +
                "/add -> add new product" +
                "Delete Method + /{id} -> delete product" +
                "/update -> update product";
    }

    @PostMapping("/add")
    public ResponseEntity<?> addProduct(@RequestBody Inventory newProduct){
        inventoryRepo.save(newProduct);
        return ResponseEntity.ok("Product Added");
    }

    @PostMapping("/update")
    public ResponseEntity<?> updateProduct(@RequestBody Inventory product){
//        Inventory existingProduct = inventoryRepo.findById(product.getId()).orElseThrow();
//        assuming we are always providing a valid product
        inventoryRepo.save(product);
        return ResponseEntity.ok("Product Updated");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id){
        Inventory existing = inventoryRepo.findById(id).orElseThrow();
        inventoryRepo.delete(existing);
        return ResponseEntity.ok("Successfully Removed Product");
    }

    @GetMapping("/all")
    public ResponseEntity<?> allProducts(){
        return ResponseEntity.ok(inventoryRepo.findAll());
    }
}
