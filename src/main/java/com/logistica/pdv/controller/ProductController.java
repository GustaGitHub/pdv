package com.logistica.pdv.controller;

import com.logistica.pdv.DTO.NewProductDTO;
import com.logistica.pdv.entity.Product;
import com.logistica.pdv.entity.Seller;
import com.logistica.pdv.repository.IProductRepository;
import com.logistica.pdv.repository.ISellerRepository;
import com.logistica.pdv.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService _productService;

    @GetMapping()
    public ResponseEntity findAllProducts(){
        try{
            return new ResponseEntity<>(_productService.getAllProducts(), HttpStatus.OK);
        }
        catch (Exception err){
            return new ResponseEntity<>(err.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("{id}")
    public ResponseEntity findProductById(@PathVariable long id){
        try{
           var product = _productService.getProductById(id);

           if (product != null)
               return new ResponseEntity<>(product, HttpStatus.OK);

            return new ResponseEntity<>("Product not found", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        catch (Exception err){
            return new ResponseEntity<>(err.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping()
    public ResponseEntity createProduct(@RequestBody NewProductDTO productDTO){
        try{

            if(productDTO.getSellerID() != null && productDTO.getSellerID() > 0){
                return new ResponseEntity<>(_productService.createProduct(productDTO), HttpStatus.OK);
            }
            else{
                throw new Exception("Seller not informed");
            }
        }
        catch (Exception err){
            return new ResponseEntity<>(err.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping()
    public ResponseEntity editProduct(@RequestBody Product product) {
        try {
            var editedProduct = _productService.editProduct(product);

            if(editedProduct != null)
                return new ResponseEntity(editedProduct, HttpStatus.OK);

            return new ResponseEntity<>("Product not found", HttpStatus.INTERNAL_SERVER_ERROR);

        } catch (Exception err) {
            return new ResponseEntity<>(err.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteProduct(@PathVariable long id){
        try{
            var deletedProduct = _productService.deleteProduct(id);

            if(deletedProduct != null)
              return new ResponseEntity<>(deletedProduct , HttpStatus.OK);

            return new ResponseEntity<>("Product not found", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        catch (Exception err){
            return new ResponseEntity<>(err.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
