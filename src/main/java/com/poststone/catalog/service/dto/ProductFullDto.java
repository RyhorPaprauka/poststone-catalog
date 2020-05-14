package com.poststone.catalog.service.dto;

import com.poststone.catalog.database.entities.Description;
import com.poststone.catalog.database.entities.Image;
import com.poststone.catalog.database.entities.Material;
import com.poststone.catalog.database.entities.Parameters;
import com.poststone.catalog.database.entities.enam.ProductType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductFullDto {

    private UUID id;
    private ProductType type;
    private Integer price;
    private String icon;
    private Parameters parameters;
    private Description description;
    private Set<Image> images;
    private Set<Material> materials;
}
