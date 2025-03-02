package com.vita.bookworm.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vita.Models.AuthorMaster;
import com.vita.Models.ProductMaster;
import com.vita.bookworm.DTO.FilterHelper;
import com.vita.bookworm.DTO.ProductDiscout;
import com.vita.bookworm.Repository.ProductMasterRepository;

import jakarta.transaction.Transactional;



@Service
public class ProductMasterService {
    @Autowired
    private ProductMasterRepository productMasterRepository;

    @Autowired
    private AuthorService authorService;

    @Transactional
    public void addDiscount(ProductDiscout p ) {
        ProductMaster temp = getProductById(p.getProductId());
        if ( temp == null) {
            return;
        }
        if (temp.getProductBasePrice() < p.getOfferPrice()){
            System.out.println("Offer price is greater than base price");
            return;
        }
        temp.setProductOfferPrice(p.getOfferPrice());
        temp.setProductSpCost(temp.getProductBasePrice() - p.getOfferPrice());
    }

    public ProductMaster getProductById(int id) {
        return productMasterRepository.findById(id).orElse(null);
    }

    public List<ProductMaster> getProduct(FilterHelper filter) {
        List<ProductMaster> products = productMasterRepository.getProduct(filter.getTypeId(), filter.getProductName(),filter.getLangId(), filter.getGenreId() , filter.getAuthorName());
        return products;
    }

    public List<ProductMaster> getAllProducts() {
        return productMasterRepository.findAll();
    }

    public ProductMaster addProduct(ProductMaster product) {
        return productMasterRepository.save(product);
    }

    public ProductMaster updateProduct(ProductMaster product) {
        return productMasterRepository.save(product);
    }

    public void deleteProduct(int id) {
        productMasterRepository.deleteById(id);
    }

    public List<ProductMaster> getProductByName(String name) {
        return productMasterRepository.findByName(name);
    }

    public List<ProductMaster> getProductByType(int type) {
        return productMasterRepository.findByType(type);
    }

    public List<ProductMaster> getProductByGenre(int genre) {
        return productMasterRepository.findByGenre(genre);
    }

    public List<ProductMaster> getProductByLanguage(int language) {
        return productMasterRepository.findByLanguage(language);
    }

    public List<ProductMaster> getProductByAuthorName(String authorName) {
        List<AuthorMaster> a = authorService.getAuthorByName(authorName);
        List<ProductMaster> products = new ArrayList<>();
        for (AuthorMaster author : a) {
            products.addAll(productMasterRepository.findByAuthorId(author.getAuthorId()));
        }
        return products;
    }
}
