package com.vita.Models;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class ProductMaster {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Integer productId;

    @Column(nullable = true)
    private String productName;

    @Column(nullable = true)
    private String productEnglishName;

    @ManyToOne
    @JoinColumn(name = "type_id", nullable = true, referencedColumnName = "type_id")
    private ProductTypeMaster productType;

    @Column(nullable = false)
    private String productPath;

    @Column(nullable = true)
    private Double productBasePrice;

    @Column(nullable = true)
    private Double productSpCost;

    @Column(nullable = true)
    private Double productOfferPrice;

    @Column(nullable = true)
    private LocalDate productOffPriceExpiryDate;

    @Column(nullable = true)
    private String productDescriptionShort;

    @Column(nullable = true)
    private String productDescriptionLong;

    @Column(nullable = true)
    private String productIsbn;

    @ManyToOne
    @JoinColumn(name = "author_id", nullable = true)
    private AuthorMaster productAuthor;

    @ManyToOne
    @JoinColumn(name = "language_id", nullable = true)
    private LanguageMaster productLang;

    @ManyToOne
    @JoinColumn(name = "genre_id", nullable = true)
    private GenreMaster productGenre;

    private Boolean isRentable; // Y/N

    @Column(nullable = true)
    private Double rentPerDay;

    @Column(nullable = true, columnDefinition = "int default 3")
    private Integer minRentDays;
}
