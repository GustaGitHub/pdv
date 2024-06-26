package com.logistica.pdv.service;

import com.logistica.pdv.DTO.EditSellerDTO;
import com.logistica.pdv.DTO.NewSellerDTO;
import com.logistica.pdv.entity.Product;
import com.logistica.pdv.entity.Seller;
import com.logistica.pdv.exceptions.NotFoundException;
import com.logistica.pdv.repository.IProductRepository;
import com.logistica.pdv.repository.ISellerRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
        
        throw new NotFoundException("Seller not found");
    }

    public Seller createSeller(NewSellerDTO sellerDTO){
        Seller seller = new Seller();
        seller.setName(sellerDTO.getName());

        return _sellerRepository.save(seller);
    }

    public Seller editSeller(EditSellerDTO sellerDTO){
        Optional<Seller> sellerById = _sellerRepository.findById(sellerDTO.getId());

        if(sellerById.isPresent()) {
            Seller seller = new Seller();
            seller.setName(sellerDTO.getName());
            seller.setId(sellerDTO.getId());

            return _sellerRepository.save(seller);
        }

        throw new NotFoundException("Seller not found");
    }

    @Transactional
    public Seller deleteSeller(long id){
        Optional<Seller> sellerById = _sellerRepository.findById(id);

        if(sellerById.isPresent()) {

            for (Product product : sellerById.get().getProducts()){
                _productRepository.deleteById(product.getId());
            }

            _sellerRepository.deleteById(sellerById.get().getId());
            return sellerById.get();
        }

        throw new NotFoundException("Seller not found");
    }
}
