package com.logistica.pdv.controller;

import com.logistica.pdv.entity.Seller;
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
            return new ResponseEntity(_sellerService.getAllSellers(), HttpStatus.OK);
        }catch (Exception ex) {
            return new ResponseEntity(ex.getMessage() ,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<Seller> getSellerById(@PathVariable long id){
        try {
            Seller sellerById = _sellerService.getSellerById(id);

            if(sellerById != null)
                return new ResponseEntity(sellerById ,HttpStatus.OK);

            return new ResponseEntity("Seller Not Found", HttpStatus.NOT_FOUND);

        }catch (Exception ex) {
            return new ResponseEntity(ex.getMessage() ,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping()
    public ResponseEntity<Seller> createSeller(@RequestBody Seller seller){
        try {
            return new ResponseEntity(_sellerService.createSeller(seller), HttpStatus.OK);
        }catch (Exception ex) {
            return new ResponseEntity(ex.getMessage() ,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping()
    public ResponseEntity<Seller> editSeller(@RequestBody Seller seller){
        try {
            Seller editedSeller = _sellerService.editSeller(seller);

            if(editedSeller != null)
                return new ResponseEntity(editedSeller, HttpStatus.OK);

            return new ResponseEntity("Seller not found", HttpStatus.NOT_FOUND);
        }catch (Exception ex){
            return new ResponseEntity(ex.getMessage() ,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping()
    public ResponseEntity deleteSeller(@RequestBody Seller seller){
        try {
            Seller deletedSeller = _sellerService.deleteSeller(seller);

            if(deletedSeller != null)
                return new ResponseEntity(deletedSeller , HttpStatus.OK);

            return new ResponseEntity("Seller not found", HttpStatus.NOT_FOUND);

        }catch (Exception ex){
            return new ResponseEntity(ex.getMessage() ,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
