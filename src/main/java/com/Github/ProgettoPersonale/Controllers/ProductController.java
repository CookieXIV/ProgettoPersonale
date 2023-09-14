package com.Github.ProgettoPersonale.Controllers;


import com.Github.ProgettoPersonale.DTO.ProductDTO;
import com.Github.ProgettoPersonale.Entities.Product;
import com.Github.ProgettoPersonale.Repositories.ProductRepo;
import com.Github.ProgettoPersonale.Services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/prod")
public class ProductController {

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private ProductService productService;

    @GetMapping("/all")
    public List<Product> getProdList(){
        return productService.getAllProd();
    }

    @GetMapping("/findByName")
    public ResponseEntity getProdByName(@RequestParam String name){
        try {
            List<Product> prod = productService.getProdByName(name);
            if (prod.size() == 1) {
                return ResponseEntity.ok(prod.get(0));
            }
            return ResponseEntity.ok(prod);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/save")
    public ResponseEntity saveProd(@RequestBody ProductDTO productDTO) throws Exception{
        productService.saveProd(productDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete")
    public String deleteProdById(@RequestParam long id){
        productService.deleteProd(id);
        return "The product with ID: " +id+ " has been deleted successfully";
    }

    @PutMapping("/update/id")
    public ResponseEntity<?> updateProd(@PathVariable long id, @RequestBody ProductDTO productDTO){
        try {
            Product p = productService.updateProd(id, productDTO);
            return ResponseEntity.ok(p);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
