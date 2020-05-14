package com.poststone.catalog.controller;

import com.poststone.catalog.database.entities.Material;
import com.poststone.catalog.database.entities.enam.ProductType;
import com.poststone.catalog.service.ProductService;
import com.poststone.catalog.service.dto.ProductFullDto;
import com.poststone.catalog.service.dto.ProductListDto;
import com.poststone.catalog.service.vo.ProductFilter;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import static com.poststone.catalog.controller.util.UrlPath.BASE;
import static com.poststone.catalog.controller.util.UrlPath.PRODUCT;

@RestController
@RequestMapping(BASE + PRODUCT)
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ProductController {

    private final ProductService productService;

    @GetMapping
    private ResponseEntity<Page<ProductListDto>> findAll(
            @RequestParam(required = false, defaultValue = "GRAVESTONE") ProductType type,
            @RequestParam(required = false) Double lowerPrice,
            @RequestParam(required = false) Double higherPrice,
            @RequestParam(required = false) Set<Material> materials,
            Pageable pageable) {
        ProductFilter filter = ProductFilter.builder()
                .type(type)
                .lowerPrice(lowerPrice)
                .higherPrice(higherPrice)
                .build();
        Optional.ofNullable(materials).ifPresent(filter.getMaterials()::addAll);

        return ResponseEntity.ok(productService.findAll(filter, pageable));
    }

    @GetMapping("/{id}")
    private ResponseEntity<ProductFullDto> findOne(@PathVariable UUID id) {
        return productService.findOne(id)
                .map(ResponseEntity::ok)
                .orElseGet(ResponseEntity.notFound()::build);
    }
}
