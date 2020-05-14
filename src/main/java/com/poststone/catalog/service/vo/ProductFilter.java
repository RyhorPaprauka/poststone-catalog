package com.poststone.catalog.service.vo;

import com.poststone.catalog.database.entities.Material;
import com.poststone.catalog.database.entities.enam.ProductType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
public class ProductFilter {

    private ProductType type;
    private Double lowerPrice;
    private Double higherPrice;
    @Builder.Default
    private Set<Material> materials = new HashSet<>();
}
