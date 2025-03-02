package com.vita.bookworm.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vita.Models.RentDetails;
import com.vita.bookworm.Repository.RentDetailsRepository;

@Service
public class RentDetailsService {
    @Autowired
    private RentDetailsRepository rentDetailsRepository;

    public RentDetails addRentDetails(RentDetails rd) {
        if (rd == null) return null;
        return rentDetailsRepository.save(rd);
    }
}
