package com.vita.bookworm.DTO;

import lombok.Data;

@Data
public class CartHelper {
        private int custId;
        private int prodId;
        private boolean isRented;
        private Integer noOfDays;
}
