package com.logistica.pdv.service;

import com.logistica.pdv.entity.Product;
import com.logistica.pdv.entity.Seller;
import com.logistica.pdv.repository.IProductRepository;
import com.logistica.pdv.repository.ISellerRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@NoArgsConstructor
public class SellerService {

    @Autowired
    private ISellerRepository _sellerRepository;

    @Autowired
    private IProductRepository _productRepository;

    public List<Seller> getAllSellers(){
        return _sellerRepository.findAll();
    }

    public Seller getSellerById(long id){
        Optional<Seller> sellerOptional = _sellerRepository.findById(id);

        if(sellerOptional.isPresent())
            return sellerOptional.get();

        return null;
    }

    public Seller createSeller(Seller seller){
        return _sellerRepository.save(seller);
    }

    public Seller editSeller(Seller seller){
        Optional<Seller> sellerById = _sellerRepository.findById(seller.getId());

        if(sellerById.isPresent())
            return _sellerRepository.save(seller);

        return null;
    }

    @Transactional
    public Seller deleteSeller(Seller seller){
        Optional<Seller> sellerById = _sellerRepository.findById(seller.getId());

        if(sellerById.isPresent()) {

            for (Product product : sellerById.get().getProducts()){
                _productRepository.deleteById(product.getId());
            }

            _sellerRepository.deleteById(seller.getId());
            return sellerById.get();
        }

        return null;
    }
}
