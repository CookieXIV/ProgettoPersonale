package com.Github.ProgettoPersonale.Services;

import com.Github.ProgettoPersonale.DTO.ProductDTO;
import com.Github.ProgettoPersonale.Entities.Product;
import com.Github.ProgettoPersonale.Repositories.ProductRepo;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProductService {

    @Autowired
    private ProductRepo productRepo;

    public List<Product> getAllProd(){
        return productRepo.findAll();
    }

    public List<Product> getProdByName(String name) throws Exception{

        List<Product> prod = productRepo.findByNameIgnoreCase(name);
        if(prod.size() == 0 ) {
            throw new Exception("Can't find any product with name: " + name);
        }
        return prod;
    }

    public Product saveProd(ProductDTO productDTO) throws Exception{
        try{
            Product prod = new Product();
            prod.setName(productDTO.getName());
            prod.setPrice(productDTO.getPrice());
            return productRepo.save(prod);
        } catch (Exception e) {
            throw new Exception("I couldn't create the product, check if everything is alright!");
        }
    }

    public String deleteProd(long id){
        try { productRepo.deleteById(id);
            return "The product with id: " + id + " has been deleted correctly";
        } catch (Exception e) {
            return "I Couldn't find the product ID: " +id;
        }
    }

    public Product updateProd(long id, ProductDTO productDTO) throws Exception {
        try {
            Product prodToBeUpdated = productRepo.findById(id).get();

            prodToBeUpdated.setName(productDTO.getName());
            prodToBeUpdated.setPrice(productDTO.getPrice());

            return productRepo.save(prodToBeUpdated);

        } catch (Exception e) {
            throw new Exception("Cannot find Book with ID : "+id);
        }
    }
}