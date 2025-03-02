package com.vita.Models;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Entity
@Data
public class CustomerMaster {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private int customerId;

    @Column(nullable = false, unique = true)
    @Pattern(regexp = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$")
    private String email;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String password;

    private LocalDate dob;

    private Integer age;

    private String pan;

    @Pattern(regexp = "^[0-9]{10}$")
    private String phoneNumber;

    @OneToOne(targetEntity = MyShelf.class)
    @JoinColumn(name = "shelf_id", nullable = true)
    private MyShelf shelfId;
}