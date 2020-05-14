package com.poststone.catalog.service.dto;

import com.poststone.catalog.database.entities.enam.ProductType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductListDto {

    private UUID id;
    private ProductType type;
    private Integer price;
    private String icon;
}
