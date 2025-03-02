package com.vita.bookworm.DTO;

import lombok.Data;

@Data
public class FilterHelper {
    private Integer langId;
    private Integer genreId;
    private Integer TypeId;
    private String productName;
    private String authorName;
}
