package com.logistica.pdv.service;

import com.logistica.pdv.DTO.EditProductDTO;
import com.logistica.pdv.DTO.NewProductDTO;
import com.logistica.pdv.entity.Product;
import com.logistica.pdv.entity.Seller;
import com.logistica.pdv.exceptions.NotFoundException;
import com.logistica.pdv.repository.IProductRepository;
import com.logistica.pdv.repository.ISellerRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@NoArgsConstructor
public class ProductService {
    @Autowired
    private IProductRepository _productRepository;

    @Autowired
    private ISellerRepository _sellerRepository;

    public List<Product> getAllProducts(){
        return _productRepository.findAll();
    }

    public Product getProductById(long id){
        Optional<Product> product = _productRepository.findById(id);

        if(product.isPresent())
            return product.get();

        throw new NotFoundException("Product not found");
    }

    public Product createProduct(NewProductDTO productDTO){
        Product product = new Product();

        product.setPrice(productDTO.getPrice());
        product.setDescription(productDTO.getDescription());
        product.setSaleDate(LocalDate.now());

        Optional<Seller> seller = _sellerRepository.findById(productDTO.getSellerID());

        if(seller.isPresent()){
            product.setSeller(seller.get());
            return _productRepository.save(product);
        }

        throw new NotFoundException("Seller not found");
    }

    @Transactional
    public Product editProduct(EditProductDTO productDTO){
        Optional<Product> optionalProduct = _productRepository.findById(productDTO.getId());

        if(optionalProduct.isPresent()) {
            Product product = new Product();
            String description = productDTO.getDescription() != null ?
                                                        productDTO.getDescription() :
                                                        optionalProduct.get().getDescription();

            Seller seller = productDTO.getSellerID() != null ?
                                                    _sellerRepository.findById(productDTO.getSellerID()).get() :
                                                    optionalProduct.get().getSeller();

            product.setId(optionalProduct.get().getId());
            product.setSaleDate(optionalProduct.get().getSaleDate());
            product.setSeller(seller);
            product.setDescription(description);
            product.setPrice(productDTO.getPrice());

            return _productRepository.save(product);
        }

        throw new NotFoundException("Product not found");
    }

    public Product deleteProduct(long id){
        Optional<Product> product = _productRepository.findById(id);

        if(product.isPresent()) {
            _productRepository.deleteById(id);
            return product.get();
        }

        throw new NotFoundException("Product not informed");
    }
}
