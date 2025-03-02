package com.vita.bookworm.Service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vita.Models.CartDetails;
import com.vita.Models.CustomerMaster;
import com.vita.Models.MyShelf;
import com.vita.Models.ProductMaster;
import com.vita.Models.RentDetails;
import com.vita.Models.ShelfDetails;
import com.vita.bookworm.Repository.MyShelfRepository;

import jakarta.transaction.Transactional;


@Service
public class MyShelfServices  {
    private MyShelfRepository myShelfRepository;    

    @Autowired
    private ShelfDetailsService shelfDetailsService;

    @Autowired
    private CustomerMasterServices customerMasterServices;

    @Autowired
    private CartServices cartServices;

    @Autowired
    private RentDetailsService rentDetailsService;
    
    @Autowired
    public MyShelfServices(MyShelfRepository myShelfRepository) {
        this.myShelfRepository = myShelfRepository;
    }

    public List<ProductMaster> getShelfProducts(int myShelfId) {
        return shelfDetailsService.getShelfProducts(myShelfId);
        
    }

    public MyShelf addBook(MyShelf myShelf) {
        myShelf.setNoOfBooks(myShelf.getNoOfBooks() + 1);
        MyShelf m = myShelfRepository.save(myShelf);
        return m;
    }

    public MyShelf getMyShelf(int custId) {
        myShelfRepository.getCustomerById(custId);
        return null;
    }

    @Transactional
    public void addProducts(Integer custId) {
        if (custId == null ) return;
        //find customer
        CustomerMaster tcm= customerMasterServices.getCustomerMasterById(custId);
        if (tcm == null) return;
        
        // find cart
        Integer c = cartServices.getActiveCart(custId);
        if (c == null) return;

        //get Details
        List<CartDetails> cd = cartServices.getDetails(c);
        if (cd == null || cd.isEmpty()) return;
        for (CartDetails tc : cd){
            //shelf item
            ShelfDetails t = new ShelfDetails();
            t.setProduct(tc.getProductId());
            t.setShelf(tcm.getShelfId());
            t.setTranType(tc.getIsRented() ? "R" : "P");
            if (tc.getIsRented()){
                //rented item
                RentDetails trd = new RentDetails();
                trd = rentDetailsService.addRentDetails(trd);
                trd.setCustomerId(tcm);
                trd.setProductId(tc.getProductId());
                trd.setRentStartDate(LocalDate.now());
                trd.setRentStatus("R");
                trd.setRentEndDate(LocalDate.now().plusDays(tc.getRentNoOfDays()));
                t.setRentDetails(trd);
            }
            //setting price
            t.setBasePrice(tc.getProductId().getProductBasePrice());
            shelfDetailsService.addProduct(t);
            tcm.getShelfId().setNoOfBooks(tcm.getShelfId().getNoOfBooks() + 1);
        }

    }
}
