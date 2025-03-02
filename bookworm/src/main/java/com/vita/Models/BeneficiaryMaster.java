package com.vita.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Entity
@Data
public class BeneficiaryMaster {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer benId;
    
    @Column(name = "ben_name")
    private String benName;

    @Column(nullable = true)
    @Pattern(regexp = "^[a-zA-Z0-9_.-]+@[a-zA-Z0-9.-]+$")
    private String benEmail;

    @Column(nullable = true)
    @Pattern(regexp = "^[0-9]{10}$")
    private String benPhone;

    private String benBankName;

    private String benBankBranch;

    private String benBankAccNo;

    private String benIfsc;

    private String benAccType;

    private String benPan;
}