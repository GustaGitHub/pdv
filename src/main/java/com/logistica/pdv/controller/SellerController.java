package com.logistica.pdv.controller;

import com.logistica.pdv.entity.Seller;
import com.logistica.pdv.exceptions.NotFoundException;
import com.logistica.pdv.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/seller")
public class SellerController {

    @Autowired
    private SellerService _sellerService;

    @GetMapping()
    public ResponseEntity<List<Seller>> getAllSellers(){
        try {
            return new ResponseEntity<>(_sellerService.getAllSellers(), HttpStatus.OK);
        }catch (Exception ex) {
            return new ResponseEntity(ex.getMessage() ,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<Seller> getSellerById(@PathVariable long id){
        try {
            Seller sellerById = _sellerService.getSellerById(id);

            if(sellerById != null)
                return new ResponseEntity<>(sellerById ,HttpStatus.OK);

            throw new IllegalArgumentException("Seller not informed");

        }catch (NotFoundException ex){
            return new ResponseEntity(ex.getMessage() ,HttpStatus.NOT_FOUND);
        }catch (IllegalArgumentException ex){
            return new ResponseEntity(ex.getMessage() ,HttpStatus.BAD_REQUEST);
        }catch (Exception ex) {
            return new ResponseEntity(ex.getMessage() ,HttpStatus.INTERNAL_SERVER_ERROR);
        } 
    }

    @PostMapping()
    public ResponseEntity<Seller> createSeller(@RequestBody Seller seller){
        try {
            if(seller != null)
                return new ResponseEntity<>(_sellerService.createSeller(seller), HttpStatus.OK);

            throw new IllegalArgumentException("Seller not informed");
        }catch (IllegalArgumentException ex) {
            return new ResponseEntity(ex.getMessage() ,HttpStatus.BAD_REQUEST);
        }catch (Exception ex) {
            return new ResponseEntity(ex.getMessage() ,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping()
    public ResponseEntity<Seller> editSeller(@RequestBody Seller seller){
        try {
            if(seller != null)
                return new ResponseEntity<>(_sellerService.editSeller(seller), HttpStatus.OK);

            throw new IllegalArgumentException("Seller not informed");
        }catch (NotFoundException ex){
            return new ResponseEntity(ex.getMessage() ,HttpStatus.NOT_FOUND);
        }catch (IllegalArgumentException ex){
            return new ResponseEntity(ex.getMessage() ,HttpStatus.BAD_REQUEST);
        }catch (Exception ex){
            return new ResponseEntity(ex.getMessage() ,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteSeller(@PathVariable long id){
        try {
            if(id > 0)
                return new ResponseEntity<>(_sellerService.deleteSeller(id), HttpStatus.OK);

            throw new IllegalArgumentException("Seller not informed");
        }catch (NotFoundException ex){
            return new ResponseEntity(ex.getMessage(), HttpStatus.NOT_FOUND);
        }catch (IllegalArgumentException ex){
            return new ResponseEntity(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }catch (Exception ex){
            return new ResponseEntity(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
