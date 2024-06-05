package com.logistica.pdv.controller;

import com.logistica.pdv.DTO.EditProductDTO;
import com.logistica.pdv.DTO.NewProductDTO;
import com.logistica.pdv.entity.Product;
import com.logistica.pdv.entity.Seller;
import com.logistica.pdv.exceptions.NotFoundException;
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
           if (id > 0)
               return new ResponseEntity<>(_productService.getProductById(id), HttpStatus.OK);

            throw new IllegalArgumentException("Product not informed");
        }
        catch (IllegalArgumentException err) {
            return new ResponseEntity<>(err.getMessage(), HttpStatus.BAD_REQUEST);
        }catch (NotFoundException err) {
            return new ResponseEntity<>(err.getMessage(), HttpStatus.NOT_FOUND);
        }catch (Exception err){
            return new ResponseEntity<>(err.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping()
    public ResponseEntity createProduct(@RequestBody NewProductDTO productDTO){
        try {

            if (productDTO != null)
                return new ResponseEntity<>(_productService.createProduct(productDTO), HttpStatus.OK);

            throw new IllegalArgumentException("Product not informed");
        }catch (NotFoundException err){
            return new ResponseEntity<>(err.getMessage(), HttpStatus.NOT_FOUND);
        }catch (IllegalArgumentException err){
            return new ResponseEntity<>(err.getMessage(), HttpStatus.BAD_REQUEST);
        }catch (Exception err){
            return new ResponseEntity<>(err.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping()
    public ResponseEntity editProduct(@RequestBody EditProductDTO product) {
        try {
            if(product != null)
                return new ResponseEntity<>(_productService.editProduct(product), HttpStatus.OK);

            throw new IllegalArgumentException("Product not informed");

        } catch (IllegalArgumentException err) {
            return new ResponseEntity<>(err.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (NotFoundException err) {
            return new ResponseEntity<>(err.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception err) {
            return new ResponseEntity<>(err.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteProduct(@PathVariable long id){
        try{
            if(id > 0)
                return new ResponseEntity<>(_productService.deleteProduct(id), HttpStatus.OK);

            throw new IllegalArgumentException("Product not informed");

        } catch (NotFoundException err) {
            return new ResponseEntity<>(err.getMessage(), HttpStatus.NOT_FOUND);
        } catch (IllegalArgumentException err) {
            return new ResponseEntity<>(err.getMessage(), HttpStatus.BAD_REQUEST);
        }catch (Exception err){
            return new ResponseEntity<>(err.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
