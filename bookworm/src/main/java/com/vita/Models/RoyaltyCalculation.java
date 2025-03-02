package com.vita.Models;
import java.time.LocalDate;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class RoyaltyCalculation {

    @Id 
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="RoyCalId", length=10)
    private Integer royaltyCalculationId;

	@ManyToOne
	private BeneficiaryMaster beneficiaryMaster;

	@Column(nullable=false)
    private LocalDate royaltyTranDate;

    @ManyToOne
    private ProductMaster product;

	@Column(nullable=false)
    private Integer quantity;

	@Column(nullable=false)
    private String transactionType;

	@Column(nullable=false)
    private Double salesPrice;

	@Column(nullable=false)
    private Double basePrice;

	@Column(nullable=false)
    private Double royaltyOnBasePrice;

}