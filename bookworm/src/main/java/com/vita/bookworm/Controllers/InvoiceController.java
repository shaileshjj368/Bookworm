package com.vita.bookworm.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.vita.bookworm.Service.InvoiceService;
import com.vita.bookworm.Service.MyShelfServices;

@RestController
public class InvoiceController {
    @Autowired
    private InvoiceService invoiceService;

    @Autowired
    private MyShelfServices myShelfServices;

    @GetMapping("/user/checkout/{custId}")
    public void checkout(@PathVariable("custId") Integer id) {  
        if (id == null ) {
            System.out.println("null");
        }
        invoiceService.generateInvoice(id);
        myShelfServices.addProducts(id);
        invoiceService.afterCheckOut(id);
    }
}
